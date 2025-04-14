package android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ViewDragHelper;
import com.tencent.qq.widget.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class DrawerLayout extends ViewGroup implements DrawerLayoutImpl {
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNLOCKED = 0;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    static final DrawerLayoutCompatImpl a = (Build.VERSION.SDK_INT >= 21 ? new DrawerLayoutCompatImplApi21() : new DrawerLayoutCompatImplBase());
    /* access modifiers changed from: private */
    public static final int[] b = {16842931};
    private static final boolean c = (Build.VERSION.SDK_INT >= 19);
    private static final boolean d;
    private Drawable A;
    private CharSequence B;
    private CharSequence C;
    private Object D;
    private boolean E;
    private Drawable F;
    private Drawable G;
    private Drawable H;
    private Drawable I;
    private final ArrayList<View> J;
    private final View.AccessibilityDelegate e;
    private float f;
    private int g;
    private int h;
    private float i;
    private Paint j;
    private final ViewDragHelper k;
    private final ViewDragHelper l;
    private final ViewDragCallback m;
    private final ViewDragCallback n;
    private int o;
    private boolean p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f3q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private DrawerListener v;
    private float w;
    private float x;
    private Drawable y;
    private Drawable z;

    @SuppressLint({"NewApi"})
    public static class DrawerLayoutCompatApi21 {
        private static final int[] a = {16843828};

        static class InsetsListener implements View.OnApplyWindowInsetsListener {
            InsetsListener() {
            }

            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                ((DrawerLayoutImpl) view).setChildInsets(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
                return windowInsets.consumeSystemWindowInsets();
            }
        }

        public static void applyMarginInsets(ViewGroup.MarginLayoutParams marginLayoutParams, Object obj, int i) {
            WindowInsets windowInsets = (WindowInsets) obj;
            if (i == 3) {
                windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
            } else if (i == 5) {
                windowInsets = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
            }
            marginLayoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
            marginLayoutParams.topMargin = windowInsets.getSystemWindowInsetTop();
            marginLayoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
            marginLayoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom();
        }

        public static void configureApplyInsets(View view) {
            if (view instanceof DrawerLayoutImpl) {
                view.setOnApplyWindowInsetsListener(new InsetsListener());
                view.setSystemUiVisibility(1280);
            }
        }

        public static void dispatchChildInsets(View view, Object obj, int i) {
            WindowInsets windowInsets = (WindowInsets) obj;
            if (i == 3) {
                windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
            } else if (i == 5) {
                windowInsets = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
            }
            view.dispatchApplyWindowInsets(windowInsets);
        }

        public static Drawable getDefaultStatusBarBackground(Context context) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(a);
            try {
                return obtainStyledAttributes.getDrawable(0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }

        public static int getTopInset(Object obj) {
            if (obj != null) {
                return ((WindowInsets) obj).getSystemWindowInsetTop();
            }
            return 0;
        }
    }

    interface DrawerLayoutCompatImpl {
        void applyMarginInsets(ViewGroup.MarginLayoutParams marginLayoutParams, Object obj, int i);

        void configureApplyInsets(View view);

        void dispatchChildInsets(View view, Object obj, int i);

        Drawable getDefaultStatusBarBackground(Context context);

        int getTopInset(Object obj);
    }

    @SuppressLint({"NewApi"})
    static class DrawerLayoutCompatImplApi21 implements DrawerLayoutCompatImpl {
        DrawerLayoutCompatImplApi21() {
        }

        public void applyMarginInsets(ViewGroup.MarginLayoutParams marginLayoutParams, Object obj, int i) {
            DrawerLayoutCompatApi21.applyMarginInsets(marginLayoutParams, obj, i);
        }

        public void configureApplyInsets(View view) {
            DrawerLayoutCompatApi21.configureApplyInsets(view);
        }

        public void dispatchChildInsets(View view, Object obj, int i) {
            DrawerLayoutCompatApi21.dispatchChildInsets(view, obj, i);
        }

        public Drawable getDefaultStatusBarBackground(Context context) {
            return DrawerLayoutCompatApi21.getDefaultStatusBarBackground(context);
        }

        public int getTopInset(Object obj) {
            return DrawerLayoutCompatApi21.getTopInset(obj);
        }
    }

    @SuppressLint({"NewApi"})
    static class DrawerLayoutCompatImplBase implements DrawerLayoutCompatImpl {
        DrawerLayoutCompatImplBase() {
        }

        public void applyMarginInsets(ViewGroup.MarginLayoutParams marginLayoutParams, Object obj, int i) {
        }

        public void configureApplyInsets(View view) {
        }

        public void dispatchChildInsets(View view, Object obj, int i) {
        }

        public Drawable getDefaultStatusBarBackground(Context context) {
            return null;
        }

        public int getTopInset(Object obj) {
            return 0;
        }
    }

    public interface DrawerListener {
        void onDrawerClosed(View view);

        void onDrawerOpened(View view);

        void onDrawerSlide(View view, float f);

        void onDrawerStateChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface EdgeGravity {
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        float a;
        boolean b;
        boolean c;
        public int gravity;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = 0;
        }

        public LayoutParams(int i, int i2, int i3) {
            this(i, i2);
            this.gravity = i3;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.b);
            this.gravity = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = 0;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
            this.gravity = layoutParams.gravity;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface LockMode {
    }

    protected static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a = 0;
        int b = 0;
        int c = 0;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public static abstract class SimpleDrawerListener implements DrawerListener {
        public void onDrawerClosed(View view) {
        }

        public void onDrawerOpened(View view) {
        }

        public void onDrawerSlide(View view, float f) {
        }

        public void onDrawerStateChanged(int i) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface State {
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        private final int b;
        private ViewDragHelper c;
        private final Runnable d = new Runnable() {
            public void run() {
                ViewDragCallback.this.b();
            }
        };

        public ViewDragCallback(int i) {
            this.b = i;
        }

        private void a() {
            int i = 3;
            if (this.b == 3) {
                i = 5;
            }
            View a2 = DrawerLayout.this.a(i);
            if (a2 != null) {
                DrawerLayout.this.closeDrawer(a2);
            }
        }

        /* access modifiers changed from: private */
        public void b() {
            View view;
            int i;
            int edgeSize = this.c.getEdgeSize();
            int i2 = 0;
            boolean z = this.b == 3;
            if (z) {
                view = DrawerLayout.this.a(3);
                if (view != null) {
                    i2 = -view.getWidth();
                }
                i = i2 + edgeSize;
            } else {
                view = DrawerLayout.this.a(5);
                i = DrawerLayout.this.getWidth() - edgeSize;
            }
            if (view == null) {
                return;
            }
            if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                this.c.smoothSlideViewTo(view, i, view.getTop());
                ((LayoutParams) view.getLayoutParams()).b = true;
                DrawerLayout.this.invalidate();
                a();
                DrawerLayout.this.b();
            }
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int width;
            int width2;
            if (DrawerLayout.this.a(view, 3)) {
                width2 = -view.getWidth();
                width = 0;
            } else {
                width = DrawerLayout.this.getWidth();
                width2 = width - view.getWidth();
            }
            return Math.max(width2, Math.min(i, width));
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(View view) {
            if (DrawerLayout.this.f(view)) {
                return view.getWidth();
            }
            return 0;
        }

        public void onEdgeDragStarted(int i, int i2) {
            DrawerLayout drawerLayout;
            int i3;
            if ((i & 1) == 1) {
                drawerLayout = DrawerLayout.this;
                i3 = 3;
            } else {
                drawerLayout = DrawerLayout.this;
                i3 = 5;
            }
            View a2 = drawerLayout.a(i3);
            if (a2 != null && DrawerLayout.this.getDrawerLockMode(a2) == 0) {
                this.c.captureChildView(a2, i2);
            }
        }

        public boolean onEdgeLock(int i) {
            return false;
        }

        public void onEdgeTouched(int i, int i2) {
            DrawerLayout.this.postDelayed(this.d, 160);
        }

        public void onViewCaptured(View view, int i) {
            ((LayoutParams) view.getLayoutParams()).b = false;
            a();
        }

        public void onViewDragStateChanged(int i) {
            DrawerLayout.this.a(this.b, i, this.c.getCapturedView());
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            int width = view.getWidth();
            float width2 = (DrawerLayout.this.a(view, 3) ? (float) (i + width) : (float) (DrawerLayout.this.getWidth() - i)) / ((float) width);
            DrawerLayout.this.b(view, width2);
            view.setVisibility(width2 == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        public void onViewReleased(View view, float f, float f2) {
            int i;
            float c2 = DrawerLayout.this.c(view);
            int width = view.getWidth();
            if (DrawerLayout.this.a(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && c2 > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f < 0.0f || (f == 0.0f && c2 > 0.5f)) {
                    width2 -= width;
                }
                i = width2;
            }
            this.c.settleCapturedViewAt(i, view.getTop());
            DrawerLayout.this.invalidate();
        }

        public void removeCallbacks() {
            DrawerLayout.this.removeCallbacks(this.d);
        }

        public void setDragger(ViewDragHelper viewDragHelper) {
            this.c = viewDragHelper;
        }

        public boolean tryCaptureView(View view, int i) {
            return DrawerLayout.this.f(view) && DrawerLayout.this.a(view, this.b) && DrawerLayout.this.getDrawerLockMode(view) == 0;
        }
    }

    static {
        boolean z2 = true;
        if (Build.VERSION.SDK_INT < 21) {
            z2 = false;
        }
        d = z2;
    }

    public DrawerLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = new View.AccessibilityDelegate();
        this.h = -1728053248;
        this.j = new Paint();
        this.f3q = true;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        setDescendantFocusability(262144);
        float f2 = getResources().getDisplayMetrics().density;
        this.g = (int) ((64.0f * f2) + 0.5f);
        float f3 = 400.0f * f2;
        this.m = new ViewDragCallback(3);
        this.n = new ViewDragCallback(5);
        this.k = ViewDragHelper.create(this, 1.0f, this.m);
        this.k.setEdgeTrackingEnabled(1);
        this.k.setMinVelocity(f3);
        this.m.setDragger(this.k);
        this.l = ViewDragHelper.create(this, 1.0f, this.n);
        this.l.setEdgeTrackingEnabled(2);
        this.l.setMinVelocity(f3);
        this.n.setDragger(this.l);
        setFocusableInTouchMode(true);
        setImportantForAccessibility(1);
        setAccessibilityDelegate(new View.AccessibilityDelegate());
        setMotionEventSplittingEnabled(false);
        if (getFitsSystemWindows()) {
            a.configureApplyInsets(this);
            this.y = a.getDefaultStatusBarBackground(context);
        }
        this.f = f2 * 10.0f;
        this.J = new ArrayList<>();
    }

    private void a(View view, boolean z2) {
        int i2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((!z2 && !f(childAt)) || (z2 && childAt == view)) {
                i2 = 1;
            } else if (Build.VERSION.SDK_INT >= 19) {
                i2 = 4;
            }
            childAt.setImportantForAccessibility(i2);
        }
    }

    private boolean a(Drawable drawable, int i2) {
        if (drawable == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 19 && !drawable.isAutoMirrored()) {
            return false;
        }
        setLayoutDirection(i2);
        return true;
    }

    static String b(int i2) {
        return (i2 & 3) == 3 ? "LEFT" : (i2 & 5) == 5 ? "RIGHT" : Integer.toHexString(i2);
    }

    private void d() {
        if (!d) {
            this.z = e();
            this.A = f();
        }
    }

    private Drawable e() {
        int layoutDirection = getLayoutDirection();
        if (layoutDirection == 0) {
            if (this.F != null) {
                a(this.F, layoutDirection);
                return this.F;
            }
        } else if (this.G != null) {
            a(this.G, layoutDirection);
            return this.G;
        }
        return this.H;
    }

    private Drawable f() {
        int layoutDirection = getLayoutDirection();
        if (layoutDirection == 0) {
            if (this.G != null) {
                a(this.G, layoutDirection);
                return this.G;
            }
        } else if (this.F != null) {
            a(this.F, layoutDirection);
            return this.F;
        }
        return this.I;
    }

    private boolean g() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (((LayoutParams) getChildAt(i2).getLayoutParams()).b) {
                return true;
            }
        }
        return false;
    }

    private static boolean g(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    private boolean h() {
        return i() != null;
    }

    private View i() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (f(childAt) && isDrawerVisible(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public View a() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (((LayoutParams) childAt.getLayoutParams()).c) {
                return childAt;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public View a(int i2) {
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, getLayoutDirection()) & 7;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((d(childAt) & 7) == absoluteGravity) {
                return childAt;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, View view) {
        int viewDragState = this.k.getViewDragState();
        int viewDragState2 = this.l.getViewDragState();
        int i4 = 2;
        if (viewDragState == 1 || viewDragState2 == 1) {
            i4 = 1;
        } else if (!(viewDragState == 2 || viewDragState2 == 2)) {
            i4 = 0;
        }
        if (view != null && i3 == 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams.a == 0.0f) {
                a(view);
            } else if (layoutParams.a == 1.0f) {
                b(view);
            }
        }
        if (i4 != this.o) {
            this.o = i4;
            if (this.v != null) {
                this.v.onDrawerStateChanged(i4);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(View view) {
        View rootView;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.c) {
            layoutParams.c = false;
            if (this.v != null) {
                this.v.onDrawerClosed(view);
            }
            a(view, false);
            if (hasWindowFocus() && (rootView = getRootView()) != null) {
                rootView.sendAccessibilityEvent(32);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(View view, float f2) {
        if (this.v != null) {
            this.v.onDrawerSlide(view, f2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
        int childCount = getChildCount();
        boolean z3 = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (f(childAt) && (!z2 || layoutParams.b)) {
                z3 |= a(childAt, 3) ? this.k.smoothSlideViewTo(childAt, -childAt.getWidth(), childAt.getTop()) : this.l.smoothSlideViewTo(childAt, getWidth(), childAt.getTop());
                layoutParams.b = false;
            }
        }
        this.m.removeCallbacks();
        this.n.removeCallbacks();
        if (z3) {
            invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(View view, int i2) {
        return (d(view) & i2) == i2;
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        if (getDescendantFocusability() != 393216) {
            int childCount = getChildCount();
            boolean z2 = false;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (!f(childAt)) {
                    this.J.add(childAt);
                } else if (isDrawerOpen(childAt)) {
                    childAt.addFocusables(arrayList, i2, i3);
                    z2 = true;
                }
            }
            if (!z2) {
                int size = this.J.size();
                for (int i5 = 0; i5 < size; i5++) {
                    View view = this.J.get(i5);
                    if (view.getVisibility() == 0) {
                        view.addFocusables(arrayList, i2, i3);
                    }
                }
            }
            this.J.clear();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addView(android.view.View r1, int r2, android.view.ViewGroup.LayoutParams r3) {
        /*
            r0 = this;
            super.addView(r1, r2, r3)
            android.view.View r2 = r0.a()
            if (r2 != 0) goto L_0x0015
            boolean r2 = r0.f(r1)
            if (r2 == 0) goto L_0x0010
            goto L_0x0015
        L_0x0010:
            r2 = 1
        L_0x0011:
            r1.setImportantForAccessibility(r2)
            goto L_0x001d
        L_0x0015:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 19
            if (r2 < r3) goto L_0x001d
            r2 = 4
            goto L_0x0011
        L_0x001d:
            boolean r2 = c
            if (r2 != 0) goto L_0x0026
            android.view.View$AccessibilityDelegate r2 = r0.e
            r1.setAccessibilityDelegate(r2)
        L_0x0026:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.DrawerLayout.addView(android.view.View, int, android.view.ViewGroup$LayoutParams):void");
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (!this.u) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                getChildAt(i2).dispatchTouchEvent(obtain);
            }
            obtain.recycle();
            this.u = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void b(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.c) {
            layoutParams.c = true;
            if (this.v != null) {
                this.v.onDrawerOpened(view);
            }
            a(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
            view.requestFocus();
        }
    }

    /* access modifiers changed from: package-private */
    public void b(View view, float f2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f2 != layoutParams.a) {
            layoutParams.a = f2;
            a(view, f2);
        }
    }

    /* access modifiers changed from: package-private */
    public float c(View view) {
        return ((LayoutParams) view.getLayoutParams()).a;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public void closeDrawer(int i2) {
        View a2 = a(i2);
        if (a2 == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + b(i2));
        }
        closeDrawer(a2);
    }

    public void closeDrawer(View view) {
        ViewDragHelper viewDragHelper;
        int width;
        if (!f(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        if (this.f3q) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.a = 0.0f;
            layoutParams.c = false;
        } else {
            if (a(view, 3)) {
                viewDragHelper = this.k;
                width = -view.getWidth();
            } else {
                viewDragHelper = this.l;
                width = getWidth();
            }
            viewDragHelper.smoothSlideViewTo(view, width, view.getTop());
        }
        invalidate();
    }

    public void closeDrawers() {
        a(false);
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < childCount; i2++) {
            f2 = Math.max(f2, ((LayoutParams) getChildAt(i2).getLayoutParams()).a);
        }
        this.i = f2;
        if (this.k.continueSettling(true) || this.l.continueSettling(true)) {
            postInvalidateOnAnimation();
        }
    }

    /* access modifiers changed from: package-private */
    public int d(View view) {
        return Gravity.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).gravity, getLayoutDirection());
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j2) {
        int i2;
        int i3;
        Drawable drawable;
        Canvas canvas2 = canvas;
        View view2 = view;
        int height = getHeight();
        boolean e2 = e(view2);
        int width = getWidth();
        int save = canvas2.save();
        if (e2) {
            int childCount = getChildCount();
            i2 = width;
            i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt != view2 && childAt.getVisibility() == 0 && g(childAt) && f(childAt) && childAt.getHeight() >= height) {
                    if (a(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i3) {
                            i3 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < i2) {
                            i2 = left;
                        }
                    }
                }
            }
            canvas2.clipRect(i3, 0, i2, getHeight());
        } else {
            i2 = width;
            i3 = 0;
        }
        boolean drawChild = super.drawChild(canvas, view, j2);
        canvas2.restoreToCount(save);
        if (this.i <= 0.0f || !e2) {
            if (this.z != null && a(view2, 3)) {
                int intrinsicWidth = this.z.getIntrinsicWidth();
                int right2 = view.getRight();
                float max = Math.max(0.0f, Math.min(((float) right2) / ((float) this.k.getEdgeSize()), 1.0f));
                this.z.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
                this.z.setAlpha((int) (max * 255.0f));
                drawable = this.z;
            } else if (this.A == null || !a(view2, 5)) {
                return drawChild;
            } else {
                int intrinsicWidth2 = this.A.getIntrinsicWidth();
                int left2 = view.getLeft();
                float max2 = Math.max(0.0f, Math.min(((float) (getWidth() - left2)) / ((float) this.l.getEdgeSize()), 1.0f));
                this.A.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
                this.A.setAlpha((int) (max2 * 255.0f));
                drawable = this.A;
            }
            drawable.draw(canvas2);
            return drawChild;
        }
        this.j.setColor((((int) (((float) ((this.h & ViewCompat.MEASURED_STATE_MASK) >>> 24)) * this.i)) << 24) | (this.h & ViewCompat.MEASURED_SIZE_MASK));
        canvas2.drawRect((float) i3, 0.0f, (float) i2, (float) getHeight(), this.j);
        return drawChild;
    }

    /* access modifiers changed from: package-private */
    public boolean e(View view) {
        return ((LayoutParams) view.getLayoutParams()).gravity == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean f(View view) {
        return (Gravity.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).gravity, view.getLayoutDirection()) & 7) != 0;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public float getDrawerElevation() {
        if (d) {
            return this.f;
        }
        return 0.0f;
    }

    public int getDrawerLockMode(int i2) {
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, getLayoutDirection());
        if (absoluteGravity == 3) {
            return this.r;
        }
        if (absoluteGravity == 5) {
            return this.s;
        }
        return 0;
    }

    public int getDrawerLockMode(View view) {
        int d2 = d(view);
        if (d2 == 3) {
            return this.r;
        }
        if (d2 == 5) {
            return this.s;
        }
        return 0;
    }

    public CharSequence getDrawerTitle(int i2) {
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, getLayoutDirection());
        if (absoluteGravity == 3) {
            return this.B;
        }
        if (absoluteGravity == 5) {
            return this.C;
        }
        return null;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.y;
    }

    public boolean isDrawerOpen(int i2) {
        View a2 = a(i2);
        if (a2 != null) {
            return isDrawerOpen(a2);
        }
        return false;
    }

    public boolean isDrawerOpen(View view) {
        if (f(view)) {
            return ((LayoutParams) view.getLayoutParams()).c;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean isDrawerVisible(int i2) {
        View a2 = a(i2);
        if (a2 != null) {
            return isDrawerVisible(a2);
        }
        return false;
    }

    public boolean isDrawerVisible(View view) {
        if (f(view)) {
            return ((LayoutParams) view.getLayoutParams()).a > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f3q = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f3q = true;
    }

    public void onDraw(Canvas canvas) {
        int topInset;
        super.onDraw(canvas);
        if (this.E && this.y != null && (topInset = a.getTopInset(this.D)) > 0) {
            this.y.setBounds(0, 0, getWidth(), topInset);
            this.y.draw(canvas);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0046, code lost:
        r7 = r6.k.findTopChildUnder((int) r0, (int) r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getActionMasked()
            android.widget.ViewDragHelper r1 = r6.k
            boolean r1 = r1.shouldInterceptTouchEvent(r7)
            android.widget.ViewDragHelper r2 = r6.l
            boolean r2 = r2.shouldInterceptTouchEvent(r7)
            r1 = r1 | r2
            r2 = 1
            r3 = 0
            switch(r0) {
                case 0: goto L_0x0033;
                case 1: goto L_0x002b;
                case 2: goto L_0x0017;
                case 3: goto L_0x002b;
                default: goto L_0x0016;
            }
        L_0x0016:
            goto L_0x005e
        L_0x0017:
            android.widget.ViewDragHelper r7 = r6.k
            r0 = 3
            boolean r7 = r7.checkTouchSlop(r0)
            if (r7 == 0) goto L_0x005e
            android.widget.DrawerLayout$ViewDragCallback r7 = r6.m
            r7.removeCallbacks()
            android.widget.DrawerLayout$ViewDragCallback r7 = r6.n
            r7.removeCallbacks()
            goto L_0x005e
        L_0x002b:
            r6.a((boolean) r2)
            r6.t = r3
            r6.u = r3
            goto L_0x005e
        L_0x0033:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.w = r0
            r6.x = r7
            float r4 = r6.i
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x0058
            android.widget.ViewDragHelper r4 = r6.k
            int r0 = (int) r0
            int r7 = (int) r7
            android.view.View r7 = r4.findTopChildUnder(r0, r7)
            if (r7 == 0) goto L_0x0058
            boolean r7 = r6.e(r7)
            if (r7 == 0) goto L_0x0058
            r7 = 1
            goto L_0x0059
        L_0x0058:
            r7 = 0
        L_0x0059:
            r6.t = r3
            r6.u = r3
            goto L_0x005f
        L_0x005e:
            r7 = 0
        L_0x005f:
            if (r1 != 0) goto L_0x006f
            if (r7 != 0) goto L_0x006f
            boolean r7 = r6.g()
            if (r7 != 0) goto L_0x006f
            boolean r7 = r6.u
            if (r7 == 0) goto L_0x006e
            return r2
        L_0x006e:
            r2 = 0
        L_0x006f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || !h()) {
            return super.onKeyDown(i2, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return super.onKeyUp(i2, keyEvent);
        }
        View i3 = i();
        if (i3 != null && getDrawerLockMode(i3) == 0) {
            closeDrawers();
        }
        return i3 != null;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        float f2;
        int i6;
        this.p = true;
        int i7 = i4 - i2;
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (e(childAt)) {
                    childAt.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + childAt.getMeasuredWidth(), layoutParams.topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        float f3 = (float) measuredWidth;
                        i6 = (-measuredWidth) + ((int) (layoutParams.a * f3));
                        f2 = ((float) (measuredWidth + i6)) / f3;
                    } else {
                        float f4 = (float) measuredWidth;
                        int i9 = i7 - ((int) (layoutParams.a * f4));
                        f2 = ((float) (i7 - i9)) / f4;
                        i6 = i9;
                    }
                    boolean z3 = f2 != layoutParams.a;
                    int i10 = layoutParams.gravity & R.styleable.AppCompatTheme_spinnerStyle;
                    if (i10 == 16) {
                        int i11 = i5 - i3;
                        int i12 = (i11 - measuredHeight) / 2;
                        if (i12 < layoutParams.topMargin) {
                            i12 = layoutParams.topMargin;
                        } else if (i12 + measuredHeight > i11 - layoutParams.bottomMargin) {
                            i12 = (i11 - layoutParams.bottomMargin) - measuredHeight;
                        }
                        childAt.layout(i6, i12, measuredWidth + i6, measuredHeight + i12);
                    } else if (i10 != 80) {
                        childAt.layout(i6, layoutParams.topMargin, measuredWidth + i6, layoutParams.topMargin + measuredHeight);
                    } else {
                        int i13 = i5 - i3;
                        childAt.layout(i6, (i13 - layoutParams.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i6, i13 - layoutParams.bottomMargin);
                    }
                    if (z3) {
                        b(childAt, f2);
                    }
                    int i14 = layoutParams.a > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i14) {
                        childAt.setVisibility(i14);
                    }
                }
            }
        }
        this.p = false;
        this.f3q = false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int childMeasureSpec;
        int childMeasureSpec2;
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        if (!(mode == 1073741824 && mode2 == 1073741824)) {
            if (isInEditMode()) {
                if (mode != Integer.MIN_VALUE && mode == 0) {
                    size = 300;
                }
                if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                    size2 = 300;
                }
            } else {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
        }
        setMeasuredDimension(size, size2);
        boolean z2 = this.D != null && getFitsSystemWindows();
        int layoutDirection = getLayoutDirection();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (z2) {
                    int absoluteGravity = Gravity.getAbsoluteGravity(layoutParams.gravity, layoutDirection);
                    if (childAt.getFitsSystemWindows()) {
                        a.dispatchChildInsets(childAt, this.D, absoluteGravity);
                    } else {
                        a.applyMarginInsets(layoutParams, this.D, absoluteGravity);
                    }
                }
                if (e(childAt)) {
                    childMeasureSpec = View.MeasureSpec.makeMeasureSpec((size - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824);
                    childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec((size2 - layoutParams.topMargin) - layoutParams.bottomMargin, 1073741824);
                } else if (f(childAt)) {
                    if (d && childAt.getElevation() != this.f) {
                        childAt.setElevation(this.f);
                    }
                    int d2 = d(childAt) & 7;
                    if ((0 & d2) != 0) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + b(d2) + " but this " + "DrawerLayout" + " already has a drawer view along that edge");
                    }
                    childMeasureSpec = getChildMeasureSpec(i2, this.g + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width);
                    childMeasureSpec2 = getChildMeasureSpec(i3, layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height);
                } else {
                    throw new IllegalStateException("Child " + childAt + " at index " + i4 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                }
                childAt.measure(childMeasureSpec, childMeasureSpec2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        View a2;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!(savedState.a == 0 || (a2 = a(savedState.a)) == null)) {
            openDrawer(a2);
        }
        setDrawerLockMode(savedState.b, 3);
        setDrawerLockMode(savedState.c, 5);
    }

    public void onRtlPropertiesChanged(int i2) {
        d();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        View a2 = a();
        if (a2 != null) {
            savedState.a = ((LayoutParams) a2.getLayoutParams()).gravity;
        }
        savedState.b = this.r;
        savedState.c = this.s;
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        View a2;
        this.k.processTouchEvent(motionEvent);
        this.l.processTouchEvent(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 3) {
            switch (action) {
                case 0:
                    float x2 = motionEvent.getX();
                    float y2 = motionEvent.getY();
                    this.w = x2;
                    this.x = y2;
                    break;
                case 1:
                    float x3 = motionEvent.getX();
                    float y3 = motionEvent.getY();
                    View findTopChildUnder = this.k.findTopChildUnder((int) x3, (int) y3);
                    if (findTopChildUnder != null && e(findTopChildUnder)) {
                        float f2 = x3 - this.w;
                        float f3 = y3 - this.x;
                        int touchSlop = this.k.getTouchSlop();
                        if (!((f2 * f2) + (f3 * f3) >= ((float) (touchSlop * touchSlop)) || (a2 = a()) == null || getDrawerLockMode(a2) == 2)) {
                            z2 = false;
                            a(z2);
                            this.t = false;
                            return true;
                        }
                    }
                    z2 = true;
                    a(z2);
                    this.t = false;
                    return true;
                default:
                    return true;
            }
        } else {
            a(true);
        }
        this.t = false;
        this.u = false;
        return true;
    }

    public void openDrawer(int i2) {
        View a2 = a(i2);
        if (a2 == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + b(i2));
        }
        openDrawer(a2);
    }

    public void openDrawer(View view) {
        ViewDragHelper viewDragHelper;
        int width;
        if (!f(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        if (this.f3q) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.a = 1.0f;
            layoutParams.c = true;
            a(view, true);
        } else {
            if (a(view, 3)) {
                viewDragHelper = this.k;
                width = 0;
            } else {
                viewDragHelper = this.l;
                width = getWidth() - view.getWidth();
            }
            viewDragHelper.smoothSlideViewTo(view, width, view.getTop());
        }
        invalidate();
    }

    public void requestDisallowInterceptTouchEvent(boolean z2) {
        super.requestDisallowInterceptTouchEvent(z2);
        this.t = z2;
        if (z2) {
            a(true);
        }
    }

    public void requestLayout() {
        if (!this.p) {
            super.requestLayout();
        }
    }

    public void setChildInsets(Object obj, boolean z2) {
        this.D = obj;
        this.E = z2;
        setWillNotDraw(!z2 && getBackground() == null);
        requestLayout();
    }

    public void setDrawerElevation(float f2) {
        this.f = f2;
        if (Build.VERSION.SDK_INT >= 21) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (f(childAt)) {
                    childAt.setElevation(this.f);
                }
            }
        }
    }

    public void setDrawerListener(DrawerListener drawerListener) {
        this.v = drawerListener;
    }

    public void setDrawerLockMode(int i2) {
        setDrawerLockMode(i2, 3);
        setDrawerLockMode(i2, 5);
    }

    public void setDrawerLockMode(int i2, int i3) {
        int absoluteGravity = Gravity.getAbsoluteGravity(i3, getLayoutDirection());
        if (absoluteGravity == 3) {
            this.r = i2;
        } else if (absoluteGravity == 5) {
            this.s = i2;
        }
        if (i2 != 0) {
            (absoluteGravity == 3 ? this.k : this.l).cancel();
        }
        switch (i2) {
            case 1:
                View a2 = a(absoluteGravity);
                if (a2 != null) {
                    closeDrawer(a2);
                    return;
                }
                return;
            case 2:
                View a3 = a(absoluteGravity);
                if (a3 != null) {
                    openDrawer(a3);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setDrawerLockMode(int i2, View view) {
        if (!f(view)) {
            throw new IllegalArgumentException("View " + view + " is not a drawer with appropriate layout_gravity");
        }
        setDrawerLockMode(i2, ((LayoutParams) view.getLayoutParams()).gravity);
    }

    public void setDrawerShadow(int i2, int i3) {
        setDrawerShadow(getResources().getDrawable(i2), i3);
    }

    public void setDrawerShadow(Drawable drawable, int i2) {
        if (!d) {
            if ((i2 & GravityCompat.START) == 8388611) {
                this.F = drawable;
            } else if ((i2 & GravityCompat.END) == 8388613) {
                this.G = drawable;
            } else if ((i2 & 3) == 3) {
                this.H = drawable;
            } else if ((i2 & 5) == 5) {
                this.I = drawable;
            } else {
                return;
            }
            d();
            invalidate();
        }
    }

    public void setDrawerTitle(int i2, CharSequence charSequence) {
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, getLayoutDirection());
        if (absoluteGravity == 3) {
            this.B = charSequence;
        } else if (absoluteGravity == 5) {
            this.C = charSequence;
        }
    }

    public void setScrimColor(int i2) {
        this.h = i2;
        invalidate();
    }

    public void setStatusBarBackground(int i2) {
        this.y = i2 != 0 ? getContext().getResources().getDrawable(i2) : null;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.y = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i2) {
        this.y = new ColorDrawable(i2);
        invalidate();
    }
}
