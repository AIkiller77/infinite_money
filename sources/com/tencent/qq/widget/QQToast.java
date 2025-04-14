package com.tencent.qq.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;

public class QQToast {
    private static Context context;
    private static ImageView imageview;
    private static TextView textview;
    private static Toast toast;
    private static LinearLayout viewgroup;

    public enum setBackgroundColors {
        ;
        
        private static setBackgroundColors[] $VALUES;
        public static final setBackgroundColors BLUE = null;
        public static final setBackgroundColors DEFAULT = null;
        public static final setBackgroundColors RED = null;
        public static final setBackgroundColors WHITE = null;
        private String name;

        static {
            setBackgroundColors setbackgroundcolors;
            setBackgroundColors setbackgroundcolors2;
            setBackgroundColors setbackgroundcolors3;
            setBackgroundColors setbackgroundcolors4;
            new setBackgroundColors("DEFAULT", 0, "#FFFFFF");
            DEFAULT = setbackgroundcolors;
            new setBackgroundColors("BLUE", 1, "#47BAFE");
            BLUE = setbackgroundcolors2;
            new setBackgroundColors("RED", 2, "#FE6C6C");
            RED = setbackgroundcolors3;
            new setBackgroundColors("WHITE", 3, "#FFFFFF");
            WHITE = setbackgroundcolors4;
            setBackgroundColors[] setbackgroundcolorsArr = new setBackgroundColors[4];
            setbackgroundcolorsArr[0] = DEFAULT;
            setBackgroundColors[] setbackgroundcolorsArr2 = setbackgroundcolorsArr;
            setbackgroundcolorsArr2[1] = BLUE;
            setBackgroundColors[] setbackgroundcolorsArr3 = setbackgroundcolorsArr2;
            setbackgroundcolorsArr3[2] = RED;
            setBackgroundColors[] setbackgroundcolorsArr4 = setbackgroundcolorsArr3;
            setbackgroundcolorsArr4[3] = WHITE;
            $VALUES = setbackgroundcolorsArr4;
        }

