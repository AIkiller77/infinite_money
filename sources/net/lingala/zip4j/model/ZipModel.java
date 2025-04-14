package net.lingala.zip4j.model;

import java.util.List;

public class ZipModel implements Cloneable {
    private ArchiveExtraDataRecord archiveExtraDataRecord;
    private CentralDirectory centralDirectory;
    private List dataDescriptorList;
    private long end;
    private EndCentralDirRecord endCentralDirRecord;
    private String fileNameCharset;
    private boolean isNestedZipFile;
    private boolean isZip64Format;
    private List localFileHeaderList;
    private boolean splitArchive;
    private long splitLength = -1;
    private long start;
    private Zip64EndCentralDirLocator zip64EndCentralDirLocator;
    private Zip64EndCentralDirRecord zip64EndCentralDirRecord;
    private String zipFile;

    public ZipModel() {
    }

    public List getLocalFileHeaderList() {
        return this.localFileHeaderList;
    }

    public void setLocalFileHeaderList(List list) {
        List list2 = list;
        this.localFileHeaderList = list2;
    }

    public List getDataDescriptorList() {
        return this.dataDescriptorList;
    }

    public void setDataDescriptorList(List list) {
        List list2 = list;
        this.dataDescriptorList = list2;
    }

    public CentralDirectory getCentralDirectory() {
        return this.centralDirectory;
    }

    public void setCentralDirectory(CentralDirectory centralDirectory2) {
        CentralDirectory centralDirectory3 = centralDirectory2;
        this.centralDirectory = centralDirectory3;
    }

    public EndCentralDirRecord getEndCentralDirRecord() {
        return this.endCentralDirRecord;
    }

    public void setEndCentralDirRecord(EndCentralDirRecord endCentralDirRecord2) {
        EndCentralDirRecord endCentralDirRecord3 = endCentralDirRecord2;
        this.endCentralDirRecord = endCentralDirRecord3;
    }

    public ArchiveExtraDataRecord getArchiveExtraDataRecord() {
        return this.archiveExtraDataRecord;
    }

    public void setArchiveExtraDataRecord(ArchiveExtraDataRecord archiveExtraDataRecord2) {
        ArchiveExtraDataRecord archiveExtraDataRecord3 = archiveExtraDataRecord2;
        this.archiveExtraDataRecord = archiveExtraDataRecord3;
    }

    public boolean isSplitArchive() {
        return this.splitArchive;
    }

    public void setSplitArchive(boolean z) {
        boolean z2 = z;
        this.splitArchive = z2;
    }

    public String getZipFile() {
        return this.zipFile;
    }

    public void setZipFile(String str) {
        String str2 = str;
        this.zipFile = str2;
    }

    public Zip64EndCentralDirLocator getZip64EndCentralDirLocator() {
        return this.zip64EndCentralDirLocator;
    }

    public void setZip64EndCentralDirLocator(Zip64EndCentralDirLocator zip64EndCentralDirLocator2) {
        Zip64EndCentralDirLocator zip64EndCentralDirLocator3 = zip64EndCentralDirLocator2;
        this.zip64EndCentralDirLocator = zip64EndCentralDirLocator3;
    }

    public Zip64EndCentralDirRecord getZip64EndCentralDirRecord() {
        return this.zip64EndCentralDirRecord;
    }

    public void setZip64EndCentralDirRecord(Zip64EndCentralDirRecord zip64EndCentralDirRecord2) {
        Zip64EndCentralDirRecord zip64EndCentralDirRecord3 = zip64EndCentralDirRecord2;
        this.zip64EndCentralDirRecord = zip64EndCentralDirRecord3;
    }

    public boolean isZip64Format() {
        return this.isZip64Format;
    }

    public void setZip64Format(boolean z) {
        boolean z2 = z;
        this.isZip64Format = z2;
    }

    public boolean isNestedZipFile() {
        return this.isNestedZipFile;
    }

    public void setNestedZipFile(boolean z) {
        boolean z2 = z;
        this.isNestedZipFile = z2;
    }

    public long getStart() {
        return this.start;
    }

    public void setStart(long j) {
        long j2 = j;
        this.start = j2;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long j) {
        long j2 = j;
        this.end = j2;
    }

    public long getSplitLength() {
        return this.splitLength;
    }

    public void setSplitLength(long j) {
        long j2 = j;
        this.splitLength = j2;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getFileNameCharset() {
        return this.fileNameCharset;
    }

    public void setFileNameCharset(String str) {
        String str2 = str;
        this.fileNameCharset = str2;
    }
}
