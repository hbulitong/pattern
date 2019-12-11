package com.design.pattern;

import com.design.pattern.services.QueryBizService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/*@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class PatternApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext=
				SpringApplication.run(PatternApplication.class, args);
		QueryBizService queryBizService=
				applicationContext.getBean("queryBizService", QueryBizService.class);
		System.out.println(queryBizService.query("c"));
	}

}*/

@SpringBootApplication
@EnableScheduling
@ImportResource("classpath:applicationContext.xml")
public class PatternApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PatternApplication.class, args);
	}
}
