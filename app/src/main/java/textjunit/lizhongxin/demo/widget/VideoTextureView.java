package textjunit.lizhongxin.demo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import textjunit.lizhongxin.demo.BuildConfig;


/**
 * Disc: 背景视频 这个包下只有这个类有用，其他的都是测试滤镜的类
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-12-12 14:44
 */
public class VideoTextureView extends TextureView {


    public VideoTextureView(Context context) {
        super(context);
        init();
    }

    public VideoTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Surface mOESSurface;
    public boolean havePlayedAudio = false;//是否已经播放过音频

    private void init() {

        setSurfaceTextureListener(new SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
                Log.d("diy", "diy,vd,onSurfaceTextureAvailable");
                if (true) {
                    mOESSurface = new Surface(surfaceTexture);
                    playVideo();
                    return;
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Log.d("diy", "diy,vd,onSurfaceTextureDestroyed");
                mOESSurface = null;
                onRelease();
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }


    public String filePath;
    float duration;
    int delay;
    public MediaPlayer mMediaPlayer;
    private boolean autoDestroyed = false;

    private void playVideo() {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setSurface(mOESSurface);
        if (BuildConfig.DEBUG) {
//            file= new File("/storage/emulated/0/Android/data/com.biaoqing
// .BiaoQingShuoShuo/cache/video_cache/0cf7fbd3da7446141ee885f2c862cb3f.mp4");
        }
//        FileInputStream fis = null;
//        http://img.biaoqing.com/video/20180613/164422000283630.mp4
//            mMediaPlayer.setDataSource("http://img.biaoqing.com/video/20180613/164422000283630.mp4");
        try {
            mMediaPlayer.setDataSource(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //        File file = new File(filePath);
//            fis = new FileInputStream(file);
//            mMediaPlayer.setDataSource(fis.getFD());

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                duration = mMediaPlayer.getDuration();
                mMediaPlayer.start();
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
        try {
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setVideoUrl(String strFileName) {
        filePath = strFileName;
        if (mOESSurface != null) {
            playVideo();
        } else {
            init();
        }
    }


    public void onRelease() {
        try {
            autoDestroyed = true;
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
