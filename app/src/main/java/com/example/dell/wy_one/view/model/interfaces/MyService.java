package com.example.dell.wy_one.view.model.interfaces;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface MyService {
    @POST("")
    Observable<Object> getData();

}
