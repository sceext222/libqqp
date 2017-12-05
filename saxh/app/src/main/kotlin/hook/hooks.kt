package org.sceext.saxh.hook

import java.util.List

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XposedHelpers.findAndHookConstructor
import de.robv.android.xposed.XposedHelpers.findClass
import de.robv.android.xposed.XposedHelpers.callStaticMethod
import de.robv.android.xposed.XposedHelpers.callMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.saxh.Config
import org.sceext.saxh.hook.util.log_debug
import org.sceext.saxh.hook.util.print_string
import org.sceext.saxh.msg.print_message_record
import org.sceext.saxh.cmd.on_msg


class Hooks(val cl: ClassLoader) {
    val A: String = "a"
    val ENTITY_MANAGER: String = "com.tencent.mobileqq.persistence.EntityManager"
    val MESSAGE_RECORD: String = "com.tencent.mobileqq.data.MessageRecord"
    val MESSAGE_OBSERVER: String = "com.tencent.mobileqq.app.MessageObserver"
    val QQ_APP_INTERFACE: String = "com.tencent.mobileqq.app.QQAppInterface"
    val MESSAGE_RECORD_FACTORY: String = "com.tencent.mobileqq.service.message.MessageRecordFactory"

    var qq_message_facade: Object? = null
    var MessageRecordFactory: Class<*>? = null


    fun init_hooks() {
        // before: a(List<MessageRecord>, EntityManager, boolean, boolean, boolean, boolean)
        findAndHookMethod(Config.HOOK_CLASS, cl, A, List::class.java, ENTITY_MANAGER, Boolean::class.java, Boolean::class.java, Boolean::class.java, Boolean::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                print_msg(param.args[0] as List<Object?>?)
            }
            override fun afterHookedMethod(param: MethodHookParam) {
                call_on_msg(param.args[0] as List<Object?>?)
            }
        })

        // after: public QQMessageFacade(QQAppInterface)
        findAndHookConstructor(Config.HOOK_CLASS, cl, QQ_APP_INTERFACE, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                qq_message_facade = param.thisObject as Object?
                log_debug("${Config.HOOK_CLASS}: new ${qq_message_facade}")
            }
        })

        MessageRecordFactory = findClass(MESSAGE_RECORD_FACTORY, cl)

        log_debug("hooked.")
    }

    fun print_msg(list: List<Object?>?) {
        if (Config.debug_print_msg) {
            log_debug("print_msg  ${print_message_record(list)}")
        }
    }

    fun call_on_msg(list: List<Object?>?) {
        on_msg(this, list)
    }

    fun gen_msg(msg_type: Int): Object {
        // call MessageRecordFactory.a(int) -> MessageRecord
        return callStaticMethod(MessageRecordFactory, A, msg_type) as Object
    }

    fun send_msg(msg: Object) {
        // call QQMessageFacade.a(MessageRecord, MessageObserver)
        callMethod(qq_message_facade, A, msg, null)
    }
}
