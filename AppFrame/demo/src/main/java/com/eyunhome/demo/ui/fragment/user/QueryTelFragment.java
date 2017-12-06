package com.eyunhome.demo.ui.fragment.user;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.eyunhome.appframe.common.CommonUtil;
import com.eyunhome.demo.R;
import com.eyunhome.demo.contract.UserContract;
import com.eyunhome.demo.model.user.QueryTelModel;
import com.eyunhome.demo.ui.fragment.base.QkBaseFragment;
import com.eyunhome.demo.presenter.user.QueryTelPresenter;

/**
 * @desc
 * @auth zhoubenhua
 * @time 2017-12-4. 16:11.
 */

public class QueryTelFragment extends QkBaseFragment<QueryTelPresenter,QueryTelModel> implements
        UserContract.QueryTelContract.QueryTelView{
    private TextView queryResultTv;
    @Override
    public void initViews(View view) {
        queryResultTv = (TextView)view.findViewById(R.id.query_result_tv);
    }

    @Override
    public void initData() {
        JSONObject paramsJson = new JSONObject();
        mPresenter.sendQueryTelRequest(mContext,paramsJson);
    }

    @Override
    public void addListeners() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.query_tel_model;
    }

    /**
     * 查询电话成功
     * @param json 后台返回来的json
     */
    @Override
    public void queryTelSucess(String json) {
        CommonUtil.sendToast(mContext,json);
    }

    /**
     * 查询电话失败
     * @param error 返回的错误信息
     */
    @Override
    public void queryTelFailed(String error) {
        CommonUtil.sendToast(mContext,error);
    }
}
