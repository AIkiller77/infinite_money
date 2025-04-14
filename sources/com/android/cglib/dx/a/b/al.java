package com.android.cglib.dx.a.b;

import java.util.HashMap;
import java.util.TreeMap;

public final class al {
    private final HashMap<String, a> a = new HashMap<>(50);

    private static class a {
        /* access modifiers changed from: private */
        public final String a;
        private int b = 1;
        private int c;
        private int d;
        private int e;

        public a(x xVar, String str) {
            int b_ = xVar.b_();
            this.a = str;
            this.c = b_;
            this.d = b_;
            this.e = b_;
        }

        public String a() {
            String str;
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  ");
            sb2.append(this.a);
            sb2.append(": ");
            sb2.append(this.b);
            sb2.append(" item");
            sb2.append(this.b == 1 ? "" : "s");
            sb2.append("; ");
            sb2.append(this.c);
            sb2.append(" bytes total\n");
            sb.append(sb2.toString());
            if (this.e == this.d) {
                str = "    " + this.e + " bytes/item\n";
            } else {
                str = "    " + this.e + ".." + this.d + " bytes/item; average " + (this.c / this.b) + "\n";
            }
            sb.append(str);
            return sb.toString();
        }

        public void a(x xVar) {
            int b_ = xVar.b_();
            this.b++;
            this.c += b_;
            if (b_ > this.d) {
                this.d = b_;
            }
            if (b_ < this.e) {
                this.e = b_;
            }
        }

        public void a(com.android.cglib.dx.d.a aVar) {
            aVar.a(a());
        }
    }

    public void a(ak akVar) {
        for (x a2 : akVar.a()) {
            a(a2);
        }
    }

    public void a(x xVar) {
        String i = xVar.i();
        a aVar = this.a.get(i);
        if (aVar == null) {
            this.a.put(i, new a(xVar, i));
        } else {
            aVar.a(xVar);
        }
    }

    public final void a(com.android.cglib.dx.d.a aVar) {
        if (this.a.size() != 0) {
            aVar.a(0, "\nstatistics:\n");
            TreeMap treeMap = new TreeMap();
            for (a next : this.a.values()) {
                treeMap.put(next.a, next);
            }
            for (a a2 : treeMap.values()) {
                a2.a(aVar);
            }
        }
    }
}
