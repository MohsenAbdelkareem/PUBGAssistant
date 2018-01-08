package course.leedev.cn.pubgassistant.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import course.leedev.cn.pubgassistant.base.BasePresenter;
import course.leedev.cn.pubgassistant.base.IBaseModel;

/**
 * Created by lt on 2018/1/2.
 *
 * 带 RecycleView 加载状态 view 的 fragment，主要用于显示加载中、加载失败、空界面等状态界面显示
 */

public abstract class BaseRecycleFragment<P extends BasePresenter, M extends IBaseModel> extends BaseMVPCompatFragment<P, M> {

    /**
     * 网络异常 View
     */
    protected View errorView;

    /**
     * loadingView
     */
    protected View loadingView;

    /**
     * 无内容 View
     */
    protected View emptyView;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        showLoading();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO: inflate 各类 View，并添加点击事件
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 网络异常 View 被点击时触发，由子类实现
     * @param v
     */
    protected abstract void onErrorViewClick(View v);

    /**
     * 显示加载中 View，由子类实现
     */
    protected abstract void showLoading();
}
