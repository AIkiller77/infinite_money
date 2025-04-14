package android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.RoundRectDrawableWithShadow;

public class CardView extends FrameLayout implements CardViewDelegate {
    private static final CardViewImpl a = (Build.VERSION.SDK_INT >= 21 ? new CardViewApi21() : Build.VERSION.SDK_INT >= 17 ? new CardViewJellybeanMr1() : new CardViewEclairMr1());
    private DisplayMetrics b;
    private boolean c;
    private boolean d;
    private final Rect e = new Rect();
    private final Rect f = new Rect();

    @SuppressLint({"NewApi"})
    static class CardViewApi21 implements CardViewImpl {
        CardViewApi21() {
        }

        public float getElevation(CardViewDelegate cardViewDelegate) {
            return ((View) cardViewDelegate).getElevation();
        }

        public float getMaxElevation(CardViewDelegate cardViewDelegate) {
            return ((RoundRectDrawable) cardViewDelegate.getBackground()).a();
        }

        public float getMinHeight(CardViewDelegate cardViewDelegate) {
            return getRadius(cardViewDelegate) * 2.0f;
        }

        public float getMinWidth(CardViewDelegate cardViewDelegate) {
            return getRadius(cardViewDelegate) * 2.0f;
        }

        public float getRadius(CardViewDelegate cardViewDelegate) {
            return ((RoundRectDrawable) cardViewDelegate.getBackground()).getRadius();
        }

        public void initStatic() {
        }

        public void initialize(CardViewDelegate cardViewDelegate, Context context, int i, float f, float f2, float f3) {
            cardViewDelegate.setBackgroundDrawable(new RoundRectDrawable(i, f));
            View view = (View) cardViewDelegate;
            view.setClipToOutline(true);
            view.setElevation(f2);
            setMaxElevation(cardViewDelegate, f3);
        }

        public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate) {
            setMaxElevation(cardViewDelegate, getMaxElevation(cardViewDelegate));
        }

