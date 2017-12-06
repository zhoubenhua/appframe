package com.eyunhome.demo.model.user;
import android.content.Context;
import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.common.CommonUtil;
import com.eyunhome.appframe.listener.ResponseResultListener;
import com.eyunhome.demo.api.ApiAsyncTask;
import com.eyunhome.demo.api.Protocol;
import com.eyunhome.demo.api.QkBuildConfig;
import com.eyunhome.demo.bean.ResponseResult;
import com.eyunhome.demo.contract.UserContract;
import com.eyunhome.demo.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 处理登录业务(数据校验,获取数据)
 * @auth zhoubenhua
 * @time 2017-11-21. 14:10.
 */
public class LoginModel extends UserContract.LoginContract.AbstractLoginModel{

    /**
     * 处理登录请求
     * @param mContext 上下文
     * @param bodyParams 请求参数
     */
    @Override
    public void doLoginRequest(Context mContext, JSONObject bodyParams) {
        String loginUrl = QkBuildConfig.getInstance().getConnect().getApiUrl() + Protocol.LOGIN_URL;
        ApiAsyncTask apiAsyncTask = new ApiAsyncTask(mContext);
        String apiLogFileDirectory = Constants.LogDef.LOG_DIRECTORY;
        String apiLogFileName = Constants.LogDef.LOG_FILE_NAME;
        apiAsyncTask.post(apiLogFileDirectory, apiLogFileName, loginUrl, bodyParams, null, new ResponseResultListener() {
            @Override
            public void doResponse(Object data) {
                ResponseResult result = (ResponseResult) data;
                setChanged();
                notifyObservers(result);
            }
        });

    }

    /**
     * 验证登录
     * @param mContext 上下文
     * @param bodyParams 请求参数
     * @return 是否通过
     */
    @Override
    public boolean validateLogin(Context mContext, JSONObject bodyParams) {
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

}
