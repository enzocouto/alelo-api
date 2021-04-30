//package br.com.testealelo.aleloapi.openapi;
//
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import br.com.testealelo.aleloapi.controller.VehicleController;
//
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//@ComponentScan(basePackageClasses = {VehicleController.class})
//public class SpringFoxConfig implements WebMvcConfigurer{
//
//
//	@Bean
//	public Docket apiDocket() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.any())
//				.build()
//				.apiInfo(apiInfo());
//	}
//
//	private ApiInfo apiInfo() {
//
//		return new ApiInfo(
//				"Alelo API Specification",
//				"Teste Desenvolvedor Java - Alelo",
//				"V1",
//				"",
//				new Contact("Enzo Couto","","enzocouto@gmail.com"),
//				"License of API",
//				"",
//				Collections.emptyList()
//				);
//	}
//
//}
