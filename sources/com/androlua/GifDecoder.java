package com.androlua;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentTransaction;
import com.a.a.a.a.a.a.a;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class GifDecoder extends Thread {
    public static final int STATUS_FINISH = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARSING = 0;
    private boolean A = false;
    private byte[] B = new byte[256];
    private int C = 0;
    private int D = 0;
    private int E = 0;
    private boolean F = false;
    private int G = 0;
    private int H;
    private short[] I;
    private byte[] J;
    private byte[] K;
    private byte[] L;
    private GifFrame M;
    private int N;
    private GifAction O = null;
    private byte[] P = null;
    private InputStream a;
    private int b;
    private boolean c;
    private int d;
    private int e = 1;
    private int[] f;
    private int[] g;
    private int[] h;
    public int height;
    private int i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;

    /* renamed from: q  reason: collision with root package name */
    private int f20q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    public int width;
    private Bitmap x;
    private Bitmap y;
    private GifFrame z = null;

    public interface GifAction {
        void parseOk(boolean z, int i);
    }

    public static class GifFrame {
        public int delay;
        public Bitmap image;
        public GifFrame nextFrame = null;

        public GifFrame(Bitmap bitmap, int i) {
            this.image = bitmap;
            this.delay = i;
        }
    }

    public GifDecoder(InputStream inputStream, GifAction gifAction) {
        this.a = inputStream;
        this.O = gifAction;
    }

    public GifDecoder(String str, GifAction gifAction) {
        this.a = new FileInputStream(str);
        this.O = gifAction;
    }

    public GifDecoder(byte[] bArr, GifAction gifAction) {
        this.P = bArr;
        this.O = gifAction;
    }

    private void a() {
        int i2;
        int[] iArr = new int[(this.width * this.height)];
        int i3 = 0;
        if (this.E > 0) {
            if (this.E == 3) {
                int i4 = this.N - 2;
                this.y = i4 > 0 ? getFrameImage(i4 - 1) : null;
            }
            if (this.y != null) {
                this.y.getPixels(iArr, 0, this.width, 0, 0, this.width, this.height);
                if (this.E == 2) {
                    int i5 = !this.F ? this.k : 0;
                    for (int i6 = 0; i6 < this.w; i6++) {
                        int i7 = ((this.u + i6) * this.width) + this.t;
                        int i8 = this.v + i7;
                        while (i7 < i8) {
                            iArr[i7] = i5;
                            i7++;
                        }
                    }
                }
            }
        }
        int i9 = 0;
        int i10 = 1;
        int i11 = 8;
        while (i3 < this.s) {
            if (this.n) {
                if (i9 >= this.s) {
                    i10++;
                    switch (i10) {
                        case 2:
                            i9 = 4;
                            break;
                        case 3:
                            i9 = 2;
                            i11 = 4;
                            break;
                        case 4:
                            i9 = 1;
                            i11 = 2;
                            break;
                    }
                }
                i2 = i9 + i11;
            } else {
                i2 = i9;
                i9 = i3;
            }
            int i12 = i9 + this.f20q;
            if (i12 < this.height) {
                int i13 = i12 * this.width;
                int i14 = this.p + i13;
                int i15 = this.r + i14;
                if (this.width + i13 < i15) {
                    i15 = this.width + i13;
                }
                int i16 = this.r * i3;
                while (i14 < i15) {
                    int i17 = i16 + 1;
                    int i18 = this.h[this.L[i16] & 255];
                    if (i18 != 0) {
                        iArr[i14] = i18;
                    }
                    i14++;
                    i16 = i17;
                }
            }
            i3++;
            i9 = i2;
        }
        this.x = Bitmap.createBitmap(iArr, this.width, this.height, Bitmap.Config.ARGB_4444);
    }

    private int[] a(int i2) {
        int i3;
        int i4 = i2 * 3;
        byte[] bArr = new byte[i4];
        try {
            i3 = this.a.read(bArr);
        } catch (Exception e2) {
            a.a(e2);
            i3 = 0;
        }
        if (i3 < i4) {
            this.b = 1;
            return null;
        }
        int[] iArr = new int[256];
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = i5 + 1;
            int i8 = i7 + 1;
            iArr[i6] = ((bArr[i5] & 255) << 16) | -16777216 | ((bArr[i7] & 255) << 8) | (bArr[i8] & 255);
            i5 = i8 + 1;
        }
        return iArr;
    }

    private int b() {
        this.a = new ByteArrayInputStream(this.P);
        this.P = null;
        return c();
    }

    private int c() {
        f();
        if (this.a != null) {
            k();
            if (!e()) {
                i();
                if (this.N < 0) {
                    this.b = 1;
                } else {
                    this.b = -1;
                    this.O.parseOk(true, -1);
                    this.a.close();
                }
            }
            this.O.parseOk(false, -1);
            try {
                this.a.close();
            } catch (Exception e2) {
                a.a(e2);
            }
        } else {
            this.b = 2;
            this.O.parseOk(false, -1);
        }
        return this.b;
    }

    private void d() {
        int i2;
        int i3;
        byte b2;
        int i4;
        short s2;
        int i5 = this.r * this.s;
        if (this.L == null || this.L.length < i5) {
            this.L = new byte[i5];
        }
        if (this.I == null) {
            this.I = new short[4096];
        }
        if (this.J == null) {
            this.J = new byte[4096];
        }
        if (this.K == null) {
            this.K = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        int g2 = g();
        int i6 = 1 << g2;
        int i7 = i6 + 1;
        int i8 = i6 + 2;
        int i9 = g2 + 1;
        int i10 = (1 << i9) - 1;
        for (int i11 = 0; i11 < i6; i11++) {
            this.I[i11] = 0;
            this.J[i11] = (byte) i11;
        }
        int i12 = i9;
        int i13 = i8;
        int i14 = i10;
        byte b3 = -1;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        byte b4 = 0;
        int i21 = 0;
        while (i15 < i5) {
            if (i16 == 0) {
                if (i17 >= i12) {
                    byte b5 = i19 & i14;
                    i19 >>= i12;
                    i17 -= i12;
                    if (b5 > i13 || b5 == i7) {
                        break;
                    } else if (b5 == i6) {
                        i12 = i9;
                        i13 = i8;
                        i14 = i10;
                        b3 = -1;
                    } else if (b3 == -1) {
                        this.K[i16] = this.J[b5];
                        b3 = b5;
                        b4 = b3;
                        i16++;
                        i9 = i9;
                    } else {
                        i3 = i9;
                        if (b5 == i13) {
                            i4 = i16 + 1;
                            b2 = b5;
                            this.K[i16] = (byte) b4;
                            s2 = b3;
                        } else {
                            b2 = b5;
                            i4 = i16;
                            s2 = b2;
                        }
                        while (s2 > i6) {
                            this.K[i4] = this.J[s2];
                            s2 = this.I[s2];
                            i4++;
                            i6 = i6;
                        }
                        i2 = i6;
                        byte b6 = this.J[s2] & 255;
                        if (i13 >= 4096) {
                            break;
                        }
                        i16 = i4 + 1;
                        byte b7 = (byte) b6;
                        this.K[i4] = b7;
                        this.I[i13] = (short) b3;
                        this.J[i13] = b7;
                        i13++;
                        if ((i13 & i14) == 0) {
                            if (i13 < 4096) {
                                i12++;
                                i14 += i13;
                            }
                        }
                        b4 = b6;
                        b3 = b2;
                    }
                } else {
                    if (i18 == 0) {
                        i18 = h();
                        if (i18 <= 0) {
                            break;
                        }
                        i20 = 0;
                    }
                    i19 += (this.B[i20] & 255) << i17;
                    i17 += 8;
                    i20++;
                    i18--;
                }
            } else {
                i3 = i9;
                i2 = i6;
                byte b8 = b4;
            }
            i16--;
            this.L[i21] = this.K[i16];
            i15++;
            i21++;
            i9 = i3;
            i6 = i2;
        }
        for (int i22 = i21; i22 < i5; i22++) {
            this.L[i22] = 0;
        }
    }

    private boolean e() {
        return this.b != 0;
    }

    private void f() {
        this.b = 0;
        this.N = 0;
        this.M = null;
        this.f = null;
        this.g = null;
    }

    private int g() {
        try {
            return this.a.read();
        } catch (Exception unused) {
            this.b = 1;
            return 0;
        }
    }

    private int h() {
        this.C = g();
        int i2 = 0;
        if (this.C > 0) {
            while (i2 < this.C) {
                try {
                    int read = this.a.read(this.B, i2, this.C - i2);
                    if (read == -1) {
                        break;
                    }
                    i2 += read;
                } catch (Exception e2) {
                    a.a(e2);
                }
            }
            if (i2 < this.C) {
                this.b = 1;
            }
        }
        return i2;
    }

    private void i() {
        boolean z2 = false;
        while (!z2 && !e()) {
            int g2 = g();
            if (g2 != 0) {
                if (g2 == 33) {
                    int g3 = g();
                    if (g3 != 249) {
                        if (g3 == 255) {
                            h();
                            String str = "";
                            for (int i2 = 0; i2 < 11; i2++) {
                                str = str + ((char) this.B[i2]);
                            }
                            if (str.equals("NETSCAPE2.0")) {
                                n();
                            }
                        }
                        q();
                    } else {
                        j();
                    }
                } else if (g2 == 44) {
                    l();
                } else if (g2 != 59) {
                    this.b = 1;
                } else {
                    z2 = true;
                }
            }
        }
    }

    private void j() {
        g();
        int g2 = g();
        this.D = (g2 & 28) >> 2;
        boolean z2 = true;
        if (this.D == 0) {
            this.D = 1;
        }
        if ((g2 & 1) == 0) {
            z2 = false;
        }
        this.F = z2;
        this.G = o() * 10;
        this.H = g();
        g();
    }

    private void k() {
        String str = "";
        for (int i2 = 0; i2 < 6; i2++) {
            str = str + ((char) g());
        }
        if (!str.startsWith("GIF")) {
            this.b = 1;
            return;
        }
        m();
        if (this.c && !e()) {
            this.f = a(this.d);
            this.j = this.f[this.i];
        }
    }

    private void l() {
        int i2;
        this.p = o();
        this.f20q = o();
        this.r = o();
        this.s = o();
        int g2 = g();
        this.m = (g2 & 128) != 0;
        this.n = (g2 & 64) != 0;
        this.o = 2 << (g2 & 7);
        if (this.m) {
            this.g = a(this.o);
            this.h = this.g;
        } else {
            this.h = this.f;
            if (this.i == this.H) {
                this.j = 0;
            }
        }
        if (this.F) {
            i2 = this.h[this.H];
            this.h[this.H] = 0;
        } else {
            i2 = 0;
        }
        if (this.h == null) {
            this.b = 1;
        }
        if (!e()) {
            d();
            q();
            if (!e()) {
                this.N++;
                this.x = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_4444);
                a();
                if (this.M == null) {
                    this.M = new GifFrame(this.x, this.G);
                    this.z = this.M;
                } else {
                    GifFrame gifFrame = this.M;
                    while (gifFrame.nextFrame != null) {
                        gifFrame = gifFrame.nextFrame;
                    }
                    gifFrame.nextFrame = new GifFrame(this.x, this.G);
                }
                if (this.F) {
                    this.h[this.H] = i2;
                }
                p();
                this.O.parseOk(true, this.N);
            }
        }
    }

    private void m() {
        this.width = o();
        this.height = o();
        int g2 = g();
        this.c = (g2 & 128) != 0;
        this.d = 2 << (g2 & 7);
        this.i = g();
        this.l = g();
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void n() {
        /*
            r3 = this;
        L_0x0000:
            r3.h()
            byte[] r0 = r3.B
            r1 = 0
            byte r0 = r0[r1]
            r1 = 1
            if (r0 != r1) goto L_0x001d
            byte[] r0 = r3.B
            byte r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte[] r1 = r3.B
            r2 = 2
            byte r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 8
            r0 = r0 | r1
            r3.e = r0
        L_0x001d:
            int r0 = r3.C
            if (r0 <= 0) goto L_0x0027
            boolean r0 = r3.e()
            if (r0 == 0) goto L_0x0000
        L_0x0027:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.GifDecoder.n():void");
    }

    private int o() {
        return g() | (g() << 8);
    }

    private void p() {
        this.E = this.D;
        this.t = this.p;
        this.u = this.f20q;
        this.v = this.r;
        this.w = this.s;
        this.y = this.x;
        this.k = this.j;
        this.D = 0;
        this.F = false;
        this.G = 0;
        this.g = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void q() {
        /*
            r1 = this;
        L_0x0000:
            r1.h()
            int r0 = r1.C
            if (r0 <= 0) goto L_0x000d
            boolean r0 = r1.e()
            if (r0 == 0) goto L_0x0000
        L_0x000d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.GifDecoder.q():void");
    }

    public void free() {
        while (true) {
            GifFrame gifFrame = this.M;
            if (gifFrame == null) {
                break;
            }
            gifFrame.image = null;
            this.M = this.M.nextFrame;
        }
        if (this.a != null) {
            try {
                this.a.close();
            } catch (Exception unused) {
            }
            this.a = null;
        }
        this.P = null;
    }

    public GifFrame getCurrentFrame() {
        return this.z;
    }

    public int getDelay(int i2) {
        GifFrame frame;
        this.G = -1;
        if (i2 >= 0 && i2 < this.N && (frame = getFrame(i2)) != null) {
            this.G = frame.delay;
        }
        return this.G;
    }

    public int[] getDelays() {
        GifFrame gifFrame = this.M;
        int[] iArr = new int[this.N];
        int i2 = 0;
        while (gifFrame != null && i2 < this.N) {
            iArr[i2] = gifFrame.delay;
            gifFrame = gifFrame.nextFrame;
            i2++;
        }
        return iArr;
    }

    public GifFrame getFrame(int i2) {
        GifFrame gifFrame = this.M;
        int i3 = 0;
        while (gifFrame != null) {
            if (i3 == i2) {
                return gifFrame;
            }
            gifFrame = gifFrame.nextFrame;
            i3++;
        }
        return null;
    }

    public int getFrameCount() {
        return this.N;
    }

    public Bitmap getFrameImage(int i2) {
        GifFrame frame = getFrame(i2);
        if (frame == null) {
            return null;
        }
        return frame.image;
    }

    public Bitmap getImage() {
        return getFrameImage(0);
    }

    public int getLoopCount() {
        return this.e;
    }

    public int getStatus() {
        return this.b;
    }

    public GifFrame next() {
        GifFrame gifFrame;
        if (!this.A) {
            this.A = true;
            return this.M;
        }
        if (this.b == 0) {
            if (this.z.nextFrame != null) {
                gifFrame = this.z.nextFrame;
            }
            return this.z;
        }
        this.z = this.z.nextFrame;
        if (this.z == null) {
            gifFrame = this.M;
        }
        return this.z;
        this.z = gifFrame;
        return this.z;
    }

    public boolean parseOk() {
        return this.b == -1;
    }

    public void reset() {
        this.z = this.M;
    }

    public void run() {
        if (this.a != null) {
            c();
        } else if (this.P != null) {
            b();
        }
    }
}