        public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate) {
            setMaxElevation(cardViewDelegate, getMaxElevation(cardViewDelegate));
        }

        public void setBackgroundColor(CardViewDelegate cardViewDelegate, int i) {
            ((RoundRectDrawable) cardViewDelegate.getBackground()).setColor(i);
        }

        public void setElevation(CardViewDelegate cardViewDelegate, float f) {
            ((View) cardViewDelegate).setElevation(f);
        }

        public void setMaxElevation(CardViewDelegate cardViewDelegate, float f) {
            ((RoundRectDrawable) cardViewDelegate.getBackground()).a(f, cardViewDelegate.getUseCompatPadding(), cardViewDelegate.getPreventCornerOverlap());
            updatePadding(cardViewDelegate);
        }

        public void setRadius(CardViewDelegate cardViewDelegate, float f) {
            ((RoundRectDrawable) cardViewDelegate.getBackground()).a(f);
        }

        public void updatePadding(CardViewDelegate cardViewDelegate) {
            if (!cardViewDelegate.getUseCompatPadding()) {
                cardViewDelegate.setShadowPadding(0, 0, 0, 0);
                return;
            }
            float maxElevation = getMaxElevation(cardViewDelegate);
            float radius = getRadius(cardViewDelegate);
            int ceil = (int) Math.ceil((double) RoundRectDrawableWithShadow.b(maxElevation, radius, cardViewDelegate.getPreventCornerOverlap()));
            int ceil2 = (int) Math.ceil((double) RoundRectDrawableWithShadow.a(maxElevation, radius, cardViewDelegate.getPreventCornerOverlap()));
            cardViewDelegate.setShadowPadding(ceil, ceil2, ceil, ceil2);
        }
    }

    static class CardViewEclairMr1 implements CardViewImpl {
        final RectF a = new RectF();

        CardViewEclairMr1() {
        }

        private RoundRectDrawableWithShadow a(CardViewDelegate cardViewDelegate) {
            return (RoundRectDrawableWithShadow) cardViewDelegate.getBackground();
        }

        /* access modifiers changed from: package-private */
        public RoundRectDrawableWithShadow a(Context context, int i, float f, float f2, float f3) {
            return new RoundRectDrawableWithShadow(context.getResources(), i, f, f2, f3);
        }

        public float getElevation(CardViewDelegate cardViewDelegate) {
            return a(cardViewDelegate).b();
        }

        public float getMaxElevation(CardViewDelegate cardViewDelegate) {
            return a(cardViewDelegate).c();
        }

        public float getMinHeight(CardViewDelegate cardViewDelegate) {
            return a(cardViewDelegate).e();
        }

        public float getMinWidth(CardViewDelegate cardViewDelegate) {
            return a(cardViewDelegate).d();
        }

        public float getRadius(CardViewDelegate cardViewDelegate) {
            return a(cardViewDelegate).a();
        }

        public void initStatic() {
            RoundRectDrawableWithShadow.c = new RoundRectDrawableWithShadow.RoundRectHelper() {
                public void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint) {
                    float f2;
                    Canvas canvas2 = canvas;
                    RectF rectF2 = rectF;
                    float f3 = 2.0f * f;
                    float width = (rectF.width() - f3) - 1.0f;
                    float height = (rectF.height() - f3) - 1.0f;
                    if (f >= 1.0f) {
                        f2 = f + 0.5f;
                        float f4 = -f2;
                        CardViewEclairMr1.this.a.set(f4, f4, f2, f2);
                        int save = canvas.save();
                        canvas2.translate(rectF2.left + f2, rectF2.top + f2);
                        Paint paint2 = paint;
                        canvas2.drawArc(CardViewEclairMr1.this.a, 180.0f, 90.0f, true, paint2);
                        canvas2.translate(width, 0.0f);
                        canvas2.rotate(90.0f);
                        canvas2.drawArc(CardViewEclairMr1.this.a, 180.0f, 90.0f, true, paint2);
                        canvas2.translate(height, 0.0f);
                        canvas2.rotate(90.0f);
                        canvas2.drawArc(CardViewEclairMr1.this.a, 180.0f, 90.0f, true, paint2);
                        canvas2.translate(width, 0.0f);
                        canvas2.rotate(90.0f);
                        canvas2.drawArc(CardViewEclairMr1.this.a, 180.0f, 90.0f, true, paint2);
                        canvas2.restoreToCount(save);
                        canvas2.drawRect((rectF2.left + f2) - 1.0f, rectF2.top, (rectF2.right - f2) + 1.0f, rectF2.top + f2, paint2);
                        canvas2.drawRect((rectF2.left + f2) - 1.0f, (rectF2.bottom - f2) + 1.0f, (rectF2.right - f2) + 1.0f, rectF2.bottom, paint2);
                    } else {
                        f2 = f;
                    }
                    canvas2.drawRect(rectF2.left, Math.max(0.0f, f2 - 1.0f) + rectF2.top, rectF2.right, (rectF2.bottom - f2) + 1.0f, paint);
                }
            };
        }

        public void initialize(CardViewDelegate cardViewDelegate, Context context, int i, float f, float f2, float f3) {
            RoundRectDrawableWithShadow a2 = a(context, i, f, f2, f3);
            a2.setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
            cardViewDelegate.setBackgroundDrawable(a2);
            updatePadding(cardViewDelegate);
        }

        public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate) {
        }

        public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate) {
            a(cardViewDelegate).setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
            updatePadding(cardViewDelegate);
        }

        public void setBackgroundColor(CardViewDelegate cardViewDelegate, int i) {
            a(cardViewDelegate).setColor(i);
        }

        public void setElevation(CardViewDelegate cardViewDelegate, float f) {
            a(cardViewDelegate).b(f);
        }

        public void setMaxElevation(CardViewDelegate cardViewDelegate, float f) {
            a(cardViewDelegate).c(f);
            updatePadding(cardViewDelegate);
        }

        public void setRadius(CardViewDelegate cardViewDelegate, float f) {
            a(cardViewDelegate).a(f);
            updatePadding(cardViewDelegate);
        }

        public void updatePadding(CardViewDelegate cardViewDelegate) {
            Rect rect = new Rect();
            a(cardViewDelegate).a(rect);
            View view = (View) cardViewDelegate;
            view.setMinimumHeight((int) Math.ceil((double) getMinHeight(cardViewDelegate)));
            view.setMinimumWidth((int) Math.ceil((double) getMinWidth(cardViewDelegate)));
            cardViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    static class CardViewJellybeanMr1 extends CardViewEclairMr1 {
        CardViewJellybeanMr1() {
        }

        public void initStatic() {
            RoundRectDrawableWithShadow.c = new RoundRectDrawableWithShadow.RoundRectHelper() {
                public void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint) {
                    canvas.drawRoundRect(rectF, f, f, paint);
                }
            };
        }
    }

    static {
        a.initStatic();
    }

    public CardView(Context context) {
        super(context);
        a(context, (AttributeSet) null, 0);
    }

    public CardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public CardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private float a(float f2) {
        return TypedValue.applyDimension(1, f2, this.b);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        this.b = context.getResources().getDisplayMetrics();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16842801});
        int color = obtainStyledAttributes.getColor(0, -328966);
        obtainStyledAttributes.recycle();
        float a2 = a(2.0f);
        float a3 = a(2.0f);
        float a4 = a(2.0f);
        this.c = false;
        this.d = true;
        this.e.left = 0;
        this.e.top = 0;
        this.e.right = 0;
        this.e.bottom = 0;
        a.initialize(this, context, color, a2, a3, a3 > a4 ? a3 : a4);
    }

    public float getCardElevation() {
        return a.getElevation(this);
    }

    public int getContentPaddingBottom() {
        return this.e.bottom;
    }

    public int getContentPaddingLeft() {
        return this.e.left;
    }

    public int getContentPaddingRight() {
        return this.e.right;
    }

    public int getContentPaddingTop() {
        return this.e.top;
    }

    public float getMaxCardElevation() {
        return a.getMaxElevation(this);
    }

    public boolean getPreventCornerOverlap() {
        return this.d;
    }

    public float getRadius() {
        return a.getRadius(this);
    }

    public boolean getUseCompatPadding() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"SwitchIntDef"})
    public void onMeasure(int i, int i2) {
        if (!(a instanceof CardViewApi21)) {
            int mode = View.MeasureSpec.getMode(i);
            if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                i = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) a.getMinWidth(this)), View.MeasureSpec.getSize(i)), mode);
            }
            int mode2 = View.MeasureSpec.getMode(i2);
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) a.getMinHeight(this)), View.MeasureSpec.getSize(i2)), mode2);
            }
        }
        super.onMeasure(i, i2);
    }

    public void setBackgroundColor(int i) {
        a.setBackgroundColor(this, i);
    }

    public void setCardBackgroundColor(int i) {
        a.setBackgroundColor(this, i);
    }

    public void setCardElevation(float f2) {
        a.setElevation(this, f2);
    }

    public void setContentPadding(int i, int i2, int i3, int i4) {
        this.e.set(i, i2, i3, i4);
        a.updatePadding(this);
    }

    public void setMaxCardElevation(float f2) {
        a.setMaxElevation(this, f2);
    }

    public void setPadding(int i, int i2, int i3, int i4) {
    }

    public void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.d) {
            this.d = z;
            a.onPreventCornerOverlapChanged(this);
        }
    }

    public void setRadius(float f2) {
        a.setRadius(this, f2);
    }

    public void setShadowPadding(int i, int i2, int i3, int i4) {
        this.f.set(i, i2, i3, i4);
        super.setPadding(i + this.e.left, i2 + this.e.top, i3 + this.e.right, i4 + this.e.bottom);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.c != z) {
            this.c = z;
            a.onCompatPaddingChanged(this);
        }
    }
}
