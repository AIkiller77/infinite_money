package com.b.a.b;

import java.util.ArrayList;

public class e extends o {
    private boolean f = false;
    private a g;
    private ArrayList<Integer> h;

    public interface a {
        int getAdvance(char c);

        int getRowWidth();
    }

    public e(a aVar) {
        this.g = aVar;
        m();
    }

    private void a(int i, int i2) {
        while (i < this.h.size() && this.h.get(i).intValue() <= i2) {
            this.h.remove(i);
        }
    }

    private void a(int i, int i2, int i3) {
        if (i > 0) {
            i--;
        }
        int intValue = this.h.get(i).intValue();
        int i4 = i + 1;
        a(i4, i2 - i3);
        b(i4, i3);
        b(i4, intValue, i2);
    }

    private void b(int i, int i2) {
        while (i < this.h.size()) {
            this.h.set(i, Integer.valueOf(this.h.get(i).intValue() + i2));
            i++;
        }
    }

    private void b(int i, int i2, int i3) {
        if (!this.f) {
            int p = p(i2);
            int p2 = p(i3);
            ArrayList arrayList = new ArrayList();
            while (p < p2) {
                if (p == this.b) {
                    p = this.c;
                }
                if (this.a[p] == 10) {
                    arrayList.add(Integer.valueOf(q(p) + 1));
                }
                p++;
            }
            this.h.addAll(i, arrayList);
        } else if (!n()) {
            q.a("Not enough space to do word wrap");
        } else {
            ArrayList arrayList2 = new ArrayList();
            int p3 = p(i2);
            int p4 = p(i3);
            int rowWidth = this.g.getRowWidth();
            int i4 = i2;
            int i5 = rowWidth;
            int i6 = 0;
            while (p3 < p4) {
                if (p3 == this.b) {
                    p3 = this.c;
                }
                char c = this.a[p3];
                i6 += this.g.getAdvance(c);
                if (c == ' ' || c == 9 || c == 10 || c == 65535) {
                    if (i6 <= i5) {
                        i5 -= i6;
                    } else if (i6 > rowWidth) {
                        int p5 = p(i4);
                        if (i4 != i2 && (arrayList2.isEmpty() || i4 != ((Integer) arrayList2.get(arrayList2.size() - 1)).intValue())) {
                            arrayList2.add(Integer.valueOf(i4));
                        }
                        i5 = rowWidth;
                        while (p5 <= p3) {
                            if (p5 == this.b) {
                                p5 = this.c;
                            }
                            int advance = this.g.getAdvance(this.a[p5]);
                            if (advance > i5) {
                                arrayList2.add(Integer.valueOf(q(p5)));
                                i5 = rowWidth - advance;
                            } else {
                                i5 -= advance;
                            }
                            p5++;
                        }
                    } else {
                        arrayList2.add(Integer.valueOf(i4));
                        i5 = rowWidth - i6;
                    }
                    i4 = q(p3) + 1;
                    i6 = 0;
                }
                if (c == 10) {
                    arrayList2.add(Integer.valueOf(i4));
                    i5 = rowWidth;
                }
                p3++;
            }
            this.h.addAll(i, arrayList2);
        }
    }

    private void m() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        this.h = arrayList;
    }

    private boolean n() {
        return this.g.getRowWidth() >= this.g.getAdvance('M') * 2;
    }

    private int s(int i) {
        int p = p(i);
        while (p < this.a.length) {
            if (p == this.b) {
                p = this.c;
            }
            if (this.a[p] == 10 || this.a[p] == 65535) {
                break;
            }
            p++;
        }
        return q(p) + 1;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(int i) {
        super.a(i);
        if (i != 0) {
            a(e(i > 0 ? this.b - i : this.b), s(this.b), i);
        }
    }

    public synchronized void a(int i, int i2, long j, boolean z) {
        super.a(i, i2, j, z);
        a(e(i), s(i), -i2);
    }

    public void a(CharSequence charSequence) {
        int length = charSequence.length();
        char[] cArr = new char[o.g(length)];
        int i = 1;
        for (int i2 = 0; i2 < length; i2++) {
            cArr[i2] = charSequence.charAt(i2);
            if (charSequence.charAt(i2) == 10) {
                i++;
            }
        }
        a(cArr, length, i);
    }

    public void a(boolean z) {
        boolean z2;
        if (z && !this.f) {
            z2 = true;
        } else if (!z && this.f) {
            z2 = false;
        } else {
            return;
        }
        this.f = z2;
        b();
    }

    public synchronized void a(char[] cArr, int i, long j, boolean z) {
        super.a(cArr, i, j, z);
        a(e(i), s(i + cArr.length), cArr.length);
    }

    public boolean a() {
        return this.f;
    }

    public String b(int i) {
        int c = c(i);
        return c == 0 ? new String() : subSequence(this.h.get(i).intValue(), c).toString();
    }

    public void b() {
        m();
        if (!this.f || n()) {
            b(1, 0, d());
        } else if (this.g.getRowWidth() > 0) {
            q.a("Text field has non-zero width but still too small for word wrap");
        }
    }

    public int c() {
        return this.h.size();
    }

    public int c(int i) {
        if (f(i)) {
            return 0;
        }
        return (i != this.h.size() + -1 ? this.h.get(i + 1).intValue() : d()) - this.h.get(i).intValue();
    }

    public int d(int i) {
        if (f(i)) {
            return -1;
        }
        return this.h.get(i).intValue();
    }

    public int e(int i) {
        if (!o(i)) {
            return -1;
        }
        int size = this.h.size() - 1;
        int i2 = 0;
        while (size >= i2) {
            int i3 = (i2 + size) / 2;
            int i4 = i3 + 1;
            int intValue = i4 < this.h.size() ? this.h.get(i4).intValue() : d();
            if (i >= this.h.get(i3).intValue() && i < intValue) {
                return i3;
            }
            if (i >= intValue) {
                i2 = i4;
            } else {
                size = i3 - 1;
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public boolean f(int i) {
        return i < 0 || i >= this.h.size();
    }
}
