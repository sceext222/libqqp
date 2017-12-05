package org.sceext.android_hook.qq.log_msg

import java.util.List

import de.robv.android.xposed.XposedHelpers.getIntField
import de.robv.android.xposed.XposedHelpers.getObjectField
import de.robv.android.xposed.XposedHelpers.setIntField
import de.robv.android.xposed.XposedHelpers.setObjectField
import de.robv.android.xposed.XposedHelpers.callStaticMethod
import de.robv.android.xposed.XposedHelpers.callMethod

import org.sceext.android_hook.qq.log_msg.util.log_debug


const val CMD_TEST: String = "libqqp:test"
const val SEND_TEXT: String = "+ test ok 666"

const val F_MSG: String = "msg"
const val F_FRIEND_UIN: String = "frienduin"
const val F_IS_TROOP: String = "istroop"
const val F_SELF_UIN: String = "selfuin"
const val F_SENDER_UIN: String = "senderuin"
const val F_IS_SEND: String = "issend"
const val F_A: String = "a"


fun on_msg(h: Hooks, list: List<Object?>?) {
    if (list != null) {
        for (i in list) {
            if (i != null) {
                _on_one_msg(h, i)
            }
        }
    }
}

fun _on_one_msg(h: Hooks, m: Object) {
    val text: String? = getObjectField(m, F_MSG) as String?
    if (text == null) {
        return
    }
    val friend_uin: String = getObjectField(m, F_FRIEND_UIN) as String
    val is_troop: Int = getIntField(m, F_IS_TROOP)
    val self_uin: String = getObjectField(m, F_SELF_UIN) as String
    val sender_uin: String = getObjectField(m, F_SENDER_UIN) as String
    // DEBUG
    log_debug("got msg: [${self_uin}] ${sender_uin} -> (${is_troop})${friend_uin}: ${text}")
    // check cmd
    if ((self_uin == sender_uin) and (text.trim() == CMD_TEST)) {
        log_debug("got cmd: ${CMD_TEST}, send to ${friend_uin}")

        // call MessageRecordFactory.a(int) -> MessageRecord
        val msg: Object = callStaticMethod(h.MessageRecordFactory, F_A, INT_MSG_TYPE_TEXT) as Object
        // set MessageRecord
        setObjectField(msg, F_FRIEND_UIN, friend_uin)
        setIntField(msg, F_IS_TROOP, is_troop)
        setObjectField(msg, F_SELF_UIN, self_uin)
        setObjectField(msg, F_SENDER_UIN, self_uin)
        setIntField(msg, F_IS_SEND, 1)
        setObjectField(msg, F_MSG, SEND_TEXT)
        // call QQMessageFacade.a(MessageRecord, MessageObserver)
        callMethod(h.qq_message_facade, F_A, msg, null)
    }
}
