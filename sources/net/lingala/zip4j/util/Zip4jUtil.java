package net.lingala.zip4j.util;

import android.support.v4.media.TransportMediator;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;

public class Zip4jUtil {
    public Zip4jUtil() {
    }

    public static boolean isStringNotNullAndNotEmpty(String str) {
        String str2 = str;
        if (str2 == null || str2.trim().length() <= 0) {
            return false;
        }
        return true;
    }

    public static boolean checkOutputFolder(String str) throws ZipException {
        File file;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        String str2 = str;
        if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th8 = th6;
            new NullPointerException("output path is null");
            new ZipException(th7);
            throw th8;
        }
        new File(str2);
        File file2 = file;
        if (!file2.exists()) {
            try {
                boolean mkdirs = file2.mkdirs();
                if (!file2.isDirectory()) {
                    Throwable th9 = th3;
                    new ZipException("output folder is not valid");
                    throw th9;
                } else if (!file2.canWrite()) {
                    Throwable th10 = th2;
                    new ZipException("no write access to destination folder");
                    throw th10;
                }
            } catch (Exception e) {
                Exception exc = e;
                Throwable th11 = th;
                new ZipException("Cannot create destination folder");
                throw th11;
            }
        } else if (!file2.isDirectory()) {
            Throwable th12 = th5;
            new ZipException("output folder is not valid");
            throw th12;
        } else if (!file2.canWrite()) {
            Throwable th13 = th4;
            new ZipException("no write access to output folder");
            throw th13;
        }
        return true;
    }

    public static boolean checkFileReadAccess(String str) throws ZipException {
        Throwable th;
        File file;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        String str2 = str;
        if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th4 = th3;
            new ZipException("path is null");
            throw th4;
        } else if (!checkFileExists(str2)) {
            Throwable th5 = th2;
            new StringBuilder();
            new ZipException(sb.append("file does not exist: ").append(str2).toString());
            throw th5;
        } else {
            try {
                new File(str2);
                return file.canRead();
            } catch (Exception e) {
                Exception exc = e;
                Throwable th6 = th;
                new ZipException("cannot read zip file");
                throw th6;
            }
        }
    }

    public static boolean checkFileWriteAccess(String str) throws ZipException {
        Throwable th;
        File file;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        String str2 = str;
        if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th4 = th3;
            new ZipException("path is null");
            throw th4;
        } else if (!checkFileExists(str2)) {
            Throwable th5 = th2;
            new StringBuilder();
            new ZipException(sb.append("file does not exist: ").append(str2).toString());
            throw th5;
        } else {
            try {
                new File(str2);
                return file.canWrite();
            } catch (Exception e) {
                Exception exc = e;
                Throwable th6 = th;
                new ZipException("cannot read zip file");
                throw th6;
            }
        }
    }

    public static boolean checkFileExists(String str) throws ZipException {
        File file;
        Throwable th;
        String str2 = str;
        if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th2 = th;
            new ZipException("path is null");
            throw th2;
        }
        new File(str2);
        return checkFileExists(file);
    }

    public static boolean checkFileExists(File file) throws ZipException {
        Throwable th;
        File file2 = file;
        if (file2 != null) {
            return file2.exists();
        }
        Throwable th2 = th;
        new ZipException("cannot check if file exists: input file is null");
        throw th2;
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }

    public static void setFileReadOnly(File file) throws ZipException {
        Throwable th;
        File file2 = file;
        if (file2 == null) {
            Throwable th2 = th;
            new ZipException("input file is null. cannot set read only file attribute");
            throw th2;
        } else if (file2.exists()) {
            boolean readOnly = file2.setReadOnly();
        }
    }

    public static void setFileHidden(File file) throws ZipException {
    }

    public static void setFileArchive(File file) throws ZipException {
    }

    public static void setFileSystemMode(File file) throws ZipException {
    }

    public static long getLastModifiedFileTime(File file, TimeZone timeZone) throws ZipException {
        Throwable th;
        Throwable th2;
        File file2 = file;
        TimeZone timeZone2 = timeZone;
        if (file2 == null) {
            Throwable th3 = th2;
            new ZipException("input file is null, cannot read last modified file time");
            throw th3;
        } else if (file2.exists()) {
            return file2.lastModified();
        } else {
            Throwable th4 = th;
            new ZipException("input file does not exist, cannot read last modified file time");
            throw th4;
        }
    }

    public static String getFileNameFromFilePath(File file) throws ZipException {
        Throwable th;
        File file2 = file;
        if (file2 == null) {
            Throwable th2 = th;
            new ZipException("input file is null, cannot get file name");
            throw th2;
        } else if (file2.isDirectory()) {
            return null;
        } else {
            return file2.getName();
        }
    }

    public static long getFileLengh(String str) throws ZipException {
        File file;
        Throwable th;
        String str2 = str;
        if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th2 = th;
            new ZipException("invalid file name");
            throw th2;
        }
        new File(str2);
        return getFileLengh(file);
    }

    public static long getFileLengh(File file) throws ZipException {
        Throwable th;
        File file2 = file;
        if (file2 == null) {
            Throwable th2 = th;
            new ZipException("input file is null, cannot calculate file length");
            throw th2;
        } else if (file2.isDirectory()) {
            return -1;
        } else {
            return file2.length();
        }
    }

    public static long javaToDosTime(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i = instance.get(1);
        if (i < 1980) {
            return 2162688;
        }
        return (long) (((i - 1980) << 25) | ((instance.get(2) + 1) << 21) | (instance.get(5) << 16) | (instance.get(11) << 11) | (instance.get(12) << 5) | (instance.get(13) >> 1));
    }

    public static long dosToJavaTme(int i) {
        int i2 = i;
        int i3 = (i2 >> 11) & 31;
        int i4 = (i2 >> 16) & 31;
        int i5 = ((i2 >> 21) & 15) - 1;
        int i6 = ((i2 >> 25) & TransportMediator.KEYCODE_MEDIA_PAUSE) + 1980;
        Calendar instance = Calendar.getInstance();
        instance.set(i6, i5, i4, i3, (i2 >> 5) & 63, 2 * (i2 & 31));
        instance.set(14, 0);
        return instance.getTime().getTime();
    }

    public static FileHeader getFileHeader(ZipModel zipModel, String str) throws ZipException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        ZipModel zipModel2 = zipModel;
        String str2 = str;
        if (zipModel2 == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new ZipException(sb2.append("zip model is null, cannot determine file header for fileName: ").append(str2).toString());
            throw th3;
        } else if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th4 = th;
            new StringBuilder();
            new ZipException(sb.append("file name is null, cannot determine file header for fileName: ").append(str2).toString());
            throw th4;
        } else {
            FileHeader fileHeaderWithExactMatch = getFileHeaderWithExactMatch(zipModel2, str2);
            if (fileHeaderWithExactMatch == null) {
                String replaceAll = str2.replaceAll("\\\\", InternalZipConstants.ZIP_FILE_SEPARATOR);
                fileHeaderWithExactMatch = getFileHeaderWithExactMatch(zipModel2, replaceAll);
                if (fileHeaderWithExactMatch == null) {
                    fileHeaderWithExactMatch = getFileHeaderWithExactMatch(zipModel2, replaceAll.replaceAll(InternalZipConstants.ZIP_FILE_SEPARATOR, "\\\\"));
                }
            }
            return fileHeaderWithExactMatch;
        }
    }

    public static FileHeader getFileHeaderWithExactMatch(ZipModel zipModel, String str) throws ZipException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        ZipModel zipModel2 = zipModel;
        String str2 = str;
        if (zipModel2 == null) {
            Throwable th5 = th4;
            new StringBuilder();
            new ZipException(sb4.append("zip model is null, cannot determine file header with exact match for fileName: ").append(str2).toString());
            throw th5;
        } else if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th6 = th3;
            new StringBuilder();
            new ZipException(sb3.append("file name is null, cannot determine file header with exact match for fileName: ").append(str2).toString());
            throw th6;
        } else if (zipModel2.getCentralDirectory() == null) {
            Throwable th7 = th2;
            new StringBuilder();
            new ZipException(sb2.append("central directory is null, cannot determine file header with exact match for fileName: ").append(str2).toString());
            throw th7;
        } else if (zipModel2.getCentralDirectory().getFileHeaders() == null) {
            Throwable th8 = th;
            new StringBuilder();
            new ZipException(sb.append("file Headers are null, cannot determine file header with exact match for fileName: ").append(str2).toString());
            throw th8;
        } else if (zipModel2.getCentralDirectory().getFileHeaders().size() <= 0) {
            return null;
        } else {
            ArrayList fileHeaders = zipModel2.getCentralDirectory().getFileHeaders();
            for (int i = 0; i < fileHeaders.size(); i++) {
                FileHeader fileHeader = (FileHeader) fileHeaders.get(i);
                String fileName = fileHeader.getFileName();
                if (isStringNotNullAndNotEmpty(fileName) && str2.equalsIgnoreCase(fileName)) {
                    return fileHeader;
                }
            }
            return null;
        }
    }

    public static int getIndexOfFileHeader(ZipModel zipModel, FileHeader fileHeader) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        ZipModel zipModel2 = zipModel;
        FileHeader fileHeader2 = fileHeader;
        if (zipModel2 == null || fileHeader2 == null) {
            Throwable th5 = th;
            new ZipException("input parameters is null, cannot determine index of file header");
            throw th5;
        } else if (zipModel2.getCentralDirectory() == null) {
            Throwable th6 = th4;
            new ZipException("central directory is null, ccannot determine index of file header");
            throw th6;
        } else if (zipModel2.getCentralDirectory().getFileHeaders() == null) {
            Throwable th7 = th3;
            new ZipException("file Headers are null, cannot determine index of file header");
            throw th7;
        } else if (zipModel2.getCentralDirectory().getFileHeaders().size() <= 0) {
            return -1;
        } else {
            String fileName = fileHeader2.getFileName();
            if (!isStringNotNullAndNotEmpty(fileName)) {
                Throwable th8 = th2;
                new ZipException("file name in file header is empty or null, cannot determine index of file header");
                throw th8;
            }
            ArrayList fileHeaders = zipModel2.getCentralDirectory().getFileHeaders();
            for (int i = 0; i < fileHeaders.size(); i++) {
                String fileName2 = ((FileHeader) fileHeaders.get(i)).getFileName();
                if (isStringNotNullAndNotEmpty(fileName2) && fileName.equalsIgnoreCase(fileName2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public static ArrayList getFilesInDirectoryRec(File file, boolean z) throws ZipException {
        ArrayList arrayList;
        Throwable th;
        File file2 = file;
        boolean z2 = z;
        if (file2 == null) {
            Throwable th2 = th;
            new ZipException("input path is null, cannot read files in the directory");
            throw th2;
        }
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        List asList = Arrays.asList(file2.listFiles());
        if (!file2.canRead()) {
            return arrayList2;
        }
        for (int i = 0; i < asList.size(); i++) {
            File file3 = (File) asList.get(i);
            if (file3.isHidden() && !z2) {
                return arrayList2;
            }
            boolean add = arrayList2.add(file3);
            if (file3.isDirectory()) {
                boolean addAll = arrayList2.addAll(getFilesInDirectoryRec(file3, z2));
            }
        }
        return arrayList2;
    }

    public static String getZipFileNameWithoutExt(String str) throws ZipException {
        Throwable th;
        String str2 = str;
        if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th2 = th;
            new ZipException("zip file name is empty or null, cannot determine zip file name");
            throw th2;
        }
        String str3 = str2;
        if (str2.indexOf(System.getProperty("file.separator")) >= 0) {
            str3 = str2.substring(str2.lastIndexOf(System.getProperty("file.separator")));
        }
        if (str3.indexOf(".") > 0) {
            str3 = str3.substring(0, str3.lastIndexOf("."));
        }
        return str3;
    }

    public static byte[] convertCharset(String str) throws ZipException {
        Throwable th;
        byte[] bytes;
        String str2 = str;
        try {
            String detectCharSet = detectCharSet(str2);
            if (detectCharSet.equals(InternalZipConstants.CHARSET_CP850)) {
                bytes = str2.getBytes(InternalZipConstants.CHARSET_CP850);
            } else if (detectCharSet.equals(InternalZipConstants.CHARSET_UTF8)) {
                bytes = str2.getBytes(InternalZipConstants.CHARSET_UTF8);
            } else {
                bytes = str2.getBytes();
            }
            return bytes;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            return str2.getBytes();
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th2 = th;
            new ZipException((Throwable) exc);
            throw th2;
        }
    }

    public static String decodeFileName(byte[] bArr, boolean z) {
        String str;
        String str2;
        byte[] bArr2 = bArr;
        if (!z) {
            return getCp850EncodedString(bArr2);
        }
        try {
            String str3 = str2;
            new String(bArr2, InternalZipConstants.CHARSET_UTF8);
            return str3;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            new String(bArr2);
            return str;
        }
    }

    public static String getCp850EncodedString(byte[] bArr) {
        String str;
        String str2;
        byte[] bArr2 = bArr;
        try {
            String str3 = str2;
            new String(bArr2, InternalZipConstants.CHARSET_CP850);
            return str3;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            new String(bArr2);
            return str;
        }
    }

    public static String getAbsoluteFilePath(String str) throws ZipException {
        File file;
        Throwable th;
        String str2 = str;
        if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th2 = th;
            new ZipException("filePath is null or empty, cannot get absolute file path");
            throw th2;
        }
        new File(str2);
        return file.getAbsolutePath();
    }

    public static boolean checkArrayListTypes(ArrayList arrayList, int i) throws ZipException {
        boolean z;
        Throwable th;
        ArrayList arrayList2 = arrayList;
        int i2 = i;
        if (arrayList2 == null) {
            Throwable th2 = th;
            new ZipException("input arraylist is null, cannot check types");
            throw th2;
        } else if (arrayList2.size() <= 0) {
            return true;
        } else {
            boolean z2 = false;
            switch (i2) {
                case 1:
                    int i3 = 0;
                    while (true) {
                        if (i3 >= arrayList2.size()) {
                            break;
                        } else if (!(arrayList2.get(i3) instanceof File)) {
                            z2 = true;
                            break;
                        } else {
                            i3++;
                        }
                    }
                case 2:
                    int i4 = 0;
                    while (true) {
                        if (i4 >= arrayList2.size()) {
                            break;
                        } else if (!(arrayList2.get(i4) instanceof String)) {
                            z2 = true;
                            break;
                        } else {
                            i4++;
                        }
                    }
            }
            if (!z2) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
    }

    public static String detectCharSet(String str) throws ZipException {
        Object obj;
        Object obj2;
        Throwable th;
        String str2 = str;
        if (str2 == null) {
            Throwable th2 = th;
            new ZipException("input string is null, cannot detect charset");
            throw th2;
        }
        try {
            new String(str2.getBytes(InternalZipConstants.CHARSET_CP850), InternalZipConstants.CHARSET_CP850);
            if (str2.equals(obj)) {
                return InternalZipConstants.CHARSET_CP850;
            }
            new String(str2.getBytes(InternalZipConstants.CHARSET_UTF8), InternalZipConstants.CHARSET_UTF8);
            return str2.equals(obj2) ? InternalZipConstants.CHARSET_UTF8 : InternalZipConstants.CHARSET_DEFAULT;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            return InternalZipConstants.CHARSET_DEFAULT;
        } catch (Exception e2) {
            Exception exc = e2;
            return InternalZipConstants.CHARSET_DEFAULT;
        }
    }

    public static int getEncodedStringLength(String str) throws ZipException {
        Throwable th;
        String str2 = str;
        if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th2 = th;
            new ZipException("input string is null, cannot calculate encoded String length");
            throw th2;
        }
        return getEncodedStringLength(str2, detectCharSet(str2));
    }

    public static int getEncodedStringLength(String str, String str2) throws ZipException {
        Throwable th;
        ByteBuffer wrap;
        Throwable th2;
        Throwable th3;
        String str3 = str;
        String str4 = str2;
        if (!isStringNotNullAndNotEmpty(str3)) {
            Throwable th4 = th3;
            new ZipException("input string is null, cannot calculate encoded String length");
            throw th4;
        } else if (!isStringNotNullAndNotEmpty(str4)) {
            Throwable th5 = th2;
            new ZipException("encoding is not defined, cannot calculate string length");
            throw th5;
        } else {
            try {
                if (str4.equals(InternalZipConstants.CHARSET_CP850)) {
                    wrap = ByteBuffer.wrap(str3.getBytes(InternalZipConstants.CHARSET_CP850));
                } else if (str4.equals(InternalZipConstants.CHARSET_UTF8)) {
                    wrap = ByteBuffer.wrap(str3.getBytes(InternalZipConstants.CHARSET_UTF8));
                } else {
                    wrap = ByteBuffer.wrap(str3.getBytes(str4));
                }
            } catch (UnsupportedEncodingException e) {
                UnsupportedEncodingException unsupportedEncodingException = e;
                wrap = ByteBuffer.wrap(str3.getBytes());
            } catch (Exception e2) {
                Exception exc = e2;
                Throwable th6 = th;
                new ZipException((Throwable) exc);
                throw th6;
            }
            return wrap.limit();
        }
    }

    public static boolean isSupportedCharset(String str) throws ZipException {
        Throwable th;
        Object obj;
        Throwable th2;
        String str2 = str;
        if (!isStringNotNullAndNotEmpty(str2)) {
            Throwable th3 = th2;
            new ZipException("charset is null or empty, cannot check if it is supported");
            throw th3;
        }
        try {
            Object obj2 = obj;
            new String("a".getBytes(), str2);
            return true;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            return false;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th4 = th;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    public static ArrayList getSplitZipFiles(ZipModel zipModel) throws ZipException {
        ArrayList arrayList;
        File file;
        StringBuilder sb;
        Throwable th;
        Throwable th2;
        ZipModel zipModel2 = zipModel;
        if (zipModel2 == null) {
            Throwable th3 = th2;
            new ZipException("cannot get split zip files: zipmodel is null");
            throw th3;
        } else if (zipModel2.getEndCentralDirRecord() == null) {
            return null;
        } else {
            new ArrayList();
            ArrayList arrayList2 = arrayList;
            String zipFile = zipModel2.getZipFile();
            new File(zipFile);
            String name = file.getName();
            if (!isStringNotNullAndNotEmpty(zipFile)) {
                Throwable th4 = th;
                new ZipException("cannot get split zip files: zipfile is null");
                throw th4;
            } else if (!zipModel2.isSplitArchive()) {
                boolean add = arrayList2.add(zipFile);
                return arrayList2;
            } else {
                int noOfThisDisk = zipModel2.getEndCentralDirRecord().getNoOfThisDisk();
                if (noOfThisDisk == 0) {
                    boolean add2 = arrayList2.add(zipFile);
                    return arrayList2;
                }
                for (int i = 0; i <= noOfThisDisk; i++) {
                    if (i == noOfThisDisk) {
                        boolean add3 = arrayList2.add(zipModel2.getZipFile());
                    } else {
                        String str = ".z0";
                        if (i > 9) {
                            str = ".z";
                        }
                        String substring = name.indexOf(".") >= 0 ? zipFile.substring(0, zipFile.lastIndexOf(".")) : zipFile;
                        new StringBuilder();
                        boolean add4 = arrayList2.add(sb.append(substring).append(str).append(i + 1).toString());
                    }
                }
                return arrayList2;
            }
        }
    }

    public static String getRelativeFileName(String str, String str2, String str3) throws ZipException {
        File file;
        File file2;
        String fileNameFromFilePath;
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        File file3;
        File file4;
        StringBuilder sb3;
        String sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        Throwable th2;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        if (!isStringNotNullAndNotEmpty(str4)) {
            Throwable th3 = th2;
            new ZipException("input file path/name is empty, cannot calculate relative file name");
            throw th3;
        }
        if (isStringNotNullAndNotEmpty(str6)) {
            new File(str6);
            String path = file3.getPath();
            if (!path.endsWith(InternalZipConstants.FILE_SEPARATOR)) {
                new StringBuilder();
                path = sb6.append(path).append(InternalZipConstants.FILE_SEPARATOR).toString();
            }
            String substring = str4.substring(path.length());
            if (substring.startsWith(System.getProperty("file.separator"))) {
                substring = substring.substring(1);
            }
            new File(str4);
            File file5 = file4;
            if (file5.isDirectory()) {
                String replaceAll = substring.replaceAll("\\\\", InternalZipConstants.ZIP_FILE_SEPARATOR);
                new StringBuilder();
                sb4 = sb5.append(replaceAll).append(InternalZipConstants.ZIP_FILE_SEPARATOR).toString();
            } else {
                String replaceAll2 = substring.substring(0, substring.lastIndexOf(file5.getName())).replaceAll("\\\\", InternalZipConstants.ZIP_FILE_SEPARATOR);
                new StringBuilder();
                sb4 = sb3.append(replaceAll2).append(file5.getName()).toString();
            }
            fileNameFromFilePath = sb4;
        } else {
            new File(str4);
            File file6 = file;
            if (file6.isDirectory()) {
                new StringBuilder();
                fileNameFromFilePath = sb.append(file6.getName()).append(InternalZipConstants.ZIP_FILE_SEPARATOR).toString();
            } else {
                new File(str4);
                fileNameFromFilePath = getFileNameFromFilePath(file2);
            }
        }
        if (isStringNotNullAndNotEmpty(str5)) {
            new StringBuilder();
            fileNameFromFilePath = sb2.append(str5).append(fileNameFromFilePath).toString();
        }
        if (isStringNotNullAndNotEmpty(fileNameFromFilePath)) {
            return fileNameFromFilePath;
        }
        Throwable th4 = th;
        new ZipException("Error determining file name");
        throw th4;
    }

    public static long[] getAllHeaderSignatures() {
        return new long[]{67324752, 134695760, 33639248, 101010256, 84233040, 134630224, 134695760, 117853008, 101075792, 1, 39169};
    }
}
