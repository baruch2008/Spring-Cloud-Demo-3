package com.huawei.tdt.common.authorization.intercepter;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huawei.tdt.common.authorization.annotation.Authorization;
import com.huawei.tdt.common.constants.Constants;
import com.huawei.tdt.common.mapper.UserMapper;
import com.huawei.tdt.common.util.CommonUtil;

/**
 * 自定义拦截器，判断此次请求是否有权限.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter
{
    /**
     * 用户Mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 鉴权预处理.
     *
     * @param request servlet请求
     * @param response servlet响应
     * @param handler 处理器
     * @return 预处理是否成功
     * @throws Exception 任何异常
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod))
        {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Authorization authAnnotation = method.getAnnotation(Authorization.class);
        if (authAnnotation == null)
        {
            return true;
        }

        String userId = CommonUtil.getUserId(request);

        String resourceId = CommonUtil.getValue(request, Constants.RESOURCE_ID);
        if (null == resourceId)
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String privilegeId = authAnnotation.id();

        if (!checkPrivilege(resourceId, userId, privilegeId))
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    private boolean checkPrivilege(String projectId, String userId, String privilegeId)
    {
        List<String> privileges = userMapper.getUserPrivileges(projectId, userId);
        if (null == privileges || privileges.isEmpty())
        {
            return false;
        }

        for (String privilege : privileges)
        {
            if (privilege.equals(privilegeId))
            {
                return true;
            }
        }

        return false;
    }
}
