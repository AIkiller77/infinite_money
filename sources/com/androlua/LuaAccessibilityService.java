package com.androlua;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.hardware.display.VirtualDisplay;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.a.a.a.a.a.a.a;
import com.androlua.util.ClickRunnable;
import com.androlua.util.GlobalActionAutomator;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaTable;
import com.nirenr.Point;
import com.nirenr.screencapture.ScreenCaptureListener;
import com.nirenr.screencapture.ScreenShot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressLint({"NewApi"})
public class LuaAccessibilityService extends AccessibilityService {
    private static AccessibilityServiceCallbacks a;
    private static LuaAccessibilityService d;
    public static LuaFunction onAccessibilityEvent;
    private LuaApplication b;
    private Map c;
    /* access modifiers changed from: private */
    public HashMap<String, ComponentName> e = new HashMap<>();
    private boolean f;
    private Handler g;
    private GlobalActionAutomator h;
    private ScreenShot i;
    private int j;
    private int k;
    private int l;

    public interface AccessibilityServiceCallbacks {
        void onAccessibilityEvent(LuaAccessibilityService luaAccessibilityService, AccessibilityEvent accessibilityEvent);

        void onConfigurationChanged(LuaAccessibilityService luaAccessibilityService, Configuration configuration);

        void onCreate(LuaAccessibilityService luaAccessibilityService);

        void onDestroy(LuaAccessibilityService luaAccessibilityService);

        void onInterrupt(LuaAccessibilityService luaAccessibilityService);

        boolean onKeyEvent(LuaAccessibilityService luaAccessibilityService, KeyEvent keyEvent);

        void onServiceConnected(LuaAccessibilityService luaAccessibilityService);
    }

