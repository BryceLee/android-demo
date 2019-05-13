package textjunit.lizhongxin.demo.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.lahm.library.EasyProtectorLib;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import textjunit.lizhongxin.demo.R;

public class AsynctaskActivity extends AppCompatActivity {

  private AsyncTask<String, Integer, Long> asyncTask = new AsyncTask<String, Integer, Long>() {

    private int progress;

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      Log.d(TAG, "onPreExecute:" + progress);
    }

    @Override
    protected Long doInBackground(String... strings) {
      if (isCancelled()) {
        return 300L;
      }
      String string = strings[0];
      Log.d(TAG, "doInBackground:" + string);
      while (progress < 100) {
        progress++;
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        publishProgress(progress);
//          Log.d(TAG, "doInBackground:" + progress);
      }
      return 200L;
    }


    @Override
    protected void onPostExecute(Long aLong) {
      super.onPostExecute(aLong);
      Log.d(TAG, "onPostExecute:" + aLong.intValue());
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
      super.onProgressUpdate(values);
      Log.d(TAG, "onProgressUpdate:" + values[0]);
    }

    @Override
    protected void onCancelled() {
      super.onCancelled();
      Log.d(TAG, "onCancelled");
    }

    @Override
    protected void onCancelled(Long aLong) {
      super.onCancelled(aLong);
      Log.d(TAG, "onCancelled:" + aLong.intValue());
    }
  };
  private String TAG = AsynctaskActivity.class.getName();
  private ThreadPoolExecutor executorPool;
  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_asynctask);
