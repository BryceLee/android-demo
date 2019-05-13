package Thread;

import android.support.annotation.WorkerThread;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;

/**
 * Created by lizhongxin on 22/01/2019.
 */

public class MulThreadStackOOM {

  private static final String TAG = MulThreadStackOOM.class.getName();

  public static void main(String[] a) {
//    HashMap<String, String> stringStringHashMap = new HashMap<>();
//    for (int i = 0; i < 1000; i++) {
//      sendData(i);
//    }
    //RejectedExecutionHandler implementation
    //Get the ThreadFactory implementation to use
    ThreadFactory threadFactory = Executors.defaultThreadFactory();
    //creating the ThreadPoolExecutor
    ThreadPoolExecutor executorPool = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
    //submit work to the thread pool
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(500);
          Log.d(TAG,""+Thread.currentThread().getName());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    for(int i=0; i<10; i++){
      executorPool.execute(runnable);
    }

    try {
      Thread.sleep(30000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //shut down the pool
    executorPool.shutdown();
  }

  private static void sendData(final int num) {
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
