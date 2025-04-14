package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import com.tencent.qq.widget.R;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PageView extends ViewGroup {
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    /* access modifiers changed from: private */
    public static final int[] a = {16842931};
    private static final Comparator<ItemInfo> b = new Comparator<ItemInfo>() {
        public int compare(ItemInfo itemInfo, ItemInfo itemInfo2) {
            return itemInfo.b - itemInfo2.b;
        }
    };
    private static final Interpolator c = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private static final ViewPositionComparator d = new ViewPositionComparator();
    private int A = 1;
    private boolean B;
    private boolean C;
    private int D;
    private int E;
    private int F;
    private float G;
    private float H;
    private float I;
    private float J;
    private int K = -1;
    private VelocityTracker L;
    private int M;
    private int N;
    private int O;
    private int P;
    private boolean Q;
    private long R;
    private EdgeEffect S;
    private EdgeEffect T;
    private boolean U = true;
    private boolean V = false;
    private boolean W;
    private int aa;
    private List<OnPageChangeListener> ab;
    private OnPageChangeListener ac;
    private OnPageChangeListener ad;
    private OnAdapterChangeListener ae;
    private PageTransformer af;
    private Method ag;
    private int ah;
    private ArrayList<View> ai;
    private int aj = 0;
    private final Runnable ak = new Runnable() {
        public void run() {
            PageView.this.setScrollState(0);
            PageView.this.c();
        }
    };
    private boolean al = true;
    private final ArrayList<ItemInfo> e = new ArrayList<>();
    private final ItemInfo f = new ItemInfo();
    private final Rect g = new Rect();
    private int h;
    private BasePageAdapter i;
    private int j;
    private int k = -1;
    private Parcelable l = null;
    private ClassLoader m = null;
    private Scroller n;
    private PageObserver o;
    private int p;

    /* renamed from: q  reason: collision with root package name */
    private Drawable f5q;
    private int r;
    private int s;
    private float t = -3.4028235E38f;
    private float u = Float.MAX_VALUE;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    interface Decor {
    }

    static class ItemInfo {
        Object a;
        int b;
        boolean c;
        float d;
        float e;

        ItemInfo() {
        }
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        float a = 0.0f;
        boolean b;
        int c;
        int d;
        public int gravity;
        public boolean isDecor;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, PageView.a);
            this.gravity = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(-1, -1);
        }
    }

    interface OnAdapterChangeListener {
        void onAdapterChanged(BasePageAdapter basePageAdapter, BasePageAdapter basePageAdapter2);
    }

    public interface OnPageChangeListener {
        void onPageChange(View view, int i);

        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, int i2);

        void onPageSelected(int i);
    }

    private class PageObserver extends DataSetObserver {
        private PageObserver() {
        }

        public void onChanged() {
            PageView.this.b();
        }

        public void onInvalidated() {
            PageView.this.b();
        }
    }

    public interface PageTransformer {
        void transformPage(View view, float f);
    }

    private class PagerObserver extends DataSetObserver {
        private PagerObserver() {
        }

        public void onChanged() {
            PageView.this.b();
        }

        public void onInvalidated() {
            PageView.this.b();
        }
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;
        Parcelable b;
        ClassLoader c;

        SavedState(Parcel parcel) {
            super(parcel);
            ClassLoader classLoader = getClass().getClassLoader();
            this.a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPage.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeParcelable(this.b, i);
        }
    }

    public static class SimpleOnPageChangeListener implements OnPageChangeListener {
        public void onPageChange(View view, int i) {
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
        }
    }

    static class ViewPositionComparator implements Comparator<View> {
        ViewPositionComparator() {
        }

        public int compare(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            return layoutParams.isDecor != layoutParams2.isDecor ? layoutParams.isDecor ? 1 : -1 : layoutParams.c - layoutParams2.c;
        }
    }

    public PageView(Context context) {
        super(context);
        a();
    }

    public PageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private int a(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.O || Math.abs(i3) <= this.M) {
            i2 = (int) (((float) i2) + f2 + (i2 >= this.j ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        return this.e.size() > 0 ? Math.max(this.e.get(0).b, Math.min(i2, this.e.get(this.e.size() - 1).b)) : i2;
    }

    private Rect a(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    private void a(int i2, int i3, int i4, int i5) {
        if (i3 <= 0 || this.e.isEmpty()) {
            ItemInfo b2 = b(this.j);
            int min = (int) ((b2 != null ? Math.min(b2.e, this.u) : 0.0f) * ((float) ((i2 - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                a(false);
                scrollTo(min, getScrollY());
                return;
            }
            return;
        }
        int scrollX = (int) ((((float) getScrollX()) / ((float) (((i3 - getPaddingLeft()) - getPaddingRight()) + i5))) * ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)));
        scrollTo(scrollX, getScrollY());
        if (!this.n.isFinished()) {
            this.n.startScroll(scrollX, 0, (int) (b(this.j).e * ((float) i2)), 0, this.n.getDuration() - this.n.timePassed());
        }
    }

    private void a(int i2, boolean z2, int i3, boolean z3) {
        ItemInfo b2 = b(i2);
        int clientWidth = b2 != null ? (int) (((float) getClientWidth()) * Math.max(this.t, Math.min(b2.e, this.u))) : 0;
        if (z2) {
            a(clientWidth, 0, i3);
            if (z3) {
                d(i2);
                return;
            }
            return;
        }
        if (z3) {
            d(i2);
        }
        a(false);
        scrollTo(clientWidth, 0);
        c(clientWidth);
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.K) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.G = motionEvent.getX(i2);
            this.K = motionEvent.getPointerId(i2);
            if (this.L != null) {
                this.L.clear();
            }
        }
    }

    private void a(ItemInfo itemInfo, int i2, ItemInfo itemInfo2) {
        ItemInfo itemInfo3;
        ItemInfo itemInfo4;
        int count = this.i.getCount();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? ((float) this.p) / ((float) clientWidth) : 0.0f;
        if (itemInfo2 != null) {
            int i3 = itemInfo2.b;
            if (i3 < itemInfo.b) {
                float f3 = itemInfo2.e + itemInfo2.d + f2;
                int i4 = i3 + 1;
                int i5 = 0;
                while (i4 <= itemInfo.b && i5 < this.e.size()) {
                    while (true) {
                        itemInfo4 = this.e.get(i5);
                        if (i4 > itemInfo4.b && i5 < this.e.size() - 1) {
                            i5++;
                        }
                    }
                    while (i4 < itemInfo4.b) {
                        f3 += this.i.getPageWidth(i4) + f2;
                        i4++;
                    }
                    itemInfo4.e = f3;
                    f3 += itemInfo4.d + f2;
                    i4++;
                }
            } else if (i3 > itemInfo.b) {
                int size = this.e.size() - 1;
                float f4 = itemInfo2.e;
                while (true) {
                    i3--;
                    if (i3 < itemInfo.b || size < 0) {
                        break;
                    }
                    while (true) {
                        itemInfo3 = this.e.get(size);
                        if (i3 < itemInfo3.b && size > 0) {
                            size--;
                        }
                    }
                    while (i3 > itemInfo3.b) {
                        f4 -= this.i.getPageWidth(i3) + f2;
                        i3--;
                    }
                    f4 -= itemInfo3.d + f2;
                    itemInfo3.e = f4;
                }
            }
        }
        int size2 = this.e.size();
        float f5 = itemInfo.e;
        int i6 = itemInfo.b - 1;
        this.t = itemInfo.b == 0 ? itemInfo.e : -3.4028235E38f;
        int i7 = count - 1;
        this.u = itemInfo.b == i7 ? (itemInfo.e + itemInfo.d) - 1.0f : Float.MAX_VALUE;
        int i8 = i2 - 1;
        while (i8 >= 0) {
            ItemInfo itemInfo5 = this.e.get(i8);
            while (i6 > itemInfo5.b) {
                f5 -= this.i.getPageWidth(i6) + f2;
                i6--;
            }
            f5 -= itemInfo5.d + f2;
            itemInfo5.e = f5;
            if (itemInfo5.b == 0) {
                this.t = f5;
            }
            i8--;
            i6--;
        }
        float f6 = itemInfo.e + itemInfo.d + f2;
        int i9 = itemInfo.b + 1;
        int i10 = i2 + 1;
        while (i10 < size2) {
            ItemInfo itemInfo6 = this.e.get(i10);
            while (i9 < itemInfo6.b) {
                f6 += this.i.getPageWidth(i9) + f2;
                i9++;
            }
            if (itemInfo6.b == i7) {
                this.u = (itemInfo6.d + f6) - 1.0f;
            }
            itemInfo6.e = f6;
            f6 += itemInfo6.d + f2;
            i10++;
            i9++;
        }
        this.V = false;
    }

    private void a(boolean z2) {
        boolean z3 = this.aj == 2;
        if (z3) {
            setScrollingCacheEnabled(false);
            this.n.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.n.getCurrX();
            int currY = this.n.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
                if (currX != scrollX) {
                    c(currX);
                }
            }
        }
        this.z = false;
        boolean z4 = z3;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            ItemInfo itemInfo = this.e.get(i2);
            if (itemInfo.c) {
                itemInfo.c = false;
                z4 = true;
            }
        }
        if (!z4) {
            return;
        }
        if (z2) {
            postOnAnimation(this.ak);
        } else {
            this.ak.run();
        }
    }

    private boolean a(float f2, float f3) {
        if (f2 >= ((float) this.E) || f3 <= 0.0f) {
            return f2 > ((float) (getWidth() - this.E)) && f3 < 0.0f;
        }
        return true;
    }

    private void b(int i2, float f2, int i3) {
        if (this.ac != null) {
            this.ac.onPageScrolled(i2, f2, i3);
        }
        if (this.ab != null) {
            int size = this.ab.size();
            for (int i4 = 0; i4 < size; i4++) {
                OnPageChangeListener onPageChangeListener = this.ab.get(i4);
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrolled(i2, f2, i3);
                }
            }
        }
        if (this.ad != null) {
            this.ad.onPageScrolled(i2, f2, i3);
        }
    }

    private void b(boolean z2) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).setLayerType(z2 ? 2 : 0, (Paint) null);
        }
    }

    private boolean b(float f2) {
        boolean z2;
        float f3 = this.G - f2;
        this.G = f2;
        float scrollX = ((float) getScrollX()) + f3;
        float clientWidth = (float) getClientWidth();
        float f4 = this.t * clientWidth;
        float f5 = this.u * clientWidth;
        ItemInfo itemInfo = this.e.get(0);
        boolean z3 = true;
        ItemInfo itemInfo2 = this.e.get(this.e.size() - 1);
        if (itemInfo.b != 0) {
            f4 = itemInfo.e * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (itemInfo2.b != this.i.getCount() - 1) {
            f5 = itemInfo2.e * clientWidth;
            z3 = false;
        }
        if (scrollX < f4) {
            if (z2) {
                this.S.onPull(Math.abs(f4 - scrollX) / clientWidth);
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z3) {
                this.T.onPull(Math.abs(scrollX - f5) / clientWidth);
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.G += scrollX - ((float) i2);
        scrollTo(i2, getScrollY());
        c(i2);
        return false;
    }

    private void c(boolean z2) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z2);
        }
    }

    private boolean c(int i2) {
        if (this.e.size() == 0) {
            this.W = false;
            a(0, 0.0f, 0);
            if (this.W) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        ItemInfo j2 = j();
        int clientWidth = getClientWidth();
        int i3 = this.p + clientWidth;
        float f2 = (float) clientWidth;
        float f3 = ((float) this.p) / f2;
        int i4 = j2.b;
        float f4 = ((((float) i2) / f2) - j2.e) / (j2.d + f3);
        this.W = false;
        a(i4, f4, (int) (((float) i3) * f4));
        if (this.W) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private void d(int i2) {
        if (this.ac != null) {
            this.ac.onPageSelected(i2);
            this.ac.onPageChange(getChildAt(i2), i2);
        }
        if (this.ab != null) {
            int size = this.ab.size();
            for (int i3 = 0; i3 < size; i3++) {
                OnPageChangeListener onPageChangeListener = this.ab.get(i3);
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(i2);
                }
            }
        }
        if (this.ad != null) {
            this.ad.onPageSelected(i2);
        }
    }

    private void e(int i2) {
        if (this.ac != null) {
            this.ac.onPageScrollStateChanged(i2);
        }
        if (this.ab != null) {
            int size = this.ab.size();
            for (int i3 = 0; i3 < size; i3++) {
                OnPageChangeListener onPageChangeListener = this.ab.get(i3);
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrollStateChanged(i2);
                }
            }
        }
        if (this.ad != null) {
            this.ad.onPageScrollStateChanged(i2);
        }
    }

    private void g() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).isDecor) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void h() {
        if (this.ah != 0) {
            if (this.ai == null) {
                this.ai = new ArrayList<>();
            } else {
                this.ai.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.ai.add(getChildAt(i2));
            }
            Collections.sort(this.ai, d);
        }
    }

    private boolean i() {
        this.K = -1;
        k();
        this.S.onRelease();
        this.T.onRelease();
        return true;
    }

    private ItemInfo j() {
        int i2;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? ((float) getScrollX()) / ((float) clientWidth) : 0.0f;
        float f2 = clientWidth > 0 ? ((float) this.p) / ((float) clientWidth) : 0.0f;
        ItemInfo itemInfo = null;
        int i3 = 0;
        boolean z2 = true;
        int i4 = -1;
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (i3 < this.e.size()) {
            ItemInfo itemInfo2 = this.e.get(i3);
            if (!z2 && itemInfo2.b != (i2 = i4 + 1)) {
                itemInfo2 = this.f;
                itemInfo2.e = f3 + f4 + f2;
                itemInfo2.b = i2;
                itemInfo2.d = this.i.getPageWidth(itemInfo2.b);
                i3--;
            }
            f3 = itemInfo2.e;
            float f5 = itemInfo2.d + f3 + f2;
            if (!z2 && scrollX < f3) {
                return itemInfo;
            }
            if (scrollX < f5 || i3 == this.e.size() - 1) {
                return itemInfo2;
            }
            i4 = itemInfo2.b;
            f4 = itemInfo2.d;
            i3++;
            itemInfo = itemInfo2;
            z2 = false;
        }
        return itemInfo;
    }

    private void k() {
        this.B = false;
        this.C = false;
        if (this.L != null) {
            this.L.recycle();
            this.L = null;
        }
    }

    /* access modifiers changed from: private */
    public void setScrollState(int i2) {
        if (this.aj != i2) {
            this.aj = i2;
            if (this.af != null) {
                b(i2 != 0);
            }
            e(i2);
        }
    }

    private void setScrollingCacheEnabled(boolean z2) {
        if (this.y != z2) {
            this.y = z2;
        }
    }

    /* access modifiers changed from: package-private */
    public float a(float f2) {
        return (float) Math.sin((double) ((float) (((double) (f2 - 0.5f)) * 0.4712389167638204d)));
    }

    /* access modifiers changed from: package-private */
    public ItemInfo a(int i2, int i3) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.b = i2;
        itemInfo.a = this.i.instantiateItem((ViewGroup) this, i2);
        itemInfo.d = this.i.getPageWidth(i2);
        if (i3 < 0 || i3 >= this.e.size()) {
            this.e.add(itemInfo);
            return itemInfo;
        }
        this.e.add(i3, itemInfo);
        return itemInfo;
    }

    /* access modifiers changed from: package-private */
    public ItemInfo a(View view) {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            ItemInfo itemInfo = this.e.get(i2);
            if (this.i.isViewFromObject(view, itemInfo.a)) {
                return itemInfo;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.n = new Scroller(context, c);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.F = viewConfiguration.getScaledPagingTouchSlop();
        this.M = (int) (400.0f * f2);
        this.N = viewConfiguration.getScaledMaximumFlingVelocity();
        this.S = new EdgeEffect(context);
        this.T = new EdgeEffect(context);
        this.O = (int) (25.0f * f2);
        this.P = (int) (2.0f * f2);
        this.D = (int) (f2 * 16.0f);
        setAccessibilityDelegate(new View.AccessibilityDelegate());
        if (Build.VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006c, code lost:
        if (r9.b == r0.j) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cc, code lost:
        if (r11 >= 0) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00da, code lost:
        if (r11 >= 0) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e8, code lost:
        if (r11 >= 0) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f3, code lost:
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0148, code lost:
        if (r5 < r0.e.size()) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0153, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0166, code lost:
        if (r5 < r0.e.size()) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0178, code lost:
        if (r5 < r0.e.size()) goto L_0x014a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            int r2 = r0.j
            if (r2 == r1) goto L_0x001a
            int r2 = r0.j
            if (r2 >= r1) goto L_0x000f
            r2 = 66
            goto L_0x0011
        L_0x000f:
            r2 = 17
        L_0x0011:
            int r4 = r0.j
            android.widget.PageView$ItemInfo r4 = r0.b((int) r4)
            r0.j = r1
            goto L_0x001c
        L_0x001a:
            r2 = 2
            r4 = 0
        L_0x001c:
            android.widget.BasePageAdapter r1 = r0.i
            if (r1 != 0) goto L_0x0024
            r18.h()
            return
        L_0x0024:
            boolean r1 = r0.z
            if (r1 == 0) goto L_0x002c
            r18.h()
            return
        L_0x002c:
            android.os.IBinder r1 = r18.getWindowToken()
            if (r1 != 0) goto L_0x0033
            return
        L_0x0033:
            android.widget.BasePageAdapter r1 = r0.i
            r1.startUpdate((android.view.ViewGroup) r0)
            int r1 = r0.A
            int r5 = r0.j
            int r5 = r5 - r1
            r6 = 0
            int r5 = java.lang.Math.max(r6, r5)
            android.widget.BasePageAdapter r7 = r0.i
            int r7 = r7.getCount()
            int r8 = r7 + -1
            int r9 = r0.j
            int r9 = r9 + r1
            int r1 = java.lang.Math.min(r8, r9)
            r8 = 0
        L_0x0052:
            java.util.ArrayList<android.widget.PageView$ItemInfo> r9 = r0.e
            int r9 = r9.size()
            if (r8 >= r9) goto L_0x0072
            java.util.ArrayList<android.widget.PageView$ItemInfo> r9 = r0.e
            java.lang.Object r9 = r9.get(r8)
            android.widget.PageView$ItemInfo r9 = (android.widget.PageView.ItemInfo) r9
            int r10 = r9.b
            int r11 = r0.j
            if (r10 < r11) goto L_0x006f
            int r10 = r9.b
            int r11 = r0.j
            if (r10 != r11) goto L_0x0072
            goto L_0x0073
        L_0x006f:
            int r8 = r8 + 1
            goto L_0x0052
        L_0x0072:
            r9 = 0
        L_0x0073:
            if (r9 != 0) goto L_0x007d
            if (r7 <= 0) goto L_0x007d
            int r9 = r0.j
            android.widget.PageView$ItemInfo r9 = r0.a((int) r9, (int) r8)
        L_0x007d:
            if (r9 == 0) goto L_0x017f
            int r11 = r8 + -1
            if (r11 < 0) goto L_0x008c
            java.util.ArrayList<android.widget.PageView$ItemInfo> r12 = r0.e
            java.lang.Object r12 = r12.get(r11)
            android.widget.PageView$ItemInfo r12 = (android.widget.PageView.ItemInfo) r12
            goto L_0x008d
        L_0x008c:
            r12 = 0
        L_0x008d:
            int r13 = r18.getClientWidth()
            r14 = 1073741824(0x40000000, float:2.0)
            if (r13 > 0) goto L_0x0097
            r3 = 0
            goto L_0x00a3
        L_0x0097:
            float r15 = r9.d
            float r15 = r14 - r15
            int r3 = r18.getPaddingLeft()
            float r3 = (float) r3
            float r6 = (float) r13
            float r3 = r3 / r6
            float r3 = r3 + r15
        L_0x00a3:
            int r6 = r0.j
            int r6 = r6 + -1
            r15 = r8
            r8 = 0
        L_0x00a9:
            if (r6 < 0) goto L_0x00f8
            int r16 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r16 < 0) goto L_0x00cf
            if (r6 >= r5) goto L_0x00cf
            if (r12 != 0) goto L_0x00b4
            goto L_0x00f8
        L_0x00b4:
            int r10 = r12.b
            if (r6 != r10) goto L_0x00f5
            boolean r10 = r12.c
            if (r10 != 0) goto L_0x00f5
            java.util.ArrayList<android.widget.PageView$ItemInfo> r10 = r0.e
            r10.remove(r11)
            android.widget.BasePageAdapter r10 = r0.i
            java.lang.Object r12 = r12.a
            r10.destroyItem((android.view.ViewGroup) r0, (int) r6, (java.lang.Object) r12)
            int r11 = r11 + -1
            int r15 = r15 + -1
            if (r11 < 0) goto L_0x00f3
            goto L_0x00ea
        L_0x00cf:
            if (r12 == 0) goto L_0x00dd
            int r10 = r12.b
            if (r6 != r10) goto L_0x00dd
            float r10 = r12.d
            float r8 = r8 + r10
            int r11 = r11 + -1
            if (r11 < 0) goto L_0x00f3
            goto L_0x00ea
        L_0x00dd:
            int r10 = r11 + 1
            android.widget.PageView$ItemInfo r10 = r0.a((int) r6, (int) r10)
            float r10 = r10.d
            float r8 = r8 + r10
            int r15 = r15 + 1
            if (r11 < 0) goto L_0x00f3
        L_0x00ea:
            java.util.ArrayList<android.widget.PageView$ItemInfo> r10 = r0.e
            java.lang.Object r10 = r10.get(r11)
            android.widget.PageView$ItemInfo r10 = (android.widget.PageView.ItemInfo) r10
            goto L_0x00f4
        L_0x00f3:
            r10 = 0
        L_0x00f4:
            r12 = r10
        L_0x00f5:
            int r6 = r6 + -1
            goto L_0x00a9
        L_0x00f8:
            float r3 = r9.d
            int r5 = r15 + 1
            int r6 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r6 >= 0) goto L_0x017c
            java.util.ArrayList<android.widget.PageView$ItemInfo> r6 = r0.e
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0111
            java.util.ArrayList<android.widget.PageView$ItemInfo> r6 = r0.e
            java.lang.Object r6 = r6.get(r5)
            android.widget.PageView$ItemInfo r6 = (android.widget.PageView.ItemInfo) r6
            goto L_0x0112
        L_0x0111:
            r6 = 0
        L_0x0112:
            if (r13 > 0) goto L_0x0116
            r10 = 0
            goto L_0x011f
        L_0x0116:
            int r8 = r18.getPaddingRight()
            float r8 = (float) r8
            float r10 = (float) r13
            float r8 = r8 / r10
            float r10 = r8 + r14
        L_0x011f:
            int r8 = r0.j
        L_0x0121:
            int r8 = r8 + 1
            if (r8 >= r7) goto L_0x017c
            int r11 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r11 < 0) goto L_0x0155
            if (r8 <= r1) goto L_0x0155
            if (r6 != 0) goto L_0x012e
            goto L_0x017c
        L_0x012e:
            int r11 = r6.b
            if (r8 != r11) goto L_0x017b
            boolean r11 = r6.c
            if (r11 != 0) goto L_0x017b
            java.util.ArrayList<android.widget.PageView$ItemInfo> r11 = r0.e
            r11.remove(r5)
            android.widget.BasePageAdapter r11 = r0.i
            java.lang.Object r6 = r6.a
            r11.destroyItem((android.view.ViewGroup) r0, (int) r8, (java.lang.Object) r6)
            java.util.ArrayList<android.widget.PageView$ItemInfo> r6 = r0.e
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0153
        L_0x014a:
            java.util.ArrayList<android.widget.PageView$ItemInfo> r6 = r0.e
            java.lang.Object r6 = r6.get(r5)
            android.widget.PageView$ItemInfo r6 = (android.widget.PageView.ItemInfo) r6
            goto L_0x017b
        L_0x0153:
            r6 = 0
            goto L_0x017b
        L_0x0155:
            if (r6 == 0) goto L_0x0169
            int r11 = r6.b
            if (r8 != r11) goto L_0x0169
            float r6 = r6.d
            float r3 = r3 + r6
            int r5 = r5 + 1
            java.util.ArrayList<android.widget.PageView$ItemInfo> r6 = r0.e
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0153
            goto L_0x014a
        L_0x0169:
            android.widget.PageView$ItemInfo r6 = r0.a((int) r8, (int) r5)
            int r5 = r5 + 1
            float r6 = r6.d
            float r3 = r3 + r6
            java.util.ArrayList<android.widget.PageView$ItemInfo> r6 = r0.e
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0153
            goto L_0x014a
        L_0x017b:
            goto L_0x0121
        L_0x017c:
            r0.a((android.widget.PageView.ItemInfo) r9, (int) r15, (android.widget.PageView.ItemInfo) r4)
        L_0x017f:
            android.widget.BasePageAdapter r1 = r0.i
            int r3 = r0.j
            if (r9 == 0) goto L_0x0188
            java.lang.Object r4 = r9.a
            goto L_0x0189
        L_0x0188:
            r4 = 0
        L_0x0189:
            r1.setPrimaryItem((android.view.ViewGroup) r0, (int) r3, (java.lang.Object) r4)
            android.widget.BasePageAdapter r1 = r0.i
            r1.finishUpdate((android.view.ViewGroup) r0)
            int r1 = r18.getChildCount()
            r3 = 0
        L_0x0196:
            if (r3 >= r1) goto L_0x01c2
            android.view.View r4 = r0.getChildAt(r3)
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            android.widget.PageView$LayoutParams r5 = (android.widget.PageView.LayoutParams) r5
            r5.d = r3
            boolean r6 = r5.isDecor
            if (r6 != 0) goto L_0x01be
            float r6 = r5.a
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x01bf
            android.widget.PageView$ItemInfo r4 = r0.a((android.view.View) r4)
            if (r4 == 0) goto L_0x01bf
            float r6 = r4.d
            r5.a = r6
            int r4 = r4.b
            r5.c = r4
            goto L_0x01bf
        L_0x01be:
            r7 = 0
        L_0x01bf:
            int r3 = r3 + 1
            goto L_0x0196
        L_0x01c2:
            r18.h()
            boolean r1 = r18.hasFocus()
            if (r1 == 0) goto L_0x0200
            android.view.View r1 = r18.findFocus()
            if (r1 == 0) goto L_0x01d6
            android.widget.PageView$ItemInfo r3 = r0.b((android.view.View) r1)
            goto L_0x01d7
        L_0x01d6:
            r3 = 0
        L_0x01d7:
            if (r3 == 0) goto L_0x01df
            int r1 = r3.b
            int r3 = r0.j
            if (r1 == r3) goto L_0x0200
        L_0x01df:
            r1 = 0
        L_0x01e0:
            int r3 = r18.getChildCount()
            if (r1 >= r3) goto L_0x0200
            android.view.View r3 = r0.getChildAt(r1)
            android.widget.PageView$ItemInfo r4 = r0.a((android.view.View) r3)
            if (r4 == 0) goto L_0x01fd
            int r4 = r4.b
            int r5 = r0.j
            if (r4 != r5) goto L_0x01fd
            boolean r3 = r3.requestFocus(r2)
            if (r3 == 0) goto L_0x01fd
            return
        L_0x01fd:
            int r1 = r1 + 1
            goto L_0x01e0
        L_0x0200:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.PageView.a(int):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.aa
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L_0x006d
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r4
            r4 = r3
            r3 = 0
        L_0x001d:
            if (r3 >= r6) goto L_0x006d
            android.view.View r8 = r12.getChildAt(r3)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            android.widget.PageView$LayoutParams r9 = (android.widget.PageView.LayoutParams) r9
            boolean r10 = r9.isDecor
            if (r10 != 0) goto L_0x002e
            goto L_0x006a
        L_0x002e:
            int r9 = r9.gravity
            r9 = r9 & 7
            if (r9 == r2) goto L_0x004f
            r10 = 3
            if (r9 == r10) goto L_0x0049
            r10 = 5
            if (r9 == r10) goto L_0x003c
            r9 = r4
            goto L_0x005e
        L_0x003c:
            int r9 = r5 - r7
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r7 = r7 + r10
            goto L_0x005b
        L_0x0049:
            int r9 = r8.getWidth()
            int r9 = r9 + r4
            goto L_0x005e
        L_0x004f:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r4)
        L_0x005b:
            r11 = r9
            r9 = r4
            r4 = r11
        L_0x005e:
            int r4 = r4 + r0
            int r10 = r8.getLeft()
            int r4 = r4 - r10
            if (r4 == 0) goto L_0x0069
            r8.offsetLeftAndRight(r4)
        L_0x0069:
            r4 = r9
        L_0x006a:
            int r3 = r3 + 1
            goto L_0x001d
        L_0x006d:
            r12.b(r13, r14, r15)
            android.widget.PageView$PageTransformer r13 = r12.af
            if (r13 == 0) goto L_0x00a1
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L_0x007c:
            if (r1 >= r14) goto L_0x00a1
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            android.widget.PageView$LayoutParams r0 = (android.widget.PageView.LayoutParams) r0
            boolean r0 = r0.isDecor
            if (r0 == 0) goto L_0x008d
            goto L_0x009e
        L_0x008d:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            android.widget.PageView$PageTransformer r3 = r12.af
            r3.transformPage(r15, r0)
        L_0x009e:
            int r1 = r1 + 1
            goto L_0x007c
        L_0x00a1:
            r12.W = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.PageView.a(int, float, int):void");
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4) {
        int i5;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i6 = i2 - scrollX;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            a(false);
            c();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = (float) clientWidth;
        float f3 = (float) i8;
        float a2 = f3 + (a(Math.min(1.0f, (((float) Math.abs(i6)) * 1.0f) / f2)) * f3);
        int abs = Math.abs(i4);
        if (abs > 0) {
            i5 = Math.round(Math.abs(a2 / ((float) abs)) * 1000.0f) * 4;
        } else {
            i5 = (int) (((((float) Math.abs(i6)) / ((f2 * this.i.getPageWidth(this.j)) + ((float) this.p))) + 1.0f) * 100.0f);
        }
        this.n.startScroll(scrollX, scrollY, i6, i7, Math.min(i5, 600));
        postInvalidateOnAnimation();
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, boolean z2, boolean z3) {
        a(i2, z2, z3, 0);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, boolean z2, boolean z3, int i3) {
        if (this.i == null || this.i.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z3 || this.j != i2 || this.e.size() == 0) {
            boolean z4 = true;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.i.getCount()) {
                i2 = this.i.getCount() - 1;
            }
            int i4 = this.A;
            if (i2 > this.j + i4 || i2 < this.j - i4) {
                for (int i5 = 0; i5 < this.e.size(); i5++) {
                    this.e.get(i5).c = true;
                }
            }
            if (this.j == i2) {
                z4 = false;
            }
            if (this.U) {
                this.j = i2;
                if (z4) {
                    d(i2);
                }
                requestLayout();
                return;
            }
            a(i2);
            a(i2, z2, i3, z4);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(View view, boolean z2, int i2, int i3, int i4) {
        int i5;
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view2.getScrollX();
            int scrollY = view2.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom()) {
                    if (a(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        return z2 && view2.canScrollHorizontally(-i2);
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        ItemInfo a2;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.j) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i3 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.ab == null) {
            this.ab = new ArrayList();
        }
        this.ab.add(onPageChangeListener);
    }

    public void addTouchables(ArrayList<View> arrayList) {
        ItemInfo a2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.j) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        layoutParams2.isDecor |= view instanceof Decor;
        if (!this.x) {
            super.addView(view, i2, layoutParams);
        } else if (layoutParams2 == null || !layoutParams2.isDecor) {
            layoutParams2.b = true;
            addViewInLayout(view, i2, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add page decor view during layout");
        }
    }

    public boolean arrowScroll(int i2) {
        boolean e2;
        boolean z2;
        View findFocus = findFocus();
        boolean z3 = false;
        View view = null;
        if (findFocus != this) {
            if (findFocus != null) {
                ViewParent parent = findFocus.getParent();
                while (true) {
                    if (!(parent instanceof ViewGroup)) {
                        z2 = false;
                        break;
                    } else if (parent == this) {
                        z2 = true;
                        break;
                    } else {
                        parent = parent.getParent();
                    }
                }
                if (!z2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        sb.append(" => ");
                        sb.append(parent2.getClass().getSimpleName());
                    }
                    Log.e("PageView", "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i2);
        if (findNextFocus != null && findNextFocus != view) {
            if (i2 == 17) {
                int i3 = a(this.g, findNextFocus).left;
                int i4 = a(this.g, view).left;
                if (view != null && i3 >= i4) {
                    e2 = d();
                    z3 = e2;
                }
            } else if (i2 == 66) {
                int i5 = a(this.g, findNextFocus).left;
                int i6 = a(this.g, view).left;
                if (view != null && i5 <= i6) {
                    e2 = e();
                    z3 = e2;
                }
            }
            e2 = findNextFocus.requestFocus();
            z3 = e2;
        } else if (i2 == 17 || i2 == 1) {
            z3 = d();
        } else if (i2 == 66 || i2 == 2) {
            z3 = e();
        }
        if (z3) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return z3;
    }

    /* access modifiers changed from: package-private */
    public ItemInfo b(int i2) {
        for (int i3 = 0; i3 < this.e.size(); i3++) {
            ItemInfo itemInfo = this.e.get(i3);
            if (itemInfo.b == i2) {
                return itemInfo;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ItemInfo b(View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent == this) {
                return a(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        int count = this.i.getCount();
        this.h = count;
        boolean z2 = this.e.size() < (this.A * 2) + 1 && this.e.size() < count;
        int i2 = this.j;
        int i3 = 0;
        boolean z3 = false;
        while (i3 < this.e.size()) {
            ItemInfo itemInfo = this.e.get(i3);
            int itemPosition = this.i.getItemPosition(itemInfo.a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.e.remove(i3);
                    i3--;
                    if (!z3) {
                        this.i.startUpdate((ViewGroup) this);
                        z3 = true;
                    }
                    this.i.destroyItem((ViewGroup) this, itemInfo.b, itemInfo.a);
                    if (this.j == itemInfo.b) {
                        i2 = Math.max(0, Math.min(this.j, count - 1));
                    }
                } else if (itemInfo.b != itemPosition) {
                    if (itemInfo.b == this.j) {
                        i2 = itemPosition;
                    }
                    itemInfo.b = itemPosition;
                }
                z2 = true;
            }
            i3++;
        }
        if (z3) {
            this.i.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.e, b);
        if (z2) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
                if (!layoutParams.isDecor) {
                    layoutParams.a = 0.0f;
                }
            }
            a(i2, false, true);
            requestLayout();
        }
    }

    public boolean beginFakeDrag() {
        if (this.B) {
            return false;
        }
        this.Q = true;
        setScrollState(1);
        this.G = 0.0f;
        this.I = 0.0f;
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        } else {
            this.L.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.L.addMovement(obtain);
        obtain.recycle();
        this.R = uptimeMillis;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        a(this.j);
    }

    public boolean canScrollHorizontally(int i2) {
        if (this.i == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i2 < 0 ? scrollX > ((int) (((float) clientWidth) * this.t)) : i2 > 0 && scrollX < ((int) (((float) clientWidth) * this.u));
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public void clearOnPageChangeListeners() {
        if (this.ab != null) {
            this.ab.clear();
        }
    }

    public void computeScroll() {
        if (this.n.isFinished() || !this.n.computeScrollOffset()) {
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.n.getCurrX();
        int currY = this.n.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!c(currX)) {
                this.n.abortAnimation();
                scrollTo(0, currY);
            }
        }
        postInvalidateOnAnimation();
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        if (this.j <= 0) {
            return false;
        }
        setCurrentItem(this.j - 1, true);
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ItemInfo a2;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.j && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean z2 = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && this.i != null && this.i.getCount() > 1)) {
            if (!this.S.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.t * ((float) width));
                this.S.setSize(height, width);
                z2 = false | this.S.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.T.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.u + 1.0f)) * ((float) width2));
                this.T.setSize(height2, width2);
                z2 |= this.T.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.S.finish();
            this.T.finish();
        }
        if (z2) {
            postInvalidateOnAnimation();
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f5q;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        if (this.i == null || this.j >= this.i.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.j + 1, true);
        return true;
    }

    public void endFakeDrag() {
        if (!this.Q) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        VelocityTracker velocityTracker = this.L;
        velocityTracker.computeCurrentVelocity(1000, (float) this.N);
        int xVelocity = (int) velocityTracker.getXVelocity(this.K);
        this.z = true;
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        ItemInfo j2 = j();
        a(a(j2.b, ((((float) scrollX) / ((float) clientWidth)) - j2.e) / j2.d, xVelocity, (int) (this.G - this.I)), true, true, xVelocity);
        k();
        this.Q = false;
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int i2;
        if (keyEvent.getAction() != 0) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 61) {
            switch (keyCode) {
                case 21:
                    i2 = 17;
                    break;
                case 22:
                    i2 = 66;
                    break;
                default:
                    return false;
            }
        } else if (Build.VERSION.SDK_INT < 11) {
            return false;
        } else {
            if (keyEvent.hasNoModifiers()) {
                i2 = 2;
            } else if (keyEvent.hasModifiers(1)) {
                return arrowScroll(1);
            } else {
                return false;
            }
        }
        return arrowScroll(i2);
    }

    public void fakeDragBy(float f2) {
        if (!this.Q) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        this.G += f2;
        float scrollX = ((float) getScrollX()) - f2;
        float clientWidth = (float) getClientWidth();
        float f3 = this.t * clientWidth;
        float f4 = this.u * clientWidth;
        ItemInfo itemInfo = this.e.get(0);
        ItemInfo itemInfo2 = this.e.get(this.e.size() - 1);
        if (itemInfo.b != 0) {
            f3 = itemInfo.e * clientWidth;
        }
        if (itemInfo2.b != this.i.getCount() - 1) {
            f4 = itemInfo2.e * clientWidth;
        }
        if (scrollX < f3) {
            scrollX = f3;
        } else if (scrollX > f4) {
            scrollX = f4;
        }
        int i2 = (int) scrollX;
        this.G += scrollX - ((float) i2);
        scrollTo(i2, getScrollY());
        c(i2);
        MotionEvent obtain = MotionEvent.obtain(this.R, SystemClock.uptimeMillis(), 2, this.G, 0.0f, 0);
        this.L.addMovement(obtain);
        obtain.recycle();
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public BasePageAdapter getAdapter() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        if (this.ah == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((LayoutParams) this.ai.get(i3).getLayoutParams()).d;
    }

    public int getCurrentItem() {
        return this.j;
    }

    public int getOffscreenPageLimit() {
        return this.A;
    }

    public int getPageMargin() {
        return this.p;
    }

    public boolean isFakeDragging() {
        return this.Q;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.U = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.ak);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.p > 0 && this.f5q != null && this.e.size() > 0 && this.i != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f4 = (float) width;
            float f5 = ((float) this.p) / f4;
            int i2 = 0;
            ItemInfo itemInfo = this.e.get(0);
            float f6 = itemInfo.e;
            int size = this.e.size();
            int i3 = itemInfo.b;
            int i4 = this.e.get(size - 1).b;
            while (i3 < i4) {
                while (i3 > itemInfo.b && i2 < size) {
                    i2++;
                    itemInfo = this.e.get(i2);
                }
                if (i3 == itemInfo.b) {
                    f2 = (itemInfo.e + itemInfo.d) * f4;
                    f6 = itemInfo.e + itemInfo.d + f5;
                } else {
                    float pageWidth = this.i.getPageWidth(i3);
                    f2 = (f6 + pageWidth) * f4;
                    f6 += pageWidth + f5;
                }
                if (((float) this.p) + f2 > ((float) scrollX)) {
                    f3 = f5;
                    this.f5q.setBounds((int) f2, this.r, (int) (((float) this.p) + f2 + 0.5f), this.s);
                    this.f5q.draw(canvas);
                } else {
                    Canvas canvas2 = canvas;
                    f3 = f5;
                }
                if (f2 <= ((float) (scrollX + width))) {
                    i3++;
                    f5 = f3;
                } else {
                    return;
                }
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        if (!this.al) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            i();
            return false;
        }
        if (action != 0) {
            if (this.B) {
                return true;
            }
            if (this.C) {
                return false;
            }
        }
        if (action == 0) {
            float x2 = motionEvent.getX();
            this.I = x2;
            this.G = x2;
            float y2 = motionEvent.getY();
            this.J = y2;
            this.H = y2;
            this.K = motionEvent2.getPointerId(0);
            this.C = false;
            this.n.computeScrollOffset();
            if (this.aj != 2 || Math.abs(this.n.getFinalX() - this.n.getCurrX()) <= this.P) {
                a(false);
                this.B = false;
            } else {
                this.n.abortAnimation();
                this.z = false;
                c();
                this.B = true;
                c(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.K;
            if (i2 != -1) {
                int findPointerIndex = motionEvent2.findPointerIndex(i2);
                float x3 = motionEvent2.getX(findPointerIndex);
                float f2 = x3 - this.G;
                float abs = Math.abs(f2);
                float y3 = motionEvent2.getY(findPointerIndex);
                float abs2 = Math.abs(y3 - this.J);
                if (f2 != 0.0f && !a(this.G, f2)) {
                    if (a(this, false, (int) f2, (int) x3, (int) y3)) {
                        this.G = x3;
                        this.H = y3;
                        this.C = true;
                        return false;
                    }
                }
                if (abs > ((float) this.F) && abs * 0.5f > abs2) {
                    this.B = true;
                    c(true);
                    setScrollState(1);
                    this.G = f2 > 0.0f ? this.I + ((float) this.F) : this.I - ((float) this.F);
                    this.H = y3;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > ((float) this.F)) {
                    this.C = true;
                }
                if (this.B && b(x3)) {
                    postInvalidateOnAnimation();
                }
            }
        } else if (action == 6) {
            a(motionEvent);
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(motionEvent2);
        return this.B;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        boolean z3;
        ItemInfo a2;
        int i6;
        int i7;
        int childCount = getChildCount();
        int i8 = i4 - i2;
        int i9 = i5 - i3;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i10 = paddingBottom;
        int i11 = 0;
        int i12 = paddingTop;
        int i13 = paddingLeft;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isDecor) {
                    int i15 = layoutParams.gravity & 7;
                    int i16 = layoutParams.gravity & R.styleable.AppCompatTheme_spinnerStyle;
                    if (i15 == 1) {
                        i6 = Math.max((i8 - childAt.getMeasuredWidth()) / 2, i13);
                    } else if (i15 == 3) {
                        i6 = i13;
                        i13 = childAt.getMeasuredWidth() + i13;
                    } else if (i15 != 5) {
                        i6 = i13;
                    } else {
                        i6 = (i8 - paddingRight) - childAt.getMeasuredWidth();
                        paddingRight += childAt.getMeasuredWidth();
                    }
                    if (i16 == 16) {
                        i7 = Math.max((i9 - childAt.getMeasuredHeight()) / 2, i12);
                    } else if (i16 == 48) {
                        i7 = i12;
                        i12 = childAt.getMeasuredHeight() + i12;
                    } else if (i16 != 80) {
                        i7 = i12;
                    } else {
                        i7 = (i9 - i10) - childAt.getMeasuredHeight();
                        i10 += childAt.getMeasuredHeight();
                    }
                    int i17 = i6 + scrollX;
                    childAt.layout(i17, i7, childAt.getMeasuredWidth() + i17, i7 + childAt.getMeasuredHeight());
                    i11++;
                }
            }
        }
        int i18 = (i8 - i13) - paddingRight;
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt2 = getChildAt(i19);
            if (childAt2.getVisibility() != 8) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams2.isDecor && (a2 = a(childAt2)) != null) {
                    float f2 = (float) i18;
                    int i20 = ((int) (a2.e * f2)) + i13;
                    if (layoutParams2.b) {
                        layoutParams2.b = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f2 * layoutParams2.a), 1073741824), View.MeasureSpec.makeMeasureSpec((i9 - i12) - i10, 1073741824));
                    }
                    childAt2.layout(i20, i12, childAt2.getMeasuredWidth() + i20, childAt2.getMeasuredHeight() + i12);
                }
            }
        }
        this.r = i12;
        this.s = i9 - i10;
        this.aa = i11;
        if (this.U) {
            z3 = false;
            a(this.j, false, 0, true);
        } else {
            z3 = false;
        }
        this.U = z3;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r18, int r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = 0
            r2 = r18
            int r2 = getDefaultSize(r1, r2)
            r3 = r19
            int r3 = getDefaultSize(r1, r3)
            r0.setMeasuredDimension(r2, r3)
            int r2 = r17.getMeasuredWidth()
            int r3 = r2 / 10
            int r4 = r0.D
            int r3 = java.lang.Math.min(r3, r4)
            r0.E = r3
            int r3 = r17.getPaddingLeft()
            int r2 = r2 - r3
            int r3 = r17.getPaddingRight()
            int r2 = r2 - r3
            int r3 = r17.getMeasuredHeight()
            int r4 = r17.getPaddingTop()
            int r3 = r3 - r4
            int r4 = r17.getPaddingBottom()
            int r3 = r3 - r4
            int r4 = r17.getChildCount()
            r5 = r3
            r3 = r2
            r2 = 0
        L_0x003f:
            r6 = 8
            r7 = 1
            r8 = 1073741824(0x40000000, float:2.0)
            if (r2 >= r4) goto L_0x00c6
            android.view.View r9 = r0.getChildAt(r2)
            int r10 = r9.getVisibility()
            if (r10 == r6) goto L_0x00c1
            android.view.ViewGroup$LayoutParams r6 = r9.getLayoutParams()
            android.widget.PageView$LayoutParams r6 = (android.widget.PageView.LayoutParams) r6
            if (r6 == 0) goto L_0x00c1
            boolean r10 = r6.isDecor
            if (r10 == 0) goto L_0x00c1
            int r10 = r6.gravity
            r10 = r10 & 7
            int r11 = r6.gravity
            r11 = r11 & 112(0x70, float:1.57E-43)
            r12 = 48
            if (r11 == r12) goto L_0x006f
            r12 = 80
            if (r11 != r12) goto L_0x006d
            goto L_0x006f
        L_0x006d:
            r11 = 0
            goto L_0x0070
        L_0x006f:
            r11 = 1
        L_0x0070:
            r12 = 3
            if (r10 == r12) goto L_0x0078
            r12 = 5
            if (r10 != r12) goto L_0x0077
            goto L_0x0078
        L_0x0077:
            r7 = 0
        L_0x0078:
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r11 == 0) goto L_0x0081
            r10 = 1073741824(0x40000000, float:2.0)
        L_0x007e:
            r12 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0085
        L_0x0081:
            if (r7 == 0) goto L_0x007e
            r12 = 1073741824(0x40000000, float:2.0)
        L_0x0085:
            int r13 = r6.width
            r14 = -1
            r15 = -2
            if (r13 == r15) goto L_0x0097
            int r10 = r6.width
            if (r10 == r14) goto L_0x0093
            int r10 = r6.width
            r13 = r10
            goto L_0x0094
        L_0x0093:
            r13 = r3
        L_0x0094:
            r10 = 1073741824(0x40000000, float:2.0)
            goto L_0x0098
        L_0x0097:
            r13 = r3
        L_0x0098:
            int r1 = r6.height
            if (r1 == r15) goto L_0x00a5
            int r1 = r6.height
            if (r1 == r14) goto L_0x00a3
            int r1 = r6.height
            goto L_0x00a7
        L_0x00a3:
            r1 = r5
            goto L_0x00a7
        L_0x00a5:
            r1 = r5
            r8 = r12
        L_0x00a7:
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r10)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r8)
            r9.measure(r6, r1)
            if (r11 == 0) goto L_0x00ba
            int r1 = r9.getMeasuredHeight()
            int r5 = r5 - r1
            goto L_0x00c1
        L_0x00ba:
            if (r7 == 0) goto L_0x00c1
            int r1 = r9.getMeasuredWidth()
            int r3 = r3 - r1
        L_0x00c1:
            int r2 = r2 + 1
            r1 = 0
            goto L_0x003f
        L_0x00c6:
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r8)
            r0.v = r1
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r8)
            r0.w = r1
            r0.x = r7
            r17.c()
            r1 = 0
            r0.x = r1
            int r2 = r17.getChildCount()
        L_0x00de:
            if (r1 >= r2) goto L_0x0108
            android.view.View r4 = r0.getChildAt(r1)
            int r5 = r4.getVisibility()
            if (r5 == r6) goto L_0x0105
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            android.widget.PageView$LayoutParams r5 = (android.widget.PageView.LayoutParams) r5
            if (r5 == 0) goto L_0x00f6
            boolean r7 = r5.isDecor
            if (r7 != 0) goto L_0x0105
        L_0x00f6:
            float r7 = (float) r3
            float r5 = r5.a
            float r7 = r7 * r5
            int r5 = (int) r7
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r8)
            int r7 = r0.w
            r4.measure(r5, r7)
        L_0x0105:
            int r1 = r1 + 1
            goto L_0x00de
        L_0x0108:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.PageView.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        ItemInfo a2;
        int childCount = getChildCount();
        int i5 = -1;
        if ((i2 & 2) != 0) {
            i5 = childCount;
            i4 = 0;
            i3 = 1;
        } else {
            i4 = childCount - 1;
            i3 = -1;
        }
        while (i4 != i5) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.j && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i4 += i3;
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (this.i != null) {
            this.i.restoreState(savedState.b, savedState.c);
            a(savedState.a, false, true);
            return;
        }
        this.k = savedState.a;
        this.l = savedState.b;
        this.m = savedState.c;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.j;
        if (this.i != null) {
            savedState.b = this.i.saveState();
        }
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            a(i2, i4, this.p, this.p);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x011d, code lost:
        r2 = i();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            boolean r0 = r7.Q
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r8.getAction()
            r2 = 0
            if (r0 != 0) goto L_0x0014
            int r0 = r8.getEdgeFlags()
            if (r0 == 0) goto L_0x0014
            return r2
        L_0x0014:
            android.widget.BasePageAdapter r0 = r7.i
            if (r0 == 0) goto L_0x0148
            android.widget.BasePageAdapter r0 = r7.i
            int r0 = r0.getCount()
            if (r0 != 0) goto L_0x0021
            return r2
        L_0x0021:
            android.view.VelocityTracker r0 = r7.L
            if (r0 != 0) goto L_0x002b
            android.view.VelocityTracker r0 = android.view.VelocityTracker.obtain()
            r7.L = r0
        L_0x002b:
            android.view.VelocityTracker r0 = r7.L
            r0.addMovement(r8)
            int r0 = r8.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            switch(r0) {
                case 0: goto L_0x0122;
                case 1: goto L_0x00da;
                case 2: goto L_0x0067;
                case 3: goto L_0x005c;
                case 4: goto L_0x0039;
                case 5: goto L_0x004c;
                case 6: goto L_0x003b;
                default: goto L_0x0039;
            }
        L_0x0039:
            goto L_0x0142
        L_0x003b:
            r7.a((android.view.MotionEvent) r8)
            int r0 = r7.K
            int r0 = r8.findPointerIndex(r0)
            float r8 = r8.getX(r0)
            r7.G = r8
            goto L_0x0142
        L_0x004c:
            int r0 = r8.getActionIndex()
            float r3 = r8.getX(r0)
            r7.G = r3
            int r8 = r8.getPointerId(r0)
            goto L_0x0140
        L_0x005c:
            boolean r8 = r7.B
            if (r8 == 0) goto L_0x0142
            int r8 = r7.j
            r7.a((int) r8, (boolean) r1, (int) r2, (boolean) r2)
            goto L_0x011d
        L_0x0067:
            boolean r0 = r7.B
            if (r0 != 0) goto L_0x00c6
            int r0 = r7.K
            int r0 = r8.findPointerIndex(r0)
            r3 = -1
            if (r0 != r3) goto L_0x0076
            goto L_0x011d
        L_0x0076:
            float r3 = r8.getX(r0)
            float r4 = r7.G
            float r4 = r3 - r4
            float r4 = java.lang.Math.abs(r4)
            float r0 = r8.getY(r0)
            float r5 = r7.H
            float r5 = r0 - r5
            float r5 = java.lang.Math.abs(r5)
            int r6 = r7.F
            float r6 = (float) r6
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x00c6
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x00c6
            r7.B = r1
            r7.c((boolean) r1)
            float r4 = r7.I
            float r3 = r3 - r4
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x00ad
            float r3 = r7.I
            int r4 = r7.F
            float r4 = (float) r4
            float r3 = r3 + r4
            goto L_0x00b3
        L_0x00ad:
            float r3 = r7.I
            int r4 = r7.F
            float r4 = (float) r4
            float r3 = r3 - r4
        L_0x00b3:
            r7.G = r3
            r7.H = r0
            r7.setScrollState(r1)
            r7.setScrollingCacheEnabled(r1)
            android.view.ViewParent r0 = r7.getParent()
            if (r0 == 0) goto L_0x00c6
            r0.requestDisallowInterceptTouchEvent(r1)
        L_0x00c6:
            boolean r0 = r7.B
            if (r0 == 0) goto L_0x0142
            int r0 = r7.K
            int r0 = r8.findPointerIndex(r0)
            float r8 = r8.getX(r0)
            boolean r8 = r7.b((float) r8)
            r2 = r2 | r8
            goto L_0x0142
        L_0x00da:
            boolean r0 = r7.B
            if (r0 == 0) goto L_0x0142
            android.view.VelocityTracker r0 = r7.L
            r2 = 1000(0x3e8, float:1.401E-42)
            int r3 = r7.N
            float r3 = (float) r3
            r0.computeCurrentVelocity(r2, r3)
            int r2 = r7.K
            float r0 = r0.getXVelocity(r2)
            int r0 = (int) r0
            r7.z = r1
            int r2 = r7.getClientWidth()
            int r3 = r7.getScrollX()
            android.widget.PageView$ItemInfo r4 = r7.j()
            int r5 = r4.b
            float r3 = (float) r3
            float r2 = (float) r2
            float r3 = r3 / r2
            float r2 = r4.e
            float r3 = r3 - r2
            float r2 = r4.d
            float r3 = r3 / r2
            int r2 = r7.K
            int r2 = r8.findPointerIndex(r2)
            float r8 = r8.getX(r2)
            float r2 = r7.I
            float r8 = r8 - r2
            int r8 = (int) r8
            int r8 = r7.a((int) r5, (float) r3, (int) r0, (int) r8)
            r7.a((int) r8, (boolean) r1, (boolean) r1, (int) r0)
        L_0x011d:
            boolean r2 = r7.i()
            goto L_0x0142
        L_0x0122:
            android.widget.Scroller r0 = r7.n
            r0.abortAnimation()
            r7.z = r2
            r7.c()
            float r0 = r8.getX()
            r7.I = r0
            r7.G = r0
            float r0 = r8.getY()
            r7.J = r0
            r7.H = r0
            int r8 = r8.getPointerId(r2)
        L_0x0140:
            r7.K = r8
        L_0x0142:
            if (r2 == 0) goto L_0x0147
            r7.postInvalidateOnAnimation()
        L_0x0147:
            return r1
        L_0x0148:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.PageView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void removeOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.ab != null) {
            this.ab.remove(onPageChangeListener);
        }
    }

    public void removeView(View view) {
        if (this.x) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(BasePageAdapter basePageAdapter) {
        if (this.i != null) {
            this.i.unregisterDataSetObserver(this.o);
            this.i.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                ItemInfo itemInfo = this.e.get(i2);
                this.i.destroyItem((ViewGroup) this, itemInfo.b, itemInfo.a);
            }
            this.i.finishUpdate((ViewGroup) this);
            this.e.clear();
            g();
            this.j = 0;
            scrollTo(0, 0);
        }
        BasePageAdapter basePageAdapter2 = this.i;
        this.i = basePageAdapter;
        this.h = 0;
        if (this.i != null) {
            if (this.o == null) {
                this.o = new PageObserver();
            }
            this.i.registerDataSetObserver(this.o);
            this.z = false;
            boolean z2 = this.U;
            this.U = true;
            this.h = this.i.getCount();
            if (this.k >= 0) {
                this.i.restoreState(this.l, this.m);
                a(this.k, false, true);
                this.k = -1;
                this.l = null;
                this.m = null;
            } else if (!z2) {
                c();
            } else {
                requestLayout();
            }
        }
        if (this.ae != null && basePageAdapter2 != basePageAdapter) {
            this.ae.onAdapterChanged(basePageAdapter2, basePageAdapter);
        }
    }

    /* access modifiers changed from: package-private */
    public void setChildrenDrawingOrderEnabledCompat(boolean z2) {
        if (Build.VERSION.SDK_INT >= 7) {
            if (this.ag == null) {
                Class<ViewGroup> cls = ViewGroup.class;
                try {
                    this.ag = cls.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (NoSuchMethodException e2) {
                    Log.e("PageView", "Can't find setChildrenDrawingOrderEnabled", e2);
                }
            }
            try {
                this.ag.invoke(this, new Object[]{Boolean.valueOf(z2)});
            } catch (Exception e3) {
                Log.e("PageView", "Error changing children drawing order", e3);
            }
        }
    }

    public void setCurrentItem(int i2) {
        this.z = false;
        a(i2, !this.U, false);
    }

    public void setCurrentItem(int i2, boolean z2) {
        this.z = false;
        a(i2, z2, false);
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("PageView", "Requested offscreen page limit " + i2 + " too small; defaulting to " + 1);
            i2 = 1;
        }
        if (i2 != this.A) {
            this.A = i2;
            c();
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        this.ae = onAdapterChangeListener;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.ac = onPageChangeListener;
    }

    public void setPageMargin(int i2) {
        int i3 = this.p;
        this.p = i2;
        int width = getWidth();
        a(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i2));
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f5q = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageTransformer(boolean z2, PageTransformer pageTransformer) {
        if (Build.VERSION.SDK_INT >= 11) {
            int i2 = 1;
            boolean z3 = pageTransformer != null;
            boolean z4 = z3 != (this.af != null);
            this.af = pageTransformer;
            setChildrenDrawingOrderEnabledCompat(z3);
            if (z3) {
                if (z2) {
                    i2 = 2;
                }
                this.ah = i2;
            } else {
                this.ah = 0;
            }
            if (z4) {
                c();
            }
        }
    }

    public void setScrollEnabled(boolean z2) {
        this.al = z2;
    }

    public void setTouchEnabled(boolean z2) {
        this.al = z2;
    }

    public void showPage(int i2) {
        setCurrentItem(i2, true);
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f5q;
    }
}
