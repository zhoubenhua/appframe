package com.eyunhome.demo.model;
import android.content.Context;
import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.common.CommonUtil;
import com.eyunhome.appframe.listener.ResponseResultListener;
import com.eyunhome.demo.api.ApiAsyncTask;
import com.eyunhome.demo.api.Protocol;
import com.eyunhome.demo.api.QkBuildConfig;
import com.eyunhome.demo.contract.UserContract;
import com.eyunhome.demo.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acer on 2016-9-21.
 */
public class LoginModel implements UserContract.LoginContract.LoginModel{


    @Override
    public boolean validateUserLogin(Context mContext, JSONObject bodyParams) {
        boolean flag = true;
        String userName = bodyParams.getString("userName");
        String userPassword = bodyParams.getString("password");
        if(CommonUtil.isEmpty(userName)) {
            flag = false;
            CommonUtil.sendToast(mContext,"用户名不能为空");
        } else if(CommonUtil.isEmpty(userPassword)) {
            flag = false;
            CommonUtil.sendToast(mContext,"密码不能为空");
        }
        return flag;
    }

    @Override
    public void doLoginRequest(Context mContext, JSONObject bodyParams, Map<String, Object> headParams, ResponseResultListener listener) {
        String loginUrl = QkBuildConfig.getInstance().getConnect().getApiUrl() + Protocol.LOGIN_URl;
        ApiAsyncTask apiAsyncTask = new ApiAsyncTask(mContext);
        String apiLogFileDirectory = Constants.LogDef.LOG_DIRECTORY;
        String apiLogFileName = Constants.LogDef.LOG_FILE_NAME;
        apiAsyncTask.post(apiLogFileDirectory, apiLogFileName, loginUrl, bodyParams, (HashMap<String, Object>) headParams,listener);
    }

}
