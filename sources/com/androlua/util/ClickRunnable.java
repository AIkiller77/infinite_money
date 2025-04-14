package com.androlua.util;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import com.androlua.LuaAccessibilityService;
import com.luajava.LuaTable;

public class ClickRunnable implements Runnable {
    private final LuaAccessibilityService a;
    private LuaTable b;
    private int c = 1;
    private int d = -1;
    private int e = -1;
    private ClickCallback f;
    private boolean g = false;
    /* access modifiers changed from: private */
    public ClickRunnable h;

    public interface ClickCallback {
        void onDone(boolean z, LuaTable luaTable, String str, int i);
    }

    public ClickRunnable(LuaAccessibilityService luaAccessibilityService, LuaTable luaTable) {
        this.a = luaAccessibilityService;
        this.b = luaTable;
    }

    private boolean a(String str) {
        if (str == null) {
            return false;
        }
        int lastIndexOf = str.lastIndexOf("$");
        long j = 1000;
        if (lastIndexOf > 0) {
            try {
                j = Long.valueOf(str.substring(lastIndexOf + 1)).longValue();
            } catch (Exception unused) {
            }
            str = str.substring(0, lastIndexOf);
        }
        int lastIndexOf2 = str.lastIndexOf(">");
        if (lastIndexOf2 > 0) {
            if (this.d < 0) {
                try {
                    this.d = Integer.valueOf(str.substring(lastIndexOf2 + 1)).intValue();
                } catch (Exception unused2) {
                    this.d = -1;
                }
            }
            str = str.substring(0, lastIndexOf2);
        }
        int lastIndexOf3 = str.lastIndexOf("<");
        if (lastIndexOf3 > 0) {
            if (this.e < 0) {
                try {
                    this.e = Integer.valueOf(str.substring(lastIndexOf3 + 1)).intValue();
                } catch (Exception unused3) {
                    this.e = -1;
                }
            }
            str = str.substring(0, lastIndexOf3);
        }
        this.e--;
        this.d--;
        AccessibilityNodeInfo findAccessibilityNodeInfo = this.a.findAccessibilityNodeInfo(str);
        Log.i("lua", "findAccessibilityNodeInfo " + str + "," + this.d + "," + this.e + "," + findAccessibilityNodeInfo);
        if (findAccessibilityNodeInfo != null) {
            this.d = -1;
            this.a.toClick2(findAccessibilityNodeInfo);
        } else if (this.d <= 0 && this.e <= 0) {
            if (this.f != null) {
                this.f.onDone(true, this.b, str, this.c);
            }
            return false;
        }
        this.a.getHandler().postDelayed(this, j);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
        r3 = (java.lang.String) r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean canClick() {
        /*
            r7 = this;
            com.luajava.LuaTable r0 = r7.b
            int r0 = r0.length()
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            com.luajava.LuaTable r0 = r7.b
            int r0 = r0.length()
            r2 = 0
        L_0x0011:
            r3 = -1
            r4 = 0
            if (r2 >= r0) goto L_0x0076
            boolean r5 = r7.g
            if (r5 == 0) goto L_0x0025
            com.androlua.util.ClickRunnable$ClickCallback r0 = r7.f
            if (r0 == 0) goto L_0x0024
            com.androlua.util.ClickRunnable$ClickCallback r0 = r7.f
            com.luajava.LuaTable r2 = r7.b
            r0.onDone(r1, r2, r4, r3)
        L_0x0024:
            return r1
        L_0x0025:
            com.luajava.LuaTable r3 = r7.b
            int r4 = r2 + 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            java.lang.Object r3 = r3.get(r5)
            boolean r5 = r3 instanceof com.luajava.LuaTable
            r6 = 1
            if (r5 == 0) goto L_0x0055
            com.luajava.LuaTable r3 = (com.luajava.LuaTable) r3
            int r2 = r3.length()
            if (r2 != 0) goto L_0x003f
            goto L_0x0074
        L_0x003f:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            java.lang.Object r2 = r3.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L_0x004c
            goto L_0x0074
        L_0x004c:
            boolean r2 = r7.a(r2)
            if (r2 == 0) goto L_0x0074
            r7.b = r3
            return r6
        L_0x0055:
            boolean r5 = r3 instanceof java.lang.String
            if (r5 == 0) goto L_0x0074
            java.lang.String r3 = (java.lang.String) r3
            com.androlua.LuaAccessibilityService r5 = r7.a
            android.view.accessibility.AccessibilityNodeInfo r5 = r5.findAccessibilityNodeInfo(r3)
            if (r5 == 0) goto L_0x0074
            com.androlua.LuaAccessibilityService r0 = r7.a
            r0.toClick2(r5)
            com.androlua.util.ClickRunnable$ClickCallback r0 = r7.f
            if (r0 == 0) goto L_0x0073
            com.androlua.util.ClickRunnable$ClickCallback r0 = r7.f
            com.luajava.LuaTable r1 = r7.b
            r0.onDone(r6, r1, r3, r2)
        L_0x0073:
            return r6
        L_0x0074:
            r2 = r4
            goto L_0x0011
        L_0x0076:
            com.androlua.util.ClickRunnable$ClickCallback r0 = r7.f
            if (r0 == 0) goto L_0x0081
            com.androlua.util.ClickRunnable$ClickCallback r0 = r7.f
            com.luajava.LuaTable r2 = r7.b
            r0.onDone(r1, r2, r4, r3)
        L_0x0081:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.util.ClickRunnable.canClick():boolean");
    }

    public boolean canClick(ClickCallback clickCallback) {
        this.f = clickCallback;
        return canClick();
    }

    public void cancel() {
        this.g = true;
        if (this.h != null) {
            this.h.cancel();
        }
    }

    public void run() {
        boolean z = false;
        if (!this.g) {
            if (this.d < 0 && this.e < 0) {
                this.c++;
            }
            Object obj = this.b.get(Integer.valueOf(this.c));
            if (obj == null) {
                if (this.f != null) {
                    ClickCallback clickCallback = this.f;
                    if (this.c == this.b.length()) {
                        z = true;
                    }
                    clickCallback.onDone(z, this.b, (String) null, this.c);
                }
            } else if (obj instanceof LuaTable) {
                LuaTable luaTable = (LuaTable) obj;
                if (luaTable.length() != 0) {
                    this.h = new ClickRunnable(this.a, luaTable);
                    this.h.canClick(new ClickCallback() {
                        public void onDone(boolean z, LuaTable luaTable, String str, int i) {
                            ClickRunnable unused = ClickRunnable.this.h = null;
                            ClickRunnable.this.run();
                        }
                    });
                }
            } else if (obj instanceof String) {
                a((String) obj);
            }
        } else if (this.f != null) {
            this.f.onDone(false, this.b, (String) null, -1);
        }
    }
}
