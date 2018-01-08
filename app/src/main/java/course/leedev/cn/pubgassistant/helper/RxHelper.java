package course.leedev.cn.pubgassistant.helper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lt on 2017/12/19.
 */

public class RxHelper {

    /**
     * 统一线程处理
     *
     * 发布事件io线程，接收事件主线程
     */
    public static <T>ObservableTransformer<T, T> rxSchedulerHelper() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
