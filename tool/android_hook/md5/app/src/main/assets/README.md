<!-- README.md, libqqp/tool/android_hook/md5/app/src/main/assets/
-->
(<https://github.com/sceext222/libqqp>)

# android_hook.md5

Hook package: `com.tencent.mobileqq`, version `7.2.5` (`744`)

Hook class: `oicq.wlogin_sdk.tools.MD5`

Hook methods: (9)

|  # | hook type | method |
| -: | :-------: | :----- |
|  1 | [after]   | `public byte[] getMD5(byte[] bArr)` |
|  2 | [after]   | `public byte[] getMD5(InputStream inputStream, long j)` |
|  3 | [after]   | `public static byte[] toMD5Byte(byte[] bArr)` |
|  4 | [after]   | `public static byte[] toMD5Byte(String str)` |
|  5 | [after]   | `public static byte[] toMD5Byte(InputStream inputStream, long j)` |
|  6 | [after]   | `public static String toMD5(byte[] bArr)` |
|  7 | [after]   | `public static String toMD5(String str)` |
|  8 | [after]   | `public static String getMD5String(byte[] bArr)` |
|  9 | [after]   | `public static String getFileMD5(File file)` |


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
