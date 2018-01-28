package course.leedev.cn.pubgassistant.ui.fragment.home.child.tabs;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.fragment.BaseCompatFragment;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lt on 2018/1/8.
 */

public class RegisterFragment extends BaseCompatFragment {

    @BindView(R.id.wv_register)
    WebView wvRegister;
    @BindView(R.id.btn_back_register)
    Button btnRegister;
//    @BindView(R.id.btn_back_login)
//    Button btnLogin;
//    @BindView(R.id.btn_back_bind)
//    Button btnBind;

    private WebSettings webSettings;
    private Handler handler = new Handler();

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {

        wvRegister.loadUrl("http://www.jixunjsq.com/user/index/register.html");

        webSettings = wvRegister.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);

//        wvRegister.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
//                webView.loadUrl(s);
//                return super.shouldOverrideUrlLoading(webView, s);
//            }
//        });

        btnClick();
    }

    private void btnClick() {
        RxView.clicks(btnRegister).debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        reloadUrl("http://www.jixunjsq.com/user/index/register.html");
                    }
                });
//
//        RxView.clicks(btnLogin).debounce(300, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        reloadUrl("http://www.jixunjsq.com/user/index/login.html");
//                    }
//                });
//
//        RxView.clicks(btnBind).debounce(300, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        reloadUrl("http://www.jixunjsq.com/user/manage/phone.html");
//                    }
//                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_register;
    }

    public void reloadUrl(String url) {
        wvRegister.loadUrl(url);
    }

}
