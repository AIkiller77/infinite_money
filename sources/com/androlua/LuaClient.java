package com.androlua;

import android.util.Log;
import com.a.a.a.a.a.a.a;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class LuaClient implements LuaGcable {
    /* access modifiers changed from: private */
    public OnReadLineListener a;
    private Socket b;
    /* access modifiers changed from: private */
    public BufferedReader c;
    /* access modifiers changed from: private */
    public BufferedWriter d;
    private boolean e;

    public interface OnReadLineListener {
        void onReadLine(LuaClient luaClient, SocketThread socketThread, String str);
    }

    private class SocketThread extends Thread {
        private final Socket b;

        public SocketThread(Socket socket) {
            this.b = socket;
        }

        public boolean close() {
            try {
                this.b.close();
                return true;
            } catch (Exception e) {
                a.a(e);
                return false;
            }
        }

        public boolean flush() {
            try {
                LuaClient.this.d.flush();
                return true;
            } catch (Exception e) {
                a.a(e);
                return false;
            }
        }

        public boolean newLine() {
            try {
                LuaClient.this.d.newLine();
                LuaClient.this.d.flush();
                return true;
            } catch (Exception e) {
                a.a(e);
                return false;
            }
        }

        public void run() {
            while (true) {
                try {
                    String readLine = LuaClient.this.c.readLine();
                    if (readLine == null) {
                        return;
                    }
                    if (LuaClient.this.a != null) {
                        LuaClient.this.a.onReadLine(LuaClient.this, this, readLine);
                    }
                } catch (Exception e) {
                    a.a(e);
                    return;
                }
            }
        }

        public boolean write(String str) {
            try {
                Log.i("lua", str);
                LuaClient.this.d.write(str);
                return true;
            } catch (Exception e) {
                a.a(e);
                return false;
            }
        }
    }

    public LuaClient() {
    }

    public LuaClient(LuaContext luaContext) {
        luaContext.regGc(this);
    }

    public boolean flush() {
        try {
            this.d.flush();
            return true;
        } catch (Exception e2) {
            a.a(e2);
            return false;
        }
    }

    public void gc() {
        stop();
        this.e = true;
    }

    public boolean isGc() {
        return this.e;
    }

    public boolean newLine() {
        try {
            this.d.newLine();
            this.d.flush();
            return true;
        } catch (Exception e2) {
            a.a(e2);
            return false;
        }
    }

    public void setOnReadLineListener(OnReadLineListener onReadLineListener) {
        this.a = onReadLineListener;
    }

    public boolean start(String str, int i) {
        if (this.b != null) {
            return false;
        }
        try {
            this.b = new Socket(str, i);
            this.c = new BufferedReader(new InputStreamReader(this.b.getInputStream()));
            this.d = new BufferedWriter(new OutputStreamWriter(this.b.getOutputStream()));
            new SocketThread(this.b).start();
            return true;
        } catch (IOException e2) {
            a.a(e2);
            return false;
        }
    }

    public boolean stop() {
        if (this.b == null) {
            return false;
        }
        try {
            this.b.close();
            return true;
        } catch (Exception e2) {
            a.a(e2);
            return false;
        }
    }

    public boolean write(String str) {
        try {
            this.d.write(str);
            return true;
        } catch (Exception e2) {
            a.a(e2);
            return false;
        }
    }
}
