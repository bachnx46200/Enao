package dev.hunghh.springsecurityjwtmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringsecurityJwtMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityJwtMysqlApplication.class, args);

	}

}
