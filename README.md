# Money Earning (UPI) - Decompiled Android Application Analysis

This repository contains the decompiled source code of the Android application "Money Earning (UPI)" (package name `yige.liwu`).

## Analysis Summary

- **Framework:** The application is built using the **AndroLua** framework, which allows embedding Lua scripting within an Android app.
- **Core Logic:** The primary application logic resides in Lua scripts (`main.lua`, `init.lua`, `import.lua`, etc.) originally located in the APK's `assets` folder.
- **Obfuscation:** These core Lua scripts are **obfuscated/encoded** (likely Base64 encoded, potentially with further encryption, starting with an `=` character). They are not human-readable in their current form.
- **Loading Mechanism:**
    - The `Welcome.java` activity extracts these encoded Lua files from the APK's `assets` folder into the app's internal storage (`filesDir`) upon first launch or update.
    - The `LuaActivity.java` class (the base for `Main.java`) loads the scripts (`init.lua`, then `main.lua`) from internal storage using the `LuaState.LloadFile()` method.
- **Decoding Location:** The decoding of the obfuscated scripts is **not performed in the visible Java code**. It most likely occurs within the **native C code of the `libluajava.so` library** (located in `resources/lib/armeabi-v7a/`), specifically within the native implementation of the Lua file loading functions.
- **Permissions & Security Concerns:**
    - The application requests `WRITE_EXTERNAL_STORAGE`.
    - **Crucially, it defines and enables an Accessibility Service (`com.androlua.LuaAccessibilityService`).** Accessibility services grant apps extensive permissions to monitor screen content and interact with other applications.
    - The combination of the app's name ("Money Earning (UPI)"), the obfuscation of its core logic, and the use of a powerful Accessibility Service is a **major security red flag**. It strongly suggests the application might engage in risky or malicious activities related to financial transactions (e.g., monitoring UPI apps, performing unauthorized actions, phishing).

## Conclusion

This application requires significant caution. Its true functionality is hidden by obfuscation, and the requested Accessibility Service permission indicates a high potential for malicious behavior. Further analysis would require reverse-engineering the obfuscated Lua scripts or the native `libluajava.so` library.
