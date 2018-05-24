package com.example.dell.wy_one.presenter;

import com.example.dell.wy_one.model.bean.CommentBean;
import com.example.dell.wy_one.model.http.HttpConfig;
import com.example.dell.wy_one.model.http.RetrofitUtils;
import com.example.dell.wy_one.view.interfaces.CommentIView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommentPresenter extends BasePresenter<CommentIView>{
    public  void CommentShuju(){
        Map<String, String> map=new HashMap<>();
        map.put("mediaId","CMCC_00000000000000001_621653189");
        RetrofitUtils.getService().getComment(HttpConfig.PINGLUN,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentBean commentBean) {
                        getView().onSuccess(commentBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
