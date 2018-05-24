package com.example.dell.wy_one.presenter;

import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.model.http.HttpConfig;
import com.example.dell.wy_one.model.http.RetrofitUtils;
import com.example.dell.wy_one.view.interfaces.FindIView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FindPresenter extends BasePresenter <FindIView>{
    public  void FindShuju(){
        Map<String ,String> map=new HashMap<>();

        RetrofitUtils.getService().getChoiceness(HttpConfig.JINGXUAN,map)
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
