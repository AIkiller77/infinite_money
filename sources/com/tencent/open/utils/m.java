package com.tencent.open.utils;

import android.support.v4.view.MotionEventCompat;

/* compiled from: ProGuard */
public final class m implements Cloneable {
    private int a;

    public m(int i) {
        this.a = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public m(byte[] bArr) {
        this(bArr, 0);
    }

    public m(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        this.a = (bArr2[i2 + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        this.a += bArr2[i2] & 255;
    }

    public byte[] a() {
        return new byte[]{(byte) (this.a & 255), (byte) ((this.a & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8)};
    }

    public int b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof m)) {
            return false;
        }
        return this.a == ((m) obj2).b();
    }

    public int hashCode() {
        return this.a;
    }
}
