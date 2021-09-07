package org.olympos.cloud.server.configuration.listener;

import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.common.utils.StringUtils;
import lombok.SneakyThrows;
import org.olympos.cloud.server.configuration.ApiConfHandle;
import org.olympos.cloud.server.configuration.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Executor;

/**
 * ApiConfigListenListListener
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-07-29 15:21
 */
@Component
public class ApiConfigListenListListener implements Listener {

    @Autowired
    private ApiConfListenerHandle apiConfListenerHandle;
    @Autowired
    private ApiConfListenList apiConfListenList;
    @Autowired
    private ApiConfHandle apiConfHandle;
    @Autowired
    private ApiConfig apiConfig;

    @SneakyThrows
    @Override
    public void receiveConfigInfo(String configInfo) {
        // todo 此处需要检查，很多情况下有bug

        // 获取本地监听列表缓存
        Set<String> listenListLocal = apiConfListenList.getListenList();

        // 获取线上监听列表
        List<String> listenListOnline = apiConfListenerHandle.string2ListForListenList( configInfo);
        if ( null == listenListOnline)
            listenListOnline = new ArrayList<>();

        Set<String> var = new HashSet<>( listenListLocal);

        for ( String listenOnline : listenListOnline)
            // 遇到本地监听列表不存在的监听器名，则添加此监听器，并且把内容一并存入接口配置列表
            if ( !listenListLocal.contains( listenOnline)) {
                String var2 = apiConfListenerHandle.addApiConfListener( listenOnline);
                Map<String, Set<String>> var3;
                if ( StringUtils.isNotBlank( var2)
                        && null != (var3 = apiConfHandle.makeApiEnhanceMap( var2)))
                    apiConfig.getApiEnhanceConfigs().putAll( var3);
            } else
                var.remove( listenOnline);

        // 移除线上监听列表中不存在的监听器
        for ( String s : var)
            apiConfListenerHandle.removeApiConfListener( s);

        // 更新本地缓存的监听列表
        apiConfListenList.getListenList().addAll( listenListOnline);
    }

    @Override
    public Executor getExecutor() {
        return null;
    }
}
