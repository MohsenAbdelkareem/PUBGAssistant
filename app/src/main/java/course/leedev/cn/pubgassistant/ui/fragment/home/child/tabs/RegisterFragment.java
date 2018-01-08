package course.leedev.cn.pubgassistant.ui.fragment.home.child.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.fragment.BaseCompatFragment;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by thoma on 2018/1/8.
 */

public class RegisterFragment extends BaseCompatFragment {

    @BindView(R.id.wv_register)
    WebView wvRegister;

    private WebSettings webSettings;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        wvRegister.loadUrl("http://www.jixunjsq.com/user/index/register.html");

        webSettings = wvRegister.getSettings();
        webSettings.setJavaScriptEnabled(true);
        
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_register;
    }

}
