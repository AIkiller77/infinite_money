package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import com.tencent.open.SocialOperation;
import com.tencent.qq.widget.R;

public final class t extends w {
    public y a() {
        return y.TYPE_HEADER_ITEM;
    }

    public void a(l lVar) {
    }

    public void a(l lVar, a aVar) {
        int g = lVar.f().g();
        ak o = lVar.o();
        ak p = lVar.p();
        int g2 = o.g();
        int g3 = (p.g() + p.c_()) - g2;
        String a = lVar.a().a();
        if (aVar.a()) {
            aVar.a(8, "magic: " + new v(a).f());
            aVar.a(4, "checksum");
            aVar.a(20, SocialOperation.GAME_SIGNATURE);
            aVar.a(4, "file_size:       " + i.a(lVar.b()));
            aVar.a(4, "header_size:     " + i.a((int) R.styleable.AppCompatTheme_spinnerStyle));
            aVar.a(4, "endian_tag:      " + i.a(305419896));
            aVar.a(4, "link_size:       0");
            aVar.a(4, "link_off:        0");
            aVar.a(4, "map_off:         " + i.a(g));
        }
        for (int i = 0; i < 8; i++) {
            aVar.b(a.charAt(i));
        }
        aVar.g(24);
        aVar.d(lVar.b());
        aVar.d(R.styleable.AppCompatTheme_spinnerStyle);
        aVar.d(305419896);
        aVar.g(8);
        aVar.d(g);
        lVar.g().b(aVar);
        lVar.j().b(aVar);
        lVar.k().b(aVar);
        lVar.l().a(aVar);
        lVar.m().a(aVar);
        lVar.h().a(aVar);
        if (aVar.a()) {
            aVar.a(4, "data_size:       " + i.a(g3));
            aVar.a(4, "data_off:        " + i.a(g2));
        }
        aVar.d(g3);
        aVar.d(g2);
    }

    public int b_() {
        return R.styleable.AppCompatTheme_spinnerStyle;
    }
}
