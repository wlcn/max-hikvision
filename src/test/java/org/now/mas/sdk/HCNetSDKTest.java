package org.now.mas.sdk;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class HCNetSDKTest {
    // 接口的实例，通过接口实例调用外部dll/so的函数
    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;

    // 用户登录返回句柄
    static int lUserID;
    int iErr = 0;

    @Test
    public void testLogin() {
        Assert.isTrue(Boolean.TRUE, "I am always true");
    }

    /**
     * @param m_sDeviceIP 设备ip地址
     * @param wPort       端口号，设备网络SDK登录默认端口8000
     * @param m_sUsername 用户名
     * @param m_sPassword 密码
     */
    public void Login_V40(String m_sDeviceIP, short wPort, String m_sUsername, String m_sPassword) {

    }

}
