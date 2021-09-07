package org.olympos.cloud.common.configuration;

import lombok.Data;

/**
 * ModuleBaseConfig
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-05-31 15:49
 */
@Data
public class ModuleBaseConfig {

    // 模块类型：gateway=网关 client/其它/空=业务模块
    private String type;

    // todo 是否启用配置更新功能 代替集群开关
    private boolean update;
}
