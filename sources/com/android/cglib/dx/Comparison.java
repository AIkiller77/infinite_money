package com.android.cglib.dx;

import com.android.cglib.dx.c.b.p;
import com.android.cglib.dx.c.b.r;
import com.android.cglib.dx.c.d.e;

public enum Comparison {
    LT {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.c(eVar);
        }
    },
    LE {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.f(eVar);
        }
    },
    EQ {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.a(eVar);
        }
    },
    GE {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.d(eVar);
        }
    },
    GT {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.e(eVar);
        }
    },
    NE {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.b(eVar);
        }
    };

    /* access modifiers changed from: package-private */
    public abstract p rop(e eVar);
}
