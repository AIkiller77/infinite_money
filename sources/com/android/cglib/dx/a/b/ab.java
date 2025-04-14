package com.android.cglib.dx.a.b;

import com.android.cglib.dx.d.f;
import java.util.Iterator;

public abstract class ab extends as {
    public ab(String str, l lVar) {
        super(str, lVar, 4);
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (a().size() > 65536) {
            String str = this instanceof ae ? "methods" : "fields";
            throw new f("Too many " + str + ": " + a().size() + "; max is " + 65536);
        }
        Iterator<? extends x> it = a().iterator();
        int i = 0;
        while (it.hasNext()) {
            ((aa) it.next()).a(i);
            i++;
        }
    }
}
