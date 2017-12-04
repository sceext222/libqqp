package org.sceext.android_hook.qq.md5

import java.io.InputStream
import java.io.File

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.md5.Config
import org.sceext.android_hook.qq.md5.util.log_debug
import org.sceext.android_hook.qq.md5.util.print_bytes
import org.sceext.android_hook.qq.md5.util.print_string


fun print_input_stream(s: InputStream?): String {
    return "<<InputStream>>"
}

fun print_file(f: File?): String {
    if (f == null) {
        return "<<null>>"
    }
    return " ${f.absolutePath}"
}


class Hooks(val cl: ClassLoader) {
    val GET_MD5: String = "getMD5"
    //val B2IU: String = "b2iu"
    //val BYTE_HEX: String = "byteHEX"
    val TO_MD5_BYTE: String = "toMD5Byte"
    val TO_MD5: String = "toMD5"
    val GET_MD5_STRING: String = "getMD5String"
    val GET_FILE_MD5: String = "getFileMD5"

    fun init_hooks() {
        // getMD5(byte[]) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, GET_MD5, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                val result = print_bytes(param.result as ByteArray?)
                log_debug("${GET_MD5}: ${data} -> ${result}")
            }
        })

        // getMD5(InputStream, long) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, GET_MD5, InputStream::class.java, Long::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val result = print_bytes(param.result as ByteArray?)
                log_debug("${GET_MD5}: ${print_input_stream(param.args[0] as InputStream?)}, ${param.args[1] as Long} -> ${result}")
            }
        })

        // b2iu(byte) -> long
        //findAndHookMethod(Config.HOOK_CLASS, cl, B2IU, Byte::class.java, object: XC_MethodHook() {
        //    override fun afterHookedMethod(param: MethodHookParam) {
        //        log_debug("${B2IU}: ${param.args[0] as Byte} -> ${param.result as Long}")
        //    }
        //})

        // byteHEX(byte) -> String
        //findAndHookMethod(Config.HOOK_CLASS, cl, BYTE_HEX, Byte::class.java, object: XC_MethodHook() {
        //    override fun afterHookedMethod(param: MethodHookParam) {
        //        log_debug("${BYTE_HEX}: ${param.args[0] as Byte} -> ${param.result as String?}")
        //    }
        //})

        // toMD5Byte(byte[]) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, TO_MD5_BYTE, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                val result = print_bytes(param.result as ByteArray?)
                log_debug("${TO_MD5_BYTE}: ${data} -> ${result}")
            }
        })

        // toMD5Byte(String) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, TO_MD5_BYTE, String::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_string(param.args[0] as String?)
                val result = print_bytes(param.result as ByteArray?)
                log_debug("${TO_MD5_BYTE}: ${data} -> ${result}")
            }
        })

        // toMD5Byte(InputStream, long) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, TO_MD5_BYTE, InputStream::class.java, Long::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val result = print_bytes(param.result as ByteArray?)
                log_debug("${TO_MD5_BYTE}: ${print_input_stream(param.args[0] as InputStream?)}, ${param.args[1] as Long} -> ${result}")
            }
        })

        // toMD5(byte[]) -> String
        findAndHookMethod(Config.HOOK_CLASS, cl, TO_MD5, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                log_debug("${TO_MD5}: ${data} -> ${param.result as String?}")
            }
        })

        // toMD5(String) -> String
        findAndHookMethod(Config.HOOK_CLASS, cl, TO_MD5, String::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_string(param.args[0] as String?)
                log_debug("${TO_MD5}: ${data} -> ${param.result as String?}")
            }
        })

        // getMD5String(byte[]) -> String
        findAndHookMethod(Config.HOOK_CLASS, cl, GET_MD5_STRING, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                log_debug("${GET_MD5_STRING}: ${data} -> ${param.result as String?}")
            }
        })

        // getFileMD5(File file) -> String
        findAndHookMethod(Config.HOOK_CLASS, cl, GET_FILE_MD5, File::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                log_debug("${GET_FILE_MD5}: ${print_file(param.args[0] as File?)} -> ${param.result as String?}")
            }
        })

        log_debug("hooked.")
    }
}
