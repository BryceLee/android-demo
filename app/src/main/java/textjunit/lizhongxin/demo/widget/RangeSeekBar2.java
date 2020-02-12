package textjunit.lizhongxin.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.orhanobut.logger.Logger;
import java.util.List;
import java.util.Vector;
import textjunit.lizhongxin.demo.R;

public class RangeSeekBar2 extends View {

  private static final String TAG = "RangeSeekBar2";
  public static final int HORIZONTAL = 0;
  public static final int VERTICAL = 1;
  private static final int DEFAULT_THUMBS = 2;
  private static final float DEFAULT_STEP = 5.0f;
  private float progress;
  private float pixelMinSelectRangeLength;
  private boolean isScrollSelectArea;
  private float pixelMaxSelectRangeLength;
  private Drawable scroll_ball;
  private float scroll_ball_radius;

  public float getProgress() {
    return progress;
  }

  public void setProgress(float progress) {
    Log.d(TAG, "progress：" + progress);
    this.progress = progress;
    invalidate();
  }

  private long videotime;

  public long getVideotime() {
    return videotime;
  }

  public void setVideotime(long videotime) {
    this.videotime = videotime;
  }


  private Drawable rightThumb;


  private List<RangeSeekBarListener> listeners;

  private List<Thumb> thumbList;
  private float thumbWidth;
  private float thumbHeight;
  private float thumbHalf;
  private float pixelRangeMin;
  private float pixelRangeMax;
  private int orientation;
  private boolean limitThumbRange;
  private int viewWidth;
  private int ThumbViewHeight;
  private float progressMin;
  private float progressMax;
  private float progressStep;
  //轨迹
  private float track_left;
  private float track_right;

  private Paint track_paint;//轨迹画笔
  private Paint scroll_paint;//滚动条
  private Drawable track;
  private Drawable range;
  private Drawable leftThumb;

  private boolean firstRun;
  float minVelocitX;
  float horizontalMinDistance;
  private int currentThumb = 0;
  private float lowLimit = pixelRangeMin;
  private float highLimit = pixelRangeMax;

  /**
   * 进度条在两个按钮间进行切换，距离知道了
   * 速度如何知道呢？根据视频的时间来计算，距离除以时间。
   * 但是会有误差，这适合，在监听视频比方完成，在完成的适合进行进度条重置，即可
   */
  public interface onFlingVelocity {

    void backonFlingVelocity(float volocityX);

    void backonScroll(float volocityX);

    void onTouchThumb();
  }

  private GestureDetector gestureDetector;

  public void setOnFlingVeclocity(onFlingVelocity l) {
    mOnFlingVelocity = l;
  }

  public onFlingVelocity mOnFlingVelocity;

  public RangeSeekBar2(Context context) {
    super(context);
    init(context);

  }

