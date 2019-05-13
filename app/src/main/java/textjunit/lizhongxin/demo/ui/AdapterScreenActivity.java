package textjunit.lizhongxin.demo.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import textjunit.lizhongxin.demo.R;

public class AdapterScreenActivity extends AppCompatActivity {

  private EditText editText;
  private EditText editText2;
  private EditText editTextScale;
  private EditText etLeft;
  private EditText etTop;
  private ImageView img;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adapter_screen);
//        float w = getResources().getDimension(R.dimen.bq_px_750);
//        float h = getResources().getDimension(R.dimen.bq_px_1334);
//        Log.d("dimensionw目标W:",""+w);
//        Log.d("dimensionh目标H:",""+h);
//        int screenWidth = getScreenWidth();
//        int screenHeight = getScreenHeight();
//        Log.d("dimension_ScreenWidth:",""+screenWidth);
//        Log.d("dimension_screenHeight:",""+screenHeight);
//        Log.d("dimension-理想dpiw:",""+px2dip(w));
//        Log.d("dimension-理想dpih:",""+px2dip(h));
//        Log.d("dimension-实际dpiW:",""+px2dip(screenWidth));
//        Log.d("dimension-实际dpiH:",""+px2dip(screenHeight));
//
//        File ffmpegFile = new File("");
//        Log.d("ffmpegFile-ffmpegFile:",""+ffmpegFile.exists());
    editText = (EditText) findViewById(R.id.et);
    editText2 = (EditText) findViewById(R.id.et2);
    editTextScale = (EditText) findViewById(R.id.etScale);
