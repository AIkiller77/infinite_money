package com.android.cglib.dx.d;

public final class c {
    private final byte[] a;
    private final int b;
    private final int c;

    public c(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public c(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("bytes == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("start < 0");
        } else if (i2 < i) {
            throw new IllegalArgumentException("end < start");
        } else if (i2 > bArr.length) {
            throw new IllegalArgumentException("end > bytes.length");
        } else {
            this.a = bArr;
            this.b = i;
            this.c = i2 - i;
        }
    }

    public int a() {
        return this.c;
    }

    public void a(byte[] bArr, int i) {
        if (bArr.length - i < this.c) {
            throw new IndexOutOfBoundsException("(out.length - offset) < size()");
        }
        System.arraycopy(this.a, this.b, bArr, i, this.c);
    }
}
