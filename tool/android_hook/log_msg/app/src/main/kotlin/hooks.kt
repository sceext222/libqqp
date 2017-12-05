package org.sceext.android_hook.qq.log_msg

import java.util.List

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XposedHelpers.findAndHookConstructor
import de.robv.android.xposed.XposedHelpers.findClass
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.log_msg.Config
import org.sceext.android_hook.qq.log_msg.util.log_debug
import org.sceext.android_hook.qq.log_msg.util.print_string


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
                val text = print_message_record(param.args[0] as List<Object?>?)
                val bs = "${param.args[2] as Boolean}, ${param.args[3] as Boolean}, ${param.args[4] as Boolean}, ${param.args[5] as Boolean}"
                log_debug("${A}: ${bs}, ${param.args[1] as Object?}, ${text}")
            }
            override fun afterHookedMethod(param: MethodHookParam) {
                call_on_msg(param.args[0] as List<Object?>?)
            }
        })

        // before: a(MessageRecord, MessageObserver, boolean)
        findAndHookMethod(Config.HOOK_CLASS, cl, A, MESSAGE_RECORD, MESSAGE_OBSERVER, Boolean::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val text = print_one_message_record(param.args[0] as Object?)
                log_debug("${A}: ${param.args[2] as Boolean}, ${param.args[1] as Object?}, ${text}")
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

    fun call_on_msg(list: List<Object?>?) {
        on_msg(this, list)
    }
}
