
# `wu` hook tool

Hook tool: `org.wu`

Hook package: `com.tencent.mobileqq`

Hooks methods: (9)

|  # | OK | hook type | class | method |
| -: | -: | :-------: | :---- | :----- |
|  1 |    | [after]   | `oicq.wlogin_sdk.request.Ticket`       | constructor(`int, byte[], byte[], long, byte[], byte[]`) |
|  2 |    | [before]  | `com.tencent.mobileqq.msf.core.auth.a` | `n(byte[])` |
|  3 |  x | [after]   | `oicq.wlogin_sdk.tools.cryptor`        | `encrypt(byte[], int, int, byte[])` |
|  4 |  x | [after]   | `oicq.wlogin_sdk.tools.cryptor`        | `decrypt(byte[], int, int, byte[])` |
|  5 |    | [after]   | `mqq.app.Packet`                       | `toMsg()` |
|  6 |  x | [before]  | `oicq.wlogin_sdk.tools.EcdhCrypt`      | `GenECDHKeyEx(String, String, String)` |
|  7 |  x | [before]  | `oicq.wlogin_sdk.tools.EcdhCrypt`      | `set_c_pub_key(byte[])` |
|  8 |  x | [before]  | `oicq.wlogin_sdk.tools.EcdhCrypt`      | `set_c_pri_key(byte[])` |
|  9 |  x | [before]  | `oicq.wlogin_sdk.tools.EcdhCrypt`      | `set_g_share_key(byte[])` |
