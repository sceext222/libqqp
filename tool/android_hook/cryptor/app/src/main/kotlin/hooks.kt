package org.sceext.android_hook.qq.cryptor

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.cryptor.Config
import org.sceext.android_hook.qq.cryptor.util.log_debug
import org.sceext.android_hook.qq.cryptor.util.print_bytes


class Hooks(val cl: ClassLoader) {
    val DECRYPT: String = "decrypt"
    val ENCRYPT: String = "encrypt"
    val ENABLE_RESULT_RANDOM: String = "enableResultRandom"

    fun init_hooks() {
        // after: decrypt(byte[], int, int, byte[]) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, DECRYPT, ByteArray::class.java, Int::class.java, Int::class.java, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                val data2 = print_bytes(param.args[3] as ByteArray?)
                val result = print_bytes(param.result as ByteArray?)
                log_debug("${DECRYPT}: ${param.args[1] as Int}, ${param.args[2] as Int}, ${data2}, ${data} -> ${result}")
            }
        })

        // after: decrypt(byte[], byte[]) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, DECRYPT, ByteArray::class.java, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                val data2  = print_bytes(param.args[1] as ByteArray?)
                val result = print_bytes(param.result as ByteArray?)
                log_debug("${DECRYPT}: ${data2}, ${data} -> ${result}")
            }
        })

        // after: encrypt(byte[], byte[]) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, ENCRYPT, ByteArray::class.java, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                val data2 = print_bytes(param.args[1] as ByteArray?)
                val result = print_bytes(param.result as ByteArray?)
                log_debug("${ENCRYPT}: ${data2}, ${data} -> ${result}")
            }
        })

        // before: enableResultRandom(boolean)
        findAndHookMethod(Config.HOOK_CLASS, cl, ENABLE_RESULT_RANDOM, Boolean::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                log_debug("${ENABLE_RESULT_RANDOM}: ${param.args[0] as Boolean}")
            }
        })

        log_debug("hooked.")
    }
}
