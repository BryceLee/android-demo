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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;


/**
 * Created by lizhongxin on 2018/3/1.
 */

public class TouchSwitchView extends View {

    private Paint paint;
    private Context context;

    public TouchSwitchView(Context context) {
        super(context);
        init(context);
    }

    public TouchSwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TouchSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    /**
     * 左右滑动手势，捕捉
     */
    private int horizontalMinDistance = 20;
    private int minVelocitX = 10;
    public class filterSwitchGesture extends GestureDetector.SimpleOnGestureListener {


        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 != null && e2 != null) {
                if (e1.getX() - e2.getX() > horizontalMinDistance && Math.abs(velocityX) > minVelocitX) {
//                    ToastUtil.show(kwFilter.getName() + "");
                    Toast.makeText(context, "向左手势,v:" + velocityX, Toast.LENGTH_SHORT).show();
                    return true;
                } else if (e2.getX() - e1.getX() > horizontalMinDistance && Math.abs(velocityX) > minVelocitX) {
//                    ToastUtil.show(kwFilter.getName() + "");
                    Toast.makeText(context, "向右手势,v:" + velocityX, Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()!=MotionEvent.ACTION_DOWN){
            return false;
        }
        float x = event.getX();
        float percent=x/getMeasuredWidth();
        return true;
    }

    private long width = 66;
    private long height = 10;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(255,255,100,100);
        RectF rectF = new RectF(getMeasuredWidth() / 2 - width/2, getMeasuredHeight()/2 - height/2, getMeasuredWidth() / 2 + width/2,
                getMeasuredHeight()/2 + height/2);
        canvas.drawRoundRect(rectF, 5, 5, paint);
    }


    private void init(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#bebebe"));
        paint.setTextAlign(Paint.Align.CENTER);
    }
}
