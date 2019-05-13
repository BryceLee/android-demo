package textjunit.lizhongxin.demo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.qqtheme.framework.picker.DateTimePicker;
import textjunit.lizhongxin.demo.R;

public class TimePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {

            private DateTimePicker picker;
            private int year;
            private int month;
            private int day;
            private int hour;
            private int min;
            private int endDay;

            @Override
            public void onClick(View view) {
                Date date = new Date();// 获取当前时间
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
//                sdf.applyPattern("yyyy-MM-dd HH:mm");// a为am/pm的标记
//                Log.e("BQtime现在时间1", sdf.format(date));
                sdf.applyPattern("yyyy-MM-dd");
                String yeartime=sdf.format(date);
                String[] yearArray = yeartime.split("-");
                year=Integer.parseInt(yearArray[0]);
                month=Integer.parseInt(yearArray[1]);
                day=Integer.parseInt(yearArray[2]);
//                Log.e("BQtime现在时间2", sdf.format(date));
                sdf.applyPattern("HH:mm");
                String hourtime=sdf.format(date);
                String[] hourArray = hourtime.split(":");
                hour=Integer.parseInt(hourArray[0]);
                min=Integer.parseInt(hourArray[1]);
//                Log.e("BQtime现在时间3", sdf.format(date));

                if (picker==null){
                    picker = new DateTimePicker(TimePickerActivity.this, DateTimePicker.HOUR_24);
                }
                picker.setDateRangeStart(year, month, day);
                if (month==2){
                    endDay=28;//闰月暂时不管啦
                }else if (month%2==0){
                    endDay=30;
                }else {
                    endDay=31;
                }
                picker.setDateRangeEnd(year+1, 12, endDay);
                picker.setTimeRangeStart(hour, min);
                picker.setTimeRangeEnd(23, 59);
                picker.setTopLineColor(0x9963d9a5);
                picker.setLabelTextColor(0xFF333333);//#333333
                picker.setTextColor(0xFF333333);//#333333
                picker.setTitleTextColor(0xFF333333);
                picker.setCancelTextColor(0xFF333333);
                picker.setSubmitTextColor(0xFF333333);
                picker.setDividerColor(0x63d9a5);
                picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        String time = year + "-" + month + "-" + day + " " + hour + ":" + minute;
//                        定时发布 执行时间：10位时间戳
                        Log.e("BQtime", time);
                        String format = "yyyy-MM-dd HH:mm";
                        SimpleDateFormat sdf = new SimpleDateFormat(format);
                        try {
                            long l = sdf.parse(time).getTime() / 1000;
                            Log.e("BQtime==>", l+"");
                        } catch (ParseException e) {
                            e.printStackTrace();
                            Log.e("BQtime==>", "error");
                        }

                    }
                }); picker.show();
            }
        });
    }
}
