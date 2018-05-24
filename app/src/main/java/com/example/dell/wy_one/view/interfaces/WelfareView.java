package com.example.dell.wy_one.view.interfaces;

import okhttp3.ResponseBody;

public interface WelfareView extends BaseIView {
    void onSuccess(ResponseBody responseBody);

    void onError(Throwable throwable);
}
