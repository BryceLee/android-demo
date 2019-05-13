package textjunit.lizhongxin.demo.av;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ClippingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.orhanobut.logger.Logger;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;
import javademo.lizhongxin.mylibrary.utils.RxUtils;
import textjunit.lizhongxin.demo.R;

public class ExoplayerActivity extends AppCompatActivity {

  private SimpleExoPlayer exoPlayer;
  private Uri mp4VideoUri;
  //  private static final File FILES_DIR = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
//      "Camera");
//  private static final String INPUT_FILE = "a.mp4";
  private EditText m_et;
  private Factory dataSourceFactory;
  private String path = "http://img.biaoqing.com/work/20180912/113603000393651.mp4";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exoplayer);

    //
    m_et = (EditText) findViewById(R.id.et);
//    m_et.setText(INPUT_FILE);
    //
    Context context = ExoplayerActivity.this;
    PlayerView playerView = (PlayerView) findViewById(R.id.playview);
    //Creating the exoPlayer
    exoPlayer = ExoPlayerFactory.newSimpleInstance(context);
    // Bind the exoPlayer to the view.
    playerView.setPlayer(exoPlayer);
    // Produces DataSource instances through which media data is loaded.
    dataSourceFactory = new DefaultDataSourceFactory(context,
        Util.getUserAgent(context, "BaseApplication"));
//    path = new File(FILES_DIR, m_et.getText().toString()).toString();
    mp4VideoUri = Uri.parse(path);
    // This is the MediaSource representing the media to be played.
    MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
        .createMediaSource(mp4VideoUri);
    // Prepare the exoPlayer with the source.
    // Clip to start at 5 seconds and end at 10 seconds.
    ClippingMediaSource clippingSource =
        new ClippingMediaSource(
            videoSource,
        /* startPositionUs= */ 2_000_000,
        /* endPositionUs= */ 5_000_000);
    exoPlayer.prepare(clippingSource);
    //
    playerView.setUseController(false);
    exoPlayer.setPlayWhenReady(true);
    exoPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);
    Observable.interval(30, TimeUnit.MILLISECONDS).compose(RxUtils.onRxThread())
        .subscribe(new Consumer<Long>() {
          @Override
          public void accept(Long aLong) throws Exception {
            if (exoPlayer != null) {
              Toast.makeText(context,
                  "time=" + exoPlayer.getCurrentPosition() + ",duration:" + exoPlayer.getDuration(),
                  Toast.LENGTH_SHORT)
                  .show();
            }
          }
        });
    exoPlayer.addListener(new EventListener() {
      public static final String TAG = "EXOTEST";

      @Override
      public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

      }

      @Override
      public void onTracksChanged(TrackGroupArray trackGroups,
          TrackSelectionArray trackSelections) {

      }

      @Override
      public void onLoadingChanged(boolean isLoading) {

      }

      @Override
      public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        Logger.t(TAG).d("playWhenReady:" + playWhenReady + ",playbackState:" + playbackState);
        switch (playbackState) {
          case Player.STATE_READY:
            long duration = exoPlayer.getDuration();
//            Toast.makeText(context, "playbackState:" + playbackState + ",duration:" + duration,
//                Toast.LENGTH_SHORT).show();
            break;
        }
      }

      @Override
      public void onRepeatModeChanged(int repeatMode) {

      }

      @Override
      public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

      }

      @Override
      public void onPlayerError(ExoPlaybackException error) {

      }

      @Override
      public void onPositionDiscontinuity(int reason) {

      }

      @Override
      public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

      }

      @Override
      public void onSeekProcessed() {

      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (exoPlayer != null) {
      exoPlayer.release();
    }
  }

  public void getkeyframenum(View view) {
//    String path = new File(FILES_DIR, m_et.getText().toString()).toString();
//    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//      int videoKEY_i_frame_interval = BQMediaUtils.getSingleton()
//          .getVideoKEY_I_FRAME_INTERVAL(path);
//      Log.e("mp4KeyFrame", "videoKEY_i_frame_interval:" + videoKEY_i_frame_interval);
//      Log.e("mp4KeyFrame", "path:" + path);
//      Toast.makeText(ExoplayerActivity.this,
//          "videoKEY_i_frame_interval:" + videoKEY_i_frame_interval, Toast.LENGTH_SHORT).show();
//    }
  }

  public void replay(View view) {
//    String path = new File(FILES_DIR, m_et.getText().toString()).toString();
    mp4VideoUri = Uri.parse(path);
    // This is the MediaSource representing the media to be played.
    MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
        .createMediaSource(mp4VideoUri);
    // Prepare the exoPlayer with the source.
    exoPlayer.prepare(videoSource);
  }

  public void reSetPlayClip(View view) {
    mp4VideoUri = Uri.parse(path);
    // This is the MediaSource representing the media to be played.
    MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
        .createMediaSource(mp4VideoUri);
    ClippingMediaSource clippingSource =
        new ClippingMediaSource(
            videoSource,
        /* startPositionUs= */ 2_000_000,
        /* endPositionUs= */ 5_000_000);
    // Prepare the exoPlayer with the source.
    exoPlayer.prepare(clippingSource);
  }

  public void seek(View view) {
    if (exoPlayer != null) {
      exoPlayer.seekTo(0);
    }
  }

  public void seek2(View view) {
    if (exoPlayer != null) {
      exoPlayer.seekTo(4_000_000);
    }
  }
}
