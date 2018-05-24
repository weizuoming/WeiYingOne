package com.example.dell.wy_one.view.interfaces;

import com.example.dell.wy_one.model.bean.ChoicenessBean;

public interface SummaryIView extends BaseIView {
    void onSuccess(ChoicenessBean choicenessBean);

    void onError(Throwable throwable);
}
