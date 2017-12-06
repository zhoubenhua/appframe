package com.eyunhome.demo.model.user;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.listener.ResponseResultListener;
import com.eyunhome.demo.api.ApiAsyncTask;
import com.eyunhome.demo.api.Protocol;
import com.eyunhome.demo.api.QkBuildConfig;
import com.eyunhome.demo.bean.ResponseResult;
import com.eyunhome.demo.contract.UserContract;
import com.eyunhome.demo.util.Constants;

/**
 * @desc 查询用户信息业务
 * @auth zhoubenhua
 * @time 2017-12-4. 16:28.
 */

public class QueryUserInfoModel extends UserContract.QueryUserInfoContract.AbstractQueryUserInfoModel {
    @Override
    public void doQueryUserInfoRequest(Context mContext, JSONObject bodyParams) {
        String user = QkBuildConfig.getInstance().getConnect().getApiUrl() + Protocol.QUERY_USER_INFO_URl;
        ApiAsyncTask apiAsyncTask = new ApiAsyncTask(mContext);
        String apiLogFileDirectory = Constants.LogDef.LOG_DIRECTORY;
        String apiLogFileName = Constants.LogDef.LOG_FILE_NAME;
        apiAsyncTask.post(apiLogFileDirectory, apiLogFileName, user, bodyParams, null, new ResponseResultListener() {
            @Override
            public void doResponse(Object data) {
                ResponseResult result = (ResponseResult) data;
                setChanged();
                notifyObservers(result);
            }
        });
    }
}
