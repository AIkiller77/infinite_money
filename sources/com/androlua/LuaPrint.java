package com.androlua;

import com.luajava.JavaFunction;
import com.luajava.LuaState;

public class LuaPrint extends JavaFunction {
    private LuaState a;
    private LuaContext c;
    private StringBuilder d = new StringBuilder();

    public LuaPrint(LuaContext luaContext, LuaState luaState) {
        super(luaState);
        this.a = luaState;
        this.c = luaContext;
    }

    public int execute() {
        if (this.a.getTop() < 2) {
            this.c.sendMsg("");
            return 0;
        }
        for (int i = 2; i <= this.a.getTop(); i++) {
            String str = null;
            String typeName = this.a.typeName(this.a.type(i));
            if (typeName.equals("userdata")) {
                Object javaObject = this.a.toJavaObject(i);
                if (javaObject != null) {
                    str = javaObject.toString();
                }
            } else {
                str = typeName.equals("boolean") ? this.a.toBoolean(i) ? "true" : "false" : this.a.toString(i);
            }
            if (str != null) {
                typeName = str;
            }
            this.d.append("\t");
            this.d.append(typeName);
            this.d.append("\t");
        }
        this.c.sendMsg(this.d.toString().substring(1, this.d.length() - 1));
        this.d.setLength(0);
        return 0;
    }
}
