package android.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class SwipeMenuItem {
    private Drawable background;
    private Drawable icon;
    private int id;
    private Context mContext;
    private String title;
    private int titleColor;
    private int titleSize;
    private int width;

    public SwipeMenuItem(Context context) {
        this.mContext = context;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        int i2 = i;
        this.id = i2;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public int getTitleSize() {
        return this.titleSize;
    }

    public void setTitleSize(int i) {
        int i2 = i;
        this.titleSize = i2;
    }

    public void setTitleColor(int i) {
        int i2 = i;
        this.titleColor = i2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        String str2 = str;
        this.title = str2;
    }

    public void setTitle(int i) {
        setTitle(this.mContext.getString(i));
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIcon(Drawable drawable) {
        Drawable drawable2 = drawable;
        this.icon = drawable2;
    }

    public void setIcon(int i) {
        this.icon = this.mContext.getResources().getDrawable(i);
    }

    public Drawable getBackground() {
        return this.background;
    }

    public void setBackground(Drawable drawable) {
        Drawable drawable2 = drawable;
        this.background = drawable2;
    }

    public void setBackground(int i) {
        this.background = this.mContext.getResources().getDrawable(i);
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        int i2 = i;
        this.width = i2;
    }
}
