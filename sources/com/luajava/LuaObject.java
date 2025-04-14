package com.luajava;

import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.util.StringTokenizer;

public class LuaObject implements Serializable {
    protected int a;
    protected final LuaState b;

    protected LuaObject(LuaObject luaObject, LuaObject luaObject2) {
        if (luaObject.getLuaState() != luaObject2.getLuaState()) {
            throw new LuaException("LuaStates must be the same!");
        }
        synchronized (luaObject.getLuaState()) {
            if (luaObject.isTable() || luaObject.isUserdata()) {
                this.b = luaObject.getLuaState();
                luaObject.push();
                luaObject2.push();
                this.b.getTable(-2);
                this.b.remove(-2);
                a(-1);
                this.b.pop(1);
            } else {
                throw new LuaException("Object parent should be a table or userdata .");
            }
        }
    }

    protected LuaObject(LuaObject luaObject, Number number) {
        synchronized (luaObject.getLuaState()) {
            this.b = luaObject.getLuaState();
            if (luaObject.isTable() || luaObject.isUserdata()) {
                luaObject.push();
                this.b.pushNumber(number.doubleValue());
                this.b.getTable(-2);
                this.b.remove(-2);
                a(-1);
                this.b.pop(1);
            } else {
                throw new LuaException("Object parent should be a table or userdata .");
            }
        }
    }

    protected LuaObject(LuaObject luaObject, String str) {
        synchronized (luaObject.getLuaState()) {
            this.b = luaObject.getLuaState();
            if (luaObject.isTable() || luaObject.isUserdata()) {
                luaObject.push();
                this.b.pushString(str);
                this.b.getTable(-2);
                this.b.remove(-2);
                a(-1);
                this.b.pop(1);
            } else {
                throw new LuaException("Object parent should be a table or userdata .");
            }
        }
    }

    protected LuaObject(LuaState luaState) {
        this.b = luaState;
    }

    protected LuaObject(LuaState luaState, int i) {
        synchronized (luaState) {
            this.b = luaState;
            a(i);
        }
    }

    protected LuaObject(LuaState luaState, String str) {
        synchronized (luaState) {
            this.b = luaState;
            luaState.getGlobal(str);
            a(-1);
            luaState.pop(1);
        }
    }

    public LuaObject _call(Object... objArr) {
        return _call_aux(objArr, 1)[0];
    }

