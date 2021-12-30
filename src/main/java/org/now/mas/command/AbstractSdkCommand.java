package org.now.mas.command;

import org.now.mas.command.vo.ExecuteCommandVo;
import org.now.mas.sdk.HCNetSDK;
import org.now.mas.util.ConfigUtil;
import org.now.mas.util.MasUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * call hikvision step by step
 *
 * @author Administrator
 */
public abstract class AbstractSdkCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSdkCommand.class);

    /**
     * 初始化SDK
     *
     * @return true or false
     */
    protected boolean init() {
        LOGGER.info("NET_DVR_init");
        return HCNetSDK.INSTANCE.NET_DVR_Init();
    }

    protected boolean setLog() {
        LOGGER.info("NET_DVR_SetLogToFile");
        return HCNetSDK.INSTANCE.NET_DVR_SetLogToFile(ConfigUtil.getSdkConfig().getLogModel(), ConfigUtil.getSdkConfig().getLogPath(), false);
    }

    /**
     * login
     *
     * @param executeCommandVo loginUserVo
     * @return true or false
     */
    protected boolean login(ExecuteCommandVo executeCommandVo) {
        LOGGER.info("NET_DVR_Login_V40, loginUser is {}, device ip is {}", executeCommandVo.getUserName(), executeCommandVo.getIp());
        final HCNetSDK.NET_DVR_USER_LOGIN_INFO loginInfo = convertLoginInfo(executeCommandVo);
        final int res = HCNetSDK.INSTANCE.NET_DVR_Login_V40(loginInfo, executeCommandVo.getNetDvrDeviceinfoV40());
        executeCommandVo.setUserId(res);
        return res != MasUtil.LOGIN_FAIL;
    }

    /**
     * 登录信息VO转换成HikVision对象
     *
     * @param executeCommandVo loginUser
     * @return net dvr user login info
     */
    private HCNetSDK.NET_DVR_USER_LOGIN_INFO convertLoginInfo(ExecuteCommandVo executeCommandVo) {
        //注册
        HCNetSDK.NET_DVR_USER_LOGIN_INFO loginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();

        //设备ip地址
        final String mSDeviceIP = executeCommandVo.getIp();
        loginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(mSDeviceIP.getBytes(), 0, loginInfo.sDeviceAddress, 0, mSDeviceIP.length());

        //设备用户名
        final String mSUsername = executeCommandVo.getUserName();
        loginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(mSUsername.getBytes(), 0, loginInfo.sUserName, 0, mSUsername.length());

        //设备密码
        final String mSPassword = executeCommandVo.getPassword();
        loginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(mSPassword.getBytes(), 0, loginInfo.sPassword, 0, mSPassword.length());

        loginInfo.wPort = executeCommandVo.getPort();
        //是否异步登录：0- 否，1- 是
        loginInfo.bUseAsynLogin = false;
        loginInfo.write();
        return loginInfo;
    }

    /**
     * before command
     *
     * @param executeCommandVo loginUser
     * @return true or false
     */
    protected boolean beforeCommand(ExecuteCommandVo executeCommandVo) {
        LOGGER.info("beforeCommand device ip is {}", executeCommandVo.getIp());
        return Boolean.TRUE;
    }

    /**
     * do command
     *
     * @param executeCommandVo loginUser
     * @return true or false
     */
    protected abstract boolean doCommand(ExecuteCommandVo executeCommandVo);

    /**
     * after command
     *
     * @param executeCommandVo loginUser
     * @return true or false
     */
    protected boolean afterCommand(ExecuteCommandVo executeCommandVo) {
        LOGGER.info("afterCommand device ip is {}", executeCommandVo.getIp());
        return Boolean.TRUE;
    }

    /**
     * logout
     *
     * @return true or false
     */
    protected boolean logout(int userId) {
        LOGGER.info("NET_DVR_Logout");
        return HCNetSDK.INSTANCE.NET_DVR_Logout(userId);
    }

    /**
     * 释放SDK
     *
     * @return true or false
     */
    protected boolean cleanup() {
        LOGGER.info("NET_DVR_Cleanup");
        return HCNetSDK.INSTANCE.NET_DVR_Cleanup();
    }
}
