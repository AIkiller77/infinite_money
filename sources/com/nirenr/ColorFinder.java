package com.nirenr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ColorFinder {
    private int a;
    private int b;
    private int[][] c;
    private float[][] d;
    private float e;

    public ColorFinder(Bitmap bitmap) {
        a(bitmap);
    }

    public ColorFinder(String str) {
        a(BitmapFactory.decodeFile(str));
    }

    private int a(int i, int i2, int[][] iArr, int i3, int i4, int i5) {
        int i6 = 0;
        while (i6 < (this.b - i2) - i3) {
            int i7 = i2 + i6;
            if (iArr[i][i7] == 1) {
                int i8 = i + i5;
                if (iArr[i8][i7] == 0 && iArr[i8 + i4][i7] == 0) {
                    i6++;
                }
            }
            if (i6 > i3) {
                return i6;
            }
            return -1;
        }
        return (this.b - i2) - i3;
    }

    private void a(Bitmap bitmap) {
        this.a = bitmap.getWidth();
        this.b = bitmap.getHeight();
        int[] iArr = new int[(this.a * this.b)];
        bitmap.getPixels(iArr, 0, this.a, 0, 0, this.a, this.b);
        this.c = (int[][]) Array.newInstance(int.class, new int[]{this.a, this.b});
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.a; i2++) {
                this.c[i2][i] = iArr[(this.a * i) + i2];
            }
        }
    }

    public Point find(int i) {
        for (int i2 = 0; i2 < this.b; i2++) {
            for (int i3 = 0; i3 < this.a; i3++) {
                if (this.c[i3][i2] == i) {
                    return new Point(i3, i2);
                }
            }
        }
        return new Point(-1, -1);
    }

    public Point find(int i, int i2, int i3) {
        for (int i4 = 0; i4 < this.b; i4++) {
            for (int i5 = 0; i5 < this.a; i5++) {
                int i6 = this.c[i5][i4];
                int i7 = (i6 << 8) >>> 24;
                int i8 = (i6 << 16) >>> 24;
                int i9 = (i6 << 24) >>> 24;
                if (i7 == i && i8 == i2 && i9 == i3) {
                    return new Point(i5, i4);
                }
            }
        }
        return new Point(-1, -1);
    }

    public Point find(int i, int i2, int i3, int i4) {
        int i5 = i - i4;
        int i6 = i + i4;
        int i7 = i2 - i4;
        int i8 = i2 + i4;
        int i9 = i3 - i4;
        int i10 = i3 + i4;
        for (int i11 = 0; i11 < this.b; i11++) {
            for (int i12 = 0; i12 < this.a; i12++) {
                int i13 = this.c[i12][i11];
                int i14 = (i13 << 8) >>> 24;
                int i15 = (i13 << 16) >>> 24;
                int i16 = (i13 << 24) >>> 24;
                if (i14 >= i5 && i14 <= i6 && i15 >= i7 && i15 <= i8 && i16 >= i9 && i16 <= i10) {
                    return new Point(i12, i11);
                }
            }
        }
        return new Point(-1, -1);
    }

    public Point find(int i, int i2, int i3, int i4, int i5) {
        while (i2 < i4) {
            for (int i6 = i; i6 < i3; i6++) {
                if (this.c[i6][i2] == i5) {
                    return new Point(i6, i2);
                }
            }
            i2++;
        }
        return new Point(-1, -1);
    }

    public Point find(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        while (i2 < i4) {
            for (int i8 = i; i8 < i3; i8++) {
                int i9 = this.c[i8][i2];
                int i10 = (i9 << 8) >>> 24;
                int i11 = (i9 << 16) >>> 24;
                int i12 = (i9 << 24) >>> 24;
                if (i10 == i5 && i11 == i6 && i12 == i7) {
                    return new Point(i8, i2);
                }
            }
            i2++;
        }
        return new Point(-1, -1);
    }

    public Point find(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i5 - i8;
        int i10 = i5 + i8;
        int i11 = i6 - i8;
        int i12 = i6 + i8;
        int i13 = i7 - i8;
        int i14 = i7 + i8;
        while (i2 < i4) {
            for (int i15 = i; i15 < i3; i15++) {
                int i16 = this.c[i15][i2];
                int i17 = (i16 << 8) >>> 24;
                int i18 = (i16 << 16) >>> 24;
                int i19 = (i16 << 24) >>> 24;
                if (i17 >= i9 && i17 <= i10 && i18 >= i11 && i18 <= i12 && i19 >= i13 && i19 <= i14) {
                    return new Point(i15, i2);
                }
            }
            i2++;
        }
        return new Point(-1, -1);
    }

    public Point find(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ColorPoint[] colorPointArr) {
        int i9;
        boolean z;
        ColorFinder colorFinder = this;
        ColorPoint[] colorPointArr2 = colorPointArr;
        int i10 = i5 - i8;
        int i11 = i5 + i8;
        int i12 = i6 - i8;
        int i13 = i6 + i8;
        int i14 = i7 - i8;
        int i15 = i7 + i8;
        int i16 = i2;
        int i17 = i4;
        while (i16 < i17) {
            int i18 = i;
            int i19 = i3;
            while (i18 < i19) {
                int i20 = colorFinder.c[i18][i16];
                int i21 = (i20 << 8) >>> 24;
                int i22 = (i20 << 16) >>> 24;
                int i23 = (i20 << 24) >>> 24;
                if (i21 < i10 || i21 > i11 || i22 < i12 || i22 > i13 || i23 < i14 || i23 > i15) {
                    int i24 = i;
                    i9 = i11;
                    int i25 = i2;
                } else {
                    int length = colorPointArr2.length;
                    int i26 = 0;
                    while (true) {
                        if (i26 >= length) {
                            int i27 = i;
                            i9 = i11;
                            int i28 = i2;
                            z = true;
                            break;
                        }
                        i9 = i11;
                        if (!colorPointArr2[i26].check(colorFinder.c, i, i2)) {
                            z = false;
                            break;
                        }
                        i26++;
                        i11 = i9;
                        colorFinder = this;
                    }
                    if (z) {
                        return new Point(i18, i16);
                    }
                }
                i18++;
                i11 = i9;
                colorFinder = this;
            }
            int i29 = i;
            int i30 = i11;
            int i31 = i2;
            i16++;
            i11 = i30;
            colorFinder = this;
        }
        return new Point(-1, -1);
    }

    public Point find(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[][] iArr) {
        int[][] iArr2 = iArr;
        ColorPoint[] colorPointArr = new ColorPoint[iArr2.length];
        for (int i9 = 0; i9 < iArr2.length; i9++) {
            colorPointArr[i9] = new ColorPoint(iArr2[i9]);
        }
        return find(i, i2, i3, i4, i5, i6, i7, i8, colorPointArr);
    }

    public Point find(Color color) {
        return find(color.red, color.green, color.blue);
    }

    public Point find(Color color, int i) {
        return find(color.red, color.green, color.blue, i);
    }

    public Point find(Point point, Point point2, Color color) {
        return find(point.x, point.y, point2.x, point2.y, color.red, color.green, color.blue);
    }

    public Point find(Point point, Point point2, Color color, int i) {
        return find(point.x, point.y, point2.x, point2.y, color.red, color.green, color.blue, i);
    }

    public ArrayList<Rect> findLine(float f, int i) {
        return findLine(this.a / 2, 10, this.a - 10, this.b - (i * 16), f, i * 8, i * 4, i);
    }

    public ArrayList<Rect> findLine(float f, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (this.b < this.a) {
            i3 = this.a / 2;
            i4 = 0;
            i5 = this.a - 10;
            i6 = this.b - i;
        } else {
            i3 = this.a / 2;
            i4 = this.a / 3;
            i5 = this.a - 10;
            i6 = this.a;
        }
        return findLine(i3, i4, i5, i6, f, i, i2 * 4, i2);
    }

    public ArrayList<Rect> findLine(float f, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        if (this.b < this.a) {
            i4 = this.a / 2;
            i5 = 0;
            i6 = this.a - 10;
            i7 = this.b - i;
        } else {
            i4 = this.a / 2;
            i5 = this.a / 3;
            i6 = this.a - 10;
            i7 = this.a;
        }
        return findLine(i4, i5, i6, i7, f, i, i2, i3);
    }

    public ArrayList<Rect> findLine(int i) {
        return findLine(this.a / 2, 10, this.a - 10, this.b - (i * 16), 0.5f, i * 8, i * 4, i);
    }

    public ArrayList<Rect> findLine(int i, int i2, int i3, int i4, float f, int i5, int i6, int i7) {
        if (this.d == null) {
            this.d = (float[][]) Array.newInstance(float.class, new int[]{this.a, this.b});
            float[] fArr = new float[3];
            int i8 = 0;
            float f2 = 0.0f;
            while (i8 < this.b) {
                float f3 = f2;
                for (int i9 = 0; i9 < this.a; i9++) {
                    Color.colorToHSV(this.c[i9][i8], fArr);
                    this.d[i9][i8] = fArr[2];
                    f3 += fArr[2];
                }
                i8++;
                f2 = f3;
            }
            this.e = f2 / ((float) (this.a * this.b));
        }
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{this.a, this.b});
        float f4 = this.e * f;
        for (int i10 = 0; i10 < this.b; i10++) {
            for (int i11 = 0; i11 < this.a; i11++) {
                int i12 = this.a;
                if (this.d[i11][i10] > f4) {
                    iArr[i11][i10] = 1;
                } else {
                    iArr[i11][i10] = 0;
                }
            }
        }
        ArrayList<Rect> arrayList = new ArrayList<>();
        int i13 = i;
        int i14 = i3;
        while (i13 < i14) {
            int i15 = i2;
            int i16 = i4;
            while (true) {
                if (i15 >= i16) {
                    break;
                }
                int a2 = a(i13, i15, iArr, i5, i6, i7);
                if (a2 > -1) {
                    i13 += i7;
                    arrayList.add(new Rect(i13, i15, i13, a2 + i13));
                    break;
                }
                i15++;
            }
            i13++;
        }
        return arrayList;
    }

    public int[][] getPixels() {
        return this.c;
    }
}
