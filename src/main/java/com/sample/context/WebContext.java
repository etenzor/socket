package com.sample.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.sample.controller"
})
public class WebContext extends WebMvcConfigurerAdapter {
        @Bean
        public ViewResolver getInternalResourceViewResolver(){
                InternalResourceViewResolver resolver = new InternalResourceViewResolver();
                resolver.setPrefix("/WEB-INF/jsp/");
                resolver.setSuffix(".jsp");
                resolver.setViewClass(JstlView.class);
                return resolver;
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/res/**", "/js/**").addResourceLocations("/WEB-INF/res/", "/WEB-INF/js/");
        }
}