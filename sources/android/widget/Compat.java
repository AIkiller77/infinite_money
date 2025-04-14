package android.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.view.View;

public class Compat {
    private static final int SIXTY_FPS_INTERVAL = 16;

    public Compat() {
    }

    public static int getPointerIndex(int i) {
        int i2 = i;
        return Build.VERSION.SDK_INT >= 11 ? getPointerIndexHoneyComb(i2) : getPointerIndexEclair(i2);
    }

    @SuppressWarnings("deprecation")
    @TargetApi(5)
    private static int getPointerIndexEclair(int i) {
        return (i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
    }

    @TargetApi(11)
    private static int getPointerIndexHoneyComb(int i) {
        return (i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        View view2 = view;
        Runnable runnable2 = runnable;
        if (Build.VERSION.SDK_INT >= 16) {
            postOnAnimationJellyBean(view2, runnable2);
        } else {
            boolean postDelayed = view2.postDelayed(runnable2, (long) 16);
        }
    }

    @TargetApi(16)
    private static void postOnAnimationJellyBean(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }
}
