package com.androlua;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

class LuaCameraView extends SurfaceView {
    private SurfaceHolder a;

    public LuaCameraView(Context context) {
        super(context);
        this.a = null;
        this.a = getHolder();
        this.a.addCallback(new SurfaceHolder.Callback() {
            private Camera b;

            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                Camera.Parameters parameters = this.b.getParameters();
                parameters.setPictureFormat(256);
                parameters.setPreviewSize(854, 480);
                parameters.setFocusMode("auto");
                parameters.setPictureSize(2592, 1456);
                this.b.startPreview();
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                this.b = Camera.open();
                try {
                    this.b.setPreviewDisplay(surfaceHolder);
                } catch (IOException unused) {
                    this.b = null;
                }
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                this.b.stopPreview();
                this.b.release();
                this.b = null;
            }
        });
        this.a.setType(3);
    }
}
