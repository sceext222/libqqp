package org.sceext.saxh.cmd

import java.util.List

import org.sceext.saxh.hook.Hooks
import org.sceext.saxh.hook.util.log_debug


const val CMD_TEST: String = "libqqp:test"
const val SEND_TEXT: String = "+ test ok 666"


fun on_msg(h: Hooks, list: List<Object?>?) {
    if (list != null) {
        for (i in list) {
            if (i != null) {
                _on_one_msg(h, i)
            }
        }
    }
}

fun _on_one_msg(h: Hooks, raw: Object) {
    val m = decode_msg(raw)
    val text = m.text
    if (text == null) {
        return
    }
    // DEBUG
    log_debug("got msg: [${m.self_uin}] ${m.sender_uin} -> (${m.is_troop})${m.friend_uin}: ${m.text}")
    // check cmd
    if ((m.self_uin == m.sender_uin) and (text.trim() == CMD_TEST)) {
        log_debug("got cmd: ${CMD_TEST}, send to ${m.friend_uin}")

        val to = encode_msg_text(h, m.self_uin, m.friend_uin, m.is_troop, SEND_TEXT)
        h.send_msg(to)
    }
}
