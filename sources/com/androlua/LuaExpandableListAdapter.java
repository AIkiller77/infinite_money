package com.androlua;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
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

public class LuaExpandableListAdapter extends BaseExpandableListAdapter {
    /* access modifiers changed from: private */
    public BitmapDrawable a;
    /* access modifiers changed from: private */
    public Resources b;
    private LuaState c;
    private LuaContext d;
    private LuaTable<Integer, LuaTable<String, Object>> e;
    private LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> f;
    private HashMap<View, Animation> g;
    private LuaTable h;
    private LuaTable i;
    private LuaFunction<View> j;
    /* access modifiers changed from: private */
    public LuaFunction<?> k;
    /* access modifiers changed from: private */
    public LuaFunction<?> l;
    private boolean m;
    private LuaFunction<Animation> n;
    /* access modifiers changed from: private */
    public boolean o;
    /* access modifiers changed from: private */
    public Handler p;
    /* access modifiers changed from: private */

    /* renamed from: q  reason: collision with root package name */
    public HashMap<String, Boolean> f24q;

    private class AsyncLoader extends Thread {
        private String b;
        private LuaContext c;

        private AsyncLoader() {
        }

        public Drawable getBitmap(LuaContext luaContext, String str) {
            this.c = luaContext;
            this.b = str;
            if (!str.toLowerCase().startsWith("http://") && !str.toLowerCase().startsWith("https://")) {
                return new BitmapDrawable(LuaExpandableListAdapter.this.b, LuaBitmap.getBitmap(luaContext, str));
            }
            if (LuaBitmap.checkCache(luaContext, str)) {
                return new BitmapDrawable(LuaExpandableListAdapter.this.b, LuaBitmap.getBitmap(luaContext, str));
            }
            if (!LuaExpandableListAdapter.this.f24q.containsKey(this.b)) {
                start();
                LuaExpandableListAdapter.this.f24q.put(this.b, true);
            }
            return LuaExpandableListAdapter.this.a;
        }

        public void run() {
            try {
                LuaBitmap.getBitmap(this.c, this.b);
                LuaExpandableListAdapter.this.p.sendEmptyMessage(0);
            } catch (IOException e) {
                this.c.sendError("AsyncLoader", e);
            }
        }
    }

    private class GroupItem {
        private LuaTable<Integer, LuaTable<String, Object>> b;

        public GroupItem(LuaTable<Integer, LuaTable<String, Object>> luaTable) {
            this.b = luaTable;
        }

        public void add(LuaTable<String, Object> luaTable) {
            this.b.put(Integer.valueOf(this.b.length() + 1), luaTable);
            if (LuaExpandableListAdapter.this.o) {
                LuaExpandableListAdapter.this.notifyDataSetChanged();
            }
        }

        public void clear() {
            this.b.clear();
            if (LuaExpandableListAdapter.this.o) {
                LuaExpandableListAdapter.this.notifyDataSetChanged();
            }
        }

        public LuaTable<Integer, LuaTable<String, Object>> getData() {
            return this.b;
        }

        public void insert(int i, LuaTable<String, Object> luaTable) {
            LuaExpandableListAdapter.this.k.call(this.b, Integer.valueOf(i + 1), luaTable);
            if (LuaExpandableListAdapter.this.o) {
                LuaExpandableListAdapter.this.notifyDataSetChanged();
            }
        }

        public void remove(int i) {
            LuaExpandableListAdapter.this.l.call(this.b, Integer.valueOf(i + 1));
            if (LuaExpandableListAdapter.this.o) {
                LuaExpandableListAdapter.this.notifyDataSetChanged();
            }
        }
    }

    public LuaExpandableListAdapter(LuaContext luaContext, LuaTable luaTable, LuaTable luaTable2) {
        this(luaContext, (LuaTable<Integer, LuaTable<String, Object>>) null, (LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>>) null, luaTable, luaTable2);
    }