        setBackgroundColors(String str) {
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

    public QQToast() {
    }

    public static int dip2px(Context context2, double d) {
        return (int) ((d * ((double) context2.getResources().getDisplayMetrics().density)) + ((double) 0.5f));
    }

    public static Toast makeText(Context context2, CharSequence charSequence, setBackgroundColors setbackgroundcolors) {
        LinearLayout linearLayout;
        ViewGroup.LayoutParams layoutParams;
        LinearLayout linearLayout2;
        LinearLayout.LayoutParams layoutParams2;
        LinearLayout linearLayout3;
        LinearLayout.LayoutParams layoutParams3;
        ImageView imageView;
        LinearLayout.LayoutParams layoutParams4;
        TextView textView;
        ViewGroup.LayoutParams layoutParams5;
        Toast toast2;
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        Context context3 = context2;
        setBackgroundColors setbackgroundcolors2 = setbackgroundcolors;
        new LinearLayout(context3);
        LinearLayout linearLayout4 = linearLayout;
        linearLayout4.setOrientation(0);
        new LinearLayout.LayoutParams(-1, -2);
        linearLayout4.setLayoutParams(layoutParams);
        linearLayout4.setGravity(17);
        linearLayout4.setElevation((float) 12);
        linearLayout4.setBackgroundColor(-1);
        linearLayout4.setId(0);
        new LinearLayout(context3);
        LinearLayout linearLayout5 = linearLayout2;
        new LinearLayout.LayoutParams(-1, dip2px(context3, (double) 73));
        LinearLayout.LayoutParams layoutParams6 = layoutParams2;
        layoutParams6.setMargins(0, 0, 0, dip2px(context3, (double) 3));
        linearLayout5.setLayoutParams(layoutParams6);
        linearLayout5.setGravity(81);
        linearLayout5.setId(2);
        linearLayout4.addView(linearLayout5);
        new LinearLayout(context3);
        LinearLayout linearLayout6 = linearLayout3;
        linearLayout6.setOrientation(0);
        new LinearLayout.LayoutParams(-1, -2);
        LinearLayout.LayoutParams layoutParams7 = layoutParams3;
        layoutParams7.setMargins(0, 0, 0, dip2px(context3, (double) 10));
        linearLayout6.setLayoutParams(layoutParams7);
        linearLayout6.setGravity(3);
        linearLayout6.setId(3);
        linearLayout5.addView(linearLayout6);
        new ImageView(context3);
        ImageView imageView2 = imageView;
        new LinearLayout.LayoutParams(dip2px(context3, (double) 23), dip2px(context3, (double) 19));
        LinearLayout.LayoutParams layoutParams8 = layoutParams4;
        layoutParams8.setMargins(dip2px(context3, (double) 12), dip2px(context3, (double) 2), 0, 0);
        imageView2.setLayoutParams(layoutParams8);
        imageView2.setId(4);
        linearLayout6.addView(imageView2);
        new TextView(context3);
        TextView textView2 = textView;
        new LinearLayout.LayoutParams(-2, -1);
        textView2.setLayoutParams(layoutParams5);
        textView2.setPadding(dip2px(context3, (double) 4), dip2px(context3, (double) 0), 0, 0);
        textView2.setTextSize((float) 17);
        textView2.setId(5);
        linearLayout6.addView(textView2);
        imageview = (ImageView) linearLayout4.findViewById(4);
        viewgroup = (LinearLayout) linearLayout4.findViewById(0);
        textview = (TextView) linearLayout4.findViewById(5);
        textview.setText(charSequence);
        new Toast(context3);
        toast = toast2;
        toast.setView(linearLayout4);
        toast.getView().setSystemUiVisibility(1024);
        toast.setGravity(55, 0, 0);
        toast.setDuration(0);
        if (setbackgroundcolors2 == null) {
            textview.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            viewgroup.setBackgroundColor(-1);
            setAssetsRes(context3, imageview, "toast_image_day.png");
        } else if (setbackgroundcolors2 == setBackgroundColors.DEFAULT) {
            textview.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            viewgroup.setBackgroundColor(Color.parseColor(setbackgroundcolors2.getName()));
            try {
                new BitmapDrawable(context3.getResources(), context3.getAssets().open("toast_image_day.png"));
                imageview.setImageDrawable(drawable4);
            } catch (IOException e) {
                IOException iOException = e;
            }
        } else if (setbackgroundcolors2 == setBackgroundColors.WHITE) {
            textview.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            viewgroup.setBackgroundColor(Color.parseColor(setbackgroundcolors2.getName()));
            try {
                new BitmapDrawable(context3.getResources(), context3.getAssets().open("toast_image_day.png"));
                imageview.setImageDrawable(drawable3);
            } catch (IOException e2) {
                IOException iOException2 = e2;
            }
        } else if (setbackgroundcolors2 == setBackgroundColors.BLUE) {
            textview.setTextColor(-1);
            viewgroup.setBackgroundColor(Color.parseColor(setbackgroundcolors2.getName()));
            try {
                new BitmapDrawable(context3.getResources(), context3.getAssets().open("toast_image_light.png"));
                imageview.setImageDrawable(drawable2);
            } catch (IOException e3) {
                IOException iOException3 = e3;
            }
        } else if (setbackgroundcolors2 == setBackgroundColors.RED) {
            textview.setTextColor(-1);
            viewgroup.setBackgroundColor(Color.parseColor(setbackgroundcolors2.getName()));
            try {
                new BitmapDrawable(context3.getResources(), context3.getAssets().open("toast_image_light.png"));
                imageview.setImageDrawable(drawable);
            } catch (IOException e4) {
                IOException iOException4 = e4;
            }
        }
        return toast;
    }

    public static int px2dip(Context context2, float f) {
        return (int) ((f / context2.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void setAssetsRes(Context context2, ImageView imageView, String str) {
        Drawable drawable;
        Context context3 = context2;
        ImageView imageView2 = imageView;
        try {
            new BitmapDrawable(context3.getResources(), context3.getAssets().open(str));
            imageView2.setImageDrawable(drawable);
        } catch (IOException e) {
            IOException iOException = e;
        }
    }

    public QQToast show() {
        toast.show();
        return null;
    }
}
