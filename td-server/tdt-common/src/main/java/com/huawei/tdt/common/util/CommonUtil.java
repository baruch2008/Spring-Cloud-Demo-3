package com.huawei.tdt.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class CommonUtil {
    private CommonUtil() {

    }

        public static String getValue(HttpServletRequest request, String key)
    {
        String value = request.getHeader(key);
        if (null != value)
        {
            return value;
        }
        Cookie[] cookies = request.getCookies();
        if (null == cookies)
        {
            return value;
        }
        if (Constants.ZERO >= cookies.length)
        {
            return value;
        }
        for (Cookie cookie : cookies)
        {
            if (cookie.getName().equals(key))
            {
                value = cookie.getValue();
                break;
            }
        }
        return value;
    }

    /**
     * 获取当前用户ID
     * 
     * @param request 请求体
     * @return 用户ID
     */
    public static String getUserId(HttpServletRequest request)
    {
        Object attr = request.getAttribute(Constants.CURRENT_USER_ID);
        if (null != attr)
        {
            return String.valueOf(attr);
        }

        Token token = CommonUtil.getToken(CommonUtil.getValue(request, Constants.AUTHORIZATION));
        if (null != token)
        {
            return token.getUserId();
        }

        return null;
    }

    /**
     * 根据请求中的authentication获取Token
     * 
     * @param authentication 鉴权串
     * @return Token
     */
    public static Token getToken(String authentication)
    {
        if (authentication == null || authentication.length() == 0)
        {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != Constants.TWO)
        {
            return null;
        }
        // 使用userId和源token简单拼接成的token，可以增加加密措施
        String userId = param[0];
        String token = param[1];
        return new Token(userId, token);
    }
}
