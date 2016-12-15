package idoussef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"idoussef.*" })

public class WebBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebBootApplication.class, args);
	}
}
