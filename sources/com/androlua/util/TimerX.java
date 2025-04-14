package com.androlua.util;

import java.util.Date;

public class TimerX {
    private static long a;
    private final TimerImpl b;
    private final FinalizerHelper c;

    private static final class FinalizerHelper {
        private final TimerImpl a;

        FinalizerHelper(TimerImpl timerImpl) {
            this.a = timerImpl;
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            try {
                synchronized (this.a) {
                    boolean unused = this.a.b = true;
                    this.a.notify();
                }
                super.finalize();
            } catch (Throwable th) {
                super.finalize();
                throw th;
            }
        }
    }

    private static final class TimerImpl extends Thread {
        /* access modifiers changed from: private */
        public boolean a;
        /* access modifiers changed from: private */
        public boolean b;
        private TimerHeap c = new TimerHeap();

        private static final class TimerHeap {
            private int a;
            private TimerTaskX[] b;
            private int c;
            /* access modifiers changed from: private */
            public int d;

            private TimerHeap() {
                this.a = 256;
                this.b = new TimerTaskX[this.a];
                this.c = 0;
                this.d = 0;
            }

            /* access modifiers changed from: private */
            public int a(TimerTaskX timerTaskX) {
                for (int i = 0; i < this.b.length; i++) {
                    if (this.b[i] == timerTaskX) {
                        return i;
                    }
                }
                return -1;
            }

            private void a() {
                int i = this.c - 1;
                int i2 = (i - 1) / 2;
                while (this.b[i].d < this.b[i2].d) {
                    TimerTaskX timerTaskX = this.b[i];
                    this.b[i] = this.b[i2];
                    this.b[i2] = timerTaskX;
                    int i3 = i2;
                    i2 = (i2 - 1) / 2;
                    i = i3;
                }
            }

            private void a(int i) {
                int i2 = (i * 2) + 1;
                while (i2 < this.c && this.c > 0) {
                    int i3 = i2 + 1;
                    if (i3 < this.c && this.b[i3].d < this.b[i2].d) {
                        i2 = i3;
                    }
                    if (this.b[i].d >= this.b[i2].d) {
                        TimerTaskX timerTaskX = this.b[i];
                        this.b[i] = this.b[i2];
                        this.b[i2] = timerTaskX;
                        int i4 = i2;
                        i2 = (i2 * 2) + 1;
                        i = i4;
                    } else {
                        return;
                    }
                }
            }

            public void adjustMinimum() {
                a(0);
            }

            public void delete(int i) {
                if (i >= 0 && i < this.c) {
                    TimerTaskX[] timerTaskXArr = this.b;
                    TimerTaskX[] timerTaskXArr2 = this.b;
                    int i2 = this.c - 1;
                    this.c = i2;
                    timerTaskXArr[i] = timerTaskXArr2[i2];
                    this.b[this.c] = null;
                    a(i);
                }
            }

            public void deleteIfCancelled() {
                int i = 0;
                while (i < this.c) {
                    if (this.b[i].c) {
                        this.d++;
                        delete(i);
                        i--;
                    }
                    i++;
                }
            }

            public void insert(TimerTaskX timerTaskX) {
                if (this.b.length == this.c) {
                    TimerTaskX[] timerTaskXArr = new TimerTaskX[(this.c * 2)];
                    System.arraycopy(this.b, 0, timerTaskXArr, 0, this.c);
                    this.b = timerTaskXArr;
                }
                TimerTaskX[] timerTaskXArr2 = this.b;
                int i = this.c;
                this.c = i + 1;
                timerTaskXArr2[i] = timerTaskX;
                a();
            }

            public boolean isEmpty() {
                return this.c == 0;
            }

            public TimerTaskX minimum() {
                return this.b[0];
            }

            public void reset() {
                this.b = new TimerTaskX[this.a];
                this.c = 0;
            }
        }

        TimerImpl(String str, boolean z) {
            setName(str);
            setDaemon(z);
            start();
        }

        /* access modifiers changed from: private */
        public void a(TimerTaskX timerTaskX) {
            this.c.insert(timerTaskX);
            notify();
        }

        public synchronized void cancel() {
            this.a = true;
            this.c.reset();
            notify();
        }

