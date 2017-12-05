package org.sceext.saxh.cmd

import org.sceext.saxh.Config
import org.sceext.saxh.hook.Hooks
import org.sceext.saxh.hook.util.log_debug


fun _master_cmd_help() = """
    |supported commands:
    |
    |    libqqp: ping            # ping test
    |    libqqp: test            # test res
    |
    |    libqqp: debug print-msg [ enable | disable ]
    |    libqqp: debug echo [ enable | disable ]
    |    libqqp: debug echo-at-only [ enable | disable ]
    |
    |    libqqp: master          # show master
    |    libqqp: master unset    # unset master
    |
    |    libqqp: help            # show this help
    """.trimMargin()


fun on_master_cmd(h: Hooks, m: DecodedMsg, c: Array<String>) {
    log_debug("got master cmd: ${c.joinToString(" ")}")
    // check empty command
    if (c.size < 1) {
        res_text(h, m, CMD_EMPTY_HELP)
        return
    }
    // check command type
    when(c[0]) {
        "help" -> {  // help command
            res_text(h, m, _master_cmd_help())
        }
        "ping" -> _cmd_ping(h, m)
        "test" -> _cmd_test(h, m)
        "master" -> _cmd_master(h, m, c)
        "debug" -> _cmd_debug(h, m, c)

        else -> {
            res_text(h, m, CMD_BAD_HELP)
        }
    }
}

fun _cmd_ping(h: Hooks, m: DecodedMsg) {
    res_text(h, m, "pong")
}

fun _cmd_test(h: Hooks, m: DecodedMsg) {
    res_text(h, m, "test ok 666")
}

fun _cmd_master(h: Hooks, m: DecodedMsg, c: Array<String>) {
    if (c.size == 1) {
        // cmd: master
        res_text(h, m, "master is ${Config.master}")
    } else if (c.size == 2) {
        // cmd: master unset
        if (c[1] != "unset") {
            res_text(h, m, CMD_BAD_HELP)
            return
        }
        // unset master
        Config.master = null
        res_text(h, m, "set master ${Config.master}")
    } else {
        res_text(h, m, CMD_BAD_HELP)
    }
}

fun _cmd_debug(h: Hooks, m: DecodedMsg, c: Array<String>) {
    if (c.size < 2) {
        res_text(h, m, CMD_BAD_HELP)
        return
    }
    when(c[1]) {
        "print-msg" -> {
            if (c.size == 2) {
                // cmd: debug print-msg
                res_text(h, m, _print_debug_status(Config.debug_print_msg, "print-msg"))
            } else if (c.size == 3) {
                // cmd: debug print-msg [ enable | disable ]
                when(c[2]) {
                    "enable" -> {
                        Config.debug_print_msg = true
                        res_text(h, m, _print_debug_enable("print-msg"))
                    }
                    "disable" -> {
                        Config.debug_print_msg = false
                        res_text(h, m, _print_debug_disable("print-msg"))
                    }
                    else -> {
                        res_text(h, m, CMD_BAD_HELP)
                    }
                }
            } else {
                res_text(h, m, CMD_BAD_HELP)
            }
        }
        "echo" -> {
            if (c.size == 2) {
                // cmd: debug echo
                res_text(h, m, _print_debug_status(Config.debug_echo, "echo"))
            } else if (c.size == 3) {
                // cmd: debug echo [ enable | disable ]
                when(c[2]) {
                    "enable" -> {
                        Config.debug_echo = true
                        res_text(h, m, _print_debug_enable("echo"))
                    }
                    "disable" -> {
                        Config.debug_echo = false
                        res_text(h, m, _print_debug_disable("echo"))
                    }
                    else -> {
                        res_text(h, m, CMD_BAD_HELP)
                    }
                }
            } else {
                res_text(h, m, CMD_BAD_HELP)
            }
        }
        "echo-at-only" -> {
            if (c.size == 2) {
                // cmd: debug echo-at-only
                res_text(h, m, _print_debug_status(Config.debug_echo_at_only, "echo-at-only"))
            } else if (c.size == 3) {
                // cmd: debug echo-at-only [ enable | disable ]
                when(c[2]) {
                    "enable" -> {
                        Config.debug_echo_at_only = true
                        res_text(h, m, _print_debug_enable("echo-at-only"))
                    }
                    "disable" -> {
                        Config.debug_echo_at_only = false
                        res_text(h, m, _print_debug_disable("echo-at-only"))
                    }
                    else -> {
                        res_text(h, m, CMD_BAD_HELP)
                    }
                }
            } else {
                res_text(h, m, CMD_BAD_HELP)
            }
        }
        else -> {
            res_text(h, m, CMD_BAD_HELP)
        }
    }
}

fun _print_debug_status(s: Boolean, name: String): String {
    val t = if (s) "enabled" else "disabled"
    return "debug ${name} is ${t}"
}

fun _print_debug_enable(name: String) = "debug ${name} enabled"

fun _print_debug_disable(name: String) = "debug ${name} disabled"
