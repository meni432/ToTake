package website.totake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootApplication
@WebAppConfiguration
@ContextConfiguration
public class TotakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotakeApplication.class, args);
	}
}
