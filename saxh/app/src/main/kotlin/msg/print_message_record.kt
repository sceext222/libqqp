package org.sceext.saxh.msg

import java.util.List

import de.robv.android.xposed.XposedHelpers.getBooleanField
import de.robv.android.xposed.XposedHelpers.getIntField
import de.robv.android.xposed.XposedHelpers.getLongField
import de.robv.android.xposed.XposedHelpers.getShortField
import de.robv.android.xposed.XposedHelpers.getByteField
import de.robv.android.xposed.XposedHelpers.getFloatField
import de.robv.android.xposed.XposedHelpers.getObjectField

import org.sceext.saxh.hook.util.print_string
import org.sceext.saxh.hook.util.print_bytes

const val PREFIX: String = "  "
const val NULL: String = "null"

// MessageRecord fields
const val F_AT_INFO_LIST          :String = "atInfoList"
const val F_AT_INFO_TEMP_LIST     :String = "atInfoTempList"
const val F_EXT_INT               :String = "extInt"
const val F_EXT_LONG              :String = "extLong"
const val F_EXT_STR               :String = "extStr"
const val F_EXTRA_FLAG            :String = "extraflag"
const val F_FRIEND_UIN            :String = "frienduin"
const val F_IS_BLESS_MSG          :String = "isBlessMsg"
const val F_IS_MULTI_MSG          :String = "isMultiMsg"
const val F_IS_OPEN_TROOP_MESSAGE :String = "isOpenTroopMessage"
const val F_IS_RE_MULTI_MSG       :String = "isReMultiMsg"
const val F_IS_VALID              :String = "isValid"
const val F_IS_READ               :String = "isread"
const val F_IS_SEND               :String = "issend"
const val F_IS_TROOP              :String = "istroop"
const val F_LONG_MSG_COUNT        :String = "longMsgCount"
const val F_LONG_MSG_ID           :String = "longMsgId"
const val F_LONG_MSG_INDEX        :String = "longMsgIndex"
const val F_M_IS_SHOW_QIDIAN_TIPS :String = "mIsShowQidianTips"
const val F_M_MESSAGE_INFO        :String = "mMessageInfo"
const val F_M_QIDIAN_MASTER_UIN   :String = "mQidianMasterUin"
const val F_M_QIDIAN_TASK_ID      :String = "mQidianTaskId"
const val F_M_QIDIAN_TIP_TEXT     :String = "mQidianTipText"
const val F_M_ROBOT_FLAG          :String = "mRobotFlag"
const val F_MSG                   :String = "msg"
const val F_MSG2                  :String = "msg2"
const val F_MSG_DATA              :String = "msgData"
const val F_MSG_ID                :String = "msgId"
const val F_MSG_UID               :String = "msgUid"
const val F_MSG_SEQ               :String = "msgseq"
const val F_MSG_TYPE              :String = "msgtype"
const val F_NEED_UPDATE_MSG_TAG   :String = "needUpdateMsgTag"
const val F_SELF_UIN              :String = "selfuin"
const val F_SEND_FAIL_CODE        :String = "sendFailCode"
const val F_SENDER_UIN            :String = "senderuin"
const val F_SH_MSG_SEQ            :String = "shmsgseq"
const val F_STICKER_HIDDEN        :String = "stickerHidden"
const val F_STICKER_INFO          :String = "stickerInfo"
const val F_TIME                  :String = "time"
const val F_UNISEQ                :String = "uniseq"
const val F_VERSION_CODE          :String = "versionCode"
const val F_VIP_BUBBLE_DIY_TEXT_ID:String = "vipBubbleDiyTextId"
const val F_VIP_BUBBLE_ID         :String = "vipBubbleID"
const val F_VIP_SUB_BUBBLE_ID     :String = "vipSubBubbleId"

// AtTroopMemberInfo fields
const val FA_FLAG          :String = "flag"
const val FA_START_POS     :String = "startPos"
const val FA_TEXT_LEN      :String = "textLen"
const val FA_UIN           :String = "uin"
const val FA_W_EXT_BUF_LEN :String = "wExtBufLen"


fun print_message_record(list: List<Object?>?, prefix: String = ""): String {
    if (list == null) {
        return NULL
    }

    val o = StringBuilder()
    o.append("[\n")
    for (i in list) {
        if (i == null) {
            o.append("${PREFIX}${NULL}\n")
            continue
        }
        _p_message_record(o, i, prefix + PREFIX)
    }
    o.append("]")
    return o.toString()
}

fun print_one_message_record(m: Object?): String {
    if (m == null) {
        return NULL
    }
    val o = StringBuilder()
    // FIXME
    _p_message_record(o, m, PREFIX)
    return o.toString()
}

