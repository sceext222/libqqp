package org.sceext.android_hook.qq.tea

class Config {
    companion object {
        // log text prefix
        const val DEBUG_PREFIX: String = "org.sceext.android_hook.qq.tea"

        const val HOOK_PACKAGE: String = "com.tencent.mobileqq"
        const val HOOK_CLASS: String = "oicq.wlogin_sdk.tools.cryptor"

        // logcat print large bytes
        const val MULTI_LINE_MIN_BYTE: Int = 512
        const val ONE_LINE_MAX_BYTE: Int = 64

        // DEBUG only
        const val PACKAGE_VERSION: String = "7.2.5 (744)"
    }
}
