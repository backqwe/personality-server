package org.olympos.cloud.server.handler.paramHandler;

import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import org.dromara.soul.common.constant.Constants;
import org.dromara.soul.common.dto.MetaData;
import org.olympos.cloud.common.content.ApiEnhanceConstants;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.server.ServerWebExchange;

import java.util.*;

/**
 * ProcessorParam
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-07-24 13:41
 */
public class PreProcessorParam extends BaseParam {

    private final ServerWebExchange exchange;

    public static PreProcessorParam getInstance(ServerWebExchange exchange) {
        return new PreProcessorParam( exchange);
    }

    private PreProcessorParam(ServerWebExchange exchange) {
        this.exchange = exchange;

        // 初始化 接口参数 自定义参数
        exchange.getAttributes().put( ApiEnhanceConstants.CUSTOM_PARAMS, new HashMap<String, Object>());

        // 初始化参数类型
        super.setParamTypes( new ArrayList<>( Arrays.asList( this.getParameterTypes().split(","))));

        String param = exchange.getAttribute( Constants.PARAM_TRANSFORM);
        if ( StringUtils.isNotEmpty( param)) {
            super.setApiParams( new JacksonJsonParser().parseMap(param));
            exchange.getAttributes().put( ApiEnhanceConstants.API_PARAMS,
                    new JacksonJsonParser().parseMap( this.exchange.getAttribute( Constants.PARAM_TRANSFORM)));
        } else {
            super.setApiParams( new HashMap<>());
            exchange.getAttributes().put( ApiEnhanceConstants.API_PARAMS, new HashMap<>());
        }
    }

    private String getParameterTypes() {
        MetaData metaData = this.exchange.getAttribute( Constants.META_DATA);
        return null == metaData || null == metaData.getParameterTypes() ?
                "" : metaData.getParameterTypes();
    }

    public boolean ifEmptySetParam(String paramName, Object paramValue) {
        return this.setParam( paramName, paramValue, null != super.getApiParams().get( paramName));
    }

    public boolean modifyParam(String paramName, Object paramValue) {
        return this.setParam( paramName, paramValue, null == super.getApiParams().get( paramName));
    }

    private boolean setParam(String paramName, Object paramValue, boolean condition) {
        if ( condition) return false;

        super.getApiParams().put( paramName, paramValue);
        this.exchange.getAttributes().put( Constants.PARAM_TRANSFORM, JacksonUtils.toJson( super.getApiParams()));
        this.exchange.getAttributes().put( ApiEnhanceConstants.API_PARAMS, super.getApiParams());
        return true;
    }

    public Object getParam(String paramName) {
        return super.getApiParams().get( paramName);
    }

    public Map<String, Object> getParams() {
        return super.getApiParams();
    }

    public List<String> getParamTypes() {
        return super.getParamTypes();
    }

    public Object getCustomParam(String paramName) {
        Map<String, Object> customParams = this.exchange.getAttribute( ApiEnhanceConstants.CUSTOM_PARAMS);
        return null == customParams ? null : customParams.get( paramName);
    }

    public void setCustomParam(String paramName, Object paramValue) {
        Map<String, Object> customParams = this.exchange.getAttribute( ApiEnhanceConstants.CUSTOM_PARAMS);
        if ( null != customParams)
            customParams.put( paramName, paramValue);
    }

    public Map<String, Object> getCustomParams() {
        return this.exchange.getAttribute( ApiEnhanceConstants.CUSTOM_PARAMS);
    }

    public void setCustomParams(Map<String, Object> paramMap) {
        Map<String, Object> customParams = this.exchange.getAttribute( ApiEnhanceConstants.CUSTOM_PARAMS);
        if ( null != customParams)
            customParams.putAll( paramMap);
    }
}
