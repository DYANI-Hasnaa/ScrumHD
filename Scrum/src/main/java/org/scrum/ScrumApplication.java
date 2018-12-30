package org.scrum;

import java.io.File;

import org.scrum.dao.UserRepository;
import org.scrum.entities.Role;
import org.scrum.entities.user;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.scrum.storage.StorageProperties;
import org.scrum.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ScrumApplication {

	public static void main(String[] args) {
		
		
		ApplicationContext ctx = SpringApplication.run(ScrumApplication.class, args);
		UserRepository ur = ctx.getBean(UserRepository.class);
		//ur.save(new user("hasnaa","dyani", "hasnaa" , null , "hasnaadyani@gmail.com" , "dyani123", "0615389420", 1 ,Role.productOwner));
		//ur.findAll().forEach(u->System.out.println(u.getNom()));
	}
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
	
	
	
}
