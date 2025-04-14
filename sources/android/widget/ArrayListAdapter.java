package android.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import com.a.a.a.a.a.a.a;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayListAdapter<T> extends BaseAdapter implements Filterable {
    /* access modifiers changed from: private */
    public ArrayList<T> a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    private int c;
    private int d;
    private int e = 0;
    private boolean f = true;
    private Context g;
    /* access modifiers changed from: private */
    public ArrayList<T> h;
    private ArrayListAdapter<T>.ArrayFilter i;
    private LayoutInflater j;
    /* access modifiers changed from: private */
    public LuaFunction k;

    private class ArrayFilter extends Filter {
        private ArrayFilter() {
        }

        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            int i;
            ArrayList arrayList;
            ArrayList arrayList2;
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (ArrayListAdapter.this.h == null) {
                synchronized (ArrayListAdapter.this.b) {
                    ArrayList unused = ArrayListAdapter.this.h = new ArrayList(ArrayListAdapter.this.a);
                }
            } else if (TextUtils.isEmpty(charSequence)) {
                filterResults.values = new ArrayList(ArrayListAdapter.this.h);
                filterResults.count = ArrayListAdapter.this.h.size();
                ArrayList unused2 = ArrayListAdapter.this.h = null;
                return filterResults;
            }
            if (ArrayListAdapter.this.k != null) {
                ArrayList arrayList3 = new ArrayList();
                try {
                    ArrayListAdapter.this.k.call(new ArrayList(ArrayListAdapter.this.h), arrayList3, charSequence);
                } catch (LuaException e) {
                    a.a(e);
                }
                filterResults.values = arrayList3;
                filterResults.count = arrayList3.size();
                return filterResults;
            }
            if (charSequence == null || charSequence.length() == 0) {
                synchronized (ArrayListAdapter.this.b) {
                    arrayList = new ArrayList(ArrayListAdapter.this.h);
                }
                filterResults.values = arrayList;
                i = arrayList.size();
            } else {
                String lowerCase = charSequence.toString().toLowerCase();
                synchronized (ArrayListAdapter.this.b) {
                    arrayList2 = new ArrayList(ArrayListAdapter.this.h);
                }
                int size = arrayList2.size();
                ArrayList arrayList4 = new ArrayList();
                for (int i2 = 0; i2 < size; i2++) {
                    Object obj = arrayList2.get(i2);
                    if (obj.toString().toLowerCase().contains(lowerCase)) {
                        arrayList4.add(obj);
                    }
                }
                filterResults.values = arrayList4;
                i = arrayList4.size();
            }
            filterResults.count = i;
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            ArrayList unused = ArrayListAdapter.this.a = (ArrayList) filterResults.values;
            if (filterResults.count > 0) {
                ArrayListAdapter.this.notifyDataSetChanged();
            } else {
                ArrayListAdapter.this.notifyDataSetInvalidated();
            }
        }
    }

    public ArrayListAdapter(Context context) {
        a(context, 17367043, 0, new ArrayList());
    }

    public ArrayListAdapter(Context context, int i2) {
        a(context, i2, 0, new ArrayList());
    }

    public ArrayListAdapter(Context context, int i2, int i3) {
        a(context, i2, i3, new ArrayList());
    }

    public ArrayListAdapter(Context context, int i2, int i3, List<T> list) {
        a(context, i2, i3, list);
    }

    public ArrayListAdapter(Context context, int i2, int i3, T[] tArr) {
        a(context, i2, i3, Arrays.asList(tArr));
    }

    public ArrayListAdapter(Context context, int i2, List<T> list) {
        a(context, i2, 0, list);
    }

    public ArrayListAdapter(Context context, int i2, T[] tArr) {
        a(context, i2, 0, Arrays.asList(tArr));
    }

    public ArrayListAdapter(Context context, List<T> list) {
        a(context, 17367043, 0, list);
    }

    public ArrayListAdapter(Context context, T[] tArr) {
        a(context, 17367043, 0, Arrays.asList(tArr));
    }

    private View a(int i2, View view, ViewGroup viewGroup, int i3) {
        if (view == null) {
            view = this.j.inflate(i3, viewGroup, false);
        }
        try {
            TextView textView = this.e == 0 ? (TextView) view : (TextView) view.findViewById(this.e);
            Object item = getItem(i2);
            textView.setText(item instanceof CharSequence ? (CharSequence) item : item.toString());
            return view;
        } catch (ClassCastException e2) {
            Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException("ArrayAdapter requires the resource ID to be a TextView", e2);
        }
    }

    private void a(Context context, int i2, int i3, List<T> list) {
        this.g = context;
        this.j = (LayoutInflater) context.getSystemService("layout_inflater");
        this.d = i2;
        this.c = i2;
        this.a = new ArrayList<>(list);
        this.e = i3;
    }

    public static ArrayListAdapter<CharSequence> createFromResource(Context context, int i2, int i3) {
        return new ArrayListAdapter<>(context, i3, (T[]) context.getResources().getTextArray(i2));
    }

    public void add(T t) {
        synchronized (this.b) {
            (this.h != null ? this.h : this.a).add(t);
        }
        if (this.f) {
            notifyDataSetChanged();
        }
    }

    public void addAll(Collection<? extends T> collection) {
        synchronized (this.b) {
            (this.h != null ? this.h : this.a).addAll(collection);
        }
        if (this.f) {
            notifyDataSetChanged();
        }
    }

    public void addAll(T... tArr) {
        synchronized (this.b) {
            Collections.addAll(this.h != null ? this.h : this.a, tArr);
        }
        if (this.f) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        synchronized (this.b) {
            (this.h != null ? this.h : this.a).clear();
        }
        if (this.f) {
            notifyDataSetChanged();
        }
    }

    public void filter(CharSequence charSequence) {
        getFilter().filter(charSequence);
    }

    public Context getContext() {
        return this.g;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getData() {
        return this.a;
    }

    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        return a(i2, view, viewGroup, this.d);
    }

    public Filter getFilter() {
        if (this.i == null) {
            this.i = new ArrayFilter();
        }
        return this.i;
    }

    public T getItem(int i2) {
        return this.a.get(i2);
    }

    public long getItemId(int i2) {
        return (long) (i2 + 1);
    }

    public int getPosition(T t) {
        return this.a.indexOf(t);
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        return a(i2, view, viewGroup, this.c);
    }

    public void insert(int i2, T t) {
        synchronized (this.b) {
            (this.h != null ? this.h : this.a).add(i2, t);
        }
        if (this.f) {
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.f = true;
    }

    public void remove(int i2) {
        synchronized (this.b) {
            (this.h != null ? this.h : this.a).remove(i2);
        }
        if (this.f) {
            notifyDataSetChanged();
        }
    }

    public void remove(T t) {
        synchronized (this.b) {
            (this.h != null ? this.h : this.a).remove(t);
        }
        if (this.f) {
            notifyDataSetChanged();
        }
    }

    public void setDropDownViewResource(int i2) {
        this.d = i2;
    }

    public void setFilter(LuaFunction luaFunction) {
        this.k = luaFunction;
    }

    public void setNotifyOnChange(boolean z) {
        this.f = z;
    }

    public void sort(Comparator<? super T> comparator) {
        synchronized (this.b) {
            Collections.sort(this.h != null ? this.h : this.a, comparator);
        }
        if (this.f) {
            notifyDataSetChanged();
        }
    }
}
