package org.olympos.cloud.server.configuration.listener;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.olympos.cloud.common.content.ApiEnhanceConstants;
import org.olympos.cloud.common.content.ContentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ApiFaceConfListenerHandle
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-06-05 01:17
 */
@Slf4j
@Component
public class ApiConfListenerHandle {

    @Autowired
    private NacosConfigManager nacosConfigManager;
    @Autowired
    private ContentUtils contentUtils;
    @Autowired
    private ApiConfListener apiConfListener;
    @Autowired
    private ApiConfigListenListListener apiConfigListenListListener;

    public void apiConfigListenListHandle(ApiConfListenList apiConfListenList) throws NacosException {
        // todo 部分方法提出一个共通文件中，新建core，放入其中
        // todo 2 优化整理，初始化不进行配置更新等处理，全部放到监听器中去做

        // 获取最新监听列表
        String listenListContentOnline = nacosConfigManager.getConfigService().getConfig(
                ApiEnhanceConstants.API_CONFIG_LISTEN_LIST,
                ApiEnhanceConstants.NACOS_GROUP_NAME,
                500);

        // 初始化本地监听列表
        nacosConfigManager.getConfigService().addListener(
                ApiEnhanceConstants.API_CONFIG_LISTEN_LIST,
                ApiEnhanceConstants.NACOS_GROUP_NAME,
                apiConfigListenListListener);

        if ( null != listenListContentOnline) {
            // 发布配置 & 本地缓存监听列表
            apiConfListenList.getListenList().addAll(
                    this.string2ListForListenList( listenListContentOnline));
            return;
        }

        // 初始化线上监听列表
        nacosConfigManager.getConfigService().publishConfig(
                ApiEnhanceConstants.API_CONFIG_LISTEN_LIST,
                ApiEnhanceConstants.NACOS_GROUP_NAME,
                ApiEnhanceConstants.API_CONFIG_LISTEN_LIST_HEAD);
    }

    /**
     * 移除接口信息的监听器
     * @param apiConfigName
     * @throws NacosException
     */
    // todo 0614：监听器优化
    public void removeApiConfListener(String apiConfigName) {

        nacosConfigManager.getConfigService().removeListener(
                apiConfigName,
                ApiEnhanceConstants.NACOS_GROUP_NAME,
                apiConfListener);
        log.debug("Listener Remove! listener name: " + apiConfigName);
    }

    /**
     * 添加接口信息的监听器
     * @param apiConfigName
     * @throws NacosException
     */
    public String addApiConfListener(String apiConfigName) throws NacosException {

        nacosConfigManager.getConfigService().addListener(
                apiConfigName,
                ApiEnhanceConstants.NACOS_GROUP_NAME,
                apiConfListener);

        // 激活监听器
        String var = nacosConfigManager.getConfigService().getConfig(
                apiConfigName,
                ApiEnhanceConstants.NACOS_GROUP_NAME,
                contentUtils.getTimeout());
        log.debug( "Listener Loaded! api config is:" + StringUtils.defaultIfEmpty( var, ""));

        return var;
    }

    /**
     * 获取线上监听列表
     * @return
     * @throws NacosException
     * @throws IOException
     */
    public List<String> getListenListOnline() throws NacosException {

        String listenListConf = nacosConfigManager.getConfigService().getConfig(
                ApiEnhanceConstants.API_CONFIG_LISTEN_LIST,
                ApiEnhanceConstants.NACOS_GROUP_NAME,
                contentUtils.getTimeout());

        if ( StringUtils.isEmpty( listenListConf))
            return null;

        return this.string2ListForListenList( listenListConf);
    }

    /**
     * 字符串内容转换列表(监听列表使用)
     * @param s
     * @return
     */
    List<String> string2ListForListenList(String s) {

        if ( StringUtils.isBlank( s))
            return null;

        // todo 此处长度截取可能会出现bug，还有长度比较可能会出bug，需要重新检查
        int var = ApiEnhanceConstants.API_CONFIG_LISTEN_LIST_HEAD.length();
        if ( s.length() <= var)
            return new ArrayList<>();

        return  new ArrayList<>(
                    Arrays.asList(
                        s.substring( var).split(",")));
    }
}
