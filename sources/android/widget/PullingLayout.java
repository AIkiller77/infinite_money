package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.a.a.a.a.a.a.a;
import java.util.Timer;
import java.util.TimerTask;

public class PullingLayout extends RelativeLayout {
    public static final int DONE = 5;
    public static final int FAIL = 1;
    public static final int INIT = 0;
    public static final int LOADING = 4;
    public static final int NOTHING = 2;
    public static final int REFRESHING = 2;
    public static final int RELEASE_TO_LOAD = 3;
    public static final int RELEASE_TO_REFRESH = 1;
    public static final int SUCCEED = 0;
    public static final String TAG = "PullToRefreshLayout";
    private View A;
    private int B;
    private boolean C = true;
    private boolean D = true;
    private Context E;
    private boolean F;
    private boolean G;
    private LayoutInflater H;
    private OnLoadMoreListener I;
    private OnPullUpListener J;
    private OnPullDownListener K;
    private DisplayMetrics L;
    private int M;
    public float MOVE_SPEED = 8.0f;
    private int N;
    private String O = "刷新成功";
    private String P = "暂无更新";
    private String Q = "刷新失败";
    private String R = "加载成功";
    private String S = "没有更多内容";
    private String T = "加载失败";
    /* access modifiers changed from: private */
    public String U = "上拉加载更多";
    private String V = "释放立即刷新";
    private String W = "正在刷新...";
    Handler a = new Handler() {
        public void handleMessage(Message message) {
            PullingLayout.this.MOVE_SPEED = (float) ((Math.tan((1.5707963267948966d / ((double) PullingLayout.this.getMeasuredHeight())) * ((double) (PullingLayout.this.pullDownY + Math.abs(PullingLayout.this.f)))) * 5.0d) + 8.0d);
            if (!PullingLayout.this.k) {
                if (PullingLayout.this.b == 2 && PullingLayout.this.pullDownY <= PullingLayout.this.g) {
                    PullingLayout.this.pullDownY = PullingLayout.this.g;
                } else if (PullingLayout.this.b == 4 && (-PullingLayout.this.f) <= PullingLayout.this.h) {
                    float unused = PullingLayout.this.f = -PullingLayout.this.h;
                }
                PullingLayout.this.i.cancel();
            }
            if (PullingLayout.this.pullDownY > 0.0f) {
                PullingLayout.this.pullDownY -= PullingLayout.this.MOVE_SPEED;
            } else if (PullingLayout.this.f < 0.0f) {
                float unused2 = PullingLayout.this.f = PullingLayout.this.f + PullingLayout.this.MOVE_SPEED;
            }
            if (PullingLayout.this.pullDownY < 0.0f) {
                PullingLayout.this.pullDownY = 0.0f;
                PullingLayout.this.p.clearAnimation();
                if (!(PullingLayout.this.b == 2 || PullingLayout.this.b == 4)) {
                    PullingLayout.this.a(0);
                }
                PullingLayout.this.i.cancel();
                PullingLayout.this.requestLayout();
            }
            if (PullingLayout.this.f > 0.0f) {
                float unused3 = PullingLayout.this.f = 0.0f;
                PullingLayout.this.u.clearAnimation();
                if (!(PullingLayout.this.b == 2 || PullingLayout.this.b == 4)) {
                    PullingLayout.this.a(0);
                }
                PullingLayout.this.i.cancel();
                PullingLayout.this.requestLayout();
            }
            PullingLayout.this.requestLayout();
            if (PullingLayout.this.pullDownY + Math.abs(PullingLayout.this.f) == 0.0f) {
                PullingLayout.this.i.cancel();
            }
        }
    };
    private String aa = "释放立即加载";
    /* access modifiers changed from: private */
    public String ab = "下拉刷新";
    private String ac = "正在加载...";
    /* access modifiers changed from: private */
    public int ad = 0;
    /* access modifiers changed from: private */
    public int ae = 0;
    private int af = 0;
    private int ag = 0;
    /* access modifiers changed from: private */
    public int ah = 6;
    /* access modifiers changed from: private */
    public int ai = 2;
    /* access modifiers changed from: private */
    public int b = 0;
    /* access modifiers changed from: private */
    public OnRefreshListener c;
    private float d;
    private float e;
    /* access modifiers changed from: private */
    public float f = 0.0f;
    /* access modifiers changed from: private */
    public float g = 200.0f;
    /* access modifiers changed from: private */
    public float h = 200.0f;
    /* access modifiers changed from: private */
    public MyTimer i;
    private boolean j = false;
    /* access modifiers changed from: private */
    public boolean k = false;
    private float l = 2.0f;
    private RotateAnimation m;
    private RotateAnimation n;
    private HeadView o;
    /* access modifiers changed from: private */
    public View p;
    public float pullDownY = 0.0f;

    /* renamed from: q  reason: collision with root package name */
    private View f6q;
    private ImageView r;
    private TextView s;
    private FootView t;
    /* access modifiers changed from: private */
    public View u;
    private View v;
    private ImageView w;
    private TextView x;
    /* access modifiers changed from: private */
    public int y;
    private FrameLayout z;

