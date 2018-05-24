package com.example.dell.wy_one.view.interfaces;


import com.example.dell.wy_one.model.bean.CommentBean;

public interface CommentIView extends BaseIView {
    void onSuccess(CommentBean commentBean);

    void onError(Throwable throwable);
}
