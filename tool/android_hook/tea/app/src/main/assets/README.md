<!-- README.md, libqqp/tool/android_hook/tea/app/src/main/assets/
-->
(<https://github.com/sceext222/libqqp>)

# android_hook.tea

Hook package: `com.tencent.mobileqq`, version `7.2.5` (`744`)

Hook class: `oicq.wlogin_sdk.tools.cryptor`

Hook methods: (2)

|  # | hook type | method |
| -: | :-------: | :----- |
|  1 | [after]   | `public static byte[] encrypt(byte[] bArr, int i, int i2, byte[] bArr2)` |
|  2 | [after]   | `public static byte[] decrypt(byte[] bArr, int i, int i2, byte[] bArr2)` |


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
