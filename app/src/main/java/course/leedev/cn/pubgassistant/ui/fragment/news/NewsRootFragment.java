package course.leedev.cn.pubgassistant.ui.fragment.news;

import android.os.Bundle;
import android.view.View;

import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.fragment.BaseCompatFragment;
import course.leedev.cn.pubgassistant.ui.fragment.home.HomeRootFragment;

/**
 * Created by lt on 2018/1/9.
 */

public class NewsRootFragment extends BaseCompatFragment {

    public static NewsRootFragment newInstance() {
        Bundle args = new Bundle();
        NewsRootFragment fragment = new NewsRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragent_news;
    }
}
