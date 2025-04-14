package net.lingala.zip4j.model;

import java.util.ArrayList;

public class CentralDirectory {
    private DigitalSignature digitalSignature;
    private ArrayList fileHeaders;

    public CentralDirectory() {
    }

    public ArrayList getFileHeaders() {
        return this.fileHeaders;
    }

    public void setFileHeaders(ArrayList arrayList) {
        ArrayList arrayList2 = arrayList;
        this.fileHeaders = arrayList2;
    }

    public DigitalSignature getDigitalSignature() {
        return this.digitalSignature;
    }

    public void setDigitalSignature(DigitalSignature digitalSignature2) {
        DigitalSignature digitalSignature3 = digitalSignature2;
        this.digitalSignature = digitalSignature3;
    }
}
