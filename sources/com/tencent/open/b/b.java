package com.tencent.open.b;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

/* compiled from: ProGuard */
public class b implements Serializable {
    public final HashMap<String, String> a;

    public b(Bundle bundle) {
        HashMap<String, String> hashMap;
        Bundle bundle2 = bundle;
        new HashMap<>();
        this.a = hashMap;
        if (bundle2 != null) {
            for (String str : bundle2.keySet()) {
                String put = this.a.put(str, bundle2.getString(str));
            }
        }
    }
}
