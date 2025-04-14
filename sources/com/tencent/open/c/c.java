package com.tencent.open.c;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.tencent.open.a.f;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.open.web.security.a;
import com.tencent.qq.widget.R;

/* compiled from: ProGuard */
public class c extends b {
    public static boolean a;
    private KeyEvent b;
    private a c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(Context context) {
        super(context);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        StringBuilder sb;
        int unicodeChar;
        KeyEvent keyEvent2;
        KeyEvent keyEvent3 = keyEvent;
        new StringBuilder();
        f.b("openSDK_LOG.SecureWebView", sb.append("-->dispatchKeyEvent, is device support: ").append(a).toString());
        if (!a) {
            return super.dispatchKeyEvent(keyEvent3);
        }
        if (keyEvent3.getAction() != 0) {
            return super.dispatchKeyEvent(keyEvent3);
        }
        switch (keyEvent3.getKeyCode()) {
            case 4:
                return super.dispatchKeyEvent(keyEvent3);
            case R.styleable.AppCompatTheme_textAppearanceSearchResultTitle /*66*/:
                return super.dispatchKeyEvent(keyEvent3);
            case R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle /*67*/:
                a.b = true;
                return super.dispatchKeyEvent(keyEvent3);
            default:
                if (keyEvent3.getUnicodeChar() == 0) {
                    return super.dispatchKeyEvent(keyEvent3);
                }
                if (!SecureJsInterface.isPWDEdit || (((unicodeChar = keyEvent3.getUnicodeChar()) < 33 || unicodeChar > 95) && (unicodeChar < 97 || unicodeChar > 125))) {
                    return super.dispatchKeyEvent(keyEvent3);
                }
                new KeyEvent(0, 17);
                this.b = keyEvent2;
                return super.dispatchKeyEvent(this.b);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        StringBuilder sb;
        StringBuilder sb2;
        a aVar;
        EditorInfo editorInfo2 = editorInfo;
        new StringBuilder();
        f.c("openSDK_LOG.SecureWebView", sb.append("-->create input connection, is edit: ").append(SecureJsInterface.isPWDEdit).toString());
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo2);
        new StringBuilder();
        f.a("openSDK_LOG.SecureWebView", sb2.append("-->onCreateInputConnection, inputConn is ").append(onCreateInputConnection).toString());
        if (onCreateInputConnection != null) {
            a = true;
            new a(super.onCreateInputConnection(editorInfo2), false);
            this.c = aVar;
            return this.c;
        }
        a = false;
        return onCreateInputConnection;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        StringBuilder sb;
        int unicodeChar;
        KeyEvent keyEvent2;
        int i2 = i;
        KeyEvent keyEvent3 = keyEvent;
        new StringBuilder();
        f.b("openSDK_LOG.SecureWebView", sb.append("-->onKeyDown, is device support: ").append(a).toString());
        if (!a) {
            return super.onKeyDown(i2, keyEvent3);
        }
        if (keyEvent3.getAction() != 0) {
            return super.onKeyDown(i2, keyEvent3);
        }
        switch (keyEvent3.getKeyCode()) {
            case 4:
                return super.onKeyDown(i2, keyEvent3);
            case R.styleable.AppCompatTheme_textAppearanceSearchResultTitle /*66*/:
                return super.onKeyDown(i2, keyEvent3);
            case R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle /*67*/:
                a.b = true;
                return super.onKeyDown(i2, keyEvent3);
            default:
                if (keyEvent3.getUnicodeChar() == 0) {
                    return super.onKeyDown(i2, keyEvent3);
                }
                if (!SecureJsInterface.isPWDEdit || (((unicodeChar = keyEvent3.getUnicodeChar()) < 33 || unicodeChar > 95) && (unicodeChar < 97 || unicodeChar > 125))) {
                    return super.onKeyDown(i2, keyEvent3);
                }
                new KeyEvent(0, 17);
                this.b = keyEvent2;
                return super.onKeyDown(this.b.getKeyCode(), this.b);
        }
    }
}
