package textjunit.lizhongxin.demo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import textjunit.lizhongxin.demo.R;


/**
 * Created by lizhongxin on 2018/3/1.
 */

public class ModeView extends View {

    private Paint paint;
    private int offset;
    private int VideoX;
    private float measureText;

    public ModeView(Context context) {
        super(context);
        init(context);
    }

    public ModeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ModeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public mode currentmode = mode.recordvideo;

    public enum mode {
        recordvideo, photo, pingpang
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()!=MotionEvent.ACTION_DOWN){
            return false;
        }
        float x = event.getX();
        float percent=x/getMeasuredWidth();
        Log.d("btn", "percent:"+percent+",currentmode:"+currentmode.toString());
        switch (currentmode) {
            case recordvideo:
                if (percent > 0.65f&&percent<=0.8f) {
                    changMode(mode.photo);
                } else if (percent > 0.8f && percent <=1f) {
                    changMode(mode.pingpang);
                }
                break;
            case photo:
                if (percent >0.65f&&percent<=0.8f) {
                    changMode(mode.pingpang);
                } else if (percent > 0.2&&percent<=0.35) {
                    changMode(mode.recordvideo);
                }
                break;
            case pingpang:
                if (percent >=0&&percent<=0.15) {
                    changMode(mode.recordvideo);
                } else if (percent >=0.15&&percent<=0.35) {
                    changMode(mode.photo);
                }
                break;
        }
        return true;
    }

    private long animetime = 500;

    public void changMode(mode tomode) {
        int current=VideoX;
        currentmode = tomode;
        Log.d("btn", "currentmode:"+tomode.toString());
        switch (tomode) {
            case recordvideo:
                ValueAnimator anim1 = ValueAnimator.ofInt(current, getMeasuredWidth() / 2).setDuration(animetime);
                anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        VideoX= (int) valueAnimator.getAnimatedValue();
                        invalidate();
                    }
                });
                anim1.start();
                break;
            case photo:
                ValueAnimator anim2 = ValueAnimator.ofInt(current, getMeasuredWidth() / 2 - offset).setDuration(animetime);
                anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        VideoX= (int) valueAnimator.getAnimatedValue();
                        invalidate();
                    }
                });
                anim2.start();
                break;
            case pingpang:
                ValueAnimator anim3 = ValueAnimator.ofInt(current, getMeasuredWidth() / 2 - 2*offset).setDuration(animetime);
                anim3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        VideoX= (int) valueAnimator.getAnimatedValue();
                        invalidate();
                    }
                });
                anim3.start();
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (measureText==0){
            measureText = paint.measureText("拍照");
        }
        if (offset == 0) {
            offset = (int) (getMeasuredWidth()-2*measureText)/4;
        }
        if (VideoX == 0) {
            //init
            VideoX = getMeasuredWidth() / 2;
        }
        Log.d("btn","videox:"+VideoX);
        canvas.drawARGB(200, 125, 12, 188);
        paint.setColor(currentmode == mode.recordvideo ? Color.WHITE : getResources().getColor(R.color.colorPrimary));
        canvas.drawText("拍摄", VideoX, getMeasuredHeight() / 2, paint);
        paint.setColor(currentmode == mode.photo ? Color.WHITE : getResources().getColor(R.color.colorPrimary));
        canvas.drawText("拍照", VideoX + offset, getMeasuredHeight() / 2, paint);
        paint.setColor(currentmode == mode.pingpang ? Color.WHITE : getResources().getColor(R.color.colorPrimary));
        canvas.drawText("来回", VideoX + 2 * offset, getMeasuredHeight() / 2, paint);
        paint.setColor(Color.WHITE);
        RectF rectF = new RectF(getMeasuredWidth() / 2 - 20, getMeasuredHeight() - 20, getMeasuredWidth() / 2 + 20,
                getMeasuredHeight() - 10);
        canvas.drawRoundRect(rectF, 5, 5, paint);
    }


    private void init(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
    }
}
