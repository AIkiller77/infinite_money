package com.android.cglib.dx;

import com.android.cglib.dx.c.b.m;

public final class Local<T> {
    final TypeId<T> a;
    private final Code b;
    private int c = -1;
    private m d;

    private Local(Code code, TypeId<T> typeId) {
        this.b = code;
        this.a = typeId;
    }

    static <T> Local<T> a(Code code, TypeId<T> typeId) {
        return new Local<>(code, typeId);
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.a.b.g();
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        this.c = i;
        this.d = m.a(i, this.a.b);
        return a();
    }

    /* access modifiers changed from: package-private */
    public m b() {
        if (this.d == null) {
            this.b.a();
            if (this.d == null) {
                throw new AssertionError();
            }
        }
        return this.d;
    }

    public TypeId getType() {
        return this.a;
    }

    public String toString() {
        return "v" + this.c + "(" + this.a + ")";
    }
}
