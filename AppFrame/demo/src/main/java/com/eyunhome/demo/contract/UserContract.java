package com.eyunhome.demo.contract;
import android.content.Context;
import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.model.BaseModel;
import com.eyunhome.appframe.presenter.BasePresenter;
import com.eyunhome.appframe.view.IBaseView;

import java.util.Observer;

/**
 * @desc 用户模块契约类
 * 管理用户每个功能的(view,model,presenter)角色,维护方便.
 * @auth zhoubenhua
 * @time 2017-9-19. 15:41.
 */

public interface UserContract {
    /**
     * 管理登录功能(view,model,presenter)
     */
    interface LoginContract{

        /**
         * 处理登录业务抽象类
         */
        abstract class  AbstractLoginModel  extends BaseModel{
            /**
             * 处理登录请求
             * @param mContext 上下文
             * @param bodyParams 请求参数
             */
            public abstract void doLoginRequest(Context mContext, JSONObject bodyParams) ;

            /**
             * 验证登录
             * @param mContext 上下文
             * @param bodyParams 请求参数
             * @return 验证是否通过
             */
            public abstract boolean validateLogin(Context mContext,JSONObject bodyParams);
        }

        /**
         * 登录视图接口
         */
        interface LoginView  extends IBaseView {
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
        abstract class AbstractLoginPresenter extends BasePresenter<LoginView,AbstractLoginModel> implements Observer {

            /**
             * 发送登录请求
             * @param mContext 上下文
             * @param bodyParams 参数
             */
            public abstract void sendLoginRequest(Context mContext, JSONObject bodyParams);
        }

    }

    /**
     * 管理查询电话功能(view,model,presenter)
     */
    interface QueryTelContract{

        /**
         * 处理查询电话抽象类
         */
        abstract class  AbstractQueryTelModel  extends BaseModel{
            /**
             * 处理查询电话请求
             * @param mContext 上下文
             * @param bodyParams 请求参数
             */
            public abstract void doQueryTelRequest(Context mContext, JSONObject bodyParams) ;

        }

        /**
         * 查询电话视图接口
         */
        interface QueryTelView  extends IBaseView {
            /**
             * 查询电话成功
             * @param json 后台返回来的json
             */
            public void queryTelSucess(String json);
            /**
             * 查询电话失败
             * @param error 返回的错误信息
             */
            public void queryTelFailed(String error);

        }

        /**
         * 查询电话处理器抽象类
         */
        abstract class AbstractQueryTelPresenter extends BasePresenter<QueryTelView,AbstractQueryTelModel> implements Observer{

            /**
             * 发送查询电话请求
             * @param mContext 上下文
             * @param bodyParams 参数
             */
            public abstract void sendQueryTelRequest(Context mContext, JSONObject bodyParams);
        }

    }

    /**
     * 管理用户信息功能(view,model,presenter)
     */
    interface QueryUserInfoContract{

        /**
         * 处理查询用户信息抽象类
         */
        abstract class  AbstractQueryUserInfoModel  extends BaseModel{
            /**
             * 处理查询电话请求
             * @param mContext 上下文
             * @param bodyParams 请求参数
             */
            public abstract void doQueryUserInfoRequest(Context mContext, JSONObject bodyParams) ;

        }

        /**
         * 查询用户信息视图接口
         */
        interface QueryUserInfoView  extends IBaseView {
            /**
             * 查询用户信息成功
             * @param json 后台返回来的json
             */
            public void queryUserInfoSucess(String json);
            /**
             * 查询用户信息失败
             * @param error 返回的错误信息
             */
            public void queryUserInfoFailed(String error);

        }

        /**
         * 查询用户信息抽象类
         */
        abstract class AbstractQueryUserInfoPresenter extends BasePresenter<QueryUserInfoView,AbstractQueryUserInfoModel> {

            /**
             * 发送查询用户信息请求
             * @param mContext 上下文
             * @param bodyParams 参数
             */
            public abstract void sendQueryUserInfoRequest(Context mContext, JSONObject bodyParams);
        }
    }


}
