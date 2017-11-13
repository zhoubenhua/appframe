package com.eyunhome.demo.api;


import com.eyunhome.appframe.bean.BaseBuildConfig;
import com.eyunhome.appframe.bean.BaseConnect;

/**
 * Created by acer on 2016-6-24.
 * 配置各个环境服务器地址
 */
public class QkBuildConfig extends BaseBuildConfig {
    private BaseConnect connect;
    private static QkBuildConfig instance;
    private int environmentType;//环境类型 1是正式 2是仿真 3是测试
    public static QkBuildConfig getInstance() {
       if(instance == null) {
           instance = new QkBuildConfig();
       }
       return instance;
    }

    private QkBuildConfig() {
        /**
         * 默认切换到测试环境
         */
        environmentType = 3;
        connect = new BaseConnect();
        changeEnvironment();
    }

    public BaseConnect getConnect() {
        return connect;
    }

    /**
     * 切换环境
     */
    public void changeEnvironment(){
        switch(environmentType) {
            case 1:
                /**
                 * 切换正式环境
                 */
                switchOfficial();
                break;
            case 2:
                /**
                 * 切换仿真环境
                 */
                switchSimulation();
                break;
            case 3:
                /**
                 * 切换测试环境
                 */
                switchTest();
                break;
        }
    }
    /**
     * 切换正式环境
     */
    @Override
    public void switchOfficial() {
        connect.setApiUrl("https://api-dev.qingkepay.com:10080"); //开发环境 https
        connect.setH5Url("https://dev.qingkepay.com:10080");
    }

    /**
     * 切换测试环境
     */
    @Override
    public void switchTest() {
        connect.setApiUrl("http://192.168.1.75:100");//测试
    }

    /**
     * 切换仿真环境
     */
    @Override
    public void switchSimulation() {
        connect.setApiUrl("https://api-dev.qingkepay.com:10080"); //开发环境 https
        connect.setH5Url("https://dev.qingkepay.com:10080");
    }

}
