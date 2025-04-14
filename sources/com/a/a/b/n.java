package com.a.a.b;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.androlua.LuaEditor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class n extends AsyncTask {
    private static int c;
    protected final e a;
    private ProgressDialog b;
    private LuaEditor d;
    private File e;
    private long f = this.e.length();

    /* JADX WARNING: type inference failed for: r3v0, types: [com.a.a.b.e$a, com.androlua.LuaEditor] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public n(com.androlua.LuaEditor r3, java.io.File r4) {
        /*
            r2 = this;
            r2.<init>()
            r2.e = r4
            java.io.File r4 = r2.e
            long r0 = r4.length()
            r2.f = r0
            r2.d = r3
            com.a.a.b.e r4 = new com.a.a.b.e
            r4.<init>(r3)
            r2.a = r4
            android.app.ProgressDialog r4 = new android.app.ProgressDialog
            android.content.Context r3 = r3.getContext()
            r4.<init>(r3)
            r2.b = r4
            android.app.ProgressDialog r3 = r2.b
            r4 = 1
            r3.setProgressStyle(r4)
            android.app.ProgressDialog r3 = r2.b
            java.lang.String r4 = "正在打开"
            r3.setTitle(r4)
            android.app.ProgressDialog r3 = r2.b
            r4 = 17301659(0x108009b, float:2.497969E-38)
            r3.setIcon(r4)
            android.app.ProgressDialog r3 = r2.b
            long r0 = r2.f
            int r4 = (int) r0
            r3.setMax(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.n.<init>(com.androlua.LuaEditor, java.io.File):void");
    }

    private byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        byte[] bArr = new byte[4096];
        c = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                byteArrayOutputStream.write(bArr, 0, read);
                c += read;
                publishProgress(new Object[0]);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public void a() {
        execute(new Object[0]);
        this.b.show();
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        try {
            return new String(a(new FileInputStream(this.e)));
        } catch (Exception e2) {
            this.b.setMessage(e2.getMessage());
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        super.onPostExecute(obj);
        this.d.setText((String) obj);
        this.b.dismiss();
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdate(Object[] objArr) {
        this.b.setProgress(c);
        super.onProgressUpdate(objArr);
    }
}
