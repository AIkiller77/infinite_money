package com.androlua;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.b.a.a.c;
import com.b.a.a.h;
import com.b.a.b.b;
import com.b.a.b.d;
import com.b.a.b.e;
import com.b.a.b.f;
import com.b.a.b.i;
import com.b.a.b.k;
import com.b.a.b.l;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class LuaEditor extends c {
    private l A;
    private int B;
    private String C;
    private boolean u;
    /* access modifiers changed from: private */
    public Context v;
    private String w;
    private String x = LuaApplication.getInstance().getLuaExtDir("fonts");
    private String y = LuaApplication.getInstance().getLuaExtPath("android.jar");
    private int z;

    @SuppressLint({"StaticFieldLeak"})
    public LuaEditor(Context context) {
        super(context);
        this.v = context;
        setTypeface(Typeface.MONOSPACE);
        File file = new File(this.x, "default.ttf");
        if (file.exists()) {
            setTypeface(Typeface.createFromFile(file));
        }
        File file2 = new File(this.x, "bold.ttf");
        if (file2.exists()) {
            setBoldTypeface(Typeface.createFromFile(file2));
        }
        File file3 = new File(this.x, "italic.ttf");
        if (file3.exists()) {
            setItalicTypeface(Typeface.createFromFile(file3));
        }
        setTextSize((int) TypedValue.applyDimension(2, (float) d, context.getResources().getDisplayMetrics()));
        setShowLineNumbers(true);
        setHighlightCurrentRow(true);
        setWordWrap(false);
        setAutoIndentWidth(2);
        k.a(i.g());
        setNavigationMethod(isAccessibilityEnabled() ? new h(this) : new com.b.a.a.i(this));
        TypedArray obtainStyledAttributes = this.v.getTheme().obtainStyledAttributes(new int[]{16842801, 16842806, 16842905});
        obtainStyledAttributes.getColor(0, 16711935);
        int color = obtainStyledAttributes.getColor(1, 16711935);
        int color2 = obtainStyledAttributes.getColor(2, 16711935);
        obtainStyledAttributes.recycle();
        setTextColor(color);
        setTextHighlightColor(color2);
    }

    public void addNames(String[] strArr) {
        i iVar = (i) k.a();
        String[] c = iVar.c();
        String[] strArr2 = new String[(c.length + strArr.length)];
        System.arraycopy(c, 0, strArr2, 0, c.length);
        System.arraycopy(strArr, 0, strArr2, c.length, strArr.length);
        iVar.b(strArr2);
        k.a((com.b.a.b.h) iVar);
        respan();
        invalidate();
    }

    public void addPackage(String str, String[] strArr) {
        i iVar = (i) k.a();
        iVar.a(str, strArr);
        k.a((com.b.a.b.h) iVar);
        respan();
        invalidate();
    }

    public boolean findNext(String str) {
        if (!str.equals(this.C)) {
            this.C = str;
            this.B = 0;
        }
        this.A = new l();
        String str2 = this.C;
        if (str2.isEmpty()) {
            selectText(false);
            return false;
        }
        this.B = this.A.a(getText(), str2, this.B, getText().length(), false, false);
        if (this.B == -1) {
            selectText(false);
            Toast.makeText(this.v, "未找到", 0).show();
            this.B = 0;
            return false;
        }
        setSelection(this.B, this.C.length());
        this.B += this.C.length();
        moveCaret(this.B);
        return true;
    }

    public String getFilePath() {
        return this.w;
    }

    public String getSelectedText() {
        return this.h.subSequence(getSelectionStart(), getSelectionEnd() - getSelectionStart()).toString();
    }

    public f getText() {
        return createDocumentProvider();
    }

    public void gotoLine() {
        startGotoMode();
    }

    public void gotoLine(int i) {
        if (i > this.h.f()) {
            i = this.h.f();
        }
        setSelection(getText().e(i - 1));
    }

    public void insert(int i, String str) {
        selectText(false);
        moveCaret(i);
        paste(str);
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        if (KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState() & -28673)) {
            if (i == 29) {
                selectAll();
                return true;
            } else if (i == 31) {
                copy();
                return true;
            } else if (i == 35) {
                gotoLine();
                return true;
            } else if (i == 40) {
                format();
                return true;
            } else if (i == 47) {
                search();
                return true;
            } else if (i == 50) {
                paste();
                return true;
            } else if (i == 52) {
                cut();
                return true;
            }
        }
        return super.onKeyShortcut(i, keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i, int i2, int i3, int i4) {
        super.onLayout(z2, i, i2, i3, i4);
        if (this.z != 0 && i3 > 0) {
            moveCaret(this.z);
            this.z = 0;
        }
    }

    public void open(String str) {
        this.w = str;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            sb.append(readLine);
            sb.append("\n");
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        setText(sb);
    }

    public void redo() {
        int m = createDocumentProvider().m();
        if (m >= 0) {
            setEdited(true);
            respan();
            selectText(false);
            moveCaret(m);
            invalidate();
        }
    }

    public void removePackage(String str) {
        i iVar = (i) k.a();
        iVar.b(str);
        k.a((com.b.a.b.h) iVar);
        respan();
        invalidate();
    }

    public boolean save() {
        return save(this.w);
    }

    public boolean save(String str) {
        if (str == null) {
            return true;
        }
        File file = new File(str);
        if (file.exists() && !file.canWrite()) {
            return false;
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
        bufferedWriter.write(getText().toString());
        bufferedWriter.close();
        return true;
    }

    public void search() {
        startFindMode();
    }

    public void setBackgoudColor(int i) {
        getColorScheme().a(b.a.BACKGROUND, i);
    }

    public void setBasewordColor(int i) {
        getColorScheme().a(b.a.NAME, i);
    }

    public void setCommentColor(int i) {
        getColorScheme().a(b.a.COMMENT, i);
    }

    public void setDark(boolean z2) {
        setColorScheme(z2 ? new com.b.a.b.c() : new d());
    }

    public void setKeywordColor(int i) {
        getColorScheme().a(b.a.KEYWORD, i);
    }

    public void setPanelBackgroundColor(int i) {
        this.s.b(i);
    }

    public void setPanelTextColor(int i) {
        this.s.a(i);
    }

    public void setSelection(int i) {
        selectText(false);
        if (!hasLayout()) {
            moveCaret(i);
        } else {
            this.z = i;
        }
    }

    public void setStringColor(int i) {
        getColorScheme().a(b.a.STRING, i);
    }

    public void setText(CharSequence charSequence) {
        e eVar = new e(this);
        eVar.a(this.u);
        eVar.a(charSequence);
        setDocumentProvider(new f(eVar));
    }

    public void setText(CharSequence charSequence, boolean z2) {
        replaceText(0, getLength() - 1, charSequence.toString());
    }

    public void setTextColor(int i) {
        getColorScheme().a(b.a.FOREGROUND, i);
    }

    public void setTextHighlightColor(int i) {
        getColorScheme().a(b.a.SELECTION_BACKGROUND, i);
    }

    public void setUserwordColor(int i) {
        getColorScheme().a(b.a.LITERAL, i);
    }

    public void setWordWrap(boolean z2) {
        this.u = z2;
        super.setWordWrap(z2);
    }

    public void startFindMode() {
        startActionMode(new ActionMode.Callback() {
            private l b;
            /* access modifiers changed from: private */
            public int c;
            private EditText d;

            /* access modifiers changed from: private */
            public void a() {
                this.b = new l();
                String obj = this.d.getText().toString();
                if (obj.isEmpty()) {
                    LuaEditor.this.selectText(false);
                    return;
                }
                this.c = this.b.a(LuaEditor.this.getText(), obj, this.c, LuaEditor.this.getText().length(), false, false);
                if (this.c == -1) {
                    LuaEditor.this.selectText(false);
                    Toast.makeText(LuaEditor.this.v, "未找到", 0).show();
                    this.c = 0;
                    return;
                }
                LuaEditor.this.setSelection(this.c, this.d.getText().length());
                this.c += this.d.getText().length();
                LuaEditor.this.moveCaret(this.c);
            }

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case 2:
                        a();
                        return false;
                    default:
                        return false;
                }
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.setTitle("搜索");
                actionMode.setSubtitle((CharSequence) null);
                this.d = new EditText(LuaEditor.this.v) {
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        if (charSequence.length() > 0) {
                            int unused = AnonymousClass2.this.c = 0;
                            AnonymousClass2.this.a();
                        }
                    }
                };
                this.d.setSingleLine(true);
                this.d.setImeOptions(3);
                this.d.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        AnonymousClass2.this.a();
                        return true;
                    }
                });
                this.d.setLayoutParams(new RadioGroup.LayoutParams(LuaEditor.this.getWidth() / 3, -1));
                menu.add(0, 1, 0, "").setActionView(this.d);
                menu.add(0, 2, 0, LuaEditor.this.v.getString(17039372));
                this.d.requestFocus();
                return true;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
    }

    public void startGotoMode() {
        startActionMode(new ActionMode.Callback() {
            /* access modifiers changed from: private */
            public int b;
            private EditText c;

            /* access modifiers changed from: private */
            public void a() {
                String obj = this.c.getText().toString();
                if (!obj.isEmpty()) {
                    int intValue = Integer.valueOf(obj).intValue();
                    if (intValue > LuaEditor.this.h.f()) {
                        intValue = LuaEditor.this.h.f();
                    }
                    LuaEditor.this.gotoLine(intValue);
                }
            }

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case 2:
                        a();
                        return false;
                    default:
                        return false;
                }
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.setTitle("转到");
                actionMode.setSubtitle((CharSequence) null);
                this.c = new EditText(LuaEditor.this.v) {
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        if (charSequence.length() > 0) {
                            int unused = AnonymousClass1.this.b = 0;
                            AnonymousClass1.this.a();
                        }
                    }
                };
                this.c.setSingleLine(true);
                this.c.setInputType(2);
                this.c.setImeOptions(2);
                this.c.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        AnonymousClass1.this.a();
                        return true;
                    }
                });
                this.c.setLayoutParams(new RadioGroup.LayoutParams(LuaEditor.this.getWidth() / 3, -1));
                menu.add(0, 1, 0, "").setActionView(this.c);
                menu.add(0, 2, 0, LuaEditor.this.v.getString(17039370));
                this.c.requestFocus();
                return true;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
    }

    public void undo() {
        int l = createDocumentProvider().l();
        if (l >= 0) {
            setEdited(true);
            respan();
            selectText(false);
            moveCaret(l);
            invalidate();
        }
    }
}
