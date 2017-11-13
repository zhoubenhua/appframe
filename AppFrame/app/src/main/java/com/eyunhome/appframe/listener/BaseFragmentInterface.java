package com.eyunhome.appframe.listener;

import android.view.View;

/**
 * 基类fragment实现接口
 * Created by acer on 2015-10-20.
 */
public interface BaseFragmentInterface {
    public void initViews(View view);

    public void initData();

    public void addListeners();
}
