package net.lingala.zip4j.crypto.PBKDF2;

class BinTools {
    public static final String hex = "0123456789ABCDEF";

    BinTools() {
    }

    public static String bin2hex(byte[] bArr) {
        StringBuffer stringBuffer;
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            return "";
        }
        new StringBuffer(2 * bArr2.length);
        StringBuffer stringBuffer2 = stringBuffer;
        for (int i = 0; i < bArr2.length; i++) {
            int i2 = (256 + bArr2[i]) % 256;
            StringBuffer append = stringBuffer2.append(hex.charAt((i2 / 16) & 15));
            StringBuffer append2 = stringBuffer2.append(hex.charAt((i2 % 16) & 15));
        }
        return stringBuffer2.toString();
    }

    public static byte[] hex2bin(String str) {
        StringBuilder sb;
        String str2 = str;
        String str3 = str2;
        if (str2 == null) {
            str3 = "";
        } else if (str2.length() % 2 != 0) {
            new StringBuilder();
            str3 = sb.append("0").append(str2).toString();
        }
        byte[] bArr = new byte[(str3.length() / 2)];
        int i = 0;
        int i2 = 0;
        while (i < str3.length()) {
            int i3 = i;
            int i4 = i + 1;
            char charAt = str3.charAt(i3);
            int i5 = i4;
            i = i4 + 1;
            bArr[i2] = (byte) ((hex2bin(charAt) * 16) + hex2bin(str3.charAt(i5)));
            i2++;
        }
        return bArr;
    }

    public static int hex2bin(char c) {
        Throwable th;
        StringBuilder sb;
        char c2 = c;
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        if (c2 >= 'A' && c2 <= 'F') {
            return (c2 - 'A') + 10;
        }
        if (c2 >= 'a' && c2 <= 'f') {
            return (c2 - 'a') + 10;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Input string may only contain hex digits, but found '").append(c2).append("'").toString());
        throw th2;
    }
}
