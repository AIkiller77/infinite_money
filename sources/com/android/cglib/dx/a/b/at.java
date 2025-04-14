package com.android.cglib.dx.a.b;

import com.android.cglib.dx.a.b.ag;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import java.util.List;

public final class at<T extends ag> extends ag {
    private final y a;
    private final List<T> b;

    public at(y yVar, List<T> list) {
        super(a((List<? extends ag>) list), b(list));
        if (yVar == null) {
            throw new NullPointerException("itemType == null");
        }
        this.b = list;
        this.a = yVar;
    }

    private static int a(List<? extends ag> list) {
        try {
            return Math.max(4, ((ag) list.get(0)).f());
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("items.size() == 0");
        } catch (NullPointerException unused2) {
            throw new NullPointerException("items == null");
        }
    }

    private static int b(List<? extends ag> list) {
        return (list.size() * ((ag) list.get(0)).b_()) + a(list);
    }

    private int d() {
        return f();
    }

    public y a() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void a(ak akVar, int i) {
        int d = i + d();
        int i2 = -1;
        boolean z = true;
        int i3 = d;
        int i4 = -1;
        for (T t : this.b) {
            int b_ = t.b_();
            if (z) {
                i4 = t.f();
                i2 = b_;
                z = false;
            } else if (b_ != i2) {
                throw new UnsupportedOperationException("item size mismatch");
            } else if (t.f() != i4) {
                throw new UnsupportedOperationException("item alignment mismatch");
            }
            i3 = t.b(akVar, i3) + b_;
        }
    }

    public void a(l lVar) {
        for (T a2 : this.b) {
            a2.a(lVar);
        }
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, a aVar) {
        int size = this.b.size();
        if (aVar.a()) {
            aVar.a(0, g() + " " + i());
            StringBuilder sb = new StringBuilder();
            sb.append("  size: ");
            sb.append(i.a(size));
            aVar.a(4, sb.toString());
        }
        aVar.d(size);
        for (T a2 : this.b) {
            a2.a(lVar, aVar);
        }
    }

    public final String b() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("{");
        boolean z = true;
        for (T t : this.b) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(", ");
            }
            stringBuffer.append(t.b());
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public final List<T> c() {
        return this.b;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(getClass().getName());
        stringBuffer.append(this.b);
        return stringBuffer.toString();
    }
}
