package com.android.cglib.dx.c.b;

import com.android.cglib.dx.d.i;
import com.tencent.qq.widget.R;

public final class l {
    public static String a(int i) {
        switch (i) {
            case 1:
                return "nop";
            case 2:
                return "move";
            case 3:
                return "move-param";
            case 4:
                return "move-exception";
            case 5:
                return "const";
            case 6:
                return "goto";
            case 7:
                return "if-eq";
            case 8:
                return "if-ne";
            case 9:
                return "if-lt";
            case 10:
                return "if-ge";
            case 11:
                return "if-le";
            case 12:
                return "if-gt";
            case 13:
                return "switch";
            case 14:
                return "add";
            case 15:
                return "sub";
            case 16:
                return "mul";
            case 17:
                return "div";
            case 18:
                return "rem";
            case 19:
                return "neg";
            case 20:
                return "and";
            case 21:
                return "or";
            case 22:
                return "xor";
            case 23:
                return "shl";
            case 24:
                return "shr";
            case 25:
                return "ushr";
            case 26:
                return "not";
            case 27:
                return "cmpl";
            case 28:
                return "cmpg";
            case R.styleable.AppCompatTheme_actionModeBackground /*29*/:
                return "conv";
            case 30:
                return "to-byte";
            case R.styleable.AppCompatTheme_actionModeCloseDrawable /*31*/:
                return "to-char";
            case 32:
                return "to-short";
            case 33:
                return "return";
            case 34:
                return "array-length";
            case 35:
                return "throw";
            case 36:
                return "monitor-enter";
            case 37:
                return "monitor-exit";
            case 38:
                return "aget";
            case 39:
                return "aput";
            case 40:
                return "new-instance";
            case 41:
                return "new-array";
            case 42:
                return "filled-new-array";
            case 43:
                return "check-cast";
            case 44:
                return "instance-of";
            case 45:
                return "get-field";
            case 46:
                return "get-static";
            case 47:
                return "put-field";
            case 48:
                return "put-static";
            case R.styleable.AppCompatTheme_homeAsUpIndicator /*49*/:
                return "invoke-static";
            case 50:
                return "invoke-virtual";
            case R.styleable.AppCompatTheme_buttonBarStyle /*51*/:
                return "invoke-super";
            case R.styleable.AppCompatTheme_buttonBarButtonStyle /*52*/:
                return "invoke-direct";
            case R.styleable.AppCompatTheme_selectableItemBackground /*53*/:
                return "invoke-interface";
            case R.styleable.AppCompatTheme_borderlessButtonStyle /*55*/:
                return "move-result";
            case R.styleable.AppCompatTheme_dividerVertical /*56*/:
                return "move-result-pseudo";
            case R.styleable.AppCompatTheme_dividerHorizontal /*57*/:
                return "fill-array-data";
            default:
                return "unknown-" + i.e(i);
        }
    }
}
