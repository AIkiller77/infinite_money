package com.luajava;

import android.util.Log;
import com.androlua.LuaContext;
import java.io.PrintStream;

public class LuaState {
    public static final int LUAI_MAXSTACK = 1000000;
    public static final int LUA_ERRERR = 6;
    public static final int LUA_ERRGCMM = 5;
    public static final int LUA_ERRMEM = 4;
    public static final int LUA_ERRRUN = 2;
    public static final int LUA_ERRSYNTAX = 3;
    public static final int LUA_GCCOLLECT = 2;
    public static final int LUA_GCCOUNT = 3;
    public static final int LUA_GCCOUNTB = 4;
    public static final int LUA_GCRESTART = 1;
    public static final int LUA_GCSETPAUSE = 6;
    public static final int LUA_GCSETSTEPMUL = 7;
    public static final int LUA_GCSTEP = 5;
    public static final int LUA_GCSTOP = 0;
    public static final int LUA_MULTRET = -1;
    public static final int LUA_OPEQ = 0;
    public static final int LUA_OPLE = 2;
    public static final int LUA_OPLT = 1;
    public static final int LUA_REGISTRYINDEX = -1001000;
    public static final int LUA_RIDX_GLOBALS = 2;
    public static final int LUA_RIDX_LAST = 2;
    public static final int LUA_RIDX_MAINTHREAD = 1;
    public static final int LUA_TBOOLEAN = 1;
    public static final int LUA_TFUNCTION = 6;
    public static final int LUA_TINTEGER = 9;
    public static final int LUA_TLIGHTUSERDATA = 2;
    public static final int LUA_TNIL = 0;
    public static final int LUA_TNONE = -1;
    public static final int LUA_TNUMBER = 3;
    public static final int LUA_TSTRING = 4;
    public static final int LUA_TTABLE = 5;
    public static final int LUA_TTHREAD = 8;
    public static final int LUA_TUSERDATA = 7;
    public static final int LUA_YIELD = 1;
    private static Class<?> a = Number.class;
    private static Class<?> b = Byte.class;
    private static Class<?> c = Short.class;
    private static Class<?> d = Integer.class;
    private static Class<?> e = Long.class;
    private static Class<?> f = Float.class;
    private static Class<?> g = Double.class;
    private long h;
    private LuaContext i;

    static {
        System.loadLibrary("luajava");
    }

    protected LuaState() {
        this.h = _newstate();
    }

    protected LuaState(long j) {
        this.h = j;
        LuaStateFactory.insertLuaState(this);
    }

    private native synchronized int _LargError(long j, int i2, String str);

    private native synchronized int _LcallMeta(long j, int i2, String str);

    private native synchronized void _LcheckAny(long j, int i2);

    private native synchronized int _LcheckInteger(long j, int i2);

    private native synchronized double _LcheckNumber(long j, int i2);

    private native synchronized void _LcheckStack(long j, int i2, String str);

    private native synchronized String _LcheckString(long j, int i2);

    private native synchronized void _LcheckType(long j, int i2, int i3);

    private native synchronized int _LdoFile(long j, String str);

    private native synchronized int _LdoString(long j, String str);

    private native synchronized int _LgetMetaField(long j, int i2, String str);

    private native synchronized void _LgetMetatable(long j, String str);

    private native synchronized String _Lgsub(long j, String str, String str2, String str3);

    private native synchronized int _LloadBuffer(long j, byte[] bArr, long j2, String str);

    private native synchronized int _LloadFile(long j, String str);

    private native synchronized int _LloadString(long j, String str);

    private native synchronized int _LnewMetatable(long j, String str);

    private native synchronized int _LoptInteger(long j, int i2, int i3);

    private native synchronized double _LoptNumber(long j, int i2, double d2);

    private native synchronized String _LoptString(long j, int i2, String str);

    private native synchronized int _Lref(long j, int i2);

    private native synchronized void _LunRef(long j, int i2, int i3);

