package com.androlua;

import android.util.Log;
import com.a.a.a.a.a.a.a;
import com.androlua.util.AsyncTaskX;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaString;
import com.tencent.connect.common.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Http {
    /* access modifiers changed from: private */
    public static HashMap<String, String> a;

    public static class HttpTask extends AsyncTaskX<Object, Object, Object> {
        private String a;
        private LuaObject b;
        private byte[] c;
        private String d;
        private String e;
        private String f;
        private HashMap<String, String> g;
        private String h;

        public HttpTask(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, LuaObject luaObject) {
            this.a = str;
            this.h = str2;
            this.f = str3;
            this.d = str4;
            this.e = str4;
            this.g = hashMap;
            this.b = luaObject;
        }

        private byte[] a(Map<String, String> map) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry next : map.entrySet()) {
                sb.append((String) next.getKey());
                sb.append("=");
                sb.append((String) next.getValue());
                sb.append("&");
            }
            return sb.toString().getBytes(this.d);
        }

        private byte[] d(Object[] objArr) {
            if (objArr.length != 1) {
                return null;
            }
            String str = objArr[0];
            if (str instanceof String) {
                return str.getBytes(this.d);
            }
            if (str.getClass().getComponentType() == Byte.TYPE) {
                return (byte[]) str;
            }
            if (str instanceof File) {
                return LuaUtil.readAll(new FileInputStream((File) str));
            }
            if (str instanceof Map) {
                return a((Map<String, String>) (Map) str);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public Object a(Object[] objArr) {
            int indexOf;
            Object[] objArr2 = objArr;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a).openConnection();
                httpURLConnection.setConnectTimeout(6000);
                HttpURLConnection.setFollowRedirects(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
                if (this.d == null) {
                    this.d = "UTF-8";
                }
                httpURLConnection.setRequestProperty("Accept-Charset", this.d);
                if (this.f != null) {
                    httpURLConnection.setRequestProperty("Cookie", this.f);
                }
                if (Http.a != null) {
                    for (Map.Entry entry : Http.a.entrySet()) {
                        httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (this.g != null) {
                    for (Map.Entry next : this.g.entrySet()) {
                        httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
                    }
                }
                if (this.h != null) {
                    httpURLConnection.setRequestMethod(this.h);
                }
                if (!Constants.HTTP_GET.equals(this.h) && objArr2.length != 0) {
                    this.c = d(objArr);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestProperty("Content-length", "" + this.c.length);
                }
                httpURLConnection.connect();
                if (!Constants.HTTP_GET.equals(this.h) || objArr2.length == 0) {
                    if (objArr2.length != 0) {
                        httpURLConnection.getOutputStream().write(this.c);
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    Map headerFields = httpURLConnection.getHeaderFields();
                    httpURLConnection.getContentEncoding();
                    List<String> list = (List) headerFields.get("Set-Cookie");
                    StringBuilder sb = new StringBuilder();
                    if (list != null) {
                        for (String append : list) {
                            sb.append(append);
                            sb.append(";");
                        }
                    }
                    List list2 = (List) headerFields.get("Content-Type");
                    if (list2 != null) {
                        Iterator it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String str = (String) it.next();
                            int indexOf2 = str.indexOf("charset");
                            if (indexOf2 != -1 && (indexOf = str.indexOf("=", indexOf2)) != -1) {
                                int indexOf3 = str.indexOf(";", indexOf);
                                if (indexOf3 == -1) {
                                    indexOf3 = str.length();
                                }
                                this.d = str.substring(indexOf + 1, indexOf3);
                            }
                        }
                    }
                    if (this.e == null) {
                        try {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            byte[] readAll = LuaUtil.readAll(inputStream);
                            inputStream.close();
                            return new Object[]{Integer.valueOf(responseCode), new LuaString(readAll), sb.toString(), headerFields};
                        } catch (Exception e2) {
                            a.a(e2);
                        }
                    }
                    StringBuilder sb2 = new StringBuilder();
                    try {
                        InputStream inputStream2 = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream2, this.d));
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null || isCancelled()) {
                                inputStream2.close();
                            } else {
                                sb2.append(readLine);
                                sb2.append(10);
                            }
                        }
                        inputStream2.close();
                    } catch (Exception e3) {
                        a.a(e3);
                    }
                    InputStream errorStream = httpURLConnection.getErrorStream();
                    if (errorStream != null) {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(errorStream, this.d));
                        while (true) {
                            String readLine2 = bufferedReader2.readLine();
                            if (readLine2 == null || isCancelled()) {
                                errorStream.close();
                            } else {
                                sb2.append(readLine2);
                                sb2.append(10);
                            }
                        }
                        errorStream.close();
                    }
                    return new Object[]{Integer.valueOf(responseCode), new String(sb2), sb.toString(), headerFields};
                }
                File file = new File((String) objArr2[0]);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                LuaUtil.copyFile(httpURLConnection.getInputStream(), (OutputStream) new FileOutputStream(file));
                return new Object[]{Integer.valueOf(httpURLConnection.getResponseCode()), objArr2[0], httpURLConnection.getHeaderFields()};
            } catch (Exception e4) {
                Exception exc = e4;
                a.a(exc);
                return new Object[]{-1, exc.getMessage()};
            }
        }

        /* access modifiers changed from: protected */
        public void a(Object obj) {
            if (!isCancelled()) {
                try {
                    this.b.call((Object[]) obj);
                } catch (LuaException e2) {
                    try {
                        this.b.getLuaState().getLuaObject("print").call(e2.getMessage());
                    } catch (LuaException unused) {
                    }
                    Log.i("lua", e2.getMessage());
                }
            }
        }

        public boolean cancel() {
            return super.cancel(true);
        }
    }

    private static String a(HashMap<String, String> hashMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : hashMap.entrySet()) {
            sb.append((String) next.getKey());
            sb.append("=");
            sb.append((String) next.getValue());
            sb.append("&");
        }
        if (!hashMap.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static byte[] a(HashMap<String, String> hashMap, HashMap<String, String> hashMap2, String str) {
        if (str == null) {
            str = "UTF-8";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (Map.Entry next : hashMap.entrySet()) {
            try {
                byteArrayOutputStream.write(String.format("--%s\r\nContent-Disposition:form-data;name=\"%s\"\r\n\r\n%s\r\n", new Object[]{"----qwertyuiopasdfghjklzxcvbnm", next.getKey(), next.getValue()}).getBytes(str));
            } catch (IOException e) {
                a.a(e);
            }
        }
        for (Map.Entry next2 : hashMap2.entrySet()) {
            try {
                byteArrayOutputStream.write(String.format("--%s\r\nContent-Disposition:form-data;name=\"%s\";filename=\"%s\"\r\nContent-Type:application/octet-stream\r\n\r\n", new Object[]{"----qwertyuiopasdfghjklzxcvbnm", next2.getKey(), next2.getValue()}).getBytes(str));
                byteArrayOutputStream.write(LuaUtil.readAll(new FileInputStream((String) next2.getValue())));
                byteArrayOutputStream.write("\r\n".getBytes(str));
            } catch (IOException e2) {
                a.a(e2);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static HttpTask delete(String str, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "DELETE", (String) null, (String) null, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = (!str2.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str2)) ? new HttpTask(str, "DELETE", str2, (String) null, (HashMap<String, String>) null, luaObject) : new HttpTask(str, "DELETE", (String) null, str2, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "DELETE", str2, str3, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, String str2, String str3, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "DELETE", str2, str3, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, String str2, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = (!str2.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str2)) ? new HttpTask(str, "DELETE", str2, (String) null, hashMap, luaObject) : new HttpTask(str, "DELETE", (String) null, str2, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask delete(String str, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "DELETE", (String) null, (String) null, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask download(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_GET, (String) null, (String) null, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask download(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_GET, str3, (String) null, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask download(String str, String str2, String str3, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_GET, str3, (String) null, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask download(String str, String str2, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_GET, (String) null, (String) null, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask get(String str, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_GET, (String) null, (String) null, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = (!str2.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str2)) ? new HttpTask(str, Constants.HTTP_GET, str2, (String) null, (HashMap<String, String>) null, luaObject) : new HttpTask(str, Constants.HTTP_GET, (String) null, str2, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_GET, str2, str3, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, String str2, String str3, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_GET, str2, str3, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, String str2, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = (!str2.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str2)) ? new HttpTask(str, Constants.HTTP_GET, str2, (String) null, hashMap, luaObject) : new HttpTask(str, Constants.HTTP_GET, (String) null, str2, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HttpTask get(String str, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_GET, (String) null, (String) null, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[0]);
        return httpTask;
    }

    public static HashMap<String, String> getHeader() {
        return a;
    }

    public static HttpTask post(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_POST, (String) null, (String) null, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask post(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = (!str3.matches("[\\w\\-.:]+") || !Charset.isSupported(str3)) ? new HttpTask(str, Constants.HTTP_POST, str3, (String) null, (HashMap<String, String>) null, luaObject) : new HttpTask(str, Constants.HTTP_POST, (String) null, str3, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask post(String str, String str2, String str3, String str4, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_POST, str3, str4, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask post(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_POST, str3, str4, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask post(String str, String str2, String str3, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = (!str3.matches("[\\w\\-.:]+") || !Charset.isSupported(str3)) ? new HttpTask(str, Constants.HTTP_POST, str3, (String) null, hashMap, luaObject) : new HttpTask(str, Constants.HTTP_POST, (String) null, str3, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask post(String str, String str2, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_POST, (String) null, (String) null, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, LuaObject luaObject) {
        return post(str, a(hashMap), luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, String str2, LuaObject luaObject) {
        return post(str, a(hashMap), str2, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, String str2, String str3, LuaObject luaObject) {
        return post(str, a(hashMap), str2, str3, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, String str2, String str3, HashMap<String, String> hashMap2, LuaObject luaObject) {
        return post(str, a(hashMap), str2, str3, hashMap2, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, String str2, HashMap<String, String> hashMap2, LuaObject luaObject) {
        return post(str, a(hashMap), str2, hashMap2, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, LuaObject luaObject) {
        return post(str, hashMap, hashMap2, (String) null, (String) null, (HashMap<String, String>) null, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, String str2, LuaObject luaObject) {
        return post(str, hashMap, hashMap2, str2, (HashMap<String, String>) new HashMap(), luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, String str2, String str3, LuaObject luaObject) {
        return post(str, hashMap, hashMap2, str2, str3, (HashMap<String, String>) null, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, String str2, String str3, HashMap<String, String> hashMap3, LuaObject luaObject) {
        if (hashMap3 == null) {
            hashMap3 = new HashMap<>();
        }
        HashMap<String, String> hashMap4 = hashMap3;
        hashMap4.put("Content-Type", "multipart/form-data;boundary=----qwertyuiopasdfghjklzxcvbnm");
        HttpTask httpTask = new HttpTask(str, Constants.HTTP_POST, str2, str3, hashMap4, luaObject);
        httpTask.execute((Params[]) new Object[]{a(hashMap, hashMap2, str3)});
        return httpTask;
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, String str2, HashMap<String, String> hashMap3, LuaObject luaObject) {
        return (!str2.matches("[\\w\\-.:]+") || !Charset.isSupported(str2)) ? post(str, hashMap, hashMap2, (String) null, str2, hashMap3, luaObject) : post(str, hashMap, hashMap2, str2, (String) null, hashMap3, luaObject);
    }

    public static HttpTask post(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HashMap<String, String> hashMap3, LuaObject luaObject) {
        return post(str, hashMap, hashMap2, (String) null, hashMap3, luaObject);
    }

    public static HttpTask put(String str, String str2, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "PUT", (String) null, (String) null, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask put(String str, String str2, String str3, LuaObject luaObject) {
        HttpTask httpTask = (!str3.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str3)) ? new HttpTask(str, "PUT", str3, (String) null, (HashMap<String, String>) null, luaObject) : new HttpTask(str, "PUT", (String) null, str3, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask put(String str, String str2, String str3, String str4, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "PUT", str3, str4, (HashMap<String, String>) null, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask put(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "PUT", str3, str4, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask put(String str, String str2, String str3, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = (!str3.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str3)) ? new HttpTask(str, "PUT", str3, (String) null, hashMap, luaObject) : new HttpTask(str, "PUT", (String) null, str3, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static HttpTask put(String str, String str2, HashMap<String, String> hashMap, LuaObject luaObject) {
        HttpTask httpTask = new HttpTask(str, "PUT", (String) null, (String) null, hashMap, luaObject);
        httpTask.execute((Params[]) new Object[]{str2});
        return httpTask;
    }

    public static void setHeader(HashMap<String, String> hashMap) {
        a = hashMap;
    }

    public static void setUserAgent(String str) {
        if (a == null) {
            a = new HashMap<>();
        }
        a.put("User-Agent", str);
    }
}
