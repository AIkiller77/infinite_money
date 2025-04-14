package android.widget.scrollerproxy;

import android.annotation.TargetApi;
import android.content.Context;

@TargetApi(14)
public class IcsScroller extends GingerScroller {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IcsScroller(Context context) {
        super(context);
    }

    @Override
    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }
}
