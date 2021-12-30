package org.now.mas.dto;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class ExecuteCommandRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * device unique name
     */
    private String deviceName;

    /**
     * command name
     */
    private String commandName;

    private CommandParam commandParam = new CommandParam();

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public CommandParam getCommandParam() {
        return commandParam;
    }

    public void setCommandParam(CommandParam commandParam) {
        this.commandParam = commandParam;
    }
}
