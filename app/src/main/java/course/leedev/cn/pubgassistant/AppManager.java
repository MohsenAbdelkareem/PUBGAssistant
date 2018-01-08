package course.leedev.cn.pubgassistant;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by lt on 2017/12/19.
 *
 * AppManager 管理Activity栈
 */

public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {

    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        activity.finish();
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
       activityStack.remove(activity);
       activity.finish();
       activity = null;
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity() {
        for (Activity activity:activityStack) {
            if (null != activity) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }
    }

    public boolean isAppExit() {
        return activityStack == null || activityStack.isEmpty();
    }

}
