package com.b.a.a;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.b.a.b.b;
import com.b.a.b.f;

public class g extends GestureDetector.SimpleOnGestureListener {
    protected static int a = 10;
    protected static int b = 12;
    private static final Rect e = new Rect(0, 0, 0, 0);
    protected c c;
    protected boolean d = false;
    private GestureDetector f;
    private float g;
    private float h;
    private float i;
    private float j;
    private int k;
    private boolean l;

    private g() {
    }

    public g(c cVar) {
        this.c = cVar;
        this.f = new GestureDetector(cVar.getContext(), this);
        this.f.setIsLongpressEnabled(true);
    }

    private void a(float f2, float f3) {
        int scrollX = ((int) f2) + this.c.getScrollX();
        int scrollY = ((int) f3) + this.c.getScrollY();
        int max = Math.max(this.c.getMaxScrollX(), this.c.getScrollX());
        if (scrollX > max) {
            scrollX = max;
        } else if (scrollX < 0) {
            scrollX = 0;
        }
        int max2 = Math.max(this.c.getMaxScrollY(), this.c.getScrollY());
        if (scrollY > max2) {
            scrollY = max2;
        } else if (scrollY < 0) {
            scrollY = 0;
        }
        this.c.smoothScrollTo(scrollX, scrollY);
    }

