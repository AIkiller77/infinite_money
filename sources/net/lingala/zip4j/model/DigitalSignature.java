package net.lingala.zip4j.model;

public class DigitalSignature {
    private int headerSignature;
    private String signatureData;
    private int sizeOfData;

    public DigitalSignature() {
    }

    public int getHeaderSignature() {
        return this.headerSignature;
    }

    public void setHeaderSignature(int i) {
        int i2 = i;
        this.headerSignature = i2;
    }

    public int getSizeOfData() {
        return this.sizeOfData;
    }

    public void setSizeOfData(int i) {
        int i2 = i;
        this.sizeOfData = i2;
    }

    public String getSignatureData() {
        return this.signatureData;
    }

    public void setSignatureData(String str) {
        String str2 = str;
        this.signatureData = str2;
    }
}
