package com.tencent.open.utils;

import android.util.Base64;
import com.tencent.open.a.f;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: ProGuard */
public class d {
    private static byte[] a = {1, 2, 3, 4, 5, 6, 7, 8};

    public static String a(String str, String str2) {
        StringBuilder sb;
        AlgorithmParameterSpec algorithmParameterSpec;
        Key key;
        String str3 = str;
        String str4 = str2;
        try {
            new IvParameterSpec(a);
            new SecretKeySpec(str4.getBytes(), "DES");
            Key key2 = key;
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(1, key2, algorithmParameterSpec);
            return Base64.encodeToString(instance.doFinal(str3.getBytes()), 0);
        } catch (Exception e) {
            new StringBuilder();
            f.c("DESUtils", sb.append("encode ").append(e.toString()).toString());
            return null;
        }
    }

    public static String b(String str, String str2) {
        StringBuilder sb;
        AlgorithmParameterSpec algorithmParameterSpec;
        Key key;
        String str3;
        String str4 = str2;
        try {
            byte[] decode = Base64.decode(str, 0);
            new IvParameterSpec(a);
            new SecretKeySpec(str4.getBytes(), "DES");
            Key key2 = key;
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(2, key2, algorithmParameterSpec);
            String str5 = str3;
            new String(instance.doFinal(decode));
            return str5;
        } catch (Exception e) {
            new StringBuilder();
            f.c("DESUtils", sb.append("decode ").append(e.toString()).toString());
            return null;
        }
    }
}
