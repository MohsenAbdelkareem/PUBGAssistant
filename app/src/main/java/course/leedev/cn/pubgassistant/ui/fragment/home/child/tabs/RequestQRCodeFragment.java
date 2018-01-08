package course.leedev.cn.pubgassistant.ui.fragment.home.child.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;
import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.fragment.BaseCompatFragment;
import course.leedev.cn.pubgassistant.contract.home.tabs.RequestQRCodeContract;
import course.leedev.cn.pubgassistant.presenter.home.tabs.RequestQRCodePresenter;
import course.leedev.cn.pubgassistant.service.MyService;
import info.hoang8f.widget.FButton;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lgx on 2018/1/7.
 */

public class RequestQRCodeFragment extends BaseCompatFragment<RequestQRCodeContract.RequestQRCodePresenter,
        RequestQRCodeContract.IRequestQRCodeModel> implements RequestQRCodeContract.IRequestQRCodeView, View.OnClickListener {

    @BindView(R.id.btn_get_phone)
    FButton btnGetPhone;
    @BindView(R.id.btn_copy_message)
    FButton btnCopyMessage;
    @BindView(R.id.btn_copy_phone)
    FButton btnCopyPhone;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_code)
    TextView tvCode;

    private String mToken = "";
    private String mPhone = "";
    private Retrofit retrofit;
    private MyService service;

    public static RequestQRCodeFragment newInstance() {
        Bundle args = new Bundle();
        RequestQRCodeFragment requestQRCodeFragment = new RequestQRCodeFragment();
        requestQRCodeFragment.setArguments(args);
        return requestQRCodeFragment;
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        btnGetPhone.setOnClickListener(this);
        btnCopyPhone.setOnClickListener(this);
        btnCopyMessage.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_request_qrcode;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return RequestQRCodePresenter.newInstance();
    }

    @Override
    public void hideKeybord() {

    }

    @Override
    public void back() {

    }

    @Override
    public void popToFragment(Class<?> targetFragmentClass, boolean includeTargetFragment) {

    }

    @Override
    public void startNewFragment(@NonNull SupportFragment supportFragment) {

    }

    @Override
    public void startNewFragmentWithPop(@NonNull SupportFragment supportFragment) {

    }

    @Override
    public void startNewFragmentForResult(@NonNull SupportFragment supportFragment, int requestCode) {

    }

    @Override
    public void setOnFragmentResult(int resultCode, Bundle data) {

    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {

    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {

    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {

    }

    @Override
    public boolean isVisiable() {
        return false;
    }

    @Override
    public Activity getBindActivity() {
        return null;
    }

    @Override
    public void showPhone(String phone) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_phone:
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.xingjk.cn/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();
                service = retrofit.create(MyService.class);
                Observable<String> observable = service.getToken("loginIn", "api-dvgtuvr9", "6205240");
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                mToken = s.split("\\|")[1];
                                Toast.makeText(mContext, "" + mToken, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Observable<String> getPhone = service.getPhone("getPhone", "55378", mToken, "CMCC");
                                getPhone.subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Observer<String>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onNext(String s) {
                                                mPhone = s.split("\\|")[1];
                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }

                                            @Override
                                            public void onComplete() {
                                                tvPhone.setText(mPhone);
                                                requestCode();
                                            }
                                        });
                            }
                        });



                break;
            case R.id.btn_copy_phone:

                break;
            case R.id.btn_copy_message:

                break;
            default:

                break;
        }
    }

    private void requestCode() {
//        service.getMessage("getMessage", "55378", mPhone, mToken, "thomaslee");
//
    }
}
