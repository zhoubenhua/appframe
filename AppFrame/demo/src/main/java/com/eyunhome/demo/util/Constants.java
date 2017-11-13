package com.eyunhome.demo.util;

import com.eyunhome.appframe.common.CommonUtil;

/**
 * Created by acer on 2016-7-19.
 */
public class Constants {
    /**
     * 第一次获取root权限后，push 到 /system/bin/ 目录下的 二进制可执行程序 的名称
     */
    public static final String ROOT_SU = "zlsu";
    /**
     * 调用 {@link #ROOT_SU}命令时传入的returnKey 返回值是通过该returnKey来获取
     */
    public static final String RETURNCODE_MOVE_PACKAGE = "returnCode_move_package:";
    public static final String RETURN_CODE_ERROR = "return_code_error";

    /**
     * 存放在/res/raw 目录下的 hw.apk 的名字，该apk是用来测试软件搬家的，请在搬家之前实现安装该apk
     */
    public static final String APK_NAME = "hw.apk";
    /**
     * 日志常量
     */
    public static final class LogDef {
        /**
         * 本地文件基目录
         */
        public static final String FILE_BASE_DIRECTORY = CommonUtil.getSDCardPath() + "/" + "project";
        /**
         * 日志目录
         */
        public static final String LOG_DIRECTORY = FILE_BASE_DIRECTORY + "/log";
        /**
         * 日志名称
         */
        public static final String LOG_FILE_NAME = "qk_api_log.txt";
    }
}
