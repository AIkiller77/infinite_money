package com.tencent.qq.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ReboundEffectsView extends ListView implements AbsListView.OnScrollListener {
    /* access modifiers changed from: private */
    public static final float PULL_BACK_REDUCE_STEP = 1.0f;
    private static final int PULL_BACK_TASK_PERIOD = 500000;
    private static final float PULL_FACTOR = 0.4f;
    private static final String TAG = "ReboundEffectsView";
    private int currentScrollState;
    private int firstItemIndex;
    /* access modifiers changed from: private */
    public Handler handler;
    /* access modifiers changed from: private */
    public View headView;
    private boolean isRecored;
    /* access modifiers changed from: private */
    public ScheduledExecutorService schedulor;
    private int startY;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReboundEffectsView(Context context) {
        super(context);
        Handler handler2;
        new Handler(this) {
            private final ReboundEffectsView this$0;

            {
                this.this$0 = r6;
            }

            static ReboundEffectsView access$0(AnonymousClass100000000 r4) {
                return r4.this$0;
            }

            @Override
            public void handleMessage(Message message) {
                super.handleMessage(message);
                AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) this.this$0.headView.getLayoutParams();
                AbsListView.LayoutParams layoutParams2 = layoutParams;
                layoutParams2.height = (int) (((float) layoutParams2.height) - 1.0f);
                this.this$0.headView.setLayoutParams(layoutParams);
                this.this$0.headView.invalidate();
                if (layoutParams.height <= 0) {
                    List shutdownNow = this.this$0.schedulor.shutdownNow();
                }
            }
        };
        this.handler = handler2;
        init();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReboundEffectsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Handler handler2;
        new Handler(this) {
            private final ReboundEffectsView this$0;

            {
                this.this$0 = r6;
            }

            static ReboundEffectsView access$0(AnonymousClass100000000 r4) {
                return r4.this$0;
            }

            @Override
            public void handleMessage(Message message) {
                super.handleMessage(message);
                AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) this.this$0.headView.getLayoutParams();
                AbsListView.LayoutParams layoutParams2 = layoutParams;
                layoutParams2.height = (int) (((float) layoutParams2.height) - 1.0f);
                this.this$0.headView.setLayoutParams(layoutParams);
                this.this$0.headView.invalidate();
                if (layoutParams.height <= 0) {
                    List shutdownNow = this.this$0.schedulor.shutdownNow();
                }
            }
        };
        this.handler = handler2;
        init();
    }

    private void init() {
        View view;
        ViewGroup.LayoutParams layoutParams;
        setOnScrollListener(this);
        new View(getContext());
        this.headView = view;
        this.headView.setBackgroundColor(Color.parseColor("#FFF9F9FB"));
        new AbsListView.LayoutParams(-1, 0);
        this.headView.setLayoutParams(layoutParams);
        addHeaderView(this.headView);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        AbsListView absListView2 = absListView;
        int i4 = i2;
        int i5 = i3;
        int i6 = i;
        this.firstItemIndex = i6;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        AbsListView absListView2 = absListView;
        int i2 = i;
        this.currentScrollState = i2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewGroup.LayoutParams layoutParams;
        Runnable runnable;
        MotionEvent motionEvent2 = motionEvent;
        switch (motionEvent2.getAction()) {
            case 0:
                if (this.firstItemIndex == 0) {
                    this.isRecored = true;
                    this.startY = (int) motionEvent2.getY();
                    break;
                }
                break;
            case 1:
            case 3:
                if (this.isRecored) {
                    this.schedulor = Executors.newScheduledThreadPool(1);
                    new Runnable(this) {
                        private final ReboundEffectsView this$0;

                        {
                            this.this$0 = r6;
                        }

                        static ReboundEffectsView access$0(AnonymousClass100000001 r4) {
                            return r4.this$0;
                        }

                        @Override
                        public void run() {
                            this.this$0.handler.obtainMessage().sendToTarget();
                        }
                    };
                    ScheduledFuture<?> scheduleAtFixedRate = this.schedulor.scheduleAtFixedRate(runnable, (long) 0, (long) PULL_BACK_TASK_PERIOD, TimeUnit.NANOSECONDS);
                    this.isRecored = false;
                    break;
                }
                break;
            case 2:
                if (!this.isRecored && this.firstItemIndex == 0) {
                    this.isRecored = true;
                    this.startY = (int) motionEvent2.getY();
                }
                if (this.isRecored) {
                    int y = ((int) motionEvent2.getY()) - this.startY;
                    if (y >= 0) {
                        new AbsListView.LayoutParams(-1, (int) (((float) y) * PULL_FACTOR));
                        this.headView.setLayoutParams(layoutParams);
                        this.headView.invalidate();
                        break;
                    } else {
                        this.isRecored = false;
                        break;
                    }
                }
                break;
        }
        return super.onTouchEvent(motionEvent2);
    }
}
