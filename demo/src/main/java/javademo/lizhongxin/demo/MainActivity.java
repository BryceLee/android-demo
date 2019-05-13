package javademo.lizhongxin.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

  private String path;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    path = "/storage/emulated/0/Pictures/表情说说/FollotShotVideo/followshot_5b46bdc760b68e2bf52564e0e136c6ab.mp4";
    Uri uri = getIntent().getData();
//    openbiaoqing://subjectDetail/{{subjectId}}
    if (uri != null) {
      TextView mTv = findViewById(R.id.tv);
      mTv.setText("scheme:" + uri.getScheme()+"\n"+"host:" + uri.getHost()+"\n"+"subjectId:" + "\n"+uri.getQueryParameter("id"));
//      mTv.setText("scheme:" + uri.getScheme()+"\n"+"host:" + uri.getHost()+"\n"+"subjectId:" + "\n"+uri.getQueryParameter("subjectId"));
    }
  }

  public boolean isMp4File(String path) {
    final int HEAD_COUNT = 3; //gif扩展名的长度
    if (TextUtils.isEmpty(path)) {
      return false;
    }
    boolean ismp4 = false;
    InputStream stream = null;
    try {
      stream = new FileInputStream(path);
      byte[] head = new byte[HEAD_COUNT];
      stream.read(head);
      String imgType = new String(head);
      ismp4 = imgType.equalsIgnoreCase("mp4");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return ismp4;
  }

  public void getFileType(View view) {
    boolean mp4File = isMp4File(path);
    String fileHeader = getFileHeader(path);
  }

  public String getFileHeader(String filePath) {
    if (TextUtils.isEmpty(filePath)) {
      return "";
    }
    File file = new File(filePath);
    if (!file.exists() || file.length() < 4) {
      return "";
    }
    FileInputStream is = null;
    String value = null;
    try {
      is = new FileInputStream(file);
      byte[] b = new byte[3];
      is.read(b, 0, b.length);
      value = bytesToHexString(b);
    } catch (Exception e) {
    } finally {
      if (null != is) {
        try {
          is.close();
        } catch (IOException e) {
        }
      }
    }
    return value;
  }

  private String bytesToHexString(byte[] src) {
    StringBuilder builder = new StringBuilder();
    if (src == null || src.length <= 0) {
      return null;
    }
    String hv;
    for (int i = 0; i < src.length; i++) {
      hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
      if (hv.length() < 2) {
        builder.append(0);
      }
      builder.append(hv);
    }
    return builder.toString();
  }
}
