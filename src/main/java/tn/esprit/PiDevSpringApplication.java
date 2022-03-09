package tn.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;






import java.io.File;




@EnableScheduling
@SpringBootApplication(exclude ={SecurityAutoConfiguration.class})


public class PiDevSpringApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(PiDevSpringApplication.class, args);
			
	}
}
