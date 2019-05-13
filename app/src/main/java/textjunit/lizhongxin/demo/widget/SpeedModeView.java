package textjunit.lizhongxin.demo.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import textjunit.lizhongxin.demo.R;

/**
 * Created by lizhongxin on 08/11/2018.
 */

public class SpeedModeView extends RelativeLayout {

  public interface onClickSpeedListener {

    void normal();

    void speed();
  }

  onClickSpeedListener onClickSpeedListener;

  public void setOnClickSpeedListener(onClickSpeedListener l) {
    this.onClickSpeedListener = l;
  }

  private int selectColor;
  private int unSelectColor;

  public SpeedModeView(Context context) {
    super(context);
    init(context);
  }

  private void init(Context context) {
    selectColor = Color.parseColor("#16151A");
    unSelectColor = Color.parseColor("#ffffff");
    View inflate = LayoutInflater.from(context).inflate(R.layout.layout_speed, this);
    View viewSelect = inflate.findViewById(R.id.viewSelect);
    TextView mTvNormal = inflate.findViewById(R.id.left);
    TextView mTvSpeed = inflate.findViewById(R.id.right);
    mTvNormal.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (onClickSpeedListener != null) {
          onClickSpeedListener.normal();
        }
        Log.e("mTvNormal.getWidth()", "mTvNormal.getWidth():" + mTvNormal.getWidth());
        ObjectAnimator.ofFloat(viewSelect, "translationX", viewSelect.getTranslationX(),
            0)
            .setDuration(200)
            .start();
        mTvNormal.setTextColor(selectColor);
        mTvSpeed.setTextColor(unSelectColor);
      }
    });

    mTvSpeed.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (onClickSpeedListener != null) {
          onClickSpeedListener.speed();
        }
        ObjectAnimator.ofFloat(viewSelect, "translationX", viewSelect.getTranslationX(),
            mTvNormal.getWidth())
            .setDuration(200)
            .start();
        mTvSpeed.setTextColor(selectColor);
        mTvNormal.setTextColor(unSelectColor);
      }
    });
  }

  public SpeedModeView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public SpeedModeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }
}
