package org.sceext.android_hook.qq.ecdh

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.ecdh.Config
import org.sceext.android_hook.qq.ecdh.util.log_debug
import org.sceext.android_hook.qq.ecdh.util.print_bytes


class Hooks(val cl: ClassLoader) {
    // TODO

    fun init_hooks() {
        // TODO

        log_debug("hooked.")
    }
}
