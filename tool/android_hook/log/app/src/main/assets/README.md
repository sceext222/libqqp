<!-- README.md, libqqp/tool/android_hook/log/app/src/main/assets/
-->
(<https://github.com/sceext222/libqqp>)

# android_hook.log

Hook package: `com.tencent.mobileqq`, version `7.2.5` (`744`)

Hook class: `oicq.wlogin_sdk.tools.util`

Hook methods: (6)

|  # | hook type | method |
| -: | :-------: | :----- |
|  1 | [before]  | `public static void LOGI(String str)` |
|  2 | [before]  | `public static void LOGI(String str, String str2)` |
|  3 | [before]  | `public static void LOGD(String str)` |
|  4 | [before]  | `public static void LOGD(String str, String str2)` |
|  5 | [before]  | `public static void LOGW(String str, String str2)` |
|  6 | [before]  | `public static void LOGW(String str, String str2, String str3)` |


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
