package com.b.a.b;

import android.util.Log;

public class q extends Exception {
    public static void a(String str) {
        a(false, str);
    }

    public static void a(boolean z, String str) {
        if (!z) {
            System.err.print("TextWarrior assertion failed: ");
            System.err.println(str);
            Log.i("lua", str);
        }
    }
}
