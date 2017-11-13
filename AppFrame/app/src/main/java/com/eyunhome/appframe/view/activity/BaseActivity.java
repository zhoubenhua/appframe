package com.eyunhome.appframe.view.activity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;


/**
 * activity基类,所有的activity继承它
 */
public abstract class BaseActivity extends Activity{
    public Context mContext;
    protected ProgressDialog loadingProgressDialog; //请求网络弹出加载框

      /**
     * 初始化控件
     */
    public abstract void initViews();

    /**
     * 初始化数据
     */
    public abstract void initData() ;

    /**
     * 添加事件
     */
    public abstract void addListeners();

    /**
     * 获取布局文件ID
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 打开加载框,用于调用接口弹出的对话框
     * @param title
     * @param message
     * @return
     */
    public abstract ProgressDialog showProgressDialog(String title, String message);

    /**
     * 关闭对话框
     */
    public abstract void dissmissProgressDialog();

 }
