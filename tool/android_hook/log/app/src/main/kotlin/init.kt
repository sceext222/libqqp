package org.sceext.android_hook.qq.log

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

import org.sceext.android_hook.qq.log.util.debug_hook_package


class InitXposed: IXposedHookLoadPackage {
    lateinit var h: Hooks

    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (lpparam.packageName != Config.HOOK_PACKAGE) {
            return
        }
        debug_hook_package(lpparam)

        h = Hooks(lpparam.classLoader)
        h.init_hooks()
    }
}
