package android.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;

public class YLListView extends ListView implements AbsListView.OnScrollListener {
    private static final float OFFSET_RADIO = 5.0f;
    private static final int SCROLLBACK_FOOTER = 1;
    private static final int SCROLLBACK_HEADER = 0;
    private static final int SCROLL_DURATION = 400;
    /* access modifiers changed from: private */
    public int finalBottomHeight;
    /* access modifiers changed from: private */
    public int finalTopHeight;
    /* access modifiers changed from: private */
    public View mFooterView;
    /* access modifiers changed from: private */
    public View mHeaderView;
    private float mLastY = ((float) -1);
    private int mScrollBack;
    private AbsListView.OnScrollListener mScrollListener;
    private Scroller mScroller;
    private int mTotalItemCount;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public YLListView(android.content.Context r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = -1
            float r4 = (float) r4
            r3.mLastY = r4
            r3 = r0
            r4 = r1
            r3.initWithContext(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.YLListView.<init>(android.content.Context):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public YLListView(android.content.Context r8, android.util.AttributeSet r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r0
            r5 = -1
            float r5 = (float) r5
            r4.mLastY = r5
            r4 = r0
            r5 = r1
            r4.initWithContext(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.YLListView.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public YLListView(android.content.Context r10, android.util.AttributeSet r11, int r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = -1
            float r6 = (float) r6
            r5.mLastY = r6
            r5 = r0
            r6 = r1
            r5.initWithContext(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.YLListView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private void initWithContext(Context context) {
        Scroller scroller;
        Interpolator interpolator;
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        new DecelerateInterpolator();
        new Scroller(context, interpolator);
        this.mScroller = scroller;
        super.setOnScrollListener(this);
        new ViewTreeObserver.OnGlobalLayoutListener(this) {
            private final YLListView this$0;

            {
                this.this$0 = r6;
            }

            static YLListView access$0(AnonymousClass100000000 r4) {
                return r4.this$0;
            }

            @Override
            public void onGlobalLayout() {
                View view;
                View view2;
                if (this.this$0.mHeaderView == null) {
                    new View(this.this$0.getContext());
                    this.this$0.addHeaderView(view2);
                }
                if (this.this$0.mFooterView == null) {
                    new View(this.this$0.getContext());
                    this.this$0.addFooterView(view);
                }
                this.this$0.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        if (this.mLastY == ((float) -1)) {
            this.mLastY = motionEvent2.getRawY();
        }
        switch (motionEvent2.getAction()) {
            case 0:
                this.mLastY = motionEvent2.getRawY();
                break;
            case 2:
                float rawY = motionEvent2.getRawY() - this.mLastY;
                this.mLastY = motionEvent2.getRawY();
                if (getFirstVisiblePosition() != 0 || ((this.mHeaderView.getHeight() <= this.finalTopHeight && rawY <= ((float) 0)) || this.mHeaderView.getTop() < 0)) {
                    if (getLastVisiblePosition() == this.mTotalItemCount - 1 && (getFootHeight() > this.finalBottomHeight || rawY < ((float) 0))) {
                        updateFooterHeight((-rawY) / 1.8f);
                        break;
                    }
                } else {
                    updateHeaderHeight(rawY / 1.8f);
                    break;
                }
                break;
            default:
                this.mLastY = (float) -1;
                if (getFirstVisiblePosition() == 0 && getHeaderHeight() > this.finalTopHeight) {
                    resetHeaderHeight();
                }
                if (getLastVisiblePosition() == this.mTotalItemCount - 1 && getFootHeight() > this.finalBottomHeight) {
                    resetFooterHeight();
                    break;
                }
        }
        return super.onTouchEvent(motionEvent2);
    }

    private void resetFooterHeight() {
        int footHeight = getFootHeight();
        if (footHeight > this.finalBottomHeight) {
            this.mScrollBack = 1;
            this.mScroller.startScroll(0, footHeight, 0, (-footHeight) + this.finalBottomHeight, SCROLL_DURATION);
            invalidate();
        }
    }

    @Override
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.mScrollBack == 0) {
                setHeaderHeight(this.mScroller.getCurrY());
            } else {
                setFooterViewHeight(this.mScroller.getCurrY());
            }
            postInvalidate();
        }
        super.computeScroll();
    }

    /* access modifiers changed from: private */
    public void setHeaderHeight(int i) {
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) this.mHeaderView.getLayoutParams();
        layoutParams.height = i;
        this.mHeaderView.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public void setFooterViewHeight(int i) {
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) this.mFooterView.getLayoutParams();
        layoutParams.height = i;
        this.mFooterView.setLayoutParams(layoutParams);
    }

    public int getHeaderHeight() {
        return ((AbsListView.LayoutParams) this.mHeaderView.getLayoutParams()).height;
    }

    public int getFootHeight() {
        return ((AbsListView.LayoutParams) this.mFooterView.getLayoutParams()).height;
    }

    private void resetHeaderHeight() {
        int headerHeight = getHeaderHeight();
        if (headerHeight != 0) {
            this.mScrollBack = 0;
            this.mScroller.startScroll(0, headerHeight, 0, this.finalTopHeight - headerHeight, SCROLL_DURATION);
            invalidate();
        }
    }

    public void setFinalTopHeight(int i) {
        int i2 = i;
        this.finalTopHeight = i2;
    }

    public void setFinalBottomHeight(int i) {
        int i2 = i;
        this.finalBottomHeight = i2;
    }

    @Override
    public void addHeaderView(View view) {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        this.mHeaderView = view;
        super.addHeaderView(this.mHeaderView);
        new ViewTreeObserver.OnGlobalLayoutListener(this) {
            private final YLListView this$0;

            {
                this.this$0 = r6;
            }

            static YLListView access$0(AnonymousClass100000001 r4) {
                return r4.this$0;
            }

            @Override
            public void onGlobalLayout() {
                if (this.this$0.finalTopHeight == 0) {
                    this.this$0.finalTopHeight = this.this$0.mHeaderView.getMeasuredHeight();
                }
                this.this$0.setHeaderHeight(this.this$0.finalTopHeight);
                this.this$0.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        };
        this.mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    @Override
    public void addFooterView(View view) {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        this.mFooterView = view;
        super.addFooterView(this.mFooterView);
        new ViewTreeObserver.OnGlobalLayoutListener(this) {
            private final YLListView this$0;

            {
                this.this$0 = r6;
            }

            static YLListView access$0(AnonymousClass100000002 r4) {
                return r4.this$0;
            }

            @Override
            public void onGlobalLayout() {
                if (this.this$0.finalBottomHeight == 0) {
                    this.this$0.finalBottomHeight = this.this$0.mFooterView.getMeasuredHeight();
                }
                this.this$0.setFooterViewHeight(this.this$0.finalBottomHeight);
                this.this$0.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        };
        this.mFooterView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    @Override
    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        AbsListView.OnScrollListener onScrollListener2 = onScrollListener;
        this.mScrollListener = onScrollListener2;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        AbsListView absListView2 = absListView;
        int i2 = i;
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(absListView2, i2);
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        AbsListView absListView2 = absListView;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        this.mTotalItemCount = i6;
        if (this.mScrollListener != null) {
            this.mScrollListener.onScroll(absListView2, i4, i5, i6);
        }
    }

    private void updateHeaderHeight(float f) {
        setHeaderHeight((int) (((float) getHeaderHeight()) + f));
        setSelection(0);
    }

    private void updateFooterHeight(float f) {
        setFooterViewHeight((int) (((float) getFootHeight()) + f));
    }
}
