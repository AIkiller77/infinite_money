package com.a.a.b;

import com.a.a.b.e;
import java.util.List;

public class f implements CharSequence {
    private int a = 0;
    private final e b;

    public f(e.a aVar) {
        this.b = new e(aVar);
    }

    public f(e eVar) {
        this.b = eVar;
    }

    public f(f fVar) {
        this.b = fVar.b;
    }

    public String a(int i) {
        return this.b.b(i);
    }

    public void a(char c, int i, long j) {
        if (this.b.o(i)) {
            this.b.a(new char[]{c}, i, j, true);
        }
    }

    public void a(int i, int i2, long j) {
        if (this.b.o(i) && i2 > 0) {
            this.b.a(i, Math.min(i2, this.b.d() - i), j, true);
        }
    }

    public void a(int i, long j) {
        if (this.b.o(i)) {
            this.b.a(i, 1, j, true);
        }
    }

    public void a(List<m> list) {
        this.b.a(list);
    }

    public void a(boolean z) {
        this.b.a(z);
    }

    public void a(char[] cArr, int i, long j) {
        if (this.b.o(i) && cArr.length != 0) {
            this.b.a(cArr, i, j, true);
        }
    }

    public boolean a() {
        return this.a >= 0 && this.a < this.b.d();
    }

    public char b() {
        char charAt = this.b.charAt(this.a);
        this.a++;
        return charAt;
    }

    public int b(int i) {
        return this.b.e(i);
    }

    public int c(int i) {
        return this.b.i(i);
    }

    public boolean c() {
        return this.b.h();
    }

    public char charAt(int i) {
        if (this.b.o(i)) {
            return this.b.charAt(i);
        }
        return 0;
    }

    public int d(int i) {
        return this.b.d(i);
    }

    public void d() {
        this.b.i();
    }

    public int e(int i) {
        return this.b.h(i);
    }

    public void e() {
        this.b.j();
    }

    public int f() {
        return this.b.c();
    }

    public int f(int i) {
        if (!this.b.o(i)) {
            i = -1;
        }
        this.a = i;
        return this.a;
    }

    public int g() {
        return this.b.d();
    }

    public int g(int i) {
        return this.b.c(i);
    }

    public void h() {
        this.b.f();
    }

    public List<m> i() {
        return this.b.g();
    }

    public boolean j() {
        return this.b.a();
    }

    public void k() {
        this.b.b();
    }

    public int l() {
        return this.b.k();
    }

    public int length() {
        return this.b.length();
    }

    public int m() {
        return this.b.l();
    }

    public CharSequence subSequence(int i, int i2) {
        return this.b.subSequence(i, i2);
    }

    public String toString() {
        return this.b.toString();
    }
}
