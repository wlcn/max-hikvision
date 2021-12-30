package org.now.mas.command.ptz;

import org.now.mas.command.AbstractSdkCommand;
import org.now.mas.command.vo.ExecuteCommandVo;
import org.now.mas.sdk.HCNetSDK;
import org.now.mas.util.MasUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 云台控制
 *
 * @author Administrator
 */
@Component("PtzControlCommand")
public class PtzControlCommand extends AbstractSdkCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(PtzControlCommand.class);

    @Override
    protected boolean doCommand(ExecuteCommandVo executeCommandVo) {
        final int dwPtzCommand = executeCommandVo.getCommandParam().getPtzCommand();
        final int dwStop = executeCommandVo.getCommandParam().getDwStop();
        final int dwSpeed = executeCommandVo.getCommandParam().getDwSpeed();
        LOGGER.info("NET_DVR_PTZControl_Other start, dwPtzCommand is {}, dwSpeed is {}, dwStop is {}",
                dwPtzCommand, dwSpeed, dwStop);
        return HCNetSDK.INSTANCE.NET_DVR_PTZControlWithSpeed_Other(
                executeCommandVo.getUserId(),
                MasUtil.DEFAULT_CHANNEL,
                dwPtzCommand,
                dwStop,
                dwSpeed);
    }
}
