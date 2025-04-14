package com.androlua;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaMetaTable;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import java.util.regex.Pattern;

public class LuaRunnable extends Thread implements LuaGcable, LuaMetaTable, Runnable {
    private boolean a;
    private LuaState b;
    private Handler c;
    /* access modifiers changed from: private */
    public LuaContext d;
    private boolean e;
    private String f;
    private Object[] g;
    private byte[] h;
    public boolean isRun;

    private class ThreadHandler extends Handler {
        private ThreadHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            Bundle data = message.getData();
            switch (message.what) {
                case 0:
                    LuaRunnable.this.a(data.getString("data"), (Object[]) data.getSerializable("args"));
                    return;
                case 1:
                    LuaRunnable.this.d(data.getString("data"), (Object[]) data.getSerializable("args"));
                    return;
                case 2:
                    LuaRunnable.this.a(data.getString("data"), new Object[0]);
                    return;
                case 3:
                    LuaRunnable.this.d(data.getString("data"), new Object[0]);
                    return;
                case 4:
                    LuaRunnable.this.a(data.getString("data"), ((Object[]) data.getSerializable("args"))[0]);
                    return;
                default:
                    return;
            }
        }
    }

    public LuaRunnable(LuaContext luaContext, LuaObject luaObject) {
        this(luaContext, luaObject, false, (Object[]) null);
    }

    public LuaRunnable(LuaContext luaContext, LuaObject luaObject, boolean z) {
        this(luaContext, luaObject, z, (Object[]) null);
    }

    public LuaRunnable(LuaContext luaContext, LuaObject luaObject, boolean z, Object[] objArr) {
        this.isRun = false;
        this.g = new Object[0];
        this.d = luaContext;
        if (objArr != null) {
            this.g = objArr;
        }
        this.e = z;
        this.h = luaObject.dump();
    }

    public LuaRunnable(LuaContext luaContext, LuaObject luaObject, Object[] objArr) {
        this(luaContext, luaObject, false, objArr);
    }

    public LuaRunnable(LuaContext luaContext, String str) {
        this(luaContext, str, false, (Object[]) null);
    }

    public LuaRunnable(LuaContext luaContext, String str, boolean z) {
        this(luaContext, str, z, (Object[]) null);
    }

    public LuaRunnable(LuaContext luaContext, String str, boolean z, Object[] objArr) {
        this.isRun = false;
        this.g = new Object[0];
        luaContext.regGc(this);
        this.d = luaContext;
        this.f = str;
        this.e = z;
        if (objArr != null) {
            this.g = objArr;
        }
    }

    public LuaRunnable(LuaContext luaContext, String str, Object[] objArr) {
        this(luaContext, str, false, objArr);
    }

    private String a(int i) {
        switch (i) {
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
                return "Unknown error " + i;
        }
    }

    private void a() {
        LuaState luaState;
        String str;
        this.b = LuaStateFactory.newLuaState();
        this.b.openLibs();
        this.b.pushJavaObject(this.d.getContext());
        if (this.d instanceof LuaActivity) {
            luaState = this.b;
            str = "activity";
        } else {
            if (this.d instanceof LuaService) {
                luaState = this.b;
                str = "service";
            }
            this.b.pushJavaObject(this);
            this.b.setGlobal("this");
            this.b.pushContext(this.d);
            new LuaPrint(this.d, this.b).register("print");
            this.b.getGlobal("package");
            this.b.pushString(this.d.getLuaLpath());
            this.b.setField(-2, "path");
            this.b.pushString(this.d.getLuaCpath());
            this.b.setField(-2, "cpath");
            this.b.pop(1);
            new JavaFunction(this.b) {
                public int execute() {
                    LuaRunnable.this.d.set(this.b.toString(2), this.b.toJavaObject(3));
                    return 0;
                }
            }.register("set");
            new JavaFunction(this.b) {
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
                        luaContext = LuaRunnable.this.d;
                        str = this.b.toString(2);
                    } else {
                        if (top == 2) {
                            luaContext = LuaRunnable.this.d;
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
        this.b.pushJavaObject(this);
        this.b.setGlobal("this");
        this.b.pushContext(this.d);
        new LuaPrint(this.d, this.b).register("print");
        this.b.getGlobal("package");
        this.b.pushString(this.d.getLuaLpath());
        this.b.setField(-2, "path");
        this.b.pushString(this.d.getLuaCpath());
        this.b.setField(-2, "cpath");
        this.b.pop(1);
        new JavaFunction(this.b) {
            public int execute() {
                LuaRunnable.this.d.set(this.b.toString(2), this.b.toJavaObject(3));
                return 0;
            }
        }.register("set");
        new JavaFunction(this.b) {
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
                    luaContext = LuaRunnable.this.d;
                    str = this.b.toString(2);
                } else {
                    if (top == 2) {
                        luaContext = LuaRunnable.this.d;
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

    /* access modifiers changed from: private */
    public void a(String str, Object obj) {
        try {
            this.b.pushObjectValue(obj);
            this.b.setGlobal(str);
        } catch (LuaException e2) {
            this.d.sendMsg(e2.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, Object... objArr) {
        try {
            if (Pattern.matches("^\\w+$", str)) {
                doAsset(str + ".lua", objArr);
            } else if (Pattern.matches("^[\\w\\.\\_/]+$", str)) {
                this.b.getGlobal("luajava");
                this.b.pushString(this.d.getLuaDir());
                this.b.setField(-2, "luadir");
                this.b.pushString(str);
                this.b.setField(-2, "luapath");
                this.b.pop(1);
                b(str, objArr);
            } else {
                c(str, objArr);
            }
        } catch (Exception e2) {
            LuaContext luaContext = this.d;
            luaContext.sendMsg(toString() + " " + e2.getMessage());
            quit();
        }
    }

    private void a(byte[] bArr, Object... objArr) {
        try {
            this.b.setTop(0);
            int LloadBuffer = this.b.LloadBuffer(bArr, "TimerTask");
            if (LloadBuffer == 0) {
                this.b.getGlobal("debug");
                this.b.getField(-1, "traceback");
                this.b.remove(-2);
                this.b.insert(-2);
                for (Object pushObjectValue : objArr) {
                    this.b.pushObjectValue(pushObjectValue);
                }
                LloadBuffer = this.b.pcall(r7, 0, -2 - r7);
                if (LloadBuffer == 0) {
                    return;
                }
            }
            throw new LuaException(a(LloadBuffer) + ": " + this.b.toString(-1));
        } catch (Exception e2) {
            this.d.sendMsg(toString() + " " + e2.getMessage());
            quit();
        }
    }

    private void b(String str, Object... objArr) {
        this.b.setTop(0);
        int LloadFile = this.b.LloadFile(str);
        if (LloadFile == 0) {
            this.b.getGlobal("debug");
            this.b.getField(-1, "traceback");
            this.b.remove(-2);
            this.b.insert(-2);
            for (Object pushObjectValue : objArr) {
                this.b.pushObjectValue(pushObjectValue);
            }
            LloadFile = this.b.pcall(r7, 0, -2 - r7);
            if (LloadFile == 0) {
                return;
            }
        }
        throw new LuaException(a(LloadFile) + ": " + this.b.toString(-1));
    }

    private void c(String str, Object... objArr) {
        this.b.setTop(0);
        int LloadString = this.b.LloadString(str);
        if (LloadString == 0) {
            this.b.getGlobal("debug");
            this.b.getField(-1, "traceback");
            this.b.remove(-2);
            this.b.insert(-2);
            for (Object pushObjectValue : objArr) {
                this.b.pushObjectValue(pushObjectValue);
            }
            LloadString = this.b.pcall(r7, 0, -2 - r7);
            if (LloadString == 0) {
                return;
            }
        }
        throw new LuaException(a(LloadString) + ": " + this.b.toString(-1));
    }

    /* access modifiers changed from: private */
    public void d(String str, Object... objArr) {
        try {
            this.b.setTop(0);
            this.b.getGlobal(str);
            if (this.b.isFunction(-1)) {
                this.b.getGlobal("debug");
                this.b.getField(-1, "traceback");
                this.b.remove(-2);
                this.b.insert(-2);
                for (Object pushObjectValue : objArr) {
                    this.b.pushObjectValue(pushObjectValue);
                }
                int pcall = this.b.pcall(r0, 1, -2 - r0);
                if (pcall != 0) {
                    throw new LuaException(a(pcall) + ": " + this.b.toString(-1));
                }
            }
        } catch (LuaException e2) {
            this.d.sendMsg(str + " " + e2.getMessage());
        }
    }

    public Object __call(Object[] objArr) {
        return null;
    }

    public Object __index(final String str) {
        return new LuaMetaTable() {
            public Object __call(Object[] objArr) {
                LuaRunnable.this.call(str, objArr);
                return null;
            }

            public Object __index(String str) {
                return null;
            }

            public void __newIndex(String str, Object obj) {
            }
        };
    }

    public void __newIndex(String str, Object obj) {
        set(str, obj);
    }

    public void call(String str) {
        push(3, str);
    }

    public void call(String str, Object[] objArr) {
        if (objArr.length == 0) {
            push(3, str);
        } else {
            push(1, str, objArr);
        }
    }

    public void doAsset(String str, Object... objArr) {
        byte[] readAsset = LuaUtil.readAsset(this.d.getContext(), str);
        this.b.setTop(0);
        int LloadBuffer = this.b.LloadBuffer(readAsset, str);
        if (LloadBuffer == 0) {
            this.b.getGlobal("debug");
            this.b.getField(-1, "traceback");
            this.b.remove(-2);
            this.b.insert(-2);
            for (Object pushObjectValue : objArr) {
                this.b.pushObjectValue(pushObjectValue);
            }
            LloadBuffer = this.b.pcall(r7, 0, -2 - r7);
            if (LloadBuffer == 0) {
                return;
            }
        }
        throw new LuaException(a(LloadBuffer) + ": " + this.b.toString(-1));
    }

    public void gc() {
        quit();
        this.a = true;
    }

    public Object get(String str) {
        this.b.getGlobal(str);
        return this.b.toJavaObject(-1);
    }

    public boolean isGc() {
        return this.a;
    }

    public void push(int i, String str) {
        if (!this.isRun) {
            this.d.sendMsg("thread is not running");
            return;
        }
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        message.setData(bundle);
        message.what = i;
        this.c.sendMessage(message);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void push(int r4, java.lang.String r5, java.lang.Object[] r6) {
        /*
            r3 = this;
            boolean r0 = r3.isRun
            if (r0 != 0) goto L_0x000c
            com.androlua.LuaContext r4 = r3.d
            java.lang.String r5 = "thread is not running"
            r4.sendMsg(r5)
            return
        L_0x000c:
            android.os.Message r0 = new android.os.Message
            r0.<init>()
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r2 = "data"
            r1.putString(r2, r5)
            java.lang.String r5 = "args"
            r1.putSerializable(r5, r6)
            r0.setData(r1)
            r0.what = r4
            android.os.Handler r4 = r3.c
            r4.sendMessage(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaRunnable.push(int, java.lang.String, java.lang.Object[]):void");
    }

    public void quit() {
        if (this.isRun) {
            this.isRun = false;
            this.c.getLooper().quit();
        }
    }

    public void run() {
        try {
            if (this.b == null) {
                a();
                if (this.h != null) {
                    a(this.h, this.g);
                } else {
                    a(this.f, this.g);
                }
            }
            if (this.e) {
                Looper.prepare();
                this.c = new ThreadHandler();
                this.isRun = true;
                this.b.getGlobal("run");
                if (!this.b.isNil(-1)) {
                    this.b.pop(1);
                    d("run", new Object[0]);
                }
                Looper.loop();
            }
            this.isRun = false;
            this.b.gc(2, 1);
            System.gc();
        } catch (LuaException e2) {
            this.d.sendMsg(e2.getMessage());
        }
    }

    public void set(String str, Object obj) {
        push(4, str, new Object[]{obj});
    }
}
