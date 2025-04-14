package com.androlua;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.FileProvider;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayListAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.a.a.a.a.a.a.a;
import com.androlua.LuaBroadcastReceiver;
import com.androlua.LuaService;
import com.androlua.Ticker;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import dalvik.system.DexClassLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.lingala.zip4j.util.InternalZipConstants;

public class LuaActivity extends Activity implements LuaBroadcastReceiver.OnReceiveListener, LuaContext {
    private static String J;
    private static final HashMap<String, LuaActivity> K = new HashMap<>();
    private static ArrayList<String> a = new ArrayList<>();
    private String A;
    private LuaBroadcastReceiver B;
    private String C;
    private String D;
    private boolean E;
    /* access modifiers changed from: private */
    public boolean F = true;
    private LuaResources G;
    private ArrayList<LuaGcable> H = new ArrayList<>();
    private String I = "main";
    private LuaObject L;
    private String b;
    private Handler c;
    /* access modifiers changed from: private */
    public TextView d;
    private String e;
    private LuaDexLoader f;
    private int g;
    private int h;
    private ListView i;
    /* access modifiers changed from: private */
    public ArrayListAdapter<String> j;
    private LuaState k;
    private String l;
    private StringBuilder m = new StringBuilder();
    private Boolean n = false;
    private Toast o;
    private LinearLayout p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f21q;
    private long r;
    private Menu s;
    private LuaObject t;
    private LuaObject u;
    private LuaObject v;
    private LuaObject w;
    private String x;
    private String y;
    private String z;

