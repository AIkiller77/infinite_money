package com.android.cglib.dx.c.b;

public final class o extends com.android.cglib.dx.d.o {
    public static final o a = new o(0);
    private final m[] b;
    private int c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public o(int i) {
        super(i != 0);
        this.b = new m[i];
        this.c = 0;
    }

    public int a() {
        return this.b.length;
    }

    public m a(int i) {
        try {
            return this.b[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("bogus reg");
        }
    }

    public m a(m mVar) {
        for (m mVar2 : this.b) {
            if (mVar2 != null && mVar.b(mVar2)) {
                return mVar2;
            }
        }
        return null;
    }

    public void a(o oVar) {
        int a2 = oVar.a();
        for (int i = 0; i < a2; i++) {
            m a3 = oVar.a(i);
            if (a3 != null) {
                c(a3);
            }
        }
    }

    public int b() {
        int i = this.c;
        if (i >= 0) {
            return i;
        }
        int i2 = 0;
        for (m mVar : this.b) {
            if (mVar != null) {
                i2++;
            }
        }
        this.c = i2;
        return i2;
    }

    public o b(int i) {
        o oVar = new o(r0 + i);
        for (m mVar : this.b) {
            if (mVar != null) {
                oVar.c(mVar.c(i));
            }
        }
        oVar.c = this.c;
        if (f()) {
            oVar.e();
        }
        return oVar;
    }

    public void b(m mVar) {
        try {
            this.b[mVar.e()] = null;
            this.c = -1;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("bogus reg");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        r3 = r0 - 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(com.android.cglib.dx.c.b.m r6) {
        /*
            r5 = this;
            r5.h()
            if (r6 != 0) goto L_0x000d
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r0 = "spec == null"
            r6.<init>(r0)
            throw r6
        L_0x000d:
            r0 = -1
            r5.c = r0
            int r0 = r6.e()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
            com.android.cglib.dx.c.b.m[] r1 = r5.b     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
            r1[r0] = r6     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
            r1 = 0
            r2 = 2
            if (r0 <= 0) goto L_0x002e
            int r3 = r0 + -1
            com.android.cglib.dx.c.b.m[] r4 = r5.b     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
            r4 = r4[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
            if (r4 == 0) goto L_0x002e
            int r4 = r4.i()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
            if (r4 != r2) goto L_0x002e
            com.android.cglib.dx.c.b.m[] r4 = r5.b     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
            r4[r3] = r1     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
        L_0x002e:
            int r6 = r6.i()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
            if (r6 != r2) goto L_0x003a
            com.android.cglib.dx.c.b.m[] r6 = r5.b     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
            int r0 = r0 + 1
            r6[r0] = r1     // Catch:{ ArrayIndexOutOfBoundsException -> 0x003b }
        L_0x003a:
            return
        L_0x003b:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "spec.getReg() out of range"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.c.b.o.c(com.android.cglib.dx.c.b.m):void");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        m[] mVarArr = oVar.b;
        int length = this.b.length;
        if (length != mVarArr.length || b() != oVar.b()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            m mVar = this.b[i];
            m mVar2 = mVarArr[i];
            if (mVar != mVar2 && (mVar == null || !mVar.equals(mVar2))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int length = this.b.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            m mVar = this.b[i2];
            i = (i * 31) + (mVar == null ? 0 : mVar.hashCode());
        }
        return i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(r0 * 25);
        stringBuffer.append('{');
        boolean z = false;
        for (m mVar : this.b) {
            if (mVar != null) {
                if (z) {
                    stringBuffer.append(", ");
                } else {
                    z = true;
                }
                stringBuffer.append(mVar);
            }
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
