package com.androlua;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.a.a.a.a.a.a.a;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaJavaAPI;
import com.luajava.LuaState;
import com.luajava.LuaTable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LuaAdapter extends BaseAdapter implements Filterable {
    /* access modifiers changed from: private */
    public final LuaTable<Integer, LuaTable<String, Object>> a;
    /* access modifiers changed from: private */
    public Resources b;
    private LuaState c;
    /* access modifiers changed from: private */
    public LuaContext d;
    private final Object e;
    private LuaTable f;
    /* access modifiers changed from: private */
    public LuaTable<Integer, LuaTable<String, Object>> g;
    private LuaTable<String, Object> h;
    /* access modifiers changed from: private */
    public CharSequence i;
    private LuaFunction<View> j;
    private LuaFunction k;
    private LuaFunction l;
    private LuaFunction<Animation> m;
    private HashMap<View, Animation> n;
    private HashMap<View, Boolean> o;
    private boolean p;
    /* access modifiers changed from: private */

    /* renamed from: q  reason: collision with root package name */
    public boolean f22q;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler r;
    /* access modifiers changed from: private */
    public HashMap<String, Boolean> s;
    private ArrayFilter t;
    /* access modifiers changed from: private */
    public LuaFunction u;

    private class ArrayFilter extends Filter {
        private ArrayFilter() {
        }

        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            CharSequence unused = LuaAdapter.this.i = charSequence;
            if (LuaAdapter.this.g == null) {
                return filterResults;
            }
            if (LuaAdapter.this.u != null) {
                LuaAdapter.this.r.sendEmptyMessage(1);
                return null;
            }
            filterResults.values = LuaAdapter.this.g;
            filterResults.count = LuaAdapter.this.g.size();
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        }
    }

    private class AsyncLoader extends Thread {
        private String b;
        private LuaContext c;

        private AsyncLoader() {
        }

        public Drawable getBitmap(LuaContext luaContext, String str) {
            this.c = luaContext;
            this.b = str;
            if (!str.toLowerCase().startsWith("http://") && !str.toLowerCase().startsWith("https://")) {
                return new BitmapDrawable(LuaAdapter.this.b, LuaBitmap.getBitmap(luaContext, str));
            }
            if (LuaBitmap.checkCache(luaContext, str)) {
                return new BitmapDrawable(LuaAdapter.this.b, LuaBitmap.getBitmap(luaContext, str));
            }
            if (!LuaAdapter.this.s.containsKey(this.b)) {
                start();
                LuaAdapter.this.s.put(this.b, true);
            }
            return new LoadingDrawable(this.c.getContext());
        }

        public void run() {
            try {
                LuaBitmap.getBitmap(this.c, this.b);
                LuaAdapter.this.r.sendEmptyMessage(0);
            } catch (IOException e) {
                this.c.sendError("AsyncLoader", e);
            }
        }
    }

    public LuaAdapter(LuaContext luaContext, LuaTable luaTable) {
        this(luaContext, (LuaTable<Integer, LuaTable<String, Object>>) null, luaTable);
    }

    public LuaAdapter(LuaContext luaContext, LuaTable<Integer, LuaTable<String, Object>> luaTable, LuaTable luaTable2) {
        this.e = new Object();
        this.n = new HashMap<>();
        this.o = new HashMap<>();
        this.p = true;
        this.r = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    LuaAdapter.this.notifyDataSetChanged();
                    return;
                }
                try {
                    LuaTable luaTable = new LuaTable(LuaAdapter.this.g.getLuaState());
                    LuaAdapter.this.u.call(LuaAdapter.this.a, luaTable, LuaAdapter.this.i);
                    LuaTable unused = LuaAdapter.this.g = luaTable;
                    LuaAdapter.this.notifyDataSetChanged();
                } catch (LuaException e) {
                    a.a(e);
                    LuaAdapter.this.d.sendError("performFiltering", e);
                }
            }
        };
        this.s = new HashMap<>();
        this.d = luaContext;
        this.f = luaTable2;
        this.b = this.d.getContext().getResources();
        this.c = luaContext.getLuaState();
        this.g = luaTable == null ? new LuaTable<>(this.c) : luaTable;
        this.a = this.g;
        this.j = this.c.getLuaObject("loadlayout").getFunction();
        this.k = this.c.getLuaObject("table").getField("insert").getFunction();
        this.l = this.c.getLuaObject("table").getField("remove").getFunction();
        this.c.newTable();
        this.j.call(this.f, this.c.getLuaObject(-1), AbsListView.class);
        this.c.pop(1);
    }

    private int a(Object obj, String str, Object obj2) {
        return (str.length() <= 2 || !str.substring(0, 2).equals("on") || !(obj2 instanceof LuaFunction)) ? c(obj, str, obj2) : b(obj, str, obj2);
    }

    private void a(View view, LuaTable<String, Object> luaTable) {
        for (Map.Entry next : luaTable.entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            if (str.toLowerCase().equals("src")) {
                a(view, value);
            } else {
                a(view, str, value);
            }
        }
    }

    private void a(View view, Object obj) {
        ImageView imageView;
        Drawable drawable;
        TextView textView;
        CharSequence obj2;
        try {
            if (obj instanceof LuaTable) {
                a(view, (LuaTable<String, Object>) (LuaTable) obj);
            } else if (view instanceof TextView) {
                if (obj instanceof CharSequence) {
                    textView = (TextView) view;
                    obj2 = (CharSequence) obj;
                } else {
                    textView = (TextView) view;
                    obj2 = obj.toString();
                }
                textView.setText(obj2);
            } else if (!(view instanceof ImageView)) {
            } else {
                if (obj instanceof Bitmap) {
                    ((ImageView) view).setImageBitmap((Bitmap) obj);
                    return;
                }
                if (obj instanceof String) {
                    imageView = (ImageView) view;
                    drawable = new AsyncLoader().getBitmap(this.d, (String) obj);
                } else if (obj instanceof Drawable) {
                    imageView = (ImageView) view;
                    drawable = (Drawable) obj;
                } else if (obj instanceof Number) {
                    ((ImageView) view).setImageResource(((Number) obj).intValue());
                    return;
                } else {
                    return;
                }
                imageView.setImageDrawable(drawable);
            }
        } catch (Exception e2) {
            this.d.sendError("setHelper", e2);
        }
    }

    private int b(Object obj, String str, Object obj2) {
        Iterator<Method> it = LuaJavaAPI.getMethod(obj.getClass(), "setOn" + str.substring(2) + "Listener", false).iterator();
        while (it.hasNext()) {
            Method next = it.next();
            Class[] parameterTypes = next.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].isInterface()) {
                this.c.newTable();
                this.c.pushObjectValue(obj2);
                this.c.setField(-2, str);
                try {
                    next.invoke(obj, new Object[]{this.c.getLuaObject(-1).createProxy(parameterTypes[0])});
                    return 1;
                } catch (Exception e2) {
                    throw new LuaException(e2);
                }
            }
        }
        return 0;
    }

    private int c(Object obj, String str, Object obj2) {
        Object[] objArr;
        if (Character.isLowerCase(str.charAt(0))) {
            str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
        Class<?> cls = obj2.getClass();
        StringBuilder sb = new StringBuilder();
        Iterator<Method> it = LuaJavaAPI.getMethod(obj.getClass(), "set" + str, false).iterator();
        while (it.hasNext()) {
            Method next = it.next();
            Class[] parameterTypes = next.getParameterTypes();
            if (parameterTypes.length == 1) {
                if (parameterTypes[0].isPrimitive()) {
                    try {
                        if (obj2 instanceof Double) {
                            objArr = new Object[]{LuaState.convertLuaNumber(Double.valueOf(((Number) obj2).doubleValue()), (Class<?>) parameterTypes[0])};
                        } else if (obj2 instanceof Float) {
                            objArr = new Object[]{LuaState.convertLuaNumber(Double.valueOf(((Number) obj2).doubleValue()), (Class<?>) parameterTypes[0])};
                        } else {
                            if (!(obj2 instanceof Long)) {
                                if (!(obj2 instanceof Integer)) {
                                    if (obj2 instanceof Boolean) {
                                        next.invoke(obj, new Object[]{(Boolean) obj2});
                                        return 1;
                                    }
                                }
                            }
                            objArr = new Object[]{LuaState.convertLuaNumber(Long.valueOf(((Number) obj2).longValue()), (Class<?>) parameterTypes[0])};
                        }
                        next.invoke(obj, objArr);
                        return 1;
                    } catch (Exception e2) {
                        sb.append(e2.getMessage());
                        sb.append("\n");
                    }
                } else if (!parameterTypes[0].isAssignableFrom(cls)) {
                    continue;
                } else {
                    next.invoke(obj, new Object[]{obj2});
                    return 1;
                }
            }
        }
        if (sb.length() > 0) {
            throw new LuaException("Invalid setter " + str + ". Invalid Parameters.\n" + sb.toString() + cls.toString());
        }
        throw new LuaException("Invalid setter " + str + " is not a method.\n");
    }

    public void add(LuaTable<String, Object> luaTable) {
        this.k.call(this.a, luaTable);
        if (this.p) {
            notifyDataSetChanged();
        }
    }

    public void addAll(LuaTable<Integer, LuaTable<String, Object>> luaTable) {
        int length = luaTable.length();
        for (int i2 = 1; i2 <= length; i2++) {
            this.k.call(this.a, luaTable.get(Integer.valueOf(i2)));
        }
        if (this.p) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.a.clear();
        if (this.p) {
            notifyDataSetChanged();
        }
    }

    public void filter(CharSequence charSequence) {
        getFilter().filter(charSequence);
    }

    public int getCount() {
        return this.g.length();
    }

    public LuaTable<Integer, LuaTable<String, Object>> getData() {
        return this.g;
    }

    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        return getView(i2, view, viewGroup);
    }

    public Filter getFilter() {
        if (this.t == null) {
            this.t = new ArrayFilter();
        }
        return this.t;
    }

    public Object getItem(int i2) {
        return this.g.get(Integer.valueOf(i2 + 1));
    }

    public long getItemId(int i2) {
        return (long) (i2 + 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x0105  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r9, android.view.View r10, android.view.ViewGroup r11) {
        /*
            r8 = this;
            r11 = 0
            r0 = 1
            if (r10 != 0) goto L_0x003b
            com.luajava.LuaState r1 = r8.c     // Catch:{ LuaException -> 0x002f }
            r1.newTable()     // Catch:{ LuaException -> 0x002f }
            com.luajava.LuaState r1 = r8.c     // Catch:{ LuaException -> 0x002f }
            r2 = -1
            com.luajava.LuaObject r1 = r1.getLuaObject((int) r2)     // Catch:{ LuaException -> 0x002f }
            com.luajava.LuaState r2 = r8.c     // Catch:{ LuaException -> 0x002f }
            r2.pop(r0)     // Catch:{ LuaException -> 0x002f }
            com.luajava.LuaFunction<android.view.View> r2 = r8.j     // Catch:{ LuaException -> 0x002f }
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ LuaException -> 0x002f }
            com.luajava.LuaTable r4 = r8.f     // Catch:{ LuaException -> 0x002f }
            r3[r11] = r4     // Catch:{ LuaException -> 0x002f }
            r3[r0] = r1     // Catch:{ LuaException -> 0x002f }
            r4 = 2
            java.lang.Class<android.widget.AbsListView> r5 = android.widget.AbsListView.class
            r3[r4] = r5     // Catch:{ LuaException -> 0x002f }
            java.lang.Object r2 = r2.call(r3)     // Catch:{ LuaException -> 0x002f }
            android.view.View r2 = (android.view.View) r2     // Catch:{ LuaException -> 0x002f }
            r2.setTag(r1)     // Catch:{ LuaException -> 0x002f }
            goto L_0x0042
        L_0x002f:
            android.view.View r9 = new android.view.View
            com.androlua.LuaContext r10 = r8.d
            android.content.Context r10 = r10.getContext()
            r9.<init>(r10)
            return r9
        L_0x003b:
            java.lang.Object r1 = r10.getTag()
            com.luajava.LuaObject r1 = (com.luajava.LuaObject) r1
            r2 = r10
        L_0x0042:
            com.luajava.LuaTable<java.lang.Integer, com.luajava.LuaTable<java.lang.String, java.lang.Object>> r3 = r8.g
            int r4 = r9 + 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object r3 = r3.get(r4)
            com.luajava.LuaTable r3 = (com.luajava.LuaTable) r3
            if (r3 != 0) goto L_0x0069
            java.lang.String r10 = "lua"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r9)
            java.lang.String r9 = " is null"
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            android.util.Log.i(r10, r9)
            return r2
        L_0x0069:
            java.util.HashMap<android.view.View, java.lang.Boolean> r9 = r8.o
            java.lang.Object r9 = r9.get(r2)
            if (r9 != 0) goto L_0x0073
            r9 = 1
            goto L_0x0074
        L_0x0073:
            r9 = 0
        L_0x0074:
            if (r9 == 0) goto L_0x007f
            java.util.HashMap<android.view.View, java.lang.Boolean> r4 = r8.o
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r4.put(r2, r0)
        L_0x007f:
            java.util.Set r0 = r3.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0087:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00d1
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()     // Catch:{ Exception -> 0x00c6 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x00c6 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ Exception -> 0x00c6 }
            com.luajava.LuaObject r5 = r1.getField(r4)     // Catch:{ Exception -> 0x00c6 }
            boolean r6 = r5.isJavaObject()     // Catch:{ Exception -> 0x00c6 }
            if (r6 == 0) goto L_0x0087
            com.luajava.LuaTable<java.lang.String, java.lang.Object> r6 = r8.h     // Catch:{ Exception -> 0x00c6 }
            if (r6 == 0) goto L_0x00bc
            if (r9 == 0) goto L_0x00bc
            java.lang.Object r6 = r5.getObject()     // Catch:{ Exception -> 0x00c6 }
            android.view.View r6 = (android.view.View) r6     // Catch:{ Exception -> 0x00c6 }
            com.luajava.LuaTable<java.lang.String, java.lang.Object> r7 = r8.h     // Catch:{ Exception -> 0x00c6 }
            java.lang.Object r4 = r7.get(r4)     // Catch:{ Exception -> 0x00c6 }
            r8.a((android.view.View) r6, (java.lang.Object) r4)     // Catch:{ Exception -> 0x00c6 }
        L_0x00bc:
            java.lang.Object r4 = r5.getObject()     // Catch:{ Exception -> 0x00c6 }
            android.view.View r4 = (android.view.View) r4     // Catch:{ Exception -> 0x00c6 }
            r8.a((android.view.View) r4, (java.lang.Object) r3)     // Catch:{ Exception -> 0x00c6 }
            goto L_0x0087
        L_0x00c6:
            r3 = move-exception
            java.lang.String r4 = "lua"
            java.lang.String r3 = r3.getMessage()
            android.util.Log.i(r4, r3)
            goto L_0x0087
        L_0x00d1:
            boolean r9 = r8.f22q
            if (r9 == 0) goto L_0x00d6
            return r2
        L_0x00d6:
            com.luajava.LuaFunction<android.view.animation.Animation> r9 = r8.m
            if (r9 == 0) goto L_0x010b
            if (r10 == 0) goto L_0x010b
            java.util.HashMap<android.view.View, android.view.animation.Animation> r9 = r8.n
            java.lang.Object r9 = r9.get(r10)
            android.view.animation.Animation r9 = (android.view.animation.Animation) r9
            if (r9 != 0) goto L_0x0103
            com.luajava.LuaFunction<android.view.animation.Animation> r0 = r8.m     // Catch:{ Exception -> 0x00fb }
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ Exception -> 0x00fb }
            java.lang.Object r11 = r0.call(r11)     // Catch:{ Exception -> 0x00fb }
            android.view.animation.Animation r11 = (android.view.animation.Animation) r11     // Catch:{ Exception -> 0x00fb }
            java.util.HashMap<android.view.View, android.view.animation.Animation> r9 = r8.n     // Catch:{ Exception -> 0x00f7 }
            r9.put(r10, r11)     // Catch:{ Exception -> 0x00f7 }
            r9 = r11
            goto L_0x0103
        L_0x00f7:
            r9 = move-exception
            r10 = r9
            r9 = r11
            goto L_0x00fc
        L_0x00fb:
            r10 = move-exception
        L_0x00fc:
            com.androlua.LuaContext r11 = r8.d
            java.lang.String r0 = "setAnimation"
            r11.sendError(r0, r10)
        L_0x0103:
            if (r9 == 0) goto L_0x010b
            r2.clearAnimation()
            r2.startAnimation(r9)
        L_0x010b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public void insert(int i2, LuaTable<String, Object> luaTable) {
        this.k.call(this.a, Integer.valueOf(i2 + 1), luaTable);
        if (this.p) {
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!this.f22q) {
            this.f22q = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    boolean unused = LuaAdapter.this.f22q = false;
                }
            }, 500);
        }
    }

    public void remove(int i2) {
        this.l.call(this.a, Integer.valueOf(i2 + 1));
        if (this.p) {
            notifyDataSetChanged();
        }
    }

    public void setAnimation(LuaFunction<Animation> luaFunction) {
        setAnimationUtil(luaFunction);
    }

    public void setAnimationUtil(LuaFunction<Animation> luaFunction) {
        this.n.clear();
        this.m = luaFunction;
    }

    public void setFilter(LuaFunction luaFunction) {
        this.u = luaFunction;
    }

    public void setNotifyOnChange(boolean z) {
        this.p = z;
        if (this.p) {
            notifyDataSetChanged();
        }
    }

    public void setStyle(LuaTable<String, Object> luaTable) {
        this.o.clear();
        this.h = luaTable;
    }
}
