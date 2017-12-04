package org.sceext.android_hook.qq.log_msg

import java.util.List

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.log_msg.Config
import org.sceext.android_hook.qq.log_msg.util.log_debug
import org.sceext.android_hook.qq.log_msg.util.print_string


class Hooks(val cl: ClassLoader) {
    val A: String = "a"
    val ENTITY_MANAGER: String = "com.tencent.mobileqq.persistence.EntityManager"

    fun init_hooks() {
        // before: a(List<MessageRecord>, EntityManager, boolean, boolean, boolean, boolean)
        findAndHookMethod(Config.HOOK_CLASS, cl, A, List::class.java, ENTITY_MANAGER, Boolean::class.java, Boolean::class.java, Boolean::class.java, Boolean::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val text = print_message_record(param.args[0] as List<Object?>?)
                val bs = "${param.args[2] as Boolean}, ${param.args[3] as Boolean}, ${param.args[4] as Boolean}, ${param.args[5] as Boolean}"
                log_debug("${A}: ${bs}, ${param.args[1] as Object?}, ${text}")
            }
        })

        log_debug("hooked.")
    }
}
