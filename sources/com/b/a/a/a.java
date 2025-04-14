package com.b.a.a;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import com.b.a.b.g;
import com.b.a.b.h;
import com.b.a.b.j;
import java.util.ArrayList;

public class a {
    /* access modifiers changed from: private */
    public static h c = j.g();
    /* access modifiers changed from: private */
    public c a;
    private Context b;
    private ListPopupWindow d;
    /* access modifiers changed from: private */
    public C0004a e;
    private Filter f;
    private int g;
    private int h;
    private int i;
    /* access modifiers changed from: private */
    public CharSequence j;
    private int k;
    private GradientDrawable l;
    /* access modifiers changed from: private */
    public int m;

    /* renamed from: com.b.a.a.a$a  reason: collision with other inner class name */
    class C0004a extends ArrayAdapter<String> implements Filterable {
        private int b;
        /* access modifiers changed from: private */
        public g c = new g();
        private DisplayMetrics d;

        public C0004a(Context context, int i) {
            super(context, i);
            setNotifyOnChange(false);
            this.d = context.getResources().getDisplayMetrics();
        }

        public void a() {
            this.c.a();
        }

        public void b() {
            this.c.b();
        }

        public int c() {
            if (this.b != 0) {
                return this.b;
            }
            TextView textView = (TextView) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(17367043, (ViewGroup) null);
            textView.measure(0, 0);
            this.b = textView.getMeasuredHeight();
            return this.b;
        }

        public Filter getFilter() {
            return new Filter() {
                /* access modifiers changed from: protected */
                public Filter.FilterResults performFiltering(CharSequence charSequence) {
                    String str;
                    ArrayList arrayList = new ArrayList();
                    String lowerCase = String.valueOf(charSequence).toLowerCase();
                    String[] split = lowerCase.split("\\.");
                    int i = 0;
                    if (split.length == 2) {
                        String str2 = split[0];
                        str = split[1];
                        if (a.c.f(str2)) {
                            String[] a2 = a.c.a(str2);
                            int length = a2.length;
                            while (i < length) {
                                String str3 = a2[i];
                                if (str3.toLowerCase().startsWith(str)) {
                                    arrayList.add(str3);
                                }
                                i++;
                            }
                        }
                    } else {
                        if (split.length == 1) {
                            if (lowerCase.charAt(lowerCase.length() - 1) == '.') {
                                String substring = lowerCase.substring(0, lowerCase.length() - 1);
                                str = "";
                                if (a.c.f(substring)) {
                                    String[] a3 = a.c.a(substring);
                                    int length2 = a3.length;
                                    while (i < length2) {
                                        arrayList.add(a3[i]);
                                        i++;
                                    }
                                }
                            } else {
                                for (String str4 : a.c.b()) {
                                    if (str4.toLowerCase().startsWith(lowerCase)) {
                                        arrayList.add(str4);
                                    }
                                }
                                for (String str5 : a.c.d()) {
                                    if (str5.indexOf(lowerCase) == 0) {
                                        arrayList.add(str5);
                                    }
                                }
                                String[] c = a.c.c();
                                int length3 = c.length;
                                while (i < length3) {
                                    String str6 = c[i];
                                    if (str6.toLowerCase().startsWith(lowerCase)) {
                                        arrayList.add(str6);
                                    }
                                    i++;
                                }
                            }
                        }
                        str = lowerCase;
                    }
                    CharSequence unused = a.this.j = str;
                    Filter.FilterResults filterResults = new Filter.FilterResults();
                    filterResults.values = arrayList;
                    filterResults.count = arrayList.size();
                    return filterResults;
                }

                /* access modifiers changed from: protected */
                public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                    if (filterResults == null || filterResults.count <= 0 || C0004a.this.c.c()) {
                        C0004a.this.notifyDataSetInvalidated();
                        return;
                    }
                    C0004a.this.clear();
                    C0004a.this.addAll((ArrayList) filterResults.values);
                    int caretY = (a.this.a.getCaretY() + (a.this.a.a() / 2)) - a.this.a.getScrollY();
                    a.this.d(C0004a.this.c() * Math.min(2, filterResults.count));
                    a.this.e(a.this.a.getCaretX() - a.this.a.getScrollX());
                    a.this.f(caretY - a.this.a.getHeight());
                    C0004a.this.notifyDataSetChanged();
                    a.this.a();
                }
            };
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = (TextView) super.getView(i, view, viewGroup);
            textView.setTextColor(a.this.m);
            return textView;
        }
    }

    public a(c cVar) {
        this.a = cVar;
        this.b = cVar.getContext();
        d();
    }

    private void d() {
        this.d = new ListPopupWindow(this.b);
        this.d.setAnchorView(this.a);
        this.e = new C0004a(this.b, 17367043);
        this.d.setAdapter(this.e);
        this.f = this.e.getFilter();
        d(300);
        TypedArray obtainStyledAttributes = this.b.getTheme().obtainStyledAttributes(new int[]{16842801, 16842806});
        int color = obtainStyledAttributes.getColor(0, 16711935);
        int color2 = obtainStyledAttributes.getColor(1, 16711935);
        obtainStyledAttributes.recycle();
        this.l = new GradientDrawable();
        this.l.setColor(color);
        this.l.setCornerRadius(4.0f);
        this.l.setStroke(1, color2);
        a(color2);
        this.d.setBackgroundDrawable(this.l);
        this.d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                a.this.a.replaceText(a.this.a.getCaretPosition() - a.this.j.length(), a.this.j.length(), ((TextView) view).getText().toString());
                a.this.e.a();
                a.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public void d(int i2) {
        if (this.h != i2) {
            this.h = i2;
            this.d.setHeight(i2);
        }
    }

    /* access modifiers changed from: private */
    public void e(int i2) {
        int min = Math.min(i2, this.a.getWidth() / 2);
        if (this.i != min) {
            this.i = min;
            this.d.setHorizontalOffset(min);
        }
    }

    /* access modifiers changed from: private */
    public void f(int i2) {
        int height = 0 - this.d.getHeight();
        if (i2 > height) {
            this.a.scrollBy(0, i2 - height);
            i2 = height;
        }
        if (this.g != i2) {
            this.g = i2;
            this.d.setVerticalOffset(i2);
        }
    }

    public void a() {
        if (!this.d.isShowing()) {
            this.d.show();
        }
        this.d.getListView().setFadingEdgeLength(0);
    }

    public void a(int i2) {
        this.m = i2;
        this.l.setStroke(1, i2);
        this.d.setBackgroundDrawable(this.l);
    }

    public synchronized void a(h hVar) {
        c = hVar;
    }

    public void a(CharSequence charSequence) {
        this.e.b();
        this.f.filter(charSequence);
    }

    public void b() {
        if (this.d.isShowing()) {
            this.d.dismiss();
        }
    }

    public void b(int i2) {
        this.k = i2;
        this.l.setColor(i2);
        this.d.setBackgroundDrawable(this.l);
    }

    public void c(int i2) {
        this.d.setWidth(i2);
    }
}
