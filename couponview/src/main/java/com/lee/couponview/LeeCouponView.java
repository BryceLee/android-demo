package com.lee.couponview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * @Auther:BryceLee leezx1993@163.com
 * @Date: 2019-09-04
 * @Desc:
 */
public class LeeCouponView extends RelativeLayout {

  private int divider_marginleft_percent;
  private int bgcolor;
  private int divider_circle_radius;
  private int semicircle_radius;
  private Paint mPaint;
  private Bitmap mBitmap;
  private Canvas mCanvas;
  private int divider_marginleft;
  private int startY;
  private int endY;
  private int numCircle;
  private int margin;
  private Paint mCirclePaint;
  private int corner_radius;

  public LeeCouponView(Context context) {
    this(context, null);
  }

  public LeeCouponView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LeeCouponView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray typedArray = (TypedArray) context
        .obtainStyledAttributes(attrs, R.styleable.LeeCouponView, defStyleAttr, 0);
    if (typedArray != null) {
      bgcolor = typedArray.getColor(R.styleable.LeeCouponView_lee_coupon_bg_color, 0xFFc0c0c0);
      divider_marginleft_percent = typedArray.getInt(
          R.styleable.LeeCouponView_lee_coupon_divider_marginleft_percent, 50);
//      divider_circle_radius = typedArray
//          .getDimensionPixelSize(R.styleable.LeeCouponView_lee_coupon_divider_circle_radius, 2);
      semicircle_radius = typedArray
          .getDimensionPixelSize(R.styleable.LeeCouponView_lee_coupon_semicircle_radius, 30);
      corner_radius = typedArray
          .getDimensionPixelSize(R.styleable.LeeCouponView_lee_coupon_corner_radius, 0);
      typedArray.recycle();
    }
    //
    initPaint();
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    initDrawCanvas(w, h);
  }

  private void initDrawCanvas(int w, int h) {
    if (getBackground() == null) {
      setBackgroundColor(Color.TRANSPARENT);
    }
    if (mBitmap != null) {
      mBitmap.recycle();
      mBitmap = null;
    }
    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    mCanvas = new Canvas(mBitmap);
    mCanvas.drawColor(bgcolor);
    divider_marginleft = w * divider_marginleft_percent / 100;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (mBitmap != null) {
      canvas.drawBitmap(mBitmap, 0, 0, null);
    }
    drawSemiCircle();
    drawDivder();
//    if (mBitmap!=null)
//    canvas.drawRoundRect(new RectF(0,0,mBitmap.getWidth(),mBitmap.getHeight()),10,10,mCirclePaint);
  }

  private void drawSemiCircle() {
    if (mCanvas != null && mPaint != null) {
      mCanvas.drawCircle(divider_marginleft, 0, semicircle_radius, mPaint);
      mCanvas.drawCircle(divider_marginleft, getHeight(), semicircle_radius, mPaint);
    }
  }

  private void drawDivder() {
    if (startY == 0) {
      startY = (int) (getHeight() * 0.2);
      endY = (int) (getHeight() * 0.8);
      numCircle = 10;
      margin = (endY - startY) / (numCircle - 1);
    }
    if (divider_circle_radius == 0) {
      divider_circle_radius = semicircle_radius / 8;
    }
    for (int i = 0; i < numCircle; i++) {
      mCanvas.drawCircle(divider_marginleft, startY + i * margin, divider_circle_radius, mPaint);
    }
  }

  private void initPaint() {//边缘锯齿画笔
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setDither(true);
    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    mPaint.setStyle(Paint.Style.FILL);

    mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mCirclePaint.setDither(true);
    mCirclePaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
    mCirclePaint.setStyle(Paint.Style.FILL);
  }

  public void setBgcolor(int color) {
    bgcolor = color;
    init();
  }

  public void init() {
    if (mBitmap != null) {
      int width = mBitmap.getWidth();
      int height = mBitmap.getHeight();
      mBitmap.recycle();
      mBitmap = null;
      mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
      mCanvas = new Canvas(mBitmap);
      mCanvas.drawColor(bgcolor);
      invalidate();
    }
  }
}
