package com.androlua;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.DownloadListener;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.AndLua.LY.R;
import com.a.a.a.a.a.a.a;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import net.lingala.zip4j.util.InternalZipConstants;

public class LuaWebView extends WebView implements LuaGcable {
    /* access modifiers changed from: private */
    public DownloadBroadcastReceiver a;
    /* access modifiers changed from: private */
    public HashMap<Long, String[]> b = new HashMap<>();
    /* access modifiers changed from: private */
    public OnDownloadCompleteListener c;
    /* access modifiers changed from: private */
    public LuaActivity d;
    /* access modifiers changed from: private */
    public ProgressBar e;
    private DisplayMetrics f;
    private Dialog g;
    private ListView h;
    /* access modifiers changed from: private */
    public ValueCallback<Uri> i;
    /* access modifiers changed from: private */
    public String j = InternalZipConstants.ZIP_FILE_SEPARATOR;
    /* access modifiers changed from: private */
    public LuaFunction<Boolean> k;
    private boolean l;
    /* access modifiers changed from: private */
    public OnReceivedTitleListener m;
    /* access modifiers changed from: private */
    public OnReceivedIconListener n;

    private class Download implements DownloadListener {
        EditText a;
        private String c;
        private String d;
        private String e;
        private String f;
        private long g;
        /* access modifiers changed from: private */
        public String h;

        private Download() {
        }

        /* access modifiers changed from: private */
        public long a(boolean z) {
            if (LuaWebView.this.a == null) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
                DownloadBroadcastReceiver unused = LuaWebView.this.a = new DownloadBroadcastReceiver();
                LuaWebView.this.d.registerReceiver(LuaWebView.this.a, intentFilter);
            }
            DownloadManager downloadManager = (DownloadManager) LuaWebView.this.d.getSystemService("download");
            Uri parse = Uri.parse(this.c);
            parse.getLastPathSegment();
            DownloadManager.Request request = new DownloadManager.Request(parse);
            String luaExtDir = LuaWebView.this.d.getLuaExtDir("Download");
            request.setDestinationInExternalPublicDir(new File(LuaWebView.this.d.getLuaExtDir()).getName() + InternalZipConstants.ZIP_FILE_SEPARATOR + "Download", this.h);
            request.setTitle(this.h);
            request.setDescription(this.c);
            if (z) {
                request.setAllowedNetworkTypes(2);
            }
            File file = new File(luaExtDir, this.h);
            if (file.exists()) {
                file.delete();
            }
            request.setMimeType(this.f);
            long enqueue = downloadManager.enqueue(request);
            LuaWebView.this.b.put(Long.valueOf(enqueue), new String[]{new File(luaExtDir, this.h).getAbsolutePath(), this.f});
            return enqueue;
        }

