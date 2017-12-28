package com.eyunhome.demo.ui.activity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eyunhome.appframe.common.CommonUtil;
import com.eyunhome.appframe.db.QkDb;
import com.eyunhome.appframe.listener.TopbarImplListener;
import com.eyunhome.appframe.widget.TopbarView;
import com.eyunhome.demo.R;
import com.eyunhome.demo.bean.UserBean;
import com.eyunhome.demo.ui.activity.base.QkBaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 作者：zhoubenhua
 * 时间：2017-4-21 12:26
 * 功能:测试数据库增删改
 */

public class TestDbActivity extends QkBaseActivity {
    private Context mContext;
    private TopbarView topbarView;
    private EditText addUserNameEt,deleteUseNameEt;
    private TextView queryResult;
    private Button saveUserBt,deleteUserBt;
    private QkDb qkDb;

    @Override
    public void initViews() {
        topbarView = (TopbarView)findViewById(R.id.top_bar_view);
        addUserNameEt = (EditText)findViewById(R.id.add_user_name_et);
        deleteUseNameEt = (EditText)findViewById(R.id.delete_user_name_et);
        queryResult = (TextView)findViewById(R.id.query_result_tv);
        saveUserBt = (Button)findViewById(R.id.save_user_bt);
        deleteUserBt = (Button)findViewById(R.id.delete_user_bt);
    }

    @Override
    public void initData() {
        mContext = this;
        topbarView.setTopbarTitle("测试数据库demo");
        qkDb = QkDb.create(mContext,"qk_db",true);
        topbarView.setBackgroundResource(R.color.colorAccent);
        queryUserInfo();
    }

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topbarListener);
        saveUserBt.setOnClickListener(saveUserListener);
        deleteUserBt.setOnClickListener(deleteUserListener);
    }

    /**
     * 保存用户事件
     */
    private View.OnClickListener saveUserListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String userName = addUserNameEt.getText().toString();
            if(CommonUtil.isEmpty(userName)) {
                CommonUtil.sendToast(mContext,"请输入用户名");
                return;
            }
            UserBean user = new UserBean();
            user.setName(userName);
            /**
             * 保存数据到表中
             */
            qkDb.save(user);
            queryUserInfo();
        }
    };

    /**
     * 删除用户事件
     */
    private View.OnClickListener deleteUserListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String userName = deleteUseNameEt.getText().toString();
            if(CommonUtil.isEmpty(userName)) {
                CommonUtil.sendToast(mContext,"请输入用户名");
                return;
            }
            Map whereMap = new HashMap<String,String>();
            whereMap.put("name","zhou");
            /**
             * 删除用户名为zhou的数据
             */
            qkDb.deletByWhere(whereMap,UserBean.class);
            queryUserInfo();
        }
    };

    /**
     * 查询用户信息
     */
    private void queryUserInfo() {
        List<UserBean> userList = qkDb.findAll(UserBean.class);
        if(userList != null && userList.size()>0) {
            String result = "";
            for(UserBean bean:userList) {
                result += bean.getName()+"--"+bean.getEmail()+"\n";
            }
            queryResult.setText(result);
        }
    }

    private TopbarImplListener topbarListener = new TopbarImplListener() {

        @Override
        public void leftClick() {
            finish();
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.test_db;
    }
}
