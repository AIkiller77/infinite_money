package android.widget.scrollerproxy;

import android.content.Context;
import android.os.Build;

public abstract class ScrollerProxy {
    public ScrollerProxy() {
    }

    public static ScrollerProxy getScroller(Context context) {
        ScrollerProxy scrollerProxy;
        ScrollerProxy scrollerProxy2;
        ScrollerProxy scrollerProxy3;
        Context context2 = context;
        if (Build.VERSION.SDK_INT < 9) {
            new PreGingerScroller(context2);
            return scrollerProxy3;
        } else if (Build.VERSION.SDK_INT < 14) {
            new GingerScroller(context2);
            return scrollerProxy2;
        } else {
            new IcsScroller(context2);
            return scrollerProxy;
        }
    }

    public abstract boolean computeScrollOffset();

    public abstract void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public abstract void forceFinished(boolean z);

    public abstract int getCurrX();

    public abstract int getCurrY();

    public abstract boolean isFinished();
}
