package android.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

public class RippleLayout extends FrameLayout {
    private int a;
    private int b;
    private int c = 1152035498;
    private boolean d;
    private boolean e;
    private int f;

    public RippleLayout(Context context) {
        super(context);
    }

    private void setRippleDrawable(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (!(childAt instanceof RippleLayout)) {
                    setRippleDrawable(childAt);
                }
            }
            return;
        }
        Drawable background = view.getBackground();
        RippleHelper rippleHelper = background instanceof RippleHelper ? (RippleHelper) background : new RippleHelper(view);
        rippleHelper.setRippleColor(this.c);
        rippleHelper.setRippleLineColor(this.f);
        rippleHelper.setCircle(this.d);
        rippleHelper.setSingle(this.e);
    }

    public boolean isCircle() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.b = getChildCount();
        if (this.a != this.b) {
            this.a = this.b;
            setRippleDrawable(this);
        }
    }

    public void setCircle(boolean z) {
        this.d = z;
    }

    public void setRippleColor(int i) {
        this.c = i;
    }

    public void setRippleLineColor(int i) {
        this.f = i;
    }

    public void setSingle(boolean z) {
        this.e = z;
    }
}
