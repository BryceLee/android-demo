package textjunit.lizhongxin.demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import textjunit.lizhongxin.demo.R;


/**
 * Created by lizhongxin on 2018/3/1.
 */

public class CountDownView extends View {

    private Paint paint;
    private Context context;
    private Bitmap count_down_1;
    private Bitmap count_down_2;
    private Bitmap count_down_3;
    private Bitmap bitmap;
    private CountDownView.onCountDownTiemListener onCountDownTiemListener;

    public CountDownView(Context context) {
        super(context);
        init(context);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    interface onCountDownTiemListener {
        void readyGo();
    }
    public void setOnReady(onCountDownTiemListener onCountDownTiemListener){

        this.onCountDownTiemListener = onCountDownTiemListener;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        start();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        canvas.drawARGB(255, 255, 100, 100);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (measuredWidth - bitmap.getWidth()) / 2, (measuredHeight - bitmap.getHeight()) / 2, paint);
        } else {

        }
    }

    final int handlerTag = 0;
    int time;
    @SuppressLint("HandlerLeak")
    private android.os.Handler mhandler = new android.os.Handler() {
        private Drawable record_red_point;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case handlerTag:
                    time += 60;
                    Log.d("time:", "" + time);
                    if (time < 1000) {
                        bitmap = count_down_3;
                        float v = (1000 - time) / 1000f * 255;
                        paint.setAlpha((int) v);
                    } else if (time < 2000) {
                        bitmap = count_down_2;
                        float v = (2000 - time) / 1000f * 255;
                        paint.setAlpha((int) v);
                    } else if (time <= 3000) {
                        bitmap = count_down_1;
                        float v = (2000 - time) / 1000f * 255;
                        paint.setAlpha((int) v);
                    } else {
                        paint.setAlpha(255);
                        if (onCountDownTiemListener!=null){
                            onCountDownTiemListener.readyGo();
                        }
                        bitmap = null;
                        return;
                    }
                    invalidate();
                    mhandler.sendEmptyMessageDelayed(handlerTag, 60);
                    break;
            }
        }
    };

    public void start() {
        if (mhandler == null) {
            return;
        }
        mhandler.removeMessages(handlerTag);
        time = 0;
        mhandler.sendEmptyMessage(handlerTag);
    }

    private void init(Context context) {
        this.context = context;
        count_down_1 = BitmapFactory.decodeResource(getResources(), R.mipmap.count_down_1);
        count_down_2 = BitmapFactory.decodeResource(getResources(), R.mipmap.count_down_2);
        count_down_3 = BitmapFactory.decodeResource(getResources(), R.mipmap.count_down_3);
        paint = new Paint();
        paint.setAntiAlias(true);
//        paint.setStrokeWidth(2);
//        paint.setTextSize(30);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setColor(Color.parseColor("#bebebe"));
//        paint.setTextAlign(Paint.Align.CENTER);
    }
}