//    detect();
    textView=findViewById(R.id.tv);
    textView.setText("😄");
  }

  private void detect() {
//    boolean isRunningInEmulator = EasyProtectorLib.checkIsRunningInEmulator(this);
    int length = known_files.length;
    Log.d("known_files","length:"+length);
    if (hasQEmuFiles()){
      textView.setText("你偷偷用模拟器");
    }else {
      textView.setText("不是模拟器");
    }
  }
  private static String[] known_files = {"/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace",
      "/system/bin/qemu-props",
  //
      // vbox模擬器文檔
      "/data/youwave_id",
      "/dev/vboxguest",
      "/dev/vboxuser",
      "/mnt/prebundledapps/bluestacks.prop.orig",
      "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.note",
      "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s2",
      "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s3",
      "/mnt/sdcard/bstfolder/InputMapper/com.bluestacks.appmart.cfg",
      "/mnt/sdcard/buildroid-gapps-ics-20120317-signed.tgz",
      "/mnt/sdcard/windows/InputMapper/com.bluestacks.appmart.cfg",
      "/proc/irq/9/vboxguest",
      "/sys/bus/pci/drivers/vboxguest",
      "/sys/bus/pci/drivers/vboxguest/0000:00:04.0",
      "/sys/bus/pci/drivers/vboxguest/bind",
      "/sys/bus/pci/drivers/vboxguest/module",
      "/sys/bus/pci/drivers/vboxguest/new_id",
      "/sys/bus/pci/drivers/vboxguest/remove_id",
      "/sys/bus/pci/drivers/vboxguest/uevent",
      "/sys/bus/pci/drivers/vboxguest/unbind",
      "/sys/bus/platform/drivers/qemu_pipe",
      "/sys/bus/platform/drivers/qemu_trace",
      "/sys/class/bdi/vboxsf-c",
      "/sys/class/misc/vboxguest",
      "/sys/class/misc/vboxuser",
      "/sys/devices/virtual/bdi/vboxsf-c",
      "/sys/devices/virtual/misc/vboxguest",
      "/sys/devices/virtual/misc/vboxguest/dev",
      "/sys/devices/virtual/misc/vboxguest/power",
      "/sys/devices/virtual/misc/vboxguest/subsystem",
      "/sys/devices/virtual/misc/vboxguest/uevent",
      "/sys/devices/virtual/misc/vboxuser",
      "/sys/devices/virtual/misc/vboxuser/dev",
      "/sys/devices/virtual/misc/vboxuser/power",
      "/sys/devices/virtual/misc/vboxuser/subsystem",
      "/sys/devices/virtual/misc/vboxuser/uevent",
      "/sys/module/vboxguest",
      "/sys/module/vboxguest/coresize",
      "/sys/module/vboxguest/drivers",
      "/sys/module/vboxguest/drivers/pci:vboxguest",
      "/sys/module/vboxguest/holders",
      "/sys/module/vboxguest/holders/vboxsf",
      "/sys/module/vboxguest/initsize",
      "/sys/module/vboxguest/initstate",
      "/sys/module/vboxguest/notes",
      "/sys/module/vboxguest/notes/.note.gnu.build-id",
      "/sys/module/vboxguest/parameters",
      "/sys/module/vboxguest/parameters/log",
      "/sys/module/vboxguest/parameters/log_dest",
      "/sys/module/vboxguest/parameters/log_flags",
      "/sys/module/vboxguest/refcnt",
      "/sys/module/vboxguest/sections",
      "/sys/module/vboxguest/sections/.altinstructions",
      "/sys/module/vboxguest/sections/.altinstr_replacement",
      "/sys/module/vboxguest/sections/.bss",
      "/sys/module/vboxguest/sections/.data",
      "/sys/module/vboxguest/sections/.devinit.data",
      "/sys/module/vboxguest/sections/.exit.text",
      "/sys/module/vboxguest/sections/.fixup",
      "/sys/module/vboxguest/sections/.gnu.linkonce.this_module",
      "/sys/module/vboxguest/sections/.init.text",
      "/sys/module/vboxguest/sections/.note.gnu.build-id",
      "/sys/module/vboxguest/sections/.rodata",
      "/sys/module/vboxguest/sections/.rodata.str1.1",
      "/sys/module/vboxguest/sections/.smp_locks",
      "/sys/module/vboxguest/sections/.strtab",
      "/sys/module/vboxguest/sections/.symtab",
      "/sys/module/vboxguest/sections/.text",
      "/sys/module/vboxguest/sections/__ex_table",
      "/sys/module/vboxguest/sections/__ksymtab",
      "/sys/module/vboxguest/sections/__ksymtab_strings",
      "/sys/module/vboxguest/sections/__param",
      "/sys/module/vboxguest/srcversion",
      "/sys/module/vboxguest/taint",
      "/sys/module/vboxguest/uevent",
      "/sys/module/vboxguest/version",
      "/sys/module/vboxsf",
      "/sys/module/vboxsf/coresize",
      "/sys/module/vboxsf/holders",
      "/sys/module/vboxsf/initsize",
      "/sys/module/vboxsf/initstate",
      "/sys/module/vboxsf/notes",
      "/sys/module/vboxsf/notes/.note.gnu.build-id",
      "/sys/module/vboxsf/refcnt",
      "/sys/module/vboxsf/sections",
      "/sys/module/vboxsf/sections/.bss",
      "/sys/module/vboxsf/sections/.data",
      "/sys/module/vboxsf/sections/.exit.text",
      "/sys/module/vboxsf/sections/.gnu.linkonce.this_module",
      "/sys/module/vboxsf/sections/.init.text",
      "/sys/module/vboxsf/sections/.note.gnu.build-id",
      "/sys/module/vboxsf/sections/.rodata",
      "/sys/module/vboxsf/sections/.rodata.str1.1",
      "/sys/module/vboxsf/sections/.smp_locks",
      "/sys/module/vboxsf/sections/.strtab",
      "/sys/module/vboxsf/sections/.symtab",
      "/sys/module/vboxsf/sections/.text",
      "/sys/module/vboxsf/sections/__bug_table",
      "/sys/module/vboxsf/sections/__param",
      "/sys/module/vboxsf/srcversion",
      "/sys/module/vboxsf/taint",
      "/sys/module/vboxsf/uevent",
      "/sys/module/vboxsf/version",
      "/sys/module/vboxvideo",
      "/sys/module/vboxvideo/coresize",
      "/sys/module/vboxvideo/holders",
      "/sys/module/vboxvideo/initsize",
      "/sys/module/vboxvideo/initstate",
      "/sys/module/vboxvideo/notes",
      "/sys/module/vboxvideo/notes/.note.gnu.build-id",
      "/sys/module/vboxvideo/refcnt",
      "/sys/module/vboxvideo/sections",
      "/sys/module/vboxvideo/sections/.data",
      "/sys/module/vboxvideo/sections/.exit.text",
      "/sys/module/vboxvideo/sections/.gnu.linkonce.this_module",
      "/sys/module/vboxvideo/sections/.init.text",
      "/sys/module/vboxvideo/sections/.note.gnu.build-id",
      "/sys/module/vboxvideo/sections/.rodata.str1.1",
      "/sys/module/vboxvideo/sections/.strtab",
      "/sys/module/vboxvideo/sections/.symtab",
      "/sys/module/vboxvideo/sections/.text",
      "/sys/module/vboxvideo/srcversion",
      "/sys/module/vboxvideo/taint",
      "/sys/module/vboxvideo/uevent",
      "/sys/module/vboxvideo/version",
      "/system/app/bluestacksHome.apk",
      "/system/bin/androVM-prop",
      "/system/bin/androVM-vbox-sf",
      "/system/bin/androVM_setprop",
      "/system/bin/get_androVM_host",
      "/system/bin/mount.vboxsf",
      "/system/etc/init.androVM.sh",
      "/system/etc/init.buildroid.sh",
      "/system/lib/hw/audio.primary.vbox86.so",
      "/system/lib/hw/camera.vbox86.so",
      "/system/lib/hw/gps.vbox86.so",
      "/system/lib/hw/gralloc.vbox86.so",
      "/system/lib/hw/sensors.vbox86.so",
      "/system/lib/modules/3.0.8-android-x86+/extra/vboxguest",
      "/system/lib/modules/3.0.8-android-x86+/extra/vboxguest/vboxguest.ko",
      "/system/lib/modules/3.0.8-android-x86+/extra/vboxsf",
      "/system/lib/modules/3.0.8-android-x86+/extra/vboxsf/vboxsf.ko",
      "/system/lib/vboxguest.ko",
      "/system/lib/vboxsf.ko",
      "/system/lib/vboxvideo.ko",
      "/system/usr/idc/androVM_Virtual_Input.idc",
      "/system/usr/keylayout/androVM_Virtual_Input.kl",

      "/system/xbin/mount.vboxsf",
      "/ueventd.android_x86.rc",
      "/ueventd.vbox86.rc",
      "/ueventd.goldfish.rc",
      "/fstab.vbox86",
      "/init.vbox86.rc",
      "/init.goldfish.rc",

      // ========針對原生Android模擬器 內核：goldfish===========
      "/sys/module/goldfish_audio",
      "/sys/module/goldfish_sync",

      // ========針對藍疊模擬器===========
      "/data/app/com.bluestacks.appmart-1.apk",
      "/data/app/com.bluestacks.BstCommandProcessor-1.apk",
      "/data/app/com.bluestacks.help-1.apk",
      "/data/app/com.bluestacks.home-1.apk",
      "/data/app/com.bluestacks.s2p-1.apk",
      "/data/app/com.bluestacks.searchapp-1.apk",
      "/data/bluestacks.prop",
      "/data/data/com.androVM.vmconfig",
      "/data/data/com.bluestacks.accelerometerui",
      "/data/data/com.bluestacks.appfinder",
      "/data/data/com.bluestacks.appmart",
      "/data/data/com.bluestacks.appsettings",
      "/data/data/com.bluestacks.BstCommandProcessor",
      "/data/data/com.bluestacks.bstfolder",
      "/data/data/com.bluestacks.help",
      "/data/data/com.bluestacks.home",
      "/data/data/com.bluestacks.s2p",
      "/data/data/com.bluestacks.searchapp",
      "/data/data/com.bluestacks.settings",
      "/data/data/com.bluestacks.setup",
      "/data/data/com.bluestacks.spotlight",

      // ========針對逍遙安卓模擬器===========
      // 虛擬化網卡和pci，可能存在誤判，不可靠
//            "/sys/module/virtio_net",
//            "/sys/module/virtio_pci",
      "/data/data/com.microvirt.download",
      "/data/data/com.microvirt.guide",
      "/data/data/com.microvirt.installer",
      "/data/data/com.microvirt.launcher",
      "/data/data/com.microvirt.market",
      "/data/data/com.microvirt.memuime",
      "/data/data/com.microvirt.tools",

      // ========針對Mumu模擬器===========
      "/data/data/com.mumu.launcher",
      "/data/data/com.mumu.store",
      "/data/data/com.netease.mumu.cloner"

   };
  /**
   * Check the existence of known files used by the Android QEmu environment.
   *
   * @return {@code true} if any files where found to exist or {@code false} if not.
   */
  public static boolean hasQEmuFiles() {
    for (String pipe : known_files) {
      File qemu_file = new File(pipe);
      if (qemu_file.exists()) {
        return true;
      }
    }

    return false;
  }
  public static boolean isPcKernel(){
    String str = "";
    try {
      Process start = new ProcessBuilder(new String[]{"/system/bin/cat", "/proc/cpuinfo"}).start();
      StringBuffer stringBuffer = new StringBuffer();
      String str2 = "";
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream(), "utf-8"));
      while (true) {
        String readLine = bufferedReader.readLine();
        if (readLine == null) {
          break;
        }
        stringBuffer.append(readLine);
      }
      bufferedReader.close();
      str = stringBuffer.toString().toLowerCase();
      if (str.contains("intel") || str.contains("amd")) {
        return true;
      }
    } catch (IOException e) {
    }
    return false;
  }
  @SuppressLint("StaticFieldLeak")
  public void onAction(View view) {
    detect();

//    for (int i = 0; i < 1000000; i++) {
//      sendData(i);
//    }
//    asyncTask.execute("go");
    //RejectedExecutionHandler implementation
    //Get the ThreadFactory implementation to use
    //creating the ThreadPoolExecutor
//    executorPool = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
//        new SynchronousQueue<>());
//    ExecutorService executorService = Executors.newFixedThreadPool(100);
//    //submit work to the thread pool
//    Runnable runnable = new Runnable() {
//      @Override
//      public void run() {
//        try {
//          Thread.sleep(10);
//          Log.d(TAG, "" + Thread.currentThread().getName());
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//      }
//    };
//    for (int i = 0; i < 10000; i++) {
////      executorPool.execute(runnable);
//      executorService.execute(runnable);
//    }

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  public void onCancel(View view) {
    Log.d(TAG, "onCancel");
    if (asyncTask != null) {
      asyncTask.cancel(false);
    }
    //shut down the pool
    if (executorPool != null) {
      executorPool.shutdown();
    }
  }

  private void sendData(final int num) {
    Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        emitter.onNext(1);
        Log.d(TAG, "========num=" + num + ",thread-name=" + Thread.currentThread().getName());
      }
    });
    Observer<Integer> observer = new Observer<Integer>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(Integer integer) {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    };
    integerObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer);
//    Observable.create(new Observable.OnSubscribe<Integer>() {
//      @Override
//      public void call(Subscriber<? super Integer> subscriber) {
//        try {
//          Thread.sleep(400);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//
//        subscriber.onNext(num);
//        subscriber.onCompleted();
//      }
//    }).subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
//      @Override
//      public void onNext(Integer num) {
//        Log.d(TAG, "current_tread == " + Thread.currentThread().getId());
//      }
//
//      @Override
//      public void onCompleted() {
//        Log.d(TAG, "work_num == " + num);
//      }
//
//      @Override
//      public void onError(Throwable e) {
//      }
//    });
  }
}
