package com.androlua;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.luajava.LuaMetaTable;
import java.io.InputStream;
import java.util.HashMap;

@SuppressLint({"UseSparseArrays"})
public class LuaResources extends Resources implements LuaMetaTable {
    private static int a = 2131034112;
    private final HashMap<Integer, String> b = new HashMap<>();
    private final HashMap<Integer, Drawable> c = new HashMap<>();
    private final HashMap<Integer, Integer> d = new HashMap<>();
    private final HashMap<Integer, String[]> e = new HashMap<>();
    private final HashMap<Integer, int[]> f = new HashMap<>();
    private final HashMap<Integer, Typeface> g = new HashMap<>();
    private final HashMap<Integer, Integer> h = new HashMap<>();
    private final HashMap<Integer, Float> i = new HashMap<>();
    private final HashMap<Integer, Boolean> j = new HashMap<>();
    private final HashMap<String, Integer> k = new HashMap<>();
    private Resources l;

    public LuaResources(AssetManager assetManager, DisplayMetrics displayMetrics, Configuration configuration) {
        super(assetManager, displayMetrics, configuration);
    }

    public Object __call(Object... objArr) {
        return null;
    }

    public Object __index(String str) {
        return get(str);
    }

    public void __newIndex(String str, Object obj) {
        put(str, obj);
    }

    public Object get(String str) {
        return this.k.get(str);
    }

    public XmlResourceParser getAnimation(int i2) {
        return this.l.getAnimation(i2);
    }

    public boolean getBoolean(int i2) {
        Boolean bool = this.j.get(Integer.valueOf(i2));
        return bool != null ? bool.booleanValue() : this.l.getBoolean(i2);
    }

    public int getColor(int i2) {
        Integer num = this.d.get(Integer.valueOf(i2));
        return num != null ? num.intValue() : this.l.getColor(i2);
    }

    @TargetApi(23)
    public int getColor(int i2, Resources.Theme theme) {
        Integer num = this.d.get(Integer.valueOf(i2));
        return num != null ? num.intValue() : this.l.getColor(i2, theme);
    }

    public ColorStateList getColorStateList(int i2) {
        return this.l.getColorStateList(i2);
    }

    @TargetApi(23)
    public ColorStateList getColorStateList(int i2, Resources.Theme theme) {
        return this.l.getColorStateList(i2, theme);
    }

    public Configuration getConfiguration() {
        return this.l.getConfiguration();
    }

    public float getDimension(int i2) {
        return this.l.getDimension(i2);
    }

    public int getDimensionPixelOffset(int i2) {
        return this.l.getDimensionPixelOffset(i2);
    }

    public int getDimensionPixelSize(int i2) {
        return this.l.getDimensionPixelSize(i2);
    }

    public DisplayMetrics getDisplayMetrics() {
        return this.l.getDisplayMetrics();
    }

    public Drawable getDrawable(int i2) {
        Drawable drawable = this.c.get(Integer.valueOf(i2));
        return drawable != null ? drawable : this.l.getDrawable(i2);
    }

    @TargetApi(21)
    public Drawable getDrawable(int i2, Resources.Theme theme) {
        Drawable drawable = this.c.get(Integer.valueOf(i2));
        return drawable != null ? drawable : this.l.getDrawable(i2, theme);
    }

    public Drawable getDrawableForDensity(int i2, int i3) {
        return this.l.getDrawableForDensity(i2, i3);
    }

    public Drawable getDrawableForDensity(int i2, int i3, Resources.Theme theme) {
        return this.l.getDrawableForDensity(i2, i3, theme);
    }

    @TargetApi(26)
    public Typeface getFont(int i2) {
        Typeface typeface = this.g.get(Integer.valueOf(i2));
        return typeface != null ? typeface : this.l.getFont(i2);
    }

    public float getFraction(int i2, int i3, int i4) {
        return this.l.getFraction(i2, i3, i4);
    }

    public int getIdentifier(String str, String str2, String str3) {
        return this.l.getIdentifier(str, str2, str3);
    }

    public int[] getIntArray(int i2) {
        int[] iArr = this.f.get(Integer.valueOf(i2));
        return iArr != null ? iArr : this.l.getIntArray(i2);
    }

    public int getInteger(int i2) {
        Integer num = this.h.get(Integer.valueOf(i2));
        return num != null ? num.intValue() : this.l.getInteger(i2);
    }

    public XmlResourceParser getLayout(int i2) {
        return this.l.getLayout(i2);
    }

    public Movie getMovie(int i2) {
        return this.l.getMovie(i2);
    }

