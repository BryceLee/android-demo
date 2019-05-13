package textjunit.lizhongxin.demo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lizhongxin on 01/08/2018.
 */

public class CircleProgress extends View {

    private Paint mPaint;
    private double arc;

    public CircleProgress(Context context) {
        super(context);
        init();
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(arc >360)
            arc =0;
        /* 设置画布的颜色 */
        canvas.drawColor(Color.BLACK);

        /* 设置取消锯齿效果 */
        mPaint.setAntiAlias(true);
        mPaint.setAlpha(255);
        if(arc >200){

            mPaint.setColor(Color.YELLOW);
        }else{

            mPaint.setColor(Color.GREEN);
        }
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        RectF rf = new RectF(10, 10, 90, 140);
        canvas.drawRoundRect(rf, 10, 10, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mPaint.setAlpha(0);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(new RectF(-100, -75, 200, 225), 240, (float) arc, true, mPaint);
        arc +=2.5;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        return true;
    }
}
