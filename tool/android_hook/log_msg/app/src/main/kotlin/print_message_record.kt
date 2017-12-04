package org.sceext.android_hook.qq.log_msg

import java.util.List

import de.robv.android.xposed.XposedHelpers.getBooleanField
import de.robv.android.xposed.XposedHelpers.getIntField
import de.robv.android.xposed.XposedHelpers.getLongField
import de.robv.android.xposed.XposedHelpers.getShortField
import de.robv.android.xposed.XposedHelpers.getByteField
import de.robv.android.xposed.XposedHelpers.getFloatField
import de.robv.android.xposed.XposedHelpers.getObjectField

import org.sceext.android_hook.qq.log_msg.util.print_string
import org.sceext.android_hook.qq.log_msg.util.print_bytes

const val PREFIX: String = "  "


fun print_message_record(list: List<Object?>?, prefix: String = ""): String {
    if (list == null) {
        return "null"
    }

    val o = StringBuilder()
    o.append("[\n")
    for (i in list) {
        if (i == null) {
            o.append("${PREFIX}null\n")
            continue
        }
        _p_message_record(o, i, prefix + PREFIX)
    }
    o.append("]")
    return o.toString()
}

// m: com.tencent.mobileqq.data.MessageRecord
fun _p_message_record(o: StringBuilder, m: Object, prefix: String = "") {
    // print msg type
    val msg_type = getIntField(m, "msgtype")
    o.append("${prefix}MessageRecord, msgtype = ${get_msg_type(msg_type)} (${msg_type}): {\n")

    // public ArrayList atInfoList
    _p_atinfo_list(o, m, "atInfoList", prefix + PREFIX)
    // public ArrayList atInfoTempList
    _p_atinfo_list(o, m, "atInfoTempList", prefix + PREFIX)

    // public int extInt
    _p_int(o, m, "extInt", prefix + PREFIX)
    // public int extLong
    _p_int(o, m, "extLong", prefix + PREFIX)

    // public String extStr
    _p_str(o, m, "extStr", prefix + PREFIX)

    // public int extraflag
    _p_int(o, m, "extraflag", prefix + PREFIX)

    // public String frienduin
    _p_str(o, m, "frienduin", prefix + PREFIX)

    // public boolean isBlessMsg
    _p_bool(o, m, "isBlessMsg", prefix + PREFIX)
    // public boolean isMultiMsg
    _p_bool(o, m, "isMultiMsg", prefix + PREFIX)
    // public boolean isOpenTroopMessage
    _p_bool(o, m, "isOpenTroopMessage", prefix + PREFIX)
    // public boolean isReMultiMsg
    _p_bool(o, m, "isReMultiMsg", prefix + PREFIX)
    // public boolean isValid = true
    _p_bool(o, m, "isValid", prefix + PREFIX)
    // public boolean isread
    _p_bool(o, m, "isread", prefix + PREFIX)

    // public int issend
    _p_int(o, m, "issend", prefix + PREFIX)
    // public int istroop
    _p_int(o, m, "istroop", prefix + PREFIX)
    // public int longMsgCount
    _p_int(o, m, "longMsgCount", prefix + PREFIX)
    // public int longMsgId
    _p_int(o, m, "longMsgId", prefix + PREFIX)
    // public int longMsgIndex
    _p_int(o, m, "longMsgIndex", prefix + PREFIX)

    //private JSONObject mExJsonObject

    // public int mIsShowQidianTips
    _p_int(o, m, "mIsShowQidianTips", prefix + PREFIX)

    // public MessageInfo mMessageInfo
    _p_message_info(o, m, "mMessageInfo", prefix + PREFIX)

    // public long mQidianMasterUin
    _p_long(o, m, "mQidianMasterUin", prefix + PREFIX)
    // public int mQidianTaskId
    _p_int(o, m, "mQidianTaskId", prefix + PREFIX)

    // public String mQidianTipText
    _p_str(o, m, "mQidianTipText", prefix + PREFIX)

    // public int mRobotFlag
    _p_int(o, m, "mRobotFlag", prefix + PREFIX)

    // public String msg
    _p_str(o, m, "msg", prefix + PREFIX)
    // public String msg2
    _p_str(o, m, "msg2", prefix + PREFIX)

    // public byte[] msgData
    _p_bytes(o, m, "msgData", prefix + PREFIX)

    // public long msgId
    _p_long(o, m, "msgId", prefix + PREFIX)
    // public long msgUid
    _p_long(o, m, "msgUid", prefix + PREFIX)
    // public long msgseq
    _p_long(o, m, "msgseq", prefix + PREFIX)
    // public int msgtype
    _p_int(o, m, "msgtype", prefix + PREFIX)
    // public boolean needUpdateMsgTag = true
    _p_bool(o, m, "needUpdateMsgTag", prefix + PREFIX)

    // public String selfuin
    _p_str(o, m, "selfuin", prefix + PREFIX)

    // public int sendFailCode
    _p_int(o, m, "sendFailCode", prefix + PREFIX)

    // public String senderuin
    _p_str(o, m, "senderuin", prefix + PREFIX)

    // public long shmsgseq
    _p_long(o, m, "shmsgseq", prefix + PREFIX)
    // public boolean stickerHidden
    _p_bool(o, m, "stickerHidden", prefix + PREFIX)

    // public StickerInfo stickerInfo
    _p_sticker_info(o, m, "stickerInfo", prefix + PREFIX)

    // public long time
    _p_long(o, m, "time", prefix + PREFIX)
    // public long uniseq
    _p_long(o, m, "uniseq", prefix + PREFIX)
    // public int versionCode = 3
    _p_int(o, m, "versionCode", prefix + PREFIX)
    // public int vipBubbleDiyTextId
    _p_int(o, m, "vipBubbleDiyTextId", prefix + PREFIX)
    // public long vipBubbleID
    _p_long(o, m, "vipBubbleID", prefix + PREFIX)
    // public int vipSubBubbleId
    _p_int(o, m, "vipSubBubbleId", prefix + PREFIX)

    o.append("${prefix}}\n")
}

