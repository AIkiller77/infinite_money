package com.androlua;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaObject;

public class LuaDrawable extends Drawable {
    private final LuaContext a = this.b.getLuaState().getContext();
    private LuaObject b;
    private Paint c = new Paint();
    private LuaFunction d;

    public LuaDrawable(LuaFunction luaFunction) {
        this.b = luaFunction;
    }

    public void draw(Canvas canvas) {
        Object call;
        try {
            if (this.d == null && (call = this.b.call(canvas, this.c, this)) != null && (call instanceof LuaFunction)) {
                this.d = (LuaFunction) call;
            }
            if (this.d != null) {
                this.d.call(canvas);
            }
        } catch (LuaException e) {
            this.a.sendError("onDraw", e);
        }
    }

    public int getOpacity() {
        return 0;
    }

    public Paint getPaint() {
        return this.c;
    }

    public void setAlpha(int i) {
        this.c.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.c.setColorFilter(colorFilter);
    }
}
