package course.leedev.cn.pubgassistant.ui.fragment.home.child.tabs;

import android.os.Bundle;
import android.view.View;

import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.IBaseModel;
import course.leedev.cn.pubgassistant.base.fragment.BaseCompatFragment;
import course.leedev.cn.pubgassistant.contract.home.tabs.RequestQRCodeContract;

/**
 * Created by lt on 2017/12/31.
 */

public class AssistantFragment extends BaseCompatFragment<BasePresenter<RequestQRCodeContract.IRequestQRCodeModel, RequestQRCodeContract.IRequestQRCodeView>, IBaseModel> {

    public static AssistantFragment newInstance() {
        Bundle args = new Bundle();
        AssistantFragment assistantFragment = new AssistantFragment();
        assistantFragment.setArguments(args);
        return assistantFragment;
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_assistant;
    }
}
