package tn.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

<<<<<<< HEAD





@SpringBootApplication(exclude ={SecurityAutoConfiguration.class})

=======
@SpringBootApplication(exclude ={SecurityAutoConfiguration.class})
>>>>>>> 51a6c3fb7d09fc0888591595b07f5824ca28a09b
public class PiDevSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiDevSpringApplication.class, args);
	}

}
