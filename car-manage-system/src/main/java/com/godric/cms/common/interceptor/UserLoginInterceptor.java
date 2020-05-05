package com.godric.cms.common.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Godric
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("----------------用户进入UserLoginInterceptor-----------------");

        Object username = request.getSession().getAttribute("username");
        if (username == null) {
            log.info("-------用户未登录，不予放行-------");
            request.getRequestDispatcher("/user/not/login").forward(request, response);
            return false;
        }
        log.info("------用户已登陆，UserLoginInterceptor放行-------");
        return true;
    }
}
