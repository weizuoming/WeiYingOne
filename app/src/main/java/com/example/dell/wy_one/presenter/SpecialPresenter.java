package com.example.dell.wy_one.presenter;

import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.model.http.RetrofitUtils;
import com.example.dell.wy_one.view.interfaces.SpecialIView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SpecialPresenter extends BasePresenter<SpecialIView> {
    public void SpecialShuju(){
        Map<String ,String> map=new HashMap<>();

        RetrofitUtils.getService().getChoiceness("http://api.svipmovie.com/front/homePageApi/homePage.do",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChoicenessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChoicenessBean choicenessBean) {
                        getView().onSuccess(choicenessBean);
//                        Log.i("asd",choicenessBean.getMsg());
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
