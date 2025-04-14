package android.widget.scrollerproxy;

import android.content.Context;
import android.widget.Scroller;

public class PreGingerScroller extends ScrollerProxy {
    private final Scroller mScroller;

    public PreGingerScroller(Context context) {
        Scroller scroller;
        new Scroller(context);
        this.mScroller = scroller;
    }

    @Override
    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }

    @Override
    public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        int i11 = i9;
        int i12 = i10;
        this.mScroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
    }

    @Override
    public void forceFinished(boolean z) {
        this.mScroller.forceFinished(z);
    }

    @Override
    public int getCurrX() {
        return this.mScroller.getCurrX();
    }

    @Override
    public int getCurrY() {
        return this.mScroller.getCurrY();
    }

    public boolean isFinished() {
        return this.mScroller.isFinished();
    }
}
