package com.androlua;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.a.a.a.a.a.a.a;
import com.androlua.LuaBroadcastReceiver;
import com.androlua.Ticker;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import dalvik.system.DexClassLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.lingala.zip4j.util.InternalZipConstants;

public class LuaService extends Service implements LuaBroadcastReceiver.OnReceiveListener, LuaContext {
    private static LuaService b;
    LuaBinder a = new LuaBinder();
    private LuaDexLoader c;
    private ArrayList<LuaGcable> d = new ArrayList<>();
    private String e;
    private MainHandler f;
    private String g;
    private LuaState h;
    private String i;
    private String j;
    private String k;
    private String l;
    public String luaCpath;
    public String luaDir;
    private String m;
    private BroadcastReceiver n;
    /* access modifiers changed from: private */
    public StringBuilder o = new StringBuilder();
    private Toast p;

    /* renamed from: q  reason: collision with root package name */
    private StringBuilder f26q = new StringBuilder();
    private long r;
    private LuaResources s;

    public class LuaBinder extends Binder {
        public LuaBinder() {
        }

        public LuaService getService() {
            return LuaService.this;
        }
    }

    public class MainHandler extends Handler {
        public MainHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    LuaService.this.showToast(message.getData().getString("data"));
                    return;
                case 1:
                    Bundle data = message.getData();
                    LuaService.this.a(data.getString("data"), ((Object[]) data.getSerializable("args"))[0]);
                    return;
                case 2:
                    LuaService.this.runFunc(message.getData().getString("data"), new Object[0]);
                    return;
                case 3:
                    LuaService.this.runFunc(message.getData().getString("data"), (Object[]) message.getData().getSerializable("args"));
                    return;
                default:
                    return;
            }
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

