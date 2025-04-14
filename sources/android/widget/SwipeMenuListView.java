package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;

public class SwipeMenuListView extends ListView {
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = -1;
    private static final int TOUCH_STATE_NONE = 0;
    private static final int TOUCH_STATE_X = 1;
    private static final int TOUCH_STATE_Y = 2;
    private int MAX_X = 3;
    private int MAX_Y = 5;
    private Interpolator mCloseInterpolator;
    private int mDirection = 1;
    private float mDownX;
    private float mDownY;
    /* access modifiers changed from: private */
    public SwipeMenuCreator mMenuCreator;
    /* access modifiers changed from: private */
    public OnMenuItemClickListener mOnMenuItemClickListener;
    private OnMenuStateChangeListener mOnMenuStateChangeListener;
    private OnSwipeListener mOnSwipeListener;
    private Interpolator mOpenInterpolator;
    private int mTouchPosition;
    private int mTouchState;
    /* access modifiers changed from: private */
    public SwipeMenuLayout mTouchView;

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(int i, SwipeMenu swipeMenu, int i2);
    }

    public interface OnMenuStateChangeListener {
        void onMenuClose(int i);

        void onMenuOpen(int i);
    }

    public interface OnSwipeListener {
        void onSwipeEnd(int i);

        void onSwipeStart(int i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SwipeMenuListView(Context context) {
        super(context);
        init();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SwipeMenuListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SwipeMenuListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.MAX_X = dp2px(this.MAX_X);
        this.MAX_Y = dp2px(this.MAX_Y);
        this.mTouchState = 0;
    }

    public void setAdapter(ListAdapter listAdapter) {
        ListAdapter listAdapter2;
        new SwipeMenuAdapter(this, getContext(), listAdapter) {
            final /* synthetic */ SwipeMenuListView this$0;

            {
                this.this$0 = r8;
            }

            public void createMenu(SwipeMenu swipeMenu) {
                SwipeMenu swipeMenu2 = swipeMenu;
                if (this.this$0.mMenuCreator != null) {
                    this.this$0.mMenuCreator.create(swipeMenu2);
                }
            }

            public void onItemClick(SwipeMenuView swipeMenuView, SwipeMenu swipeMenu, int i) {
                SwipeMenuView swipeMenuView2 = swipeMenuView;
                SwipeMenu swipeMenu2 = swipeMenu;
                int i2 = i;
                boolean z = false;
                if (this.this$0.mOnMenuItemClickListener != null) {
                    z = this.this$0.mOnMenuItemClickListener.onMenuItemClick(swipeMenuView2.getPosition(), swipeMenu2, i2);
                }
                if (this.this$0.mTouchView != null && !z) {
                    this.this$0.mTouchView.smoothCloseMenu();
                }
            }
        };
        super.setAdapter(listAdapter2);
    }

    public void setCloseInterpolator(Interpolator interpolator) {
        Interpolator interpolator2 = interpolator;
        this.mCloseInterpolator = interpolator2;
    }

    public void setOpenInterpolator(Interpolator interpolator) {
        Interpolator interpolator2 = interpolator;
        this.mOpenInterpolator = interpolator2;
    }

    public Interpolator getOpenInterpolator() {
        return this.mOpenInterpolator;
    }

    public Interpolator getCloseInterpolator() {
        return this.mCloseInterpolator;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        if (motionEvent2.getAction() != 0 && this.mTouchView == null) {
            return super.onTouchEvent(motionEvent2);
        }
        switch (motionEvent2.getAction()) {
            case 0:
                int i = this.mTouchPosition;
                this.mDownX = motionEvent2.getX();
                this.mDownY = motionEvent2.getY();
                this.mTouchState = 0;
                this.mTouchPosition = pointToPosition((int) motionEvent2.getX(), (int) motionEvent2.getY());
                if (this.mTouchPosition != i || this.mTouchView == null || !this.mTouchView.isOpen()) {
                    View childAt = getChildAt(this.mTouchPosition - getFirstVisiblePosition());
                    if (this.mTouchView == null || !this.mTouchView.isOpen()) {
                        if (childAt instanceof SwipeMenuLayout) {
                            this.mTouchView = (SwipeMenuLayout) childAt;
                            this.mTouchView.setSwipeDirection(this.mDirection);
                        }
                        if (this.mTouchView != null) {
                            boolean onSwipe = this.mTouchView.onSwipe(motionEvent2);
                            break;
                        }
                    } else {
                        this.mTouchView.smoothCloseMenu();
                        this.mTouchView = null;
                        MotionEvent obtain = MotionEvent.obtain(motionEvent2);
                        obtain.setAction(3);
                        boolean onTouchEvent = onTouchEvent(obtain);
                        if (this.mOnMenuStateChangeListener != null) {
                            this.mOnMenuStateChangeListener.onMenuClose(i);
                        }
                        return true;
                    }
                } else {
                    this.mTouchState = 1;
                    boolean onSwipe2 = this.mTouchView.onSwipe(motionEvent2);
                    return true;
                }
                break;
            case 1:
                if (this.mTouchState == 1) {
                    if (this.mTouchView != null) {
                        boolean isOpen = this.mTouchView.isOpen();
                        boolean onSwipe3 = this.mTouchView.onSwipe(motionEvent2);
                        boolean isOpen2 = this.mTouchView.isOpen();
                        if (!(isOpen == isOpen2 || this.mOnMenuStateChangeListener == null)) {
                            if (isOpen2) {
                                this.mOnMenuStateChangeListener.onMenuOpen(this.mTouchPosition);
                            } else {
                                this.mOnMenuStateChangeListener.onMenuClose(this.mTouchPosition);
                            }
                        }
                        if (!isOpen2) {
                            this.mTouchPosition = -1;
                            this.mTouchView = null;
                        }
                    }
                    if (this.mOnSwipeListener != null) {
                        this.mOnSwipeListener.onSwipeEnd(this.mTouchPosition);
                    }
                    motionEvent2.setAction(3);
                    boolean onTouchEvent2 = super.onTouchEvent(motionEvent2);
                    return true;
                }
                break;
            case 2:
                float abs = Math.abs(motionEvent2.getY() - this.mDownY);
                float abs2 = Math.abs(motionEvent2.getX() - this.mDownX);
                if (this.mTouchState != 1) {
                    if (this.mTouchState == 0) {
                        if (Math.abs(abs) <= ((float) this.MAX_Y)) {
                            if (abs2 > ((float) this.MAX_X)) {
                                this.mTouchState = 1;
                                if (this.mOnSwipeListener != null) {
                                    this.mOnSwipeListener.onSwipeStart(this.mTouchPosition);
                                    break;
                                }
                            }
                        } else {
                            this.mTouchState = 2;
                            break;
                        }
                    }
                } else {
                    if (this.mTouchView != null) {
                        boolean onSwipe4 = this.mTouchView.onSwipe(motionEvent2);
                    }
                    boolean state = getSelector().setState(new int[]{0});
                    motionEvent2.setAction(3);
                    boolean onTouchEvent3 = super.onTouchEvent(motionEvent2);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(motionEvent2);
    }

    public void smoothOpenMenu(int i) {
        int i2 = i;
        if (i2 >= getFirstVisiblePosition() && i2 <= getLastVisiblePosition()) {
            View childAt = getChildAt(i2 - getFirstVisiblePosition());
            if (childAt instanceof SwipeMenuLayout) {
                this.mTouchPosition = i2;
                if (this.mTouchView != null && this.mTouchView.isOpen()) {
                    this.mTouchView.smoothCloseMenu();
                }
                this.mTouchView = (SwipeMenuLayout) childAt;
                this.mTouchView.setSwipeDirection(this.mDirection);
                this.mTouchView.smoothOpenMenu();
            }
        }
    }

    public void smoothCloseMenu() {
        if (this.mTouchView != null && this.mTouchView.isOpen()) {
            this.mTouchView.smoothCloseMenu();
        }
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getContext().getResources().getDisplayMetrics());
    }

    public void setMenuCreator(SwipeMenuCreator swipeMenuCreator) {
        SwipeMenuCreator swipeMenuCreator2 = swipeMenuCreator;
        this.mMenuCreator = swipeMenuCreator2;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        OnMenuItemClickListener onMenuItemClickListener2 = onMenuItemClickListener;
        this.mOnMenuItemClickListener = onMenuItemClickListener2;
    }

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        OnSwipeListener onSwipeListener2 = onSwipeListener;
        this.mOnSwipeListener = onSwipeListener2;
    }

    public void setOnMenuStateChangeListener(OnMenuStateChangeListener onMenuStateChangeListener) {
        OnMenuStateChangeListener onMenuStateChangeListener2 = onMenuStateChangeListener;
        this.mOnMenuStateChangeListener = onMenuStateChangeListener2;
    }

    public void setSwipeDirection(int i) {
        int i2 = i;
        this.mDirection = i2;
    }
}
