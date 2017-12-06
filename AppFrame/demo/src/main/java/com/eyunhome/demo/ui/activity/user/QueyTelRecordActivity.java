package com.eyunhome.demo.ui.activity.user;
import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.common.CommonUtil;
import com.eyunhome.appframe.listener.TopbarImplListener;
import com.eyunhome.appframe.widget.TopbarView;
import com.eyunhome.demo.R;
import com.eyunhome.demo.bean.ResponseResult;
import com.eyunhome.demo.model.user.QueryUserInfoModel;
import com.eyunhome.demo.ui.activity.base.QkBaseActivity;
import com.eyunhome.demo.ui.fragment.user.QueryTelFragment;

import java.util.Observable;
import java.util.Observer;

/**
 * @desc
 * @auth zhoubenhua
 * @time 2017-12-4. 14:52.
 */

public class QueyTelRecordActivity extends QkBaseActivity  implements Observer{
    private TopbarView topbarView;
    private QueryTelFragment queryTelFragment;//查询电话模块碎片
    private QueryUserInfoModel queryUserInfoModel;//查询用户信息业务

    @Override
    public void initViews() {
        topbarView = (TopbarView)findViewById(R.id.top_bar_view);
    }

    @Override
    public void initData() {
        queryTelFragment = new QueryTelFragment();
        sendQueryUserInfoRequest();
    }

    /**
     * 发送加载用户信息
     */
    private void sendQueryUserInfoRequest() {
        queryUserInfoModel = new QueryUserInfoModel();
        if(CommonUtil.checkNetwork(mContext)) {
            showProgressDialog("加载中","");
            JSONObject paramsJson = new JSONObject();
            queryUserInfoModel.addObserver(this);
            queryUserInfoModel.doQueryUserInfoRequest(mContext,paramsJson);
        }
    }

    /**
     * 顶部事件
     */
    private TopbarImplListener topbarImplListener = new TopbarImplListener() {
        @Override
        public void leftClick() {
            finish();
        }
    };

    @Override
    public void addListeners() {
        topbarView.setTopBarClickListener(topbarImplListener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.query_tel;
    }


    @Override
    public void update(Observable o, Object data) {
        /**
         * 删除观察者
         */
        mModel.deleteObserver(this);
        if(data != null) {
            ResponseResult result = (ResponseResult)data;
            CommonUtil.sendToast(mContext,result.message);
        }
    }
}
