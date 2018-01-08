package course.leedev.cn.pubgassistant.base.activity;

import android.content.Intent;
import android.support.annotation.NonNull;

import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.IBaseActivity;
import course.leedev.cn.pubgassistant.base.IBaseModel;

/**
 * Created by lt on 2017/12/26.
 *
 * Mvp Activity 基类
 */

public abstract class BaseMVPCompatActivity<P extends BasePresenter, M extends IBaseModel> extends BaseActivity implements IBaseActivity {

    /**
     * presenter 具体的 presenter 由子类决定
     */
    protected P mPresenter;

    /**
     * model 具体的 model 由子类决定
     */
    protected M mModel;

    /**
     * 初始化数据
     *
     * 子类可重写此方法
     */
    protected void initData() {
        super.initData();
        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mModel = (M) mPresenter.getModel();
            if (mModel != null) {
                mPresenter.attachMV(mModel, this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
        }
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {

        // TODO: startActivity

    }

    @Override
    public void hideKeybord() {
        hiddenKeyboard();
    }

    @Override
    public void back() {
        super.onBackPressedSupport();
    }
}
