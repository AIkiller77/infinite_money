package com.android.cglib.dx.c.d;

import java.util.HashMap;

public final class a implements Comparable<a> {
    private static final HashMap<String, a> a = new HashMap<>(500);
    private final String b;
    private final c c;
    private final b d;
    private b e;

    private a(String str, c cVar, b bVar) {
        if (str == null) {
            throw new NullPointerException("descriptor == null");
        } else if (cVar == null) {
            throw new NullPointerException("returnType == null");
        } else if (bVar == null) {
            throw new NullPointerException("parameterTypes == null");
        } else {
            this.b = str;
            this.c = cVar;
            this.d = bVar;
            this.e = null;
        }
    }

    public static a a(String str) {
        a aVar;
        int i;
        if (str == null) {
            throw new NullPointerException("descriptor == null");
        }
        synchronized (a) {
            aVar = a.get(str);
        }
        if (aVar != null) {
            return aVar;
        }
        c[] b2 = b(str);
        int i2 = 1;
        int i3 = 0;
        while (true) {
            char charAt = str.charAt(i2);
            if (charAt == ')') {
                c b3 = c.b(str.substring(i2 + 1));
                b bVar = new b(i3);
                for (int i4 = 0; i4 < i3; i4++) {
                    bVar.a(i4, b2[i4]);
                }
                return b(new a(str, b3, bVar));
            }
            int i5 = i2;
            while (charAt == '[') {
                i5++;
                charAt = str.charAt(i5);
            }
            if (charAt == 'L') {
                int indexOf = str.indexOf(59, i5);
                if (indexOf == -1) {
                    throw new IllegalArgumentException("bad descriptor");
                }
                i = indexOf + 1;
            } else {
                i = i5 + 1;
            }
            b2[i3] = c.a(str.substring(i2, i));
            i3++;
            i2 = i;
        }
    }

    private static a b(a aVar) {
        synchronized (a) {
            String a2 = aVar.a();
            a aVar2 = a.get(a2);
            if (aVar2 != null) {
                return aVar2;
            }
            a.put(a2, aVar);
            return aVar;
        }
    }

    private static c[] b(String str) {
        int length = str.length();
        int i = 0;
        if (str.charAt(0) != '(') {
            throw new IllegalArgumentException("bad descriptor");
        }
        int i2 = 1;
        int i3 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = str.charAt(i2);
            if (charAt == ')') {
                i = i2;
                break;
            }
            if (charAt >= 'A' && charAt <= 'Z') {
                i3++;
            }
            i2++;
        }
        if (i == 0 || i == length - 1) {
            throw new IllegalArgumentException("bad descriptor");
        } else if (str.indexOf(41, i + 1) == -1) {
            return new c[i3];
        } else {
            throw new IllegalArgumentException("bad descriptor");
        }
    }

    /* renamed from: a */
    public int compareTo(a aVar) {
        if (this == aVar) {
            return 0;
        }
        int a2 = this.c.compareTo(aVar.c);
        if (a2 != 0) {
            return a2;
        }
        int a3 = this.d.a();
        int a4 = aVar.d.a();
        int min = Math.min(a3, a4);
        for (int i = 0; i < min; i++) {
            int a5 = this.d.b(i).compareTo(aVar.d.b(i));
            if (a5 != 0) {
                return a5;
            }
        }
        if (a3 < a4) {
            return -1;
        }
        return a3 > a4 ? 1 : 0;
    }

    public a a(c cVar) {
        String str = "(" + cVar.e() + this.b.substring(1);
        b b2 = this.d.b(cVar);
        b2.e();
        return b(new a(str, this.c, b2));
    }

    public String a() {
        return this.b;
    }

    public c b() {
        return this.c;
    }

    public b c() {
        return this.d;
    }

    public b d() {
        if (this.e == null) {
            int a2 = this.d.a();
            b bVar = new b(a2);
            boolean z = false;
            for (int i = 0; i < a2; i++) {
                c b2 = this.d.b(i);
                if (b2.i()) {
                    b2 = c.f;
                    z = true;
                }
                bVar.a(i, b2);
            }
            if (!z) {
                bVar = this.d;
            }
            this.e = bVar;
        }
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        return this.b.equals(((a) obj).b);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        return this.b;
    }
}
