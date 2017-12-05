package org.sceext.saxh.cmd

import de.robv.android.xposed.XposedHelpers.getIntField
import de.robv.android.xposed.XposedHelpers.getObjectField

import org.sceext.saxh.msg.F_MSG_TYPE
import org.sceext.saxh.msg.F_SELF_UIN
import org.sceext.saxh.msg.F_FRIEND_UIN
import org.sceext.saxh.msg.F_IS_TROOP
import org.sceext.saxh.msg.F_SENDER_UIN
import org.sceext.saxh.msg.F_MSG
import org.sceext.saxh.msg.F_IS_SEND


// text: msg text (field: `msg`)
data class DecodedMsg(
    var msg_type: Int,
    var self_uin: String,
    var friend_uin: String,
    var is_troop: Int,
    var sender_uin: String,

    var text: String?,

    var is_send: Int
)
// TODO support more fields


fun decode_msg(m: Object): DecodedMsg {
    val msg_type = getIntField(m, F_MSG_TYPE)
    val self_uin = getObjectField(m, F_SELF_UIN) as String
    val friend_uin = getObjectField(m, F_FRIEND_UIN) as String
    val is_troop = getIntField(m, F_IS_TROOP)
    val sender_uin = getObjectField(m, F_SENDER_UIN) as String

    val text = getObjectField(m, F_MSG) as String?

    val is_send = getIntField(m, F_IS_SEND)

    return DecodedMsg(
        msg_type, self_uin, friend_uin, is_troop, sender_uin,
        text,
        is_send
    )
}
