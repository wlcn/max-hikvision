package org.now.mas.command.ability;

import com.sun.jna.Pointer;
import org.apache.commons.lang3.StringUtils;
import org.now.mas.command.AbstractSdkCommand;
import org.now.mas.command.vo.ExecuteCommandVo;
import org.now.mas.dto.CommandResult;
import org.now.mas.sdk.HCNetSDK;
import org.now.mas.util.MasUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * 设备能力集
 *
 * @author Administrator
 */
@Component("GetDeviceAbilityCommand")
public class GetDeviceAbilityCommand extends AbstractSdkCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetDeviceAbilityCommand.class);


    @Override
    protected boolean doCommand(ExecuteCommandVo executeCommandVo) {
        final String inputXml = executeCommandVo.getCommandParam().getAbilityInputXml();
        Pointer pInBuf = null;
        final MasUtil.NET_DVR_STRING_POINTER inputPointer = new MasUtil.NET_DVR_STRING_POINTER();
        if (StringUtils.isNotBlank(inputXml)) {
            inputPointer.byString = inputXml.getBytes();
            inputPointer.write();
            pInBuf = inputPointer.getPointer();
        }

        final MasUtil.NET_DVR_STRING_POINTER outputPointer = new MasUtil.NET_DVR_STRING_POINTER();
        outputPointer.byString = new byte[MasUtil.XML_ABILITY_OUT_LENGTH];
        outputPointer.write();
        final Pointer pOutBuf = outputPointer.getPointer();

        boolean success = HCNetSDK.INSTANCE.NET_DVR_GetDeviceAbility(
                executeCommandVo.getUserId(),
                executeCommandVo.getCommandParam().getAbilityType(),
                pInBuf,
                inputXml.length(),
                pOutBuf,
                MasUtil.XML_ABILITY_OUT_LENGTH
        );
        outputPointer.read();
        final String result = new String(outputPointer.byString).trim();
        LOGGER.info("get device ability result is {}", result);
        // clear
        if (Objects.nonNull(inputPointer.byString)) {
            inputPointer.clear();
        }
        outputPointer.clear();
        // set result
        final CommandResult commandResult = new CommandResult();
        commandResult.setAbilityResultXml(result);
        executeCommandVo.setCommandResult(commandResult);
        return success;
    }


}
