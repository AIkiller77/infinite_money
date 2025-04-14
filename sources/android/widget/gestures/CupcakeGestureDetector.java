package android.widget.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.log.LogManager;

public class CupcakeGestureDetector implements GestureDetector {
    private static final String LOG_TAG = "CupcakeGestureDetector";
    private boolean mIsDragging;
    float mLastTouchX;
    float mLastTouchY;
    protected OnGestureListener mListener;
    final float mMinimumVelocity;
    final float mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public CupcakeGestureDetector(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mMinimumVelocity = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.mTouchSlop = (float) viewConfiguration.getScaledTouchSlop();
    }

    /* access modifiers changed from: package-private */
    public float getActiveX(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    /* access modifiers changed from: package-private */
    public float getActiveY(MotionEvent motionEvent) {
        return motionEvent.getY();
    }

    @Override
    public boolean isDragging() {
        return this.mIsDragging;
    }

    @Override
    public boolean isScaling() {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        switch (motionEvent2.getAction()) {
            case 0:
                this.mVelocityTracker = VelocityTracker.obtain();
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.addMovement(motionEvent2);
                } else {
                    int i = LogManager.getLogger().i(LOG_TAG, "Velocity tracker is null");
                }
                this.mLastTouchX = getActiveX(motionEvent2);
                this.mLastTouchY = getActiveY(motionEvent2);
                this.mIsDragging = false;
                break;
            case 1:
                if (this.mIsDragging && this.mVelocityTracker != null) {
                    this.mLastTouchX = getActiveX(motionEvent2);
                    this.mLastTouchY = getActiveY(motionEvent2);
                    this.mVelocityTracker.addMovement(motionEvent2);
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    float xVelocity = this.mVelocityTracker.getXVelocity();
                    float yVelocity = this.mVelocityTracker.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.mMinimumVelocity) {
                        this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -xVelocity, -yVelocity);
                    }
                }
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
            case 2:
                float activeX = getActiveX(motionEvent2);
                float activeY = getActiveY(motionEvent2);
                float f = activeX - this.mLastTouchX;
                float f2 = activeY - this.mLastTouchY;
                if (!this.mIsDragging) {
                    this.mIsDragging = Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.mTouchSlop);
                }
                if (this.mIsDragging) {
                    this.mListener.onDrag(f, f2);
                    this.mLastTouchX = activeX;
                    this.mLastTouchY = activeY;
                    if (this.mVelocityTracker != null) {
                        this.mVelocityTracker.addMovement(motionEvent2);
                        break;
                    }
                }
                break;
            case 3:
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
        }
        return true;
    }

    @Override
    public void setOnGestureListener(OnGestureListener onGestureListener) {
        OnGestureListener onGestureListener2 = onGestureListener;
        this.mListener = onGestureListener2;
    }
}