    private native synchronized void _Lwhere(long j, int i2);

    private native synchronized void _call(long j, int i2, int i3);

    private native synchronized int _checkStack(long j, int i2);

    private native synchronized void _close(long j);

    private native synchronized int _compare(long j, int i2, int i3, int i4);

    private native synchronized void _concat(long j, int i2);

    private native synchronized void _copy(long j, int i2, int i3);

    private native synchronized void _createTable(long j, int i2, int i3);

    private native synchronized byte[] _dump(long j, int i2);

    private native synchronized int _equal(long j, int i2, int i3);

    private native synchronized int _error(long j);

    private native synchronized int _gc(long j, int i2, int i3);

    private native synchronized int _getField(long j, int i2, String str);

    private native synchronized int _getGlobal(long j, String str);

    private native synchronized int _getI(long j, int i2, long j2);

    private native synchronized int _getMetaTable(long j, int i2);

    private native synchronized Object _getObjectFromUserdata(long j, int i2);

    private native synchronized int _getTable(long j, int i2);

    private native synchronized int _getTop(long j);

    private native synchronized String _getUpValue(long j, int i2, int i3);

    private native synchronized int _getUserValue(long j, int i2);

    private native synchronized void _insert(long j, int i2);

    private native synchronized int _isBoolean(long j, int i2);

    private native synchronized int _isCFunction(long j, int i2);

    private native synchronized int _isFunction(long j, int i2);

    private native synchronized int _isInteger(long j, int i2);

    private native synchronized boolean _isJavaFunction(long j, int i2);

    private native synchronized int _isNil(long j, int i2);

    private native synchronized int _isNone(long j, int i2);

    private native synchronized int _isNoneOrNil(long j, int i2);

    private native synchronized int _isNumber(long j, int i2);

    private native synchronized boolean _isObject(long j, int i2);

    private native synchronized int _isString(long j, int i2);

    private native synchronized int _isTable(long j, int i2);

    private native synchronized int _isThread(long j, int i2);

    private native synchronized int _isUserdata(long j, int i2);

    private native synchronized int _isYieldable(long j);

    private native synchronized int _lessThan(long j, int i2, int i3);

    private native synchronized void _newTable(long j);

    private native synchronized long _newstate();

    private native synchronized long _newthread(long j);

    private native synchronized int _next(long j, int i2);

    private native synchronized int _objlen(long j, int i2);

    private native synchronized void _openBase(long j);

    private native synchronized void _openDebug(long j);

    private native synchronized void _openIo(long j);

    private native synchronized void _openLibs(long j);

    private native synchronized void _openLuajava(long j);

    private native synchronized void _openMath(long j);

    private native synchronized void _openOs(long j);

    private native synchronized void _openPackage(long j);

    private native synchronized void _openString(long j);

    private native synchronized void _openTable(long j);

    private native synchronized int _pcall(long j, int i2, int i3, int i4);

    private native synchronized void _pop(long j, int i2);

    private native synchronized void _pushBoolean(long j, int i2);

    private native synchronized void _pushGlobalTable(long j);

    private native synchronized void _pushInteger(long j, long j2);

    private native synchronized void _pushJavaFunction(long j, JavaFunction javaFunction);

    private native synchronized void _pushJavaObject(long j, Object obj);

    private native synchronized void _pushLString(long j, byte[] bArr, int i2);

    private native synchronized void _pushNil(long j);

    private native synchronized void _pushNumber(long j, double d2);

    private native synchronized void _pushString(long j, String str);

    private native synchronized void _pushValue(long j, int i2);

    private native synchronized int _rawGet(long j, int i2);

    private native synchronized int _rawGetI(long j, int i2, long j2);

    private native synchronized void _rawSet(long j, int i2);

    private native synchronized void _rawSetI(long j, int i2, long j2);

    private native synchronized int _rawequal(long j, int i2, int i3);

    private native synchronized int _rawlen(long j, int i2);

