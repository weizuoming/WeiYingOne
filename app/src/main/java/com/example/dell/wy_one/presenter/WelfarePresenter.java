package com.example.dell.wy_one.presenter;

import com.example.dell.wy_one.model.http.RetrofitUtils;
import com.example.dell.wy_one.view.interfaces.WelfareView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class WelfarePresenter extends BasePresenter<WelfareView> {
    private Disposable d;
    int i=0;
    public void WelfareShuju(){
        Map<String ,String> map=new HashMap<>();
//         map.put("catalogId","402834815584e463015584e539330016");
//        map.put("pnum","7");
        RetrofitUtils.getService().getWelfare("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/"+ i++,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        WelfarePresenter.this.d=d;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        getView().onSuccess(responseBody);

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
