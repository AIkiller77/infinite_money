package android.widget.gestures;

import android.content.Context;
import android.os.Build;

public final class VersionedGestureDetector {
    public VersionedGestureDetector() {
    }

    public static GestureDetector newInstance(Context context, OnGestureListener onGestureListener) {
        GestureDetector gestureDetector;
        GestureDetector gestureDetector2;
        GestureDetector gestureDetector3;
        GestureDetector gestureDetector4;
        Context context2 = context;
        OnGestureListener onGestureListener2 = onGestureListener;
        int i = Build.VERSION.SDK_INT;
        if (i < 5) {
            new CupcakeGestureDetector(context2);
            gestureDetector2 = gestureDetector4;
        } else if (i < 8) {
            new EclairGestureDetector(context2);
            gestureDetector2 = gestureDetector3;
        } else {
            new FroyoGestureDetector(context2);
            gestureDetector2 = gestureDetector;
        }
        gestureDetector2.setOnGestureListener(onGestureListener2);
        return gestureDetector2;
    }
}
