package q.rorbin.badgeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.tencent.qq.widget.R;
import java.util.ArrayList;
import java.util.List;
import q.rorbin.badgeview.Badge;

public class QBadgeView extends View implements Badge {
    protected ViewGroup mActivityRoot;
    protected BadgeAnimator mAnimator;
    protected float mBackgroundBorderWidth;
    protected Paint mBadgeBackgroundBorderPaint;
    protected Paint mBadgeBackgroundPaint;
    protected RectF mBadgeBackgroundRect;
    protected PointF mBadgeCenter;
    protected int mBadgeGravity;
    protected int mBadgeNumber;
    protected float mBadgePadding;
    protected String mBadgeText;
    protected Paint.FontMetrics mBadgeTextFontMetrics;
    protected TextPaint mBadgeTextPaint;
    protected RectF mBadgeTextRect;
    protected float mBadgeTextSize;
    protected Bitmap mBitmapClip;
    protected int mColorBackground;
    protected int mColorBackgroundBorder;
    protected int mColorBadgeText;
    protected PointF mControlPoint;
    protected float mDefalutRadius;
    protected PointF mDragCenter;
    protected boolean mDragOutOfRange;
    protected Path mDragPath;
    protected int mDragQuadrant;
    protected Badge.OnDragStateChangedListener mDragStateChangedListener;
    protected boolean mDraggable;
    protected boolean mDragging;
    protected Drawable mDrawableBackground;
    protected boolean mDrawableBackgroundClip;
    protected boolean mExact;
    protected float mFinalDragDistance;
    protected float mGravityOffsetX;
    protected float mGravityOffsetY;
    protected int mHeight;
    protected List<PointF> mInnertangentPoints;
    protected PointF mRowBadgeCenter;
    protected boolean mShowShadow;
    protected View mTargetView;
    protected int mWidth;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public QBadgeView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    QBadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    QBadgeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        RectF rectF;
        RectF rectF2;
        Path path;
        PointF pointF;
        PointF pointF2;
        PointF pointF3;
        PointF pointF4;
        List<PointF> list;
        TextPaint textPaint;
        Xfermode xfermode;
        Paint paint;
        Paint paint2;
        setLayerType(1, (Paint) null);
        new RectF();
        this.mBadgeTextRect = rectF;
        new RectF();
        this.mBadgeBackgroundRect = rectF2;
        new Path();
        this.mDragPath = path;
        new PointF();
        this.mBadgeCenter = pointF;
        new PointF();
        this.mDragCenter = pointF2;
        new PointF();
        this.mRowBadgeCenter = pointF3;
        new PointF();
        this.mControlPoint = pointF4;
        new ArrayList();
        this.mInnertangentPoints = list;
        new TextPaint();
        this.mBadgeTextPaint = textPaint;
        this.mBadgeTextPaint.setAntiAlias(true);
        this.mBadgeTextPaint.setSubpixelText(true);
        this.mBadgeTextPaint.setFakeBoldText(true);
        new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        Xfermode xfermode2 = this.mBadgeTextPaint.setXfermode(xfermode);
        new Paint();
        this.mBadgeBackgroundPaint = paint;
        this.mBadgeBackgroundPaint.setAntiAlias(true);
        this.mBadgeBackgroundPaint.setStyle(Paint.Style.FILL);
        new Paint();
        this.mBadgeBackgroundBorderPaint = paint2;
        this.mBadgeBackgroundBorderPaint.setAntiAlias(true);
        this.mBadgeBackgroundBorderPaint.setStyle(Paint.Style.STROKE);
        this.mColorBackground = -1552832;
        this.mColorBadgeText = -1;
        this.mBadgeTextSize = (float) DisplayUtil.dp2px(getContext(), (float) 11);
        this.mBadgePadding = (float) DisplayUtil.dp2px(getContext(), (float) 5);
        this.mBadgeNumber = 0;
        this.mBadgeGravity = 8388661;
        this.mGravityOffsetX = (float) DisplayUtil.dp2px(getContext(), (float) 5);
        this.mGravityOffsetY = (float) DisplayUtil.dp2px(getContext(), (float) 5);
        this.mFinalDragDistance = (float) DisplayUtil.dp2px(getContext(), (float) 90);
        this.mShowShadow = true;
        this.mDrawableBackgroundClip = false;
        if (Build.VERSION.SDK_INT >= 21) {
            setTranslationZ((float) 1000);
        }
    }

    @Override
    public Badge bindTarget(View view) {
        Throwable th;
        BadgeContainer badgeContainer;
        Throwable th2;
        View view2 = view;
        if (view2 == null) {
            Throwable th3 = th2;
            new IllegalStateException("targetView can not be null");
            throw th3;
        }
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        ViewParent parent = view2.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            Throwable th4 = th;
            new IllegalStateException("targetView must have a parent");
            throw th4;
        }
        this.mTargetView = view2;
        if (parent instanceof BadgeContainer) {
            ((BadgeContainer) parent).addView(this);
        } else {
            ViewGroup viewGroup = (ViewGroup) parent;
            int indexOfChild = viewGroup.indexOfChild(view2);
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            viewGroup.removeView(view2);
            new BadgeContainer(this, getContext());
            BadgeContainer badgeContainer2 = badgeContainer;
            badgeContainer2.setId(view2.getId());
            viewGroup.addView(badgeContainer2, indexOfChild, layoutParams);
            badgeContainer2.addView(view2);
            badgeContainer2.addView(this);
        }
        return this;
    }

    @Override
    public View getTargetView() {
        return this.mTargetView;
    }

    /* access modifiers changed from: protected */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mActivityRoot == null) {
            findActivityRoot(this.mTargetView);
        }
    }

    private void findActivityRoot(View view) {
        View view2 = view;
        if (view2.getParent() != null && (view2.getParent() instanceof View)) {
            findActivityRoot((View) view2.getParent());
        } else if (view2 instanceof ViewGroup) {
            this.mActivityRoot = (ViewGroup) view2;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        MotionEvent motionEvent2 = motionEvent;
        switch (motionEvent2.getActionMasked()) {
            case 0:
            case 5:
                float x = motionEvent2.getX();
                float y = motionEvent2.getY();
                if (this.mDraggable && motionEvent2.getPointerId(motionEvent2.getActionIndex()) == 0 && x > this.mBadgeBackgroundRect.left && x < this.mBadgeBackgroundRect.right && y > this.mBadgeBackgroundRect.top && y < this.mBadgeBackgroundRect.bottom && this.mBadgeText != null) {
                    initRowBadgeCenter();
                    this.mDragging = true;
                    updataListener(1);
                    this.mDefalutRadius = (float) DisplayUtil.dp2px(getContext(), (float) 7);
                    getParent().requestDisallowInterceptTouchEvent(true);
                    screenFromWindow(true);
                    this.mDragCenter.x = motionEvent2.getRawX();
                    this.mDragCenter.y = motionEvent2.getRawY();
                    break;
                }
            case 1:
            case 3:
            case 6:
                if (motionEvent2.getPointerId(motionEvent2.getActionIndex()) == 0 && this.mDragging) {
                    this.mDragging = false;
                    onPointerUp();
                    break;
                }
            case 2:
                if (this.mDragging) {
                    this.mDragCenter.x = motionEvent2.getRawX();
                    this.mDragCenter.y = motionEvent2.getRawY();
                    invalidate();
                    break;
                }
                break;
        }
        if (this.mDragging || super.onTouchEvent(motionEvent2)) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private void onPointerUp() {
        if (this.mDragOutOfRange) {
            animateHide(this.mDragCenter);
            updataListener(5);
            return;
        }
        reset();
        updataListener(4);
    }

    /* access modifiers changed from: protected */
    public Bitmap createBadgeBitmap() {
        Canvas canvas;
        PointF pointF;
        Bitmap createBitmap = Bitmap.createBitmap(((int) this.mBadgeBackgroundRect.width()) + DisplayUtil.dp2px(getContext(), (float) 3), ((int) this.mBadgeBackgroundRect.height()) + DisplayUtil.dp2px(getContext(), (float) 3), Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap);
        Canvas canvas2 = canvas;
        new PointF(((float) canvas2.getWidth()) / 2.0f, ((float) canvas2.getHeight()) / 2.0f);
        drawBadge(canvas2, pointF, getBadgeCircleRadius());
        return createBitmap;
    }

    /* access modifiers changed from: protected */
    public void screenFromWindow(boolean z) {
        ViewGroup.LayoutParams layoutParams;
        boolean z2 = z;
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (z2) {
            new FrameLayout.LayoutParams(-1, -1);
            this.mActivityRoot.addView(this, layoutParams);
            return;
        }
        Badge bindTarget = bindTarget(this.mTargetView);
    }

    private void showShadowImpl(boolean z) {
        int i;
        boolean z2 = z;
        int dp2px = DisplayUtil.dp2px(getContext(), (float) 1);
        int dp2px2 = DisplayUtil.dp2px(getContext(), 1.5f);
        switch (this.mDragQuadrant) {
            case 1:
                dp2px = DisplayUtil.dp2px(getContext(), (float) 1);
                dp2px2 = DisplayUtil.dp2px(getContext(), -1.5f);
                break;
            case 2:
                dp2px = DisplayUtil.dp2px(getContext(), (float) -1);
                dp2px2 = DisplayUtil.dp2px(getContext(), -1.5f);
                break;
            case 3:
                dp2px = DisplayUtil.dp2px(getContext(), (float) -1);
                dp2px2 = DisplayUtil.dp2px(getContext(), 1.5f);
                break;
            case 4:
                dp2px = DisplayUtil.dp2px(getContext(), (float) 1);
                dp2px2 = DisplayUtil.dp2px(getContext(), 1.5f);
                break;
        }
        Paint paint = this.mBadgeBackgroundPaint;
        if (z2) {
            i = DisplayUtil.dp2px(getContext(), 2.0f);
        } else {
            i = 0;
        }
        paint.setShadowLayer((float) i, (float) dp2px, (float) dp2px2, 855638016);
    }

    /* access modifiers changed from: protected */
    @Override
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = i2;
        super.onSizeChanged(i5, i6, i3, i4);
        this.mWidth = i5;
        this.mHeight = i6;
    }

    /* access modifiers changed from: protected */
    @Override
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.mAnimator != null && this.mAnimator.isRunning()) {
            this.mAnimator.draw(canvas2);
        } else if (this.mBadgeText != null) {
            initPaints();
            float badgeCircleRadius = getBadgeCircleRadius();
            float pointDistance = this.mDefalutRadius * (((float) 1) - (MathUtil.getPointDistance(this.mRowBadgeCenter, this.mDragCenter) / this.mFinalDragDistance));
            if (!this.mDraggable || !this.mDragging) {
                findBadgeCenter();
                drawBadge(canvas2, this.mBadgeCenter, badgeCircleRadius);
                return;
            }
            this.mDragQuadrant = MathUtil.getQuadrant(this.mDragCenter, this.mRowBadgeCenter);
            showShadowImpl(this.mShowShadow);
            boolean z = pointDistance < ((float) DisplayUtil.dp2px(getContext(), 1.5f));
            boolean z2 = z;
            this.mDragOutOfRange = z;
            if (z2) {
                updataListener(3);
                drawBadge(canvas2, this.mDragCenter, badgeCircleRadius);
                return;
            }
            updataListener(2);
            drawDragging(canvas2, pointDistance, badgeCircleRadius);
            drawBadge(canvas2, this.mDragCenter, badgeCircleRadius);
        }
    }

    private void initPaints() {
        showShadowImpl(this.mShowShadow);
        this.mBadgeBackgroundPaint.setColor(this.mColorBackground);
        this.mBadgeBackgroundBorderPaint.setColor(this.mColorBackgroundBorder);
        this.mBadgeBackgroundBorderPaint.setStrokeWidth(this.mBackgroundBorderWidth);
        this.mBadgeTextPaint.setColor(this.mColorBadgeText);
        this.mBadgeTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0235  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drawDragging(android.graphics.Canvas r28, float r29, float r30) {
        /*
            r27 = this;
            r2 = r27
            r3 = r28
            r4 = r29
            r5 = r30
            r14 = r2
            android.graphics.PointF r14 = r14.mDragCenter
            float r14 = r14.y
            r15 = r2
            android.graphics.PointF r15 = r15.mRowBadgeCenter
            float r15 = r15.y
            float r14 = r14 - r15
            r7 = r14
            r14 = r2
            android.graphics.PointF r14 = r14.mDragCenter
            float r14 = r14.x
            r15 = r2
            android.graphics.PointF r15 = r15.mRowBadgeCenter
            float r15 = r15.x
            float r14 = r14 - r15
            r8 = r14
            r14 = r2
            java.util.List<android.graphics.PointF> r14 = r14.mInnertangentPoints
            r14.clear()
            r14 = r8
            r15 = 0
            float r15 = (float) r15
            int r14 = (r14 > r15 ? 1 : (r14 == r15 ? 0 : -1))
            if (r14 == 0) goto L_0x03dc
            r14 = r7
            r15 = r8
            float r14 = r14 / r15
            double r14 = (double) r14
            r9 = r14
            r14 = -1
            double r14 = (double) r14
            r16 = r9
            double r14 = r14 / r16
            r11 = r14
            r14 = r2
            android.graphics.PointF r14 = r14.mDragCenter
            r15 = r5
            r16 = r11
            java.lang.Double r18 = new java.lang.Double
            r24 = r16
            r26 = r18
            r16 = r26
            r17 = r24
            r19 = r26
            r24 = r17
            r26 = r19
            r17 = r26
            r18 = r24
            r20 = r26
            r17.<init>(r18)
            r17 = r2
            r0 = r17
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r17 = r0
            q.rorbin.badgeview.MathUtil.getInnertangentPoints(r14, r15, r16, r17)
            r14 = r2
            android.graphics.PointF r14 = r14.mRowBadgeCenter
            r15 = r4
            r16 = r11
            java.lang.Double r18 = new java.lang.Double
            r24 = r16
            r26 = r18
            r16 = r26
            r17 = r24
            r19 = r26
            r24 = r17
            r26 = r19
            r17 = r26
            r18 = r24
            r20 = r26
            r17.<init>(r18)
            r17 = r2
            r0 = r17
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r17 = r0
            q.rorbin.badgeview.MathUtil.getInnertangentPoints(r14, r15, r16, r17)
        L_0x008d:
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r14.reset()
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            android.graphics.PointF r15 = r15.mRowBadgeCenter
            float r15 = r15.x
            r16 = r2
            r0 = r16
            android.graphics.PointF r0 = r0.mRowBadgeCenter
            r16 = r0
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r17 = r4
            r18 = r2
            r0 = r18
            int r0 = r0.mDragQuadrant
            r18 = r0
            r19 = 1
            r0 = r18
            r1 = r19
            if (r0 == r1) goto L_0x00cb
            r18 = r2
            r0 = r18
            int r0 = r0.mDragQuadrant
            r18 = r0
            r19 = 2
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x0432
        L_0x00cb:
            android.graphics.Path$Direction r18 = android.graphics.Path.Direction.CCW
        L_0x00cd:
            r14.addCircle(r15, r16, r17, r18)
            r14 = r2
            android.graphics.PointF r14 = r14.mControlPoint
            r15 = r2
            android.graphics.PointF r15 = r15.mRowBadgeCenter
            float r15 = r15.x
            r16 = r2
            r0 = r16
            android.graphics.PointF r0 = r0.mDragCenter
            r16 = r0
            r0 = r16
            float r0 = r0.x
            r16 = r0
            float r15 = r15 + r16
            r16 = 1073741824(0x40000000, float:2.0)
            float r15 = r15 / r16
            r14.x = r15
            r14 = r2
            android.graphics.PointF r14 = r14.mControlPoint
            r15 = r2
            android.graphics.PointF r15 = r15.mRowBadgeCenter
            float r15 = r15.y
            r16 = r2
            r0 = r16
            android.graphics.PointF r0 = r0.mDragCenter
            r16 = r0
            r0 = r16
            float r0 = r0.y
            r16 = r0
            float r15 = r15 + r16
            r16 = 1073741824(0x40000000, float:2.0)
            float r15 = r15 / r16
            r14.y = r15
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            java.util.List<android.graphics.PointF> r15 = r15.mInnertangentPoints
            r16 = 2
            java.lang.Object r15 = r15.get(r16)
            android.graphics.PointF r15 = (android.graphics.PointF) r15
            float r15 = r15.x
            r16 = r2
            r0 = r16
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r16 = r0
            r17 = 2
            java.lang.Object r16 = r16.get(r17)
            android.graphics.PointF r16 = (android.graphics.PointF) r16
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r14.moveTo(r15, r16)
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            android.graphics.PointF r15 = r15.mControlPoint
            float r15 = r15.x
            r16 = r2
            r0 = r16
            android.graphics.PointF r0 = r0.mControlPoint
            r16 = r0
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r17 = r2
            r0 = r17
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r17 = r0
            r18 = 0
            java.lang.Object r17 = r17.get(r18)
            android.graphics.PointF r17 = (android.graphics.PointF) r17
            r0 = r17
            float r0 = r0.x
            r17 = r0
            r18 = r2
            r0 = r18
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r18 = r0
            r19 = 0
            java.lang.Object r18 = r18.get(r19)
            android.graphics.PointF r18 = (android.graphics.PointF) r18
            r0 = r18
            float r0 = r0.y
            r18 = r0
            r14.quadTo(r15, r16, r17, r18)
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            java.util.List<android.graphics.PointF> r15 = r15.mInnertangentPoints
            r16 = 1
            java.lang.Object r15 = r15.get(r16)
            android.graphics.PointF r15 = (android.graphics.PointF) r15
            float r15 = r15.x
            r16 = r2
            r0 = r16
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r16 = r0
            r17 = 1
            java.lang.Object r16 = r16.get(r17)
            android.graphics.PointF r16 = (android.graphics.PointF) r16
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r14.lineTo(r15, r16)
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            android.graphics.PointF r15 = r15.mControlPoint
            float r15 = r15.x
            r16 = r2
            r0 = r16
            android.graphics.PointF r0 = r0.mControlPoint
            r16 = r0
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r17 = r2
            r0 = r17
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r17 = r0
            r18 = 3
            java.lang.Object r17 = r17.get(r18)
            android.graphics.PointF r17 = (android.graphics.PointF) r17
            r0 = r17
            float r0 = r0.x
            r17 = r0
            r18 = r2
            r0 = r18
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r18 = r0
            r19 = 3
            java.lang.Object r18 = r18.get(r19)
            android.graphics.PointF r18 = (android.graphics.PointF) r18
            r0 = r18
            float r0 = r0.y
            r18 = r0
            r14.quadTo(r15, r16, r17, r18)
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            java.util.List<android.graphics.PointF> r15 = r15.mInnertangentPoints
            r16 = 2
            java.lang.Object r15 = r15.get(r16)
            android.graphics.PointF r15 = (android.graphics.PointF) r15
            float r15 = r15.x
            r16 = r2
            r0 = r16
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r16 = r0
            r17 = 2
            java.lang.Object r16 = r16.get(r17)
            android.graphics.PointF r16 = (android.graphics.PointF) r16
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r14.lineTo(r15, r16)
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r14.close()
            r14 = r3
            r15 = r2
            android.graphics.Path r15 = r15.mDragPath
            r16 = r2
            r0 = r16
            android.graphics.Paint r0 = r0.mBadgeBackgroundPaint
            r16 = r0
            r14.drawPath(r15, r16)
            r14 = r2
            int r14 = r14.mColorBackgroundBorder
            r15 = 0
            if (r14 == r15) goto L_0x03db
            r14 = r2
            float r14 = r14.mBackgroundBorderWidth
            r15 = 0
            float r15 = (float) r15
            int r14 = (r14 > r15 ? 1 : (r14 == r15 ? 0 : -1))
            if (r14 <= 0) goto L_0x03db
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r14.reset()
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            java.util.List<android.graphics.PointF> r15 = r15.mInnertangentPoints
            r16 = 2
            java.lang.Object r15 = r15.get(r16)
            android.graphics.PointF r15 = (android.graphics.PointF) r15
            float r15 = r15.x
            r16 = r2
            r0 = r16
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r16 = r0
            r17 = 2
            java.lang.Object r16 = r16.get(r17)
            android.graphics.PointF r16 = (android.graphics.PointF) r16
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r14.moveTo(r15, r16)
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            android.graphics.PointF r15 = r15.mControlPoint
            float r15 = r15.x
            r16 = r2
            r0 = r16
            android.graphics.PointF r0 = r0.mControlPoint
            r16 = r0
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r17 = r2
            r0 = r17
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r17 = r0
            r18 = 0
            java.lang.Object r17 = r17.get(r18)
            android.graphics.PointF r17 = (android.graphics.PointF) r17
            r0 = r17
            float r0 = r0.x
            r17 = r0
            r18 = r2
            r0 = r18
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r18 = r0
            r19 = 0
            java.lang.Object r18 = r18.get(r19)
            android.graphics.PointF r18 = (android.graphics.PointF) r18
            r0 = r18
            float r0 = r0.y
            r18 = r0
            r14.quadTo(r15, r16, r17, r18)
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            java.util.List<android.graphics.PointF> r15 = r15.mInnertangentPoints
            r16 = 1
            java.lang.Object r15 = r15.get(r16)
            android.graphics.PointF r15 = (android.graphics.PointF) r15
            float r15 = r15.x
            r16 = r2
            r0 = r16
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r16 = r0
            r17 = 1
            java.lang.Object r16 = r16.get(r17)
            android.graphics.PointF r16 = (android.graphics.PointF) r16
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r14.moveTo(r15, r16)
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            android.graphics.PointF r15 = r15.mControlPoint
            float r15 = r15.x
            r16 = r2
            r0 = r16
            android.graphics.PointF r0 = r0.mControlPoint
            r16 = r0
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r17 = r2
            r0 = r17
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r17 = r0
            r18 = 3
            java.lang.Object r17 = r17.get(r18)
            android.graphics.PointF r17 = (android.graphics.PointF) r17
            r0 = r17
            float r0 = r0.x
            r17 = r0
            r18 = r2
            r0 = r18
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r18 = r0
            r19 = 3
            java.lang.Object r18 = r18.get(r19)
            android.graphics.PointF r18 = (android.graphics.PointF) r18
            r0 = r18
            float r0 = r0.y
            r18 = r0
            r14.quadTo(r15, r16, r17, r18)
            r14 = r2
            int r14 = r14.mDragQuadrant
            r15 = 1
            if (r14 == r15) goto L_0x0323
            r14 = r2
            int r14 = r14.mDragQuadrant
            r15 = 2
            if (r14 != r15) goto L_0x0436
        L_0x0323:
            r14 = r2
            java.util.List<android.graphics.PointF> r14 = r14.mInnertangentPoints
            r15 = 2
            java.lang.Object r14 = r14.get(r15)
            android.graphics.PointF r14 = (android.graphics.PointF) r14
            float r14 = r14.x
            r15 = r2
            android.graphics.PointF r15 = r15.mRowBadgeCenter
            float r15 = r15.x
            float r14 = r14 - r15
            r10 = r14
            r14 = r2
            android.graphics.PointF r14 = r14.mRowBadgeCenter
            float r14 = r14.y
            r15 = r2
            java.util.List<android.graphics.PointF> r15 = r15.mInnertangentPoints
            r16 = 2
            java.lang.Object r15 = r15.get(r16)
            android.graphics.PointF r15 = (android.graphics.PointF) r15
            float r15 = r15.y
            float r14 = r14 - r15
            r9 = r14
        L_0x034a:
            r14 = 360(0x168, float:5.04E-43)
            float r14 = (float) r14
            r15 = r9
            r16 = r10
            float r15 = r15 / r16
            double r15 = (double) r15
            double r15 = java.lang.Math.atan(r15)
            r17 = r2
            r0 = r17
            int r0 = r0.mDragQuadrant
            r17 = r0
            r18 = 1
            int r17 = r17 + -1
            r18 = 0
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x045f
            r17 = 4
        L_0x036d:
            double r15 = q.rorbin.badgeview.MathUtil.getTanRadian(r15, r17)
            double r15 = q.rorbin.badgeview.MathUtil.radianToAngle(r15)
            float r15 = (float) r15
            float r14 = r14 - r15
            r11 = r14
            int r14 = android.os.Build.VERSION.SDK_INT
            r15 = 21
            if (r14 < r15) goto L_0x046d
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            r15 = r2
            android.graphics.PointF r15 = r15.mRowBadgeCenter
            float r15 = r15.x
            r16 = r4
            float r15 = r15 - r16
            r16 = r2
            r0 = r16
            android.graphics.PointF r0 = r0.mRowBadgeCenter
            r16 = r0
            r0 = r16
            float r0 = r0.y
            r16 = r0
            r17 = r4
            float r16 = r16 - r17
            r17 = r2
            r0 = r17
            android.graphics.PointF r0 = r0.mRowBadgeCenter
            r17 = r0
            r0 = r17
            float r0 = r0.x
            r17 = r0
            r18 = r4
            float r17 = r17 + r18
            r18 = r2
            r0 = r18
            android.graphics.PointF r0 = r0.mRowBadgeCenter
            r18 = r0
            r0 = r18
            float r0 = r0.y
            r18 = r0
            r19 = r4
            float r18 = r18 + r19
            r19 = r11
            r20 = 180(0xb4, float:2.52E-43)
            r0 = r20
            float r0 = (float) r0
            r20 = r0
            r14.addArc(r15, r16, r17, r18, r19, r20)
        L_0x03cc:
            r14 = r3
            r15 = r2
            android.graphics.Path r15 = r15.mDragPath
            r16 = r2
            r0 = r16
            android.graphics.Paint r0 = r0.mBadgeBackgroundBorderPaint
            r16 = r0
            r14.drawPath(r15, r16)
        L_0x03db:
            return
        L_0x03dc:
            r14 = r2
            android.graphics.PointF r14 = r14.mDragCenter
            r15 = r5
            r16 = 0
            java.lang.Double r18 = new java.lang.Double
            r24 = r16
            r26 = r18
            r16 = r26
            r17 = r24
            r19 = r26
            r24 = r17
            r26 = r19
            r17 = r26
            r18 = r24
            r20 = r26
            r17.<init>(r18)
            r17 = r2
            r0 = r17
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r17 = r0
            q.rorbin.badgeview.MathUtil.getInnertangentPoints(r14, r15, r16, r17)
            r14 = r2
            android.graphics.PointF r14 = r14.mRowBadgeCenter
            r15 = r4
            r16 = 0
            java.lang.Double r18 = new java.lang.Double
            r24 = r16
            r26 = r18
            r16 = r26
            r17 = r24
            r19 = r26
            r24 = r17
            r26 = r19
            r17 = r26
            r18 = r24
            r20 = r26
            r17.<init>(r18)
            r17 = r2
            r0 = r17
            java.util.List<android.graphics.PointF> r0 = r0.mInnertangentPoints
            r17 = r0
            q.rorbin.badgeview.MathUtil.getInnertangentPoints(r14, r15, r16, r17)
            goto L_0x008d
        L_0x0432:
            android.graphics.Path$Direction r18 = android.graphics.Path.Direction.CW
            goto L_0x00cd
        L_0x0436:
            r14 = r2
            java.util.List<android.graphics.PointF> r14 = r14.mInnertangentPoints
            r15 = 3
            java.lang.Object r14 = r14.get(r15)
            android.graphics.PointF r14 = (android.graphics.PointF) r14
            float r14 = r14.x
            r15 = r2
            android.graphics.PointF r15 = r15.mRowBadgeCenter
            float r15 = r15.x
            float r14 = r14 - r15
            r10 = r14
            r14 = r2
            android.graphics.PointF r14 = r14.mRowBadgeCenter
            float r14 = r14.y
            r15 = r2
            java.util.List<android.graphics.PointF> r15 = r15.mInnertangentPoints
            r16 = 3
            java.lang.Object r15 = r15.get(r16)
            android.graphics.PointF r15 = (android.graphics.PointF) r15
            float r15 = r15.y
            float r14 = r14 - r15
            r9 = r14
            goto L_0x034a
        L_0x045f:
            r17 = r2
            r0 = r17
            int r0 = r0.mDragQuadrant
            r17 = r0
            r18 = 1
            int r17 = r17 + -1
            goto L_0x036d
        L_0x046d:
            r14 = r2
            android.graphics.Path r14 = r14.mDragPath
            android.graphics.RectF r15 = new android.graphics.RectF
            r24 = r15
            r15 = r24
            r16 = r24
            r17 = r2
            r0 = r17
            android.graphics.PointF r0 = r0.mRowBadgeCenter
            r17 = r0
            r0 = r17
            float r0 = r0.x
            r17 = r0
            r18 = r4
            float r17 = r17 - r18
            r18 = r2
            r0 = r18
            android.graphics.PointF r0 = r0.mRowBadgeCenter
            r18 = r0
            r0 = r18
            float r0 = r0.y
            r18 = r0
            r19 = r4
            float r18 = r18 - r19
            r19 = r2
            r0 = r19
            android.graphics.PointF r0 = r0.mRowBadgeCenter
            r19 = r0
            r0 = r19
            float r0 = r0.x
            r19 = r0
            r20 = r4
            float r19 = r19 + r20
            r20 = r2
            r0 = r20
            android.graphics.PointF r0 = r0.mRowBadgeCenter
            r20 = r0
            r0 = r20
            float r0 = r0.y
            r20 = r0
            r21 = r4
            float r20 = r20 + r21
            r16.<init>(r17, r18, r19, r20)
            r16 = r11
            r17 = 180(0xb4, float:2.52E-43)
            r0 = r17
            float r0 = (float) r0
            r17 = r0
            r14.addArc(r15, r16, r17)
            goto L_0x03cc
        */
        throw new UnsupportedOperationException("Method not decompiled: q.rorbin.badgeview.QBadgeView.drawDragging(android.graphics.Canvas, float, float):void");
    }

    private void drawBadge(Canvas canvas, PointF pointF, float f) {
        Canvas canvas2 = canvas;
        PointF pointF2 = pointF;
        float f2 = f;
        if (pointF2.x != ((float) NotificationManagerCompat.IMPORTANCE_UNSPECIFIED) || pointF2.y != ((float) NotificationManagerCompat.IMPORTANCE_UNSPECIFIED)) {
            if (this.mBadgeText.isEmpty() || this.mBadgeText.length() == 1) {
                this.mBadgeBackgroundRect.left = pointF2.x - ((float) ((int) f2));
                this.mBadgeBackgroundRect.top = pointF2.y - ((float) ((int) f2));
                this.mBadgeBackgroundRect.right = pointF2.x + ((float) ((int) f2));
                this.mBadgeBackgroundRect.bottom = pointF2.y + ((float) ((int) f2));
                if (this.mDrawableBackground != null) {
                    drawBadgeBackground(canvas2);
                } else {
                    canvas2.drawCircle(pointF2.x, pointF2.y, f2, this.mBadgeBackgroundPaint);
                    if (this.mColorBackgroundBorder != 0 && this.mBackgroundBorderWidth > ((float) 0)) {
                        canvas2.drawCircle(pointF2.x, pointF2.y, f2, this.mBadgeBackgroundBorderPaint);
                    }
                }
            } else {
                this.mBadgeBackgroundRect.left = pointF2.x - ((this.mBadgeTextRect.width() / 2.0f) + this.mBadgePadding);
                this.mBadgeBackgroundRect.top = pointF2.y - ((this.mBadgeTextRect.height() / 2.0f) + (this.mBadgePadding * 0.5f));
                this.mBadgeBackgroundRect.right = pointF2.x + (this.mBadgeTextRect.width() / 2.0f) + this.mBadgePadding;
                this.mBadgeBackgroundRect.bottom = pointF2.y + (this.mBadgeTextRect.height() / 2.0f) + (this.mBadgePadding * 0.5f);
                float height = this.mBadgeBackgroundRect.height() / 2.0f;
                if (this.mDrawableBackground != null) {
                    drawBadgeBackground(canvas2);
                } else {
                    canvas2.drawRoundRect(this.mBadgeBackgroundRect, height, height, this.mBadgeBackgroundPaint);
                    if (this.mColorBackgroundBorder != 0 && this.mBackgroundBorderWidth > ((float) 0)) {
                        canvas2.drawRoundRect(this.mBadgeBackgroundRect, height, height, this.mBadgeBackgroundBorderPaint);
                    }
                }
            }
            if (!this.mBadgeText.isEmpty()) {
                canvas2.drawText(this.mBadgeText, pointF2.x, (((this.mBadgeBackgroundRect.bottom + this.mBadgeBackgroundRect.top) - this.mBadgeTextFontMetrics.bottom) - this.mBadgeTextFontMetrics.top) / 2.0f, this.mBadgeTextPaint);
            }
        }
    }

    private void drawBadgeBackground(Canvas canvas) {
        Xfermode xfermode;
        Canvas canvas2 = canvas;
        this.mBadgeBackgroundPaint.setShadowLayer((float) 0, (float) 0, (float) 0, 0);
        int i = (int) this.mBadgeBackgroundRect.left;
        int i2 = (int) this.mBadgeBackgroundRect.top;
        int i3 = (int) this.mBadgeBackgroundRect.right;
        int i4 = (int) this.mBadgeBackgroundRect.bottom;
        if (this.mDrawableBackgroundClip) {
            i3 = i + this.mBitmapClip.getWidth();
            i4 = i2 + this.mBitmapClip.getHeight();
            int saveLayer = canvas2.saveLayer((float) i, (float) i2, (float) i3, (float) i4, (Paint) null, 31);
        }
        this.mDrawableBackground.setBounds(i, i2, i3, i4);
        this.mDrawableBackground.draw(canvas2);
        if (this.mDrawableBackgroundClip) {
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
            Xfermode xfermode2 = this.mBadgeBackgroundPaint.setXfermode(xfermode);
            canvas2.drawBitmap(this.mBitmapClip, (float) i, (float) i2, this.mBadgeBackgroundPaint);
            canvas2.restore();
            Xfermode xfermode3 = this.mBadgeBackgroundPaint.setXfermode((Xfermode) null);
            if (this.mBadgeText.isEmpty() || this.mBadgeText.length() == 1) {
                canvas2.drawCircle(this.mBadgeBackgroundRect.centerX(), this.mBadgeBackgroundRect.centerY(), this.mBadgeBackgroundRect.width() / 2.0f, this.mBadgeBackgroundBorderPaint);
            } else {
                canvas2.drawRoundRect(this.mBadgeBackgroundRect, this.mBadgeBackgroundRect.height() / ((float) 2), this.mBadgeBackgroundRect.height() / ((float) 2), this.mBadgeBackgroundBorderPaint);
            }
        } else {
            canvas2.drawRect(this.mBadgeBackgroundRect, this.mBadgeBackgroundBorderPaint);
        }
    }

    private void createClipLayer() {
        Canvas canvas;
        Canvas canvas2;
        RectF rectF;
        if (this.mBadgeText != null && this.mDrawableBackgroundClip) {
            if (this.mBitmapClip != null && !this.mBitmapClip.isRecycled()) {
                this.mBitmapClip.recycle();
            }
            float badgeCircleRadius = getBadgeCircleRadius();
            if (this.mBadgeText.isEmpty() || this.mBadgeText.length() == 1) {
                this.mBitmapClip = Bitmap.createBitmap(((int) badgeCircleRadius) * 2, ((int) badgeCircleRadius) * 2, Bitmap.Config.ARGB_4444);
                new Canvas(this.mBitmapClip);
                Canvas canvas3 = canvas;
                canvas3.drawCircle(((float) canvas3.getWidth()) / 2.0f, ((float) canvas3.getHeight()) / 2.0f, ((float) canvas3.getWidth()) / 2.0f, this.mBadgeBackgroundPaint);
                return;
            }
            this.mBitmapClip = Bitmap.createBitmap((int) (this.mBadgeTextRect.width() + (this.mBadgePadding * ((float) 2))), (int) (this.mBadgeTextRect.height() + this.mBadgePadding), Bitmap.Config.ARGB_4444);
            new Canvas(this.mBitmapClip);
            Canvas canvas4 = canvas2;
            if (Build.VERSION.SDK_INT >= 21) {
                canvas4.drawRoundRect((float) 0, (float) 0, (float) canvas4.getWidth(), (float) canvas4.getHeight(), ((float) canvas4.getHeight()) / 2.0f, ((float) canvas4.getHeight()) / 2.0f, this.mBadgeBackgroundPaint);
                return;
            }
            new RectF((float) 0, (float) 0, (float) canvas4.getWidth(), (float) canvas4.getHeight());
            canvas4.drawRoundRect(rectF, ((float) canvas4.getHeight()) / 2.0f, ((float) canvas4.getHeight()) / 2.0f, this.mBadgeBackgroundPaint);
        }
    }

    private float getBadgeCircleRadius() {
        if (this.mBadgeText.isEmpty()) {
            return this.mBadgePadding;
        }
        if (this.mBadgeText.length() != 1) {
            return this.mBadgeBackgroundRect.height() / 2.0f;
        }
        return this.mBadgeTextRect.height() > this.mBadgeTextRect.width() ? (this.mBadgeTextRect.height() / 2.0f) + (this.mBadgePadding * 0.5f) : (this.mBadgeTextRect.width() / 2.0f) + (this.mBadgePadding * 0.5f);
    }

    private void findBadgeCenter() {
        float height = this.mBadgeTextRect.height() > this.mBadgeTextRect.width() ? this.mBadgeTextRect.height() : this.mBadgeTextRect.width();
        switch (this.mBadgeGravity) {
            case 17:
                this.mBadgeCenter.x = ((float) this.mWidth) / 2.0f;
                this.mBadgeCenter.y = ((float) this.mHeight) / 2.0f;
                break;
            case R.styleable.AppCompatTheme_homeAsUpIndicator:
                this.mBadgeCenter.x = ((float) this.mWidth) / 2.0f;
                this.mBadgeCenter.y = this.mGravityOffsetY + this.mBadgePadding + (this.mBadgeTextRect.height() / 2.0f);
                break;
            case R.styleable.AppCompatTheme_panelMenuListTheme:
                this.mBadgeCenter.x = ((float) this.mWidth) / 2.0f;
                this.mBadgeCenter.y = ((float) this.mHeight) - ((this.mGravityOffsetY + this.mBadgePadding) + (this.mBadgeTextRect.height() / 2.0f));
                break;
            case 8388627:
                this.mBadgeCenter.x = this.mGravityOffsetX + this.mBadgePadding + (height / 2.0f);
                this.mBadgeCenter.y = ((float) this.mHeight) / 2.0f;
                break;
            case 8388629:
                this.mBadgeCenter.x = ((float) this.mWidth) - ((this.mGravityOffsetX + this.mBadgePadding) + (height / 2.0f));
                this.mBadgeCenter.y = ((float) this.mHeight) / 2.0f;
                break;
            case 8388659:
                this.mBadgeCenter.x = this.mGravityOffsetX + this.mBadgePadding + (height / 2.0f);
                this.mBadgeCenter.y = this.mGravityOffsetY + this.mBadgePadding + (this.mBadgeTextRect.height() / 2.0f);
                break;
            case 8388661:
                this.mBadgeCenter.x = ((float) this.mWidth) - ((this.mGravityOffsetX + this.mBadgePadding) + (height / 2.0f));
                this.mBadgeCenter.y = this.mGravityOffsetY + this.mBadgePadding + (this.mBadgeTextRect.height() / 2.0f);
                break;
            case 8388691:
                this.mBadgeCenter.x = this.mGravityOffsetX + this.mBadgePadding + (height / 2.0f);
                this.mBadgeCenter.y = ((float) this.mHeight) - ((this.mGravityOffsetY + this.mBadgePadding) + (this.mBadgeTextRect.height() / 2.0f));
                break;
            case 8388693:
                this.mBadgeCenter.x = ((float) this.mWidth) - ((this.mGravityOffsetX + this.mBadgePadding) + (height / 2.0f));
                this.mBadgeCenter.y = ((float) this.mHeight) - ((this.mGravityOffsetY + this.mBadgePadding) + (this.mBadgeTextRect.height() / 2.0f));
                break;
        }
        initRowBadgeCenter();
    }

    private void measureText() {
        this.mBadgeTextRect.left = (float) 0;
        this.mBadgeTextRect.top = (float) 0;
        if (TextUtils.isEmpty(this.mBadgeText)) {
            this.mBadgeTextRect.right = (float) 0;
            this.mBadgeTextRect.bottom = (float) 0;
        } else {
            this.mBadgeTextPaint.setTextSize(this.mBadgeTextSize);
            this.mBadgeTextRect.right = this.mBadgeTextPaint.measureText(this.mBadgeText);
            this.mBadgeTextFontMetrics = this.mBadgeTextPaint.getFontMetrics();
            this.mBadgeTextRect.bottom = this.mBadgeTextFontMetrics.descent - this.mBadgeTextFontMetrics.ascent;
        }
        createClipLayer();
    }

    private void initRowBadgeCenter() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        this.mRowBadgeCenter.x = this.mBadgeCenter.x + ((float) iArr[0]);
        this.mRowBadgeCenter.y = this.mBadgeCenter.y + ((float) iArr[1]);
    }

    /* access modifiers changed from: protected */
    public void animateHide(PointF pointF) {
        BadgeAnimator badgeAnimator;
        PointF pointF2 = pointF;
        if (this.mBadgeText != null) {
            if (this.mAnimator == null || !this.mAnimator.isRunning()) {
                screenFromWindow(true);
                new BadgeAnimator(createBadgeBitmap(), pointF2, this);
                this.mAnimator = badgeAnimator;
                this.mAnimator.start();
                Badge badgeNumber = setBadgeNumber(0);
            }
        }
    }

    public void reset() {
        this.mDragCenter.x = (float) NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
        this.mDragCenter.y = (float) NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
        this.mDragQuadrant = 4;
        screenFromWindow(false);
        getParent().requestDisallowInterceptTouchEvent(false);
        invalidate();
    }

    @Override
    public void hide(boolean z) {
        if (!z || this.mActivityRoot == null) {
            Badge badgeNumber = setBadgeNumber(0);
        } else {
            animateHide(this.mRowBadgeCenter);
        }
    }

    @Override
    public Badge setBadgeNumber(int i) {
        this.mBadgeNumber = i;
        if (this.mBadgeNumber < 0) {
            this.mBadgeText = "";
        } else if (this.mBadgeNumber > 99) {
            this.mBadgeText = this.mExact ? String.valueOf(this.mBadgeNumber) : "99+";
        } else if (this.mBadgeNumber > 0 && this.mBadgeNumber <= 99) {
            this.mBadgeText = String.valueOf(this.mBadgeNumber);
        } else if (this.mBadgeNumber == 0) {
            this.mBadgeText = null;
        }
        measureText();
        invalidate();
        return this;
    }

    @Override
    public int getBadgeNumber() {
        return this.mBadgeNumber;
    }

    @Override
    public Badge setBadgeText(String str) {
        this.mBadgeText = str;
        this.mBadgeNumber = 1;
        measureText();
        invalidate();
        return this;
    }

    @Override
    public String getBadgeText() {
        return this.mBadgeText;
    }

    @Override
    public Badge setExactMode(boolean z) {
        this.mExact = z;
        if (this.mBadgeNumber > 99) {
            Badge badgeNumber = setBadgeNumber(this.mBadgeNumber);
        }
        return this;
    }

    @Override
    public boolean isExactMode() {
        return this.mExact;
    }

    @Override
    public Badge setShowShadow(boolean z) {
        this.mShowShadow = z;
        invalidate();
        return this;
    }

    @Override
    public boolean isShowShadow() {
        return this.mShowShadow;
    }

    @Override
    public Badge setBadgeBackgroundColor(int i) {
        Xfermode xfermode;
        this.mColorBackground = i;
        if (this.mColorBackground == 0) {
            Xfermode xfermode2 = this.mBadgeTextPaint.setXfermode((Xfermode) null);
        } else {
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
            Xfermode xfermode3 = this.mBadgeTextPaint.setXfermode(xfermode);
        }
        invalidate();
        return this;
    }

    @Override
    public Badge stroke(int i, float f, boolean z) {
        float f2 = f;
        this.mColorBackgroundBorder = i;
        this.mBackgroundBorderWidth = z ? (float) DisplayUtil.dp2px(getContext(), f2) : f2;
        invalidate();
        return this;
    }

    @Override
    public int getBadgeBackgroundColor() {
        return this.mColorBackground;
    }

    @Override
    public Badge setBadgeBackground(Drawable drawable) {
        return setBadgeBackground(drawable, false);
    }

    @Override
    public Badge setBadgeBackground(Drawable drawable, boolean z) {
        this.mDrawableBackgroundClip = z;
        this.mDrawableBackground = drawable;
        createClipLayer();
        invalidate();
        return this;
    }

    @Override
    public Drawable getBadgeBackground() {
        return this.mDrawableBackground;
    }

    @Override
    public Badge setBadgeTextColor(int i) {
        this.mColorBadgeText = i;
        invalidate();
        return this;
    }

    @Override
    public int getBadgeTextColor() {
        return this.mColorBadgeText;
    }

    @Override
    public Badge setBadgeTextSize(float f, boolean z) {
        float f2 = f;
        this.mBadgeTextSize = z ? (float) DisplayUtil.dp2px(getContext(), f2) : f2;
        measureText();
        invalidate();
        return this;
    }

    @Override
    public float getBadgeTextSize(boolean z) {
        return z ? (float) DisplayUtil.px2dp(getContext(), this.mBadgeTextSize) : this.mBadgeTextSize;
    }

    @Override
    public Badge setBadgePadding(float f, boolean z) {
        float f2 = f;
        this.mBadgePadding = z ? (float) DisplayUtil.dp2px(getContext(), f2) : f2;
        createClipLayer();
        invalidate();
        return this;
    }

    @Override
    public float getBadgePadding(boolean z) {
        return z ? (float) DisplayUtil.px2dp(getContext(), this.mBadgePadding) : this.mBadgePadding;
    }

    @Override
    public boolean isDraggable() {
        return this.mDraggable;
    }

    @Override
    public Badge setBadgeGravity(int i) {
        Throwable th;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        int i2 = i;
        if (i2 == 8388659 || i2 == 8388661 || i2 == 8388691 || i2 == 8388693 || i2 == 17 || i2 == 49 || i2 == 81 || i2 == 8388627 || i2 == 8388629) {
            this.mBadgeGravity = i2;
            invalidate();
            return this;
        }
        Throwable th2 = th;
        new StringBuffer();
        new StringBuffer();
        new StringBuffer();
        new IllegalStateException(stringBuffer.append(stringBuffer2.append(stringBuffer3.append("only support Gravity.START | Gravity.TOP , Gravity.END | Gravity.TOP , ").append("Gravity.START | Gravity.BOTTOM , Gravity.END | Gravity.BOTTOM , Gravity.CENTER").toString()).append(" , Gravity.CENTER | Gravity.TOP , Gravity.CENTER | Gravity.BOTTOM ,").toString()).append("Gravity.CENTER | Gravity.START , Gravity.CENTER | Gravity.END").toString());
        throw th2;
    }

    @Override
    public int getBadgeGravity() {
        return this.mBadgeGravity;
    }

    @Override
    public Badge setGravityOffset(float f, boolean z) {
        float f2 = f;
        return setGravityOffset(f2, f2, z);
    }

    @Override
    public Badge setGravityOffset(float f, float f2, boolean z) {
        float f3 = f;
        float f4 = f2;
        boolean z2 = z;
        this.mGravityOffsetX = z2 ? (float) DisplayUtil.dp2px(getContext(), f3) : f3;
        this.mGravityOffsetY = z2 ? (float) DisplayUtil.dp2px(getContext(), f4) : f4;
        invalidate();
        return this;
    }

    @Override
    public float getGravityOffsetX(boolean z) {
        return z ? (float) DisplayUtil.px2dp(getContext(), this.mGravityOffsetX) : this.mGravityOffsetX;
    }

    @Override
    public float getGravityOffsetY(boolean z) {
        return z ? (float) DisplayUtil.px2dp(getContext(), this.mGravityOffsetY) : this.mGravityOffsetY;
    }

    private void updataListener(int i) {
        int i2 = i;
        if (this.mDragStateChangedListener != null) {
            this.mDragStateChangedListener.onDragStateChanged(i2, this, this.mTargetView);
        }
    }

    @Override
    public Badge setOnDragStateChangedListener(Badge.OnDragStateChangedListener onDragStateChangedListener) {
        Badge.OnDragStateChangedListener onDragStateChangedListener2 = onDragStateChangedListener;
        this.mDraggable = onDragStateChangedListener2 != null;
        this.mDragStateChangedListener = onDragStateChangedListener2;
        return this;
    }

    @Override
    public PointF getDragCenter() {
        if (!this.mDraggable || !this.mDragging) {
            return null;
        }
        return this.mDragCenter;
    }

    private class BadgeContainer extends ViewGroup {
        private final QBadgeView this$0;

        static QBadgeView access$0(BadgeContainer badgeContainer) {
            return badgeContainer.this$0;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BadgeContainer(QBadgeView qBadgeView, Context context) {
            super(context);
            this.this$0 = qBadgeView;
        }

        /* access modifiers changed from: protected */
        @Override
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            boolean z2 = z;
            int i5 = i;
            int i6 = i2;
            int i7 = i3;
            int i8 = i4;
            for (int i9 = 0; i9 < getChildCount(); i9++) {
                View childAt = getChildAt(i9);
                childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
            }
        }

        /* access modifiers changed from: protected */
        @Override
        public void onMeasure(int i, int i2) {
            int i3 = i;
            int i4 = i2;
            View view = null;
            View view2 = null;
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                if (!(childAt instanceof QBadgeView)) {
                    view = childAt;
                } else {
                    view2 = childAt;
                }
            }
            if (view == null) {
                super.onMeasure(i3, i4);
                return;
            }
            view.measure(i3, i4);
            if (view2 != null) {
                view2.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
            }
            setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }
}
