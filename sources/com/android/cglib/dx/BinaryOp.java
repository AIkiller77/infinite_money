package com.android.cglib.dx;

import com.android.cglib.dx.c.b.p;
import com.android.cglib.dx.c.b.r;
import com.android.cglib.dx.c.d.e;

public enum BinaryOp {
    ADD {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.g(eVar);
        }
    },
    SUBTRACT {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.h(eVar);
        }
    },
    MULTIPLY {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.i(eVar);
        }
    },
    DIVIDE {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.j(eVar);
        }
    },
    REMAINDER {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.k(eVar);
        }
    },
    AND {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.l(eVar);
        }
    },
    OR {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.m(eVar);
        }
    },
    XOR {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.n(eVar);
        }
    },
    SHIFT_LEFT {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.o(eVar);
        }
    },
    SHIFT_RIGHT {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.p(eVar);
        }
    },
    UNSIGNED_SHIFT_RIGHT {
        /* access modifiers changed from: package-private */
        public p rop(e eVar) {
            return r.q(eVar);
        }
    };

    /* access modifiers changed from: package-private */
    public abstract p rop(e eVar);
}