// m: com.tencent.mobileqq.troop.data.MessageInfo
fun _p_message_info(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    val i = getObjectField(m, name)
    if (i == null) {
        o.append("${prefix}${name}: MessageInfo  <<null>>\n")
        return
    }
    o.append("${prefix}${name}: MessageInfo {\n")
    val j = i as Object

    // FIXME: java.lang.IllegalArgumentException: Not a primitive field: java.lang.String com.tencent.mobileqq.troop.data.MessageInfo.a
    // public int a = -1
    //_p_int(o, j, "a", prefix + PREFIX)
    // public MessageNavInfo a
    //_p_message_nav_info(o, j, "a", prefix + PREFIX)
    // public String a
    //_p_str(o, j, "a", prefix + PREFIX)

    // public MessageNavInfo b
    _p_message_nav_info(o, j, "b", prefix + PREFIX)
    // public MessageNavInfo c
    _p_message_nav_info(o, j, "c", prefix + PREFIX)
    // public MessageNavInfo d
    _p_message_nav_info(o, j, "d", prefix + PREFIX)
    // public MessageNavInfo e
    _p_message_nav_info(o, j, "e", prefix + PREFIX)
    // public MessageNavInfo f
    _p_message_nav_info(o, j, "f", prefix + PREFIX)
    // public MessageNavInfo g
    _p_message_nav_info(o, j, "g", prefix + PREFIX)
    // public MessageNavInfo h
    _p_message_nav_info(o, j, "h", prefix + PREFIX)
    // public MessageNavInfo i
    _p_message_nav_info(o, j, "i", prefix + PREFIX)
    // public MessageNavInfo j
    _p_message_nav_info(o, j, "j", prefix + PREFIX)
    // public MessageNavInfo k
    _p_message_nav_info(o, j, "k", prefix + PREFIX)
    // public MessageNavInfo l
    _p_message_nav_info(o, j, "l", prefix + PREFIX)
    // public MessageNavInfo m
    _p_message_nav_info(o, j, "m", prefix + PREFIX)
    // public MessageNavInfo n
    _p_message_nav_info(o, j, "n", prefix + PREFIX)
    // public MessageNavInfo o
    _p_message_nav_info(o, j, "o", prefix + PREFIX)
    // public MessageNavInfo p
    _p_message_nav_info(o, j, "p", prefix + PREFIX)
    // public MessageNavInfo q
    _p_message_nav_info(o, j, "q", prefix + PREFIX)
    // public MessageNavInfo r
    _p_message_nav_info(o, j, "r", prefix + PREFIX)
    // public MessageNavInfo s
    _p_message_nav_info(o, j, "s", prefix + PREFIX)
    // public MessageNavInfo t
    _p_message_nav_info(o, j, "t", prefix + PREFIX)

    o.append("${prefix}}\n")
}

