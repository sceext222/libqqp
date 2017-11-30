<!-- README.md, libqqp/tool/android_hook/ecdh/app/src/main/assets/
-->
(<https://github.com/sceext222/libqqp>)

# android_hook.ecdh

Hook package: `com.tencent.mobileqq`, version `7.2.5` (`744`)

Hook class: `oicq.wlogin_sdk.tools.EcdhCrypt`

Hook methods: (10)

|  # | hook type | method |
| -: | :-------: | :----- |
|  1 | [after]   | `public native int GenECDHKeyEx(String str, String str2, String str3)` |
|  2 | [after]   | `public int GenereateKey()` |
|  3 | [after]   | `public byte[] get_c_pub_key()` |
|  4 | [before]  | `public void set_c_pub_key(byte[] bArr)` |
|  5 | [before]  | `public void set_c_pri_key(byte[] bArr)` |
|  6 | [after]   | `public byte[] get_g_share_key()` |
|  7 | [before]  | `public void set_g_share_key(byte[] bArr)` |
|  8 | [after]   | `public byte[] calShareKeyMd5ByPeerPublicKey(byte[] bArr)` |
|  9 | [after]   | `public int initShareKeyByDefault()` |
| 10 | [after]   | `public int initShareKey()` |


## How to use this tool

+ **1**. Install **`Xposed framework`**
  <https://github.com/rovo89/XposedInstaller>

  <http://repo.xposed.info/>

+ **2**. Install and enable this module.

+ **3**. Use `adb logcat` to see DEBUG logs.

  ```
  > adb logcat '*:S Xposed'
  ```


## LICENSE

`MIT License`

<!-- end README.md -->
