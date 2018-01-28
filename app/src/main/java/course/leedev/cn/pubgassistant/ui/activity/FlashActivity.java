package course.leedev.cn.pubgassistant.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.activity.BaseActivity;
import course.leedev.cn.pubgassistant.helper.RxHelper;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by lt on 2017/12/19.
 *
 * FlashActivity
 */

public class FlashActivity extends BaseActivity {

    @BindView(R.id.tv_count_down)
    TextView tvCountDown;

    private int mTime = 3;
    private boolean mIsCancle;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initCountDown();
    }

    /**
     * 倒数计时
     */
    private void initCountDown() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mTime - aLong;
                    }
                })
                .compose(RxHelper.<Long>rxSchedulerHelper())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        tvCountDown.setText(String.valueOf(aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (!mIsCancle) {
                            judgeLogin();
                        }
                    }
                });
    }

    /**
     * 登陆判断
     */
    private void judgeLogin() {
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean("logined", false)) {
            startActivity(new Intent(FlashActivity.this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(FlashActivity.this, LoginActivity.class));
            finish();
        }
    }

    @OnClick(R.id.ll_skip)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_skip:
                mIsCancle = true;
                judgeLogin();
                break;
        }
    }

}
