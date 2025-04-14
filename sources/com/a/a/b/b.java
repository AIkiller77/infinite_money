package com.a.a.b;

import android.support.v4.view.ViewCompat;
import java.util.HashMap;

public abstract class b {
    protected HashMap<a, Integer> a = a();

    public enum a {
        FOREGROUND,
        BACKGROUND,
        SELECTION_FOREGROUND,
        SELECTION_BACKGROUND,
        CARET_FOREGROUND,
        CARET_BACKGROUND,
        CARET_DISABLED,
        LINE_HIGHLIGHT,
        NON_PRINTING_GLYPH,
        COMMENT,
        KEYWORD,
        NAME,
        LITERAL,
        STRING,
        SECONDARY
    }

    private HashMap<a, Integer> a() {
        HashMap<a, Integer> hashMap = new HashMap<>(a.values().length);
        hashMap.put(a.FOREGROUND, Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
        hashMap.put(a.BACKGROUND, -32);
        hashMap.put(a.SELECTION_FOREGROUND, -32);
        hashMap.put(a.SELECTION_BACKGROUND, -6832092);
        hashMap.put(a.CARET_FOREGROUND, -32);
        hashMap.put(a.CARET_BACKGROUND, -12537601);
        hashMap.put(a.CARET_DISABLED, -8355712);
        hashMap.put(a.LINE_HIGHLIGHT, 545818760);
        hashMap.put(a.NON_PRINTING_GLYPH, -5592406);
        hashMap.put(a.COMMENT, -12615841);
        hashMap.put(a.KEYWORD, -3129123);
        hashMap.put(a.NAME, -14008065);
        hashMap.put(a.LITERAL, -10452737);
        hashMap.put(a.STRING, -2276216);
        hashMap.put(a.SECONDARY, -8355712);
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        r2 = com.a.a.b.b.a.a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(int r2) {
        /*
            r1 = this;
            r0 = 10
            if (r2 == r0) goto L_0x002c
            r0 = 30
            if (r2 == r0) goto L_0x0029
            r0 = 40
            if (r2 == r0) goto L_0x0029
            switch(r2) {
                case 0: goto L_0x001a;
                case 1: goto L_0x0026;
                case 2: goto L_0x002c;
                case 3: goto L_0x0023;
                case 4: goto L_0x0020;
                default: goto L_0x000f;
            }
        L_0x000f:
            switch(r2) {
                case 20: goto L_0x002c;
                case 21: goto L_0x0029;
                default: goto L_0x0012;
            }
        L_0x0012:
            switch(r2) {
                case 50: goto L_0x001d;
                case 51: goto L_0x001d;
                default: goto L_0x0015;
            }
        L_0x0015:
            java.lang.String r2 = "Invalid token type"
            com.a.a.b.r.a(r2)
        L_0x001a:
            com.a.a.b.b$a r2 = com.a.a.b.b.a.FOREGROUND
            goto L_0x002e
        L_0x001d:
            com.a.a.b.b$a r2 = com.a.a.b.b.a.STRING
            goto L_0x002e
        L_0x0020:
            com.a.a.b.b$a r2 = com.a.a.b.b.a.LITERAL
            goto L_0x002e
        L_0x0023:
            com.a.a.b.b$a r2 = com.a.a.b.b.a.NAME
            goto L_0x002e
        L_0x0026:
            com.a.a.b.b$a r2 = com.a.a.b.b.a.KEYWORD
            goto L_0x002e
        L_0x0029:
            com.a.a.b.b$a r2 = com.a.a.b.b.a.COMMENT
            goto L_0x002e
        L_0x002c:
            com.a.a.b.b$a r2 = com.a.a.b.b.a.SECONDARY
        L_0x002e:
            int r2 = r1.a((com.a.a.b.b.a) r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.b.a(int):int");
    }

    public int a(a aVar) {
        Integer num = this.a.get(aVar);
        if (num != null) {
            return num.intValue();
        }
        r.a("Color not specified for " + aVar);
        return 0;
    }

    public void a(a aVar, int i) {
        this.a.put(aVar, Integer.valueOf(i));
    }
}
