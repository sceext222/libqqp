package org.sceext.android_hook.qq.tea.util

import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

import org.sceext.android_hook.qq.tea.Config


fun log_debug(text: String) {
    var o = "${Config.DEBUG_PREFIX}: DEBUG: ${text}"
    XposedBridge.log(o)
}

fun debug_hook_package(p: LoadPackageParam) {
    var o = "hook package: ${p.packageName}  uid = ${p.appInfo.uid}  process_name: ${p.processName}"
    if (p.isFirstApplication) {
        o += "  isFirstApplication"
    }
    log_debug(o)
}


fun print_string(text: String?): String {
    if (text == null) {
        return "null"
    }
    return "${text.length}[${text}]"
}

fun print_string_raw(text: String?): String {
    if (text == null) {
        return "<<null>>"
    }
    return " ${text}"
}


// TODO
