package android.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.androlua.util.TimerTaskX;
import com.androlua.util.TimerX;

public class RippleHelper extends Drawable implements View.OnTouchListener {
    private final DisplayMetrics a;
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    public int c;
    /* access modifiers changed from: private */
    public int d;
    private boolean e;
    private boolean f;
    /* access modifiers changed from: private */
    public int g;
    private TimerX h;
    private task i;
    /* access modifiers changed from: private */
    public Paint j;
    private float k;
    private float l;
    /* access modifiers changed from: private */
    public View m;
    private Drawable n;
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public boolean p;

    /* renamed from: q  reason: collision with root package name */
    private int f7q;
    private int r;
    /* access modifiers changed from: private */
    public int s;

    private class task extends TimerTaskX {
        private task() {
        }

        public void run() {
            RippleHelper rippleHelper;
            int c;
            int d;
            switch (RippleHelper.this.b) {
                case 1:
                    if (RippleHelper.this.p) {
                        rippleHelper = RippleHelper.this;
                        c = RippleHelper.this.g;
                        d = Math.max(RippleHelper.this.g / 16, RippleHelper.this.d);
                    } else {
                        rippleHelper = RippleHelper.this;
                        c = RippleHelper.this.g;
                        d = RippleHelper.this.d;
                    }
                    int unused = rippleHelper.g = c + d;
                    int unused2 = RippleHelper.this.s = Math.min(RippleHelper.this.o, RippleHelper.this.g / RippleHelper.this.d);
                    RippleHelper.this.m.postInvalidate();
                    return;
                case 2:
                    int unused3 = RippleHelper.this.g = RippleHelper.this.g + (RippleHelper.this.d * 4);
                    int unused4 = RippleHelper.this.s = Math.min(RippleHelper.this.o, (RippleHelper.this.g / RippleHelper.this.d) * 2);
                    RippleHelper.this.m.postInvalidate();
                    if (RippleHelper.this.g / RippleHelper.this.c >= 1) {
                        int unused5 = RippleHelper.this.g = RippleHelper.this.c;
                        int unused6 = RippleHelper.this.s = RippleHelper.this.o;
                        int unused7 = RippleHelper.this.b = 3;
                        return;
                    }
                    return;
                case 3:
                    int unused8 = RippleHelper.this.s = RippleHelper.this.s - Math.max(RippleHelper.this.s / 16, 4);
                    RippleHelper.this.j.setAlpha(RippleHelper.this.s);
                    RippleHelper.this.m.postInvalidate();
                    if (RippleHelper.this.s < 4) {
                        int unused9 = RippleHelper.this.b = 0;
                        return;
                    }
                    return;
                default:
                    int unused10 = RippleHelper.this.g = 0;
                    setEnabled(false);
                    return;
            }
        }
    }

    public RippleHelper(View view) {
        this.m = view;
        this.a = view.getResources().getDisplayMetrics();
        a();
    }

    private int a(float f2) {
        return (int) TypedValue.applyDimension(1, f2, this.a);
    }

    private void a() {
        if (this.m.isClickable()) {
            this.f = true;
        }
        this.n = this.m.getBackground();
        this.m.setBackgroundDrawable(this);
        this.m.setOnTouchListener(this);
        this.j = new Paint();
        this.j.setColor(1152035498);
        this.j.setAntiAlias(true);
        this.j.setStrokeWidth((float) a(4.0f));
        this.o = this.j.getAlpha();
        this.h = new TimerX();
        this.i = new task();
        this.h.schedule((TimerTaskX) this.i, 0, 16);
        this.i.setEnabled(false);
        this.g = 0;
    }

    public void draw(Canvas canvas) {
        if (this.n != null) {
            this.n.setBounds(getBounds());
            this.n.draw(canvas);
        }
        this.j.setColor(this.r);
        this.j.setAlpha(this.s);
        if (this.b != 0) {
            if (this.e) {
                canvas.drawCircle(this.k, this.l, (float) this.c, this.j);
            } else {
                canvas.drawRect(getBounds(), this.j);
            }
            int i2 = this.c;
            if (this.p) {
                canvas.drawCircle(this.k, this.l, (float) Math.min(this.g, this.c), this.j);
                return;
            }
            int i3 = this.g;
            int i4 = 0;
            while (true) {
                if (i3 < 0) {
                    break;
                }
                canvas.drawCircle(this.k, this.l, (float) Math.min(i3, i2), this.j);
                i4++;
                if (i4 >= 2) {
                    this.j.setShader(new RadialGradient(this.k, this.l, (float) a(6.0f), new int[]{1157627903, this.f7q, 1140850688}, (float[]) null, Shader.TileMode.MIRROR));
                    this.j.setStyle(Paint.Style.STROKE);
                    this.j.setColor(this.f7q);
                    canvas.drawCircle(this.k, this.l, (float) (this.g % i2), this.j);
                    break;
                }
                i3 -= i2;
            }
            this.j.setShader((Shader) null);
            this.j.setStyle(Paint.Style.FILL);
        }
    }

    public int getOpacity() {
        return 0;
    }

    public boolean isCircle() {
        return this.e;
    }

    public boolean isSingle() {
        return this.p;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        onTouchEvent(motionEvent);
        return false;
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        int hypot;
        if (this.m.hasOnClickListeners() || this.f) {
            switch (motionEvent.getAction()) {
                case 0:
                    Rect bounds = getBounds();
                    if (this.e) {
                        this.l = (float) (bounds.bottom / 2);
                        this.k = (float) (bounds.right / 2);
                        hypot = Math.max(bounds.bottom, bounds.right) / 2;
                    } else {
                        this.k = motionEvent.getX();
                        this.l = motionEvent.getY();
                        hypot = (int) Math.hypot((double) bounds.bottom, (double) bounds.right);
                    }
                    this.c = hypot;
                    this.d = Math.max(this.c / 60, 1);
                    this.g = 0;
                    this.i.setEnabled(true);
                    this.j.setAlpha(this.o);
                    this.b = 1;
                    return;
                case 1:
                case 3:
                case 4:
                    this.b = 2;
                    return;
                default:
                    return;
            }
        }
    }

    public void setAlpha(int i2) {
        this.o = i2;
        this.j.setAlpha(i2);
    }

    public void setBackgroundColor(int i2) {
        this.n = new ColorDrawable(i2);
    }

    public void setCircle(boolean z) {
        this.e = z;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.j.setColorFilter(colorFilter);
    }

    public void setRippleColor(int i2) {
        this.r = i2;
        this.j.setColor(i2);
        this.o = this.j.getAlpha();
    }

    public void setRippleLineColor(int i2) {
        this.f7q = i2;
    }

    public void setSingle(boolean z) {
        this.p = z;
    }
}
