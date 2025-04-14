package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewDebug;

public class MarText extends TextView {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MarText(Context context) {
        super(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MarText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MarText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @ViewDebug.ExportedProperty(category = "focus")
    @Override
    public boolean isFocused() {
        return true;
    }

    /* access modifiers changed from: protected */
    @Override
    public void onFocusChanged(boolean z, int i, Rect rect) {
        boolean z2 = z;
        super.onFocusChanged(true, i, rect);
    }
}