    private native synchronized void _remove(long j, int i2);

    private native synchronized void _replace(long j, int i2);

    private native synchronized int _resume(long j, long j2, int i2);

    private native synchronized void _rotate(long j, int i2, int i3);

    private native synchronized void _setField(long j, int i2, String str);

    private native synchronized void _setGlobal(long j, String str);

    private native synchronized void _setI(long j, int i2, long j2);

    private native synchronized int _setMetaTable(long j, int i2);

    private native synchronized void _setTable(long j, int i2);

    private native synchronized void _setTop(long j, int i2);

    private native synchronized String _setUpValue(long j, int i2, int i3);

    private native synchronized void _setUserValue(long j, int i2);

    private native synchronized int _status(long j);

    private native synchronized int _strlen(long j, int i2);

    private native synchronized int _toBoolean(long j, int i2);

    private native synchronized byte[] _toBuffer(long j, int i2);

    private native synchronized long _toInteger(long j, int i2);

    private native synchronized double _toNumber(long j, int i2);

    private native synchronized String _toString(long j, int i2);

    private native synchronized long _toThread(long j, int i2);

    private native synchronized int _type(long j, int i2);

    private native synchronized String _typeName(long j, int i2);

    private native synchronized void _xmove(long j, long j2, int i2);

    private native synchronized int _yield(long j, int i2);

