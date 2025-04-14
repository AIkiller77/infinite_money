package com.androlua;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.luajava.LuaException;
import com.luajava.LuaFunction;

public class LuaAnimation extends Animation {
    private final LuaContext a = this.b.getLuaState().getContext();
    private LuaFunction b;
    private LuaFunction c;

    public LuaAnimation(LuaFunction luaFunction) {
        this.b = luaFunction;
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f, Transformation transformation) {
        Object call;
        super.applyTransformation(f, transformation);
        try {
            this.b.call(Float.valueOf(f), transformation);
            if (this.c == null && (call = this.b.call(Float.valueOf(f), transformation, this)) != null && (call instanceof LuaFunction)) {
                this.c = (LuaFunction) call;
            }
            if (this.c != null) {
                this.c.call(Float.valueOf(f), transformation);
            }
        } catch (LuaException e) {
            this.a.sendError("applyTransformation", e);
        }
    }

    /* access modifiers changed from: protected */
    public float resolveSize(int i, float f, int i2, int i3) {
        return super.resolveSize(i, f, i2, i3);
    }
}
