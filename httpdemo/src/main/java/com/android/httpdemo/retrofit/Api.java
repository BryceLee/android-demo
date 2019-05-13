package com.android.httpdemo.retrofit;

import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
  @GET("api/v3.0/feed")
  Call<ResponseBody> getResponseBody();

  @GET("api/v3.0/feed")
  Call<JsonObject> getJson();

}
