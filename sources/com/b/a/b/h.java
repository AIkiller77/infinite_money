package com.b.a.b;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class h {
    private static final char[] f = {'(', ')', '{', '}', '.', ',', ';', '=', '+', '-', '/', '*', '&', '!', '|', ':', '[', ']', '<', '>', '?', '~', '%', '^'};
    protected HashMap<String, Integer> a = new HashMap<>(0);
    protected HashMap<String, Integer> b = new HashMap<>(0);
    protected HashMap<String, String[]> c = new HashMap<>(0);
    protected HashMap<String, Integer> d = new HashMap<>(0);
    protected HashMap<Character, Integer> e = b(f);
    private ArrayList<String> g = new ArrayList<>();
    private String[] h = new String[0];
    private String[] i;
    private String[] j;

    private HashMap<Character, Integer> b(char[] cArr) {
        HashMap<Character, Integer> hashMap = new HashMap<>(cArr.length);
        for (char valueOf : cArr) {
            hashMap.put(Character.valueOf(valueOf), 2);
        }
        return hashMap;
    }

    public void a() {
        this.h = (String[]) this.g.toArray(new String[this.g.size()]);
    }

    public void a(String str, String[] strArr) {
        this.c.put(str, strArr);
    }

    /* access modifiers changed from: protected */
    public void a(char[] cArr) {
        this.e = b(cArr);
    }

    public void a(String[] strArr) {
        this.i = strArr;
        this.a = new HashMap<>(strArr.length);
        for (String put : strArr) {
            this.a.put(put, 1);
        }
    }

    public final boolean a(char c2) {
        return this.e.containsKey(Character.valueOf(c2));
    }

    public boolean a(char c2, char c3) {
        return c2 == '/' && c3 == '/';
    }

    public final boolean a(String str, String str2) {
        for (String equals : this.c.get(str)) {
            if (equals.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public String[] a(String str) {
        return this.c.get(str);
    }

    public void b(String str) {
        this.c.remove(str);
    }

    public void b(String[] strArr) {
        this.j = strArr;
        ArrayList arrayList = new ArrayList();
        this.b = new HashMap<>(strArr.length);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (!arrayList.contains(strArr[i2])) {
                arrayList.add(strArr[i2]);
            }
            this.b.put(strArr[i2], 3);
        }
        this.j = new String[arrayList.size()];
        arrayList.toArray(this.j);
    }

    public boolean b(char c2) {
        return c2 == ' ' || c2 == 10 || c2 == 9 || c2 == 13 || c2 == 12 || c2 == 65535;
    }

    public boolean b(char c2, char c3) {
        return c2 == '/' && c3 == '*';
    }

    public String[] b() {
        return this.h;
    }

    public void c(String str) {
        if (!this.g.contains(str) && !this.b.containsKey(str)) {
            this.g.add(str);
        }
        this.d.put(str, 3);
    }

    public boolean c(char c2) {
        return c2 == '.';
    }

    public boolean c(char c2, char c3) {
        return c2 == '*' && c3 == '/';
    }

    public String[] c() {
        return this.j;
    }

    public boolean d(char c2) {
        return c2 == '\\';
    }

    public final boolean d(String str) {
        return this.a.containsKey(str);
    }

    public String[] d() {
        return this.i;
    }

    public void e() {
        this.g.clear();
        this.d.clear();
    }

    public boolean e(char c2) {
        return false;
    }

    public final boolean e(String str) {
        return this.b.containsKey(str);
    }

    public boolean f() {
        return true;
    }

    public boolean f(char c2) {
        return c2 == '\"';
    }

    public final boolean f(String str) {
        return this.c.containsKey(str);
    }

    public boolean g(char c2) {
        return c2 == '\'';
    }

    public final boolean g(String str) {
        return this.d.containsKey(str);
    }

    public boolean h(char c2) {
        return c2 == '#';
    }

    public boolean i(char c2) {
        return false;
    }
}
