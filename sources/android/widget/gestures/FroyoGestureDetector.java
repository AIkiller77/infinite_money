package android.widget.gestures;

import android.annotation.TargetApi;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

@TargetApi(8)
public class FroyoGestureDetector extends EclairGestureDetector {
    protected final ScaleGestureDetector mDetector;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FroyoGestureDetector(android.content.Context r12) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r5 = r0
            r6 = r1
            r5.<init>(r6)
            android.widget.gestures.FroyoGestureDetector$100000000 r5 = new android.widget.gestures.FroyoGestureDetector$100000000
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r0
            r6.<init>(r7)
            r3 = r5
            r5 = r0
            android.view.ScaleGestureDetector r6 = new android.view.ScaleGestureDetector
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r1
            r9 = r3
            r7.<init>(r8, r9)
            r5.mDetector = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.gestures.FroyoGestureDetector.<init>(android.content.Context):void");
    }

    @Override
    public boolean isScaling() {
        return this.mDetector.isInProgress();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        try {
            boolean onTouchEvent = this.mDetector.onTouchEvent(motionEvent2);
            return super.onTouchEvent(motionEvent2);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException illegalArgumentException = e;
            return true;
        }
    }
}
