package org.now.mas.command.capture;

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
 * 实时抓图功能
 *
 * @author Administrator
 */
@Component("CaptureJpegPictureCommand")
public class CaptureJpegPictureCommand extends AbstractSdkCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureJpegPictureCommand.class);

    @Autowired
    private DeviceConfig deviceConfig;

    @Override
    protected boolean doCommand(ExecuteCommandVo executeCommandVo) {

        final String current = LocalDateTime.now().format(DateTimeFormatter.ofPattern(MasUtil.DATE_TIME_PATTERN));
        final String distPath = StringUtils.join(
                deviceConfig.getDeviceInfoMap().get(executeCommandVo.getDeviceName()).getPicturePath(),
                StringUtils.join(executeCommandVo.getDeviceName(), File.separator, current),
                MasUtil.JPEG_SUFFIX);
        final HCNetSDK.NET_DVR_JPEGPARA netDvrJpegPara = new HCNetSDK.NET_DVR_JPEGPARA();
        netDvrJpegPara.wPicSize = 0;
        netDvrJpegPara.wPicQuality = 0;

        // zoom in
        HCNetSDK.INSTANCE.NET_DVR_PTZControl_Other(
                executeCommandVo.getUserId(),
                MasUtil.DEFAULT_CHANNEL,
                HCNetSDK.ZOOM_IN,
                MasUtil.PTZ_START);
        try {
            // sleep 2s wait zoom in call
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (Exception e) {
            LOGGER.error("interrupted exception", e);
        }

        // catch picture
        boolean res = HCNetSDK.INSTANCE.NET_DVR_CaptureJPEGPicture(
                executeCommandVo.getUserId(),
                MasUtil.DEFAULT_CHANNEL,
                netDvrJpegPara,
                MasUtil.convert2ByteArray(distPath).byValue);

        //zoom out
        HCNetSDK.INSTANCE.NET_DVR_PTZControl_Other(
                executeCommandVo.getUserId(),
                MasUtil.DEFAULT_CHANNEL,
                HCNetSDK.ZOOM_OUT,
                MasUtil.PTZ_START);
        return res;
    }
}
