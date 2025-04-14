package net.lingala.zip4j.model;

public class ArchiveExtraDataRecord {
    private String extraFieldData;
    private int extraFieldLength;
    private int signature;

    public ArchiveExtraDataRecord() {
    }

    public int getSignature() {
        return this.signature;
    }

    public void setSignature(int i) {
        int i2 = i;
        this.signature = i2;
    }

    public int getExtraFieldLength() {
        return this.extraFieldLength;
    }

    public void setExtraFieldLength(int i) {
        int i2 = i;
        this.extraFieldLength = i2;
    }

    public String getExtraFieldData() {
        return this.extraFieldData;
    }

    public void setExtraFieldData(String str) {
        String str2 = str;
        this.extraFieldData = str2;
    }
}
