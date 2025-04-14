package com.tencent.qq.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MenuDialog {
    private Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    private LinearLayout lLayout_content;
    private TextView lines;
    private ScrollView sLayout_content;
    private List<SheetItem> sheetItemList;
    private boolean showTitle = false;
    /* access modifiers changed from: private */
    public TextView txt_cancel;
    private TextView txt_title;

    public interface OnSheetItemClickListener {
        void onClick(int i);
    }

    public class SheetItem {
        setTextColor color;
        OnSheetItemClickListener itemClickListener;
        String name;
        private final MenuDialog this$0;

        public SheetItem(MenuDialog menuDialog, String str, setTextColor settextcolor, OnSheetItemClickListener onSheetItemClickListener) {
            this.this$0 = menuDialog;
            this.name = str;
            this.color = settextcolor;
            this.itemClickListener = onSheetItemClickListener;
        }

        static MenuDialog access$0(SheetItem sheetItem) {
            return sheetItem.this$0;
        }
    }

    public enum setTextColor {
        ;
        
        private static setTextColor[] $VALUES;
        public static final setTextColor BLACK = null;
        public static final setTextColor BLUE = null;
        public static final setTextColor DEFAULT = null;
        public static final setTextColor ORANGE = null;
        public static final setTextColor RED = null;
        private String name;

        static {
            setTextColor settextcolor;
            setTextColor settextcolor2;
            setTextColor settextcolor3;
            setTextColor settextcolor4;
            setTextColor settextcolor5;
            new setTextColor("DEFAULT", 0, "#8F8F8F");
            DEFAULT = settextcolor;
            new setTextColor("BLUE", 1, "#268DFF");
            BLUE = settextcolor2;
            new setTextColor("RED", 2, "#FD472B");
            RED = settextcolor3;
            new setTextColor("BLACK", 3, "#000000");
            BLACK = settextcolor4;
            new setTextColor("ORANGE", 4, "#FF8D17");
            ORANGE = settextcolor5;
            setTextColor[] settextcolorArr = new setTextColor[5];
            settextcolorArr[0] = DEFAULT;
            setTextColor[] settextcolorArr2 = settextcolorArr;
            settextcolorArr2[1] = BLUE;
            setTextColor[] settextcolorArr3 = settextcolorArr2;
            settextcolorArr3[2] = RED;
            setTextColor[] settextcolorArr4 = settextcolorArr3;
            settextcolorArr4[3] = BLACK;
            setTextColor[] settextcolorArr5 = settextcolorArr4;
            settextcolorArr5[4] = ORANGE;
            $VALUES = settextcolorArr5;
        }

        setTextColor(String str) {
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

    public MenuDialog(Context context2) {
        LinearLayout linearLayout;
        ViewGroup.LayoutParams layoutParams;
        TextView textView;
        ViewGroup.LayoutParams layoutParams2;
        TextView textView2;
        ViewGroup.LayoutParams layoutParams3;
        ScrollView scrollView;
        ViewGroup.LayoutParams layoutParams4;
        LinearLayout linearLayout2;
        ViewGroup.LayoutParams layoutParams5;
        TextView textView3;
        ViewGroup.LayoutParams layoutParams6;
        TextView textView4;
        ViewGroup.LayoutParams layoutParams7;
        View.OnClickListener onClickListener;
        View.OnTouchListener onTouchListener;
        Dialog dialog2;
        ViewGroup.LayoutParams layoutParams8;
        Context context3 = context2;
        this.context = context3;
        this.display = ((WindowManager) context3.getSystemService("window")).getDefaultDisplay();
        new LinearLayout(context3);
        LinearLayout linearLayout3 = linearLayout;
        linearLayout3.setOrientation(1);
        new LinearLayout.LayoutParams(-1, -2);
        linearLayout3.setLayoutParams(layoutParams);
        int dip2px = dip2px(context3, (double) 44);
        new TextView(context3);
        TextView textView5 = textView;
        new LinearLayout.LayoutParams(-1, dip2px);
        linearLayout3.setMinimumWidth(this.display.getWidth());
        linearLayout3.setId(0);
        textView5.setLayoutParams(layoutParams2);
        textView5.setBackgroundColor(-1);
        textView5.setGravity(17);
        textView5.setTextColor(-7368817);
        textView5.setTextSize((float) 12);
        textView5.setVisibility(8);
        textView5.setId(1);
        linearLayout3.addView(textView5);
        int dip2px2 = dip2px(context3, (double) 0);
        new TextView(context3);
        TextView textView6 = textView2;
        new LinearLayout.LayoutParams(-1, dip2px2);
        textView6.setLayoutParams(layoutParams3);
        textView6.setBackgroundColor(-1711132);
        linearLayout3.addView(textView6);
        new ScrollView(context3);
        ScrollView scrollView2 = scrollView;
        new LinearLayout.LayoutParams(-1, -2);
        scrollView2.setLayoutParams(layoutParams4);
        scrollView2.setFillViewport(false);
        scrollView2.setFadingEdgeLength(0);
        scrollView2.setOverScrollMode(2);
        scrollView2.setId(3);
        linearLayout3.addView(scrollView2);
        new LinearLayout(context3);
        LinearLayout linearLayout4 = linearLayout2;
        linearLayout4.setOrientation(1);
        new LinearLayout.LayoutParams(-1, -2);
        linearLayout4.setLayoutParams(layoutParams5);
        linearLayout4.setId(4);
        scrollView2.addView(linearLayout4);
        new TextView(context3);
        TextView textView7 = textView3;
        new LinearLayout.LayoutParams(-1, dip2px(context3, (double) 8));
        textView7.setLayoutParams(layoutParams6);
        textView7.setBackgroundColor(-1711132);
        textView7.setVisibility(8);
        textView7.setId(5);
        linearLayout3.addView(textView7);
        new TextView(context3);
        TextView textView8 = textView4;
        new LinearLayout.LayoutParams(-1, dip2px);
        textView8.setLayoutParams(layoutParams7);
        textView8.setBackgroundColor(-1);
        textView8.setGravity(17);
        textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView8.setVisibility(8);
        textView8.setTextSize((float) 16);
        textView8.setId(6);
        linearLayout3.addView(textView8);
        this.sLayout_content = (ScrollView) linearLayout3.findViewById(3);
        this.lLayout_content = (LinearLayout) linearLayout3.findViewById(4);
        this.txt_title = (TextView) linearLayout3.findViewById(1);
        this.lines = (TextView) linearLayout3.findViewById(5);
        this.txt_cancel = (TextView) linearLayout3.findViewById(6);
        new View.OnClickListener(this) {
            private final MenuDialog this$0;

            {
                this.this$0 = r6;
            }

            static MenuDialog access$0(AnonymousClass100000002 r4) {
                return r4.this$0;
            }

            @Override
            public void onClick(View view) {
                View view2 = view;
                this.this$0.dialog.dismiss();
            }
        };
        this.txt_cancel.setOnClickListener(onClickListener);
        new View.OnTouchListener(this) {
            private final MenuDialog this$0;

            {
                this.this$0 = r6;
            }

            static MenuDialog access$0(AnonymousClass100000003 r4) {
                return r4.this$0;
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                View view2 = view;
                MotionEvent motionEvent2 = motionEvent;
                if (motionEvent2.getAction() == 0) {
                    this.this$0.txt_cancel.setBackgroundColor(-1710361);
                } else if (motionEvent2.getAction() == 1) {
                    this.this$0.txt_cancel.setBackgroundColor(-1);
                }
                return false;
            }
        };
        this.txt_cancel.setOnTouchListener(onTouchListener);
        new Dialog(context3);
        this.dialog = dialog2;
        new LinearLayout.LayoutParams(-1, -2);
        this.dialog.setContentView(linearLayout3, layoutParams8);
        Window window = this.dialog.getWindow();
        window.setWindowAnimations(16973910);
        window.setGravity(83);
        WindowManager.LayoutParams attributes = window.getAttributes();
        this.dialog.getWindow().setBackgroundDrawableResource(17170445);
        attributes.x = 0;
        attributes.y = -1;
        window.setAttributes(attributes);
    }

    public MenuDialog(Context context2, int i) {
        LinearLayout linearLayout;
        ViewGroup.LayoutParams layoutParams;
        TextView textView;
        ViewGroup.LayoutParams layoutParams2;
        TextView textView2;
        ViewGroup.LayoutParams layoutParams3;
        ScrollView scrollView;
        ViewGroup.LayoutParams layoutParams4;
        LinearLayout linearLayout2;
        ViewGroup.LayoutParams layoutParams5;
        TextView textView3;
        ViewGroup.LayoutParams layoutParams6;
        TextView textView4;
        ViewGroup.LayoutParams layoutParams7;
        View.OnClickListener onClickListener;
        View.OnTouchListener onTouchListener;
        Dialog dialog2;
        ViewGroup.LayoutParams layoutParams8;
        Context context3 = context2;
        int i2 = i;
        this.context = context3;
        this.display = ((WindowManager) context3.getSystemService("window")).getDefaultDisplay();
        new LinearLayout(context3);
        LinearLayout linearLayout3 = linearLayout;
        linearLayout3.setOrientation(1);
        new LinearLayout.LayoutParams(-1, -2);
        linearLayout3.setLayoutParams(layoutParams);
        int dip2px = dip2px(context3, (double) 44);
        new TextView(context3);
        TextView textView5 = textView;
        new LinearLayout.LayoutParams(-1, dip2px);
        linearLayout3.setMinimumWidth(this.display.getWidth());
        linearLayout3.setId(0);
        textView5.setLayoutParams(layoutParams2);
        textView5.setBackgroundColor(-1);
        textView5.setGravity(17);
        textView5.setTextColor(-7368817);
        textView5.setTextSize((float) 12);
        textView5.setVisibility(8);
        textView5.setId(1);
        linearLayout3.addView(textView5);
        int dip2px2 = dip2px(context3, (double) 0);
        new TextView(context3);
        TextView textView6 = textView2;
        new LinearLayout.LayoutParams(-1, dip2px2);
        textView6.setLayoutParams(layoutParams3);
        textView6.setBackgroundColor(-1711132);
        linearLayout3.addView(textView6);
        new ScrollView(context3);
        ScrollView scrollView2 = scrollView;
        new LinearLayout.LayoutParams(-1, -2);
        scrollView2.setLayoutParams(layoutParams4);
        scrollView2.setFillViewport(false);
        scrollView2.setFadingEdgeLength(0);
        scrollView2.setOverScrollMode(2);
        scrollView2.setId(3);
        linearLayout3.addView(scrollView2);
        new LinearLayout(context3);
        LinearLayout linearLayout4 = linearLayout2;
        linearLayout4.setOrientation(1);
        new LinearLayout.LayoutParams(-1, -2);
        linearLayout4.setLayoutParams(layoutParams5);
        linearLayout4.setId(4);
        scrollView2.addView(linearLayout4);
        new TextView(context3);
        TextView textView7 = textView3;
        new LinearLayout.LayoutParams(-1, dip2px(context3, (double) 8));
        textView7.setLayoutParams(layoutParams6);
        textView7.setBackgroundColor(-1711132);
        textView7.setVisibility(8);
        textView7.setId(5);
        linearLayout3.addView(textView7);
        new TextView(context3);
        TextView textView8 = textView4;
        new LinearLayout.LayoutParams(-1, dip2px);
        textView8.setLayoutParams(layoutParams7);
        textView8.setBackgroundColor(-1);
        textView8.setGravity(17);
        textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView8.setVisibility(8);
        textView8.setTextSize((float) 16);
        textView8.setId(6);
        linearLayout3.addView(textView8);
        this.sLayout_content = (ScrollView) linearLayout3.findViewById(3);
        this.lLayout_content = (LinearLayout) linearLayout3.findViewById(4);
        this.txt_title = (TextView) linearLayout3.findViewById(1);
        this.lines = (TextView) linearLayout3.findViewById(5);
        this.txt_cancel = (TextView) linearLayout3.findViewById(6);
        new View.OnClickListener(this) {
            private final MenuDialog this$0;

            {
                this.this$0 = r6;
            }

            static MenuDialog access$0(AnonymousClass100000000 r4) {
                return r4.this$0;
            }

            @Override
            public void onClick(View view) {
                View view2 = view;
                this.this$0.dialog.dismiss();
            }
        };
        this.txt_cancel.setOnClickListener(onClickListener);
        new View.OnTouchListener(this) {
            private final MenuDialog this$0;

            {
                this.this$0 = r6;
            }

            static MenuDialog access$0(AnonymousClass100000001 r4) {
                return r4.this$0;
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                View view2 = view;
                MotionEvent motionEvent2 = motionEvent;
                if (motionEvent2.getAction() == 0) {
                    this.this$0.txt_cancel.setBackgroundColor(-1710361);
                } else if (motionEvent2.getAction() == 1) {
                    this.this$0.txt_cancel.setBackgroundColor(-1);
                }
                return false;
            }
        };
        this.txt_cancel.setOnTouchListener(onTouchListener);
        new Dialog(context3);
        this.dialog = dialog2;
        new LinearLayout.LayoutParams(-1, -2);
        this.dialog.setContentView(linearLayout3, layoutParams8);
        Window window = this.dialog.getWindow();
        window.setWindowAnimations(i2);
        if (i2 == 0) {
            window.setWindowAnimations(16973910);
        } else {
            window.setWindowAnimations(i2);
        }
        window.setGravity(83);
        WindowManager.LayoutParams attributes = window.getAttributes();
        this.dialog.getWindow().setBackgroundDrawableResource(17170445);
        attributes.x = 0;
        attributes.y = -1;
        window.setAttributes(attributes);
    }

    public static int dip2px(Context context2, double d) {
        return (int) ((d * ((double) context2.getResources().getDisplayMetrics().density)) + ((double) 0.5f));
    }

    public static int px2dip(Context context2, float f) {
        return (int) ((f / context2.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void setSheetItems() {
        LinearLayout linearLayout;
        ViewGroup.LayoutParams layoutParams;
        TextView textView;
        ViewGroup.LayoutParams layoutParams2;
        TextView textView2;
        ViewGroup.LayoutParams layoutParams3;
        View.OnClickListener onClickListener;
        View.OnTouchListener onTouchListener;
        if (this.sheetItemList != null) {
            if (this.sheetItemList.size() > 0) {
                int size = this.sheetItemList.size();
                if (size >= 8) {
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.sLayout_content.getLayoutParams();
                    layoutParams4.height = this.display.getHeight() / 2;
                    this.sLayout_content.setLayoutParams(layoutParams4);
                }
                for (int i = 1; i <= size; i++) {
                    int i2 = i;
                    SheetItem sheetItem = this.sheetItemList.get(i - 1);
                    String str = sheetItem.name;
                    setTextColor settextcolor = sheetItem.color;
                    OnSheetItemClickListener onSheetItemClickListener = sheetItem.itemClickListener;
                    new LinearLayout(this.context);
                    LinearLayout linearLayout2 = linearLayout;
                    linearLayout2.setOrientation(1);
                    new LinearLayout.LayoutParams(-1, dip2px(this.context, (double) 44));
                    linearLayout2.setLayoutParams(layoutParams);
                    int dip2px = dip2px(this.context, 0.3d);
                    new TextView(this.context);
                    TextView textView3 = textView;
                    new LinearLayout.LayoutParams(-1, dip2px);
                    textView3.setLayoutParams(layoutParams2);
                    textView3.setBackgroundColor(-1711132);
                    linearLayout2.addView(textView3);
                    new TextView(this.context);
                    TextView textView4 = textView2;
                    textView4.setText(str);
                    textView4.setTextSize((float) 16);
                    textView4.setGravity(17);
                    textView4.setBackgroundColor(-1);
                    linearLayout2.addView(textView4);
                    if (settextcolor == null) {
                        textView4.setTextColor(Color.parseColor(setTextColor.BLACK.getName()));
                    } else {
                        textView4.setTextColor(Color.parseColor(settextcolor.getName()));
                    }
                    new LinearLayout.LayoutParams(-1, (int) ((((float) 45) * this.context.getResources().getDisplayMetrics().density) + 0.5f));
                    textView4.setLayoutParams(layoutParams3);
                    new View.OnClickListener(this, onSheetItemClickListener, i2) {
                        private final MenuDialog this$0;
                        private final int val$index;
                        private final OnSheetItemClickListener val$listener;

                        {
                            this.this$0 = r8;
                            this.val$listener = r9;
                            this.val$index = r10;
                        }

                        static MenuDialog access$0(AnonymousClass100000004 r4) {
                            return r4.this$0;
                        }

                        @Override
                        public void onClick(View view) {
                            View view2 = view;
                            this.val$listener.onClick(this.val$index);
                            this.this$0.dialog.dismiss();
                        }
                    };
                    textView4.setOnClickListener(onClickListener);
                    new View.OnTouchListener(this, textView4) {
                        private final MenuDialog this$0;
                        private final TextView val$textView;

                        {
                            this.this$0 = r7;
                            this.val$textView = r8;
                        }

                        static MenuDialog access$0(AnonymousClass100000005 r4) {
                            return r4.this$0;
                        }

                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            View view2 = view;
                            MotionEvent motionEvent2 = motionEvent;
                            if (motionEvent2.getAction() == 0) {
                                this.val$textView.setBackgroundColor(-1710361);
                            } else if (motionEvent2.getAction() == 1) {
                                this.val$textView.setBackgroundColor(-1);
                            }
                            return false;
                        }
                    };
                    textView4.setOnTouchListener(onTouchListener);
                    this.lLayout_content.addView(linearLayout2);
                }
            }
        }
    }

    public MenuDialog addItem(String str, setTextColor settextcolor, OnSheetItemClickListener onSheetItemClickListener) {
        Object obj;
        List<SheetItem> list;
        String str2 = str;
        setTextColor settextcolor2 = settextcolor;
        OnSheetItemClickListener onSheetItemClickListener2 = onSheetItemClickListener;
        if (this.sheetItemList == null) {
            new ArrayList();
            this.sheetItemList = list;
        }
        new SheetItem(this, str2, settextcolor2, onSheetItemClickListener2);
        boolean add = this.sheetItemList.add(obj);
        return this;
    }

    public MenuDialog setButton(String str) {
        String str2 = str;
        this.lines.setVisibility(0);
        this.txt_cancel.setVisibility(0);
        if (str2 == null) {
            this.txt_cancel.setText("取消");
        } else {
            this.txt_cancel.setText(str2);
        }
        this.txt_cancel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        return this;
    }

    public MenuDialog setButton(String str, int i) {
        String str2 = str;
        int i2 = i;
        this.lines.setVisibility(0);
        this.txt_cancel.setVisibility(0);
        if (str2 == null) {
            this.txt_cancel.setText("取消");
        } else {
            this.txt_cancel.setText(str2);
        }
        if (i2 == 0) {
            this.txt_cancel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.txt_cancel.setTextColor(i2);
        }
        return this;
    }

    public MenuDialog setButton(String str, setTextColor settextcolor) {
        String str2 = str;
        setTextColor settextcolor2 = settextcolor;
        this.lines.setVisibility(0);
        this.txt_cancel.setVisibility(0);
        if (str2 == null) {
            this.txt_cancel.setText("取消");
        } else {
            this.txt_cancel.setText(str2);
        }
        if (settextcolor2 == null) {
            this.txt_cancel.setTextColor(Color.parseColor(setTextColor.BLACK.getName()));
        } else {
            this.txt_cancel.setTextColor(Color.parseColor(settextcolor2.getName()));
        }
        return this;
    }

    public MenuDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public MenuDialog setCanceledOnTouchOutside(boolean z) {
        this.dialog.setCanceledOnTouchOutside(z);
        return this;
    }

    public MenuDialog setTitle(String str) {
        String str2 = str;
        this.showTitle = true;
        this.txt_title.setVisibility(0);
        if (str2 == null || str2 == "") {
            this.txt_title.setVisibility(8);
        } else {
            this.txt_title.setText(str2);
        }
        this.txt_title.setTextColor(-7368817);
        return this;
    }

    public MenuDialog setTitle(String str, int i) {
        String str2 = str;
        int i2 = i;
        this.showTitle = true;
        this.txt_title.setVisibility(0);
        if (str2 == null || str2 == "") {
            this.txt_title.setVisibility(8);
        } else {
            this.txt_title.setText(str2);
        }
        if (i2 == 0) {
            this.txt_title.setTextColor(-7368817);
        } else {
            this.txt_title.setTextColor(i2);
        }
        return this;
    }

    public MenuDialog setTitle(String str, setTextColor settextcolor) {
        String str2 = str;
        setTextColor settextcolor2 = settextcolor;
        this.showTitle = true;
        this.txt_title.setVisibility(0);
        if (str2 == null || str2 == "") {
            this.txt_title.setVisibility(8);
        } else {
            this.txt_title.setText(str2);
        }
        if (settextcolor2 == null) {
            this.txt_title.setTextColor(-7368817);
        } else {
            this.txt_title.setTextColor(Color.parseColor(settextcolor2.getName()));
        }
        return this;
    }

    public MenuDialog setView(int i) {
        int i2 = i;
        if (i2 != 0) {
            this.txt_title.setVisibility(8);
            this.lLayout_content.addView((LinearLayout) LayoutInflater.from(this.context).inflate(i2, (ViewGroup) null));
        }
        return this;
    }

    public void show() {
        setSheetItems();
        this.dialog.show();
    }
}
