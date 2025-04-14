package android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.PhotoViewAttacher;

public class PhotoView extends ImageView implements IPhotoView {
    private PhotoViewAttacher mAttacher;
    private ImageView.ScaleType mPendingScaleType;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PhotoView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        init();
    }

    @Override
    public boolean canZoom() {
        return this.mAttacher.canZoom();
    }

    @Override
    public void getDisplayMatrix(Matrix matrix) {
        this.mAttacher.getDisplayMatrix(matrix);
    }

    @Override
    public RectF getDisplayRect() {
        return this.mAttacher.getDisplayRect();
    }

    @Override
    public IPhotoView getIPhotoViewImplementation() {
        return this.mAttacher;
    }

    @Override
    public Matrix getImageMatrix() {
        return this.mAttacher.getImageMatrix();
    }

    @Override
    public float getMaximumScale() {
        return this.mAttacher.getMaximumScale();
    }

    @Override
    public float getMediumScale() {
        return this.mAttacher.getMediumScale();
    }

    @Override
    public float getMinimumScale() {
        return this.mAttacher.getMinimumScale();
    }

    @Override
    public float getScale() {
        return this.mAttacher.getScale();
    }

    @Override
    public ImageView.ScaleType getScaleType() {
        return this.mAttacher.getScaleType();
    }

    @Override
    public Bitmap getVisibleRectangleBitmap() {
        return this.mAttacher.getVisibleRectangleBitmap();
    }

    /* access modifiers changed from: protected */
    public void init() {
        PhotoViewAttacher photoViewAttacher;
        if (this.mAttacher == null || this.mAttacher.getImageView() == null) {
            new PhotoViewAttacher(this);
            this.mAttacher = photoViewAttacher;
        }
        if (this.mPendingScaleType != null) {
            setScaleType(this.mPendingScaleType);
            this.mPendingScaleType = null;
        }
    }

    /* access modifiers changed from: protected */
    @Override
    public void onAttachedToWindow() {
        init();
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    @Override
    public void onDetachedFromWindow() {
        this.mAttacher.cleanup();
        this.mAttacher = null;
        super.onDetachedFromWindow();
    }

    @Override
    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mAttacher.setAllowParentInterceptOnEdge(z);
    }

    @Override
    public boolean setDisplayMatrix(Matrix matrix) {
        return this.mAttacher.setDisplayMatrix(matrix);
    }

    /* access modifiers changed from: protected */
    @Override
    public boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
        return frame;
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
    }

    @Override
    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
    }

    @Override
    public void setMaximumScale(float f) {
        this.mAttacher.setMaximumScale(f);
    }

    @Override
    public void setMediumScale(float f) {
        this.mAttacher.setMediumScale(f);
    }

    @Override
    public void setMinimumScale(float f) {
        this.mAttacher.setMinimumScale(f);
    }

    @Override
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.mAttacher.setOnDoubleTapListener(onDoubleTapListener);
    }

    @Override
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mAttacher.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener onMatrixChangedListener) {
        this.mAttacher.setOnMatrixChangeListener(onMatrixChangedListener);
    }

    @Override
    public void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener onPhotoTapListener) {
        this.mAttacher.setOnPhotoTapListener(onPhotoTapListener);
    }

    @Override
    public void setOnScaleChangeListener(PhotoViewAttacher.OnScaleChangeListener onScaleChangeListener) {
        this.mAttacher.setOnScaleChangeListener(onScaleChangeListener);
    }

    @Override
    public void setOnSingleFlingListener(PhotoViewAttacher.OnSingleFlingListener onSingleFlingListener) {
        this.mAttacher.setOnSingleFlingListener(onSingleFlingListener);
    }

    @Override
    public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener onViewTapListener) {
        this.mAttacher.setOnViewTapListener(onViewTapListener);
    }

    @Override
    public void setRotationBy(float f) {
        this.mAttacher.setRotationBy(f);
    }

    @Override
    public void setRotationTo(float f) {
        this.mAttacher.setRotationTo(f);
    }

    @Override
    public void setScale(float f) {
        this.mAttacher.setScale(f);
    }

    @Override
    public void setScale(float f, float f2, float f3, boolean z) {
        this.mAttacher.setScale(f, f2, f3, z);
    }

    @Override
    public void setScale(float f, boolean z) {
        this.mAttacher.setScale(f, z);
    }

    @Override
    public void setScaleLevels(float f, float f2, float f3) {
        this.mAttacher.setScaleLevels(f, f2, f3);
    }

    @Override
    public void setScaleType(ImageView.ScaleType scaleType) {
        ImageView.ScaleType scaleType2 = scaleType;
        if (this.mAttacher != null) {
            this.mAttacher.setScaleType(scaleType2);
            return;
        }
        this.mPendingScaleType = scaleType2;
    }

    @Override
    public void setZoomTransitionDuration(int i) {
        this.mAttacher.setZoomTransitionDuration(i);
    }

    @Override
    public void setZoomable(boolean z) {
        this.mAttacher.setZoomable(z);
    }
}
