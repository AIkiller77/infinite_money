package net.lingala.zip4j.unzip;

import java.io.File;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.util.Zip4jUtil;

public class UnzipUtil {
    public UnzipUtil() {
    }

    public static void applyFileAttributes(FileHeader fileHeader, File file) throws ZipException {
        applyFileAttributes(fileHeader, file, (UnzipParameters) null);
    }

    public static void applyFileAttributes(FileHeader fileHeader, File file, UnzipParameters unzipParameters) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        FileHeader fileHeader2 = fileHeader;
        File file2 = file;
        UnzipParameters unzipParameters2 = unzipParameters;
        if (fileHeader2 == null) {
            Throwable th4 = th3;
            new ZipException("cannot set file properties: file header is null");
            throw th4;
        } else if (file2 == null) {
            Throwable th5 = th2;
            new ZipException("cannot set file properties: output file is null");
            throw th5;
        } else if (!Zip4jUtil.checkFileExists(file2)) {
            Throwable th6 = th;
            new ZipException("cannot set file properties: file doesnot exist");
            throw th6;
        } else {
            if (unzipParameters2 == null || !unzipParameters2.isIgnoreDateTimeAttributes()) {
                setFileLastModifiedTime(fileHeader2, file2);
            }
            if (unzipParameters2 == null) {
                setFileAttributes(fileHeader2, file2, true, true, true, true);
            } else if (unzipParameters2.isIgnoreAllFileAttributes()) {
                setFileAttributes(fileHeader2, file2, false, false, false, false);
            } else {
                setFileAttributes(fileHeader2, file2, !unzipParameters2.isIgnoreReadOnlyFileAttribute(), !unzipParameters2.isIgnoreHiddenFileAttribute(), !unzipParameters2.isIgnoreArchiveFileAttribute(), !unzipParameters2.isIgnoreSystemFileAttribute());
            }
        }
    }

    private static void setFileAttributes(FileHeader fileHeader, File file, boolean z, boolean z2, boolean z3, boolean z4) throws ZipException {
        Throwable th;
        FileHeader fileHeader2 = fileHeader;
        File file2 = file;
        boolean z5 = z;
        boolean z6 = z2;
        boolean z7 = z3;
        boolean z8 = z4;
        if (fileHeader2 == null) {
            Throwable th2 = th;
            new ZipException("invalid file header. cannot set file attributes");
            throw th2;
        }
        byte[] externalFileAttr = fileHeader2.getExternalFileAttr();
        if (externalFileAttr != null) {
            switch (externalFileAttr[0]) {
                case 1:
                    if (z5) {
                        Zip4jUtil.setFileReadOnly(file2);
                        return;
                    }
                    return;
                case 2:
                case 18:
                    if (z6) {
                        Zip4jUtil.setFileHidden(file2);
                        return;
                    }
                    return;
                case 3:
                    if (z5) {
                        Zip4jUtil.setFileReadOnly(file2);
                    }
                    if (z6) {
                        Zip4jUtil.setFileHidden(file2);
                        return;
                    }
                    return;
                case 32:
                case 48:
                    if (z7) {
                        Zip4jUtil.setFileArchive(file2);
                        return;
                    }
                    return;
                case 33:
                    if (z7) {
                        Zip4jUtil.setFileArchive(file2);
                    }
                    if (z5) {
                        Zip4jUtil.setFileReadOnly(file2);
                        return;
                    }
                    return;
                case 34:
                case 50:
                    if (z7) {
                        Zip4jUtil.setFileArchive(file2);
                    }
                    if (z6) {
                        Zip4jUtil.setFileHidden(file2);
                        return;
                    }
                    return;
                case 35:
                    if (z7) {
                        Zip4jUtil.setFileArchive(file2);
                    }
                    if (z5) {
                        Zip4jUtil.setFileReadOnly(file2);
                    }
                    if (z6) {
                        Zip4jUtil.setFileHidden(file2);
                        return;
                    }
                    return;
                case 38:
                    if (z5) {
                        Zip4jUtil.setFileReadOnly(file2);
                    }
                    if (z6) {
                        Zip4jUtil.setFileHidden(file2);
                    }
                    if (z8) {
                        Zip4jUtil.setFileSystemMode(file2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private static void setFileLastModifiedTime(FileHeader fileHeader, File file) throws ZipException {
        FileHeader fileHeader2 = fileHeader;
        File file2 = file;
        if (fileHeader2.getLastModFileTime() > 0 && file2.exists()) {
            boolean lastModified = file2.setLastModified(Zip4jUtil.dosToJavaTme(fileHeader2.getLastModFileTime()));
        }
    }
}
