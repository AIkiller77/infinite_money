package android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.gestures.OnGestureListener;
import android.widget.gestures.VersionedGestureDetector;
import android.widget.log.LogManager;
import android.widget.log.Logger;
import android.widget.scrollerproxy.ScrollerProxy;
import java.lang.ref.WeakReference;

public class PhotoViewAttacher implements IPhotoView, View.OnTouchListener, OnGestureListener, ViewTreeObserver.OnGlobalLayoutListener {
    /* access modifiers changed from: private */
    public static final boolean DEBUG = Log.isLoggable(LOG_TAG, 3);
    static final int EDGE_BOTH = 2;
    static final int EDGE_LEFT = 0;
    static final int EDGE_NONE = -1;
    static final int EDGE_RIGHT = 1;
    /* access modifiers changed from: private */
    public static final String LOG_TAG = "PhotoViewAttacher";
    static int SINGLE_TOUCH = 1;
    int ZOOM_DURATION;
    private boolean mAllowParentInterceptOnEdge;
    private final Matrix mBaseMatrix;
    private float mBaseRotation;
    private boolean mBlockParentIntercept;
    private FlingRunnable mCurrentFlingRunnable;
    private final RectF mDisplayRect;
    private final Matrix mDrawMatrix;
    private GestureDetector mGestureDetector;
    private WeakReference<ImageView> mImageView;
    /* access modifiers changed from: private */
    public Interpolator mInterpolator;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mIvTop;
    /* access modifiers changed from: private */
    public View.OnLongClickListener mLongClickListener;
    private OnMatrixChangedListener mMatrixChangeListener;
    private final float[] mMatrixValues;
    private float mMaxScale;
    private float mMidScale;
    private float mMinScale;
    private OnPhotoTapListener mPhotoTapListener;
    private OnScaleChangeListener mScaleChangeListener;
    private android.widget.gestures.GestureDetector mScaleDragDetector;
    private ImageView.ScaleType mScaleType;
    private int mScrollEdge;
    /* access modifiers changed from: private */
    public OnSingleFlingListener mSingleFlingListener;
    /* access modifiers changed from: private */
    public final Matrix mSuppMatrix;
    private OnViewTapListener mViewTapListener;
    private boolean mZoomEnabled;

    private class AnimatedZoomRunnable implements Runnable {
        private final float mFocalX;
        private final float mFocalY;
        private final long mStartTime = System.currentTimeMillis();
        private final float mZoomEnd;
        private final float mZoomStart;
        private final PhotoViewAttacher this$0;

        public AnimatedZoomRunnable(PhotoViewAttacher photoViewAttacher, float f, float f2, float f3, float f4) {
            this.this$0 = photoViewAttacher;
            this.mFocalX = f3;
            this.mFocalY = f4;
            this.mZoomStart = f;
            this.mZoomEnd = f2;
        }

        static PhotoViewAttacher access$0(AnimatedZoomRunnable animatedZoomRunnable) {
            return animatedZoomRunnable.this$0;
        }

        private float interpolate() {
            return this.this$0.mInterpolator.getInterpolation(Math.min(1.0f, (1.0f * ((float) (System.currentTimeMillis() - this.mStartTime))) / ((float) this.this$0.ZOOM_DURATION)));
        }

        @Override
        public void run() {
            ImageView imageView = this.this$0.getImageView();
            if (imageView != null) {
                float interpolate = interpolate();
                this.this$0.onScale((this.mZoomStart + (interpolate * (this.mZoomEnd - this.mZoomStart))) / this.this$0.getScale(), this.mFocalX, this.mFocalY);
                if (interpolate < 1.0f) {
                    Compat.postOnAnimation(imageView, this);
                }
            }
        }
    }

    private class FlingRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final ScrollerProxy mScroller;
        private final PhotoViewAttacher this$0;

        public FlingRunnable(PhotoViewAttacher photoViewAttacher, Context context) {
            this.this$0 = photoViewAttacher;
            this.mScroller = ScrollerProxy.getScroller(context);
        }

        static PhotoViewAttacher access$0(FlingRunnable flingRunnable) {
            return flingRunnable.this$0;
        }

        public void cancelFling() {
            if (PhotoViewAttacher.DEBUG) {
                int d = LogManager.getLogger().d(PhotoViewAttacher.LOG_TAG, "Cancel Fling");
            }
            this.mScroller.forceFinished(true);
        }

