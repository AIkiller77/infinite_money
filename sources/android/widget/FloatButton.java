package android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import com.AndLua.LY.R;

public class FloatButton extends ImageView {
    private PopupWindow a;
    private CircleImageView b;
    private int c;
    private CardView d;
    private DisplayMetrics e;
    private RippleHelper f;

    public FloatButton(Context context) {
        super(context);
        a(context);
    }

    private int a(float f2) {
        return (int) TypedValue.applyDimension(1, f2, this.e);
    }

    private void a(Context context) {
        this.e = context.getResources().getDisplayMetrics();
        FrameLayout frameLayout = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(a(16.0f), a(16.0f), a(16.0f), a(16.0f));
        this.d = new CardView(context);
        this.d.setCardElevation((float) a(8.0f));
        this.b = new CircleImageView(context);
        this.b.setImageResource(R.drawable.icon);
        this.f = new RippleHelper(this.b);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(a(64.0f), a(64.0f));
        frameLayout.addView(this.d, layoutParams);
        this.d.addView(this.b, layoutParams2);
        this.d.setRadius((float) a(32.0f));
        this.a = new PopupWindow(-2, -2);
        this.a.setContentView(frameLayout);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i, i2);
        if (!this.a.isShowing()) {
            this.a.showAtLocation((View) getParent(), this.c, 0, 0);
        }
        this.a.update();
    }

    public void setGravity(int i) {
        this.c = i;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.b.setImageBitmap(bitmap);
    }

    public void setImageDrawable(Drawable drawable) {
        this.b.setImageDrawable(drawable);
    }

    public void setImageResource(int i) {
        this.b.setImageResource(i);
    }

    public void setRippleColor(int i) {
        this.f.setRippleColor(i);
    }
}
