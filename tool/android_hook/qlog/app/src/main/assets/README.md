<!-- README.md, libqqp/tool/android_hook/qlog/app/src/main/assets/
-->
(<https://github.com/sceext222/libqqp>)

# android_hook.qlog

Hook package: `com.tencent.mobileqq`, version `7.2.5` (`744`)

Hook class: `com.tencent.qphone.base.util.QLog`

Hook methods: (13)

|  # | hook type | method |
| -: | :-------: | :----- |
|  1 | [before]  | `public static void init(String str, String str2, String str3, long j)` |
|  2 | [after]   | `public static boolean isColorLevel()` |
|  3 | [after]   | `public static final boolean isDevelopLevel()` |
|  4 | [before]  | `public static void p(String str, String str2)` |
|  5 | [before]  | `public static void e(String str, int i, String str2, Throwable th)` |
|  6 | [before]  | `public static void w(String str, int i, String str2, Throwable th)` |
|  7 | [before]  | `public static void i(String str, int i, String str2, Throwable th)` |
|  8 | [before]  | `public static void d(String str, int i, String str2, Throwable th)` |
|  9 | [before]  | `public static void doReportLogSelf(int i, String str, String str2, boolean z)` |
| 10 | [before]  | `public static void syncReportLogSelf(int i, String str, String str2, String str3)` |
| 11 | [before]  | `public static void startColorLog(String[] strArr)` |
| 12 | [before]  | `public static void endColorLog(String[] strArr, int i, boolean z, String str)` |
| 13 | [after]   | `public static boolean isDebugVersion()` |


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