    public static Number convertLuaNumber(Double d2, Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return Integer.valueOf(d2.intValue());
            }
            if (cls == Long.TYPE) {
                return Long.valueOf(d2.longValue());
            }
            if (cls == Float.TYPE) {
                return Float.valueOf(d2.floatValue());
            }
            if (cls == Double.TYPE) {
                return Double.valueOf(d2.doubleValue());
            }
            if (cls == Byte.TYPE) {
                return Byte.valueOf(d2.byteValue());
            }
            if (cls == Short.TYPE) {
                return Short.valueOf(d2.shortValue());
            }
            return null;
        } else if (!cls.isAssignableFrom(a)) {
            return null;
        } else {
            if (cls.isAssignableFrom(d)) {
                return new Integer(d2.intValue());
            }
            if (cls.isAssignableFrom(e)) {
                return new Long(d2.longValue());
            }
            if (cls.isAssignableFrom(f)) {
                return new Float(d2.floatValue());
            }
            if (cls.isAssignableFrom(g)) {
                return d2;
            }
            if (cls.isAssignableFrom(b)) {
                return new Byte(d2.byteValue());
            }
            if (cls.isAssignableFrom(c)) {
                return new Short(d2.shortValue());
            }
            return null;
        }
    }

    public static Number convertLuaNumber(Long l, Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return Integer.valueOf(l.intValue());
            }
            if (cls == Long.TYPE) {
                return Long.valueOf(l.longValue());
            }
            if (cls == Float.TYPE) {
                return Float.valueOf(l.floatValue());
            }
            if (cls == Double.TYPE) {
                return Double.valueOf(l.doubleValue());
            }
            if (cls == Byte.TYPE) {
                return Byte.valueOf(l.byteValue());
            }
            if (cls == Short.TYPE) {
                return Short.valueOf(l.shortValue());
            }
            return null;
        } else if (!cls.isAssignableFrom(a)) {
            return null;
        } else {
            if (cls.isAssignableFrom(d)) {
                return new Integer(l.intValue());
            }
            if (cls.isAssignableFrom(e)) {
                return new Long(l.longValue());
            }
            if (cls.isAssignableFrom(f)) {
                return new Float(l.floatValue());
            }
            if (cls.isAssignableFrom(g)) {
                return l;
            }
            if (cls.isAssignableFrom(b)) {
                return new Byte(l.byteValue());
            }
            if (cls.isAssignableFrom(c)) {
                return new Short(l.shortValue());
            }
            return null;
        }
    }

    public int LargError(int i2, String str) {
        return _LargError(this.h, i2, str);
    }

    public int LcallMeta(int i2, String str) {
        return _LcallMeta(this.h, i2, str);
    }

    public void LcheckAny(int i2) {
        _LcheckAny(this.h, i2);
    }

    public int LcheckInteger(int i2) {
        return _LcheckInteger(this.h, i2);
    }

    public double LcheckNumber(int i2) {
        return _LcheckNumber(this.h, i2);
    }

    public void LcheckStack(int i2, String str) {
        _LcheckStack(this.h, i2, str);
    }

    public String LcheckString(int i2) {
        return _LcheckString(this.h, i2);
    }

    public void LcheckType(int i2, int i3) {
        _LcheckType(this.h, i2, i3);
    }

    public int LdoFile(String str) {
        return _LdoFile(this.h, str);
    }

    public int LdoString(String str) {
        return _LdoString(this.h, str);
    }

    public int LgetMetaField(int i2, String str) {
        return _LgetMetaField(this.h, i2, str);
    }

    public void LgetMetatable(String str) {
        _LgetMetatable(this.h, str);
    }

    public String Lgsub(String str, String str2, String str3) {
        return _Lgsub(this.h, str, str2, str3);
    }

    public int LloadBuffer(byte[] bArr, String str) {
        return _LloadBuffer(this.h, bArr, (long) bArr.length, str);
    }

    public int LloadFile(String str) {
        return _LloadFile(this.h, str);
    }

    public int LloadString(String str) {
        return _LloadString(this.h, str);
    }

    public int LnewMetatable(String str) {
        return _LnewMetatable(this.h, str);
    }

    public int LoptInteger(int i2, int i3) {
        return _LoptInteger(this.h, i2, i3);
    }

    public double LoptNumber(int i2, double d2) {
        return _LoptNumber(this.h, i2, d2);
    }

    public String LoptString(int i2, String str) {
        return _LoptString(this.h, i2, str);
    }

    public int Lref(int i2) {
        return _Lref(this.h, i2);
    }

    public void LunRef(int i2, int i3) {
        _LunRef(this.h, i2, i3);
    }

    public void Lwhere(int i2) {
        _Lwhere(this.h, i2);
    }

    public void call(int i2, int i3) {
        _call(this.h, i2, i3);
    }

    public int checkStack(int i2) {
        return _checkStack(this.h, i2);
    }

    public synchronized void close() {
        LuaStateFactory.removeLuaState(this.h);
        _close(this.h);
        this.h = 0;
    }

    public int compare(int i2, int i3, int i4) {
        return _compare(this.h, i2, i3, i4);
    }

    public void concat(int i2) {
        _concat(this.h, i2);
    }

    public void copy(int i2, int i3) {
        _copy(this.h, i2, i3);
    }

    public void createTable(int i2, int i3) {
        _createTable(this.h, i2, i3);
    }

    public byte[] dump(int i2) {
        return _dump(this.h, i2);
    }

    public int equal(int i2, int i3) {
        return _equal(this.h, i2, i3);
    }

    public int error() {
        return _error(this.h);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        Log.i("luaState", "finalize: " + this.h);
        try {
            close();
        } catch (Exception unused) {
            PrintStream printStream = System.err;
            printStream.println("Unable to release luaState " + this.h);
        }
    }

    public int gc(int i2, int i3) {
        return _gc(this.h, i2, i3);
    }

    public LuaContext getContext() {
        return this.i;
    }

    public int getField(int i2, String str) {
        return _getField(this.h, i2, str);
    }

    public LuaFunction getFunction(int i2) {
        LuaObject luaObject = getLuaObject(i2);
        if (luaObject.isFunction()) {
            return luaObject.getFunction();
        }
        return null;
    }

    public LuaFunction getFunction(String str) {
        LuaObject luaObject = getLuaObject(str);
        if (luaObject.isFunction()) {
            return luaObject.getFunction();
        }
        return null;
    }

    public synchronized int getGlobal(String str) {
        return _getGlobal(this.h, str);
    }

    public int getI(int i2, long j) {
        return _getI(this.h, i2, j);
    }

    public LuaObject getLuaObject(int i2) {
        return isFunction(i2) ? new LuaFunction(this, i2) : isTable(i2) ? new LuaTable(this, i2) : new LuaObject(this, i2);
    }

    public LuaObject getLuaObject(LuaObject luaObject, LuaObject luaObject2) {
        if (luaObject.getLuaState().getPointer() == this.h && luaObject.getLuaState().getPointer() == luaObject2.getLuaState().getPointer()) {
            return new LuaObject(luaObject, luaObject2);
        }
        throw new LuaException("Object must have the same LuaState as the parent!");
    }

    public LuaObject getLuaObject(LuaObject luaObject, Number number) {
        return new LuaObject(luaObject, number);
    }

    public LuaObject getLuaObject(LuaObject luaObject, String str) {
        return new LuaObject(luaObject, str);
    }

    public LuaObject getLuaObject(String str) {
        pushGlobalTable();
        pushString(str);
        rawGet(-2);
        LuaObject luaObject = getLuaObject(-1);
        pop(2);
        return luaObject;
    }

    public int getMetaTable(int i2) {
        return _getMetaTable(this.h, i2);
    }

    public Object getObjectFromUserdata(int i2) {
        return _getObjectFromUserdata(this.h, i2);
    }

    public long getPointer() {
        return this.h;
    }

    public int getTable(int i2) {
        return _getTable(this.h, i2);
    }

    public int getTop() {
        return _getTop(this.h);
    }

    public String getUpValue(int i2, int i3) {
        return _getUpValue(this.h, i2, i3);
    }

    public int getUserValue(int i2) {
        return _getUserValue(this.h, i2);
    }

    public void insert(int i2) {
        _insert(this.h, i2);
    }

    public boolean isBoolean(int i2) {
        return _isBoolean(this.h, i2) != 0;
    }

    public boolean isCFunction(int i2) {
        return _isCFunction(this.h, i2) != 0;
    }

    public synchronized boolean isClosed() {
        return this.h == 0;
    }

    public boolean isFunction(int i2) {
        return _isFunction(this.h, i2) != 0;
    }

    public boolean isInteger(int i2) {
        return _isInteger(this.h, i2) != 0;
    }

    public boolean isJavaFunction(int i2) {
        return _isJavaFunction(this.h, i2);
    }

    public boolean isNil(int i2) {
        return _isNil(this.h, i2) != 0;
    }

    public boolean isNone(int i2) {
        return _isNone(this.h, i2) != 0;
    }

    public boolean isNoneOrNil(int i2) {
        return _isNoneOrNil(this.h, i2) != 0;
    }

    public boolean isNumber(int i2) {
        return _isNumber(this.h, i2) != 0;
    }

    public boolean isObject(int i2) {
        return _isObject(this.h, i2);
    }

    public boolean isString(int i2) {
        return _isString(this.h, i2) != 0;
    }

    public boolean isTable(int i2) {
        return _isTable(this.h, i2) != 0;
    }

    public boolean isThread(int i2) {
        return _isThread(this.h, i2) != 0;
    }

    public boolean isUserdata(int i2) {
        return _isUserdata(this.h, i2) != 0;
    }

    public int isYieldable() {
        return _isYieldable(this.h);
    }

    public int lessThan(int i2, int i3) {
        return _lessThan(this.h, i2, i3);
    }

    public void newTable() {
        _newTable(this.h);
    }

    public LuaState newThread() {
        LuaState luaState = new LuaState(_newthread(this.h));
        LuaStateFactory.insertLuaState(luaState);
        return luaState;
    }

    public int next(int i2) {
        return _next(this.h, i2);
    }

    public int objLen(int i2) {
        return _objlen(this.h, i2);
    }

    public void openBase() {
        _openBase(this.h);
    }

    public void openDebug() {
        _openDebug(this.h);
    }

    public void openIo() {
        _openIo(this.h);
    }

    public void openLibs() {
        _openLibs(this.h);
        _openLuajava(this.h);
        pushPrimitive();
    }

    public void openLuajava() {
        _openLuajava(this.h);
        pushPrimitive();
    }

    public void openMath() {
        _openMath(this.h);
    }

    public void openOs() {
        _openOs(this.h);
    }

    public void openPackage() {
        _openPackage(this.h);
    }

    public void openString() {
        _openString(this.h);
    }

    public void openTable() {
        _openTable(this.h);
    }

    public int pcall(int i2, int i3, int i4) {
        return _pcall(this.h, i2, i3, i4);
    }

    public void pop(int i2) {
        _pop(this.h, i2);
    }

    public void pushBoolean(boolean z) {
        _pushBoolean(this.h, z ? 1 : 0);
    }

    public void pushContext(LuaContext luaContext) {
        this.i = luaContext;
        pushString("_LuaContext");
        pushJavaObject(luaContext);
        setTable(LUA_REGISTRYINDEX);
    }

    public synchronized void pushGlobalTable() {
        _pushGlobalTable(this.h);
    }

    public void pushInteger(long j) {
        _pushInteger(this.h, j);
    }

    public void pushJavaFunction(JavaFunction javaFunction) {
        _pushJavaFunction(this.h, javaFunction);
    }

    public void pushJavaObject(Object obj) {
        _pushJavaObject(this.h, obj);
    }

    public void pushNil() {
        _pushNil(this.h);
    }

    public void pushNumber(double d2) {
        _pushNumber(this.h, d2);
    }

    public void pushObjectValue(Object obj) {
        double doubleValue;
        int byteValue;
        long j;
        if (obj == null) {
            pushNil();
        } else if (obj instanceof Boolean) {
            pushBoolean(((Boolean) obj).booleanValue());
        } else {
            if (obj instanceof Long) {
                j = ((Long) obj).longValue();
            } else {
                if (obj instanceof Integer) {
                    byteValue = ((Integer) obj).intValue();
                } else if (obj instanceof Short) {
                    byteValue = ((Short) obj).shortValue();
                } else if (obj instanceof Character) {
                    byteValue = ((Character) obj).charValue();
                } else if (obj instanceof Byte) {
                    byteValue = ((Byte) obj).byteValue();
                } else {
                    if (obj instanceof Float) {
                        doubleValue = (double) ((Float) obj).floatValue();
                    } else if (obj instanceof Double) {
                        doubleValue = ((Double) obj).doubleValue();
                    } else if (obj instanceof String) {
                        pushString((String) obj);
                        return;
                    } else if (obj instanceof LuaString) {
                        pushString(((LuaString) obj).toByteArray());
                        return;
                    } else if (obj instanceof JavaFunction) {
                        pushJavaFunction((JavaFunction) obj);
                        return;
                    } else {
                        boolean z = obj instanceof LuaObject;
                        LuaObject luaObject = obj;
                        if (z) {
                            LuaObject luaObject2 = (LuaObject) obj;
                            LuaState luaState = luaObject2.getLuaState();
                            luaObject = luaObject2;
                            if (luaState == this) {
                                luaObject2.push();
                                return;
                            }
                        }
                        pushJavaObject(luaObject);
                        return;
                    }
                    pushNumber(doubleValue);
                    return;
                }
                j = (long) byteValue;
            }
            pushInteger(j);
        }
    }

    public void pushPrimitive() {
        pushJavaObject(Boolean.TYPE);
        setGlobal("boolean");
        pushJavaObject(Byte.TYPE);
        setGlobal("byte");
        pushJavaObject(Character.TYPE);
        setGlobal("char");
        pushJavaObject(Short.TYPE);
        setGlobal("short");
        pushJavaObject(Integer.TYPE);
        setGlobal("int");
        pushJavaObject(Long.TYPE);
        setGlobal("long");
        pushJavaObject(Float.TYPE);
        setGlobal("float");
        pushJavaObject(Double.TYPE);
        setGlobal("double");
    }

    public void pushString(String str) {
        if (str == null) {
            _pushNil(this.h);
        } else {
            _pushString(this.h, str);
        }
    }

    public void pushString(byte[] bArr) {
        if (bArr == null) {
            _pushNil(this.h);
        } else {
            _pushLString(this.h, bArr, bArr.length);
        }
    }

    public void pushValue(int i2) {
        _pushValue(this.h, i2);
    }

    public int rawGet(int i2) {
        return _rawGet(this.h, i2);
    }

    public int rawGetI(int i2, long j) {
        return _rawGetI(this.h, i2, j);
    }

    public int rawLen(int i2) {
        return _rawlen(this.h, i2);
    }

    public void rawSet(int i2) {
        _rawSet(this.h, i2);
    }

    public void rawSetI(int i2, long j) {
        _rawSetI(this.h, i2, j);
    }

    public int rawequal(int i2, int i3) {
        return _rawequal(this.h, i2, i3);
    }

    public void remove(int i2) {
        _remove(this.h, i2);
    }

    public void replace(int i2) {
        _replace(this.h, i2);
    }

    public int resume(LuaState luaState, int i2) {
        return _resume(this.h, luaState.getPointer(), i2);
    }

    public void rotate(int i2, int i3) {
        _rotate(this.h, i2, i3);
    }

    public void setField(int i2, String str) {
        _setField(this.h, i2, str);
    }

    public synchronized void setGlobal(String str) {
        _setGlobal(this.h, str);
    }

    public void setI(int i2, long j) {
        _setI(this.h, i2, j);
    }

    public int setMetaTable(int i2) {
        return _setMetaTable(this.h, i2);
    }

    public void setTable(int i2) {
        _setTable(this.h, i2);
    }

    public void setTop(int i2) {
        _setTop(this.h, i2);
    }

    public String setUpValue(int i2, int i3) {
        return _setUpValue(this.h, i2, i3);
    }

    public void setUserValue(int i2) {
        _setUserValue(this.h, i2);
    }

    public int status() {
        return _status(this.h);
    }

    public int strLen(int i2) {
        return _strlen(this.h, i2);
    }

    public boolean toBoolean(int i2) {
        return _toBoolean(this.h, i2) != 0;
    }

    public byte[] toBuffer(int i2) {
        return _toBuffer(this.h, i2);
    }

    public long toInteger(int i2) {
        return _toInteger(this.h, i2);
    }

    public synchronized Object toJavaObject(int i2) {
        Object obj;
        obj = null;
        if (isBoolean(i2)) {
            obj = Boolean.valueOf(toBoolean(i2));
        } else if (type(i2) == 4) {
            obj = toString(i2);
        } else if (isFunction(i2)) {
            obj = getLuaObject(i2).getFunction();
        } else if (isTable(i2)) {
            obj = getLuaObject(i2).getTable();
        } else if (type(i2) == 3) {
            obj = isInteger(i2) ? Long.valueOf(toInteger(i2)) : Double.valueOf(toNumber(i2));
        } else if (isUserdata(i2)) {
            obj = isObject(i2) ? getObjectFromUserdata(i2) : getLuaObject(i2);
        } else {
            boolean isNil = isNil(i2);
        }
        return obj;
    }

    public double toNumber(int i2) {
        return _toNumber(this.h, i2);
    }

    public String toString(int i2) {
        return _toString(this.h, i2);
    }

    public LuaState toThread(int i2) {
        return new LuaState(_toThread(this.h, i2));
    }

    public int type(int i2) {
        return _type(this.h, i2);
    }

    public String typeName(int i2) {
        return _typeName(this.h, i2);
    }

    public void xmove(LuaState luaState, int i2) {
        _xmove(this.h, luaState.h, i2);
    }

    public int yield(int i2) {
        return _yield(this.h, i2);
    }
}
