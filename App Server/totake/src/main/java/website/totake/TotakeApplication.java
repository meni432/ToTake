package website.totake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = ItemController.class)
@ComponentScan(basePackageClasses = TripController.class)
@ComponentScan(basePackageClasses = UserController.class)
public class TotakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotakeApplication.class, args);
	}
}
