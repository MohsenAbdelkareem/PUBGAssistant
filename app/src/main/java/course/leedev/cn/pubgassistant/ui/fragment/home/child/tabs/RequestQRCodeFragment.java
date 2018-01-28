package course.leedev.cn.pubgassistant.ui.fragment.home.child.tabs;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import org.reactivestreams.Subscription;
import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.fragment.BaseCompatFragment;
import course.leedev.cn.pubgassistant.contract.home.tabs.RequestQRCodeContract;
import course.leedev.cn.pubgassistant.helper.RxHelper;
import course.leedev.cn.pubgassistant.presenter.home.tabs.RequestQRCodePresenter;
import course.leedev.cn.pubgassistant.service.MyService;
import info.hoang8f.widget.FButton;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
    private String mCode = "";
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

        RxView.clicks(btnGetPhone).debounce(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
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

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        btnGetPhone.setEnabled(false);
                        Observable.interval(1, TimeUnit.SECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .take(3)
                                .map(new Function<Long, Long>() {
                                    @Override
                                    public Long apply(Long aLong) throws Exception {
                                        return 3 - aLong;
                                    }
                                })
                                .subscribe(new Observer<Long>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Long aLong) {
                                        btnGetPhone.setText(btnGetPhone.getText().toString() + "(" + aLong + "s)");
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        btnGetPhone.setText("接收验证码");
                                        btnGetPhone.setEnabled(true);
                                    }
                                });
                    }
                });

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
            case R.id.btn_copy_phone:
                putTextIntoClip(this.mContext, tvPhone.getText().toString());
                Toast.makeText(mContext, tvPhone.getText().toString() + "已复制到剪切板", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_copy_message:
                putTextIntoClip(this.mContext, tvCode.getText().toString());
                Toast.makeText(mContext, tvCode.getText().toString() + "已复制到剪切板", Toast.LENGTH_SHORT).show();
                break;
            default:

                break;
        }
    }

    /**
     * 验证码请求，每六秒一次
     */
    private void requestCode() {
        Observable observable = Observable.interval(1, 6, TimeUnit.SECONDS)
                .take(60)
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {

                        // 手机号码释放
                        if (aLong == 59) {
                            Observable<String> cancelRecv = service.cancleRecv("cancelRecv", "55378", mPhone, mToken);
                            cancelRecv.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<String>() {

                                        @Override
                                        public void accept(String s) throws Exception {
                                            Toast.makeText(mContext, "请求次数过多，请重新接受验证码", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            return false;
                        }

                        Observable<String> observable1 = service.getMessage("getMessage", "55378", mPhone, mToken, "thomaslee");
                        observable1.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<String>() {
                                    @Override
                                    public void accept(String s) throws Exception {
                                        mCode = s;
                                        if (mCode.split("\\|")[0].equals("1")) {
                                            tvCode.setText(s.split("\\|")[1]);
                                            Toast.makeText(mContext, "验证码为" + s.split("\\|")[1], Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(mContext, "正在接收验证码，请稍候！", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        boolean isOk = mCode.split("\\|")[0].equals("1");
                        return isOk;
                    }
                });
        observable.subscribe();

    }

    public void putTextIntoClip(Context context, String data){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建ClipData对象
        ClipData clipData = ClipData.newPlainText("simple text copy", data);
        //添加ClipData对象到剪切板中
        clipboardManager.setPrimaryClip(clipData);
    }
}
