package com.github.mvujas.nightmareauctionsbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.mvujas.nightmareauctionsbackend.managers.files.UploadFolderNameHolder;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private UploadFolderNameHolder uploadFolderNameHolder;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedHeaders("*")
			.allowedMethods("*")
			.allowedOrigins("*")
			.exposedHeaders("Authorization");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String publicStaticDir = uploadFolderNameHolder.getFileUploadFolderPathName();
		registry.addResourceHandler("/static/*")
			.addResourceLocations("classpath:" + publicStaticDir);
	}
}