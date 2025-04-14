package android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.a.a.a.a.a.a.a;

public class CircleImageView extends ImageView {
    private static final ImageView.ScaleType a = ImageView.ScaleType.CENTER_CROP;
    private static final Bitmap.Config b = Bitmap.Config.ARGB_8888;
    private final RectF c;
    private final RectF d;
    private final Matrix e;
    private final Paint f;
    private final Paint g;
    private final Paint h;
    private int i;
    private int j;
    private int k;
    private Bitmap l;
    private BitmapShader m;
    private int n;
    private int o;
    private float p;

    /* renamed from: q  reason: collision with root package name */
    private float f2q;
    private ColorFilter r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private float w;

    public CircleImageView(Context context) {
        super(context);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        this.i = ViewCompat.MEASURED_STATE_MASK;
        this.j = 0;
        this.k = 0;
        a();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        this.i = ViewCompat.MEASURED_STATE_MASK;
        this.j = 0;
        this.k = 0;
        this.j = 0;
        this.i = ViewCompat.MEASURED_STATE_MASK;
        this.u = false;
        this.k = 0;
        a();
    }

    private Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = drawable instanceof ColorDrawable ? Bitmap.createBitmap(2, 2, b) : Bitmap.createBitmap(drawable.getIntrinsicWidth() - ((int) (this.w * 2.0f)), drawable.getIntrinsicHeight() - ((int) (this.w * 2.0f)), b);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e2) {
            a.a(e2);
            return null;
        }
    }

    private void a() {
        super.setScaleType(a);
        this.s = true;
        if (this.t) {
            d();
            this.t = false;
        }
    }

    private void b() {
        if (this.f != null) {
            this.f.setColorFilter(this.r);
        }
    }

    private void c() {
        this.l = this.v ? null : a(getDrawable());
        d();
    }

    private void d() {
        if (!this.s) {
            this.t = true;
        } else if (getWidth() != 0 || getHeight() != 0) {
            if (this.l == null) {
                invalidate();
                return;
            }
            this.m = new BitmapShader(this.l, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.f.setAntiAlias(true);
            this.f.setShader(this.m);
            this.g.setStyle(Paint.Style.STROKE);
            this.g.setAntiAlias(true);
            this.g.setColor(this.i);
            this.g.setStrokeWidth((float) this.j);
            this.h.setStyle(Paint.Style.FILL);
            this.h.setAntiAlias(true);
            this.h.setColor(this.k);
            this.o = this.l.getHeight();
            this.n = this.l.getWidth();
            this.d.set(e());
            this.f2q = Math.min((this.d.height() - ((float) this.j)) / 2.0f, (this.d.width() - ((float) this.j)) / 2.0f);
            this.c.set(this.d);
            if (!this.u && this.j > 0) {
                this.c.inset(((float) this.j) - 1.0f, ((float) this.j) - 1.0f);
            }
            this.p = Math.min(this.c.height() / 2.0f, this.c.width() / 2.0f);
            b();
            f();
            invalidate();
        }
    }

    private RectF e() {
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int min = Math.min(width, height);
        float paddingLeft = ((float) getPaddingLeft()) + (((float) (width - min)) / 2.0f);
        float paddingTop = ((float) getPaddingTop()) + (((float) (height - min)) / 2.0f);
        float f2 = (float) min;
        return new RectF(paddingLeft, paddingTop, paddingLeft + f2, f2 + paddingTop);
    }

    private void f() {
        float f2;
        float f3;
        this.e.set((Matrix) null);
        float f4 = 0.0f;
        if (((float) this.n) * this.c.height() > this.c.width() * ((float) this.o)) {
            f3 = this.c.height() / ((float) this.o);
            f4 = (this.c.width() - (((float) this.n) * f3)) * 0.5f;
            f2 = 0.0f;
        } else {
            f3 = this.c.width() / ((float) this.n);
            f2 = (this.c.height() - (((float) this.o) * f3)) * 0.5f;
        }
        this.e.setScale(f3, f3);
        this.e.postTranslate(((float) ((int) (f4 + 0.5f))) + this.c.left, ((float) ((int) (f2 + 0.5f))) + this.c.top);
        this.m.setLocalMatrix(this.e);
    }

    public int getBorderColor() {
        return this.i;
    }

    public int getBorderWidth() {
        return this.j;
    }

    public ColorFilter getColorFilter() {
        return this.r;
    }

    @Deprecated
    public int getFillColor() {
        return this.k;
    }

    public ImageView.ScaleType getScaleType() {
        return a;
    }

    public boolean isBorderOverlay() {
        return this.u;
    }

    public boolean isDisableCircularTransformation() {
        return this.v;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.v) {
            super.onDraw(canvas);
        } else if (this.l != null) {
            if (this.k != 0) {
                canvas.drawCircle(this.c.centerX(), this.c.centerY(), this.p - this.w, this.h);
            }
            canvas.drawCircle(this.c.centerX(), this.c.centerY(), this.p - this.w, this.f);
            if (this.j > 0) {
                canvas.drawCircle(this.d.centerX(), this.d.centerY(), this.f2q - this.w, this.g);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        d();
    }

    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    public void setBackgroundColor(int i2) {
        setFillColor(i2);
    }

    public void setBorderColor(int i2) {
        if (i2 != this.i) {
            this.i = i2;
            this.g.setColor(this.i);
            invalidate();
        }
    }

    @Deprecated
    public void setBorderColorResource(int i2) {
        setBorderColor(getContext().getResources().getColor(i2));
    }

    public void setBorderOverlay(boolean z) {
        if (z != this.u) {
            this.u = z;
            d();
        }
    }

    public void setBorderWidth(int i2) {
        if (i2 != this.j) {
            this.j = i2;
            d();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.r) {
            this.r = colorFilter;
            b();
            invalidate();
        }
    }

    public void setDisableCircularTransformation(boolean z) {
        if (this.v != z) {
            this.v = z;
            c();
        }
    }

    public void setElevation2(float f2) {
        this.w = f2;
        this.h.setShadowLayer(f2, 0.0f, f2 / 2.0f, ViewCompat.MEASURED_STATE_MASK);
        invalidate();
    }

    @Deprecated
    public void setFillColor(int i2) {
        if (i2 != this.k) {
            this.k = i2;
            this.h.setColor(i2);
            invalidate();
        }
    }

    @Deprecated
    public void setFillColorResource(int i2) {
        setFillColor(getContext().getResources().getColor(i2));
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        c();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        c();
    }

    public void setImageResource(int i2) {
        super.setImageResource(i2);
        c();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        c();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }
}
