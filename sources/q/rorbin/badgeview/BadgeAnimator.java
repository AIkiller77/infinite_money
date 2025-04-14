package q.rorbin.badgeview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Random;

public class BadgeAnimator extends ValueAnimator {
    private BitmapFragment[][] mFragments;
    /* access modifiers changed from: private */
    public WeakReference<QBadgeView> mWeakBadge;

    public BadgeAnimator(Bitmap bitmap, PointF pointF, QBadgeView qBadgeView) {
        WeakReference<QBadgeView> weakReference;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        Animator.AnimatorListener animatorListener;
        new WeakReference<>(qBadgeView);
        this.mWeakBadge = weakReference;
        setFloatValues(new float[]{0.0f, 1.0f});
        ValueAnimator duration = setDuration((long) 500);
        this.mFragments = getFragments(bitmap, pointF);
        new ValueAnimator.AnimatorUpdateListener(this) {
            private final BadgeAnimator this$0;

            {
                this.this$0 = r6;
            }

            static BadgeAnimator access$0(AnonymousClass100000000 r4) {
                return r4.this$0;
            }

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator valueAnimator2 = valueAnimator;
                QBadgeView qBadgeView = (QBadgeView) this.this$0.mWeakBadge.get();
                if (qBadgeView == null || !qBadgeView.isShown()) {
                    this.this$0.cancel();
                } else {
                    qBadgeView.invalidate();
                }
            }
        };
        addUpdateListener(animatorUpdateListener);
        new AnimatorListenerAdapter(this) {
            private final BadgeAnimator this$0;

            {
                this.this$0 = r6;
            }

            static BadgeAnimator access$0(AnonymousClass100000001 r4) {
                return r4.this$0;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                QBadgeView qBadgeView = (QBadgeView) this.this$0.mWeakBadge.get();
                if (qBadgeView != null) {
                    qBadgeView.reset();
                }
            }
        };
        addListener(animatorListener);
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        for (int i = 0; i < this.mFragments.length; i++) {
            for (int i2 = 0; i2 < this.mFragments[i].length; i2++) {
                this.mFragments[i][i2].updata(Float.parseFloat(getAnimatedValue().toString()), canvas2);
            }
        }
    }

    private BitmapFragment[][] getFragments(Bitmap bitmap, PointF pointF) {
        BitmapFragment bitmapFragment;
        Bitmap bitmap2 = bitmap;
        PointF pointF2 = pointF;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        float min = ((float) Math.min(width, height)) / 6.0f;
        float width2 = pointF2.x - (((float) bitmap2.getWidth()) / 2.0f);
        float height2 = pointF2.y - (((float) bitmap2.getHeight()) / 2.0f);
        BitmapFragment[][] bitmapFragmentArr = (BitmapFragment[][]) Array.newInstance(BitmapFragment.class, new int[]{(int) (((float) height) / min), (int) (((float) width) / min)});
        for (int i = 0; i < bitmapFragmentArr.length; i++) {
            for (int i2 = 0; i2 < bitmapFragmentArr[i].length; i2++) {
                new BitmapFragment(this);
                BitmapFragment bitmapFragment2 = bitmapFragment;
                bitmapFragment2.color = bitmap2.getPixel((int) (((float) i2) * min), (int) (((float) i) * min));
                bitmapFragment2.x = width2 + (((float) i2) * min);
                bitmapFragment2.y = height2 + (((float) i) * min);
                bitmapFragment2.size = min;
                bitmapFragment2.maxSize = Math.max(width, height);
                bitmapFragmentArr[i][i2] = bitmapFragment2;
            }
        }
        bitmap2.recycle();
        return bitmapFragmentArr;
    }

    private class BitmapFragment {
        int color;
        int maxSize;
        Paint paint;
        Random random;
        float size;
        private final BadgeAnimator this$0;
        float x;
        float y;

        static BadgeAnimator access$0(BitmapFragment bitmapFragment) {
            return bitmapFragment.this$0;
        }

        public BitmapFragment(BadgeAnimator badgeAnimator) {
            Paint paint2;
            Random random2;
            this.this$0 = badgeAnimator;
            new Paint();
            this.paint = paint2;
            this.paint.setAntiAlias(true);
            this.paint.setStyle(Paint.Style.FILL);
            new Random();
            this.random = random2;
        }

        public void updata(float f, Canvas canvas) {
            this.paint.setColor(this.color);
            this.x += 0.1f * ((float) this.random.nextInt(this.maxSize)) * (this.random.nextFloat() - 0.5f);
            this.y += 0.1f * ((float) this.random.nextInt(this.maxSize)) * (this.random.nextFloat() - 0.5f);
            canvas.drawCircle(this.x, this.y, this.size - (f * this.size), this.paint);
        }
    }
}