    public LuaExpandableListAdapter(LuaContext luaContext, LuaTable<Integer, LuaTable<String, Object>> luaTable, LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> luaTable2, LuaTable luaTable3, LuaTable luaTable4) {
        this.g = new HashMap<>();
        this.p = new Handler() {
            public void handleMessage(Message message) {
                LuaExpandableListAdapter.this.notifyDataSetChanged();
            }
        };
        this.f24q = new HashMap<>();
        this.d = luaContext;
        this.c = luaContext.getLuaState();
        this.b = this.d.getContext().getResources();
        this.a = new BitmapDrawable(this.b, getClass().getResourceAsStream("/res/drawable/icon.png"));
        this.a.setColorFilter(-1996488705, PorterDuff.Mode.SRC_ATOP);
        this.h = luaTable3;
        this.i = luaTable4;
        luaTable = luaTable == null ? new LuaTable<>(this.c) : luaTable;
        luaTable2 = luaTable2 == null ? new LuaTable<>(this.c) : luaTable2;
        this.e = luaTable;
        this.f = luaTable2;
        this.j = this.c.getLuaObject("loadlayout").getFunction();
        this.k = this.c.getLuaObject("table").getField("insert").getFunction();
        this.l = this.c.getLuaObject("table").getField("remove").getFunction();
        this.c.newTable();
        this.j.call(this.h, this.c.getLuaObject(-1), AbsListView.class);
        this.j.call(this.i, this.c.getLuaObject(-1), AbsListView.class);
        this.c.pop(1);
    }

    private int a(Object obj, String str, Object obj2) {
        return (str.length() <= 2 || !str.substring(0, 2).equals("on") || !(obj2 instanceof LuaFunction)) ? c(obj, str, obj2) : b(obj, str, obj2);
    }

    private void a(View view, LuaTable<String, Object> luaTable) {
        for (Map.Entry next : luaTable.entrySet()) {
            try {
                String str = (String) next.getKey();
                Object value = next.getValue();
                if (str.toLowerCase().equals("src")) {
                    a(view, value);
                } else {
                    a(view, str, value);
                }
            } catch (Exception e2) {
                Log.i("lua", e2.getMessage());
            }
        }
    }

