package com.eyunhome.demo.presenter;
import android.content.Context;
import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.common.CommonUtil;
import com.eyunhome.appframe.listener.ResponseResultListener;
import com.eyunhome.demo.bean.ResponseResult;
import com.eyunhome.demo.contract.UserContract;

import java.util.Map;

/**
 * Created by acer on 2016-9-21.
 * 登录处理器
 */
public class LoginPresenter extends UserContract.LoginContract.LoginPresenter {

    /**
     * 发送登录请求
     * @param mContext 上下文
     * @param bodyParams 参数
     * @param headParams 头部参数
     */
    @Override
    public void sendLoginRequest(Context mContext, JSONObject bodyParams, Map<String, Object> headParams) {

        if(m.validateUserLogin(mContext,bodyParams)) {
            if(CommonUtil.checkNetwork(mContext)) {
                m.doLoginRequest(mContext, bodyParams,headParams, new ResponseResultListener() {
                    public void onResult(Object o) {
                        ResponseResult result = (ResponseResult)o;
                        if(result.code == ResponseResult.SUCESS_CODE) {
                            /**
                             * 登录成功。通知界面刷新ui
                             */
                            v.loginSucess(result.data);
                        } else {
                            /**
                             登陆失败,通知界面刷新ui
                             */
                            v.loginFailed(result.message);
                        }
                    }
                });
            }
        }

    }
}
