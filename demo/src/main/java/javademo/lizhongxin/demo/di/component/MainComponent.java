package javademo.lizhongxin.demo.di.component;

import android.app.Activity;
import dagger.Component;
import javademo.lizhongxin.demo.MainActivity;
import javademo.lizhongxin.demo.di.moudle.AModule;

/**
 * Created by lizhongxin on 05/11/2018.
 */
@Component(modules = {AModule.class})
public interface MainComponent {

  void intject(MainActivity activity);

}
