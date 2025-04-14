package android.widget;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class SwipeMenu {
    private Context mContext;
    private List<SwipeMenuItem> mItems;
    private int mViewType;

    public SwipeMenu(Context context) {
        List<SwipeMenuItem> list;
        this.mContext = context;
        new ArrayList();
        this.mItems = list;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void addMenuItem(SwipeMenuItem swipeMenuItem) {
        boolean add = this.mItems.add(swipeMenuItem);
    }

    public void removeMenuItem(SwipeMenuItem swipeMenuItem) {
        boolean remove = this.mItems.remove(swipeMenuItem);
    }

    public List<SwipeMenuItem> getMenuItems() {
        return this.mItems;
    }

    public SwipeMenuItem getMenuItem(int i) {
        return this.mItems.get(i);
    }

    public int getViewType() {
        return this.mViewType;
    }

    public void setViewType(int i) {
        int i2 = i;
        this.mViewType = i2;
    }
}
