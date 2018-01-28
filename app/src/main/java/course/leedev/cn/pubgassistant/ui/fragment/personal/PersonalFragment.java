package course.leedev.cn.pubgassistant.ui.fragment.personal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.fragment.BaseCompatFragment;
import course.leedev.cn.pubgassistant.ui.fragment.news.NewsRootFragment;
import retrofit2.http.POST;

/**
 * Created by thoma on 2018/1/9.
 */

public class PersonalFragment extends BaseCompatFragment {

    @BindView(R.id.tv_user)
    TextView tvUser;

    private SharedPreferences sharedPreferences;

    public static PersonalFragment newInstance() {
        Bundle args = new Bundle();
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        tvUser.setText(tvUser.getText() + sharedPreferences.getString("username", "unknow"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }
}
