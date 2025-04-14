package com.android.cglib.dx;

import com.android.cglib.dx.a.a.w;
import com.android.cglib.dx.a.b.g;
import com.android.cglib.dx.a.b.l;
import com.android.cglib.dx.a.b.n;
import com.android.cglib.dx.a.b.p;
import com.android.cglib.dx.c.b.i;
import com.android.cglib.dx.c.b.q;
import com.android.cglib.dx.c.c.v;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

public final class DexMaker {
    private final Map<TypeId<?>, c> a = new LinkedHashMap();

    static class a {
        final FieldId<?, ?> a;
        private final int b;
        /* access modifiers changed from: private */
        public final Object c;

        a(FieldId<?, ?> fieldId, int i, Object obj) {
            if ((i & 8) != 0 || obj == null) {
                this.a = fieldId;
                this.b = i;
                this.c = obj;
                return;
            }
            throw new IllegalArgumentException("instance fields may not have a value");
        }

        /* access modifiers changed from: package-private */
        public n a() {
            return new n(this.a.e, this.b);
        }

        public boolean b() {
            return (this.b & 8) != 0;
        }
    }

    static class b {
        final MethodId<?, ?> a;
        private final int b;
        /* access modifiers changed from: private */
        public final Code c = new Code(this);

        public b(MethodId<?, ?> methodId, int i) {
            this.a = methodId;
            this.b = i;
        }

        /* access modifiers changed from: package-private */
        public p a(com.android.cglib.dx.a.b bVar) {
            return new p(this.a.f, this.b, w.a(new q(this.c.c(), 0), 1, (i) null, this.c.b(), bVar), com.android.cglib.dx.c.d.b.a);
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return (this.b & 8) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            return (this.b & 65546) != 0;
        }
    }

    private static class c {
        private final TypeId<?> a;
        /* access modifiers changed from: private */
        public boolean b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public TypeId<?> d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public TypeList f;
        /* access modifiers changed from: private */
        public final Map<FieldId, a> g = new LinkedHashMap();
        /* access modifiers changed from: private */
        public final Map<MethodId, b> h = new LinkedHashMap();

        c(TypeId<?> typeId) {
            this.a = typeId;
        }

        /* access modifiers changed from: package-private */
        public g a() {
            if (!this.b) {
                throw new IllegalStateException("Undeclared type " + this.a + " declares members: " + this.g.keySet() + " " + this.h.keySet());
            }
            com.android.cglib.dx.a.b bVar = new com.android.cglib.dx.a.b();
            bVar.a = 13;
            g gVar = new g(this.a.c, this.c, this.d.c, this.f.b, new v(this.e));
            for (b next : this.h.values()) {
                p a2 = next.a(bVar);
                if (next.b()) {
                    gVar.a(a2);
                } else {
                    gVar.b(a2);
                }
            }
            for (a next2 : this.g.values()) {
                n a3 = next2.a();
                if (next2.b()) {
                    gVar.a(a3, (com.android.cglib.dx.c.c.a) Constants.a(next2.c));
                } else {
                    gVar.a(a3);
                }
            }
            return gVar;
        }
    }

    private c a(TypeId<?> typeId) {
        c cVar = this.a.get(typeId);
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(typeId);
        this.a.put(typeId, cVar2);
        return cVar2;
    }

    private ClassLoader a(File file, File file2, ClassLoader classLoader) {
        try {
            return (ClassLoader) Class.forName("dalvik.system.DexClassLoader").getConstructor(new Class[]{String.class, String.class, String.class, ClassLoader.class}).newInstance(new Object[]{file.getPath(), file2.getAbsolutePath(), null, classLoader});
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("load() requires a Dalvik VM", e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2.getCause());
        } catch (InstantiationException unused) {
            throw new AssertionError();
        } catch (NoSuchMethodException unused2) {
            throw new AssertionError();
        } catch (IllegalAccessException unused3) {
            throw new AssertionError();
        }
    }

    private String a() {
        Set<TypeId<?>> keySet = this.a.keySet();
        int[] iArr = new int[keySet.size()];
        int i = 0;
        for (TypeId<?> a2 : keySet) {
            c a3 = a(a2);
            Set keySet2 = a3.h.keySet();
            if (a3.d != null) {
                iArr[i] = (a3.d.hashCode() * 31) + keySet2.hashCode();
                i++;
            }
        }
        Arrays.sort(iArr);
        int i2 = 1;
        for (int i3 : iArr) {
            i2 = (i2 * 31) + i3;
        }
        return "Generated_" + i2 + ".jar";
    }

    public Code declare(MethodId<?, ?> methodId, int i) {
        c a2 = a(methodId.a);
        if (a2.h.containsKey(methodId)) {
            throw new IllegalStateException("already declared: " + methodId);
        } else if ((i & -64) != 0) {
            throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(i));
        } else {
            if ((i & 32) != 0) {
                i = (i & -33) | 131072;
            }
            if (methodId.isConstructor()) {
                i |= 65536;
            }
            b bVar = new b(methodId, i);
            a2.h.put(methodId, bVar);
            return bVar.c;
        }
    }

    public void declare(FieldId<?, ?> fieldId, int i, Object obj) {
        c a2 = a(fieldId.a);
        if (a2.g.containsKey(fieldId)) {
            throw new IllegalStateException("already declared: " + fieldId);
        } else if ((i & -224) != 0) {
            throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(i));
        } else if ((i & 8) != 0 || obj == null) {
            a2.g.put(fieldId, new a(fieldId, i, obj));
        } else {
            throw new IllegalArgumentException("staticValue is non-null, but field is not static");
        }
    }

    public void declare(TypeId<?> typeId, String str, int i, TypeId<?> typeId2, TypeId<?>... typeIdArr) {
        c a2 = a(typeId);
        if ((i & -1042) != 0) {
            throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(i));
        } else if (a2.b) {
            throw new IllegalStateException("already declared: " + typeId);
        } else {
            boolean unused = a2.b = true;
            int unused2 = a2.c = i;
            TypeId unused3 = a2.d = typeId2;
            String unused4 = a2.e = str;
            TypeList unused5 = a2.f = new TypeList(typeIdArr);
        }
    }

    public byte[] generate() {
        com.android.cglib.dx.a.b bVar = new com.android.cglib.dx.a.b();
        bVar.a = 13;
        l lVar = new l(bVar);
        for (c a2 : this.a.values()) {
            lVar.a(a2.a());
        }
        try {
            return lVar.a((Writer) null, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ClassLoader generateAndLoad(ClassLoader classLoader, File file) {
        if (file == null) {
            String property = System.getProperty("dexmaker.dexcache");
            if (property != null) {
                file = new File(property);
            } else {
                file = new a().a();
                if (file == null) {
                    throw new IllegalArgumentException("dexcache == null (and no default could be found; consider setting the 'dexmaker.dexcache' system property)");
                }
            }
        }
        File file2 = new File(file, a());
        if (file2.exists()) {
            return a(file2, file, classLoader);
        }
        byte[] generate = generate();
        file2.createNewFile();
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(file2));
        JarEntry jarEntry = new JarEntry("classes.dex");
        jarEntry.setSize((long) generate.length);
        jarOutputStream.putNextEntry(jarEntry);
        jarOutputStream.write(generate);
        jarOutputStream.closeEntry();
        jarOutputStream.close();
        return a(file2, file, classLoader);
    }
}
