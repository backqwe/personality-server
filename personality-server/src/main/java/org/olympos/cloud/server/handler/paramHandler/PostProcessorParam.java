package org.olympos.cloud.server.handler.paramHandler;

import org.dromara.soul.common.constant.Constants;
import org.olympos.cloud.common.content.ApiEnhanceConstants;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;
import java.util.Objects;

/**
 * ProcessorParam
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-07-24 13:41
 */
public class PostProcessorParam extends BaseParam {

    private final ServerWebExchange exchange;

    public static PostProcessorParam getInstance(ServerWebExchange exchange) {
        return new PostProcessorParam( exchange);
    }

    private PostProcessorParam(ServerWebExchange exchange) {
        this.exchange = exchange;
        // 获取接口参数
        super.setApiParams( exchange.getAttribute( ApiEnhanceConstants.API_PARAMS));
    }

    public Map<String, String> getResults() {
        return exchange.getAttribute( Constants.DUBBO_RPC_RESULT);
    }

    public String getResult(String k) {
        Map<String, String> result = exchange.getAttribute( Constants.DUBBO_RPC_RESULT);
        return Objects.nonNull( result) ? result.get( k) : "";
    }

    public void setResult(String resultName, String resultValue) {
        Map<String, String> result = exchange.getAttribute( Constants.DUBBO_RPC_RESULT);
        if ( Objects.nonNull( result))
            result.put( resultName, resultValue);
    }

    public String getResultType() {
        return exchange.getAttribute( Constants.CLIENT_RESPONSE_RESULT_TYPE);
    }

    public void setResultType(String resultType) {
        exchange.getAttributes().put( Constants.CLIENT_RESPONSE_RESULT_TYPE, resultType);
    }

    public Object getParam(String paramName) {
        return super.getApiParams().get( paramName);
    }

    public Map<String, Object> getParams() {
        return super.getApiParams();
    }

    public Object getCustomParam(String paramName) {
        Map<String, Object> customParams = exchange.getAttribute( ApiEnhanceConstants.CUSTOM_PARAMS);
        return null == customParams ? null : customParams.get( paramName);
    }

    public Map<String, Object> getCustomParams() {
        return exchange.getAttribute( ApiEnhanceConstants.CUSTOM_PARAMS);
    }
}
