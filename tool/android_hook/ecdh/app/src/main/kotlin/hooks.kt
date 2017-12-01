package org.sceext.android_hook.qq.ecdh

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook

import org.sceext.android_hook.qq.ecdh.Config
import org.sceext.android_hook.qq.ecdh.util.log_debug
import org.sceext.android_hook.qq.ecdh.util.print_bytes
import org.sceext.android_hook.qq.ecdh.util.print_string


class Hooks(val cl: ClassLoader) {
    val GEN_ECDH_KEY_EX: String = "GenECDHKeyEx"
    val GENEREATE_KEY: String = "GenereateKey"
    val GET_C_PUB_KEY: String = "get_c_pub_key"
    val SET_C_PUB_KEY: String = "set_c_pub_key"
    val SET_C_PRI_KEY: String = "set_c_pri_key"
    val GET_G_SHARE_KEY: String = "get_g_share_key"
    val SET_G_SHARE_KEY: String = "set_g_share_key"
    val CAL_SHARE_KEY_MD5_BY_PEER_PUBLIC_KEY: String = "calShareKeyMd5ByPeerPublicKey"
    val INIT_SHARE_KEY_BY_DEFAULT: String = "initShareKeyByDefault"
    val INIT_SHARE_KEY: String = "initShareKey"

    fun init_hooks() {
        // after: GenECDHKeyEX(String, String, String) -> int
        findAndHookMethod(Config.HOOK_CLASS, cl, GEN_ECDH_KEY_EX, String::class.java, String::class.java, String::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val s1 = print_string(param.args[0] as String?)
                val s2 = print_string(param.args[1] as String?)
                val s3 = print_string(param.args[3] as String?)

                log_debug("${GEN_ECDH_KEY_EX}: ${s1}, ${s2}, ${s3} -> ${param.getResult() as Int}")
            }
        })

        // after: GenereateKey() -> int
        findAndHookMethod(Config.HOOK_CLASS, cl, GENEREATE_KEY, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                log_debug("${GENEREATE_KEY}: -> ${param.getResult() as Int}")
            }
        })

        // after: get_c_pub_key() -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, GET_C_PUB_KEY, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val result = print_bytes(param.getResult() as ByteArray?)
                log_debug("${GET_C_PUB_KEY}: -> ${result}")
            }
        })

        // before: set_c_pub_key(byte[])
        findAndHookMethod(Config.HOOK_CLASS, cl, SET_C_PUB_KEY, ByteArray::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                log_debug("${SET_C_PUB_KEY}: ${data}")
            }
        })

        // before: set_c_pri_key(byte[])
        findAndHookMethod(Config.HOOK_CLASS, cl, SET_C_PRI_KEY, ByteArray::class.java, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                log_debug("${SET_C_PRI_KEY}: ${data}")
            }
        })

        // after: get_g_share_key() -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, GET_G_SHARE_KEY, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val result = print_bytes(param.getResult() as ByteArray?)
                log_debug("${GET_G_SHARE_KEY}: -> ${result}")
            }
        })

        // before: set_g_share_key(byte[])
        findAndHookMethod(Config.HOOK_CLASS, cl, SET_G_SHARE_KEY, object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                log_debug("${SET_G_SHARE_KEY}: ${data}")
            }
        })

        // after: calShareKeyMd5ByPeerPublicKey(byte[]) -> byte[]
        findAndHookMethod(Config.HOOK_CLASS, cl, CAL_SHARE_KEY_MD5_BY_PEER_PUBLIC_KEY, ByteArray::class.java, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val data = print_bytes(param.args[0] as ByteArray?)
                val result = print_bytes(param.getResult() as ByteArray?)
                log_debug("${CAL_SHARE_KEY_MD5_BY_PEER_PUBLIC_KEY}: ${data} -> ${result}")
            }
        })

        // after: initShareKeyByDefault() -> int
        findAndHookMethod(Config.HOOK_CLASS, cl, INIT_SHARE_KEY_BY_DEFAULT, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                log_debug("${INIT_SHARE_KEY_BY_DEFAULT}: -> ${param.getResult() as Int}")
            }
        })

        // after: initShareKey() -> int
        findAndHookMethod(Config.HOOK_CLASS, cl, INIT_SHARE_KEY, object: XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                log_debug("${INIT_SHARE_KEY}: -> ${param.getResult() as Int}")
            }
        })

        log_debug("hooked.")
    }
}
