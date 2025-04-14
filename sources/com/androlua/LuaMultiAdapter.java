package com.androlua;

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
import android.widget.ImageView;
import android.widget.TextView;
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

public class LuaMultiAdapter extends BaseAdapter {
    /* access modifiers changed from: private */
    public Resources a;
    private LuaState b;
    private LuaContext c;
    private LuaTable<Integer, LuaTable> d;
    private LuaTable<Integer, LuaTable<String, Object>> e;
    private LuaTable<String, Object> f;
    private LuaFunction<View> g;
    private LuaFunction h;
    private LuaFunction i;
    private LuaTable<Integer, LuaFunction<Animation>> j;
    private HashMap<View, Animation> k;
    private HashMap<View, Boolean> l;
    private boolean m;
    /* access modifiers changed from: private */
    public boolean n;
    /* access modifiers changed from: private */
    public Handler o;
    /* access modifiers changed from: private */
    public HashMap<String, Boolean> p;

    private class AsyncLoader extends Thread {
        private String b;
        private LuaContext c;

        private AsyncLoader() {
        }

        public Drawable getBitmap(LuaContext luaContext, String str) {
            this.c = luaContext;
            this.b = str;
            if (!str.toLowerCase().startsWith("http://") && !str.toLowerCase().startsWith("https://")) {
                return new BitmapDrawable(LuaMultiAdapter.this.a, LuaBitmap.getBitmap(luaContext, str));
            }
            if (LuaBitmap.checkCache(luaContext, str)) {
                return new BitmapDrawable(LuaMultiAdapter.this.a, LuaBitmap.getBitmap(luaContext, str));
            }
            if (!LuaMultiAdapter.this.p.containsKey(this.b)) {
                start();
                LuaMultiAdapter.this.p.put(this.b, true);
            }
            return new LoadingDrawable(this.c.getContext());
        }

        public void run() {
            try {
                LuaBitmap.getBitmap(this.c, this.b);
                LuaMultiAdapter.this.o.sendEmptyMessage(0);
            } catch (IOException e) {
                this.c.sendError("AsyncLoader", e);
            }
        }
    }

    public LuaMultiAdapter(LuaContext luaContext, LuaTable luaTable) {
        this(luaContext, (LuaTable<Integer, LuaTable<String, Object>>) null, luaTable);
    }

