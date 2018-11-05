package com.linkdev.linkdevlopmenttask.network;



import com.linkdev.linkdevlopmenttask.model.response.ArticalsModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface EndPointInterfaces {


    @GET("articles?")
    Observable<ArticalsModel> getArticles(@Query("source") String source , @Query("apiKey") String apiKey);



}
