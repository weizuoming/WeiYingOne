package com.example.dell.wy_one.model.interfaces;

import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.model.bean.CommentBean;
import com.example.dell.wy_one.model.bean.SpecialClassBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyService {
    @FormUrlEncoded
    @POST("{url}")
    Observable<ChoicenessBean>  getChoiceness(@Path(value = "url",encoded = true) String url, @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("{url}")
    Observable<SpecialClassBean>  getSpeciali(@Path(value = "url",encoded = true) String url, @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("{url}")
    Observable<CommentBean> getComment(@Path(value = "url",encoded = true) String url,@FieldMap Map<String,String> map);


}
