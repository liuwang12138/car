package com.godric.cms.common.config;

import com.godric.cms.common.interceptor.AdminLoginInterceptor;
import com.godric.cms.common.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Godric
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> userExcludePaths = new ArrayList<>();
        userExcludePaths.add("/user/login");
        userExcludePaths.add("/user/register");
        userExcludePaths.add("/user/not/login");
        userExcludePaths.add("/admin/login");
        userExcludePaths.add("/user/login/");
        userExcludePaths.add("/user/register/");
        userExcludePaths.add("/user/not/login/");
        userExcludePaths.add("/admin/login/");

        registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/**").excludePathPatterns(userExcludePaths);

        List<String> adminExcludePaths = new ArrayList<>();
        adminExcludePaths.add("/admin/login");
        adminExcludePaths.add("/admin/login/");

        registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns(adminExcludePaths);
    }
}
