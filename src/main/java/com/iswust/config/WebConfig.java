package com.iswust.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.uploadImage}")
    private String uploadImage;
    @Value("${file.uploadReportImage}")
    private String uploadReportImage;

    /**
     * 配置静态资源地址,也就是图片存储位置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath+"**")
                .addResourceLocations("file:///" + uploadFolder + uploadImage)
                .addResourceLocations("file:///" + uploadFolder + uploadReportImage);
    }
}
