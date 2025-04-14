package android.widget;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;

public class SwipeMenuLayout extends FrameLayout {
    private static final int CONTENT_VIEW_ID = 1;
    private static final int MENU_VIEW_ID = 2;
    private static final int STATE_CLOSE = 0;
    private static final int STATE_OPEN = 1;
    /* access modifiers changed from: private */
    public int MAX_VELOCITYX;
    /* access modifiers changed from: private */
    public int MIN_FLING;
    private boolean isFling;
    private int mBaseX;
    private Interpolator mCloseInterpolator;
    private ScrollerCompat mCloseScroller;
    private View mContentView;
    private int mDownX;
    private GestureDetectorCompat mGestureDetector;
    private GestureDetector.OnGestureListener mGestureListener;
    private SwipeMenuView mMenuView;
    private Interpolator mOpenInterpolator;
    private ScrollerCompat mOpenScroller;
    private int mSwipeDirection;
    private int position;
    private int state;

    static /* synthetic */ boolean access$002(SwipeMenuLayout swipeMenuLayout, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        swipeMenuLayout.isFling = z3;
        return z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwipeMenuLayout(View view, SwipeMenuView swipeMenuView) {
        this(view, swipeMenuView, (Interpolator) null, (Interpolator) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SwipeMenuLayout(android.view.View r9, android.widget.SwipeMenuView r10, android.view.animation.Interpolator r11, android.view.animation.Interpolator r12) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r0
            r6 = r1
            android.content.Context r6 = r6.getContext()
            r5.<init>(r6)
            r5 = r0
            r6 = 0
            r5.state = r6
            r5 = r0
            r6 = r0
            r7 = 15
            int r6 = r6.dp2px(r7)
            r5.MIN_FLING = r6
            r5 = r0
            r6 = r0
            r7 = 500(0x1f4, float:7.0E-43)
            int r6 = r6.dp2px(r7)
            int r6 = -r6
            r5.MAX_VELOCITYX = r6
            r5 = r0
            r6 = r3
            r5.mCloseInterpolator = r6
            r5 = r0
            r6 = r4
            r5.mOpenInterpolator = r6
            r5 = r0
            r6 = r1
            r5.mContentView = r6
            r5 = r0
            r6 = r2
            r5.mMenuView = r6
            r5 = r0
            android.widget.SwipeMenuView r5 = r5.mMenuView
            r6 = r0
            r5.setLayout(r6)
            r5 = r0
            r5.init()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.SwipeMenuLayout.<init>(android.view.View, android.widget.SwipeMenuView, android.view.animation.Interpolator, android.view.animation.Interpolator):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private SwipeMenuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.state = 0;
        this.MIN_FLING = dp2px(15);
        this.MAX_VELOCITYX = -dp2px(500);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private SwipeMenuLayout(Context context) {
        super(context);
        this.state = 0;
        this.MIN_FLING = dp2px(15);
        this.MAX_VELOCITYX = -dp2px(500);
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        int i2 = i;
        this.position = i2;
        this.mMenuView.setPosition(i2);
    }

    public void setSwipeDirection(int i) {
        int i2 = i;
        this.mSwipeDirection = i2;
    }

    private void init() {
        ViewGroup.LayoutParams layoutParams;
        GestureDetector.OnGestureListener onGestureListener;
        GestureDetectorCompat gestureDetectorCompat;
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        new AbsListView.LayoutParams(-1, -2);
        setLayoutParams(layoutParams);
        new GestureDetector.SimpleOnGestureListener(this) {
            final /* synthetic */ SwipeMenuLayout this$0;

            {
                this.this$0 = r5;
            }

            public boolean onDown(MotionEvent motionEvent) {
                MotionEvent motionEvent2 = motionEvent;
                boolean access$002 = SwipeMenuLayout.access$002(this.this$0, false);
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                MotionEvent motionEvent3 = motionEvent;
                MotionEvent motionEvent4 = motionEvent2;
                float f3 = f;
                float f4 = f2;
                if (Math.abs(motionEvent3.getX() - motionEvent4.getX()) > ((float) this.this$0.MIN_FLING) && f3 < ((float) this.this$0.MAX_VELOCITYX)) {
                    boolean access$002 = SwipeMenuLayout.access$002(this.this$0, true);
                }
                return super.onFling(motionEvent3, motionEvent4, f3, f4);
            }
        };
        this.mGestureListener = onGestureListener;
        new GestureDetectorCompat(getContext(), this.mGestureListener);
        this.mGestureDetector = gestureDetectorCompat;
        if (this.mCloseInterpolator != null) {
            this.mCloseScroller = ScrollerCompat.create(getContext(), this.mCloseInterpolator);
        } else {
            this.mCloseScroller = ScrollerCompat.create(getContext());
        }
        if (this.mOpenInterpolator != null) {
            this.mOpenScroller = ScrollerCompat.create(getContext(), this.mOpenInterpolator);
        } else {
            this.mOpenScroller = ScrollerCompat.create(getContext());
        }
        new FrameLayout.LayoutParams(-1, -2);
        this.mContentView.setLayoutParams(layoutParams2);
        if (this.mContentView.getId() < 1) {
            this.mContentView.setId(1);
        }
        this.mMenuView.setId(2);
        new FrameLayout.LayoutParams(-2, -2);
        this.mMenuView.setLayoutParams(layoutParams3);
        addView(this.mContentView);
        addView(this.mMenuView);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public boolean onSwipe(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        boolean onTouchEvent = this.mGestureDetector.onTouchEvent(motionEvent2);
        switch (motionEvent2.getAction()) {
            case 0:
                this.mDownX = (int) motionEvent2.getX();
                this.isFling = false;
                break;
            case 1:
                if ((this.isFling || Math.abs(((float) this.mDownX) - motionEvent2.getX()) > ((float) (this.mMenuView.getWidth() / 2))) && Math.signum(((float) this.mDownX) - motionEvent2.getX()) == ((float) this.mSwipeDirection)) {
                    smoothOpenMenu();
                    break;
                } else {
                    smoothCloseMenu();
                    return false;
                }
            case 2:
                int x = (int) (((float) this.mDownX) - motionEvent2.getX());
                if (this.state == 1) {
                    x += this.mMenuView.getWidth() * this.mSwipeDirection;
                }
                swipe(x);
                break;
        }
        return true;
    }

    public boolean isOpen() {
        return this.state == 1;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    private void swipe(int i) {
        int i2 = i;
        if (Math.signum((float) i2) != ((float) this.mSwipeDirection)) {
            i2 = 0;
        } else if (Math.abs(i2) > this.mMenuView.getWidth()) {
            i2 = this.mMenuView.getWidth() * this.mSwipeDirection;
        }
        this.mContentView.layout(-i2, this.mContentView.getTop(), this.mContentView.getWidth() - i2, getMeasuredHeight());
        if (this.mSwipeDirection == 1) {
            this.mMenuView.layout(this.mContentView.getWidth() - i2, this.mMenuView.getTop(), (this.mContentView.getWidth() + this.mMenuView.getWidth()) - i2, this.mMenuView.getBottom());
        } else {
            this.mMenuView.layout((-this.mMenuView.getWidth()) - i2, this.mMenuView.getTop(), -i2, this.mMenuView.getBottom());
        }
    }

    public void computeScroll() {
        if (this.state == 1) {
            if (this.mOpenScroller.computeScrollOffset()) {
                swipe(this.mOpenScroller.getCurrX() * this.mSwipeDirection);
                postInvalidate();
            }
        } else if (this.mCloseScroller.computeScrollOffset()) {
            swipe((this.mBaseX - this.mCloseScroller.getCurrX()) * this.mSwipeDirection);
            postInvalidate();
        }
    }

    public void smoothCloseMenu() {
        this.state = 0;
        if (this.mSwipeDirection == 1) {
            this.mBaseX = -this.mContentView.getLeft();
            this.mCloseScroller.startScroll(0, 0, this.mMenuView.getWidth(), 0, 350);
        } else {
            this.mBaseX = this.mMenuView.getRight();
            this.mCloseScroller.startScroll(0, 0, this.mMenuView.getWidth(), 0, 350);
        }
        postInvalidate();
    }

    public void smoothOpenMenu() {
        this.state = 1;
        if (this.mSwipeDirection == 1) {
            this.mOpenScroller.startScroll(-this.mContentView.getLeft(), 0, this.mMenuView.getWidth(), 0, 350);
        } else {
            this.mOpenScroller.startScroll(this.mContentView.getLeft(), 0, this.mMenuView.getWidth(), 0, 350);
        }
        postInvalidate();
    }

    public void closeMenu() {
        if (this.mCloseScroller.computeScrollOffset()) {
            this.mCloseScroller.abortAnimation();
        }
        if (this.state == 1) {
            this.state = 0;
            swipe(0);
        }
    }

    public void openMenu() {
        if (this.state == 0) {
            this.state = 1;
            swipe(this.mMenuView.getWidth() * this.mSwipeDirection);
        }
    }

    public View getContentView() {
        return this.mContentView;
    }

    public SwipeMenuView getMenuView() {
        return this.mMenuView;
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getContext().getResources().getDisplayMetrics());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mMenuView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = z;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        this.mContentView.layout(0, 0, getMeasuredWidth(), this.mContentView.getMeasuredHeight());
        if (this.mSwipeDirection == 1) {
            this.mMenuView.layout(getMeasuredWidth(), 0, getMeasuredWidth() + this.mMenuView.getMeasuredWidth(), this.mContentView.getMeasuredHeight());
        } else {
            this.mMenuView.layout(-this.mMenuView.getMeasuredWidth(), 0, 0, this.mContentView.getMeasuredHeight());
        }
    }

    public void setMenuHeight(int i) {
        StringBuilder sb;
        int i2 = i;
        new StringBuilder();
        int i3 = Log.i("byz", sb.append("pos = ").append(this.position).append(", height = ").append(i2).toString());
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mMenuView.getLayoutParams();
        if (layoutParams.height != i2) {
            layoutParams.height = i2;
            this.mMenuView.setLayoutParams(this.mMenuView.getLayoutParams());
        }
    }
}
