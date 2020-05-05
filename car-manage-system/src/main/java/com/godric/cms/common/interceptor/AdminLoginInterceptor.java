package com.godric.cms.common.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.godric.cms.common.constants.CmsConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author Godric
 */
@Slf4j
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("------------------用户进入AdminLoginInterceptor--------------------");

        Object username = request.getSession().getAttribute("username");
        if (!CmsConstants.ADMIN_USERNAME.equals(username)) {
            log.info("-------用户未登录或权限不够，不予放行-------");
            request.getRequestDispatcher("/admin/not/login").forward(request, response);
            return false;
        }
        log.info("------用户具有管理员权限，放行-------");
        return true;
    }
}
