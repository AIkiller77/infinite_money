package com.tencent.open.c;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;

/* compiled from: ProGuard */
public class a extends RelativeLayout {
    private static final String a = a.class.getName();
    private Rect b = null;
    private boolean c = false;
    private C0009a d = null;

    /* renamed from: com.tencent.open.c.a$a  reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public interface C0009a {
        void a();

        void a(int i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(Context context) {
        super(context);
        Rect rect;
        if (this.b == null) {
            new Rect();
            this.b = rect;
        }
    }

    public void a(C0009a aVar) {
        C0009a aVar2 = aVar;
        this.d = aVar2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        int size = View.MeasureSpec.getSize(i4);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.b);
        int height = (activity.getWindowManager().getDefaultDisplay().getHeight() - this.b.top) - size;
        if (!(this.d == null || size == 0)) {
            if (height > 100) {
                this.d.a((Math.abs(this.b.height()) - getPaddingBottom()) - getPaddingTop());
            } else {
                this.d.a();
            }
        }
        super.onMeasure(i3, i4);
    }
}
