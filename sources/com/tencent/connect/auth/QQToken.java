package com.tencent.connect.auth;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.utils.d;
import com.tencent.open.utils.e;
import com.tencent.open.utils.k;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class QQToken {
    public static final int AUTH_QQ = 2;
    public static final int AUTH_QZONE = 3;
    public static final int AUTH_WEB = 1;
    private static SharedPreferences f;
    private String a;
    private String b;
    private String c;
    private int d = 1;
    private long e = -1;

    public QQToken(String str) {
        this.a = str;
    }

    @TargetApi(11)
    private static synchronized SharedPreferences a() {
        SharedPreferences sharedPreferences;
        synchronized (QQToken.class) {
            if (f == null) {
                f = e.a().getSharedPreferences("token_info_file", 0);
            }
            sharedPreferences = f;
        }
        return sharedPreferences;
    }

    private static synchronized JSONObject a(String str) {
        StringBuilder sb;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        String str2 = str;
        synchronized (QQToken.class) {
            if (e.a() == null) {
                f.c("QQToken", "loadJsonPreference context null");
                jSONObject = null;
            } else if (str2 == null) {
                jSONObject = null;
            } else {
                String string = a().getString(Base64.encodeToString(k.i(str2), 2), (String) null);
                if (string == null) {
                    f.c("QQToken", "loadJsonPreference encoded value null");
                    jSONObject = null;
                } else {
                    try {
                        JSONObject jSONObject3 = jSONObject2;
                        new JSONObject(d.b(string, "asdfghjk"));
                        jSONObject = jSONObject3;
                    } catch (Exception e2) {
                        Exception exc = e2;
                        new StringBuilder();
                        f.c("QQToken", sb.append("loadJsonPreference decode").append(exc.toString()).toString());
                        jSONObject = null;
                    }
                }
            }
        }
        return jSONObject;
    }

    private static synchronized void a(String str, JSONObject jSONObject) {
        String str2 = str;
        JSONObject jSONObject2 = jSONObject;
        synchronized (QQToken.class) {
            if (e.a() == null) {
                f.c("QQToken", "saveJsonPreference context null");
            } else if (!(str2 == null || jSONObject2 == null)) {
                try {
                    String string = jSONObject2.getString(Constants.PARAM_EXPIRES_IN);
                    if (!TextUtils.isEmpty(string)) {
                        JSONObject put = jSONObject2.put(Constants.PARAM_EXPIRES_TIME, System.currentTimeMillis() + (Long.parseLong(string) * 1000));
                        String encodeToString = Base64.encodeToString(k.i(str2), 2);
                        String a2 = d.a(jSONObject2.toString(), "asdfghjk");
                        if (encodeToString != null && a2 != null) {
                            boolean commit = a().edit().putString(encodeToString, a2).commit();
                        }
                    }
                } catch (Exception e2) {
                    Exception exc = e2;
                }
            }
        }
    }

    public String getAccessToken() {
        return this.b;
    }

    public String getAppId() {
        return this.a;
    }

    public int getAuthSource() {
        return this.d;
    }

    public long getExpireTimeInSecond() {
        return this.e;
    }

    public String getOpenId() {
        return this.c;
    }

    public boolean isSessionValid() {
        return this.b != null && System.currentTimeMillis() < this.e;
    }

    public JSONObject loadSession(String str) {
        StringBuilder sb;
        try {
            return a(str);
        } catch (Exception e2) {
            new StringBuilder();
            f.c("QQToken", sb.append("login loadSession").append(e2.toString()).toString());
            return null;
        }
    }

    public void saveSession(JSONObject jSONObject) {
        StringBuilder sb;
        try {
            a(this.a, jSONObject);
        } catch (Exception e2) {
            new StringBuilder();
            f.c("QQToken", sb.append("login saveSession").append(e2.toString()).toString());
        }
    }

    public void setAccessToken(String str, String str2) throws NumberFormatException {
        String str3 = str2;
        this.b = str;
        this.e = 0;
        if (str3 != null) {
            this.e = System.currentTimeMillis() + (Long.parseLong(str3) * 1000);
        }
    }

    public void setAppId(String str) {
        String str2 = str;
        this.a = str2;
    }

    public void setAuthSource(int i) {
        int i2 = i;
        this.d = i2;
    }

    public void setOpenId(String str) {
        String str2 = str;
        this.c = str2;
    }
}
