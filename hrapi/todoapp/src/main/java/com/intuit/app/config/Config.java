package com.intuit.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = { "com.intuit.app.controller"})
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {


}
