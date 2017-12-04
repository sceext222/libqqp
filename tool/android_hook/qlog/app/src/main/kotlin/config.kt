package org.sceext.android_hook.qq.qlog

class Config {
    companion object {
        // log text prefix
        const val DEBUG_PREFIX: String = "org.sceext.android_hook.qq.qlog"

        const val HOOK_PACKAGE: String = "com.tencent.mobileqq"
        const val HOOK_CLASS: String = "com.tencent.qphone.base.util.QLog"

        // logcat print large bytes
        const val MULTI_LINE_MIN_BYTE: Int = 512
        const val ONE_LINE_MAX_BYTE: Int = 64

        // DEBUG only
        const val PACKAGE_VERSION: String = "7.2.5 (744)"
    }
}
