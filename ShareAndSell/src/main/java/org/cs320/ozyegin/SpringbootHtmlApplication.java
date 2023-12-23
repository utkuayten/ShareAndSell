package org.cs320.ozyegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class})
public class SpringbootHtmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHtmlApplication.class, args);
	}

}
