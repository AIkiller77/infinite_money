package com.tencent.open.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import com.tencent.open.utils.e;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/* compiled from: ProGuard */
public class f extends SQLiteOpenHelper {
    protected static final String[] a = {"key"};
    protected static f b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(Context context) {
        super(context, "sdk_report.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    public static synchronized f a() {
        f fVar;
        f fVar2;
        synchronized (f.class) {
            if (b == null) {
                new f(e.a());
                b = fVar2;
            }
            fVar = b;
        }
        return fVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<java.io.Serializable> a(java.lang.String r28) {
        /*
            r27 = this;
            r2 = r27
            r3 = r28
            r25 = r27
            monitor-enter(r25)
            java.util.ArrayList r17 = new java.util.ArrayList     // Catch:{ all -> 0x0178 }
            r26 = r17
            r17 = r26
            r18 = r26
            r18.<init>()     // Catch:{ all -> 0x0178 }
            java.util.List r17 = java.util.Collections.synchronizedList(r17)     // Catch:{ all -> 0x0178 }
            r4 = r17
            r17 = r3
            boolean r17 = android.text.TextUtils.isEmpty(r17)     // Catch:{ all -> 0x0178 }
            if (r17 == 0) goto L_0x0026
            r17 = r4
            r2 = r17
        L_0x0024:
            monitor-exit(r25)
            return r2
        L_0x0026:
            r17 = r2
            android.database.sqlite.SQLiteDatabase r17 = r17.getReadableDatabase()     // Catch:{ all -> 0x0178 }
            r5 = r17
            r17 = r5
            if (r17 != 0) goto L_0x0037
            r17 = r4
            r2 = r17
            goto L_0x0024
        L_0x0037:
            r17 = 0
            r6 = r17
            r17 = 0
            r7 = r17
            r17 = r5
            java.lang.String r18 = "via_cgi_report"
            r19 = 0
            java.lang.String r20 = "type = ?"
            r21 = 1
            r0 = r21
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0138 }
            r21 = r0
            r26 = r21
            r21 = r26
            r22 = r26
            r23 = 0
            r24 = r3
            r22[r23] = r24     // Catch:{ Exception -> 0x0138 }
            r22 = 0
            r23 = 0
            r24 = 0
            android.database.Cursor r17 = r17.query(r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x0138 }
            r6 = r17
            r17 = r6
            if (r17 == 0) goto L_0x00db
            r17 = r6
            int r17 = r17.getCount()     // Catch:{ Exception -> 0x0138 }
            if (r17 <= 0) goto L_0x00db
            r17 = r6
            boolean r17 = r17.moveToFirst()     // Catch:{ Exception -> 0x0138 }
        L_0x0079:
            r17 = r6
            r18 = r6
            java.lang.String r19 = "blob"
            int r18 = r18.getColumnIndex(r19)     // Catch:{ Exception -> 0x0138 }
            byte[] r17 = r17.getBlob(r18)     // Catch:{ Exception -> 0x0138 }
            r8 = r17
            java.io.ByteArrayInputStream r17 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0138 }
            r26 = r17
            r17 = r26
            r18 = r26
            r19 = r8
            r18.<init>(r19)     // Catch:{ Exception -> 0x0138 }
            r9 = r17
            r17 = 0
            r10 = r17
            r17 = 0
            r11 = r17
            java.io.ObjectInputStream r17 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x010a, all -> 0x0124 }
            r26 = r17
            r17 = r26
            r18 = r26
            r19 = r9
            r18.<init>(r19)     // Catch:{ Exception -> 0x010a, all -> 0x0124 }
            r10 = r17
            r17 = r10
            java.lang.Object r17 = r17.readObject()     // Catch:{ Exception -> 0x010a, all -> 0x0124 }
            java.io.Serializable r17 = (java.io.Serializable) r17     // Catch:{ Exception -> 0x010a, all -> 0x0124 }
            r11 = r17
            r17 = r10
            if (r17 == 0) goto L_0x00c2
            r17 = r10
            r17.close()     // Catch:{ IOException -> 0x0102 }
        L_0x00c2:
            r17 = r9
            r17.close()     // Catch:{ IOException -> 0x0106 }
        L_0x00c7:
            r17 = r11
            if (r17 == 0) goto L_0x00d3
            r17 = r4
            r18 = r11
            boolean r17 = r17.add(r18)     // Catch:{ Exception -> 0x0138 }
        L_0x00d3:
            r17 = r6
            boolean r17 = r17.moveToNext()     // Catch:{ Exception -> 0x0138 }
            if (r17 != 0) goto L_0x0079
        L_0x00db:
            r17 = r6
            if (r17 == 0) goto L_0x00e4
            r17 = r6
            r17.close()     // Catch:{ all -> 0x0178 }
        L_0x00e4:
            r17 = r7
            if (r17 == 0) goto L_0x00ed
            r17 = r7
            r17.close()     // Catch:{ IOException -> 0x016e }
        L_0x00ed:
            r17 = 0
            r18 = r5
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x00fc
            r17 = r5
            r17.close()     // Catch:{ all -> 0x0178 }
        L_0x00fc:
            r17 = r4
            r2 = r17
            goto L_0x0024
        L_0x0102:
            r17 = move-exception
            r12 = r17
            goto L_0x00c2
        L_0x0106:
            r17 = move-exception
            r12 = r17
            goto L_0x00c7
        L_0x010a:
            r17 = move-exception
            r12 = r17
            r17 = r10
            if (r17 == 0) goto L_0x0116
            r17 = r10
            r17.close()     // Catch:{ IOException -> 0x011c }
        L_0x0116:
            r17 = r9
            r17.close()     // Catch:{ IOException -> 0x0120 }
            goto L_0x00c7
        L_0x011c:
            r17 = move-exception
            r12 = r17
            goto L_0x0116
        L_0x0120:
            r17 = move-exception
            r12 = r17
            goto L_0x00c7
        L_0x0124:
            r17 = move-exception
            r13 = r17
            r17 = r10
            if (r17 == 0) goto L_0x0130
            r17 = r10
            r17.close()     // Catch:{ IOException -> 0x0166 }
        L_0x0130:
            r17 = r9
            r17.close()     // Catch:{ IOException -> 0x016a }
        L_0x0135:
            r17 = r13
            throw r17     // Catch:{ Exception -> 0x0138 }
        L_0x0138:
            r17 = move-exception
            r8 = r17
            java.lang.String r17 = "openSDK_LOG.ReportDatabaseHelper"
            java.lang.String r18 = "getReportItemFromDB has exception."
            r19 = r8
            com.tencent.open.a.f.b(r17, r18, r19)     // Catch:{ all -> 0x0184 }
            r17 = r6
            if (r17 == 0) goto L_0x014d
            r17 = r6
            r17.close()     // Catch:{ all -> 0x0178 }
        L_0x014d:
            r17 = r7
            if (r17 == 0) goto L_0x0156
            r17 = r7
            r17.close()     // Catch:{ IOException -> 0x017b }
        L_0x0156:
            r17 = 0
            r18 = r5
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x00fc
            r17 = r5
            r17.close()     // Catch:{ all -> 0x0178 }
            goto L_0x00fc
        L_0x0166:
            r17 = move-exception
            r14 = r17
            goto L_0x0130
        L_0x016a:
            r17 = move-exception
            r14 = r17
            goto L_0x0135
        L_0x016e:
            r17 = move-exception
            r8 = r17
            r17 = r8
            r17.printStackTrace()     // Catch:{ all -> 0x0178 }
            goto L_0x00ed
        L_0x0178:
            r2 = move-exception
            monitor-exit(r25)
            throw r2
        L_0x017b:
            r17 = move-exception
            r8 = r17
            r17 = r8
            r17.printStackTrace()     // Catch:{ all -> 0x0178 }
            goto L_0x0156
        L_0x0184:
            r17 = move-exception
            r15 = r17
            r17 = r6
            if (r17 == 0) goto L_0x0190
            r17 = r6
            r17.close()     // Catch:{ all -> 0x0178 }
        L_0x0190:
            r17 = r7
            if (r17 == 0) goto L_0x0199
            r17 = r7
            r17.close()     // Catch:{ IOException -> 0x01ab }
        L_0x0199:
            r17 = 0
            r18 = r5
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x01a8
            r17 = r5
            r17.close()     // Catch:{ all -> 0x0178 }
        L_0x01a8:
            r17 = r15
            throw r17     // Catch:{ all -> 0x0178 }
        L_0x01ab:
            r17 = move-exception
            r16 = r17
            r17 = r16
            r17.printStackTrace()     // Catch:{ all -> 0x0178 }
            goto L_0x0199
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.f.a(java.lang.String):java.util.List");
    }

    public synchronized void a(String str, List<Serializable> list) {
        ContentValues contentValues;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2;
        String str2 = str;
        List<Serializable> list2 = list;
        synchronized (this) {
            int size = list2.size();
            if (size != 0) {
                int i = size <= 20 ? size : 20;
                if (!TextUtils.isEmpty(str2)) {
                    b(str2);
                    SQLiteDatabase writableDatabase = getWritableDatabase();
                    if (writableDatabase != null) {
                        writableDatabase.beginTransaction();
                        try {
                            new ContentValues();
                            ContentValues contentValues2 = contentValues;
                            for (int i2 = 0; i2 < i; i2++) {
                                Serializable serializable = list2.get(i2);
                                if (serializable != null) {
                                    contentValues2.put(SocialConstants.PARAM_TYPE, str2);
                                    ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream;
                                    new ByteArrayOutputStream(512);
                                    byteArrayOutputStream2 = byteArrayOutputStream3;
                                    objectOutputStream = null;
                                    new ObjectOutputStream(byteArrayOutputStream2);
                                    objectOutputStream = objectOutputStream2;
                                    objectOutputStream.writeObject(serializable);
                                    if (objectOutputStream != null) {
                                        try {
                                            objectOutputStream.close();
                                        } catch (IOException e) {
                                            IOException iOException = e;
                                        }
                                    }
                                    try {
                                        byteArrayOutputStream2.close();
                                    } catch (IOException e2) {
                                        IOException iOException2 = e2;
                                    }
                                    contentValues2.put("blob", byteArrayOutputStream2.toByteArray());
                                    long insert = writableDatabase.insert("via_cgi_report", (String) null, contentValues2);
                                }
                                contentValues2.clear();
                            }
                            writableDatabase.setTransactionSuccessful();
                            writableDatabase.endTransaction();
                            if (null != writableDatabase) {
                                writableDatabase.close();
                            }
                        } catch (IOException e3) {
                            IOException iOException3 = e3;
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException e4) {
                                    IOException iOException4 = e4;
                                }
                            }
                            try {
                                byteArrayOutputStream2.close();
                            } catch (IOException e5) {
                                IOException iOException5 = e5;
                            }
                        } catch (Exception e6) {
                            Exception exc = e6;
                            try {
                                com.tencent.open.a.f.e("openSDK_LOG.ReportDatabaseHelper", "saveReportItemToDB has exception.");
                                writableDatabase.endTransaction();
                                if (null != writableDatabase) {
                                    writableDatabase.close();
                                }
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                writableDatabase.endTransaction();
                                if (null != writableDatabase) {
                                    writableDatabase.close();
                                }
                                throw th2;
                            }
                        } catch (Throwable th3) {
                            Throwable th4 = th3;
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException e7) {
                                    IOException iOException6 = e7;
                                }
                            }
                            try {
                                byteArrayOutputStream2.close();
                            } catch (IOException e8) {
                                IOException iOException7 = e8;
                            }
                            throw th4;
                        }
                    }
                }
            }
        }
    }

    public synchronized void b(String str) {
        String str2 = str;
        synchronized (this) {
            if (!TextUtils.isEmpty(str2)) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase != null) {
                    try {
                        int delete = writableDatabase.delete("via_cgi_report", "type = ?", new String[]{str2});
                        if (null != writableDatabase) {
                            writableDatabase.close();
                        }
                    } catch (Exception e) {
                        com.tencent.open.a.f.b("openSDK_LOG.ReportDatabaseHelper", "clearReportItem has exception.", e);
                        if (null != writableDatabase) {
                            writableDatabase.close();
                        }
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        if (null != writableDatabase) {
                            writableDatabase.close();
                        }
                        throw th2;
                    }
                }
            }
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS via_cgi_report( _id INTEGER PRIMARY KEY,key TEXT,type TEXT,blob BLOB);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        int i3 = i;
        int i4 = i2;
        sQLiteDatabase2.execSQL("DROP TABLE IF EXISTS via_cgi_report");
        onCreate(sQLiteDatabase2);
    }
}
