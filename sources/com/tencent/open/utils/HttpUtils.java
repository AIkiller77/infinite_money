package com.tencent.open.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.a.f;
import com.tencent.tauth.IRequestListener;
import java.io.ByteArrayOutputStream;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.util.InvalidPropertiesFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class HttpUtils {

    /* compiled from: ProGuard */
    public static class HttpStatusException extends Exception {
        public static final String ERROR_INFO = "http status code error:";

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public HttpStatusException(String str) {
            super(str);
        }
    }

    /* compiled from: ProGuard */
    public static class NetworkUnavailableException extends Exception {
        public static final String ERROR_INFO = "network unavailable";

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public NetworkUnavailableException(String str) {
            super(str);
        }
    }

    /* compiled from: ProGuard */
    public static class a {
        public final String a;
        public final int b;

        private a(String str, int i) {
            this.a = str;
            this.b = i;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ a(String str, int i, AnonymousClass1 r10) {
            this(str, i);
            AnonymousClass1 r3 = r10;
        }
    }

    private HttpUtils() {
    }

    private static int a(Context context) {
        Context context2 = context;
        int i = -1;
        if (Build.VERSION.SDK_INT >= 11) {
            String property = System.getProperty("http.proxyPort");
            if (!TextUtils.isEmpty(property)) {
                try {
                    i = Integer.parseInt(property);
                } catch (NumberFormatException e) {
                    NumberFormatException numberFormatException = e;
                }
            }
        } else if (context2 != null) {
            i = Proxy.getPort(context2);
            if (i < 0) {
                i = Proxy.getDefaultPort();
            }
        } else {
            i = Proxy.getDefaultPort();
        }
        return i;
    }

    private static String a(HttpResponse httpResponse) throws IllegalStateException, IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        String str;
        InputStream inputStream;
        HttpResponse httpResponse2 = httpResponse;
        Object obj = "";
        InputStream content = httpResponse2.getEntity().getContent();
        new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
        Header firstHeader = httpResponse2.getFirstHeader("Content-Encoding");
        if (firstHeader != null && firstHeader.getValue().toLowerCase().indexOf("gzip") > -1) {
            new GZIPInputStream(content);
            content = inputStream;
        }
        byte[] bArr = new byte[512];
        while (true) {
            int read = content.read(bArr);
            int i = read;
            if (read != -1) {
                byteArrayOutputStream2.write(bArr, 0, i);
            } else {
                new String(byteArrayOutputStream2.toByteArray(), "UTF-8");
                String str2 = str;
                content.close();
                return str2;
            }
        }
    }

    private static void a(Context context, QQToken qQToken, String str) {
        Context context2 = context;
        QQToken qQToken2 = qQToken;
        String str2 = str;
        if (str2.indexOf("add_share") > -1 || str2.indexOf("upload_pic") > -1 || str2.indexOf("add_topic") > -1 || str2.indexOf("set_user_face") > -1 || str2.indexOf("add_t") > -1 || str2.indexOf("add_pic_t") > -1 || str2.indexOf("add_pic_url") > -1 || str2.indexOf("add_video") > -1) {
            com.tencent.connect.a.a.a(context2, qQToken2, "requireApi", str2);
        }
    }

    private static String b(Context context) {
        String property;
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 11) {
            property = System.getProperty("http.proxyHost");
        } else if (context2 != null) {
            property = Proxy.getHost(context2);
            if (TextUtils.isEmpty(property)) {
                property = Proxy.getDefaultHost();
            }
        } else {
            property = Proxy.getDefaultHost();
        }
        return property;
    }

    public static String encodePostBody(Bundle bundle, String str) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Bundle bundle2 = bundle;
        String str2 = str;
        if (bundle2 == null) {
            return "";
        }
        new StringBuilder();
        StringBuilder sb4 = sb;
        int i = -1;
        int size = bundle2.size();
        for (String str3 : bundle2.keySet()) {
            i++;
            Object obj = bundle2.get(str3);
            if (obj instanceof String) {
                new StringBuilder();
                StringBuilder append = sb4.append(sb2.append("Content-Disposition: form-data; name=\"").append(str3).append("\"").append("\r\n").append("\r\n").append((String) obj).toString());
                if (i < size - 1) {
                    new StringBuilder();
                    StringBuilder append2 = sb4.append(sb3.append("\r\n--").append(str2).append("\r\n").toString());
                }
            }
        }
        return sb4.toString();
    }

    public static String encodeUrl(Bundle bundle) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Bundle bundle2 = bundle;
        if (bundle2 == null) {
            return "";
        }
        new StringBuilder();
        StringBuilder sb5 = sb;
        boolean z = true;
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if ((obj instanceof String) || (obj instanceof String[])) {
                if (obj instanceof String[]) {
                    if (z) {
                        z = false;
                    } else {
                        StringBuilder append = sb5.append("&");
                    }
                    new StringBuilder();
                    StringBuilder append2 = sb5.append(sb3.append(URLEncoder.encode(str)).append("=").toString());
                    String[] stringArray = bundle2.getStringArray(str);
                    if (stringArray != null) {
                        for (int i = 0; i < stringArray.length; i++) {
                            if (i == 0) {
                                StringBuilder append3 = sb5.append(URLEncoder.encode(stringArray[i]));
                            } else {
                                new StringBuilder();
                                StringBuilder append4 = sb5.append(URLEncoder.encode(sb4.append(",").append(stringArray[i]).toString()));
                            }
                        }
                    }
                } else {
                    if (z) {
                        z = false;
                    } else {
                        StringBuilder append5 = sb5.append("&");
                    }
                    new StringBuilder();
                    StringBuilder append6 = sb5.append(sb2.append(URLEncoder.encode(str)).append("=").append(URLEncoder.encode(bundle2.getString(str))).toString());
                }
            }
        }
        return sb5.toString();
    }

    public static int getErrorCodeFromException(IOException iOException) {
        IOException iOException2 = iOException;
        if (iOException2 instanceof CharConversionException) {
            return -20;
        }
        if (iOException2 instanceof MalformedInputException) {
            return -21;
        }
        if (iOException2 instanceof UnmappableCharacterException) {
            return -22;
        }
        if (iOException2 instanceof HttpResponseException) {
            return -23;
        }
        if (iOException2 instanceof ClosedChannelException) {
            return -24;
        }
        if (iOException2 instanceof ConnectionClosedException) {
            return -25;
        }
        if (iOException2 instanceof EOFException) {
            return -26;
        }
        if (iOException2 instanceof FileLockInterruptionException) {
            return -27;
        }
        if (iOException2 instanceof FileNotFoundException) {
            return -28;
        }
        if (iOException2 instanceof HttpRetryException) {
            return -29;
        }
        if (iOException2 instanceof ConnectTimeoutException) {
            return -7;
        }
        if (iOException2 instanceof SocketTimeoutException) {
            return -8;
        }
        if (iOException2 instanceof InvalidPropertiesFormatException) {
            return -30;
        }
        if (iOException2 instanceof MalformedChunkCodingException) {
            return -31;
        }
        if (iOException2 instanceof MalformedURLException) {
            return -3;
        }
        if (iOException2 instanceof NoHttpResponseException) {
            return -32;
        }
        if (iOException2 instanceof InvalidClassException) {
            return -33;
        }
        if (iOException2 instanceof InvalidObjectException) {
            return -34;
        }
        if (iOException2 instanceof NotActiveException) {
            return -35;
        }
        if (iOException2 instanceof NotSerializableException) {
            return -36;
        }
        if (iOException2 instanceof OptionalDataException) {
            return -37;
        }
        if (iOException2 instanceof StreamCorruptedException) {
            return -38;
        }
        if (iOException2 instanceof WriteAbortedException) {
            return -39;
        }
        if (iOException2 instanceof ProtocolException) {
            return -40;
        }
        if (iOException2 instanceof SSLHandshakeException) {
            return -41;
        }
        if (iOException2 instanceof SSLKeyException) {
            return -42;
        }
        if (iOException2 instanceof SSLPeerUnverifiedException) {
            return -43;
        }
        if (iOException2 instanceof SSLProtocolException) {
            return -44;
        }
        if (iOException2 instanceof BindException) {
            return -45;
        }
        if (iOException2 instanceof ConnectException) {
            return -46;
        }
        if (iOException2 instanceof NoRouteToHostException) {
            return -47;
        }
        if (iOException2 instanceof PortUnreachableException) {
            return -48;
        }
        if (iOException2 instanceof SyncFailedException) {
            return -49;
        }
        if (iOException2 instanceof UTFDataFormatException) {
            return -50;
        }
        if (iOException2 instanceof UnknownHostException) {
            return -51;
        }
        if (iOException2 instanceof UnknownServiceException) {
            return -52;
        }
        if (iOException2 instanceof UnsupportedEncodingException) {
            return -53;
        }
        return iOException2 instanceof ZipException ? -54 : -2;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.http.client.HttpClient getHttpClient(android.content.Context r21, java.lang.String r22, java.lang.String r23) {
        /*
            r1 = r21
            r2 = r22
            r3 = r23
            org.apache.http.conn.scheme.SchemeRegistry r14 = new org.apache.http.conn.scheme.SchemeRegistry
            r20 = r14
            r14 = r20
            r15 = r20
            r15.<init>()
            r4 = r14
            r14 = r4
            org.apache.http.conn.scheme.Scheme r15 = new org.apache.http.conn.scheme.Scheme
            r20 = r15
            r15 = r20
            r16 = r20
            java.lang.String r17 = "http"
            org.apache.http.conn.scheme.PlainSocketFactory r18 = org.apache.http.conn.scheme.PlainSocketFactory.getSocketFactory()
            r19 = 80
            r16.<init>(r17, r18, r19)
            org.apache.http.conn.scheme.Scheme r14 = r14.register(r15)
            int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0151 }
            r15 = 23
            if (r14 < r15) goto L_0x0130
            org.apache.http.conn.ssl.SSLSocketFactory r14 = org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory()     // Catch:{ Exception -> 0x0151 }
            r5 = r14
            r14 = r5
            org.apache.http.conn.ssl.X509HostnameVerifier r15 = org.apache.http.conn.ssl.SSLSocketFactory.STRICT_HOSTNAME_VERIFIER     // Catch:{ Exception -> 0x0151 }
            r14.setHostnameVerifier(r15)     // Catch:{ Exception -> 0x0151 }
            r14 = r4
            org.apache.http.conn.scheme.Scheme r15 = new org.apache.http.conn.scheme.Scheme     // Catch:{ Exception -> 0x0151 }
            r20 = r15
            r15 = r20
            r16 = r20
            java.lang.String r17 = "https"
            r18 = r5
            r19 = 443(0x1bb, float:6.21E-43)
            r16.<init>(r17, r18, r19)     // Catch:{ Exception -> 0x0151 }
            org.apache.http.conn.scheme.Scheme r14 = r14.register(r15)     // Catch:{ Exception -> 0x0151 }
        L_0x0051:
            org.apache.http.params.BasicHttpParams r14 = new org.apache.http.params.BasicHttpParams
            r20 = r14
            r14 = r20
            r15 = r20
            r15.<init>()
            r5 = r14
            r14 = 0
            r6 = r14
            r14 = r1
            if (r14 == 0) goto L_0x0069
            r14 = r1
            r15 = r2
            com.tencent.open.utils.f r14 = com.tencent.open.utils.f.a((android.content.Context) r14, (java.lang.String) r15)
            r6 = r14
        L_0x0069:
            r14 = 0
            r7 = r14
            r14 = 0
            r8 = r14
            r14 = r6
            if (r14 == 0) goto L_0x0080
            r14 = r6
            java.lang.String r15 = "Common_HttpConnectionTimeout"
            int r14 = r14.a((java.lang.String) r15)
            r7 = r14
            r14 = r6
            java.lang.String r15 = "Common_SocketConnectionTimeout"
            int r14 = r14.a((java.lang.String) r15)
            r8 = r14
        L_0x0080:
            r14 = r7
            if (r14 != 0) goto L_0x016d
            r14 = 15000(0x3a98, float:2.102E-41)
        L_0x0085:
            r7 = r14
            r14 = r8
            if (r14 != 0) goto L_0x0170
            r14 = 30000(0x7530, float:4.2039E-41)
        L_0x008b:
            r8 = r14
            r14 = r5
            r15 = r7
            org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r14, r15)
            r14 = r5
            r15 = r8
            org.apache.http.params.HttpConnectionParams.setSoTimeout(r14, r15)
            r14 = r5
            org.apache.http.HttpVersion r15 = org.apache.http.HttpVersion.HTTP_1_1
            org.apache.http.params.HttpProtocolParams.setVersion(r14, r15)
            r14 = r5
            java.lang.String r15 = "UTF-8"
            org.apache.http.params.HttpProtocolParams.setContentCharset(r14, r15)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r20 = r14
            r14 = r20
            r15 = r20
            r15.<init>()
            java.lang.String r15 = "AndroidSDK_"
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = android.os.Build.VERSION.SDK
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = "_"
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = android.os.Build.DEVICE
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = "_"
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = android.os.Build.VERSION.RELEASE
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r9 = r14
            r14 = r5
            r15 = r9
            org.apache.http.params.HttpProtocolParams.setUserAgent(r14, r15)
            org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager r14 = new org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = r5
            r17 = r4
            r15.<init>(r16, r17)
            r10 = r14
            org.apache.http.impl.client.DefaultHttpClient r14 = new org.apache.http.impl.client.DefaultHttpClient
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = r10
            r17 = r5
            r15.<init>(r16, r17)
            r11 = r14
            r14 = r1
            com.tencent.open.utils.HttpUtils$a r14 = getProxy(r14)
            r12 = r14
            r14 = r12
            if (r14 == 0) goto L_0x012d
            org.apache.http.HttpHost r14 = new org.apache.http.HttpHost
            r20 = r14
            r14 = r20
            r15 = r20
            r16 = r12
            r0 = r16
            java.lang.String r0 = r0.a
            r16 = r0
            r17 = r12
            r0 = r17
            int r0 = r0.b
            r17 = r0
            r15.<init>(r16, r17)
            r13 = r14
            r14 = r11
            org.apache.http.params.HttpParams r14 = r14.getParams()
            java.lang.String r15 = "http.route.default-proxy"
            r16 = r13
            org.apache.http.params.HttpParams r14 = r14.setParameter(r15, r16)
        L_0x012d:
            r14 = r11
            r1 = r14
            return r1
        L_0x0130:
            r14 = r4
            org.apache.http.conn.scheme.Scheme r15 = new org.apache.http.conn.scheme.Scheme     // Catch:{ Exception -> 0x0151 }
            r20 = r15
            r15 = r20
            r16 = r20
            java.lang.String r17 = "https"
            com.tencent.open.utils.j r18 = new com.tencent.open.utils.j     // Catch:{ Exception -> 0x0151 }
            r20 = r18
            r18 = r20
            r19 = r20
            r19.<init>()     // Catch:{ Exception -> 0x0151 }
            r19 = 443(0x1bb, float:6.21E-43)
            r16.<init>(r17, r18, r19)     // Catch:{ Exception -> 0x0151 }
            org.apache.http.conn.scheme.Scheme r14 = r14.register(r15)     // Catch:{ Exception -> 0x0151 }
            goto L_0x0051
        L_0x0151:
            r14 = move-exception
            r5 = r14
            r14 = r4
            org.apache.http.conn.scheme.Scheme r15 = new org.apache.http.conn.scheme.Scheme
            r20 = r15
            r15 = r20
            r16 = r20
            java.lang.String r17 = "https"
            org.apache.http.conn.ssl.SSLSocketFactory r18 = org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory()
            r19 = 443(0x1bb, float:6.21E-43)
            r16.<init>(r17, r18, r19)
            org.apache.http.conn.scheme.Scheme r14 = r14.register(r15)
            goto L_0x0051
        L_0x016d:
            r14 = r7
            goto L_0x0085
        L_0x0170:
            r14 = r8
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.HttpUtils.getHttpClient(android.content.Context, java.lang.String, java.lang.String):org.apache.http.client.HttpClient");
    }

    public static a getProxy(Context context) {
        a aVar;
        Context context2 = context;
        if (context2 == null) {
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        if (activeNetworkInfo.getType() == 0) {
            String b = b(context2);
            int a2 = a(context2);
            if (!TextUtils.isEmpty(b) && a2 >= 0) {
                new a(b, a2, (AnonymousClass1) null);
                return aVar;
            }
        }
        return null;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.open.utils.k.a openUrl2(android.content.Context r25, java.lang.String r26, java.lang.String r27, android.os.Bundle r28) throws java.net.MalformedURLException, java.io.IOException, com.tencent.open.utils.HttpUtils.NetworkUnavailableException, com.tencent.open.utils.HttpUtils.HttpStatusException {
        /*
            r2 = r25
            r3 = r26
            r4 = r27
            r5 = r28
            r20 = r2
            if (r20 == 0) goto L_0x003e
            r20 = r2
            java.lang.String r21 = "connectivity"
            java.lang.Object r20 = r20.getSystemService(r21)
            android.net.ConnectivityManager r20 = (android.net.ConnectivityManager) r20
            r6 = r20
            r20 = r6
            if (r20 == 0) goto L_0x003e
            r20 = r6
            android.net.NetworkInfo r20 = r20.getActiveNetworkInfo()
            r7 = r20
            r20 = r7
            if (r20 == 0) goto L_0x0030
            r20 = r7
            boolean r20 = r20.isAvailable()
            if (r20 != 0) goto L_0x003e
        L_0x0030:
            com.tencent.open.utils.HttpUtils$NetworkUnavailableException r20 = new com.tencent.open.utils.HttpUtils$NetworkUnavailableException
            r24 = r20
            r20 = r24
            r21 = r24
            java.lang.String r22 = "network unavailable"
            r21.<init>(r22)
            throw r20
        L_0x003e:
            r20 = 0
            r6 = r20
            r20 = r5
            if (r20 == 0) goto L_0x0188
            android.os.Bundle r20 = new android.os.Bundle
            r24 = r20
            r20 = r24
            r21 = r24
            r22 = r5
            r21.<init>(r22)
            r6 = r20
        L_0x0055:
            java.lang.String r20 = ""
            r7 = r20
            r20 = r6
            java.lang.String r21 = "appid_for_getting_config"
            java.lang.String r20 = r20.getString(r21)
            r8 = r20
            r20 = r6
            java.lang.String r21 = "appid_for_getting_config"
            r20.remove(r21)
            r20 = r2
            r21 = r8
            r22 = r3
            org.apache.http.client.HttpClient r20 = getHttpClient(r20, r21, r22)
            r9 = r20
            r20 = 0
            r10 = r20
            r20 = 0
            r11 = r20
            r20 = r4
            java.lang.String r21 = "GET"
            boolean r20 = r20.equals(r21)
            if (r20 == 0) goto L_0x01b6
            r20 = r6
            java.lang.String r20 = encodeUrl(r20)
            r12 = r20
            r20 = r11
            r21 = r12
            int r21 = r21.length()
            int r20 = r20 + r21
            r11 = r20
            java.lang.String r20 = "openSDK_LOG.HttpUtils"
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r24 = r21
            r21 = r24
            r22 = r24
            r22.<init>()
            java.lang.String r22 = "-->openUrl2 before url ="
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r3
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            com.tencent.open.a.f.a(r20, r21)
            r20 = r3
            java.lang.String r21 = "?"
            int r20 = r20.indexOf(r21)
            r21 = -1
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0197
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r24 = r20
            r20 = r24
            r21 = r24
            r21.<init>()
            r21 = r3
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r21 = "?"
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r20 = r20.toString()
            r3 = r20
        L_0x00e9:
            java.lang.String r20 = "openSDK_LOG.HttpUtils"
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r24 = r21
            r21 = r24
            r22 = r24
            r22.<init>()
            java.lang.String r22 = "-->openUrl2 encodedParam ="
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r12
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = " -- url = "
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r3
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            com.tencent.open.a.f.a(r20, r21)
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r24 = r20
            r20 = r24
            r21 = r24
            r21.<init>()
            r21 = r3
            java.lang.StringBuilder r20 = r20.append(r21)
            r21 = r12
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r20 = r20.toString()
            r3 = r20
            org.apache.http.client.methods.HttpGet r20 = new org.apache.http.client.methods.HttpGet
            r24 = r20
            r20 = r24
            r21 = r24
            r22 = r3
            r21.<init>(r22)
            r10 = r20
            r20 = r10
            java.lang.String r21 = "Accept-Encoding"
            java.lang.String r22 = "gzip"
            r20.addHeader(r21, r22)
        L_0x014a:
            r20 = r9
            r21 = r10
            org.apache.http.HttpResponse r20 = r20.execute(r21)
            r12 = r20
            r20 = r12
            org.apache.http.StatusLine r20 = r20.getStatusLine()
            r13 = r20
            r20 = r13
            int r20 = r20.getStatusCode()
            r14 = r20
            r20 = r14
            r21 = 200(0xc8, float:2.8E-43)
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x036a
            r20 = r12
            java.lang.String r20 = a((org.apache.http.HttpResponse) r20)
            r7 = r20
            com.tencent.open.utils.k$a r20 = new com.tencent.open.utils.k$a
            r24 = r20
            r20 = r24
            r21 = r24
            r22 = r7
            r23 = r11
            r21.<init>(r22, r23)
            r2 = r20
            return r2
        L_0x0188:
            android.os.Bundle r20 = new android.os.Bundle
            r24 = r20
            r20 = r24
            r21 = r24
            r21.<init>()
            r6 = r20
            goto L_0x0055
        L_0x0197:
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r24 = r20
            r20 = r24
            r21 = r24
            r21.<init>()
            r21 = r3
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r21 = "&"
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r20 = r20.toString()
            r3 = r20
            goto L_0x00e9
        L_0x01b6:
            r20 = r4
            java.lang.String r21 = "POST"
            boolean r20 = r20.equals(r21)
            if (r20 == 0) goto L_0x014a
            org.apache.http.client.methods.HttpPost r20 = new org.apache.http.client.methods.HttpPost
            r24 = r20
            r20 = r24
            r21 = r24
            r22 = r3
            r21.<init>(r22)
            r12 = r20
            r20 = r12
            java.lang.String r21 = "Accept-Encoding"
            java.lang.String r22 = "gzip"
            r20.addHeader(r21, r22)
            android.os.Bundle r20 = new android.os.Bundle
            r24 = r20
            r20 = r24
            r21 = r24
            r21.<init>()
            r13 = r20
            r20 = r6
            java.util.Set r20 = r20.keySet()
            java.util.Iterator r20 = r20.iterator()
            r14 = r20
        L_0x01f1:
            r20 = r14
            boolean r20 = r20.hasNext()
            if (r20 == 0) goto L_0x0225
            r20 = r14
            java.lang.Object r20 = r20.next()
            java.lang.String r20 = (java.lang.String) r20
            r15 = r20
            r20 = r6
            r21 = r15
            java.lang.Object r20 = r20.get(r21)
            r16 = r20
            r20 = r16
            r0 = r20
            boolean r0 = r0 instanceof byte[]
            r20 = r0
            if (r20 == 0) goto L_0x0224
            r20 = r13
            r21 = r15
            r22 = r16
            byte[] r22 = (byte[]) r22
            byte[] r22 = (byte[]) r22
            r20.putByteArray(r21, r22)
        L_0x0224:
            goto L_0x01f1
        L_0x0225:
            r20 = r6
            java.lang.String r21 = "method"
            boolean r20 = r20.containsKey(r21)
            if (r20 != 0) goto L_0x0238
            r20 = r6
            java.lang.String r21 = "method"
            r22 = r4
            r20.putString(r21, r22)
        L_0x0238:
            r20 = r12
            java.lang.String r21 = "Content-Type"
            java.lang.String r22 = "multipart/form-data; boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f"
            r20.setHeader(r21, r22)
            r20 = r12
            java.lang.String r21 = "Connection"
            java.lang.String r22 = "Keep-Alive"
            r20.setHeader(r21, r22)
            java.io.ByteArrayOutputStream r20 = new java.io.ByteArrayOutputStream
            r24 = r20
            r20 = r24
            r21 = r24
            r21.<init>()
            r14 = r20
            r20 = r14
            java.lang.String r21 = "--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"
            byte[] r21 = com.tencent.open.utils.k.i(r21)
            r20.write(r21)
            r20 = r14
            r21 = r6
            java.lang.String r22 = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f"
            java.lang.String r21 = encodePostBody(r21, r22)
            byte[] r21 = com.tencent.open.utils.k.i(r21)
            r20.write(r21)
            r20 = r13
            boolean r20 = r20.isEmpty()
            if (r20 != 0) goto L_0x0329
            r20 = r13
            int r20 = r20.size()
            r15 = r20
            r20 = -1
            r16 = r20
            r20 = r14
            java.lang.String r21 = "\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"
            byte[] r21 = com.tencent.open.utils.k.i(r21)
            r20.write(r21)
            r20 = r13
            java.util.Set r20 = r20.keySet()
            java.util.Iterator r20 = r20.iterator()
            r17 = r20
        L_0x029e:
            r20 = r17
            boolean r20 = r20.hasNext()
            if (r20 == 0) goto L_0x0329
            r20 = r17
            java.lang.Object r20 = r20.next()
            java.lang.String r20 = (java.lang.String) r20
            r18 = r20
            int r16 = r16 + 1
            r20 = r14
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r24 = r21
            r21 = r24
            r22 = r24
            r22.<init>()
            java.lang.String r22 = "Content-Disposition: form-data; name=\""
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r18
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = "\"; filename=\""
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r18
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = "\""
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = "\r\n"
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            byte[] r21 = com.tencent.open.utils.k.i(r21)
            r20.write(r21)
            r20 = r14
            java.lang.String r21 = "Content-Type: content/unknown\r\n\r\n"
            byte[] r21 = com.tencent.open.utils.k.i(r21)
            r20.write(r21)
            r20 = r13
            r21 = r18
            byte[] r20 = r20.getByteArray(r21)
            r19 = r20
            r20 = r19
            if (r20 == 0) goto L_0x030e
            r20 = r14
            r21 = r19
            r20.write(r21)
        L_0x030e:
            r20 = r16
            r21 = r15
            r22 = 1
            int r21 = r21 + -1
            r0 = r20
            r1 = r21
            if (r0 >= r1) goto L_0x0327
            r20 = r14
            java.lang.String r21 = "\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"
            byte[] r21 = com.tencent.open.utils.k.i(r21)
            r20.write(r21)
        L_0x0327:
            goto L_0x029e
        L_0x0329:
            r20 = r14
            java.lang.String r21 = "\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f--\r\n"
            byte[] r21 = com.tencent.open.utils.k.i(r21)
            r20.write(r21)
            r20 = r14
            byte[] r20 = r20.toByteArray()
            r15 = r20
            r20 = r11
            r21 = r15
            r0 = r21
            int r0 = r0.length
            r21 = r0
            int r20 = r20 + r21
            r11 = r20
            r20 = r14
            r20.close()
            org.apache.http.entity.ByteArrayEntity r20 = new org.apache.http.entity.ByteArrayEntity
            r24 = r20
            r20 = r24
            r21 = r24
            r22 = r15
            r21.<init>(r22)
            r16 = r20
            r20 = r12
            r21 = r16
            r20.setEntity(r21)
            r20 = r12
            r10 = r20
            goto L_0x014a
        L_0x036a:
            com.tencent.open.utils.HttpUtils$HttpStatusException r20 = new com.tencent.open.utils.HttpUtils$HttpStatusException
            r24 = r20
            r20 = r24
            r21 = r24
            java.lang.StringBuilder r22 = new java.lang.StringBuilder
            r24 = r22
            r22 = r24
            r23 = r24
            r23.<init>()
            java.lang.String r23 = "http status code error:"
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r14
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r22 = r22.toString()
            r21.<init>(r22)
            throw r20
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.HttpUtils.openUrl2(android.content.Context, java.lang.String, java.lang.String, android.os.Bundle):com.tencent.open.utils.k$a");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0186, code lost:
        r22 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0187, code lost:
        r21 = r22;
        r12 = -4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x018e, code lost:
        r22 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x018f, code lost:
        r19 = r22;
        r19.printStackTrace();
        r12 = -7;
        r13 = 0;
        r15 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x01aa, code lost:
        if (r17 < r18) goto L_0x01ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x01ac, code lost:
        r10 = android.os.SystemClock.elapsedRealtime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x01bd, code lost:
        com.tencent.open.b.g.a().a(r8, r10, 0, 0, -7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x01d0, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x01d1, code lost:
        r22 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x01d2, code lost:
        r19 = r22;
        r19.printStackTrace();
        r12 = -8;
        r13 = 0;
        r15 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x01ed, code lost:
        if (r17 < r18) goto L_0x01ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x01ef, code lost:
        r10 = android.os.SystemClock.elapsedRealtime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x01f6, code lost:
        com.tencent.open.b.g.a().a(r8, r10, 0, 0, -8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0209, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x020a, code lost:
        r22 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x020b, code lost:
        r19 = r22;
        r19.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x022b, code lost:
        r12 = java.lang.Integer.parseInt(r19.getMessage().replace(com.tencent.open.utils.HttpUtils.HttpStatusException.ERROR_INFO, ""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x024a, code lost:
        r22 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x024b, code lost:
        r22.printStackTrace();
        r12 = -9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0257, code lost:
        r22 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0258, code lost:
        r19 = r22;
        r19.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0261, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0262, code lost:
        r22 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0263, code lost:
        r19 = r22;
        r19.printStackTrace();
        com.tencent.open.b.g.a().a(r8, r10, 0, 0, -3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0289, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x028a, code lost:
        r22 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x028b, code lost:
        r19 = r22;
        r19.printStackTrace();
        com.tencent.open.b.g.a().a(r8, r10, 0, 0, getErrorCodeFromException(r19));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x02b5, code lost:
        throw r19;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x018e A[ExcHandler: ConnectTimeoutException (r22v72 'e' org.apache.http.conn.ConnectTimeoutException A[CUSTOM_DECLARE]), PHI: r9 
      PHI: (r9v4 org.json.JSONObject) = (r9v1 org.json.JSONObject), (r9v1 org.json.JSONObject), (r9v6 org.json.JSONObject), (r9v6 org.json.JSONObject), (r9v6 org.json.JSONObject), (r9v6 org.json.JSONObject) binds: [B:8:0x0136, B:9:?, B:11:0x0152, B:15:0x015c, B:16:?, B:12:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:8:0x0136] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x01d1 A[ExcHandler: SocketTimeoutException (r22v62 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE]), PHI: r9 
      PHI: (r9v2 org.json.JSONObject) = (r9v1 org.json.JSONObject), (r9v1 org.json.JSONObject), (r9v6 org.json.JSONObject), (r9v6 org.json.JSONObject), (r9v6 org.json.JSONObject), (r9v6 org.json.JSONObject) binds: [B:8:0x0136, B:9:?, B:11:0x0152, B:15:0x015c, B:16:?, B:12:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:8:0x0136] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x020a A[ExcHandler: HttpStatusException (r22v47 'e' com.tencent.open.utils.HttpUtils$HttpStatusException A[CUSTOM_DECLARE]), Splitter:B:8:0x0136] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0257 A[ExcHandler: NetworkUnavailableException (r22v44 'e' com.tencent.open.utils.HttpUtils$NetworkUnavailableException A[CUSTOM_DECLARE]), Splitter:B:8:0x0136] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0262 A[ExcHandler: MalformedURLException (r22v37 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:8:0x0136] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x028a A[ExcHandler: IOException (r22v29 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:8:0x0136] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject request(com.tencent.connect.auth.QQToken r32, android.content.Context r33, java.lang.String r34, android.os.Bundle r35, java.lang.String r36) throws java.io.IOException, org.json.JSONException, com.tencent.open.utils.HttpUtils.NetworkUnavailableException, com.tencent.open.utils.HttpUtils.HttpStatusException {
        /*
            r2 = r32
            r3 = r33
            r4 = r34
            r5 = r35
            r6 = r36
            java.lang.String r22 = "openSDK_LOG.HttpUtils"
            java.lang.String r23 = "OpenApi request"
            com.tencent.open.a.f.a(r22, r23)
            r22 = r4
            r7 = r22
            r22 = r4
            r8 = r22
            r22 = r4
            java.lang.String r22 = r22.toLowerCase()
            java.lang.String r23 = "http"
            boolean r22 = r22.startsWith(r23)
            if (r22 != 0) goto L_0x0075
            java.lang.StringBuilder r22 = new java.lang.StringBuilder
            r31 = r22
            r22 = r31
            r23 = r31
            r23.<init>()
            com.tencent.open.utils.g r23 = com.tencent.open.utils.g.a()
            r24 = r3
            java.lang.String r25 = "https://openmobile.qq.com/"
            java.lang.String r23 = r23.a(r24, r25)
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r4
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r22 = r22.toString()
            r7 = r22
            java.lang.StringBuilder r22 = new java.lang.StringBuilder
            r31 = r22
            r22 = r31
            r23 = r31
            r23.<init>()
            com.tencent.open.utils.g r23 = com.tencent.open.utils.g.a()
            r24 = r3
            java.lang.String r25 = "https://openmobile.qq.com/"
            java.lang.String r23 = r23.a(r24, r25)
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r4
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r22 = r22.toString()
            r8 = r22
        L_0x0075:
            r22 = r3
            r23 = r2
            r24 = r4
            a(r22, r23, r24)
            r22 = 0
            r9 = r22
            long r22 = android.os.SystemClock.elapsedRealtime()
            r10 = r22
            r22 = 0
            r12 = r22
            r22 = 0
            r13 = r22
            r22 = 0
            r15 = r22
            r22 = 0
            r17 = r22
            r22 = r3
            r23 = r2
            java.lang.String r23 = r23.getAppId()
            com.tencent.open.utils.f r22 = com.tencent.open.utils.f.a((android.content.Context) r22, (java.lang.String) r23)
            java.lang.String r23 = "Common_HttpRetryCount"
            int r22 = r22.a((java.lang.String) r23)
            r18 = r22
            java.lang.String r22 = "OpenConfig_test"
            java.lang.StringBuilder r23 = new java.lang.StringBuilder
            r31 = r23
            r23 = r31
            r24 = r31
            r24.<init>()
            java.lang.String r24 = "config 1:Common_HttpRetryCount            config_value:"
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r18
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r24 = "   appid:"
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r2
            java.lang.String r24 = r24.getAppId()
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r24 = "     url:"
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r8
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r23 = r23.toString()
            com.tencent.open.a.f.a(r22, r23)
            r22 = r18
            if (r22 != 0) goto L_0x0182
            r22 = 3
        L_0x00ee:
            r18 = r22
            java.lang.String r22 = "OpenConfig_test"
            java.lang.StringBuilder r23 = new java.lang.StringBuilder
            r31 = r23
            r23 = r31
            r24 = r31
            r24.<init>()
            java.lang.String r24 = "config 1:Common_HttpRetryCount            result_value:"
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r18
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r24 = "   appid:"
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r2
            java.lang.String r24 = r24.getAppId()
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r24 = "     url:"
            java.lang.StringBuilder r23 = r23.append(r24)
            r24 = r8
            java.lang.StringBuilder r23 = r23.append(r24)
            java.lang.String r23 = r23.toString()
            com.tencent.open.a.f.a(r22, r23)
        L_0x012c:
            int r17 = r17 + 1
            r22 = r3
            r23 = r7
            r24 = r6
            r25 = r5
            com.tencent.open.utils.k$a r22 = openUrl2(r22, r23, r24, r25)     // Catch:{ ConnectTimeoutException -> 0x018e, SocketTimeoutException -> 0x01d1, HttpStatusException -> 0x020a, NetworkUnavailableException -> 0x0257, MalformedURLException -> 0x0262, IOException -> 0x028a, JSONException -> 0x02b6 }
            r19 = r22
            r22 = r19
            r0 = r22
            java.lang.String r0 = r0.a     // Catch:{ ConnectTimeoutException -> 0x018e, SocketTimeoutException -> 0x01d1, HttpStatusException -> 0x020a, NetworkUnavailableException -> 0x0257, MalformedURLException -> 0x0262, IOException -> 0x028a, JSONException -> 0x02b6 }
            r22 = r0
            r20 = r22
            r22 = r20
            org.json.JSONObject r22 = com.tencent.open.utils.k.d((java.lang.String) r22)     // Catch:{ ConnectTimeoutException -> 0x018e, SocketTimeoutException -> 0x01d1, HttpStatusException -> 0x020a, NetworkUnavailableException -> 0x0257, MalformedURLException -> 0x0262, IOException -> 0x028a, JSONException -> 0x02b6 }
            r9 = r22
            r22 = r9
            java.lang.String r23 = "ret"
            int r22 = r22.getInt(r23)     // Catch:{ JSONException -> 0x0186, ConnectTimeoutException -> 0x018e, SocketTimeoutException -> 0x01d1, HttpStatusException -> 0x020a, NetworkUnavailableException -> 0x0257, MalformedURLException -> 0x0262, IOException -> 0x028a }
            r12 = r22
        L_0x0158:
            r22 = r19
            r0 = r22
            long r0 = r0.b     // Catch:{ ConnectTimeoutException -> 0x018e, SocketTimeoutException -> 0x01d1, HttpStatusException -> 0x020a, NetworkUnavailableException -> 0x0257, MalformedURLException -> 0x0262, IOException -> 0x028a, JSONException -> 0x02b6 }
            r22 = r0
            r13 = r22
            r22 = r19
            r0 = r22
            long r0 = r0.c     // Catch:{ ConnectTimeoutException -> 0x018e, SocketTimeoutException -> 0x01d1, HttpStatusException -> 0x020a, NetworkUnavailableException -> 0x0257, MalformedURLException -> 0x0262, IOException -> 0x028a, JSONException -> 0x02b6 }
            r22 = r0
            r15 = r22
        L_0x016c:
            com.tencent.open.b.g r22 = com.tencent.open.b.g.a()
            r23 = r8
            r24 = r10
            r26 = r13
            r28 = r15
            r30 = r12
            r22.a(r23, r24, r26, r28, r30)
            r22 = r9
            r2 = r22
            return r2
        L_0x0182:
            r22 = r18
            goto L_0x00ee
        L_0x0186:
            r22 = move-exception
            r21 = r22
            r22 = -4
            r12 = r22
            goto L_0x0158
        L_0x018e:
            r22 = move-exception
            r19 = r22
            r22 = r19
            r22.printStackTrace()
            r22 = -7
            r12 = r22
            r22 = 0
            r13 = r22
            r22 = 0
            r15 = r22
            r22 = r17
            r23 = r18
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x01bd
            long r22 = android.os.SystemClock.elapsedRealtime()
            r10 = r22
        L_0x01b2:
            r22 = r17
            r23 = r18
            r0 = r22
            r1 = r23
            if (r0 < r1) goto L_0x012c
            goto L_0x016c
        L_0x01bd:
            com.tencent.open.b.g r22 = com.tencent.open.b.g.a()
            r23 = r8
            r24 = r10
            r26 = r13
            r28 = r15
            r30 = r12
            r22.a(r23, r24, r26, r28, r30)
            r22 = r19
            throw r22
        L_0x01d1:
            r22 = move-exception
            r19 = r22
            r22 = r19
            r22.printStackTrace()
            r22 = -8
            r12 = r22
            r22 = 0
            r13 = r22
            r22 = 0
            r15 = r22
            r22 = r17
            r23 = r18
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x01f6
            long r22 = android.os.SystemClock.elapsedRealtime()
            r10 = r22
            goto L_0x01b2
        L_0x01f6:
            com.tencent.open.b.g r22 = com.tencent.open.b.g.a()
            r23 = r8
            r24 = r10
            r26 = r13
            r28 = r15
            r30 = r12
            r22.a(r23, r24, r26, r28, r30)
            r22 = r19
            throw r22
        L_0x020a:
            r22 = move-exception
            r19 = r22
            r22 = r19
            r22.printStackTrace()
            r22 = r19
            java.lang.String r22 = r22.getMessage()
            r20 = r22
            r22 = r20
            java.lang.String r23 = "http status code error:"
            java.lang.String r24 = ""
            java.lang.String r22 = r22.replace(r23, r24)     // Catch:{ Exception -> 0x024a }
            r20 = r22
            r22 = r20
            int r22 = java.lang.Integer.parseInt(r22)     // Catch:{ Exception -> 0x024a }
            r12 = r22
        L_0x022e:
            r22 = 0
            r13 = r22
            r22 = 0
            r15 = r22
            com.tencent.open.b.g r22 = com.tencent.open.b.g.a()
            r23 = r8
            r24 = r10
            r26 = r13
            r28 = r15
            r30 = r12
            r22.a(r23, r24, r26, r28, r30)
            r22 = r19
            throw r22
        L_0x024a:
            r22 = move-exception
            r21 = r22
            r22 = r21
            r22.printStackTrace()
            r22 = -9
            r12 = r22
            goto L_0x022e
        L_0x0257:
            r22 = move-exception
            r19 = r22
            r22 = r19
            r22.printStackTrace()
            r22 = r19
            throw r22
        L_0x0262:
            r22 = move-exception
            r19 = r22
            r22 = r19
            r22.printStackTrace()
            r22 = -3
            r12 = r22
            r22 = 0
            r13 = r22
            r22 = 0
            r15 = r22
            com.tencent.open.b.g r22 = com.tencent.open.b.g.a()
            r23 = r8
            r24 = r10
            r26 = r13
            r28 = r15
            r30 = r12
            r22.a(r23, r24, r26, r28, r30)
            r22 = r19
            throw r22
        L_0x028a:
            r22 = move-exception
            r19 = r22
            r22 = r19
            r22.printStackTrace()
            r22 = r19
            int r22 = getErrorCodeFromException(r22)
            r12 = r22
            r22 = 0
            r13 = r22
            r22 = 0
            r15 = r22
            com.tencent.open.b.g r22 = com.tencent.open.b.g.a()
            r23 = r8
            r24 = r10
            r26 = r13
            r28 = r15
            r30 = r12
            r22.a(r23, r24, r26, r28, r30)
            r22 = r19
            throw r22
        L_0x02b6:
            r22 = move-exception
            r19 = r22
            r22 = r19
            r22.printStackTrace()
            r22 = -4
            r12 = r22
            r22 = 0
            r13 = r22
            r22 = 0
            r15 = r22
            com.tencent.open.b.g r22 = com.tencent.open.b.g.a()
            r23 = r8
            r24 = r10
            r26 = r13
            r28 = r15
            r30 = r12
            r22.a(r23, r24, r26, r28, r30)
            r22 = r19
            throw r22
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.HttpUtils.request(com.tencent.connect.auth.QQToken, android.content.Context, java.lang.String, android.os.Bundle, java.lang.String):org.json.JSONObject");
    }

    public static void requestAsync(QQToken qQToken, Context context, String str, Bundle bundle, String str2, IRequestListener iRequestListener) {
        AnonymousClass1 r14;
        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync");
        final QQToken qQToken2 = qQToken;
        final Context context2 = context;
        final String str3 = str;
        final Bundle bundle2 = bundle;
        final String str4 = str2;
        final IRequestListener iRequestListener2 = iRequestListener;
        new Thread() {
            public void run() {
                try {
                    JSONObject request = HttpUtils.request(qQToken2, context2, str3, bundle2, str4);
                    if (iRequestListener2 != null) {
                        iRequestListener2.onComplete(request);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi onComplete");
                    }
                } catch (MalformedURLException e2) {
                    MalformedURLException malformedURLException = e2;
                    if (iRequestListener2 != null) {
                        iRequestListener2.onMalformedURLException(malformedURLException);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync MalformedURLException", malformedURLException);
                    }
                } catch (ConnectTimeoutException e3) {
                    ConnectTimeoutException connectTimeoutException = e3;
                    if (iRequestListener2 != null) {
                        iRequestListener2.onConnectTimeoutException(connectTimeoutException);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onConnectTimeoutException", connectTimeoutException);
                    }
                } catch (SocketTimeoutException e4) {
                    SocketTimeoutException socketTimeoutException = e4;
                    if (iRequestListener2 != null) {
                        iRequestListener2.onSocketTimeoutException(socketTimeoutException);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onSocketTimeoutException", socketTimeoutException);
                    }
                } catch (NetworkUnavailableException e5) {
                    NetworkUnavailableException networkUnavailableException = e5;
                    if (iRequestListener2 != null) {
                        iRequestListener2.onNetworkUnavailableException(networkUnavailableException);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onNetworkUnavailableException", networkUnavailableException);
                    }
                } catch (HttpStatusException e6) {
                    HttpStatusException httpStatusException = e6;
                    if (iRequestListener2 != null) {
                        iRequestListener2.onHttpStatusException(httpStatusException);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onHttpStatusException", httpStatusException);
                    }
                } catch (IOException e7) {
                    IOException iOException = e7;
                    if (iRequestListener2 != null) {
                        iRequestListener2.onIOException(iOException);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync IOException", iOException);
                    }
                } catch (JSONException e8) {
                    JSONException jSONException = e8;
                    if (iRequestListener2 != null) {
                        iRequestListener2.onJSONException(jSONException);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync JSONException", jSONException);
                    }
                } catch (Exception e9) {
                    Exception exc = e9;
                    if (iRequestListener2 != null) {
                        iRequestListener2.onUnknowException(exc);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onUnknowException", exc);
                    }
                }
            }
        };
        r14.start();
    }
}
