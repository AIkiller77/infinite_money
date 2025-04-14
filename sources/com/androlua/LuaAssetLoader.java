package com.androlua;

import android.content.Context;
import com.luajava.JavaFunction;
import com.luajava.LuaState;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LuaAssetLoader extends JavaFunction {
    private LuaState a;
    private Context c;

    public LuaAssetLoader(LuaContext luaContext, LuaState luaState) {
        super(luaState);
        this.a = luaState;
        this.c = luaContext.getContext();
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public int execute() {
        String luaState = this.a.toString(-1);
        String str = luaState.replace('.', '/') + ".lua";
        try {
            if (this.a.LloadBuffer(readAsset(str), str) != 0) {
                this.a.pushString("\n\t" + this.a.toString(-1));
            }
            return 1;
        } catch (IOException unused) {
            this.a.pushString("\n\tno file '/assets/" + str + "'");
            return 1;
        }
    }

    public byte[] readAsset(String str) {
        InputStream open = this.c.getAssets().open(str);
        byte[] a2 = a(open);
        open.close();
        return a2;
    }
}
