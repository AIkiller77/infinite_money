package com.tencent.open.utils;

/* compiled from: ProGuard */
public final class l implements Cloneable {
    private long a;

    public l(long j) {
        this.a = j;
    }

    public byte[] a() {
        return new byte[]{(byte) ((int) (this.a & 255)), (byte) ((int) ((this.a & 65280) >> 8)), (byte) ((int) ((this.a & 16711680) >> 16)), (byte) ((int) ((this.a & 4278190080L) >> 24))};
    }

    public long b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof l)) {
            return false;
        }
        return this.a == ((l) obj2).b();
    }

    public int hashCode() {
        return (int) this.a;
    }
}
