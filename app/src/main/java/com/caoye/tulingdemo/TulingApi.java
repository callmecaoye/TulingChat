package com.caoye.tulingdemo;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by admin on 7/25/16.
 */
public interface TulingApi {
    @FormUrlEncoded
    @POST("openapi/api")
    Call<ResponseBody> getRetrofitResponse(@Field("key") String key, @Field("info") String content);
}
