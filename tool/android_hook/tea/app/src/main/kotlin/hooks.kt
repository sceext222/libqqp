package org.sceext.android_hook.qq.tea

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.tea.Config
import org.sceext.android_hook.qq.tea.util.log_debug
import org.sceext.android_hook.qq.tea.util.print_bytes


class Hooks(val cl: ClassLoader) {
    val ENCRYPT: String = "encrypt"
    val DECRYPT: String = "decrypt"

    fun init_hooks() {
        // encrypt(byte[], int, int, byte[]) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, ENCRYPT, ByteArray::class.java, Int::class.java, Int::class.java, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                val data2 = print_bytes(param.args[3] as ByteArray?)
                val result = print_bytes(param.result as ByteArray?)
                // improve output style
                log_debug("${ENCRYPT}: ${param.args[1] as Int}, ${param.args[2] as Int}, ${data2}\n    ${data}\n -> ${result}")
            }
        })

        // decrypt(byte[], int, int, byte[]) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, DECRYPT, ByteArray::class.java, Int::class.java, Int::class.java, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                val data2 = print_bytes(param.args[3] as ByteArray?)
                val result = print_bytes(param.result as ByteArray?)

                log_debug("${DECRYPT}: ${param.args[1] as Int}, ${param.args[2] as Int}, ${data2}\n    ${data}\n -> ${result}")
            }
        })

        log_debug("hooked.")
    }
}
