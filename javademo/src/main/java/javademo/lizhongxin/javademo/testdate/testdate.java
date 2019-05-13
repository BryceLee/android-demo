package javademo.lizhongxin.javademo.testdate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class testdate {

  public static void main(String args[]) {
//    DateFormat df1 = null ;     // 声明一个DateFormat
//    DateFormat df2 = null ;     // 声明一个DateFormat
//    df1 = DateFormat.getDateInstance(DateFormat.YEAR_FIELD,new Locale("zh","CN")) ; // 得到日期的DateFormat对象
//    df2 = DateFormat.getDateTimeInstance(DateFormat.YEAR_FIELD,DateFormat.ERA_FIELD,new Locale("zh","CN")) ;    // 得到日期时间的DateFormat对象
//    System.out.println("DATE：" + df1.format(new Date())) ; // 按照日期格式化
//    System.out.println("DATETIME：" + df2.format(new Date())) ;   // 按照日期时间格式化
    String time = "2019-01-01 13:45:67";
    {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
      try {
        Date date = simpleDateFormat.parse(time);
        System.out.println("one````");
        System.out.println(date.toString());
        {

          SimpleDateFormat format = new SimpleDateFormat("d天 HH 时 mm 分 ss 秒后过期",Locale.CHINA);
          System.out.println(format.format(date));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        System.out.println(
            calendar.get(Calendar.DATE) + "天，" + calendar.get(Calendar.HOUR) + "时" + calendar
                .get(Calendar.MINUTE) + "分" + calendar.get(Calendar.MINUTE) + "秒");
        System.out.println("---------");

        System.out.println(
            date.getDay() + "天，" + date.getHours() + "时" + date.getMinutes() + "分" + date
                .getSeconds() + "秒");


      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
//    {
//      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
//      Date date = null;
//      try {
//        System.out.println("two````");
//        date = simpleDateFormat.parse(time);
//        System.out.println(date.toString());
//        {
//
//          SimpleDateFormat format = new SimpleDateFormat("d天 HH 时 mm 分 ss 秒后过期");
//          System.out.println(format.format(date));
//        }
//        System.out.println(
//            date.getDay() + "天，" + date.getHours() + "时" + date.getMinutes() + "分" + date
//                .getSeconds() + "秒");
//      } catch (ParseException e) {
//        e.printStackTrace();
//      }
//    }

  }
}
