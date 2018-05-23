package com.example.dell.wy_one.presenter;

import com.example.dell.wy_one.model.bean.SpecialClassBean;
import com.example.dell.wy_one.model.http.RetrofitUtils;
import com.example.dell.wy_one.view.interfaces.SpecialIClassView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SpecialClassPresenter extends BasePresenter<SpecialIClassView> {
    public void SpecialClassShuju(){
        Map<String ,String> map=new HashMap<>();

        map.put("catalogId","402834815584e463015584e539330016");
        map.put("pnum","7");

        RetrofitUtils.getService().getSpeciali("http://api.svipmovie.com/front/columns/getVideoList.do",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpecialClassBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpecialClassBean specialClassBean) {
                        getView().onSuccess(specialClassBean);
//                        Log.i("asd",specialClassBean.getMsg().toString()+"+++++++++++");
                    }

                    @Override
                    public void onError(Throwable e) {
//                        Log.i("asd",e.getMessage());
                        getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
