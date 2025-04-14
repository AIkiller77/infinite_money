package com.android.cglib.dx.d;

import java.util.Arrays;

public class h extends o implements r {
    private Object[] a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(int i) {
        super(i != 0);
        try {
            this.a = new Object[i];
        } catch (NegativeArraySizeException unused) {
            throw new IllegalArgumentException("size < 0");
        }
    }

    private Object a(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("n < 0");
        }
        throw new IndexOutOfBoundsException("n >= size()");
    }

    private String a(String str, String str2, String str3, boolean z) {
        int length = this.a.length;
        StringBuffer stringBuffer = new StringBuffer((length * 10) + 10);
        if (str != null) {
            stringBuffer.append(str);
        }
        for (int i = 0; i < length; i++) {
            if (!(i == 0 || str2 == null)) {
                stringBuffer.append(str2);
            }
            if (z) {
                stringBuffer.append(((r) this.a[i]).a_());
            } else {
                stringBuffer.append(this.a[i]);
            }
        }
        if (str3 != null) {
            stringBuffer.append(str3);
        }
        return stringBuffer.toString();
    }

    public final int a() {
        return this.a.length;
    }

    public String a(String str, String str2, String str3) {
        return a(str, str2, str3, false);
    }

    /* access modifiers changed from: protected */
    public final void a(int i, Object obj) {
        h();
        try {
            this.a[i] = obj;
        } catch (ArrayIndexOutOfBoundsException unused) {
            a(i);
        }
    }

    public String a_() {
        String name = getClass().getName();
        return a(name.substring(name.lastIndexOf(46) + 1) + '{', ", ", "}", true);
    }

    public String b(String str, String str2, String str3) {
        return a(str, str2, str3, true);
    }

    /* access modifiers changed from: protected */
    public final Object d(int i) {
        try {
            Object obj = this.a[i];
            if (obj != null) {
                return obj;
            }
            throw new NullPointerException("unset: " + i);
        } catch (ArrayIndexOutOfBoundsException unused) {
            return a(i);
        }
    }

    /* access modifiers changed from: protected */
    public final Object e(int i) {
        return this.a[i];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.a, ((h) obj).a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public String toString() {
        String name = getClass().getName();
        return a(name.substring(name.lastIndexOf(46) + 1) + '{', ", ", "}", false);
    }
}