    private void a() {
        this.h = LuaStateFactory.newLuaState();
        this.h.openLibs();
        this.h.pushJavaObject(this);
        this.h.setGlobal("service");
        this.h.getGlobal("service");
        this.h.setGlobal("this");
        this.h.pushContext(this);
        this.h.getGlobal("luajava");
        this.h.pushString(this.m);
        this.h.setField(-2, "luaextdir");
        this.h.pushString(this.luaDir);
        this.h.setField(-2, "luadir");
        this.h.pushString(this.i);
        this.h.setField(-2, "luapath");
        this.h.pop(1);
        new LuaAssetLoader(this, this.h);
        this.h.getGlobal("package");
        this.h.pushString(this.e);
        this.h.setField(-2, "path");
        this.h.pushString(this.luaCpath);
        this.h.setField(-2, "cpath");
        this.h.pop(1);
        new JavaFunction(this.h) {
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x003e, code lost:
                if (r0.equals("boolean") == false) goto L_0x004b;
             */
            /* JADX WARNING: Removed duplicated region for block: B:17:0x004f  */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0056  */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x0065  */
            /* JADX WARNING: Removed duplicated region for block: B:27:0x0074  */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x0075 A[SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public int execute() {
                /*
                    r8 = this;
                    com.luajava.LuaState r0 = r8.b
                    int r0 = r0.getTop()
                    r1 = 2
                    r2 = 0
                    if (r0 >= r1) goto L_0x0012
                    com.androlua.LuaService r0 = com.androlua.LuaService.this
                    java.lang.String r1 = ""
                    r0.sendMsg(r1)
                    return r2
                L_0x0012:
                    com.luajava.LuaState r0 = r8.b
                    int r0 = r0.getTop()
                    r3 = 1
                    if (r1 > r0) goto L_0x0098
                    com.luajava.LuaState r0 = r8.b
                    int r0 = r0.type(r1)
                    r4 = 0
                    com.luajava.LuaState r5 = r8.b
                    java.lang.String r0 = r5.typeName(r0)
                    r5 = -1
                    int r6 = r0.hashCode()
                    r7 = -266011147(0xfffffffff024fdf5, float:-2.0425023E29)
                    if (r6 == r7) goto L_0x0041
                    r7 = 64711720(0x3db6c28, float:1.2896495E-36)
                    if (r6 == r7) goto L_0x0038
                    goto L_0x004b
                L_0x0038:
                    java.lang.String r6 = "boolean"
                    boolean r6 = r0.equals(r6)
                    if (r6 == 0) goto L_0x004b
                    goto L_0x004c
                L_0x0041:
                    java.lang.String r3 = "userdata"
                    boolean r3 = r0.equals(r3)
                    if (r3 == 0) goto L_0x004b
                    r3 = 0
                    goto L_0x004c
                L_0x004b:
                    r3 = -1
                L_0x004c:
                    switch(r3) {
                        case 0: goto L_0x0065;
                        case 1: goto L_0x0056;
                        default: goto L_0x004f;
                    }
                L_0x004f:
                    com.luajava.LuaState r3 = r8.b
                    java.lang.String r4 = r3.toString(r1)
                    goto L_0x0071
                L_0x0056:
                    com.luajava.LuaState r3 = r8.b
                    boolean r3 = r3.toBoolean(r1)
                    if (r3 == 0) goto L_0x0062
                    java.lang.String r3 = "true"
                L_0x0060:
                    r4 = r3
                    goto L_0x0071
                L_0x0062:
                    java.lang.String r3 = "false"
                    goto L_0x0060
                L_0x0065:
                    com.luajava.LuaState r3 = r8.b
                    java.lang.Object r3 = r3.toJavaObject(r1)
                    if (r3 == 0) goto L_0x0071
                    java.lang.String r4 = r3.toString()
                L_0x0071:
                    if (r4 != 0) goto L_0x0074
                    goto L_0x0075
                L_0x0074:
                    r0 = r4
                L_0x0075:
                    com.androlua.LuaService r3 = com.androlua.LuaService.this
                    java.lang.StringBuilder r3 = r3.o
                    java.lang.String r4 = "\t"
                    r3.append(r4)
                    com.androlua.LuaService r3 = com.androlua.LuaService.this
                    java.lang.StringBuilder r3 = r3.o
                    r3.append(r0)
                    com.androlua.LuaService r0 = com.androlua.LuaService.this
                    java.lang.StringBuilder r0 = r0.o
                    java.lang.String r3 = "\t"
                    r0.append(r3)
                    int r1 = r1 + 1
                    goto L_0x0012
                L_0x0098:
                    com.androlua.LuaService r0 = com.androlua.LuaService.this
                    com.androlua.LuaService r1 = com.androlua.LuaService.this
                    java.lang.StringBuilder r1 = r1.o
                    java.lang.String r1 = r1.toString()
                    com.androlua.LuaService r4 = com.androlua.LuaService.this
                    java.lang.StringBuilder r4 = r4.o
                    int r4 = r4.length()
                    int r4 = r4 - r3
                    java.lang.String r1 = r1.substring(r3, r4)
                    r0.sendMsg(r1)
                    com.androlua.LuaService r0 = com.androlua.LuaService.this
                    java.lang.StringBuilder r0 = r0.o
                    r0.setLength(r2)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaService.AnonymousClass2.execute():int");
            }
        }.register("print");
        new JavaFunction(this.h) {
            public int execute() {
                ((LuaThread) this.b.toJavaObject(2)).set(this.b.toString(3), this.b.toJavaObject(4));
                return 0;
            }
        }.register("set");
        new JavaFunction(this.h) {
            public int execute() {
                LuaThread luaThread = (LuaThread) this.b.toJavaObject(2);
                int top = this.b.getTop();
                if (top > 3) {
                    Object[] objArr = new Object[(top - 3)];
                    for (int i = 4; i <= top; i++) {
                        objArr[i - 4] = this.b.toJavaObject(i);
                    }
                    luaThread.call(this.b.toString(3), objArr);
                    return 0;
                } else if (top != 3) {
                    return 0;
                } else {
                    luaThread.call(this.b.toString(3));
                    return 0;
                }
            }
        }.register("call");
    }

    /* access modifiers changed from: private */
    public void a(String str, Object obj) {
        try {
            this.h.pushObjectValue(obj);
            this.h.setGlobal(str);
        } catch (LuaException e2) {
            sendMsg(e2.getMessage());
        }
    }

    private void a(String str, String str2) {
        try {
            if (new File(str).exists()) {
                FileInputStream fileInputStream = new FileInputStream(str);
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                byte[] bArr = new byte[4096];
                int i2 = 0;
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        i2 += read;
                        System.out.println(i2);
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        return;
                    }
                }
            }
        } catch (Exception e2) {
            System.out.println("复制文件操作出错");
            a.a(e2);
        }
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static LuaService getService() {
        return b;
    }

    public void call(String str) {
        push(2, str);
    }

    public void call(String str, Object[] objArr) {
        if (objArr.length == 0) {
            push(2, str);
        } else {
            push(3, str, objArr);
        }
    }

    public Object doAsset(String str, Object... objArr) {
        try {
            byte[] readAsset = readAsset(str);
            this.h.setTop(0);
            int LloadBuffer = this.h.LloadBuffer(readAsset, str);
            if (LloadBuffer == 0) {
                this.h.getGlobal("debug");
                this.h.getField(-1, "traceback");
                this.h.remove(-2);
                this.h.insert(-2);
                int length = objArr != null ? objArr.length : 0;
                for (int i2 = 0; i2 < length; i2++) {
                    this.h.pushObjectValue(objArr[i2]);
                }
                LloadBuffer = this.h.pcall(length, 0, -2 - length);
                if (LloadBuffer == 0) {
                    return this.h.toJavaObject(-1);
                }
            }
            throw new LuaException(a(LloadBuffer) + ": " + this.h.toString(-1));
        } catch (Exception e2) {
            sendMsg(e2.getMessage());
            return null;
        }
    }

    public Object doFile(String str) {
        return doFile(str, new Object[0]);
    }

    public Object doFile(String str, Object[] objArr) {
        try {
            if (str.charAt(0) != '/') {
                str = this.luaDir + InternalZipConstants.ZIP_FILE_SEPARATOR + str;
            }
            this.h.setTop(0);
            int LloadFile = this.h.LloadFile(str);
            if (LloadFile == 0) {
                this.h.getGlobal("debug");
                this.h.getField(-1, "traceback");
                this.h.remove(-2);
                this.h.insert(-2);
                int length = objArr != null ? objArr.length : 0;
                for (int i2 = 0; i2 < length; i2++) {
                    this.h.pushObjectValue(objArr[i2]);
                }
                LloadFile = this.h.pcall(length, 1, -2 - length);
                if (LloadFile == 0) {
                    return this.h.toJavaObject(-1);
                }
            }
            throw new LuaException(a(LloadFile) + ": " + this.h.toString(-1));
        } catch (LuaException e2) {
            sendMsg(e2.getMessage());
            return null;
        }
    }

    public Object doString(String str, Object... objArr) {
        try {
            this.h.setTop(0);
            int LloadString = this.h.LloadString(str);
            if (LloadString == 0) {
                this.h.getGlobal("debug");
                this.h.getField(-1, "traceback");
                this.h.remove(-2);
                this.h.insert(-2);
                int length = objArr != null ? objArr.length : 0;
                for (int i2 = 0; i2 < length; i2++) {
                    this.h.pushObjectValue(objArr[i2]);
                }
                LloadString = this.h.pcall(length, 1, -2 - length);
                if (LloadString == 0) {
                    return this.h.toJavaObject(-1);
                }
            }
            throw new LuaException(a(LloadString) + ": " + this.h.toString(-1));
        } catch (LuaException e2) {
            sendMsg(e2.getMessage());
            return null;
        }
    }

    public Object get(String str) {
        this.h.getGlobal(str);
        return this.h.toJavaObject(-1);
    }

    public AssetManager getAssets() {
        return (this.c == null || this.c.getAssets() == null) ? super.getAssets() : this.c.getAssets();
    }

    public LuaBinder getBinder() {
        return this.a;
    }

    public ArrayList<ClassLoader> getClassLoaders() {
        return this.c.getClassLoaders();
    }

    public Context getContext() {
        return this;
    }

    public Map getGlobalData() {
        return LuaApplication.getInstance().getGlobalData();
    }

    public int getHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public HashMap<String, String> getLibrarys() {
        return this.c.getLibrarys();
    }

    public String getLuaCpath() {
        return this.luaCpath;
    }

    public String getLuaDir() {
        return this.luaDir;
    }

    public String getLuaDir(String str) {
        File file = new File(this.luaDir + InternalZipConstants.ZIP_FILE_SEPARATOR + str);
        if (file.exists() || file.mkdirs()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public String getLuaExtDir() {
        return this.m;
    }

    public String getLuaExtDir(String str) {
        File file = new File(this.m + InternalZipConstants.ZIP_FILE_SEPARATOR + str);
        if (file.exists() || file.mkdirs()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public String getLuaExtPath(String str) {
        return new File(getLuaExtDir(), str).getAbsolutePath();
    }

    public String getLuaExtPath(String str, String str2) {
        return new File(getLuaExtDir(str), str2).getAbsolutePath();
    }

    public String getLuaLpath() {
        return this.e;
    }

    public String getLuaPath() {
        return this.i;
    }

    public String getLuaPath(String str) {
        return new File(getLuaDir(), str).getAbsolutePath();
    }

    public String getLuaPath(String str, String str2) {
        return new File(getLuaDir(str), str2).getAbsolutePath();
    }

    public LuaResources getLuaResources() {
        Resources resources = super.getResources();
        if (!(this.c == null || this.c.getResources() == null)) {
            resources = this.c.getResources();
        }
        this.s = new LuaResources(getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.s.setSuperResources(resources);
        return this.s;
    }

    public LuaState getLuaState() {
        return this.h;
    }

    public Resources getResources() {
        return (this.c == null || this.c.getResources() == null) ? this.s != null ? this.s : super.getResources() : this.c.getResources();
    }

    public Object getSharedData(String str) {
        return LuaApplication.getInstance().getSharedData(str);
    }

    public Object getSharedData(String str, Object obj) {
        return LuaApplication.getInstance().getSharedData(str, obj);
    }

    public Resources getSuperResources() {
        return super.getResources();
    }

    public int getWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    public DexClassLoader loadDex(String str) {
        return this.c.loadDex(str);
    }

    public Object loadLib(String str) {
        int indexOf = str.indexOf(".");
        String substring = indexOf > 0 ? str.substring(0, indexOf) : str;
        if (!new File(this.l + "/lib" + substring + ".so").exists()) {
            if (!new File(this.luaDir + "/lib" + substring + ".so").exists()) {
                throw new LuaException("can not find lib " + str);
            }
            a(this.luaDir + "/lib" + substring + ".so", this.l + "/lib" + substring + ".so");
        }
        return this.h.getLuaObject("require").call(str);
    }

    public void loadResources(String str) {
        this.c.loadResources(str);
    }

    public LuaAsyncTask newTask(LuaObject luaObject) {
        return newTask(luaObject, (LuaObject) null, (LuaObject) null);
    }

    public LuaAsyncTask newTask(LuaObject luaObject, LuaObject luaObject2) {
        return newTask(luaObject, (LuaObject) null, luaObject2);
    }

    public LuaAsyncTask newTask(LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3) {
        return new LuaAsyncTask(this, luaObject, luaObject2, luaObject3);
    }

    public LuaThread newThread(LuaObject luaObject) {
        return newThread(luaObject, (Object[]) null);
    }

    public LuaThread newThread(LuaObject luaObject, Object[] objArr) {
        return new LuaThread((LuaContext) this, luaObject, true, objArr);
    }

    public LuaTimer newTimer(LuaObject luaObject) {
        return newTimer(luaObject, (Object[]) null);
    }

    public LuaTimer newTimer(LuaObject luaObject, Object[] objArr) {
        return new LuaTimer((LuaContext) this, luaObject, objArr);
    }

    public IBinder onBind(Intent intent) {
        startForeground(1, new Notification());
        return new LuaBinder();
    }

    public void onCreate() {
        super.onCreate();
        b = this;
        LuaApplication luaApplication = (LuaApplication) getApplication();
        this.j = luaApplication.getLocalDir();
        this.k = luaApplication.getOdexDir();
        this.l = luaApplication.getLibDir();
        this.g = luaApplication.getMdDir();
        this.luaCpath = luaApplication.getLuaCpath();
        this.luaDir = this.j;
        this.e = luaApplication.getLuaLpath();
        this.m = luaApplication.getLuaExtDir();
        this.f = new MainHandler();
    }

    public void onDestroy() {
        runFunc("onDestroy", new Object[0]);
        if (this.n != null) {
            unregisterReceiver(this.n);
        }
        super.onDestroy();
    }

    public void onReceive(Context context, Intent intent) {
        runFunc("onReceive", context, intent);
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        b = this;
        if (this.h == null) {
            startForeground(1, new Notification());
            this.i = intent.getStringExtra("luaPath");
            this.luaDir = intent.getStringExtra("luaDir");
            this.e = this.luaDir + "/?.lua;" + this.luaDir + "/lua/?.lua;" + this.luaDir + "/?/init.lua;" + this.e;
            Uri data = intent.getData();
            try {
                a();
                this.c = new LuaDexLoader(this);
                this.c.loadLibs();
                doFile(data != null ? data.getPath() : "service.lua");
            } catch (Exception e2) {
                sendMsg(e2.getMessage());
            }
        }
        runFunc("onStartCommand", intent, Integer.valueOf(i2), Integer.valueOf(i3));
        runFunc("onStart", (Object[]) intent.getSerializableExtra("arg"));
        return super.onStartCommand(intent, i2, i3);
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void push(int i2, String str) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        message.setData(bundle);
        message.what = i2;
        this.f.sendMessage(message);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void push(int r4, java.lang.String r5, java.lang.Object[] r6) {
        /*
            r3 = this;
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
            com.androlua.LuaService$MainHandler r4 = r3.f
            r4.sendMessage(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaService.push(int, java.lang.String, java.lang.Object[]):void");
    }

    public byte[] readAsset(String str) {
        InputStream open = getAssets().open(str);
        byte[] a2 = a(open);
        open.close();
        return a2;
    }

    public void regGc(LuaGcable luaGcable) {
        this.d.add(luaGcable);
    }

    public Intent registerReceiver(IntentFilter intentFilter) {
        if (this.n != null) {
            unregisterReceiver(this.n);
        }
        this.n = new LuaBroadcastReceiver(this);
        return super.registerReceiver(this.n, intentFilter);
    }

    public Intent registerReceiver(LuaBroadcastReceiver.OnReceiveListener onReceiveListener, IntentFilter intentFilter) {
        return super.registerReceiver(new LuaBroadcastReceiver(onReceiveListener), intentFilter);
    }

    public Intent registerReceiver(LuaBroadcastReceiver luaBroadcastReceiver, IntentFilter intentFilter) {
        return super.registerReceiver(luaBroadcastReceiver, intentFilter);
    }

    public Object runFunc(String str, Object... objArr) {
        if (this.h == null) {
            return null;
        }
        try {
            this.h.setTop(0);
            this.h.getGlobal(str);
            if (!this.h.isFunction(-1)) {
                return null;
            }
            this.h.getGlobal("debug");
            this.h.getField(-1, "traceback");
            this.h.remove(-2);
            this.h.insert(-2);
            int length = objArr != null ? objArr.length : 0;
            for (int i2 = 0; i2 < length; i2++) {
                this.h.pushObjectValue(objArr[i2]);
            }
            int pcall = this.h.pcall(length, 1, -2 - length);
            if (pcall == 0) {
                return this.h.toJavaObject(-1);
            }
            throw new LuaException(a(pcall) + ": " + this.h.toString(-1));
        } catch (LuaException e2) {
            sendMsg(str + " " + e2.getMessage());
            return null;
        }
    }

    public void sendError(String str, Exception exc) {
        runFunc("onError", str, exc);
    }

    public void sendMsg(String str) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        message.setData(bundle);
        message.what = 0;
        this.f.sendMessage(message);
        Log.i("lua", str);
    }

    public void set(String str, Object obj) {
        push(1, str, new Object[]{obj});
    }

    public void setBinder(LuaBinder luaBinder) {
        this.a = luaBinder;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setLuaExtDir(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = android.os.Environment.getExternalStorageState()
            java.lang.String r1 = "mounted"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0020
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r0 = r0.getAbsolutePath()
            java.io.File r1 = new java.io.File
            r1.<init>(r0, r8)
            java.lang.String r8 = r1.getAbsolutePath()
        L_0x001d:
            r7.m = r8
            goto L_0x0058
        L_0x0020:
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/storage"
            r0.<init>(r1)
            java.io.File[] r0 = r0.listFiles()
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x002e:
            if (r3 >= r1) goto L_0x004b
            r4 = r0[r3]
            java.lang.String[] r5 = r4.list()
            if (r5 != 0) goto L_0x0039
            goto L_0x0048
        L_0x0039:
            int r5 = r5.length
            r6 = 5
            if (r5 <= r6) goto L_0x0048
            java.io.File r5 = new java.io.File
            r5.<init>(r4, r8)
            java.lang.String r4 = r5.getAbsolutePath()
            r7.m = r4
        L_0x0048:
            int r3 = r3 + 1
            goto L_0x002e
        L_0x004b:
            java.lang.String r0 = r7.m
            if (r0 != 0) goto L_0x0058
            java.io.File r8 = r7.getDir(r8, r2)
            java.lang.String r8 = r8.getAbsolutePath()
            goto L_0x001d
        L_0x0058:
            java.io.File r8 = new java.io.File
            java.lang.String r0 = r7.m
            r8.<init>(r0)
            boolean r0 = r8.exists()
            if (r0 != 0) goto L_0x0068
            r8.mkdirs()
        L_0x0068:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaService.setLuaExtDir(java.lang.String):void");
    }

    public boolean setSharedData(String str, Object obj) {
        return LuaApplication.getInstance().setSharedData(str, obj);
    }

    @SuppressLint({"ShowToast"})
    public void showToast(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.p != null) {
                if (currentTimeMillis - this.r <= 1000) {
                    this.f26q.append("\n");
                    this.f26q.append(str);
                    this.p.setText(this.f26q.toString());
                    this.p.setDuration(1);
                    this.r = currentTimeMillis;
                    this.p.show();
                }
            }
            this.f26q.setLength(0);
            this.p = Toast.makeText(this, str, 1);
            this.f26q.append(str);
            this.r = currentTimeMillis;
            this.p.show();
        } catch (Exception e2) {
            a.a(e2);
        }
    }

    public LuaAsyncTask task(long j2, LuaObject luaObject) {
        return task(j2, (Object[]) null, (LuaObject) null);
    }

    public LuaAsyncTask task(long j2, Object[] objArr, LuaObject luaObject) {
        LuaAsyncTask luaAsyncTask = new LuaAsyncTask((LuaContext) this, j2, luaObject);
        luaAsyncTask.execute((Params[]) objArr);
        return luaAsyncTask;
    }

    public LuaAsyncTask task(LuaObject luaObject) {
        return task(luaObject, (Object[]) null, (LuaObject) null, (LuaObject) null);
    }

    public LuaAsyncTask task(LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3) {
        return task(luaObject, (Object[]) null, luaObject2, luaObject3);
    }

    public LuaAsyncTask task(LuaObject luaObject, Object[] objArr) {
        return task(luaObject, objArr, (LuaObject) null, (LuaObject) null);
    }

    public LuaAsyncTask task(LuaObject luaObject, Object[] objArr, LuaObject luaObject2) {
        return task(luaObject, (Object[]) null, (LuaObject) null, luaObject2);
    }

    public LuaAsyncTask task(LuaObject luaObject, Object[] objArr, LuaObject luaObject2, LuaObject luaObject3) {
        LuaAsyncTask luaAsyncTask = new LuaAsyncTask(this, luaObject, luaObject2, luaObject3);
        luaAsyncTask.execute((Params[]) objArr);
        return luaAsyncTask;
    }

    public LuaThread thread(LuaObject luaObject) {
        LuaThread newThread = newThread(luaObject, (Object[]) null);
        newThread.start();
        return newThread;
    }

    public LuaThread thread(LuaObject luaObject, Object[] objArr) {
        LuaThread luaThread = new LuaThread((LuaContext) this, luaObject, true, objArr);
        luaThread.start();
        return luaThread;
    }

    public Ticker ticker(final LuaObject luaObject, long j2) {
        Ticker ticker = new Ticker();
        ticker.setOnTickListener(new Ticker.OnTickListener() {
            public void onTick() {
                try {
                    luaObject.call(new Object[0]);
                } catch (LuaException e) {
                    a.a(e);
                    LuaService.this.sendError("onTick", e);
                }
            }
        });
        ticker.setPeriod(j2);
        ticker.start();
        return ticker;
    }

    public LuaTimer timer(LuaObject luaObject, long j2) {
        return timer(luaObject, 0, j2, (Object[]) null);
    }

    public LuaTimer timer(LuaObject luaObject, long j2, long j3) {
        return timer(luaObject, j2, j3, (Object[]) null);
    }

    public LuaTimer timer(LuaObject luaObject, long j2, long j3, Object[] objArr) {
        LuaTimer luaTimer = new LuaTimer((LuaContext) this, luaObject, objArr);
        luaTimer.start(j2, j3);
        return luaTimer;
    }

    public LuaTimer timer(LuaObject luaObject, long j2, Object[] objArr) {
        return timer(luaObject, 0, j2, objArr);
    }
}
