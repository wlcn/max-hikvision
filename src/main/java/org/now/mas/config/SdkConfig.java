package org.now.mas.config;

/**
 * @author Administrator
 */
public class SdkConfig {

    private String sdkPath;
    private String sdkFile;
    private String logPath;
    private int logModel;

    public String getSdkPath() {
        return sdkPath;
    }

    public void setSdkPath(String sdkPath) {
        this.sdkPath = sdkPath;
    }

    public String getSdkFile() {
        return sdkFile;
    }

    public void setSdkFile(String sdkFile) {
        this.sdkFile = sdkFile;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public int getLogModel() {
        return logModel;
    }

    public void setLogModel(int logModel) {
        this.logModel = logModel;
    }
}
