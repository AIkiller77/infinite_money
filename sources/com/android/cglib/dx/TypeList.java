package com.android.cglib.dx;

import com.android.cglib.dx.c.d.b;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class TypeList {
    final TypeId<?>[] a;
    final b b;

    TypeList(TypeId<?>[] typeIdArr) {
        this.a = (TypeId[]) typeIdArr.clone();
        this.b = new b(typeIdArr.length);
        for (int i = 0; i < typeIdArr.length; i++) {
            this.b.a(i, typeIdArr[i].b);
        }
    }

    public List<TypeId<?>> asList() {
        return Collections.unmodifiableList(Arrays.asList(this.a));
    }

    public boolean equals(Object obj) {
        return (obj instanceof TypeList) && Arrays.equals(((TypeList) obj).a, this.a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.a.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.a[i]);
        }
        return sb.toString();
    }
}
