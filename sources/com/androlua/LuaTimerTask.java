package com.androlua;

import com.androlua.util.TimerTaskX;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import java.util.regex.Pattern;

public class LuaTimerTask extends TimerTaskX {
    private LuaState a;
    /* access modifiers changed from: private */
    public LuaContext g;
    private String h;
    private Object[] i;
    private boolean j;
    private byte[] k;

    public LuaTimerTask(LuaContext luaContext, LuaObject luaObject) {
        this(luaContext, luaObject, (Object[]) null);
    }

    public LuaTimerTask(LuaContext luaContext, LuaObject luaObject, Object[] objArr) {
        this.i = new Object[0];
        this.j = true;
        this.g = luaContext;
        if (objArr != null) {
            this.i = objArr;
        }
        this.k = luaObject.dump();
    }

    public LuaTimerTask(LuaContext luaContext, String str) {
        this(luaContext, str, (Object[]) null);
    }

    public LuaTimerTask(LuaContext luaContext, String str, Object[] objArr) {
        this.i = new Object[0];
        this.j = true;
        this.g = luaContext;
        this.h = str;
        if (objArr != null) {
            this.i = objArr;
        }
    }

    private String a(int i2) {
        switch (i2) {
            case 1:
                return "Yield error";
            case 2:
                return "Runtime error";
            case 3:
                return "Syntax error";
            case 4:
                return "Out of memory";
            case 5:
                return "GC error";
            case 6:
                return "error error";
            default:
                return "Unknown error " + i2;
        }
    }

    private void a(String str, Object... objArr) {
        try {
            if (Pattern.matches("^\\w+$", str)) {
                doAsset(str + ".lua", objArr);
            } else if (Pattern.matches("^[\\w\\.\\_/]+$", str)) {
                this.a.getGlobal("luajava");
                this.a.pushString(this.g.getLuaDir());
                this.a.setField(-2, "luadir");
                this.a.pushString(str);
                this.a.setField(-2, "luapath");
                this.a.pop(1);
                b(str, objArr);
            } else {
                c(str, objArr);
            }
        } catch (Exception e) {
            this.g.sendError(toString(), e);
        }
    }

    private void a(byte[] bArr, Object... objArr) {
        this.a.setTop(0);
        int LloadBuffer = this.a.LloadBuffer(bArr, "TimerTask");
        if (LloadBuffer == 0) {
            this.a.getGlobal("debug");
            this.a.getField(-1, "traceback");
            this.a.remove(-2);
            this.a.insert(-2);
            for (Object pushObjectValue : objArr) {
                this.a.pushObjectValue(pushObjectValue);
            }
            LloadBuffer = this.a.pcall(r7, 0, -2 - r7);
            if (LloadBuffer == 0) {
                return;
            }
        }
        throw new LuaException(a(LloadBuffer) + ": " + this.a.toString(-1));
    }

    private void b() {
        LuaState luaState;
        String str;
        this.a = LuaStateFactory.newLuaState();
        this.a.openLibs();
        this.a.pushJavaObject(this.g);
        if (this.g instanceof LuaActivity) {
            luaState = this.a;
            str = "activity";
        } else {
            if (this.g instanceof LuaService) {
                luaState = this.a;
                str = "service";
            }
            this.a.pushJavaObject(this);
            this.a.setGlobal("this");
            this.a.pushContext(this.g);
            new LuaPrint(this.g, this.a).register("print");
            this.a.getGlobal("package");
            this.a.pushString(this.g.getLuaLpath());
            this.a.setField(-2, "path");
            this.a.pushString(this.g.getLuaCpath());
            this.a.setField(-2, "cpath");
            this.a.pop(1);
            new JavaFunction(this.a) {
                public int execute() {
                    LuaTimerTask.this.g.set(this.b.toString(2), this.b.toJavaObject(3));
                    return 0;
                }
            }.register("set");
            new JavaFunction(this.a) {
                public int execute() {
                    Object[] objArr;
                    String str;
                    LuaContext luaContext;
                    int top = this.b.getTop();
                    if (top > 2) {
                        objArr = new Object[(top - 2)];
                        for (int i = 3; i <= top; i++) {
                            objArr[i - 3] = this.b.toJavaObject(i);
                        }
                        luaContext = LuaTimerTask.this.g;
                        str = this.b.toString(2);
                    } else {
                        if (top == 2) {
                            luaContext = LuaTimerTask.this.g;
                            str = this.b.toString(2);
                            objArr = new Object[0];
                        }
                        return 0;
                    }
                    luaContext.call(str, objArr);
                    return 0;
                }
            }.register("call");
        }
        luaState.setGlobal(str);
        this.a.pushJavaObject(this);
        this.a.setGlobal("this");
        this.a.pushContext(this.g);
        new LuaPrint(this.g, this.a).register("print");
        this.a.getGlobal("package");
        this.a.pushString(this.g.getLuaLpath());
        this.a.setField(-2, "path");
        this.a.pushString(this.g.getLuaCpath());
        this.a.setField(-2, "cpath");
        this.a.pop(1);
        new JavaFunction(this.a) {
            public int execute() {
                LuaTimerTask.this.g.set(this.b.toString(2), this.b.toJavaObject(3));
                return 0;
            }
        }.register("set");
        new JavaFunction(this.a) {
            public int execute() {
                Object[] objArr;
                String str;
                LuaContext luaContext;
                int top = this.b.getTop();
                if (top > 2) {
                    objArr = new Object[(top - 2)];
                    for (int i = 3; i <= top; i++) {
                        objArr[i - 3] = this.b.toJavaObject(i);
                    }
                    luaContext = LuaTimerTask.this.g;
                    str = this.b.toString(2);
                } else {
                    if (top == 2) {
                        luaContext = LuaTimerTask.this.g;
                        str = this.b.toString(2);
                        objArr = new Object[0];
                    }
                    return 0;
                }
                luaContext.call(str, objArr);
                return 0;
            }
        }.register("call");
    }