    public class MainHandler extends Handler {
        public MainHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    String string = message.getData().getString("data");
                    if (LuaActivity.this.F) {
                        LuaActivity.this.showToast(string);
                    }
                    TextView b = LuaActivity.this.d;
                    b.append(string + "\n");
                    LuaActivity.this.j.add(string);
                    return;
                case 1:
                    Bundle data = message.getData();
                    LuaActivity.this.a(data.getString("data"), ((Object[]) data.getSerializable("args"))[0]);
                    return;
                case 2:
                    LuaActivity.this.runFunc(message.getData().getString("data"), new Object[0]);
                    return;
                case 3:
                    LuaActivity.this.runFunc(message.getData().getString("data"), (Object[]) message.getData().getSerializable("args"));
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

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r3 = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(r3.getName().substring(r0 + 1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.io.File r3) {
        /*
            r2 = this;
            java.lang.String r0 = r3.getName()
            r1 = 46
            int r0 = r0.lastIndexOf(r1)
            if (r0 < 0) goto L_0x0021
            java.lang.String r3 = r3.getName()
            int r0 = r0 + 1
            java.lang.String r3 = r3.substring(r0)
            android.webkit.MimeTypeMap r0 = android.webkit.MimeTypeMap.getSingleton()
            java.lang.String r3 = r0.getMimeTypeFromExtension(r3)
            if (r3 == 0) goto L_0x0021
            return r3
        L_0x0021:
            java.lang.String r3 = "application/octet-stream"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.a(java.io.File):java.lang.String");
    }

    private void a() {
        try {
            Field declaredField = ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedBridge").getDeclaredField("disableHooks");
            declaredField.setAccessible(true);
            declaredField.set((Object) null, true);
        } catch (Exception unused) {
        }
    }

    private void a(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            ((HashMap) declaredField.get(obj)).clear();
            LuaDialog luaDialog = new LuaDialog(this);
            luaDialog.setTitle("提示3");
            luaDialog.setMessage("你的手机运行环境不安全");
            luaDialog.setPosButton("确定");
            luaDialog.show();
        } catch (Exception e2) {
            a.a(e2);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, Object obj) {
        synchronized (this.k) {
            try {
                this.k.pushObjectValue(obj);
                this.k.setGlobal(str);
            } catch (LuaException e2) {
                sendError("setField", e2);
            }
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

    private void b() {
        try {
            Object newInstance = ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedHelpers").newInstance();
            if (newInstance != null) {
                a((Object) newInstance, "fieldCache");
                a((Object) newInstance, "methodCache");
                a((Object) newInstance, "constructorCache");
            }
        } catch (Exception unused) {
        }
    }

    private void c() {
        this.k = LuaStateFactory.newLuaState();
        this.k.openLibs();
        this.k.pushJavaObject(this);
        this.k.setGlobal("activity");
        this.k.getGlobal("activity");
        this.k.setGlobal("this");
        this.k.pushContext(this);
        this.k.getGlobal("luajava");
        this.k.pushString(this.A);
        this.k.setField(-2, "luaextdir");
        this.k.pushString(this.b);
        this.k.setField(-2, "luadir");
        this.k.pushString(this.l);
        this.k.setField(-2, "luapath");
        this.k.pop(1);
        d();
        new LuaPrint(this, this.k).register("print");
        this.k.getGlobal("package");
        this.k.pushString(this.C);
        this.k.setField(-2, "path");
        this.k.pushString(this.e);
        this.k.setField(-2, "cpath");
        this.k.pop(1);
        new JavaFunction(this.k) {
            public int execute() {
                ((LuaThread) this.b.toJavaObject(2)).set(this.b.toString(3), this.b.toJavaObject(4));
                return 0;
            }
        }.register("set");
        new JavaFunction(this.k) {
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

    private void d() {
        if (new File(this.b + "/init.lua").exists()) {
            try {
                LuaState luaState = this.k;
                int LloadFile = luaState.LloadFile(this.b + "/init.lua");
                if (LloadFile == 0) {
                    this.k.newTable();
                    LuaObject luaObject = this.k.getLuaObject(-1);
                    this.k.setUpValue(-2, 1);
                    int pcall = this.k.pcall(0, 0, 0);
                    if (pcall == 0) {
                        if (J == null) {
                            LuaObject field = luaObject.getField("app_key");
                            if (field.isString()) {
                                J = field.toString();
                                String luaObject2 = field.toString();
                            }
                            LuaObject field2 = luaObject.getField("app_channel");
                            if (field2.isString()) {
                                String luaObject3 = field2.toString();
                            }
                        }
                        LuaObject field3 = luaObject.getField("appname");
                        if (field3.isString()) {
                            setTitle(field3.getString());
                        }
                        LuaObject field4 = luaObject.getField("app_name");
                        if (field4.isString()) {
                            setTitle(field4.getString());
                        }
                        LuaObject field5 = luaObject.getField("debugmode");
                        if (field5.isBoolean()) {
                            this.F = field5.getBoolean();
                        }
                        LuaObject field6 = luaObject.getField("debug_mode");
                        if (field6.isBoolean()) {
                            this.F = field6.getBoolean();
                        }
                        LuaObject field7 = luaObject.getField("theme");
                        if (field7.isNumber()) {
                            setTheme((int) field7.getInteger());
                            return;
                        } else if (field7.isString()) {
                            setTheme(R.style.class.getField(field7.getString()).getInt((Object) null));
                            return;
                        } else {
                            return;
                        }
                    } else {
                        LloadFile = pcall;
                    }
                }
                throw new LuaException(a(LloadFile) + ": " + this.k.toString(-1));
            } catch (Exception e2) {
                sendMsg(e2.getMessage());
            }
        }
    }

    public static LuaActivity getActivity(String str) {
        return K.get(str);
    }

    public void assetsToSD(String str, String str2) {
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        InputStream open = getAssets().open(str);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = open.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                open.close();
                fileOutputStream.close();
                return;
            }
        }
    }

    public boolean bindService(int i2) {
        return bindService(new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                LuaActivity.this.runFunc("onServiceConnected", componentName, ((LuaService.LuaBinder) iBinder).getService());
            }

            public void onServiceDisconnected(ComponentName componentName) {
                LuaActivity.this.runFunc("onServiceDisconnected", componentName);
            }
        }, i2);
    }

    public boolean bindService(ServiceConnection serviceConnection, int i2) {
        Intent intent = new Intent(this, LuaService.class);
        intent.putExtra("luaDir", this.b);
        intent.putExtra("luaPath", this.l);
        return super.bindService(intent, serviceConnection, i2);
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

    public void createShortcut(String str, String str2) {
        String str3;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setClassName(getPackageName(), LuaActivity.class.getName());
        intent.setData(Uri.parse(str));
        if (Build.VERSION.SDK_INT >= 22) {
            intent.addFlags(524288);
            intent.addFlags(134217728);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                ((ShortcutManager) getSystemService("shortcut")).requestPinShortcut(new ShortcutInfo.Builder(this, str).setIcon(Icon.createWithResource(this, com.AndLua.LY.R.drawable.icon)).setShortLabel(str2).setIntent(intent).build(), (IntentSender) null);
            } catch (Exception e2) {
                a.a(e2);
                str3 = "添加快捷方式出错";
            }
        } else {
            Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            Intent.ShortcutIconResource fromContext = Intent.ShortcutIconResource.fromContext(this, com.AndLua.LY.R.drawable.icon);
            intent2.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
            intent2.putExtra("duplicate", 0);
            intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", fromContext);
            sendBroadcast(intent2);
            str3 = "已添加快捷方式";
            Toast.makeText(this, str3, 0).show();
        }
    }

    public void createShortcut(String str, String str2, String str3) {
        String str4;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setClassName(getPackageName(), LuaActivity.class.getName());
        intent.setData(Uri.parse(str));
        if (Build.VERSION.SDK_INT >= 22) {
            intent.addFlags(524288);
            intent.addFlags(134217728);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                ((ShortcutManager) getSystemService("shortcut")).requestPinShortcut(new ShortcutInfo.Builder(this, str).setIcon(Icon.createWithFilePath(str3)).setShortLabel(str2).setIntent(intent).build(), (IntentSender) null);
            } catch (Exception e2) {
                a.a(e2);
                str4 = "添加快捷方式出错";
            }
        } else {
            Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            intent2.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
            intent2.putExtra("duplicate", 0);
            intent2.putExtra("android.intent.extra.shortcut.ICON", BitmapFactory.decodeFile(str3));
            sendBroadcast(intent2);
            str4 = "已添加快捷方式";
            Toast.makeText(this, str4, 0).show();
        }
    }

    public Object doAsset(String str, Object... objArr) {
        int i2 = 0;
        try {
            byte[] readAsset = readAsset(str);
            this.k.setTop(0);
            int LloadBuffer = this.k.LloadBuffer(readAsset, str);
            if (LloadBuffer == 0) {
                try {
                    this.k.getGlobal("debug");
                    this.k.getField(-1, "traceback");
                    this.k.remove(-2);
                    this.k.insert(-2);
                    for (Object pushObjectValue : objArr) {
                        this.k.pushObjectValue(pushObjectValue);
                    }
                    i2 = this.k.pcall(r2, 0, -2 - r2);
                    if (i2 == 0) {
                        return this.k.toJavaObject(-1);
                    }
                } catch (Exception e2) {
                    e = e2;
                    i2 = LloadBuffer;
                    setTitle(a(i2));
                    setContentView(this.p);
                    sendMsg(e.getMessage());
                    return null;
                }
            } else {
                i2 = LloadBuffer;
            }
            throw new LuaException(a(i2) + ": " + this.k.toString(-1));
        } catch (Exception e3) {
            e = e3;
            setTitle(a(i2));
            setContentView(this.p);
            sendMsg(e.getMessage());
            return null;
        }
    }

    public Object doFile(String str) {
        return doFile(str, new Object[0]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0134  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doFile(java.lang.String r10, java.lang.Object[] r11) {
        /*
            r9 = this;
            r0 = 1
            r1 = 0
            r2 = -1
            char r3 = r10.charAt(r1)     // Catch:{ LuaException -> 0x00a5 }
            r4 = 47
            if (r3 == r4) goto L_0x0021
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ LuaException -> 0x00a5 }
            r3.<init>()     // Catch:{ LuaException -> 0x00a5 }
            java.lang.String r4 = r9.b     // Catch:{ LuaException -> 0x00a5 }
            r3.append(r4)     // Catch:{ LuaException -> 0x00a5 }
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch:{ LuaException -> 0x00a5 }
            r3.append(r10)     // Catch:{ LuaException -> 0x00a5 }
            java.lang.String r10 = r3.toString()     // Catch:{ LuaException -> 0x00a5 }
        L_0x0021:
            com.luajava.LuaState r3 = r9.k     // Catch:{ LuaException -> 0x00a5 }
            r3.setTop(r1)     // Catch:{ LuaException -> 0x00a5 }
            com.luajava.LuaState r3 = r9.k     // Catch:{ LuaException -> 0x00a5 }
            int r10 = r3.LloadFile(r10)     // Catch:{ LuaException -> 0x00a5 }
            if (r10 != 0) goto L_0x006e
            com.luajava.LuaState r3 = r9.k     // Catch:{ LuaException -> 0x006c }
            java.lang.String r4 = "debug"
            r3.getGlobal(r4)     // Catch:{ LuaException -> 0x006c }
            com.luajava.LuaState r3 = r9.k     // Catch:{ LuaException -> 0x006c }
            java.lang.String r4 = "traceback"
            r3.getField(r2, r4)     // Catch:{ LuaException -> 0x006c }
            com.luajava.LuaState r3 = r9.k     // Catch:{ LuaException -> 0x006c }
            r4 = -2
            r3.remove(r4)     // Catch:{ LuaException -> 0x006c }
            com.luajava.LuaState r3 = r9.k     // Catch:{ LuaException -> 0x006c }
            r3.insert(r4)     // Catch:{ LuaException -> 0x006c }
            int r3 = r11.length     // Catch:{ LuaException -> 0x006c }
            r5 = 0
        L_0x0049:
            if (r5 >= r3) goto L_0x0055
            com.luajava.LuaState r6 = r9.k     // Catch:{ LuaException -> 0x006c }
            r7 = r11[r5]     // Catch:{ LuaException -> 0x006c }
            r6.pushObjectValue(r7)     // Catch:{ LuaException -> 0x006c }
            int r5 = r5 + 1
            goto L_0x0049
        L_0x0055:
            com.luajava.LuaState r11 = r9.k     // Catch:{ LuaException -> 0x006c }
            int r4 = r4 - r3
            int r11 = r11.pcall(r3, r0, r4)     // Catch:{ LuaException -> 0x006c }
            if (r11 != 0) goto L_0x006a
            com.luajava.LuaState r10 = r9.k     // Catch:{ LuaException -> 0x0065 }
            java.lang.Object r10 = r10.toJavaObject(r2)     // Catch:{ LuaException -> 0x0065 }
            return r10
        L_0x0065:
            r10 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto L_0x00a8
        L_0x006a:
            r10 = r11
            goto L_0x006e
        L_0x006c:
            r11 = move-exception
            goto L_0x00a8
        L_0x006e:
            android.content.Intent r11 = new android.content.Intent     // Catch:{ LuaException -> 0x006c }
            r11.<init>()     // Catch:{ LuaException -> 0x006c }
            java.lang.String r3 = "data"
            com.luajava.LuaState r4 = r9.k     // Catch:{ LuaException -> 0x006c }
            java.lang.String r4 = r4.toString(r2)     // Catch:{ LuaException -> 0x006c }
            r11.putExtra(r3, r4)     // Catch:{ LuaException -> 0x006c }
            r9.setResult(r10, r11)     // Catch:{ LuaException -> 0x006c }
            com.luajava.LuaException r11 = new com.luajava.LuaException     // Catch:{ LuaException -> 0x006c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ LuaException -> 0x006c }
            r3.<init>()     // Catch:{ LuaException -> 0x006c }
            java.lang.String r4 = r9.a((int) r10)     // Catch:{ LuaException -> 0x006c }
            r3.append(r4)     // Catch:{ LuaException -> 0x006c }
            java.lang.String r4 = ": "
            r3.append(r4)     // Catch:{ LuaException -> 0x006c }
            com.luajava.LuaState r4 = r9.k     // Catch:{ LuaException -> 0x006c }
            java.lang.String r4 = r4.toString(r2)     // Catch:{ LuaException -> 0x006c }
            r3.append(r4)     // Catch:{ LuaException -> 0x006c }
            java.lang.String r3 = r3.toString()     // Catch:{ LuaException -> 0x006c }
            r11.<init>((java.lang.String) r3)     // Catch:{ LuaException -> 0x006c }
            throw r11     // Catch:{ LuaException -> 0x006c }
        L_0x00a5:
            r10 = move-exception
            r11 = r10
            r10 = 0
        L_0x00a8:
            java.lang.String r10 = r9.a((int) r10)
            r9.setTitle(r10)
            android.widget.LinearLayout r10 = r9.p
            r9.setContentView(r10)
            java.lang.String r10 = r11.getMessage()
            r9.sendMsg(r10)
            java.lang.String r10 = r11.getMessage()
            java.lang.String r11 = "android.permission."
            int r3 = r10.indexOf(r11)
            r4 = 0
            if (r3 <= 0) goto L_0x0134
            int r11 = r11.length()
            int r3 = r3 + r11
            java.lang.String r11 = "."
            int r11 = r10.indexOf(r11, r3)
            if (r11 <= r3) goto L_0x0134
            java.lang.String r10 = r10.substring(r3, r11)
            com.luajava.LuaState r11 = r9.k
            java.lang.String r3 = "require"
            r11.getGlobal(r3)
            com.luajava.LuaState r11 = r9.k
            java.lang.String r3 = "permission"
            r11.pushString((java.lang.String) r3)
            com.luajava.LuaState r11 = r9.k
            r11.pcall(r0, r1, r1)
            com.luajava.LuaState r11 = r9.k
            java.lang.String r0 = "permission_info"
            r11.getGlobal(r0)
            com.luajava.LuaState r11 = r9.k
            r11.getField(r2, r10)
            com.luajava.LuaState r11 = r9.k
            boolean r11 = r11.isString(r2)
            if (r11 == 0) goto L_0x011f
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r10)
            java.lang.String r10 = " ("
            r11.append(r10)
            com.luajava.LuaState r10 = r9.k
            java.lang.String r10 = r10.toString(r2)
            r11.append(r10)
            java.lang.String r10 = ")"
            r11.append(r10)
            java.lang.String r10 = r11.toString()
        L_0x011f:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "权限错误: "
            r11.append(r0)
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r9.sendMsg(r10)
            return r4
        L_0x0134:
            boolean r10 = r9.E
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.doFile(java.lang.String, java.lang.Object[]):java.lang.Object");
    }

    public Object doString(String str, Object... objArr) {
        try {
            this.k.setTop(0);
            int LloadString = this.k.LloadString(str);
            if (LloadString == 0) {
                this.k.getGlobal("debug");
                this.k.getField(-1, "traceback");
                this.k.remove(-2);
                this.k.insert(-2);
                for (Object pushObjectValue : objArr) {
                    this.k.pushObjectValue(pushObjectValue);
                }
                LloadString = this.k.pcall(r6, 1, -2 - r6);
                if (LloadString == 0) {
                    return this.k.toJavaObject(-1);
                }
            }
            throw new LuaException(a(LloadString) + ": " + this.k.toString(-1));
        } catch (LuaException e2) {
            sendMsg(e2.getMessage());
            return null;
        }
    }

    public void finish(boolean z2) {
        Intent intent;
        if (!z2) {
            super.finish();
        } else if (Build.VERSION.SDK_INT < 21 || (intent = getIntent()) == null || (intent.getFlags() & 524288) == 0) {
            super.finish();
        } else {
            finishAndRemoveTask();
        }
    }

    public Object get(String str) {
        Object javaObject;
        synchronized (this.k) {
            this.k.getGlobal(str);
            javaObject = this.k.toJavaObject(-1);
        }
        return javaObject;
    }

    public Object getArg(int i2) {
        Object[] objArr = (Object[]) getIntent().getSerializableExtra("arg");
        if (objArr == null || objArr.length >= i2) {
            return null;
        }
        return objArr[i2];
    }

    public AssetManager getAssets() {
        return (this.f == null || this.f.getAssets() == null) ? super.getAssets() : this.f.getAssets();
    }

    public ArrayList<ClassLoader> getClassLoaders() {
        return this.f.getClassLoaders();
    }

    public Context getContext() {
        return this;
    }

    public View getDecorView() {
        return getWindow().getDecorView();
    }

    public Map getGlobalData() {
        return ((LuaApplication) getApplication()).getGlobalData();
    }

    public int getHeight() {
        return this.h;
    }

    public HashMap<String, String> getLibrarys() {
        return this.f.getLibrarys();
    }

    public String getLocalDir() {
        return this.x;
    }

    public String getLuaCpath() {
        return this.e;
    }

    public String getLuaDir() {
        return this.b;
    }

    public String getLuaDir(String str) {
        File file = new File(this.b + InternalZipConstants.ZIP_FILE_SEPARATOR + str);
        if (file.exists() || file.mkdirs()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public String getLuaExtDir() {
        return this.A;
    }

    public String getLuaExtDir(String str) {
        File file = new File(getLuaExtDir(), str);
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
        return this.C;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0065, code lost:
        if (a.contains(r4.b) == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a2, code lost:
        if (a.contains(r4.b) == false) goto L_0x0067;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getLuaPath() {
        /*
            r4 = this;
            android.content.Intent r0 = r4.getIntent()
            android.net.Uri r0 = r0.getData()
            if (r0 != 0) goto L_0x000c
            r0 = 0
            return r0
        L_0x000c:
            java.lang.String r0 = r0.getPath()
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r1 = r1.exists()
            if (r1 != 0) goto L_0x002e
            java.io.File r1 = new java.io.File
            java.lang.String r2 = r4.getLuaPath(r0)
            r1.<init>(r2)
            boolean r1 = r1.exists()
            if (r1 == 0) goto L_0x002e
            java.lang.String r0 = r4.getLuaPath(r0)
        L_0x002e:
            r4.l = r0
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            java.io.File r2 = new java.io.File
            java.lang.String r3 = r4.l
            r2.<init>(r3)
            java.lang.String r2 = r2.getParent()
            r4.b = r2
            java.lang.String r1 = r1.getName()
            java.lang.String r2 = "main.lua"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x006f
            java.io.File r1 = new java.io.File
            java.lang.String r2 = r4.b
            java.lang.String r3 = "init.lua"
            r1.<init>(r2, r3)
            boolean r1 = r1.exists()
            if (r1 == 0) goto L_0x006f
            java.util.ArrayList<java.lang.String> r1 = a
            java.lang.String r2 = r4.b
            boolean r1 = r1.contains(r2)
            if (r1 != 0) goto L_0x00af
        L_0x0067:
            java.util.ArrayList<java.lang.String> r1 = a
            java.lang.String r2 = r4.b
            r1.add(r2)
            return r0
        L_0x006f:
            java.lang.String r1 = r4.b
        L_0x0071:
            if (r1 == 0) goto L_0x00af
            java.util.ArrayList<java.lang.String> r2 = a
            boolean r2 = r2.contains(r1)
            if (r2 == 0) goto L_0x007e
            r4.b = r1
            return r0
        L_0x007e:
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "main.lua"
            r2.<init>(r1, r3)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L_0x00a5
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "init.lua"
            r2.<init>(r1, r3)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L_0x00a5
            r4.b = r1
            java.util.ArrayList<java.lang.String> r1 = a
            java.lang.String r2 = r4.b
            boolean r1 = r1.contains(r2)
            if (r1 != 0) goto L_0x00af
            goto L_0x0067
        L_0x00a5:
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            java.lang.String r1 = r2.getParent()
            goto L_0x0071
        L_0x00af:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.getLuaPath():java.lang.String");
    }

    public String getLuaPath(String str) {
        return new File(getLuaDir(), str).getAbsolutePath();
    }

    public String getLuaPath(String str, String str2) {
        return new File(getLuaDir(str), str2).getAbsolutePath();
    }

    public LuaResources getLuaResources() {
        Resources resources = super.getResources();
        if (!(this.f == null || this.f.getResources() == null)) {
            resources = this.f.getResources();
        }
        this.G = new LuaResources(getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.G.setSuperResources(resources);
        return this.G;
    }

    public LuaState getLuaState() {
        return this.k;
    }

    public Menu getOptionsMenu() {
        return this.s;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        if (r1.equals("file") != false) goto L_0x0032;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getPathFromUri(android.net.Uri r8) {
        /*
            r7 = this;
            if (r8 == 0) goto L_0x0060
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r1 = "_data"
            r2 = 0
            r3[r2] = r1
            java.lang.String r1 = r8.getScheme()
            r4 = -1
            int r5 = r1.hashCode()
            r6 = 3143036(0x2ff57c, float:4.404332E-39)
            if (r5 == r6) goto L_0x0028
            r0 = 951530617(0x38b73479, float:8.735894E-5)
            if (r5 == r0) goto L_0x001e
            goto L_0x0031
        L_0x001e:
            java.lang.String r0 = "content"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0031
            r0 = 0
            goto L_0x0032
        L_0x0028:
            java.lang.String r2 = "file"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0031
            goto L_0x0032
        L_0x0031:
            r0 = -1
        L_0x0032:
            switch(r0) {
                case 0: goto L_0x003b;
                case 1: goto L_0x0036;
                default: goto L_0x0035;
            }
        L_0x0035:
            goto L_0x0060
        L_0x0036:
            java.lang.String r8 = r8.getPath()
            return r8
        L_0x003b:
            android.content.ContentResolver r1 = r7.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r8
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)
            if (r8 == 0) goto L_0x0060
            java.lang.String r0 = r7.getPackageName()
            int r0 = r8.getColumnIndexOrThrow(r0)
            if (r0 >= 0) goto L_0x0054
            goto L_0x0060
        L_0x0054:
            java.lang.String r0 = r8.getString(r0)
            r8.moveToFirst()
            r8.close()
            r8 = r0
            return r8
        L_0x0060:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.getPathFromUri(android.net.Uri):java.lang.String");
    }

    public String getQuery(String str) {
        Uri data = getIntent().getData();
        if (data == null) {
            return null;
        }
        return data.getQueryParameter(str);
    }

    public Resources getResources() {
        return (this.f == null || this.f.getResources() == null) ? this.G != null ? this.G : super.getResources() : this.f.getResources();
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

    public Uri getUriForFile(File file) {
        return FileProvider.getUriForFile(this, getPackageName(), file);
    }

    public Uri getUriForPath(String str) {
        return FileProvider.getUriForFile(this, getPackageName(), new File(str));
    }

    public int getWidth() {
        return this.g;
    }

    public void initMain() {
        a.add(getLocalDir());
    }

    public void installApk(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        File file = new File(str);
        intent.setFlags(1);
        intent.setDataAndType(getUriForFile(file), a(file));
        intent.addFlags(268435456);
        startActivity(Intent.createChooser(intent, file.getName()));
    }

    public DexClassLoader loadApp(String str) {
        return this.f.loadApp(str);
    }

    public Bitmap loadBitmap(String str) {
        return LuaBitmap.getBitmap(this, str);
    }

    public DexClassLoader loadDex(String str) {
        return this.f.loadDex(str);
    }

    public Object loadLib(String str) {
        int indexOf = str.indexOf(".");
        String substring = indexOf > 0 ? str.substring(0, indexOf) : str;
        if (!new File(this.z + "/lib" + substring + ".so").exists()) {
            if (!new File(this.b + "/lib" + substring + ".so").exists()) {
                throw new LuaException("can not find lib " + str);
            }
            LuaUtil.copyFile(this.b + "/lib" + substring + ".so", this.z + "/lib" + substring + ".so");
        }
        return this.k.getLuaObject("require").call(str);
    }

    public void loadResources(String str) {
        this.f.loadResources(str);
    }

    public void newActivity(int i2, String str) {
        newActivity(i2, str, (Object[]) null);
    }

    public void newActivity(int i2, String str, int i3, int i4) {
        newActivity(i2, str, i3, i4, (Object[]) null);
    }

    public void newActivity(int i2, String str, int i3, int i4, boolean z2) {
        newActivity(i2, str, i3, i4, (Object[]) null, z2);
    }

    public void newActivity(int i2, String str, int i3, int i4, Object[] objArr) {
        newActivity(i2, str, i3, i4, objArr, false);
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [java.lang.Object[], java.io.Serializable] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009d  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void newActivity(int r6, java.lang.String r7, int r8, int r9, java.lang.Object[] r10, boolean r11) {
        /*
            r5 = this;
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.androlua.LuaActivity> r1 = com.androlua.LuaActivity.class
            r0.<init>(r5, r1)
            if (r11 == 0) goto L_0x0010
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.androlua.LuaActivityX> r1 = com.androlua.LuaActivityX.class
            r0.<init>(r5, r1)
        L_0x0010:
            java.lang.String r1 = "name"
            r0.putExtra(r1, r7)
            r1 = 0
            char r1 = r7.charAt(r1)
            r2 = 47
            if (r1 == r2) goto L_0x0034
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r5.b
            r1.append(r2)
            java.lang.String r2 = "/"
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
        L_0x0034:
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            boolean r2 = r1.isDirectory()
            if (r2 == 0) goto L_0x006d
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r7)
            java.lang.String r4 = "/main.lua"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L_0x006d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = "/main.lua"
        L_0x0065:
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            goto L_0x008c
        L_0x006d:
            boolean r2 = r1.isDirectory()
            if (r2 != 0) goto L_0x0079
            boolean r1 = r1.exists()
            if (r1 != 0) goto L_0x008c
        L_0x0079:
            java.lang.String r1 = ".lua"
            boolean r1 = r7.endsWith(r1)
            if (r1 != 0) goto L_0x008c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = ".lua"
            goto L_0x0065
        L_0x008c:
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            boolean r1 = r1.exists()
            if (r1 != 0) goto L_0x009d
            java.io.FileNotFoundException r6 = new java.io.FileNotFoundException
            r6.<init>(r7)
            throw r6
        L_0x009d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "file://"
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            android.net.Uri r7 = android.net.Uri.parse(r7)
            r0.setData(r7)
            if (r11 == 0) goto L_0x00c7
            int r7 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r7 < r1) goto L_0x00c7
            r7 = 524288(0x80000, float:7.34684E-40)
            r0.addFlags(r7)
            r7 = 134217728(0x8000000, float:3.85186E-34)
            r0.addFlags(r7)
        L_0x00c7:
            if (r10 == 0) goto L_0x00ce
            java.lang.String r7 = "arg"
            r0.putExtra(r7, r10)
        L_0x00ce:
            if (r11 == 0) goto L_0x00d4
            r5.startActivity(r0)
            goto L_0x00d7
        L_0x00d4:
            r5.startActivityForResult(r0, r6)
        L_0x00d7:
            r5.overridePendingTransition(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.newActivity(int, java.lang.String, int, int, java.lang.Object[], boolean):void");
    }

    public void newActivity(int i2, String str, boolean z2) {
        newActivity(i2, str, (Object[]) null, z2);
    }

    public void newActivity(int i2, String str, Object[] objArr) {
        newActivity(i2, str, objArr, false);
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [java.lang.Object[], java.io.Serializable] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009d A[ADDED_TO_REGION] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void newActivity(int r6, java.lang.String r7, java.lang.Object[] r8, boolean r9) {
        /*
            r5 = this;
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.androlua.LuaActivity> r1 = com.androlua.LuaActivity.class
            r0.<init>(r5, r1)
            if (r9 == 0) goto L_0x0010
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.androlua.LuaActivityX> r1 = com.androlua.LuaActivityX.class
            r0.<init>(r5, r1)
        L_0x0010:
            java.lang.String r1 = "name"
            r0.putExtra(r1, r7)
            r1 = 0
            char r1 = r7.charAt(r1)
            r2 = 47
            if (r1 == r2) goto L_0x0034
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r5.b
            r1.append(r2)
            java.lang.String r2 = "/"
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
        L_0x0034:
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            boolean r2 = r1.isDirectory()
            if (r2 == 0) goto L_0x006d
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r7)
            java.lang.String r4 = "/main.lua"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L_0x006d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = "/main.lua"
        L_0x0065:
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            goto L_0x008c
        L_0x006d:
            boolean r2 = r1.isDirectory()
            if (r2 != 0) goto L_0x0079
            boolean r1 = r1.exists()
            if (r1 != 0) goto L_0x008c
        L_0x0079:
            java.lang.String r1 = ".lua"
            boolean r1 = r7.endsWith(r1)
            if (r1 != 0) goto L_0x008c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = ".lua"
            goto L_0x0065
        L_0x008c:
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            boolean r1 = r1.exists()
            if (r1 != 0) goto L_0x009d
            java.io.FileNotFoundException r6 = new java.io.FileNotFoundException
            r6.<init>(r7)
            throw r6
        L_0x009d:
            if (r9 == 0) goto L_0x00af
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 21
            if (r1 < r2) goto L_0x00af
            r1 = 524288(0x80000, float:7.34684E-40)
            r0.addFlags(r1)
            r1 = 134217728(0x8000000, float:3.85186E-34)
            r0.addFlags(r1)
        L_0x00af:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "file://"
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            android.net.Uri r7 = android.net.Uri.parse(r7)
            r0.setData(r7)
            if (r8 == 0) goto L_0x00ce
            java.lang.String r7 = "arg"
            r0.putExtra(r7, r8)
        L_0x00ce:
            if (r9 == 0) goto L_0x00d4
            r5.startActivity(r0)
            return
        L_0x00d4:
            r5.startActivityForResult(r0, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.newActivity(int, java.lang.String, java.lang.Object[], boolean):void");
    }

    public void newActivity(String str) {
        newActivity(1, str, (Object[]) null);
    }

    public void newActivity(String str, int i2, int i3) {
        newActivity(1, str, i2, i3, (Object[]) null);
    }

    public void newActivity(String str, int i2, int i3, boolean z2) {
        newActivity(1, str, i2, i3, (Object[]) null, z2);
    }

    public void newActivity(String str, int i2, int i3, Object[] objArr) {
        newActivity(1, str, i2, i3, objArr);
    }

    public void newActivity(String str, int i2, int i3, Object[] objArr, boolean z2) {
        newActivity(1, str, i2, i3, objArr, z2);
    }

    public void newActivity(String str, boolean z2) {
        newActivity(1, str, (Object[]) null, z2);
    }

    public void newActivity(String str, Object[] objArr) {
        newActivity(1, str, objArr);
    }

    public void newActivity(String str, Object[] objArr, boolean z2) {
        newActivity(1, str, objArr, z2);
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

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        String stringExtra;
        if (!(intent == null || (stringExtra = intent.getStringExtra("name")) == null)) {
            Object[] objArr = (Object[]) intent.getSerializableExtra("data");
            if (objArr == null) {
                runFunc("onResult", stringExtra);
            } else {
                Object[] objArr2 = new Object[(objArr.length + 1)];
                objArr2[0] = stringExtra;
                int i4 = 0;
                while (i4 < objArr.length) {
                    int i5 = i4 + 1;
                    objArr2[i5] = objArr[i4];
                    i4 = i5;
                }
                Object runFunc = runFunc("onResult", objArr2);
                if (runFunc != null && runFunc.getClass() == Boolean.class && ((Boolean) runFunc).booleanValue()) {
                    return;
                }
            }
        }
        runFunc("onActivityResult", Integer.valueOf(i2), Integer.valueOf(i3), intent);
        super.onActivityResult(i2, i3, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.g = displayMetrics.widthPixels;
        this.h = displayMetrics.heightPixels;
        runFunc("onConfigurationChanged", configuration);
    }

    public void onContentChanged() {
        super.onContentChanged();
        this.f21q = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        runFunc("onContextItemSelected", menuItem);
        return super.onContextItemSelected(menuItem);
    }

    public void onCreate(Bundle bundle) {
        setTheme(16974064);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        super.onCreate((Bundle) null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.g = displayMetrics.widthPixels;
        this.h = displayMetrics.heightPixels;
        this.p = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);
        int i2 = 1;
        scrollView.setFillViewport(true);
        this.d = new TextView(this);
        this.d.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        scrollView.addView(this.d, new ViewGroup.LayoutParams(-1, -2));
        this.d.setText("");
        this.d.setTextIsSelectable(true);
        this.i = new ListView(this);
        this.i.setFastScrollEnabled(true);
        this.j = new ArrayListAdapter<String>(this, 17367043) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView textView = (TextView) super.getView(i, view, viewGroup);
                if (view == null) {
                    textView.setTextIsSelectable(true);
                }
                return textView;
            }
        };
        this.i.setAdapter(this.j);
        this.p.addView(this.i, new ViewGroup.LayoutParams(-1, -2));
        LuaApplication luaApplication = (LuaApplication) getApplication();
        if (luaApplication.getClass() != LuaApplication.class) {
            do {
            } while (luaApplication.getClass() != LuaApplication.class);
        }
        this.x = luaApplication.getLocalDir();
        this.y = luaApplication.getOdexDir();
        this.z = luaApplication.getLibDir();
        this.D = luaApplication.getMdDir();
        this.e = luaApplication.getLuaCpath();
        this.b = this.x;
        this.C = luaApplication.getLuaLpath();
        this.A = luaApplication.getLuaExtDir();
        this.c = new MainHandler();
        try {
            this.d.setText("");
            this.j.clear();
            Object[] objArr = (Object[]) getIntent().getSerializableExtra("arg");
            if (objArr == null) {
                objArr = new Object[0];
            }
            this.l = getLuaPath();
            this.I = new File(this.l).getName();
            int lastIndexOf = this.I.lastIndexOf(".");
            if (lastIndexOf > 0) {
                this.I = this.I.substring(0, lastIndexOf);
            }
            this.C = this.b + "/?.lua;" + this.b + "/lua/?.lua;" + this.b + "/?/init.lua;" + this.C;
            c();
            this.f = new LuaDexLoader(this);
            this.f.loadLibs();
            K.put(this.I, this);
            doFile(this.l, objArr);
            this.n = true;
            if (!this.I.equals("main")) {
                runFunc("main", objArr);
            }
            runFunc(this.I, objArr);
            runFunc("onCreate", bundle);
            if (!this.f21q) {
                TypedArray obtainStyledAttributes = getTheme().obtainStyledAttributes(new int[]{16842801, 16842806, 16843599});
                int color = obtainStyledAttributes.getColor(0, 16711935);
                int color2 = obtainStyledAttributes.getColor(1, 16711935);
                obtainStyledAttributes.recycle();
                this.d.setTextColor(color2);
                this.p.setBackgroundColor(color);
                setContentView(this.p);
            }
            this.L = this.k.getLuaObject("onKeyShortcut");
            if (this.L.isNil()) {
                this.L = null;
            }
            this.t = this.k.getLuaObject("onKeyDown");
            if (this.t.isNil()) {
                this.t = null;
            }
            this.u = this.k.getLuaObject("onKeyUp");
            if (this.u.isNil()) {
                this.u = null;
            }
            this.v = this.k.getLuaObject("onKeyLongPress");
            if (this.v.isNil()) {
                this.v = null;
            }
            this.w = this.k.getLuaObject("onTouchEvent");
            if (this.w.isNil()) {
                this.w = null;
            }
            LuaObject luaObject = this.k.getLuaObject("onAccessibilityEvent");
            if (luaObject.isFunction()) {
                LuaAccessibilityService.onAccessibilityEvent = luaObject.getFunction();
            }
            a();
            b();
            try {
                throw new RuntimeException("");
            } catch (Exception e2) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                a.a((Throwable) e2, new PrintStream(byteArrayOutputStream));
                String[] split = byteArrayOutputStream.toString().split("\n");
                while (i2 < split.length) {
                    String str = split[i2];
                    if (str.contains("com.androlua") || str.contains("com.nirenr.talkman") || str.contains("android.app") || str.contains("android.os") || str.contains("java.lang") || str.contains("com.android")) {
                        i2++;
                    } else {
                        runFunc("onHook", new Object[0]);
                        return;
                    }
                }
            }
        } catch (Exception e3) {
            sendMsg(e3.getMessage());
            setContentView(this.p);
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        runFunc("onCreateContextMenu", contextMenu, view, contextMenuInfo);
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.s = menu;
        runFunc("onCreateOptionsMenu", menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.B != null) {
            unregisterReceiver(this.B);
        }
        Iterator<LuaGcable> it = this.H.iterator();
        while (it.hasNext()) {
            it.next().gc();
        }
        K.remove(this.I);
        runFunc("onDestroy_VM", new Object[0]);
        super.onDestroy();
        System.gc();
        this.k.gc(2, 1);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.t != null) {
            try {
                Object call = this.t.call(Integer.valueOf(i2), keyEvent);
                if (call != null && call.getClass() == Boolean.class && ((Boolean) call).booleanValue()) {
                    return true;
                }
            } catch (LuaException e2) {
                sendError("onKeyDown", e2);
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        if (this.v != null) {
            try {
                Object call = this.v.call(Integer.valueOf(i2), keyEvent);
                if (call != null && call.getClass() == Boolean.class && ((Boolean) call).booleanValue()) {
                    return true;
                }
            } catch (LuaException e2) {
                sendError("onKeyLongPress", e2);
            }
        }
        return super.onKeyLongPress(i2, keyEvent);
    }

    public boolean onKeyShortcut(int i2, KeyEvent keyEvent) {
        if (this.L != null) {
            try {
                Object call = this.L.call(Integer.valueOf(i2), keyEvent);
                if (call != null && call.getClass() == Boolean.class && ((Boolean) call).booleanValue()) {
                    return true;
                }
            } catch (LuaException e2) {
                sendError("onKeyShortcut", e2);
            }
        }
        return super.onKeyShortcut(i2, keyEvent);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.u != null) {
            try {
                Object call = this.u.call(Integer.valueOf(i2), keyEvent);
                if (call != null && call.getClass() == Boolean.class && ((Boolean) call).booleanValue()) {
                    return true;
                }
            } catch (LuaException e2) {
                sendError("onKeyUp", e2);
            }
        }
        return super.onKeyUp(i2, keyEvent);
    }

    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (!menuItem.hasSubMenu()) {
            runFunc("onMenuItemSelected", Integer.valueOf(i2), menuItem);
        }
        return super.onMenuItemSelected(i2, menuItem);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Object obj;
        if (!menuItem.hasSubMenu()) {
            obj = runFunc("onOptionsItemSelected", menuItem);
        } else {
            obj = null;
        }
        if (obj == null || obj.getClass() != Boolean.class || !((Boolean) obj).booleanValue()) {
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        runFunc("onPause", new Object[0]);
    }

    public void onReceive(Context context, Intent intent) {
        runFunc("onReceive", context, intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        runFunc("onResume_VM", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        runFunc("onStart", new Object[0]);
        String str = this.I;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        runFunc("onStop_VM", new Object[0]);
        String str = this.I;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.w != null) {
            try {
                Object call = this.w.call(motionEvent);
                if (call != null && call.getClass() == Boolean.class && ((Boolean) call).booleanValue()) {
                    return true;
                }
            } catch (LuaException e2) {
                sendError("onTouchEvent", e2);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void push(int i2, String str) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        message.setData(bundle);
        message.what = i2;
        this.c.sendMessage(message);
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
            android.os.Handler r4 = r3.c
            r4.sendMessage(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.push(int, java.lang.String, java.lang.Object[]):void");
    }

    public byte[] readAsset(String str) {
        InputStream open = getAssets().open(str);
        byte[] a2 = a(open);
        open.close();
        return a2;
    }

    public void regGc(LuaGcable luaGcable) {
        this.H.add(luaGcable);
    }

    public Intent registerReceiver(IntentFilter intentFilter) {
        if (this.B != null) {
            unregisterReceiver(this.B);
        }
        this.B = new LuaBroadcastReceiver(this);
        return super.registerReceiver(this.B, intentFilter);
    }

    public Intent registerReceiver(LuaBroadcastReceiver.OnReceiveListener onReceiveListener, IntentFilter intentFilter) {
        return super.registerReceiver(new LuaBroadcastReceiver(onReceiveListener), intentFilter);
    }

    public Intent registerReceiver(LuaBroadcastReceiver luaBroadcastReceiver, IntentFilter intentFilter) {
        return super.registerReceiver(luaBroadcastReceiver, intentFilter);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void result(java.lang.Object[] r5) {
        /*
            r4 = this;
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r1 = "name"
            android.content.Intent r2 = r4.getIntent()
            java.lang.String r3 = "name"
            java.lang.String r2 = r2.getStringExtra(r3)
            r0.putExtra(r1, r2)
            java.lang.String r1 = "data"
            r0.putExtra(r1, r5)
            r5 = 0
            r4.setResult(r5, r0)
            r4.finish()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.result(java.lang.Object[]):void");
    }

    public Object runFunc(String str, Object... objArr) {
        if (this.k == null) {
            return null;
        }
        synchronized (this.k) {
            try {
                this.k.setTop(0);
                this.k.pushGlobalTable();
                this.k.pushString(str);
                this.k.rawGet(-2);
                if (this.k.isFunction(-1)) {
                    this.k.getGlobal("debug");
                    this.k.getField(-1, "traceback");
                    this.k.remove(-2);
                    this.k.insert(-2);
                    for (Object pushObjectValue : objArr) {
                        this.k.pushObjectValue(pushObjectValue);
                    }
                    int pcall = this.k.pcall(r1, 1, -2 - r1);
                    if (pcall == 0) {
                        Object javaObject = this.k.toJavaObject(-1);
                        return javaObject;
                    }
                    throw new LuaException(a(pcall) + ": " + this.k.toString(-1));
                }
            } catch (LuaException e2) {
                sendError(str, e2);
                return null;
            }
        }
    }

    public void sendError(String str, Exception exc) {
        Object runFunc = runFunc("onError", str, exc);
        if (runFunc == null || runFunc.getClass() != Boolean.class || !((Boolean) runFunc).booleanValue()) {
            sendMsg(str + ": " + exc.getMessage());
        }
    }

    public void sendMsg(String str) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", str);
        message.setData(bundle);
        message.what = 0;
        this.c.sendMessage(message);
        Log.i("lua", str);
    }

    public void set(String str, Object obj) {
        push(1, str, new Object[]{obj});
    }

    public void setContentView(LuaObject luaObject) {
        setContentView(luaObject, (LuaObject) null);
    }

    public void setContentView(LuaObject luaObject, LuaObject luaObject2) {
        Object[] objArr;
        LuaObject luaObject3 = this.k.getLuaObject("loadlayout");
        if (luaObject.isString()) {
            objArr = new Object[]{luaObject.getString(), luaObject2};
        } else if (luaObject.isTable()) {
            objArr = new Object[]{luaObject, luaObject2};
        } else {
            throw new LuaException("layout may be table or string.");
        }
        super.setContentView((View) luaObject3.call(objArr));
    }

    public void setContentView(String str) {
        setContentView(str, (LuaObject) null);
    }

    public void setContentView(String str, LuaObject luaObject) {
        super.setContentView((View) this.k.getLuaObject("loadlayout").call(str, luaObject));
    }

    public void setDebug(boolean z2) {
        this.F = z2;
    }

    public void setFragment(Fragment fragment) {
        this.f21q = true;
        getFragmentManager().beginTransaction().replace(16908290, fragment).commit();
    }

    public void setLuaDir(String str) {
        this.b = str;
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
            r7.A = r8
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
            r7.A = r4
        L_0x0048:
            int r3 = r3 + 1
            goto L_0x002e
        L_0x004b:
            java.lang.String r0 = r7.A
            if (r0 != 0) goto L_0x0058
            java.io.File r8 = r7.getDir(r8, r2)
            java.lang.String r8 = r8.getAbsolutePath()
            goto L_0x001d
        L_0x0058:
            java.io.File r8 = new java.io.File
            java.lang.String r0 = r7.A
            r8.<init>(r0)
            boolean r0 = r8.exists()
            if (r0 != 0) goto L_0x0068
            r8.mkdirs()
        L_0x0068:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.setLuaExtDir(java.lang.String):void");
    }

    public boolean setSharedData(String str, Object obj) {
        return LuaApplication.getInstance().setSharedData(str, obj);
    }

    public void setTitle(CharSequence charSequence) {
        ActivityManager.TaskDescription taskDescription;
        super.setTitle(charSequence);
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                taskDescription = new ActivityManager.TaskDescription(charSequence.toString(), loadBitmap(getLuaPath("icon.png")));
            } catch (IOException e2) {
                a.a(e2);
                taskDescription = new ActivityManager.TaskDescription(charSequence.toString());
            }
            setTaskDescription(taskDescription);
        }
    }

    public void shareFile(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        File file = new File(str);
        intent.setType(a(file));
        intent.setFlags(1);
        intent.putExtra("android.intent.extra.STREAM", getUriForFile(file));
        startActivity(Intent.createChooser(intent, file.getName()));
    }

    @SuppressLint({"ShowToast"})
    public void showToast(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.o == null || currentTimeMillis - this.r > 1000) {
            this.m.setLength(0);
            this.o = Toast.makeText(this, str, 1);
            this.m.append(str);
            this.o.show();
        } else {
            this.m.append("\n");
            this.m.append(str);
            this.o.setText(this.m.toString());
            this.o.setDuration(1);
        }
        this.r = currentTimeMillis;
    }

    public ComponentName startService() {
        return startService((String) null, (Object[]) null);
    }

    public ComponentName startService(String str) {
        return startService(str, (Object[]) null);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.content.ComponentName startService(java.lang.String r4, java.lang.Object[] r5) {
        /*
            r3 = this;
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.androlua.LuaService> r1 = com.androlua.LuaService.class
            r0.<init>(r3, r1)
            java.lang.String r1 = "luaDir"
            java.lang.String r2 = r3.b
            r0.putExtra(r1, r2)
            java.lang.String r1 = "luaPath"
            java.lang.String r2 = r3.l
            r0.putExtra(r1, r2)
            if (r4 == 0) goto L_0x0053
            r1 = 0
            char r1 = r4.charAt(r1)
            r2 = 47
            if (r1 == r2) goto L_0x0048
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "file://"
            r1.append(r2)
            java.lang.String r2 = r3.b
            r1.append(r2)
            java.lang.String r2 = "/"
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = ".lua"
        L_0x0039:
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            android.net.Uri r4 = android.net.Uri.parse(r4)
            r0.setData(r4)
            goto L_0x0053
        L_0x0048:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "file://"
            r1.append(r2)
            goto L_0x0039
        L_0x0053:
            if (r5 == 0) goto L_0x005a
            java.lang.String r4 = "arg"
            r0.putExtra(r4, r5)
        L_0x005a:
            android.content.ComponentName r4 = super.startService(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaActivity.startService(java.lang.String, java.lang.Object[]):android.content.ComponentName");
    }

    public ComponentName startService(Object[] objArr) {
        return startService((String) null, objArr);
    }

    public boolean stopService() {
        return stopService(new Intent(this, LuaService.class));
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

    public long test(String str, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i3 = 0; i3 < i2; i3++) {
            this.k.LdoString(str);
        }
        return System.currentTimeMillis() - currentTimeMillis;
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
                    LuaActivity.this.sendError("onTick", e);
                }
            }
        });
        ticker.start();
        ticker.setPeriod(j2);
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

    public void unZipAssets(String str, String str2) {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            ZipInputStream zipInputStream = new ZipInputStream(getAssets().open(str));
            byte[] bArr = new byte[4096];
            for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                if (nextEntry.isDirectory()) {
                    new File(str2 + File.separator + nextEntry.getName()).mkdir();
                } else {
                    File file2 = new File(str2 + File.separator + nextEntry.getName());
                    file2.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                }
            }
            zipInputStream.close();
        } catch (IOException unused) {
        }
    }
}
