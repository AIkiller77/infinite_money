package android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayExpandableListAdapter extends BaseExpandableListAdapter {
    private Context a;
    private List<List<String>> b;
    private List<String> c;
    private int d;
    private int e;
    private LayoutInflater f;
    private boolean g;

    public ArrayExpandableListAdapter(Context context) {
        this(context, new ArrayList(), 17367046, new ArrayList(), 17367046);
    }

    public ArrayExpandableListAdapter(Context context, List<String> list, int i, List<List<String>> list2, int i2) {
        this.g = true;
        this.a = context;
        this.c = list;
        this.d = i;
        this.b = list2;
        this.e = i2;
        this.f = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public ArrayExpandableListAdapter(Context context, List<String> list, List<List<String>> list2) {
        this(context, list, 17367046, list2, 17367046);
    }

    public void add(String str, List<String> list) {
        this.c.add(str);
        this.b.add(list);
        if (this.g) {
            notifyDataSetChanged();
        }
    }

    public void add(String str, String[] strArr) {
        this.c.add(str);
        this.b.add(Arrays.asList(strArr));
        if (this.g) {
            notifyDataSetChanged();
        }
    }

    public Object getChild(int i, int i2) {
        return this.b.get(i).get(i2);
    }

    public long getChildId(int i, int i2) {
        return 0;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f.inflate(this.e, viewGroup, false);
        }
        ((TextView) view).setText((CharSequence) this.b.get(i).get(i2));
        return view;
    }

    public int getChildrenCount(int i) {
        return this.b.get(i).size();
    }

    public Object getGroup(int i) {
        return this.c.get(i);
    }

    public int getGroupCount() {
        return this.c.size();
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f.inflate(this.d, viewGroup, false);
        }
        ((TextView) view).setText(this.c.get(i));
        return view;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void setNotifyOnChange(boolean z) {
        this.g = z;
    }
}
