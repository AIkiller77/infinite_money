package com.android.cglib.dx.c.c;

import com.android.cglib.dx.d.c;

public final class v extends x {
    public static final v a = new v("");
    private final String b;
    private final c c;

    public v(String str) {
        if (str == null) {
            throw new NullPointerException("string == null");
        }
        this.b = str.intern();
        this.c = new c(a(str));
    }

    public static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length * 3)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt != 0 && charAt < 128) {
                bArr[i] = (byte) charAt;
                i++;
            } else if (charAt < 2048) {
                bArr[i] = (byte) (((charAt >> 6) & 31) | 192);
                bArr[i + 1] = (byte) ((charAt & '?') | 128);
                i += 2;
            } else {
                bArr[i] = (byte) (((charAt >> 12) & 15) | 224);
                bArr[i + 1] = (byte) (((charAt >> 6) & 63) | 128);
                bArr[i + 2] = (byte) ((charAt & '?') | 128);
                i += 3;
            }
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    public String a(int i) {
        String str;
        String a_ = a_();
        if (a_.length() <= i - 2) {
            str = "";
        } else {
            a_ = a_.substring(0, i - 5);
            str = "...";
        }
        return '\"' + a_ + str + '\"';
    }

    public String a_() {
        String str;
        int length = this.b.length();
        StringBuilder sb = new StringBuilder((length * 3) / 2);
        int i = 0;
        while (i < length) {
            char charAt = this.b.charAt(i);
            if (charAt < ' ' || charAt >= 127) {
                if (charAt <= 127) {
                    if (charAt != 13) {
                        switch (charAt) {
                            case 9:
                                str = "\\t";
                                break;
                            case 10:
                                str = "\\n";
                                break;
                            default:
                                char charAt2 = i < length + -1 ? this.b.charAt(i + 1) : 0;
                                boolean z = charAt2 >= '0' && charAt2 <= '7';
                                sb.append('\\');
                                for (int i2 = 6; i2 >= 0; i2 -= 3) {
                                    char c2 = (char) (((charAt >> i2) & 7) + 48);
                                    if (c2 != '0' || z) {
                                        sb.append(c2);
                                        z = true;
                                    }
                                }
                                if (!z) {
                                    sb.append('0');
                                    break;
                                } else {
                                    continue;
                                }
                                break;
                        }
                    } else {
                        str = "\\r";
                    }
                    sb.append(str);
                    i++;
                } else {
                    sb.append("\\u");
                    sb.append(Character.forDigit(charAt >> 12, 16));
                    sb.append(Character.forDigit((charAt >> 8) & 15, 16));
                    sb.append(Character.forDigit((charAt >> 4) & 15, 16));
                    charAt = Character.forDigit(charAt & 15, 16);
                }
            } else if (charAt == '\'' || charAt == '\"' || charAt == '\\') {
                sb.append('\\');
            }
            sb.append(charAt);
            i++;
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public int b(a aVar) {
        return this.b.compareTo(((v) aVar).b);
    }

    public com.android.cglib.dx.c.d.c b() {
        return com.android.cglib.dx.c.d.c.f19q;
    }

    public String e() {
        return "utf8";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof v)) {
            return false;
        }
        return this.b.equals(((v) obj).b);
    }

    public String f() {
        return '\"' + a_() + '\"';
    }

    public String g() {
        return this.b;
    }

    public c h() {
        return this.c;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public int i() {
        return this.c.a();
    }

    public int j() {
        return this.b.length();
    }

    public String toString() {
        return "string{\"" + a_() + "\"}";
    }
}
