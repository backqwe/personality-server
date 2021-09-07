package org.olympos.cloud.common.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * BaseConfig
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-05-31 15:10
 */
@ConfigurationProperties(prefix = "personality")
@Component
@Data
public class BaseConfig {

    // 是否启用框架功能
    private boolean enable;

    // 模块配置
    private ModuleBaseConfig module;

    // 延迟时间(ms)
    private long timeout = 500;
}
