package com.b.a.a;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.ClipboardManager;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.method.CharacterPickerDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Scroller;
import com.b.a.b.b;
import com.b.a.b.d;
import com.b.a.b.e;
import com.b.a.b.f;
import com.b.a.b.h;
import com.b.a.b.i;
import com.b.a.b.k;
import com.b.a.b.m;
import com.b.a.b.n;
import com.b.a.b.q;
import com.tencent.qq.widget.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c extends View implements e.a {
    public static final int SCROLL_DOWN = 1;
    public static final int SCROLL_LEFT = 2;
    public static final int SCROLL_RIGHT = 3;
    public static final int SCROLL_UP = 0;
    protected static float a = 0.75f;
    protected static float b = 0.5f;
    protected static int c = 4;
    protected static int d = 16;
    protected static long e = 250;
    private static SparseArray<String> u = new SparseArray<>();
    /* access modifiers changed from: private */
    public int A = 0;
    private Paint B;
    private int C = 0;
    private int D = 0;
    private boolean E = false;
    /* access modifiers changed from: private */
    public b F;
    private ClipboardManager G;
    private float H = 1.0f;
    private int I;
    private int J;
    /* access modifiers changed from: private */
    public f K;
    private int L;
    private Typeface M = Typeface.DEFAULT;
    private Typeface N = Typeface.DEFAULT_BOLD;
    private Typeface O = Typeface.create(Typeface.DEFAULT, 2);
    private char P;
    private boolean Q;
    private Paint R;
    private int S;
    /* access modifiers changed from: private */
    public final Runnable T = new Runnable() {
        public void run() {
            c.this.w.c();
            if (!c.this.d()) {
                c.this.postDelayed(c.this.T, c.e);
            }
        }
    };
    /* access modifiers changed from: private */
    public final Runnable U = new Runnable() {
        public void run() {
            c.this.w.d();
            if (!c.this.c()) {
                c.this.postDelayed(c.this.U, c.e);
            }
        }
    };
    /* access modifiers changed from: private */
    public final Runnable V = new Runnable() {
        public void run() {
            c.this.w.b(false);
            if (c.this.i > 0 && c.this.A == c.this.h.b(c.this.i - 1)) {
                c.this.postDelayed(c.this.V, c.e);
            }
        }
    };
    /* access modifiers changed from: private */
    public final Runnable W = new Runnable() {
        public void run() {
            c.this.w.a(false);
            if (!c.this.e() && c.this.A == c.this.h.b(c.this.i + 1)) {
                c.this.postDelayed(c.this.W, c.e);
            }
        }
    };
    private int aa;
    private long ab;
    /* access modifiers changed from: private */
    public boolean ac = false;
    private MotionEvent ad;
    private float ae;
    private float af;
    protected boolean f = false;
    protected g g = new g(this);
    /* access modifiers changed from: protected */
    public f h = new f((e.a) this);
    protected int i = 0;
    protected int j = -1;
    protected int k = -1;
    protected int l = c;
    protected com.b.a.b.b m = new d();
    protected boolean n = false;
    protected boolean o = false;
    protected boolean p = true;

    /* renamed from: q  reason: collision with root package name */
    protected int f27q = 4;
    protected boolean r = false;
    protected a s;
    protected boolean t = true;
    private final Scroller v;
    /* access modifiers changed from: private */
    public a w;
    /* access modifiers changed from: private */
    public b x;
    /* access modifiers changed from: private */
    public n y;
    /* access modifiers changed from: private */
    public e z;

    private class a implements k.a {
        private final k b;
        private boolean c;
        private boolean d;

        private a() {
            this.b = new k(this);
            this.c = false;
        }

        private void b(int i, int i2) {
            if (c.this.ac && Build.VERSION.SDK_INT >= 16) {
                AccessibilityRecord.obtain();
                AccessibilityEvent obtain = AccessibilityEvent.obtain(131072);
                int i3 = i - i2;
                if (i3 * i3 == 1) {
                    obtain.setMovementGranularity(1);
                }
                obtain.setAction(i > i2 ? 512 : 256);
                obtain.setFromIndex(Math.min(i, i2));
                obtain.setToIndex(Math.max(i, i2));
                c.this.sendAccessibilityEventUnchecked(obtain);
            }
            if (this.c) {
                if (i < c.this.k) {
                    if (i2 > c.this.k) {
                        c.this.j = c.this.k;
                    }
                    c.this.j = i2;
                    return;
                } else if (i2 < c.this.j) {
                    c.this.k = c.this.j;
                    c.this.j = i2;
                    return;
                }
                c.this.k = i2;
            }
        }

        private char[] j() {
            int e = c.this.h.e(c.this.h.c(c.this.i));
            c.this.h.f(e);
            int i = 0;
            int i2 = 0;
            while (c.this.h.a() && (((r3 = c.this.h.b()) == ' ' || r3 == 9) && e + i2 < c.this.i)) {
                i2++;
            }
            int a2 = i2 + (c.this.f27q * com.b.a.b.a.a(c.this.h.subSequence(e, c.this.i - e)));
            if (a2 < 0) {
                return new char[]{10};
            }
            char[] cArr = new char[(a2 + 1)];
            cArr[0] = 10;
            c.this.h.f(e);
            while (i < a2) {
                i++;
                cArr[i] = ' ';
            }
            return cArr;
        }

        private void k() {
            int d2 = c.this.A;
            e();
            if (!c.this.h(c.this.i)) {
                c.this.d(d2, d2 + 1);
                c.this.h();
            }
            f();
        }

        public void a() {
            this.b.a(c.this.h);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0063, code lost:
            if (r3 != r8.a.h.d(r2)) goto L_0x0065;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0173, code lost:
            if (r3 != r8.a.h.d(r2)) goto L_0x0065;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(char r9) {
            /*
                r8 = this;
                boolean r0 = r8.c
                r1 = 1
                if (r0 == 0) goto L_0x000a
                r8.i()
                r0 = 1
                goto L_0x000b
            L_0x000a:
                r0 = 0
            L_0x000b:
                com.b.a.a.c r2 = com.b.a.a.c.this
                int r2 = r2.A
                com.b.a.a.c r3 = com.b.a.a.c.this
                com.b.a.b.f r3 = r3.h
                int r3 = r3.d(r2)
                r4 = 8
                if (r9 == r4) goto L_0x00d8
                r0 = 10
                if (r9 == r0) goto L_0x006e
                com.b.a.a.c r0 = com.b.a.a.c.this
                com.b.a.b.f r0 = r0.h
                com.b.a.a.c r4 = com.b.a.a.c.this
                int r4 = r4.i
                long r5 = java.lang.System.nanoTime()
                r0.a((char) r9, (int) r4, (long) r5)
                r8.a((boolean) r1)
                com.b.a.a.c r0 = com.b.a.a.c.this
                com.b.a.a.f r0 = r0.K
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r9)
                java.lang.String r9 = ""
                r4.append(r9)
                java.lang.String r9 = r4.toString()
                com.b.a.a.c r4 = com.b.a.a.c.this
                int r4 = r4.i
                r0.b(r9, r4, r1)
                com.b.a.a.c r9 = com.b.a.a.c.this
                com.b.a.b.f r9 = r9.h
                boolean r9 = r9.j()
                if (r9 == 0) goto L_0x0177
                com.b.a.a.c r9 = com.b.a.a.c.this
                com.b.a.b.f r9 = r9.h
                int r9 = r9.d(r2)
                if (r3 == r9) goto L_0x0067
            L_0x0065:
                int r2 = r2 + -1
            L_0x0067:
                com.b.a.a.c r9 = com.b.a.a.c.this
                r9.g((int) r2)
                goto L_0x0177
            L_0x006e:
                com.b.a.a.c r0 = com.b.a.a.c.this
                boolean r0 = r0.p
                if (r0 == 0) goto L_0x0091
                char[] r0 = r8.j()
                com.b.a.a.c r4 = com.b.a.a.c.this
                com.b.a.b.f r4 = r4.h
                com.b.a.a.c r5 = com.b.a.a.c.this
                int r5 = r5.i
                long r6 = java.lang.System.nanoTime()
                r4.a((char[]) r0, (int) r5, (long) r6)
                com.b.a.a.c r4 = com.b.a.a.c.this
                int r4 = r4.i
                int r0 = r0.length
                int r4 = r4 + r0
                r8.a((int) r4)
                goto L_0x00a3
            L_0x0091:
                com.b.a.a.c r0 = com.b.a.a.c.this
                com.b.a.b.f r0 = r0.h
                com.b.a.a.c r4 = com.b.a.a.c.this
                int r4 = r4.i
                long r5 = java.lang.System.nanoTime()
                r0.a((char) r9, (int) r4, (long) r5)
                r8.a((boolean) r1)
            L_0x00a3:
                com.b.a.a.c r0 = com.b.a.a.c.this
                com.b.a.b.f r0 = r0.h
                boolean r0 = r0.j()
                if (r0 == 0) goto L_0x00b9
                com.b.a.a.c r0 = com.b.a.a.c.this
                com.b.a.b.f r0 = r0.h
                int r0 = r0.d(r2)
                if (r3 == r0) goto L_0x00b9
                int r2 = r2 + -1
            L_0x00b9:
                com.b.a.a.c r0 = com.b.a.a.c.this
                com.b.a.a.f r0 = r0.K
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                r3.append(r9)
                java.lang.String r9 = ""
                r3.append(r9)
                java.lang.String r9 = r3.toString()
                com.b.a.a.c r3 = com.b.a.a.c.this
                int r3 = r3.i
                r0.a((java.lang.String) r9, (int) r3, (int) r1)
                goto L_0x0067
            L_0x00d8:
                if (r0 == 0) goto L_0x00dc
                goto L_0x0177
            L_0x00dc:
                com.b.a.a.c r0 = com.b.a.a.c.this
                int r0 = r0.i
                if (r0 <= 0) goto L_0x0177
                com.b.a.a.c r0 = com.b.a.a.c.this
                com.b.a.a.f r0 = r0.K
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r9)
                java.lang.String r9 = ""
                r4.append(r9)
                java.lang.String r9 = r4.toString()
                com.b.a.a.c r4 = com.b.a.a.c.this
                int r4 = r4.i
                r0.a((java.lang.CharSequence) r9, (int) r4, (int) r1)
                com.b.a.a.c r9 = com.b.a.a.c.this
                com.b.a.b.f r9 = r9.h
                com.b.a.a.c r0 = com.b.a.a.c.this
                int r0 = r0.i
                int r0 = r0 - r1
                long r4 = java.lang.System.nanoTime()
                r9.a(r0, r4)
                com.b.a.a.c r9 = com.b.a.a.c.this
                com.b.a.b.f r9 = r9.h
                com.b.a.a.c r0 = com.b.a.a.c.this
                int r0 = r0.i
                int r0 = r0 + -2
                char r9 = r9.charAt(r0)
                r0 = 55357(0xd83d, float:7.7572E-41)
                if (r9 == r0) goto L_0x0136
                com.b.a.a.c r9 = com.b.a.a.c.this
                com.b.a.b.f r9 = r9.h
                com.b.a.a.c r0 = com.b.a.a.c.this
                int r0 = r0.i
                int r0 = r0 + -2
                char r9 = r9.charAt(r0)
                r0 = 55356(0xd83c, float:7.757E-41)
                if (r9 != r0) goto L_0x014a
            L_0x0136:
                com.b.a.a.c r9 = com.b.a.a.c.this
                com.b.a.b.f r9 = r9.h
                com.b.a.a.c r0 = com.b.a.a.c.this
                int r0 = r0.i
                int r0 = r0 + -2
                long r4 = java.lang.System.nanoTime()
                r9.a(r0, r4)
                r8.b((boolean) r1)
            L_0x014a:
                r8.b((boolean) r1)
                com.b.a.a.c r9 = com.b.a.a.c.this
                int r9 = r9.A
                if (r9 >= r2) goto L_0x0161
                com.b.a.a.c r9 = com.b.a.a.c.this
                com.b.a.a.c r0 = com.b.a.a.c.this
                int r0 = r0.A
                r9.g((int) r0)
                goto L_0x0177
            L_0x0161:
                com.b.a.a.c r9 = com.b.a.a.c.this
                com.b.a.b.f r9 = r9.h
                boolean r9 = r9.j()
                if (r9 == 0) goto L_0x0177
                com.b.a.a.c r9 = com.b.a.a.c.this
                com.b.a.b.f r9 = r9.h
                int r9 = r9.d(r2)
                if (r3 == r9) goto L_0x0067
                goto L_0x0065
            L_0x0177:
                com.b.a.a.c r9 = com.b.a.a.c.this
                r9.setEdited(r1)
                r8.a()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.c.a.a(char):void");
        }

        public void a(int i) {
            if (i < 0 || i >= c.this.h.g()) {
                q.a("Invalid caret position");
                return;
            }
            b(c.this.i, i);
            c.this.i = i;
            k();
        }

        /* access modifiers changed from: package-private */
        public void a(int i, int i2) {
            int i3 = c.this.i - i;
            if (i3 < 0) {
                i3 = 0;
            }
            int i4 = c.this.i + i2;
            int g = c.this.h.g() - 1;
            if (i4 > g) {
                i4 = g;
            }
            b(i3, i4 - i3, "");
        }

        /* access modifiers changed from: package-private */
        public void a(int i, int i2, String str) {
            boolean z;
            boolean z2;
            int i3;
            int i4;
            boolean z3 = false;
            if (this.c) {
                i4 = c.this.h.b(c.this.j);
                i3 = c.this.h.d(i4);
                int i5 = c.this.k - c.this.j;
                if (i5 > 0) {
                    c.this.i = c.this.j;
                    c.this.h.a(c.this.j, i5, System.nanoTime());
                    z = i4 == c.this.A;
                    z2 = true;
                } else {
                    z2 = false;
                    z = true;
                }
                c(false);
            } else {
                i4 = c.this.A;
                i3 = c.this.h.d(c.this.A);
                z2 = false;
                z = true;
            }
            if (i2 > 0) {
                int b2 = c.this.h.b(i);
                if (b2 < i4) {
                    i3 = c.this.h.d(b2);
                    i4 = b2;
                }
                if (i4 != c.this.A) {
                    z = false;
                }
                c.this.i = i;
                c.this.h.a(i, i2, System.nanoTime());
                z2 = true;
            }
            if (str != null && str.length() > 0) {
                int b3 = c.this.h.b(i);
                if (b3 < i4) {
                    i4 = b3;
                    i3 = c.this.h.d(b3);
                }
                c.this.h.a(str.toCharArray(), c.this.i, System.nanoTime());
                c.this.i += str.length();
                z2 = true;
            }
            if (z2) {
                c.this.setEdited(true);
                a();
            }
            int d2 = c.this.A;
            e();
            if (d2 == c.this.A) {
                z3 = z;
            }
            if (!c.this.h(c.this.i)) {
                if (c.this.h.j() && i3 != c.this.h.d(i4)) {
                    i4--;
                }
                if (!z3 || c.this.h.j()) {
                    c.this.g(i4);
                } else {
                    c.this.d(c.this.A, c.this.A + 1);
                }
            }
        }

        public void a(int i, int i2, boolean z, boolean z2) {
            q.a(i >= 0 && i2 <= c.this.h.g() - 1 && i2 >= 0, "Invalid range to select");
            if (this.c) {
                c.this.i();
            } else {
                c.this.h();
                if (z2) {
                    c(true);
                } else {
                    this.c = true;
                }
            }
            c.this.j = i;
            c.this.k = c.this.j + i2;
            c.this.i = c.this.k;
            f();
            e();
            if (z2) {
                c.this.z.a(g(), c.this.j, c.this.k);
            }
            boolean b2 = c.this.h(c.this.k);
            if (z) {
                b2 = c.this.h(c.this.j);
            }
            if (!b2) {
                c.this.i();
            }
        }

        public void a(ClipboardManager clipboardManager) {
            b(clipboardManager);
            i();
        }

        public void a(String str) {
            if (str != null) {
                c.this.h.d();
                i();
                int d2 = c.this.A;
                int d3 = c.this.h.d(d2);
                c.this.h.a(str.toCharArray(), c.this.i, System.nanoTime());
                c.this.h.e();
                c.this.i += str.length();
                e();
                c.this.setEdited(true);
                a();
                f();
                if (!c.this.h(c.this.i)) {
                    int i = (!c.this.h.j() || d3 == c.this.h.d(d2)) ? d2 : d2 - 1;
                    if (d2 != c.this.A || c.this.h.j()) {
                        c.this.g(i);
                    } else {
                        c.this.d(i, i + 1);
                    }
                }
            }
        }

        public void a(final List<m> list) {
            c.this.post(new Runnable() {
                public void run() {
                    c.this.h.a((List<m>) list);
                    c.this.invalidate();
                }
            });
        }

        public void a(boolean z) {
            if (!c.this.e()) {
                int d2 = c.this.A;
                c.this.i++;
                e();
                b(c.this.i - 1, c.this.i);
                if (!c.this.h(c.this.i)) {
                    c.this.d(d2, c.this.A + 1);
                }
                if (!z) {
                    f();
                }
            }
        }

        public void b() {
            this.b.b();
        }

        /* access modifiers changed from: package-private */
        public void b(int i, int i2, String str) {
            boolean z;
            boolean z2;
            int i3;
            int i4;
            boolean z3 = false;
            if (this.c) {
                i4 = c.this.h.b(c.this.j);
                i3 = c.this.h.d(i4);
                int i5 = c.this.k - c.this.j;
                if (i5 > 0) {
                    c.this.i = c.this.j;
                    c.this.h.a(c.this.j, i5, System.nanoTime());
                    z = i4 == c.this.A;
                    z2 = true;
                } else {
                    z2 = false;
                    z = true;
                }
                c(false);
            } else {
                i4 = c.this.A;
                i3 = c.this.h.d(c.this.A);
                z2 = false;
                z = true;
            }
            if (i2 > 0) {
                int b2 = c.this.h.b(i);
                if (b2 < i4) {
                    i3 = c.this.h.d(b2);
                    i4 = b2;
                }
                if (i4 != c.this.A) {
                    z = false;
                }
                c.this.i = i;
                c.this.h.a(i, i2, System.nanoTime());
                z2 = true;
            }
            if (str != null && str.length() > 0) {
                int b3 = c.this.h.b(i);
                if (b3 < i4) {
                    i3 = c.this.h.d(b3);
                    i4 = b3;
                }
                c.this.h.a(str.toCharArray(), c.this.i, System.nanoTime());
                c.this.i += str.length();
                z2 = true;
            }
            c.this.K.b(str, c.this.i, str.length() - i2);
            if (z2) {
                c.this.setEdited(true);
                a();
            }
            int d2 = c.this.A;
            e();
            if (d2 == c.this.A) {
                z3 = z;
            }
            if (!c.this.h(c.this.i)) {
                if (c.this.h.j() && i3 != c.this.h.d(i4)) {
                    i4--;
                }
                if (!z3 || c.this.h.j()) {
                    c.this.g(i4);
                } else {
                    c.this.d(c.this.A, c.this.A + 1);
                }
            }
        }

        public void b(ClipboardManager clipboardManager) {
            if (this.c && c.this.j < c.this.k) {
                clipboardManager.setText(c.this.h.subSequence(c.this.j, c.this.k - c.this.j));
            }
        }

        public void b(boolean z) {
            if (c.this.i > 0) {
                int d2 = c.this.A;
                c cVar = c.this;
                cVar.i--;
                e();
                b(c.this.i + 1, c.this.i);
                if (!c.this.h(c.this.i)) {
                    c.this.d(c.this.A, d2 + 1);
                }
                if (!z) {
                    f();
                }
            }
        }

        public boolean b(int i) {
            return c.this.j >= 0 && c.this.j <= i && i < c.this.k;
        }

        /* access modifiers changed from: package-private */
        public String c(int i) {
            int g = c.this.h.g();
            return (c.this.i + i > g + -1 ? c.this.h.subSequence(c.this.i, (g - c.this.i) - 1) : c.this.h.subSequence(c.this.i, i)).toString();
        }

        public void c() {
            if (!c.this.d()) {
                int i = c.this.i;
                int d2 = c.this.A;
                int i2 = d2 + 1;
                int e = c.this.e(i);
                int g = c.this.h.g(d2);
                int g2 = c.this.h.g(i2);
                if (e < g2) {
                    c.this.i += g;
                } else {
                    c.this.i += ((g - e) + g2) - 1;
                }
                c.j(c.this);
                b(i, c.this.i);
                if (!c.this.h(c.this.i)) {
                    c.this.d(d2, i2 + 1);
                }
                c.this.y.a(i2);
                f();
            }
        }

        public void c(boolean z) {
            int i;
            c cVar;
            if (this.c ^ z) {
                if (z) {
                    c.this.j = c.this.i;
                    cVar = c.this;
                    i = c.this.i;
                } else {
                    i = -1;
                    c.this.j = -1;
                    cVar = c.this;
                }
                cVar.k = i;
                this.c = z;
                this.d = z;
                c.this.z.a(z, c.this.getSelectionStart(), c.this.getSelectionEnd());
            }
        }

        /* access modifiers changed from: package-private */
        public String d(int i) {
            int i2 = c.this.i - i;
            if (i2 < 0) {
                i2 = 0;
            }
            return c.this.h.subSequence(i2, c.this.i - i2).toString();
        }

        public void d() {
            if (!c.this.c()) {
                int i = c.this.i;
                int d2 = c.this.A;
                int i2 = d2 - 1;
                int e = c.this.e(i);
                int g = c.this.h.g(i2);
                if (e < g) {
                    c.this.i -= g;
                } else {
                    c.this.i -= e + 1;
                }
                c.l(c.this);
                b(i, c.this.i);
                if (!c.this.h(c.this.i)) {
                    c.this.d(i2, d2 + 1);
                }
                c.this.y.a(i2);
                f();
            }
        }

        public void d(boolean z) {
            c cVar;
            int i;
            if (this.c) {
                if (z && c.this.i != c.this.j) {
                    cVar = c.this;
                    i = c.this.j;
                } else if (!z && c.this.i != c.this.k) {
                    cVar = c.this;
                    i = c.this.k;
                } else {
                    return;
                }
                cVar.i = i;
                k();
            }
        }

        /* access modifiers changed from: package-private */
        public void e() {
            int b2 = c.this.h.b(c.this.i);
            if (c.this.A != b2) {
                int unused = c.this.A = b2;
                c.this.y.a(b2);
            }
        }

        public void f() {
            ((InputMethodManager) c.this.getContext().getSystemService("input_method")).restartInput(c.this);
            if (c.this.x != null && c.this.x.b()) {
                c.this.x.a();
            }
        }

        public final boolean g() {
            return this.c;
        }

        public final boolean h() {
            return this.d;
        }

        public void i() {
            if (this.c) {
                int i = c.this.k - c.this.j;
                if (i > 0) {
                    int b2 = c.this.h.b(c.this.j);
                    int d2 = c.this.h.d(b2);
                    boolean z = c.this.h.b(c.this.k) == b2;
                    c.this.K.a((CharSequence) "", c.this.i, i);
                    c.this.h.a(c.this.j, i, System.nanoTime());
                    c.this.i = c.this.j;
                    e();
                    c.this.setEdited(true);
                    a();
                    c(false);
                    f();
                    if (!c.this.h(c.this.i)) {
                        if (c.this.h.j() && d2 != c.this.h.d(b2)) {
                            b2--;
                        }
                        if (!z || c.this.h.j()) {
                            c.this.g(b2);
                        } else {
                            c.this.d(b2, b2 + 1);
                        }
                    }
                } else {
                    c(false);
                    c.this.h();
                }
            }
        }
    }

    private class b extends BaseInputConnection {
        private boolean b = false;
        private int c = 0;

        public b(c cVar) {
            super(cVar, true);
        }

        public void a() {
            this.c = 0;
            this.b = false;
            c.this.h.e();
        }

        public boolean b() {
            return this.b;
        }

        public boolean commitText(CharSequence charSequence, int i) {
            c.this.w.b(c.this.getCaretPosition() - this.c, this.c, charSequence.toString());
            this.c = 0;
            c.this.h.e();
            if (i > 1) {
                c.this.w.a((c.this.i + i) - 1);
            } else if (i <= 0) {
                c.this.w.a((c.this.i - charSequence.length()) - i);
            }
            this.b = false;
            return true;
        }

        public boolean deleteSurroundingText(int i, int i2) {
            if (this.c != 0) {
                Log.i("lua", "Warning: Implmentation of InputConnection.deleteSurroundingText will not skip composing text");
            }
            c.this.w.a(i, i2);
            return true;
        }

        public boolean finishComposingText() {
            a();
            return true;
        }

        public int getCursorCapsMode(int i) {
            boolean z = true;
            if ((i & 8192) == 8192) {
                int i2 = c.this.i - 1;
                if (i2 < 0 || k.a().b(c.this.h.charAt(i2))) {
                    return (i & 16384) == 16384 ? 24576 : 8192;
                }
                return 0;
            }
            h a2 = k.a();
            int i3 = c.this.i - 1;
            int i4 = 0;
            while (true) {
                if (i3 >= 0) {
                    char charAt = c.this.h.charAt(i3);
                    if (charAt == 10) {
                        break;
                    } else if (a2.b(charAt)) {
                        i4++;
                        i3--;
                    } else if (i4 == 0 || !a2.c(charAt)) {
                        z = false;
                    }
                } else {
                    break;
                }
            }
            return z ? 16384 : 0;
        }

        public CharSequence getTextAfterCursor(int i, int i2) {
            return c.this.w.c(i);
        }

        public CharSequence getTextBeforeCursor(int i, int i2) {
            return c.this.w.d(i);
        }

        public boolean performContextMenuAction(int i) {
            switch (i) {
                case 16908319:
                    break;
                case 16908320:
                    c.this.cut();
                    return false;
                case 16908321:
                    c.this.copy();
                    return false;
                case 16908322:
                    c.this.paste();
                    return false;
                default:
                    switch (i) {
                        case 16908328:
                        case 16908329:
                            break;
                        default:
                            return false;
                    }
            }
            c.this.selectAll();
            return false;
        }

        public boolean reportFullscreenMode(boolean z) {
            return false;
        }

        public boolean sendKeyEvent(KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 59) {
                switch (keyCode) {
                    case 19:
                        c.this.moveCaretUp();
                        return true;
                    case 20:
                        c.this.moveCaretDown();
                        return true;
                    case 21:
                        c.this.moveCaretLeft();
                        return true;
                    case 22:
                        c.this.moveCaretRight();
                        return true;
                    default:
                        switch (keyCode) {
                            case 122:
                                c.this.moveCaret(0);
                                return true;
                            case 123:
                                c.this.moveCaret(c.this.h.length());
                                return true;
                            default:
                                return super.sendKeyEvent(keyEvent);
                        }
                }
            } else if (c.this.isSelectText()) {
                c.this.selectText(false);
                return true;
            } else {
                c.this.selectText(true);
                return true;
            }
        }

        public boolean setComposingText(CharSequence charSequence, int i) {
            this.b = true;
            if (!c.this.h.c()) {
                c.this.h.d();
            }
            c.this.w.b(c.this.getCaretPosition() - this.c, this.c, charSequence.toString());
            this.c = charSequence.length();
            if (i > 1) {
                c.this.w.a((c.this.i + i) - 1);
                return true;
            }
            if (i <= 0) {
                c.this.w.a((c.this.i - charSequence.length()) - i);
            }
            return true;
        }

        public boolean setSelection(int i, int i2) {
            if (i == i2) {
                c.this.w.a(i);
                return true;
            }
            c.this.w.a(i, i2 - i, false, true);
            return true;
        }
    }

    /* renamed from: com.b.a.a.c$c  reason: collision with other inner class name */
    public static class C0005c implements Parcelable {
        public static final Parcelable.Creator<C0005c> CREATOR = new Parcelable.Creator<C0005c>() {
            /* renamed from: a */
            public C0005c createFromParcel(Parcel parcel) {
                return new C0005c(parcel);
            }

            /* renamed from: a */
            public C0005c[] newArray(int i) {
                return new C0005c[i];
            }
        };
        final int a;
        final int b;
        final int c;
        final boolean d;
        final int e;
        final int f;

        private C0005c(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt() != 0;
            this.e = parcel.readInt();
            this.f = parcel.readInt();
        }

        public C0005c(c cVar) {
            this.a = cVar.getCaretPosition();
            this.b = cVar.getScrollX();
            this.c = cVar.getScrollY();
            this.d = cVar.isSelectText();
            this.e = cVar.getSelectionStart();
            this.f = cVar.getSelectionEnd();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d ? 1 : 0);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
        }
    }

    static {
        u.put(65, "ÀÁÂÄÆÃÅĄĀ");
        u.put(67, "ÇĆČ");
        u.put(68, "Ď");
        u.put(69, "ÈÉÊËĘĚĒ");
        u.put(71, "Ğ");
        u.put(76, "Ł");
        u.put(73, "ÌÍÎÏĪİ");
        u.put(78, "ÑŃŇ");
        u.put(79, "ØŒÕÒÓÔÖŌ");
        u.put(82, "Ř");
        u.put(83, "ŚŠŞ");
        u.put(84, "Ť");
        u.put(85, "ÙÚÛÜŮŪ");
        u.put(89, "ÝŸ");
        u.put(90, "ŹŻŽ");
        u.put(97, "àáâäæãåąā");
        u.put(99, "çćč");
        u.put(100, "ď");
        u.put(R.styleable.AppCompatTheme_autoCompleteTextViewStyle, "èéêëęěē");
        u.put(R.styleable.AppCompatTheme_buttonStyleSmall, "ğ");
        u.put(R.styleable.AppCompatTheme_checkedTextViewStyle, "ìíîïīı");
        u.put(R.styleable.AppCompatTheme_ratingBarStyle, "ł");
        u.put(R.styleable.AppCompatTheme_ratingBarStyleSmall, "ñńň");
        u.put(R.styleable.AppCompatTheme_seekBarStyle, "øœõòóôöō");
        u.put(R.styleable.AppCompatTheme_listMenuViewStyle, "ř");
        u.put(115, "§ßśšş");
        u.put(116, "ť");
        u.put(117, "ùúûüůū");
        u.put(121, "ýÿ");
        u.put(122, "źżž");
        u.put(61185, "…¥•®©±[]{}\\|");
        u.put(47, "\\");
        u.put(49, "¹½⅓¼⅛");
        u.put(50, "²⅔");
        u.put(51, "³¾⅜");
        u.put(52, "⁴");
        u.put(53, "⅝");
        u.put(55, "⅞");
        u.put(48, "ⁿ∅");
        u.put(36, "¢£€¥₣₤₱");
        u.put(37, "‰");
        u.put(42, "†‡");
        u.put(45, "–—");
        u.put(43, "±");
        u.put(40, "[{<");
        u.put(41, "]}>");
        u.put(33, "¡");
        u.put(34, "“”«»˝");
        u.put(63, "¿");
        u.put(44, "‚„");
        u.put(61, "≠≈∞");
        u.put(60, "≤«‹");
        u.put(62, "≥»›");
    }

    public c(Context context) {
        super(context);
        this.v = new Scroller(context);
        f();
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = new Scroller(context);
        f();
    }

    public c(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.v = new Scroller(context);
        f();
    }

    private int a(Canvas canvas) {
        return canvas.getClipBounds().top / a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008f, code lost:
        r23.drawText(r8, 0, 1, (float) r2, (float) r3, r0.B);
        r0.B.setColor(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009f, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(android.graphics.Canvas r23, char r24, int r25, int r26) {
        /*
            r22 = this;
            r0 = r22
            r1 = r24
            r2 = r25
            r3 = r26
            android.graphics.Paint r4 = r0.B
            int r4 = r4.getColor()
            int r5 = r0.getAdvance(r1, r2)
            int r6 = r22.getScrollX()
            if (r2 > r6) goto L_0x0023
            int r6 = r22.getScrollX()
            int r7 = r22.getContentWidth()
            int r6 = r6 + r7
            if (r2 >= r6) goto L_0x00b9
        L_0x0023:
            switch(r1) {
                case 9: goto L_0x007c;
                case 10: goto L_0x0068;
                case 32: goto L_0x0046;
                case 55356: goto L_0x0043;
                case 55357: goto L_0x0043;
                case 65535: goto L_0x0068;
                default: goto L_0x0026;
            }
        L_0x0026:
            char r4 = r0.P
            r6 = 1
            r7 = 0
            if (r4 == 0) goto L_0x00a0
            r4 = 2
            char[] r9 = new char[r4]
            char r4 = r0.P
            r9[r7] = r4
            r9[r6] = r1
            r10 = 0
            r11 = 2
            float r12 = (float) r2
            float r13 = (float) r3
            android.graphics.Paint r14 = r0.B
            r8 = r23
            r8.drawText(r9, r10, r11, r12, r13, r14)
            r0.P = r7
            return r5
        L_0x0043:
            r0.P = r1
            return r5
        L_0x0046:
            boolean r1 = r0.o
            if (r1 == 0) goto L_0x005a
            android.graphics.Paint r1 = r0.B
            com.b.a.b.b r6 = r0.m
            com.b.a.b.b$a r7 = com.b.a.b.b.a.NON_PRINTING_GLYPH
            int r6 = r6.a((com.b.a.b.b.a) r7)
            r1.setColor(r6)
            java.lang.String r8 = "·"
            goto L_0x008f
        L_0x005a:
            java.lang.String r7 = " "
            r8 = 0
            r9 = 1
            float r10 = (float) r2
            float r11 = (float) r3
            android.graphics.Paint r12 = r0.B
            r6 = r23
            r6.drawText(r7, r8, r9, r10, r11, r12)
            return r5
        L_0x0068:
            boolean r1 = r0.o
            if (r1 == 0) goto L_0x00b9
            android.graphics.Paint r1 = r0.B
            com.b.a.b.b r6 = r0.m
            com.b.a.b.b$a r7 = com.b.a.b.b.a.NON_PRINTING_GLYPH
            int r6 = r6.a((com.b.a.b.b.a) r7)
            r1.setColor(r6)
            java.lang.String r8 = "↵"
            goto L_0x008f
        L_0x007c:
            boolean r1 = r0.o
            if (r1 == 0) goto L_0x00b9
            android.graphics.Paint r1 = r0.B
            com.b.a.b.b r6 = r0.m
            com.b.a.b.b$a r7 = com.b.a.b.b.a.NON_PRINTING_GLYPH
            int r6 = r6.a((com.b.a.b.b.a) r7)
            r1.setColor(r6)
            java.lang.String r8 = "»"
        L_0x008f:
            r9 = 0
            r10 = 1
            float r11 = (float) r2
            float r12 = (float) r3
            android.graphics.Paint r13 = r0.B
            r7 = r23
            r7.drawText(r8, r9, r10, r11, r12, r13)
            android.graphics.Paint r1 = r0.B
            r1.setColor(r4)
            return r5
        L_0x00a0:
            char[] r4 = new char[r6]
            r4[r7] = r1
            r17 = 0
            r18 = 1
            float r1 = (float) r2
            float r2 = (float) r3
            android.graphics.Paint r3 = r0.B
            r15 = r23
            r16 = r4
            r19 = r1
            r20 = r2
            r21 = r3
            r15.drawText(r16, r17, r18, r19, r20, r21)
        L_0x00b9:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.c.a(android.graphics.Canvas, char, int, int):int");
    }

    private int a(Canvas canvas, String str, int i2, int i3) {
        canvas.drawText(str, (float) i2, (float) i3, this.R);
        return 0;
    }

    private void a(char c2) {
        a aVar;
        if (!Character.isLowerCase(c2) || c2 != this.h.charAt(this.i - 1)) {
            aVar = this.w;
        } else {
            this.w.a(8);
            aVar = this.w;
            c2 = Character.toUpperCase(c2);
        }
        aVar.a(c2);
    }

    private void a(float f2, float f3) {
        int scrollX = ((int) f2) + getScrollX();
        int scrollY = ((int) f3) + getScrollY();
        int max = Math.max(getMaxScrollX(), getScrollX());
        if (scrollX > max) {
            scrollX = max;
        } else if (scrollX < 0) {
            scrollX = 0;
        }
        int max2 = Math.max(getMaxScrollY(), getScrollY());
        if (scrollY > max2) {
            scrollY = max2;
        } else if (scrollY < 0) {
            scrollY = 0;
        }
        smoothScrollTo(scrollX, scrollY);
    }

    private void a(int i2, KeyEvent keyEvent) {
        if (keyEvent.isShiftPressed() && !isSelectText()) {
            h();
            this.w.c(true);
        } else if (!keyEvent.isShiftPressed() && isSelectText()) {
            i();
            this.w.c(false);
        }
        switch (i2) {
            case 19:
                this.w.d();
                return;
            case 20:
                this.w.c();
                return;
            case 21:
                this.w.b(false);
                return;
            case 22:
                this.w.a(false);
                return;
            default:
                return;
        }
    }

    private void a(Canvas canvas, int i2, int i3) {
        int color = this.B.getColor();
        this.I = i2;
        this.J = i3;
        this.B.setColor(this.m.a(b.a.CARET_DISABLED));
        a(canvas, i2 - 1, i3, 2);
        this.B.setColor(color);
    }

    private void a(Canvas canvas, int i2, int i3, int i4) {
        Paint.FontMetricsInt fontMetricsInt = this.B.getFontMetricsInt();
        canvas.drawRect((float) i2, (float) (fontMetricsInt.ascent + i3), (float) (i2 + i4), (float) (i3 + fontMetricsInt.descent), this.B);
    }

    private void a(String str, final boolean z2) {
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Selection.setSelection(spannableStringBuilder, 0);
        CharacterPickerDialog characterPickerDialog = new CharacterPickerDialog(getContext(), this, spannableStringBuilder, str, true);
        characterPickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (spannableStringBuilder.length() > 0) {
                    if (z2) {
                        c.this.w.a(8);
                    }
                    c.this.w.a(spannableStringBuilder.charAt(0));
                }
            }
        });
        characterPickerDialog.show();
    }

    private int b(Canvas canvas) {
        return (canvas.getClipBounds().bottom - 1) / a();
    }

    private int b(Canvas canvas, char c2, int i2, int i3) {
        int color = this.B.getColor();
        int advance = getAdvance(c2);
        this.B.setColor(this.m.a(b.a.SELECTION_BACKGROUND));
        a(canvas, i2, i3, advance);
        this.B.setColor(this.m.a(b.a.SELECTION_FOREGROUND));
        a(canvas, c2, i2, i3);
        this.B.setColor(color);
        return advance;
    }

    private void b(char c2) {
        String str = u.get(Character.isUpperCase(this.h.charAt(this.i - 1)) ? Character.toUpperCase(c2) : c2);
        if (str != null) {
            this.w.f();
            a(str, true);
            return;
        }
        this.w.a(c2);
    }

    private void c(Canvas canvas) {
        int a2;
        int i2;
        m mVar;
        Paint paint;
        Typeface typeface;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        Typeface typeface2;
        int i10;
        int i11;
        m mVar2;
        int i12;
        Canvas canvas2 = canvas;
        int a3 = a(canvas);
        int d2 = this.h.d(a3);
        if (d2 >= 0) {
            this.h.length();
            int c2 = isWordWrap() ? this.h.c(d2) + 1 : a3 + 1;
            if (this.E) {
                this.D = (int) this.R.measureText(this.h.f() + " ");
            }
            int b2 = b(canvas);
            int paintBaseline = getPaintBaseline(a3);
            List<m> i13 = this.h.i();
            q.a(!i13.isEmpty(), "No spans to paint in TextWarrior.paint()");
            if (i13.isEmpty()) {
                i13.add(new m(0, 0));
            }
            m mVar3 = i13.get(0);
            int size = i13.size();
            int i14 = 0;
            int i15 = 1;
            while (true) {
                a2 = i14 + mVar3.a();
                if (i15 < size) {
                    i2 = i15 + 1;
                    mVar = i13.get(i15);
                } else {
                    i2 = i15;
                    mVar = null;
                }
                if (mVar == null || a2 > d2) {
                    int b3 = mVar3.b();
                    int b4 = mVar3.b();
                } else {
                    i14 = a2;
                    mVar3 = mVar;
                    i15 = i2;
                }
            }
            int b32 = mVar3.b();
            int b42 = mVar3.b();
            if (b42 == 1) {
                paint = this.B;
                typeface = this.N;
            } else if (b42 != 30) {
                paint = this.B;
                typeface = this.M;
            } else {
                paint = this.B;
                typeface = this.O;
            }
            paint.setTypeface(typeface);
            this.B.setColor(this.m.a(mVar3.b()));
            int f2 = this.h.f();
            if (this.E) {
                this.R.setColor(this.m.a(b.a.NON_PRINTING_GLYPH));
                i5 = a3;
                i4 = d2;
                i8 = 30;
                i9 = f2;
                i6 = b32;
                i7 = a2;
                i3 = size;
                canvas2.drawLine((float) (this.D - (this.aa / 2)), (float) getScrollY(), (float) (this.D - (this.aa / 2)), (float) (getScrollY() + getHeight()), this.R);
                if (getMaxScrollY() > getHeight()) {
                    int scrollY = getScrollY() + ((getHeight() * getScrollY()) / getMaxScrollY());
                    int scrollY2 = getScrollY() + ((getHeight() * (getScrollY() + getHeight())) / getMaxScrollY());
                    if (scrollY2 - scrollY < this.S / 4) {
                        scrollY2 = (this.S / 4) + scrollY;
                    }
                    canvas2.drawLine((float) ((this.D - (this.aa / 2)) - (this.S / 4)), (float) scrollY, (float) ((this.D - (this.aa / 2)) - (this.S / 4)), (float) scrollY2, this.R);
                }
            } else {
                i6 = b32;
                i7 = a2;
                i3 = size;
                i5 = a3;
                i4 = d2;
                i8 = 30;
                i9 = f2;
            }
            Typeface typeface3 = i6 != 1 ? i6 != i8 ? this.M : this.O : this.N;
            this.B.setTypeface(typeface3);
            Typeface typeface4 = typeface3;
            int i16 = i5;
            int i17 = 0;
            while (i16 <= b2) {
                int g2 = this.h.g(i16);
                if (i16 >= i9) {
                    break;
                }
                if (this.E && c2 != i17) {
                    a(canvas2, String.valueOf(c2), 0, paintBaseline);
                    i17 = c2;
                }
                int i18 = i17;
                Typeface typeface5 = typeface4;
                int i19 = this.D;
                int i20 = i9;
                int i21 = i6;
                m mVar4 = mVar;
                int i22 = i2;
                int i23 = 0;
                int i24 = i7;
                int i25 = i4;
                while (i23 < g2) {
                    if (mVar4 == null || i25 < i24) {
                        int i26 = i21;
                        i11 = i3;
                        typeface2 = typeface5;
                        int i27 = i22;
                        mVar2 = mVar4;
                        i12 = i26;
                        i10 = i27;
                    } else {
                        i24 += mVar4.a();
                        i12 = mVar4.b();
                        if (i21 != i12) {
                            Typeface typeface6 = i12 != 1 ? i12 != 30 ? this.M : this.O : this.N;
                            if (typeface5 != typeface6) {
                                this.B.setTypeface(typeface6);
                                typeface5 = typeface6;
                            }
                            this.B.setColor(this.m.a(i12));
                            i11 = i3;
                            typeface5 = typeface5;
                        } else {
                            i11 = i3;
                        }
                        if (i22 < i11) {
                            i10 = i22 + 1;
                            mVar2 = i13.get(i22);
                            typeface2 = typeface5;
                        } else {
                            typeface2 = typeface5;
                            i10 = i22;
                            mVar2 = null;
                        }
                    }
                    if (i25 == this.i) {
                        a(canvas2, i19, paintBaseline);
                    }
                    char charAt = this.h.charAt(i25);
                    int i28 = g2;
                    i19 += this.w.b(i25) ? b(canvas2, charAt, i19, paintBaseline) : a(canvas2, charAt, i19, paintBaseline);
                    i25++;
                    i23++;
                    i3 = i11;
                    typeface5 = typeface2;
                    g2 = i28;
                    i21 = i12;
                    mVar4 = mVar2;
                    i22 = i10;
                }
                int i29 = i21;
                int i30 = i3;
                if (this.h.charAt(i25 - 1) == 10) {
                    c2++;
                }
                paintBaseline += a();
                if (i19 > this.C) {
                    this.C = i19;
                }
                i16++;
                typeface4 = typeface5;
                i2 = i22;
                i3 = i30;
                i4 = i25;
                i7 = i24;
                i6 = i29;
                i17 = i18;
                i9 = i20;
                mVar = mVar4;
            }
            e(canvas);
            if (!isWordWrap()) {
                d(canvas);
            }
        }
    }

    /* access modifiers changed from: private */
    public void d(int i2, int i3) {
        q.a(i2 <= i3 && i2 >= 0, "Invalid startRow and/or endRow");
        Rect a2 = this.g.a();
        super.invalidate(0, Math.max(0, ((i2 * a()) + getPaddingTop()) - Math.max(a2.top, this.B.getFontMetricsInt().descent)), getScrollX() + getWidth(), (i3 * a()) + getPaddingTop() + a2.bottom);
    }

    private void d(Canvas canvas) {
        ArrayList<Rect> d2 = k.d();
        if (d2 != null && !d2.isEmpty()) {
            Rect clipBounds = canvas.getClipBounds();
            int i2 = clipBounds.top;
            int i3 = clipBounds.bottom;
            Rect rect = null;
            Iterator<Rect> it = d2.iterator();
            while (it.hasNext()) {
                Rect next = it.next();
                int a2 = (next.top + 1) * a();
                int a3 = next.bottom * a();
                if (a3 >= i2 && a2 <= i3) {
                    int min = Math.min(b(next.left).a(), b(next.right).a());
                    if (next.left < this.i && next.right >= this.i && (rect == null || rect.left < next.left)) {
                        rect = next;
                    }
                    float f2 = (float) min;
                    canvas.drawLine(f2, (float) a2, f2, (float) a3, this.R);
                }
            }
            if (rect != null) {
                int a4 = (rect.top + 1) * a();
                int a5 = rect.bottom * a();
                if (a5 >= i2 && a4 <= i3) {
                    int min2 = Math.min(b(rect.left).a(), b(rect.right).a());
                    this.R.setColor(this.m.a(b.a.CARET_FOREGROUND));
                    float f3 = (float) min2;
                    canvas.drawLine(f3, (float) a4, f3, (float) a5, this.R);
                    this.R.setColor(this.m.a(b.a.NON_PRINTING_GLYPH));
                }
            }
        }
    }

    private void e(Canvas canvas) {
        if (this.n) {
            int paintBaseline = getPaintBaseline(this.A);
            int color = this.B.getColor();
            this.B.setColor(this.m.a(b.a.LINE_HIGHLIGHT));
            a(canvas, 0, paintBaseline, Math.max(this.C, getContentWidth()));
            this.B.setColor(color);
        }
    }

    private final boolean e(int i2, int i3) {
        return i2 >= 0 && i2 < getWidth() && i3 >= 0 && i3 < getHeight();
    }

    private int f(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 || mode == Integer.MIN_VALUE) {
            return size;
        }
        q.a("MeasureSpec cannot be UNSPECIFIED. Setting dimensions to max.");
        return Integer.MAX_VALUE;
    }

    private void f() {
        this.ac = ((AccessibilityManager) getContext().getSystemService("accessibility")).isTouchExplorationEnabled();
        this.w = new a();
        this.G = (ClipboardManager) getContext().getSystemService("clipboard");
        this.B = new Paint();
        this.B.setAntiAlias(true);
        this.B.setTextSize((float) d);
        this.R = new Paint();
        this.R.setAntiAlias(true);
        this.R.setTextSize((float) d);
        setLongClickable(true);
        setFocusableInTouchMode(true);
        setHapticFeedbackEnabled(true);
        this.y = new n() {
            public void a(int i) {
            }
        };
        this.z = new e() {
            public void a(boolean z, int i, int i2) {
                if (z) {
                    c.this.F.a();
                } else {
                    c.this.F.b();
                }
            }
        };
        this.K = new f() {
            public void a(CharSequence charSequence, int i, int i2) {
                if (c.this.ac) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain(16);
                    obtain.setFromIndex(i - i2);
                    obtain.setRemovedCount(i2);
                    obtain.setBeforeText(c.this.h);
                    c.this.sendAccessibilityEventUnchecked(obtain);
                }
                c.this.s.b();
            }

            public void a(String str, int i, int i2) {
                if (c.this.ac) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain(16);
                    obtain.setFromIndex(i - 1);
                    obtain.setAddedCount(1);
                    c.this.sendAccessibilityEventUnchecked(obtain);
                }
                c.this.s.b();
            }

            public void b(CharSequence charSequence, int i, int i2) {
                if (c.this.ac) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain(16);
                    obtain.setFromIndex(i - i2);
                    obtain.setAddedCount(i2);
                    c.this.sendAccessibilityEventUnchecked(obtain);
                }
                if (c.this.t) {
                    int i3 = c.this.i;
                    while (i3 >= 0) {
                        char charAt = c.this.h.charAt(i3 - 1);
                        if (!Character.isLetterOrDigit(charAt) && charAt != '_' && charAt != '.') {
                            break;
                        }
                        i3--;
                    }
                    if (c.this.i - i3 > 0) {
                        c.this.s.a(c.this.h.subSequence(i3, c.this.i - i3));
                    } else {
                        c.this.s.b();
                    }
                }
            }
        };
        g();
        this.F = new b(this);
        this.s = new a(this);
        this.s.a(i.g());
        invalidate();
    }

    private void g() {
        this.i = 0;
        this.A = 0;
        this.C = 0;
        this.w.c(false);
        this.w.f();
        this.h.h();
        if (getContentWidth() > 0 || !this.h.j()) {
            this.h.k();
        }
        this.y.a(0);
        scrollTo(0, 0);
    }

    /* access modifiers changed from: private */
    public void g(int i2) {
        q.a(i2 >= 0, "Invalid startRow");
        super.invalidate(0, Math.max(0, ((i2 * a()) + getPaddingTop()) - Math.max(this.g.a().top, this.B.getFontMetricsInt().descent)), getScrollX() + getWidth(), getScrollY() + getHeight());
    }

    /* access modifiers changed from: private */
    public void h() {
        d(this.A, this.A + 1);
    }

    /* access modifiers changed from: private */
    public boolean h(int i2) {
        q.a(i2 >= 0 && i2 < this.h.g(), "Invalid charOffset given");
        int i3 = i(i2);
        int j2 = j(i2);
        if (i3 == 0 && j2 == 0) {
            return false;
        }
        scrollBy(j2, i3);
        return true;
    }

    private int i(int i2) {
        int b2 = this.h.b(i2) * a();
        int a2 = a() + b2;
        if (b2 < getScrollY()) {
            return b2 - getScrollY();
        }
        if (a2 > getScrollY() + getContentHeight()) {
            return (a2 - getScrollY()) - getContentHeight();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public void i() {
        d(this.h.b(this.j), this.h.b(this.k) + 1);
    }

    private int j(int i2) {
        m b2 = b(i2);
        int a2 = b2.a();
        int b3 = b2.b();
        return a2 < getScrollX() + this.S ? (a2 - getScrollX()) - this.S : b3 > getScrollX() + getContentWidth() ? (b3 - getScrollX()) - getContentWidth() : 0;
    }

    static /* synthetic */ int j(c cVar) {
        int i2 = cVar.A + 1;
        cVar.A = i2;
        return i2;
    }

    static /* synthetic */ int l(c cVar) {
        int i2 = cVar.A - 1;
        cVar.A = i2;
        return i2;
    }

    /* access modifiers changed from: protected */
    public int a() {
        Paint.FontMetricsInt fontMetricsInt = this.B.getFontMetricsInt();
        return fontMetricsInt.descent - fontMetricsInt.ascent;
    }

    /* access modifiers changed from: protected */
    public int a(int i2) {
        if (this.o) {
            return this.l * ((int) this.B.measureText("·", 0, "·".length()));
        }
        return (this.l - (((i2 - this.D) / this.aa) % this.l)) * this.aa;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0065, code lost:
        r6 = r6 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006c, code lost:
        if (r6 < r10) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006f, code lost:
        r2 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(int r10, int r11) {
        /*
            r9 = this;
            int r0 = r9.a()
            int r11 = r11 / r0
            com.b.a.b.f r0 = r9.h
            int r0 = r0.f()
            r1 = 1
            if (r11 <= r0) goto L_0x0016
            com.b.a.b.f r10 = r9.h
            int r10 = r10.g()
            int r10 = r10 - r1
            return r10
        L_0x0016:
            com.b.a.b.f r0 = r9.h
            int r0 = r0.d(r11)
            if (r0 >= 0) goto L_0x0020
            r10 = -1
            return r10
        L_0x0020:
            if (r10 >= 0) goto L_0x0023
            return r0
        L_0x0023:
            com.b.a.b.f r2 = r9.h
            java.lang.String r11 = r2.a((int) r11)
            int r2 = r9.D
            int r3 = r11.length()
            r4 = 0
            r6 = r2
            r2 = 0
            r5 = 0
        L_0x0033:
            if (r2 >= r3) goto L_0x0072
            char r7 = r11.charAt(r2)
            switch(r7) {
                case 9: goto L_0x0061;
                case 10: goto L_0x005c;
                case 32: goto L_0x0057;
                case 55356: goto L_0x0040;
                case 55357: goto L_0x0040;
                case 65535: goto L_0x005c;
                default: goto L_0x003c;
            }
        L_0x003c:
            if (r5 == 0) goto L_0x0067
            r5 = 0
            goto L_0x006c
        L_0x0040:
            r5 = 2
            char[] r8 = new char[r5]
            r8[r4] = r7
            int r7 = r2 + 1
            char r7 = r11.charAt(r7)
            r8[r1] = r7
            android.graphics.Paint r7 = r9.B
            float r5 = r7.measureText(r8, r4, r5)
            int r5 = (int) r5
            int r6 = r6 + r5
            r5 = 1
            goto L_0x006c
        L_0x0057:
            int r7 = r9.getSpaceAdvance()
            goto L_0x0065
        L_0x005c:
            int r7 = r9.getEOLAdvance()
            goto L_0x0065
        L_0x0061:
            int r7 = r9.a((int) r6)
        L_0x0065:
            int r6 = r6 + r7
            goto L_0x006c
        L_0x0067:
            int r7 = r9.getCharAdvance(r7)
            goto L_0x0065
        L_0x006c:
            if (r6 < r10) goto L_0x006f
            goto L_0x0072
        L_0x006f:
            int r2 = r2 + 1
            goto L_0x0033
        L_0x0072:
            int r10 = r11.length()
            if (r2 >= r10) goto L_0x007a
            int r0 = r0 + r2
            return r0
        L_0x007a:
            int r0 = r0 + r2
            int r0 = r0 - r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.c.a(int, int):int");
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (z2) {
            inputMethodManager.showSoftInput(this, 0);
        } else {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        r6 = r6 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        if (r6 < r11) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005c, code lost:
        r4 = r4 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b(int r11, int r12) {
        /*
            r10 = this;
            int r0 = r10.a()
            int r12 = r12 / r0
            com.b.a.b.f r0 = r10.h
            int r0 = r0.d(r12)
            r1 = -1
            if (r0 < 0) goto L_0x0067
            if (r11 >= 0) goto L_0x0011
            return r1
        L_0x0011:
            com.b.a.b.f r2 = r10.h
            java.lang.String r12 = r2.a((int) r12)
            int r2 = r12.length()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x001f:
            if (r4 >= r2) goto L_0x005f
            char r7 = r12.charAt(r4)
            r8 = 1
            switch(r7) {
                case 9: goto L_0x004e;
                case 10: goto L_0x0049;
                case 32: goto L_0x0044;
                case 55356: goto L_0x002d;
                case 55357: goto L_0x002d;
                case 65535: goto L_0x0049;
                default: goto L_0x0029;
            }
        L_0x0029:
            if (r5 == 0) goto L_0x0054
            r5 = 0
            goto L_0x0059
        L_0x002d:
            r5 = 2
            char[] r9 = new char[r5]
            r9[r3] = r7
            int r7 = r4 + 1
            char r7 = r12.charAt(r7)
            r9[r8] = r7
            android.graphics.Paint r7 = r10.B
            float r5 = r7.measureText(r9, r3, r5)
            int r5 = (int) r5
            int r6 = r6 + r5
            r5 = 1
            goto L_0x0059
        L_0x0044:
            int r7 = r10.getSpaceAdvance()
            goto L_0x0052
        L_0x0049:
            int r7 = r10.getEOLAdvance()
            goto L_0x0052
        L_0x004e:
            int r7 = r10.a((int) r6)
        L_0x0052:
            int r6 = r6 + r7
            goto L_0x0059
        L_0x0054:
            int r7 = r10.getCharAdvance(r7)
            goto L_0x0052
        L_0x0059:
            if (r6 < r11) goto L_0x005c
            goto L_0x005f
        L_0x005c:
            int r4 = r4 + 1
            goto L_0x001f
        L_0x005f:
            int r11 = r12.length()
            if (r4 >= r11) goto L_0x0067
            int r0 = r0 + r4
            return r0
        L_0x0067:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.c.b(int, int):int");
    }

    /* access modifiers changed from: protected */
    public m b(int i2) {
        int i3;
        int i4;
        int b2 = this.h.b(i2);
        int d2 = this.h.d(b2);
        int i5 = this.D;
        int i6 = this.D;
        String a2 = this.h.a(b2);
        int length = a2.length();
        int i7 = i6;
        boolean z2 = false;
        int i8 = i5;
        int i9 = 0;
        while (d2 + i9 <= i2 && i9 < length) {
            char charAt = a2.charAt(i9);
            switch (charAt) {
                case 9:
                    i4 = a(i7);
                    break;
                case 10:
                case 65535:
                    i4 = getEOLAdvance();
                    break;
                case ' ':
                    i4 = getSpaceAdvance();
                    break;
                case 55356:
                case 55357:
                    i3 = ((int) this.B.measureText(new char[]{charAt, a2.charAt(i9 + 1)}, 0, 2)) + i7;
                    z2 = true;
                    continue;
                default:
                    if (!z2) {
                        i4 = getCharAdvance(charAt);
                        break;
                    } else {
                        i3 = i7;
                        z2 = false;
                        continue;
                    }
            }
            i3 = i4 + i7;
            i9++;
            int i10 = i7;
            i7 = i3;
            i8 = i10;
        }
        return new m(i8, i7);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        removeCallbacks(this.T);
        removeCallbacks(this.U);
        removeCallbacks(this.V);
        removeCallbacks(this.W);
    }

    /* access modifiers changed from: package-private */
    public Rect c(int i2) {
        if (i2 < 0 || i2 >= this.h.g()) {
            return new Rect(-1, -1, -1, -1);
        }
        int b2 = this.h.b(i2) * a();
        m b3 = b(i2);
        return new Rect(b3.a(), b2, b3.b(), a() + b2);
    }

    /* access modifiers changed from: package-private */
    public void c(int i2, int i3) {
        this.v.fling(getScrollX(), getScrollY(), i2, i3, 0, getMaxScrollX(), 0, getMaxScrollY());
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return this.A == 0;
    }

    public void cancelSpanning() {
        this.w.b();
    }

    public void computeScroll() {
        if (this.v.computeScrollOffset()) {
            scrollTo(this.v.getCurrX(), this.v.getCurrY());
            postInvalidate();
        }
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollOffset() {
        return getScrollY();
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollRange() {
        return (this.h.f() * a()) + getPaddingTop() + getPaddingBottom();
    }

    public void copy() {
        if (this.j != this.k) {
            this.w.b(this.G);
        }
        selectText(false);
    }

    public void copy(ClipboardManager clipboardManager) {
        this.w.b(clipboardManager);
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo() {
        AccessibilityNodeInfo createAccessibilityNodeInfo = super.createAccessibilityNodeInfo();
        if (Build.VERSION.SDK_INT > 20) {
            createAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            createAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
            createAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
            createAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
        } else if (Build.VERSION.SDK_INT > 15) {
            createAccessibilityNodeInfo.addAction(4096);
            createAccessibilityNodeInfo.addAction(8192);
            createAccessibilityNodeInfo.addAction(256);
            createAccessibilityNodeInfo.addAction(512);
        }
        if (Build.VERSION.SDK_INT >= 18) {
            createAccessibilityNodeInfo.setTextSelection(getSelectionStart(), getSelectionEnd());
        }
        createAccessibilityNodeInfo.setFocusable(true);
        if (Build.VERSION.SDK_INT >= 18) {
            createAccessibilityNodeInfo.setEditable(true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            createAccessibilityNodeInfo.setMultiLine(true);
        }
        return createAccessibilityNodeInfo;
    }

    public f createDocumentProvider() {
        return new f(this.h);
    }

    public void cut() {
        if (this.j != this.k) {
            this.w.a(this.G);
        }
    }

    public void cut(ClipboardManager clipboardManager) {
        this.w.a(clipboardManager);
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return this.A == this.h.f() - 1;
    }

    /* access modifiers changed from: package-private */
    public boolean d(int i2) {
        Runnable runnable;
        switch (i2) {
            case 0:
                removeCallbacks(this.U);
                if (!c()) {
                    runnable = this.U;
                    break;
                } else {
                    return false;
                }
            case 1:
                removeCallbacks(this.T);
                if (!d()) {
                    runnable = this.T;
                    break;
                } else {
                    return false;
                }
            case 2:
                removeCallbacks(this.V);
                if (this.i > 0 && this.A == this.h.b(this.i - 1)) {
                    runnable = this.V;
                    break;
                } else {
                    return false;
                }
            case 3:
                removeCallbacks(this.W);
                if (!e() && this.A == this.h.b(this.i + 1)) {
                    runnable = this.W;
                    break;
                } else {
                    return false;
                }
            default:
                q.a("Invalid scroll direction");
                return false;
        }
        post(runnable);
        return true;
    }

    /* access modifiers changed from: protected */
    public int e(int i2) {
        int b2 = this.h.b(i2);
        q.a(b2 >= 0, "Invalid char offset given to getColumn");
        return i2 - this.h.d(b2);
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return this.i == this.h.g() - 1;
    }

    public void focusCaret() {
        h(this.i);
    }

    public void focusSelectionEnd() {
        this.w.d(false);
    }

    public void focusSelectionStart() {
        this.w.d(true);
    }

    public void format() {
        selectText(false);
        CharSequence a2 = com.b.a.b.a.a(new f(this.h), this.f27q);
        this.h.d();
        this.h.a(0, this.h.g() - 1, System.nanoTime());
        this.h.a(a2.toString().toCharArray(), 0, System.nanoTime());
        this.h.e();
        this.h.h();
        respan();
        invalidate();
    }

    public int getAdvance(char c2) {
        float measureText;
        switch (c2) {
            case 9:
                return getTabAdvance();
            case 10:
            case 65535:
                return getEOLAdvance();
            case ' ':
                return getSpaceAdvance();
            case 55356:
            case 55357:
                return 0;
            default:
                if (this.P != 0) {
                    measureText = this.B.measureText(new char[]{this.P, c2}, 0, 2);
                } else {
                    measureText = this.B.measureText(new char[]{c2}, 0, 1);
                }
                return (int) measureText;
        }
    }

    public int getAdvance(char c2, int i2) {
        float measureText;
        switch (c2) {
            case 9:
                return a(i2);
            case 10:
            case 65535:
                return getEOLAdvance();
            case ' ':
                return getSpaceAdvance();
            case 55356:
            case 55357:
                return 0;
            default:
                if (this.P != 0) {
                    measureText = this.B.measureText(new char[]{this.P, c2}, 0, 2);
                } else {
                    measureText = this.B.measureText(new char[]{c2}, 0, 1);
                }
                return (int) measureText;
        }
    }

    public int getAutoIndentWidth() {
        return this.f27q;
    }

    public int getCaretPosition() {
        return this.i;
    }

    public int getCaretRow() {
        return this.A;
    }

    public int getCaretX() {
        return this.I;
    }

    public int getCaretY() {
        return this.J;
    }

    public int getCharAdvance(char c2) {
        return (int) this.B.measureText(new char[]{c2}, 0, 1);
    }

    public com.b.a.b.b getColorScheme() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public int getContentHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    /* access modifiers changed from: protected */
    public int getContentWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    /* access modifiers changed from: protected */
    public int getEOLAdvance() {
        return (int) (this.o ? this.B.measureText("↵", 0, "↵".length()) : a * this.B.measureText(" ", 0, 1));
    }

    public int getLeftOffset() {
        return this.D;
    }

    public int getLength() {
        return this.h.g();
    }

    /* access modifiers changed from: package-private */
    public int getMaxScrollX() {
        return isWordWrap() ? this.D : Math.max(0, (this.C - getContentWidth()) + this.g.a().right + this.S);
    }

    /* access modifiers changed from: package-private */
    public int getMaxScrollY() {
        return Math.max(0, ((this.h.f() * a()) - (getContentHeight() / 2)) + this.g.a().bottom);
    }

    /* access modifiers changed from: protected */
    public int getNumVisibleRows() {
        return (int) Math.ceil(((double) getContentHeight()) / ((double) a()));
    }

    public int getPaintBaseline(int i2) {
        return ((i2 + 1) * a()) - this.B.getFontMetricsInt().descent;
    }

    public final int getRowWidth() {
        return getContentWidth() - this.D;
    }

    public int getSelectionEnd() {
        return this.k < 0 ? this.i : this.k;
    }

    public int getSelectionStart() {
        return this.j < 0 ? this.i : this.j;
    }

    /* access modifiers changed from: protected */
    public int getSpaceAdvance() {
        return this.o ? (int) this.B.measureText("·", 0, "·".length()) : this.aa;
    }

    /* access modifiers changed from: protected */
    public int getTabAdvance() {
        int i2;
        int i3;
        if (this.o) {
            i2 = this.l;
            i3 = (int) this.B.measureText("·", 0, "·".length());
        } else {
            i2 = this.l;
            i3 = this.aa;
        }
        return i2 * i3;
    }

    public float getTextSize() {
        return this.B.getTextSize();
    }

    public int getTopOffset() {
        return this.L;
    }

    public Parcelable getUiState() {
        return new C0005c(this);
    }

    public float getZoom() {
        return this.H;
    }

    public boolean hasLayout() {
        return getWidth() == 0;
    }

    public boolean inSelectionRange(int i2) {
        return this.w.b(i2);
    }

    public boolean isAccessibilityEnabled() {
        return this.ac;
    }

    public boolean isEdited() {
        return this.f;
    }

    public boolean isFlingScrolling() {
        return !this.v.isFinished();
    }

    public boolean isSaveEnabled() {
        return true;
    }

    public final boolean isSelectText() {
        return this.w.g();
    }

    public final boolean isSelectText2() {
        return this.w.h();
    }

    public boolean isShowLineNumbers() {
        return this.E;
    }

    public boolean isWordWrap() {
        return this.h.j();
    }

    public void moveCaret(int i2) {
        this.w.a(i2);
    }

    public void moveCaretDown() {
        this.w.c();
    }

    public void moveCaretLeft() {
        this.w.b(false);
    }

    public void moveCaretRight() {
        this.w.a(false);
    }

    public void moveCaretUp() {
        this.w.d();
    }

    public boolean onCheckIsTextEditor() {
        return true;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        editorInfo.inputType = 131073;
        editorInfo.imeOptions = 1342177286;
        if (this.x == null) {
            this.x = new b(this);
        } else {
            this.x.a();
        }
        return this.x;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.save();
        canvas.clipRect(getScrollX() + getPaddingLeft(), getScrollY() + getPaddingTop(), (getScrollX() + getWidth()) - getPaddingRight(), (getScrollY() + getHeight()) - getPaddingBottom());
        canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
        c(canvas);
        canvas.restore();
        this.g.a(canvas);
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z2, int i2, Rect rect) {
        super.onFocusChanged(z2, i2, rect);
        h();
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() != 8) {
            return super.onGenericMotionEvent(motionEvent);
        }
        a(0.0f, (-motionEvent.getAxisValue(9)) * ((float) a()));
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (this.ac) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            int action = motionEvent.getAction();
            if (action != 7) {
                switch (action) {
                    case 9:
                        this.ad = motionEvent;
                        break;
                    case 10:
                        this.g.a(motionEvent);
                        break;
                }
            } else {
                this.g.onScroll(this.ad, motionEvent, this.ae - x2, this.af - y2);
            }
            this.ae = x2;
            this.af = y2;
        }
        return super.onHoverEvent(motionEvent);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.g.a(i2, keyEvent)) {
            return true;
        }
        if (d.b(keyEvent)) {
            a(i2, keyEvent);
            return true;
        } else if (i2 == 63 || i2 == 61185) {
            a(u.get(61185), false);
            return true;
        } else {
            char a2 = d.a(keyEvent);
            if (a2 == 0) {
                return super.onKeyDown(i2, keyEvent);
            }
            int repeatCount = keyEvent.getRepeatCount();
            if (repeatCount != 1) {
                if (repeatCount == 0 || ((this.r && !Character.isLowerCase(a2)) || (!this.r && u.get(a2) == null))) {
                    this.w.a(a2);
                }
                return true;
            } else if (this.r) {
                a(a2);
                return true;
            } else {
                b(a2);
                return true;
            }
        }
    }

    public boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
        if (this.r && keyEvent.getRepeatCount() == 1 && keyEvent.getAction() == 0) {
            char a2 = d.a(keyEvent);
            if (Character.isLowerCase(a2) && a2 == Character.toLowerCase(this.h.charAt(this.i - 1))) {
                this.w.a(8);
                this.w.a(Character.toUpperCase(a2));
                return true;
            }
        }
        return super.onKeyPreIme(i2, keyEvent);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.g.b(i2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (z2) {
            Rect rect = new Rect();
            getWindowVisibleDisplayFrame(rect);
            this.L = (rect.top + rect.height()) - getHeight();
            if (!this.Q) {
                respan();
            }
            this.Q = i4 > 0;
            invalidate();
            this.s.c(getWidth() / 2);
        }
        super.onLayout(z2, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(f(i2), f(i3));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.h.j() && i4 != i2) {
            this.h.k();
        }
        this.w.e();
        if (i3 < i5) {
            h(this.i);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isFocused()) {
            this.g.b(motionEvent);
            return true;
        }
        if ((motionEvent.getAction() & 255) == 1 && e((int) motionEvent.getX(), (int) motionEvent.getY())) {
            requestFocus();
        }
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        int round = Math.round(motionEvent.getX());
        int round2 = Math.round(motionEvent.getY());
        while (round > 0) {
            this.w.a(false);
            round--;
        }
        while (round < 0) {
            this.w.b(false);
            round++;
        }
        while (round2 > 0) {
            this.w.c();
            round2--;
        }
        while (round2 < 0) {
            this.w.d();
            round2++;
        }
        return true;
    }

    public void paste() {
        CharSequence text = this.G.getText();
        if (text != null) {
            this.w.a(text.toString());
        }
    }

    public void paste(String str) {
        this.w.a(str);
    }

    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT < 16) {
            return super.performAccessibilityAction(i2, bundle);
        }
        if (i2 == 256) {
            int i3 = bundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT);
            if (i3 == 1) {
                moveCaretRight();
                return true;
            } else if (i3 != 4) {
                return true;
            } else {
                moveCaretDown();
                return true;
            }
        } else if (i2 != 512) {
            return super.performAccessibilityAction(i2, bundle);
        } else {
            int i4 = bundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT);
            if (i4 == 1) {
                moveCaretLeft();
                return true;
            } else if (i4 != 4) {
                return true;
            } else {
                moveCaretUp();
                return true;
            }
        }
    }

    public void replaceText(int i2, int i3, String str) {
        this.h.d();
        this.w.a(i2, i3, str);
        this.w.f();
        this.h.e();
    }

    public void respan() {
        this.w.a();
    }

    public void restoreUiState(Parcelable parcelable) {
        C0005c cVar = (C0005c) parcelable;
        final int i2 = cVar.a;
        if (cVar.d) {
            final int i3 = cVar.e;
            final int i4 = cVar.f;
            post(new Runnable() {
                public void run() {
                    c.this.setSelectionRange(i3, i4 - i3);
                    if (i2 < i4) {
                        c.this.focusSelectionStart();
                    }
                }
            });
            return;
        }
        post(new Runnable() {
            public void run() {
                c.this.moveCaret(i2);
            }
        });
    }

    public void selectAll() {
        this.w.a(0, this.h.g() - 1, false, true);
    }

    public void selectText(boolean z2) {
        a aVar;
        boolean z3;
        if (this.w.g() && !z2) {
            i();
            aVar = this.w;
            z3 = false;
        } else if (!this.w.g() && z2) {
            h();
            aVar = this.w;
            z3 = true;
        } else {
            return;
        }
        aVar.c(z3);
    }

    public void setAutoComplete(boolean z2) {
        this.t = z2;
    }

    public void setAutoIndent(boolean z2) {
        this.p = z2;
    }

    public void setAutoIndentWidth(int i2) {
        this.f27q = i2;
    }

    public void setBoldTypeface(Typeface typeface) {
        this.N = typeface;
    }

    public void setChirality(boolean z2) {
        this.g.a(z2);
    }

    public void setColorScheme(com.b.a.b.b bVar) {
        this.m = bVar;
        this.g.a(bVar);
        setBackgroundColor(bVar.a(b.a.BACKGROUND));
    }

    public void setDocumentProvider(f fVar) {
        this.h = fVar;
        g();
        this.w.b();
        this.w.a();
        invalidate();
        if (this.ac) {
            setContentDescription(this.h);
        }
    }

    public void setEdited(boolean z2) {
        this.f = z2;
    }

    public void setHighlightCurrentRow(boolean z2) {
        this.n = z2;
        h();
    }

    public void setItalicTypeface(Typeface typeface) {
        this.O = typeface;
    }

    public void setLongPressCaps(boolean z2) {
        this.r = z2;
    }

    public void setNavigationMethod(g gVar) {
        this.g = gVar;
    }

    public void setNonPrintingCharVisibility(boolean z2) {
        if (this.o ^ z2) {
            this.o = z2;
            if (this.h.j()) {
                this.h.k();
            }
            this.w.e();
            if (!h(this.i)) {
                invalidate();
            }
        }
    }

    public void setOnSelectionChangedListener(e eVar) {
        this.z = eVar;
    }

    public void setRowListener(n nVar) {
        this.y = nVar;
    }

    public void setSelection(int i2, int i3) {
        this.w.a(i2, i3, true, false);
    }

    public void setSelectionRange(int i2, int i3) {
        this.w.a(i2, i3, true, true);
    }

    public void setShowLineNumbers(boolean z2) {
        this.E = z2;
    }

    public void setTabSpaces(int i2) {
        if (i2 >= 0) {
            this.l = i2;
            if (this.h.j()) {
                this.h.k();
            }
            this.w.e();
            if (!h(this.i)) {
                invalidate();
            }
        }
    }

    public void setTextSize(int i2) {
        if (i2 > 8 && i2 < 80) {
            float f2 = (float) i2;
            if (f2 != this.B.getTextSize()) {
                double a2 = (double) a();
                double advance = (double) getAdvance('a');
                this.H = (float) (i2 / d);
                this.B.setTextSize(f2);
                this.R.setTextSize(f2);
                if (this.h.j()) {
                    this.h.k();
                }
                this.w.e();
                scrollTo((int) (((double) getScrollX()) * (((double) getAdvance('a')) / advance)), (int) (((double) getScrollY()) * (((double) a()) / a2)));
                this.S = (int) this.B.measureText("a");
                this.aa = (int) this.B.measureText(" ");
                invalidate();
            }
        }
    }

    public void setTypeface(Typeface typeface) {
        this.M = typeface;
        this.N = Typeface.create(typeface, 1);
        this.O = Typeface.create(typeface, 2);
        this.B.setTypeface(typeface);
        this.R.setTypeface(typeface);
        if (this.h.j()) {
            this.h.k();
        }
        this.w.e();
        if (!h(this.i)) {
            invalidate();
        }
    }

    public void setWordWrap(boolean z2) {
        this.h.a(z2);
        if (z2) {
            this.C = 0;
            scrollTo(0, 0);
        }
        this.w.e();
        if (!h(this.i)) {
            invalidate();
        }
    }

    public void setZoom(float f2) {
        if (((double) f2) > 0.5d && f2 < 5.0f && f2 != this.H) {
            this.H = f2;
            float f3 = (float) ((int) (f2 * ((float) d)));
            this.B.setTextSize(f3);
            this.R.setTextSize(f3);
            if (this.h.j()) {
                this.h.k();
            }
            this.w.e();
            this.S = (int) this.B.measureText("a");
            invalidate();
        }
    }

    public final void smoothScrollBy(int i2, int i3) {
        if (getHeight() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.ab > 250) {
                int scrollY = getScrollY();
                this.v.startScroll(getScrollX(), scrollY, i2, i3);
                postInvalidate();
            } else {
                if (!this.v.isFinished()) {
                    this.v.abortAnimation();
                }
                scrollBy(i2, i3);
            }
            this.ab = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void smoothScrollTo(int i2, int i3) {
        smoothScrollBy(i2 - getScrollX(), i3 - getScrollY());
    }

    public void stopFlingScrolling() {
        this.v.forceFinished(true);
    }
}
