package org.olympos.cloud.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.dromara.soul.common.dto.RuleData;
import org.dromara.soul.common.dto.SelectorData;
import org.dromara.soul.plugin.api.SoulPluginChain;
import org.dromara.soul.plugin.base.AbstractSoulPlugin;
import org.olympos.cloud.server.configuration.ApiConfig;
import org.olympos.cloud.server.handler.enhanceHandler.ApiFaceEnhanceHandler;
import org.olympos.cloud.server.handler.paramHandler.PreProcessorParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ApiFacePreprocessor
 *
 * @Describe: 接口前置处理
 * @Author Chanse Hao
 * @Date 2021-07-15 20:50
 */
@Slf4j
@Component
public class ApiFacePreprocessor extends AbstractSoulPlugin {

    @Autowired
    private ApiConfig apiConfig;
/*    @Autowired
    private ProcessorChainHandler processorChainHandler;*/

    @Override
    public Mono<Void> doExecute(ServerWebExchange exchange, SoulPluginChain chain, SelectorData selector, RuleData rule) {
        // todo 接口增强执行（插件处理）

        String apiFaceName = getApiFaceName( exchange);

        // 检索增强配置列表，判断是否需要继续进行，无需进行则退出
        if ( !apiConfig.getApiEnhanceConfigs().containsKey( apiFaceName))
            return chain.execute( exchange);

        // 参数
        PreProcessorParam param = PreProcessorParam.getInstance( exchange);

        // 调用处理循环
        for ( ApiFaceEnhanceHandler enhanceHandler : apiConfig.getApiEnhanceHandles())
            if ( !enhanceHandler.beforeSkip()
                    && apiConfig.getApiEnhanceConfigs().get( apiFaceName).contains( enhanceHandler.getClass().getSimpleName()))
                enhanceHandler.beforeHandle( param, exchange);
            else
                continue;

        // 调用处理循环( todo reactor版本)
        // processorChainHandler.execute( exchange, preprocessorParamHandler);

        exchange.getAttributes().put("BeforePlugin", "success");
        return chain.execute( exchange);
    }

    /**
     * todo 日后提出 common方法
     * @param exchange
     * @return
     */
    private String getApiFaceName(ServerWebExchange exchange) {

        // todo 获取前端请求接口名
        String path = exchange.getRequest().getPath().pathWithinApplication().value();

        if ( !path.contains("/"))
            return path;

        String[] var = path.split("/");

        if ( var.length <= 1)
            return var[0];

        return var[var.length-1];
    }

    @Override
    public int getOrder() {
        return 59;
    }

    @Override
    public String named() {
        return "BeforePlugin";
    }

    @Override
    public Boolean skip(ServerWebExchange exchange) {
        // todo 日后增加跳过条件，当接口配置列表（前置增强列表）中没有此接口，则跳过
        return false;
    }
}
