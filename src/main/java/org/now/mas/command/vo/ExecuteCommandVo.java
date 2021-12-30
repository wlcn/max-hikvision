package org.now.mas.command.vo;

import org.now.mas.dto.CommandParam;
import org.now.mas.dto.CommandResult;
import org.now.mas.sdk.HCNetSDK;

import java.util.StringJoiner;

/**
 * @author Administrator
 */
public class ExecuteCommandVo {
    /**
     * login device unique name
     */
    private String deviceName;

    /**
     * login user info
     */
    private String ip;
    private short port;
    private String userName;
    private String password;

    /**
     * 执行命令的参数
     */
    private CommandParam commandParam;

    /**
     * 执行命令的参数
     */
    private CommandResult commandResult;

    /**
     * 注册后返回userId
     */
    private int userId;
    /**
     * 登录后返回设备信息
     */
    private HCNetSDK.NET_DVR_DEVICEINFO_V40 netDvrDeviceinfoV40 = new HCNetSDK.NET_DVR_DEVICEINFO_V40();

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public short getPort() {
        return port;
    }

    public void setPort(short port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CommandParam getCommandParam() {
        return commandParam;
    }

    public void setCommandParam(CommandParam commandParam) {
        this.commandParam = commandParam;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public HCNetSDK.NET_DVR_DEVICEINFO_V40 getNetDvrDeviceinfoV40() {
        return netDvrDeviceinfoV40;
    }

    public void setNetDvrDeviceinfoV40(HCNetSDK.NET_DVR_DEVICEINFO_V40 netDvrDeviceinfoV40) {
        this.netDvrDeviceinfoV40 = netDvrDeviceinfoV40;
    }

    public CommandResult getCommandResult() {
        return commandResult;
    }

    public void setCommandResult(CommandResult commandResult) {
        this.commandResult = commandResult;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ExecuteCommandVo.class.getSimpleName() + "[", "]")
                .add("deviceName='" + deviceName + "'")
                .add("ip='" + ip + "'")
                .add("port=" + port)
                .add("userName='" + userName + "'")
                .add("password='" + password + "'")
                .add("commandParam=" + commandParam)
                .add("commandResult=" + commandResult)
                .add("userId=" + userId)
                .toString();
    }
}
