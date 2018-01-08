package course.leedev.cn.pubgassistant.presenter.home.tabs;

import course.leedev.cn.pubgassistant.contract.home.tabs.RequestQRCodeContract;
import course.leedev.cn.pubgassistant.model.home.RequestQRCodeModel;

/**
 * Created by thoma on 2018/1/8.
 */

public class RequestQRCodePresenter extends RequestQRCodeContract.RequestQRCodePresenter {

    public static RequestQRCodePresenter newInstance() {
        return new RequestQRCodePresenter();
    }

    @Override
    public RequestQRCodeContract.IRequestQRCodeModel getModel() {
        return new RequestQRCodeModel();
    }

    @Override
    protected void onStart() {

    }

    @Override
    public void getToken(String action, String name, String password) {

    }

    @Override
    public void getPhone(String action, String sid, String token, String phoneType) {

    }

    @Override
    public void getMessage(String action, String sid, String phone, String token, String author) {

    }
}
