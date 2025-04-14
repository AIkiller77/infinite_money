package com.androlua.util;

public abstract class TimerTaskX implements Runnable {
    private long a;
    final Object b = new Object();
    boolean c;
    long d;
    long e;
    boolean f;
    private boolean g;

    protected TimerTaskX() {
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        boolean z;
        synchronized (this.b) {
            if (this.d <= 0) {
                if (this.a <= 0) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public boolean cancel() {
        boolean z;
        synchronized (this.b) {
            z = !this.c && this.d > 0;
            this.c = true;
        }
        return z;
    }

    public long getPeriod() {
        return this.e;
    }

    public boolean isEnabled() {
        return this.g;
    }

    public abstract void run();

    public long scheduledExecutionTime() {
        long j;
        synchronized (this.b) {
            j = this.a;
        }
        return j;
    }

    public void setEnabled(boolean z) {
        this.g = z;
    }

    public void setPeriod(long j) {
        this.e = j;
    }

    public void setScheduledTime(long j) {
        synchronized (this.b) {
            this.a = j;
        }
    }
}
