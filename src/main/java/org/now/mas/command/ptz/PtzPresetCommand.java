package org.now.mas.command.ptz;

import org.now.mas.command.AbstractSdkCommand;
import org.now.mas.command.vo.ExecuteCommandVo;
import org.now.mas.sdk.HCNetSDK;
import org.now.mas.util.MasUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 云台预置点控制
 *
 * @author Administrator
 */
@Component("PtzPresetCommand")
public class PtzPresetCommand extends AbstractSdkCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(PtzPresetCommand.class);

    @Override
    protected boolean doCommand(ExecuteCommandVo executeCommandVo) {
        final int dwPTZPresetCmd = executeCommandVo.getCommandParam().getPtzPresetCmd();
        final int dwPresetIndex = executeCommandVo.getCommandParam().getPresetIndex();
        LOGGER.info("NET_DVR_PTZPreset_Other start, dwPTZPresetCmd is {}, dwPresetIndex is {}", dwPTZPresetCmd, dwPresetIndex);
        return HCNetSDK.INSTANCE.NET_DVR_PTZPreset_Other(
                executeCommandVo.getUserId(),
                MasUtil.DEFAULT_CHANNEL,
                dwPTZPresetCmd,
                dwPresetIndex);
    }
}
