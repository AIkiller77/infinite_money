package com.androlua;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.luajava.LuaObject;
import com.luajava.LuaTable;

public class LuaFragment extends Fragment {
    private LuaTable a = null;
    private LuaObject b = null;
    private View c;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            if (this.c != null) {
                return this.c;
            }
            if (this.a == null) {
                return new TextView(getActivity());
            }
            LuaObject luaObject = this.a.getLuaState().getLuaObject("require");
            Object[] objArr = {"loadlayout"};
            return (View) ((LuaObject) luaObject.call(objArr)).call(this.a);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void setLayout(View view) {
        this.c = view;
        this.a = null;
    }

    public void setLayout(LuaTable luaTable) {
        this.a = luaTable;
        this.c = null;
    }
}
