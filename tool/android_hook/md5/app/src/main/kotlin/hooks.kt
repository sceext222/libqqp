package org.sceext.android_hook.qq.md5

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.md5.Config
import org.sceext.android_hook.qq.md5.util.log_debug
import org.sceext.android_hook.qq.md5.util.print_bytes
import org.sceext.android_hook.qq.md5.util.print_string


class Hooks(val cl: ClassLoader) {
    // TODO

    fun init_hooks() {
        // TODO

        log_debug("hooked.")
    }
}
