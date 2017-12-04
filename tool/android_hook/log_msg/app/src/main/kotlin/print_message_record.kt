package org.sceext.android_hook.qq.log_msg

import java.util.List

import de.robv.android.xposed.XposedHelpers.getBooleanField
import de.robv.android.xposed.XposedHelpers.getIntField
import de.robv.android.xposed.XposedHelpers.getLongField
import de.robv.android.xposed.XposedHelpers.getObjectField

import org.sceext.android_hook.qq.log_msg.util.print_string
import org.sceext.android_hook.qq.log_msg.util.print_bytes


fun print_message_record(list: List<Object?>?): String {
    if (list == null) {
        return "<<null>>"
    }

    val o = StringBuilder()
    o.append("{\n")
    for (i in list) {
        if (i == null) {
            o.append("  <<null>>\n")
            continue
        }
        _p_message_record(o, i)
    }
    o.append("}")
    return o.toString()
}

// m: com.tencent.mobileqq.data.MessageRecord
fun _p_message_record(o: StringBuilder, m: Object) {
    o.append("  MessageRecord: [\n")

    // public ArrayList atInfoList
    _p_array_list(o, m, "atInfoList")
    // public ArrayList atInfoTempList
    _p_array_list(o, m, "atInfoTempList")

    // public int extInt
    _p_int(o, m, "extInt")
    // public int extLong
    _p_int(o, m, "extLong")

    // public String extStr
    _p_str(o, m, "extStr")

    // public int extraflag
    _p_int(o, m, "extraflag")

    // public String frienduin
    _p_str(o, m, "frienduin")

    // public boolean isBlessMsg
    _p_bool(o, m, "isBlessMsg")
    // public boolean isMultiMsg
    _p_bool(o, m, "isMultiMsg")
    // public boolean isOpenTroopMessage
    _p_bool(o, m, "isOpenTroopMessage")
    // public boolean isReMultiMsg
    _p_bool(o, m, "isReMultiMsg")
    // public boolean isValid = true
    _p_bool(o, m, "isValid")
    // public boolean isread
    _p_bool(o, m, "isread")

    // public int issend
    _p_int(o, m, "issend")
    // public int istroop
    _p_int(o, m, "istroop")
    // public int longMsgCount
    _p_int(o, m, "longMsgCount")
    // public int longMsgId
    _p_int(o, m, "longMsgId")
    // public int longMsgIndex
    _p_int(o, m, "longMsgIndex")

    //private JSONObject mExJsonObject

    // public int mIsShowQidianTips
    _p_int(o, m, "mIsShowQidianTips")

    // public MessageInfo mMessageInfo
    _p_message_info(o, m, "mMessageInfo")

    // public long mQidianMasterUin
    _p_long(o, m, "mQidianMasterUin")
    // public int mQidianTaskId
    _p_int(o, m, "mQidianTaskId")

    // public String mQidianTipText
    _p_str(o, m, "mQidianTipText")

    // public int mRobotFlag
    _p_int(o, m, "mRobotFlag")

    // public String msg
    _p_str(o, m, "msg")
    // public String msg2
    _p_str(o, m, "msg2")

    // public byte[] msgData
    _p_bytes(o, m, "msgData")

    // public long msgId
    _p_long(o, m, "msgId")
    // public long msgUid
    _p_long(o, m, "msgUid")
    // public long msgseq
    _p_long(o, m, "msgseq")
    // public int msgtype
    _p_int(o, m, "msgtype")
    // public boolean needUpdateMsgTag = true
    _p_bool(o, m, "needUpdateMsgTag")

    // public String selfuin
    _p_str(o, m, "selfuin")

    // public int sendFailCode
    _p_int(o, m, "sendFailCode")

    // public String senderuin
    _p_str(o, m, "senderuin")

    // public long shmsgseq
    _p_long(o, m, "shmsgseq")
    // public boolean stickerHidden
    _p_bool(o, m, "stickerHidden")

    // public StickerInfo stickerInfo
    _p_sticker_info(o, m, "stickerInfo")

    // public long time
    _p_long(o, m, "time")
    // public long uniseq
    _p_long(o, m, "uniseq")
    // public int versionCode = 3
    _p_int(o, m, "versionCode")
    // public int vipBubbleDiyTextId
    _p_int(o, m, "vipBubbleDiyTextId")
    // public long vipBubbleID
    _p_long(o, m, "vipBubbleID")
    // public int vipSubBubbleId
    _p_int(o, m, "vipSubBubbleId")

    o.append("  ]\n")
}

// m: com.tencent.mobileqq.troop.data.MessageInfo
fun _p_message_info(o: StringBuilder, m: Object, name: String) {
    val mi = getObjectField(m, name)
    if (mi == null) {
        o.append("    ${name}: MessageInfo  <<null>>\n")
        return
    }
    // TODO
    o.append("    ${name}: MessageInfo TODO  ${mi}\n")
}

// m: com.tencent.mobileqq.emoticon.EmojiStickerManager.StickerInfo
fun _p_sticker_info(o: StringBuilder, m: Object, name: String) {
    val si = getObjectField(m, name)
    if (si == null) {
        o.append("    ${name}: StickerInfo  <<null>>\n")
        return
    }
    // TODO
    o.append("    ${name}: StickerInfo TODO  ${si}\n")
}

// TODO
fun _p_array_list(o: StringBuilder, m: Object, name: String) {
    val list = getObjectField(m, name) as ArrayList<Object?>?
    if (list == null) {
        o.append("    ${name}: ArrayList  <<null>>\n")
        return
    }
    o.append("    ${name}: ArrayList [\n")
    for (i in list) {
        if (i == null) {
            o.append("      <<null>>\n")
            continue
        }
        // TODO
        o.append("      ${i}\n")
    }
    o.append("    ]\n")
}


// print different type fields

fun _p_bool(o: StringBuilder, m: Object, name: String) {
    o.append("    ${name}: ${getBooleanField(m, name)}\n")
}

fun _p_int(o: StringBuilder, m: Object, name: String) {
    o.append("    ${name}: ${getIntField(m, name)}\n")
}

fun _p_long(o: StringBuilder, m: Object, name: String) {
    o.append("    ${name}: ${getLongField(m, name)}\n")
}

fun _p_str(o: StringBuilder, m: Object, name: String) {
    val text = print_string(getObjectField(m, name) as String?)
    o.append("    ${name}: ${text}\n")
}

fun _p_bytes(o: StringBuilder, m: Object, name: String) {
    val text = print_bytes(getObjectField(m, name) as ByteArray?)
    o.append("    ${name}: ${text}\n")
}
