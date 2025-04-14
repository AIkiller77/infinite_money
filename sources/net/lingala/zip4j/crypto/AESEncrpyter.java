package net.lingala.zip4j.crypto;

import java.util.Random;
import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Engine;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Parameters;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.Raw;

public class AESEncrpyter implements IEncrypter {
    private int KEY_LENGTH;
    private int MAC_LENGTH;
    private final int PASSWORD_VERIFIER_LENGTH = 2;
    private int SALT_LENGTH;
    private AESEngine aesEngine;
    private byte[] aesKey;
    private byte[] counterBlock;
    private byte[] derivedPasswordVerifier;
    private boolean finished;
    private byte[] iv;
    private int keyStrength;
    private int loopCount = 0;
    private MacBasedPRF mac;
    private byte[] macKey;
    private int nonce = 1;
    private char[] password;
    private byte[] saltBytes;

    public AESEncrpyter(char[] cArr, int i) throws ZipException {
        Throwable th;
        Throwable th2;
        char[] cArr2 = cArr;
        int i2 = i;
        if (cArr2 == null || cArr2.length == 0) {
            Throwable th3 = th;
            new ZipException("input password is empty or null in AES encrypter constructor");
            throw th3;
        } else if (i2 == 1 || i2 == 3) {
            this.password = cArr2;
            this.keyStrength = i2;
            this.finished = false;
            this.counterBlock = new byte[16];
            this.iv = new byte[16];
            init();
        } else {
            Throwable th4 = th2;
            new ZipException("Invalid key strength in AES encrypter constructor");
            throw th4;
        }
    }

    private void init() throws ZipException {
        Throwable th;
        AESEngine aESEngine;
        MacBasedPRF macBasedPRF;
        Throwable th2;
        switch (this.keyStrength) {
            case 1:
                this.KEY_LENGTH = 16;
                this.MAC_LENGTH = 16;
                this.SALT_LENGTH = 8;
                break;
            case 3:
                this.KEY_LENGTH = 32;
                this.MAC_LENGTH = 32;
                this.SALT_LENGTH = 16;
                break;
            default:
                Throwable th3 = th2;
                new ZipException("invalid aes key strength, cannot determine key sizes");
                throw th3;
        }
        this.saltBytes = generateSalt(this.SALT_LENGTH);
        byte[] deriveKey = deriveKey(this.saltBytes, this.password);
        if (deriveKey == null || deriveKey.length != this.KEY_LENGTH + this.MAC_LENGTH + 2) {
            Throwable th4 = th;
            new ZipException("invalid key generated, cannot decrypt file");
            throw th4;
        }
        this.aesKey = new byte[this.KEY_LENGTH];
        this.macKey = new byte[this.MAC_LENGTH];
        this.derivedPasswordVerifier = new byte[2];
        System.arraycopy(deriveKey, 0, this.aesKey, 0, this.KEY_LENGTH);
        System.arraycopy(deriveKey, this.KEY_LENGTH, this.macKey, 0, this.MAC_LENGTH);
        System.arraycopy(deriveKey, this.KEY_LENGTH + this.MAC_LENGTH, this.derivedPasswordVerifier, 0, 2);
        new AESEngine(this.aesKey);
        this.aesEngine = aESEngine;
        new MacBasedPRF("HmacSHA1");
        this.mac = macBasedPRF;
        this.mac.init(this.macKey);
    }

    private byte[] deriveKey(byte[] bArr, char[] cArr) throws ZipException {
        Throwable th;
        PBKDF2Parameters pBKDF2Parameters;
        PBKDF2Engine pBKDF2Engine;
        char[] cArr2 = cArr;
        try {
            new PBKDF2Parameters("HmacSHA1", "ISO-8859-1", bArr, 1000);
            new PBKDF2Engine(pBKDF2Parameters);
            return pBKDF2Engine.deriveKey(cArr2, this.KEY_LENGTH + this.MAC_LENGTH + 2);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new ZipException((Throwable) exc);
            throw th2;
        }
    }

    public int encryptData(byte[] bArr) throws ZipException {
        Throwable th;
        byte[] bArr2 = bArr;
        if (bArr2 != null) {
            return encryptData(bArr2, 0, bArr2.length);
        }
        Throwable th2 = th;
        new ZipException("input bytes are null, cannot perform AES encrpytion");
        throw th2;
    }

    public int encryptData(byte[] bArr, int i, int i2) throws ZipException {
        Throwable th;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (this.finished) {
            Throwable th2 = th;
            new ZipException("AES Encrypter is in finished state (A non 16 byte block has already been passed to encrypter)");
            throw th2;
        }
        if (i4 % 16 != 0) {
            this.finished = true;
        }
        for (int i5 = i3; i5 < i3 + i4; i5 += 16) {
            this.loopCount = i5 + 16 <= i3 + i4 ? 16 : (i3 + i4) - i5;
            Raw.prepareBuffAESIVBytes(this.iv, this.nonce, 16);
            int processBlock = this.aesEngine.processBlock(this.iv, this.counterBlock);
            for (int i6 = 0; i6 < this.loopCount; i6++) {
                bArr2[i5 + i6] = (byte) (bArr2[i5 + i6] ^ this.counterBlock[i6]);
            }
            this.mac.update(bArr2, i5, this.loopCount);
            this.nonce++;
        }
        return i4;
    }

    private static byte[] generateSalt(int i) throws ZipException {
        Random random;
        Throwable th;
        int i2 = i;
        if (i2 == 8 || i2 == 16) {
            int i3 = 0;
            if (i2 == 8) {
                i3 = 2;
            }
            if (i2 == 16) {
                i3 = 4;
            }
            byte[] bArr = new byte[i2];
            for (int i4 = 0; i4 < i3; i4++) {
                new Random();
                int nextInt = random.nextInt();
                bArr[0 + (i4 * 4)] = (byte) (nextInt >> 24);
                bArr[1 + (i4 * 4)] = (byte) (nextInt >> 16);
                bArr[2 + (i4 * 4)] = (byte) (nextInt >> 8);
                bArr[3 + (i4 * 4)] = (byte) nextInt;
            }
            return bArr;
        }
        Throwable th2 = th;
        new ZipException("invalid salt size, cannot generate salt");
        throw th2;
    }

    public byte[] getFinalMac() {
        byte[] bArr = new byte[10];
        System.arraycopy(this.mac.doFinal(), 0, bArr, 0, 10);
        return bArr;
    }

    public byte[] getDerivedPasswordVerifier() {
        return this.derivedPasswordVerifier;
    }

    public void setDerivedPasswordVerifier(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.derivedPasswordVerifier = bArr2;
    }

    public byte[] getSaltBytes() {
        return this.saltBytes;
    }

    public void setSaltBytes(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.saltBytes = bArr2;
    }

    public int getSaltLength() {
        return this.SALT_LENGTH;
    }

    public int getPasswordVeriifierLength() {
        return 2;
    }
}
