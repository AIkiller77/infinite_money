package com.androlua;

import android.os.Handler;
import android.os.Message;

public class Ticker {
    /* access modifiers changed from: private */
    public Handler a;
    /* access modifiers changed from: private */
    public OnTickListener b;
    private Thread c;
    /* access modifiers changed from: private */
    public long d = 1000;
    /* access modifiers changed from: private */
    public boolean e = true;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public long g;
    /* access modifiers changed from: private */
    public long h;

    public interface OnTickListener {
        void onTick();
    }

    public Ticker() {
        a();
    }

    private void a() {
        this.a = new Handler() {
            public void handleMessage(Message message) {
                if (Ticker.this.b != null) {
                    Ticker.this.b.onTick();
                }
            }
        };
        this.c = new Thread() {
            public void run() {
                boolean unused = Ticker.this.f = true;
                while (Ticker.this.f) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (!Ticker.this.e) {
                        long unused2 = Ticker.this.g = currentTimeMillis - Ticker.this.h;
                    }
                    if (currentTimeMillis - Ticker.this.g >= Ticker.this.d) {
                        long unused3 = Ticker.this.g = currentTimeMillis;
                        Ticker.this.a.sendEmptyMessage(0);
                    }
                    try {
                        sleep(1);
                    } catch (InterruptedException unused4) {
                    }
                }
            }
        };
    }

    public boolean getEnabled() {
        return this.e;
    }

    public long getInterval() {
        return this.d;
    }

    public long getPeriod() {
        return this.d;
    }

    public boolean isRun() {
        return this.f;
    }

    public void setEnabled(boolean z) {
        this.e = z;
        if (!z) {
            this.h = System.currentTimeMillis() - this.g;
        }
    }

    public void setInterval(long j) {
        this.g = System.currentTimeMillis();
        this.d = j;
    }

    public void setOnTickListener(OnTickListener onTickListener) {
        this.b = onTickListener;
    }

    public void setPeriod(long j) {
        this.g = System.currentTimeMillis();
        this.d = j;
    }

    public void start() {
        this.c.start();
    }

    public void stop() {
        this.f = false;
    }
}
