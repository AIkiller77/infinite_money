package com.android.cglib.dx.a.a;

import com.android.cglib.dx.b.b;
import com.android.cglib.dx.b.c;
import com.tencent.qq.widget.R;

public final class j {
    private final int a;
    private final int b;
    private final int c;
    private final n d;
    private final boolean e;

    public j(int i, int i2, int i3, n nVar, boolean z) {
        if (!c.a(i)) {
            throw new IllegalArgumentException("bogus opcode");
        } else if (!c.a(i2)) {
            throw new IllegalArgumentException("bogus family");
        } else if (!c.a(i3)) {
            throw new IllegalArgumentException("bogus nextOpcode");
        } else if (nVar == null) {
            throw new NullPointerException("format == null");
        } else {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = nVar;
            this.e = z;
        }
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public n c() {
        return this.d;
    }

    public boolean d() {
        return this.e;
    }

    public String e() {
        return b.b(this.a);
    }

    public int f() {
        return this.c;
    }

    public j g() {
        switch (this.a) {
            case 50:
                return k.aa;
            case R.styleable.AppCompatTheme_buttonBarStyle /*51*/:
                return k.Z;
            case R.styleable.AppCompatTheme_buttonBarButtonStyle /*52*/:
                return k.ac;
            case R.styleable.AppCompatTheme_selectableItemBackground /*53*/:
                return k.ab;
            case R.styleable.AppCompatTheme_selectableItemBackgroundBorderless /*54*/:
                return k.ae;
            case R.styleable.AppCompatTheme_borderlessButtonStyle /*55*/:
                return k.ad;
            case R.styleable.AppCompatTheme_dividerVertical /*56*/:
                return k.ag;
            case R.styleable.AppCompatTheme_dividerHorizontal /*57*/:
                return k.af;
            case R.styleable.AppCompatTheme_activityChooserViewStyle /*58*/:
                return k.ai;
            case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                return k.ah;
            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                return k.ak;
            case R.styleable.AppCompatTheme_popupMenuStyle /*61*/:
                return k.aj;
            default:
                throw new IllegalArgumentException("bogus opcode: " + this);
        }
    }

    public String toString() {
        return e();
    }
}
