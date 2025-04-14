package com.tencent.open.utils;

import android.annotation.TargetApi;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.os.Build;
import com.tencent.open.a.f;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.params.HttpParams;

@TargetApi(17)
/* compiled from: ProGuard */
public class j implements LayeredSocketFactory {
    static final HostnameVerifier a;
    SSLCertificateSocketFactory b = ((SSLCertificateSocketFactory) SSLCertificateSocketFactory.getInsecure(0, (SSLSessionCache) null));

    static {
        HostnameVerifier hostnameVerifier;
        new StrictHostnameVerifier();
        a = hostnameVerifier;
    }

    public j() {
    }

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) throws IOException {
        SocketAddress socketAddress;
        Socket socket2 = socket;
        InetAddress inetAddress2 = inetAddress;
        int i3 = i2;
        HttpParams httpParams2 = httpParams;
        new InetSocketAddress(str, i);
        socket2.connect(socketAddress);
        return socket2;
    }

    public Socket createSocket() {
        Socket socket;
        new Socket();
        return socket;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Socket socket2 = socket;
        String str2 = str;
        int i2 = i;
        boolean z2 = z;
        new StringBuilder();
        f.a("SNISocketFactory", sb.append("createSocket ").append(socket2.toString()).append(" host:").append(str2).append(" port:").append(i2).append(" autoClose:").append(z2).toString());
        SSLSocket sSLSocket = (SSLSocket) this.b.createSocket(socket2, str2, i2, z2);
        sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        if (Build.VERSION.SDK_INT >= 17) {
            f.a("SNISocketFactory", "Setting SNI hostname");
            this.b.setHostname(sSLSocket, str2);
        } else {
            f.a("SNISocketFactory", "No documented SNI support on Android <4.2, trying with reflection");
            try {
                Object[] objArr = {str2};
                Object invoke = sSLSocket.getClass().getMethod("setHostname", new Class[]{String.class}).invoke(sSLSocket, objArr);
            } catch (Exception e) {
                Exception exc = e;
                f.a("SNISocketFactory", "SNI not useable");
            }
        }
        if (a.verify(str2, sSLSocket.getSession())) {
            return sSLSocket;
        }
        Throwable th2 = th;
        new StringBuilder();
        new SSLPeerUnverifiedException(sb2.append("Cannot verify hostname: ").append(str2).toString());
        throw th2;
    }

    public boolean isSecure(Socket socket) throws IllegalArgumentException {
        Socket socket2 = socket;
        if (socket2 instanceof SSLSocket) {
            return ((SSLSocket) socket2).isConnected();
        }
        return false;
    }
}
