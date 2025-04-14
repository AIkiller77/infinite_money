package com.android.cglib.dx.d;

import java.io.Writer;
import java.util.ArrayList;

public final class d implements a, e {
    private final boolean a;
    private byte[] b;
    private int c;
    private boolean d;
    private ArrayList<a> e;
    private int f;
    private int g;

    private static class a {
        private final int a;
        private int b;
        private final String c;

        public a(int i, int i2, String str) {
            this.a = i;
            this.b = i2;
            this.c = str;
        }

        public a(int i, String str) {
            this(i, Integer.MAX_VALUE, str);
        }

        public int a() {
            return this.a;
        }

        public void a(int i) {
            if (this.b == Integer.MAX_VALUE) {
                this.b = i;
            }
        }

        public int b() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }

        public String c() {
            return this.c;
        }
    }

    public d() {
        this(1000);
    }

    public d(int i) {
        this(new byte[i], true);
    }

    public d(byte[] bArr) {
        this(bArr, false);
    }

    private d(byte[] bArr, boolean z) {
        if (bArr == null) {
            throw new NullPointerException("data == null");
        }
        this.a = z;
        this.b = bArr;
        this.c = 0;
        this.d = false;
        this.e = null;
        this.f = 0;
        this.g = 0;
    }

    private static void i() {
        throw new IndexOutOfBoundsException("attempt to write past the end");
    }

    private void i(int i) {
        if (this.b.length < i) {
            byte[] bArr = new byte[((i * 2) + 1000)];
            System.arraycopy(this.b, 0, bArr, 0, this.c);
            this.b = bArr;
        }
    }

    public void a(int i) {
        if (this.c != i) {
            throw new g("expected cursor " + i + "; actual value: " + this.c);
        }
    }

    public void a(int i, String str) {
        if (this.e != null) {
            c();
            int size = this.e.size();
            int b2 = size == 0 ? 0 : this.e.get(size - 1).b();
            if (b2 <= this.c) {
                b2 = this.c;
            }
            this.e.add(new a(b2, i + b2, str));
        }
    }

    public void a(int i, boolean z) {
        if (this.e != null || this.c != 0) {
            throw new RuntimeException("cannot enable annotations");
        } else if (i < 40) {
            throw new IllegalArgumentException("annotationWidth < 40");
        } else {
            int i2 = (((i - 7) / 15) + 1) & -2;
            if (i2 < 6) {
                i2 = 6;
            } else if (i2 > 10) {
                i2 = 10;
            }
            this.e = new ArrayList<>(1000);
            this.f = i;
            this.g = i2;
            this.d = z;
        }
    }

    public void a(c cVar) {
        int a2 = cVar.a();
        int i = this.c;
        int i2 = a2 + i;
        if (this.a) {
            i(i2);
        } else if (i2 > this.b.length) {
            i();
            return;
        }
        cVar.a(this.b, i);
        this.c = i2;
    }

    public void a(Writer writer) {
        int i;
        String str;
        int d2 = d();
        s sVar = new s(writer, (this.f - d2) - 1, d2, "|");
        Writer a2 = sVar.a();
        Writer b2 = sVar.b();
        int size = this.e.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < this.c && i2 < size) {
            a aVar = this.e.get(i2);
            int a3 = aVar.a();
            if (i3 < a3) {
                str = "";
                i = i3;
            } else {
                int b3 = aVar.b();
                str = aVar.c();
                i2++;
                i = a3;
                a3 = b3;
            }
            a2.write(i.a(this.b, i, a3 - i, i, this.g, 6));
            b2.write(str);
            sVar.c();
            i3 = a3;
        }
        if (i3 < this.c) {
            a2.write(i.a(this.b, i3, this.c - i3, i3, this.g, 6));
        }
        while (i2 < size) {
            b2.write(this.e.get(i2).c());
            i2++;
        }
        sVar.c();
    }

    public void a(String str) {
        if (this.e != null) {
            c();
            this.e.add(new a(this.c, str));
        }
    }

    public void a(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    public void a(byte[] bArr, int i, int i2) {
        int i3 = this.c;
        int i4 = i3 + i2;
        int i5 = i + i2;
        if ((i | i2 | i4) < 0 || i5 > bArr.length) {
            throw new IndexOutOfBoundsException("bytes.length " + bArr.length + "; " + i + "..!" + i4);
        }
        if (this.a) {
            i(i4);
        } else if (i4 > this.b.length) {
            i();
            return;
        }
        System.arraycopy(bArr, i, this.b, i3, i2);
        this.c = i4;
    }

    public boolean a() {
        return this.e != null;
    }

    public void b(int i) {
        int i2 = this.c;
        int i3 = i2 + 1;
        if (this.a) {
            i(i3);
        } else if (i3 > this.b.length) {
            i();
            return;
        }
        this.b[i2] = (byte) i;
        this.c = i3;
    }

    public boolean b() {
        return this.d;
    }

    public void c() {
        int size;
        if (this.e != null && (size = this.e.size()) != 0) {
            this.e.get(size - 1).a(this.c);
        }
    }

    public void c(int i) {
        int i2 = this.c;
        int i3 = i2 + 2;
        if (this.a) {
            i(i3);
        } else if (i3 > this.b.length) {
            i();
            return;
        }
        this.b[i2] = (byte) i;
        this.b[i2 + 1] = (byte) (i >> 8);
        this.c = i3;
    }

    public int d() {
        return this.f - (((this.g * 2) + 8) + (this.g / 2));
    }

    public void d(int i) {
        int i2 = this.c;
        int i3 = i2 + 4;
        if (this.a) {
            i(i3);
        } else if (i3 > this.b.length) {
            i();
            return;
        }
        this.b[i2] = (byte) i;
        this.b[i2 + 1] = (byte) (i >> 8);
        this.b[i2 + 2] = (byte) (i >> 16);
        this.b[i2 + 3] = (byte) (i >> 24);
        this.c = i3;
    }

    public int e(int i) {
        if (this.a) {
            i(this.c + 5);
        }
        int i2 = this.c;
        n.a(this, i);
        return this.c - i2;
    }

    public byte[] e() {
        return this.b;
    }

    public int f(int i) {
        if (this.a) {
            i(this.c + 5);
        }
        int i2 = this.c;
        n.b(this, i);
        return this.c - i2;
    }

    public byte[] f() {
        byte[] bArr = new byte[this.c];
        System.arraycopy(this.b, 0, bArr, 0, this.c);
        return bArr;
    }

    public int g() {
        return this.c;
    }

    public void g(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("count < 0");
        }
        int i2 = this.c + i;
        if (this.a) {
            i(i2);
        } else if (i2 > this.b.length) {
            i();
            return;
        }
        this.c = i2;
    }

    public void h() {
        c();
        if (this.e != null) {
            int size = this.e.size();
            while (size > 0) {
                int i = size - 1;
                a aVar = this.e.get(i);
                if (aVar.a() > this.c) {
                    this.e.remove(i);
                    size--;
                } else if (aVar.b() > this.c) {
                    aVar.b(this.c);
                    return;
                } else {
                    return;
                }
            }
        }
    }

    public void h(int i) {
        int i2 = i - 1;
        if (i < 0 || (i & i2) != 0) {
            throw new IllegalArgumentException("bogus alignment");
        }
        int i3 = (this.c + i2) & (i2 ^ -1);
        if (this.a) {
            i(i3);
        } else if (i3 > this.b.length) {
            i();
            return;
        }
        this.c = i3;
    }
}
