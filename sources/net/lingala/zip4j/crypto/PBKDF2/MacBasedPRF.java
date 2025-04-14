package net.lingala.zip4j.crypto.PBKDF2;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MacBasedPRF implements PRF {
    protected int hLen;
    protected Mac mac;
    protected String macAlgorithm;

    public MacBasedPRF(String str) {
        Throwable th;
        String str2 = str;
        this.macAlgorithm = str2;
        try {
            this.mac = Mac.getInstance(str2);
            this.hLen = this.mac.getMacLength();
        } catch (NoSuchAlgorithmException e) {
            NoSuchAlgorithmException noSuchAlgorithmException = e;
            Throwable th2 = th;
            new RuntimeException(noSuchAlgorithmException);
            throw th2;
        }
    }

    public MacBasedPRF(String str, String str2) {
        Throwable th;
        Throwable th2;
        String str3 = str;
        this.macAlgorithm = str3;
        try {
            this.mac = Mac.getInstance(str3, str2);
            this.hLen = this.mac.getMacLength();
        } catch (NoSuchAlgorithmException e) {
            NoSuchAlgorithmException noSuchAlgorithmException = e;
            Throwable th3 = th2;
            new RuntimeException(noSuchAlgorithmException);
            throw th3;
        } catch (NoSuchProviderException e2) {
            NoSuchProviderException noSuchProviderException = e2;
            Throwable th4 = th;
            new RuntimeException(noSuchProviderException);
            throw th4;
        }
    }

    public byte[] doFinal(byte[] bArr) {
        return this.mac.doFinal(bArr);
    }

    public byte[] doFinal() {
        return this.mac.doFinal();
    }

    public int getHLen() {
        return this.hLen;
    }

    public void init(byte[] bArr) {
        Throwable th;
        Key key;
        try {
            new SecretKeySpec(bArr, this.macAlgorithm);
            this.mac.init(key);
        } catch (InvalidKeyException e) {
            InvalidKeyException invalidKeyException = e;
            Throwable th2 = th;
            new RuntimeException(invalidKeyException);
            throw th2;
        }
    }

    public void update(byte[] bArr) {
        Throwable th;
        try {
            this.mac.update(bArr);
        } catch (IllegalStateException e) {
            IllegalStateException illegalStateException = e;
            Throwable th2 = th;
            new RuntimeException(illegalStateException);
            throw th2;
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        Throwable th;
        try {
            this.mac.update(bArr, i, i2);
        } catch (IllegalStateException e) {
            IllegalStateException illegalStateException = e;
            Throwable th2 = th;
            new RuntimeException(illegalStateException);
            throw th2;
        }
    }
}
