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
 * @desc 处理查询电话业务
 * @auth zhoubenhua
 * @time 2017-12-4. 15:32.
 */

public class QueryTelModel extends UserContract.QueryTelContract.AbstractQueryTelModel {
    @Override
    public void doQueryTelRequest(Context mContext, JSONObject bodyParams) {
        String url = QkBuildConfig.getInstance().getConnect().getApiUrl() + Protocol.QUERY_TEL_URl;
        ApiAsyncTask apiAsyncTask = new ApiAsyncTask(mContext);
        String apiLogFileDirectory = Constants.LogDef.LOG_DIRECTORY;
        String apiLogFileName = Constants.LogDef.LOG_FILE_NAME;
        apiAsyncTask.post(apiLogFileDirectory, apiLogFileName, url, bodyParams, null, new ResponseResultListener() {
            @Override
            public void doResponse(Object data) {
                ResponseResult result = (ResponseResult) data;
                setChanged();
                notifyObservers(result);
            }
        });
    }
}
