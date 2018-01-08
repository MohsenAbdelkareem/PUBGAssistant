package course.leedev.cn.pubgassistant.model.home;

import android.support.annotation.NonNull;

import course.leedev.cn.pubgassistant.constant.RequestQRCodeConstant;
import course.leedev.cn.pubgassistant.contract.home.tabs.RequestQRCodeContract;

/**
 * Created by lt on 2018/1/7.
 */

public class RequestQRCodeModel implements RequestQRCodeContract.IRequestQRCodeModel{

    @NonNull
    public static RequestQRCodeModel newInstant() {
        return new RequestQRCodeModel();
    }

}
