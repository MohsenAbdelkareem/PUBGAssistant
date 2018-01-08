package course.leedev.cn.pubgassistant;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lt on 2017/12/21.
 *
 * 管理 RxJava 的注册订阅和取消订阅
 */

public class RxManager {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void register(Disposable d) {
        mCompositeDisposable.add(d);
    }

    public void unSubscrible() {
        mCompositeDisposable.dispose();
    }

}
