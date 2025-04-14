package com.andlua;

import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

public class Zip {
    public static boolean Zip4j(String str, String str2, String str3, String str4) {
        File file;
        ZipFile zipFile;
        File file2;
        String str5 = str2;
        String str6 = str3;
        String str7 = str4;
        new File(str);
        try {
            new ZipFile(file);
            ZipFile zipFile2 = zipFile;
            zipFile2.setFileNameCharset(str7);
            if (!zipFile2.isValidZipFile()) {
                return false;
            }
            new File(str5);
            File file3 = file2;
            if (file3.isDirectory() && !file3.exists()) {
                boolean mkdirs = file3.mkdirs();
            }
            if (zipFile2.isEncrypted()) {
                zipFile2.setPassword(str6.toCharArray());
            }
            zipFile2.extractAll(str5);
            return true;
        } catch (Exception e) {
            Exception exc = e;
            return false;
        }
    }

    public static boolean zip4j_ZipDir(String str, String str2, String str3, String str4) {
        File file;
        File file2;
        ZipFile zipFile;
        ZipParameters zipParameters;
        String str5 = str3;
        String str6 = str4;
        new File(str);
        File file3 = file;
        new File(str2);
        try {
            new ZipFile(file2);
            ZipFile zipFile2 = zipFile;
            zipFile2.setFileNameCharset(str6);
            new ZipParameters();
            ZipParameters zipParameters2 = zipParameters;
            zipParameters2.setCompressionMethod(8);
            zipParameters2.setCompressionLevel(5);
            if (str5 != null) {
                zipParameters2.setEncryptFiles(true);
                zipParameters2.setEncryptionMethod(0);
                zipParameters2.setPassword(str5);
            }
            if (file3.isDirectory()) {
                zipFile2.addFolder(file3, zipParameters2);
            } else {
                zipFile2.addFile(file3, zipParameters2);
            }
            return true;
        } catch (Exception e) {
            Exception exc = e;
            return false;
        }
    }

    public Zip() {
    }
}
