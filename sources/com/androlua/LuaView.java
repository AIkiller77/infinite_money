package com.androlua;

import android.content.Context;
import android.view.View;
import com.a.a.a.a.a.a.a;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaTable;

public class LuaView extends View {
    private LuaTable a;
    private LuaObject b;

    public LuaView(Context context) {
        super(context);
    }

    public LuaView(Context context, LuaTable luaTable) {
        super(context);
        this.a = luaTable;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.a != null) {
            try {
                this.b = this.a.getField("onMeasure");
                if (this.b.isFunction()) {
                    this.b.call(Integer.valueOf(i), Integer.valueOf(i2), this);
                    return;
                }
            } catch (LuaException e) {
                a.a(e);
            }
        }
        super.onMeasure(i, i2);
    }
}
