package android.widget.gestures;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.widget.Compat;

@TargetApi(5)
public class EclairGestureDetector extends CupcakeGestureDetector {
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = -1;
    private int mActivePointerIndex = 0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EclairGestureDetector(Context context) {
        super(context);
    }

    /* access modifiers changed from: package-private */
    @Override
    public float getActiveX(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        try {
            return motionEvent2.getX(this.mActivePointerIndex);
        } catch (Exception e) {
            Exception exc = e;
            return motionEvent2.getX();
        }
    }

    /* access modifiers changed from: package-private */
    @Override
    public float getActiveY(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        try {
            return motionEvent2.getY(this.mActivePointerIndex);
        } catch (Exception e) {
            Exception exc = e;
            return motionEvent2.getY();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        switch (motionEvent2.getAction() & 255) {
            case 0:
                this.mActivePointerId = motionEvent2.getPointerId(0);
                break;
            case 1:
            case 3:
                this.mActivePointerId = -1;
                break;
            case 6:
                int pointerIndex = Compat.getPointerIndex(motionEvent2.getAction());
                if (motionEvent2.getPointerId(pointerIndex) == this.mActivePointerId) {
                    int i = pointerIndex == 0 ? 1 : 0;
                    this.mActivePointerId = motionEvent2.getPointerId(i);
                    this.mLastTouchX = motionEvent2.getX(i);
                    this.mLastTouchY = motionEvent2.getY(i);
                    break;
                }
                break;
        }
        this.mActivePointerIndex = motionEvent2.findPointerIndex(this.mActivePointerId != -1 ? this.mActivePointerId : 0);
        try {
            return super.onTouchEvent(motionEvent2);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException illegalArgumentException = e;
            return true;
        }
    }
}
