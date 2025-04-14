package com.android.cglib.dx.c.a;

import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.o;
import java.util.Iterator;
import java.util.TreeMap;

public final class c extends o implements Comparable<c> {
    public static final c a = new c();
    private final TreeMap<w, a> b = new TreeMap<>();

    static {
        a.e();
    }

    /* renamed from: a */
    public int compareTo(c cVar) {
        Iterator<a> it = this.b.values().iterator();
        Iterator<a> it2 = cVar.b.values().iterator();
        while (it.hasNext() && it2.hasNext()) {
            int a2 = it.next().compareTo(it2.next());
            if (a2 != 0) {
                return a2;
            }
        }
        if (it.hasNext()) {
            return 1;
        }
        return it2.hasNext() ? -1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        return this.b.equals(((c) obj).b);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("annotations{");
        boolean z = true;
        for (a next : this.b.values()) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(next.a_());
        }
        sb.append("}");
        return sb.toString();
    }
}
