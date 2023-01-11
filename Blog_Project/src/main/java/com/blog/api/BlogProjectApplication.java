package com.blog.api;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogProjectApplication {

	public static final Logger logger = LoggerFactory.getLogger(BlogProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BlogProjectApplication.class, args);

		System.out.println("hello");

//		logger.atError();
//		logger.atDebug();
//		logger.atInfo();
//		logger.atTrace();
//		logger.atWarn();

	}

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();

	}

}
