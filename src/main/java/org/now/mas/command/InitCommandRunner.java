package org.now.mas.command;

import com.google.common.collect.ImmutableList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.now.mas.config.DeviceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * 初始化需要的目录
 *
 * @author Administrator
 */
@Component
public class InitCommandRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitCommandRunner.class);

    @Autowired
    private DeviceConfig deviceConfig;

    @Override
    public void run(String... args) throws Exception {
        deviceConfig.getDeviceInfoMap().forEach((deviceName, deviceInfo) ->
                ImmutableList.of(
                        deviceInfo.getPicturePath(),
                        deviceInfo.getRealDataPath()
                )
                        .stream()
                        .map(p -> StringUtils.join(p, deviceName))
                        .forEach(p -> {
                            try {
                                final File file = FileUtils.getFile(p);
                                if (file.exists()) {
                                    LOGGER.info("tmp dir already exist {}", p);
                                } else {
                                    FileUtils.forceMkdir(FileUtils.getFile(p));
                                    LOGGER.info("create tmp dir done {}", p);
                                }
                            } catch (IOException e) {
                                LOGGER.error("create tmp path error", e);
                            }
                        })
        );
        LOGGER.info("init done");
    }
}
