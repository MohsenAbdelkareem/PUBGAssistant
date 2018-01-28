package course.leedev.cn.pubgassistant.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Observable;

import butterknife.BindView;
import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.activity.BaseActivity;
import course.leedev.cn.pubgassistant.model.User;
import course.leedev.cn.pubgassistant.service.MyService;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

/**
 * Created by lt on 2018/1/9.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login)
    MaterialLoginView loginView;

    private Retrofit retrofit;
    private MyService service;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(final Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ((DefaultLoginView) loginView.getLoginView()) .setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.bmob.cn/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service = retrofit.create(MyService.class);
                io.reactivex.Observable<User> observable = service.login(loginUser.getEditText().getText().toString(), loginPass.getEditText().getText().toString());
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(User user) {
                                if (user.getSessionToken() != null) {
                                    editor.putBoolean("logined", true);
                                    editor.putString("username", user.getUsername());
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(mApplication, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        ((DefaultRegisterView)loginView.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                Toast.makeText(mApplication, "抱歉，暂不开放注册！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
