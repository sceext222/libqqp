package org.sceext.android_hook.qq.log

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.log.Config
import org.sceext.android_hook.qq.log.util.log_debug
import org.sceext.android_hook.qq.log.util.print_string
import org.sceext.android_hook.qq.log.util.print_string_raw


class Hooks(val cl: ClassLoader) {
    val LOGI: String = "LOGI"
    val LOGD: String = "LOGD"
    val LOGW: String = "LOGW"

    private fun hooked(name: String) {
        log_debug("hooked ${name}")
    }

    fun init_hooks() {
        // LOGI(String)
        findAndHookMethod(Config.HOOK_CLASS, cl, LOGI, String::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val text: String? = param.args[0] as String?

                log_debug("${LOGI}: ${print_string_raw(text)}")
            }
        })
        hooked("LOGI(String)")

        // LOGI(String, String)
        findAndHookMethod(Config.HOOK_CLASS, cl, LOGI, String::class.java, String::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val text: String? = param.args[0] as String?
                val text2: String? = param.args[1] as String?

                log_debug("${LOGI}: ${print_string(text)}, ${print_string_raw(text2)}")
            }
        })
        hooked("LOGI(String, String)")

        // LOGD(String)
        findAndHookMethod(Config.HOOK_CLASS, cl, LOGD, String::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val text: String? = param.args[0] as String?

                log_debug("${LOGD}: ${print_string_raw(text)}")
            }
        })
        hooked("LOGD(String)")

        // LOGD(String, String)
        findAndHookMethod(Config.HOOK_CLASS, cl, LOGD, String::class.java, String::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val text: String? = param.args[0] as String?
                val text2: String? = param.args[1] as String?

                log_debug("${LOGD}: ${print_string(text)}, ${print_string_raw(text2)}")
            }
        })
        hooked("LOGD(String, String)")

        // LOGW(String, String)
        findAndHookMethod(Config.HOOK_CLASS, cl, LOGW, String::class.java, String::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val text: String? = param.args[0] as String?
                val text2: String? = param.args[1] as String?

                log_debug("${LOGW}: ${print_string(text)}, ${print_string_raw(text2)}")
            }
        })
        hooked("LOGW(String, String)")

        // LOGW(String, String, String)
        findAndHookMethod(Config.HOOK_CLASS, cl, LOGW, String::class.java, String::class.java, String::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val text: String? = param.args[0] as String?
                val text2: String? = param.args[1] as String?
                val text3: String? = param.args[2] as String?

                log_debug("${LOGW}: ${print_string(text)}, ${print_string(text2)}, ${print_string_raw(text3)}")
            }
        })
        hooked("LOGW(String, String, String)")
    }
}
