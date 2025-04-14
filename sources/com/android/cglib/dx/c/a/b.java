package com.android.cglib.dx.c.a;

import com.android.cglib.dx.d.r;

public enum b implements r {
    RUNTIME("runtime"),
    BUILD("build"),
    SYSTEM("system"),
    EMBEDDED("embedded");
    
    private final String e;

    private b(String str) {
        this.e = str;
    }

    public String a_() {
        return this.e;
    }
}
