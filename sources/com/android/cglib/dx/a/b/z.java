package com.android.cglib.dx.a.b;

import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import java.util.ArrayList;

public final class z extends ag {
    private final y a;
    private final ak b;
    private final x c;
    private final x d;
    private final int e;

    private z(ak akVar) {
        super(4, 12);
        if (akVar == null) {
            throw new NullPointerException("section == null");
        }
        this.a = y.TYPE_MAP_LIST;
        this.b = akVar;
        this.c = null;
        this.d = null;
        this.e = 1;
    }

    private z(y yVar, ak akVar, x xVar, x xVar2, int i) {
        super(4, 12);
        if (yVar == null) {
            throw new NullPointerException("type == null");
        } else if (akVar == null) {
            throw new NullPointerException("section == null");
        } else if (xVar == null) {
            throw new NullPointerException("firstItem == null");
        } else if (xVar2 == null) {
            throw new NullPointerException("lastItem == null");
        } else if (i <= 0) {
            throw new IllegalArgumentException("itemCount <= 0");
        } else {
            this.a = yVar;
            this.b = akVar;
            this.c = xVar;
            this.d = xVar2;
            this.e = i;
        }
    }

    public static void a(ak[] akVarArr, af afVar) {
        z zVar;
        ak[] akVarArr2 = akVarArr;
        af afVar2 = afVar;
        if (akVarArr2 == null) {
            throw new NullPointerException("sections == null");
        } else if (afVar.a().size() != 0) {
            throw new IllegalArgumentException("mapSection.items().size() != 0");
        } else {
            ArrayList arrayList = new ArrayList(50);
            for (ak akVar : akVarArr2) {
                y yVar = null;
                x xVar = null;
                x xVar2 = null;
                int i = 0;
                for (x xVar3 : akVar.a()) {
                    y a2 = xVar3.a();
                    if (a2 != yVar) {
                        if (i != 0) {
                            z zVar2 = r6;
                            z zVar3 = new z(yVar, akVar, xVar, xVar2, i);
                            arrayList.add(zVar2);
                        }
                        xVar = xVar3;
                        yVar = a2;
                        i = 0;
                    }
                    i++;
                    xVar2 = xVar3;
                }
                if (i != 0) {
                    zVar = new z(yVar, akVar, xVar, xVar2, i);
                } else if (akVar == afVar2) {
                    zVar = new z(afVar2);
                }
                arrayList.add(zVar);
            }
            afVar2.a((ag) new at(y.TYPE_MAP_LIST, arrayList));
        }
    }

    public y a() {
        return y.TYPE_MAP_ITEM;
    }

    public void a(l lVar) {
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, a aVar) {
        int b2 = this.a.b();
        int g = this.c == null ? this.b.g() : this.b.a(this.c);
        if (aVar.a()) {
            aVar.a(0, g() + ' ' + this.a.c() + " map");
            aVar.a(2, "  type:   " + i.c(b2) + " // " + this.a.toString());
            aVar.a(2, "  unused: 0");
            StringBuilder sb = new StringBuilder();
            sb.append("  size:   ");
            sb.append(i.a(this.e));
            aVar.a(4, sb.toString());
            aVar.a(4, "  offset: " + i.a(g));
        }
        aVar.c(b2);
        aVar.c(0);
        aVar.d(this.e);
        aVar.d(g);
    }

    public final String b() {
        return toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(getClass().getName());
        stringBuffer.append('{');
        stringBuffer.append(this.b.toString());
        stringBuffer.append(' ');
        stringBuffer.append(this.a.a_());
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
