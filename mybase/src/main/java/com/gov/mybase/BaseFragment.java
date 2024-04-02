package com.gov.mybase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.gov.mybase.dialog.LoadingDialog;
import com.gov.mybase.utils.LogUtils;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * @date: 2019/11/18 19:55
 * @author: 冯小川
 * @package: com.gov.govmanage.base
 * @description Fragment的根父类
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends SimpleImmersionFragment {

    public String TAG = getClass().getSimpleName() + "";

    /**
     * 用户设置的ContentView
     */
    protected View mContentView;

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected T mPresenter;

    //定义一个View用来保存Fragment创建的时候使用打气筒工具进行的布局获取对象的存储
    protected View view;

    /**
     * View有没有加载过
     */
    protected boolean isViewInitiated;
    /**
     * 页面是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 是不是加载过
     */
    protected boolean isDataInitiated;

    private Activity mActivity;
    private boolean mIsHidden = true;   // 用于记录Fragment show/hide 状态

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    /**
     * 当Fragment进行创建的时候执行的方法
     */
    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initImmersionBar();
//        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_BAR).statusBarDarkFont(true).init();
//        ImmersionBar.with(this).statusBarColor(R.color.color_004098).init();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mPresenter = createPresenter();//创建presenter
    }

    @Override
    public void initImmersionBar() {
        View view = mActivity.findViewById(R.id.state_bar);
        //设置共同沉浸式样式
        if (view == null) {
            ImmersionBar.with(this).init();
        } else {
            ImmersionBar.with(this).statusBarDarkFont(true).titleBar(view).init();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    /**
     * 这个方法是关于Fragment完成创建的过程中，进行界面填充的方法,该方法返回的是一个view对象
     * 在这个对象中封装的就是Fragment对应的布局
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = createContentView(container);
        return mContentView;
    }

    /**
     * 这个方法当onCreateView方法中的view创建完成之后，执行
     * 在inflate完成view的创建之后，可以将对应view中的各个控件进行查找findViewById
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        if (!isViewInitiated) {
            initFragmentChildView(view);
        }
    }

    /**
     * 这个方法是在Fragment完成创建操作之后，进行数据填充操作的时候执行的方法
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragmentData(savedInstanceState);
        isViewInitiated = true;
        loadData();
    }

    /**
     * 判断是否当前Fragment是否显示
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        loadData();
    }

    /**
     * 创建View
     */
    private View createContentView(ViewGroup parent) {
        Object layout = getContentLayout();
        View contentView = null;
        if (layout instanceof View) {
            contentView = (View) layout;
        } else if (layout instanceof Integer) {
            contentView = getLayoutInflater().inflate((Integer) layout, parent, false);
        }
        if (contentView == null) {
            new IllegalArgumentException("getContentLayout must View or LayoutId");
        }
        return contentView;
    }

    boolean isSupportHidden() {
        return mIsHidden;
    }

    protected abstract Object getContentLayout();

    /**
     * @return 是否注册EventBus，默认为false，不注册
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     * 进行findViewById的操作
     *
     * @param view 打气筒生成的View对象
     */
    protected abstract void initFragmentChildView(View view);

    /**
     * 网络数据填充的操作
     *
     * @param savedInstanceState
     */
    protected abstract void initFragmentData(Bundle savedInstanceState);

    /**
     * 创建Presenter对象
     */
    protected abstract T createPresenter();

    /**
     * 懒加载
     */
    private void loadData() {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated)) {
            isDataInitiated = true;
            lazyLoad();
        }
    }

    /**
     * 6. 懒加载，Fragment可见的时候调用这个方法，而且只调用一次
     */
    protected void lazyLoad() {}


    /**
     * 打开Activity
     */
    public final void startActivity(Class<?> clazz) {
        startActivity(clazz, null);
    }

    /**
     * 打开Activity
     */
    public final void startActivity(Class<?> clazz, @Nullable Bundle options) {
        Intent intent = new Intent(getAppActivity(), clazz);
        if (options != null) {
            intent.putExtras(options);
        }
        startActivity(intent);
    }

    public LoadingDialog loadingDialog;

    public LoadingDialog getDialog() {
        if (loadingDialog == null) {
            synchronized (LoadingDialog.class) {
                if (loadingDialog == null) {
                    loadingDialog = new LoadingDialog(getContext());
                }
            }
        }
        return loadingDialog;
    }

    public void startLodingDialog() {
        try {
            if (loadingDialog != null) {
                loadingDialog.show();
            } else {
                loadingDialog = getDialog();
                loadingDialog.show();
            }
        } catch (Exception e) {
            LogUtils.e("startLodingDialog error : " + e);
        }
    }

    public void setLoadingDialogText(String text){
        try {
            if (loadingDialog != null) {
                loadingDialog.setDialogText(text);
            }
        } catch (Exception e) {
            LogUtils.e("setLoadingDialogText error : " + e);
        }
    }

    public void setLoadingDialogText2(String text){
        try {
            if (loadingDialog != null) {
                loadingDialog.setDialogText2(text);
            }
        } catch (Exception e) {
            LogUtils.e("setLoadingDialogText error : " + e);
        }
    }

    public void stopLodingDialog() {
        try {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
        } catch (Exception e) {
            LogUtils.e("stopLodingDialog error : " + e);
        }
    }

    /**
     * 获取当前的Activity
     */
    public final Activity getAppActivity() {
        return mActivity;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isRegisterEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.dispose();
            mPresenter.detachView();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    protected void invisible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

}

