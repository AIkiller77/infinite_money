package com.luajava;

import java.util.HashMap;
import java.util.Map;

public final class LuaStateFactory {
    private static final Map<Long, LuaState> a = new HashMap();

    private LuaStateFactory() {
    }

    public static synchronized LuaState getExistingState(long j) {
        LuaState luaState;
        synchronized (LuaStateFactory.class) {
            luaState = a.get(Long.valueOf(j));
            if (luaState == null) {
                luaState = new LuaState(j);
                a.put(Long.valueOf(j), luaState);
            }
        }
        return luaState;
    }

    public static synchronized long insertLuaState(LuaState luaState) {
        long pointer;
        synchronized (LuaStateFactory.class) {
            a.put(Long.valueOf(luaState.getPointer()), luaState);
            pointer = luaState.getPointer();
        }
        return pointer;
    }

    public static synchronized LuaState newLuaState() {
        LuaState luaState;
        synchronized (LuaStateFactory.class) {
            luaState = new LuaState();
            a.put(Long.valueOf(luaState.getPointer()), luaState);
        }
        return luaState;
    }

    public static synchronized void removeLuaState(long j) {
        synchronized (LuaStateFactory.class) {
            a.put(Long.valueOf(j), (Object) null);
        }
    }
}
