package com.android.cglib.dx.d;

import android.widget.IPhotoView;
import java.io.PrintStream;
import java.io.PrintWriter;

public class g extends RuntimeException {
    private StringBuffer a;

    public g(String str) {
        this(str, (Throwable) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(String str, Throwable th) {
        super(str == null ? th != null ? th.getMessage() : null : str, th);
        if (th instanceof g) {
            String stringBuffer = ((g) th).a.toString();
            this.a = new StringBuffer(stringBuffer.length() + IPhotoView.DEFAULT_ZOOM_DURATION);
            this.a.append(stringBuffer);
            return;
        }
        this.a = new StringBuffer(IPhotoView.DEFAULT_ZOOM_DURATION);
    }

    public g(Throwable th) {
        this((String) null, th);
    }

    public static g a(Throwable th, String str) {
        g gVar = th instanceof g ? (g) th : new g(th);
        gVar.a(str);
        return gVar;
    }

    public void a(String str) {
        if (str == null) {
            throw new NullPointerException("str == null");
        }
        this.a.append(str);
        if (!str.endsWith("\n")) {
            this.a.append(10);
        }
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        printStream.println(this.a);
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        printWriter.println(this.a);
    }
}
