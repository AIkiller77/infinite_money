package com.tencent.qq.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.IOException;

public class QQProgress {

    public enum setTheme {
        ;
        
        private static setTheme[] $VALUES;
        public static final setTheme BLACK = null;
        public static final setTheme DEFAULT = null;
        public static final setTheme WHITE = null;
        private String name;

        static {
            setTheme settheme;
            setTheme settheme2;
            setTheme settheme3;
            new setTheme("DEFAULT", 0, "#95000000");
            DEFAULT = settheme;
            new setTheme("BLACK", 1, "#95000000");
            BLACK = settheme2;
            new setTheme("WHITE", 2, "#95FFFFFF");
            WHITE = settheme3;
            setTheme[] setthemeArr = new setTheme[3];
            setthemeArr[0] = DEFAULT;
            setTheme[] setthemeArr2 = setthemeArr;
            setthemeArr2[1] = BLACK;
            setTheme[] setthemeArr3 = setthemeArr2;
            setthemeArr3[2] = WHITE;
            $VALUES = setthemeArr3;
        }

        setTheme(String str) {
            String str2 = r9;
            int i = r10;
            this.name = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            String str2 = str;
            this.name = str2;
        }
    }

    public QQProgress() {
    }

    public static int dip2px(Context context, double d) {
        return (int) ((d * ((double) context.getResources().getDisplayMetrics().density)) + ((double) 0.5f));
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void showPorgressBar(Context context, String str, setTheme settheme) {
        FrameLayout frameLayout;
        ViewGroup.LayoutParams layoutParams;
        LinearLayout linearLayout;
        LinearLayout.LayoutParams layoutParams2;
        RectF rectF;
        Shape shape;
        ShapeDrawable shapeDrawable;
        ProgressBar progressBar;
        LinearLayout.LayoutParams layoutParams3;
        ImageView imageView;
        LinearLayout.LayoutParams layoutParams4;
        Animation animation;
        Interpolator interpolator;
        TextView textView;
        LinearLayout.LayoutParams layoutParams5;
        Dialog dialog;
        Drawable drawable;
        Context context2 = context;
        String str2 = str;
        setTheme settheme2 = settheme;
        new FrameLayout(context2);
        FrameLayout frameLayout2 = frameLayout;
        new LinearLayout.LayoutParams(-1, -2);
        frameLayout2.setLayoutParams(layoutParams);
        int dip2px = dip2px(context2, (double) 15);
        int dip2px2 = dip2px(context2, (double) 10);
        frameLayout2.setPadding(dip2px2, dip2px, dip2px2, 0);
        frameLayout2.setBackgroundColor(17170445);
        frameLayout2.setId(1);
        new LinearLayout(context2);
        LinearLayout linearLayout2 = linearLayout;
        linearLayout2.setOrientation(0);
        new LinearLayout.LayoutParams(-1, dip2px(context2, (double) 40));
        LinearLayout.LayoutParams layoutParams6 = layoutParams2;
        int dip2px3 = dip2px(context2, (double) 10);
        layoutParams6.setMargins(dip2px3, 0, dip2px3, 0);
        layoutParams6.gravity = 17;
        linearLayout2.setLayoutParams(layoutParams6);
        linearLayout2.setGravity(17);
        int dip2px4 = dip2px(context2, (double) 2);
        float[] fArr = new float[8];
        fArr[0] = (float) dip2px4;
        float[] fArr2 = fArr;
        fArr2[1] = (float) dip2px4;
        float[] fArr3 = fArr2;
        fArr3[2] = (float) dip2px4;
        float[] fArr4 = fArr3;
        fArr4[3] = (float) dip2px4;
        float[] fArr5 = fArr4;
        fArr5[4] = (float) dip2px4;
        float[] fArr6 = fArr5;
        fArr6[5] = (float) dip2px4;
        float[] fArr7 = fArr6;
        fArr7[6] = (float) dip2px4;
        float[] fArr8 = fArr7;
        fArr8[7] = (float) dip2px4;
        float[] fArr9 = fArr8;
        new RectF((float) 0, (float) 0, (float) 0, (float) 0);
        RectF rectF2 = rectF;
        float[] fArr10 = new float[8];
        fArr10[0] = (float) 0;
        float[] fArr11 = fArr10;
        fArr11[1] = (float) 0;
        float[] fArr12 = fArr11;
        fArr12[2] = (float) 0;
        float[] fArr13 = fArr12;
        fArr13[3] = (float) 0;
        float[] fArr14 = fArr13;
        fArr14[4] = (float) 0;
        float[] fArr15 = fArr14;
        fArr15[5] = (float) 0;
        float[] fArr16 = fArr15;
        fArr16[6] = (float) 0;
        float[] fArr17 = fArr16;
        fArr17[7] = (float) 0;
        new RoundRectShape(fArr9, rectF2, fArr17);
        new ShapeDrawable(shape);
        ShapeDrawable shapeDrawable2 = shapeDrawable;
        shapeDrawable2.getPaint().setAntiAlias(true);
        shapeDrawable2.getPaint().setAntiAlias(true);
        shapeDrawable2.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable2.getPaint().setColor(Color.parseColor(settheme2.getName()));
        linearLayout2.setBackgroundDrawable(shapeDrawable2);
        linearLayout2.setId(2);
        frameLayout2.addView(linearLayout2);
        new ProgressBar(context2);
        ProgressBar progressBar2 = progressBar;
        int dip2px5 = dip2px(context2, (double) 17);
        new LinearLayout.LayoutParams(dip2px5, dip2px5);
        LinearLayout.LayoutParams layoutParams7 = layoutParams3;
        layoutParams7.gravity = 17;
        progressBar2.setLayoutParams(layoutParams7);
        progressBar2.setVisibility(8);
        linearLayout2.addView(progressBar2);
        new ImageView(context2);
        ImageView imageView2 = imageView;
        int dip2px6 = dip2px(context2, 17.0d);
        new LinearLayout.LayoutParams(dip2px6, dip2px6);
        LinearLayout.LayoutParams layoutParams8 = layoutParams4;
        layoutParams8.gravity = 17;
        imageView2.setLayoutParams(layoutParams8);
        ImageView imageView3 = imageView2;
        try {
            new BitmapDrawable(context2.getResources(), context2.getAssets().open("ProgressDrawable.png"));
            imageView3.setImageDrawable(drawable);
        } catch (IOException e) {
            IOException iOException = e;
        }
        imageView2.setVisibility(8);
        linearLayout2.addView(imageView2);
        new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        Animation animation2 = animation;
        new LinearInterpolator();
        animation2.setInterpolator(interpolator);
        animation2.setDuration(3600);
        animation2.setRepeatCount(-1);
        animation2.setFillAfter(true);
        animation2.setStartOffset(0);
        imageView2.setAnimation(animation2);
        new TextView(context2);
        TextView textView2 = textView;
        new LinearLayout.LayoutParams(-2, -2);
        LinearLayout.LayoutParams layoutParams9 = layoutParams5;
        layoutParams9.gravity = 17;
        layoutParams9.setMargins(0, dip2px(context2, (double) 0), 0, 0);
        textView2.setLayoutParams(layoutParams9);
        textView2.setPadding(dip2px(context2, (double) 10), 0, 0, 0);
        textView2.setGravity(17);
        textView2.setText(str2);
        textView2.setTextSize((float) 13);
        textView2.setId(4);
        linearLayout2.addView(textView2);
        new Dialog(context2);
        Dialog dialog2 = dialog;
        dialog2.setContentView(frameLayout2);
        Window window = dialog2.getWindow();
        window.setWindowAnimations(16973826);
        window.setGravity(49);
        WindowManager.LayoutParams attributes = window.getAttributes();
        dialog2.getWindow().setBackgroundDrawableResource(17170445);
        attributes.width = -1;
        window.setAttributes(attributes);
        if (settheme2 == null) {
            textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            progressBar2.getIndeterminateDrawable().setColorFilter(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.MULTIPLY);
        } else if (settheme2 == setTheme.DEFAULT) {
            textView2.setTextColor(-1);
            imageView2.setVisibility(0);
        } else if (settheme2 == setTheme.BLACK) {
            progressBar2.setVisibility(0);
            textView2.setTextColor(-1);
            progressBar2.getIndeterminateDrawable().setColorFilter(-1, PorterDuff.Mode.MULTIPLY);
        } else if (settheme2 == setTheme.WHITE) {
            progressBar2.setVisibility(0);
            textView2.setTextColor(-1795162112);
        }
        if (str2 != null) {
            dialog2.show();
        }
    }
}
