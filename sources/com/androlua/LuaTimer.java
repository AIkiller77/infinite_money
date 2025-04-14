package com.androlua;

import com.androlua.util.TimerTaskX;
import com.androlua.util.TimerX;
import com.luajava.LuaObject;

public class LuaTimer extends TimerX implements LuaGcable {
    private boolean a;
    private LuaTimerTask b;

    public LuaTimer(LuaContext luaContext, LuaObject luaObject) {
        this(luaContext, luaObject, (Object[]) null);
    }

    public LuaTimer(LuaContext luaContext, LuaObject luaObject, Object[] objArr) {
        super("LuaTimer");
        luaContext.regGc(this);
        this.b = new LuaTimerTask(luaContext, luaObject, objArr);
    }

    public LuaTimer(LuaContext luaContext, String str) {
        this(luaContext, str, (Object[]) null);
    }

    public LuaTimer(LuaContext luaContext, String str, Object[] objArr) {
        super("LuaTimer");
        luaContext.regGc(this);
        this.b = new LuaTimerTask(luaContext, str, objArr);
    }

    public void gc() {
        stop();
        this.a = true;
    }

    public boolean getEnabled() {
        return this.b.isEnabled();
    }

    public long getPeriod() {
        return this.b.getPeriod();
    }

    public boolean isEnabled() {
        return this.b.isEnabled();
    }

    public boolean isGc() {
        return this.a;
    }

    public void setEnabled(boolean z) {
        this.b.setEnabled(z);
    }

    public void setPeriod(long j) {
        this.b.setPeriod(j);
    }

    public void start(long j) {
        schedule((TimerTaskX) this.b, j);
    }

    public void start(long j, long j2) {
        schedule((TimerTaskX) this.b, j, j2);
    }

    public void stop() {
        this.b.cancel();
    }
}
