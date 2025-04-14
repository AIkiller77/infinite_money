package com.android.cglib.dx.d;

import java.util.Arrays;

public final class k extends o {
    public static final k a = new k(0);
    private int[] b;
    private int c;
    private boolean d;

    static {
        a.e();
    }

    public k() {
        this(4);
    }

    public k(int i) {
        super(true);
        try {
            this.b = new int[i];
            this.c = 0;
            this.d = true;
        } catch (NegativeArraySizeException unused) {
            throw new IllegalArgumentException("size < 0");
        }
    }

    private void c() {
        if (this.c == this.b.length) {
            int[] iArr = new int[(((this.c * 3) / 2) + 10)];
            System.arraycopy(this.b, 0, iArr, 0, this.c);
            this.b = iArr;
        }
    }

    public int a() {
        return this.c;
    }

    public int a(int i) {
        if (i >= this.c) {
            throw new IndexOutOfBoundsException("n >= size()");
        }
        try {
            return this.b[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IndexOutOfBoundsException("n < 0");
        }
    }

    public void a(int i, int i2) {
        h();
        if (i >= this.c) {
            throw new IndexOutOfBoundsException("n >= size()");
        }
        try {
            this.b[i] = i2;
            this.d = false;
        } catch (ArrayIndexOutOfBoundsException unused) {
            if (i < 0) {
                throw new IllegalArgumentException("n < 0");
            }
        }
    }

    public void b() {
        h();
        if (!this.d) {
            Arrays.sort(this.b, 0, this.c);
            this.d = true;
        }
    }

    public void b(int i) {
        h();
        c();
        int[] iArr = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        iArr[i2] = i;
        if (this.d) {
            boolean z = true;
            if (this.c > 1) {
                if (i < this.b[this.c - 2]) {
                    z = false;
                }
                this.d = z;
            }
        }
    }

    public void c(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("newSize < 0");
        } else if (i > this.c) {
            throw new IllegalArgumentException("newSize > size");
        } else {
            h();
            this.c = i;
        }
    }

    public int d(int i) {
        int e = e(i);
        if (e >= 0) {
            return e;
        }
        return -1;
    }

    public int e(int i) {
        int i2 = this.c;
        if (!this.d) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.b[i3] == i) {
                    return i3;
                }
            }
            return -i2;
        }
        int i4 = i2;
        int i5 = -1;
        while (i4 > i5 + 1) {
            int i6 = ((i4 - i5) >> 1) + i5;
            if (i <= this.b[i6]) {
                i4 = i6;
            } else {
                i5 = i6;
            }
        }
        return i4 != i2 ? i == this.b[i4] ? i4 : (-i4) - 1 : (-i2) - 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (this.d != kVar.d || this.c != kVar.c) {
            return false;
        }
        for (int i = 0; i < this.c; i++) {
            if (this.b[i] != kVar.b[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean f(int i) {
        return d(i) >= 0;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.c; i2++) {
            i = (i * 31) + this.b[i2];
        }
        return i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer((this.c * 5) + 10);
        stringBuffer.append('{');
        for (int i = 0; i < this.c; i++) {
            if (i != 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.b[i]);
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
