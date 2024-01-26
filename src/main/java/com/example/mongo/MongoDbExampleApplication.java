package com.example.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "com.example.mongo.repository")
@EnableSwagger2
public class MongoDbExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbExampleApplication.class, args);
	}

}
