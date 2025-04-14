package com.android.cglib.dx;

import com.android.cglib.dx.DexMaker;
import com.android.cglib.dx.c.b.f;
import com.android.cglib.dx.c.b.j;
import com.android.cglib.dx.c.b.k;
import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.p;
import com.android.cglib.dx.c.b.r;
import com.android.cglib.dx.c.b.s;
import com.android.cglib.dx.c.b.t;
import com.android.cglib.dx.c.b.u;
import com.android.cglib.dx.c.c.l;
import com.android.cglib.dx.c.d.b;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.c.d.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Code {
    private final MethodId<?, ?> a;
    private final List<Label> b = new ArrayList();
    private Label c;
    private boolean d;
    private final Local<?> e;
    private final List<Local<?>> f = new ArrayList();
    private final List<Local<?>> g = new ArrayList();
    private s h = s.a;
    private final List<TypeId<?>> i = new ArrayList();
    private final List<Label> j = new ArrayList();
    private b k = b.a;

    Code(DexMaker.b bVar) {
        this.a = bVar.a;
        if (bVar.a()) {
            this.e = null;
        } else {
            this.e = Local.a(this, this.a.a);
            this.f.add(this.e);
        }
        for (TypeId<?> a2 : this.a.d.a) {
            this.f.add(Local.a(this, a2));
        }
        this.c = new Label();
        a(this.c);
        this.c.c = true;
    }

    private <T> Local<T> a(Local<?> local, TypeId<T> typeId) {
        if (local.a.equals(typeId)) {
            return local;
        }
        throw new IllegalArgumentException("requested " + typeId + " but was " + local.a);
    }

    private static n a(Local<?> local, Local<?>[] localArr) {
        int i2 = local != null ? 1 : 0;
        n nVar = new n(localArr.length + i2);
        if (local != null) {
            nVar.a(0, local.b());
        }
        for (int i3 = 0; i3 < localArr.length; i3++) {
            nVar.a(i3 + i2, localArr[i3].b());
        }
        return nVar;
    }

    private p a(c cVar, c cVar2) {
        if (cVar.c() == 6) {
            int c2 = cVar2.c();
            if (c2 == 8) {
                return r.bv;
            }
            switch (c2) {
                case 2:
                    return r.bt;
                case 3:
                    return r.bu;
            }
        }
        return r.a(cVar2, cVar);
    }

    private b a(List<TypeId<?>> list) {
        b bVar = new b(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            bVar.a(i2, list.get(i2).b);
        }
        return bVar;
    }

    private void a(Label label) {
        if (label.b != this) {
            if (label.b != null) {
                throw new IllegalArgumentException("Cannot adopt label; it belongs to another Code");
            }
            label.b = this;
            this.b.add(label);
        }
    }

    private void a(Label label, List<Label> list) {
        Label label2 = new Label();
        a(label2);
        this.c.e = label2;
        this.c.f = label;
        this.c.d = list;
        this.c = label2;
        this.c.c = true;
    }

    private void a(Local<?> local, boolean z) {
        a((f) new k(z ? r.d((d) local.a.b) : r.c((d) local.a.b), this.h, local.b(), n.a));
    }

    private void a(f fVar) {
        a(fVar, (Label) null);
    }

    private void a(f fVar, Label label) {
        if (this.c == null || !this.c.c) {
            throw new IllegalStateException("no current label");
        }
        this.c.a.add(fVar);
        int b2 = fVar.d().b();
        if (b2 != 6) {
            switch (b2) {
                case 1:
                    if (label != null) {
                        throw new IllegalArgumentException("unexpected branch: " + label);
                    }
                    return;
                case 2:
                    if (label != null) {
                        throw new IllegalArgumentException("unexpected branch: " + label);
                    }
                    break;
                case 3:
                    if (label != null) {
                        this.c.e = label;
                        break;
                    } else {
                        throw new IllegalArgumentException("branch == null");
                    }
                case 4:
                    if (label == null) {
                        throw new IllegalArgumentException("branch == null");
                    }
                    a(label, (List<Label>) Collections.emptyList());
                    return;
                default:
                    throw new IllegalArgumentException();
            }
            this.c = null;
        } else if (label != null) {
            throw new IllegalArgumentException("unexpected branch: " + label);
        } else {
            a((Label) null, (List<Label>) new ArrayList(this.j));
        }
    }

    private <D, R> void a(p pVar, MethodId<D, R> methodId, Local<? super R> local, Local<? extends D> local2, Local<?>... localArr) {
        a((f) new t(pVar, this.h, a((Local<?>) local2, localArr), this.k, methodId.f));
        if (local != null) {
            a((Local<?>) local, false);
        }
    }

    private void d() {
        Iterator<Label> it = this.b.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Label next = it.next();
            if (next.a()) {
                it.remove();
            } else {
                next.b();
                next.g = i2;
                i2++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.d) {
            throw new AssertionError();
        }
        this.d = true;
        int i2 = 0;
        for (Local<?> a2 : this.g) {
            i2 += a2.a(i2);
        }
        ArrayList arrayList = new ArrayList();
        int i3 = i2;
        for (Local next : this.f) {
            l a3 = l.a(i3 - i2);
            i3 += next.a(i3);
            arrayList.add(new j(r.b((d) next.a.b), this.h, next.b(), n.a, a3));
        }
        this.b.get(0).a.addAll(0, arrayList);
    }

    public void addCatchClause(TypeId<? extends Throwable> typeId, Label label) {
        if (this.i.contains(typeId)) {
            throw new IllegalArgumentException("Already caught: " + typeId);
        }
        a(label);
        this.i.add(typeId);
        this.k = a(this.i);
        this.j.add(label);
    }

    public void aget(Local<?> local, Local<?> local2, Local<Integer> local3) {
        a((f) new u(r.k((d) local.a.b), this.h, n.a(local2.b(), local3.b()), this.k));
        a(local, true);
    }

    public void aput(Local<?> local, Local<Integer> local2, Local<?> local3) {
        a((f) new u(r.l((d) local3.a.b), this.h, n.a(local3.b(), local.b(), local2.b()), this.k));
    }

    public <T> void arrayLength(Local<Integer> local, Local<T> local2) {
        a((f) new u(r.bC, this.h, n.a(local2.b()), this.k));
        a((Local<?>) local, true);
    }

    /* access modifiers changed from: package-private */
    public int b() {
        int i2 = 0;
        for (Local<?> a2 : this.f) {
            i2 += a2.a();
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public com.android.cglib.dx.c.b.c c() {
        if (!this.d) {
            a();
        }
        d();
        com.android.cglib.dx.c.b.c cVar = new com.android.cglib.dx.c.b.c(this.b.size());
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            cVar.a(i2, this.b.get(i2).c());
        }
        return cVar;
    }

    public void cast(Local<?> local, Local<?> local2) {
        if (local2.getType().b.j()) {
            a((f) new t(r.ch, this.h, n.a(local2.b()), this.k, local.a.c));
            a(local, true);
            return;
        }
        a((f) new k(a(local2.a.b, local.a.b), this.h, local.b(), local2.b()));
    }

    public <T> void compare(Comparison comparison, Label label, Local<T> local, Local<T> local2) {
        a(label);
        a((f) new k(comparison.rop(b.a(local.a.b, local2.a.b)), this.h, (m) null, n.a(local.b(), local2.b())), label);
    }

    public <T extends Number> void compareFloatingPoint(Local<Integer> local, Local<T> local2, Local<T> local3, int i2) {
        p pVar;
        if (i2 == 1) {
            pVar = r.i((d) local2.a.b);
        } else if (i2 == -1) {
            pVar = r.h((d) local2.a.b);
        } else {
            throw new IllegalArgumentException("expected 1 or -1 but was " + i2);
        }
        a((f) new k(pVar, this.h, local.b(), n.a(local2.b(), local3.b())));
    }

    public void compareLongs(Local<Integer> local, Local<Long> local2, Local<Long> local3) {
        a((f) new k(r.bc, this.h, local.b(), n.a(local2.b(), local3.b())));
    }

    public <T> Local<T> getParameter(int i2, TypeId<T> typeId) {
        if (this.e != null) {
            i2++;
        }
        return a(this.f.get(i2), typeId);
    }

    public <T> Local<T> getThis(TypeId<T> typeId) {
        if (this.e != null) {
            return a(this.e, typeId);
        }
        throw new IllegalStateException("static methods cannot access 'this'");
    }

    public <D, V> void iget(FieldId<D, V> fieldId, Local<V> local, Local<D> local2) {
        a((f) new t(r.n((d) local.a.b), this.h, n.a(local2.b()), this.k, fieldId.e));
        a((Local<?>) local, true);
    }

    public void instanceOfType(Local<?> local, Local<?> local2, TypeId<?> typeId) {
        a((f) new t(r.ci, this.h, n.a(local2.b()), this.k, typeId.c));
        a(local, true);
    }

    public <D, R> void invokeDirect(MethodId<D, R> methodId, Local<? super R> local, Local<? extends D> local2, Local<?>... localArr) {
        a(r.d(methodId.b(true)), methodId, local, local2, localArr);
    }

    public <D, R> void invokeInterface(MethodId<D, R> methodId, Local<? super R> local, Local<? extends D> local2, Local<?>... localArr) {
        a(r.e(methodId.b(true)), methodId, local, local2, localArr);
    }

    public <R> void invokeStatic(MethodId<?, R> methodId, Local<? super R> local, Local<?>... localArr) {
        a(r.a(methodId.b(true)), methodId, local, (Local) null, localArr);
    }

    public <D, R> void invokeSuper(MethodId<D, R> methodId, Local<? super R> local, Local<? extends D> local2, Local<?>... localArr) {
        a(r.c(methodId.b(true)), methodId, local, local2, localArr);
    }

    public <D, R> void invokeVirtual(MethodId<D, R> methodId, Local<? super R> local, Local<? extends D> local2, Local<?>... localArr) {
        a(r.b(methodId.b(true)), methodId, local, local2, localArr);
    }

    public <D, V> void iput(FieldId<D, V> fieldId, Local<D> local, Local<V> local2) {
        a((f) new t(r.o((d) local2.a.b), this.h, n.a(local2.b(), local.b()), this.k, fieldId.e));
    }

    public void jump(Label label) {
        a(label);
        a((f) new k(r.s, this.h, (m) null, n.a), label);
    }

    public <T> void loadConstant(Local<T> local, T t) {
        p e2 = t == null ? r.r : r.e((d) local.a.b);
        if (e2.b() == 1) {
            a((f) new j(e2, this.h, local.b(), n.a, Constants.a(t)));
            return;
        }
        a((f) new t(e2, this.h, n.a, this.k, Constants.a(t)));
        a((Local<?>) local, true);
    }

    public void mark(Label label) {
        a(label);
        if (label.c) {
            throw new IllegalStateException("already marked");
        }
        label.c = true;
        if (this.c != null) {
            jump(label);
        }
        this.c = label;
    }

    public void monitorEnter(Local<?> local) {
        a((f) new u(r.bE, this.h, n.a(local.b()), this.k));
    }

    public void monitorExit(Local<?> local) {
        a((f) new u(r.bE, this.h, n.a(local.b()), this.k));
    }

    public <T> void move(Local<T> local, Local<T> local2) {
        a((f) new k(r.a((d) local2.a.b), this.h, local.b(), local2.b()));
    }

    public <T> void newArray(Local<T> local, Local<Integer> local2) {
        a((f) new t(r.m((d) local.a.b), this.h, n.a(local2.b()), this.k, local.a.c));
        a((Local<?>) local, true);
    }

    public <T> void newInstance(Local<T> local, MethodId<T, Void> methodId, Local<?>... localArr) {
        if (local == null) {
            throw new IllegalArgumentException();
        }
        a((f) new t(r.bY, this.h, n.a, this.k, methodId.a.c));
        a((Local<?>) local, true);
        invokeDirect(methodId, (Local) null, local, localArr);
    }

    public <T> Local<T> newLocal(TypeId<T> typeId) {
        if (this.d) {
            throw new IllegalStateException("Cannot allocate locals after adding instructions");
        }
        Local<T> a2 = Local.a(this, typeId);
        this.g.add(a2);
        return a2;
    }

    public <T1, T2> void op(BinaryOp binaryOp, Local<T1> local, Local<T1> local2, Local<T2> local3) {
        p rop = binaryOp.rop(b.a(local2.a.b, local3.a.b));
        n a2 = n.a(local2.b(), local3.b());
        if (rop.b() == 1) {
            a((f) new k(rop, this.h, local.b(), a2));
            return;
        }
        a((f) new u(rop, this.h, a2, this.k));
        a((Local<?>) local, true);
    }

    public <T> void op(UnaryOp unaryOp, Local<T> local, Local<T> local2) {
        a((f) new k(unaryOp.rop(local2.a), this.h, local.b(), local2.b()));
    }

    public Label removeCatchClause(TypeId<? extends Throwable> typeId) {
        int indexOf = this.i.indexOf(typeId);
        if (indexOf == -1) {
            throw new IllegalArgumentException("No catch clause: " + typeId);
        }
        this.i.remove(indexOf);
        this.k = a(this.i);
        return this.j.remove(indexOf);
    }

    public void returnValue(Local<?> local) {
        if (!local.a.equals(this.a.b)) {
            throw new IllegalArgumentException("declared " + this.a.b + " but returned " + local.a);
        }
        a((f) new k(r.j((d) local.a.b), this.h, (m) null, n.a(local.b())));
    }

    public void returnVoid() {
        if (!this.a.b.equals(TypeId.VOID)) {
            throw new IllegalArgumentException("declared " + this.a.b + " but returned void");
        }
        a((f) new k(r.bw, this.h, (m) null, n.a));
    }

    public <V> void sget(FieldId<?, V> fieldId, Local<V> local) {
        a((f) new t(r.p((d) local.a.b), this.h, n.a, this.k, fieldId.e));
        a((Local<?>) local, true);
    }

    public <V> void sput(FieldId<?, V> fieldId, Local<V> local) {
        a((f) new t(r.q((d) local.a.b), this.h, n.a(local.b()), this.k, fieldId.e));
    }

    public void throwValue(Local<? extends Throwable> local) {
        a((f) new u(r.bD, this.h, n.a(local.b()), this.k));
    }
}
