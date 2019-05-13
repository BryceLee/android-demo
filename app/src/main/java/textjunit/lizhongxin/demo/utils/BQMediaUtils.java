package textjunit.lizhongxin.demo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build.VERSION_CODES;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import com.orhanobut.logger.Logger;
import java.io.IOException;

/**
 * Created by lizhongxin on 2018/4/10.
 */
@RequiresApi(api = VERSION_CODES.JELLY_BEAN)
public class BQMediaUtils {

  private volatile static BQMediaUtils singleton;

  public static BQMediaUtils getSingleton() {
    if (singleton == null) {
      synchronized (BQMediaUtils.class) {
        if (singleton == null) {
          singleton = new BQMediaUtils();
        }
      }
    }
    return singleton;
  }


  public static long getVideoTime(String url) {
    try {
      long l = System.currentTimeMillis();
      MediaMetadataRetriever retriever = new MediaMetadataRetriever();
      retriever.setDataSource(url);
      long duration = Long
          .parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
      retriever.release();
      Logger.d("getVideoTime:" + duration + ",耗时：" + (System.currentTimeMillis() - l));
      return duration;
    } catch (Exception e) {
      e.printStackTrace();
      Logger.d("getVideoTime,url:" + url);
    }
    return 0;
  }

  public Long[] getVideoWidthAndHeight(String url) {
    try {
      Logger.d("getVideoWidthAndHeight,url:" + url);
      MediaMetadataRetriever retriever = new MediaMetadataRetriever();
      retriever.setDataSource(url);
      long w = Long
          .parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
      long h = Long
          .parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
      Long[] ints = new Long[2];
      ints[0] = w;
      ints[1] = h;
      Logger.d("getVideoWidthAndHeight,w:" + w + ",h:" + h);
      retriever.release();
      return ints;
    } catch (Exception e) {
      e.printStackTrace();
      Logger.d("getVideoWidthAndHeight,Exception:" + e.toString());
    }
    return null;
  }


  public static String VIDEO_TYPE = "video";
  public static String AUDIO_TYPE = "audio";

  /**
   * 获取媒体编码格式
   */
  public static MediaFormat getMediaFormat(MediaExtractor extractor, String type,
      String mediaPath) {
    if (extractor == null || mediaPath == null || mediaPath.equals("")) {
      return null;
    }
    try {
      MediaFormat format;
      extractor.setDataSource(mediaPath);
      for (int i = 0; i < extractor.getTrackCount(); i++) {
        format = extractor.getTrackFormat(i);
        if (format.getString(MediaFormat.KEY_MIME).startsWith(type)) {
          extractor.selectTrack(i);
          return format;
        }
      }
    } catch (IOException e) {
      Log.e("BQMeidaUtils", "getMediaFormat:" + e.getMessage());
    }
    return null;
  }


  public int getVideoFPs(String mediaPath) {
//        format.setInteger(MediaFormat.KEY_COLOR_FORMAT,
//                MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
//        format.setInteger(MediaFormat.KEY_BIT_RATE, bitRate);
//        format.setInteger(MediaFormat.KEY_FRAME_RATE, FRAME_RATE);
//        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, IFRAME_INTERVAL);
    MediaExtractor extractor = new MediaExtractor();
    if (extractor == null || mediaPath == null || mediaPath.equals("")) {
      return 0;
    }
    try {
      MediaFormat format;
      extractor.setDataSource(mediaPath);
      for (int i = 0; i < extractor.getTrackCount(); i++) {
        format = extractor.getTrackFormat(i);
        if (format != null) {
          String KEY_MIME = format.getString(MediaFormat.KEY_MIME);
          if (!TextUtils.isEmpty(KEY_MIME) && KEY_MIME.startsWith(VIDEO_TYPE)) {
            if (format.containsKey(MediaFormat.KEY_FRAME_RATE)) {
              int integer = format.getInteger(MediaFormat.KEY_FRAME_RATE);
              if (extractor != null) {
                extractor.release();
                extractor = null;
              }
              Log.e("getVideoFPs", "KEY_FRAME_RATE:" + integer);
              return integer;
            }
          }
        }
      }
    } catch (IOException e) {
      Log.e("BQMeidaUtils", "getMediaFormat:" + e.getMessage());
      if (extractor != null) {
        extractor.release();
        extractor = null;
      }
    }
    if (extractor != null) {
      extractor.release();
      extractor = null;
    }
    return 0;
  }

  public int getVideoKEY_I_FRAME_INTERVAL(String mediaPath) {
//        format.setInteger(MediaFormat.KEY_COLOR_FORMAT,
//                MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
//        format.setInteger(MediaFormat.KEY_BIT_RATE, bitRate);
//        format.setInteger(MediaFormat.KEY_FRAME_RATE, FRAME_RATE);
//        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, IFRAME_INTERVAL);
    MediaExtractor extractor = new MediaExtractor();
    if (extractor == null || mediaPath == null || mediaPath.equals("")) {
      return 0;
    }
    try {
      MediaFormat format;
      extractor.setDataSource(mediaPath);
      for (int i = 0; i < extractor.getTrackCount(); i++) {
        format = extractor.getTrackFormat(i);
        if (format != null) {
          String KEY_MIME = format.getString(MediaFormat.KEY_MIME);
          if (!TextUtils.isEmpty(KEY_MIME) && KEY_MIME.startsWith(VIDEO_TYPE)) {
            if (format.containsKey(MediaFormat.KEY_I_FRAME_INTERVAL)) {
              int integer = format.getInteger(MediaFormat.KEY_I_FRAME_INTERVAL);
              if (extractor != null) {
                extractor.release();
                extractor = null;
              }
              Log.e("KEY_I_FRAME_INTERVAL", "KEY_I_FRAME_INTERVAL:" + integer);
              return integer;
            }
          }
        }
      }
    } catch (IOException e) {
      Log.e("BQMeidaUtils", "KEY_I_FRAME_INTERVAL:" + e.getMessage());
      if (extractor != null) {
        extractor.release();
        extractor = null;
      }
    }
    if (extractor != null) {
      extractor.release();
      extractor = null;
    }
    return 0;
  }

