package com.androlua;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.ArrayListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import java.io.IOException;
import java.util.HashMap;

public class LuaArrayAdapter extends ArrayListAdapter {
    /* access modifiers changed from: private */
    public Resources a;
    private LuaContext b;
    private LuaState c;
    private LuaObject d;
    private LuaObject e;
    private Animation f;
    /* access modifiers changed from: private */
    public Handler g;
    /* access modifiers changed from: private */
    public HashMap<String, Boolean> h;

    private class AsyncLoader extends Thread {
        private String b;
        private LuaContext c;

        private AsyncLoader() {
        }

        public Drawable getBitmap(LuaContext luaContext, String str) {
            this.c = luaContext;
            this.b = str;
            if (!str.toLowerCase().startsWith("http://") && !str.toLowerCase().startsWith("https://")) {
                return new BitmapDrawable(LuaArrayAdapter.this.a, LuaBitmap.getBitmap(luaContext, str));
            }
            if (LuaBitmap.checkCache(luaContext, str)) {
                return new BitmapDrawable(LuaArrayAdapter.this.a, LuaBitmap.getBitmap(luaContext, str));
            }
            if (!LuaArrayAdapter.this.h.containsKey(this.b)) {
                start();
                LuaArrayAdapter.this.h.put(this.b, true);
            }
            return new LoadingDrawable(this.c.getContext());
        }

        public void run() {
            try {
                LuaBitmap.getBitmap(this.c, this.b);
                LuaArrayAdapter.this.g.sendEmptyMessage(0);
            } catch (IOException e) {
                this.c.sendError("AsyncLoader", e);
            }
        }
    }

    public LuaArrayAdapter(LuaContext luaContext, LuaObject luaObject) {
        this(luaContext, luaObject, new String[0]);
    }

    public LuaArrayAdapter(LuaContext luaContext, LuaObject luaObject, Object[] objArr) {
        super(luaContext.getContext(), 0, (T[]) objArr);
        this.g = new Handler() {
            public void handleMessage(Message message) {
                LuaArrayAdapter.this.notifyDataSetChanged();
            }
        };
        this.h = new HashMap<>();
        this.b = luaContext;
        this.d = luaObject;
        this.a = this.b.getContext().getResources();
        this.c = luaContext.getLuaState();
        this.e = this.c.getLuaObject("loadlayout");
        this.c.newTable();
        this.e.call(this.d, this.c.getLuaObject(-1), AbsListView.class);
        this.c.pop(1);
    }

    private void a(View view, Object obj) {
        TextView textView;
        CharSequence obj2;
        if (view instanceof TextView) {
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
                ImageView imageView = (ImageView) view;
                Drawable drawable = null;
                if (obj instanceof Bitmap) {
                    drawable = new BitmapDrawable(this.a, (Bitmap) obj);
                } else if (obj instanceof String) {
                    drawable = new AsyncLoader().getBitmap(this.b, (String) obj);
                } else if (obj instanceof Drawable) {
                    drawable = (Drawable) obj;
                } else if (obj instanceof Number) {
                    drawable = this.a.getDrawable(((Number) obj).intValue());
                }
                imageView.setImageDrawable(drawable);
                if (drawable instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    if (imageView.getScaleType() == ImageView.ScaleType.FIT_XY) {
                        imageView.setLayoutParams(new ViewGroup.LayoutParams(this.b.getWidth(), (int) ((((float) this.b.getWidth()) * ((float) height)) / ((float) width))));
                    }
                }
            } catch (Exception e2) {
                Log.i("lua", e2.getMessage());
            }
        }
    }

    public Animation getAnimation() {
        return this.f;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            this.c.newTable();
            LuaObject luaObject = this.c.getLuaObject(-1);
            this.c.pop(1);
            try {
                view = (View) this.e.call(this.d, luaObject, AbsListView.class);
            } catch (LuaException unused) {
                return new View(this.b.getContext());
            }
        }
        a(view, getItem(i));
        if (this.f != null) {
            view.startAnimation(this.f);
        }
        return view;
    }

    public void setAnimation(Animation animation) {
        this.f = animation;
    }
}
