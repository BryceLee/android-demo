package textjunit.lizhongxin.bmodule;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;


/**
 * Created by lizhongxin on 26/06/2018.
 */
@Interceptor(priority = 2,name = "Binterceptor")
public class Binterceptor implements IInterceptor{

    private Context context;

    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        Log.d("拦截器","我来自moduleB，我的优先级是2");
        if ("/bq/b".equals(postcard.getPath())){
//            final AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.getThis());
//            ab.setCancelable(false);
//            ab.setTitle("温馨提醒");
//            ab.setMessage("想要跳转到Test4Activity么？(触发了\"/inter/test1\"拦截器，拦截了本次跳转)");
//            ab.setNegativeButton("继续", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    callback.onContinue(postcard);
//                }
//            });
//            ab.setNeutralButton("算了", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    callback.onInterrupt(null);
//                }
//            });
//            ab.setPositiveButton("加点料", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    postcard.withString("extra", "我是在拦截器中附加的参数");
//                    callback.onContinue(postcard);
//                }
//            });
//
//            MainLooper.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    ab.create().show();
//                }
//            });
//            Toast.makeText(context,"bbb",Toast.LENGTH_LONG).show();

            callback.onContinue(postcard);
        }else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        Log.d("interceptor","Binterceptor");
        this.context = context;
    }
}
