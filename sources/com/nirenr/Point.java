package com.nirenr;

public class Point {
    public int t;
    public int x;
    public int y;

    public Point(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public Point(int i, int i2, int i3) {
        this.x = i;
        this.y = i2;
        this.t = i3;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public final boolean equals(int i, int i2) {
        return this.x == i && this.y == i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point point = (Point) obj;
        return this.x == point.x && this.y == point.y;
    }

    public int hashCode() {
        return (this.x * 31) + this.y;
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }

    public final void offset(int i, int i2) {
        this.x += i;
        this.y += i2;
    }

    public void set(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public String toString() {
        return "Point(" + this.x + ", " + this.y + ": " + this.t + ")";
    }
}