        public int purge() {
            if (this.c.isEmpty()) {
                return 0;
            }
            int unused = this.c.d = 0;
            this.c.deleteIfCancelled();
            return this.c.d;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(3:6|(2:8|(3:84|10|11)(2:12|13))(2:17|26)|14) */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a0, code lost:
            if (r2.isEnabled() == false) goto L_0x0000;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a2, code lost:
            r2.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x00a7, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00a8, code lost:
            monitor-enter(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
            r10.a = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x00b1, code lost:
            throw r0;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0018 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r10 = this;
            L_0x0000:
                monitor-enter(r10)
                boolean r0 = r10.a     // Catch:{ all -> 0x00b8 }
                if (r0 == 0) goto L_0x0007
                monitor-exit(r10)     // Catch:{ all -> 0x00b8 }
                return
            L_0x0007:
                com.androlua.util.TimerX$TimerImpl$TimerHeap r0 = r10.c     // Catch:{ all -> 0x00b8 }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00b8 }
                if (r0 == 0) goto L_0x001a
                boolean r0 = r10.b     // Catch:{ all -> 0x00b8 }
                if (r0 == 0) goto L_0x0015
                monitor-exit(r10)     // Catch:{ all -> 0x00b8 }
                return
            L_0x0015:
                r10.wait()     // Catch:{ InterruptedException -> 0x0018 }
            L_0x0018:
                monitor-exit(r10)     // Catch:{ all -> 0x00b8 }
                goto L_0x0000
            L_0x001a:
                long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00b8 }
                com.androlua.util.TimerX$TimerImpl$TimerHeap r2 = r10.c     // Catch:{ all -> 0x00b8 }
                com.androlua.util.TimerTaskX r2 = r2.minimum()     // Catch:{ all -> 0x00b8 }
                java.lang.Object r3 = r2.b     // Catch:{ all -> 0x00b8 }
                monitor-enter(r3)     // Catch:{ all -> 0x00b8 }
                boolean r4 = r2.c     // Catch:{ all -> 0x00b5 }
                r5 = 0
                if (r4 == 0) goto L_0x0033
                com.androlua.util.TimerX$TimerImpl$TimerHeap r0 = r10.c     // Catch:{ all -> 0x00b5 }
                r0.delete(r5)     // Catch:{ all -> 0x00b5 }
                monitor-exit(r3)     // Catch:{ all -> 0x00b5 }
                goto L_0x0018
            L_0x0033:
                long r6 = r2.d     // Catch:{ all -> 0x00b5 }
                r4 = 0
                long r8 = r6 - r0
                monitor-exit(r3)     // Catch:{ all -> 0x00b5 }
                r0 = 0
                int r3 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                if (r3 <= 0) goto L_0x0043
                r10.wait(r8)     // Catch:{ InterruptedException -> 0x0018 }
                goto L_0x0018
            L_0x0043:
                java.lang.Object r3 = r2.b     // Catch:{ all -> 0x00b8 }
                monitor-enter(r3)     // Catch:{ all -> 0x00b8 }
                com.androlua.util.TimerX$TimerImpl$TimerHeap r4 = r10.c     // Catch:{ all -> 0x00b2 }
                com.androlua.util.TimerTaskX r4 = r4.minimum()     // Catch:{ all -> 0x00b2 }
                long r6 = r4.d     // Catch:{ all -> 0x00b2 }
                long r8 = r2.d     // Catch:{ all -> 0x00b2 }
                int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r4 == 0) goto L_0x005a
                com.androlua.util.TimerX$TimerImpl$TimerHeap r4 = r10.c     // Catch:{ all -> 0x00b2 }
                int r5 = r4.a((com.androlua.util.TimerTaskX) r2)     // Catch:{ all -> 0x00b2 }
            L_0x005a:
                boolean r4 = r2.c     // Catch:{ all -> 0x00b2 }
                if (r4 == 0) goto L_0x006b
                com.androlua.util.TimerX$TimerImpl$TimerHeap r0 = r10.c     // Catch:{ all -> 0x00b2 }
                com.androlua.util.TimerX$TimerImpl$TimerHeap r1 = r10.c     // Catch:{ all -> 0x00b2 }
                int r1 = r1.a((com.androlua.util.TimerTaskX) r2)     // Catch:{ all -> 0x00b2 }
                r0.delete(r1)     // Catch:{ all -> 0x00b2 }
                monitor-exit(r3)     // Catch:{ all -> 0x00b2 }
                goto L_0x0018
            L_0x006b:
                long r6 = r2.d     // Catch:{ all -> 0x00b2 }
                r2.setScheduledTime(r6)     // Catch:{ all -> 0x00b2 }
                com.androlua.util.TimerX$TimerImpl$TimerHeap r4 = r10.c     // Catch:{ all -> 0x00b2 }
                r4.delete(r5)     // Catch:{ all -> 0x00b2 }
                long r4 = r2.e     // Catch:{ all -> 0x00b2 }
                int r6 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r6 < 0) goto L_0x0098
                boolean r0 = r2.f     // Catch:{ all -> 0x00b2 }
                if (r0 == 0) goto L_0x0089
                long r0 = r2.d     // Catch:{ all -> 0x00b2 }
                long r4 = r2.e     // Catch:{ all -> 0x00b2 }
                r6 = 0
                long r6 = r0 + r4
                r2.d = r6     // Catch:{ all -> 0x00b2 }
                goto L_0x0094
            L_0x0089:
                long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00b2 }
                long r4 = r2.e     // Catch:{ all -> 0x00b2 }
                r6 = 0
                long r6 = r0 + r4
                r2.d = r6     // Catch:{ all -> 0x00b2 }
            L_0x0094:
                r10.a((com.androlua.util.TimerTaskX) r2)     // Catch:{ all -> 0x00b2 }
                goto L_0x009a
            L_0x0098:
                r2.d = r0     // Catch:{ all -> 0x00b2 }
            L_0x009a:
                monitor-exit(r3)     // Catch:{ all -> 0x00b2 }
                monitor-exit(r10)     // Catch:{ all -> 0x00b8 }
                boolean r0 = r2.isEnabled()     // Catch:{ all -> 0x00a7 }
                if (r0 == 0) goto L_0x0000
                r2.run()     // Catch:{ all -> 0x00a7 }
                goto L_0x0000
            L_0x00a7:
                r0 = move-exception
                monitor-enter(r10)
                r1 = 1
                r10.a = r1     // Catch:{ all -> 0x00ae }
                monitor-exit(r10)     // Catch:{ all -> 0x00ae }
                goto L_0x00b1
            L_0x00ae:
                r0 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x00ae }
                throw r0
            L_0x00b1:
                throw r0
            L_0x00b2:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x00b2 }
                throw r0     // Catch:{ all -> 0x00b8 }
            L_0x00b5:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x00b5 }
                throw r0     // Catch:{ all -> 0x00b8 }
            L_0x00b8:
                r0 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x00b8 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.androlua.util.TimerX.TimerImpl.run():void");
        }
    }

    public TimerX() {
        this(false);
    }

    public TimerX(String str) {
        this(str, false);
    }

    public TimerX(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("name is null");
        }
        this.b = new TimerImpl(str, z);
        this.c = new FinalizerHelper(this.b);
    }

    public TimerX(boolean z) {
        this("Timer-" + a(), z);
    }

    private static synchronized long a() {
        long j;
        synchronized (TimerX.class) {
            j = a;
            a = j + 1;
        }
        return j;
    }

    private void a(TimerTaskX timerTaskX, long j, long j2, boolean z) {
        synchronized (this.b) {
            if (this.b.a) {
                throw new IllegalStateException("Timer was canceled");
            }
            long currentTimeMillis = j + System.currentTimeMillis();
            if (currentTimeMillis < 0) {
                throw new IllegalArgumentException("Illegal delay to start the TimerTask: " + currentTimeMillis);
            }
            synchronized (timerTaskX.b) {
                if (timerTaskX.a()) {
                    throw new IllegalStateException("TimerTask is scheduled already");
                } else if (timerTaskX.c) {
                    throw new IllegalStateException("TimerTask is canceled");
                } else {
                    timerTaskX.d = currentTimeMillis;
                    timerTaskX.e = j2;
                    timerTaskX.f = z;
                }
            }
            this.b.a(timerTaskX);
        }
    }

    public void cancel() {
        this.b.cancel();
    }

    public int purge() {
        int purge;
        synchronized (this.b) {
            purge = this.b.purge();
        }
        return purge;
    }

    public void schedule(TimerTaskX timerTaskX, long j) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        a(timerTaskX, j, -1, false);
    }

    public void schedule(TimerTaskX timerTaskX, long j, long j2) {
        if (j < 0 || j2 <= 0) {
            throw new IllegalArgumentException();
        }
        a(timerTaskX, j, j2, false);
    }

    public void schedule(TimerTaskX timerTaskX, Date date) {
        if (date.getTime() < 0) {
            throw new IllegalArgumentException();
        }
        long time = date.getTime() - System.currentTimeMillis();
        a(timerTaskX, time < 0 ? 0 : time, -1, false);
    }

    public void schedule(TimerTaskX timerTaskX, Date date, long j) {
        if (j <= 0 || date.getTime() < 0) {
            throw new IllegalArgumentException();
        }
        long time = date.getTime() - System.currentTimeMillis();
        a(timerTaskX, time < 0 ? 0 : time, j, false);
    }

    public void scheduleAtFixedRate(TimerTaskX timerTaskX, long j, long j2) {
        if (j < 0 || j2 <= 0) {
            throw new IllegalArgumentException();
        }
        a(timerTaskX, j, j2, true);
    }

    public void scheduleAtFixedRate(TimerTaskX timerTaskX, Date date, long j) {
        if (j <= 0 || date.getTime() < 0) {
            throw new IllegalArgumentException();
        }
        a(timerTaskX, date.getTime() - System.currentTimeMillis(), j, true);
    }
}
