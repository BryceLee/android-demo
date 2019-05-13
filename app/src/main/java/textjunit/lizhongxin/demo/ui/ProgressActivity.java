package textjunit.lizhongxin.demo.ui;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;
import textjunit.lizhongxin.demo.R;

public class ProgressActivity extends AppCompatActivity {

    private ProgressBar mPbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);


        long timestamp = ((long) 100) << 32;
        Log.d("timestamp==>",timestamp+"");

        String model = Build.MODEL;
        Toast.makeText(ProgressActivity.this,model,Toast.LENGTH_LONG).show();
        Log.d("MODEL",model);

        Toast.makeText(ProgressActivity.this, android.os.Build.MODEL, Toast.LENGTH_LONG).show();
        SeekBar mseekbar = (SeekBar) findViewById(R.id.seekbar);
        mPbar = (ProgressBar) findViewById(R.id.publish_progress);
        mseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPbar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
