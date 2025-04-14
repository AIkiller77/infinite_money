package com.lua.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.IPhotoView;
import android.widget.TextView;

public class MarTextView extends TextView implements Runnable {
    private int currentScrollX = 0;
    private boolean isMeasure = false;
    private boolean isStop = false;
    private long stime = ((long) 0);
    private int textWidth;

    public MarTextView(Context context) {
        super(context);
    }

    public MarTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MarTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void getTextWidth() {
        this.textWidth = (int) getPaint().measureText(getText().toString());
    }

    /* access modifiers changed from: protected */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.isMeasure) {
            getTextWidth();
            this.isMeasure = true;
        }
    }

    public void restartScroll() {
        this.currentScrollX = 0;
        startScroll();
    }

    @Override
    public void run() {
        if (this.textWidth == 0 || getWidth() == 0) {
            postDelayed(this, (long) IPhotoView.DEFAULT_ZOOM_DURATION);
        } else if (getWidth() < this.textWidth && !this.isStop) {
            if (this.stime == ((long) 0)) {
                this.stime = System.currentTimeMillis();
            }
            this.currentScrollX = (int) ((((double) (System.currentTimeMillis() - this.stime)) * 2.0d) / ((double) 20));
            scrollTo(this.currentScrollX, 0);
            if (getScrollX() >= this.textWidth) {
                this.currentScrollX = 0 - getWidth();
                this.stime = System.currentTimeMillis() - ((long) (this.currentScrollX * 10));
                scrollTo(this.currentScrollX, 0);
            }
            postDelayed(this, (long) 20);
        }
    }

    @Override
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        startScroll();
    }

    public void startScroll() {
        this.isStop = false;
        removeCallbacks(this);
        post(this);
    }

    public void stopScroll() {
        this.isStop = true;
    }
}
