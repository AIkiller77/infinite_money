package com.androlua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LuaBroadcastReceiver extends BroadcastReceiver {
    private OnReceiveListener a;

    public interface OnReceiveListener {
        void onReceive(Context context, Intent intent);
    }

    public interface OnReceiveListerer {
        void onReceive(Context context, Intent intent);
    }

    public LuaBroadcastReceiver(OnReceiveListener onReceiveListener) {
        this.a = onReceiveListener;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.onReceive(context, intent);
    }
}
