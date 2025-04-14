package net.lingala.zip4j.model;

import java.util.TimeZone;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

public class ZipParameters implements Cloneable {
    private int aesKeyStrength = -1;
    private int compressionLevel;
    private int compressionMethod = 8;
    private String defaultFolderPath;
    private boolean encryptFiles = false;
    private int encryptionMethod = -1;
    private String fileNameInZip;
    private boolean includeRootFolder = true;
    private boolean isSourceExternalStream;
    private char[] password;
    private boolean readHiddenFiles = true;
    private String rootFolderInZip;
    private int sourceFileCRC;
    private TimeZone timeZone = TimeZone.getDefault();

    public ZipParameters() {
    }

    public int getCompressionMethod() {
        return this.compressionMethod;
    }

    public void setCompressionMethod(int i) {
        int i2 = i;
        this.compressionMethod = i2;
    }

    public boolean isEncryptFiles() {
        return this.encryptFiles;
    }

    public void setEncryptFiles(boolean z) {
        boolean z2 = z;
        this.encryptFiles = z2;
    }

    public int getEncryptionMethod() {
        return this.encryptionMethod;
    }

    public void setEncryptionMethod(int i) {
        int i2 = i;
        this.encryptionMethod = i2;
    }

    public int getCompressionLevel() {
        return this.compressionLevel;
    }

    public void setCompressionLevel(int i) {
        int i2 = i;
        this.compressionLevel = i2;
    }

    public boolean isReadHiddenFiles() {
        return this.readHiddenFiles;
    }

    public void setReadHiddenFiles(boolean z) {
        boolean z2 = z;
        this.readHiddenFiles = z2;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        String str2 = str;
        if (str2 != null) {
            setPassword(str2.toCharArray());
        }
    }

    public void setPassword(char[] cArr) {
        char[] cArr2 = cArr;
        this.password = cArr2;
    }

    public int getAesKeyStrength() {
        return this.aesKeyStrength;
    }

    public void setAesKeyStrength(int i) {
        int i2 = i;
        this.aesKeyStrength = i2;
    }

    public boolean isIncludeRootFolder() {
        return this.includeRootFolder;
    }

    public void setIncludeRootFolder(boolean z) {
        boolean z2 = z;
        this.includeRootFolder = z2;
    }

    public String getRootFolderInZip() {
        return this.rootFolderInZip;
    }

    public void setRootFolderInZip(String str) {
        StringBuilder sb;
        String str2 = str;
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            if (!str2.endsWith("\\") && !str2.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                new StringBuilder();
                str2 = sb.append(str2).append(InternalZipConstants.FILE_SEPARATOR).toString();
            }
            str2 = str2.replaceAll("\\\\", InternalZipConstants.ZIP_FILE_SEPARATOR);
        }
        this.rootFolderInZip = str2;
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone2) {
        TimeZone timeZone3 = timeZone2;
        this.timeZone = timeZone3;
    }

    public int getSourceFileCRC() {
        return this.sourceFileCRC;
    }

    public void setSourceFileCRC(int i) {
        int i2 = i;
        this.sourceFileCRC = i2;
    }

    public String getDefaultFolderPath() {
        return this.defaultFolderPath;
    }

    public void setDefaultFolderPath(String str) {
        String str2 = str;
        this.defaultFolderPath = str2;
    }

    public String getFileNameInZip() {
        return this.fileNameInZip;
    }

    public void setFileNameInZip(String str) {
        String str2 = str;
        this.fileNameInZip = str2;
    }

    public boolean isSourceExternalStream() {
        return this.isSourceExternalStream;
    }

    public void setSourceExternalStream(boolean z) {
        boolean z2 = z;
        this.isSourceExternalStream = z2;
    }
}
