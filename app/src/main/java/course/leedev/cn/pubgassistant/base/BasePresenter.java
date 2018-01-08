package course.leedev.cn.pubgassistant.base;

import android.support.annotation.NonNull;

import course.leedev.cn.pubgassistant.RxManager;

/**
 * Created by lt on 2017/12/26.
 *
 * base presenter
 */

public abstract class BasePresenter<M, V> {

    public M mIModel;
    public V mIView;
    protected RxManager rxManager = new RxManager();

    /**
     * 返回 presenter 想持有的 model 引用
     *
     * @return presenter 持有的 model 引用
     */
    public abstract M getModel();

    /**
     * 绑定 IModel 和 IView 的引用
     *
     * @param m model
     * @param v view
     */
    public void attachMV(@NonNull M m, @NonNull V v) {
        this.mIModel = m;
        this.mIView = v;
        this.onStart();
    }

    /**
     * 解绑 IModel 和 IView
     */
    public void detachMV() {
        rxManager.unSubscrible();
        this.mIModel = null;
        this.mIView = null;
    }

    /**
     * IModel 和 IView 绑定成功立即执行
     *
     * 实现绑定后完成的逻辑，例如数据初始化，界面初始化、更新等
     */
    protected abstract void onStart();

}
