package org.sceext.saxh.cmd

import java.util.List

import org.sceext.saxh.Config
import org.sceext.saxh.hook.Hooks
import org.sceext.saxh.hook.util.log_debug
import org.sceext.saxh.msg.get_msg_type
import org.sceext.saxh.msg.INT_MSG_TYPE_TEXT
import org.sceext.saxh.msg.INT_MSG_TYPE_REPLY_TEXT


const val CMD_PREFIX: String = "libqqp:"
const val CMD_SEP: String = " "  // space

const val RES_PREFIX: String = "+ libqqp: "
const val ECHO_PREFIX: String = "> "


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
    // check msg_type
    when(m.msg_type) {
        INT_MSG_TYPE_TEXT,
        INT_MSG_TYPE_REPLY_TEXT -> {
            log_debug("got msg  ${print_msg_short(m)}")
            _check_cmd(h, m)
        }
        else -> {
            log_debug("unknow msg type: ${print_msg_short(m)}")
        }
    }
}

fun print_msg_short(m: DecodedMsg): String {
    val p = "[${m.self_uin}]"
    val t = "${get_msg_type(m.msg_type)}(${m.msg_type}):: ${m.text}"
    val f = "(${m.is_troop})${m.friend_uin}"
    if (m.self_uin == m.sender_uin) {
        return "${p} send -> ${f}: ${t}"
    } else {
        return "${p} recv ${f} <- ${m.sender_uin}: ${t}"
    }
}

fun send_text(h: Hooks, m: DecodedMsg, text: String) {
    val to = encode_msg_text(h, m.self_uin, m.friend_uin, m.is_troop, text)
    h.send_msg(to)
}

fun res_text(h: Hooks, m: DecodedMsg, text: String) {
    send_text(h, m, RES_PREFIX + text)
}

fun _remove_at_text(raw: String, list: MutableList<AtInfo>, replace: Char = ' '): String {
    val o = StringBuilder(raw)
    for (i in list) {
        for (j in i.start_pos until (i.start_pos + i.text_len)) {
            o.setCharAt(j, replace)
        }
    }
    return o.toString()
}

fun _is_send(m: DecodedMsg): Boolean {
    if ((m.sender_uin == m.self_uin) and (m.is_send == 1)) {
        return true
    }
    return false
}

fun _debug_echo(h: Hooks, m: DecodedMsg, text: String) {
    if (! Config.debug_echo) {
        return
    }
    if ((m.is_troop == 1) and (Config.debug_echo_at_only) and (! m.is_at_me)) {
        return
    }
    // do not echo self send
    if ((m.sender_uin == m.self_uin) or (m.is_send == 1)) {
        return
    }
    send_text(h, m, ECHO_PREFIX + text)
}

fun _check_cmd(h: Hooks, m: DecodedMsg) {
    val raw = m.text
    if (raw == null) {
        return  // ignore null
    }
    _debug_echo(h, m, raw)

    // check is_troop
    if ((m.is_troop != 0) and (m.is_troop != 1)) {
        return
    }
    // check at me
    if ((m.is_troop == 1) and (! m.is_at_me)) {
        return
    }
    var text = raw
    // remove at text
    if (m.is_at_me) {
        text = _remove_at_text(text, m.at_info)
    }
    // check prefix
    text = text.trim()
    if (! text.startsWith(CMD_PREFIX)) {
        return
    }
    text = text.slice(CMD_PREFIX.length until text.length).trim()
    val c = _parse_cmd(text)
    // check command type

    // check master command
    if ((_is_send(m)) and (m.is_troop == 0) and (m.friend_uin == Config.master)) {
        // send to master
        on_master_cmd(h, m, c)
    } else if ((m.sender_uin == Config.master) and ((m.is_troop == 0) or (m.is_troop == 1))) {
        // send from master
        on_master_cmd(h, m, c)
    // check init command
    } else if ((m.is_troop == 0) and (_is_send(m))) {
        // send to one friend only
        on_init_cmd(h, m, c)
    }
    // else: not a command, ignore
}

fun _parse_cmd(raw: String): Array<String> {
    val o = mutableListOf<String>()
    for (i in raw.split(CMD_SEP)) {
        val one = i.trim()
        if (one.length > 0) {
            o.add(one)
        }
    }
    return o.toTypedArray()
}
