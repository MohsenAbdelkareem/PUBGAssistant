package course.leedev.cn.pubgassistant.utils;

import android.content.Context;
import android.os.Handler;

import course.leedev.cn.pubgassistant.global.GlobalApplication;

/**
 * Created by lt on 2017/12/19.
 *
 * App工具类
 */

public class AppUtils {

    /**
     * 获取上下文对象
     *
     * @return 上下文对象
     */
    public static Context getContext() {
        return GlobalApplication.getContext();
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return GlobalApplication.getHandler();
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return GlobalApplication.getMainThreadId();
    }

    /**
     * 使运行在主线程
     */
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            r.run();
        } else {
            // 借助 handler 让其运行在主线程
            getHandler().post(r);
        }
    }

    /**
     * 判断是否在主线程
     *
     * @return true:当前线程运行在主线程
     * false:当前线程运行在主线程
     */
    private static boolean isRunOnUIThread() {
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        }
        return false;
    }

    /**
     * TODO:版本代码
     */

}
