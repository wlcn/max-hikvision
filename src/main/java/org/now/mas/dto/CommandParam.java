package org.now.mas.dto;

/**
 * command param
 *
 * @author Administrator
 */
public class CommandParam {

    /**
     * ptz control
     */
    private int ptzCommand;
    private int dwStop;
    private int dwSpeed;

    /**
     * ptz preset
     */
    private int ptzPresetCmd;
    private int presetIndex;

    /**
     * device ability type
     */
    private int abilityType;
    private String abilityInputXml;

    public int getPtzCommand() {
        return ptzCommand;
    }

    public void setPtzCommand(int ptzCommand) {
        this.ptzCommand = ptzCommand;
    }

    public int getDwStop() {
        return dwStop;
    }

    public void setDwStop(int dwStop) {
        this.dwStop = dwStop;
    }

    public int getDwSpeed() {
        return dwSpeed;
    }

    public void setDwSpeed(int dwSpeed) {
        this.dwSpeed = dwSpeed;
    }

    public int getPtzPresetCmd() {
        return ptzPresetCmd;
    }

    public void setPtzPresetCmd(int ptzPresetCmd) {
        this.ptzPresetCmd = ptzPresetCmd;
    }

    public int getPresetIndex() {
        return presetIndex;
    }

    public void setPresetIndex(int presetIndex) {
        this.presetIndex = presetIndex;
    }

    public int getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(int abilityType) {
        this.abilityType = abilityType;
    }

    public String getAbilityInputXml() {
        return abilityInputXml;
    }

    public void setAbilityInputXml(String abilityInputXml) {
        this.abilityInputXml = abilityInputXml;
    }
}
