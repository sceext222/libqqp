package org.sceext.saxh.cmd

import java.util.List

import de.robv.android.xposed.XposedHelpers.getIntField
import de.robv.android.xposed.XposedHelpers.getLongField
import de.robv.android.xposed.XposedHelpers.getShortField
import de.robv.android.xposed.XposedHelpers.getByteField
import de.robv.android.xposed.XposedHelpers.getObjectField

import org.sceext.saxh.msg.F_MSG_TYPE
import org.sceext.saxh.msg.F_SELF_UIN
import org.sceext.saxh.msg.F_FRIEND_UIN
import org.sceext.saxh.msg.F_IS_TROOP
import org.sceext.saxh.msg.F_SENDER_UIN
import org.sceext.saxh.msg.F_MSG
import org.sceext.saxh.msg.F_IS_SEND
import org.sceext.saxh.msg.F_AT_INFO_LIST
import org.sceext.saxh.msg.F_AT_INFO_TEMP_LIST
import org.sceext.saxh.msg.FA_UIN
import org.sceext.saxh.msg.FA_START_POS
import org.sceext.saxh.msg.FA_TEXT_LEN
import org.sceext.saxh.msg.FA_W_EXT_BUF_LEN
import org.sceext.saxh.msg.FA_FLAG


// text: msg text (field: `msg`)
data class DecodedMsg(
    var msg_type: Int,
    var self_uin: String,
    var friend_uin: String,
    var is_troop: Int,
    var sender_uin: String,

    var text: String?,

    var is_send: Int,
    var at_info: MutableList<AtInfo>,
    var is_at_me: Boolean = false
)
// TODO support more fields

data class AtInfo(
    var uin: String,
    var start_pos: Int,
    var text_len: Int,

    var ext_buf_len: Int,
    var flag: Int
)


fun decode_msg(m: Object): DecodedMsg {
    val msg_type = getIntField(m, F_MSG_TYPE)
    val self_uin = getObjectField(m, F_SELF_UIN) as String
    val friend_uin = getObjectField(m, F_FRIEND_UIN) as String
    val is_troop = getIntField(m, F_IS_TROOP)
    val sender_uin = getObjectField(m, F_SENDER_UIN) as String

    val text = getObjectField(m, F_MSG) as String?

    val is_send = getIntField(m, F_IS_SEND)

    val o = DecodedMsg(
        msg_type, self_uin, friend_uin, is_troop, sender_uin,
        text,
        is_send,
        mutableListOf<AtInfo>()
    )
    // at info
    _parse_at_info_list(o.at_info, getObjectField(m, F_AT_INFO_LIST) as List<Object?>?)
    _parse_at_info_list(o.at_info, getObjectField(m, F_AT_INFO_TEMP_LIST) as List<Object?>?)
    // check at me
    for (i in o.at_info) {
        if (i.uin == self_uin) {
            o.is_at_me = true
            break
        }
    }
    return o
}

fun _parse_at_info_list(o: MutableList<AtInfo>, list: List<Object?>?) {
    if (list == null) {
        return
    }
    for (i in list) {
        if (i != null) {
            o.add(parse_at_info(i))
        }
    }
}

fun parse_at_info(m: Object): AtInfo {
    val uin = getLongField(m, FA_UIN).toString()
    val start_pos = getShortField(m, FA_START_POS).toInt()
    val text_len = getShortField(m, FA_TEXT_LEN).toInt()
    val ext_buf_len = getShortField(m, FA_W_EXT_BUF_LEN).toInt()
    val flag = getByteField(m, FA_FLAG).toInt()

    return AtInfo(
        uin,
        start_pos, text_len,
        ext_buf_len, flag
    )
}
