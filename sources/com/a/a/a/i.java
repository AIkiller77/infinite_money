package com.a.a.a;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;
import android.view.MotionEvent;
import com.a.a.b.b;
import com.a.a.b.m;

public class i extends g {
    private final a e;
    private final a f;
    private final a g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    /* access modifiers changed from: private */
    public int l;

    private class a {
        public final Rect a = new Rect(a(), 0, 0, this.d.bottom + this.c);
        private final int c = (i.this.l / 3);
        private final Rect d = new Rect(0, 0, i.this.l, i.this.l);
        private int e = 0;
        private int f = 0;
        private int g = 0;
        private int h = 0;
        private int i = 0;
        private int j = 0;
        private final Paint k = new Paint();
        private boolean l;

        public a() {
            this.k.setColor(i.this.c.getColorScheme().a(b.a.CARET_BACKGROUND));
            this.k.setAntiAlias(true);
        }

        private void f() {
            int i2;
            int i3;
            int i4;
            int a2 = this.g + a();
            if (a2 >= this.e) {
                i2 = a2 + 1;
                a2 = this.e;
            } else {
                i2 = this.e + 1;
            }
            if (this.h >= this.f) {
                i4 = this.f;
                i3 = this.h;
            } else {
                i4 = this.h;
                i3 = this.f;
            }
            i.this.c.invalidate(a2, i4, i2, i3);
            b();
        }

        public final int a() {
            return this.d.right / 2;
        }

        public void a(int i2) {
            this.k.setColor(i2);
        }

        public void a(int i2, int i3) {
            f();
            b(i2, i3);
            f();
        }

        public void a(Canvas canvas, boolean z) {
            int a2 = a();
            canvas.drawLine((float) this.e, (float) this.f, (float) (this.g + a2), (float) (this.h + a2), this.k);
            int i2 = a2 / 2;
            canvas.drawArc(new RectF((float) (this.e - a2), (float) ((this.f - i2) - this.c), (float) (this.g + (a2 * 2)), (float) (this.h + i2)), 60.0f, 60.0f, true, this.k);
            canvas.drawOval(new RectF((float) this.g, (float) this.h, (float) (this.g + this.d.right), (float) (this.h + this.d.bottom)), this.k);
        }

        public void b() {
            i.this.c.invalidate(new Rect(this.g, this.h, this.g + this.d.right, this.h + this.d.bottom));
        }

        public void b(int i2, int i3) {
            this.e = i2;
            this.f = i3;
            this.g = i2 - a();
            this.h = i3 + this.c;
        }

        public m c(int i2, int i3) {
            int a2 = (i.this.a(i2) - this.i) + a();
            int b2 = ((i.this.b(i3) - this.j) - this.c) - 2;
            return new m(i.this.c.a(a2, b2), i.this.c.b(a2, b2));
        }

        public void c() {
            this.i = 0;
            this.j = 0;
        }

        public void d() {
            this.l = true;
        }

        public void d(int i2, int i3) {
            this.i = i2 - this.g;
            this.j = i3 - this.h;
        }

        public void e() {
            this.l = false;
        }

        public boolean e(int i2, int i3) {
            return this.l && i2 >= this.g && i2 < this.g + this.d.right && i3 >= this.h && i3 < this.h + this.d.bottom;
        }
    }

    public i(c cVar) {
        super(cVar);
        this.l = (int) TypedValue.applyDimension(2, (float) (((double) c.d) * 1.5d), cVar.getContext().getResources().getDisplayMetrics());
        this.e = new a();
        this.f = new a();
        this.g = new a();
    }

    private void a(a aVar, MotionEvent motionEvent) {
        int a2 = aVar.c((int) motionEvent.getX(), (int) motionEvent.getY()).a();
        if (a2 >= 0) {
            this.c.moveCaret(a2);
            Rect c = this.c.c(a2);
            aVar.a(c.left + this.c.getPaddingLeft(), c.bottom + this.c.getPaddingTop());
        }
    }

