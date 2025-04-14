package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SlidingLayout extends HorizontalScrollView {
    private int a;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private boolean e;
    private boolean f;
    private LinearLayout g;
    private OnMenuOpenedListener h;
    private OnMenuClosedListener i;
    private OnMenuStateChangeListener j;
    private boolean k;

    public interface OnMenuClosedListener {
        void onMenuClosed(View view);
    }

    public interface OnMenuOpenedListener {
        void onMenuOpened(View view);
    }

    public interface OnMenuStateChangeListener {
        void onMenuStateChange(View view, boolean z);
    }

    public SlidingLayout(Context context) {
        super(context);
        a(context);
    }

    public SlidingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        setHorizontalScrollBarEnabled(false);
        this.a = context.getResources().getDisplayMetrics().widthPixels;
        this.b = this.a / 10;
        this.g = new LinearLayout(context);
        super.addView(this.g);
    }

    public void addView(View view) {
        this.g.addView(view);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        this.g.addView(view, layoutParams);
    }

    public void closeMenu() {
        smoothScrollTo(this.c, 0);
        if (this.f && this.i != null) {
            this.i.onMenuClosed(this);
        }
        this.f = false;
    }

    public int getMenuWidth() {
        return this.c;
    }

    public int getTouchScale() {
        return this.b;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f || motionEvent.getX() < ((float) this.b)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            if (!this.e) {
                scrollTo(this.c, 0);
            } else {
                closeMenu();
            }
            this.e = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (!this.e) {
            View childAt = this.g.getChildAt(0);
            View childAt2 = this.g.getChildAt(1);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (this.c == 0 && layoutParams.width < 0) {
                layoutParams.width = (int) (((double) this.a) * 0.8d);
            }
            this.c = layoutParams.width;
            this.d = layoutParams.width / 2;
            childAt2.getLayoutParams().width = this.a;
            this.g.getLayoutParams().width = this.a + this.c;
        }
        if (this.f) {
            openMenu();
        } else {
            closeMenu();
        }
        super.onMeasure(i2, i3);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
                int scrollX = getScrollX();
                if (!this.f ? ((double) scrollX) <= ((double) this.d) * 1.5d : scrollX <= this.d / 2) {
                    openMenu();
                } else {
                    closeMenu();
                }
                this.k = false;
                return true;
            case 2:
                if (!this.k && this.j != null) {
                    this.j.onMenuStateChange(this, this.f);
                }
                this.k = true;
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void openMenu() {
        smoothScrollTo(0, 0);
        if (!this.f && this.h != null) {
            this.h.onMenuOpened(this);
        }
        this.f = true;
    }

    public void setMenuWidth(int i2) {
        this.c = i2;
    }

    public void setOnMenuClosedListener(OnMenuClosedListener onMenuClosedListener) {
        this.i = onMenuClosedListener;
    }

    public void setOnMenuOpenedListener(OnMenuOpenedListener onMenuOpenedListener) {
        this.h = onMenuOpenedListener;
    }

    public void setOnMenuStateChangeListener(OnMenuStateChangeListener onMenuStateChangeListener) {
        this.j = onMenuStateChangeListener;
    }

    public void setTouchScale(int i2) {
        this.b = i2;
    }

    public void toggle() {
        if (this.f) {
            closeMenu();
        } else {
            openMenu();
        }
    }
}
