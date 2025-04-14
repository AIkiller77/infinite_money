package com.b.a.a;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

public class b {
    protected c a;
    /* access modifiers changed from: private */
    public Context b;
    /* access modifiers changed from: private */
    public ActionMode c;

    public b(c cVar) {
        this.a = cVar;
        this.b = cVar.getContext();
    }

    public void a() {
        c();
    }

    public void b() {
        d();
    }

    public void c() {
        if (this.c == null) {
            this.a.startActionMode(new ActionMode.Callback() {
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case 0:
                            b.this.a.selectAll();
                            return false;
                        case 1:
                            b.this.a.cut();
                            break;
                        case 2:
                            b.this.a.copy();
                            break;
                        case 3:
                            b.this.a.paste();
                            break;
                        default:
                            return false;
                    }
                    actionMode.finish();
                    return false;
                }

                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    ActionMode unused = b.this.c = actionMode;
                    actionMode.setTitle(17039382);
                    TypedArray obtainStyledAttributes = b.this.b.getTheme().obtainStyledAttributes(new int[]{16843646, 16843537, 16843538, 16843539});
                    menu.add(0, 0, 0, b.this.b.getString(17039373)).setShowAsActionFlags(2).setAlphabeticShortcut('a').setIcon(obtainStyledAttributes.getDrawable(0));
                    menu.add(0, 1, 0, b.this.b.getString(17039363)).setShowAsActionFlags(2).setAlphabeticShortcut('x').setIcon(obtainStyledAttributes.getDrawable(1));
                    menu.add(0, 2, 0, b.this.b.getString(17039361)).setShowAsActionFlags(2).setAlphabeticShortcut('c').setIcon(obtainStyledAttributes.getDrawable(2));
                    menu.add(0, 3, 0, b.this.b.getString(17039371)).setShowAsActionFlags(2).setAlphabeticShortcut('v').setIcon(obtainStyledAttributes.getDrawable(3));
                    obtainStyledAttributes.recycle();
                    return true;
                }

                public void onDestroyActionMode(ActionMode actionMode) {
                    b.this.a.selectText(false);
                    ActionMode unused = b.this.c = null;
                }

                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }
            });
        }
    }

    public void d() {
        if (this.c != null) {
            this.c.finish();
            this.c = null;
        }
    }
}
