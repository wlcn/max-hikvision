package org.now.mas.command;

import com.google.common.collect.ImmutableList;
import org.now.mas.command.vo.ExecuteCommandVo;
import org.now.mas.sdk.HCNetSDK;
import org.now.mas.util.MasUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * CommandExecutor
 *
 * @author Administrator
 */
public final class CommandExecutor {

    private CommandExecutor() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandExecutor.class);

    /**
     * user execute command
     * user map to unique device
     *
     * @param command command
     * @return true or false
     */
    public static boolean executeCommand(final ExecuteCommandVo executeCommandVo, final AbstractSdkCommand command) {
        LOGGER.info("executeCommand executeCommandVo is {}", executeCommandVo);

        final BooleanSupplier loginSupplier = () -> command.login(executeCommandVo);
        final BooleanSupplier beforeCommandSupplier = () -> command.beforeCommand(executeCommandVo);
        final BooleanSupplier doCommandSupplier = () -> command.doCommand(executeCommandVo);
        final BooleanSupplier afterCommandSupplier = () -> command.afterCommand(executeCommandVo);

        final List<BooleanSupplier> functionList = ImmutableList.of(
                command::init,
                command::setLog,
                loginSupplier,
                beforeCommandSupplier,
                doCommandSupplier,
                afterCommandSupplier
        );

        boolean allStepsAreSuccess = Boolean.TRUE;
        for (BooleanSupplier supplier : functionList) {
            final boolean success = supplier.getAsBoolean();
            if (success) {
                LOGGER.debug("step execute success");
            } else {
                allStepsAreSuccess = Boolean.FALSE;
                LOGGER.error("step execute fail. error code is {}", HCNetSDK.INSTANCE.NET_DVR_GetLastError());
                break;
            }
        }
        // if already login, need logout and cleanup finally
        if (MasUtil.checkCallResult(executeCommandVo.getUserId())) {
            command.logout(executeCommandVo.getUserId());
            command.cleanup();
        }
        return allStepsAreSuccess;
    }
}
