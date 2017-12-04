package org.sceext.android_hook.qq.log_msg

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.log_msg.Config
import org.sceext.android_hook.qq.log_msg.util.log_debug
import org.sceext.android_hook.qq.log_msg.util.print_string


class Hooks(val cl: ClassLoader) {
    // TODO

    fun init_hooks() {
        // TODO

        log_debug("hooked.")
    }
}
