package com.android.cglib.dx;

import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.d.c;
import java.util.HashMap;
import java.util.Map;

public final class TypeId<T> {
    public static final TypeId<Boolean> BOOLEAN = new TypeId<>(c.a);
    public static final TypeId<Byte> BYTE = new TypeId<>(c.b);
    public static final TypeId<Character> CHAR = new TypeId<>(c.c);
    public static final TypeId<Double> DOUBLE = new TypeId<>(c.d);
    public static final TypeId<Float> FLOAT = new TypeId<>(c.e);
    public static final TypeId<Integer> INT = new TypeId<>(c.f);
    public static final TypeId<Long> LONG = new TypeId<>(c.g);
    public static final TypeId<Object> OBJECT = new TypeId<>(c.o);
    public static final TypeId<Short> SHORT = new TypeId<>(c.h);
    public static final TypeId<String> STRING = new TypeId<>(c.f19q);
    public static final TypeId<Void> VOID = new TypeId<>(c.i);
    private static final Map<Class<?>, TypeId<?>> d = new HashMap();
    final String a;
    final c b;
    final w c;

    static {
        d.put(Boolean.TYPE, BOOLEAN);
        d.put(Byte.TYPE, BYTE);
        d.put(Character.TYPE, CHAR);
        d.put(Double.TYPE, DOUBLE);
        d.put(Float.TYPE, FLOAT);
        d.put(Integer.TYPE, INT);
        d.put(Long.TYPE, LONG);
        d.put(Short.TYPE, SHORT);
        d.put(Void.TYPE, VOID);
    }

    TypeId(c cVar) {
        this(cVar.e(), cVar);
    }

    TypeId(String str, c cVar) {
        if (str == null || cVar == null) {
            throw new NullPointerException();
        }
        this.a = str;
        this.b = cVar;
        this.c = w.a(cVar);
    }

    public static <T> TypeId<T> get(Class<T> cls) {
        if (cls.isPrimitive()) {
            return d.get(cls);
        }
        String replace = cls.getName().replace('.', '/');
        if (!cls.isArray()) {
            replace = 'L' + replace + ';';
        }
        return get(replace);
    }

    public static <T> TypeId<T> get(String str) {
        return new TypeId<>(str, c.b(str));
    }

    public boolean equals(Object obj) {
        return (obj instanceof TypeId) && ((TypeId) obj).a.equals(this.a);
    }

    public MethodId<T, Void> getConstructor(TypeId<?>... typeIdArr) {
        return new MethodId<>(this, VOID, "<init>", new TypeList(typeIdArr));
    }

    public <V> FieldId<T, V> getField(TypeId<V> typeId, String str) {
        return new FieldId<>(this, typeId, str);
    }

    public <R> MethodId<T, R> getMethod(TypeId<R> typeId, String str, TypeId<?>... typeIdArr) {
        return new MethodId<>(this, typeId, str, new TypeList(typeIdArr));
    }

    public String getName() {
        return this.a;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a;
    }
}
