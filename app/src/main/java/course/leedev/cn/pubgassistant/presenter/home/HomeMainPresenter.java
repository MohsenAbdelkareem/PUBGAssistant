package course.leedev.cn.pubgassistant.presenter.home;

import android.support.annotation.NonNull;

import course.leedev.cn.pubgassistant.contract.home.HomeMainContract;
import course.leedev.cn.pubgassistant.model.home.HomeMainModel;

/**
 * Created by lt on 2017/12/31.
 */

public class HomeMainPresenter extends HomeMainContract.HomeMainPresenter {

    @NonNull
    public static HomeMainPresenter newInstance() {
        return new HomeMainPresenter();
    }

    @Override
    public void getTabList() {
        if (mIView == null || mIModel == null) {
            return;
        }
        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    public HomeMainContract.IHomeMainModel getModel() {
        return HomeMainModel.newInstance();
    }

    @Override
    protected void onStart() {

    }
}
