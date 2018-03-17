package com.huawei.tdt.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class CommonUtil {
    private CommonUtil() {

    }

    public static String getValue(HttpServletRequest request, String key) {
        Object value = request.getAttribute(key);
        if (null != value) {
            return String.valueOf(value);
        }

        Cookie[] cookies = request.getCookies();
        if (null != cookies && 0 < cookies.length) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        
        return null;
    }
}
