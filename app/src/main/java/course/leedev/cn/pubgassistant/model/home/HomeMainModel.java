package course.leedev.cn.pubgassistant.model.home;

import android.support.annotation.NonNull;

import course.leedev.cn.pubgassistant.base.BaseModel;
import course.leedev.cn.pubgassistant.contract.home.HomeMainContract;

/**
 * Created by lt on 2017/12/30.
 *
 * 助手主页 model
 */

public class HomeMainModel extends BaseModel implements HomeMainContract.IHomeMainModel {

    @NonNull
    public static HomeMainModel newInstance() {
        return new HomeMainModel();
    }

    @Override
    public String[] getTabs() {
        return new String[]{"吃鸡助手", "收验证码", "加速器注册"};
    }

}
