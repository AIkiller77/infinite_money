package com.androlua;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.a.a.a.a.a.a.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.WeakHashMap;
import net.lingala.zip4j.util.InternalZipConstants;

public class LuaBitmap {
    static WeakHashMap<String, WeakReference<Bitmap>> a = new WeakHashMap<>();
    private static long b = 604800000;

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int b2 = b(options, i, i2);
        if (b2 > 8) {
            return ((b2 + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < b2) {
            i3 <<= 1;
        }
        return i3;
    }

    private static int b(BitmapFactory.Options options, int i, int i2) {
        int i3;
        double d = (double) options.outWidth;
        double d2 = (double) options.outHeight;
        int ceil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        if (i == -1) {
            i3 = 128;
        } else {
            double d3 = (double) i;
            i3 = (int) Math.min(Math.floor(d / d3), Math.floor(d2 / d3));
        }
        if (i3 < ceil) {
            return ceil;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        return i == -1 ? ceil : i3;
    }

    public static boolean checkCache(LuaContext luaContext, String str) {
        File file = new File(luaContext.getLuaExtDir("cache") + InternalZipConstants.ZIP_FILE_SEPARATOR + str.hashCode());
        return file.exists() && b != -1 && System.currentTimeMillis() - file.lastModified() < b;
    }

    public static Bitmap decodeScale(int i, File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i2 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        if (options.outHeight > i * 4 || options.outWidth > i) {
            i2 = (int) Math.pow(2.0d, (double) ((int) Math.round(Math.log(((double) i) / ((double) Math.max(options.outHeight, options.outWidth))) / Math.log(0.5d))));
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = i2;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options2);
    }

    public static Bitmap getAssetBitmap(Context context, String str) {
        InputStream open = context.getAssets().open(str);
        Bitmap decodeStream = BitmapFactory.decodeStream(open);
        open.close();
        return decodeStream;
    }

    public static Bitmap getBitmap(LuaContext luaContext, String str) {
        Bitmap bitmap;
        Bitmap bitmap2;
        WeakReference weakReference = a.get(str);
        if (weakReference != null && (bitmap2 = (Bitmap) weakReference.get()) != null) {
            return bitmap2;
        }
        if (str.toLowerCase().startsWith("http://") || str.toLowerCase().startsWith("https://")) {
            bitmap = getHttpBitmap(luaContext, str);
        } else if (str.charAt(0) != '/') {
            bitmap = getLocalBitmap(luaContext, luaContext.getLuaDir() + InternalZipConstants.ZIP_FILE_SEPARATOR + str);
        } else {
            bitmap = getLocalBitmap(luaContext, str);
        }
        a.put(str, new WeakReference(bitmap));
        return bitmap;
    }

    public static Bitmap getBitmapFromFile(File file, int i, int i2) {
        BitmapFactory.Options options;
        if (file != null && file.exists()) {
            if (i <= 0 || i2 <= 0) {
                options = null;
            } else {
                options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file.getPath(), options);
                options.inSampleSize = a(options, Math.min(i, i2), i * i2);
                options.inJustDecodeBounds = false;
                options.inInputShareable = true;
                options.inPurgeable = true;
            }
            try {
                return BitmapFactory.decodeFile(file.getPath(), options);
            } catch (OutOfMemoryError e) {
                a.a(e);
            }
        }
        return null;
    }

    public static long getCacheTime() {
        return b;
    }

    public static Bitmap getHttpBitmap(LuaContext luaContext, String str) {
        int width;
        File file;
        String str2 = luaContext.getLuaExtDir("cache") + InternalZipConstants.ZIP_FILE_SEPARATOR + str.hashCode();
        File file2 = new File(str2);
        if (!file2.exists() || b == -1 || System.currentTimeMillis() - file2.lastModified() >= b) {
            new File(str2).delete();
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(120000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            if (!LuaUtil.copyFile(inputStream, (OutputStream) fileOutputStream)) {
                fileOutputStream.close();
                inputStream.close();
                new File(str2).delete();
                throw new RuntimeException("LoadHttpBitmap Error.");
            }
            fileOutputStream.close();
            inputStream.close();
            width = luaContext.getWidth();
            file = new File(str2);
        } else {
            width = luaContext.getWidth();
            file = new File(str2);
        }
        return decodeScale(width, file);
    }

    public static Bitmap getHttpBitmap(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return decodeStream;
    }

    public static Bitmap getImageFromPath(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = a(options, -1, 62500);
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Bitmap getLocalBitmap(LuaContext luaContext, String str) {
        return decodeScale(luaContext.getWidth(), new File(str));
    }

    public static Bitmap getLocalBitmap(String str) {
        FileInputStream fileInputStream = new FileInputStream(str);
        Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream);
        fileInputStream.close();
        return decodeStream;
    }

    public static void removeBitmap(Bitmap bitmap) {
        for (Map.Entry next : a.entrySet()) {
            if (bitmap.equals(((WeakReference) next.getValue()).get())) {
                a.remove(next.getKey());
                return;
            }
        }
    }

    public static void setCacheTime(long j) {
        b = j;
    }
}
