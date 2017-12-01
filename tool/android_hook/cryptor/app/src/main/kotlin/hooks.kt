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
        // TODO

        // after: decrypt(byte[], byte[])
        // TODO

        // after: encrypt(byte[], byte[])
        // TODO

        // before: enableResultRandom(boolean)
        // TODO

        log_debug("hooked.")
    }
}