        @SuppressLint({"DefaultLocale"})
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            String str5;
            Object[] objArr;
            int indexOf;
            int length;
            int indexOf2;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
            this.g = j;
            this.h = Uri.parse(this.c).getLastPathSegment();
            if (!(str3 == null || (indexOf = str3.indexOf("filename=\"")) == -1 || (indexOf2 = str3.indexOf(34, length)) <= (length = indexOf + "filename=\"".length()))) {
                this.h = str3.substring(length, indexOf2);
            }
            this.a = new EditText(LuaWebView.this.d);
            this.a.setText(this.h);
            String str6 = String.valueOf(j) + "B";
            if (j > 1048576) {
                str5 = "%.2f MB";
                objArr = new Object[]{Double.valueOf(Long.valueOf(j).doubleValue() / 1048576.0d)};
            } else {
                if (j > PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                    str5 = "%.2f KB";
                    objArr = new Object[]{Double.valueOf(Long.valueOf(j).doubleValue() / 1024.0d)};
                }
                new AlertDialog.Builder(LuaWebView.this.d).setTitle("Download").setMessage("Type: " + str4 + "\nSize: " + str6).setView(this.a).setPositiveButton("Download", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String unused = Download.this.h = Download.this.a.getText().toString();
                        long unused2 = Download.this.a(false);
                    }
                }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setNeutralButton("Only Wifi", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String unused = Download.this.h = Download.this.a.getText().toString();
                        long unused2 = Download.this.a(true);
                    }
                }).create().show();
            }
            str6 = String.format(str5, objArr);
            new AlertDialog.Builder(LuaWebView.this.d).setTitle("Download").setMessage("Type: " + str4 + "\nSize: " + str6).setView(this.a).setPositiveButton("Download", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String unused = Download.this.h = Download.this.a.getText().toString();
                    long unused2 = Download.this.a(false);
                }
            }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setNeutralButton("Only Wifi", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String unused = Download.this.h = Download.this.a.getText().toString();
                    long unused2 = Download.this.a(true);
                }
            }).create().show();
        }
    }

    private class DownloadBroadcastReceiver extends BroadcastReceiver {
        private DownloadBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            long longExtra = intent.getLongExtra("extra_download_id", 0);
            intent.getExtras();
            if (LuaWebView.this.b.containsKey(Long.valueOf(longExtra)) && LuaWebView.this.c != null) {
                String[] strArr = (String[]) LuaWebView.this.b.get(Long.valueOf(longExtra));
                LuaWebView.this.c.onDownloadComplete(strArr[0], strArr[1]);
            }
        }
    }

    public interface JsInterface {
        @JavascriptInterface
        String execute(String str);
    }

    class JsObject {
        private JsInterface b;

        public JsObject(JsInterface jsInterface) {
            this.b = jsInterface;
        }

        @JavascriptInterface
        public String execute(String str) {
            return this.b.execute(str);
        }
    }

    private class LuaJavaScriptInterface {
        private LuaActivity b;

        public LuaJavaScriptInterface(LuaActivity luaActivity) {
            this.b = luaActivity;
        }

        @JavascriptInterface
        public Object callLuaFunction(String str) {
            return this.b.runFunc(str, new Object[0]);
        }

        @JavascriptInterface
        public Object callLuaFunction(String str, String str2) {
            return this.b.runFunc(str, str2);
        }

        @JavascriptInterface
        public Object doLuaString(String str) {
            return this.b.doString(str, new Object[0]);
        }
    }

    class LuaWebChromeClient extends WebChromeClient {
        EditText a = new EditText(LuaWebView.this.d);

        LuaWebChromeClient() {
        }

        public Bitmap getDefaultVideoPoster() {
            return BitmapFactory.decodeResource(LuaWebView.this.d.getResources(), R.drawable.icon);
        }

        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            new AlertDialog.Builder(LuaWebView.this.d).setTitle(str).setMessage(str2).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            }).setCancelable(false).create().show();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LuaWebView.this.d);
            builder.setTitle(str);
            builder.setMessage(str2);
            builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            });
            builder.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.cancel();
                }
            });
            builder.setCancelable(false);
            builder.create();
            builder.show();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
            this.a.setText(str3);
            AlertDialog.Builder builder = new AlertDialog.Builder(LuaWebView.this.d);
            builder.setTitle(str);
            builder.setMessage(str2);
            builder.setView(this.a);
            builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsPromptResult.confirm(LuaWebChromeClient.this.a.getText().toString());
                }
            });
            builder.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsPromptResult.cancel();
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    jsPromptResult.cancel();
                }
            });
            builder.show();
            return true;
        }

        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                LuaWebView.this.e.setVisibility(8);
            } else {
                LuaWebView.this.e.setVisibility(0);
                LuaWebView.this.e.setProgress(i);
            }
            super.onProgressChanged(webView, i);
        }

        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            super.onReceivedIcon(webView, bitmap);
            if (LuaWebView.this.n != null) {
                LuaWebView.this.n.onReceivedIcon(bitmap);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (LuaWebView.this.m != null) {
                LuaWebView.this.m.onReceivedTitle(str);
            }
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback) {
            openFileChooser(valueCallback, "");
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
            if (LuaWebView.this.i == null) {
                ValueCallback unused = LuaWebView.this.i = valueCallback;
                LuaWebView.this.openFile(LuaWebView.this.j);
            }
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            openFileChooser(valueCallback, str);
        }
    }

    public interface LuaWebViewClient {
        public static final int ERROR_AUTHENTICATION = -4;
        public static final int ERROR_BAD_URL = -12;
        public static final int ERROR_CONNECT = -6;
        public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
        public static final int ERROR_FILE = -13;
        public static final int ERROR_FILE_NOT_FOUND = -14;
        public static final int ERROR_HOST_LOOKUP = -2;
        public static final int ERROR_IO = -7;
        public static final int ERROR_PROXY_AUTHENTICATION = -5;
        public static final int ERROR_REDIRECT_LOOP = -9;
        public static final int ERROR_TIMEOUT = -8;
        public static final int ERROR_TOO_MANY_REQUESTS = -15;
        public static final int ERROR_UNKNOWN = -1;
        public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
        public static final int ERROR_UNSUPPORTED_SCHEME = -10;

        void doUpdateVisitedHistory(WebView webView, String str, boolean z);

        void onFormResubmission(WebView webView, Message message, Message message2);

        void onLoadResource(WebView webView, String str);

        void onPageFinished(WebView webView, String str);

        void onPageStarted(WebView webView, String str, Bitmap bitmap);

        void onProceededAfterSslError(WebView webView, SslError sslError);

        void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest, String str);

        void onReceivedError(WebView webView, int i, String str, String str2);

        void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2);

        void onReceivedLoginRequest(WebView webView, String str, String str2, String str3);

        void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError);

        void onScaleChanged(WebView webView, float f, float f2);

        @Deprecated
        void onTooManyRedirects(WebView webView, Message message, Message message2);

        void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent);

        WebResourceResponse shouldInterceptRequest(WebView webView, String str);

        boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent);

        boolean shouldOverrideUrlLoading(WebView webView, String str);
    }

    public interface OnDownloadCompleteListener {
        void onDownloadComplete(String str, String str2);
    }

    public interface OnDownloadStartListener {
        void onDownloadStart(String str, String str2, String str3, String str4, long j);
    }

    public interface OnReceivedIconListener {
        void onReceivedIcon(Bitmap bitmap);
    }

    public interface OnReceivedTitleListener {
        void onReceivedTitle(String str);
    }

    private class SimpleLuaWebViewClient extends WebViewClient {
        private LuaWebViewClient b;

        public SimpleLuaWebViewClient(LuaWebViewClient luaWebViewClient) {
            this.b = luaWebViewClient;
        }

        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            this.b.doUpdateVisitedHistory(webView, str, z);
        }

        public void onFormResubmission(WebView webView, Message message, Message message2) {
            message.sendToTarget();
        }

        public void onLoadResource(WebView webView, String str) {
            this.b.onLoadResource(webView, str);
        }

        public void onPageFinished(WebView webView, String str) {
            this.b.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.b.onPageStarted(webView, str, bitmap);
        }

        public void onProceededAfterSslError(WebView webView, SslError sslError) {
            this.b.onProceededAfterSslError(webView, sslError);
        }

        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest, String str) {
            this.b.onReceivedClientCertRequest(webView, clientCertRequest, str);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            this.b.onReceivedError(webView, i, str, str2);
        }

        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            this.b.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            this.b.onReceivedLoginRequest(webView, str, str2, str3);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            this.b.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        public void onScaleChanged(WebView webView, float f, float f2) {
            this.b.onScaleChanged(webView, f, f2);
        }

        @Deprecated
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            message.sendToTarget();
        }

        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            this.b.onUnhandledKeyEvent(webView, keyEvent);
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (LuaWebView.this.k != null) {
                try {
                    if (((Boolean) LuaWebView.this.k.call(str)).booleanValue()) {
                        return new WebResourceResponse((String) null, (String) null, (InputStream) null);
                    }
                } catch (LuaException e) {
                    a.a(e);
                }
            }
            return this.b.shouldInterceptRequest(webView, str);
        }

        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            return this.b.shouldOverrideKeyEvent(webView, keyEvent);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return this.b.shouldOverrideUrlLoading(webView, str);
        }
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    public LuaWebView(LuaActivity luaActivity) {
        super(luaActivity);
        luaActivity.regGc(this);
        this.d = luaActivity;
        getSettings().setJavaScriptEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setDisplayZoomControls(true);
        getSettings().setSupportZoom(true);
        getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            getSettings().setMixedContentMode(0);
        }
        addJavascriptInterface(new LuaJavaScriptInterface(luaActivity), "androlua");
        setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LuaWebView.this.d);
                builder.setTitle("SslError");
                builder.setMessage(sslError.toString());
                builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sslErrorHandler.proceed();
                    }
                });
                builder.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sslErrorHandler.cancel();
                    }
                });
                builder.setCancelable(false);
                builder.create();
                builder.show();
            }

            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                if (LuaWebView.this.k != null) {
                    try {
                        Boolean bool = (Boolean) LuaWebView.this.k.call(str);
                        if (bool != null && bool.booleanValue()) {
                            return new WebResourceResponse((String) null, (String) null, (InputStream) null);
                        }
                    } catch (LuaException e) {
                        a.a(e);
                    }
                }
                return null;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (LuaWebView.this.k != null) {
                    try {
                        Boolean bool = (Boolean) LuaWebView.this.k.call(str);
                        if (bool != null && bool.booleanValue()) {
                            return true;
                        }
                    } catch (LuaException e) {
                        a.a(e);
                    }
                }
                if (str.startsWith("http") || str.startsWith("file")) {
                    webView.loadUrl(str);
                    return true;
                }
                try {
                    LuaWebView.this.d.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(str)), 0);
                    return true;
                } catch (Exception e2) {
                    LuaWebView.this.d.sendError("LuaWebView", e2);
                    return true;
                }
            }
        });
        this.f = luaActivity.getResources().getDisplayMetrics();
        this.e = new ProgressBar(luaActivity, (AttributeSet) null, 16842872);
        this.e.setLayoutParams(new AbsoluteLayout.LayoutParams(-1, (int) TypedValue.applyDimension(1, 2.0f, this.f), 0, 0));
        addView(this.e);
        setWebChromeClient(new LuaWebChromeClient());
        setDownloadListener(new Download());
    }

    @SuppressLint({"AddJavascriptInterface"})
    public void addJSInterface(JsInterface jsInterface, String str) {
        super.addJavascriptInterface(new JsObject(jsInterface), str);
    }

    @SuppressLint({"AddJavascriptInterface"})
    public void addJsInterface(JsInterface jsInterface, String str) {
        super.addJavascriptInterface(new JsObject(jsInterface), str);
    }

    public void destroy() {
        if (this.a != null) {
            this.d.unregisterReceiver(this.a);
        }
        super.destroy();
    }

    public void gc() {
        destroy();
        this.l = true;
    }

    public boolean isGc() {
        return this.l;
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || !canGoBack()) {
            return super.onKeyDown(i2, keyEvent);
        }
        goBack();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) this.e.getLayoutParams();
        layoutParams.x = i2;
        layoutParams.y = i3;
        this.e.setLayoutParams(layoutParams);
        super.onScrollChanged(i2, i3, i4, i5);
    }

    public void openFile(String str) {
        if (this.g == null) {
            this.g = new Dialog(getContext());
            this.h = new ListView(getContext());
            this.h.setFastScrollEnabled(true);
            this.h.setFastScrollAlwaysVisible(true);
            this.g.setContentView(this.h);
            this.h.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    String charSequence = ((TextView) view).getText().toString();
                    if (charSequence.equals("../")) {
                        String unused = LuaWebView.this.j = new File(LuaWebView.this.j).getParent() + InternalZipConstants.ZIP_FILE_SEPARATOR;
                        LuaWebView.this.openFile(LuaWebView.this.j);
                        return;
                    }
                    String str = LuaWebView.this.j + charSequence;
                    if (new File(str).isDirectory()) {
                        String unused2 = LuaWebView.this.j = str;
                        LuaWebView.this.openFile(LuaWebView.this.j);
                        return;
                    }
                    LuaWebView.this.i.onReceiveValue(Uri.parse(str));
                }
            });
        }
        File file = new File(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add("../");
        String[] list = file.list();
        if (list != null) {
            Arrays.sort(list);
            for (String str2 : list) {
                if (new File(this.j + str2).isDirectory()) {
                    arrayList.add(str2 + InternalZipConstants.ZIP_FILE_SEPARATOR);
                }
            }
            for (String str3 : list) {
                if (new File(this.j + str3).isFile()) {
                    arrayList.add(str3);
                }
            }
        }
        this.h.setAdapter(new ArrayAdapter(getContext(), 17367043, arrayList));
        this.g.setTitle(this.j);
        this.g.show();
    }

    public void setAdsFilter(LuaFunction<Boolean> luaFunction) {
        this.k = luaFunction;
    }

    public void setDownloadListener(DownloadListener downloadListener) {
        super.setDownloadListener(downloadListener);
    }

    public void setOnDownloadCompleteListener(OnDownloadCompleteListener onDownloadCompleteListener) {
        this.c = onDownloadCompleteListener;
    }

    public void setOnDownloadStartListener(final OnDownloadStartListener onDownloadStartListener) {
        setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                onDownloadStartListener.onDownloadStart(str, str2, str3, str4, j);
            }
        });
    }

    public void setOnKeyListener(View.OnKeyListener onKeyListener) {
        super.setOnKeyListener(onKeyListener);
    }

    public void setOnReceivedIconListener(OnReceivedIconListener onReceivedIconListener) {
        this.n = onReceivedIconListener;
    }

    public void setOnReceivedTitleListener(OnReceivedTitleListener onReceivedTitleListener) {
        this.m = onReceivedTitleListener;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.e = progressBar;
    }

    public void setProgressBarEnabled(boolean z) {
        ProgressBar progressBar;
        int i2;
        if (z) {
            progressBar = this.e;
            i2 = 0;
        } else {
            progressBar = this.e;
            i2 = 8;
        }
        progressBar.setVisibility(i2);
    }

    public void setWebViewClient(LuaWebViewClient luaWebViewClient) {
        super.setWebViewClient(new SimpleLuaWebViewClient(luaWebViewClient));
    }
}
