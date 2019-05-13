package lzx.avdemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import lzx.avdemo.ijkplayer.IRenderView;
import lzx.avdemo.ijkplayer.IjkVideoView;

public class IjkActivity extends AppCompatActivity {
  private String url5 = "http://stream1.grtn.cn/tvs2/sd/live.m3u8?_ts&time=1518428696629";
  private String url6 = "http://218.207.213.137//PLTV/88888888/224/3221225879/index.m3u8";
  private String url7 = "http://183.251.61.207/PLTV/88888888/224/3221225829/index.m3u8";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ijk);
    IjkVideoView mVideoView = findViewById(R.id.ijkplayer);
    mVideoView.setVideoURI(Uri.parse(url5));
    mVideoView.start();
  }
}
