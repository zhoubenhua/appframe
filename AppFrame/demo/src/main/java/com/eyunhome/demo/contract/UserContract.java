package com.eyunhome.demo.contract;
import android.content.Context;
import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.listener.ResponseResultListener;
import com.eyunhome.appframe.model.BaseModel;
import com.eyunhome.appframe.presenter.BasePresenter;
import com.eyunhome.appframe.view.BaseView;

import java.util.Map;

/**
 * @desc 用户模块契约类
 * 管理用户每个功能的(view,model,presenter),维护方便.
 * @auth zhoubenhua
 * @time 2017-9-19. 15:41.
 */

public interface UserContract {
    /**
     * 管理登录功能(view,model,presenter)
     */
    interface LoginContract{

        /**
         * 处理登录业务
         */
        interface LoginModel extends BaseModel {

            /**
             * 验证用户登录
             * @param mContext 上下文
             * @param bodyParams 参数
             * @return 是否验证通过
             */
            public boolean validateUserLogin(Context mContext, JSONObject bodyParams);

            /**
             * 处理登录请求
             * @param mContext 上下文
             * @param listener 回调接口
             * @param bodyParams 请求参数
             * @param headParams 头部参数
             */
            public void doLoginRequest(Context mContext, JSONObject bodyParams, Map<String, Object> headParams, ResponseResultListener listener) ;
        }

        /**
         * 登录视图
         */
        interface LoginView  extends BaseView {
            /**
             * 登录成功
             * @param json 后台返回来的json
             */
            public void loginSucess(String json);
            /**
             * 登录失败
             * @param error 返回的错误信息
             */
            public void loginFailed(String error);

        }

        /**
         * 登录处理器
         */
        abstract class LoginPresenter extends BasePresenter<LoginView,LoginModel> {

            /**
             * 发送登录请求
             * @param mContext 上下文
             * @param bodyParams 参数
             * @param headParams 头部参数
             */
            public abstract void sendLoginRequest(Context mContext, JSONObject bodyParams, Map<String,Object> headParams);
        }

    }

    /**
     * 管理注册功能(view,model,presenter)
     */
    interface RegisterContract{
        /**
         * 处理注册业务
         */
        interface RegisterModel extends BaseModel {

            /**
             * 处理注册请求
             * @param mContext 上下文
             * @param listener 回调接口
             * @param bodyParams 请求参数
             */
            public void doRegisterRequest(Context mContext, JSONObject bodyParams, ResponseResultListener listener) ;
        }

        /**
         * 注册视图
         */
        interface RegisterView  extends BaseView {
            /**
             * 注册成功
             * @param json
             */
            public void registerSucess(String json);
            /**
             * 注册失败
             * @param error
             */
            public void registerFailed(String error);

        }

        /**
         * 注册处理器
         */
        abstract class RegisterPresenter extends BasePresenter<RegisterView,RegisterModel> {
            /**
             * 发送注册请求
             * @param mContext 上下文
             * @param bodyParams 请求参数
             */
            public abstract void sendRegisterRequest(Context mContext, JSONObject bodyParams);
        }
    }

}
