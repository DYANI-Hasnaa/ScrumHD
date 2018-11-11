package org.scrum.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.scrum.ScrumApplication;
import org.scrum.dao.BacklogRepository;
import org.scrum.dao.UserRepository;
import org.scrum.entities.backlog;
import org.scrum.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private BacklogRepository br;
	

	@RequestMapping(value="/")
	public String home() {
		return "redirect:/index";
	}
	

	public void projects(Model model) {
		List<backlog> backlogs=br.findAll();
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	@RequestMapping(value="/index")
	public String index(Model model) {
		
		this.projects(model);	
	    return "index";
	}
	
	@RequestMapping(value="/403")
	public String accessDneied() {
		return "403";
	}
	
	@RequestMapping(value="/signin")
	public String signin() {
		return "signin";
	}
	
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String edit(Model model, String username) {
		
		this.projects(model);
		
		user u=ur.FindByUsername(username);
		
		model.addAttribute("user",u);
		
		System.out.println(u.getNom());
		
		return "userProfile";
		
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(Model model) {
		
		model.addAttribute("user",new user());
		return "signup";
		
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public String saveUser(Model model,@Valid user user, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return "signup";
		ur.save(user);
		return "signin";
		
	}
	
	@RequestMapping(value="/saveEdit", method=RequestMethod.POST)
	public String saveEdit(Model model,@Valid user user, BindingResult bindingResult) {
		
		this.projects(model);
		
		if(bindingResult.hasErrors())
			return "userProfile";
		ur.save(user);
		return "userProfile";
		
	}
	
	

	
}
