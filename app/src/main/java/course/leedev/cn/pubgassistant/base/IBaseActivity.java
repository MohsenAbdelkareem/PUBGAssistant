package course.leedev.cn.pubgassistant.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by lt on 2017/12/26.
 *
 * BaseActivity 接口
 */

public interface IBaseActivity extends IBaseView {

    /**
     * 跳往新的 Activity
     *
     * @param clz 要跳往的 Activity
     */
    void startNewActivity(@NonNull Class<?> clz);

    /**
     * 跳往新的 Activity
     *
     * @param clz 要跳往的 Activity
     * @param bundle 携带的 Bundle 数据
     */
    void startNewActivity(@NonNull Class<?> clz, Bundle bundle);

    /**
     * 跳往新的 Activity
     *
     * @param clz 要跳往的 Activity
     * @param bundle 携带的 Bundle 数据
     * @param requestCode 请求码
     */
    void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode);

}
