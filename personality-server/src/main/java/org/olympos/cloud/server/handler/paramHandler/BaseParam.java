package org.olympos.cloud.server.handler.paramHandler;

import java.util.List;
import java.util.Map;

/**
 * BaseParam
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-08-30 05:46
 */
public class BaseParam {

    private Map<String, Object> apiParams;

    private List<String> paramTypes;

    public Map<String, Object> getApiParams() {
        return apiParams;
    }

    public void setApiParams(Map<String, Object> apiParams) {
        // 向 exchange.getAttribute( ApiEnhanceConstants.API_PARAMS) put数据
        this.apiParams = apiParams;
    }

    public List<String> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<String> paramTypes) {
        this.paramTypes = paramTypes;
    }
}
