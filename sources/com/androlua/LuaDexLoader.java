package com.androlua;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.a.a.a.a.a.a.a;
import com.luajava.LuaException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import net.lingala.zip4j.util.InternalZipConstants;

public class LuaDexLoader {
    private static HashMap<String, LuaDexClassLoader> a = new HashMap<>();
    private ArrayList<ClassLoader> b = new ArrayList<>();
    private HashMap<String, String> c = new HashMap<>();
    private LuaContext d;
    private String e;
    private AssetManager f;
    private LuaResources g;
    private Resources.Theme h;
    private String i;

    public LuaDexLoader(LuaContext luaContext) {
        this.d = luaContext;
        this.e = luaContext.getLuaDir();
        this.i = LuaApplication.getInstance().getOdexDir();
    }

    public AssetManager getAssets() {
        return this.f;
    }

    public ArrayList<ClassLoader> getClassLoaders() {
        return this.b;
    }

    public HashMap<String, String> getLibrarys() {
        return this.c;
    }

    public Resources getResources() {
        return this.g;
    }

    public Resources.Theme getTheme() {
        return this.h;
    }

    public LuaDexClassLoader loadApp(String str) {
        try {
            LuaDexClassLoader luaDexClassLoader = a.get(str);
            if (luaDexClassLoader == null) {
                ApplicationInfo applicationInfo = this.d.getContext().getPackageManager().getPackageInfo(str, 0).applicationInfo;
                LuaDexClassLoader luaDexClassLoader2 = new LuaDexClassLoader(applicationInfo.publicSourceDir, LuaApplication.getInstance().getOdexDir(), applicationInfo.nativeLibraryDir, this.d.getContext().getClassLoader());
                a.put(str, luaDexClassLoader2);
                luaDexClassLoader = luaDexClassLoader2;
            }
            if (!this.b.contains(luaDexClassLoader)) {
                this.b.add(luaDexClassLoader);
            }
            return luaDexClassLoader;
        } catch (PackageManager.NameNotFoundException e2) {
            a.a(e2);
            return null;
        }
    }

    public DexClassLoader loadDex(String str) {
        LuaDexClassLoader luaDexClassLoader;
        String str2;
        StringBuilder sb;
        String str3;
        LuaDexClassLoader luaDexClassLoader2 = a.get(str);
        if (luaDexClassLoader2 == null) {
            luaDexClassLoader2 = loadApp(str);
        }
        if (luaDexClassLoader2 == null) {
            if (str.charAt(0) != '/') {
                str2 = this.e + InternalZipConstants.ZIP_FILE_SEPARATOR + str;
            } else {
                str2 = str;
            }
            if (!new File(str2).exists()) {
                if (new File(str2 + ".dex").exists()) {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str3 = ".dex";
                } else {
                    if (new File(str2 + ".jar").exists()) {
                        sb = new StringBuilder();
                        sb.append(str2);
                        str3 = ".jar";
                    } else {
                        throw new LuaException(str2 + " not found");
                    }
                }
                sb.append(str3);
                str2 = sb.toString();
            }
            String fileMD5 = LuaUtil.getFileMD5(str2);
            if (fileMD5 == null || !fileMD5.equals("0")) {
                str = fileMD5;
            }
            luaDexClassLoader = a.get(str);
            if (luaDexClassLoader == null) {
                luaDexClassLoader = new LuaDexClassLoader(str2, this.i, LuaApplication.getInstance().getApplicationInfo().nativeLibraryDir, this.d.getContext().getClassLoader());
                a.put(str, luaDexClassLoader);
            }
        } else {
            luaDexClassLoader = luaDexClassLoader2;
        }
        if (!this.b.contains(luaDexClassLoader)) {
            this.b.add(luaDexClassLoader);
            String dexPath = luaDexClassLoader.getDexPath();
            if (dexPath.endsWith(".jar")) {
                loadResources(dexPath);
            }
        }
        return luaDexClassLoader;
    }

    public void loadLib(String str) {
        int indexOf = str.indexOf(".");
        String substring = indexOf > 0 ? str.substring(0, indexOf) : str;
        if (substring.startsWith("lib")) {
            substring = substring.substring(3);
        }
        String str2 = this.d.getContext().getDir(substring, 0).getAbsolutePath() + "/lib" + substring + ".so";
        if (!new File(str2).exists()) {
            if (!new File(this.e + "/libs/lib" + substring + ".so").exists()) {
                throw new LuaException("can not find lib " + str);
            }
            LuaUtil.copyFile(this.e + "/libs/lib" + substring + ".so", str2);
        }
        this.c.put(substring, str2);
    }

    public void loadLibs() {
        File[] listFiles = new File(this.d.getLuaDir() + "/libs").listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (!file.isDirectory()) {
                    if (file.getAbsolutePath().endsWith(".so")) {
                        loadLib(file.getName());
                    } else {
                        loadDex(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public void loadResources(String str) {
        try {
            AssetManager newInstance = AssetManager.class.newInstance();
            if (((Integer) newInstance.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(newInstance, new Object[]{str})).intValue() != 0) {
                this.f = newInstance;
                Resources resources = this.d.getContext().getResources();
                this.g = new LuaResources(this.f, resources.getDisplayMetrics(), resources.getConfiguration());
                this.g.setSuperResources(resources);
                this.h = this.g.newTheme();
                this.h.setTo(this.d.getContext().getTheme());
            }
        } catch (Exception e2) {
            a.a(e2);
        }
    }
}
