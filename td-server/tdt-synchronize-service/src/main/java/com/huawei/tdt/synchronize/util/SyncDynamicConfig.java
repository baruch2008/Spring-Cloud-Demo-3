package com.huawei.tdt.synchronize.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 鉴权动态配置.
 */
@Component
public final class SyncDynamicConfig
{
    /**
     * 项目同步Schedule.
     */
    @Value("${tdt.project.sync.schedule}")
    private String schedule;

    /**
     * 获取LDAP管理员密码.
     *
     * @return LDAP管理员密码
     */
    public String getProjectSyncSchedule()
    {
        return schedule;
    }
}
