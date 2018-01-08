package course.leedev.cn.pubgassistant.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.IBaseModel;
import course.leedev.cn.pubgassistant.base.fragment.BaseCompatFragment;
import course.leedev.cn.pubgassistant.contract.home.tabs.RequestQRCodeContract;
import course.leedev.cn.pubgassistant.ui.fragment.home.child.HomeFragment;

/**
 * Created by lt on 2017/12/21.
 *
 * 助手页面
 */

public class HomeRootFragment extends BaseCompatFragment<BasePresenter<RequestQRCodeContract.IRequestQRCodeModel, RequestQRCodeContract.IRequestQRCodeView>, IBaseModel> {

    public static HomeRootFragment newInstance() {
        Bundle args = new Bundle();
        HomeRootFragment fragment = new HomeRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 加载子 Fragment
     */
    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