// m: com.tencent.mobileqq.troop.data.MessageNavInfo
fun _p_message_nav_info(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    val i = getObjectField(m, name)
    if (i == null) {
        o.append("${prefix}${name}: null\n")
        return
    }
    // public long a: shmsgseq
    // public long b: uniseq
    o.append("${prefix}${name}: MessageNavInfo: shmsgseq = ${getLongField(i, "a")}, uniseq = ${getLongField(i, "b")}\n")
}

// m: com.tencent.mobileqq.emoticon.EmojiStickerManager.StickerInfo
fun _p_sticker_info(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    val i = getObjectField(m, name)
    if (i == null) {
        o.append("${prefix}${name}: StickerInfo  <<null>>\n")
        return
    }
    o.append("${prefix}${name}: StickerInfo {\n")
    val j = i as Object

    // public float height
    _p_float(o, j, "height", prefix + PREFIX)
    // public long hostMsgSeq
    _p_long(o, j, "hostMsgSeq", prefix + PREFIX)
    // public long hostMsgTime
    _p_long(o, j, "hostMsgTime", prefix + PREFIX)
    // public long hostMsgUid
    _p_long(o, j, "hostMsgUid", prefix + PREFIX)
    // public boolean isDisplayed
    _p_bool(o, j, "isDisplayed", prefix + PREFIX)
    // public boolean isShown
    _p_bool(o, j, "isShown", prefix + PREFIX)

    // public String msg = ""
    _p_str(o, j, "msg", prefix + PREFIX)

    // public int originMsgType
    _p_int(o, j, "originMsgType", prefix + PREFIX)
    // public int rotate
    _p_int(o, j, "rotate", prefix + PREFIX)
    // public float width
    _p_float(o, j, "width", prefix + PREFIX)
    // public float x
    _p_float(o, j, "x", prefix + PREFIX)
    // public float y
    _p_float(o, j, "y", prefix + PREFIX)

    o.append("${prefix}}\n")
}

// m: ArrayList<AtTroopMemberInfo>
fun _p_atinfo_list(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    val list = getObjectField(m, name) as ArrayList<Object?>?
    if (list == null) {
        o.append("${prefix}${name}: null\n")
        return
    }
    o.append("${prefix}${name}: [\n")
    for (i in list) {
        if (i == null) {
            o.append("${prefix}${PREFIX}null\n")
            continue
        }
        _p_at_info(o, i, prefix + PREFIX)
    }
    o.append("${prefix}]\n")
}

// m: com.tencent.mobileqq.data.MessageForText.AtTroopMemberInfo
fun _p_at_info(o: StringBuilder, m: Object, prefix: String = "") {
    o.append("${prefix}AtTroopMemberInfo: {\n")

    // public byte flag
    _p_byte(o, m, "flag", prefix + PREFIX)
    // public short startPos
    _p_short(o, m, "startPos", prefix + PREFIX)
    // public short textLen
    _p_short(o, m, "textLen", prefix + PREFIX)
    // public long uin
    _p_long(o, m, "uin", prefix + PREFIX)
    // public short wExtBufLen
    _p_short(o, m, "wExtBufLen", prefix + PREFIX)

    o.append("${prefix}}\n")
}


// print different type fields

fun _p_bool(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    o.append("${prefix}${name}: ${getBooleanField(m, name)}\n")
}

fun _p_int(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    o.append("${prefix}${name}: ${getIntField(m, name)}\n")
}

fun _p_long(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    o.append("${prefix}${name}: ${getLongField(m, name)}\n")
}

fun _p_short(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    o.append("${prefix}${name}: ${getShortField(m, name)}\n")
}

fun _p_byte(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    o.append("${prefix}${name}: ${getByteField(m, name)}\n")
}

fun _p_float(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    o.append("${prefix}${name}: ${getFloatField(m, name)}\n")
}

fun _p_str(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    val text = print_string(getObjectField(m, name) as String?)
    o.append("${prefix}${name}: ${text}\n")
}

fun _p_bytes(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    val text = print_bytes(getObjectField(m, name) as ByteArray?)
    o.append("${prefix}${name}: ${text}\n")
}
