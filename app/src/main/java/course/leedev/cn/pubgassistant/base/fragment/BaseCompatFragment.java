package course.leedev.cn.pubgassistant.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.IBaseModel;
import course.leedev.cn.pubgassistant.contract.home.tabs.RequestQRCodeContract;
import course.leedev.cn.pubgassistant.global.GlobalApplication;
import course.leedev.cn.pubgassistant.utils.AppUtils;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by lt on 2017/12/21.
 */

public abstract class BaseCompatFragment<R extends BasePresenter<RequestQRCodeContract.IRequestQRCodeModel, RequestQRCodeContract.IRequestQRCodeView>, I extends IBaseModel> extends SupportFragment {

    protected String TAG;
    protected Context mContext;
    protected Activity mActivity;
    protected GlobalApplication mApplication;
    private Unbinder binder;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            return inflater.inflate(getLayoutId(), container, false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getClass().getSimpleName();
        binder = ButterKnife.bind(this, view);
        getBundle(getArguments());
        initData();
        initUI(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null) {
            binder.unbind();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 数据初始化
     */
    protected void initData() {
        mContext = AppUtils.getContext();
        mApplication = (GlobalApplication) mActivity.getApplication();
    }

    /**
     * 初始化UI
     */
    protected abstract void initUI(View view, Bundle savedInstanceState);

    /**
     * 设置 Fragment 的 LayoutView
     *
     * @return Fragment 的 LayoutView
     */
    public abstract int getLayoutId();

    /**
     * 动态更改 Fragment 的 LayoutView
     *
     * @return Fragment 的 LayoutView
     */
    private View getLayoutView() {
        return null;
    }

    /**
     * 得到 Activity 传进来的值
     *
     * @param arguments
     */
    private void getBundle(Bundle arguments) {
    }

    /**
     * 处理回退事件
     *
     * @return true 事件已消费
     * false 事件向上传递
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            return false;
        }
        return true;
    }
}
