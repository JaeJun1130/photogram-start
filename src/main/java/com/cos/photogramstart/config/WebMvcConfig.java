package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {                         //web 설정
    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")               //jsp페에지에서 /upload/** 이런 주소 패턴이 나오면 발동
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(6 * 10 * 6)                                //캐쉬 시간
                .resourceChain(true)                        //true 면 발동
                .addResolver(new PathResourceResolver());                 //등록
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }
}
