//package com.gpro.consulting.tiers.commun.rest.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//@EnableWebMvc
//@Configuration
//@ComponentScan({ "com.journaldev.spring.*" })
//@Import(value = { SecurityConfig.class })
//public class LoginApplicationConfig {
//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/");
//		viewResolver.setSuffix(".jsp");
//		return viewResolver;
//	}
//	
//}
