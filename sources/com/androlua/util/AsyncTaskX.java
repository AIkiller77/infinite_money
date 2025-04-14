package com.androlua.util;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AsyncTaskX<Params, Progress, Result> {
    public static final Executor SERIAL_EXECUTOR = new SerialExecutor();
    public static final Executor THREAD_POOL_EXECUTOR;
    private static final int a = Runtime.getRuntime().availableProcessors();
    private static final ThreadFactory b = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> c = new LinkedBlockingQueue(1024);
    private static volatile Executor d = THREAD_POOL_EXECUTOR;
    private static InternalHandler e;
    private final WorkerRunnable<Params, Result> f;
    private final FutureTask<Result> g;
    private volatile Status h;
    /* access modifiers changed from: private */
    public final AtomicBoolean i;
    /* access modifiers changed from: private */
    public final AtomicBoolean j;
    private final Handler k;

    private static class AsyncTaskResult<Data> {
        final AsyncTaskX a;
        final Data[] b;

        AsyncTaskResult(AsyncTaskX asyncTaskX, Data... dataArr) {
            this.a = asyncTaskX;
            this.b = dataArr;
        }
    }

    private static class InternalHandler extends Handler {
        public InternalHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            switch (message.what) {
                case 1:
                    asyncTaskResult.a.e(asyncTaskResult.b[0]);
                    return;
                case 2:
                    asyncTaskResult.a.b((Progress[]) asyncTaskResult.b);
                    return;
                default:
                    return;
            }
        }
    }

    private static class SerialExecutor implements Executor {
        final ArrayDeque<Runnable> a;
        Runnable b;

        private SerialExecutor() {
            this.a = new ArrayDeque<>();
        }

        /* access modifiers changed from: protected */
        public synchronized void a() {
            Runnable poll = this.a.poll();
            this.b = poll;
            if (poll != null) {
                AsyncTaskX.THREAD_POOL_EXECUTOR.execute(this.b);
            }
        }

        public synchronized void execute(final Runnable runnable) {
            this.a.offer(new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        SerialExecutor.this.a();
                    }
                }
            });
            if (this.b == null) {
                a();
            }
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] b;

        private WorkerRunnable() {
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 128, 30, TimeUnit.SECONDS, c, b);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
    }

    public AsyncTaskX() {
        this((Looper) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AsyncTaskX(Handler handler) {
        this(handler != null ? handler.getLooper() : null);
    }

    public AsyncTaskX(Looper looper) {
        this.h = Status.PENDING;
        this.i = new AtomicBoolean();
        this.j = new AtomicBoolean();
        this.k = (looper == null || looper == Looper.getMainLooper()) ? c() : new Handler(looper);
        this.f = new WorkerRunnable<Params, Result>() {
            public Result call() {
                AsyncTaskX.this.j.set(true);
                Result result = null;
                try {
                    Process.setThreadPriority(10);
                    Result a2 = AsyncTaskX.this.a((Params[]) this.b);
                    try {
                        Binder.flushPendingCommands();
                        Object unused = AsyncTaskX.this.d(a2);
                        return a2;
                    } catch (Throwable th) {
                        th = th;
                        result = a2;
                        Object unused2 = AsyncTaskX.this.d(result);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    AsyncTaskX.this.i.set(true);
                    throw th;
                }
            }
        };
        this.g = new FutureTask<Result>(this.f) {
            /* access modifiers changed from: protected */
            public void done() {
                try {
                    AsyncTaskX.this.c(get());
                } catch (InterruptedException e) {
                    Log.w("AsyncTaskX", e);
                } catch (ExecutionException e2) {
                    throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
                } catch (CancellationException unused) {
                    AsyncTaskX.this.c(null);
                }
            }
        };
    }

    private static Handler c() {
        InternalHandler internalHandler;
        synchronized (AsyncTaskX.class) {
            if (e == null) {
                e = new InternalHandler(Looper.getMainLooper());
            }
            internalHandler = e;
        }
        return internalHandler;
    }

    /* access modifiers changed from: private */
    public void c(Result result) {
        if (!this.j.get()) {
            d(result);
        }
    }

    private Handler d() {
        return this.k;
    }

    /* access modifiers changed from: private */
    public Result d(Result result) {
        d().obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    /* access modifiers changed from: private */
    public void e(Result result) {
        if (isCancelled()) {
            b(result);
        } else {
            a(result);
        }
        this.h = Status.FINISHED;
    }

    public static void execute(Runnable runnable) {
        d.execute(runnable);
    }

    public static void setDefaultExecutor(Executor executor) {
        d = executor;
    }

    /* access modifiers changed from: protected */
    public abstract Result a(Params... paramsArr);

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void a(Result result) {
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void b(Result result) {
        b();
    }

    /* access modifiers changed from: protected */
    public void b(Progress... progressArr) {
    }

    /* access modifiers changed from: protected */
    public final void c(Progress... progressArr) {
        if (!isCancelled()) {
            d().obtainMessage(2, new AsyncTaskResult(this, progressArr)).sendToTarget();
        }
    }

    public final boolean cancel(boolean z) {
        this.i.set(true);
        return this.g.cancel(z);
    }

    public final AsyncTaskX<Params, Progress, Result> execute(Params... paramsArr) {
        return executeOnExecutor(d, paramsArr);
    }

    public final AsyncTaskX<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (this.h != Status.PENDING) {
            switch (this.h) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.h = Status.RUNNING;
        a();
        this.f.b = paramsArr;
        executor.execute(this.g);
        return this;
    }

    public final Result get() {
        return this.g.get();
    }

    public final Result get(long j2, TimeUnit timeUnit) {
        return this.g.get(j2, timeUnit);
    }

    public final Status getStatus() {
        return this.h;
    }

    public final boolean isCancelled() {
        return this.i.get();
    }
}
