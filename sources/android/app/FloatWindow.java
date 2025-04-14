package android.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatWindow {
    private Context a;
    private TitleBar b;
    /* access modifiers changed from: private */
    public WindowManager c;
    /* access modifiers changed from: private */
    public WindowManager.LayoutParams d;
    /* access modifiers changed from: private */
    public LinearLayout e;
    private FrameLayout f;
    /* access modifiers changed from: private */
    public int g;
    private DisplayMetrics h;
    /* access modifiers changed from: private */
    public int i = a();
    /* access modifiers changed from: private */
    public int j = b();

    private class ContentView extends LinearLayout {
        private int b = 0;
        private int c = 0;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private boolean j;
        private int k;
        private boolean l;

        public ContentView(Context context) {
            super(context);
            this.k = FloatWindow.this.a(8.0f);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            FloatWindow floatWindow;
            boolean z;
            if (motionEvent.getAction() == 4) {
                floatWindow = FloatWindow.this;
                z = false;
            } else {
                if (motionEvent.getAction() == 0) {
                    floatWindow = FloatWindow.this;
                    z = true;
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
            floatWindow.a(z);
            return super.onInterceptTouchEvent(motionEvent);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.d = (int) motionEvent.getRawY();
            this.e = (int) motionEvent.getRawX();
            if (motionEvent.getAction() == 4) {
                FloatWindow.this.a(false);
            } else if (motionEvent.getAction() == 0) {
                FloatWindow.this.a(true);
            }
            if (motionEvent.getAction() == 0) {
                if (((float) getWidth()) - motionEvent.getX() < ((float) this.k)) {
                    this.j = true;
                }
                if (((float) getHeight()) - motionEvent.getY() < ((float) this.k)) {
                    this.l = true;
                }
                this.f = getWidth();
                this.g = getHeight();
                this.c = this.d;
                this.b = this.e;
                this.h = FloatWindow.this.d.x;
                this.i = FloatWindow.this.d.y;
                return true;
            } else if (motionEvent.getAction() == 2) {
                FloatWindow.this.d.x = this.h;
                FloatWindow.this.d.y = this.i;
                if (this.j) {
                    FloatWindow.this.d.width = Math.min(this.f + (this.e - this.b), FloatWindow.this.i);
                }
                if (this.l) {
                    FloatWindow.this.d.height = Math.min(this.g + (this.d - this.c), FloatWindow.this.j);
                }
                FloatWindow.this.c.updateViewLayout(FloatWindow.this.e, FloatWindow.this.d);
                return true;
            } else {
                if (motionEvent.getAction() == 1) {
                    this.j = false;
                    this.l = false;
                }
                return true;
            }
        }
    }

    private class TitleBar extends LinearLayout {
        private TextView b;

        public TitleBar(Context context) {
            super(context);
            this.b = new TitleView(context);
            this.b.setSingleLine(true);
            TextView textView = new TextView(context);
            textView.setText("X");
            textView.setGravity(17);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(1140850943);
            gradientDrawable.setCornerRadius(4.0f);
            gradientDrawable.setStroke(2, FloatWindow.this.g);
            gradientDrawable.setAlpha(136);
            textView.setBackgroundDrawable(gradientDrawable);
            textView.setTextSize(1, 18.0f);
            textView.setOnClickListener(new View.OnClickListener(FloatWindow.this) {
                public void onClick(View view) {
                    FloatWindow.this.dismiss();
                }
            });
            addView(this.b, new LinearLayout.LayoutParams(-1, FloatWindow.this.a(24.0f), 1.0f));
            addView(textView, new LinearLayout.LayoutParams(FloatWindow.this.a(24.0f), FloatWindow.this.a(24.0f)));
        }

        public void setTitle(CharSequence charSequence) {
            this.b.setText(charSequence);
        }
    }

    private class TitleView extends TextView {
        private int b = 0;
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;
        private int g;
        private int h;

        public TitleView(Context context) {
            super(context);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            Rect rect = new Rect();
            getWindowVisibleDisplayFrame(rect);
            this.f = rect.top;
            this.g = (int) motionEvent.getRawY();
            this.h = (int) motionEvent.getRawX();
            if (motionEvent.getAction() == 0) {
                this.e = this.g - ((int) motionEvent.getY());
                this.d = this.h - ((int) motionEvent.getX());
                this.c = this.g;
                this.b = this.h;
                return true;
            } else if (motionEvent.getAction() != 2) {
                return true;
            } else {
                FloatWindow.this.d.gravity = 51;
                FloatWindow.this.d.x = this.d + (this.h - this.b);
                FloatWindow.this.d.y = ((this.e + (this.g - this.c)) - this.f) + 3;
                FloatWindow.this.c.updateViewLayout(FloatWindow.this.e, FloatWindow.this.d);
                return true;
            }
        }
    }

    public FloatWindow(Context context) {
        this.a = context;
        this.h = context.getResources().getDisplayMetrics();
        a(context);
    }

    private int a() {
        return this.a.getResources().getDisplayMetrics().widthPixels;
    }

    /* access modifiers changed from: private */
    public int a(float f2) {
        return (int) TypedValue.applyDimension(1, f2, this.h);
    }

    private void a(Context context) {
        this.c = (WindowManager) context.getApplicationContext().getSystemService("window");
        this.d = new WindowManager.LayoutParams();
        this.d.format = 1;
        this.d.flags = 262176;
        this.d.type = 2002;
        this.d.width = -2;
        this.d.height = -2;
        this.e = new ContentView(context);
        this.e.setPadding(a(8.0f), a(8.0f), a(8.0f), a(8.0f));
        this.e.setOrientation(1);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16842801, 16842806});
        int color = obtainStyledAttributes.getColor(0, 16711935);
        this.g = obtainStyledAttributes.getColor(1, 16711935);
        obtainStyledAttributes.recycle();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(4.0f);
        gradientDrawable.setStroke(2, this.g);
        gradientDrawable.setAlpha(136);
        this.e.setBackgroundDrawable(gradientDrawable);
        this.c.addView(this.e, this.d);
        this.e.setVisibility(8);
        this.b = new TitleBar(context);
        this.f = new FrameLayout(context);
        this.e.addView(this.b);
        this.e.addView(this.f);
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        WindowManager.LayoutParams layoutParams;
        int i2 = 8;
        if (!z) {
            layoutParams = this.d;
        } else if (this.d.flags == 8) {
            this.c.removeView(this.e);
            this.c.addView(this.e, this.d);
            layoutParams = this.d;
            i2 = 262176;
        } else {
            return;
        }
        layoutParams.flags = i2;
        this.c.updateViewLayout(this.e, this.d);
    }

    private int b() {
        return this.a.getResources().getDisplayMetrics().heightPixels;
    }

    public void dismiss() {
        this.c.removeView(this.e);
    }

    public Drawable getBackground() {
        return this.e.getBackground();
    }

    public void hide() {
        this.e.setVisibility(8);
    }

    public void setBackground(Drawable drawable) {
        this.e.setBackgroundDrawable(drawable);
    }

    public void setContentView(View view) {
        this.f.removeAllViews();
        this.f.addView(view);
    }

    public void setFlags(int i2) {
        this.d.flags = i2;
        this.c.updateViewLayout(this.e, this.d);
    }

    public void setFormat(int i2) {
        this.d.format = i2;
        this.c.updateViewLayout(this.e, this.d);
    }

    public void setTitle(CharSequence charSequence) {
        this.b.setTitle(charSequence);
    }

    public void setType(int i2) {
        this.d.type = i2;
        this.c.updateViewLayout(this.e, this.d);
    }

    public void show() {
        this.e.setVisibility(0);
    }
}