        public void fling(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            StringBuffer stringBuffer;
            StringBuffer stringBuffer2;
            StringBuffer stringBuffer3;
            StringBuffer stringBuffer4;
            StringBuffer stringBuffer5;
            StringBuffer stringBuffer6;
            StringBuffer stringBuffer7;
            int i9 = i;
            int i10 = i2;
            int i11 = i3;
            int i12 = i4;
            RectF displayRect = this.this$0.getDisplayRect();
            if (displayRect != null) {
                int round = Math.round(-displayRect.left);
                if (((float) i9) < displayRect.width()) {
                    i6 = 0;
                    i5 = Math.round(displayRect.width() - ((float) i9));
                } else {
                    int i13 = round;
                    i5 = i13;
                    i6 = i13;
                }
                int round2 = Math.round(-displayRect.top);
                if (((float) i10) < displayRect.height()) {
                    i8 = 0;
                    i7 = Math.round(displayRect.height() - ((float) i10));
                } else {
                    int i14 = round2;
                    i7 = i14;
                    i8 = i14;
                }
                this.mCurrentX = round;
                this.mCurrentY = round2;
                if (PhotoViewAttacher.DEBUG) {
                    Logger logger = LogManager.getLogger();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    int d = logger.d(PhotoViewAttacher.LOG_TAG, stringBuffer.append(stringBuffer2.append(stringBuffer3.append(stringBuffer4.append(stringBuffer5.append(stringBuffer6.append(stringBuffer7.append("fling. StartX:").append(round).toString()).append(" StartY:").toString()).append(round2).toString()).append(" MaxX:").toString()).append(i5).toString()).append(" MaxY:").toString()).append(i7).toString());
                }
                if (round != i5 || round2 != i7) {
                    this.mScroller.fling(round, round2, i11, i12, i6, i5, i8, i7, 0, 0);
                }
            }
        }

