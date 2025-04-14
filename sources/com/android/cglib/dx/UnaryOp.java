package com.android.cglib.dx;

import com.android.cglib.dx.c.b.p;
import com.android.cglib.dx.c.b.r;
import com.android.cglib.dx.c.d.d;

public enum UnaryOp {
    NOT {
        /* access modifiers changed from: package-private */
        public p rop(TypeId<?> typeId) {
            return r.g((d) typeId.b);
        }
    },
    NEGATE {
        /* access modifiers changed from: package-private */
        public p rop(TypeId<?> typeId) {
            return r.f((d) typeId.b);
        }
    };

    /* access modifiers changed from: package-private */
    public abstract p rop(TypeId<?> typeId);
}
