package br.com.testealelo.aleloapi.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import br.com.testealelo.aleloapi.controller.VehicleController;

//@Configuration
//@ComponentScan(basePackageClasses= {VehicleController.class})
//@EnableSpringDataWebSupport
public class WebConfig implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	} 
	
}
