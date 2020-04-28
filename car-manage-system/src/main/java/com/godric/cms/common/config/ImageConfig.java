package com.godric.cms.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 新增加一个类用来添加虚拟路径映射
 */

@Configuration
public class ImageConfig implements WebMvcConfigurer {

    @Value("${file.uploadFolder}")
    String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        System.out.println("uploadFolder = " + uploadFolder);
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + uploadFolder);
    }
}
