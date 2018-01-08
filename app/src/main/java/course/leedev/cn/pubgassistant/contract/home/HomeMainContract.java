package course.leedev.cn.pubgassistant.contract.home;

import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.IBaseFragment;
import course.leedev.cn.pubgassistant.base.IBaseModel;

/**
 * Created by lt on 2017/12/30.
 *
 * 助手主页 Contract
 */

public interface HomeMainContract {

    // 主页接口
    abstract class HomeMainPresenter extends BasePresenter<IHomeMainModel, IHomeMainView> {
        public abstract void getTabList();
    }

    interface IHomeMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IHomeMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }
}
