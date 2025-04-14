package com.tencent.open.a;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ProGuard */
public class g implements Iterable<String> {
    private ConcurrentLinkedQueue<String> a = null;
    private AtomicInteger b = null;

    public g() {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue;
        AtomicInteger atomicInteger;
        new ConcurrentLinkedQueue<>();
        this.a = concurrentLinkedQueue;
        new AtomicInteger(0);
        this.b = atomicInteger;
    }

    public int a() {
        return this.b.get();
    }

    public int a(String str) {
        String str2 = str;
        int length = str2.length();
        boolean add = this.a.add(str2);
        return this.b.addAndGet(length);
    }

    public void a(Writer writer, char[] cArr) throws IOException {
        Writer writer2 = writer;
        char[] cArr2 = cArr;
        if (writer2 != null && cArr2 != null && cArr2.length != 0) {
            int length = cArr2.length;
            int i = length;
            int i2 = 0;
            Iterator<String> it = iterator();
            while (it.hasNext()) {
                String next = it.next();
                int i3 = 0;
                int length2 = next.length();
                while (length2 > 0) {
                    int i4 = i > length2 ? length2 : i;
                    next.getChars(i3, i3 + i4, cArr2, i2);
                    i -= i4;
                    i2 += i4;
                    length2 -= i4;
                    i3 += i4;
                    if (i == 0) {
                        writer2.write(cArr2, 0, length);
                        i2 = 0;
                        i = length;
                    }
                }
            }
            if (i2 > 0) {
                writer2.write(cArr2, 0, i2);
            }
            writer2.flush();
        }
    }

    public void b() {
        this.a.clear();
        this.b.set(0);
    }

    public Iterator<String> iterator() {
        return this.a.iterator();
    }
}
