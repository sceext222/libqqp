package org.sceext.android_hook.qq.qlog

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.qlog.Config
import org.sceext.android_hook.qq.qlog.util.log_debug
import org.sceext.android_hook.qq.qlog.util.print_string


fun print_string_array(a: Array<String>?): String {
    // TODO
    return "TODO"
}

fun print_throwable(e: Throwable?): String {
    // TODO
    return "TODO"
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

        // after: isColorLevel() -> boolean

        // after: isDevelopLevel() -> boolean

        // before: p(String, String)

        // before: e(String, int, String, Throwable)

        // before: w(String, int, String, Throwable)

        // before: i(String, int, String, Throwable)

        // before: d(String, int, String, Throwable)

        // before: doReportLogSelf(int, String, String, boolean)

        // before: syncReportLogSelf(int, String, String, String)

        // before: startColorLog(String[])

        // before: endColorLog(String[], int, boolean, String)

        // after: isDebugVersion() -> boolean

        log_debug("hooked.")
    }
}
