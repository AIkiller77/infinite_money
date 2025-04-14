package com.luajava;

public class LuaString implements CharSequence {
    private byte[] a = new byte[0];

    public LuaString(String str) {
        this.a = str.getBytes();
    }

    public LuaString(byte[] bArr) {
        this.a = bArr;
    }

    public char charAt(int i) {
        return (char) this.a[i];
    }

    public int length() {
        return this.a.length;
    }

    public CharSequence subSequence(int i, int i2) {
        return new String(this.a, i, i2);
    }

    public byte[] toByteArray() {
        return this.a;
    }

    public String toString() {
        return new String(this.a);
    }
}
