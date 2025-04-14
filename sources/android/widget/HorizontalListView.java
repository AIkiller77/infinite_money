package android.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HorizontalListView extends AdapterView<ListAdapter> {
    private int A;
    /* access modifiers changed from: private */
    public boolean B;
    private boolean C;
    /* access modifiers changed from: private */
    public View.OnClickListener D;
    /* access modifiers changed from: private */
    public Rect E;
    private int F;
    private Drawable G;
    private int H;
    private DataSetObserver I;
    private Runnable J;
    protected Scroller a;
    protected ListAdapter b;
    protected int c;
    protected int d;
    private final DisplayMetrics e;
    private final GestureListener f;
    /* access modifiers changed from: private */
    public GestureDetector g;
    private int h;
    private List<Queue<View>> i;
    /* access modifiers changed from: private */
    public boolean j;
    private Rect k;
    private View l;
    private int m;
    private Drawable n;
    private Integer o;
    private int p;
    /* access modifiers changed from: private */

    /* renamed from: q  reason: collision with root package name */
    public int f4q;
    private int r;
    private int s;
    private RunningOutOfDataListener t;
    private int u;
    /* access modifiers changed from: private */
    public boolean v;
    private OnScrollStateChangedListener w;
    private OnScrollStateChangedListener.ScrollState x;
    private EdgeEffect y;
    private EdgeEffect z;

    /* renamed from: android.widget.HorizontalListView$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnTouchListener {
        final /* synthetic */ HorizontalListView a;

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return this.a.g.onTouchEvent(motionEvent);
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        /* synthetic */ GestureListener(HorizontalListView horizontalListView, AnonymousClass1 r2) {
            this();
        }

        public boolean onDown(MotionEvent motionEvent) {
            return HorizontalListView.this.a(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            Rect unused = HorizontalListView.this.E = null;
            return HorizontalListView.this.a(motionEvent, motionEvent2, f, f2);
        }

        public void onLongPress(MotionEvent motionEvent) {
            HorizontalListView.this.e();
            int a2 = HorizontalListView.this.c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (a2 >= 0 && !HorizontalListView.this.B) {
                View childAt = HorizontalListView.this.getChildAt(a2);
                AdapterView.OnItemLongClickListener onItemLongClickListener = HorizontalListView.this.getOnItemLongClickListener();
                if (onItemLongClickListener != null) {
                    int e = HorizontalListView.this.f4q + a2;
                    if (onItemLongClickListener.onItemLongClick(HorizontalListView.this, childAt, e, HorizontalListView.this.b.getItemId(e))) {
                        HorizontalListView.this.performHapticFeedback(0);
                    }
                }
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            Rect unused = HorizontalListView.this.E = null;
            HorizontalListView.this.a((Boolean) true);
            HorizontalListView.this.setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_TOUCH_SCROLL);
            HorizontalListView.this.e();
            HorizontalListView.this.d += (int) f;
            HorizontalListView.this.i(Math.round(f));
            HorizontalListView.this.requestLayout();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            HorizontalListView.this.e();
            AdapterView.OnItemClickListener onItemClickListener = HorizontalListView.this.getOnItemClickListener();
            int a2 = HorizontalListView.this.c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (a2 >= 0 && !HorizontalListView.this.B) {
                View childAt = HorizontalListView.this.getChildAt(a2);
                int e = HorizontalListView.this.f4q + a2;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(HorizontalListView.this, childAt, e, HorizontalListView.this.b.getItemId(e));
                    return true;
                }
            }
            if (HorizontalListView.this.D == null || HorizontalListView.this.B) {
                return false;
            }
            HorizontalListView.this.D.onClick(HorizontalListView.this);
            return false;
        }
    }

    @TargetApi(11)
    private static final class HoneycombPlus {
        static {
            if (Build.VERSION.SDK_INT < 11) {
                throw new RuntimeException("Should not get to HoneycombPlus class unless sdk is >= 11!");
            }
        }

        private HoneycombPlus() {
        }

        public static void setFriction(Scroller scroller, float f) {
            if (scroller != null) {
                scroller.setFriction(f);
            }
        }
    }

    @TargetApi(14)
    private static final class IceCreamSandwichPlus {
        static {
            if (Build.VERSION.SDK_INT < 14) {
                throw new RuntimeException("Should not get to IceCreamSandwichPlus class unless sdk is >= 14!");
            }
        }

        private IceCreamSandwichPlus() {
        }

        public static float getCurrVelocity(Scroller scroller) {
            return scroller.getCurrVelocity();
        }
    }

    public interface OnScrollStateChangedListener {

        public enum ScrollState {
            SCROLL_STATE_IDLE,
            SCROLL_STATE_TOUCH_SCROLL,
            SCROLL_STATE_FLING
        }

        void onScrollStateChanged(ScrollState scrollState);
    }

    public interface RunningOutOfDataListener {
        void onRunningOutOfData();
    }

    public HorizontalListView(Context context) {
        this(context, (AttributeSet) null);
    }

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Scroller(getContext());
        this.f = new GestureListener(this, (AnonymousClass1) null);
        this.i = new ArrayList();
        this.j = false;
        this.k = new Rect();
        this.l = null;
        this.m = 0;
        this.n = null;
        this.o = null;
        this.p = Integer.MAX_VALUE;
        this.t = null;
        this.u = 0;
        this.v = false;
        this.w = null;
        this.x = OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE;
        this.B = false;
        this.C = false;
        this.F = -2004318072;
        this.I = new DataSetObserver() {
            public void onChanged() {
                boolean unused = HorizontalListView.this.j = true;
                boolean unused2 = HorizontalListView.this.v = false;
                HorizontalListView.this.e();
                HorizontalListView.this.invalidate();
                HorizontalListView.this.requestLayout();
            }

            public void onInvalidated() {
                boolean unused = HorizontalListView.this.v = false;
                HorizontalListView.this.e();
                HorizontalListView.this.b();
                HorizontalListView.this.invalidate();
                HorizontalListView.this.requestLayout();
            }
        };
        this.J = new Runnable() {
            public void run() {
                HorizontalListView.this.requestLayout();
            }
        };
        this.e = context.getResources().getDisplayMetrics();
        this.y = new EdgeEffect(context);
        this.z = new EdgeEffect(context);
        this.g = new GestureDetector(context, this.f);
        a();
        setWillNotDraw(false);
        HoneycombPlus.setFriction(this.a, 0.009f);
    }

    private int a(float f2) {
        return (int) TypedValue.applyDimension(1, f2, this.e);
    }

    private void a() {
        this.f4q = -1;
        this.r = -1;
        this.s = -1;
        this.h = 0;
        this.c = 0;
        this.d = 0;
        this.p = Integer.MAX_VALUE;
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
    }

    private void a(int i2) {
        this.i.clear();
        for (int i3 = 0; i3 < i2; i3++) {
            this.i.add(new LinkedList());
        }
    }

    private void a(int i2, int i3) {
        while (i2 + i3 + this.m < getWidth() && this.r + 1 < this.b.getCount()) {
            this.r++;
            if (this.f4q < 0) {
                this.f4q = this.r;
            }
            View view = this.b.getView(this.r, b(this.r), this);
            a(view, -1);
            i2 += (this.r == 0 ? 0 : this.m) + view.getMeasuredWidth();
            g();
        }
    }

    private void a(int i2, View view) {
        int itemViewType = this.b.getItemViewType(i2);
        if (c(itemViewType)) {
            this.i.get(itemViewType).offer(view);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0082, code lost:
        if (r4.z.draw(r5) != false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003d, code lost:
        if (r4.y.draw(r5) != false) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.graphics.Canvas r5) {
        /*
            r4 = this;
            android.widget.EdgeEffect r0 = r4.y
            r1 = 0
            if (r0 == 0) goto L_0x0046
            android.widget.EdgeEffect r0 = r4.y
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4.h()
            if (r0 == 0) goto L_0x0046
            int r0 = r5.save()
            int r2 = r4.getHeight()
            r3 = -1028390912(0xffffffffc2b40000, float:-90.0)
            r5.rotate(r3, r1, r1)
            int r2 = -r2
            int r3 = r4.getPaddingBottom()
            int r2 = r2 + r3
            float r2 = (float) r2
            r5.translate(r2, r1)
            android.widget.EdgeEffect r1 = r4.y
            int r2 = r4.getRenderHeight()
            int r3 = r4.getRenderWidth()
            r1.setSize(r2, r3)
            android.widget.EdgeEffect r1 = r4.y
            boolean r1 = r1.draw(r5)
            if (r1 == 0) goto L_0x0042
        L_0x003f:
            r4.invalidate()
        L_0x0042:
            r5.restoreToCount(r0)
            return
        L_0x0046:
            android.widget.EdgeEffect r0 = r4.z
            if (r0 == 0) goto L_0x0085
            android.widget.EdgeEffect r0 = r4.z
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x0085
            boolean r0 = r4.h()
            if (r0 == 0) goto L_0x0085
            int r0 = r5.save()
            int r2 = r4.getWidth()
            r3 = 1119092736(0x42b40000, float:90.0)
            r5.rotate(r3, r1, r1)
            int r1 = r4.getPaddingTop()
            float r1 = (float) r1
            int r2 = -r2
            float r2 = (float) r2
            r5.translate(r1, r2)
            android.widget.EdgeEffect r1 = r4.z
            int r2 = r4.getRenderHeight()
            int r3 = r4.getRenderWidth()
            r1.setSize(r2, r3)
            android.widget.EdgeEffect r1 = r4.z
            boolean r1 = r1.draw(r5)
            if (r1 == 0) goto L_0x0042
            goto L_0x003f
        L_0x0085:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.HorizontalListView.a(android.graphics.Canvas):void");
    }

    private void a(Canvas canvas, Rect rect) {
        if (this.n != null) {
            this.n.setBounds(rect);
            this.n.draw(canvas);
        }
    }

    private void a(View view) {
        ViewGroup.LayoutParams b2 = b(view);
        view.measure(b2.width > 0 ? View.MeasureSpec.makeMeasureSpec(b2.width, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0), ViewGroup.getChildMeasureSpec(this.A, getPaddingTop() + getPaddingBottom(), b2.height));
    }

    private void a(View view, int i2) {
        addViewInLayout(view, i2, b(view), true);
        a(view);
    }

    /* access modifiers changed from: private */
    public void a(Boolean bool) {
        if (this.C != bool.booleanValue()) {
            for (View view = this; view.getParent() instanceof View; view = (View) view.getParent()) {
                if ((view.getParent() instanceof ListView) || (view.getParent() instanceof ScrollView)) {
                    view.getParent().requestDisallowInterceptTouchEvent(bool.booleanValue());
                    this.C = bool.booleanValue();
                    return;
                }
            }
        }
    }

    private View b(int i2) {
        int itemViewType = this.b.getItemViewType(i2);
        if (c(itemViewType)) {
            return (View) this.i.get(itemViewType).poll();
        }
        return null;
    }

    private ViewGroup.LayoutParams b(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams == null ? new ViewGroup.LayoutParams(-2, -1) : layoutParams;
    }

    /* access modifiers changed from: private */
    public void b() {
        a();
        removeAllViewsInLayout();
        requestLayout();
    }

    private void b(int i2, int i3) {
        while ((i2 + i3) - this.m >= 0 && this.f4q >= 1) {
            this.f4q--;
            View view = this.b.getView(this.f4q, b(this.f4q), this);
            a(view, 0);
            i2 -= this.f4q == 0 ? view.getMeasuredWidth() : this.m + view.getMeasuredWidth();
            this.h -= i2 + i3 == 0 ? view.getMeasuredWidth() : view.getMeasuredWidth() + this.m;
        }
    }

    private void b(Canvas canvas) {
        int childCount = getChildCount();
        Rect rect = this.k;
        this.k.top = getPaddingTop();
        this.k.bottom = this.k.top + getRenderHeight();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (i2 != childCount - 1 || !h(this.r)) {
                View childAt = getChildAt(i2);
                rect.left = childAt.getRight();
                rect.right = childAt.getRight() + this.m;
                if (rect.left < getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                }
                if (rect.right > getWidth() - getPaddingRight()) {
                    rect.right = getWidth() - getPaddingRight();
                }
                a(canvas, rect);
                if (i2 == 0 && childAt.getLeft() > getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                    rect.right = childAt.getLeft();
                    a(canvas, rect);
                }
            }
        }
    }

    private float c() {
        if (Build.VERSION.SDK_INT >= 14) {
            return IceCreamSandwichPlus.getCurrVelocity(this.a);
        }
        return 30.0f;
    }

    /* access modifiers changed from: private */
    public int c(int i2, int i3) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            getChildAt(i4).getHitRect(this.k);
            if (this.k.contains(i2, i3)) {
                return i4;
            }
        }
        return -1;
    }

    private boolean c(int i2) {
        return i2 < this.i.size();
    }

    private void d(int i2) {
        View rightmostChild = getRightmostChild();
        int i3 = 0;
        a(rightmostChild != null ? rightmostChild.getRight() : 0, i2);
        View leftmostChild = getLeftmostChild();
        if (leftmostChild != null) {
            i3 = leftmostChild.getLeft();
        }
        b(i3, i2);
    }

    private boolean d() {
        View rightmostChild;
        if (h(this.r) && (rightmostChild = getRightmostChild()) != null) {
            int i2 = this.p;
            this.p = (this.c + (rightmostChild.getRight() - getPaddingLeft())) - getRenderWidth();
            if (this.p < 0) {
                this.p = 0;
            }
            if (this.p != i2) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.l != null) {
            this.l.setPressed(false);
            refreshDrawableState();
            this.l = null;
        }
    }

    private void e(int i2) {
        while (true) {
            View leftmostChild = getLeftmostChild();
            if (leftmostChild != null && leftmostChild.getRight() + i2 <= 0) {
                this.h += h(this.f4q) ? leftmostChild.getMeasuredWidth() : this.m + leftmostChild.getMeasuredWidth();
                a(this.f4q, leftmostChild);
                removeViewInLayout(leftmostChild);
                this.f4q++;
            }
        }
        while (true) {
            View rightmostChild = getRightmostChild();
            if (rightmostChild != null && rightmostChild.getLeft() + i2 >= getWidth()) {
                a(this.r, rightmostChild);
                removeViewInLayout(rightmostChild);
                this.r--;
            } else {
                return;
            }
        }
    }

    private void f() {
        if (this.y != null) {
            this.y.onRelease();
        }
        if (this.z != null) {
            this.z.onRelease();
        }
    }

    private void f(int i2) {
        int childCount = getChildCount();
        int i3 = this.H;
        this.H = 0;
        if (childCount > 0) {
            this.h += i2;
            int i4 = this.h;
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                int paddingLeft = getPaddingLeft() + i4;
                int paddingTop = getPaddingTop();
                int measuredHeight = childAt.getMeasuredHeight() + paddingTop;
                this.H = Math.max(this.H, getPaddingBottom() + measuredHeight);
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, measuredHeight);
                i4 += childAt.getMeasuredWidth() + this.m;
            }
            if (i3 == 0) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        HorizontalListView.this.requestLayout();
                    }
                }, 96);
            }
        }
    }

    private View g(int i2) {
        if (i2 < this.f4q || i2 > this.r) {
            return null;
        }
        return getChildAt(i2 - this.f4q);
    }

    private void g() {
        if (this.t != null && this.b != null && this.b.getCount() - (this.r + 1) < this.u && !this.v) {
            this.v = true;
            this.t.onRunningOutOfData();
        }
    }

    private View getLeftmostChild() {
        return getChildAt(0);
    }

    private int getRenderHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getRenderWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private View getRightmostChild() {
        return getChildAt(getChildCount() - 1);
    }

    private boolean h() {
        return this.b != null && !this.b.isEmpty() && this.p > 0;
    }

    private boolean h(int i2) {
        return i2 == this.b.getCount() - 1;
    }

    /* access modifiers changed from: private */
    public void i(int i2) {
        EdgeEffect edgeEffect;
        if (this.y != null && this.z != null) {
            int i3 = this.c + i2;
            if (this.a == null || this.a.isFinished()) {
                if (i3 < 0) {
                    this.y.onPull(((float) Math.abs(i2)) / ((float) getRenderWidth()));
                    if (!this.z.isFinished()) {
                        edgeEffect = this.z;
                    } else {
                        return;
                    }
                } else if (i3 > this.p) {
                    this.z.onPull(((float) Math.abs(i2)) / ((float) getRenderWidth()));
                    if (!this.y.isFinished()) {
                        edgeEffect = this.y;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
                edgeEffect.onRelease();
            }
        }
    }

    /* access modifiers changed from: private */
    public void setCurrentScrollState(OnScrollStateChangedListener.ScrollState scrollState) {
        if (!(this.x == scrollState || this.w == null)) {
            this.w.onScrollStateChanged(scrollState);
        }
        this.x = scrollState;
    }

    /* access modifiers changed from: protected */
    public boolean a(MotionEvent motionEvent) {
        int c2;
        this.B = !this.a.isFinished();
        this.a.forceFinished(true);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        e();
        if (!this.B && (c2 = c((int) motionEvent.getX(), (int) motionEvent.getY())) >= 0) {
            this.l = getChildAt(c2);
            if (this.l != null) {
                this.l.setPressed(true);
                refreshDrawableState();
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        this.a.fling(this.d, 0, (int) (-f2), 0, 0, this.p, 0, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
        return true;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        View selectedView;
        View selectedView2;
        if (this.E != null) {
            Paint paint = new Paint();
            paint.setColor(-2004318072);
            canvas.drawRect(this.E, paint);
        }
        if (this.s >= 0 && this.f4q <= this.s && this.r >= this.s && this.G != null && (selectedView2 = getSelectedView()) != null) {
            this.G.setBounds(new Rect(selectedView2.getLeft(), selectedView2.getTop(), selectedView2.getRight(), selectedView2.getBottom()));
            this.G.draw(canvas);
        }
        super.dispatchDraw(canvas);
        if (this.s >= 0 && this.f4q <= this.s && this.r >= this.s && (selectedView = getSelectedView()) != null) {
            Rect rect = new Rect(selectedView.getLeft(), selectedView.getBottom() - a(2.0f), selectedView.getRight(), selectedView.getBottom());
            Paint paint2 = new Paint();
            paint2.setColor(this.F);
            canvas.drawRect(rect, paint2);
        }
        a(canvas);
    }

    /* access modifiers changed from: protected */
    public void dispatchSetPressed(boolean z2) {
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent) | this.g.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                    int c2 = c((int) motionEvent.getX(), (int) motionEvent.getY());
                    if (c2 >= 0) {
                        View childAt = getChildAt(c2);
                        this.E = new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
                        break;
                    } else {
                        return dispatchTouchEvent;
                    }
                case 1:
                    break;
                default:
                    return dispatchTouchEvent;
            }
        }
        this.E = null;
        invalidate();
        return dispatchTouchEvent;
    }

    public ListAdapter getAdapter() {
        return this.b;
    }

    public int getFirstVisiblePosition() {
        return this.f4q;
    }

    public int getLastVisiblePosition() {
        return this.r;
    }

    /* access modifiers changed from: protected */
    public float getLeftFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (this.c == 0) {
            return 0.0f;
        }
        if (this.c < horizontalFadingEdgeLength) {
            return ((float) this.c) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    /* access modifiers changed from: protected */
    public float getRightFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (this.c == this.p) {
            return 0.0f;
        }
        if (this.p - this.c < horizontalFadingEdgeLength) {
            return ((float) (this.p - this.c)) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    public View getSelectedView() {
        return g(this.s);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b(canvas);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0090  */
    @android.annotation.SuppressLint({"WrongCall"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
        /*
            r3 = this;
            super.onLayout(r4, r5, r6, r7, r8)
            android.widget.ListAdapter r0 = r3.b
            if (r0 != 0) goto L_0x0008
            return
        L_0x0008:
            r3.invalidate()
            boolean r0 = r3.j
            r1 = 0
            if (r0 == 0) goto L_0x001c
            int r0 = r3.c
            r3.a()
            r3.removeAllViewsInLayout()
            r3.d = r0
            r3.j = r1
        L_0x001c:
            java.lang.Integer r0 = r3.o
            if (r0 == 0) goto L_0x002b
            java.lang.Integer r0 = r3.o
            int r0 = r0.intValue()
            r3.d = r0
            r0 = 0
            r3.o = r0
        L_0x002b:
            android.widget.Scroller r0 = r3.a
            boolean r0 = r0.computeScrollOffset()
            if (r0 == 0) goto L_0x003b
            android.widget.Scroller r0 = r3.a
            int r0 = r0.getCurrX()
            r3.d = r0
        L_0x003b:
            int r0 = r3.d
            r2 = 1
            if (r0 >= 0) goto L_0x005f
            r3.d = r1
            android.widget.EdgeEffect r0 = r3.y
            boolean r0 = r0.isFinished()
            if (r0 == 0) goto L_0x0054
            android.widget.EdgeEffect r0 = r3.y
        L_0x004c:
            float r1 = r3.c()
            int r1 = (int) r1
            r0.onAbsorb(r1)
        L_0x0054:
            android.widget.Scroller r0 = r3.a
            r0.forceFinished(r2)
            android.widget.HorizontalListView$OnScrollStateChangedListener$ScrollState r0 = android.widget.HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE
            r3.setCurrentScrollState(r0)
            goto L_0x0074
        L_0x005f:
            int r0 = r3.d
            int r1 = r3.p
            if (r0 <= r1) goto L_0x0074
            int r0 = r3.p
            r3.d = r0
            android.widget.EdgeEffect r0 = r3.z
            boolean r0 = r0.isFinished()
            if (r0 == 0) goto L_0x0054
            android.widget.EdgeEffect r0 = r3.z
            goto L_0x004c
        L_0x0074:
            int r0 = r3.c
            int r1 = r3.d
            int r0 = r0 - r1
            r3.e((int) r0)
            r3.d((int) r0)
            r3.f((int) r0)
            int r0 = r3.d
            r3.c = r0
            boolean r0 = r3.d()
            if (r0 == 0) goto L_0x0090
            r3.onLayout(r4, r5, r6, r7, r8)
            return
        L_0x0090:
            android.widget.Scroller r4 = r3.a
            boolean r4 = r4.isFinished()
            if (r4 == 0) goto L_0x00a4
            android.widget.HorizontalListView$OnScrollStateChangedListener$ScrollState r4 = r3.x
            android.widget.HorizontalListView$OnScrollStateChangedListener$ScrollState r5 = android.widget.HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING
            if (r4 != r5) goto L_0x00a9
            android.widget.HorizontalListView$OnScrollStateChangedListener$ScrollState r4 = android.widget.HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE
            r3.setCurrentScrollState(r4)
            return
        L_0x00a4:
            java.lang.Runnable r4 = r3.J
            r3.postOnAnimation(r4)
        L_0x00a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.HorizontalListView.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE) {
            setMeasuredDimension(i2, View.MeasureSpec.makeMeasureSpec(this.H, 1073741824));
        }
        this.A = i3;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.o = Integer.valueOf(bundle.getInt("BUNDLE_ID_CURRENT_X"));
            super.onRestoreInstanceState(bundle.getParcelable("BUNDLE_ID_PARENT_STATE"));
        }
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("BUNDLE_ID_PARENT_STATE", super.onSaveInstanceState());
        bundle.putInt("BUNDLE_ID_CURRENT_X", this.c);
        return bundle;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1) {
            if (this.a == null || this.a.isFinished()) {
                setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
            }
            a((Boolean) false);
            f();
        } else if (action == 3) {
            e();
            f();
            a((Boolean) false);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void scrollTo(int i2) {
        this.a.startScroll(this.d, 0, i2 - this.d, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.b != null) {
            this.b.unregisterDataSetObserver(this.I);
        }
        if (listAdapter != null) {
            this.v = false;
            this.b = listAdapter;
            this.b.registerDataSetObserver(this.I);
        }
        a(this.b.getViewTypeCount());
        b();
    }

    public void setDivider(Drawable drawable) {
        this.n = drawable;
        setDividerWidth(drawable != null ? drawable.getIntrinsicWidth() : 0);
    }

    public void setDividerWidth(int i2) {
        this.m = i2;
        requestLayout();
        invalidate();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.D = onClickListener;
    }

    public void setOnScrollStateChangedListener(OnScrollStateChangedListener onScrollStateChangedListener) {
        this.w = onScrollStateChangedListener;
    }

    public void setRunningOutOfDataListener(RunningOutOfDataListener runningOutOfDataListener, int i2) {
        this.t = runningOutOfDataListener;
        this.u = i2;
    }

    public void setSelectedBackground(Drawable drawable) {
        this.G = drawable;
        invalidate();
    }

    public void setSelectedBackgroundColor(int i2) {
        this.G = new ColorDrawable(i2);
        invalidate();
    }

    public void setSelectedColor(int i2) {
        this.F = i2;
        invalidate();
    }

    public void setSelection(int i2) {
        this.s = i2;
        if (this.b == null || i2 < 0 || i2 > this.b.getCount()) {
            invalidate();
            return;
        }
        View selectedView = getSelectedView();
        if (selectedView == null || selectedView.getRight() > getWidth() || selectedView.getLeft() < 0) {
            View view = null;
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2 - 1) {
                view = this.b.getView(i3, view, this);
                a(view);
                i4 += i3 == 0 ? view.getMeasuredWidth() : this.m + view.getMeasuredWidth();
                i3++;
            }
            scrollTo(i4);
        }
        invalidate();
    }
}
