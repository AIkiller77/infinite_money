package com.android.cglib.dx.c.d;

import com.tencent.qq.widget.R;
import java.util.HashMap;
import net.lingala.zip4j.util.InternalZipConstants;

public final class c implements d, Comparable<c> {
    public static final c A = a("Ljava/lang/Void;");
    public static final c B = a.l();
    public static final c C = b.l();
    public static final c D = c.l();
    public static final c E = d.l();
    public static final c F = e.l();
    public static final c G = f.l();
    public static final c H = g.l();
    public static final c I = o.l();
    public static final c J = h.l();
    private static final HashMap<String, c> K = new HashMap<>(500);
    public static final c a = new c("Z", 1);
    public static final c b = new c("B", 2);
    public static final c c = new c("C", 3);
    public static final c d = new c("D", 4);
    public static final c e = new c("F", 5);
    public static final c f = new c("I", 6);
    public static final c g = new c("J", 7);
    public static final c h = new c("S", 8);
    public static final c i = new c("V", 0);
    public static final c j = new c("<null>", 9);
    public static final c k = new c("<addr>", 10);
    public static final c l = a("Ljava/lang/annotation/Annotation;");
    public static final c m = a("Ljava/lang/Class;");
    public static final c n = a("Ljava/lang/Cloneable;");
    public static final c o = a("Ljava/lang/Object;");
    public static final c p = a("Ljava/io/Serializable;");

    /* renamed from: q  reason: collision with root package name */
    public static final c f19q = a("Ljava/lang/String;");
    public static final c r = a("Ljava/lang/Throwable;");
    public static final c s = a("Ljava/lang/Boolean;");
    public static final c t = a("Ljava/lang/Byte;");
    public static final c u = a("Ljava/lang/Character;");
    public static final c v = a("Ljava/lang/Double;");
    public static final c w = a("Ljava/lang/Float;");
    public static final c x = a("Ljava/lang/Integer;");
    public static final c y = a("Ljava/lang/Long;");
    public static final c z = a("Ljava/lang/Short;");
    private final String L;
    private final int M;
    private final int N;
    private String O;
    private c P;
    private c Q;
    private c R;

    static {
        b(a);
        b(b);
        b(c);
        b(d);
        b(e);
        b(f);
        b(g);
        b(h);
    }

    private c(String str, int i2) {
        this(str, i2, -1);
    }

    private c(String str, int i2, int i3) {
        if (str == null) {
            throw new NullPointerException("descriptor == null");
        } else if (i2 < 0 || i2 >= 11) {
            throw new IllegalArgumentException("bad basicType");
        } else if (i3 < -1) {
            throw new IllegalArgumentException("newAt < -1");
        } else {
            this.L = str;
            this.M = i2;
            this.N = i3;
            this.P = null;
            this.Q = null;
            this.R = null;
        }
    }

    public static c a(String str) {
        c cVar;
        synchronized (K) {
            cVar = K.get(str);
        }
        if (cVar != null) {
            return cVar;
        }
        try {
            char charAt = str.charAt(0);
            if (charAt == '[') {
                return a(str.substring(1)).l();
            }
            int length = str.length();
            if (charAt == 'L') {
                int i2 = length - 1;
                if (str.charAt(i2) == ';') {
                    for (int i3 = 1; i3 < i2; i3++) {
                        switch (str.charAt(i3)) {
                            case '(':
                            case ')':
                            case '.':
                            case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                            case R.styleable.AppCompatTheme_controlBackground /*91*/:
                                throw new IllegalArgumentException("bad descriptor: " + str);
                            case '/':
                                if (i3 != 1 && i3 != i2 && str.charAt(i3 - 1) != '/') {
                                    break;
                                } else {
                                    throw new IllegalArgumentException("bad descriptor: " + str);
                                }
                                break;
                        }
                    }
                    return b(new c(str, 9));
                }
            }
            throw new IllegalArgumentException("bad descriptor: " + str);
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("descriptor is empty");
        } catch (NullPointerException unused2) {
            throw new NullPointerException("descriptor == null");
        }
    }

    private static c b(c cVar) {
        synchronized (K) {
            String e2 = cVar.e();
            c cVar2 = K.get(e2);
            if (cVar2 != null) {
                return cVar2;
            }
            K.put(e2, cVar);
            return cVar;
        }
    }

    public static c b(String str) {
        try {
            return str.equals("V") ? i : a(str);
        } catch (NullPointerException unused) {
            throw new NullPointerException("descriptor == null");
        }
    }

    /* renamed from: a */
    public int compareTo(c cVar) {
        return this.L.compareTo(cVar.L);
    }

    public String a_() {
        switch (this.M) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "byte";
            case 3:
                return "char";
            case 4:
                return "double";
            case 5:
                return "float";
            case 6:
                return "int";
            case 7:
                return "long";
            case 8:
                return "short";
            case 9:
                if (!k()) {
                    return f().replace(InternalZipConstants.ZIP_FILE_SEPARATOR, ".");
                }
                return m().a_() + "[]";
            default:
                return this.L;
        }
    }

    public c b() {
        return this;
    }

    public int c() {
        return this.M;
    }

    public int d() {
        int i2 = this.M;
        if (!(i2 == 6 || i2 == 8)) {
            switch (i2) {
                case 1:
                case 2:
                case 3:
                    break;
                default:
                    return this.M;
            }
        }
        return 6;
    }

    public String e() {
        return this.L;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        return this.L.equals(((c) obj).L);
    }

    public String f() {
        if (this.O == null) {
            if (!j()) {
                throw new IllegalArgumentException("not an object type: " + this.L);
            }
            this.O = this.L.charAt(0) == '[' ? this.L : this.L.substring(1, this.L.length() - 1);
        }
        return this.O;
    }

    public int g() {
        int i2 = this.M;
        return (i2 == 4 || i2 == 7) ? 2 : 1;
    }

    public boolean h() {
        int i2 = this.M;
        return i2 == 4 || i2 == 7;
    }

    public int hashCode() {
        return this.L.hashCode();
    }

    public boolean i() {
        int i2 = this.M;
        if (i2 == 6 || i2 == 8) {
            return true;
        }
        switch (i2) {
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    public boolean j() {
        return this.M == 9;
    }

    public boolean k() {
        return this.L.charAt(0) == '[';
    }

    public c l() {
        if (this.P == null) {
            this.P = b(new c('[' + this.L, 9));
        }
        return this.P;
    }

    public c m() {
        if (this.Q == null) {
            if (this.L.charAt(0) != '[') {
                throw new IllegalArgumentException("not an array type: " + this.L);
            }
            this.Q = a(this.L.substring(1));
        }
        return this.Q;
    }

    public String toString() {
        return this.L;
    }
}