    private void b(String str, Object... objArr) {
        this.a.setTop(0);
        int LloadFile = this.a.LloadFile(str);
        if (LloadFile == 0) {
            this.a.getGlobal("debug");
            this.a.getField(-1, "traceback");
            this.a.remove(-2);
            this.a.insert(-2);
            for (Object pushObjectValue : objArr) {
                this.a.pushObjectValue(pushObjectValue);
            }
            LloadFile = this.a.pcall(r7, 0, -2 - r7);
            if (LloadFile == 0) {
                return;
            }
        }
        throw new LuaException(a(LloadFile) + ": " + this.a.toString(-1));
    }

    private void c(String str, Object... objArr) {
        this.a.setTop(0);
        int LloadString = this.a.LloadString(str);
        if (LloadString == 0) {
            this.a.getGlobal("debug");
            this.a.getField(-1, "traceback");
            this.a.remove(-2);
            this.a.insert(-2);
            for (Object pushObjectValue : objArr) {
                this.a.pushObjectValue(pushObjectValue);
            }
            LloadString = this.a.pcall(r7, 0, -2 - r7);
            if (LloadString == 0) {
                return;
            }
        }
        throw new LuaException(a(LloadString) + ": " + this.a.toString(-1));
    }

    private void d(String str, Object... objArr) {
        try {
            this.a.setTop(0);
            this.a.getGlobal(str);
            if (this.a.isFunction(-1)) {
                this.a.getGlobal("debug");
                this.a.getField(-1, "traceback");
                this.a.remove(-2);
                this.a.insert(-2);
                for (Object pushObjectValue : objArr) {
                    this.a.pushObjectValue(pushObjectValue);
                }
                int pcall = this.a.pcall(r0, 1, -2 - r0);
                if (pcall != 0) {
                    throw new LuaException(a(pcall) + ": " + this.a.toString(-1));
                }
            }
        } catch (LuaException e) {
            this.g.sendError(toString() + " " + str, e);
        }
    }

    public boolean cancel() {
        return super.cancel();
    }

    public void doAsset(String str, Object... objArr) {
        byte[] readAsset = LuaUtil.readAsset(this.g.getContext(), str);
        this.a.setTop(0);
        int LloadBuffer = this.a.LloadBuffer(readAsset, str);
        if (LloadBuffer == 0) {
            this.a.getGlobal("debug");
            this.a.getField(-1, "traceback");
            this.a.remove(-2);
            this.a.insert(-2);
            for (Object pushObjectValue : objArr) {
                this.a.pushObjectValue(pushObjectValue);
            }
            LloadBuffer = this.a.pcall(r7, 0, -2 - r7);
            if (LloadBuffer == 0) {
                return;
            }
        }
        throw new LuaException(a(LloadBuffer) + ": " + this.a.toString(-1));
    }

    public Object get(String str) {
        this.a.getGlobal(str);
        return this.a.toJavaObject(-1);
    }

    public boolean isEnabled() {
        return this.j;
    }

    public void run() {
        String str;
        Object[] objArr;
        byte[] bArr;
        Object[] objArr2;
        if (this.j) {
            try {
                if (this.a == null) {
                    b();
                    if (this.k != null) {
                        bArr = this.k;
                        objArr2 = this.i;
                    } else {
                        str = this.h;
                        objArr = this.i;
                        a(str, objArr);
                        this.a.gc(2, 1);
                        System.gc();
                    }
                } else {
                    this.a.getGlobal("run");
                    if (!this.a.isNil(-1)) {
                        d("run", new Object[0]);
                        this.a.gc(2, 1);
                        System.gc();
                    } else if (this.k != null) {
                        bArr = this.k;
                        objArr2 = this.i;
                    } else {
                        str = this.h;
                        objArr = this.i;
                        a(str, objArr);
                        this.a.gc(2, 1);
                        System.gc();
                    }
                }
                a(bArr, objArr2);
            } catch (LuaException e) {
                this.g.sendError(toString(), e);
            }
            this.a.gc(2, 1);
            System.gc();
        }
    }

    public void set(String str, Object obj) {
        this.a.pushObjectValue(obj);
        this.a.setGlobal(str);
    }

    public void setArg(LuaObject luaObject) {
        this.i = luaObject.asArray();
    }

    public void setArg(Object[] objArr) {
        this.i = objArr;
    }

    public void setEnabled(boolean z) {
        this.j = z;
    }
}
