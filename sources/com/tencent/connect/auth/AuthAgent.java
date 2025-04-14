package com.tencent.connect.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.e;
import com.tencent.open.utils.g;
import com.tencent.open.utils.h;
import com.tencent.open.utils.i;
import com.tencent.open.utils.k;
import com.tencent.open.web.security.JniInterface;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class AuthAgent extends BaseApi {
    public static final String SECURE_LIB_ARM64_FILE_NAME = "libwbsafeedit_64";
    public static final String SECURE_LIB_ARM_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_FILE_NAME = null;
    public static String SECURE_LIB_NAME = null;
    public static final String SECURE_LIB_X86_64_FILE_NAME = "libwbsafeedit_x86_64";
    public static final String SECURE_LIB_X86_FILE_NAME = "libwbsafeedit_x86";
    private IUiListener c;
    private String d;
    /* access modifiers changed from: private */
    public WeakReference<Activity> e;

    /* compiled from: ProGuard */
    private class a implements IUiListener {
        IUiListener a;
        final /* synthetic */ AuthAgent b;

        public a(AuthAgent authAgent, IUiListener iUiListener) {
            this.b = authAgent;
            this.a = iUiListener;
        }

        public void onCancel() {
            if (this.a != null) {
                this.a.onCancel();
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject;
            Object obj2 = obj;
            if (obj2 == null) {
                f.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data is null");
                return;
            }
            JSONObject jSONObject2 = (JSONObject) obj2;
            try {
                int i = jSONObject2.getInt("ret");
                String string = i == 0 ? "success" : jSONObject2.getString("msg");
                if (this.a != null) {
                    IUiListener iUiListener = this.a;
                    new JSONObject();
                    iUiListener.onComplete(jSONObject.put("ret", i).put("msg", string));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                f.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data format error");
            }
        }

        public void onError(UiError uiError) {
            UiError uiError2 = uiError;
            if (this.a != null) {
                this.a.onError(uiError2);
            }
        }
    }

    /* compiled from: ProGuard */
    private class b implements IUiListener {
        IUiListener a;
        final /* synthetic */ AuthAgent b;
        private final String c = "sendinstall";
        private final String d = "installwording";
        private final String e = "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";

        /* compiled from: ProGuard */
        private abstract class a implements View.OnClickListener {
            Dialog d;
            final /* synthetic */ b e;

            a(b bVar, Dialog dialog) {
                this.e = bVar;
                this.d = dialog;
            }
        }

        public b(AuthAgent authAgent, IUiListener iUiListener) {
            this.b = authAgent;
            this.a = iUiListener;
        }

        private Drawable a(String str, Context context) {
            Drawable drawable;
            Rect rect;
            String str2 = str;
            Drawable drawable2 = null;
            try {
                InputStream open = context.getApplicationContext().getAssets().open(str2);
                if (open == null) {
                    return null;
                }
                if (str2.endsWith(".9.png")) {
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(open);
                    } catch (OutOfMemoryError e2) {
                        e2.printStackTrace();
                    }
                    if (bitmap == null) {
                        return null;
                    }
                    byte[] ninePatchChunk = bitmap.getNinePatchChunk();
                    boolean isNinePatchChunk = NinePatch.isNinePatchChunk(ninePatchChunk);
                    new Rect();
                    new NinePatchDrawable(bitmap, ninePatchChunk, rect, (String) null);
                    drawable2 = drawable;
                    return drawable2;
                }
                drawable2 = Drawable.createFromStream(open, str2);
                open.close();
                return drawable2;
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }

        private View a(Context context, Drawable drawable, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
            DisplayMetrics displayMetrics;
            RelativeLayout relativeLayout;
            ImageView imageView;
            RelativeLayout.LayoutParams layoutParams;
            TextView textView;
            RelativeLayout.LayoutParams layoutParams2;
            View view;
            RelativeLayout.LayoutParams layoutParams3;
            LinearLayout linearLayout;
            RelativeLayout.LayoutParams layoutParams4;
            Button button;
            LinearLayout.LayoutParams layoutParams5;
            Button button2;
            LinearLayout.LayoutParams layoutParams6;
            ViewGroup.LayoutParams layoutParams7;
            PaintDrawable paintDrawable;
            Context context2 = context;
            new DisplayMetrics();
            DisplayMetrics displayMetrics2 = displayMetrics;
            ((WindowManager) context2.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics2);
            float f = displayMetrics2.density;
            new RelativeLayout(context2);
            RelativeLayout relativeLayout2 = relativeLayout;
            new ImageView(context2);
            ImageView imageView2 = imageView;
            imageView2.setImageDrawable(drawable);
            imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView2.setId(1);
            int i = (int) (14.0f * f);
            new RelativeLayout.LayoutParams((int) (60.0f * f), (int) (60.0f * f));
            RelativeLayout.LayoutParams layoutParams8 = layoutParams;
            layoutParams8.addRule(9);
            layoutParams8.setMargins(0, (int) (18.0f * f), (int) (6.0f * f), (int) (18.0f * f));
            relativeLayout2.addView(imageView2, layoutParams8);
            new TextView(context2);
            TextView textView2 = textView;
            textView2.setText(str);
            textView2.setTextSize(14.0f);
            textView2.setGravity(3);
            textView2.setIncludeFontPadding(false);
            textView2.setPadding(0, 0, 0, 0);
            textView2.setLines(2);
            textView2.setId(5);
            textView2.setMinWidth((int) (185.0f * f));
            new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams9 = layoutParams2;
            layoutParams9.addRule(1, 1);
            layoutParams9.addRule(6, 1);
            int i2 = (int) (10.0f * f);
            layoutParams9.setMargins(0, 0, (int) (5.0f * f), 0);
            relativeLayout2.addView(textView2, layoutParams9);
            new View(context2);
            View view2 = view;
            view2.setBackgroundColor(Color.rgb(214, 214, 214));
            view2.setId(3);
            new RelativeLayout.LayoutParams(-2, 2);
            RelativeLayout.LayoutParams layoutParams10 = layoutParams3;
            layoutParams10.addRule(3, 1);
            layoutParams10.addRule(5, 1);
            layoutParams10.addRule(7, 5);
            layoutParams10.setMargins(0, 0, 0, (int) (12.0f * f));
            relativeLayout2.addView(view2, layoutParams10);
            new LinearLayout(context2);
            LinearLayout linearLayout2 = linearLayout;
            new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams11 = layoutParams4;
            layoutParams11.addRule(5, 1);
            layoutParams11.addRule(7, 5);
            layoutParams11.addRule(3, 3);
            new Button(context2);
            Button button3 = button;
            button3.setText("跳过");
            button3.setBackgroundDrawable(a("buttonNegt.png", context2));
            button3.setTextColor(Color.rgb(36, 97, 131));
            button3.setTextSize(20.0f);
            button3.setOnClickListener(onClickListener2);
            button3.setId(4);
            new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            LinearLayout.LayoutParams layoutParams12 = layoutParams5;
            layoutParams12.rightMargin = (int) (14.0f * f);
            layoutParams12.leftMargin = (int) (4.0f * f);
            layoutParams12.weight = 1.0f;
            linearLayout2.addView(button3, layoutParams12);
            new Button(context2);
            Button button4 = button2;
            button4.setText("确定");
            button4.setTextSize(20.0f);
            button4.setTextColor(Color.rgb(255, 255, 255));
            button4.setBackgroundDrawable(a("buttonPost.png", context2));
            button4.setOnClickListener(onClickListener);
            new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            LinearLayout.LayoutParams layoutParams13 = layoutParams6;
            layoutParams13.weight = 1.0f;
            layoutParams13.rightMargin = (int) (4.0f * f);
            linearLayout2.addView(button4, layoutParams13);
            relativeLayout2.addView(linearLayout2, layoutParams11);
            new FrameLayout.LayoutParams((int) (279.0f * f), (int) (163.0f * f));
            relativeLayout2.setPadding((int) (14.0f * f), 0, (int) (12.0f * f), (int) (12.0f * f));
            relativeLayout2.setLayoutParams(layoutParams7);
            relativeLayout2.setBackgroundColor(Color.rgb(247, 251, 247));
            new PaintDrawable(Color.rgb(247, 251, 247));
            PaintDrawable paintDrawable2 = paintDrawable;
            paintDrawable2.setCornerRadius(5.0f * f);
            relativeLayout2.setBackgroundDrawable(paintDrawable2);
            return relativeLayout2;
        }

        private void a(String str, IUiListener iUiListener, Object obj) {
            Activity activity;
            Dialog dialog;
            View.OnClickListener onClickListener;
            View.OnClickListener onClickListener2;
            ColorDrawable colorDrawable;
            DialogInterface.OnCancelListener onCancelListener;
            String str2 = str;
            IUiListener iUiListener2 = iUiListener;
            Object obj2 = obj;
            if (this.b.e != null && (activity = (Activity) this.b.e.get()) != null) {
                new Dialog(activity);
                Dialog dialog2 = dialog;
                boolean requestWindowFeature = dialog2.requestWindowFeature(1);
                PackageManager packageManager = activity.getPackageManager();
                PackageInfo packageInfo = null;
                Drawable drawable = null;
                try {
                    packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e2) {
                    e2.printStackTrace();
                }
                if (packageInfo != null) {
                    drawable = packageInfo.applicationInfo.loadIcon(packageManager);
                }
                final IUiListener iUiListener3 = iUiListener2;
                final Object obj3 = obj2;
                new a(this, dialog2) {
                    final /* synthetic */ b c;

                    {
                        b bVar = r9;
                        this.c = bVar;
                    }

                    public void onClick(View view) {
                        View view2 = view;
                        this.c.a();
                        if (this.d != null && this.d.isShowing()) {
                            this.d.dismiss();
                        }
                        if (iUiListener3 != null) {
                            iUiListener3.onComplete(obj3);
                        }
                    }
                };
                final IUiListener iUiListener4 = iUiListener2;
                final Object obj4 = obj2;
                new a(this, dialog2) {
                    final /* synthetic */ b c;

                    {
                        b bVar = r9;
                        this.c = bVar;
                    }

                    public void onClick(View view) {
                        View view2 = view;
                        if (this.d != null && this.d.isShowing()) {
                            this.d.dismiss();
                        }
                        if (iUiListener4 != null) {
                            iUiListener4.onComplete(obj4);
                        }
                    }
                };
                new ColorDrawable();
                ColorDrawable colorDrawable2 = colorDrawable;
                colorDrawable2.setAlpha(0);
                dialog2.getWindow().setBackgroundDrawable(colorDrawable2);
                dialog2.setContentView(a(activity, drawable, str2, onClickListener, onClickListener2));
                final IUiListener iUiListener5 = iUiListener2;
                final Object obj5 = obj2;
                new DialogInterface.OnCancelListener(this) {
                    final /* synthetic */ b c;

                    {
                        this.c = r7;
                    }

                    public void onCancel(DialogInterface dialogInterface) {
                        DialogInterface dialogInterface2 = dialogInterface;
                        if (iUiListener5 != null) {
                            iUiListener5.onComplete(obj5);
                        }
                    }
                };
                dialog2.setOnCancelListener(onCancelListener);
                if (activity != null && !activity.isFinishing()) {
                    dialog2.show();
                }
            }
        }

        /* access modifiers changed from: protected */
        public void a() {
            Activity activity;
            Bundle j = this.b.b();
            if (this.b.e != null && (activity = (Activity) this.b.e.get()) != null) {
                HttpUtils.requestAsync(this.b.b, activity, "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi", j, Constants.HTTP_POST, (IRequestListener) null);
            }
        }

        public void onCancel() {
            if (this.a != null) {
                this.a.onCancel();
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject;
            StringBuilder sb;
            Object obj2 = obj;
            if (obj2 != null && (jSONObject = (JSONObject) obj2) != null) {
                boolean z = false;
                String str = "";
                try {
                    z = jSONObject.getInt("sendinstall") == 1;
                    str = jSONObject.getString("installwording");
                } catch (JSONException e2) {
                    JSONException jSONException = e2;
                    f.d("openSDK_LOG.AuthAgent", "FeedConfirmListener onComplete There is no value for sendinstall.");
                }
                String decode = URLDecoder.decode(str);
                new StringBuilder();
                f.a("openSDK_LOG.AuthAgent", sb.append(" WORDING = ").append(decode).append("xx").toString());
                if (z && !TextUtils.isEmpty(decode)) {
                    a(decode, this.a, obj2);
                } else if (this.a != null) {
                    if (this.b.b != null) {
                        this.b.b.saveSession(jSONObject);
                    }
                    this.a.onComplete(obj2);
                }
            }
        }

        public void onError(UiError uiError) {
            UiError uiError2 = uiError;
            if (this.a != null) {
                this.a.onError(uiError2);
            }
        }
    }

    /* compiled from: ProGuard */
    private class c implements IUiListener {
        final /* synthetic */ AuthAgent a;
        private final IUiListener b;
        private final boolean c;
        private final Context d;

        public c(AuthAgent authAgent, Context context, IUiListener iUiListener, boolean z, boolean z2) {
            boolean z3 = z2;
            this.a = authAgent;
            this.d = context;
            this.b = iUiListener;
            this.c = z;
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener()");
        }

        public void onCancel() {
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onCancel");
            this.b.onCancel();
            f.b();
        }

        public void onComplete(Object obj) {
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete");
            JSONObject jSONObject = (JSONObject) obj;
            try {
                String string = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
                String string2 = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = jSONObject.getString("openid");
                if (!(string == null || this.a.b == null || string3 == null)) {
                    this.a.b.setAccessToken(string, string2);
                    this.a.b.setOpenId(string3);
                    com.tencent.connect.a.a.d(this.d, this.a.b);
                }
                String string4 = jSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (string4 != null) {
                    try {
                        boolean commit = this.d.getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, string4).commit();
                    } catch (Exception e) {
                        Exception exc = e;
                        exc.printStackTrace();
                        f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", exc);
                    }
                }
                if (this.c) {
                    CookieSyncManager.getInstance().sync();
                }
            } catch (JSONException e2) {
                JSONException jSONException = e2;
                jSONException.printStackTrace();
                f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", jSONException);
            }
            this.b.onComplete(jSONObject);
            this.a.releaseResource();
            f.b();
        }

        public void onError(UiError uiError) {
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onError");
            this.b.onError(uiError);
            f.b();
        }
    }

    static {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
        new StringBuilder();
        SECURE_LIB_NAME = sb.append(SECURE_LIB_FILE_NAME).append(".so").toString();
        String str = Build.CPU_ABI;
        if (str == null || str.equals("")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
            new StringBuilder();
            SECURE_LIB_NAME = sb2.append(SECURE_LIB_FILE_NAME).append(".so").toString();
            f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
        } else if (str.equalsIgnoreCase("arm64-v8a")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM64_FILE_NAME;
            new StringBuilder();
            SECURE_LIB_NAME = sb6.append(SECURE_LIB_FILE_NAME).append(".so").toString();
            f.c("openSDK_LOG.AuthAgent", "is arm64-v8a architecture");
        } else if (str.equalsIgnoreCase("x86")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_X86_FILE_NAME;
            new StringBuilder();
            SECURE_LIB_NAME = sb5.append(SECURE_LIB_FILE_NAME).append(".so").toString();
            f.c("openSDK_LOG.AuthAgent", "is x86 architecture");
        } else if (str.equalsIgnoreCase("x86_64")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_X86_64_FILE_NAME;
            new StringBuilder();
            SECURE_LIB_NAME = sb4.append(SECURE_LIB_FILE_NAME).append(".so").toString();
            f.c("openSDK_LOG.AuthAgent", "is x86_64 architecture");
        } else {
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
            new StringBuilder();
            SECURE_LIB_NAME = sb3.append(SECURE_LIB_FILE_NAME).append(".so").toString();
            f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AuthAgent(QQToken qQToken) {
        super(qQToken);
    }

    private int a(boolean z, IUiListener iUiListener, boolean z2) {
        StringBuilder sb;
        StringBuilder sb2;
        IUiListener iUiListener2;
        Runnable runnable;
        StringBuilder sb3;
        IUiListener iUiListener3 = iUiListener;
        boolean z3 = z2;
        f.c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- start");
        CookieSyncManager createInstance = CookieSyncManager.createInstance(e.a());
        Bundle a2 = a();
        if (z) {
            a2.putString("isadd", "1");
        }
        a2.putString(Constants.PARAM_SCOPE, this.d);
        a2.putString(Constants.PARAM_CLIENT_ID, this.b.getAppId());
        if (isOEM) {
            new StringBuilder();
            a2.putString(Constants.PARAM_PLATFORM_ID, sb3.append("desktop_m_qq-").append(installChannel).append("-").append("android").append("-").append(registerChannel).append("-").append(businessId).toString());
        } else {
            a2.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        new StringBuilder();
        String sb4 = sb.append(System.currentTimeMillis() / 1000).append("").toString();
        a2.putString("sign", h.b(e.a(), sb4));
        a2.putString("time", sb4);
        a2.putString("display", "mobile");
        a2.putString("response_type", "token");
        a2.putString("redirect_uri", "auth://tauth.qq.com/");
        a2.putString("cancel_display", "1");
        a2.putString("switch", "1");
        a2.putString("status_userip", k.a());
        if (z3) {
            a2.putString("style", "qr");
        }
        new StringBuilder();
        StringBuilder sb5 = sb2;
        StringBuilder append = sb5.append(g.a().a(e.a(), "https://openmobile.qq.com/oauth2.0/m_authorize?"));
        StringBuilder append2 = sb5.append(HttpUtils.encodeUrl(a2));
        String sb6 = sb5.toString();
        new c(this, e.a(), iUiListener3, true, false);
        f.b("openSDK_LOG.AuthAgent", "OpenUi, showDialog TDialog");
        final String str = sb6;
        final IUiListener iUiListener4 = iUiListener2;
        new Runnable(this) {
            final /* synthetic */ AuthAgent c;

            {
                this.c = r7;
            }

            public void run() {
                Runnable runnable;
                boolean a2 = h.a(AuthAgent.SECURE_LIB_FILE_NAME, AuthAgent.SECURE_LIB_NAME, 3);
                JniInterface.loadSo();
                if (this.c.e != null) {
                    Activity activity = (Activity) this.c.e.get();
                    if (activity != null) {
                        final Activity activity2 = activity;
                        new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            {
                                this.b = r6;
                            }

                            public void run() {
                                TDialog tDialog;
                                a aVar;
                                if (JniInterface.isJniOk) {
                                    new a(activity2, "action_login", str, iUiListener4, this.b.c.b);
                                    a aVar2 = aVar;
                                    if (!activity2.isFinishing()) {
                                        aVar2.show();
                                        return;
                                    }
                                    return;
                                }
                                f.d("openSDK_LOG.AuthAgent", "OpenUi, secure so load failed, goto download QQ.");
                                new TDialog(activity2, "", this.b.c.a(""), (IUiListener) null, this.b.c.b);
                                TDialog tDialog2 = tDialog;
                                if (!activity2.isFinishing()) {
                                    tDialog2.show();
                                }
                            }
                        };
                        activity.runOnUiThread(runnable);
                    }
                }
            }
        };
        i.a(runnable);
        f.c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- end");
        return 2;
    }

    private boolean a(Activity activity, Fragment fragment, boolean z) {
        IUiListener iUiListener;
        StringBuilder sb;
        Activity activity2 = activity;
        Fragment fragment2 = fragment;
        boolean z2 = z;
        f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- start");
        Intent b2 = b("com.tencent.open.agent.AgentActivity");
        if (b2 != null) {
            Bundle a2 = a();
            if (z2) {
                a2.putString("isadd", "1");
            }
            a2.putString(Constants.PARAM_SCOPE, this.d);
            a2.putString(Constants.PARAM_CLIENT_ID, this.b.getAppId());
            if (isOEM) {
                new StringBuilder();
                a2.putString(Constants.PARAM_PLATFORM_ID, sb.append("desktop_m_qq-").append(installChannel).append("-").append("android").append("-").append(registerChannel).append("-").append(businessId).toString());
            } else {
                a2.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
            a2.putString("need_pay", "1");
            a2.putString(Constants.KEY_APP_NAME, h.a(e.a()));
            Intent putExtra = b2.putExtra(Constants.KEY_ACTION, "action_login");
            Intent putExtra2 = b2.putExtra(Constants.KEY_PARAMS, a2);
            Intent putExtra3 = b2.putExtra("appid", this.b.getAppId());
            if (a(b2)) {
                new b(this, this.c);
                this.c = iUiListener;
                Object listenerWithRequestcode = UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_LOGIN, this.c);
                if (fragment2 != null) {
                    f.b("openSDK_LOG.AuthAgent", "startAssitActivity fragment");
                    a(fragment2, b2, (int) Constants.REQUEST_LOGIN);
                } else {
                    f.b("openSDK_LOG.AuthAgent", "startAssitActivity activity");
                    a(activity2, b2, (int) Constants.REQUEST_LOGIN);
                }
                f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, found activity for loginIntent");
                d.a().a(0, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
                return true;
            }
        }
        d.a().a(1, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "startActionActivity fail");
        f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, no target activity for loginIntent");
        return false;
    }

    /* access modifiers changed from: protected */
    public void a(IUiListener iUiListener) {
        StringBuilder sb;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.AuthAgent", "reportDAU() -- start");
        String str = "tencent&sdk&qazxc***14969%%";
        String str2 = "qzone3.4";
        String accessToken = this.b.getAccessToken();
        String openId = this.b.getOpenId();
        String appId = this.b.getAppId();
        String str3 = "";
        if (!TextUtils.isEmpty(accessToken) && !TextUtils.isEmpty(openId) && !TextUtils.isEmpty(appId)) {
            new StringBuilder();
            str3 = k.f(sb.append(str).append(accessToken).append(appId).append(openId).append(str2).toString());
        }
        if (TextUtils.isEmpty(str3)) {
            f.e("openSDK_LOG.AuthAgent", "reportDAU -- encrytoken is null");
            return;
        }
        Bundle a2 = a();
        a2.putString("encrytoken", str3);
        HttpUtils.requestAsync(this.b, e.a(), "https://openmobile.qq.com/user/user_login_statis", a2, Constants.HTTP_POST, (IRequestListener) null);
        f.c("openSDK_LOG.AuthAgent", "reportDAU() -- end");
    }

    /* access modifiers changed from: protected */
    public void b(IUiListener iUiListener) {
        IRequestListener iRequestListener;
        IUiListener iUiListener2;
        Bundle a2 = a();
        a2.putString("reqType", "checkLogin");
        new a(this, iUiListener);
        new BaseApi.TempRequestListener(this, iUiListener2);
        HttpUtils.requestAsync(this.b, e.a(), "https://openmobile.qq.com/v3/user/get_info", a2, Constants.HTTP_GET, iRequestListener);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener) {
        return doLogin(activity, str, iUiListener, false, (Fragment) null);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment) {
        return doLogin(activity, str, iUiListener, z, fragment, false);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment, boolean z2) {
        WeakReference<Activity> weakReference;
        IUiListener iUiListener2;
        Activity activity2 = activity;
        boolean z3 = z;
        Fragment fragment2 = fragment;
        boolean z4 = z2;
        this.d = str;
        new WeakReference<>(activity2);
        this.e = weakReference;
        this.c = iUiListener;
        if (com.tencent.open.utils.f.a((Context) activity2, this.b.getAppId()).b("C_LoginWeb") || !a(activity2, fragment2, z3)) {
            d.a().a(this.b.getOpenId(), this.b.getAppId(), "2", "1", "5", "1", "0", "0");
            f.d("openSDK_LOG.AuthAgent", "doLogin startActivity fail show dialog.");
            new b(this, this.c);
            this.c = iUiListener2;
            return a(z3, this.c, z4);
        }
        f.c("openSDK_LOG.AuthAgent", "OpenUi, showUi, return Constants.UI_ACTIVITY");
        d.a().a(this.b.getOpenId(), this.b.getAppId(), "2", "1", "5", "0", "0", "0");
        return 1;
    }

    public void releaseResource() {
        this.c = null;
    }
}
