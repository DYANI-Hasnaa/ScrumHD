package org.scrum;

import org.scrum.dao.UserRepository;
import org.scrum.entities.Role;
import org.scrum.entities.user;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ScrumApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ScrumApplication.class, args);
		UserRepository ur = ctx.getBean(UserRepository.class);
		//ur.save(new user("hasnaa","dyani", "hasnaa" , null , "hasnaadyani@gmail.com" , "dyani123", "0615389420", 1 ,Role.productOwner));
		//ur.findAll().forEach(u->System.out.println(u.getNom()));
	}
}
