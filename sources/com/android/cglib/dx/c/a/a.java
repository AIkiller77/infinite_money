package com.android.cglib.dx.c.a;

import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.o;
import com.android.cglib.dx.d.r;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

public final class a extends o implements r, Comparable<a> {
    private final w a;
    private final b b;
    private final TreeMap<v, d> c;

    /* renamed from: a */
    public int compareTo(a aVar) {
        int a2 = this.a.compareTo(aVar.a);
        if (a2 != 0) {
            return a2;
        }
        int compareTo = this.b.compareTo(aVar.b);
        if (compareTo != 0) {
            return compareTo;
        }
        Iterator<d> it = this.c.values().iterator();
        Iterator<d> it2 = aVar.c.values().iterator();
        while (it.hasNext() && it2.hasNext()) {
            int a3 = it.next().compareTo(it2.next());
            if (a3 != 0) {
                return a3;
            }
        }
        if (it.hasNext()) {
            return 1;
        }
        return it2.hasNext() ? -1 : 0;
    }

    public String a_() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b.a_());
        sb.append("-annotation ");
        sb.append(this.a.a_());
        sb.append(" {");
        boolean z = true;
        for (d next : this.c.values()) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(next.a().a_());
            sb.append(": ");
            sb.append(next.b().a_());
        }
        sb.append("}");
        return sb.toString();
    }

    public w b() {
        return this.a;
    }

    public b c() {
        return this.b;
    }

    public Collection<d> d() {
        return Collections.unmodifiableCollection(this.c.values());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (!this.a.equals(aVar.a) || this.b != aVar.b) {
            return false;
        }
        return this.c.equals(aVar.c);
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.c.hashCode()) * 31) + this.b.hashCode();
    }

    public String toString() {
        return a_();
    }
}
