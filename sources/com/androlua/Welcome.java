package com.androlua;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import com.a.a.a.a.a.a.a;
import com.luajava.LuaFunction;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Welcome extends Activity {
    private boolean a;
    private LuaApplication b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public long e;
    /* access modifiers changed from: private */
    public long f;
    private boolean g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public String i;
    private ArrayList<String> j;

    @SuppressLint({"StaticFieldLeak"})
    private class UpdateTask extends AsyncTask<String, String, String> {
        private UpdateTask() {
        }

        private void a(long j, long j2) {
            LuaFunction function;
            LuaState newLuaState = LuaStateFactory.newLuaState();
            newLuaState.openLibs();
            try {
                if (newLuaState.LloadBuffer(LuaUtil.readAsset(Welcome.this, "update.lua"), "update") == 0 && newLuaState.pcall(0, 0, 0) == 0 && (function = newLuaState.getFunction("onUpdate")) != null) {
                    function.call(Welcome.this.h, Welcome.this.i);
                }
            } catch (Exception e) {
                a.a(e);
            }
            try {
                a("assets", Welcome.this.d);
                a("lua", Welcome.this.c);
            } catch (IOException e2) {
                b(e2.getMessage());
            }
        }

        private void a(String str, String str2) {
            int length = str.length() + 1;
            ZipFile zipFile = new ZipFile(Welcome.this.getApplicationInfo().publicSourceDir);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                String name = zipEntry.getName();
                if (name.indexOf(str) == 0) {
                    String substring = name.substring(length);
                    if (zipEntry.isDirectory()) {
                        File file = new File(str2 + File.separator + substring);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                    } else {
                        String str3 = str2 + File.separator + substring;
                        File file2 = new File(str3);
                        File parentFile = new File(str3).getParentFile();
                        if (parentFile.exists() || parentFile.mkdirs()) {
                            try {
                                if (file2.exists() && zipEntry.getSize() == file2.length() && LuaUtil.getFileMD5(zipFile.getInputStream(zipEntry)).equals(LuaUtil.getFileMD5(file2))) {
                                }
                            } catch (NullPointerException unused) {
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(str2 + File.separator + substring);
                            InputStream inputStream = zipFile.getInputStream(zipEntry);
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.close();
                            inputStream.close();
                        } else {
                            throw new RuntimeException("create file " + parentFile.getName() + " fail");
                        }
                    }
                }
            }
            zipFile.close();
        }

        private void b(String str) {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String doInBackground(String[] strArr) {
            a(Welcome.this.e, Welcome.this.f);
            return null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            Welcome.this.startActivity();
        }
    }

    private void a(String str) {
        if (checkCallingOrSelfPermission(str) != 0) {
            this.j.add(str);
        }
    }

    public boolean checkInfo() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            long j2 = packageInfo.lastUpdateTime;
            String str = packageInfo.versionName;
            SharedPreferences sharedPreferences = getSharedPreferences("appInfo", 0);
            String string = sharedPreferences.getString("versionName", "");
            if (!str.equals(string)) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("versionName", str);
                edit.apply();
                this.g = true;
                this.h = str;
                this.i = string;
            }
            long j3 = sharedPreferences.getLong("lastUpdateTime", 0);
            if (j3 != j2) {
                SharedPreferences.Editor edit2 = sharedPreferences.edit();
                edit2.putLong("lastUpdateTime", j2);
                edit2.apply();
                this.a = true;
                this.e = j2;
                this.f = j3;
                return true;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            a.a(e2);
        }
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new TextView(this));
        this.b = (LuaApplication) getApplication();
        this.c = this.b.d;
        this.d = this.b.a;
        try {
            if (new File(this.b.getLuaPath("setup.png")).exists()) {
                getWindow().setBackgroundDrawable(new LuaBitmapDrawable(this.b, this.b.getLuaPath("setup.png"), getResources().getDrawable(2130771969)));
            }
        } catch (Exception e2) {
            a.a(e2);
        }
        if (checkInfo()) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    this.j = new ArrayList<>();
                    for (String a2 : getPackageManager().getPackageInfo(getPackageName(), 4096).requestedPermissions) {
                        try {
                            a(a2);
                        } catch (Exception e3) {
                            a.a(e3);
                        }
                    }
                    if (!this.j.isEmpty()) {
                        String[] strArr = new String[this.j.size()];
                        this.j.toArray(strArr);
                        requestPermissions(strArr, 0);
                        return;
                    }
                } catch (Exception e4) {
                    a.a(e4);
                }
            }
            new UpdateTask().execute(new String[0]);
            return;
        }
        startActivity();
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        new UpdateTask().execute(new String[0]);
    }

    public void startActivity() {
        Intent intent = new Intent(this, Main.class);
        if (this.g) {
            intent.putExtra("isVersionChanged", this.g);
            intent.putExtra("newVersionName", this.h);
            intent.putExtra("oldVersionName", this.i);
        }
        startActivity(intent);
        finish();
    }
}
