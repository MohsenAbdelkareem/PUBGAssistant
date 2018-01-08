package course.leedev.cn.pubgassistant.ui.fragment.home.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.adapter.FragmentAdapter;
import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.fragment.BaseMVPCompatFragment;
import course.leedev.cn.pubgassistant.contract.home.HomeMainContract;
import course.leedev.cn.pubgassistant.presenter.home.HomeMainPresenter;
import course.leedev.cn.pubgassistant.ui.fragment.home.child.tabs.AssistantFragment;
import course.leedev.cn.pubgassistant.ui.fragment.home.child.tabs.RegisterFragment;
import course.leedev.cn.pubgassistant.ui.fragment.home.child.tabs.RequestQRCodeFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by lt on 2017/12/21.
 *
 * 助手页面
 */

public class HomeFragment extends BaseMVPCompatFragment<HomeMainContract.HomeMainPresenter,
        HomeMainContract.IHomeMainModel> implements HomeMainContract.IHomeMainView {

    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.tl_tab)
    TabLayout tlTabs;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.fab_recharge)
    FloatingActionButton fabRecharge;

    protected OnOpenDrawerLayoutListener onOpenDrawerLayoutListener;
    private List<Fragment> fragments;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment  = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOpenDrawerLayoutListener) {
            onOpenDrawerLayoutListener = (OnOpenDrawerLayoutListener) context;
        }
        fragments = new ArrayList<>();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onOpenDrawerLayoutListener = null;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getTabList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_;
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        toolbar.setTitle("首页");
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOpenDrawerLayoutListener != null) {
                    onOpenDrawerLayoutListener.onOpen();
                }
            }
        });
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    fabRecharge.show();
                } else {
                    fabRecharge.hide();
                }
            }
        });
        fabRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 充值按钮事件
            }
        });
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exit_login:
                        // TODO: 退出登陆
                        break;
                }
                return false;
            }
        });
        // TODO: toolbar 动画
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return HomeMainPresenter.newInstance();
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
    public void showTabList(String[] tabs) {
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.addTab(tlTabs.newTab().setText(tabs[i]));
            switch (i) {
                case 0:
                    fragments.add(AssistantFragment.newInstance());
                    break;
                case 1:
                    fragments.add(RequestQRCodeFragment.newInstance());
                    break;
                case 2:
                    fragments.add(RegisterFragment.newInstance());
                    break;
                default:
                    fragments.add(AssistantFragment.newInstance());
                    break;
            }
        }
        vpFragment.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments));
        tlTabs.setupWithViewPager(vpFragment);
        tlTabs.setVerticalScrollbarPosition(0);
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.getTabAt(i).setText(tabs[i]);
        }
    }

    /**
     * fragment 打开 DrawerLayout 监听
     */
    public interface OnOpenDrawerLayoutListener {
        void onOpen();
    }
}