    private class AutoRefreshAndLoadTask extends AsyncTask<Integer, Float, String> {
        private AutoRefreshAndLoadTask() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String doInBackground(Integer... numArr) {
            while (PullingLayout.this.pullDownY < PullingLayout.this.g * 1.0f) {
                PullingLayout.this.pullDownY += PullingLayout.this.MOVE_SPEED;
                publishProgress(new Float[]{Float.valueOf(PullingLayout.this.pullDownY)});
                try {
                    Thread.sleep((long) numArr[0].intValue());
                } catch (InterruptedException e) {
                    a.a(e);
                }
            }
            return null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            PullingLayout.this.a(2);
            if (PullingLayout.this.c != null) {
                PullingLayout.this.c.onRefresh(PullingLayout.this);
            }
            PullingLayout.this.a();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onProgressUpdate(Float... fArr) {
            if (PullingLayout.this.pullDownY > PullingLayout.this.g) {
                PullingLayout.this.a(1);
            }
            PullingLayout.this.requestLayout();
        }
    }

    private class FailDrawable extends LoadingDrawable {
        FailDrawable() {
            super();
            b();
        }
    }

    private class FailDrawable2 extends Drawable {
        final /* synthetic */ PullingLayout a;
        private Paint b;

        public void draw(Canvas canvas) {
            this.b.setColor(this.a.y);
            Rect bounds = getBounds();
            canvas.drawCircle((float) (bounds.right / 2), (float) (bounds.bottom / 2), (float) ((int) (((double) ((float) Math.min(bounds.right, bounds.bottom))) * 0.35d)), this.b);
            canvas.drawLine((float) (bounds.right / 2), ((float) bounds.bottom) * 0.25f, (float) (bounds.right / 2), ((float) bounds.bottom) * 0.65f, this.b);
            canvas.drawLine((float) (bounds.right / 2), ((float) bounds.bottom) * 0.7f, (float) (bounds.right / 2), ((float) bounds.bottom) * 0.75f, this.b);
        }

        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
            this.b.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }
    }

    public class FootView extends RelativeLayout {
        private ImageView b;
        private TextView c;
        private ImageView d;
        private ImageView e;

        public FootView(Context context) {
            super(context);
            a(context);
        }

        public FootView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            a(context);
        }

