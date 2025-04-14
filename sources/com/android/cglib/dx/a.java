package com.android.cglib.dx;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

class a {
    a() {
    }

    private String a(ClassLoader classLoader, Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField("path");
            declaredField.setAccessible(true);
            return (String) declaredField.get(classLoader);
        } catch (ClassCastException | IllegalAccessException | NoSuchFieldException unused) {
            return a(classLoader.toString());
        }
    }

    static String a(String str) {
        return str.contains("DexPathList") ? e(str) : d(str);
    }

    private ClassLoader b() {
        return a.class.getClassLoader();
    }

    static String[] c(String str) {
        if (str.startsWith("dexPath=")) {
            int length = "dexPath=".length();
            int indexOf = str.indexOf(44);
            str = indexOf == -1 ? str.substring(length) : str.substring(length, indexOf);
        }
        return str.split(":");
    }

    private static String d(String str) {
        int lastIndexOf = str.lastIndexOf(91);
        if (lastIndexOf != -1) {
            str = str.substring(lastIndexOf + 1);
        }
        int indexOf = str.indexOf(93);
        return indexOf == -1 ? str : str.substring(0, indexOf);
    }

    private static String e(String str) {
        int indexOf = str.indexOf("DexPathList") + "DexPathList".length();
        if (str.length() <= indexOf + 4) {
            return str;
        }
        String substring = str.substring(indexOf);
        int indexOf2 = substring.indexOf(93);
        if (substring.charAt(0) != '[' || substring.charAt(1) != '[' || indexOf2 < 0) {
            return str;
        }
        String[] split = substring.substring(2, indexOf2).split(",");
        for (int i = 0; i < split.length; i++) {
            int indexOf3 = split[i].indexOf(34);
            int lastIndexOf = split[i].lastIndexOf(34);
            if (indexOf3 > 0 && indexOf3 < lastIndexOf) {
                split[i] = split[i].substring(indexOf3 + 1, lastIndexOf);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (sb.length() > 0) {
                sb.append(':');
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    public File a() {
        try {
            ClassLoader b = b();
            Class<?> cls = Class.forName("dalvik.system.PathClassLoader");
            cls.cast(b);
            File[] b2 = b(a(b, cls));
            if (b2.length > 0) {
                return b2[0];
            }
            return null;
        } catch (ClassCastException | ClassNotFoundException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(File file) {
        return file.exists();
    }

    /* access modifiers changed from: package-private */
    public boolean b(File file) {
        return file.isDirectory() && file.canWrite();
    }

    /* access modifiers changed from: package-private */
    public File[] b(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : c(str)) {
            if (str2.startsWith("/data/app/")) {
                int length = "/data/app/".length();
                int lastIndexOf = str2.lastIndexOf(".apk");
                if (lastIndexOf == str2.length() - 4) {
                    int indexOf = str2.indexOf("-");
                    if (indexOf != -1) {
                        lastIndexOf = indexOf;
                    }
                    File file = new File("/data/data/" + str2.substring(length, lastIndexOf));
                    if (b(file)) {
                        File file2 = new File(file, "cache");
                        if ((a(file2) || file2.mkdir()) && b(file2)) {
                            arrayList.add(file2);
                        }
                    }
                }
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }
}
