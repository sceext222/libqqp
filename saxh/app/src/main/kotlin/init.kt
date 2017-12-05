package org.sceext.saxh

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

import org.sceext.saxh.hook.util.debug_hook_package
import org.sceext.saxh.hook.Hooks


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
