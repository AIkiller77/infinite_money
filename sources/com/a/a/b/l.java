package com.a.a.b;

public class l {
    private int a = 0;

    public int a(f fVar, String str, int i, int i2, boolean z, boolean z2) {
        if (str.length() == 0) {
            return -1;
        }
        if (i < 0) {
            r.a("TextBuffer.find: Invalid start position");
            i = 0;
        }
        if (i2 > fVar.g()) {
            r.a("TextBuffer.find: Invalid end position");
            i2 = fVar.g();
        }
        int min = Math.min(i2, (fVar.g() - str.length()) + 1);
        while (i < min && (!a(fVar, str, i, z) || (z2 && !a(fVar, i, str.length())))) {
            i++;
            this.a++;
        }
        if (i < min) {
            return i;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public boolean a(f fVar, int i, int i2) {
        h a2 = k.a();
        boolean b = i == 0 ? true : a2.b(fVar.charAt(i - 1));
        int i3 = i + i2;
        return b && (i3 == fVar.g() ? true : a2.b(fVar.charAt(i3)));
    }

    /* access modifiers changed from: protected */
    public boolean a(f fVar, String str, int i, boolean z) {
        if (fVar.g() - i < str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (z && str.charAt(i2) != fVar.charAt(i2 + i)) {
                return false;
            }
            if (!z && Character.toLowerCase(str.charAt(i2)) != Character.toLowerCase(fVar.charAt(i2 + i))) {
                return false;
            }
        }
        return true;
    }
}
