package textjunit.lizhongxin.demo.ui;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import textjunit.lizhongxin.demo.R;

public class TestAnimeActivity extends AppCompatActivity {

  private float x;
  private float y;
  private float lastX;
  private float lastY;
  private int top;
  private int bottom;
  private float offsetALLY;
  private float offsetAllX;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_anime);
    View iv = findViewById(R.id.iv);
    View root = findViewById(R.id.root);
    float Standard = 1000;
    float maxScale = 1;
    float minScale = 0.5f;
    iv.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            x = event.getX();
            y = event.getY();
            if (top == 0) {
              top = iv.getTop();
              bottom = iv.getBottom();
            }
            break;
          case MotionEvent.ACTION_MOVE:
            if (event.getPointerCount() == 1) {
              float offsetX = event.getX() - x;
              float offsetY = event.getY() - y;
              offsetAllX += offsetX;
              offsetALLY += offsetY;
//              if (Math.abs(event.getX() - lastX) < 1) {
//                return true;
//              }
//              if (Math.abs(event.getY() - lastY) < 1) {
//                return true;
//              }

              float offsetScale = Math.abs(offsetALLY) / Standard;
              Log.d("onTouchTest",
                  "offsetALLY:" + offsetALLY+ ",offsetScale:" + offsetScale
                      + ",offsetX:" + offsetX);
              offsetScale = Math.min(offsetScale, minScale);
              v.setScaleX(1 - offsetScale);
              v.setScaleY(1 - offsetScale);
              root.setAlpha(1 - offsetScale);
              v.offsetLeftAndRight((int) (offsetX*v.getScaleX()));
              v.offsetTopAndBottom((int) (offsetY*v.getScaleY()));
              Log.d("onTouchTest", "offsetScale:" + offsetScale+",(1-offsetScale)*255:"+(1-offsetScale)*255);
              lastX = event.getX();
              lastY = event.getY();
            }
            break;
          case MotionEvent.ACTION_UP:
            ActivityCompat.finishAfterTransition(TestAnimeActivity.this);
            break;
        }
        return true;
      }
    });
//    Fade returnTransition = null;
//    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
//      returnTransition = new Fade();
//      returnTransition.setDuration(500);
//      returnTransition.setStartDelay(300);
//      getWindow().setReturnTransition(returnTransition);
//    }
  }

  public void back(View view) {
    overridePendingTransition(0,R.anim.preview_out);
//    ActivityCompat.finishAfterTransition(this);
    finish();
  }
}
