package com.kinzr.apellian.configration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(2);
//        registry.addResourceHandler("/css/**").addResourceLocations("/static/css/").setCachePeriod(2);
//        registry.addResourceHandler("/font/**").addResourceLocations("/static/font/").setCachePeriod(2);
        registry.addResourceHandler("/img/**").addResourceLocations("/static/img/").setCachePeriod(2);
//        registry.addResourceHandler("/js/**").addResourceLocations("/static/js/").setCachePeriod(2);
//        registry.addResourceHandler("/bootstrap/**").addResourceLocations("/static/bootstrap/").setCachePeriod(2);
//        registry.addResourceHandler("/**").addResourceLocations("/static/").setCachePeriod(20);
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}

