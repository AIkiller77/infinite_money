package q.rorbin.badgeview;

import android.graphics.PointF;
import java.util.List;

public class MathUtil {
    public static final double CIRCLE_RADIAN = 6.283185307179586d;

    public static double getTanRadian(double d, int i) {
        double d2 = d;
        int i2 = i;
        if (d2 < ((double) 0)) {
            d2 += 1.5707963267948966d;
        }
        return d2 + (1.5707963267948966d * ((double) (i2 - 1)));
    }

    public static double radianToAngle(double d) {
        return ((double) 360) * (d / 6.283185307179586d);
    }

    public static int getQuadrant(PointF pointF, PointF pointF2) {
        PointF pointF3 = pointF;
        PointF pointF4 = pointF2;
        if (pointF3.x > pointF4.x) {
            if (pointF3.y > pointF4.y) {
                return 4;
            }
            if (pointF3.y < pointF4.y) {
                return 1;
            }
        } else if (pointF3.x < pointF4.x) {
            if (pointF3.y > pointF4.y) {
                return 3;
            }
            if (pointF3.y < pointF4.y) {
                return 2;
            }
        }
        return -1;
    }

    public static float getPointDistance(PointF pointF, PointF pointF2) {
        PointF pointF3 = pointF;
        PointF pointF4 = pointF2;
        return (float) Math.sqrt(Math.pow((double) (pointF3.x - pointF4.x), (double) 2) + Math.pow((double) (pointF3.y - pointF4.y), (double) 2));
    }

    public static void getInnertangentPoints(PointF pointF, float f, Double d, List<PointF> list) {
        float f2;
        float f3;
        Object obj;
        Object obj2;
        PointF pointF2 = pointF;
        float f4 = f;
        Double d2 = d;
        List<PointF> list2 = list;
        if (d2 != null) {
            float atan = (float) Math.atan(d2.doubleValue());
            f2 = (float) (Math.cos((double) atan) * ((double) f4));
            f3 = (float) (Math.sin((double) atan) * ((double) f4));
        } else {
            f2 = f4;
            f3 = (float) 0;
        }
        new PointF(pointF2.x + f2, pointF2.y + f3);
        boolean add = list2.add(obj);
        new PointF(pointF2.x - f2, pointF2.y - f3);
        boolean add2 = list2.add(obj2);
    }

    public MathUtil() {
    }
}
