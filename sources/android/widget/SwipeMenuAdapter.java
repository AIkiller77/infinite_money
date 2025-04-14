package android.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SwipeMenuListView;
import android.widget.SwipeMenuView;

public class SwipeMenuAdapter implements WrapperListAdapter, SwipeMenuView.OnSwipeItemClickListener {
    private ListAdapter mAdapter;
    private Context mContext;
    private SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener;

    public SwipeMenuAdapter(Context context, ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
        this.mContext = context;
    }

    public int getCount() {
        return this.mAdapter.getCount();
    }

    public Object getItem(int i) {
        return this.mAdapter.getItem(i);
    }

    public long getItemId(int i) {
        return this.mAdapter.getItemId(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        SwipeMenuLayout swipeMenuLayout;
        SwipeMenu swipeMenu;
        SwipeMenuView swipeMenuView;
        SwipeMenuLayout swipeMenuLayout2;
        int i2 = i;
        View view2 = view;
        ViewGroup viewGroup2 = viewGroup;
        if (view2 == null) {
            View view3 = this.mAdapter.getView(i2, view2, viewGroup2);
            new SwipeMenu(this.mContext);
            SwipeMenu swipeMenu2 = swipeMenu;
            swipeMenu2.setViewType(this.mAdapter.getItemViewType(i2));
            createMenu(swipeMenu2);
            new SwipeMenuView(swipeMenu2, (SwipeMenuListView) viewGroup2);
            SwipeMenuView swipeMenuView2 = swipeMenuView;
            swipeMenuView2.setOnSwipeItemClickListener(this);
            SwipeMenuListView swipeMenuListView = (SwipeMenuListView) viewGroup2;
            new SwipeMenuLayout(view3, swipeMenuView2, swipeMenuListView.getCloseInterpolator(), swipeMenuListView.getOpenInterpolator());
            swipeMenuLayout = swipeMenuLayout2;
            swipeMenuLayout.setPosition(i2);
        } else {
            swipeMenuLayout = (SwipeMenuLayout) view2;
            swipeMenuLayout.closeMenu();
            swipeMenuLayout.setPosition(i2);
            View view4 = this.mAdapter.getView(i2, swipeMenuLayout.getContentView(), viewGroup2);
        }
        return swipeMenuLayout;
    }

    public void createMenu(SwipeMenu swipeMenu) {
        SwipeMenuItem swipeMenuItem;
        Drawable drawable;
        SwipeMenuItem swipeMenuItem2;
        Drawable drawable2;
        SwipeMenu swipeMenu2 = swipeMenu;
        new SwipeMenuItem(this.mContext);
        SwipeMenuItem swipeMenuItem3 = swipeMenuItem;
        swipeMenuItem3.setTitle("Item 1");
        new ColorDrawable(-7829368);
        swipeMenuItem3.setBackground(drawable);
        swipeMenuItem3.setWidth(300);
        swipeMenu2.addMenuItem(swipeMenuItem3);
        new SwipeMenuItem(this.mContext);
        SwipeMenuItem swipeMenuItem4 = swipeMenuItem2;
        swipeMenuItem4.setTitle("Item 2");
        new ColorDrawable(SupportMenu.CATEGORY_MASK);
        swipeMenuItem4.setBackground(drawable2);
        swipeMenuItem4.setWidth(300);
        swipeMenu2.addMenuItem(swipeMenuItem4);
    }

    public void onItemClick(SwipeMenuView swipeMenuView, SwipeMenu swipeMenu, int i) {
        SwipeMenuView swipeMenuView2 = swipeMenuView;
        SwipeMenu swipeMenu2 = swipeMenu;
        int i2 = i;
        if (this.onMenuItemClickListener != null) {
            boolean onMenuItemClick = this.onMenuItemClickListener.onMenuItemClick(swipeMenuView2.getPosition(), swipeMenu2, i2);
        }
    }

    public void setOnMenuItemClickListener(SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener2) {
        SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener3 = onMenuItemClickListener2;
        this.onMenuItemClickListener = onMenuItemClickListener3;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.mAdapter.registerDataSetObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.mAdapter.unregisterDataSetObserver(dataSetObserver);
    }

    public boolean areAllItemsEnabled() {
        return this.mAdapter.areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        return this.mAdapter.isEnabled(i);
    }

    public boolean hasStableIds() {
        return this.mAdapter.hasStableIds();
    }

    public int getItemViewType(int i) {
        return this.mAdapter.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return this.mAdapter.getViewTypeCount();
    }

    public boolean isEmpty() {
        return this.mAdapter.isEmpty();
    }

    public ListAdapter getWrappedAdapter() {
        return this.mAdapter;
    }
}
