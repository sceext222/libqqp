<!-- README.md, libqqp/tool/android_hook/cryptor/app/src/main/assets/
-->
(<https://github.com/sceext222/libqqp>)

# android_hook.cryptor

Hook package: `com.tencent.mobileqq`, version `7.2.5` (`744`)

Hook class: `com.tencent.qphone.base.util.Cryptor`

Hook methods: (4)

|  # | hook type | method |
| -: | :-------: | :----- |
|  1 | [after]   | `public byte[] decrypt(byte[] bArr, int i, int i2, byte[] bArr2)` |
|  2 | [after]   | `public byte[] decrypt(byte[] bArr, byte[] bArr2)` |
|  3 | [after]   | `public byte[] encrypt(byte[] bArr, byte[] bArr2)` |
|  4 | [before]  | `public void enableResultRandom(boolean z)` |


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
