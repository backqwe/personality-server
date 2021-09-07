package org.olympos.cloud.common.content;

import com.alibaba.nacos.common.utils.StringUtils;
import org.olympos.cloud.common.configuration.BaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ContentUtils
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-05-31 16:58
 */
@Component
public class ContentUtils {

    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private BaseConfig baseConfig;

    public String getApiConfigName() {
        return null == applicationName ?
                "ApiConfig.properties"
                : applicationName + "-ApiConfig.properties";
    }

    public boolean isBusinessModule() {
        return null == baseConfig.getModule()
                || StringUtils.isEmpty( baseConfig.getModule().getType())
                || StringUtils.equals( baseConfig.getModule().getType(), "client")
                || !StringUtils.equals( baseConfig.getModule().getType(), "gateway");
    }

    public boolean isGateway() {
        return null != baseConfig.getModule()
                && StringUtils.equals( baseConfig.getModule().getType(), "gateway");
    }

    public long getTimeout() {
        return baseConfig.getTimeout();
    }
}
