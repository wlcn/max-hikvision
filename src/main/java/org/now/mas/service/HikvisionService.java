package org.now.mas.service;

import org.now.mas.command.AbstractSdkCommand;
import org.now.mas.command.CommandExecutor;
import org.now.mas.command.vo.ExecuteCommandVo;
import org.now.mas.config.DeviceConfig;
import org.now.mas.dto.BaseResponseDto;
import org.now.mas.dto.ExecuteCommandRequestDto;
import org.now.mas.dto.ExecuteCommandResponseDto;
import org.now.mas.sdk.HCNetSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Optional;

/**
 * @author Administrator
 */
@Service
public class HikvisionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HikvisionService.class);

    private final DeviceConfig deviceConfig;
    private final Map<String, AbstractSdkCommand> commandMap;

    public HikvisionService(DeviceConfig deviceConfig, Map<String, AbstractSdkCommand> commandMap) {
        this.deviceConfig = deviceConfig;
        this.commandMap = commandMap;
    }

    public boolean execute(final ExecuteCommandVo executeCommandVo, final AbstractSdkCommand command) {
        return CommandExecutor.executeCommand(executeCommandVo, command);
    }

    public ResponseEntity<BaseResponseDto> executeForController(final ExecuteCommandRequestDto executeCommandRequestDto) {
        // prepare param
        final String deviceName = executeCommandRequestDto.getDeviceName();
        final String commandName = executeCommandRequestDto.getCommandName();
        LOGGER.info("device {} execute command {}", deviceName, commandName);
        final DeviceConfig.DeviceInfo deviceInfo =
                Optional.ofNullable(deviceConfig.getDeviceInfoMap().get(deviceName))
                        .orElseThrow(() -> new InvalidParameterException("invalid device"));
        final AbstractSdkCommand cmd = Optional.ofNullable(commandMap.get(commandName))
                .orElseThrow(() -> new InvalidParameterException("invalid command"));
        final ExecuteCommandVo executeCommandVo = new ExecuteCommandVo();
        executeCommandVo.setDeviceName(deviceName);
        BeanUtils.copyProperties(deviceInfo, executeCommandVo);
        BeanUtils.copyProperties(executeCommandRequestDto, executeCommandVo);
        // execute
        final boolean result = execute(executeCommandVo, cmd);
        // result
        final ExecuteCommandResponseDto executeCommandResponseDto = new ExecuteCommandResponseDto();
        executeCommandResponseDto.setExecuteCommandVo(executeCommandVo);
        if (result) {
            executeCommandResponseDto.setCode("SUCCESS");
            executeCommandResponseDto.setMessage("command process success.");
            return ResponseEntity.ok().body(executeCommandResponseDto);
        } else {
            final int errorCode = HCNetSDK.INSTANCE.NET_DVR_GetLastError();
            executeCommandResponseDto.setExecuteCommandVo(executeCommandVo);
            executeCommandResponseDto.setCode(String.valueOf(errorCode));
            executeCommandResponseDto.setMessage("command process failed.");
            return ResponseEntity.badRequest().body(executeCommandResponseDto);
        }
    }
}
