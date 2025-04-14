package com.android.cglib.dx;

import com.android.cglib.dx.c.c.e;
import com.android.cglib.dx.c.c.f;
import com.android.cglib.dx.c.c.g;
import com.android.cglib.dx.c.c.h;
import com.android.cglib.dx.c.c.k;
import com.android.cglib.dx.c.c.l;
import com.android.cglib.dx.c.c.m;
import com.android.cglib.dx.c.c.q;
import com.android.cglib.dx.c.c.u;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.c.x;

final class Constants {
    private Constants() {
    }

    static x a(Object obj) {
        if (obj == null) {
            return m.a;
        }
        if (obj instanceof Boolean) {
            return e.a(((Boolean) obj).booleanValue());
        }
        if (obj instanceof Byte) {
            return f.a(((Byte) obj).byteValue());
        }
        if (obj instanceof Character) {
            return g.a(((Character) obj).charValue());
        }
        if (obj instanceof Double) {
            return h.a(Double.doubleToLongBits(((Double) obj).doubleValue()));
        }
        if (obj instanceof Float) {
            return k.a(Float.floatToIntBits(((Float) obj).floatValue()));
        }
        if (obj instanceof Integer) {
            return l.a(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            return q.a(((Long) obj).longValue());
        }
        if (obj instanceof Short) {
            return u.a(((Short) obj).shortValue());
        }
        if (obj instanceof String) {
            return new v((String) obj);
        }
        if (obj instanceof Class) {
            return new w(TypeId.get((Class) obj).b);
        }
        if (obj instanceof TypeId) {
            return new w(((TypeId) obj).b);
        }
        throw new UnsupportedOperationException("Not a constant: " + obj);
    }
}
