package net.lingala.zip4j.crypto.PBKDF2;

import net.lingala.zip4j.util.Raw;

public class PBKDF2Engine {
    protected PBKDF2Parameters parameters;
    protected PRF prf;

    public PBKDF2Engine() {
        this.parameters = null;
        this.prf = null;
    }

    public PBKDF2Engine(PBKDF2Parameters pBKDF2Parameters) {
        this.parameters = pBKDF2Parameters;
        this.prf = null;
    }

    public PBKDF2Engine(PBKDF2Parameters pBKDF2Parameters, PRF prf2) {
        this.parameters = pBKDF2Parameters;
        this.prf = prf2;
    }

    public byte[] deriveKey(char[] cArr) {
        return deriveKey(cArr, 0);
    }

    public byte[] deriveKey(char[] cArr, int i) {
        Throwable th;
        char[] cArr2 = cArr;
        int i2 = i;
        if (cArr2 == null) {
            Throwable th2 = th;
            new NullPointerException();
            throw th2;
        }
        assertPRF(Raw.convertCharArrayToByteArray(cArr2));
        if (i2 == 0) {
            i2 = this.prf.getHLen();
        }
        return PBKDF2(this.prf, this.parameters.getSalt(), this.parameters.getIterationCount(), i2);
    }

    public boolean verifyKey(char[] cArr) {
        char[] cArr2 = cArr;
        byte[] derivedKey = getParameters().getDerivedKey();
        if (derivedKey == null || derivedKey.length == 0) {
            return false;
        }
        byte[] deriveKey = deriveKey(cArr2, derivedKey.length);
        if (deriveKey == null || deriveKey.length != derivedKey.length) {
            return false;
        }
        for (int i = 0; i < deriveKey.length; i++) {
            if (deriveKey[i] != derivedKey[i]) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void assertPRF(byte[] bArr) {
        PRF prf2;
        byte[] bArr2 = bArr;
        if (this.prf == null) {
            new MacBasedPRF(this.parameters.getHashAlgorithm());
            this.prf = prf2;
        }
        this.prf.init(bArr2);
    }

    public PRF getPseudoRandomFunction() {
        return this.prf;
    }

    /* access modifiers changed from: protected */
    public byte[] PBKDF2(PRF prf2, byte[] bArr, int i, int i2) {
        PRF prf3 = prf2;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        int hLen = prf3.getHLen();
        int ceil = ceil(i4, hLen);
        int i5 = i4 - ((ceil - 1) * hLen);
        byte[] bArr3 = new byte[(ceil * hLen)];
        int i6 = 0;
        for (int i7 = 1; i7 <= ceil; i7++) {
            _F(bArr3, i6, prf3, bArr2, i3, i7);
            i6 += hLen;
        }
        if (i5 >= hLen) {
            return bArr3;
        }
        byte[] bArr4 = new byte[i4];
        System.arraycopy(bArr3, 0, bArr4, 0, i4);
        return bArr4;
    }

    /* access modifiers changed from: protected */
    public int ceil(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        int i5 = 0;
        if (i3 % i4 > 0) {
            i5 = 1;
        }
        return (i3 / i4) + i5;
    }

    /* access modifiers changed from: protected */
    public void _F(byte[] bArr, int i, PRF prf2, byte[] bArr2, int i2, int i3) {
        byte[] bArr3 = bArr;
        int i4 = i;
        PRF prf3 = prf2;
        byte[] bArr4 = bArr2;
        int i5 = i2;
        int hLen = prf3.getHLen();
        byte[] bArr5 = new byte[hLen];
        byte[] bArr6 = new byte[(bArr4.length + 4)];
        System.arraycopy(bArr4, 0, bArr6, 0, bArr4.length);
        INT(bArr6, bArr4.length, i3);
        for (int i6 = 0; i6 < i5; i6++) {
            bArr6 = prf3.doFinal(bArr6);
            xor(bArr5, bArr6);
        }
        System.arraycopy(bArr5, 0, bArr3, i4, hLen);
    }

    /* access modifiers changed from: protected */
    public void xor(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        for (int i = 0; i < bArr3.length; i++) {
            byte[] bArr5 = bArr3;
            int i2 = i;
            bArr5[i2] = (byte) (bArr5[i2] ^ bArr4[i]);
        }
    }

    /* access modifiers changed from: protected */
    public void INT(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        bArr2[i3 + 0] = (byte) (i4 / 16777216);
        bArr2[i3 + 1] = (byte) (i4 / 65536);
        bArr2[i3 + 2] = (byte) (i4 / 256);
        bArr2[i3 + 3] = (byte) i4;
    }

    public PBKDF2Parameters getParameters() {
        return this.parameters;
    }

    public void setParameters(PBKDF2Parameters pBKDF2Parameters) {
        PBKDF2Parameters pBKDF2Parameters2 = pBKDF2Parameters;
        this.parameters = pBKDF2Parameters2;
    }

    public void setPseudoRandomFunction(PRF prf2) {
        PRF prf3 = prf2;
        this.prf = prf3;
    }
}
