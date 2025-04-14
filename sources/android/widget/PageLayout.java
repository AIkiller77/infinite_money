package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

public class PageLayout extends HorizontalScrollView {
    private int a = 0;
    private LinearLayout b;
    private OnPageChangeListener c;
    private int d;
    private Scroller e;
    private int f;
    private int g;
    private int h;
    private VelocityTracker i;
    private int j;
    private int k;

    public interface OnPageChangeListener {
        void onPageChange(View view, int i);
    }

    public PageLayout(Context context) {
        super(context);
        a(context);
    }

    public PageLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a() {
        if (this.i != null) {
            this.i.recycle();
            this.i = null;
        }
    }

    private void a(Context context) {
        setHorizontalScrollBarEnabled(false);
        this.k = context.getResources().getDisplayMetrics().widthPixels;
        this.a = this.k / 2;
        this.b = new LinearLayout(context);
        super.addView(this.b);
        this.e = new Scroller(getContext());
        setFocusable(true);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f = viewConfiguration.getScaledTouchSlop();
        this.g = viewConfiguration.getScaledMinimumFlingVelocity();
        this.h = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    private void a(MotionEvent motionEvent) {
        if (this.i == null) {
            this.i = VelocityTracker.obtain();
        }
        this.i.addMovement(motionEvent);
    }

    public void addView(View view) {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(view);
        this.b.addView(frameLayout);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(view, layoutParams);
        this.b.addView(frameLayout);
    }

    public View getPage(int i2) {
        return this.b.getChildAt(i2);
    }

    public int getTouchScale() {
        return this.a;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getX() < ((float) this.a) || motionEvent.getX() > ((float) (this.k - this.a))) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            showPage(this.d);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int measuredWidth = getMeasuredWidth();
        int childCount = this.b.getChildCount();
        if (!(this.j == childCount && this.k == measuredWidth)) {
            this.j = childCount;
            this.k = measuredWidth;
            for (int i4 = 0; i4 < childCount; i4++) {
                ViewGroup viewGroup = (ViewGroup) this.b.getChildAt(i4);
                ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                layoutParams.width = this.k;
                viewGroup.setLayoutParams(layoutParams);
                viewGroup.requestLayout();
            }
            ViewGroup.LayoutParams layoutParams2 = this.b.getLayoutParams();
            layoutParams2.width = this.k * childCount;
            this.b.setLayoutParams(layoutParams2);
            this.b.requestLayout();
            requestLayout();
        }
        super.onMeasure(i2, i3);
        showPage(this.d);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        a(motionEvent);
        if (action != 1) {
            return super.onTouchEvent(motionEvent);
        }
        VelocityTracker velocityTracker = this.i;
        velocityTracker.computeCurrentVelocity(1000, (float) this.h);
        int xVelocity = (int) velocityTracker.getXVelocity();
        a();
        int abs = Math.abs(xVelocity);
        int abs2 = Math.abs((int) velocityTracker.getYVelocity());
        if (abs <= this.g || abs <= abs2) {
            int scrollX = getScrollX();
            showPage(scrollX % this.k < this.k / 2 ? scrollX / this.k : (scrollX / this.k) + 1);
            return true;
        }
        showPage(xVelocity > 0 ? Math.max(0, this.d - 1) : Math.min(this.b.getChildCount() - 1, this.d + 1));
        return true;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.c = onPageChangeListener;
    }

    public void setTouchScale(int i2) {
        this.a = i2;
    }

    public void showPage(int i2) {
        smoothScrollTo(this.k * i2, 0);
        if (!(this.c == null || this.d == i2)) {
            this.c.onPageChange(this, i2);
        }
        this.d = i2;
    }

    public void showPage(View view) {
        int childCount = this.b.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (this.b.getChildAt(i2).equals(view)) {
                showPage(i2);
            }
        }
    }
}
