package com.gov.mybase.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gov.mybase.R;
import com.gov.mybase.utils.LogUtils;

/**
 * @description 公用dialog
 * @date: 2019/11/25 13:54
 * @author: 冯小川
 */
public class LoadingDialog {
    Dialog mLoadingDialog;
    TextView tv_dialog_loading_data;
    ProgressBar pbar;
    LinearLayout lly_dialog;
    Context ctx;
    public LoadingDialog(Context context) {
        try {
            this.ctx = context;
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            tv_dialog_loading_data = view.findViewById(R.id.tv_dialog_loading_data);
            pbar = view.findViewById(R.id.pbar);
            lly_dialog = view.findViewById(R.id.lly_dialog);
            mLoadingDialog = new Dialog(context, R.style.TransparentDialog); //设置AlertDialog背景透明
            mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            mLoadingDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,可能出现黑屏的bug
            mLoadingDialog.setCanceledOnTouchOutside(false);
        } catch (Exception e) {
        }
    }

    public void show() {
        try {
            if (mLoadingDialog != null) {
                mLoadingDialog.show();
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    public void dismiss() {
        try {
            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
                mLoadingDialog = null;
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    public void setDialogText(String text){
        lly_dialog.setBackgroundColor(ctx.getResources().getColor(R.color.color_111111));
        tv_dialog_loading_data.setTextColor(ctx.getResources().getColor(R.color.color_FFFFFF));
        tv_dialog_loading_data.setText(text);
        pbar.setVisibility(View.GONE);
    }

    public void setDialogText2(String text){
        tv_dialog_loading_data.setText(text);
    }
}
