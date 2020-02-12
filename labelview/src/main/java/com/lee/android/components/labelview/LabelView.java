package com.lee.android.components.labelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LabelView extends View {

  private Paint paint;
  private int startXPercent;
  private int startYPercent;
  private int middleXPercent;
  private int middleYPercent;
  private int finalXPercent;
  private int finalYPercent;


  public LabelView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public LabelView(Context context,
      @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStrokeWidth(10);
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
    startXPercent = a.getInt(R.styleable.LabelView_startXPercent, 0);
    startYPercent = a.getInt(R.styleable.LabelView_startYPercent, 10);
    middleXPercent = a.getInt(R.styleable.LabelView_middleXPercent, 20);
    middleYPercent = a.getInt(R.styleable.LabelView_middleYPercent, 10);
    finalXPercent = a.getInt(R.styleable.LabelView_finalXPercent, 80);
    finalYPercent = a.getInt(R.styleable.LabelView_finalYPercent, 80);
    a.recycle();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    setMeasuredDimension(100, 100);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  int startX = 0, startY = 10;
  int middleX = 10, middleY = 10;
  int finalX = 80, finalY = 80;

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (startX == 0) {
      int measuredHeight = getMeasuredHeight();
      int measuredWidth = getMeasuredWidth();
      startX = startXPercent * measuredWidth / 100;
      startY = startYPercent * measuredHeight / 100;
      middleX = middleXPercent * measuredWidth / 100;
      middleY = middleYPercent * measuredHeight / 100;
      finalX = finalXPercent * measuredWidth / 100;
      finalY = finalYPercent * measuredHeight / 100;
    }
    canvas.drawARGB(100, 100, 100, 100);
    paint.setColor(getContext().getResources().getColor(R.color.colorAccent));
    canvas.drawLine(startX, startY, middleX, middleY, paint);
    canvas.drawLine(middleX, middleY, finalX, finalY, paint);
    paint.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
    canvas.drawCircle(finalX, finalY, 15, paint);
    paint.setColor(getContext().getResources().getColor(R.color.colorPrimary));
    canvas.drawCircle(finalX, finalY, 6, paint);
  }
}
