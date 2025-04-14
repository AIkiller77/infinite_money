package com.tencent.connect;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.e;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;

/* compiled from: ProGuard */
public class UserInfo extends BaseApi {
    public static final String GRAPH_OPEN_ID = "oauth2.0/m_me";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UserInfo(Context context, QQToken qQToken) {
        super(qQToken);
        Context context2 = context;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UserInfo(Context context, c cVar, QQToken qQToken) {
        super(cVar, qQToken);
        Context context2 = context;
    }

    public void getOpenId(IUiListener iUiListener) {
        IRequestListener iRequestListener;
        Bundle a = a();
        new BaseApi.TempRequestListener(this, iUiListener);
        HttpUtils.requestAsync(this.b, e.a(), GRAPH_OPEN_ID, a, Constants.HTTP_GET, iRequestListener);
    }

    public void getUserInfo(IUiListener iUiListener) {
        IRequestListener iRequestListener;
        Bundle a = a();
        new BaseApi.TempRequestListener(this, iUiListener);
        HttpUtils.requestAsync(this.b, e.a(), "user/get_simple_userinfo", a, Constants.HTTP_GET, iRequestListener);
    }
}
