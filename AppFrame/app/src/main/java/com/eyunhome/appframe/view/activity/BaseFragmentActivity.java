package com.eyunhome.appframe.view.activity;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;


/**
 * activity基类,所有的activity继承它
 */
public abstract class BaseFragmentActivity extends FragmentActivity{
    protected ProgressDialog loadingProgressDialog; //请求网络弹出加载框

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

}
