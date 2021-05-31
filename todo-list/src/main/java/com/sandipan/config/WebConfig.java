package com.sandipan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.sandipan.util.ViewNames;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.sandipan")
public class WebConfig implements WebMvcConfigurer{
	
	//== constants ==
	public static final String RESOLVER_PREFIX= "/WEB-INF/view/";
	public static final String RESOLVER_SUFFIX= ".jsp";
	
	// == bean methods ==
	
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix(RESOLVER_PREFIX);
		viewResolver.setSuffix(RESOLVER_SUFFIX);
		return viewResolver;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(ViewNames.HOME);
	}
	
	
}
