package com.eyunhome.demo.ui.fragment.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eyunhome.appframe.common.GenericsUtils;
import com.eyunhome.appframe.model.BaseModel;
import com.eyunhome.appframe.presenter.BasePresenter;
import com.eyunhome.appframe.view.IBaseView;
import com.eyunhome.appframe.view.fragment.BaseFragment;

/**
 * @desc 青客碎片基类     `
 * @auth zhoubenhua
 * @time 2017-12-4. 15:55.
 */

public abstract class QkBaseFragment<P extends BasePresenter,M extends BaseModel>
        extends BaseFragment implements IBaseView{
    public Context mContext;
    public P mPresenter;
    public M mModel;

    /**
     * 打开加载框,用于调用接口弹出的对话框,并设置点击外部是否消失
     * @param title  加载框标题
     * @param message 加载框内容
     */
    public void showProgressDialog(String title, String message) {
        try {
            if (getActivity().isFinishing() == false) {
                if (loadingProgressDialog == null) {
                    loadingProgressDialog = ProgressDialog.show(getActivity(), title,
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
    }

    /**
     * 初始化mvp(model,view,presenter)
     */
    public  void  initMvp(){
        mPresenter = GenericsUtils.getParameterizedType(this,0);
        if(mPresenter != null) {
            mModel =  GenericsUtils.getParameterizedType(this,1);
            mPresenter.attactView(this,mModel,mContext);
        }
    }

    public void dissmissProgressDialog() {
        try {
            if (getActivity().isFinishing() == false && loadingProgressDialog != null) {
                loadingProgressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = this.mInflater.inflate(getLayoutId(),container,false);
        mContext = getActivity();
        initViews(view);
        initMvp();
        initData();
        addListeners();
        return view;
    }
}
