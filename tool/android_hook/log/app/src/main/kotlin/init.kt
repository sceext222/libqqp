package org.sceext.android_hook.qq.log

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam


class InitXposed: IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (lpparam.packageName != Config.HOOK_PACKAGE) {
            return
        }
        // DEBUG
        XposedBridge.log("${Config.DEBUG_PREFIX}: DEBUG: hook package: ${lpparam.packageName}  uid = ${lpparam.appInfo.uid}")
        // TODO
    }
}
