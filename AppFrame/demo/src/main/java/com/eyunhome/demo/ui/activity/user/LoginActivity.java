package com.eyunhome.demo.ui.activity.user;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.common.CommonUtil;
import com.eyunhome.appframe.listener.TopbarImplListener;
import com.eyunhome.appframe.widget.TopbarView;
import com.eyunhome.demo.R;
import com.eyunhome.demo.contract.UserContract;
import com.eyunhome.demo.model.user.LoginModel;
import com.eyunhome.demo.presenter.user.LoginPresenter;
import com.eyunhome.demo.ui.activity.base.QkBaseActivity;

/**
 * 登录界面
 */
public class LoginActivity extends QkBaseActivity<LoginPresenter,LoginModel> implements UserContract.LoginContract.LoginView{
    private TopbarView topbarView;
    private Context mContext;
    private EditText userNameEt;
    private EditText passwordEt;
    private Button loginBt;


    @Override
    public void initViews() {
        topbarView = (TopbarView)findViewById(R.id.top_bar_view);
        userNameEt = (EditText)findViewById(R.id.user_name_tv);
        passwordEt = (EditText)findViewById(R.id.password_et);
       loginBt = (Button)findViewById(R.id.login_bt);
    }

    @Override
    public void initData() {
        mContext = this;
        topbarView.setTopbarTitle("演示mvp demo");
    }

    private View.OnClickListener loginListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            String userName = userNameEt.getText().toString();
            String password = passwordEt.getText().toString();
            JSONObject bodyParams = new JSONObject();
            bodyParams.put("userName",userName);
            bodyParams.put("password",password);
            mPresenter.sendLoginRequest(mContext,bodyParams);
        }
    };


    @Override
    public void loginSucess(String json) {
        dissmissProgressDialog();
        /**
         * 登录成功
         */
        CommonUtil.sendToast(mContext,json);
    }

    @Override
    public void loginFailed(String error) {
        dissmissProgressDialog();
        /**
         * 登录失败
         */
        CommonUtil.sendToast(mContext,error);
    }

    private TopbarImplListener topbarListener = new TopbarImplListener() {

        @Override
        public void leftClick() {
            finish();
        }
    };

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topbarListener);
        loginBt.setOnClickListener(loginListener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_mvp;
    }

}