    private AccessibilityNodeInfo a(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return null;
        }
        if (isListView2(accessibilityNodeInfo)) {
            return accessibilityNodeInfo;
        }
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            AccessibilityNodeInfo a2 = a(accessibilityNodeInfo.getChild(i2));
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    private void a() {
        WindowManager windowManager = (WindowManager) getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            this.j = displayMetrics.densityDpi;
            this.k = displayMetrics.widthPixels;
            this.l = displayMetrics.heightPixels;
        }
    }

    private void a(AccessibilityNodeInfo accessibilityNodeInfo, ArrayList<String> arrayList) {
        if (accessibilityNodeInfo != null) {
            getNodeInfoText(accessibilityNodeInfo);
            int childCount = accessibilityNodeInfo.getChildCount();
            if (childCount > 0) {
                for (int i2 = 0; i2 < childCount; i2++) {
                    a(accessibilityNodeInfo.getChild(i2), arrayList);
                }
            }
        }
    }

    private void a(AccessibilityNodeInfo accessibilityNodeInfo, ArrayList<String> arrayList, AccessibilityNodeInfo accessibilityNodeInfo2) {
        if (accessibilityNodeInfo != null) {
            if (!this.f) {
                this.f = accessibilityNodeInfo.equals(accessibilityNodeInfo2);
            }
            String nodeInfoText = getNodeInfoText(accessibilityNodeInfo);
            if (this.f && nodeInfoText != null) {
                arrayList.add(nodeInfoText.toString());
            }
            int childCount = accessibilityNodeInfo.getChildCount();
            if (childCount > 0) {
                for (int i2 = 0; i2 < childCount; i2++) {
                    AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i2);
                    if (child != null) {
                        if (!this.f) {
                            this.f = child.equals(accessibilityNodeInfo2);
                        }
                        a(child, arrayList, accessibilityNodeInfo2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, LuaException luaException) {
    }

    private void a(List<AccessibilityNodeInfo> list, AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        if (accessibilityNodeInfo != null) {
            String[] split = str.split("\\|");
            CharSequence[] charSequenceArr = {accessibilityNodeInfo.getContentDescription(), accessibilityNodeInfo.getText()};
            for (String str2 : split) {
                boolean z = !str2.startsWith("*");
                boolean z2 = !str2.endsWith("*");
                if (!z) {
                    str2 = str2.substring(1);
                }
                if (!z2) {
                    str2 = str2.substring(0, str2.length() - 1);
                }
                int length = charSequenceArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    CharSequence charSequence = charSequenceArr[i2];
                    if (charSequence != null) {
                        String trim = charSequence.toString().trim();
                        if (!z || !z2) {
                            if (z) {
                                if (trim.startsWith(str2)) {
                                    break;
                                }
                            } else if (z2) {
                                if (trim.endsWith(str2)) {
                                    break;
                                }
                            } else if (trim.contains(str2)) {
                                break;
                            }
                        } else if (str2.equals(trim)) {
                            break;
                        }
                    }
                    i2++;
                }
                list.add(accessibilityNodeInfo);
            }
            int childCount = accessibilityNodeInfo.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                a(list, accessibilityNodeInfo.getChild(i3), str);
            }
        }
    }

    private AccessibilityNodeInfo b(AccessibilityNodeInfo accessibilityNodeInfo) {
        AccessibilityNodeInfo accessibilityNodeInfo2 = accessibilityNodeInfo;
        while (accessibilityNodeInfo2 != null) {
            try {
                if (isClickable(accessibilityNodeInfo2)) {
                    return accessibilityNodeInfo2;
                }
                accessibilityNodeInfo2 = accessibilityNodeInfo2.getParent();
            } catch (Exception e2) {
                a.a(e2);
            }
        }
        return accessibilityNodeInfo;
    }

    private void b() {
        new AsyncTask<String, String, HashMap<String, ComponentName>>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public HashMap<String, ComponentName> doInBackground(String... strArr) {
                HashMap<String, ComponentName> hashMap = new HashMap<>();
                PackageManager packageManager = LuaAccessibilityService.this.getPackageManager();
                Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
                intent.addCategory("android.intent.category.DEFAULT");
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
                Collections.sort(queryIntentActivities, new ResolveInfo.DisplayNameComparator(packageManager));
                int size = queryIntentActivities.size();
                for (int i = 0; i < size; i++) {
                    ResolveInfo resolveInfo = queryIntentActivities.get(i);
                    CharSequence loadLabel = resolveInfo.loadLabel(packageManager);
                    hashMap.put(loadLabel.toString().toLowerCase(), new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
                }
                Intent intent2 = new Intent("android.intent.action.MAIN", (Uri) null);
                intent2.addCategory("android.intent.category.LAUNCHER");
                List<ResolveInfo> queryIntentActivities2 = packageManager.queryIntentActivities(intent2, 0);
                Collections.sort(queryIntentActivities2, new ResolveInfo.DisplayNameComparator(packageManager));
                int size2 = queryIntentActivities2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    ResolveInfo resolveInfo2 = queryIntentActivities2.get(i2);
                    CharSequence loadLabel2 = resolveInfo2.loadLabel(packageManager);
                    hashMap.put(loadLabel2.toString().toLowerCase(), new ComponentName(resolveInfo2.activityInfo.applicationInfo.packageName, resolveInfo2.activityInfo.name));
                }
                return hashMap;
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(HashMap<String, ComponentName> hashMap) {
                super.onPostExecute(hashMap);
                if (hashMap != null && !hashMap.isEmpty()) {
                    HashMap unused = LuaAccessibilityService.this.e = hashMap;
                }
            }
        }.execute(new String[]{""});
    }

    private void c() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        Collections.sort(queryIntentActivities, new ResolveInfo.DisplayNameComparator(packageManager));
        if (queryIntentActivities != null) {
            int size = queryIntentActivities.size();
            for (int i2 = 0; i2 < size; i2++) {
                ResolveInfo resolveInfo = queryIntentActivities.get(i2);
                CharSequence loadLabel = resolveInfo.loadLabel(packageManager);
                this.e.put(loadLabel.toString().toLowerCase(), new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            }
        }
    }

    public static LuaAccessibilityService getInstance() {
        return d;
    }

    public static void setCallback(AccessibilityServiceCallbacks accessibilityServiceCallbacks) {
        a = accessibilityServiceCallbacks;
    }

    public boolean appendCopy() {
        return appendCopy(getText(getFocusView()));
    }

    public boolean appendCopy(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
        CharSequence text = clipboardManager.getText();
        String str = "";
        if (text != null) {
            str = text.toString();
        }
        if (str.length() > 1) {
            str = str + "\n";
        }
        clipboardManager.setPrimaryClip(ClipData.newPlainText("label", str + charSequence));
        return true;
    }

    public boolean click(int i2, int i3) {
        if (this.h != null) {
            return this.h.click(i2, i3);
        }
        return false;
    }

    public boolean click(LuaTable luaTable) {
        return new ClickRunnable(this, luaTable).canClick();
    }

    public boolean click(LuaTable luaTable, final LuaFunction luaFunction) {
        return new ClickRunnable(this, luaTable).canClick(new ClickRunnable.ClickCallback() {
            public void onDone(boolean z, LuaTable luaTable, String str, int i) {
                try {
                    luaFunction.call(Boolean.valueOf(z), luaTable, str, Integer.valueOf(i));
                } catch (LuaException e) {
                    a.a(e);
                    LuaAccessibilityService.this.a("click", e);
                }
            }
        });
    }

    public boolean click(Point point) {
        return click(point.x, point.y);
    }

    public boolean click(String str, String str2, int[] iArr) {
        AccessibilityNodeInfo rootInActiveWindow;
        if (str == null || str2 == null || !str.equals(getAppName(getFocusView())) || (rootInActiveWindow = getRootInActiveWindow()) == null || rootInActiveWindow.findAccessibilityNodeInfosByText(str2).isEmpty()) {
            return false;
        }
        AccessibilityNodeInfo accessibilityNodeInfo = rootInActiveWindow;
        for (int i2 : iArr) {
            if (accessibilityNodeInfo.getChildCount() <= i2 || (accessibilityNodeInfo = accessibilityNodeInfo.getChild(i2)) == null) {
                return false;
            }
        }
        return toClick(accessibilityNodeInfo);
    }

    public boolean copy() {
        return copy(getText(getFocusView()));
    }

    public boolean copy(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", charSequence.toString()));
        return true;
    }

    public boolean deleteApp(String str) {
        this.e.clear();
        c();
        ComponentName componentName = this.e.get(str.toLowerCase());
        if (componentName == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + componentName.getPackageName()));
        intent.setFlags(270532608);
        startActivity(intent);
        return true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean execute(java.lang.String r4, android.view.accessibility.AccessibilityNodeInfo r5) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = 1
            r2 = 0
            switch(r0) {
                case -1762910153: goto L_0x00a2;
                case 727753: goto L_0x0098;
                case 904469: goto L_0x008e;
                case 915554: goto L_0x0083;
                case 1024924: goto L_0x0079;
                case 1163658: goto L_0x006e;
                case 1211754: goto L_0x0063;
                case 20002657: goto L_0x0058;
                case 36429412: goto L_0x004d;
                case 647728589: goto L_0x0043;
                case 661385651: goto L_0x0038;
                case 661386612: goto L_0x002d;
                case 697331565: goto L_0x0022;
                case 821558167: goto L_0x0017;
                case 1119180876: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x00ad
        L_0x000b:
            java.lang.String r0 = "追加复制"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 8
            goto L_0x00ae
        L_0x0017:
            java.lang.String r0 = "最近任务"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 5
            goto L_0x00ae
        L_0x0022:
            java.lang.String r0 = "增加进度"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 3
            goto L_0x00ae
        L_0x002d:
            java.lang.String r0 = "向下翻页"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 1
            goto L_0x00ae
        L_0x0038:
            java.lang.String r0 = "向上翻页"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 0
            goto L_0x00ae
        L_0x0043:
            java.lang.String r0 = "减少进度"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 2
            goto L_0x00ae
        L_0x004d:
            java.lang.String r0 = "通知栏"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 13
            goto L_0x00ae
        L_0x0058:
            java.lang.String r0 = "主屏幕"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 9
            goto L_0x00ae
        L_0x0063:
            java.lang.String r0 = "长按"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 12
            goto L_0x00ae
        L_0x006e:
            java.lang.String r0 = "返回"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 10
            goto L_0x00ae
        L_0x0079:
            java.lang.String r0 = "粘贴"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 4
            goto L_0x00ae
        L_0x0083:
            java.lang.String r0 = "点击"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 11
            goto L_0x00ae
        L_0x008e:
            java.lang.String r0 = "清空"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 6
            goto L_0x00ae
        L_0x0098:
            java.lang.String r0 = "复制"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 7
            goto L_0x00ae
        L_0x00a2:
            java.lang.String r0 = "打开通知栏"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00ad
            r4 = 14
            goto L_0x00ae
        L_0x00ad:
            r4 = -1
        L_0x00ae:
            switch(r4) {
                case 0: goto L_0x0106;
                case 1: goto L_0x00f6;
                case 2: goto L_0x00f1;
                case 3: goto L_0x00ec;
                case 4: goto L_0x00e8;
                case 5: goto L_0x00e4;
                case 6: goto L_0x00d6;
                case 7: goto L_0x00ce;
                case 8: goto L_0x00c6;
                case 9: goto L_0x00c2;
                case 10: goto L_0x00be;
                case 11: goto L_0x00ba;
                case 12: goto L_0x00b6;
                case 13: goto L_0x00b2;
                case 14: goto L_0x00b2;
                default: goto L_0x00b1;
            }
        L_0x00b1:
            return r2
        L_0x00b2:
            r3.toNotifications()
            return r1
        L_0x00b6:
            r3.toLongClick(r5)
            return r1
        L_0x00ba:
            r3.toClick(r5)
            return r1
        L_0x00be:
            r3.toBack()
            return r1
        L_0x00c2:
            r3.toHome()
            return r1
        L_0x00c6:
            java.lang.String r4 = r3.getText(r5)
            r3.appendCopy(r4)
            return r1
        L_0x00ce:
            java.lang.String r4 = r3.getText(r5)
            r3.copy(r4)
            return r1
        L_0x00d6:
            int r4 = android.os.Build.VERSION.SDK_INT
            r0 = 21
            if (r4 < r0) goto L_0x00e3
            r4 = 2097152(0x200000, float:2.938736E-39)
            boolean r4 = r5.performAction(r4)
            return r4
        L_0x00e3:
            return r2
        L_0x00e4:
            r3.toRecents()
            return r1
        L_0x00e8:
            r3.paste((android.view.accessibility.AccessibilityNodeInfo) r5)
            return r1
        L_0x00ec:
            boolean r4 = r3.scrollForward(r5)
            return r4
        L_0x00f1:
            boolean r4 = r3.scrollBackward(r5)
            return r4
        L_0x00f6:
            android.view.accessibility.AccessibilityNodeInfo r4 = r3.getRootInActiveWindow()
            android.view.accessibility.AccessibilityNodeInfo r4 = r3.a(r4)
            if (r4 != 0) goto L_0x0101
            return r2
        L_0x0101:
            boolean r4 = r3.scrollForward(r4)
            return r4
        L_0x0106:
            android.view.accessibility.AccessibilityNodeInfo r4 = r3.getRootInActiveWindow()
            android.view.accessibility.AccessibilityNodeInfo r4 = r3.a(r4)
            if (r4 != 0) goto L_0x0111
            return r2
        L_0x0111:
            boolean r4 = r3.scrollBackward(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaAccessibilityService.execute(java.lang.String, android.view.accessibility.AccessibilityNodeInfo):boolean");
    }

    public AccessibilityNodeInfo findAccessibilityNodeInfo(String str) {
        int lastIndexOf = str.lastIndexOf("@");
        if (lastIndexOf > 0) {
            if (!str.substring(lastIndexOf + 1).equals(getAppName(getFocusView()))) {
                return null;
            }
            str = str.substring(0, lastIndexOf);
        }
        int lastIndexOf2 = str.lastIndexOf("#");
        int i2 = -1;
        if (lastIndexOf2 > 0) {
            try {
                i2 = Integer.valueOf(str.substring(lastIndexOf2 + 1)).intValue();
            } catch (Exception unused) {
            }
            str = str.substring(0, lastIndexOf2);
        }
        char charAt = str.charAt(0);
        if (charAt != '>') {
            if (charAt == '@') {
                return findAccessibilityNodeInfoById(str.substring(1), i2);
            }
            switch (charAt) {
                case '$':
                    return findAccessibilityNodeInfoByIndex(str.substring(1));
                case '%':
                    if (execute(str.substring(1), getFocusView())) {
                        return AccessibilityNodeInfo.obtain();
                    }
                    return null;
                default:
                    return findAccessibilityNodeInfoByText(str, i2);
            }
        } else if (startApp(str.substring(1))) {
            return AccessibilityNodeInfo.obtain();
        } else {
            return null;
        }
    }

    public AccessibilityNodeInfo findAccessibilityNodeInfoById(String str, int i2) {
        int size;
        List findAccessibilityNodeInfoById = findAccessibilityNodeInfoById(str);
        if (findAccessibilityNodeInfoById.isEmpty() || i2 + 1 > (size = findAccessibilityNodeInfoById.size()) || 0 - i2 > size) {
            return null;
        }
        return (AccessibilityNodeInfo) (i2 < 0 ? findAccessibilityNodeInfoById.get(findAccessibilityNodeInfoById.size() + i2) : findAccessibilityNodeInfoById.get(i2));
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfoById(String str) {
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        return rootInActiveWindow == null ? new ArrayList() : rootInActiveWindow.findAccessibilityNodeInfosByText(str);
    }

    public AccessibilityNodeInfo findAccessibilityNodeInfoByIndex(String str) {
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (rootInActiveWindow == null) {
            return null;
        }
        String[] split = str.split("-");
        int length = split.length;
        int i2 = 0;
        while (i2 < length) {
            try {
                int intValue = Integer.valueOf(split[i2]).intValue();
                if (rootInActiveWindow.getChildCount() <= intValue || (rootInActiveWindow = rootInActiveWindow.getChild(intValue)) == null) {
                    return null;
                }
                i2++;
            } catch (Exception e2) {
                a.a(e2);
                return null;
            }
        }
        return rootInActiveWindow;
    }

    public AccessibilityNodeInfo findAccessibilityNodeInfoByText(String str, int i2) {
        int size;
        List findAccessibilityNodeInfoByText = findAccessibilityNodeInfoByText(str);
        if (findAccessibilityNodeInfoByText.isEmpty() || i2 + 1 > (size = findAccessibilityNodeInfoByText.size()) || 0 - i2 > size) {
            return null;
        }
        return (AccessibilityNodeInfo) (i2 < 0 ? findAccessibilityNodeInfoByText.get(findAccessibilityNodeInfoByText.size() + i2) : findAccessibilityNodeInfoByText.get(i2));
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfoByText(String str) {
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        ArrayList arrayList = new ArrayList();
        if (rootInActiveWindow == null) {
            return arrayList;
        }
        for (String str2 : str.split("\\|")) {
            if (!str2.isEmpty()) {
                if (str2.charAt(0) != '%') {
                    int lastIndexOf = str2.lastIndexOf(38);
                    if (lastIndexOf > 0) {
                        if (findAccessibilityNodeInfo(str2.substring(lastIndexOf + 1)) != null) {
                            str2 = str2.substring(0, lastIndexOf);
                        }
                    }
                    boolean z = !str2.startsWith("*");
                    boolean z2 = !str2.endsWith("*");
                    if (!z) {
                        str2 = str2.substring(1);
                    }
                    if (!z2) {
                        str2 = str2.substring(0, str2.length() - 1);
                    }
                    for (AccessibilityNodeInfo next : rootInActiveWindow.findAccessibilityNodeInfosByText(str2)) {
                        String trim = (next.getText() + "").trim();
                        String trim2 = (next.getContentDescription() + "").trim();
                        if (!z || !z2) {
                            if (z) {
                                if (!trim.startsWith(str2) && !trim2.startsWith(str2)) {
                                }
                            } else if (z2) {
                                if (!trim.endsWith(str2) && !trim2.endsWith(str2)) {
                                }
                            } else if (!trim.contains(str2) && !trim2.contains(str2)) {
                            }
                        } else if (!str2.equals(trim) && !str2.equals(trim2)) {
                        }
                        arrayList.add(next);
                    }
                } else {
                    execute(str2.substring(1), getFocusView());
                    return arrayList;
                }
            }
        }
        if (arrayList.isEmpty()) {
            a((List<AccessibilityNodeInfo>) arrayList, rootInActiveWindow, str);
        }
        return arrayList;
    }

    public boolean findClick(String[] strArr) {
        for (String findAccessibilityNodeInfoByText : strArr) {
            AccessibilityNodeInfo findAccessibilityNodeInfoByText2 = findAccessibilityNodeInfoByText(findAccessibilityNodeInfoByText, 0);
            if (findAccessibilityNodeInfoByText2 != null && b(findAccessibilityNodeInfoByText2).performAction(16)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<AccessibilityNodeInfo> getAllEditTextList() {
        ArrayList<AccessibilityNodeInfo> arrayList = new ArrayList<>();
        getEditText(getRootInActiveWindow(), arrayList);
        return arrayList;
    }

    public String getAllText(int i2) {
        ArrayList<String> allTextList = getAllTextList();
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = allTextList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.length() > i2) {
                sb.append(next);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public ArrayList<String> getAllTextList() {
        ArrayList<String> arrayList = new ArrayList<>();
        a(getRootInActiveWindow(), arrayList);
        return arrayList;
    }

    public ArrayList<String> getAllTextList(AccessibilityNodeInfo accessibilityNodeInfo) {
        ArrayList<String> arrayList = new ArrayList<>();
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        this.f = !accessibilityNodeInfo.isVisibleToUser();
        a(rootInActiveWindow, arrayList, accessibilityNodeInfo);
        return arrayList;
    }

    public String getAppName(AccessibilityNodeInfo accessibilityNodeInfo) {
        CharSequence packageName;
        if (accessibilityNodeInfo == null || (packageName = accessibilityNodeInfo.getPackageName()) == null) {
            return "";
        }
        String charSequence = packageName.toString();
        PackageManager packageManager = getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(charSequence, 0)).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            a.a(e2);
            return "";
        }
    }

    public int getDensity() {
        if (this.j == 0) {
            a();
        }
        return this.j;
    }

    public AccessibilityNodeInfo getEditText() {
        ArrayList<AccessibilityNodeInfo> allEditTextList = getAllEditTextList();
        if (allEditTextList.isEmpty()) {
            return null;
        }
        AccessibilityNodeInfo accessibilityNodeInfo = allEditTextList.get(0);
        if (accessibilityNodeInfo != null) {
            accessibilityNodeInfo.performAction(64);
        }
        return accessibilityNodeInfo;
    }

    public void getEditText(AccessibilityNodeInfo accessibilityNodeInfo, ArrayList<AccessibilityNodeInfo> arrayList) {
        if (accessibilityNodeInfo != null) {
            if (accessibilityNodeInfo.isEditable()) {
                arrayList.add(accessibilityNodeInfo);
            }
            int childCount = accessibilityNodeInfo.getChildCount();
            if (childCount > 0) {
                for (int i2 = 0; i2 < childCount; i2++) {
                    getEditText(accessibilityNodeInfo.getChild(i2), arrayList);
                }
            }
        }
    }

    public AccessibilityNodeInfo getFocusView() {
        return getRootInActiveWindow();
    }

    public Handler getHandler() {
        return this.g;
    }

    public int getHeight() {
        if (this.l == 0) {
            a();
        }
        return this.l;
    }

    public String getNodeInfoText(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return null;
        }
        CharSequence contentDescription = accessibilityNodeInfo.getContentDescription();
        CharSequence text = accessibilityNodeInfo.getText();
        String charSequence = contentDescription != null ? contentDescription.toString() : null;
        if (charSequence != null && charSequence.trim().length() > 0 && (!accessibilityNodeInfo.isEditable() || text == null)) {
            return charSequence;
        }
        if (text == null || text.length() <= 0) {
            return null;
        }
        return text.toString();
    }

    public Bitmap getScreenshot() {
        if (this.i != null) {
            return this.i.getScreenShot();
        }
        return null;
    }

    public void getScreenshot(final LuaFunction luaFunction) {
        ScreenShot.getScreenCaptureBitmap(this, new ScreenCaptureListener() {
            public void onScreenCaptureDone(Bitmap bitmap) {
                try {
                    luaFunction.call(bitmap);
                } catch (LuaException e) {
                    a.a(e);
                }
            }

            public void onScreenCaptureError(String str) {
                try {
                    luaFunction.call(null, str);
                } catch (LuaException e) {
                    a.a(e);
                }
            }
        });
    }

    public String getText(AccessibilityNodeInfo accessibilityNodeInfo) {
        return getNodeInfoText(accessibilityNodeInfo);
    }

    public int getWidth() {
        if (this.k == 0) {
            a();
        }
        return this.k;
    }

    public boolean insert(AccessibilityNodeInfo accessibilityNodeInfo, CharSequence charSequence) {
        if (!(accessibilityNodeInfo == null || charSequence == null || !accessibilityNodeInfo.isEditable())) {
            if (!accessibilityNodeInfo.isFocused()) {
                accessibilityNodeInfo.performAction(1);
            }
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
            clipboardManager.setPrimaryClip(ClipData.newPlainText("label", charSequence));
            clipboardManager.setText(charSequence);
            if (accessibilityNodeInfo.performAction(32768)) {
                return true;
            }
        }
        return false;
    }

    public boolean installApp(String str) {
        if (str == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=" + str));
        intent.setFlags(270532608);
        try {
            startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isClickable(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        if (accessibilityNodeInfo.isClickable() || accessibilityNodeInfo.isCheckable()) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (accessibilityNodeInfo.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK)) {
                return true;
            }
        } else if ((accessibilityNodeInfo.getActions() & 16) != 0) {
            return true;
        }
        return false;
    }

    public boolean isListView2(AccessibilityNodeInfo accessibilityNodeInfo) {
        CharSequence className;
        if (!(accessibilityNodeInfo == null || (className = accessibilityNodeInfo.getClassName()) == null)) {
            String charSequence = className.toString();
            char c2 = 65535;
            switch (charSequence.hashCode()) {
                case -1433025002:
                    if (charSequence.equals("android.widget.GridView")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -1154346071:
                    if (charSequence.equals("android.widget.AdapterView")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1102376577:
                    if (charSequence.equals("com.tencent.widget.GridView")) {
                        c2 = 9;
                        break;
                    }
                    break;
                case -703660929:
                    if (charSequence.equals("android.support.v7.widget.RecyclerView")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -438596061:
                    if (charSequence.equals("flyme.support.v7.widget.RecyclerView")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case -405438610:
                    if (charSequence.equals("android.widget.ListView")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 841510749:
                    if (charSequence.equals("android.widget.ScrollView")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 1799238850:
                    if (charSequence.equals("android.widget.ExpandableListView")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 1928354017:
                    if (charSequence.equals("android.widget.HorizontalScrollView")) {
                        c2 = 8;
                        break;
                    }
                    break;
                case 1977625610:
                    if (charSequence.equals("android.widget.AbsListView")) {
                        c2 = 3;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    break;
                default:
                    return charSequence.endsWith("ScrollView") || charSequence.endsWith("GridView") || charSequence.endsWith("RecyclerView") || charSequence.endsWith("ListView");
            }
        }
    }

    public boolean longClick(int i2, int i3) {
        if (this.h != null) {
            return this.h.longClick(i2, i3);
        }
        return false;
    }

    public boolean longClick(Point point) {
        return longClick(point.x, point.y);
    }

    public ClickRunnable loopClick(final LuaTable luaTable) {
        ClickRunnable clickRunnable = new ClickRunnable(this, luaTable);
        clickRunnable.canClick(new ClickRunnable.ClickCallback() {
            public void onDone(boolean z, LuaTable luaTable, String str, int i) {
                LuaAccessibilityService.this.loopClick(luaTable);
            }
        });
        return clickRunnable;
    }

    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        LuaTable luaTable;
        if (a != null) {
            a.onAccessibilityEvent(this, accessibilityEvent);
        }
        if (onAccessibilityEvent != null) {
            try {
                onAccessibilityEvent.call(accessibilityEvent);
            } catch (LuaException e2) {
                Log.i("lua", "onAccessibilityEvent: " + e2.toString());
            }
        } else if (this.c.containsKey("LuaAccessibilityService") && (luaTable = (LuaTable) this.c.get("LuaAccessibilityService")) != null) {
            try {
                ((LuaFunction) luaTable.get("onAccessibilityEvent")).call(accessibilityEvent);
            } catch (LuaException e3) {
                LuaFunction luaFunction = (LuaFunction) luaTable.get("onError");
                if (luaFunction == null) {
                    Log.i("onAccessibilityEvent", e3.getMessage());
                    return;
                }
                try {
                    luaFunction.call(e3);
                } catch (LuaException unused) {
                    Log.i("onAccessibilityEvent", e3.getMessage());
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (a != null) {
            a.onConfigurationChanged(this, configuration);
        }
    }

    public void onCreate() {
        LuaTable luaTable;
        Log.i("lua", "onCreate");
        super.onCreate();
        this.g = new Handler();
        d = this;
        if (Build.VERSION.SDK_INT >= 24) {
            this.h = new GlobalActionAutomator(this, new Handler());
            this.h.setService(this);
        }
        if (a != null) {
            a.onCreate(this);
        }
        b();
        this.b = (LuaApplication) getApplication();
        this.c = this.b.getGlobalData();
        if (this.c.containsKey("LuaAccessibilityService") && (luaTable = (LuaTable) this.c.get("LuaAccessibilityService")) != null) {
            try {
                ((LuaFunction) luaTable.get("onCreate")).call(this);
            } catch (LuaException e2) {
                LuaFunction luaFunction = (LuaFunction) luaTable.get("onError");
                if (luaFunction == null) {
                    Log.i("onCreate", e2.getMessage());
                    return;
                }
                try {
                    luaFunction.call(e2);
                } catch (LuaException unused) {
                    Log.i("onCreate", e2.getMessage());
                }
            }
        }
    }

    public void onDestroy() {
        if (a != null) {
            a.onDestroy(this);
        }
        stopScreenshot();
        super.onDestroy();
    }

    public void onInterrupt() {
        if (a != null) {
            a.onInterrupt(this);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onKeyEvent(KeyEvent keyEvent) {
        if (a == null || !a.onKeyEvent(this, keyEvent)) {
            return super.onKeyEvent(keyEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onServiceConnected() {
        LuaTable luaTable;
        Log.i("lua", "onServiceConnected");
        super.onServiceConnected();
        if (a != null) {
            a.onServiceConnected(this);
        }
        if (this.c.containsKey("LuaAccessibilityService") && (luaTable = (LuaTable) this.c.get("LuaAccessibilityService")) != null) {
            try {
                ((LuaFunction) luaTable.get("onServiceConnected")).call(this);
            } catch (LuaException e2) {
                LuaFunction luaFunction = (LuaFunction) luaTable.get("onError");
                if (luaFunction == null) {
                    Log.i("onServiceConnected", e2.getMessage());
                    return;
                }
                try {
                    luaFunction.call(e2);
                } catch (LuaException unused) {
                    Log.i("onServiceConnected", e2.getMessage());
                }
            }
        }
    }

    public boolean paste() {
        return paste(getFocusView(), ((ClipboardManager) getSystemService("clipboard")).getText());
    }

    public boolean paste(AccessibilityNodeInfo accessibilityNodeInfo) {
        return paste(accessibilityNodeInfo, ((ClipboardManager) getSystemService("clipboard")).getText());
    }

    public boolean paste(AccessibilityNodeInfo accessibilityNodeInfo, CharSequence charSequence) {
        if (accessibilityNodeInfo == null || charSequence == null) {
            return false;
        }
        if (accessibilityNodeInfo.isEditable()) {
            if (!accessibilityNodeInfo.isFocused()) {
                accessibilityNodeInfo.performAction(1);
            }
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
            clipboardManager.setPrimaryClip(ClipData.newPlainText("label", charSequence));
            clipboardManager.setText(charSequence);
            if (accessibilityNodeInfo.performAction(32768)) {
                return true;
            }
        }
        return paste(charSequence);
    }

    public boolean paste(CharSequence charSequence) {
        AccessibilityNodeInfo editText;
        if (charSequence == null || (editText = getEditText()) == null) {
            return false;
        }
        if (getFocusView().isEditable() && getFocusView().getText() != null) {
            charSequence = getFocusView().getText().toString() + charSequence;
        }
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putCharSequence(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, charSequence);
        return editText.performAction(2097152, bundle);
    }

    public void postClick(long j2, final LuaTable luaTable) {
        this.g.postDelayed(new Runnable() {
            public void run() {
                LuaAccessibilityService.this.click(luaTable);
            }
        }, j2);
    }

    public void postClick(long j2, final LuaTable luaTable, final LuaFunction luaFunction) {
        this.g.postDelayed(new Runnable() {
            public void run() {
                LuaAccessibilityService.this.click(luaTable, luaFunction);
            }
        }, j2);
    }

    public void postExecute(long j2, final String str, final AccessibilityNodeInfo accessibilityNodeInfo) {
        this.g.postDelayed(new Runnable() {
            public void run() {
                LuaAccessibilityService.this.execute(str, accessibilityNodeInfo);
            }
        }, j2);
    }

    public void postExecute(long j2, final String str, final AccessibilityNodeInfo accessibilityNodeInfo, final LuaFunction luaFunction) {
        this.g.postDelayed(new Runnable() {
            public void run() {
                try {
                    luaFunction.call(Boolean.valueOf(LuaAccessibilityService.this.execute(str, accessibilityNodeInfo)), str, accessibilityNodeInfo);
                } catch (LuaException e) {
                    a.a(e);
                    LuaAccessibilityService.this.a("postExecute", e);
                }
            }
        }, j2);
    }

    public boolean press(int i2, int i3, int i4) {
        if (this.h != null) {
            return this.h.press(i2, i3, i4);
        }
        return false;
    }

    public boolean press(Point point, int i2) {
        return press(point.x, point.y, i2);
    }

    public boolean scrollBackward(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21) {
            if ((accessibilityNodeInfo.getActions() & 8192) == 0) {
                return false;
            }
        } else if (!accessibilityNodeInfo.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD)) {
            return false;
        }
        return accessibilityNodeInfo.performAction(8192);
    }

    public boolean scrollForward(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21) {
            if ((accessibilityNodeInfo.getActions() & 4096) == 0) {
                return false;
            }
        } else if (!accessibilityNodeInfo.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD)) {
            return false;
        }
        return accessibilityNodeInfo.performAction(4096);
    }

    public boolean setText(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        if (accessibilityNodeInfo == null || !accessibilityNodeInfo.isEditable()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21) {
            return paste(accessibilityNodeInfo, str);
        }
        Bundle bundle = new Bundle();
        bundle.putCharSequence(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, str);
        return accessibilityNodeInfo.performAction(2097152, bundle);
    }

    public boolean setText(String str) {
        return setText(getEditText(), str);
    }

    public boolean startApp(String str) {
        b();
        ComponentName componentName = this.e.get(str.toLowerCase());
        if (componentName == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setComponent(componentName);
        intent.setFlags(270532608);
        try {
            startActivity(intent);
            return true;
        } catch (Exception e2) {
            a.a(e2);
            return false;
        }
    }

    public void startScreenshot() {
        this.i = new ScreenShot(this, (VirtualDisplay.Callback) null);
    }

    public void startScreenshot(VirtualDisplay.Callback callback) {
        this.i = new ScreenShot(this, callback);
    }

    public void stopScreenshot() {
        if (this.i != null) {
            this.i.release();
        }
        this.i = null;
    }

    public boolean swipe(int i2, int i3, int i4, int i5, int i6) {
        if (this.h != null) {
            return this.h.swipe(i2, i3, i4, i5, i6);
        }
        return false;
    }

    public boolean swipe(Path path, int i2) {
        if (this.h != null) {
            return this.h.gesture(0, (long) i2, path);
        }
        return false;
    }

    public boolean swipe(Point point, Point point2, int i2) {
        return swipe(point.x, point.y, point2.x, point2.y, i2);
    }

    public void toBack() {
        performGlobalAction(1);
    }

    public boolean toClick(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        try {
            return accessibilityNodeInfo.performAction(16);
        } catch (Exception e2) {
            a.a(e2);
            return false;
        }
    }

    public void toClick2(AccessibilityNodeInfo accessibilityNodeInfo) {
        toClick(b(accessibilityNodeInfo));
    }

    public void toHome() {
        performGlobalAction(2);
    }

    public boolean toLongClick(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        try {
            return accessibilityNodeInfo.performAction(32);
        } catch (Exception e2) {
            a.a(e2);
            return false;
        }
    }

    public void toNotifications() {
        performGlobalAction(4);
    }

    public void toRecents() {
        performGlobalAction(3);
    }
}