        public FootView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            a(context);
        }

        private void a(Context context) {
            int b2 = PullingLayout.this.a(30.0f);
            RelativeLayout relativeLayout = new RelativeLayout(context);
            relativeLayout.setPadding(0, PullingLayout.this.a(20.0f), 0, PullingLayout.this.a(20.0f));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(10);
            addView(relativeLayout, layoutParams);
            RelativeLayout relativeLayout2 = new RelativeLayout(context);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(13);
            relativeLayout.addView(relativeLayout2, layoutParams2);
            this.e = new ImageView(context);
            this.e.setBackgroundDrawable(new PullUpDrawable());
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(b2, b2);
            layoutParams3.setMargins(PullingLayout.this.a(60.0f), 0, 0, 0);
            layoutParams3.addRule(15);
            relativeLayout2.addView(this.e, layoutParams3);
            this.b = new ImageView(context);
            this.b.setVisibility(8);
            this.b.setBackgroundDrawable(new LoadingDrawable());
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(b2, b2);
            layoutParams4.setMargins(PullingLayout.this.a(60.0f), 0, 0, 0);
            layoutParams4.addRule(15);
            relativeLayout2.addView(this.b, layoutParams4);
            this.c = new TextView(context);
            this.c.setText(PullingLayout.this.U);
            this.c.setTextSize(16.0f);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams5.addRule(13);
            relativeLayout2.addView(this.c, layoutParams5);
            this.d = new ImageView(context);
            this.d.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(b2, b2);
            layoutParams6.setMargins(PullingLayout.this.a(60.0f), 0, 0, 0);
            layoutParams6.addRule(15);
            relativeLayout2.addView(this.d, layoutParams6);
        }

        public ImageView getLoadingView() {
            return this.b;
        }

        public ImageView getPullView() {
            return this.e;
        }

        public TextView getStateText() {
            return this.c;
        }

        public ImageView getStateView() {
            return this.d;
        }
    }

    public class HeadView extends RelativeLayout {
        private ImageView b;
        private TextView c;
        private ImageView d;
        private ImageView e;

        public HeadView(Context context) {
            super(context);
            a(context);
        }

        public HeadView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            a(context);
        }

        public HeadView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            a(context);
        }

        private void a(Context context) {
            int b2 = PullingLayout.this.a(30.0f);
            RelativeLayout relativeLayout = new RelativeLayout(context);
            relativeLayout.setPadding(0, PullingLayout.this.a(20.0f), 0, PullingLayout.this.a(20.0f));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(12);
            addView(relativeLayout, layoutParams);
            RelativeLayout relativeLayout2 = new RelativeLayout(context);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(13);
            relativeLayout.addView(relativeLayout2, layoutParams2);
            this.e = new ImageView(context);
            this.e.setBackgroundDrawable(new PullDownDrawable());
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(b2, b2);
            layoutParams3.setMargins(PullingLayout.this.a(60.0f), 0, 0, 0);
            layoutParams3.addRule(15);
            relativeLayout2.addView(this.e, layoutParams3);
            this.b = new ImageView(context);
            this.b.setVisibility(8);
            this.b.setBackgroundDrawable(new LoadingDrawable());
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(b2, b2);
            layoutParams4.setMargins(PullingLayout.this.a(60.0f), 0, 0, 0);
            layoutParams4.addRule(15);
            relativeLayout2.addView(this.b, layoutParams4);
            this.c = new TextView(context);
            this.c.setText(PullingLayout.this.ab);
            this.c.setTextSize(16.0f);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams5.addRule(13);
            relativeLayout2.addView(this.c, layoutParams5);
            this.d = new ImageView(context);
            this.d.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(b2, b2);
            layoutParams6.setMargins(PullingLayout.this.a(60.0f), 0, 0, 0);
            layoutParams6.addRule(15);
            relativeLayout2.addView(this.d, layoutParams6);
        }

        public ImageView getLoadingView() {
            return this.b;
        }

        public ImageView getPullView() {
            return this.e;
        }

        public TextView getStateText() {
            return this.c;
        }

        public ImageView getStateView() {
            return this.d;
        }
    }

    private class LoadingDrawable extends Drawable {
        private Paint a = new Paint();
        private int c;

        LoadingDrawable() {
            this.a.setStyle(Paint.Style.STROKE);
            this.a.setAntiAlias(true);
            this.a.setStrokeWidth((float) PullingLayout.this.a(2.0f));
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.c = 1;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.c = -1;
        }

        public void draw(Canvas canvas) {
            PullingLayout pullingLayout;
            int p;
            int q2;
            this.a.setColor(PullingLayout.this.y);
            Rect bounds = getBounds();
            float min = (float) ((int) ((float) Math.min(bounds.right, bounds.bottom)));
            float f = 0.15f * min;
            float f2 = min * 0.85f;
            RectF rectF = new RectF(f, f, f2, f2);
            if (PullingLayout.this.ad >= 360 && this.c == 0) {
                int unused = PullingLayout.this.ai = 8;
                int unused2 = PullingLayout.this.ah = -6;
            } else if (PullingLayout.this.ad <= 6) {
                int unused3 = PullingLayout.this.ah = 6;
                int unused4 = PullingLayout.this.ai = 2;
            }
            if (PullingLayout.this.ad < 360 || this.c == 0) {
                if (this.c == 0) {
                    int unused5 = PullingLayout.this.ad = PullingLayout.this.ad + PullingLayout.this.ah;
                    pullingLayout = PullingLayout.this;
                    p = PullingLayout.this.ae;
                    q2 = PullingLayout.this.ai;
                } else {
                    int unused6 = PullingLayout.this.ad = PullingLayout.this.ad + (PullingLayout.this.ah * 2);
                    pullingLayout = PullingLayout.this;
                    p = PullingLayout.this.ae;
                    q2 = PullingLayout.this.ai * 2;
                }
                int unused7 = pullingLayout.ae = p + q2;
                int unused8 = PullingLayout.this.ae = PullingLayout.this.ae % 360;
            }
            canvas.drawArc(rectF, (float) PullingLayout.this.ae, (float) PullingLayout.this.ad, false, this.a);
            if (PullingLayout.this.ad >= 360) {
                int unused9 = PullingLayout.this.ah = -6;
                int unused10 = PullingLayout.this.ai = 8;
                if (this.c == 1) {
                    Path path = new Path();
                    path.moveTo(((float) bounds.right) * 0.3f, ((float) bounds.bottom) * 0.5f);
                    path.lineTo(((float) bounds.right) * 0.45f, ((float) bounds.bottom) * 0.7f);
                    path.lineTo(((float) bounds.right) * 0.75f, ((float) bounds.bottom) * 0.4f);
                    canvas.drawPath(path, this.a);
                } else if (this.c == -1) {
                    Canvas canvas2 = canvas;
                    canvas2.drawLine((float) (bounds.right / 2), ((float) bounds.bottom) * 0.25f, (float) (bounds.right / 2), ((float) bounds.bottom) * 0.65f, this.a);
                    canvas2.drawLine((float) (bounds.right / 2), ((float) bounds.bottom) * 0.7f, (float) (bounds.right / 2), ((float) bounds.bottom) * 0.75f, this.a);
                }
            }
            invalidateSelf();
        }

        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
            this.a.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.a.setColorFilter(colorFilter);
        }
    }

    private class LoadingDrawable2 extends Drawable {
        final /* synthetic */ PullingLayout a;
        private Paint b;
        private int c;
        private int d;
        private int e;
        private int f;

        public void draw(Canvas canvas) {
            this.b.setColor(this.a.y);
            Rect bounds = getBounds();
            int min = (int) (((double) ((float) Math.min(bounds.right, bounds.bottom))) * 0.35d);
            float f2 = (float) (min / 2);
            float f3 = ((float) min) * 2.5f;
            RectF rectF = new RectF(f2, f2, f3, f3);
            if (this.c > 360) {
                this.f += this.e;
                this.e = 0 - this.e;
            } else if (this.c < 6) {
                this.e = 6;
                this.f = 2;
            }
            this.c += this.e;
            this.d += this.f;
            canvas.drawArc(rectF, (float) (this.d % 360), (float) this.c, false, this.b);
            invalidateSelf();
        }

        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
            this.b.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }
    }

    class MyTimer {
        private Handler b;
        private Timer c = new Timer();
        private MyTask d;

        class MyTask extends TimerTask {
            private Handler b;

            public MyTask(Handler handler) {
                this.b = handler;
            }

            public void run() {
                this.b.obtainMessage().sendToTarget();
            }
        }

        public MyTimer(Handler handler) {
            this.b = handler;
        }

        public void cancel() {
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
        }

        public void schedule(long j) {
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
            this.d = new MyTask(this.b);
            this.c.schedule(this.d, 0, j);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore(PullingLayout pullingLayout);
    }

    public interface OnPullDownListener {
        boolean onPullDown(View view);
    }

    public interface OnPullUpListener {
        boolean onPullUp(View view);
    }

    public interface OnRefreshListener {
        void onRefresh(PullingLayout pullingLayout);
    }

    private class PullDownDrawable extends Drawable {
        private Paint b = new Paint();

        PullDownDrawable() {
            this.b.setStyle(Paint.Style.STROKE);
            this.b.setAntiAlias(true);
            this.b.setStrokeWidth((float) PullingLayout.this.a(2.0f));
        }

        public void draw(Canvas canvas) {
            this.b.setColor(PullingLayout.this.y);
            Rect bounds = getBounds();
            canvas.drawCircle((float) (bounds.right / 2), (float) (bounds.bottom / 2), (float) ((int) (((double) ((float) Math.min(bounds.right, bounds.bottom))) * 0.35d)), this.b);
            Path path = new Path();
            path.moveTo(((float) bounds.right) * 0.5f, ((float) bounds.bottom) * 0.25f);
            path.lineTo(((float) bounds.right) * 0.5f, ((float) bounds.bottom) * 0.75f);
            path.moveTo(((float) bounds.right) * 0.25f, ((float) bounds.bottom) * 0.5f);
            path.lineTo(((float) bounds.right) * 0.5f, ((float) bounds.bottom) * 0.75f);
            path.lineTo(((float) bounds.right) * 0.75f, ((float) bounds.bottom) * 0.5f);
            canvas.drawPath(path, this.b);
        }

        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
            this.b.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }
    }

    private class PullUpDrawable extends Drawable {
        private Paint b = new Paint();

        PullUpDrawable() {
            this.b.setStyle(Paint.Style.STROKE);
            this.b.setAntiAlias(true);
            this.b.setStrokeWidth((float) PullingLayout.this.a(2.0f));
        }

        public void draw(Canvas canvas) {
            this.b.setColor(PullingLayout.this.y);
            Rect bounds = getBounds();
            canvas.drawCircle((float) (bounds.right / 2), (float) (bounds.bottom / 2), (float) ((int) (((double) ((float) Math.min(bounds.right, bounds.bottom))) * 0.35d)), this.b);
            Path path = new Path();
            path.moveTo(((float) bounds.right) * 0.5f, ((float) bounds.bottom) * 0.25f);
            path.lineTo(((float) bounds.right) * 0.5f, ((float) bounds.bottom) * 0.75f);
            path.moveTo(((float) bounds.right) * 0.25f, ((float) bounds.bottom) * 0.5f);
            path.lineTo(((float) bounds.right) * 0.5f, ((float) bounds.bottom) * 0.25f);
            path.lineTo(((float) bounds.right) * 0.75f, ((float) bounds.bottom) * 0.5f);
            canvas.drawPath(path, this.b);
        }

        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
            this.b.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }
    }

    private class SucceedDrawable extends LoadingDrawable {
        SucceedDrawable() {
            super();
            a();
        }
    }

    private class SucceedDrawable2 extends Drawable {
        final /* synthetic */ PullingLayout a;
        private Paint b;

        public void draw(Canvas canvas) {
            this.b.setColor(this.a.y);
            Rect bounds = getBounds();
            canvas.drawCircle((float) (bounds.right / 2), (float) (bounds.bottom / 2), (float) ((int) (((double) ((float) Math.min(bounds.right, bounds.bottom))) * 0.35d)), this.b);
            Path path = new Path();
            path.moveTo(((float) bounds.right) * 0.3f, ((float) bounds.bottom) * 0.5f);
            path.lineTo(((float) bounds.right) * 0.45f, ((float) bounds.bottom) * 0.7f);
            path.lineTo(((float) bounds.right) * 0.75f, ((float) bounds.bottom) * 0.4f);
            canvas.drawPath(path, this.b);
        }

        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
            this.b.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }
    }

    public PullingLayout(Context context) {
        super(context);
        a(context);
    }

    public PullingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PullingLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    /* access modifiers changed from: private */
    public int a(float f2) {
        return (int) TypedValue.applyDimension(1, f2, this.L);
    }

    /* access modifiers changed from: private */
    public void a() {
        this.i.schedule(5);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x003b, code lost:
        r4.setText(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0048, code lost:
        r4.startAnimation(r3.m);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r4) {
        /*
            r3 = this;
            r3.b = r4
            int r4 = r3.b
            r0 = 4
            r1 = 0
            switch(r4) {
                case 0: goto L_0x004e;
                case 1: goto L_0x003f;
                case 2: goto L_0x0028;
                case 3: goto L_0x001e;
                case 4: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            return
        L_0x000a:
            android.view.View r4 = r3.u
            r4.clearAnimation()
            android.view.View r4 = r3.v
            r4.setVisibility(r1)
            android.view.View r4 = r3.u
            r4.setVisibility(r0)
            android.widget.TextView r4 = r3.x
            java.lang.String r0 = r3.ac
            goto L_0x003b
        L_0x001e:
            android.widget.TextView r4 = r3.x
            java.lang.String r0 = r3.aa
            r4.setText(r0)
            android.view.View r4 = r3.u
            goto L_0x0048
        L_0x0028:
            android.view.View r4 = r3.p
            r4.clearAnimation()
            android.view.View r4 = r3.f6q
            r4.setVisibility(r1)
            android.view.View r4 = r3.p
            r4.setVisibility(r0)
            android.widget.TextView r4 = r3.s
            java.lang.String r0 = r3.W
        L_0x003b:
            r4.setText(r0)
            return
        L_0x003f:
            android.widget.TextView r4 = r3.s
            java.lang.String r0 = r3.V
            r4.setText(r0)
            android.view.View r4 = r3.p
        L_0x0048:
            android.view.animation.RotateAnimation r0 = r3.m
            r4.startAnimation(r0)
            return
        L_0x004e:
            android.widget.ImageView r4 = r3.r
            r0 = 8
            r4.setVisibility(r0)
            android.widget.TextView r4 = r3.s
            java.lang.String r2 = r3.ab
            r4.setText(r2)
            android.view.View r4 = r3.p
            r4.clearAnimation()
            android.view.View r4 = r3.p
            r4.setVisibility(r1)
            android.widget.ImageView r4 = r3.w
            r4.setVisibility(r0)
            android.widget.TextView r4 = r3.x
            java.lang.String r0 = r3.U
            r4.setText(r0)
            android.view.View r4 = r3.u
            r4.clearAnimation()
            android.view.View r4 = r3.u
            r4.setVisibility(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.PullingLayout.a(int):void");
    }

    private void a(Context context) {
        this.L = context.getResources().getDisplayMetrics();
        this.E = context;
        TypedArray obtainStyledAttributes = this.E.getTheme().obtainStyledAttributes(new int[]{16842800, 16842801});
        this.M = obtainStyledAttributes.getColor(0, -1);
        this.N = obtainStyledAttributes.getColor(1, ViewCompat.MEASURED_STATE_MASK);
        obtainStyledAttributes.recycle();
        setStateColor(this.M);
        this.H = (LayoutInflater) context.getSystemService("layout_inflater");
        this.o = new HeadView(this.E);
        super.addView(this.o, new RelativeLayout.LayoutParams(-1, -1));
        this.z = new FrameLayout(this.E);
        super.addView(this.z, new RelativeLayout.LayoutParams(-1, -1));
        this.t = new FootView(this.E);
        super.addView(this.t, new RelativeLayout.LayoutParams(-1, -1));
        this.z = (FrameLayout) getChildAt(1);
        e();
        this.i = new MyTimer(this.a);
        this.m = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        this.m.setDuration(100);
        this.m.setRepeatCount(0);
        this.m.setFillAfter(true);
        this.n = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.n.setDuration(1500);
        this.n.setRepeatCount(-1);
        this.n.setFillAfter(true);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        this.m.setInterpolator(linearInterpolator);
        this.n.setInterpolator(linearInterpolator);
    }

    private boolean a(View view) {
        return view.getScrollY() == 0;
    }

    private boolean a(WebView webView) {
        return ((float) webView.getScrollY()) >= (((float) webView.getContentHeight()) * webView.getScale()) - ((float) webView.getMeasuredHeight());
    }

    private boolean a(AbsListView absListView) {
        if (absListView.getCount() == 0) {
            return true;
        }
        return absListView.getFirstVisiblePosition() == 0 && absListView.getChildAt(0).getTop() >= 0;
    }

    private boolean a(ExpandableListView expandableListView) {
        if (expandableListView.getCount() == 0) {
            return true;
        }
        return expandableListView.getLastVisiblePosition() == expandableListView.getCount() - 1 && expandableListView.getChildAt(expandableListView.getLastVisiblePosition() - expandableListView.getFirstVisiblePosition()) != null && expandableListView.getChildAt(expandableListView.getLastVisiblePosition() - expandableListView.getFirstVisiblePosition()).getBottom() <= expandableListView.getMeasuredHeight();
    }

    private boolean a(GridView gridView) {
        if (gridView.getCount() == 0) {
            return true;
        }
        return gridView.getLastVisiblePosition() == gridView.getCount() - 1 && gridView.getChildAt(gridView.getLastVisiblePosition() - gridView.getFirstVisiblePosition()) != null && gridView.getChildAt(gridView.getLastVisiblePosition() - gridView.getFirstVisiblePosition()).getBottom() <= gridView.getMeasuredHeight();
    }

    private boolean a(ListView listView) {
        if (listView.getCount() == 0) {
            return true;
        }
        return listView.getLastVisiblePosition() == listView.getCount() - 1 && listView.getChildAt(listView.getLastVisiblePosition() - listView.getFirstVisiblePosition()) != null && listView.getChildAt(listView.getLastVisiblePosition() - listView.getFirstVisiblePosition()).getBottom() <= listView.getMeasuredHeight();
    }

    private boolean a(ScrollView scrollView) {
        return scrollView.getScrollY() >= scrollView.getChildAt(0).getHeight() - scrollView.getMeasuredHeight();
    }

    private void b() {
        this.C = true;
        this.D = true;
    }

    private boolean c() {
        if (!this.F || this.A == null) {
            return false;
        }
        if (this.J != null) {
            return this.J.onPullUp(this.A);
        }
        if (this.A instanceof ListView) {
            return a((ListView) this.A);
        }
        if (this.A instanceof GridView) {
            return a((GridView) this.A);
        }
        if (this.A instanceof ExpandableListView) {
            return a((ExpandableListView) this.A);
        }
        if (this.A instanceof ScrollView) {
            return a((ScrollView) this.A);
        }
        if (this.A instanceof WebView) {
            return a((WebView) this.A);
        }
        return true;
    }

    private boolean d() {
        if (!this.G || this.A == null) {
            return false;
        }
        if (this.K != null) {
            return this.K.onPullDown(this.A);
        }
        if (this.A instanceof AbsListView) {
            return a((AbsListView) this.A);
        }
        if (this.A instanceof ScrollView) {
            return a((View) (ScrollView) this.A);
        }
        if (this.A instanceof WebView) {
            return a((View) (WebView) this.A);
        }
        return true;
    }

    private void e() {
        this.p = this.o.getPullView();
        this.s = this.o.getStateText();
        this.f6q = this.o.getLoadingView();
        this.r = this.o.getStateView();
        this.u = this.t.getPullView();
        this.x = this.t.getStateText();
        this.v = this.t.getLoadingView();
        this.w = this.t.getStateView();
    }

    public void addView(View view) {
        this.A = view;
        this.z.addView(view);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        view.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height));
        this.A = view;
        this.z.addView(view);
    }

    public void autoLoad() {
        this.f = -this.h;
        requestLayout();
        a(4);
        if (this.I != null) {
            this.I.onLoadMore(this);
        }
    }

    public void autoRefresh() {
        new AutoRefreshAndLoadTask().execute(new Integer[]{20});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0077, code lost:
        if (r9.b == 4) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a9, code lost:
        if (r9.b == 2) goto L_0x00ab;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r10) {
        /*
            r9 = this;
            int r0 = r10.getActionMasked()
            r1 = 2
            r2 = 4
            r3 = 3
            r4 = 0
            r5 = 1
            switch(r0) {
                case 0: goto L_0x0187;
                case 1: goto L_0x014f;
                case 2: goto L_0x0013;
                case 3: goto L_0x000c;
                case 4: goto L_0x000c;
                case 5: goto L_0x000e;
                case 6: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x019b
        L_0x000e:
            r0 = -1
            r9.B = r0
            goto L_0x019b
        L_0x0013:
            int r0 = r9.B
            r6 = 0
            if (r0 != 0) goto L_0x00ae
            float r0 = r9.pullDownY
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 > 0) goto L_0x007a
            boolean r0 = r9.d()
            if (r0 == 0) goto L_0x002d
            boolean r0 = r9.C
            if (r0 == 0) goto L_0x002d
            int r0 = r9.b
            if (r0 == r2) goto L_0x002d
            goto L_0x007a
        L_0x002d:
            float r0 = r9.f
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 < 0) goto L_0x0046
            boolean r0 = r9.c()
            if (r0 == 0) goto L_0x0042
            boolean r0 = r9.D
            if (r0 == 0) goto L_0x0042
            int r0 = r9.b
            if (r0 == r1) goto L_0x0042
            goto L_0x0046
        L_0x0042:
            r9.b()
            goto L_0x00b0
        L_0x0046:
            float r0 = r9.f
            float r1 = r10.getY()
            float r7 = r9.e
            float r1 = r1 - r7
            float r7 = r9.l
            float r1 = r1 / r7
            float r0 = r0 + r1
            r9.f = r0
            float r0 = r9.f
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x0061
            r9.f = r6
            r9.C = r5
            r9.D = r4
        L_0x0061:
            float r0 = r9.f
            int r1 = r9.getMeasuredHeight()
            int r1 = -r1
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x0075
            int r0 = r9.getMeasuredHeight()
            int r0 = -r0
            float r0 = (float) r0
            r9.f = r0
        L_0x0075:
            int r0 = r9.b
            if (r0 != r2) goto L_0x00b0
            goto L_0x00ab
        L_0x007a:
            float r0 = r9.pullDownY
            float r2 = r10.getY()
            float r7 = r9.e
            float r2 = r2 - r7
            float r7 = r9.l
            float r2 = r2 / r7
            float r0 = r0 + r2
            r9.pullDownY = r0
            float r0 = r9.pullDownY
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x0095
            r9.pullDownY = r6
            r9.C = r4
            r9.D = r5
        L_0x0095:
            float r0 = r9.pullDownY
            int r2 = r9.getMeasuredHeight()
            float r2 = (float) r2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x00a7
            int r0 = r9.getMeasuredHeight()
            float r0 = (float) r0
            r9.pullDownY = r0
        L_0x00a7:
            int r0 = r9.b
            if (r0 != r1) goto L_0x00b0
        L_0x00ab:
            r9.k = r5
            goto L_0x00b0
        L_0x00ae:
            r9.B = r4
        L_0x00b0:
            float r0 = r10.getY()
            r9.e = r0
            r0 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
            int r2 = r9.getMeasuredHeight()
            double r7 = (double) r2
            double r0 = r0 / r7
            float r2 = r9.pullDownY
            float r7 = r9.f
            float r7 = java.lang.Math.abs(r7)
            float r2 = r2 + r7
            double r7 = (double) r2
            double r0 = r0 * r7
            double r0 = java.lang.Math.tan(r0)
            r7 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r0 = r0 * r7
            double r0 = r0 + r7
            float r0 = (float) r0
            r9.l = r0
            float r0 = r9.pullDownY
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 > 0) goto L_0x00e5
            float r0 = r9.f
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x00e8
        L_0x00e5:
            r9.requestLayout()
        L_0x00e8:
            float r0 = r9.pullDownY
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            r1 = 5
            if (r0 <= 0) goto L_0x0112
            float r0 = r9.pullDownY
            float r2 = r9.g
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0102
            int r0 = r9.b
            if (r0 == r5) goto L_0x00ff
            int r0 = r9.b
            if (r0 != r1) goto L_0x0102
        L_0x00ff:
            r9.a((int) r4)
        L_0x0102:
            float r0 = r9.pullDownY
            float r1 = r9.g
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L_0x013c
            int r0 = r9.b
            if (r0 != 0) goto L_0x013c
            r9.a((int) r5)
            goto L_0x013c
        L_0x0112:
            float r0 = r9.f
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x013c
            float r0 = r9.f
            float r0 = -r0
            float r2 = r9.h
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x012c
            int r0 = r9.b
            if (r0 == r3) goto L_0x0129
            int r0 = r9.b
            if (r0 != r1) goto L_0x012c
        L_0x0129:
            r9.a((int) r4)
        L_0x012c:
            float r0 = r9.f
            float r0 = -r0
            float r1 = r9.h
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L_0x013c
            int r0 = r9.b
            if (r0 != 0) goto L_0x013c
            r9.a((int) r3)
        L_0x013c:
            float r0 = r9.pullDownY
            float r1 = r9.f
            float r1 = java.lang.Math.abs(r1)
            float r0 = r0 + r1
            r1 = 1090519040(0x41000000, float:8.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x019b
            r10.setAction(r3)
            goto L_0x019b
        L_0x014f:
            float r0 = r9.pullDownY
            float r6 = r9.g
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 > 0) goto L_0x0160
            float r0 = r9.f
            float r0 = -r0
            float r6 = r9.h
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x0162
        L_0x0160:
            r9.k = r4
        L_0x0162:
            int r0 = r9.b
            if (r0 != r5) goto L_0x0173
            r9.a((int) r1)
            android.widget.PullingLayout$OnRefreshListener r0 = r9.c
            if (r0 == 0) goto L_0x0183
            android.widget.PullingLayout$OnRefreshListener r0 = r9.c
            r0.onRefresh(r9)
            goto L_0x0183
        L_0x0173:
            int r0 = r9.b
            if (r0 != r3) goto L_0x0183
            r9.a((int) r2)
            android.widget.PullingLayout$OnLoadMoreListener r0 = r9.I
            if (r0 == 0) goto L_0x0183
            android.widget.PullingLayout$OnLoadMoreListener r0 = r9.I
            r0.onLoadMore(r9)
        L_0x0183:
            r9.a()
            goto L_0x019b
        L_0x0187:
            float r0 = r10.getY()
            r9.d = r0
            float r0 = r9.d
            r9.e = r0
            android.widget.PullingLayout$MyTimer r0 = r9.i
            r0.cancel()
            r9.B = r4
            r9.b()
        L_0x019b:
            super.dispatchTouchEvent(r10)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.PullingLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public String getLoadFail() {
        return this.T;
    }

    public String getLoadNothing() {
        return this.S;
    }

    public String getLoadSucceed() {
        return this.R;
    }

    public String getLoading() {
        return this.ac;
    }

    public String getPulldownToRefresh() {
        return this.ab;
    }

    public String getPullupToLoad() {
        return this.U;
    }

    public String getRefreshFail() {
        return this.Q;
    }

    public String getRefreshNothing() {
        return this.P;
    }

    public String getRefreshSucceed() {
        return this.O;
    }

    public String getRefreshing() {
        return this.W;
    }

    public String getReleaseToLoad() {
        return this.aa;
    }

    public String getReleaseToRefresh() {
        return this.V;
    }

    public void loadmoreFinish(int i2) {
        ImageView imageView;
        Drawable succeedDrawable;
        if (this.b == 4) {
            this.v.clearAnimation();
            this.v.setVisibility(8);
            if (i2 == 0) {
                this.w.setVisibility(0);
                this.x.setText(this.R);
                imageView = this.w;
                succeedDrawable = new SucceedDrawable();
            } else if (i2 != 2) {
                this.w.setVisibility(0);
                this.x.setText(this.T);
                imageView = this.w;
                succeedDrawable = new FailDrawable();
            } else {
                this.w.setVisibility(0);
                this.x.setText(this.S);
                imageView = this.w;
                succeedDrawable = new FailDrawable();
            }
            imageView.setBackgroundDrawable(succeedDrawable);
            if (this.f < 0.0f) {
                new Handler() {
                    public void handleMessage(Message message) {
                        PullingLayout.this.a(5);
                        PullingLayout.this.a();
                    }
                }.sendEmptyMessageDelayed(0, 1000);
                return;
            }
            a(5);
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (!this.j) {
            this.j = true;
            this.g = (float) this.o.getChildAt(0).getMeasuredHeight();
            this.h = (float) this.t.getChildAt(0).getMeasuredHeight();
            this.x.setTextColor(this.y);
            this.s.setTextColor(this.y);
        }
        this.o.layout(0, ((int) (this.pullDownY + this.f)) - this.o.getMeasuredHeight(), this.o.getMeasuredWidth(), (int) (this.pullDownY + this.f));
        this.z.layout(0, (int) (this.pullDownY + this.f), this.z.getMeasuredWidth(), ((int) (this.pullDownY + this.f)) + this.z.getMeasuredHeight());
        this.t.layout(0, ((int) (this.pullDownY + this.f)) + this.z.getMeasuredHeight(), this.t.getMeasuredWidth(), ((int) (this.pullDownY + this.f)) + this.z.getMeasuredHeight() + this.t.getMeasuredHeight());
    }

    public void refreshFinish(int i2) {
        ImageView imageView;
        Drawable succeedDrawable;
        if (this.b == 2) {
            this.f6q.clearAnimation();
            this.f6q.setVisibility(8);
            if (i2 == 0) {
                this.r.setVisibility(0);
                this.s.setText(this.O);
                imageView = this.r;
                succeedDrawable = new SucceedDrawable();
            } else if (i2 != 2) {
                this.r.setVisibility(0);
                this.s.setText(this.Q);
                imageView = this.r;
                succeedDrawable = new FailDrawable();
            } else {
                this.r.setVisibility(0);
                this.s.setText(this.P);
                imageView = this.r;
                succeedDrawable = new FailDrawable();
            }
            imageView.setBackgroundDrawable(succeedDrawable);
            if (this.pullDownY > 0.0f) {
                new Handler() {
                    public void handleMessage(Message message) {
                        PullingLayout.this.a(5);
                        PullingLayout.this.a();
                    }
                }.sendEmptyMessageDelayed(0, 1000);
                return;
            }
            a(5);
            a();
        }
    }

    public void setLoadFail(String str) {
        this.T = str;
    }

    public void setLoadNothing(String str) {
        this.S = str;
    }

    public void setLoadSucceed(String str) {
        this.R = str;
    }

    public void setLoading(String str) {
        this.ac = str;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.I = onLoadMoreListener;
    }

    public void setOnPullDownListener(OnPullDownListener onPullDownListener) {
        this.K = onPullDownListener;
    }

    public void setOnPullUpListener(OnPullUpListener onPullUpListener) {
        this.J = onPullUpListener;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.c = onRefreshListener;
    }

    public void setPullDownEnabled(boolean z2) {
        this.G = z2;
    }

    public void setPullUpEnabled(boolean z2) {
        this.F = z2;
    }

    public void setPulldownToRefresh(String str) {
        this.ab = str;
    }

    public void setPullupToLoad(String str) {
        this.U = str;
    }

    public void setRefreshFail(String str) {
        this.Q = str;
    }

    public void setRefreshNothing(String str) {
        this.P = str;
    }

    public void setRefreshSucceed(String str) {
        this.O = str;
    }

    public void setRefreshing(String str) {
        this.W = str;
    }

    public void setReleaseToLoad(String str) {
        this.aa = str;
    }

    public void setReleaseToRefresh(String str) {
        this.V = str;
    }

    public void setStateColor(int i2) {
        this.y = i2;
    }
}
