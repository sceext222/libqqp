package org.sceext.android_hook.qq.qlog

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.qlog.Config
import org.sceext.android_hook.qq.qlog.util.log_debug
import org.sceext.android_hook.qq.qlog.util.print_string


fun print_string_array(a: Array<String>?): String {
    if (a == null) {
        return "<<null>>"
    }
    return " " + a.joinToString(separator="\n")
}

fun print_throwable(e: Throwable?): String {
    if (e == null) {
        return ""
    }
    return e.getStackTrace().joinToString(separator="\n")
}


class Hooks(val cl: ClassLoader) {
    val INIT: String = "init"
    val IS_COLOR_LEVEL: String = "isColorLevel"
    val IS_DEVELOP_LEVEL: String = "isDevelopLevel"
    val P: String = "p"
    val E: String = "e"
    val W: String = "w"
    val I: String = "i"
    val D: String = "d"
    val DO_REPORT_LOG_SELF: String = "doReportLogSelf"
    val SYNC_REPORT_LOG_SELF: String = "syncReportLogSelf"
    val START_COLOR_LOG: String = "startColorLog"
    val END_COLOR_LOG: String = "endColorLog"
    val IS_DEBUG_VERSION: String = "isDebugVersion"  // FIXME

    fun init_hooks() {
        // before: init(String, String, String, long)
        findAndHookMethod(Config.HOOK_CLASS, cl, INIT, String::class.java, String::class.java, String::class.java, Long::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val t1 = print_string(param.args[0] as String?)
                val t2 = print_string(param.args[1] as String?)
                val t3 = print_string(param.args[2] as String?)
                log_debug("${INIT}: ${param.args[3] as Long}, ${t1}, ${t2}, ${t3}")
            }
        })

        // after: isColorLevel() -> boolean
        findAndHookMethod(Config.HOOK_CLASS, cl, IS_COLOR_LEVEL, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                // always return true
                param.result = true
            }
        })

        // after: isDevelopLevel() -> boolean
        findAndHookMethod(Config.HOOK_CLASS, cl, IS_DEVELOP_LEVEL, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                // always return true
                param.result = true
            }
        })

        // before: p(String, String)
        findAndHookMethod(Config.HOOK_CLASS, cl, P, String::class.java, String::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val t1 = print_string(param.args[0] as String?)
                val t2 = print_string(param.args[1] as String?)
                log_debug("${P}: ${t1}, ${t2}")
            }
        })

        // before: e(String, int, String, Throwable)
        findAndHookMethod(Config.HOOK_CLASS, cl, E, String::class.java, Int::class.java, String::class.java, Throwable::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                print_logs(E, param)
            }
        })

        // before: w(String, int, String, Throwable)
        findAndHookMethod(Config.HOOK_CLASS, cl, W, String::class.java, Int::class.java, String::class.java, Throwable::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                print_logs(W, param)
            }
        })

        // before: i(String, int, String, Throwable)
        findAndHookMethod(Config.HOOK_CLASS, cl, I, String::class.java, Int::class.java, String::class.java, Throwable::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                print_logs(I, param)
            }
        })

        // before: d(String, int, String, Throwable)
        findAndHookMethod(Config.HOOK_CLASS, cl, D, String::class.java, Int::class.java, String::class.java, Throwable::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                print_logs(D, param)
            }
        })

        // before: doReportLogSelf(int, String, String, boolean)
        findAndHookMethod(Config.HOOK_CLASS, cl, DO_REPORT_LOG_SELF, Int::class.java, String::class.java, String::class.java, Boolean::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val t1 = print_string(param.args[1] as String?)
                val t2 = print_string(param.args[2] as String?)
                log_debug("${DO_REPORT_LOG_SELF}: ${param.args[0] as Int}, ${param.args[3] as Boolean}, ${t1}, ${t2}")
            }
        })

        // before: syncReportLogSelf(int, String, String, String)
        findAndHookMethod(Config.HOOK_CLASS, cl, SYNC_REPORT_LOG_SELF, Int::class.java, String::class.java, String::class.java, String::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val t1 = print_string(param.args[1] as String?)
                val t2 = print_string(param.args[2] as String?)
                val t3 = print_string(param.args[3] as String?)
                log_debug("${SYNC_REPORT_LOG_SELF}: ${param.args[0] as Int}, ${t1}, ${t2}, ${t3}")
            }
        })

        // before: startColorLog(String[])
        findAndHookMethod(Config.HOOK_CLASS, cl, START_COLOR_LOG, Array<String>::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val text = print_string_array(param.args[0] as Array<String>?)
                log_debug("${START_COLOR_LOG}: ${text}")
            }
        })

        // before: endColorLog(String[], int, boolean, String)
        findAndHookMethod(Config.HOOK_CLASS, cl, END_COLOR_LOG, Array<String>::class.java, Int::class.java, Boolean::class.java, String::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val t1 = print_string_array(param.args[0] as Array<String>?)
                val t2 = print_string(param.args[3] as String?)
                log_debug("${END_COLOR_LOG}: ${param.args[1] as Int}, ${param.args[2] as Boolean}, ${t2}, ${t1}")
            }
        })

        // after: isDebugVersion() -> boolean
        findAndHookMethod(Config.HOOK_CLASS, cl, IS_DEBUG_VERSION, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                // always return true
                param.result = true
            }
        })

        log_debug("hooked.")
    }

    // before: X(String, int, String, Throwable)
    fun print_logs(f: String, param: XC_MethodHook.MethodHookParam) {
        val t1 = print_string(param.args[0] as String?)
        val t2 = print_string(param.args[2] as String?)
        val t3 = print_throwable(param.args[3] as Throwable?)
        log_debug("${f}: ${param.args[1] as Int}, ${t1}, ${t2}, ${t3}")
    }
}
