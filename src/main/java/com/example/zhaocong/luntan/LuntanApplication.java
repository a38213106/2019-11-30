package com.example.zhaocong.luntan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class LuntanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuntanApplication.class, args);
	}


	@Configuration
	public class MyWebAppConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/photo_img/**").addResourceLocations("classpath:/photo_img/");
			super.addResourceHandlers(registry);
		}
	}

}