//
    etLeft = (EditText) findViewById(R.id.etLeft);
    etTop = (EditText) findViewById(R.id.etTop);

    editText.setText("480");
    editTextScale.setText("1");

    etLeft.setText("403");
    etTop.setText("457");
    //
    img = (ImageView) findViewById(R.id.img);
  }

  public static File getFile() {
    //部分手机需要存到这里，才能被相册找到，比如VIVO
    File mediaStorageDir = getDirectory(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
        "AAA_TEST_BITMAP");
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINESE).format(new Date());
    File mediaFile = null;
    mediaFile = new File(mediaStorageDir.getPath() + File.separator + "png_" + timeStamp + ".png");
    return mediaFile;
  }

  private static File getDirectory(File externalStoragePublicDirectory, String apk) {
    File mediaStorageDir = new File(externalStoragePublicDirectory, apk);
    mediaStorageDir.mkdirs();
    return mediaStorageDir;
  }

  public static Bitmap scale(final Bitmap src, final float scaleWidth, final float scaleHeight,
      final boolean recycle) {
//        if (isEmptyBitmap(src))
//            return null;
    Matrix matrix = new Matrix();
    matrix.setScale(scaleWidth, scaleHeight);
    Bitmap ret = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    if (recycle && !src.isRecycled()) {
      src.recycle();
    }
    return ret;
  }

  /**
   * 这个遍历方案似乎不太好
   */
  public int lengthOfLongestSubstring(String s) {
    int all = 0;
    for (int i = 0; i < s.length(); i++) {
      String curr = s.substring(i, i + 1);
      int currMax = 0;
      for (int j = i + 1; j < s.length(); j++) {
        String next = s.substring(j, j + 1);
        if (!next.equals(curr)) {
          currMax++;
        }
      }
      if (all < currMax) {
        all = currMax;
      }
    }
    return all;
  }
  /**
   * 要不先找到各个字符在字符串里面有几个数字，String有方法可以找到字符在第几个位置
   */

  /**
   * 保存图片
   *
   * @param src 源图片
   * @param file 要保存到的文件
   * @param format 格式
   * @param recycle 是否回收
   * @return {@code true}: 成功<br>{@code false}: 失败
   */
  public boolean save(final Bitmap src, final File file, final Bitmap.CompressFormat format,
      final boolean recycle) {
//        if (isEmptyBitmap(src) || !FileUtils.createOrExistsFile(file))
//            return false;
    System.out.println(src.getWidth() + ", " + src.getHeight());
    OutputStream os = null;
    boolean ret = false;
    try {
      os = new BufferedOutputStream(new FileOutputStream(file));
      ret = src.compress(format, 100, os);
      if (recycle && !src.isRecycled()) {
        src.recycle();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      closeIO(os);
    }
    return ret;
  }

  public void closeIO(final Closeable... closeables) {
    if (closeables == null) {
      return;
    }
    for (Closeable closeable : closeables) {
      if (closeable != null) {
        try {
          closeable.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void start(View view) {
    //在720*720下，89*26是水印的宽高，位于右下角，距离底部8，距离右边21。
    int defaulsize = 720;
    int destWaterMaker_W = Integer.parseInt(editText.getText().toString());
    int destWaterMaker_H = Integer.parseInt(editText.getText().toString());
    int waterMark_W = 89;
    int waterMark_H = 26;
    int waterMark_ML = 26;
    int waterMark_MB = 8;
    float ratio = destWaterMaker_W * 1f / defaulsize;
    //
    /**
     * 把图片放在源水印宽高:103*31,ratio:0.6666667
     */
    Bitmap src = BitmapFactory.decodeResource(getResources(), R.mipmap.watermark_download);
//        Bitmap scaleBitmap = scale(src, ratio, ratio, false);
    //
    Bitmap dst = Bitmap.createBitmap(destWaterMaker_W, destWaterMaker_H, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(dst);
    canvas.setDrawFilter(
        new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
    canvas.drawARGB(100, 100, 100, 100);
    //
    Log.e("watermark", "源水印宽高:" + src.getWidth() + "*" + src.getHeight() + ",ratio:" + ratio);
    int left = (int) (destWaterMaker_W - ratio * (waterMark_W + waterMark_ML));
    int top = (int) (destWaterMaker_H - ratio * (waterMark_H + waterMark_MB));
    Log.e("watermark", "left:" + left + ",top:" + top);
    //****1
//        Rect rect = new Rect(left, top, (int)(left + ratio * waterMark_W), (int)(top + ratio * waterMark_H));
//        canvas.drawBitmap(src,null,rect,new Paint());
    //*****1
    //******2
    Matrix matrix = new Matrix();
    matrix.postScale(ratio, ratio);
    Bitmap newBitmap = Bitmap
        .createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, false);
    canvas.drawBitmap(newBitmap, left, top, new Paint());
    //******2
    File file = getFile();
    boolean save = save(dst, file, Bitmap.CompressFormat.PNG, false);
    if (save) {
      Toast.makeText(AdapterScreenActivity.this, "succeed,file:" + file.getAbsolutePath(),
          Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(AdapterScreenActivity.this, "failed", Toast.LENGTH_LONG).show();
    }
  }

  public void start2(View view) {
    //在720*720下，89*26是水印的宽高，位于右下角，距离底部8，距离右边21。
    int defaulsize = 720;
    int destWaterMaker_W = Integer.parseInt(editText.getText().toString());
    int destWaterMaker_H = Integer.parseInt(editText.getText().toString());
    int waterMark_W = 89;
    int waterMark_H = 26;
    int waterMark_ML = 26;
    int waterMark_MB = 8;
    //
    /**
     * 把图片放在源水印宽高:103*31,ratio:0.6666667
     */
    Bitmap src = BitmapFactory.decodeResource(getResources(), R.mipmap.watermark_download);
    float ratio = destWaterMaker_W * 1f / defaulsize;
    float ratio4bitmap = waterMark_W * 1f / src.getWidth();
//        Bitmap scaleBitmap = scale(src, ratio, ratio, false);
    //
    Bitmap dst = Bitmap.createBitmap(destWaterMaker_W, destWaterMaker_H, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(dst);
    canvas.setDrawFilter(
        new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
    canvas.drawARGB(100, 100, 100, 100);
    //
    Log.e("watermark",
        "源水印宽高:" + src.getWidth() + "*" + src.getHeight() + ",ratio:" + ratio + ",ratioBitmap:"
            + ratio4bitmap +
            ",finalR:" + ratio * ratio4bitmap);
    int ileft = (int) (destWaterMaker_W - ratio * (waterMark_W + waterMark_ML));
    int itop = (int) (destWaterMaker_H - ratio * (waterMark_H + waterMark_MB));

    Log.e("watermark", "理想LEFT_TOP1:" + ileft + "*" + itop);
    int left = Integer.parseInt(etLeft.getText().toString());
    int top = Integer.parseInt(etTop.getText().toString());

    ileft = (int) (destWaterMaker_W * ((defaulsize - waterMark_W - waterMark_ML) * 1f
        / defaulsize));
    itop = (int) (destWaterMaker_H * ((defaulsize - waterMark_H - waterMark_MB) * 1f / defaulsize));
    Log.e("watermark", "理想LEFT_TOP2:" + ileft + "*" + itop);
    etLeft.setText("" + ileft);
    etTop.setText("" + itop);
    //****1
//        Rect rect = new Rect(left, top, (int)(left + ratio * waterMark_W), (int)(top + ratio * waterMark_H));
//        canvas.drawBitmap(src,null,rect,new Paint());
    //*****1
    //******2
    float scale = Float.parseFloat(editTextScale.getText().toString());

    Matrix matrix = new Matrix();
    matrix.postScale(scale, scale);
    matrix.postTranslate(left, top);
//        Bitmap newBitmap = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, false);
    canvas.drawBitmap(src, matrix, new Paint());
    //******2
    File file = getFile();
    boolean save = save(dst, file, Bitmap.CompressFormat.PNG, false);
    if (save) {
//            Toast.makeText(AdapterScreenActivity.this,"succeed,file:"+file.getAbsolutePath(),Toast.LENGTH_LONG).show();
      img.setImageBitmap(BitmapFactory.decodeFile(file.toString()));
      hideSoftInput(AdapterScreenActivity.this);
    } else {
//            Toast.makeText(AdapterScreenActivity.this,"failed",Toast.LENGTH_LONG).show();
    }
  }

  public static void hideSoftInput(final Activity activity) {
    View view = activity.getCurrentFocus();
    if (view == null) {
      view = new View(activity);
    }
    InputMethodManager imm = (InputMethodManager) activity
        .getSystemService(Activity.INPUT_METHOD_SERVICE);
    if (imm == null) {
      return;
    }
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }
//    /**
//     * 将px值转换为dip或dp值，保证尺寸大小不变
//     *
//     * @param pxValue
//     * @return
//     */
//    public  int px2dip(float pxValue) {
//        try {
//            final float scale = getResources().getDisplayMetrics().density;
//            return (int) (pxValue / scale + 0.5f);
//        } catch (Exception e) {
//            return 0;
//        }
//    }
//    public  int getScreenHeight()
//    {
//        WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
//        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
//        return dm.heightPixels;
//    }
//    public  int getScreenWidth()
//    {
//        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
//        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
//        return dm.widthPixels;
//    }
}
