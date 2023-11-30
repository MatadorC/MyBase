package com.gov.mybase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gov.mybase.dialog.LoadingDialog;
import com.gov.mybase.utils.LogUtils;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

/**
 * @date: 2019/11/18 19:53
 * @author: 冯小川
 * @package: com.gov.govmanage.base
 * @description Activity的根父类
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    public String TAG = getClass().getSimpleName() + "";

    protected T mPresenter;

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
        if (isRequestWindowFeature()) {
            requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        }
        initActivityView(savedInstanceState);
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        //创建presenter
        mPresenter = createPresenter();
        // presenter与view绑定
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
        initView();
        initClick();
        getData();
        //初始化沉浸式
        initImmersionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        View view = findViewById(R.id.state_bar);
        //设置共同沉浸式样式
        if (view == null) {
            ImmersionBar.with(this).init();
        } else {
            ImmersionBar.with(this).titleBar(view).init();
        }
    }


    /**
     * 关于Activity的界面填充的抽象方法，需要子类必须实现
     */
    protected abstract void initActivityView(Bundle savedInstanceState);

    /**
     * @return 是否注册EventBus，默认为false，不注册
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     * @return 是否支持侧滑退出界面
     */
    protected boolean isRequestWindowFeature() {
        return false;
    }


    /**
     * 创建Presenter 对象
     *
     * @return
     */
    protected abstract T createPresenter();

    protected abstract void getData();

    protected abstract void initView();

    protected abstract void initClick();

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
        Intent intent = new Intent(this, clazz);
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
                    loadingDialog = new LoadingDialog(mContext);
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

    public void setLoadingDialogText(String text){
        try {
            if (loadingDialog != null) {
                loadingDialog.setDialogText(text);
            }
        } catch (Exception e) {
            LogUtils.e("setLoadingDialogText error : " + e);
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

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isRegisterEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (null != mPresenter) {
            mPresenter.dispose();
            mPresenter.detachView();
        }
    }
}

