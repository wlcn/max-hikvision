package org.now.mas.dto;

import org.now.mas.command.vo.ExecuteCommandVo;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class ExecuteCommandResponseDto extends BaseResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private ExecuteCommandVo executeCommandVo;

    public ExecuteCommandVo getExecuteCommandVo() {
        return executeCommandVo;
    }

    public void setExecuteCommandVo(ExecuteCommandVo executeCommandVo) {
        this.executeCommandVo = executeCommandVo;
    }
}
