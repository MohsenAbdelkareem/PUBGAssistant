package course.leedev.cn.pubgassistant.base;

import android.support.annotation.NonNull;

/**
 * Created by lt on 2017/12/26.
 *
 * fragment base view
 */

public interface IBaseView {

    /**
     * 初始化 presenter
     *
     * @return 不为空的 presenter 对象
     */
    @NonNull
    BasePresenter initPresenter();

    /**
     * TODO: 加载 Dialog 等等。。
     */

    /**
     * 隐藏键盘
     */
    void hideKeybord();

    /**
     * 回退
     */
    void back();

}
