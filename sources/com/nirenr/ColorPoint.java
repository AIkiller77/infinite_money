package com.nirenr;

import android.graphics.Point;

public class ColorPoint {
    public int blue;
    public int green;
    public int offset;
    public int red;
    public int x;
    public int y;

    public ColorPoint(int i, int i2, int i3, int i4, int i5, int i6) {
        this.x = i;
        this.y = i2;
        this.red = i3;
        this.green = i4;
        this.blue = i5;
        this.offset = i6;
    }

    public ColorPoint(Point point, Color color, int i) {
        this(point.x, point.y, color.red, color.green, color.blue, i);
    }

    public ColorPoint(int[] iArr) {
        this.x = iArr[0];
        this.y = iArr[1];
        this.red = iArr[2];
        this.green = iArr[3];
        this.blue = iArr[4];
        this.offset = iArr[5];
    }

    public boolean check(int[][] iArr) {
        return check(iArr, 0, 0);
    }

    public boolean check(int[][] iArr, int i, int i2) {
        int i3 = this.red - this.offset;
        int i4 = this.red + this.offset;
        int i5 = this.green - this.offset;
        int i6 = this.green + this.offset;
        int i7 = this.blue - this.offset;
        int i8 = this.blue + this.offset;
        int i9 = iArr[this.y + i2][this.x + i];
        int i10 = (i9 << 8) >>> 24;
        int i11 = (i9 << 16) >>> 24;
        int i12 = (i9 << 24) >>> 24;
        return i10 >= i3 && i10 <= i4 && i11 >= i5 && i11 <= i6 && i12 >= i7 && i12 <= i8;
    }
}
