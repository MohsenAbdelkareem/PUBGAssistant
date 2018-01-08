package course.leedev.cn.pubgassistant.base.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import course.leedev.cn.pubgassistant.AppManager;
import course.leedev.cn.pubgassistant.global.GlobalApplication;
import course.leedev.cn.pubgassistant.utils.AppUtils;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by lt on 2017/12/19.
 *
 * BaseActivity
 */

public abstract class BaseActivity extends SupportActivity {

    protected GlobalApplication mApplication;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 数据的初始化
     *
     * @param savedInstanceState
     */
    private void init(Bundle savedInstanceState) {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
        initView(savedInstanceState);
        AppManager.getAppManager().addActivity(this);

        // 状态栏透明
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    protected void initData() {
        mContext = AppUtils.getContext();
        mApplication = (GlobalApplication) getApplication();
    }

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 使用竖向动画切换 fragment
        return super.onCreateFragmentAnimator();
    }

    /**
     * 隐藏键盘
     *
     * @return 隐藏键盘结果
     * true 隐藏成功
     * false 隐藏失败
     */
    protected boolean hiddenKeyboard() {
        // 点击空白位置 隐藏软键盘
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService
                (INPUT_METHOD_SERVICE);
        return mInputMethodManager.hideSoftInputFromWindow(this
        .getCurrentFocus().getWindowToken(), 0);
    }

}
