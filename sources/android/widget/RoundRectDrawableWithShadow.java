package android.widget;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;

class RoundRectDrawableWithShadow extends Drawable {
    static final double a = Math.cos(Math.toRadians(45.0d));
    static RoundRectHelper c;
    final int b;
    Paint d;
    Paint e;
    Paint f;
    final RectF g;
    float h;
    Path i;
    float j;
    float k;
    float l;
    float m;
    private boolean n = true;
    private final int o = 922746880;
    private final int p = 50331648;

    /* renamed from: q  reason: collision with root package name */
    private boolean f8q = true;
    private boolean r = false;
    private DisplayMetrics s;

    interface RoundRectHelper {
        void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint);
    }

    RoundRectDrawableWithShadow(Resources resources, int i2, float f2, float f3, float f4) {
        this.s = resources.getDisplayMetrics();
        this.b = (int) d(1.0f);
        this.d = new Paint(5);
        this.d.setColor(i2);
        this.e = new Paint(5);
        this.e.setStyle(Paint.Style.FILL);
        this.h = (float) ((int) (f2 + 0.5f));
        this.g = new RectF();
        this.f = new Paint(this.e);
        this.f.setAntiAlias(false);
        a(f3, f4);
    }

    static float a(float f2, float f3, boolean z) {
        return z ? (float) (((double) (f2 * 1.5f)) + ((1.0d - a) * ((double) f3))) : f2 * 1.5f;
    }

    private void a(Canvas canvas) {
        float f2 = (-this.h) - this.l;
        float f3 = this.h + ((float) this.b) + (this.m / 2.0f);
        float f4 = f3 * 2.0f;
        boolean z = this.g.width() - f4 > 0.0f;
        boolean z2 = this.g.height() - f4 > 0.0f;
        int save = canvas.save();
        canvas.translate(this.g.left + f3, this.g.top + f3);
        canvas.drawPath(this.i, this.e);
        if (z) {
            canvas.drawRect(0.0f, f2, this.g.width() - f4, -this.h, this.f);
        }
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        canvas.translate(this.g.right - f3, this.g.bottom - f3);
        canvas.rotate(180.0f);
        canvas.drawPath(this.i, this.e);
        if (z) {
            canvas.drawRect(0.0f, f2, this.g.width() - f4, (-this.h) + this.l, this.f);
        }
        canvas.restoreToCount(save2);
        int save3 = canvas.save();
        canvas.translate(this.g.left + f3, this.g.bottom - f3);
        canvas.rotate(270.0f);
        canvas.drawPath(this.i, this.e);
        if (z2) {
            canvas.drawRect(0.0f, f2, this.g.height() - f4, -this.h, this.f);
        }
        canvas.restoreToCount(save3);
        int save4 = canvas.save();
        canvas.translate(this.g.right - f3, this.g.top + f3);
        canvas.rotate(90.0f);
        canvas.drawPath(this.i, this.e);
        if (z2) {
            canvas.drawRect(0.0f, f2, this.g.height() - f4, -this.h, this.f);
        }
        canvas.restoreToCount(save4);
    }

    static float b(float f2, float f3, boolean z) {
        return z ? (float) (((double) f2) + ((1.0d - a) * ((double) f3))) : f2;
    }

    private void b(Rect rect) {
        float f2 = this.k * 1.5f;
        this.g.set(((float) rect.left) + this.k, ((float) rect.top) + f2, ((float) rect.right) - this.k, ((float) rect.bottom) - f2);
        f();
    }

    private float d(float f2) {
        return TypedValue.applyDimension(1, f2, this.s);
    }

    private int e(float f2) {
        int i2 = (int) (f2 + 0.5f);
        return i2 % 2 == 1 ? i2 - 1 : i2;
    }

    private void f() {
        RectF rectF = new RectF(-this.h, -this.h, this.h, this.h);
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(-this.l, -this.l);
        if (this.i == null) {
            this.i = new Path();
        } else {
            this.i.reset();
        }
        this.i.setFillType(Path.FillType.EVEN_ODD);
        this.i.moveTo(-this.h, 0.0f);
        this.i.rLineTo(-this.l, 0.0f);
        this.i.arcTo(rectF2, 180.0f, 90.0f, false);
        this.i.arcTo(rectF, 270.0f, -90.0f, false);
        this.i.close();
        float f2 = this.h / (this.h + this.l);
        this.e.setShader(new RadialGradient(0.0f, 0.0f, this.h + this.l, new int[]{this.o, this.o, this.p}, new float[]{0.0f, f2, 1.0f}, Shader.TileMode.CLAMP));
        this.f.setShader(new LinearGradient(0.0f, (-this.h) + this.l, 0.0f, (-this.h) - this.l, new int[]{this.o, this.o, this.p}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.f.setAntiAlias(false);
    }

    /* access modifiers changed from: package-private */
    public float a() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public void a(float f2) {
        float f3 = (float) ((int) (f2 + 0.5f));
        if (this.h != f3) {
            this.h = f3;
            this.n = true;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(float f2, float f3) {
        if (f2 < 0.0f || f3 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float e2 = (float) e(f2);
        float e3 = (float) e(f3);
        if (e2 > e3) {
            if (!this.r) {
                this.r = true;
            }
            e2 = e3;
        }
        if (this.m != e2 || this.k != e3) {
            this.m = e2;
            this.k = e3;
            this.l = (float) ((int) ((e2 * 1.5f) + ((float) this.b) + 0.5f));
            this.j = e3 + ((float) this.b);
            this.n = true;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Rect rect) {
        getPadding(rect);
    }

    /* access modifiers changed from: package-private */
    public float b() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public void b(float f2) {
        a(f2, this.k);
    }

    /* access modifiers changed from: package-private */
    public float c() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public void c(float f2) {
        a(this.m, f2);
    }

    /* access modifiers changed from: package-private */
    public float d() {
        return (Math.max(this.k, this.h + ((float) this.b) + (this.k / 2.0f)) * 2.0f) + ((this.k + ((float) this.b)) * 2.0f);
    }

    public void draw(Canvas canvas) {
        if (this.n) {
            b(getBounds());
            this.n = false;
        }
        canvas.translate(0.0f, this.m / 2.0f);
        a(canvas);
        canvas.translate(0.0f, (-this.m) / 2.0f);
        c.drawRoundRect(canvas, this.g, this.h, this.d);
    }

    /* access modifiers changed from: package-private */
    public float e() {
        return (Math.max(this.k, this.h + ((float) this.b) + ((this.k * 1.5f) / 2.0f)) * 2.0f) + (((this.k * 1.5f) + ((float) this.b)) * 2.0f);
    }

    public int getOpacity() {
        return -3;
    }

    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil((double) a(this.k, this.h, this.f8q));
        int ceil2 = (int) Math.ceil((double) b(this.k, this.h, this.f8q));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.n = true;
    }

    public void setAddPaddingForCorners(boolean z) {
        this.f8q = z;
        invalidateSelf();
    }

    public void setAlpha(int i2) {
        this.d.setAlpha(i2);
        this.e.setAlpha(i2);
        this.f.setAlpha(i2);
    }

    public void setColor(int i2) {
        this.d.setColor(i2);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
        this.e.setColorFilter(colorFilter);
        this.f.setColorFilter(colorFilter);
    }
}