  /**
   * Construct object, initializing url any attributes we understand from a
   * layout file. These attributes are defined in
   * SDK/assets/res/any/classes.xml.
   *
   * @see View#View(Context, AttributeSet)
   */
  public RangeSeekBar2(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
    // Obtain our styled custom attributes from xml
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeSeekBar2);
    orientation = HORIZONTAL;
    limitThumbRange = a.getBoolean(R.styleable.RangeSeekBar2_limitThumbRange, true);
    progressMin = a.getFloat(R.styleable.RangeSeekBar2_progressMin, 0);
    progressMax = a.getFloat(R.styleable.RangeSeekBar2_progressMax, 100);
    progressStep = Math.abs(a.getFloat(R.styleable.RangeSeekBar2_progressStep, DEFAULT_STEP));
    leftThumb = a.getDrawable(R.styleable.RangeSeekBar2_leftThumb);
    rightThumb = a.getDrawable(R.styleable.RangeSeekBar2_rightThumb);
    range = a.getDrawable(R.styleable.RangeSeekBar2_selectRangeBg);//选中区域的背景
    track = a.getDrawable(R.styleable.RangeSeekBar2_bg);//背景
    // Register desired amount of thumbList
    int noThumbs = a.getInt(R.styleable.RangeSeekBar2_thumbs, DEFAULT_THUMBS);
    thumbWidth = a.getDimension(R.styleable.RangeSeekBar2_thumbWidth, 20);
    thumbHeight = a.getDimension(R.styleable.RangeSeekBar2_thumbHeight, 60);
    scroll_ball_radius = a.getDimension(R.styleable.RangeSeekBar2_scroll_ball_radius, 44);
    scroll_ball = a.getDrawable(R.styleable.RangeSeekBar2_scroll_ball);
    for (int i = 0; i < noThumbs; i++) {
      Thumb th = new Thumb();
      thumbList.add(th);
    }
    int width = (int) getResources().getDimension(R.dimen.bq_px_4);
    track_paint = new Paint();
    track_paint.setStyle(Paint.Style.STROKE);
    track_paint.setStrokeWidth(width);
    track_paint.setColor(Color.parseColor("#fece36"));
    scroll_paint = new Paint();
    scroll_paint.setStyle(Paint.Style.STROKE);
    scroll_paint.setStrokeWidth(width);
    scroll_paint.setColor(Color.RED);
    a.recycle();
  }

  private void init(Context context) {
    orientation = HORIZONTAL;
    limitThumbRange = true;
    progressMin = 0;
    progressMax = 100;
    progressStep = DEFAULT_STEP;
    viewWidth = 0;
    ThumbViewHeight = 0;
    horizontalMinDistance = 20;
    minVelocitX = 0;
    thumbList = new Vector<>();
    listeners = new Vector<>();
    this.setFocusable(true);
    this.setFocusableInTouchMode(true);
    firstRun = true;
    gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
      @Override
      public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                return super.onScroll(e1, e2, distanceX, distanceY);
        Log.d(TAG, "onScroll:e1x=" + e1.getX() + ",e2x=" + e2.getX());
        if (isScrollSelectArea) {
          changeThumbPosition(0, -distanceX);
          changeThumbPosition(1, -distanceX);
        } else {
          if (mOnFlingVelocity != null) {
            mOnFlingVelocity.backonScroll(distanceX);
          }
        }
        return false;
      }

      @Override
      public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1 != null && e2 != null) {
          if (mOnFlingVelocity != null) {
            mOnFlingVelocity.backonFlingVelocity(velocityX);
          }
          if (e1.getX() - e2.getX() > horizontalMinDistance && Math.abs(velocityX) > minVelocitX) {
//                        Toast.makeText(context, "向左手势,v:" + velocityX, Toast.LENGTH_SHORT).show();
            Logger.d("向左手势");
          } else if (e2.getX() - e1.getX() > horizontalMinDistance
              && Math.abs(velocityX) > minVelocitX) {
            Logger.d("向右手势");
//                        Toast.makeText(context, "向右手势,v:" + velocityX, Toast.LENGTH_SHORT).show();
          }
        } else {
          Logger.d("1=nulle2=null+");
        }
        //                return super.onFling(e1, e2, velocityX, velocityY);
        return false;
      }
    });
  }

  /**
   * {@inheritDoc}
   *
   * @see View#measure(int, int)
   */
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    viewWidth = measureWidth(widthMeasureSpec);
    int measureHeight = measureHeight(heightMeasureSpec);
    ThumbViewHeight = (int) (measureHeight - scroll_ball_radius);
    track_left = 0;
    track_right = viewWidth;

    setMeasuredDimension(viewWidth, measureHeight);
    thumbHalf = thumbWidth / 2;
    pixelRangeMin = thumbWidth;//左边选中图标的right
    pixelRangeMax = viewWidth - thumbWidth;//右边选中图标的Left
    pixelMinSelectRangeLength = (pixelRangeMax - pixelRangeMin) / 100;
    pixelMaxSelectRangeLength = (pixelRangeMax - pixelRangeMin) / 100 * 30;
    if (firstRun) {
      distributeThumbsEvenly();
      // Fire listener callback
      if (listeners != null && listeners.size() > 0) {
        for (RangeSeekBarListener l : listeners) {
          l.onCreate(currentThumb, getThumbValue(currentThumb));
        }
      }
      firstRun = false;
    }
  }

  /**
   * Draw
   *
   * @see View#onDraw(Canvas)
   */
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawGutter(canvas);        //画背景
    drawRange(canvas);        //画选中区域的背景
    drawThumbs(canvas);       //画两个拖拽图
    drawTrack(canvas);        //画进度轨迹
    drawScrollBall(canvas);
  }

  private void drawScrollBall(Canvas canvas) {
    if (!isSingleFrameSelect()){
      return;
    }
    float center = (getLeftPosition() + getRightPosition()) / 2;
    if (scroll_ball != null) {
      Rect area = new Rect();
      area.top = (int) (getMeasuredHeight() - scroll_ball_radius);
      area.bottom = (int) (getMeasuredHeight());
      area.left = (int) (center - scroll_ball_radius / 2);
      area.right = (int) (center + scroll_ball_radius / 2);
      scroll_ball.setBounds(area);
      scroll_ball.draw(canvas);
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (!thumbList.isEmpty()) {
      float coordinate = event.getX();
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        currentThumb = getOnClickThumb(event);
        Log.d(TAG, "ACTION_DOWN:" + currentThumb);
        if (currentThumb == -1) {
          isScrollSelectArea =
              (getLeftPosition() < event.getX() && event.getX() < getRightPosition())
                  || isSingleFrameSelect();
          gestureDetector.onTouchEvent(event);//down的时候必须穿数值
          Logger.d("return true");
          return true;
        }
        //                currentThumb = getClosestThumb(coordinate);
        //这里根据手指点击的坐标来判断更靠近手指的是哪个图标，然后对其进行操作。但是现在不需要这个需求
        //我们需要的是如果对这个控件做了 左右滑动的操作，我们对底部图层进行滑动
        Log.d(TAG, "Closest " + currentThumb);
        lowLimit = getLowerThumbRangeLimit(currentThumb);
        highLimit = getHigherThumbRangeLimit(currentThumb);
      }
      if (currentThumb == -1) {
        gestureDetector.onTouchEvent(event);
        Logger.d("return false");
        return false;
      } else {
        if (mOnFlingVelocity != null) {
          mOnFlingVelocity.onTouchThumb();
        }
      }
      // Update leftThumb position
      // Make sure we stay in our tracks's bounds or limited by other thumbList
      if (coordinate < lowLimit) {
//        if (lowLimit == highLimit && currentThumb >= thumbList.size() - 1) {
//          currentThumb = getUnStuckIndex(currentThumb);
//          setThumbPosition(currentThumb, coordinate);
//          lowLimit = getLowerThumbRangeLimit(currentThumb);
//          highLimit = getHigherThumbRangeLimit(currentThumb);
//        } else {
        setThumbPosition(currentThumb, lowLimit);
//        }
        //Log.d(TAG,"Setting low "+low);
      } else if (coordinate > highLimit) {
        setThumbPosition(currentThumb, highLimit);
        //Log.d(TAG,"Setting high "+high);
      } else {
        Log.d(TAG, "FixPixelByStepcoordinate111" + coordinate);
        coordinate = FixPixelByStep(coordinate);
        setThumbPosition(currentThumb, coordinate);
        Log.d(TAG, "FixPixelByStepcoordinate222" + coordinate);
      }

      // Fire listener callbacks
      if (listeners != null && listeners.size() > 0) {
        for (RangeSeekBarListener l : listeners) {
          l.onSeek(currentThumb, getThumbValue(currentThumb));
          //					if (currentThumb==0){
          //						track_left=getThumbValue(currentThumb)/100*viewWidth;
          //					}
          //					if (currentThumb==1){
          //						track_right=getThumbValue(currentThumb)/100*viewWidth;
          //					}
          //					invalidate();
        }
      }
      // Tell the view we want a complete redraw
      //invalidate();
      // Tell the system we've handled this event
      if (event.getAction() == MotionEvent.ACTION_CANCEL
          || event.getAction() == MotionEvent.ACTION_UP) {
        if (listeners != null && listeners.size() > 0) {
          for (RangeSeekBarListener l : listeners) {
            l.onTouchup();
          }
        }
      }
      return true;
    }
    return true;
  }

  private boolean isSingleFrameSelect() {
    Log.d(TAG,
        "isSingleFrameSelect-->" + "R:" + getRightPosition() + ",L:" + getLeftPosition() + ",=" + (
            getRightPosition() - getLeftPosition()));
    Log.d(TAG, "isSingleFrameSelect_pixelMinSelectRangeLength-->" + pixelMinSelectRangeLength);
    boolean b = (int) (getRightPosition() - getLeftPosition()) == (int) pixelMinSelectRangeLength;
    Log.d(TAG, "isSingleFrameSelect-->" + b);
    return b;
  }

  private int getUnStuckIndex(int index) {
    int unstuck = 0;
    float lastVal = thumbList.get(index).value;
    for (int i = index - 1; i >= 0; i--) {
      Thumb th = thumbList.get(i);
      if (th.value != lastVal) {
        return i + 1;
      }
    }
    return unstuck;
  }

  private float FixPixelByStep(float pixelValue) {
    float fix = stepProgressToPixel(pixelToStep(pixelValue));
    Log.d(TAG, "FixPixelByStep:" + fix);
    return fix;
  }

  private float pixelToProgress(float pixelValue) {
    float pixelRange = (pixelRangeMax - pixelRangeMin);
    float progressRange = (progressMax - progressMin);
    float progressValue =
        (((pixelValue - pixelRangeMin) * progressRange) / pixelRange) + progressMin;
    return progressValue;
  }

  private float progressToPixel(float progress) {
    float pixelRange = (pixelRangeMax - pixelRangeMin);
    float progressRange = (progressMax - progressMin);
    float pixelValue = (((progress - progressMin) * pixelRange) / progressRange) + pixelRangeMin;
    return pixelValue;
  }

  private float pixelToStep(float pixelValue) {
    float stepProgressMin = 0;
    float stepProgressMax = (float) Math.floor((progressMax - progressMin) / progressStep);
    float pixelRange = (pixelRangeMax - pixelRangeMin);
    float stepProgressRange = (stepProgressMax - stepProgressMin);
    float stepProgressValue =
        (((pixelValue - pixelRangeMin) * stepProgressRange) / pixelRange) + stepProgressMin;
    Log.d(TAG, "pixelToStep-->>stepProgressMax: " + stepProgressMax + "，pixelRange: " + pixelRange
        + "，stepProgressValue: " + stepProgressValue);
    return Math.round(stepProgressValue);
  }

  private float stepProgressToPixel(float stepProgressValue) {
    float stepProgressMin = 0;
    float stepProgressMax = (float) Math.floor((progressMax - progressMin) / progressStep);
    float pixelRange = (pixelRangeMax - pixelRangeMin);
    float stepProgressRange = (stepProgressMax - stepProgressMin);
    float pixelValue =
        (((stepProgressValue - stepProgressMin) * pixelRange) / stepProgressRange) + pixelRangeMin;
    Log.d(TAG, "stepProgressToPixel-->stepProgressValue: " + stepProgressValue + ",pixelValue: "
        + pixelValue
        + ",pixelRangeMin: " + pixelRangeMin);
    return pixelValue;
  }

  private void calculateThumbValue(int index) {
    if (index < thumbList.size() && !thumbList.isEmpty()) {
      Thumb th = thumbList.get(index);
      th.value = pixelToProgress(th.x);
    }
  }

  private void calculateThumbPos(int index) {
    if (index < thumbList.size() && !thumbList.isEmpty()) {
      Thumb th = thumbList.get(index);
      th.x = progressToPixel(th.value);
    }
  }

  private float getLowerThumbRangeLimit(int index) {
    float limit = pixelRangeMin;
    if (limitThumbRange && index < thumbList.size() && !thumbList.isEmpty()) {
      Thumb select = thumbList.get(index);
      for (int i = 0; i < thumbList.size(); i++) {
        if (i < index) {
          Thumb another = thumbList.get(i);
          if (another.x <= select.x && another.x >= limit) {
            limit = another.x + pixelMinSelectRangeLength;//加上拉动图标的宽度
            Log.e(TAG,
                "getLowerThumbRangeLimit: " + limit + " i:" + i + " index: " + index + ",another.x="
                    + another.x + ",thumbWidth:" + thumbWidth);
          }
        }
      }
    }
    return limit;
  }

  private float getHigherThumbRangeLimit(int index) {
    float limit = pixelRangeMax;
    if (limitThumbRange && index < thumbList.size() && !thumbList.isEmpty()) {
      Thumb th = thumbList.get(index);
      for (int i = 0; i < thumbList.size(); i++) {
        if (i > index) {
          Thumb tht = thumbList.get(i);
          if (tht.x >= th.x && tht.x <= limit) {
            limit = tht.x - pixelMinSelectRangeLength;//减去拉动图标的宽度
            Log.e(TAG, "getHigherThumbRangeLimit: " + limit + " i:" + i + " index: " + index);
          }
        }
      }
    }
    return limit;
  }

  public void distributeThumbsEvenly() {
    if (!thumbList.isEmpty()) {
      int numThumbs = thumbList.size();
      float even = pixelRangeMax / numThumbs;
      float lastPos = even / 2;
      for (int i = 0; i < thumbList.size(); i++) {
        Log.d(TAG, "distributeThumbsEvenly: " + lastPos);
        setThumbPosition(i, FixPixelByStep(lastPos));
        lastPos += even;
      }
    }
  }

  public float getThumbValue(int index) {
    return thumbList.get(index).value;
  }

  public void setThumbValue(int index, float value) {
    thumbList.get(index).value = value;
    calculateThumbPos(index);
    // Tell the view we want a complete redraw
    invalidate();
  }

  public void setThumbPosition(int index, float pos) {
    if (index == 0) {
      if (pos < getThumbPosition(1) - pixelMaxSelectRangeLength) {
        thumbList.get(index).x = getThumbPosition(1) - pixelMaxSelectRangeLength;
      } else {
        thumbList.get(index).x = pos;
      }
    } else if (index == 1) {
      if (pos > getThumbPosition(0) + pixelMaxSelectRangeLength) {
        thumbList.get(index).x = getThumbPosition(0) + pixelMaxSelectRangeLength;
      } else {
        thumbList.get(index).x = pos;
      }
    }
    Log.d(TAG, "setThumbPosition:left= " + getLeftPosition() + "，right:" + getRightPosition());
    Log.d(TAG, "setThumbPosition:leftProgress= " + getLeftProgress() + "，rightProgress:"
        + getrightProgress());
    Log.d(TAG,
        "setThumbPosition:min= " + pixelMinSelectRangeLength + ",max:" + pixelMaxSelectRangeLength
            + ",thumW:" + thumbWidth + ",halfthumbW:" + thumbWidth / 2);
    calculateThumbValue(index);
    // Tell the view we want a complete redraw
    invalidate();
  }

  public void changeThumbPosition(int index, float pos) {
    //其中一方达到边界了
    if (pos > 0 && (getThumbPosition(1) == pixelRangeMax)) {
      return;
    }
    if (pos < 0 && (getThumbPosition(0) == pixelRangeMin)) {
      return;
    }
    //
    float x = thumbList.get(index).x;
    setThumbPosition(index, x + pos);
    if (x + pos > pixelRangeMax) {
      setThumbPosition(index, pixelRangeMax);
    }
    if (x + pos < pixelRangeMin) {
      setThumbPosition(index, pixelRangeMin);
    }
    Log.d(TAG, "setThumbPosition: " + pos + "，index:" + index);
    calculateThumbValue(index);
    // Tell the view we want a complete redraw
    invalidate();
  }

  public float getThumbPosition(int index) {
    return thumbList.get(index).x;
  }

  public float getLeftPosition() {
    return getThumbPosition(0);
  }

  public float getRightPosition() {
    return getThumbPosition(1);
  }

  public float getLeftProgress() {
    return getThumbValue(0);
  }

  public float getrightProgress() {
    return getThumbValue(1);
  }

  private int getClosestThumb(float coordinate) {
    int closest = 0;
    if (!thumbList.isEmpty()) {
      float shortestDistance =
          pixelRangeMax + thumbHalf + (getPaddingLeft() + getPaddingRight());
      // Oldschool for-loop to have access to index
      for (int i = 0; i < thumbList.size(); i++) {
        // Find leftThumb closest to x coordinate
        float tcoordinate = thumbList.get(i).x;
        float distance = Math.abs(coordinate - tcoordinate);
        if (distance <= shortestDistance) {
          shortestDistance = distance;
          closest = i;
          //Log.d(TAG,"shDist: "+shortestDistance+" leftThumb i: "+closest);
        }
      }
    }
    return closest;
  }

  private int getOnClickThumb(MotionEvent event) {
    int closest = -1;
    if (isSingleFrameSelect() && event.getX() > getLeftPosition() - thumbWidth
        && event.getX() < getRightPosition() + thumbWidth && event.getY() > ThumbViewHeight) {
      return closest;
    }
    float coordinate = event.getX();
    if (!thumbList.isEmpty()) {
      for (int i = 0; i < thumbList.size(); i++) {
        float x = thumbList.get(i).x;
        if (i == 0 && x - thumbWidth <= coordinate && coordinate <= x) {
          return i;
        }
        if (i == 1 && x <= coordinate && coordinate <= x + thumbWidth) {
          return i;
        }
      }
    }
    return closest;
  }

  private void drawGutter(Canvas canvas) {
    if (track != null) {
      //Log.d(TAG,"gutterbg: "+gutterBackground.toString());
      Rect area1 = new Rect();
      area1.left = 0 + getPaddingLeft();
      area1.top = 0 + getPaddingTop();
      area1.right = getMeasuredWidth() - getPaddingRight();
      area1.bottom = getMeasuredHeight() - getPaddingBottom();
      track.setBounds(area1);
      track.draw(canvas);

    }
  }

  private void drawTrack(Canvas canvas) {
    /**
     * 绘制轨迹
     */
    canvas.drawRect(new RectF(track_left, 0, track_right, ThumbViewHeight - 0), track_paint);
    float v = track_left + (track_right - track_left) * progress;
  }

  private void drawRange(Canvas canvas) {
    if (!thumbList.isEmpty()) {
      Thumb thLow = thumbList.get(getClosestThumb(0));
      Thumb thHigh = thumbList.get(getClosestThumb(pixelRangeMax));

      // If we only have 1 leftThumb - choose to draw from 0 in Progress
      if (thumbList.size() == 1) {
        thLow = new Thumb();
      }
      //Log.d(TAG,"l: "+thLow.pos+" h: "+thHigh.pos);

      if (range != null) {
        Rect area1 = new Rect();

        if (orientation == VERTICAL) {
          area1.left = 0 + getPaddingLeft();
          area1.top = (int) thLow.x;
          area1.right = getMeasuredWidth() - getPaddingRight();
          area1.bottom = (int) thHigh.x;
        } else {
          area1.left = (int) thLow.x;
          area1.top = 0 + getPaddingTop();
          area1.right = (int) thHigh.x;
          area1.bottom = ThumbViewHeight;
        }
        range.setBounds(area1);
        range.draw(canvas);
      }
    }
  }

  private void drawThumbs(Canvas canvas) {
    if (!thumbList.isEmpty()) {
      for (int i = 0; i < thumbList.size(); i++) {
        Thumb th = thumbList.get(i);
        Rect area = new Rect();
        //矫正thumb
//        if (i == 1 && th.x < (thumbWidth + thumbHalf)) {
//          th.x = (thumbWidth + thumbHalf);
//        }
//        if (i == 0 && th.x > viewWidth - (thumbWidth + thumbHalf)) {
//          th.x = viewWidth - (thumbWidth + thumbHalf);
//        }
        area.top = 0;
        area.bottom = ThumbViewHeight;
        if (i == 0) {
          area.left = (int) ((th.x - thumbWidth));
          area.right = (int) ((th.x));
        } else {
          area.left = (int) ((th.x));
          area.right = (int) ((th.x + thumbWidth));
        }
        if (i == 0) {
          track_left = area.right;
        } else {
          track_right = area.left;
        }
        if (i == 0) {
          leftThumb.setBounds(area);
          leftThumb.draw(canvas);
          Log.d(TAG, "leftArea: " + area.toString());
        } else {
          rightThumb.setBounds(area);
          rightThumb.draw(canvas);
          Log.d(TAG, "rightArea: " + area.toString());
        }
      }
    }
  }


  /**
   * Determines the width of this view
   *
   * @param measureSpec A_Injector_Entity measureSpec packed into an int
   * @return The width of the view, honoring constraints from measureSpec
   */
  private int measureWidth(int measureSpec) {
    int result = 0;
    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);

    if (specMode == MeasureSpec.EXACTLY) {
      // We were told how big to be
      //Log.d(TAG,"measureWidth() EXACTLY");
      result = specSize;
    } else {
      // Measure
      //Log.d(TAG,"measureWidth() not EXACTLY");
      result = specSize + getPaddingLeft() + getPaddingRight();
      if (specMode == MeasureSpec.AT_MOST) {
        // Respect AT_MOST value if that was what is called for by measureSpec
        //Log.d(TAG,"measureWidth() AT_MOST");
        result = Math.min(result, specSize);
        // Add our thumbWidth to the equation if we're vertical
        if (orientation == VERTICAL) {
          int h = (int) (thumbWidth + getPaddingLeft() + getPaddingRight());
          result = Math.min(result, h);
        }
      }
    }

    return result;
  }

  /**
   * Determines the height of this view
   *
   * @param measureSpec A_Injector_Entity measureSpec packed into an int
   * @return The height of the view, honoring constraints from measureSpec
   */
  private int measureHeight(int measureSpec) {
    int result = 0;
    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);

    if (specMode == MeasureSpec.EXACTLY) {
      // We were told how big to be
      //Log.d(TAG,"measureHeight() EXACTLY");
      result = specSize;
    } else {
      // Measure
      //Log.d(TAG,"measureHeight() not EXACTLY");
      result = specSize + getPaddingTop() + getPaddingBottom();
      if (specMode == MeasureSpec.AT_MOST) {
        // Respect AT_MOST value if that was what is called for by measureSpec
        //Log.d(TAG,"measureHeight() AT_MOST");
        result = Math.min(result, specSize);
        // Add our thumbHeight to the equation if we're horizontal
        if (orientation == HORIZONTAL) {
          int h = (int) (thumbHeight + getPaddingTop() + getPaddingBottom());
          result = Math.min(result, h);
        }
      }
    }

    return result;
  }

  public class Thumb {

    private float value;//0~100
    private float x;//坐标

    public Thumb() {
      value = 0;
      x = 0;
    }
  }

  public interface RangeSeekBarListener {

    void onCreate(int index, float value);

    void onSeek(int index, float value);

    void onTouchup();

    void onVideoEnd(float ratio);
  }

  public void setListener(RangeSeekBarListener listener) {
    listeners.add(listener);
  }

  public int getOrientation() {
    return orientation;
  }

  public void setOrientation(int orientation) {
    this.orientation = orientation;
  }

  public float getThumbWidth() {
    return thumbWidth;
  }

  public void setThumbWidth(float thumbWidth) {
    this.thumbWidth = thumbWidth;
  }

  public float getThumbHeight() {
    return thumbHeight;
  }

  public void setThumbHeight(float thumbHeight) {
    this.thumbHeight = thumbHeight;
  }

  public boolean isLimitThumbRange() {
    return limitThumbRange;
  }

  public void setLimitThumbRange(boolean limitThumbRange) {
    this.limitThumbRange = limitThumbRange;
  }

  public float getProgressMin() {
    return progressMin;
  }

  public void setProgressMin(float progressMin) {
    this.progressMin = progressMin;
  }

  public float getProgressMax() {
    return progressMax;
  }

  public void setProgressMax(float progressMax) {
    this.progressMax = progressMax;
  }

  public float getProgressStep() {
    return progressStep;
  }

  public void setProgressStep(float progressStep) {
    this.progressStep = progressStep;
  }

  public Drawable getTrack() {
    return track;
  }

  public void setTrack(Drawable track) {
    this.track = track;
  }

  public Drawable getRange() {
    return range;
  }

  public void setRange(Drawable range) {
    this.range = range;
  }

  public Drawable getLeftThumb() {
    return leftThumb;
  }

  public void setLeftThumb(Drawable leftThumb) {
    this.leftThumb = leftThumb;
  }

  public void initThumbs(int noThumbs) {
    for (int i = 0; i < noThumbs; i++) {
      Thumb th = new Thumb();
      thumbList.add(th);
    }
  }

}
