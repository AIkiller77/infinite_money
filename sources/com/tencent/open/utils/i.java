package com.tencent.open.utils;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: ProGuard */
public final class i {
    public static final Executor a = c();
    private static Object b;
    private static Handler c;
    private static HandlerThread d;

    /* compiled from: ProGuard */
    private static class a implements Executor {
        final Queue<Runnable> a;
        Runnable b;

        private a() {
            Queue<Runnable> queue;
            new LinkedList();
            this.a = queue;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ a(AnonymousClass1 r4) {
            this();
            AnonymousClass1 r1 = r4;
        }

        /* access modifiers changed from: protected */
        public synchronized void a() {
            synchronized (this) {
                Runnable poll = this.a.poll();
                Runnable runnable = poll;
                this.b = poll;
                if (runnable != null) {
                    i.a.execute(this.b);
                }
            }
        }

        public synchronized void execute(Runnable runnable) {
            Object obj;
            Runnable runnable2 = runnable;
            synchronized (this) {
                final Runnable runnable3 = runnable2;
                new Runnable(this) {
                    final /* synthetic */ a b;

                    {
                        this.b = r6;
                    }

                    public void run() {
                        try {
                            runnable3.run();
                            this.b.a();
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            this.b.a();
                            throw th2;
                        }
                    }
                };
                boolean offer = this.a.offer(obj);
                if (this.b == null) {
                    a();
                }
            }
        }
    }

    static {
        Object obj;
        new Object();
        b = obj;
    }

    public i() {
    }

    public static Handler a() {
        HandlerThread handlerThread;
        Handler handler;
        if (c == null) {
            Class<i> cls = i.class;
            Class<i> cls2 = cls;
            synchronized (cls) {
                try {
                    new HandlerThread("SDK_SUB");
                    d = handlerThread;
                    d.start();
                    new Handler(d.getLooper());
                    c = handler;
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Class<i> cls3 = cls2;
                        throw th2;
                    }
                }
            }
        }
        return c;
    }

    public static void a(Runnable runnable) {
        boolean post = a().post(runnable);
    }

    public static Executor b() {
        Executor executor;
        Executor executor2 = executor;
        new a((AnonymousClass1) null);
        return executor2;
    }

    private static Executor c() {
        ThreadPoolExecutor threadPoolExecutor;
        BlockingQueue blockingQueue;
        ThreadPoolExecutor threadPoolExecutor2;
        ThreadPoolExecutor threadPoolExecutor3;
        Executor executor;
        BlockingQueue blockingQueue2;
        if (Build.VERSION.SDK_INT >= 11) {
            new LinkedBlockingQueue();
            new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, blockingQueue2);
            threadPoolExecutor3 = executor;
        } else {
            try {
                Field declaredField = AsyncTask.class.getDeclaredField("sExecutor");
                declaredField.setAccessible(true);
                threadPoolExecutor2 = (Executor) declaredField.get((Object) null);
            } catch (Exception e) {
                Exception exc = e;
                new LinkedBlockingQueue();
                new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, blockingQueue);
                threadPoolExecutor2 = threadPoolExecutor;
            }
            threadPoolExecutor3 = threadPoolExecutor2;
        }
        if (threadPoolExecutor3 instanceof ThreadPoolExecutor) {
            threadPoolExecutor3.setCorePoolSize(3);
        }
        return threadPoolExecutor3;
    }
}