    public LuaObject[] _call_aux(Object[] objArr, int i) {
        int i2;
        LuaObject[] luaObjectArr;
        String str;
        String str2;
        StringBuilder sb;
        synchronized (this.b) {
            if (isFunction() || isTable() || isUserdata()) {
                int top = this.b.getTop();
                push();
                if (objArr != null) {
                    for (Object pushObjectValue : objArr) {
                        this.b.pushObjectValue(pushObjectValue);
                    }
                } else {
                    i2 = 0;
                }
                int pcall = this.b.pcall(i2, i, 0);
                if (pcall != 0) {
                    if (this.b.isString(-1)) {
                        str = this.b.toString(-1);
                        this.b.pop(1);
                    } else {
                        str = "";
                    }
                    if (pcall == 2) {
                        sb = new StringBuilder();
                        sb.append("Runtime error. ");
                        sb.append(str);
                    } else if (pcall == 4) {
                        sb = new StringBuilder();
                        sb.append("Memory allocation error. ");
                        sb.append(str);
                    } else if (pcall == 6) {
                        sb = new StringBuilder();
                        sb.append("Error while running the error handler function. ");
                        sb.append(str);
                    } else {
                        str2 = "Lua Error code " + pcall + ". " + str;
                        throw new LuaException(str2);
                    }
                    str2 = sb.toString();
                    throw new LuaException(str2);
                }
                if (i == -1) {
                    i = this.b.getTop() - top;
                }
                if (this.b.getTop() - top < i) {
                    throw new LuaException("Invalid Number of Results .");
                }
                luaObjectArr = new LuaObject[i];
                while (i > 0) {
                    luaObjectArr[i - 1] = this.b.getLuaObject(-1);
                    this.b.pop(1);
                    i--;
                }
            } else {
                throw new LuaException("Invalid object. Not a function, table or userdata .");
            }
        }
        return luaObjectArr;
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        synchronized (this.b) {
            this.b.pushValue(i);
            this.a = this.b.Lref(LuaState.LUA_REGISTRYINDEX);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:9|10|11|12|13|14) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object[] asArray() {
        /*
            r9 = this;
            com.luajava.LuaState r0 = r9.b
            monitor-enter(r0)
            boolean r1 = r9.isTable()     // Catch:{ all -> 0x004d }
            if (r1 != 0) goto L_0x0011
            com.luajava.LuaException r1 = new com.luajava.LuaException     // Catch:{ all -> 0x004d }
            java.lang.String r2 = "Invalid object. Not a table ."
            r1.<init>((java.lang.String) r2)     // Catch:{ all -> 0x004d }
            throw r1     // Catch:{ all -> 0x004d }
        L_0x0011:
            r9.push()     // Catch:{ all -> 0x004d }
            com.luajava.LuaState r1 = r9.b     // Catch:{ all -> 0x004d }
            r2 = -1
            int r1 = r1.objLen(r2)     // Catch:{ all -> 0x004d }
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r3, r1)     // Catch:{ all -> 0x004d }
            r4 = 1
            r5 = 1
        L_0x0023:
            if (r5 > r1) goto L_0x0044
            com.luajava.LuaState r6 = r9.b     // Catch:{ all -> 0x004d }
            long r7 = (long) r5     // Catch:{ all -> 0x004d }
            r6.pushInteger(r7)     // Catch:{ all -> 0x004d }
            com.luajava.LuaState r6 = r9.b     // Catch:{ all -> 0x004d }
            r7 = -2
            r6.getTable(r7)     // Catch:{ all -> 0x004d }
            int r6 = r5 + -1
            com.luajava.LuaState r7 = r9.b     // Catch:{ LuaException -> 0x003c }
            java.lang.Object r7 = r7.toJavaObject(r2)     // Catch:{ LuaException -> 0x003c }
            java.lang.reflect.Array.set(r3, r6, r7)     // Catch:{ LuaException -> 0x003c }
        L_0x003c:
            com.luajava.LuaState r6 = r9.b     // Catch:{ all -> 0x004d }
            r6.pop(r4)     // Catch:{ all -> 0x004d }
            int r5 = r5 + 1
            goto L_0x0023
        L_0x0044:
            com.luajava.LuaState r1 = r9.b     // Catch:{ all -> 0x004d }
            r1.pop(r4)     // Catch:{ all -> 0x004d }
            java.lang.Object[] r3 = (java.lang.Object[]) r3     // Catch:{ all -> 0x004d }
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            return r3
        L_0x004d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luajava.LuaObject.asArray():java.lang.Object[]");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:10|11|12|13|14|7|8) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map asMap(com.luajava.LuaState r4, java.lang.Class<?> r5, int r6) {
        /*
            r3 = this;
            monitor-enter(r4)
            boolean r5 = r3.isTable()     // Catch:{ all -> 0x0037 }
            if (r5 != 0) goto L_0x000f
            com.luajava.LuaException r5 = new com.luajava.LuaException     // Catch:{ all -> 0x0037 }
            java.lang.String r6 = "Invalid object. Not a table ."
            r5.<init>((java.lang.String) r6)     // Catch:{ all -> 0x0037 }
            throw r5     // Catch:{ all -> 0x0037 }
        L_0x000f:
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ all -> 0x0037 }
            r5.<init>()     // Catch:{ all -> 0x0037 }
            r3.push()     // Catch:{ all -> 0x0037 }
            r4.pushNil()     // Catch:{ all -> 0x0037 }
        L_0x001a:
            int r0 = r4.next(r6)     // Catch:{ all -> 0x0037 }
            r1 = 1
            if (r0 == 0) goto L_0x0032
            r0 = -2
            java.lang.Object r0 = r4.toJavaObject(r0)     // Catch:{ LuaException -> 0x002e }
            r2 = -1
            java.lang.Object r2 = r4.toJavaObject(r2)     // Catch:{ LuaException -> 0x002e }
            r5.put(r0, r2)     // Catch:{ LuaException -> 0x002e }
        L_0x002e:
            r4.pop(r1)     // Catch:{ all -> 0x0037 }
            goto L_0x001a
        L_0x0032:
            r4.pop(r1)     // Catch:{ all -> 0x0037 }
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            return r5
        L_0x0037:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luajava.LuaObject.asMap(com.luajava.LuaState, java.lang.Class, int):java.util.Map");
    }

    public Object call(Object... objArr) {
        return call_aux(objArr, 1)[0];
    }

    public Object[] call_aux(Object[] objArr, int i) {
        int i2;
        Object[] objArr2;
        String str;
        String str2;
        StringBuilder sb;
        synchronized (this.b) {
            if (isFunction() || isTable() || isUserdata()) {
                int top = this.b.getTop();
                push();
                if (objArr != null) {
                    for (Object pushObjectValue : objArr) {
                        this.b.pushObjectValue(pushObjectValue);
                    }
                } else {
                    i2 = 0;
                }
                int pcall = this.b.pcall(i2, i, 0);
                if (pcall != 0) {
                    if (this.b.isString(-1)) {
                        str = this.b.toString(-1);
                        this.b.pop(1);
                    } else {
                        str = "";
                    }
                    if (pcall == 2) {
                        sb = new StringBuilder();
                        sb.append("Runtime error. ");
                        sb.append(str);
                    } else if (pcall == 4) {
                        sb = new StringBuilder();
                        sb.append("Memory allocation error. ");
                        sb.append(str);
                    } else if (pcall == 6) {
                        sb = new StringBuilder();
                        sb.append("Error while running the error handler function. ");
                        sb.append(str);
                    } else {
                        str2 = "Lua Error code " + pcall + ". " + str;
                        throw new LuaException(str2);
                    }
                    str2 = sb.toString();
                    throw new LuaException(str2);
                }
                if (i == -1) {
                    i = this.b.getTop() - top;
                }
                if (this.b.getTop() - top < i) {
                    throw new LuaException("Invalid Number of Results .");
                }
                objArr2 = new Object[i];
                while (i > 0) {
                    objArr2[i - 1] = this.b.toJavaObject(-1);
                    this.b.pop(1);
                    i--;
                }
            } else {
                throw new LuaException("Invalid object. Not a function, table or userdata .");
            }
        }
        return objArr2;
    }

    public Object createProxy(Class cls) {
        Object newProxyInstance;
        synchronized (this.b) {
            if (!isTable() && !isFunction()) {
                throw new LuaException("Invalid Object. Must be Table or Function.");
            } else if (isFunction() && cls.getMethods().length != 1) {
                throw new LuaException("Invalid Object. Must be a interface Method of Function.");
            } else if (!isTable() || !getTable().isList()) {
                newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new LuaInvocationHandler(this));
            } else {
                throw new LuaException("Invalid Object. Must be Table is Not Array.");
            }
        }
        return newProxyInstance;
    }

    public Object createProxy(String str) {
        Object newProxyInstance;
        synchronized (this.b) {
            if (!isTable()) {
                throw new LuaException("Invalid Object. Must be Table.");
            }
            StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
            Class[] clsArr = new Class[stringTokenizer.countTokens()];
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                clsArr[i] = Class.forName(stringTokenizer.nextToken());
                i++;
            }
            newProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), clsArr, new LuaInvocationHandler(this));
        }
        return newProxyInstance;
    }

    public byte[] dump() {
        byte[] dump;
        synchronized (this.b) {
            if (!isFunction()) {
                throw new LuaException("Invalid object. Not a function .");
            }
            push();
            dump = this.b.dump(-1);
            this.b.pop(1);
        }
        return dump;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            synchronized (this.b) {
                if (this.b.getPointer() != 0) {
                    this.b.LunRef(LuaState.LUA_REGISTRYINDEX, this.a);
                }
            }
        } catch (Exception unused) {
            PrintStream printStream = System.err;
            printStream.println("Unable to release object " + this.a);
        }
    }

    public boolean getBoolean() {
        boolean z;
        synchronized (this.b) {
            push();
            z = this.b.toBoolean(-1);
            this.b.pop(1);
        }
        return z;
    }

    public LuaObject getField(String str) {
        return this.b.getLuaObject(this, str);
    }

    public LuaFunction<?> getFunction() {
        LuaFunction<?> luaFunction;
        synchronized (this.b) {
            push();
            luaFunction = new LuaFunction<>(this.b, -1);
            this.b.pop(1);
        }
        return luaFunction;
    }

    public LuaObject getI(long j) {
        return this.b.getLuaObject(this, (Number) Long.valueOf(j));
    }

    public long getInteger() {
        long integer;
        synchronized (this.b) {
            push();
            integer = this.b.toInteger(-1);
            this.b.pop(1);
        }
        return integer;
    }

    public LuaState getLuaState() {
        return this.b;
    }

    public double getNumber() {
        double number;
        synchronized (this.b) {
            push();
            number = this.b.toNumber(-1);
            this.b.pop(1);
        }
        return number;
    }

    public Object getObject() {
        Object objectFromUserdata;
        synchronized (this.b) {
            push();
            objectFromUserdata = this.b.getObjectFromUserdata(-1);
            this.b.pop(1);
        }
        return objectFromUserdata;
    }

    public String getString() {
        String luaState;
        synchronized (this.b) {
            push();
            luaState = this.b.toString(-1);
            this.b.pop(1);
        }
        return luaState;
    }

    public LuaTable<?, ?> getTable() {
        LuaTable<?, ?> luaTable;
        synchronized (this.b) {
            push();
            luaTable = new LuaTable<>(this.b, -1);
            this.b.pop(1);
        }
        return luaTable;
    }

    public boolean isBoolean() {
        boolean isBoolean;
        synchronized (this.b) {
            push();
            isBoolean = this.b.isBoolean(-1);
            this.b.pop(1);
        }
        return isBoolean;
    }

    public boolean isFunction() {
        boolean isFunction;
        synchronized (this.b) {
            push();
            isFunction = this.b.isFunction(-1);
            this.b.pop(1);
        }
        return isFunction;
    }

    public boolean isInteger() {
        boolean isInteger;
        synchronized (this.b) {
            push();
            isInteger = this.b.isInteger(-1);
            this.b.pop(1);
        }
        return isInteger;
    }

    public boolean isJavaFunction() {
        boolean isJavaFunction;
        synchronized (this.b) {
            push();
            isJavaFunction = this.b.isJavaFunction(-1);
            this.b.pop(1);
        }
        return isJavaFunction;
    }

    public boolean isJavaObject() {
        boolean isObject;
        synchronized (this.b) {
            push();
            isObject = this.b.isObject(-1);
            this.b.pop(1);
        }
        return isObject;
    }

    public boolean isNil() {
        boolean isNil;
        synchronized (this.b) {
            push();
            isNil = this.b.isNil(-1);
            this.b.pop(1);
        }
        return isNil;
    }

    public boolean isNumber() {
        boolean isNumber;
        synchronized (this.b) {
            push();
            isNumber = this.b.isNumber(-1);
            this.b.pop(1);
        }
        return isNumber;
    }

    public boolean isString() {
        boolean isString;
        synchronized (this.b) {
            push();
            isString = this.b.isString(-1);
            this.b.pop(1);
        }
        return isString;
    }

    public boolean isTable() {
        boolean isTable;
        synchronized (this.b) {
            push();
            isTable = this.b.isTable(-1);
            this.b.pop(1);
        }
        return isTable;
    }

    public boolean isUserdata() {
        boolean isUserdata;
        synchronized (this.b) {
            push();
            isUserdata = this.b.isUserdata(-1);
            this.b.pop(1);
        }
        return isUserdata;
    }

    public void pop() {
        this.b.pop(1);
    }

    public void push() {
        this.b.rawGetI(LuaState.LUA_REGISTRYINDEX, (long) this.a);
    }

    public void setField(String str, Object obj) {
        push();
        try {
            this.b.pushObjectValue(obj);
        } catch (LuaException unused) {
            this.b.pushNil();
        }
        this.b.setField(-2, str);
        this.b.pop(1);
    }

    public void setI(long j, Object obj) {
        push();
        try {
            this.b.pushObjectValue(obj);
        } catch (LuaException unused) {
            this.b.pushNil();
        }
        this.b.setI(-2, j);
        this.b.pop(1);
    }

    public String toString() {
        synchronized (this.b) {
            try {
                if (isNil()) {
                    return "nil";
                }
                if (isBoolean()) {
                    String valueOf = String.valueOf(getBoolean());
                    return valueOf;
                } else if (isNumber()) {
                    String valueOf2 = String.valueOf(getNumber());
                    return valueOf2;
                } else if (isString()) {
                    String string = getString();
                    return string;
                } else if (isFunction()) {
                    return "Lua Function";
                } else {
                    if (isJavaObject()) {
                        String obj = getObject().toString();
                        return obj;
                    } else if (isUserdata()) {
                        return "Userdata";
                    } else {
                        if (isTable()) {
                            return "Lua Table";
                        }
                        if (isJavaFunction()) {
                            return "Java Function";
                        }
                        return null;
                    }
                }
            } catch (LuaException unused) {
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int type() {
        int type;
        synchronized (this.b) {
            push();
            type = this.b.type(-1);
            this.b.pop(1);
        }
        return type;
    }
}
