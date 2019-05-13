package com.android.httpdemo.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.android.httpdemo.R;
import com.google.gson.JsonObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

  TextView tv;
  String url = "http://api.test.biaoqing.com//api/v3.0/feed";
  private Button button;
  private Retrofit retrofit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tv = findViewById(R.id.tv);
    button = findViewById(R.id.button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        tv.setText("ready..");
        httpRequst2();
      }
    });
    //OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    Builder builder = new Builder()
        .baseUrl("http://api.test.biaoqing.com//")
        .addConverterFactory(GsonConverterFactory.create());
    retrofit = builder.build();

  }

  public void httpRequst() {
    final Call<ResponseBody> test = retrofit.create(Api.class).getResponseBody();
    test.enqueue(new Callback<ResponseBody>() {
      @Override
      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
          String string = response.body().string();
          tv.setText(string);
        } catch (IOException e) {
          e.printStackTrace();
          tv.setText(e.toString());
        }
      }

      @Override
      public void onFailure(Call<ResponseBody> call, Throwable t) {

      }
    });
  }
  public void httpRequst2() {
    Call<JsonObject> json = retrofit.create(Api.class).getJson();
    json.enqueue(new Callback<JsonObject>() {
      @Override
      public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
          String string = response.toString();
          tv.setText(string);
      }

      @Override
      public void onFailure(Call<JsonObject> call, Throwable t) {

      }
    });
  }
}
