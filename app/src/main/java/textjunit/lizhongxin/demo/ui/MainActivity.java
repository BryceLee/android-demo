package textjunit.lizhongxin.demo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import textjunit.lizhongxin.demo.BuildConfig;
import textjunit.lizhongxin.demo.R;
import textjunit.lizhongxin.demo.User;
import textjunit.lizhongxin.demo.widget.SeckillCountDownLikePoision;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SeckillCountDownLikePoision countDownLikePoision = (SeckillCountDownLikePoision) findViewById(
        R.id.positioncountdown);
    countDownLikePoision.setEndTime(System.currentTimeMillis()+1000L*60);

    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    //Log信息拦截器
    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      builder.addInterceptor(loggingInterceptor);
      //builder.interceptors().add(new ReadCookiesInterceptor());
      //            builder.interceptors().add(new SaveCookiesInterceptor());
    }
    final Retrofit retrofit = new Retrofit.Builder().client(builder.build())
        .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析
        .baseUrl("http://sjzs-api.25pp.com/").build();
//        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {

      private Call<User> call;

      @Override
      public void onClick(View view) {

//                Toast.makeText(MainActivity.this, "lzx:"+helloDI.toString() , Toast.LENGTH_SHORT).show();
//                View inflate = getLayoutInflater().inflate(R.layout.pop, null);
//                PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams
// .WRAP_CONTENT);
//                popupWindow.showAsDropDown(  findViewById(R.id.btn));

        String p = "{\n" + "  \"id\": -5800043954466239919,\n" + "  \"client\": {\n"
            + "    \"caller\": \"secret.pp" +
            ".client\",\n" + "    \"ex\": {\n" + "      \"osVersion\": 25,\n"
            + "      \"ch\": \"PM_3525\",\n" + "      " +
            "\"cityCode\": \"0592\",\n" + "      \"productId\": 2001,\n"
            + "      \"brand\": \"vivo\",\n" + "      " +
            "\"aid\": \"WlAD8mntK3mzrAmBD4brLQ==\",\n"
            + "      \"utdid\": \"WdrmnS7XeUwDAHSFpSskbjep\"\n" + "    },\n"
            + "    \"versionCode\": 1865,\n" + "    \"VName\": \"6.0.6\",\n" + "    \"puid\": " +
            "\"0391508408608870610001\",\n" + "    \"uuid\": " +
            "\"d3MnEOGiyyTgD\\/F9jX1yDS0YsAopWLN3iC9sNZJGhnl3wkxWzJsDKFUflna2HH\\/TZUEEeQ==\"\n"
            + "  },\n" + "  " +
            "\"data\": [{\n" + "    \"service\": \"resource.app.getDetail\",\n"
            + "    \"data\": {\n" + "      " +
            "\"screenWidth\": 1080,\n" + "      \"appId\": 7653972\n" + "    }\n" + "  }, {\n"
            + "    \"service\": \"op" +
            ".app.article.get\",\n" + "    \"data\": {\n" + "      \"id\": 7653972\n" + "    }\n"
            + "  }, {\n" + "    " +
            "\"service\": \"op.theme.get\",\n" + "    \"data\": {\n"
            + "      \"typeData\": 7653972\n" + "    }\n" + "  " +
            "}, {\n" + "    \"service\": \"op.activiy.simpleActiviy.getByAppId\",\n"
            + "    \"data\": {\n" + "      " +
            "\"type\": 1,\n" + "      \"appId\": 7653972,\n"
            + "      \"packageName\": \"com.biaoqing" +
            ".BiaoQingShuoShuo\"\n" + "    }\n" + "  }, {\n"
            + "    \"service\": \"op.ad.getAdsByAppId\",\n" + "    " +
            "\"data\": {\n" + "      \"screenWidth\": 1080,\n" + "      \"appId\": 7653972,\n"
            + "      \"versionCode\":" +
            " 1865,\n" + "      \"spaceId\": 1635\n" + "    }\n" + "  }],\n" + "  \"sign\": " +
            "\"f7b0c57898a4f71d7ff2baf711bfba65\",\n" + "  \"encrypt\": \"md5\"\n" + "}";
        RequestBody requestBody = RequestBody
            .create(MediaType.parse("application/json; charset=utf-8"), p);

        ApiService apiService = retrofit.create(ApiService.class);
        call = apiService.getUser(requestBody);
        new Thread(new Runnable() {
          @Override
          public void run() {
            call.enqueue(new Callback<User>() {
              @Override
              public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Gson gson = new Gson();
                Log.d("okhttp_result:", gson.toJson(user));
                Log.d("bq:", user.getData().get(0).getData().getApp().getVersionName());
                Log.d("bq:", user.getData().get(0).getData().getApp().getVersionCode() + "");
              }

              @Override
              public void onFailure(Call<User> call, Throwable t) {

              }

//                                @Override
//                                public void onResponse(Response<User> response) {
//                                    //成功後，使用 response.body() 得到結果
//                                    User user = response.body();
//                                }
//                                @Override
//                                public voidonFailure(Throwable t) {
//                                    // 請求失敗
//                                }
            });
          }
        }).start();

      }
    });
  }

  //    http://sjzs-api.25pp.com/api
// /combine?service=resource.app.getDetail,op.app.article.get,op.theme.get,op.activiy.simpleActiviy.getByAppId,op.ad.getAdsByAppId"
  public interface ApiService {

    // 會返回一個 call 類別
    @POST(
        "/api/combine?service=resource.app.getDetail,op.app.article.get,op.theme.get,op.activiy.simpleActiviy.getByAppId,op.ad"
            +
            ".getAdsByAppId")
    Call<User> getUser(@Body RequestBody body);
  }
//    @OnClick(R.id.btn)
//    public void onViewClicked() {
//        Toast.makeText(MainActivity.this, helloDI.toString() + "!", Toast.LENGTH_SHORT).show();
//    }
}