    public LuaMultiAdapter(LuaContext luaContext, LuaTable<Integer, LuaTable<String, Object>> luaTable, LuaTable<Integer, LuaTable> luaTable2) {
        this.k = new HashMap<>();
        this.l = new HashMap<>();
        this.m = true;
        this.o = new Handler() {
            public void handleMessage(Message message) {
                LuaMultiAdapter.this.notifyDataSetChanged();
            }
        };
        this.p = new HashMap<>();
        this.c = luaContext;
        this.d = luaTable2;
        this.a = this.c.getContext().getResources();
        this.b = luaContext.getLuaState();
        this.e = luaTable == null ? new LuaTable<>(this.b) : luaTable;
        this.g = this.b.getLuaObject("loadlayout").getFunction();
        this.h = this.b.getLuaObject("table").getField("insert").getFunction();
        this.i = this.b.getLuaObject("table").getField("remove").getFunction();
        int length = this.d.length();
        for (int i2 = 1; i2 <= length; i2++) {
            this.b.newTable();
            this.g.call(this.d.get(Integer.valueOf(i2)), this.b.getLuaObject(-1), AbsListView.class);
            this.b.pop(1);
        }
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
                    drawable = new AsyncLoader().getBitmap(this.c, (String) obj);
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
            this.c.sendError("setHelper", e2);
        }
    }

    private int b(Object obj, String str, Object obj2) {
        Iterator<Method> it = LuaJavaAPI.getMethod(obj.getClass(), "setOn" + str.substring(2) + "Listener", false).iterator();
        while (it.hasNext()) {
            Method next = it.next();
            Class[] parameterTypes = next.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].isInterface()) {
                this.b.newTable();
                this.b.pushObjectValue(obj2);
                this.b.setField(-2, str);
                try {
                    next.invoke(obj, new Object[]{this.b.getLuaObject(-1).createProxy(parameterTypes[0])});
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
        this.h.call(this.e, luaTable);
        if (this.m) {
            notifyDataSetChanged();
        }
    }

    public void addAll(LuaTable<Integer, LuaTable<String, Object>> luaTable) {
        int length = luaTable.length();
        for (int i2 = 1; i2 <= length; i2++) {
            this.h.call(this.e, luaTable.get(Integer.valueOf(i2)));
        }
        if (this.m) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.e.clear();
        if (this.m) {
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return this.e.length();
    }

    public LuaTable<Integer, LuaTable<String, Object>> getData() {
        return this.e;
    }

    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        return getView(i2, view, viewGroup);
    }

    public Object getItem(int i2) {
        return this.e.get(Integer.valueOf(i2 + 1));
    }

    public long getItemId(int i2) {
        return (long) (i2 + 1);
    }

    public int getItemViewType(int i2) {
        int intValue = ((Long) this.e.get(Integer.valueOf(i2 + 1)).get("__type")).intValue() - 1;
        if (intValue < 0) {
            return 0;
        }
        return intValue;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: com.luajava.LuaObject} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x013e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r10, android.view.View r11, android.view.ViewGroup r12) {
        /*
            r9 = this;
            com.luajava.LuaTable<java.lang.Integer, com.luajava.LuaTable<java.lang.String, java.lang.Object>> r12 = r9.e
            int r0 = r10 + 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            java.lang.Object r12 = r12.get(r1)
            com.luajava.LuaTable r12 = (com.luajava.LuaTable) r12
            java.lang.String r1 = "__type"
            java.lang.Object r12 = r12.get(r1)
            java.lang.Long r12 = (java.lang.Long) r12
            int r12 = r12.intValue()
            r1 = 1
            if (r12 >= r1) goto L_0x001e
            r12 = 1
        L_0x001e:
            r2 = 0
            if (r11 != 0) goto L_0x0062
            com.luajava.LuaTable<java.lang.Integer, com.luajava.LuaTable> r3 = r9.d     // Catch:{ LuaException -> 0x0056 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r12)     // Catch:{ LuaException -> 0x0056 }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ LuaException -> 0x0056 }
            com.luajava.LuaTable r3 = (com.luajava.LuaTable) r3     // Catch:{ LuaException -> 0x0056 }
            com.luajava.LuaState r4 = r9.b     // Catch:{ LuaException -> 0x0056 }
            r4.newTable()     // Catch:{ LuaException -> 0x0056 }
            com.luajava.LuaState r4 = r9.b     // Catch:{ LuaException -> 0x0056 }
            r5 = -1
            com.luajava.LuaObject r4 = r4.getLuaObject((int) r5)     // Catch:{ LuaException -> 0x0056 }
            com.luajava.LuaState r5 = r9.b     // Catch:{ LuaException -> 0x0056 }
            r5.pop(r1)     // Catch:{ LuaException -> 0x0056 }
            com.luajava.LuaFunction<android.view.View> r5 = r9.g     // Catch:{ LuaException -> 0x0056 }
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ LuaException -> 0x0056 }
            r6[r2] = r3     // Catch:{ LuaException -> 0x0056 }
            r6[r1] = r4     // Catch:{ LuaException -> 0x0056 }
            r3 = 2
            java.lang.Class<android.widget.AbsListView> r7 = android.widget.AbsListView.class
            r6[r3] = r7     // Catch:{ LuaException -> 0x0056 }
            java.lang.Object r3 = r5.call(r6)     // Catch:{ LuaException -> 0x0056 }
            android.view.View r3 = (android.view.View) r3     // Catch:{ LuaException -> 0x0056 }
            r3.setTag(r4)     // Catch:{ LuaException -> 0x0056 }
            goto L_0x006a
        L_0x0056:
            android.view.View r10 = new android.view.View
            com.androlua.LuaContext r11 = r9.c
            android.content.Context r11 = r11.getContext()
            r10.<init>(r11)
            return r10
        L_0x0062:
            java.lang.Object r3 = r11.getTag()
            r4 = r3
            com.luajava.LuaObject r4 = (com.luajava.LuaObject) r4
            r3 = r11
        L_0x006a:
            com.luajava.LuaTable<java.lang.Integer, com.luajava.LuaTable<java.lang.String, java.lang.Object>> r5 = r9.e
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object r0 = r5.get(r0)
            com.luajava.LuaTable r0 = (com.luajava.LuaTable) r0
            if (r0 != 0) goto L_0x008f
            java.lang.String r11 = "lua"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r10)
            java.lang.String r10 = " is null"
            r12.append(r10)
            java.lang.String r10 = r12.toString()
            android.util.Log.i(r11, r10)
            return r3
        L_0x008f:
            java.util.HashMap<android.view.View, java.lang.Boolean> r10 = r9.l
            java.lang.Object r10 = r10.get(r3)
            if (r10 != 0) goto L_0x0099
            r10 = 1
            goto L_0x009a
        L_0x0099:
            r10 = 0
        L_0x009a:
            if (r10 == 0) goto L_0x00a5
            java.util.HashMap<android.view.View, java.lang.Boolean> r5 = r9.l
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r5.put(r3, r1)
        L_0x00a5:
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x00ad:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0100
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r5 = r1.getKey()     // Catch:{ Exception -> 0x00f5 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x00f5 }
            java.lang.String r6 = "type"
            boolean r6 = r5.equals(r6)     // Catch:{ Exception -> 0x00f5 }
            if (r6 == 0) goto L_0x00c8
            goto L_0x00ad
        L_0x00c8:
            java.lang.Object r1 = r1.getValue()     // Catch:{ Exception -> 0x00f5 }
            com.luajava.LuaObject r6 = r4.getField(r5)     // Catch:{ Exception -> 0x00f5 }
            boolean r7 = r6.isJavaObject()     // Catch:{ Exception -> 0x00f5 }
            if (r7 == 0) goto L_0x00ad
            com.luajava.LuaTable<java.lang.String, java.lang.Object> r7 = r9.f     // Catch:{ Exception -> 0x00f5 }
            if (r7 == 0) goto L_0x00eb
            if (r10 == 0) goto L_0x00eb
            java.lang.Object r7 = r6.getObject()     // Catch:{ Exception -> 0x00f5 }
            android.view.View r7 = (android.view.View) r7     // Catch:{ Exception -> 0x00f5 }
            com.luajava.LuaTable<java.lang.String, java.lang.Object> r8 = r9.f     // Catch:{ Exception -> 0x00f5 }
            java.lang.Object r5 = r8.get(r5)     // Catch:{ Exception -> 0x00f5 }
            r9.a((android.view.View) r7, (java.lang.Object) r5)     // Catch:{ Exception -> 0x00f5 }
        L_0x00eb:
            java.lang.Object r5 = r6.getObject()     // Catch:{ Exception -> 0x00f5 }
            android.view.View r5 = (android.view.View) r5     // Catch:{ Exception -> 0x00f5 }
            r9.a((android.view.View) r5, (java.lang.Object) r1)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00ad
        L_0x00f5:
            r1 = move-exception
            java.lang.String r5 = "lua"
            java.lang.String r1 = r1.getMessage()
            android.util.Log.i(r5, r1)
            goto L_0x00ad
        L_0x0100:
            boolean r10 = r9.n
            if (r10 == 0) goto L_0x0105
            return r3
        L_0x0105:
            com.luajava.LuaTable<java.lang.Integer, com.luajava.LuaFunction<android.view.animation.Animation>> r10 = r9.j
            if (r10 == 0) goto L_0x0144
            if (r11 == 0) goto L_0x0144
            java.util.HashMap<android.view.View, android.view.animation.Animation> r10 = r9.k
            java.lang.Object r10 = r10.get(r11)
            android.view.animation.Animation r10 = (android.view.animation.Animation) r10
            if (r10 != 0) goto L_0x013c
            com.luajava.LuaTable<java.lang.Integer, com.luajava.LuaFunction<android.view.animation.Animation>> r0 = r9.j     // Catch:{ Exception -> 0x0134 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r12 = r0.get(r12)     // Catch:{ Exception -> 0x0134 }
            com.luajava.LuaFunction r12 = (com.luajava.LuaFunction) r12     // Catch:{ Exception -> 0x0134 }
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r12 = r12.call(r0)     // Catch:{ Exception -> 0x0134 }
            android.view.animation.Animation r12 = (android.view.animation.Animation) r12     // Catch:{ Exception -> 0x0134 }
            java.util.HashMap<android.view.View, android.view.animation.Animation> r10 = r9.k     // Catch:{ Exception -> 0x0130 }
            r10.put(r11, r12)     // Catch:{ Exception -> 0x0130 }
            r10 = r12
            goto L_0x013c
        L_0x0130:
            r10 = move-exception
            r11 = r10
            r10 = r12
            goto L_0x0135
        L_0x0134:
            r11 = move-exception
        L_0x0135:
            com.androlua.LuaContext r12 = r9.c
            java.lang.String r0 = "setAnimation"
            r12.sendError(r0, r11)
        L_0x013c:
            if (r10 == 0) goto L_0x0144
            r3.clearAnimation()
            r3.startAnimation(r10)
        L_0x0144:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaMultiAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public int getViewTypeCount() {
        return this.d.length();
    }

    public void insert(int i2, LuaTable<String, Object> luaTable) {
        this.h.call(this.e, Integer.valueOf(i2 + 1), luaTable);
        if (this.m) {
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!this.n) {
            this.n = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    boolean unused = LuaMultiAdapter.this.n = false;
                }
            }, 500);
        }
    }

    public void remove(int i2) {
        this.i.call(this.e, Integer.valueOf(i2 + 1));
        if (this.m) {
            notifyDataSetChanged();
        }
    }

    public void setAnimation(LuaTable<Integer, LuaFunction<Animation>> luaTable) {
        setAnimationUtil(luaTable);
    }

    public void setAnimationUtil(LuaTable<Integer, LuaFunction<Animation>> luaTable) {
        this.k.clear();
        this.j = luaTable;
    }

    public void setNotifyOnChange(boolean z) {
        this.m = z;
        if (this.m) {
            notifyDataSetChanged();
        }
    }

    public void setStyle(LuaTable<String, Object> luaTable) {
        this.l.clear();
        this.f = luaTable;
    }
}
