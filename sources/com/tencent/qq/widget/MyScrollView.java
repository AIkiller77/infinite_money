package com.tencent.qq.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private Context mContext;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MyScrollView(android.content.Context r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            r3.init(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.qq.widget.MyScrollView.<init>(android.content.Context):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MyScrollView(android.content.Context r8, android.util.AttributeSet r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r0
            r5 = r1
            r4.init(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.qq.widget.MyScrollView.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MyScrollView(android.content.Context r10, android.util.AttributeSet r11, int r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = r1
            r5.init(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.qq.widget.MyScrollView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private void init(Context context) {
        Context context2 = context;
        this.mContext = context2;
    }

    /* access modifiers changed from: protected */
    @Override
    public void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics;
        int i3 = i;
        int i4 = i2;
        try {
            Display defaultDisplay = ((Activity) this.mContext).getWindowManager().getDefaultDisplay();
            new DisplayMetrics();
            DisplayMetrics displayMetrics2 = displayMetrics;
            defaultDisplay.getMetrics(displayMetrics2);
            i4 = View.MeasureSpec.makeMeasureSpec(displayMetrics2.heightPixels / 4, ExploreByTouchHelper.INVALID_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMeasure(i3, i4);
    }
}
