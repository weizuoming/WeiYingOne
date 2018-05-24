package com.example.dell.wy_one.model.interfaces;

import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.model.bean.SpecialClassBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MyService {
    @FormUrlEncoded
    @POST("{url}")
    Observable<ChoicenessBean>  getChoiceness(@Path(value = "url",encoded = true) String url, @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("{url}")
    Observable<SpecialClassBean>  getSpeciali(@Path(value = "url",encoded = true) String url, @FieldMap Map<String,String> map);


    @GET("{url}")
    Observable<ResponseBody>  getWelfare(@Path(value = "url",encoded = true) String url, @QueryMap Map<String,String> map);



}
