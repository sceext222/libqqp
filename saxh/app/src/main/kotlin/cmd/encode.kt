package org.sceext.saxh.cmd

import de.robv.android.xposed.XposedHelpers.setIntField
import de.robv.android.xposed.XposedHelpers.setObjectField

import org.sceext.saxh.hook.Hooks
import org.sceext.saxh.msg.INT_MSG_TYPE_TEXT
import org.sceext.saxh.msg.F_SELF_UIN
import org.sceext.saxh.msg.F_SENDER_UIN
import org.sceext.saxh.msg.F_FRIEND_UIN
import org.sceext.saxh.msg.F_IS_TROOP
import org.sceext.saxh.msg.F_IS_SEND
import org.sceext.saxh.msg.F_MSG


// simple text msg
fun encode_msg_text(h: Hooks, self_uin: String, friend_uin: String, is_troop: Int, text: String): Object {
    val o = h.gen_msg(INT_MSG_TYPE_TEXT)
    // set fields
    setObjectField(o, F_SELF_UIN, self_uin)
    setObjectField(o, F_SENDER_UIN, self_uin)
    setObjectField(o, F_FRIEND_UIN, friend_uin)
    setIntField(o, F_IS_TROOP, is_troop)
    setIntField(o, F_IS_SEND, 1)

    setObjectField(o, F_MSG, text)

    return o
}