// m: com.tencent.mobileqq.data.MessageRecord
fun _p_message_record(o: StringBuilder, m: Object, prefix: String = "") {
    // print msg type
    val msg_type = getIntField(m, F_MSG_TYPE)
    o.append("${prefix}MessageRecord, msgtype = ${get_msg_type(msg_type)} (${msg_type}): {\n")

    // public ArrayList atInfoList
    _p_atinfo_list(o, m, F_AT_INFO_LIST, prefix + PREFIX)
    // public ArrayList atInfoTempList
    _p_atinfo_list(o, m, F_AT_INFO_TEMP_LIST, prefix + PREFIX)

    // public int extInt
    _p_int(o, m, F_EXT_INT, prefix + PREFIX)
    // public int extLong
    _p_int(o, m, F_EXT_LONG, prefix + PREFIX)

    // public String extStr
    _p_str(o, m, F_EXT_STR, prefix + PREFIX)

    // public int extraflag
    _p_int(o, m, F_EXTRA_FLAG, prefix + PREFIX)

    // public String frienduin
    _p_str(o, m, F_FRIEND_UIN, prefix + PREFIX)

    // public boolean isBlessMsg
    _p_bool(o, m, F_IS_BLESS_MSG, prefix + PREFIX)
    // public boolean isMultiMsg
    _p_bool(o, m, F_IS_MULTI_MSG, prefix + PREFIX)
    // public boolean isOpenTroopMessage
    _p_bool(o, m, F_IS_OPEN_TROOP_MESSAGE, prefix + PREFIX)
    // public boolean isReMultiMsg
    _p_bool(o, m, F_IS_RE_MULTI_MSG, prefix + PREFIX)
    // public boolean isValid = true
    _p_bool(o, m, F_IS_VALID, prefix + PREFIX)
    // public boolean isread
    _p_bool(o, m, F_IS_READ, prefix + PREFIX)

    // public int issend
    _p_int(o, m, F_IS_SEND, prefix + PREFIX)
    // public int istroop
    _p_int(o, m, F_IS_TROOP, prefix + PREFIX)
    // public int longMsgCount
    _p_int(o, m, F_LONG_MSG_COUNT, prefix + PREFIX)
    // public int longMsgId
    _p_int(o, m, F_LONG_MSG_ID, prefix + PREFIX)
    // public int longMsgIndex
    _p_int(o, m, F_LONG_MSG_INDEX, prefix + PREFIX)

    //private JSONObject mExJsonObject

    // public int mIsShowQidianTips
    _p_int(o, m, F_M_IS_SHOW_QIDIAN_TIPS, prefix + PREFIX)

    // public MessageInfo mMessageInfo
    _p_message_info(o, m, F_M_MESSAGE_INFO, prefix + PREFIX)

    // public long mQidianMasterUin
    _p_long(o, m, F_M_QIDIAN_MASTER_UIN, prefix + PREFIX)
    // public int mQidianTaskId
    _p_int(o, m, F_M_QIDIAN_TASK_ID, prefix + PREFIX)

    // public String mQidianTipText
    _p_str(o, m, F_M_QIDIAN_TIP_TEXT, prefix + PREFIX)

    // public int mRobotFlag
    _p_int(o, m, F_M_ROBOT_FLAG, prefix + PREFIX)

    // public String msg
    _p_str(o, m, F_MSG, prefix + PREFIX)
    // public String msg2
    _p_str(o, m, F_MSG2, prefix + PREFIX)

    // public byte[] msgData
    _p_bytes(o, m, F_MSG_DATA, prefix + PREFIX)

    // public long msgId
    _p_long(o, m, F_MSG_ID, prefix + PREFIX)
    // public long msgUid
    _p_long(o, m, F_MSG_UID, prefix + PREFIX)
    // public long msgseq
    _p_long(o, m, F_MSG_SEQ, prefix + PREFIX)
    // public int msgtype
    _p_int(o, m, F_MSG_TYPE, prefix + PREFIX)
    // public boolean needUpdateMsgTag = true
    _p_bool(o, m, F_NEED_UPDATE_MSG_TAG, prefix + PREFIX)

    // public String selfuin
    _p_str(o, m, F_SELF_UIN, prefix + PREFIX)

    // public int sendFailCode
    _p_int(o, m, F_SEND_FAIL_CODE, prefix + PREFIX)

    // public String senderuin
    _p_str(o, m, F_SENDER_UIN, prefix + PREFIX)

    // public long shmsgseq
    _p_long(o, m, F_SH_MSG_SEQ, prefix + PREFIX)
    // public boolean stickerHidden
    _p_bool(o, m, F_STICKER_HIDDEN, prefix + PREFIX)

    // public StickerInfo stickerInfo
    _p_sticker_info(o, m, F_STICKER_INFO, prefix + PREFIX)

    // public long time
    _p_long(o, m, F_TIME, prefix + PREFIX)
    // public long uniseq
    _p_long(o, m, F_UNISEQ, prefix + PREFIX)
    // public int versionCode = 3
    _p_int(o, m, F_VERSION_CODE, prefix + PREFIX)
    // public int vipBubbleDiyTextId
    _p_int(o, m, F_VIP_BUBBLE_DIY_TEXT_ID, prefix + PREFIX)
    // public long vipBubbleID
    _p_long(o, m, F_VIP_BUBBLE_ID, prefix + PREFIX)
    // public int vipSubBubbleId
    _p_int(o, m, F_VIP_SUB_BUBBLE_ID, prefix + PREFIX)

    o.append("${prefix}}\n")
}

// m: com.tencent.mobileqq.troop.data.MessageInfo
fun _p_message_info(o: StringBuilder, m: Object, name: String, prefix: String = "") {
    val i = getObjectField(m, name)
    if (i == null) {
        o.append("${prefix}${name}: ${NULL}\n")
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
        o.append("${prefix}${name}: ${NULL}\n")
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
        o.append("${prefix}${name}: ${NULL}\n")
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
        o.append("${prefix}${name}: ${NULL}\n")
        return
    }
    o.append("${prefix}${name}: [\n")
    for (i in list) {
        if (i == null) {
            o.append("${prefix}${PREFIX}${NULL}\n")
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
    _p_byte(o, m, FA_FLAG, prefix + PREFIX)
    // public short startPos
    _p_short(o, m, FA_START_POS, prefix + PREFIX)
    // public short textLen
    _p_short(o, m, FA_TEXT_LEN, prefix + PREFIX)
    // public long uin
    _p_long(o, m, FA_UIN, prefix + PREFIX)
    // public short wExtBufLen
    _p_short(o, m, FA_W_EXT_BUF_LEN, prefix + PREFIX)

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