    public Rect a() {
        return this.e.a;
    }

    public void a(Canvas canvas) {
        if (!this.c.isSelectText2()) {
            this.e.d();
            this.f.e();
            this.g.e();
            if (!this.j) {
                Rect c = this.c.c(this.c.getCaretPosition());
                this.e.b(c.left + this.c.getPaddingLeft(), c.bottom + this.c.getPaddingTop());
            }
            if (this.k) {
                this.e.a(canvas, this.j);
            }
            this.k = false;
            return;
        }
        this.e.e();
        this.f.d();
        this.g.d();
        if (!this.h || !this.i) {
            Rect c2 = this.c.c(this.c.getSelectionStart());
            this.f.b(c2.left + this.c.getPaddingLeft(), c2.bottom + this.c.getPaddingTop());
            Rect c3 = this.c.c(this.c.getSelectionEnd());
            this.g.b(c3.left + this.c.getPaddingLeft(), c3.bottom + this.c.getPaddingTop());
        }
        this.f.a(canvas, this.h);
        this.g.a(canvas, this.h);
    }

    public void a(b bVar) {
        this.e.a(bVar.a(b.a.CARET_BACKGROUND));
    }

    public boolean a(MotionEvent motionEvent) {
        this.j = false;
        this.h = false;
        this.i = false;
        this.e.c();
        this.f.c();
        this.g.c();
        super.a(motionEvent);
        return true;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        int x = ((int) motionEvent.getX()) + this.c.getScrollX();
        int y = ((int) motionEvent.getY()) + this.c.getScrollY();
        if (this.e.e(x, y)) {
            this.c.selectText(true);
            return true;
        } else if (this.f.e(x, y)) {
            return true;
        } else {
            return super.onDoubleTap(motionEvent);
        }
    }

    public boolean onDown(MotionEvent motionEvent) {
        a aVar;
        super.onDown(motionEvent);
        if (!this.d) {
            int x = ((int) motionEvent.getX()) + this.c.getScrollX();
            int y = ((int) motionEvent.getY()) + this.c.getScrollY();
            this.j = this.e.e(x, y);
            this.h = this.f.e(x, y);
            this.i = this.g.e(x, y);
            if (this.j) {
                this.k = true;
                this.e.d(x, y);
                aVar = this.e;
            } else if (this.h) {
                this.f.d(x, y);
                this.c.focusSelectionStart();
                aVar = this.f;
            } else if (this.i) {
                this.g.d(x, y);
                this.c.focusSelectionEnd();
                aVar = this.g;
            }
            aVar.b();
            return true;
        }
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (!this.j && !this.h && !this.i) {
            return super.onFling(motionEvent, motionEvent2, f2, f3);
        }
        a(motionEvent2);
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        onDoubleTap(motionEvent);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (this.j) {
            if ((motionEvent2.getAction() & 255) == 1) {
                a(motionEvent2);
                return true;
            }
            this.k = true;
            a(this.e, motionEvent2);
            return true;
        } else if (this.h) {
            if ((motionEvent2.getAction() & 255) == 1) {
                a(motionEvent2);
                return true;
            }
            a(this.f, motionEvent2);
            return true;
        } else if (!this.i) {
            return super.onScroll(motionEvent, motionEvent2, f2, f3);
        } else {
            if ((motionEvent2.getAction() & 255) == 1) {
                a(motionEvent2);
                return true;
            }
            a(this.g, motionEvent2);
            return true;
        }
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        int x = ((int) motionEvent.getX()) + this.c.getScrollX();
        int y = ((int) motionEvent.getY()) + this.c.getScrollY();
        if (this.e.e(x, y) || this.f.e(x, y) || this.g.e(x, y)) {
            return true;
        }
        this.k = true;
        return super.onSingleTapUp(motionEvent);
    }
}
