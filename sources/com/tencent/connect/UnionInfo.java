package com.tencent.connect;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialOperation;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.e;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;

/* compiled from: ProGuard */
public class UnionInfo extends BaseApi {
    public static final String URL_GET_UNION_ID = "https://graph.qq.com/oauth2.0/me";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnionInfo(Context context, QQToken qQToken) {
        super(qQToken);
        Context context2 = context;
    }

    public void getUnionId(IUiListener iUiListener) {
        IRequestListener iRequestListener;
        Bundle a = a();
        a.putString(SocialOperation.GAME_UNION_ID, "1");
        new BaseApi.TempRequestListener(this, iUiListener);
        HttpUtils.requestAsync(this.b, e.a(), URL_GET_UNION_ID, a, Constants.HTTP_GET, iRequestListener);
    }
}
