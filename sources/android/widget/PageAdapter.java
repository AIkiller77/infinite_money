package android.widget;

import android.view.View;

public class PageAdapter extends BasePageAdapter {
    private final Adapter a;
    private View[] b;

    public PageAdapter(Adapter adapter) {
        this.a = adapter;
        this.b = new View[adapter.getViewTypeCount()];
    }

    public void destroyItem(View view, int i, Object obj) {
        View view2 = (View) obj;
        ((PageView) view).removeView(view2);
        this.b[this.a.getItemViewType(i)] = view2;
    }

    public int getCount() {
        return this.a.getCount();
    }

    public Object instantiateItem(View view, int i) {
        int itemViewType = this.a.getItemViewType(i);
        if (this.b[itemViewType] != null) {
            ((PageView) view).removeView(this.b[itemViewType]);
        }
        PageView pageView = (PageView) view;
        View view2 = this.a.getView(i, this.b[itemViewType], pageView);
        pageView.addView(view2, 0);
        this.b[itemViewType] = null;
        return view2;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