        @Override
        public void run() {
            ImageView imageView;
            StringBuffer stringBuffer;
            StringBuffer stringBuffer2;
            StringBuffer stringBuffer3;
            StringBuffer stringBuffer4;
            StringBuffer stringBuffer5;
            StringBuffer stringBuffer6;
            StringBuffer stringBuffer7;
            if (!this.mScroller.isFinished() && (imageView = this.this$0.getImageView()) != null && this.mScroller.computeScrollOffset()) {
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (PhotoViewAttacher.DEBUG) {
                    Logger logger = LogManager.getLogger();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    new StringBuffer();
                    int d = logger.d(PhotoViewAttacher.LOG_TAG, stringBuffer.append(stringBuffer2.append(stringBuffer3.append(stringBuffer4.append(stringBuffer5.append(stringBuffer6.append(stringBuffer7.append("fling run(). CurrentX:").append(this.mCurrentX).toString()).append(" CurrentY:").toString()).append(this.mCurrentY).toString()).append(" NewX:").toString()).append(currX).toString()).append(" NewY:").toString()).append(currY).toString());
                }
                boolean postTranslate = this.this$0.mSuppMatrix.postTranslate((float) (this.mCurrentX - currX), (float) (this.mCurrentY - currY));
                this.this$0.setImageViewMatrix(this.this$0.getDrawMatrix());
                this.mCurrentX = currX;
                this.mCurrentY = currY;
                Compat.postOnAnimation(imageView, this);
            }
        }
    }

    public interface OnMatrixChangedListener {
        void onMatrixChanged(RectF rectF);
    }

    public interface OnPhotoTapListener {
        void onOutsidePhotoTap();

        void onPhotoTap(View view, float f, float f2);
    }

    public interface OnScaleChangeListener {
        void onScaleChange(float f, float f2, float f3);
    }

    public interface OnSingleFlingListener {
        boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);
    }

    public interface OnViewTapListener {
        void onViewTap(View view, float f, float f2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PhotoViewAttacher(ImageView imageView) {
        this(imageView, true);
    }

    public PhotoViewAttacher(ImageView imageView, boolean z) {
        Interpolator interpolator;
        Matrix matrix;
        Matrix matrix2;
        Matrix matrix3;
        RectF rectF;
        WeakReference<ImageView> weakReference;
        GestureDetector gestureDetector;
        GestureDetector.OnGestureListener onGestureListener;
        GestureDetector.OnDoubleTapListener onDoubleTapListener;
        ImageView imageView2 = imageView;
        boolean z2 = z;
        new AccelerateDecelerateInterpolator();
        this.mInterpolator = interpolator;
        this.ZOOM_DURATION = IPhotoView.DEFAULT_ZOOM_DURATION;
        this.mMinScale = 1.0f;
        this.mMidScale = 1.75f;
        this.mMaxScale = 3.0f;
        this.mAllowParentInterceptOnEdge = true;
        this.mBlockParentIntercept = false;
        new Matrix();
        this.mBaseMatrix = matrix;
        new Matrix();
        this.mDrawMatrix = matrix2;
        new Matrix();
        this.mSuppMatrix = matrix3;
        new RectF();
        this.mDisplayRect = rectF;
        this.mMatrixValues = new float[9];
        this.mScrollEdge = 2;
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        new WeakReference<>(imageView2);
        this.mImageView = weakReference;
        imageView2.setDrawingCacheEnabled(true);
        imageView2.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView2.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        setImageViewScaleTypeMatrix(imageView2);
        if (!imageView2.isInEditMode()) {
            this.mScaleDragDetector = VersionedGestureDetector.newInstance(imageView2.getContext(), this);
            new GestureDetector.SimpleOnGestureListener(this) {
                private final PhotoViewAttacher this$0;

                {
                    this.this$0 = r6;
                }

                static PhotoViewAttacher access$0(AnonymousClass100000000 r4) {
                    return r4.this$0;
                }

                @Override
                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    MotionEvent motionEvent3 = motionEvent;
                    MotionEvent motionEvent4 = motionEvent2;
                    float f3 = f;
                    float f4 = f2;
                    if (this.this$0.mSingleFlingListener == null) {
                        return false;
                    }
                    if (this.this$0.getScale() > 1.0f) {
                        return false;
                    }
                    if (MotionEventCompat.getPointerCount(motionEvent3) > PhotoViewAttacher.SINGLE_TOUCH || MotionEventCompat.getPointerCount(motionEvent4) > PhotoViewAttacher.SINGLE_TOUCH) {
                        return false;
                    }
                    return this.this$0.mSingleFlingListener.onFling(motionEvent3, motionEvent4, f3, f4);
                }

                @Override
                public void onLongPress(MotionEvent motionEvent) {
                    MotionEvent motionEvent2 = motionEvent;
                    if (this.this$0.mLongClickListener != null) {
                        boolean onLongClick = this.this$0.mLongClickListener.onLongClick(this.this$0.getImageView());
                    }
                }
            };
            new GestureDetector(imageView2.getContext(), onGestureListener);
            this.mGestureDetector = gestureDetector;
            new DefaultOnDoubleTapListener(this);
            this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
            this.mBaseRotation = 0.0f;
            setZoomable(z2);
        }
    }

    private void cancelFling() {
        if (this.mCurrentFlingRunnable != null) {
            this.mCurrentFlingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    private void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private void checkImageViewScaleType() {
        Throwable th;
        ImageView imageView = getImageView();
        if (imageView != null && !(imageView instanceof IPhotoView) && !ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            Throwable th2 = th;
            new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher. You should call setScaleType on the PhotoViewAttacher instead of on the ImageView");
            throw th2;
        }
    }

    private boolean checkMatrixBounds() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return false;
        }
        RectF displayRect = getDisplayRect(getDrawMatrix());
        if (displayRect == null) {
            return false;
        }
        float height = displayRect.height();
        float width = displayRect.width();
        float f = (float) 0;
        float f2 = (float) 0;
        int imageViewHeight = getImageViewHeight(imageView);
        if (height <= ((float) imageViewHeight)) {
            ImageView.ScaleType scaleType = this.mScaleType;
            f2 = scaleType == ImageView.ScaleType.FIT_START ? -displayRect.top : scaleType == ImageView.ScaleType.FIT_END ? (((float) imageViewHeight) - height) - displayRect.top : ((((float) imageViewHeight) - height) / ((float) 2)) - displayRect.top;
        } else if (displayRect.top > ((float) 0)) {
            f2 = -displayRect.top;
        } else if (displayRect.bottom < ((float) imageViewHeight)) {
            f2 = ((float) imageViewHeight) - displayRect.bottom;
        }
        int imageViewWidth = getImageViewWidth(imageView);
        if (width <= ((float) imageViewWidth)) {
            ImageView.ScaleType scaleType2 = this.mScaleType;
            f = scaleType2 == ImageView.ScaleType.FIT_START ? -displayRect.left : scaleType2 == ImageView.ScaleType.FIT_END ? (((float) imageViewWidth) - width) - displayRect.left : ((((float) imageViewWidth) - width) / ((float) 2)) - displayRect.left;
            this.mScrollEdge = 2;
        } else if (displayRect.left > ((float) 0)) {
            this.mScrollEdge = 0;
            f = -displayRect.left;
        } else if (displayRect.right < ((float) imageViewWidth)) {
            f = ((float) imageViewWidth) - displayRect.right;
            this.mScrollEdge = 1;
        } else {
            this.mScrollEdge = -1;
        }
        boolean postTranslate = this.mSuppMatrix.postTranslate(f, f2);
        return true;
    }

    private static void checkZoomLevels(float f, float f2, float f3) {
        Throwable th;
        Throwable th2;
        float f4 = f2;
        float f5 = f3;
        if (f >= f4) {
            Throwable th3 = th2;
            new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
            throw th3;
        } else if (f4 >= f5) {
            Throwable th4 = th;
            new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
            throw th4;
        }
    }

    private RectF getDisplayRect(Matrix matrix) {
        Drawable drawable;
        Matrix matrix2 = matrix;
        ImageView imageView = getImageView();
        if (imageView == null || (drawable = imageView.getDrawable()) == null) {
            return null;
        }
        this.mDisplayRect.set((float) 0, (float) 0, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        boolean mapRect = matrix2.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
    }

    /* access modifiers changed from: private */
    public Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        boolean postConcat = this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    private int getImageViewHeight(ImageView imageView) {
        ImageView imageView2 = imageView;
        if (imageView2 == null) {
            return 0;
        }
        return (imageView2.getHeight() - imageView2.getPaddingTop()) - imageView2.getPaddingBottom();
    }

    private int getImageViewWidth(ImageView imageView) {
        ImageView imageView2 = imageView;
        if (imageView2 == null) {
            return 0;
        }
        return (imageView2.getWidth() - imageView2.getPaddingLeft()) - imageView2.getPaddingRight();
    }

    private float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    private static boolean hasDrawable(ImageView imageView) {
        ImageView imageView2 = imageView;
        return (imageView2 == null || imageView2.getDrawable() == null) ? false : true;
    }

    private static boolean isSupportedScaleType(ImageView.ScaleType scaleType) {
        Throwable th;
        StringBuffer stringBuffer;
        ImageView.ScaleType scaleType2 = scaleType;
        if (scaleType2 == null) {
            return false;
        }
        if (scaleType2 != ImageView.ScaleType.MATRIX) {
            return true;
        }
        Throwable th2 = th;
        new StringBuffer();
        new IllegalArgumentException(stringBuffer.append(scaleType2.name()).append(" is not supported in PhotoView").toString());
        throw th2;
    }

    private void resetMatrix() {
        this.mSuppMatrix.reset();
        setRotationBy(this.mBaseRotation);
        setImageViewMatrix(getDrawMatrix());
        boolean checkMatrixBounds = checkMatrixBounds();
    }

    /* access modifiers changed from: private */
    public void setImageViewMatrix(Matrix matrix) {
        RectF displayRect;
        Matrix matrix2 = matrix;
        ImageView imageView = getImageView();
        if (imageView != null) {
            checkImageViewScaleType();
            imageView.setImageMatrix(matrix2);
            if (this.mMatrixChangeListener != null && (displayRect = getDisplayRect(matrix2)) != null) {
                this.mMatrixChangeListener.onMatrixChanged(displayRect);
            }
        }
    }

    private static void setImageViewScaleTypeMatrix(ImageView imageView) {
        ImageView imageView2 = imageView;
        if (imageView2 != null && !(imageView2 instanceof IPhotoView) && !ImageView.ScaleType.MATRIX.equals(imageView2.getScaleType())) {
            imageView2.setScaleType(ImageView.ScaleType.MATRIX);
        }
    }

    private void updateBaseMatrix(Drawable drawable) {
        RectF rectF;
        RectF rectF2;
        RectF rectF3;
        Drawable drawable2 = drawable;
        ImageView imageView = getImageView();
        if (imageView != null && drawable2 != null) {
            float imageViewWidth = (float) getImageViewWidth(imageView);
            float imageViewHeight = (float) getImageViewHeight(imageView);
            int intrinsicWidth = drawable2.getIntrinsicWidth();
            int intrinsicHeight = drawable2.getIntrinsicHeight();
            this.mBaseMatrix.reset();
            float f = imageViewWidth / ((float) intrinsicWidth);
            float f2 = imageViewHeight / ((float) intrinsicHeight);
            if (this.mScaleType == ImageView.ScaleType.CENTER) {
                boolean postTranslate = this.mBaseMatrix.postTranslate((imageViewWidth - ((float) intrinsicWidth)) / 2.0f, (imageViewHeight - ((float) intrinsicHeight)) / 2.0f);
            } else if (this.mScaleType == ImageView.ScaleType.CENTER_CROP) {
                float max = Math.max(f, f2);
                boolean postScale = this.mBaseMatrix.postScale(max, max);
                boolean postTranslate2 = this.mBaseMatrix.postTranslate((imageViewWidth - (((float) intrinsicWidth) * max)) / 2.0f, (imageViewHeight - (((float) intrinsicHeight) * max)) / 2.0f);
            } else if (this.mScaleType == ImageView.ScaleType.CENTER_INSIDE) {
                float min = Math.min(1.0f, Math.min(f, f2));
                boolean postScale2 = this.mBaseMatrix.postScale(min, min);
                boolean postTranslate3 = this.mBaseMatrix.postTranslate((imageViewWidth - (((float) intrinsicWidth) * min)) / 2.0f, (imageViewHeight - (((float) intrinsicHeight) * min)) / 2.0f);
            } else {
                new RectF((float) 0, (float) 0, (float) intrinsicWidth, (float) intrinsicHeight);
                RectF rectF4 = rectF;
                new RectF((float) 0, (float) 0, imageViewWidth, imageViewHeight);
                RectF rectF5 = rectF2;
                if (((int) this.mBaseRotation) % 180 != 0) {
                    new RectF((float) 0, (float) 0, (float) intrinsicHeight, (float) intrinsicWidth);
                    rectF4 = rectF3;
                }
                ImageView.ScaleType scaleType = this.mScaleType;
                if (scaleType == ImageView.ScaleType.FIT_CENTER) {
                    boolean rectToRect = this.mBaseMatrix.setRectToRect(rectF4, rectF5, Matrix.ScaleToFit.CENTER);
                } else if (scaleType == ImageView.ScaleType.FIT_START) {
                    boolean rectToRect2 = this.mBaseMatrix.setRectToRect(rectF4, rectF5, Matrix.ScaleToFit.START);
                } else if (scaleType == ImageView.ScaleType.FIT_END) {
                    boolean rectToRect3 = this.mBaseMatrix.setRectToRect(rectF4, rectF5, Matrix.ScaleToFit.END);
                } else if (scaleType == ImageView.ScaleType.FIT_XY) {
                    boolean rectToRect4 = this.mBaseMatrix.setRectToRect(rectF4, rectF5, Matrix.ScaleToFit.FILL);
                }
            }
            resetMatrix();
        }
    }

    @Override
    public boolean canZoom() {
        return this.mZoomEnabled;
    }

    @SuppressWarnings("deprecation")
    public void cleanup() {
        if (this.mImageView != null) {
            ImageView imageView = (ImageView) this.mImageView.get();
            if (imageView != null) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                imageView.setOnTouchListener((View.OnTouchListener) null);
                cancelFling();
            }
            if (this.mGestureDetector != null) {
                this.mGestureDetector.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) null);
            }
            this.mMatrixChangeListener = null;
            this.mPhotoTapListener = null;
            this.mViewTapListener = null;
            this.mImageView = null;
        }
    }

    @Override
    public void getDisplayMatrix(Matrix matrix) {
        matrix.set(getDrawMatrix());
    }

    @Override
    public RectF getDisplayRect() {
        boolean checkMatrixBounds = checkMatrixBounds();
        return getDisplayRect(getDrawMatrix());
    }

    @Override
    public IPhotoView getIPhotoViewImplementation() {
        return this;
    }

    public Matrix getImageMatrix() {
        return this.mDrawMatrix;
    }

    public ImageView getImageView() {
        ImageView imageView = null;
        if (this.mImageView != null) {
            imageView = (ImageView) this.mImageView.get();
        }
        if (imageView == null) {
            cleanup();
            int i = LogManager.getLogger().i(LOG_TAG, "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
        }
        return imageView;
    }

    @Override
    public float getMaximumScale() {
        return this.mMaxScale;
    }

    @Override
    public float getMediumScale() {
        return this.mMidScale;
    }

    @Override
    public float getMinimumScale() {
        return this.mMinScale;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public OnPhotoTapListener getOnPhotoTapListener() {
        return this.mPhotoTapListener;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public OnViewTapListener getOnViewTapListener() {
        return this.mViewTapListener;
    }

    @Override
    public float getScale() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) getValue(this.mSuppMatrix, 0), (double) 2)) + ((float) Math.pow((double) getValue(this.mSuppMatrix, 3), (double) 2))));
    }

    @Override
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void getSuppMatrix(Matrix matrix) {
        matrix.set(this.mSuppMatrix);
    }

    public Bitmap getVisibleRectangleBitmap() {
        ImageView imageView = getImageView();
        return imageView == null ? null : imageView.getDrawingCache();
    }

    @Override
    public void onDrag(float f, float f2) {
        Object obj;
        Object obj2;
        float f3 = f;
        float f4 = f2;
        if (!this.mScaleDragDetector.isScaling()) {
            if (DEBUG) {
                Logger logger = LogManager.getLogger();
                Object[] objArr = new Object[2];
                Object[] objArr2 = objArr;
                Object[] objArr3 = objArr;
                new Float(f3);
                objArr3[0] = obj;
                Object[] objArr4 = objArr2;
                Object[] objArr5 = objArr4;
                Object[] objArr6 = objArr4;
                new Float(f4);
                objArr6[1] = obj2;
                int d = logger.d(LOG_TAG, String.format("onDrag: dx: %.2f. dy: %.2f", objArr5));
            }
            ImageView imageView = getImageView();
            boolean postTranslate = this.mSuppMatrix.postTranslate(f3, f4);
            checkAndDisplayMatrix();
            ViewParent parent = imageView.getParent();
            if (!this.mAllowParentInterceptOnEdge || this.mScaleDragDetector.isScaling() || this.mBlockParentIntercept) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            } else if ((this.mScrollEdge == 2 || ((this.mScrollEdge == 0 && f3 >= 1.0f) || (this.mScrollEdge == 1 && f3 <= -1.0f))) && parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    @Override
    public void onFling(float f, float f2, float f3, float f4) {
        FlingRunnable flingRunnable;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        StringBuffer stringBuffer5;
        StringBuffer stringBuffer6;
        StringBuffer stringBuffer7;
        float f5 = f;
        float f6 = f2;
        float f7 = f3;
        float f8 = f4;
        if (DEBUG) {
            Logger logger = LogManager.getLogger();
            new StringBuffer();
            new StringBuffer();
            new StringBuffer();
            new StringBuffer();
            new StringBuffer();
            new StringBuffer();
            new StringBuffer();
            int d = logger.d(LOG_TAG, stringBuffer.append(stringBuffer2.append(stringBuffer3.append(stringBuffer4.append(stringBuffer5.append(stringBuffer6.append(stringBuffer7.append("onFling. sX: ").append(f5).toString()).append(" sY: ").toString()).append(f6).toString()).append(" Vx: ").toString()).append(f7).toString()).append(" Vy: ").toString()).append(f8).toString());
        }
        ImageView imageView = getImageView();
        new FlingRunnable(this, imageView.getContext());
        this.mCurrentFlingRunnable = flingRunnable;
        this.mCurrentFlingRunnable.fling(getImageViewWidth(imageView), getImageViewHeight(imageView), (int) f7, (int) f8);
        boolean post = imageView.post(this.mCurrentFlingRunnable);
    }

    @Override
    public void onGlobalLayout() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (this.mZoomEnabled) {
            int top = imageView.getTop();
            int right = imageView.getRight();
            int bottom = imageView.getBottom();
            int left = imageView.getLeft();
            if (top != this.mIvTop || bottom != this.mIvBottom || left != this.mIvLeft || right != this.mIvRight) {
                updateBaseMatrix(imageView.getDrawable());
                this.mIvTop = top;
                this.mIvRight = right;
                this.mIvBottom = bottom;
                this.mIvLeft = left;
                return;
            }
            return;
        }
        updateBaseMatrix(imageView.getDrawable());
    }

    @Override
    public void onScale(float f, float f2, float f3) {
        Object obj;
        Object obj2;
        Object obj3;
        float f4 = f;
        float f5 = f2;
        float f6 = f3;
        if (DEBUG) {
            Logger logger = LogManager.getLogger();
            Object[] objArr = new Object[3];
            Object[] objArr2 = objArr;
            Object[] objArr3 = objArr;
            new Float(f4);
            objArr3[0] = obj;
            Object[] objArr4 = objArr2;
            Object[] objArr5 = objArr4;
            Object[] objArr6 = objArr4;
            new Float(f5);
            objArr6[1] = obj2;
            Object[] objArr7 = objArr5;
            Object[] objArr8 = objArr7;
            Object[] objArr9 = objArr7;
            new Float(f6);
            objArr9[2] = obj3;
            int d = logger.d(LOG_TAG, String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", objArr8));
        }
        if (getScale() >= this.mMaxScale && f4 >= 1.0f) {
            return;
        }
        if (getScale() > this.mMinScale || f4 > 1.0f) {
            if (this.mScaleChangeListener != null) {
                this.mScaleChangeListener.onScaleChange(f4, f5, f6);
            }
            boolean postScale = this.mSuppMatrix.postScale(f4, f4, f5, f6);
            checkAndDisplayMatrix();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        RectF displayRect;
        Runnable runnable;
        View view2 = view;
        MotionEvent motionEvent2 = motionEvent;
        boolean z = false;
        if (this.mZoomEnabled && hasDrawable((ImageView) view2)) {
            ViewParent parent = view2.getParent();
            switch (motionEvent2.getAction()) {
                case 0:
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    } else {
                        int i = LogManager.getLogger().i(LOG_TAG, "onTouch getParent() returned null");
                    }
                    cancelFling();
                    break;
                case 1:
                case 3:
                    if (getScale() < this.mMinScale && (displayRect = getDisplayRect()) != null) {
                        new AnimatedZoomRunnable(this, getScale(), this.mMinScale, displayRect.centerX(), displayRect.centerY());
                        boolean post = view2.post(runnable);
                        z = true;
                        break;
                    }
            }
            if (this.mScaleDragDetector != null) {
                boolean isScaling = this.mScaleDragDetector.isScaling();
                boolean isDragging = this.mScaleDragDetector.isDragging();
                z = this.mScaleDragDetector.onTouchEvent(motionEvent2);
                this.mBlockParentIntercept = (!isScaling && !this.mScaleDragDetector.isScaling()) && (!isDragging && !this.mScaleDragDetector.isDragging());
            }
            if (this.mGestureDetector != null && this.mGestureDetector.onTouchEvent(motionEvent2)) {
                z = true;
            }
        }
        return z;
    }

    @Override
    public void setAllowParentInterceptOnEdge(boolean z) {
        boolean z2 = z;
        this.mAllowParentInterceptOnEdge = z2;
    }

    public void setBaseRotation(float f) {
        this.mBaseRotation = f % ((float) 360);
        update();
        setRotationBy(this.mBaseRotation);
        checkAndDisplayMatrix();
    }

    @Override
    public boolean setDisplayMatrix(Matrix matrix) {
        Throwable th;
        Matrix matrix2 = matrix;
        if (matrix2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Matrix cannot be null");
            throw th2;
        }
        ImageView imageView = getImageView();
        if (imageView == null) {
            return false;
        }
        if (imageView.getDrawable() == null) {
            return false;
        }
        this.mSuppMatrix.set(matrix2);
        setImageViewMatrix(getDrawMatrix());
        boolean checkMatrixBounds = checkMatrixBounds();
        return true;
    }

    @Override
    public void setMaximumScale(float f) {
        float f2 = f;
        checkZoomLevels(this.mMinScale, this.mMidScale, f2);
        this.mMaxScale = f2;
    }

    @Override
    public void setMediumScale(float f) {
        float f2 = f;
        checkZoomLevels(this.mMinScale, f2, this.mMaxScale);
        this.mMidScale = f2;
    }

    @Override
    public void setMinimumScale(float f) {
        float f2 = f;
        checkZoomLevels(f2, this.mMidScale, this.mMaxScale);
        this.mMinScale = f2;
    }

    @Override
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        GestureDetector.OnDoubleTapListener onDoubleTapListener2;
        GestureDetector.OnDoubleTapListener onDoubleTapListener3 = onDoubleTapListener;
        if (onDoubleTapListener3 != null) {
            this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener3);
            return;
        }
        new DefaultOnDoubleTapListener(this);
        this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener2);
    }

    @Override
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        View.OnLongClickListener onLongClickListener2 = onLongClickListener;
        this.mLongClickListener = onLongClickListener2;
    }

    @Override
    public void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        OnMatrixChangedListener onMatrixChangedListener2 = onMatrixChangedListener;
        this.mMatrixChangeListener = onMatrixChangedListener2;
    }

    @Override
    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        OnPhotoTapListener onPhotoTapListener2 = onPhotoTapListener;
        this.mPhotoTapListener = onPhotoTapListener2;
    }

    @Override
    public void setOnScaleChangeListener(OnScaleChangeListener onScaleChangeListener) {
        OnScaleChangeListener onScaleChangeListener2 = onScaleChangeListener;
        this.mScaleChangeListener = onScaleChangeListener2;
    }

    @Override
    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        OnSingleFlingListener onSingleFlingListener2 = onSingleFlingListener;
        this.mSingleFlingListener = onSingleFlingListener2;
    }

    @Override
    public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        OnViewTapListener onViewTapListener2 = onViewTapListener;
        this.mViewTapListener = onViewTapListener2;
    }

    @Override
    public void setRotationBy(float f) {
        boolean postRotate = this.mSuppMatrix.postRotate(f % ((float) 360));
        checkAndDisplayMatrix();
    }

    @Override
    public void setRotationTo(float f) {
        this.mSuppMatrix.setRotate(f % ((float) 360));
        checkAndDisplayMatrix();
    }

    @Override
    public void setScale(float f) {
        setScale(f, false);
    }

    @Override
    public void setScale(float f, float f2, float f3, boolean z) {
        Runnable runnable;
        float f4 = f;
        float f5 = f2;
        float f6 = f3;
        boolean z2 = z;
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (f4 < this.mMinScale || f4 > this.mMaxScale) {
            int i = LogManager.getLogger().i(LOG_TAG, "Scale must be within the range of minScale and maxScale");
        } else if (z2) {
            new AnimatedZoomRunnable(this, getScale(), f4, f5, f6);
            boolean post = imageView.post(runnable);
        } else {
            this.mSuppMatrix.setScale(f4, f4, f5, f6);
            checkAndDisplayMatrix();
        }
    }

    @Override
    public void setScale(float f, boolean z) {
        float f2 = f;
        boolean z2 = z;
        ImageView imageView = getImageView();
        if (imageView != null) {
            setScale(f2, (float) (imageView.getRight() / 2), (float) (imageView.getBottom() / 2), z2);
        }
    }

    @Override
    public void setScaleLevels(float f, float f2, float f3) {
        float f4 = f;
        float f5 = f2;
        float f6 = f3;
        checkZoomLevels(f4, f5, f6);
        this.mMinScale = f4;
        this.mMidScale = f5;
        this.mMaxScale = f6;
    }

    @Override
    public void setScaleType(ImageView.ScaleType scaleType) {
        ImageView.ScaleType scaleType2 = scaleType;
        if (isSupportedScaleType(scaleType2) && scaleType2 != this.mScaleType) {
            this.mScaleType = scaleType2;
            update();
        }
    }

    public void setZoomInterpolator(Interpolator interpolator) {
        Interpolator interpolator2 = interpolator;
        this.mInterpolator = interpolator2;
    }

    @Override
    public void setZoomTransitionDuration(int i) {
        int i2 = i;
        if (i2 < 0) {
            i2 = 200;
        }
        this.ZOOM_DURATION = i2;
    }

    @Override
    public void setZoomable(boolean z) {
        this.mZoomEnabled = z;
        update();
    }

    public void update() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (this.mZoomEnabled) {
            setImageViewScaleTypeMatrix(imageView);
            updateBaseMatrix(imageView.getDrawable());
            return;
        }
        resetMatrix();
    }
}
