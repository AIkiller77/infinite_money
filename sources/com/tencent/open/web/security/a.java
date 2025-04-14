package com.tencent.open.web.security;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import com.tencent.open.a.f;

/* compiled from: ProGuard */
public class a extends InputConnectionWrapper {
    public static String a;
    public static boolean b = false;
    public static boolean c = false;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(InputConnection inputConnection, boolean z) {
        super(inputConnection, z);
    }

    public boolean commitText(CharSequence charSequence, int i) {
        StringBuilder sb;
        CharSequence charSequence2 = charSequence;
        c = true;
        a = charSequence2.toString();
        new StringBuilder();
        f.a("openSDK_LOG.CaptureInputConnection", sb.append("-->commitText: ").append(charSequence2.toString()).toString());
        return super.commitText(charSequence2, i);
    }

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        StringBuilder sb;
        StringBuilder sb2;
        KeyEvent keyEvent2 = keyEvent;
        if (keyEvent2.getAction() == 0) {
            f.c("openSDK_LOG.CaptureInputConnection", "sendKeyEvent");
            a = String.valueOf((char) keyEvent2.getUnicodeChar());
            c = true;
            new StringBuilder();
            f.b("openSDK_LOG.CaptureInputConnection", sb2.append("s: ").append(a).toString());
        }
        new StringBuilder();
        f.b("openSDK_LOG.CaptureInputConnection", sb.append("-->sendKeyEvent: ").append(a).toString());
        return super.sendKeyEvent(keyEvent2);
    }

    public boolean setComposingText(CharSequence charSequence, int i) {
        StringBuilder sb;
        CharSequence charSequence2 = charSequence;
        c = true;
        a = charSequence2.toString();
        new StringBuilder();
        f.a("openSDK_LOG.CaptureInputConnection", sb.append("-->setComposingText: ").append(charSequence2.toString()).toString());
        return super.setComposingText(charSequence2, i);
    }
}
