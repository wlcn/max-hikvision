package org.now.mas.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.now.mas.config.SdkConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * config util
 *
 * @author Administrator
 */
public final class ConfigUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtil.class);

    private static final String OS_NAME_WINDOWS = "windows";
    private static final String OS_NAME_LINUX = "linux";

    private static SdkConfig sdkConfig;

    static {
        try {
            sdkConfig = loadSdkConfig("sdkConfig.json");
        } catch (IOException e) {
            LOGGER.error("load SDK CONFIG error", e);
        }
    }

    private ConfigUtil() {
    }

    public static SdkConfig getSdkConfig() {
        return sdkConfig;
    }

    public static SdkConfig loadSdkConfig(String fileName) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String configStr = loadFile(fileName);
        final Map<String, SdkConfig> configMap = mapper.readValue(configStr, new TypeReference<>() {
        });
        if (isWin()) {
            return configMap.get(OS_NAME_WINDOWS);
        } else {
            return configMap.get(OS_NAME_LINUX);
        }
    }

    public static String loadFile(String fileName) throws IOException {
        final ClassPathResource classPathResource = new ClassPathResource(fileName);
        final InputStream inputStream = classPathResource.getInputStream();
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }

    public static String getResourcePath() {
        try {
            return ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            LOGGER.error("get classpath error.", e);
            return StringUtils.EMPTY;
        }
    }

    public static String getOsName() {
        final String osName = System.getProperty("os.name");
        LOGGER.info("current os is ");
        return osName;
    }

    public static boolean isWin() {
        final String osName = getOsName();
        LOGGER.info("current os is {}", osName);
        return StringUtils.containsAnyIgnoreCase(osName, OS_NAME_WINDOWS);
    }

}
