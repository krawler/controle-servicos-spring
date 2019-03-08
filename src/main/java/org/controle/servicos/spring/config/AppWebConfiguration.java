package org.controle.servicos.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan(basePackages= {"org.controle.servicos.spring.controller", "org.controle.servicos.spring.dao"})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/recursos/**").addResourceLocations("/recursos/");
	}
		  
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }    

}
