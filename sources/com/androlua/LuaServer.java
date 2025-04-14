package com.androlua;

import com.a.a.a.a.a.a.a;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LuaServer implements LuaGcable {
    /* access modifiers changed from: private */
    public ServerSocket a;
    /* access modifiers changed from: private */
    public OnReadLineListener b;
    private boolean c;

    public interface OnReadLineListener {
        void onReadLine(LuaServer luaServer, SocketThread socketThread, String str);
    }

    private class ServerThread extends Thread {
        private final ServerSocket b;

        public ServerThread(ServerSocket serverSocket) {
            this.b = serverSocket;
        }

        public void run() {
            while (true) {
                try {
                    new SocketThread(LuaServer.this.a.accept()).start();
                } catch (Exception e) {
                    a.a(e);
                }
            }
        }
    }

    private class SocketThread extends Thread {
        private final Socket b;
        private BufferedWriter c;

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
                this.c.flush();
                return true;
            } catch (Exception e) {
                a.a(e);
                return false;
            }
        }

        public boolean newLine() {
            try {
                this.c.newLine();
                this.c.flush();
                return true;
            } catch (Exception e) {
                a.a(e);
                return false;
            }
        }

        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.b.getInputStream()));
                this.c = new BufferedWriter(new OutputStreamWriter(this.b.getOutputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return;
                    }
                    if (LuaServer.this.b != null) {
                        LuaServer.this.b.onReadLine(LuaServer.this, this, readLine);
                    }
                }
            } catch (Exception e) {
                a.a(e);
            }
        }

        public boolean write(String str) {
            try {
                this.c.write(str);
                return true;
            } catch (Exception e) {
                a.a(e);
                return false;
            }
        }
    }

    public LuaServer() {
    }

    public LuaServer(LuaContext luaContext) {
        luaContext.regGc(this);
    }

    public void gc() {
        if (this.a != null) {
            try {
                this.a.close();
            } catch (Exception e) {
                a.a(e);
            }
            this.c = true;
        }
    }

    public boolean isGc() {
        return this.c;
    }

    public void setOnReadLineListener(OnReadLineListener onReadLineListener) {
        this.b = onReadLineListener;
    }

    public boolean start(int i) {
        if (this.a != null) {
            return false;
        }
        try {
            this.a = new ServerSocket(i);
            new ServerThread(this.a).start();
            return true;
        } catch (Exception e) {
            a.a(e);
            return false;
        }
    }

    public boolean stop() {
        try {
            this.a.close();
            this.a = null;
            return true;
        } catch (Exception e) {
            a.a(e);
            return false;
        }
    }
}