  public int getVideoBitRate(String mediaPath) {
//        format.setInteger(MediaFormat.KEY_COLOR_FORMAT,
//                MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
//        format.setInteger(MediaFormat.KEY_BIT_RATE, bitRate);
//        format.setInteger(MediaFormat.KEY_FRAME_RATE, FRAME_RATE);
//        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, IFRAME_INTERVAL);
    MediaExtractor extractor = new MediaExtractor();
    if (extractor == null || mediaPath == null || mediaPath.equals("")) {
      return 0;
    }
    try {
      MediaFormat format;
      extractor.setDataSource(mediaPath);
      for (int i = 0; i < extractor.getTrackCount(); i++) {
        format = extractor.getTrackFormat(i);
        /**
         *              java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.Integer.intValue()'
         *              on a null object reference
         at android.media.MediaFormat.getInteger(MediaFormat.java:834)
         at com.biaoqing.library.utils.base.BQMediaUtils.getVideoBitRate(BQMediaUtils.java:238)
         */
        if (format.getString(MediaFormat.KEY_MIME).startsWith(VIDEO_TYPE)) {
          if (format.containsKey(MediaFormat.KEY_BIT_RATE)) {
            int integer = format.getInteger(MediaFormat.KEY_BIT_RATE);
            if (extractor != null) {
              extractor.release();
              extractor = null;
            }
            Log.e("getVideoBitRate", "KEY_BIT_RATE:" + integer);
            return integer;
          }
        }
      }
    } catch (IOException e) {
      Log.e("BQMeidaUtils", "getMediaFormat:" + e.getMessage());
      if (extractor != null) {
        extractor.release();
        extractor = null;
      }
    }
    if (extractor != null) {
      extractor.release();
      extractor = null;
    }
    return 0;
  }

  public boolean HasAudio(String mediaPath) {
    MediaExtractor extractor = new MediaExtractor();
    if (extractor == null || mediaPath == null || mediaPath.equals("")) {
      return false;
    }
    try {
      MediaFormat format;
      extractor.setDataSource(mediaPath);
      for (int i = 0; i < extractor.getTrackCount(); i++) {
        format = extractor.getTrackFormat(i);
        if (format.getString(MediaFormat.KEY_MIME).startsWith(AUDIO_TYPE)) {
          Log.e("BQMeidaUtils", "KEY_MIME:" + format.getString(MediaFormat.KEY_MIME));
          if (extractor != null) {
            extractor.release();
            extractor = null;
          }
          return true;
        }
      }
    } catch (IOException e) {
      Log.e("BQMeidaUtils", "getMediaFormat:" + e.getMessage());
      if (extractor != null) {
        extractor.release();
        extractor = null;
      }
    }
    if (extractor != null) {
      extractor.release();
      extractor = null;
    }
    return false;
  }

  /**
   * 判断是否有音轨
   */
  public static boolean hasAudio(String url) {
    try {
      MediaMetadataRetriever retriever = new MediaMetadataRetriever();
      retriever.setDataSource(url);
      long TRACKS = Long
          .parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS));
      Logger.d("hasAudio_TRACKS:" + TRACKS);
      retriever.release();
      if (TRACKS == 1) {
        return false;
      } else {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  private Bitmap getThumbUsingAndroidSystem(String path) {
//        if (BuildConfig.DEBUG) {
//            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
//            mediaMetadataRetriever.setDataSource(draftRes.getAlbums().TopicGroupAPIAOP(0));
//            mediaMetadataRetriever.release();
//            Logger.d("videothubm,videoW:" + videoW + ",videoh:" + videoH);
//        }

    Bitmap bitmap = ThumbnailUtils
        .createVideoThumbnail(path, MediaStore.Video.Thumbnails.MINI_KIND);
    return bitmap;
//        Logger.d("videothubm:" + thumbs.toString());
//        Bitmap scale = ImageUtils.scale(bitmap, widthHeight, widthHeight);
//        if (BuildConfig.DEBUG) {
//            if (bitmap != null) {
//                int width = bitmap.getWidth();
//                int height = bitmap.getHeight();
//                Logger.d("videothubm:w:" + width + ",h:" + height);
//            }
//            if (scale != null) {
//                int width = scale.getWidth();
//                int height = scale.getHeight();
//                Logger.d("videothubm:scalew:" + width + ",scaleh:" + height);
//            }
//        }
//        boolean save = ImageUtils.save(scale, thumbs, Bitmap.CompressFormat.JPEG);
//        if (save) {
//            setVideoThumbDone(thumbs);
//        }
  }

  public static void cancelAudioFocus(Context context) {
    AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    if (am != null) {
      am.abandonAudioFocus(null);
    }
  }

  public static void requestAudioFocus(Context context) {
    //获取音频焦点
    AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    if (am != null) {
      am.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    }
  }

}
