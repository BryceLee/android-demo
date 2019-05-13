package textjunit.lizhongxin.demo.ui;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;
import textjunit.lizhongxin.demo.R;
import textjunit.lizhongxin.demo.widget.VideoTextureView;

public class VideoViewActivity extends AppCompatActivity {
//    String path="/storage/emulated/0/Pictures/watermark_download/DIY/Download/orignal162623578.mp4";
    String path="/storage/emulated/0/Pictures/watermark_download/DIY/Download/orignal.mp4";
    private VideoTextureView textture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        VideoView videoview = (VideoView) findViewById(R.id.videoview);
        videoview.setVisibility(View.GONE);
//        textture = (VideoTextureView) findViewById(R.id.textture);
//        textture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                textture.setVideoUrl(path);
//            }
//        });
//        videoview.setVideoPath(path);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(path);
        long TRACKS = Long.parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS));
        Log.d("videolzx",TRACKS+"");
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText("TRACKS:"+TRACKS);
        if (true){
            return;
        }

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        });

    }
}