    public String getQuantityString(int i2, int i3) {
        return this.l.getQuantityString(i2, i3);
    }

    public String getQuantityString(int i2, int i3, Object... objArr) {
        return this.l.getQuantityString(i2, i3, objArr);
    }

    public CharSequence getQuantityText(int i2, int i3) {
        return this.l.getQuantityText(i2, i3);
    }

    public String getResourceEntryName(int i2) {
        return this.l.getResourceEntryName(i2);
    }

    public String getResourceName(int i2) {
        return this.l.getResourceName(i2);
    }

    public String getResourcePackageName(int i2) {
        return this.l.getResourcePackageName(i2);
    }

    public String getResourceTypeName(int i2) {
        return this.l.getResourceTypeName(i2);
    }

    public String getString(int i2) {
        return getText(i2).toString();
    }

    public String getString(int i2, Object... objArr) {
        return String.format(getString(i2), objArr);
    }

    public String[] getStringArray(int i2) {
        String[] strArr = this.e.get(Integer.valueOf(i2));
        return strArr != null ? strArr : this.l.getStringArray(i2);
    }

    public CharSequence getText(int i2) {
        String str = this.b.get(Integer.valueOf(i2));
        return str != null ? str : this.l.getText(i2);
    }

    public CharSequence getText(int i2, CharSequence charSequence) {
        String str = this.b.get(Integer.valueOf(i2));
        return str != null ? str : this.l.getText(i2, charSequence);
    }

    public CharSequence[] getTextArray(int i2) {
        String[] strArr = this.e.get(Integer.valueOf(i2));
        return strArr != null ? strArr : this.l.getTextArray(i2);
    }

    public void getValue(int i2, TypedValue typedValue, boolean z) {
        this.l.getValue(i2, typedValue, z);
    }

    public void getValue(String str, TypedValue typedValue, boolean z) {
        this.l.getValue(str, typedValue, z);
    }

    public void getValueForDensity(int i2, int i3, TypedValue typedValue, boolean z) {
        this.l.getValueForDensity(i2, i3, typedValue, z);
    }

    public XmlResourceParser getXml(int i2) {
        return this.l.getXml(i2);
    }

    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.l.obtainAttributes(attributeSet, iArr);
    }

    public TypedArray obtainTypedArray(int i2) {
        return this.l.obtainTypedArray(i2);
    }

    public InputStream openRawResource(int i2) {
        return this.l.openRawResource(i2);
    }

    public InputStream openRawResource(int i2, TypedValue typedValue) {
        return this.l.openRawResource(i2, typedValue);
    }

    public AssetFileDescriptor openRawResourceFd(int i2) {
        return this.l.openRawResourceFd(i2);
    }

    public void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) {
        this.l.parseBundleExtra(str, attributeSet, bundle);
    }

    public void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) {
        this.l.parseBundleExtras(xmlResourceParser, bundle);
    }

    public int put(String str, Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        int i2 = a;
        a = i2 + 1;
        if (obj instanceof Drawable) {
            setDrawable(i2, (Drawable) obj);
        } else if (obj instanceof String) {
            setText(i2, (String) obj);
        } else if (obj instanceof String[]) {
            setTextArray(i2, (String[]) obj);
        } else if (obj instanceof Number) {
            setColor(i2, ((Number) obj).intValue());
        } else if (obj instanceof int[]) {
            setIntArray(i2, (int[]) obj);
        } else {
            throw new IllegalArgumentException();
        }
        this.k.put(str, Integer.valueOf(i2));
        return i2;
    }

    public void setBoolean(int i2, Boolean bool) {
        this.j.put(Integer.valueOf(i2), bool);
    }

    public void setColor(int i2, int i3) {
        this.d.put(Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public void setDrawable(int i2, Drawable drawable) {
        this.c.put(Integer.valueOf(i2), drawable);
    }

    public void setFont(int i2, Typeface typeface) {
        this.g.put(Integer.valueOf(i2), typeface);
    }

    public void setIntArray(int i2, int[] iArr) {
        this.f.put(Integer.valueOf(i2), iArr);
    }

    public void setString(int i2, String str) {
        this.b.put(Integer.valueOf(i2), str);
    }

    public void setStringArray(int i2, String[] strArr) {
        this.e.put(Integer.valueOf(i2), strArr);
    }

    public void setSuperResources(Resources resources) {
        this.l = resources;
    }

    public void setText(int i2, String str) {
        this.b.put(Integer.valueOf(i2), str);
    }

    public void setTextArray(int i2, String[] strArr) {
        this.e.put(Integer.valueOf(i2), strArr);
    }
}
