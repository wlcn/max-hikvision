package org.now.mas.command.realdata;

import org.apache.commons.lang3.StringUtils;
import org.now.mas.command.AbstractSdkCommand;
import org.now.mas.command.vo.ExecuteCommandVo;
import org.now.mas.config.DeviceConfig;
import org.now.mas.sdk.HCNetSDK;
import org.now.mas.util.MasUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 实时数据保存
 * 这里只能在一次登录状态下调用
 *
 * @author Administrator
 */
@Component("RealDataSaveCommand")
public class RealDataSaveCommand extends AbstractSdkCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealDataSaveCommand.class);
    @Autowired
    private DeviceConfig deviceConfig;

    @Override
    protected boolean doCommand(ExecuteCommandVo executeCommandVo) {
        final HCNetSDK.NET_DVR_PREVIEWINFO netDvrPreviewinfo = new HCNetSDK.NET_DVR_PREVIEWINFO();
        netDvrPreviewinfo.lChannel = MasUtil.DEFAULT_CHANNEL;
        final int realHandle = HCNetSDK.INSTANCE.NET_DVR_RealPlay_V40(
                executeCommandVo.getUserId(),
                netDvrPreviewinfo,
                null,
                null);
        boolean success = MasUtil.checkCallResult(realHandle);
        if (success) {
            final String current = LocalDateTime.now().format(DateTimeFormatter.ofPattern(MasUtil.DATE_TIME_PATTERN));
            final DeviceConfig.DeviceInfo deviceInfo = deviceConfig.getDeviceInfoMap().get(executeCommandVo.getDeviceName());
            final String distPath = StringUtils.join(
                    deviceInfo.getRealDataPath(),
                    StringUtils.join(executeCommandVo.getDeviceName(), File.separator, current),
                    MasUtil.MP4_SUFFIX);
            LOGGER.info("NET_DVR_SaveRealData distPath is {}, realHandle is {}", distPath, realHandle);
            // start save
            success = HCNetSDK.INSTANCE.NET_DVR_SaveRealData(
                    realHandle,
                    distPath);

            // stop save
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(deviceInfo.getRealDataSaveTime()));
            } catch (Exception e) {
                LOGGER.error("sleep error", e);
            } finally {
                HCNetSDK.INSTANCE.NET_DVR_StopSaveRealData(realHandle);
                HCNetSDK.INSTANCE.NET_DVR_StopRealPlay(realHandle);
            }
        }
        return success;
    }
}
