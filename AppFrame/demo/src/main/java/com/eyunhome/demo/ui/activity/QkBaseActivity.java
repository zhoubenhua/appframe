package com.eyunhome.demo.ui.activity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.eyunhome.appframe.common.GenericsUtils;
import com.eyunhome.appframe.model.BaseModel;
import com.eyunhome.appframe.presenter.BasePresenter;
import com.eyunhome.appframe.view.BaseView;
import com.eyunhome.appframe.view.activity.BaseActivity;

/**
 * Created by acer on 2016-7-19.
 * 青客界面基类
 */
public abstract class QkBaseActivity<P extends BasePresenter,M extends BaseModel> extends BaseActivity {
    public P mPresenter;
    public M mModel;


    public ProgressDialog showProgressDialog(String title, String message) {
        try {
            if (isFinishing() == false) {
                if (loadingProgressDialog == null) {
                    loadingProgressDialog = ProgressDialog.show(this, title,
                            message);
                    loadingProgressDialog.setCancelable(false);
//                    loadingProgressDialog.setCanceledOnTouchOutside(true);
                } else {
                    loadingProgressDialog.setTitle(title);
                    loadingProgressDialog.setMessage(message);
                    if (loadingProgressDialog.isShowing() == false) {
                        loadingProgressDialog.show();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadingProgressDialog;
    }


    public void dissmissProgressDialog() {
        try {
            if (isFinishing() == false && loadingProgressDialog != null) {
                loadingProgressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = GenericsUtils.getParameterizedType(this,0);
        mModel =  GenericsUtils.getParameterizedType(this,1);
        if(this instanceof BaseView) {
            mPresenter.attachView(this,mModel,this);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        mContext = this;
        initViews();
        initData();
        addListeners();
    }

}
