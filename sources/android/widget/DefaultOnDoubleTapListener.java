package android.widget;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class DefaultOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {
    private PhotoViewAttacher photoViewAttacher;

    public DefaultOnDoubleTapListener(PhotoViewAttacher photoViewAttacher2) {
        setPhotoViewAttacher(photoViewAttacher2);
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        if (this.photoViewAttacher == null) {
            return false;
        }
        try {
            float scale = this.photoViewAttacher.getScale();
            float x = motionEvent2.getX();
            float y = motionEvent2.getY();
            if (scale < this.photoViewAttacher.getMediumScale()) {
                this.photoViewAttacher.setScale(this.photoViewAttacher.getMediumScale(), x, y, true);
            } else if (scale < this.photoViewAttacher.getMediumScale() || scale >= this.photoViewAttacher.getMaximumScale()) {
                this.photoViewAttacher.setScale(this.photoViewAttacher.getMinimumScale(), x, y, true);
            } else {
                this.photoViewAttacher.setScale(this.photoViewAttacher.getMaximumScale(), x, y, true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = e;
        }
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        RectF displayRect;
        MotionEvent motionEvent2 = motionEvent;
        if (this.photoViewAttacher == null) {
            return false;
        }
        ImageView imageView = this.photoViewAttacher.getImageView();
        if (!(this.photoViewAttacher.getOnPhotoTapListener() == null || (displayRect = this.photoViewAttacher.getDisplayRect()) == null)) {
            float x = motionEvent2.getX();
            float y = motionEvent2.getY();
            if (displayRect.contains(x, y)) {
                this.photoViewAttacher.getOnPhotoTapListener().onPhotoTap(imageView, (x - displayRect.left) / displayRect.width(), (y - displayRect.top) / displayRect.height());
                return true;
            }
            this.photoViewAttacher.getOnPhotoTapListener().onOutsidePhotoTap();
        }
        if (this.photoViewAttacher.getOnViewTapListener() != null) {
            this.photoViewAttacher.getOnViewTapListener().onViewTap(imageView, motionEvent2.getX(), motionEvent2.getY());
        }
        return false;
    }

    public void setPhotoViewAttacher(PhotoViewAttacher photoViewAttacher2) {
        PhotoViewAttacher photoViewAttacher3 = photoViewAttacher2;
        this.photoViewAttacher = photoViewAttacher3;
    }
}
