package textjunit.lizhongxin.demo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lizhongxin on 01/08/2018.
 */

public class PorterDuffXfermodeView extends View{


    private Paint paint;
    private int r;

    public PorterDuffXfermodeView(Context context) {
        super(context);
        init();
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    int circle;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //设置背景色
        canvas.drawARGB(255, 139, 197, 186);

        int canvasWidth = canvas.getWidth();
        r = canvasWidth / 3;
        //绘制黄色的圆形
        paint.setColor(0xFFFFCC44);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        //绘制蓝色的矩形
        paint.setColor(0xFF66AAFF);
        if (circle==0){
            circle=r;
        }
//        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
        canvas.drawCircle(r, r, circle, paint);
        paint.setXfermode(null);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(r, r, 20, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        animeStart();
        return true;
    }

    public void animeStart() {
        int tag=getMeasuredWidth() / 3;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(tag / 2, tag * 3 / 4, tag / 2);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circle=(int)animation.getAnimatedValue();
                invalidate();
                Log.d("tagR",""+circle);
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }
}