    private final boolean b() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(android.view.MotionEvent r7) {
        /*
            r6 = this;
            com.b.a.a.c r0 = r6.c
            boolean r0 = r0.isSelectText()
            r1 = 1
            if (r0 != 0) goto L_0x0014
            boolean r0 = r6.b()
            if (r0 == 0) goto L_0x0014
            com.b.a.a.c r0 = r6.c
            r0.selectText(r1)
        L_0x0014:
            float r0 = r7.getX()
            int r0 = (int) r0
            com.b.a.a.c r2 = r6.c
            int r2 = r2.getPaddingLeft()
            int r0 = r0 - r2
            float r2 = r7.getY()
            int r2 = (int) r2
            com.b.a.a.c r3 = r6.c
            int r3 = r3.getPaddingTop()
            int r2 = r2 - r3
            int r3 = a
            r4 = 0
            if (r0 >= r3) goto L_0x0039
            com.b.a.a.c r0 = r6.c
            r1 = 2
        L_0x0034:
            boolean r4 = r0.d((int) r1)
            goto L_0x0061
        L_0x0039:
            com.b.a.a.c r3 = r6.c
            int r3 = r3.getContentWidth()
            int r5 = a
            int r3 = r3 - r5
            if (r0 < r3) goto L_0x0048
            com.b.a.a.c r0 = r6.c
            r1 = 3
            goto L_0x0034
        L_0x0048:
            int r0 = a
            if (r2 >= r0) goto L_0x0053
            com.b.a.a.c r0 = r6.c
            boolean r4 = r0.d((int) r4)
            goto L_0x0061
        L_0x0053:
            com.b.a.a.c r0 = r6.c
            int r0 = r0.getContentHeight()
            int r3 = a
            int r0 = r0 - r3
            if (r2 < r0) goto L_0x0061
            com.b.a.a.c r0 = r6.c
            goto L_0x0034
        L_0x0061:
            if (r4 != 0) goto L_0x0087
            com.b.a.a.c r0 = r6.c
            r0.b()
            com.b.a.a.c r0 = r6.c
            float r1 = r7.getX()
            int r1 = (int) r1
            int r1 = r6.a((int) r1)
            float r7 = r7.getY()
            int r7 = (int) r7
            int r7 = r6.b((int) r7)
            int r7 = r0.a((int) r1, (int) r7)
            if (r7 < 0) goto L_0x0087
            com.b.a.a.c r0 = r6.c
            r0.moveCaret(r7)
        L_0x0087:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.g.c(android.view.MotionEvent):void");
    }

    private float d(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private boolean e(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2 && motionEvent.getPointerCount() == 2) {
            if (this.g == 0.0f) {
                float x = motionEvent.getX(0) - motionEvent.getX(1);
                float y = motionEvent.getY(0) - motionEvent.getY(1);
                this.g = (float) Math.sqrt((double) ((x * x) + (y * y)));
                this.h = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
                this.i = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                this.j = this.c.getTextSize();
            }
            float d2 = d(motionEvent);
            if (this.g != 0.0f) {
                this.c.setTextSize((int) (this.j * (d2 / this.g)));
            }
            return true;
        }
        this.g = 0.0f;
        return false;
    }

    /* access modifiers changed from: protected */
    public final int a(int i2) {
        return (i2 - this.c.getPaddingLeft()) + this.c.getScrollX();
    }

    public Rect a() {
        return e;
    }

    public void a(Canvas canvas) {
    }

    public void a(b bVar) {
    }

    public void a(boolean z) {
    }

    public boolean a(int i2, int i3, int i4) {
        Rect c2 = this.c.c(i4);
        return i3 >= c2.top - b && i3 < c2.bottom + b && i2 >= c2.left - b && i2 < c2.right + b;
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        return false;
    }

    public boolean a(MotionEvent motionEvent) {
        this.c.b();
        this.d = false;
        this.l = false;
        this.g = 0.0f;
        this.k = 0;
        return true;
    }

    /* access modifiers changed from: protected */
    public final int b(int i2) {
        return (i2 - this.c.getPaddingTop()) + this.c.getScrollY();
    }

    public boolean b(int i2, KeyEvent keyEvent) {
        return false;
    }

    public boolean b(MotionEvent motionEvent) {
        e(motionEvent);
        boolean onTouchEvent = this.f.onTouchEvent(motionEvent);
        return (onTouchEvent || (motionEvent.getAction() & 255) != 1) ? onTouchEvent : a(motionEvent);
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        int i2;
        this.d = true;
        int a2 = this.c.a(a((int) motionEvent.getX()), b((int) motionEvent.getY()));
        if (this.c.isSelectText() && this.c.inSelectionRange(a2)) {
            f createDocumentProvider = this.c.createDocumentProvider();
            int c2 = createDocumentProvider.c(a2);
            i2 = createDocumentProvider.e(c2);
            a2 = createDocumentProvider.e(c2 + 1) - 1;
        } else if (a2 < 0) {
            return true;
        } else {
            this.c.moveCaret(a2);
            f createDocumentProvider2 = this.c.createDocumentProvider();
            i2 = a2;
            while (i2 >= 0 && Character.isJavaIdentifierPart(createDocumentProvider2.charAt(i2))) {
                i2--;
            }
            if (i2 != a2) {
                i2++;
            }
            while (a2 >= 0 && Character.isJavaIdentifierPart(createDocumentProvider2.charAt(a2))) {
                a2++;
            }
            this.c.selectText(true);
        }
        this.c.setSelectionRange(i2, a2 - i2);
        return true;
    }

    public boolean onDown(MotionEvent motionEvent) {
        int a2 = a((int) motionEvent.getX());
        int b2 = b((int) motionEvent.getY());
        this.d = a(a2, b2, this.c.getCaretPosition());
        this.l = a2 < this.c.getLeftOffset();
        if (this.c.isFlingScrolling()) {
            this.c.stopFlingScrolling();
        } else if (this.c.isSelectText()) {
            if (a(a2, b2, this.c.getSelectionStart())) {
                this.c.focusSelectionStart();
            } else if (a(a2, b2, this.c.getSelectionEnd())) {
                this.c.focusSelectionEnd();
            }
            this.c.performHapticFeedback(0);
            this.d = true;
        }
        if (this.d) {
            this.c.performHapticFeedback(0);
        }
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (!this.d) {
            if (this.k == 1) {
                f3 = 0.0f;
            } else if (this.k == -1) {
                f2 = 0.0f;
            }
            this.c.c(((int) (-f2)) * 2, ((int) (-f3)) * 2);
        }
        a(motionEvent2);
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        onDoubleTap(motionEvent);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (this.d) {
            c(motionEvent2);
        } else if (motionEvent2.getPointerCount() == 1) {
            if (this.k == 0) {
                if (Math.abs(f2) > Math.abs(f3)) {
                    this.k = 1;
                } else {
                    this.k = -1;
                }
            }
            if (this.k == 1) {
                f3 = 0.0f;
            } else if (this.k == -1) {
                f2 = 0.0f;
            }
            if (this.l) {
                f3 *= (float) (this.c.getMaxScrollY() / this.c.getHeight());
            }
            a(f2, f3);
        }
        if ((motionEvent2.getAction() & 255) == 1) {
            a(motionEvent2);
        }
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        if (r3 >= 0) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0064, code lost:
        if (r2 >= 0) goto L_0x005e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onSingleTapUp(android.view.MotionEvent r6) {
        /*
            r5 = this;
            com.b.a.a.c r0 = r5.c
            boolean r0 = r0.isAccessibilityEnabled()
            r1 = 1
            if (r0 == 0) goto L_0x000f
            com.b.a.a.c r6 = r5.c
            r6.a((boolean) r1)
            return r1
        L_0x000f:
            float r0 = r6.getX()
            int r0 = (int) r0
            int r0 = r5.a((int) r0)
            float r6 = r6.getY()
            int r6 = (int) r6
            int r6 = r5.b((int) r6)
            com.b.a.a.c r2 = r5.c
            int r2 = r2.a((int) r0, (int) r6)
            com.b.a.a.c r3 = r5.c
            boolean r3 = r3.isSelectText()
            if (r3 == 0) goto L_0x0064
            com.b.a.a.c r3 = r5.c
            int r3 = r3.b((int) r0, (int) r6)
            com.b.a.a.c r4 = r5.c
            boolean r4 = r4.inSelectionRange(r3)
            if (r4 != 0) goto L_0x0067
            com.b.a.a.c r4 = r5.c
            int r4 = r4.getSelectionStart()
            boolean r4 = r5.a(r0, r6, r4)
            if (r4 != 0) goto L_0x0067
            com.b.a.a.c r4 = r5.c
            int r4 = r4.getSelectionEnd()
            boolean r6 = r5.a(r0, r6, r4)
            if (r6 == 0) goto L_0x0056
            goto L_0x0067
        L_0x0056:
            com.b.a.a.c r6 = r5.c
            r0 = 0
            r6.selectText(r0)
            if (r3 < 0) goto L_0x0067
        L_0x005e:
            com.b.a.a.c r6 = r5.c
            r6.moveCaret(r2)
            goto L_0x0067
        L_0x0064:
            if (r2 < 0) goto L_0x0067
            goto L_0x005e
        L_0x0067:
            com.b.a.a.c r6 = r5.c
            r6.a((boolean) r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.a.g.onSingleTapUp(android.view.MotionEvent):boolean");
    }
}
