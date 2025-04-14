package com.nirenr.screencapture;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.a.a.a.a.a.a.a;

@TargetApi(21)
public class ScreenCaptureActivity extends Activity {
    public static final int REQUEST_MEDIA_PROJECTION = 18;
    private TextView a;

    public void finish() {
        if (Build.VERSION.SDK_INT >= 21) {
            finishAndRemoveTask();
        } else {
            super.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 18) {
            ScreenShot.setResultData((Intent) null);
        } else if (i2 == -1 && intent != null) {
            ScreenShot.setResultData(intent);
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new TextView(this);
        this.a.setText("请授予权限");
        setContentView(this.a);
        requesturePermission();
    }

    public void requesturePermission() {
        if (Build.VERSION.SDK_INT < 21) {
            Toast.makeText(this, "仅支持安卓5以上系统", 0).show();
            return;
        }
        try {
            startActivityForResult(((MediaProjectionManager) getSystemService("media_projection")).createScreenCaptureIntent(), 18);
        } catch (Exception e) {
            a.a(e);
            ScreenShot.setResultData((Intent) null);
        }
    }
}
