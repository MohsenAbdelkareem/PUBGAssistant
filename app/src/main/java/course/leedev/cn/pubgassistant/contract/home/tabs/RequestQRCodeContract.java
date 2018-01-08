package course.leedev.cn.pubgassistant.contract.home.tabs;

import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.IBaseFragment;
import course.leedev.cn.pubgassistant.base.IBaseModel;

/**
 * Created by lt on 2018/1/7.
 */

public interface RequestQRCodeContract {

    abstract class RequestQRCodePresenter extends BasePresenter<IRequestQRCodeModel, IRequestQRCodeView> {
        /**
         * 获取 token
         * @param action 请求方法
         * @param name 请求用户名
         * @param password 请求密码
         */
        public abstract void getToken(String action, String name, String password);

        /**
         * 获取手机号
         * @param action
         * @param sid
         * @param token
         * @param phoneType 指定营业厅
         */
        public abstract void getPhone(String action, String sid, String token, String phoneType);

        /**
         * 获取短信
         * @param action
         * @param sid
         * @param phone
         * @param token
         * @param author
         */
        public abstract void getMessage(String action, String sid, String phone, String token,
                        String author);
    }

    interface IRequestQRCodeModel extends IBaseModel {

    }

    interface IRequestQRCodeView extends IBaseFragment {

        /**
         * 显示手机号
         * @param phone
         */
        void showPhone(String phone);

        /**
         * 显示短信
         * @param message
         */
        void showMessage(String message);
    }

}
