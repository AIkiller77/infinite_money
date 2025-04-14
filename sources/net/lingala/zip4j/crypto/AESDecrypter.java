package net.lingala.zip4j.crypto;

import java.util.Arrays;
import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Engine;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Parameters;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.util.Raw;

public class AESDecrypter implements IDecrypter {
    private int KEY_LENGTH;
    private int MAC_LENGTH;
    private final int PASSWORD_VERIFIER_LENGTH = 2;
    private int SALT_LENGTH;
    private AESEngine aesEngine;
    private byte[] aesKey;
    private byte[] counterBlock;
    private byte[] derivedPasswordVerifier;
    private byte[] iv;
    private LocalFileHeader localFileHeader;
    private int loopCount = 0;
    private MacBasedPRF mac;
    private byte[] macKey;
    private int nonce = 1;
    private byte[] storedMac;

    public AESDecrypter(LocalFileHeader localFileHeader2, byte[] bArr, byte[] bArr2) throws ZipException {
        Throwable th;
        LocalFileHeader localFileHeader3 = localFileHeader2;
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (localFileHeader3 == null) {
            Throwable th2 = th;
            new ZipException("one of the input parameters is null in AESDecryptor Constructor");
            throw th2;
        }
        this.localFileHeader = localFileHeader3;
        this.storedMac = null;
        this.iv = new byte[16];
        this.counterBlock = new byte[16];
        init(bArr3, bArr4);
    }

    private void init(byte[] bArr, byte[] bArr2) throws ZipException {
        Throwable th;
        Throwable th2;
        AESEngine aESEngine;
        MacBasedPRF macBasedPRF;
        Throwable th3;
        StringBuilder sb;
        Throwable th4;
        Throwable th5;
        StringBuilder sb2;
        Throwable th6;
        Throwable th7;
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (this.localFileHeader == null) {
            Throwable th8 = th7;
            new ZipException("invalid file header in init method of AESDecryptor");
            throw th8;
        }
        AESExtraDataRecord aesExtraDataRecord = this.localFileHeader.getAesExtraDataRecord();
        if (aesExtraDataRecord == null) {
            Throwable th9 = th6;
            new ZipException("invalid aes extra data record - in init method of AESDecryptor");
            throw th9;
        }
        switch (aesExtraDataRecord.getAesStrength()) {
            case 1:
                this.KEY_LENGTH = 16;
                this.MAC_LENGTH = 16;
                this.SALT_LENGTH = 8;
                break;
            case 2:
                this.KEY_LENGTH = 24;
                this.MAC_LENGTH = 24;
                this.SALT_LENGTH = 12;
                break;
            case 3:
                this.KEY_LENGTH = 32;
                this.MAC_LENGTH = 32;
                this.SALT_LENGTH = 16;
                break;
            default:
                Throwable th10 = th5;
                new StringBuilder();
                new ZipException(sb2.append("invalid aes key strength for file: ").append(this.localFileHeader.getFileName()).toString());
                throw th10;
        }
        if (this.localFileHeader.getPassword() == null || this.localFileHeader.getPassword().length <= 0) {
            Throwable th11 = th;
            new ZipException("empty or null password provided for AES Decryptor");
            throw th11;
        }
        byte[] deriveKey = deriveKey(bArr3, this.localFileHeader.getPassword());
        if (deriveKey == null || deriveKey.length != this.KEY_LENGTH + this.MAC_LENGTH + 2) {
            Throwable th12 = th2;
            new ZipException("invalid derived key");
            throw th12;
        }
        this.aesKey = new byte[this.KEY_LENGTH];
        this.macKey = new byte[this.MAC_LENGTH];
        this.derivedPasswordVerifier = new byte[2];
        System.arraycopy(deriveKey, 0, this.aesKey, 0, this.KEY_LENGTH);
        System.arraycopy(deriveKey, this.KEY_LENGTH, this.macKey, 0, this.MAC_LENGTH);
        System.arraycopy(deriveKey, this.KEY_LENGTH + this.MAC_LENGTH, this.derivedPasswordVerifier, 0, 2);
        if (this.derivedPasswordVerifier == null) {
            Throwable th13 = th4;
            new ZipException("invalid derived password verifier for AES");
            throw th13;
        } else if (!Arrays.equals(bArr4, this.derivedPasswordVerifier)) {
            Throwable th14 = th3;
            new StringBuilder();
            new ZipException(sb.append("Wrong Password for file: ").append(this.localFileHeader.getFileName()).toString(), 5);
            throw th14;
        } else {
            new AESEngine(this.aesKey);
            this.aesEngine = aESEngine;
            new MacBasedPRF("HmacSHA1");
            this.mac = macBasedPRF;
            this.mac.init(this.macKey);
        }
    }

    public int decryptData(byte[] bArr, int i, int i2) throws ZipException {
        int i3;
        Throwable th;
        Throwable th2;
        byte[] bArr2 = bArr;
        int i4 = i;
        int i5 = i2;
        if (this.aesEngine == null) {
            Throwable th3 = th2;
            new ZipException("AES not initialized properly");
            throw th3;
        }
        int i6 = i4;
        while (i6 < i4 + i5) {
            if (i6 + 16 <= i4 + i5) {
                i3 = 16;
            } else {
                i3 = (i4 + i5) - i6;
            }
            try {
                this.loopCount = i3;
                this.mac.update(bArr2, i6, this.loopCount);
                Raw.prepareBuffAESIVBytes(this.iv, this.nonce, 16);
                int processBlock = this.aesEngine.processBlock(this.iv, this.counterBlock);
                for (int i7 = 0; i7 < this.loopCount; i7++) {
                    bArr2[i6 + i7] = (byte) (bArr2[i6 + i7] ^ this.counterBlock[i7]);
                }
                this.nonce++;
                i6 += 16;
            } catch (ZipException e) {
                throw e;
            } catch (Exception e2) {
                Exception exc = e2;
                Throwable th4 = th;
                new ZipException((Throwable) exc);
                throw th4;
            }
        }
        return i5;
    }

    public int decryptData(byte[] bArr) throws ZipException {
        byte[] bArr2 = bArr;
        return decryptData(bArr2, 0, bArr2.length);
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

    public int getPasswordVerifierLength() {
        return 2;
    }

    public int getSaltLength() {
        return this.SALT_LENGTH;
    }

    public byte[] getCalculatedAuthenticationBytes() {
        return this.mac.doFinal();
    }

    public void setStoredMac(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.storedMac = bArr2;
    }

    public byte[] getStoredMac() {
        return this.storedMac;
    }
}
