package android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import com.AndLua.LY.R;

public class ToolBar extends LinearLayout {
    private TextView a;
    private TextView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private LinearLayout f;
    /* access modifiers changed from: private */
    public PopupMenu g;
    /* access modifiers changed from: private */
    public OnLogoClickListener h;
    /* access modifiers changed from: private */
    public OnNaviClickListener i;
    /* access modifiers changed from: private */
    public OnMenuItemClickListener j;
    private DisplayMetrics k;
    private int l;

    public interface OnLogoClickListener {
        void onLogoClick(View view);
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public interface OnNaviClickListener {
        void onNaviClick(View view);
    }

    public ToolBar(Context context) {
        super(context);
        a(context);
    }

    private int a(float f2) {
        return (int) TypedValue.applyDimension(1, f2, this.k);
    }

    private void a(Context context) {
        Context context2 = context;
        this.k = context.getResources().getDisplayMetrics();
        this.l = a(48.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.l, this.l);
        setMinimumHeight(this.l);
        this.c = new ImageView(context2);
        this.c.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.c.setVisibility(8);
        super.addView(this.c, layoutParams);
        this.d = new ImageView(context2);
        this.d.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.d.setImageResource(R.drawable.icon);
        this.d.setVisibility(8);
        super.addView(this.d, layoutParams);
        LinearLayout linearLayout = new LinearLayout(context2);
        int a2 = a(1.0f);
        linearLayout.setPadding(a2 * 4, a2, a2, a2);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        super.addView(linearLayout, new LinearLayout.LayoutParams(-1, this.l, 1.0f));
        this.a = new TextView(context2);
        this.a.setTextSize(1, 20.0f);
        this.a.setSingleLine(true);
        this.a.setTypeface(Typeface.DEFAULT_BOLD);
        linearLayout.addView(this.a, new LinearLayout.LayoutParams(-1, a(26.0f)));
        this.b = new TextView(context2);
        this.b.setTextSize(1, 14.0f);
        this.b.setSingleLine(true);
        this.b.setVisibility(8);
        linearLayout.addView(this.b, new LinearLayout.LayoutParams(-1, a(20.0f)));
        this.f = new LinearLayout(context2);
        super.addView(this.f, new LinearLayout.LayoutParams(-2, this.l));
        this.e = new ImageView(context2);
        this.e.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.e.setVisibility(8);
        super.addView(this.e, layoutParams);
        new RippleHelper(this.c);
        new RippleHelper(this.e);
        Paint paint = new Paint();
        paint.setColor(-7829368);
        Bitmap createBitmap = Bitmap.createBitmap(this.l, this.l, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        double d2 = (double) this.l;
        double d3 = d2 / 4.0d;
        float f2 = (float) ((int) d3);
        double d4 = d2 / 32.0d;
        float f3 = (float) ((int) (d3 * 3.0d));
        Canvas canvas2 = canvas;
        float f4 = f2;
        float f5 = f3;
        float f6 = f3;
        Paint paint2 = paint;
        canvas2.drawRect(f4, (float) ((int) (10.0d * d4)), f5, (float) ((int) (12.0d * d4)), paint2);
        float f7 = f6;
        canvas.drawRect(f2, (float) ((int) (15.0d * d4)), f7, (float) ((int) (17.0d * d4)), paint2);
        canvas.drawRect(f2, (float) ((int) (20.0d * d4)), f7, (float) ((int) (d4 * 22.0d)), paint2);
        this.c.setImageBitmap(createBitmap);
        Bitmap createBitmap2 = Bitmap.createBitmap(this.l, this.l, Bitmap.Config.ARGB_4444);
        Canvas canvas3 = new Canvas(createBitmap2);
        float f8 = (float) ((int) (d2 / 2.0d));
        double d5 = d2 / 3.0d;
        float f9 = (float) ((int) (d2 / 16.0d));
        canvas3.drawCircle(f8, (float) ((int) d5), f9, paint);
        canvas3.drawCircle(f8, f8, f9, paint);
        canvas3.drawCircle(f8, (float) ((int) (d5 * 2.0d)), f9, paint);
        this.e.setImageBitmap(createBitmap2);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ToolBar.this.i != null) {
                    ToolBar.this.i.onNaviClick(view);
                }
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ToolBar.this.h != null) {
                    ToolBar.this.h.onLogoClick(view);
                }
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ToolBar.this.g != null) {
                    ToolBar.this.g.show();
                }
            }
        });
        this.g = new PopupMenu(getContext(), this.e);
        this.g.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (ToolBar.this.j != null) {
                    return ToolBar.this.j.onMenuItemClick(menuItem);
                }
                return false;
            }
        });
    }

    public void addView(View view) {
        this.f.addView(view);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        this.f.addView(view, layoutParams);
    }

    public Menu getMenu() {
        setMenuEnabled(true);
        return this.g.getMenu();
    }

    public void setLogo(Drawable drawable) {
        this.d.setImageDrawable(drawable);
    }

    public void setLogoEnabled(boolean z) {
        this.d.setVisibility(z ? 0 : 8);
    }

    public void setMenuEnabled(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void setNaviEnabled(boolean z) {
        this.c.setVisibility(z ? 0 : 8);
    }

    public void setNaviIcon(Drawable drawable) {
        this.c.setImageDrawable(drawable);
    }

    public void setOnLogoClickListener(OnLogoClickListener onLogoClickListener) {
        this.h = onLogoClickListener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.j = onMenuItemClickListener;
    }

    public void setOnNaviClickListener(OnNaviClickListener onNaviClickListener) {
        this.i = onNaviClickListener;
    }

    public void setSubtitle(CharSequence charSequence) {
        int i2;
        TextView textView;
        if (charSequence == null || charSequence.length() == 0) {
            textView = this.b;
            i2 = 8;
        } else {
            textView = this.b;
            i2 = 0;
        }
        textView.setVisibility(i2);
        this.b.setText(charSequence);
    }

    public void setSubtitleColor(int i2) {
        this.b.setTextColor(i2);
    }

    public void setTitle(CharSequence charSequence) {
        this.a.setText(charSequence);
    }

    public void setTitleColor(int i2) {
        this.a.setTextColor(i2);
    }
}
