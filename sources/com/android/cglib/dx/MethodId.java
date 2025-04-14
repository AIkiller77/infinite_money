package com.android.cglib.dx;

import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.c.c.t;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.d.a;
import java.util.List;

public final class MethodId<D, R> {
    final TypeId<D> a;
    final TypeId<R> b;
    final String c;
    final TypeList d;
    final t e;
    final s f;

    MethodId(TypeId<D> typeId, TypeId<R> typeId2, String str, TypeList typeList) {
        if (typeId == null || typeId2 == null || str == null || typeList == null) {
            throw new NullPointerException();
        }
        this.a = typeId;
        this.b = typeId2;
        this.c = str;
        this.d = typeList;
        this.e = new t(new v(str), new v(a(false)));
        this.f = new s(typeId.c, this.e);
    }

    /* access modifiers changed from: package-private */
    public String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (z) {
            sb.append(this.a.a);
        }
        for (TypeId<?> typeId : this.d.a) {
            sb.append(typeId.a);
        }
        sb.append(")");
        sb.append(this.b.a);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public a b(boolean z) {
        return a.a(a(z));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MethodId)) {
            return false;
        }
        MethodId methodId = (MethodId) obj;
        return methodId.a.equals(this.a) && methodId.c.equals(this.c) && methodId.d.equals(this.d) && methodId.b.equals(this.b);
    }

    public TypeId<D> getDeclaringType() {
        return this.a;
    }

    public String getName() {
        return this.c;
    }

    public List<TypeId<?>> getParameters() {
        return this.d.asList();
    }

    public TypeId<R> getReturnType() {
        return this.b;
    }

    public int hashCode() {
        return ((((((527 + this.a.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.b.hashCode();
    }

    public boolean isConstructor() {
        return this.c.equals("<init>");
    }

    public String toString() {
        return this.a + "." + this.c + "(" + this.d + ")";
    }
}
