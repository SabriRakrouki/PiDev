package tn.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class PiDevSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiDevSpringApplication.class, args);
	}

}
