package com.b.a.a;

import android.view.KeyEvent;

public class d {
    public static char a(KeyEvent keyEvent) {
        if (e(keyEvent)) {
            return 10;
        }
        if (d(keyEvent)) {
            return 8;
        }
        if (c(keyEvent)) {
            return 9;
        }
        if (f(keyEvent)) {
            return ' ';
        }
        if (keyEvent.isPrintingKey()) {
            return (char) keyEvent.getUnicodeChar(keyEvent.getMetaState());
        }
        return 0;
    }

    public static boolean b(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        return keyCode == 20 || keyCode == 19 || keyCode == 22 || keyCode == 21;
    }

    private static boolean c(KeyEvent keyEvent) {
        return (keyEvent.isShiftPressed() && keyEvent.getKeyCode() == 62) || keyEvent.getKeyCode() == 61;
    }

    private static boolean d(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 67;
    }

    private static boolean e(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 66;
    }

    private static boolean f(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 62;
    }
}
