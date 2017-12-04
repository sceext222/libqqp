package org.sceext.android_hook.qq.log_msg

import java.util.List

import de.robv.android.xposed.XposedHelpers.getObjectField
import de.robv.android.xposed.XposedHelpers.setObjectField
import de.robv.android.xposed.XposedHelpers.callStaticMethod
import de.robv.android.xposed.XposedHelpers.callMethod

import org.sceext.android_hook.qq.log_msg.util.log_debug


const val CMD_TEST: String = "libqqp:test"
const val SEND_TEXT: String = "+ test OK 666"


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
    val text: String? = getObjectField(m, "msg") as String?
    if (text == null) {
        return
    }
    val frienduin: String = getObjectField(m, "frienduin") as String
    val selfuin: String = getObjectField(m, "selfuin") as String
    val senderuin: String = getObjectField(m, "senderuin") as String
    // DEBUG
    log_debug("got msg: [${selfuin}] ${senderuin} -> ${frienduin}: ${text}")
    // check cmd
    if ((selfuin == senderuin) and (text.trim() == CMD_TEST)) {
        log_debug("got cmd: ${CMD_TEST}, try to send to ${frienduin}")

        // call MessageRecordFactory.a(int) -> MessageRecord
        val msg: Object = callStaticMethod(h.MessageRecordFactory, "a", INT_MSG_TYPE_TEXT) as Object
        // set MessageRecord
        setObjectField(msg, "frienduin", frienduin)
        setObjectField(msg, "senderuin", senderuin)
        setObjectField(msg, "msg", SEND_TEXT)
        // do send !  call QQMessageFacade.a(MessageRecord, MessageObserver)
        callMethod(h.qq_message_facade, "a", msg, null)
    }
}
