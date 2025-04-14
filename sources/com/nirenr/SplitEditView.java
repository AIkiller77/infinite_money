package com.nirenr;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import com.androlua.LuaEditor;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitEditView extends LinearLayout implements View.OnClickListener, AdapterView.OnItemClickListener {
    /* access modifiers changed from: private */
    public final Context a;
    private LinearLayout b;
    /* access modifiers changed from: private */
    public GridView c;
    private LuaEditor d;
    private int e = 0;
    private String f = "";
    /* access modifiers changed from: private */
    public String[] g = {""};
    private OnSaveListener h;
    private LinearLayout i;

    private class EditDialog implements DialogInterface.OnClickListener {
        private final int b;
        private final EditText c;
        private AlertDialog d;

        public EditDialog(int i) {
            this.b = i;
            this.c = new EditText(SplitEditView.this.a);
            this.c.setText(SplitEditView.this.g[i]);
            this.c.setSelection(SplitEditView.this.g[i].length());
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            SplitEditView.this.g[this.b] = this.c.getText().toString();
            SplitEditView.this.f();
            SplitEditView.this.c.smoothScrollToPosition(this.b);
        }

        public void show() {
            this.d = new AlertDialog.Builder(SplitEditView.this.a).setTitle("输入内容").setView(this.c).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(17039370, this).setCancelable(false).create();
            Window window = this.d.getWindow();
            if (window != null) {
                window.setSoftInputMode(4);
                if (SplitEditView.this.a instanceof Service) {
                    window.setType(Build.VERSION.SDK_INT >= 22 ? 2032 : 2010);
                }
                this.d.show();
            }
            this.c.setFocusable(true);
            this.c.requestFocus();
        }
    }

    public interface OnSaveListener {
        void onSave(String str);
    }

    public SplitEditView(Context context) {
        super(context);
        a(context);
        this.a = context;
    }

    private void a() {
        this.g = new String[]{this.f};
        switch (this.e) {
            case 1:
                e();
                return;
            case 2:
                c();
                return;
            case 3:
                d();
                return;
            case 4:
                b();
                return;
            default:
                setShowEdit(true);
                return;
        }
    }

    private void a(Context context) {
        this.b = this;
        this.b.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1, 1.0f);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        this.c = new GridView(context);
        this.d = new LuaEditor(context);
        this.c.setOnItemClickListener(this);
        this.b.addView(this.c, layoutParams);
        this.b.addView(this.d, layoutParams);
        this.i = new LinearLayout(context);
        String[] strArr = {"全文", "按段", "按行", "按句", "按字", "确定"};
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            Button button = new Button(context);
            button.setText(str);
            button.setId(i2);
            button.setOnClickListener(this);
            this.i.addView(button, layoutParams2);
        }
        this.b.addView(this.i, layoutParams3);
        this.c.setVisibility(8);
        this.d.setVisibility(0);
        setText("");
        setOnSaveListener((OnSaveListener) null);
    }

    private String[] a(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(str2).matcher(str);
        int i2 = 0;
        while (matcher.find()) {
            int end = matcher.end();
            if (this.e == 1) {
                end = matcher.start();
            }
            arrayList.add(str.substring(i2, end));
            i2 = this.e == 1 ? matcher.end() : end;
        }
        if (i2 != str.length()) {
            arrayList.add(str.substring(i2));
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    private void b() {
        this.f = getText();
        this.e = 4;
        this.g = new String[this.f.length()];
        for (int i2 = 0; i2 < this.f.length(); i2++) {
            this.g[i2] = String.valueOf(this.f.charAt(i2));
        }
        if (this.g.length == 0) {
            this.g = new String[]{""};
        }
        this.c.setNumColumns(8);
        this.c.setAdapter(new ArrayListAdapter(this.a, 17367043, (T[]) this.g));
    }

    private void c() {
        this.f = getText();
        this.e = 2;
        this.g = a(this.f, "\n");
        if (this.g.length == 0) {
            this.g = new String[]{""};
        }
        this.c.setNumColumns(1);
        this.c.setAdapter(new ArrayListAdapter(this.a, 17367043, (T[]) this.g));
    }

    private void d() {
        this.f = getText();
        this.e = 3;
        this.g = a(this.f, "\\. |[。？！，\n “”,：；;\\?!]+");
        if (this.g.length == 0) {
            this.g = new String[]{""};
        }
        this.c.setNumColumns(1);
        this.c.setAdapter(new ArrayListAdapter(this.a, 17367043, (T[]) this.g));
    }

    private void e() {
        this.f = getText();
        this.e = 1;
        this.g = a(this.f, "\\n{2,10}");
        if (this.g.length == 0) {
            this.g = new String[]{""};
        }
        this.c.setNumColumns(1);
        this.c.setAdapter(new ArrayListAdapter(this.a, 17367043, (T[]) this.g));
    }

    /* access modifiers changed from: private */
    public void f() {
        switch (this.e) {
            case 1:
                e();
                return;
            case 2:
                c();
                return;
            case 3:
                d();
                return;
            case 4:
                b();
                return;
            default:
                return;
        }
    }

    private void setShowEdit(boolean z) {
        if (isShowEdit() != z) {
            if (!z) {
                this.c.setVisibility(0);
                this.d.setVisibility(8);
                this.f = this.d.getText().toString();
                this.g = new String[]{this.f};
                return;
            }
            this.d.setText(getText());
            this.c.setVisibility(8);
            this.d.setVisibility(0);
        }
    }

    public String getText() {
        if (isShowEdit()) {
            return this.d.getText().toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String append : this.g) {
            sb.append(append);
            if (this.e == 1) {
                sb.append("\n\n");
            }
        }
        if (this.e == 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    public boolean isShowEdit() {
        return this.d.getVisibility() == 0;
    }

    public void onClick(View view) {
        Button button = (Button) view;
        switch (view.getId()) {
            case 0:
                setShowEdit(true);
                this.e = 0;
                return;
            case 1:
                setShowEdit(false);
                e();
                return;
            case 2:
                setShowEdit(false);
                c();
                return;
            case 3:
                setShowEdit(false);
                d();
                return;
            case 4:
                setShowEdit(false);
                b();
                return;
            case 5:
                if (this.h != null) {
                    this.h.onSave(getText());
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
        new EditDialog(i2).show();
    }

    public void setOnSaveListener(OnSaveListener onSaveListener) {
        View childAt;
        int i2;
        this.h = onSaveListener;
        if (onSaveListener == null) {
            childAt = this.i.getChildAt(5);
            i2 = 8;
        } else {
            childAt = this.i.getChildAt(5);
            i2 = 0;
        }
        childAt.setVisibility(i2);
    }

    public void setText(String str) {
        this.f = str;
        if (this.f == null) {
            this.f = "";
        }
        this.d.setText(this.f);
        a();
    }
}
