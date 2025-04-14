package com.androlua;

import android.os.FileObserver;

public class LuaFileObserver extends FileObserver {
    private OnEventListener a;

    public interface OnEventListener {
        void onEvent(int i, String str);
    }

    public LuaFileObserver(String str) {
        super(str);
    }

    public LuaFileObserver(String str, int i) {
        super(str, i);
    }

    public void onEvent(int i, String str) {
        if (this.a != null) {
            this.a.onEvent(i, str);
        }
    }

    public void setOnEventListener(OnEventListener onEventListener) {
        this.a = onEventListener;
    }
}
