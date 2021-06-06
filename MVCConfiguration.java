package com.ahliunited.branch.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
        viewControllerRegistry.addViewController("/").setViewName("redirect:/pages/onboarding");
        viewControllerRegistry.addViewController("/pages").setViewName("redirect:/pages/onboarding");
        viewControllerRegistry.addViewController("/pages/").setViewName("redirect:/pages/onboarding");
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
