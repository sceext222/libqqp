package org.sceext.android_hook.qq.qlog.util

import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

import org.sceext.android_hook.qq.qlog.Config


fun log_raw(text: String) {
    XposedBridge.log(text)
}

// logcat print very-long text
fun logcat_text(text: String) {
    // print each line
    for (l in text.lines()) {
        log_raw(l)
    }
}

fun log_debug(text: String) {
    var o = "${Config.DEBUG_PREFIX}: DEBUG: ${text}"
    logcat_text(o)
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
    // check data length
    if (data.size < Config.MULTI_LINE_MIN_BYTE) {
        return "${data.size}[ ${_print_short_bytes(data)} ]"
    } else {
        return "${data.size}[\n${_print_long_bytes(data)}  ]"
    }
}

fun _print_short_bytes(data: ByteArray): String {
    val o = StringBuilder(data.size * 3)
    for (b in data) {
        o.append(' ')
        o.append(HEX_CHAR[(b.toInt() ushr 4) and 0x0f])
        o.append(HEX_CHAR[b.toInt() and 0x0f])
    }
    return o.toString()
}

fun _print_long_bytes(data: ByteArray): String {
    val o = StringBuilder(data.size * 3 + (data.size / Config.ONE_LINE_MAX_BYTE) * 5)
    // print each part as one line
    for (part in data.asIterable().chunked(Config.ONE_LINE_MAX_BYTE)) {
        o.append("    ")
        for (b in part) {
            o.append(' ')
            o.append(HEX_CHAR[(b.toInt() ushr 4) and 0x0f])
            o.append(HEX_CHAR[b.toInt() and 0x0f])
        }
        o.append('\n')
    }
    return o.toString()
}
