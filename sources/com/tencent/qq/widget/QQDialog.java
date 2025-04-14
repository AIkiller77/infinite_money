package com.tencent.qq.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class QQDialog {
    /* access modifiers changed from: private */
    public boolean Cancelable = true;
    /* access modifiers changed from: private */
    public boolean CanceledOnTouchOutside = true;
    /* access modifiers changed from: private */
    public int EditTextId = 0;
    /* access modifiers changed from: private */
    public EditText Edits;
    /* access modifiers changed from: private */
    public setColors MessageColor;
    /* access modifiers changed from: private */
    public setColors NegativeColor;
    /* access modifiers changed from: private */
    public int NegativeId = 0;
    /* access modifiers changed from: private */
    public View.OnClickListener NegativeListener;
    /* access modifiers changed from: private */
    public CharSequence NegativeText = null;
    /* access modifiers changed from: private */
    public setColors NeutralColor;
    /* access modifiers changed from: private */
    public int NeutralId = 0;
    /* access modifiers changed from: private */
    public View.OnClickListener NeutralListener;
    /* access modifiers changed from: private */
    public CharSequence NeutralText = null;
    /* access modifiers changed from: private */
    public setColors PositiveColor;
    /* access modifiers changed from: private */
    public int PositiveId = 0;
    /* access modifiers changed from: private */
    public View.OnClickListener PositiveListener;
    /* access modifiers changed from: private */
    public CharSequence PositiveText = null;
    /* access modifiers changed from: private */
    public setColors TitleColor;
    /* access modifiers changed from: private */
    public int defStyle;
    /* access modifiers changed from: private */
    public CharSequence hint = null;
    /* access modifiers changed from: private */
    public int hintId = 0;
    /* access modifiers changed from: private */
    public boolean isEditText = false;
    /* access modifiers changed from: private */
    public boolean isNegativeButtonShow = false;
    /* access modifiers changed from: private */
    public boolean isNeutralButtonShow = false;
    /* access modifiers changed from: private */
    public boolean isPositiveButtonShow = false;
    private Builder mBuilder;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public Dialog mDialog;
    /* access modifiers changed from: private */
    public View mView;
    /* access modifiers changed from: private */
    public int mViewId = 0;
    /* access modifiers changed from: private */
    public int messageId = 0;
    /* access modifiers changed from: private */
    public CharSequence messageText = null;
    /* access modifiers changed from: private */
    public CharSequence setEditText = null;
    /* access modifiers changed from: private */
    public ShapeDrawable shapeDrawable;
    /* access modifiers changed from: private */
    public int titleId = 0;
    /* access modifiers changed from: private */
    public CharSequence titleText = null;
    /* access modifiers changed from: private */
    public ViewGroup v2;

    private class Builder {
        private LinearLayout L1;
        /* access modifiers changed from: private */
        public TextView NegativeButton;
        /* access modifiers changed from: private */
        public TextView NeutralButton;
        /* access modifiers changed from: private */
        public TextView PositiveButton;
        private FrameLayout custom_miui_dialog_view;
        private LinearLayout id2;
        private LinearLayout mButton;
        private FrameLayout mFrameLayout;
        private TextView mMessage;
        private TextView mTitle;
        private View neutralview;
        private View positiveview;
        private MyScrollView scrollViews;
        private TextView testButton1;
        private TextView testButton2;
        private TextView testButton3;
        private final QQDialog this$0;
        private View v1view;
        private ViewGroup v4;

        Builder(QQDialog qQDialog) {
            Dialog dialog;
            LinearLayout linearLayout;
            ViewGroup.LayoutParams layoutParams;
            LinearLayout linearLayout2;
            LinearLayout.LayoutParams layoutParams2;
            LinearLayout linearLayout3;
            LinearLayout.LayoutParams layoutParams3;
            LinearLayout linearLayout4;
            LinearLayout.LayoutParams layoutParams4;
            TextView textView;
            LinearLayout.LayoutParams layoutParams5;
            LinearLayout linearLayout5;
            LinearLayout.LayoutParams layoutParams6;
            FrameLayout frameLayout;
            ViewGroup.LayoutParams layoutParams7;
            MyScrollView myScrollView;
            ViewGroup.LayoutParams layoutParams8;
            EditText editText;
            LinearLayout.LayoutParams layoutParams9;
            ShapeDrawable shapeDrawable;
            Shape shape;
            RectF rectF;
            LinearLayout linearLayout6;
            LinearLayout.LayoutParams layoutParams10;
            TextView textView2;
            ViewGroup.LayoutParams layoutParams11;
            LinearLayout linearLayout7;
            ViewGroup.LayoutParams layoutParams12;
            TextView textView3;
            LinearLayout.LayoutParams layoutParams13;
            TextView textView4;
            ViewGroup.LayoutParams layoutParams14;
            TextView textView5;
            TextView textView6;
            TextView textView7;
            LinearLayout linearLayout8;
            ViewGroup.LayoutParams layoutParams15;
            TextView textView8;
            ViewGroup.LayoutParams layoutParams16;
            View.OnTouchListener onTouchListener;
            LinearLayout linearLayout9;
            ViewGroup.LayoutParams layoutParams17;
            TextView textView9;
            ViewGroup.LayoutParams layoutParams18;
            TextView textView10;
            View.OnTouchListener onTouchListener2;
            LinearLayout linearLayout10;
            TextView textView11;
            TextView textView12;
            RectF rectF2;
            Shape shape2;
            ShapeDrawable shapeDrawable2;
            View.OnTouchListener onTouchListener3;
            this.this$0 = qQDialog;
            new Dialog(this.this$0.mContext, 16973937);
            this.this$0.mDialog = dialog;
            new LinearLayout(this.this$0.mContext);
            LinearLayout linearLayout11 = linearLayout;
            linearLayout11.setOrientation(1);
            new LinearLayout.LayoutParams(-1, -1);
            linearLayout11.setLayoutParams(layoutParams);
            linearLayout11.setGravity(17);
            new LinearLayout(this.this$0.mContext);
            LinearLayout linearLayout12 = linearLayout2;
            linearLayout12.setOrientation(1);
            new LinearLayout.LayoutParams(-1, -2);
            LinearLayout.LayoutParams layoutParams19 = layoutParams2;
            layoutParams19.gravity = 17;
            linearLayout12.setLayoutParams(layoutParams19);
            linearLayout12.setGravity(17);
            linearLayout12.setBackgroundDrawable(this.this$0.shapeDrawable);
            linearLayout12.setId(1);
            linearLayout11.addView(linearLayout12);
            new LinearLayout(this.this$0.mContext);
            LinearLayout linearLayout13 = linearLayout3;
            linearLayout13.setOrientation(1);
            new LinearLayout.LayoutParams(-1, -2);
            LinearLayout.LayoutParams layoutParams20 = layoutParams3;
            layoutParams20.setMargins(0, QQDialog.dip2px(this.this$0.mContext, (double) 4), 0, 0);
            linearLayout13.setLayoutParams(layoutParams20);
            linearLayout13.setId(2);
            linearLayout12.addView(linearLayout13);
            new LinearLayout(this.this$0.mContext);
            LinearLayout linearLayout14 = linearLayout4;
            linearLayout14.setOrientation(1);
            new LinearLayout.LayoutParams(-1, -2);
            LinearLayout.LayoutParams layoutParams21 = layoutParams4;
            layoutParams21.setMargins(0, QQDialog.dip2px(this.this$0.mContext, (double) 18), 0, 0);
            linearLayout14.setLayoutParams(layoutParams21);
            linearLayout14.setId(3);
            linearLayout13.addView(linearLayout14);
            new TextView(this.this$0.mContext);
            TextView textView13 = textView;
            new LinearLayout.LayoutParams(-1, -2);
            LinearLayout.LayoutParams layoutParams22 = layoutParams5;
            layoutParams22.setMargins(QQDialog.dip2px(this.this$0.mContext, (double) 10), 0, QQDialog.dip2px(this.this$0.mContext, (double) 10), 0);
            layoutParams22.gravity = 17;
            textView13.setLayoutParams(layoutParams22);
            textView13.setGravity(17);
            textView13.setTextSize((float) 19);
            textView13.setTextColor(-13421773);
            textView13.setSingleLine();
            textView13.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
            textView13.setId(4);
            linearLayout14.addView(textView13);
            new LinearLayout(this.this$0.mContext);
            this.L1 = linearLayout5;
            this.L1.setOrientation(1);
            new LinearLayout.LayoutParams(-1, -2);
            LinearLayout.LayoutParams layoutParams23 = layoutParams6;
            layoutParams23.weight = 1.0f;
            this.L1.setLayoutParams(layoutParams23);
            this.L1.setId(5);
            linearLayout13.addView(this.L1);
            new FrameLayout(this.this$0.mContext);
            this.custom_miui_dialog_view = frameLayout;
            new FrameLayout.LayoutParams(-1, -2);
            this.custom_miui_dialog_view.setLayoutParams(layoutParams7);
            this.custom_miui_dialog_view.setId(6);
            new MyScrollView(this.this$0.mContext);
            this.scrollViews = myScrollView;
            new Gallery.LayoutParams(-1, -2);
            this.scrollViews.setLayoutParams(layoutParams8);
            this.scrollViews.addView(this.custom_miui_dialog_view);
            this.L1.addView(this.scrollViews);
            new EditText(this.this$0.mContext);
            EditText editText2 = editText;
            new LinearLayout.LayoutParams(-1, -2);
            LinearLayout.LayoutParams layoutParams24 = layoutParams9;
            layoutParams24.setMargins(QQDialog.dip2px(this.this$0.mContext, (double) 30), QQDialog.dip2px(this.this$0.mContext, (double) 10), QQDialog.dip2px(this.this$0.mContext, (double) 30), 0);
            editText2.setLayoutParams(layoutParams24);
            int dip2px = QQDialog.dip2px(this.this$0.mContext, 0.4d);
            float[] fArr = new float[8];
            float[] fArr2 = new float[8];
            for (int i = 0; i < 8; i++) {
                int dip2px2 = QQDialog.dip2px(this.this$0.mContext, (double) 3);
                fArr[i] = (float) (dip2px2 + dip2px);
                fArr2[i] = (float) dip2px2;
            }
            new RectF((float) dip2px, (float) dip2px, (float) dip2px, (float) dip2px);
            new RoundRectShape(fArr, rectF, fArr2);
            new ShapeDrawable(shape);
            this.this$0.shapeDrawable = shapeDrawable;
            this.this$0.shapeDrawable.getPaint().setColor(-2302756);
            editText2.setBackgroundDrawable(this.this$0.shapeDrawable);
            editText2.setMaxLines(3);
            editText2.setTextSize((float) 13);
            editText2.setHintTextColor(-10790310);
            editText2.setId(7);
            this.L1.addView(editText2);
            new LinearLayout(this.this$0.mContext);
            LinearLayout linearLayout15 = linearLayout6;
            linearLayout15.setOrientation(0);
            new LinearLayout.LayoutParams(-1, -2);
            LinearLayout.LayoutParams layoutParams25 = layoutParams10;
            layoutParams25.setMargins(0, QQDialog.dip2px(this.this$0.mContext, (double) 18), 0, 0);
            linearLayout15.setLayoutParams(layoutParams25);
            linearLayout15.setId(19);
            linearLayout13.addView(linearLayout15);
            new TextView(this.this$0.mContext);
            TextView textView14 = textView2;
            new LinearLayout.LayoutParams(-1, QQDialog.dip2px(this.this$0.mContext, 0.3d));
            textView14.setLayoutParams(layoutParams11);
            textView14.setGravity(17);
            textView14.setBackgroundColor(-220406564);
            textView14.setId(8);
            linearLayout15.addView(textView14);
            new LinearLayout(this.this$0.mContext);
            LinearLayout linearLayout16 = linearLayout7;
            linearLayout16.setOrientation(0);
            new LinearLayout.LayoutParams(-1, QQDialog.dip2px(this.this$0.mContext, (double) 40));
            linearLayout16.setLayoutParams(layoutParams12);
            linearLayout16.setId(14);
            linearLayout12.addView(linearLayout16);
            new TextView(this.this$0.mContext);
            TextView textView15 = textView3;
            new LinearLayout.LayoutParams(0, -1);
            LinearLayout.LayoutParams layoutParams26 = layoutParams13;
            layoutParams26.weight = 1.0f;
            textView15.setLayoutParams(layoutParams26);
            textView15.setGravity(17);
            textView15.setTextSize((float) 17);
            textView15.setTextColor(-13421773);
            textView15.setId(11);
            linearLayout16.addView(textView15);
            new TextView(this.this$0.mContext);
            TextView textView16 = textView4;
            new LinearLayout.LayoutParams(QQDialog.dip2px(this.this$0.mContext, 0.2d), -1);
            ViewGroup.LayoutParams layoutParams27 = layoutParams14;
            textView16.setLayoutParams(layoutParams27);
            textView16.setGravity(17);
            textView16.setBackgroundColor(-220406564);
            textView16.setId(9);
            linearLayout16.addView(textView16);
            new TextView(this.this$0.mContext);
            TextView textView17 = textView5;
            textView17.setLayoutParams(layoutParams26);
            textView17.setGravity(17);
            textView17.setTextSize((float) 17);
            textView17.setTextColor(-13421773);
            textView17.setId(12);
            linearLayout16.addView(textView17);
            new TextView(this.this$0.mContext);
            TextView textView18 = textView6;
            textView18.setLayoutParams(layoutParams27);
            textView18.setGravity(17);
            textView18.setBackgroundColor(-220406564);
            textView18.setId(10);
            linearLayout16.addView(textView18);
            new TextView(this.this$0.mContext);
            TextView textView19 = textView7;
            textView19.setLayoutParams(layoutParams26);
            textView19.setGravity(17);
            textView19.setTextSize((float) 17);
            textView19.setTextColor(-13421773);
            textView19.setId(13);
            linearLayout16.addView(textView19);
            new LinearLayout(this.this$0.mContext);
            LinearLayout linearLayout17 = linearLayout8;
            linearLayout17.setOrientation(1);
            new LinearLayout.LayoutParams(-1, -2);
            linearLayout17.setLayoutParams(layoutParams15);
            linearLayout17.setId(15);
            linearLayout12.addView(linearLayout17);
            new TextView(this.this$0.mContext);
            TextView textView20 = textView8;
            new LinearLayout.LayoutParams(-1, QQDialog.dip2px(this.this$0.mContext, (double) 40));
            ViewGroup.LayoutParams layoutParams28 = layoutParams16;
            textView20.setLayoutParams(layoutParams28);
            textView20.setGravity(17);
            textView20.setTextSize((float) 17);
            textView20.setTextColor(-13421773);
            textView20.setBackgroundColor(-1);
            new View.OnTouchListener(this, textView20) {
                private final Builder this$0;
                private final TextView val$testButton1;

                {
                    this.this$0 = r7;
                    this.val$testButton1 = r8;
                }

                static Builder access$0(AnonymousClass100000000 r4) {
                    return r4.this$0;
                }

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    View view2 = view;
                    MotionEvent motionEvent2 = motionEvent;
                    if (motionEvent2.getAction() == 0) {
                        this.val$testButton1.setBackgroundColor(-1118482);
                    } else if (motionEvent2.getAction() == 1) {
                        this.val$testButton1.setBackgroundColor(-1);
                    }
                    return false;
                }
            };
            textView20.setOnTouchListener(onTouchListener);
            textView20.setId(16);
            linearLayout17.addView(textView20);
            new LinearLayout(this.this$0.mContext);
            LinearLayout linearLayout18 = linearLayout9;
            linearLayout18.setOrientation(0);
            new LinearLayout.LayoutParams(-1, -2);
            ViewGroup.LayoutParams layoutParams29 = layoutParams17;
            linearLayout18.setLayoutParams(layoutParams29);
            linearLayout17.addView(linearLayout18);
            new TextView(this.this$0.mContext);
            TextView textView21 = textView9;
            new LinearLayout.LayoutParams(-1, QQDialog.dip2px(this.this$0.mContext, 0.3d));
            ViewGroup.LayoutParams layoutParams30 = layoutParams18;
            textView21.setLayoutParams(layoutParams30);
            textView21.setGravity(17);
            textView21.setBackgroundColor(-220406564);
            linearLayout18.addView(textView21);
            new TextView(this.this$0.mContext);
            TextView textView22 = textView10;
            textView22.setLayoutParams(layoutParams28);
            textView22.setGravity(17);
            textView22.setTextSize((float) 17);
            textView22.setTextColor(-13421773);
            textView22.setBackgroundColor(-1);
            new View.OnTouchListener(this, textView22) {
                private final Builder this$0;
                private final TextView val$testButton2;

                {
                    this.this$0 = r7;
                    this.val$testButton2 = r8;
                }

                static Builder access$0(AnonymousClass100000001 r4) {
                    return r4.this$0;
                }

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    View view2 = view;
                    MotionEvent motionEvent2 = motionEvent;
                    if (motionEvent2.getAction() == 0) {
                        this.val$testButton2.setBackgroundColor(-1118482);
                    } else if (motionEvent2.getAction() == 1) {
                        this.val$testButton2.setBackgroundColor(-1);
                    }
                    return false;
                }
            };
            textView22.setOnTouchListener(onTouchListener2);
            textView22.setId(17);
            linearLayout17.addView(textView22);
            new LinearLayout(this.this$0.mContext);
            LinearLayout linearLayout19 = linearLayout10;
            linearLayout19.setOrientation(0);
            linearLayout19.setLayoutParams(layoutParams29);
            linearLayout17.addView(linearLayout19);
            new TextView(this.this$0.mContext);
            TextView textView23 = textView11;
            textView23.setLayoutParams(layoutParams30);
            textView23.setGravity(17);
            textView23.setBackgroundColor(-220406564);
            linearLayout19.addView(textView23);
            new TextView(this.this$0.mContext);
            TextView textView24 = textView12;
            textView24.setLayoutParams(layoutParams28);
            textView24.setGravity(17);
            textView24.setTextSize((float) 17);
            textView24.setTextColor(-13421773);
            int dip2px3 = QQDialog.dip2px(this.this$0.mContext, (double) 3);
            float[] fArr3 = new float[8];
            fArr3[0] = (float) 0;
            float[] fArr4 = fArr3;
            fArr4[1] = (float) 0;
            float[] fArr5 = fArr4;
            fArr5[2] = (float) 0;
            float[] fArr6 = fArr5;
            fArr6[3] = (float) 0;
            float[] fArr7 = fArr6;
            fArr7[4] = (float) dip2px3;
            float[] fArr8 = fArr7;
            fArr8[5] = (float) dip2px3;
            float[] fArr9 = fArr8;
            fArr9[6] = (float) dip2px3;
            float[] fArr10 = fArr9;
            fArr10[7] = (float) dip2px3;
            float[] fArr11 = fArr10;
            int i2 = 0 - 0;
            new RectF((float) i2, (float) i2, (float) i2, (float) i2);
            RectF rectF3 = rectF2;
            float[] fArr12 = new float[8];
            fArr12[0] = (float) 0;
            float[] fArr13 = fArr12;
            fArr13[1] = (float) 0;
            float[] fArr14 = fArr13;
            fArr14[2] = (float) 0;
            float[] fArr15 = fArr14;
            fArr15[3] = (float) 0;
            float[] fArr16 = fArr15;
            fArr16[4] = (float) 0;
            float[] fArr17 = fArr16;
            fArr17[5] = (float) 0;
            float[] fArr18 = fArr17;
            fArr18[6] = (float) 0;
            float[] fArr19 = fArr18;
            fArr19[7] = (float) 0;
            new RoundRectShape(fArr11, rectF3, fArr19);
            new ShapeDrawable(shape2);
            ShapeDrawable shapeDrawable3 = shapeDrawable2;
            shapeDrawable3.getPaint().setColor(-1);
            shapeDrawable3.getPaint().setAntiAlias(true);
            shapeDrawable3.getPaint().setStyle(Paint.Style.FILL);
            textView24.setBackgroundDrawable(shapeDrawable3);
            new View.OnTouchListener(this, textView24, shapeDrawable3) {
                private final Builder this$0;
                private final ShapeDrawable val$drawable0;
                private final TextView val$testButton3;

                {
                    this.this$0 = r8;
                    this.val$testButton3 = r9;
                    this.val$drawable0 = r10;
                }

                static Builder access$0(AnonymousClass100000002 r4) {
                    return r4.this$0;
                }

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    RectF rectF;
                    Shape shape;
                    ShapeDrawable shapeDrawable;
                    View view2 = view;
                    MotionEvent motionEvent2 = motionEvent;
                    if (motionEvent2.getAction() == 0) {
                        int dip2px = QQDialog.dip2px(Builder.access$0(this.this$0).mContext, (double) 3);
                        float[] fArr = new float[8];
                        fArr[0] = (float) 0;
                        float[] fArr2 = fArr;
                        fArr2[1] = (float) 0;
                        float[] fArr3 = fArr2;
                        fArr3[2] = (float) 0;
                        float[] fArr4 = fArr3;
                        fArr4[3] = (float) 0;
                        float[] fArr5 = fArr4;
                        fArr5[4] = (float) dip2px;
                        float[] fArr6 = fArr5;
                        fArr6[5] = (float) dip2px;
                        float[] fArr7 = fArr6;
                        fArr7[6] = (float) dip2px;
                        float[] fArr8 = fArr7;
                        fArr8[7] = (float) dip2px;
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
                        shapeDrawable2.getPaint().setColor(-1118482);
                        shapeDrawable2.getPaint().setAntiAlias(true);
                        shapeDrawable2.getPaint().setStyle(Paint.Style.FILL);
                        this.val$testButton3.setBackgroundDrawable(shapeDrawable2);
                    } else if (motionEvent2.getAction() == 1) {
                        this.val$testButton3.setBackgroundDrawable(this.val$drawable0);
                    }
                    return false;
                }
            };
            textView24.setOnTouchListener(onTouchListener3);
            textView24.setId(18);
            linearLayout17.addView(textView24);
            this.this$0.mDialog.setContentView(linearLayout11);
            this.this$0.mDialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
            Window window = this.this$0.mDialog.getWindow();
            window.setWindowAnimations(this.this$0.defStyle);
            this.this$0.mDialog.getWindow().setBackgroundDrawableResource(17170445);
            DisplayMetrics displayMetrics = this.this$0.mContext.getResources().getDisplayMetrics();
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setGravity(17);
            attributes.y = QQDialog.dip2px(this.this$0.mContext, (double) -22);
            attributes.width = (int) (((double) displayMetrics.widthPixels) * 0.7d);
            attributes.height = (int) (((double) displayMetrics.heightPixels) * 0.6d);
            window.setAttributes(attributes);
            setCustomView(this.this$0.mDialog);
        }

        static QQDialog access$0(Builder builder) {
            return builder.this$0;
        }

        private void setCustomView(Dialog dialog) {
            ScrollView scrollView;
            TextView textView;
            View.OnClickListener onClickListener;
            View.OnClickListener onClickListener2;
            View.OnClickListener onClickListener3;
            LinearLayout.LayoutParams layoutParams;
            Dialog dialog2 = dialog;
            this.mTitle = (TextView) dialog2.findViewById(4);
            this.v1view = dialog2.findViewById(8);
            this.neutralview = dialog2.findViewById(9);
            this.positiveview = dialog2.findViewById(10);
            this.PositiveButton = (TextView) dialog2.findViewById(13);
            this.NegativeButton = (TextView) dialog2.findViewById(12);
            this.NeutralButton = (TextView) dialog2.findViewById(11);
            this.mButton = (LinearLayout) dialog2.findViewById(14);
            this.mFrameLayout = (FrameLayout) dialog2.findViewById(6);
            this.this$0.v2 = (ViewGroup) dialog2.findViewById(19);
            this.this$0.Edits = (EditText) dialog2.findViewById(7);
            this.v4 = (ViewGroup) dialog2.findViewById(15);
            this.testButton1 = (TextView) dialog2.findViewById(16);
            this.testButton2 = (TextView) dialog2.findViewById(17);
            this.testButton3 = (TextView) dialog2.findViewById(18);
            this.id2 = (LinearLayout) dialog2.findViewById(2);
            this.PositiveButton.setTextColor(Color.parseColor("#FF333333"));
            this.NegativeButton.setTextColor(Color.parseColor("#FF333333"));
            this.NeutralButton.setTextColor(Color.parseColor("#FF333333"));
            this.id2.setBackgroundColor(-1);
            this.this$0.Edits.setVisibility(8);
            this.v4.setVisibility(8);
            new ScrollView(this.this$0.mContext);
            ScrollView scrollView2 = scrollView;
            new TextView(this.this$0.mContext);
            this.mMessage = textView;
            int dip2px = QQDialog.dip2px(this.this$0.mContext, (double) 20);
            this.mMessage.setPadding(dip2px, QQDialog.dip2px(this.this$0.mContext, (double) 13), dip2px, 0);
            this.mMessage.setTextColor(Color.parseColor("#FF333333"));
            this.mMessage.setTextSize((float) 16);
            this.mMessage.setLineSpacing((float) 1, 1.3f);
            scrollView2.addView(this.mMessage);
            this.mFrameLayout.addView(scrollView2);
            if (this.this$0.isEditText) {
                this.this$0.Edits.setVisibility(0);
                this.mMessage.setPadding(QQDialog.dip2px(this.this$0.mContext, (double) 30), QQDialog.dip2px(this.this$0.mContext, (double) 8), QQDialog.dip2px(this.this$0.mContext, (double) 30), 0);
                this.mMessage.setTextSize((float) 13);
                if (this.this$0.EditTextId == 0 && this.this$0.getEditText() != null) {
                    this.this$0.Edits.setText(this.this$0.setEditText);
                } else if (this.this$0.EditTextId != 0 && this.this$0.getEditText() == null) {
                    this.this$0.Edits.setText(this.this$0.EditTextId);
                }
                if (this.this$0.hintId == 0 && this.this$0.hint != null) {
                    this.this$0.Edits.setHint(this.this$0.hint);
                } else if (this.this$0.hintId != 0 && this.this$0.hint == null) {
                    this.this$0.Edits.setHint(this.this$0.hintId);
                }
            }
            if (this.this$0.mViewId != 0 && this.this$0.mView == null) {
                this.mMessage.setPadding(QQDialog.dip2px(this.this$0.mContext, (double) 10), QQDialog.dip2px(this.this$0.mContext, (double) 8), QQDialog.dip2px(this.this$0.mContext, (double) 10), 0);
                this.mMessage.setGravity(17);
                this.mMessage.setTextSize((float) 13);
                new LinearLayout.LayoutParams(-1, -2);
                LinearLayout.LayoutParams layoutParams2 = layoutParams;
                layoutParams2.setMargins(0, 0, 0, 0);
                this.this$0.v2.setLayoutParams(layoutParams2);
                this.mFrameLayout.addView((LinearLayout) LayoutInflater.from(this.this$0.mContext).inflate(this.this$0.mViewId, (ViewGroup) null));
            } else if (this.this$0.mViewId == 0 && this.this$0.mView != null) {
                this.mMessage.setPadding(QQDialog.dip2px(this.this$0.mContext, (double) 10), QQDialog.dip2px(this.this$0.mContext, (double) 8), QQDialog.dip2px(this.this$0.mContext, (double) 10), 0);
                this.mMessage.setGravity(17);
                this.mMessage.setTextSize((float) 13);
                int dip2px2 = QQDialog.dip2px(this.this$0.mContext, (double) 50);
                this.this$0.mView.setPadding(dip2px2, dip2px2, dip2px2, dip2px2);
                this.mFrameLayout.addView(this.this$0.mView);
            }
            if (this.this$0.CanceledOnTouchOutside) {
                this.this$0.mDialog.setCanceledOnTouchOutside(true);
            } else if (!this.this$0.CanceledOnTouchOutside) {
                this.this$0.mDialog.setCanceledOnTouchOutside(false);
            }
            if (this.this$0.Cancelable) {
                this.this$0.mDialog.setCancelable(true);
            } else if (!this.this$0.Cancelable) {
                this.this$0.mDialog.setCancelable(false);
            }
            if (this.this$0.titleId == 0 && this.this$0.titleText == null) {
                this.mTitle.setVisibility(8);
            } else if (this.this$0.titleId != 0 && this.this$0.titleText == null) {
                this.mTitle.setText(this.this$0.titleId);
                if (this.this$0.TitleColor == null) {
                    this.mTitle.setTextColor(-13421773);
                } else if (this.this$0.TitleColor == setColors.DEFAULT) {
                    this.mTitle.setTextColor(-13421773);
                } else if (this.this$0.TitleColor == setColors.BLUE) {
                    this.mTitle.setTextColor(-14042637);
                } else if (this.this$0.TitleColor == setColors.RED) {
                    this.mTitle.setTextColor(-178389);
                } else if (this.this$0.TitleColor == setColors.BLACK) {
                    this.mTitle.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.TitleColor == setColors.ORANGE) {
                    this.mTitle.setTextColor(-29417);
                } else if (this.this$0.TitleColor == setColors.GREEN) {
                    this.mTitle.setTextColor(-12269432);
                } else if (this.this$0.TitleColor == setColors.BROWN) {
                    this.mTitle.setTextColor(-15228911);
                }
            } else if (this.this$0.titleId == 0 && this.this$0.titleText != null) {
                this.mTitle.setText(this.this$0.titleText);
                if (this.this$0.TitleColor == null) {
                    this.mTitle.setTextColor(-13421773);
                } else if (this.this$0.TitleColor == setColors.DEFAULT) {
                    this.mTitle.setTextColor(-13421773);
                } else if (this.this$0.TitleColor == setColors.BLUE) {
                    this.mTitle.setTextColor(-14042637);
                } else if (this.this$0.TitleColor == setColors.RED) {
                    this.mTitle.setTextColor(-178389);
                } else if (this.this$0.TitleColor == setColors.BLACK) {
                    this.mTitle.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.TitleColor == setColors.ORANGE) {
                    this.mTitle.setTextColor(-29417);
                } else if (this.this$0.TitleColor == setColors.GREEN) {
                    this.mTitle.setTextColor(-12269432);
                } else if (this.this$0.TitleColor == setColors.BROWN) {
                    this.mTitle.setTextColor(-15228911);
                }
            }
            if (this.this$0.messageId == 0 && this.this$0.messageText == null) {
                this.mMessage.setVisibility(8);
            } else if (this.this$0.messageId != 0 && this.this$0.messageText == null) {
                this.mMessage.setText(this.this$0.messageId);
                if (this.this$0.MessageColor == null) {
                    this.mMessage.setTextColor(-13421773);
                } else if (this.this$0.MessageColor == setColors.DEFAULT) {
                    this.mMessage.setTextColor(-13421773);
                } else if (this.this$0.MessageColor == setColors.BLUE) {
                    this.mMessage.setTextColor(-14042637);
                } else if (this.this$0.MessageColor == setColors.RED) {
                    this.mMessage.setTextColor(-178389);
                } else if (this.this$0.MessageColor == setColors.BLACK) {
                    this.mMessage.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.MessageColor == setColors.ORANGE) {
                    this.mMessage.setTextColor(-29417);
                } else if (this.this$0.MessageColor == setColors.GREEN) {
                    this.mMessage.setTextColor(-12269432);
                } else if (this.this$0.MessageColor == setColors.BROWN) {
                    this.mMessage.setTextColor(-15228911);
                }
            } else if (this.this$0.messageId == 0 && this.this$0.messageText != null) {
                this.mMessage.setText(this.this$0.messageText);
                if (this.this$0.MessageColor == null) {
                    this.mMessage.setTextColor(-13421773);
                } else if (this.this$0.MessageColor == setColors.DEFAULT) {
                    this.mMessage.setTextColor(-13421773);
                } else if (this.this$0.MessageColor == setColors.BLUE) {
                    this.mMessage.setTextColor(-14042637);
                } else if (this.this$0.MessageColor == setColors.RED) {
                    this.mMessage.setTextColor(-178389);
                } else if (this.this$0.MessageColor == setColors.BLACK) {
                    this.mMessage.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.MessageColor == setColors.ORANGE) {
                    this.mMessage.setTextColor(-29417);
                } else if (this.this$0.MessageColor == setColors.GREEN) {
                    this.mMessage.setTextColor(-12269432);
                } else if (this.this$0.MessageColor == setColors.BROWN) {
                    this.mMessage.setTextColor(-15228911);
                }
            }
            if (this.this$0.PositiveId != 0 && this.this$0.PositiveText == null) {
                this.PositiveButton.setText(this.this$0.PositiveId);
                if (this.this$0.PositiveColor == null) {
                    this.PositiveButton.setTextColor(-13421773);
                } else if (this.this$0.PositiveColor == setColors.DEFAULT) {
                    this.PositiveButton.setTextColor(-13421773);
                } else if (this.this$0.PositiveColor == setColors.BLUE) {
                    this.PositiveButton.setTextColor(-14042637);
                } else if (this.this$0.PositiveColor == setColors.RED) {
                    this.PositiveButton.setTextColor(-178389);
                } else if (this.this$0.PositiveColor == setColors.BLACK) {
                    this.PositiveButton.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.PositiveColor == setColors.ORANGE) {
                    this.PositiveButton.setTextColor(-29417);
                } else if (this.this$0.PositiveColor == setColors.GREEN) {
                    this.PositiveButton.setTextColor(-12269432);
                } else if (this.this$0.PositiveColor == setColors.BROWN) {
                    this.PositiveButton.setTextColor(-15228911);
                }
            } else if (this.this$0.PositiveId == 0 && this.this$0.PositiveText != null) {
                this.PositiveButton.setText(this.this$0.PositiveText);
                if (this.this$0.PositiveColor == null) {
                    this.PositiveButton.setTextColor(-13421773);
                } else if (this.this$0.PositiveColor == setColors.DEFAULT) {
                    this.PositiveButton.setTextColor(-13421773);
                } else if (this.this$0.PositiveColor == setColors.BLUE) {
                    this.PositiveButton.setTextColor(-14042637);
                } else if (this.this$0.PositiveColor == setColors.RED) {
                    this.PositiveButton.setTextColor(-178389);
                } else if (this.this$0.PositiveColor == setColors.BLACK) {
                    this.PositiveButton.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.PositiveColor == setColors.ORANGE) {
                    this.PositiveButton.setTextColor(-29417);
                } else if (this.this$0.PositiveColor == setColors.GREEN) {
                    this.PositiveButton.setTextColor(-12269432);
                } else if (this.this$0.PositiveColor == setColors.BROWN) {
                    this.PositiveButton.setTextColor(-15228911);
                }
            }
            if (this.this$0.NegativeId != 0 && this.this$0.NegativeText == null) {
                this.NegativeButton.setText(this.this$0.NegativeId);
                if (this.this$0.NegativeColor == null) {
                    this.NegativeButton.setTextColor(-13421773);
                } else if (this.this$0.NegativeColor == setColors.DEFAULT) {
                    this.NegativeButton.setTextColor(-13421773);
                } else if (this.this$0.NegativeColor == setColors.BLUE) {
                    this.NegativeButton.setTextColor(-14042637);
                } else if (this.this$0.NegativeColor == setColors.RED) {
                    this.NegativeButton.setTextColor(-178389);
                } else if (this.this$0.NegativeColor == setColors.BLACK) {
                    this.NegativeButton.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.NegativeColor == setColors.ORANGE) {
                    this.NegativeButton.setTextColor(-29417);
                } else if (this.this$0.NegativeColor == setColors.GREEN) {
                    this.NegativeButton.setTextColor(-12269432);
                } else if (this.this$0.NegativeColor == setColors.BROWN) {
                    this.NegativeButton.setTextColor(-15228911);
                }
            } else if (this.this$0.NegativeId == 0 && this.this$0.NegativeText != null) {
                this.NegativeButton.setText(this.this$0.NegativeText);
                if (this.this$0.NegativeColor == null) {
                    this.NegativeButton.setTextColor(-13421773);
                } else if (this.this$0.NegativeColor == setColors.DEFAULT) {
                    this.NegativeButton.setTextColor(-13421773);
                } else if (this.this$0.NegativeColor == setColors.BLUE) {
                    this.NegativeButton.setTextColor(-14042637);
                } else if (this.this$0.NegativeColor == setColors.RED) {
                    this.NegativeButton.setTextColor(-178389);
                } else if (this.this$0.NegativeColor == setColors.BLACK) {
                    this.NegativeButton.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.NegativeColor == setColors.ORANGE) {
                    this.NegativeButton.setTextColor(-29417);
                } else if (this.this$0.NegativeColor == setColors.GREEN) {
                    this.NegativeButton.setTextColor(-12269432);
                } else if (this.this$0.NegativeColor == setColors.BROWN) {
                    this.NegativeButton.setTextColor(-15228911);
                }
            }
            if (this.this$0.NeutralId != 0 && this.this$0.NeutralText == null) {
                this.NeutralButton.setText(this.this$0.NegativeId);
                if (this.this$0.NeutralColor == null) {
                    this.NeutralButton.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.NeutralColor == setColors.DEFAULT) {
                    this.NeutralButton.setTextColor(-13421773);
                } else if (this.this$0.NeutralColor == setColors.BLUE) {
                    this.NeutralButton.setTextColor(-14042637);
                } else if (this.this$0.NeutralColor == setColors.RED) {
                    this.NeutralButton.setTextColor(-178389);
                } else if (this.this$0.NeutralColor == setColors.BLACK) {
                    this.NeutralButton.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.NeutralColor == setColors.ORANGE) {
                    this.NeutralButton.setTextColor(-29417);
                } else if (this.this$0.NeutralColor == setColors.GREEN) {
                    this.NeutralButton.setTextColor(-12269432);
                } else if (this.this$0.NeutralColor == setColors.BROWN) {
                    this.NeutralButton.setTextColor(-15228911);
                }
            } else if (this.this$0.NeutralId == 0 && this.this$0.NeutralText != null) {
                this.NeutralButton.setText(this.this$0.NeutralText);
                if (this.this$0.NeutralColor == null) {
                    this.NeutralButton.setTextColor(-13421773);
                } else if (this.this$0.NeutralColor == setColors.DEFAULT) {
                    this.NeutralButton.setTextColor(-13421773);
                } else if (this.this$0.NeutralColor == setColors.BLUE) {
                    this.NeutralButton.setTextColor(-14042637);
                } else if (this.this$0.NeutralColor == setColors.RED) {
                    this.NeutralButton.setTextColor(-178389);
                } else if (this.this$0.NeutralColor == setColors.BLACK) {
                    this.NeutralButton.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else if (this.this$0.NeutralColor == setColors.ORANGE) {
                    this.NeutralButton.setTextColor(-29417);
                } else if (this.this$0.NeutralColor == setColors.GREEN) {
                    this.NeutralButton.setTextColor(-12269432);
                } else if (this.this$0.NeutralColor == setColors.BROWN) {
                    this.NeutralButton.setTextColor(-15228911);
                }
            }
            if (this.this$0.PositiveListener != null) {
                this.PositiveButton.setOnClickListener(this.this$0.PositiveListener);
            } else {
                new OnDialogButtonClickListener(this.this$0);
                this.PositiveButton.setOnClickListener(onClickListener);
            }
            if (this.this$0.NegativeListener != null) {
                this.NegativeButton.setOnClickListener(this.this$0.NegativeListener);
            } else {
                new OnDialogButtonClickListener(this.this$0);
                this.NegativeButton.setOnClickListener(onClickListener2);
            }
            if (this.this$0.NeutralListener != null) {
                this.NeutralButton.setOnClickListener(this.this$0.NeutralListener);
            } else {
                new OnDialogButtonClickListener(this.this$0);
                this.NeutralButton.setOnClickListener(onClickListener3);
            }
            viewButton();
        }

        /* JADX WARNING: Removed duplicated region for block: B:157:0x12e4  */
        /* JADX WARNING: Removed duplicated region for block: B:206:0x152f  */
        /* JADX WARNING: Removed duplicated region for block: B:232:0x166c  */
        /* JADX WARNING: Removed duplicated region for block: B:233:0x168c  */
        /* JADX WARNING: Removed duplicated region for block: B:234:0x16ac  */
        /* JADX WARNING: Removed duplicated region for block: B:64:0x0e84  */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x0ed9  */
        /* JADX WARNING: Removed duplicated region for block: B:78:0x0f28  */
        /* JADX WARNING: Removed duplicated region for block: B:81:0x0f4d  */
        /* JADX WARNING: Removed duplicated region for block: B:84:0x0f72  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void viewButton() {
            /*
                r45 = this;
                r2 = r45
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                android.content.Context r38 = r38.mContext
                r39 = 3
                r0 = r39
                double r0 = (double) r0
                r39 = r0
                int r38 = com.tencent.qq.widget.QQDialog.dip2px(r38, r39)
                r4 = r38
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = r4
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = r4
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = r4
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = r4
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r5 = r38
                android.graphics.RectF r38 = new android.graphics.RectF
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r0 = r40
                float r0 = (float) r0
                r40 = r0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r42 = 0
                r0 = r42
                float r0 = (float) r0
                r42 = r0
                r43 = 0
                r0 = r43
                float r0 = (float) r0
                r43 = r0
                r39.<init>(r40, r41, r42, r43)
                r6 = r38
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r7 = r38
                android.graphics.drawable.shapes.RoundRectShape r38 = new android.graphics.drawable.shapes.RoundRectShape
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r5
                r41 = r6
                r42 = r7
                r39.<init>(r40, r41, r42)
                r8 = r38
                android.graphics.drawable.ShapeDrawable r38 = new android.graphics.drawable.ShapeDrawable
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r8
                r39.<init>(r40)
                r9 = r38
                r38 = r9
                android.graphics.Paint r38 = r38.getPaint()
                r39 = -1
                r38.setColor(r39)
                r38 = r9
                android.graphics.Paint r38 = r38.getPaint()
                r39 = 1
                r38.setAntiAlias(r39)
                r38 = r9
                android.graphics.Paint r38 = r38.getPaint()
                android.graphics.Paint$Style r39 = android.graphics.Paint.Style.FILL
                r38.setStyle(r39)
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = r4
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = r4
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = r4
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = r4
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r10 = r38
                android.graphics.RectF r38 = new android.graphics.RectF
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r0 = r40
                float r0 = (float) r0
                r40 = r0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r42 = 0
                r0 = r42
                float r0 = (float) r0
                r42 = r0
                r43 = 0
                r0 = r43
                float r0 = (float) r0
                r43 = r0
                r39.<init>(r40, r41, r42, r43)
                r11 = r38
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r12 = r38
                android.graphics.drawable.shapes.RoundRectShape r38 = new android.graphics.drawable.shapes.RoundRectShape
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r10
                r41 = r11
                r42 = r12
                r39.<init>(r40, r41, r42)
                r13 = r38
                android.graphics.drawable.ShapeDrawable r38 = new android.graphics.drawable.ShapeDrawable
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r13
                r39.<init>(r40)
                r14 = r38
                r38 = r14
                android.graphics.Paint r38 = r38.getPaint()
                r39 = -1118482(0xffffffffffeeeeee, float:NaN)
                r38.setColor(r39)
                r38 = r14
                android.graphics.Paint r38 = r38.getPaint()
                r39 = 1
                r38.setAntiAlias(r39)
                r38 = r14
                android.graphics.Paint r38 = r38.getPaint()
                android.graphics.Paint$Style r39 = android.graphics.Paint.Style.FILL
                r38.setStyle(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                android.content.Context r38 = r38.mContext
                r39 = 3
                r0 = r39
                double r0 = (double) r0
                r39 = r0
                int r38 = com.tencent.qq.widget.QQDialog.dip2px(r38, r39)
                r15 = r38
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = r15
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = r15
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r16 = r38
                android.graphics.RectF r38 = new android.graphics.RectF
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r0 = r40
                float r0 = (float) r0
                r40 = r0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r42 = 0
                r0 = r42
                float r0 = (float) r0
                r42 = r0
                r43 = 0
                r0 = r43
                float r0 = (float) r0
                r43 = r0
                r39.<init>(r40, r41, r42, r43)
                r17 = r38
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r18 = r38
                android.graphics.drawable.shapes.RoundRectShape r38 = new android.graphics.drawable.shapes.RoundRectShape
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r16
                r41 = r17
                r42 = r18
                r39.<init>(r40, r41, r42)
                r19 = r38
                android.graphics.drawable.ShapeDrawable r38 = new android.graphics.drawable.ShapeDrawable
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r19
                r39.<init>(r40)
                r20 = r38
                r38 = r20
                android.graphics.Paint r38 = r38.getPaint()
                r39 = -1
                r38.setColor(r39)
                r38 = r20
                android.graphics.Paint r38 = r38.getPaint()
                r39 = 1
                r38.setAntiAlias(r39)
                r38 = r20
                android.graphics.Paint r38 = r38.getPaint()
                android.graphics.Paint$Style r39 = android.graphics.Paint.Style.FILL
                r38.setStyle(r39)
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = r15
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = r15
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r21 = r38
                android.graphics.RectF r38 = new android.graphics.RectF
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r0 = r40
                float r0 = (float) r0
                r40 = r0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r42 = 0
                r0 = r42
                float r0 = (float) r0
                r42 = r0
                r43 = 0
                r0 = r43
                float r0 = (float) r0
                r43 = r0
                r39.<init>(r40, r41, r42, r43)
                r22 = r38
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r23 = r38
                android.graphics.drawable.shapes.RoundRectShape r38 = new android.graphics.drawable.shapes.RoundRectShape
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r21
                r41 = r22
                r42 = r23
                r39.<init>(r40, r41, r42)
                r24 = r38
                android.graphics.drawable.ShapeDrawable r38 = new android.graphics.drawable.ShapeDrawable
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r24
                r39.<init>(r40)
                r25 = r38
                r38 = r25
                android.graphics.Paint r38 = r38.getPaint()
                r39 = -1118482(0xffffffffffeeeeee, float:NaN)
                r38.setColor(r39)
                r38 = r25
                android.graphics.Paint r38 = r38.getPaint()
                r39 = 1
                r38.setAntiAlias(r39)
                r38 = r25
                android.graphics.Paint r38 = r38.getPaint()
                android.graphics.Paint$Style r39 = android.graphics.Paint.Style.FILL
                r38.setStyle(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                android.content.Context r38 = r38.mContext
                r39 = 3
                r0 = r39
                double r0 = (double) r0
                r39 = r0
                int r38 = com.tencent.qq.widget.QQDialog.dip2px(r38, r39)
                r26 = r38
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = r26
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = r26
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r27 = r38
                android.graphics.RectF r38 = new android.graphics.RectF
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r0 = r40
                float r0 = (float) r0
                r40 = r0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r42 = 0
                r0 = r42
                float r0 = (float) r0
                r42 = r0
                r43 = 0
                r0 = r43
                float r0 = (float) r0
                r43 = r0
                r39.<init>(r40, r41, r42, r43)
                r28 = r38
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r29 = r38
                android.graphics.drawable.shapes.RoundRectShape r38 = new android.graphics.drawable.shapes.RoundRectShape
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r27
                r41 = r28
                r42 = r29
                r39.<init>(r40, r41, r42)
                r30 = r38
                android.graphics.drawable.ShapeDrawable r38 = new android.graphics.drawable.ShapeDrawable
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r30
                r39.<init>(r40)
                r31 = r38
                r38 = r31
                android.graphics.Paint r38 = r38.getPaint()
                r39 = -1
                r38.setColor(r39)
                r38 = r31
                android.graphics.Paint r38 = r38.getPaint()
                r39 = 1
                r38.setAntiAlias(r39)
                r38 = r31
                android.graphics.Paint r38 = r38.getPaint()
                android.graphics.Paint$Style r39 = android.graphics.Paint.Style.FILL
                r38.setStyle(r39)
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = r26
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = r26
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r32 = r38
                android.graphics.RectF r38 = new android.graphics.RectF
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r0 = r40
                float r0 = (float) r0
                r40 = r0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r42 = 0
                r0 = r42
                float r0 = (float) r0
                r42 = r0
                r43 = 0
                r0 = r43
                float r0 = (float) r0
                r43 = r0
                r39.<init>(r40, r41, r42, r43)
                r33 = r38
                r38 = 8
                r0 = r38
                float[] r0 = new float[r0]
                r38 = r0
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 0
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 1
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 2
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 3
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 4
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 5
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 6
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = 7
                r41 = 0
                r0 = r41
                float r0 = (float) r0
                r41 = r0
                r39[r40] = r41
                r34 = r38
                android.graphics.drawable.shapes.RoundRectShape r38 = new android.graphics.drawable.shapes.RoundRectShape
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r32
                r41 = r33
                r42 = r34
                r39.<init>(r40, r41, r42)
                r35 = r38
                android.graphics.drawable.ShapeDrawable r38 = new android.graphics.drawable.ShapeDrawable
                r44 = r38
                r38 = r44
                r39 = r44
                r40 = r35
                r39.<init>(r40)
                r36 = r38
                r38 = r36
                android.graphics.Paint r38 = r38.getPaint()
                r39 = -1118482(0xffffffffffeeeeee, float:NaN)
                r38.setColor(r39)
                r38 = r36
                android.graphics.Paint r38 = r38.getPaint()
                r39 = 1
                r38.setAntiAlias(r39)
                r38 = r36
                android.graphics.Paint r38 = r38.getPaint()
                android.graphics.Paint$Style r39 = android.graphics.Paint.Style.FILL
                r38.setStyle(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isPositiveButtonShow
                if (r38 != 0) goto L_0x0aa0
            L_0x09be:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isPositiveButtonShow
                if (r38 != 0) goto L_0x0b1f
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNegativeButtonShow
                if (r38 != 0) goto L_0x0b21
            L_0x09da:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isPositiveButtonShow
                if (r38 != 0) goto L_0x0b90
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNegativeButtonShow
                if (r38 != 0) goto L_0x0b92
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNeutralButtonShow
                if (r38 != 0) goto L_0x0b94
            L_0x0a04:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isPositiveButtonShow
                if (r38 != 0) goto L_0x0bf3
            L_0x0a12:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isPositiveButtonShow
                if (r38 != 0) goto L_0x0c81
            L_0x0a20:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isPositiveButtonShow
                if (r38 != 0) goto L_0x0d0f
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNegativeButtonShow
                if (r38 != 0) goto L_0x0d11
            L_0x0a3c:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isPositiveButtonShow
                if (r38 != 0) goto L_0x0d8f
            L_0x0a4a:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isPositiveButtonShow
                if (r38 != 0) goto L_0x16cc
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNegativeButtonShow
                if (r38 != 0) goto L_0x16ce
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNeutralButtonShow
                if (r38 != 0) goto L_0x16d0
                r38 = r2
                r0 = r38
                android.widget.LinearLayout r0 = r0.mButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                android.view.ViewGroup r38 = r38.v2
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.v1view
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
            L_0x0a9f:
                return
            L_0x0aa0:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNegativeButtonShow
                if (r38 != 0) goto L_0x0b1b
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNeutralButtonShow
                if (r38 != 0) goto L_0x0b1d
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.PositiveButton
                r38 = r0
                r39 = r9
                r38.setBackgroundDrawable(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.PositiveButton
                r38 = r0
                com.tencent.qq.widget.QQDialog$Builder$100000003 r39 = new com.tencent.qq.widget.QQDialog$Builder$100000003
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r42 = r14
                r43 = r9
                r40.<init>(r41, r42, r43)
                r38.setOnTouchListener(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NegativeButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NeutralButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.neutralview
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.positiveview
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                goto L_0x09be
            L_0x0b1b:
                goto L_0x09be
            L_0x0b1d:
                goto L_0x09be
            L_0x0b1f:
                goto L_0x09da
            L_0x0b21:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNeutralButtonShow
                if (r38 != 0) goto L_0x0b8e
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.PositiveButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NegativeButton
                r38 = r0
                r39 = r9
                r38.setBackgroundDrawable(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NegativeButton
                r38 = r0
                com.tencent.qq.widget.QQDialog$Builder$100000004 r39 = new com.tencent.qq.widget.QQDialog$Builder$100000004
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r42 = r14
                r43 = r9
                r40.<init>(r41, r42, r43)
                r38.setOnTouchListener(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NeutralButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.positiveview
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.neutralview
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                goto L_0x09da
            L_0x0b8e:
                goto L_0x09da
            L_0x0b90:
                goto L_0x0a04
            L_0x0b92:
                goto L_0x0a04
            L_0x0b94:
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.PositiveButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NegativeButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.neutralview
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.positiveview
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NeutralButton
                r38 = r0
                r39 = r9
                r38.setBackgroundDrawable(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NeutralButton
                r38 = r0
                com.tencent.qq.widget.QQDialog$Builder$100000005 r39 = new com.tencent.qq.widget.QQDialog$Builder$100000005
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r42 = r14
                r43 = r9
                r40.<init>(r41, r42, r43)
                r38.setOnTouchListener(r39)
                goto L_0x0a04
            L_0x0bf3:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNegativeButtonShow
                if (r38 != 0) goto L_0x0c03
                goto L_0x0a12
            L_0x0c03:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNeutralButtonShow
                if (r38 != 0) goto L_0x0c7f
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.PositiveButton
                r38 = r0
                r39 = r20
                r38.setBackgroundDrawable(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.PositiveButton
                r38 = r0
                com.tencent.qq.widget.QQDialog$Builder$100000006 r39 = new com.tencent.qq.widget.QQDialog$Builder$100000006
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r42 = r25
                r43 = r20
                r40.<init>(r41, r42, r43)
                r38.setOnTouchListener(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NegativeButton
                r38 = r0
                r39 = r31
                r38.setBackgroundDrawable(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NegativeButton
                r38 = r0
                com.tencent.qq.widget.QQDialog$Builder$100000007 r39 = new com.tencent.qq.widget.QQDialog$Builder$100000007
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r42 = r36
                r43 = r31
                r40.<init>(r41, r42, r43)
                r38.setOnTouchListener(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NeutralButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.neutralview
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                goto L_0x0a12
            L_0x0c7f:
                goto L_0x0a12
            L_0x0c81:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNegativeButtonShow
                if (r38 != 0) goto L_0x0c9f
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNeutralButtonShow
                if (r38 != 0) goto L_0x0ca1
                goto L_0x0a20
            L_0x0c9f:
                goto L_0x0a20
            L_0x0ca1:
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.PositiveButton
                r38 = r0
                r39 = r20
                r38.setBackgroundDrawable(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.PositiveButton
                r38 = r0
                com.tencent.qq.widget.QQDialog$Builder$100000008 r39 = new com.tencent.qq.widget.QQDialog$Builder$100000008
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r42 = r25
                r43 = r20
                r40.<init>(r41, r42, r43)
                r38.setOnTouchListener(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NegativeButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.positiveview
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NeutralButton
                r38 = r0
                r39 = r31
                r38.setBackgroundDrawable(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NeutralButton
                r38 = r0
                com.tencent.qq.widget.QQDialog$Builder$100000009 r39 = new com.tencent.qq.widget.QQDialog$Builder$100000009
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r42 = r36
                r43 = r31
                r40.<init>(r41, r42, r43)
                r38.setOnTouchListener(r39)
                goto L_0x0a20
            L_0x0d0f:
                goto L_0x0a3c
            L_0x0d11:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNeutralButtonShow
                if (r38 != 0) goto L_0x0d21
                goto L_0x0a3c
            L_0x0d21:
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.PositiveButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.View r0 = r0.positiveview
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NegativeButton
                r38 = r0
                r39 = r20
                r38.setBackgroundDrawable(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NegativeButton
                r38 = r0
                com.tencent.qq.widget.QQDialog$Builder$100000010 r39 = new com.tencent.qq.widget.QQDialog$Builder$100000010
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r42 = r25
                r43 = r20
                r40.<init>(r41, r42, r43)
                r38.setOnTouchListener(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NeutralButton
                r38 = r0
                r39 = r31
                r38.setBackgroundDrawable(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.NeutralButton
                r38 = r0
                com.tencent.qq.widget.QQDialog$Builder$100000011 r39 = new com.tencent.qq.widget.QQDialog$Builder$100000011
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r42 = r36
                r43 = r31
                r40.<init>(r41, r42, r43)
                r38.setOnTouchListener(r39)
                goto L_0x0a3c
            L_0x0d8f:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNegativeButtonShow
                if (r38 != 0) goto L_0x0d9f
                goto L_0x0a4a
            L_0x0d9f:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                boolean r38 = r38.isNeutralButtonShow
                if (r38 != 0) goto L_0x0daf
                goto L_0x0a4a
            L_0x0daf:
                r38 = r2
                r0 = r38
                android.widget.LinearLayout r0 = r0.mButton
                r38 = r0
                r39 = 8
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.view.ViewGroup r0 = r0.v4
                r38 = r0
                r39 = 0
                r38.setVisibility(r39)
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.mMessage
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                android.content.Context r39 = r39.mContext
                r40 = 10
                r0 = r40
                double r0 = (double) r0
                r40 = r0
                int r39 = com.tencent.qq.widget.QQDialog.dip2px(r39, r40)
                r40 = r2
                r0 = r40
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r40 = r0
                android.content.Context r40 = r40.mContext
                r41 = 8
                r0 = r41
                double r0 = (double) r0
                r41 = r0
                int r40 = com.tencent.qq.widget.QQDialog.dip2px(r40, r41)
                r41 = r2
                r0 = r41
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r41 = r0
                android.content.Context r41 = r41.mContext
                r42 = 10
                r0 = r42
                double r0 = (double) r0
                r42 = r0
                int r41 = com.tencent.qq.widget.QQDialog.dip2px(r41, r42)
                r42 = 0
                r38.setPadding(r39, r40, r41, r42)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                int r38 = r38.PositiveId
                r39 = 0
                r0 = r38
                r1 = r39
                if (r0 == r1) goto L_0x1085
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                java.lang.CharSequence r38 = r38.PositiveText
                if (r38 != 0) goto L_0x1085
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                int r39 = r39.PositiveId
                r38.setText(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                if (r38 != 0) goto L_0x0f8b
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
            L_0x0e70:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                int r38 = r38.NegativeId
                r39 = 0
                r0 = r38
                r1 = r39
                if (r0 == r1) goto L_0x12d0
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                java.lang.CharSequence r38 = r38.NegativeText
                if (r38 != 0) goto L_0x12d0
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                int r39 = r39.NegativeId
                r38.setText(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                if (r38 != 0) goto L_0x11d6
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
            L_0x0ec5:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                int r38 = r38.NeutralId
                r39 = 0
                r0 = r38
                r1 = r39
                if (r0 == r1) goto L_0x151b
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                java.lang.CharSequence r38 = r38.NeutralText
                if (r38 != 0) goto L_0x151b
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                int r39 = r39.NegativeId
                r38.setText(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                if (r38 != 0) goto L_0x1421
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
            L_0x0f1a:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                android.view.View$OnClickListener r38 = r38.PositiveListener
                if (r38 == 0) goto L_0x166c
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                android.view.View$OnClickListener r39 = r39.PositiveListener
                r38.setOnClickListener(r39)
            L_0x0f3f:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                android.view.View$OnClickListener r38 = r38.NegativeListener
                if (r38 == 0) goto L_0x168c
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                android.view.View$OnClickListener r39 = r39.NegativeListener
                r38.setOnClickListener(r39)
            L_0x0f64:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                android.view.View$OnClickListener r38 = r38.NeutralListener
                if (r38 == 0) goto L_0x16ac
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                android.view.View$OnClickListener r39 = r39.NeutralListener
                r38.setOnClickListener(r39)
                goto L_0x0a4a
            L_0x0f8b:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.DEFAULT
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0faf
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x0faf:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLUE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0fd3
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -14042637(0xffffffffff29b9f3, float:-2.2560503E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x0fd3:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.RED
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0ff7
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -178389(0xfffffffffffd472b, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x0ff7:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLACK
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x101a
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -16777216(0xffffffffff000000, float:-1.7014118E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x101a:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.ORANGE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x103d
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -29417(0xffffffffffff8d17, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x103d:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.GREEN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1061
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -12269432(0xffffffffff44c888, float:-2.615699E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x1061:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BROWN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0e70
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -15228911(0xffffffffff17a011, float:-2.0154454E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x1085:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                int r38 = r38.PositiveId
                r39 = 0
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0e70
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                java.lang.CharSequence r38 = r38.PositiveText
                if (r38 == 0) goto L_0x0e70
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                java.lang.CharSequence r39 = r39.PositiveText
                r38.setText(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                if (r38 != 0) goto L_0x10dc
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x10dc:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.DEFAULT
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1100
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x1100:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLUE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1124
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -14042637(0xffffffffff29b9f3, float:-2.2560503E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x1124:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.RED
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1148
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -178389(0xfffffffffffd472b, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x1148:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLACK
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x116b
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -16777216(0xffffffffff000000, float:-1.7014118E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x116b:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.ORANGE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x118e
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -29417(0xffffffffffff8d17, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x118e:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.GREEN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x11b2
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -12269432(0xffffffffff44c888, float:-2.615699E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x11b2:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.PositiveColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BROWN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0e70
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                r39 = -15228911(0xffffffffff17a011, float:-2.0154454E38)
                r38.setTextColor(r39)
                goto L_0x0e70
            L_0x11d6:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.DEFAULT
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x11fa
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x11fa:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLUE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x121e
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -14042637(0xffffffffff29b9f3, float:-2.2560503E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x121e:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.RED
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1242
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -178389(0xfffffffffffd472b, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x1242:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLACK
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1265
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -16777216(0xffffffffff000000, float:-1.7014118E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x1265:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.ORANGE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1288
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -29417(0xffffffffffff8d17, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x1288:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.GREEN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x12ac
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -12269432(0xffffffffff44c888, float:-2.615699E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x12ac:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BROWN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0ec5
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -15228911(0xffffffffff17a011, float:-2.0154454E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x12d0:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                int r38 = r38.NegativeId
                r39 = 0
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0ec5
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                java.lang.CharSequence r38 = r38.NegativeText
                if (r38 == 0) goto L_0x0ec5
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                java.lang.CharSequence r39 = r39.NegativeText
                r38.setText(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                if (r38 != 0) goto L_0x1327
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x1327:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.DEFAULT
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x134b
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x134b:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLUE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x136f
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -14042637(0xffffffffff29b9f3, float:-2.2560503E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x136f:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.RED
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1393
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -178389(0xfffffffffffd472b, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x1393:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLACK
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x13b6
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -16777216(0xffffffffff000000, float:-1.7014118E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x13b6:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.ORANGE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x13d9
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -29417(0xffffffffffff8d17, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x13d9:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.GREEN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x13fd
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -12269432(0xffffffffff44c888, float:-2.615699E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x13fd:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NegativeColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BROWN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0ec5
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                r39 = -15228911(0xffffffffff17a011, float:-2.0154454E38)
                r38.setTextColor(r39)
                goto L_0x0ec5
            L_0x1421:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.DEFAULT
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1445
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x1445:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLUE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1469
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -14042637(0xffffffffff29b9f3, float:-2.2560503E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x1469:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.RED
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x148d
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -178389(0xfffffffffffd472b, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x148d:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLACK
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x14b0
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -16777216(0xffffffffff000000, float:-1.7014118E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x14b0:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.ORANGE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x14d3
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -29417(0xffffffffffff8d17, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x14d3:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.GREEN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x14f7
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -12269432(0xffffffffff44c888, float:-2.615699E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x14f7:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BROWN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0f1a
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -15228911(0xffffffffff17a011, float:-2.0154454E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x151b:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                int r38 = r38.NeutralId
                r39 = 0
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0f1a
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                java.lang.CharSequence r38 = r38.NeutralText
                if (r38 == 0) goto L_0x0f1a
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = r2
                r0 = r39
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r39 = r0
                java.lang.CharSequence r39 = r39.NeutralText
                r38.setText(r39)
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                if (r38 != 0) goto L_0x1572
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x1572:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.DEFAULT
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1596
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -13421773(0xffffffffff333333, float:-2.3819765E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x1596:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLUE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x15ba
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -14042637(0xffffffffff29b9f3, float:-2.2560503E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x15ba:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.RED
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x15de
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -178389(0xfffffffffffd472b, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x15de:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BLACK
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1601
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -16777216(0xffffffffff000000, float:-1.7014118E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x1601:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.ORANGE
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1624
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -29417(0xffffffffffff8d17, float:NaN)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x1624:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.GREEN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x1648
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -12269432(0xffffffffff44c888, float:-2.615699E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x1648:
                r38 = r2
                r0 = r38
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r38 = r0
                com.tencent.qq.widget.QQDialog$setColors r38 = r38.NeutralColor
                com.tencent.qq.widget.QQDialog$setColors r39 = com.tencent.qq.widget.QQDialog.setColors.BROWN
                r0 = r38
                r1 = r39
                if (r0 != r1) goto L_0x0f1a
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                r39 = -15228911(0xffffffffff17a011, float:-2.0154454E38)
                r38.setTextColor(r39)
                goto L_0x0f1a
            L_0x166c:
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton3
                r38 = r0
                com.tencent.qq.widget.QQDialog$OnDialogButtonClickListener r39 = new com.tencent.qq.widget.QQDialog$OnDialogButtonClickListener
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r0 = r41
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r41 = r0
                r40.<init>(r41)
                r38.setOnClickListener(r39)
                goto L_0x0f3f
            L_0x168c:
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton2
                r38 = r0
                com.tencent.qq.widget.QQDialog$OnDialogButtonClickListener r39 = new com.tencent.qq.widget.QQDialog$OnDialogButtonClickListener
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r0 = r41
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r41 = r0
                r40.<init>(r41)
                r38.setOnClickListener(r39)
                goto L_0x0f64
            L_0x16ac:
                r38 = r2
                r0 = r38
                android.widget.TextView r0 = r0.testButton1
                r38 = r0
                com.tencent.qq.widget.QQDialog$OnDialogButtonClickListener r39 = new com.tencent.qq.widget.QQDialog$OnDialogButtonClickListener
                r44 = r39
                r39 = r44
                r40 = r44
                r41 = r2
                r0 = r41
                com.tencent.qq.widget.QQDialog r0 = r0.this$0
                r41 = r0
                r40.<init>(r41)
                r38.setOnClickListener(r39)
                goto L_0x0a4a
            L_0x16cc:
                goto L_0x0a9f
            L_0x16ce:
                goto L_0x0a9f
            L_0x16d0:
                goto L_0x0a9f
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.qq.widget.QQDialog.Builder.viewButton():void");
        }
    }

    private class OnDialogButtonClickListener implements View.OnClickListener {
        private final QQDialog this$0;

        public OnDialogButtonClickListener(QQDialog qQDialog) {
            this.this$0 = qQDialog;
        }

        static QQDialog access$0(OnDialogButtonClickListener onDialogButtonClickListener) {
            return onDialogButtonClickListener.this$0;
        }

        @Override
        public void onClick(View view) {
            View view2 = view;
            this.this$0.mDialog.dismiss();
        }
    }

    public enum setColors {
        ;
        
        private static setColors[] $VALUES;
        public static final setColors BLACK = null;
        public static final setColors BLUE = null;
        public static final setColors BROWN = null;
        public static final setColors DEFAULT = null;
        public static final setColors GREEN = null;
        public static final setColors ORANGE = null;
        public static final setColors RED = null;
        private String name;

        static {
            setColors setcolors;
            setColors setcolors2;
            setColors setcolors3;
            setColors setcolors4;
            setColors setcolors5;
            setColors setcolors6;
            setColors setcolors7;
            new setColors("DEFAULT", 0, "#333333");
            DEFAULT = setcolors;
            new setColors("BLUE", 1, "#29B9F3");
            BLUE = setcolors2;
            new setColors("RED", 2, "#FD472B");
            RED = setcolors3;
            new setColors("BLACK", 3, "#000000");
            BLACK = setcolors4;
            new setColors("ORANGE", 4, "#FF8D17");
            ORANGE = setcolors5;
            new setColors("GREEN", 5, "#44C888");
            GREEN = setcolors6;
            new setColors("BROWN", 6, "#17A011");
            BROWN = setcolors7;
            setColors[] setcolorsArr = new setColors[7];
            setcolorsArr[0] = DEFAULT;
            setColors[] setcolorsArr2 = setcolorsArr;
            setcolorsArr2[1] = BLUE;
            setColors[] setcolorsArr3 = setcolorsArr2;
            setcolorsArr3[2] = RED;
            setColors[] setcolorsArr4 = setcolorsArr3;
            setcolorsArr4[3] = BLACK;
            setColors[] setcolorsArr5 = setcolorsArr4;
            setcolorsArr5[4] = ORANGE;
            setColors[] setcolorsArr6 = setcolorsArr5;
            setcolorsArr6[5] = GREEN;
            setColors[] setcolorsArr7 = setcolorsArr6;
            setcolorsArr7[6] = BROWN;
            $VALUES = setcolorsArr7;
        }

        setColors(String str) {
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

    public enum setLineColor {
        ;
        
        private static setLineColor[] $VALUES;
        public static final setLineColor BLUE = null;
        public static final setLineColor BROWN = null;
        public static final setLineColor GREEN = null;
        public static final setLineColor ORANGE = null;
        public static final setLineColor RED = null;
        public static final setLineColor WHITE = null;
        private String name;

        static {
            setLineColor setlinecolor;
            setLineColor setlinecolor2;
            setLineColor setlinecolor3;
            setLineColor setlinecolor4;
            setLineColor setlinecolor5;
            setLineColor setlinecolor6;
            new setLineColor("BLUE", 0, "#11B7F5");
            BLUE = setlinecolor;
            new setLineColor("RED", 1, "#FF8140");
            RED = setlinecolor2;
            new setLineColor("WHITE", 2, "#FFFFFF");
            WHITE = setlinecolor3;
            new setLineColor("ORANGE", 3, "#FF8D17");
            ORANGE = setlinecolor4;
            new setLineColor("GREEN", 4, "#31C27C");
            GREEN = setlinecolor5;
            new setLineColor("BROWN", 5, "#069900");
            BROWN = setlinecolor6;
            setLineColor[] setlinecolorArr = new setLineColor[6];
            setlinecolorArr[0] = BLUE;
            setLineColor[] setlinecolorArr2 = setlinecolorArr;
            setlinecolorArr2[1] = RED;
            setLineColor[] setlinecolorArr3 = setlinecolorArr2;
            setlinecolorArr3[2] = WHITE;
            setLineColor[] setlinecolorArr4 = setlinecolorArr3;
            setlinecolorArr4[3] = ORANGE;
            setLineColor[] setlinecolorArr5 = setlinecolorArr4;
            setlinecolorArr5[4] = GREEN;
            setLineColor[] setlinecolorArr6 = setlinecolorArr5;
            setlinecolorArr6[5] = BROWN;
            $VALUES = setlinecolorArr6;
        }

        setLineColor(String str) {
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

    public QQDialog(Context context) {
        this.mContext = context;
        this.defStyle = 16973826;
    }

    public QQDialog(Context context, int i) {
        int i2 = i;
        this.mContext = context;
        this.defStyle = i2;
        if (i2 == 0) {
            this.defStyle = 16973826;
            return;
        }
        this.defStyle = i2;
    }

    public static int dip2px(Context context, double d) {
        return (int) ((d * ((double) context.getResources().getDisplayMetrics().density)) + ((double) 0.5f));
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public QQDialog cancel() {
        this.mDialog.cancel();
        return this;
    }

    public QQDialog dismiss() {
        this.mDialog.dismiss();
        return this;
    }

    public String getEditText() {
        return this.Edits.getText().toString();
    }

    public QQDialog setCancelable(boolean z) {
        this.Cancelable = z;
        return this;
    }

    public QQDialog setCanceledOnTouchOutside(boolean z) {
        this.CanceledOnTouchOutside = z;
        return this;
    }

    public QQDialog setEditText(int i, int i2) {
        this.EditTextId = i;
        this.hintId = i2;
        this.isEditText = true;
        return this;
    }

    public QQDialog setEditText(int i, CharSequence charSequence) {
        this.EditTextId = i;
        this.hint = charSequence;
        this.isEditText = true;
        return this;
    }

    public QQDialog setEditText(CharSequence charSequence, int i) {
        this.setEditText = charSequence;
        this.hintId = i;
        this.isEditText = true;
        return this;
    }

    public QQDialog setEditText(CharSequence charSequence, CharSequence charSequence2) {
        this.setEditText = charSequence;
        this.hint = charSequence2;
        this.isEditText = true;
        return this;
    }

    public QQDialog setMessage(int i) {
        this.messageId = i;
        return this;
    }

    public QQDialog setMessage(int i, setColors setcolors) {
        this.messageId = i;
        this.MessageColor = setcolors;
        return this;
    }

    public QQDialog setMessage(CharSequence charSequence) {
        this.messageText = charSequence;
        return this;
    }

    public QQDialog setMessage(CharSequence charSequence, setColors setcolors) {
        this.messageText = charSequence;
        this.MessageColor = setcolors;
        return this;
    }

    public QQDialog setNegativeButton(int i, View.OnClickListener onClickListener) {
        this.NegativeId = i;
        this.NegativeListener = onClickListener;
        this.isNegativeButtonShow = true;
        return this;
    }

    public QQDialog setNegativeButton(int i, setColors setcolors, View.OnClickListener onClickListener) {
        this.NegativeId = i;
        this.NegativeColor = setcolors;
        this.NegativeListener = onClickListener;
        this.isNegativeButtonShow = true;
        return this;
    }

    public QQDialog setNegativeButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.NegativeText = charSequence;
        this.NegativeListener = onClickListener;
        this.isNegativeButtonShow = true;
        return this;
    }

    public QQDialog setNegativeButton(CharSequence charSequence, setColors setcolors, View.OnClickListener onClickListener) {
        this.NegativeText = charSequence;
        this.NegativeColor = setcolors;
        this.NegativeListener = onClickListener;
        this.isNegativeButtonShow = true;
        return this;
    }

    public QQDialog setNeutralButton(int i, View.OnClickListener onClickListener) {
        this.NeutralId = i;
        this.NeutralListener = onClickListener;
        this.isNeutralButtonShow = true;
        return this;
    }

    public QQDialog setNeutralButton(int i, setColors setcolors, View.OnClickListener onClickListener) {
        this.NeutralId = i;
        this.NeutralColor = setcolors;
        this.NeutralListener = onClickListener;
        this.isNeutralButtonShow = true;
        return this;
    }

    public QQDialog setNeutralButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.NeutralText = charSequence;
        this.NeutralListener = onClickListener;
        this.isNeutralButtonShow = true;
        return this;
    }

    public QQDialog setNeutralButton(CharSequence charSequence, setColors setcolors, View.OnClickListener onClickListener) {
        this.NeutralText = charSequence;
        this.NeutralColor = setcolors;
        this.NeutralListener = onClickListener;
        this.isNeutralButtonShow = true;
        return this;
    }

    public QQDialog setPositiveButton(int i, View.OnClickListener onClickListener) {
        this.PositiveId = i;
        this.PositiveListener = onClickListener;
        this.isPositiveButtonShow = true;
        return this;
    }

    public QQDialog setPositiveButton(int i, setColors setcolors, View.OnClickListener onClickListener) {
        this.PositiveId = i;
        this.PositiveColor = setcolors;
        this.PositiveListener = onClickListener;
        this.isPositiveButtonShow = true;
        return this;
    }

    public QQDialog setPositiveButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.PositiveText = charSequence;
        this.PositiveListener = onClickListener;
        this.isPositiveButtonShow = true;
        return this;
    }

    public QQDialog setPositiveButton(CharSequence charSequence, setColors setcolors, View.OnClickListener onClickListener) {
        this.PositiveText = charSequence;
        this.PositiveColor = setcolors;
        this.PositiveListener = onClickListener;
        this.isPositiveButtonShow = true;
        return this;
    }

    public QQDialog setTitle(int i) {
        this.titleId = i;
        return this;
    }

    public QQDialog setTitle(int i, setColors setcolors) {
        this.titleId = i;
        this.TitleColor = setcolors;
        return this;
    }

    public QQDialog setTitle(CharSequence charSequence) {
        this.titleText = charSequence;
        return this;
    }

    public QQDialog setTitle(CharSequence charSequence, setColors setcolors) {
        this.titleText = charSequence;
        this.TitleColor = setcolors;
        return this;
    }

    public QQDialog setView(int i) {
        this.mViewId = i;
        return this;
    }

    public QQDialog setView(View view) {
        this.mView = view;
        return this;
    }

    public void setViewLine(int i) {
        ShapeDrawable shapeDrawable2;
        Shape shape;
        RectF rectF;
        int i2 = i;
        float[] fArr = new float[8];
        float[] fArr2 = new float[8];
        for (int i3 = 0; i3 < 8; i3++) {
            int dip2px = dip2px(this.mContext, (double) 3);
            fArr[i3] = (float) (dip2px + 0);
            fArr2[i3] = (float) dip2px;
        }
        new RectF((float) 0, (float) 0, (float) 0, (float) 0);
        new RoundRectShape(fArr, rectF, fArr2);
        new ShapeDrawable(shape);
        this.shapeDrawable = shapeDrawable2;
        if (i2 == 0) {
            this.shapeDrawable.getPaint().setColor(-1);
        } else {
            this.shapeDrawable.getPaint().setColor(i2);
        }
    }

    public void setViewLine(setLineColor setlinecolor) {
        ShapeDrawable shapeDrawable2;
        Shape shape;
        RectF rectF;
        setLineColor setlinecolor2 = setlinecolor;
        float[] fArr = new float[8];
        float[] fArr2 = new float[8];
        for (int i = 0; i < 8; i++) {
            int dip2px = dip2px(this.mContext, (double) 3);
            fArr[i] = (float) (dip2px + 0);
            fArr2[i] = (float) dip2px;
        }
        new RectF((float) 0, (float) 0, (float) 0, (float) 0);
        new RoundRectShape(fArr, rectF, fArr2);
        new ShapeDrawable(shape);
        this.shapeDrawable = shapeDrawable2;
        this.shapeDrawable.getPaint().setColor(Color.parseColor(setlinecolor2.getName()));
    }

    public QQDialog show() {
        Builder builder;
        new Builder(this);
        this.mBuilder = builder;
        if (!this.mDialog.isShowing()) {
            this.mDialog.show();
        }
        return this;
    }
}
