package org.now.mas.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
@ConfigurationProperties(prefix = "hikvision")
public class DeviceConfig {
    private Map<String, DeviceInfo> deviceInfoMap;

    public Map<String, DeviceInfo> getDeviceInfoMap() {
        return deviceInfoMap;
    }

    public void setDeviceInfoMap(Map<String, DeviceInfo> deviceInfoMap) {
        this.deviceInfoMap = deviceInfoMap;
    }

    public static class DeviceInfo {
        private String ip;
        private short port;
        private String userName;
        private String password;
        private String rtmpUrl;
        private String picturePath;
        private String realDataPath;
        private int realDataSaveTime;

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

        public String getRtmpUrl() {
            return rtmpUrl;
        }

        public void setRtmpUrl(String rtmpUrl) {
            this.rtmpUrl = rtmpUrl;
        }

        public String getPicturePath() {
            return picturePath;
        }

        public void setPicturePath(String picturePath) {
            this.picturePath = picturePath;
        }

        public String getRealDataPath() {
            return realDataPath;
        }

        public void setRealDataPath(String realDataPath) {
            this.realDataPath = realDataPath;
        }

        public int getRealDataSaveTime() {
            return realDataSaveTime;
        }

        public void setRealDataSaveTime(int realDataSaveTime) {
            this.realDataSaveTime = realDataSaveTime;
        }
    }
}
