package com.tencent.connect.common;

import android.content.Intent;
import com.tencent.open.a.f;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class UIListenerManager {
    private static UIListenerManager a = null;
    private Map<String, ApiTask> b;

    /* compiled from: ProGuard */
    public class ApiTask {
        final /* synthetic */ UIListenerManager a;
        public IUiListener mListener;
        public int mRequestCode;

        public ApiTask(UIListenerManager uIListenerManager, int i, IUiListener iUiListener) {
            this.a = uIListenerManager;
            this.mRequestCode = i;
            this.mListener = iUiListener;
        }
    }

    private UIListenerManager() {
        Map map;
        Map map2;
        new HashMap();
        this.b = Collections.synchronizedMap(map);
        if (this.b == null) {
            new HashMap();
            this.b = Collections.synchronizedMap(map2);
        }
    }

    private IUiListener a(int i, IUiListener iUiListener) {
        int i2 = i;
        IUiListener iUiListener2 = iUiListener;
        if (i2 == 11101) {
            f.e("openSDK_LOG.UIListenerManager", "登录的接口回调不能重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i2 == 11105) {
            f.e("openSDK_LOG.UIListenerManager", "Social Api 的接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i2 == 11106) {
            f.e("openSDK_LOG.UIListenerManager", "Social Api 的H5接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        }
        return iUiListener2;
    }

    public static UIListenerManager getInstance() {
        UIListenerManager uIListenerManager;
        if (a == null) {
            new UIListenerManager();
            a = uIListenerManager;
        }
        return a;
    }

    /* JADX INFO: finally extract failed */
    public IUiListener getListnerWithAction(String str) {
        String str2 = str;
        if (str2 == null) {
            f.e("openSDK_LOG.UIListenerManager", "getListnerWithAction action is null!");
            return null;
        }
        Map<String, ApiTask> map = this.b;
        Map<String, ApiTask> map2 = map;
        synchronized (map) {
            try {
                ApiTask apiTask = this.b.get(str2);
                ApiTask remove = this.b.remove(str2);
                if (apiTask == null) {
                    return null;
                }
                return apiTask.mListener;
            } catch (Throwable th) {
                Throwable th2 = th;
                Map<String, ApiTask> map3 = map2;
                throw th2;
            }
        }
    }

    public IUiListener getListnerWithRequestCode(int i) {
        StringBuilder sb;
        int i2 = i;
        String a2 = h.a(i2);
        if (a2 != null) {
            return getListnerWithAction(a2);
        }
        new StringBuilder();
        f.e("openSDK_LOG.UIListenerManager", sb.append("getListner action is null! rquestCode=").append(i2).toString());
        return null;
    }

    public void handleDataToListener(Intent intent, IUiListener iUiListener) {
        UiError uiError;
        StringBuilder sb;
        Object obj;
        UiError uiError2;
        StringBuilder sb2;
        StringBuilder sb3;
        UiError uiError3;
        Object obj2;
        UiError uiError4;
        Intent intent2 = intent;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.UIListenerManager", "handleDataToListener");
        if (intent2 == null) {
            iUiListener2.onCancel();
            return;
        }
        String stringExtra = intent2.getStringExtra(Constants.KEY_ACTION);
        if ("action_login".equals(stringExtra)) {
            int intExtra = intent2.getIntExtra(Constants.KEY_ERROR_CODE, 0);
            if (intExtra == 0) {
                String stringExtra2 = intent2.getStringExtra(Constants.KEY_RESPONSE);
                if (stringExtra2 != null) {
                    try {
                        iUiListener2.onComplete(k.d(stringExtra2));
                    } catch (JSONException e) {
                        new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra2);
                        iUiListener2.onError(uiError4);
                        f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", e);
                    }
                } else {
                    f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
                    new JSONObject();
                    iUiListener2.onComplete(obj2);
                }
            } else {
                new StringBuilder();
                f.e("openSDK_LOG.UIListenerManager", sb3.append("OpenUi, onActivityResult, onError = ").append(intExtra).append("").toString());
                new UiError(intExtra, intent2.getStringExtra(Constants.KEY_ERROR_MSG), intent2.getStringExtra(Constants.KEY_ERROR_DETAIL));
                iUiListener2.onError(uiError3);
            }
        } else if ("action_share".equals(stringExtra)) {
            String stringExtra3 = intent2.getStringExtra("result");
            String stringExtra4 = intent2.getStringExtra("response");
            if ("cancel".equals(stringExtra3)) {
                iUiListener2.onCancel();
            } else if ("error".equals(stringExtra3)) {
                new StringBuilder();
                new UiError(-6, "unknown error", sb2.append(stringExtra4).append("").toString());
                iUiListener2.onError(uiError2);
            } else if ("complete".equals(stringExtra3)) {
                try {
                    Object obj3 = obj;
                    new JSONObject(stringExtra4 == null ? "{\"ret\": 0}" : stringExtra4);
                    iUiListener2.onComplete(obj3);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    new StringBuilder();
                    new UiError(-4, "json error", sb.append(stringExtra4).append("").toString());
                    iUiListener2.onError(uiError);
                }
            }
        }
    }

    public boolean onActivityResult(int i, int i2, Intent intent, IUiListener iUiListener) {
        StringBuilder sb;
        UiError uiError;
        Object obj;
        UiError uiError2;
        UiError uiError3;
        StringBuilder sb2;
        Object obj2;
        UiError uiError4;
        StringBuilder sb3;
        StringBuilder sb4;
        UiError uiError5;
        Object obj3;
        UiError uiError6;
        UiError uiError7;
        int i3 = i;
        int i4 = i2;
        Intent intent2 = intent;
        IUiListener iUiListener2 = iUiListener;
        new StringBuilder();
        f.c("openSDK_LOG.UIListenerManager", sb.append("onActivityResult req=").append(i3).append(" res=").append(i4).toString());
        IUiListener listnerWithRequestCode = getListnerWithRequestCode(i3);
        if (listnerWithRequestCode == null) {
            if (iUiListener2 != null) {
                listnerWithRequestCode = a(i3, iUiListener2);
            } else {
                f.e("openSDK_LOG.UIListenerManager", "onActivityResult can't find the listener");
                return false;
            }
        }
        if (i4 != -1) {
            listnerWithRequestCode.onCancel();
        } else if (null == intent2) {
            new UiError(-6, "onActivityResult intent data is null.", "onActivityResult intent data is null.");
            listnerWithRequestCode.onError(uiError7);
            return true;
        } else {
            String stringExtra = intent2.getStringExtra(Constants.KEY_ACTION);
            if ("action_login".equals(stringExtra)) {
                int intExtra = intent2.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra == 0) {
                    String stringExtra2 = intent2.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra2 != null) {
                        try {
                            listnerWithRequestCode.onComplete(k.d(stringExtra2));
                        } catch (JSONException e) {
                            new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra2);
                            listnerWithRequestCode.onError(uiError6);
                            f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", e);
                        }
                    } else {
                        f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
                        new JSONObject();
                        listnerWithRequestCode.onComplete(obj3);
                    }
                } else {
                    new StringBuilder();
                    f.e("openSDK_LOG.UIListenerManager", sb4.append("OpenUi, onActivityResult, onError = ").append(intExtra).append("").toString());
                    new UiError(intExtra, intent2.getStringExtra(Constants.KEY_ERROR_MSG), intent2.getStringExtra(Constants.KEY_ERROR_DETAIL));
                    listnerWithRequestCode.onError(uiError5);
                }
            } else if ("action_share".equals(stringExtra)) {
                String stringExtra3 = intent2.getStringExtra("result");
                String stringExtra4 = intent2.getStringExtra("response");
                if ("cancel".equals(stringExtra3)) {
                    listnerWithRequestCode.onCancel();
                } else if ("error".equals(stringExtra3)) {
                    new StringBuilder();
                    new UiError(-6, "unknown error", sb3.append(stringExtra4).append("").toString());
                    listnerWithRequestCode.onError(uiError4);
                } else if ("complete".equals(stringExtra3)) {
                    try {
                        Object obj4 = obj2;
                        new JSONObject(stringExtra4 == null ? "{\"ret\": 0}" : stringExtra4);
                        listnerWithRequestCode.onComplete(obj4);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        new StringBuilder();
                        new UiError(-4, "json error", sb2.append(stringExtra4).append("").toString());
                        listnerWithRequestCode.onError(uiError3);
                    }
                }
            } else {
                int intExtra2 = intent2.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra2 == 0) {
                    String stringExtra5 = intent2.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra5 != null) {
                        try {
                            listnerWithRequestCode.onComplete(k.d(stringExtra5));
                        } catch (JSONException e3) {
                            JSONException jSONException = e3;
                            new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra5);
                            listnerWithRequestCode.onError(uiError2);
                        }
                    } else {
                        new JSONObject();
                        listnerWithRequestCode.onComplete(obj);
                    }
                } else {
                    new UiError(intExtra2, intent2.getStringExtra(Constants.KEY_ERROR_MSG), intent2.getStringExtra(Constants.KEY_ERROR_DETAIL));
                    listnerWithRequestCode.onError(uiError);
                }
            }
        }
        return true;
    }

    public Object setListenerWithRequestcode(int i, IUiListener iUiListener) {
        Object obj;
        StringBuilder sb;
        int i2 = i;
        IUiListener iUiListener2 = iUiListener;
        String a2 = h.a(i2);
        if (a2 == null) {
            new StringBuilder();
            f.e("openSDK_LOG.UIListenerManager", sb.append("setListener action is null! rquestCode=").append(i2).toString());
            return null;
        }
        Map<String, ApiTask> map = this.b;
        Map<String, ApiTask> map2 = map;
        synchronized (map) {
            try {
                new ApiTask(this, i2, iUiListener2);
                ApiTask put = this.b.put(a2, obj);
                if (put == null) {
                    return null;
                }
                return put.mListener;
            } catch (Throwable th) {
                Throwable th2 = th;
                Map<String, ApiTask> map3 = map2;
                throw th2;
            }
        }
    }

    public Object setListnerWithAction(String str, IUiListener iUiListener) {
        Object obj;
        StringBuilder sb;
        String str2 = str;
        IUiListener iUiListener2 = iUiListener;
        int a2 = h.a(str2);
        if (a2 == -1) {
            new StringBuilder();
            f.e("openSDK_LOG.UIListenerManager", sb.append("setListnerWithAction fail, action = ").append(str2).toString());
            return null;
        }
        Map<String, ApiTask> map = this.b;
        Map<String, ApiTask> map2 = map;
        synchronized (map) {
            try {
                new ApiTask(this, a2, iUiListener2);
                ApiTask put = this.b.put(str2, obj);
                if (put == null) {
                    return null;
                }
                return put.mListener;
            } catch (Throwable th) {
                Throwable th2 = th;
                Map<String, ApiTask> map3 = map2;
                throw th2;
            }
        }
    }
}
