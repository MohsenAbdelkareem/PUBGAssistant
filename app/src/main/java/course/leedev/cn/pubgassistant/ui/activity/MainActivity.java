package course.leedev.cn.pubgassistant.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import butterknife.BindView;
import course.leedev.cn.pubgassistant.R;
import course.leedev.cn.pubgassistant.base.activity.BaseActivity;
import course.leedev.cn.pubgassistant.ui.fragment.home.HomeRootFragment;
import course.leedev.cn.pubgassistant.ui.fragment.home.child.HomeFragment;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by lt on 2017/12/19.
 *
 * MainActivity
 */

public class MainActivity extends BaseActivity implements HomeFragment.OnOpenDrawerLayoutListener {

    @BindView(R.id.dl_root)
    DrawerLayout dlRoot;
    @BindView(R.id.bottom_nav_bar)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nv_menu)
    NavigationView nvMenu;

    public static final int FIRST = 1, SECOND = 2, THIRD = 3;

    private SupportFragment[] mFragments = new SupportFragment[3];

    // NavigationView 的 Header 部分内容
    private CircleImageView civHead;

    /**
     * TODO:MovingImageView
     */

    // 再点一次退出程序的时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments[FIRST] = HomeRootFragment.newInstance();

            loadRootFragment(R.id.fl_container, mFragments[FIRST]);
        } else {
            mFragments[FIRST] = findFragment(HomeRootFragment.class);
        }
    }

    @Override
    public void onOpen() {
        if (!dlRoot.isDrawerOpen(GravityCompat.START)) {
            dlRoot.openDrawer(GravityCompat.START);
        }
    }
}
