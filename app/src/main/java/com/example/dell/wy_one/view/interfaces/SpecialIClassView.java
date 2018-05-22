package com.example.dell.wy_one.view.interfaces;

import com.example.dell.wy_one.model.bean.SpecialClassBean;

public interface SpecialIClassView extends BaseIView {
    void onSuccess(SpecialClassBean specialClassBean);

    void onError(Throwable throwable);
}
