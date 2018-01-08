package course.leedev.cn.pubgassistant.base.fragment;

import android.app.Activity;

import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.IBaseFragment;
import course.leedev.cn.pubgassistant.base.IBaseModel;
import course.leedev.cn.pubgassistant.contract.home.tabs.RequestQRCodeContract;

/**
 * Created by lt on 2017/12/26.
 *
 * Mvp Fragment 实例
 */

public abstract class BaseMVPCompatFragment<P extends BasePresenter, M extends IBaseModel> extends BaseCompatFragment<BasePresenter<RequestQRCodeContract.IRequestQRCodeModel, RequestQRCodeContract.IRequestQRCodeView>, IBaseModel> implements IBaseFragment{

    public P mPresenter;
    public M mIModel;

    /**
     * 准备数据
     */
    public void initData() {
        super.initData();
        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mIModel = (M) mPresenter.getModel();
            if (mIModel != null) {
                mPresenter.attachMV(mIModel, this);
            }
        }
    }

    // TODO: start

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
        }
    }

    @Override
    public void hideKeybord() {
        hideSoftInput();
    }

    @Override
    public boolean isVisiable() {
        return isSupportVisible();
    }

    @Override
    public Activity getBindActivity() {
        return mActivity;
    }
}
