package textjunit.lizhongxin.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongxin on 01/08/2018.
 */

public class CustomRecordProgress extends View {
    public int progress;
    private Paint paint;
    private boolean isShowSplit;
    private List<Integer> list=new ArrayList<>();
    private double minProgress=0.4d;
    public void setMinProgress(int maxtime){
        minProgress=2000d/maxtime;
        invalidate();
    }
    public void setProgress(int i) {
        progress = i;
        invalidate();
    }
    public void reset() {
        list.clear();
        isShowSplit=false;
        progress = 0;
        invalidate();
    }
    public void setShowSplit(){
        this.isShowSplit=true;
        list.add(progress);
        invalidate();
    }

    public CustomRecordProgress(Context context) {
        super(context);
        init();
    }

    public CustomRecordProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRecordProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#FAE058"));
        paint.setStrokeWidth(8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#323040"));
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float x=measuredWidth*progress/100;
        int y =  measuredHeight/ 2;
        paint.setColor(Color.parseColor("#FAE058"));
        paint.setStrokeWidth(18);
        canvas.drawLine(0,y,x,y,paint);
        paint.setColor(Color.parseColor("#B3B3B3"));
        paint.setStrokeWidth(4);
        canvas.drawLine((int)(measuredWidth*0.4),0,(int)(measuredWidth*0.4),measuredHeight,paint);
        if (isShowSplit){
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(4);
            for (Integer i:list){
                canvas.drawLine((int)(measuredWidth*i/100),0,(int)(measuredWidth*i/100),measuredHeight,paint);
                Log.d("progressBQ",i+"");
            }
        }
    }
}
