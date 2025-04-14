package com.android.cglib.dx;

import com.android.cglib.dx.c.c.j;
import com.android.cglib.dx.c.c.t;
import com.android.cglib.dx.c.c.v;

public final class FieldId<D, V> {
    final TypeId<D> a;
    final TypeId<V> b;
    final String c;
    final t d;
    final j e;

    FieldId(TypeId<D> typeId, TypeId<V> typeId2, String str) {
        if (typeId == null || typeId2 == null || str == null) {
            throw new NullPointerException();
        }
        this.a = typeId;
        this.b = typeId2;
        this.c = str;
        this.d = new t(new v(str), new v(typeId2.a));
        this.e = new j(typeId.c, this.d);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FieldId)) {
            return false;
        }
        FieldId fieldId = (FieldId) obj;
        return fieldId.a.equals(this.a) && fieldId.c.equals(this.c);
    }

    public TypeId<D> getDeclaringType() {
        return this.a;
    }

    public String getName() {
        return this.c;
    }

    public TypeId<V> getType() {
        return this.b;
    }

    public int hashCode() {
        return this.a.hashCode() + (this.c.hashCode() * 37);
    }

    public String toString() {
        return this.a + "." + this.c;
    }
}
