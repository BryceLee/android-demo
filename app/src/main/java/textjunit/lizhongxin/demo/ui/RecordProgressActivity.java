package textjunit.lizhongxin.demo.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import textjunit.lizhongxin.demo.CheckExitService;
import textjunit.lizhongxin.demo.widget.CustomRecordProgress;
import textjunit.lizhongxin.demo.widget.PorterDuffXfermodeView;
import textjunit.lizhongxin.demo.R;
import textjunit.lizhongxin.demo.TestClass;

public class RecordProgressActivity extends AppCompatActivity {
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA","onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA","onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_progress);
        TestClass test = getTest();
        if (test==null){
            Log.d("getTest","TestClass1_null");
        }else {
            Log.d("getTest","TestClass2");
        }
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Log.d("timeer",System.currentTimeMillis()+"");
            }
        }, 0, 10);

        Intent intent=new Intent(this,CheckExitService.class);
        startService(intent);


        final CustomRecordProgress mpv = (CustomRecordProgress) findViewById(R.id.pv);
        SeekBar pb = (SeekBar) findViewById(R.id.pb);

        final PorterDuffXfermodeView view = (PorterDuffXfermodeView) findViewById(R.id.view);
        pb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mpv.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                view.animeStart();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mpv.setShowSplit();
                showdialog();

            }
        });
    }
    @NonNull
    private TestClass getTest() {
        if (true){
            return null;
        }
        return new TestClass();
    }

    public void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RecordProgressActivity.this);

//        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("提示");
        builder.setMessage("请选择你要进行的操作");
        //	第一个按钮
        builder.setPositiveButton("覆盖", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                //	提示信息
                Toast toast = Toast.makeText(getApplicationContext(), "你选择了覆盖", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //	中间的按钮
        builder.setNeutralButton("跳过", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                //	提示信息
                Toast toast = Toast.makeText(getApplicationContext(), "你选择了跳过", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //	第三个按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                //	提示信息
                Toast toast = Toast.makeText(getApplicationContext(), "你选择了取消", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //	Diglog的显示
        builder.create().show();

    }
}
