package org.sceext.saxh.cmd

import org.sceext.saxh.Config
import org.sceext.saxh.hook.Hooks
import org.sceext.saxh.hook.util.log_debug


const val CMD_EMPTY_HELP: String = "ERROR: empty command, please try `libqqp:help`"
const val CMD_BAD_HELP: String = "ERROR: bad command, please try `libqqp:help`"

fun _init_cmd_help() = """
    |supported commands:
    |    libqqp: master set    # set master to current friend
    |    libqqp: help          # show this help
    """.trimMargin()


fun on_init_cmd(h: Hooks, m: DecodedMsg, c: Array<String>) {
    log_debug("got init cmd: ${c.joinToString(" ")}")
    // check empty command
    if (c.size < 1) {
        res_text(h, m, CMD_EMPTY_HELP)
        return
    }
    // check command type
    when(c[0]) {
        "help" -> {  // help command
            res_text(h, m, _init_cmd_help())
        }
        "master" -> {  // master command
            if (c.size < 2) {
                res_text(h, m, CMD_BAD_HELP)
                return
            }
            if (c[1] != "set") {
                res_text(h, m, CMD_BAD_HELP)
                return
            }
            // `master set` command
            // check is_troop again
            if (m.is_troop != 0) {
                res_text(h, m, "ERROR: bad is_troop (${m.is_troop})")
                return
            }
            Config.master = m.friend_uin
            // OK
            res_text(h, m, "set master ${Config.master}")
        }
        else -> {
            res_text(h, m, CMD_BAD_HELP)
        }
    }
}