    private void a(View view, Object obj) {
        ImageView imageView;
        Drawable drawable;
        TextView textView;
        CharSequence obj2;
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
        } else if (view instanceof ImageView) {
            try {
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
            } catch (Exception e2) {
                Log.i("lua", e2.getMessage());
            }
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

    public GroupItem add(LuaTable<String, Object> luaTable) {
        this.e.put(Integer.valueOf(this.e.length() + 1), luaTable);
        LuaTable luaTable2 = new LuaTable(this.c);
        this.f.put(Integer.valueOf(this.e.length()), luaTable2);
        if (this.o) {
            notifyDataSetChanged();
        }
        return new GroupItem(luaTable2);
    }

    public GroupItem add(LuaTable<String, Object> luaTable, LuaTable<Integer, LuaTable<String, Object>> luaTable2) {
        this.e.put(Integer.valueOf(this.e.length() + 1), luaTable);
        this.f.put(Integer.valueOf(this.e.length()), luaTable2);
        if (this.o) {
            notifyDataSetChanged();
        }
        return new GroupItem(luaTable2);
    }

    public void clear() {
        this.e.clear();
        this.f.clear();
        if (this.o) {
            notifyDataSetChanged();
        }
    }

    public Object getChild(int i2, int i3) {
        return this.f.get(Integer.valueOf(i2 + 1)).get(Integer.valueOf(i3 + 1));
    }

    public LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> getChildData() {
        return this.f;
    }

    public long getChildId(int i2, int i3) {
        return (long) (i3 + 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getChildView(int r7, int r8, boolean r9, android.view.View r10, android.view.ViewGroup r11) {
        /*
            r6 = this;
            r9 = 0
            r11 = 1
            if (r10 != 0) goto L_0x0031
            com.luajava.LuaTable r0 = new com.luajava.LuaTable     // Catch:{ LuaException -> 0x0025 }
            com.luajava.LuaState r1 = r6.c     // Catch:{ LuaException -> 0x0025 }
            r0.<init>(r1)     // Catch:{ LuaException -> 0x0025 }
            com.luajava.LuaFunction<android.view.View> r1 = r6.j     // Catch:{ LuaException -> 0x0025 }
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ LuaException -> 0x0025 }
            com.luajava.LuaTable r3 = r6.i     // Catch:{ LuaException -> 0x0025 }
            r2[r9] = r3     // Catch:{ LuaException -> 0x0025 }
            r2[r11] = r0     // Catch:{ LuaException -> 0x0025 }
            r3 = 2
            java.lang.Class<android.widget.AbsListView> r4 = android.widget.AbsListView.class
            r2[r3] = r4     // Catch:{ LuaException -> 0x0025 }
            java.lang.Object r1 = r1.call(r2)     // Catch:{ LuaException -> 0x0025 }
            android.view.View r1 = (android.view.View) r1     // Catch:{ LuaException -> 0x0025 }
            r1.setTag(r0)     // Catch:{ LuaException -> 0x0025 }
            goto L_0x0038
        L_0x0025:
            android.view.View r7 = new android.view.View
            com.androlua.LuaContext r8 = r6.d
            android.content.Context r8 = r8.getContext()
            r7.<init>(r8)
            return r7
        L_0x0031:
            java.lang.Object r0 = r10.getTag()
            com.luajava.LuaTable r0 = (com.luajava.LuaTable) r0
            r1 = r10
        L_0x0038:
            com.luajava.LuaTable<java.lang.Integer, com.luajava.LuaTable<java.lang.Integer, com.luajava.LuaTable<java.lang.String, java.lang.Object>>> r2 = r6.f
            int r7 = r7 + r11
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r7 = r2.get(r7)
            com.luajava.LuaTable r7 = (com.luajava.LuaTable) r7
            int r11 = r8 + 1
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object r7 = r7.get(r11)
            com.luajava.LuaTable r7 = (com.luajava.LuaTable) r7
            if (r7 != 0) goto L_0x006a
            java.lang.String r7 = "lua"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r8)
            java.lang.String r8 = " is null"
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            android.util.Log.i(r7, r8)
            return r1
        L_0x006a:
            java.util.Set r7 = r7.entrySet()
            java.util.Iterator r7 = r7.iterator()
        L_0x0072:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x009f
            java.lang.Object r8 = r7.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            java.lang.Object r11 = r8.getKey()     // Catch:{ Exception -> 0x0094 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ Exception -> 0x0094 }
            java.lang.Object r8 = r8.getValue()     // Catch:{ Exception -> 0x0094 }
            java.lang.Object r11 = r0.get(r11)     // Catch:{ Exception -> 0x0094 }
            android.view.View r11 = (android.view.View) r11     // Catch:{ Exception -> 0x0094 }
            if (r11 == 0) goto L_0x0072
            r6.a((android.view.View) r11, (java.lang.Object) r8)     // Catch:{ Exception -> 0x0094 }
            goto L_0x0072
        L_0x0094:
            r8 = move-exception
            java.lang.String r11 = "lua"
            java.lang.String r8 = r8.getMessage()
            android.util.Log.i(r11, r8)
            goto L_0x0072
        L_0x009f:
            boolean r7 = r6.m
            if (r7 == 0) goto L_0x00a4
            return r1
        L_0x00a4:
            com.luajava.LuaFunction<android.view.animation.Animation> r7 = r6.n
            if (r7 == 0) goto L_0x00dc
            if (r10 == 0) goto L_0x00dc
            java.util.HashMap<android.view.View, android.view.animation.Animation> r7 = r6.g
            java.lang.Object r7 = r7.get(r10)
            android.view.animation.Animation r7 = (android.view.animation.Animation) r7
            if (r7 != 0) goto L_0x00d4
            com.luajava.LuaFunction<android.view.animation.Animation> r8 = r6.n     // Catch:{ Exception -> 0x00ca }
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x00ca }
            java.lang.Object r8 = r8.call(r9)     // Catch:{ Exception -> 0x00ca }
            android.view.animation.Animation r8 = (android.view.animation.Animation) r8     // Catch:{ Exception -> 0x00ca }
            java.util.HashMap<android.view.View, android.view.animation.Animation> r7 = r6.g     // Catch:{ Exception -> 0x00c5 }
            r7.put(r10, r8)     // Catch:{ Exception -> 0x00c5 }
            r7 = r8
            goto L_0x00d4
        L_0x00c5:
            r7 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
            goto L_0x00cb
        L_0x00ca:
            r8 = move-exception
        L_0x00cb:
            java.lang.String r9 = "lua"
            java.lang.String r8 = r8.getMessage()
            android.util.Log.i(r9, r8)
        L_0x00d4:
            if (r7 == 0) goto L_0x00dc
            r1.clearAnimation()
            r1.startAnimation(r7)
        L_0x00dc:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaExpandableListAdapter.getChildView(int, int, boolean, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public int getChildrenCount(int i2) {
        return this.f.get(Integer.valueOf(i2 + 1)).length();
    }

    public Object getGroup(int i2) {
        return this.e.get(Integer.valueOf(i2 + 1));
    }

    public int getGroupCount() {
        return this.e.length();
    }

    public LuaTable<Integer, LuaTable<String, Object>> getGroupData() {
        return this.e;
    }

    public long getGroupId(int i2) {
        return (long) (i2 + 1);
    }

    public GroupItem getGroupItem(int i2) {
        return new GroupItem(this.f.get(Integer.valueOf(i2 + 1)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getGroupView(int r6, boolean r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            r5 = this;
            r7 = 0
            if (r8 != 0) goto L_0x0031
            com.luajava.LuaTable r9 = new com.luajava.LuaTable     // Catch:{ LuaException -> 0x0025 }
            com.luajava.LuaState r0 = r5.c     // Catch:{ LuaException -> 0x0025 }
            r9.<init>(r0)     // Catch:{ LuaException -> 0x0025 }
            com.luajava.LuaFunction<android.view.View> r0 = r5.j     // Catch:{ LuaException -> 0x0025 }
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ LuaException -> 0x0025 }
            com.luajava.LuaTable r2 = r5.h     // Catch:{ LuaException -> 0x0025 }
            r1[r7] = r2     // Catch:{ LuaException -> 0x0025 }
            r2 = 1
            r1[r2] = r9     // Catch:{ LuaException -> 0x0025 }
            r2 = 2
            java.lang.Class<android.widget.AbsListView> r3 = android.widget.AbsListView.class
            r1[r2] = r3     // Catch:{ LuaException -> 0x0025 }
            java.lang.Object r0 = r0.call(r1)     // Catch:{ LuaException -> 0x0025 }
            android.view.View r0 = (android.view.View) r0     // Catch:{ LuaException -> 0x0025 }
            r0.setTag(r9)     // Catch:{ LuaException -> 0x0025 }
            goto L_0x0038
        L_0x0025:
            android.view.View r6 = new android.view.View
            com.androlua.LuaContext r7 = r5.d
            android.content.Context r7 = r7.getContext()
            r6.<init>(r7)
            return r6
        L_0x0031:
            java.lang.Object r9 = r8.getTag()
            com.luajava.LuaTable r9 = (com.luajava.LuaTable) r9
            r0 = r8
        L_0x0038:
            com.luajava.LuaTable<java.lang.Integer, com.luajava.LuaTable<java.lang.String, java.lang.Object>> r1 = r5.e
            int r2 = r6 + 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r1 = r1.get(r2)
            com.luajava.LuaTable r1 = (com.luajava.LuaTable) r1
            if (r1 != 0) goto L_0x005f
            java.lang.String r7 = "lua"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r6)
            java.lang.String r6 = " is null"
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            android.util.Log.i(r7, r6)
            return r0
        L_0x005f:
            java.util.Set r6 = r1.entrySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0067:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x0094
            java.lang.Object r1 = r6.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()     // Catch:{ Exception -> 0x0089 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0089 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ Exception -> 0x0089 }
            java.lang.Object r2 = r9.get(r2)     // Catch:{ Exception -> 0x0089 }
            android.view.View r2 = (android.view.View) r2     // Catch:{ Exception -> 0x0089 }
            if (r2 == 0) goto L_0x0067
            r5.a((android.view.View) r2, (java.lang.Object) r1)     // Catch:{ Exception -> 0x0089 }
            goto L_0x0067
        L_0x0089:
            r1 = move-exception
            java.lang.String r2 = "lua"
            java.lang.String r1 = r1.getMessage()
            android.util.Log.i(r2, r1)
            goto L_0x0067
        L_0x0094:
            boolean r6 = r5.m
            if (r6 == 0) goto L_0x0099
            return r0
        L_0x0099:
            com.luajava.LuaFunction<android.view.animation.Animation> r6 = r5.n
            if (r6 == 0) goto L_0x00d1
            if (r8 == 0) goto L_0x00d1
            java.util.HashMap<android.view.View, android.view.animation.Animation> r6 = r5.g
            java.lang.Object r6 = r6.get(r8)
            android.view.animation.Animation r6 = (android.view.animation.Animation) r6
            if (r6 != 0) goto L_0x00c9
            com.luajava.LuaFunction<android.view.animation.Animation> r9 = r5.n     // Catch:{ Exception -> 0x00bf }
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x00bf }
            java.lang.Object r7 = r9.call(r7)     // Catch:{ Exception -> 0x00bf }
            android.view.animation.Animation r7 = (android.view.animation.Animation) r7     // Catch:{ Exception -> 0x00bf }
            java.util.HashMap<android.view.View, android.view.animation.Animation> r6 = r5.g     // Catch:{ Exception -> 0x00ba }
            r6.put(r8, r7)     // Catch:{ Exception -> 0x00ba }
            r6 = r7
            goto L_0x00c9
        L_0x00ba:
            r6 = move-exception
            r4 = r7
            r7 = r6
            r6 = r4
            goto L_0x00c0
        L_0x00bf:
            r7 = move-exception
        L_0x00c0:
            java.lang.String r8 = "lua"
            java.lang.String r7 = r7.getMessage()
            android.util.Log.i(r8, r7)
        L_0x00c9:
            if (r6 == 0) goto L_0x00d1
            r0.clearAnimation()
            r0.startAnimation(r6)
        L_0x00d1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaExpandableListAdapter.getGroupView(int, boolean, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public boolean hasStableIds() {
        return false;
    }

    public GroupItem insert(int i2, LuaTable<String, Object> luaTable, LuaTable<Integer, LuaTable<String, Object>> luaTable2) {
        int i3 = i2 + 1;
        this.k.call(this.e, Integer.valueOf(i3), luaTable);
        this.k.call(this.f, Integer.valueOf(i3), luaTable2);
        if (this.o) {
            notifyDataSetChanged();
        }
        return new GroupItem(luaTable2);
    }

    public boolean isChildSelectable(int i2, int i3) {
        return false;
    }

    public void remove(int i2) {
        this.l.call(this.e, Integer.valueOf(i2 + 1));
        if (this.o) {
            notifyDataSetChanged();
        }
    }

    public void setAnimationUtil(LuaFunction<Animation> luaFunction) {
        this.g.clear();
        this.n = luaFunction;
    }

    public void setNotifyOnChange(boolean z) {
        this.o = z;
        if (this.o) {
            notifyDataSetChanged();
        }
    }
}
