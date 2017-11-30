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


const val HEX_CHAR: String = "0123456789abcdef"

fun print_bytes(data: ByteArray?): String {
    if (data == null) {
        return "null"
    }
    val o = StringBuilder(data.size * 3)
    for (b in data) {
        o.append(' ')
        o.append(HEX_CHAR[((b as Int) ushr 4) and 0x0f])
        o.append(HEX_CHAR[(b as Int) and 0x0f])
    }
    return o.toString()
}
