<!-- README.md, libqqp/tool/android_hook/log_msg/app/src/main/assets/
-->
(<https://github.com/sceext222/libqqp>)

# android_hook.log_msg

Hook package: `com.tencent.mobileqq`, version `7.2.5` (`744`)

Hook class: `com.tencent.mobileqq.app.message.QQMessageFacade`

Hook methods: (1)

|  # | hook type | method |
| -: | :-------: | :----- |
|  1 | [before]  | `.method private a(Ljava/util/List;Lcom/tencent/mobileqq/persistence/EntityManager;ZZZZ)V` |
|    |           | `private void a(List<MessageRecord>, EntityManager, boolean, boolean, boolean, boolean)` |


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
