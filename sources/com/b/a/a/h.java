package com.b.a.a;

import android.view.MotionEvent;

public class h extends g {
    private static int f = 16;
    private int e;
    private float g = 0.0f;
    private float h = 0.0f;

    public h(c cVar) {
        super(cVar);
        f = this.c.a() * 2;
    }

    private void a(float f2, float f3) {
        if ((this.g < 0.0f && f2 > 0.0f) || (this.g > 0.0f && f2 < 0.0f)) {
            this.g = 0.0f;
        }
        if ((this.h < 0.0f && f3 > 0.0f) || (this.h > 0.0f && f3 < 0.0f)) {
            this.h = 0.0f;
        }
        double atan2 = Math.atan2((double) Math.abs(f2), (double) Math.abs(f3));
        if (atan2 >= 0.322d) {
            float f4 = this.g + f2;
            int i = ((int) f4) / f;
            this.g = f4 - ((float) (f * i));
            while (i > 0) {
                this.c.moveCaretRight();
                i--;
                if (this.e == 0) {
                    this.e = 1;
                }
            }
            while (i < 0) {
                this.c.moveCaretLeft();
                i++;
                if (this.e == 0) {
                    this.e = 1;
                }
            }
        }
        if (1.5707963267948966d - atan2 >= 0.322d) {
            float f5 = this.h + f3;
            int i2 = ((int) f5) / f;
            this.h = f5 - ((float) (f * i2));
            for (int i3 = i2; i3 > 0; i3--) {
                this.c.moveCaretDown();
                if (this.e == 0) {
                    this.e = -1;
                }
            }
            while (i2 < 0) {
                this.c.moveCaretUp();
                if (this.e == 0) {
                    this.e = -1;
                }
                i2++;
            }
        }
    }

    public boolean a(MotionEvent motionEvent) {
        this.g = 0.0f;
        this.h = 0.0f;
        this.e = 0;
        super.a(motionEvent);
        return true;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.c.setSelected(!this.c.isSelectText());
        this.c.setSelectionRange(this.c.getCaretPosition(), 0);
        return true;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.e = 0;
        f = this.c.a() * 2;
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        a(motionEvent2);
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.c.setSelected(!this.c.isSelectText());
        this.c.setSelectionRange(this.c.getCaretPosition(), 0);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (this.e == 1) {
            f3 = 0.0f;
        } else if (this.e == -1) {
            f2 = 0.0f;
        }
        a(-f2, -f3);
        if ((motionEvent2.getAction() & 255) == 1) {
            a(motionEvent2);
        }
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return super.onSingleTapConfirmed(motionEvent);
    }
}
