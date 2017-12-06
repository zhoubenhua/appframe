package com.eyunhome.demo.presenter.user;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.common.CommonUtil;
import com.eyunhome.demo.bean.ResponseResult;
import com.eyunhome.demo.contract.UserContract;

import java.util.Observable;

/**
 * @desc 查询电话处理器
 * @auth zhoubenhua
 * @time 2017-12-4. 15:40.
 */

public class QueryTelPresenter extends UserContract.QueryTelContract.AbstractQueryTelPresenter {
    @Override
    public void sendQueryTelRequest(Context mContext, JSONObject bodyParams) {
        if(CommonUtil.checkNetwork(mContext)) {
            /**
             * 注册观察者
             */
            mModel.addObserver(this);
            mView.showProgressDialog("加载中...",null);
            mModel.doQueryTelRequest(mContext, bodyParams);
        }
    }


    /**
     * 被观察者数据发生变化  服务器返回查询电话响应
     * @param o
     * @param data
     */
    @Override
    public void update(Observable o, Object data) {
        /**
         * 删除观察者
         */
        mModel.deleteObserver(this);
        mView.dissmissProgressDialog();
        if(data != null) {
            ResponseResult result = (ResponseResult)data;
            if(result.code == ResponseResult.SUCESS_CODE) {
                mView.queryTelSucess(result.data);
            } else if(!CommonUtil.isEmpty(result.message)){
                mView.queryTelFailed(result.message);
            }
        }
    }
}
