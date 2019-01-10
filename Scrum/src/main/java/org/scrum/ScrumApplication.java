package org.scrum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ScrumApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ScrumApplication.class, args);
		//UserRepository ur = ctx.getBean(UserRepository.class);
		//ur.save(new user("mariam" , "larhouti", "mariam" , null , "mariam@gmail.com", "mariam00", "0612457896", 1 ,Role.productOwner));
		//ur.findAll().forEach(u->System.out.println(u.getNom()));
	}
}
