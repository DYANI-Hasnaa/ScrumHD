package org.scrum.web;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.scrum.ScrumApplication;
import org.scrum.dao.BacklogRepository;
import org.scrum.dao.MessageRepository;
import org.scrum.dao.UserRepository;
import org.scrum.entities.Role;
import org.scrum.entities.backlog;
import org.scrum.entities.message;
import org.scrum.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	
	@Autowired
	private MessageRepository mr;

	@RequestMapping(value="/")
	public String home() {
		return "redirect:/index";
	}
	
	

	public void projects(Model model, String username) {
		Set<backlog> backlogs=br.FindByUser(username);
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	@RequestMapping(value="/index")
	public String index(Model model, Principal principal) {
		String username = principal.getName();
		this.projects(model,username);
		user u=ur.FindByUsername(username);
		
		if(u.getRole()==Role.client)
		{
			return "indexClient";
		}
		else 
			
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
		
		this.projects(model, username);
		
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
		
		String username = user.getUsername();
		this.projects(model, username);
		
		if(bindingResult.hasErrors())
			return "userProfile";
		ur.save(user);
		return "userProfile";
		
	}
	
	
	@RequestMapping(value="/discution")
	public String discution(Model model, Model mod, String username) {
		
		user u=ur.FindByUsername(username);
		
		Set<message> messages= mr.FindMessageById(u.getId());
		
	    model.addAttribute("listMessages", messages);
	    
	    //mod.addAttribute("role", u.getRole());
		
		return "discution";
		
	}
	
	
	@RequestMapping(value="/SendMessage", method=RequestMethod.POST)
	public String SendMessage(Model model,String username, @Valid message message, BindingResult bindingResult) {
		
		Set<backlog> b = br.FindByUser(username);
		
		System.out.println("username du client:"+username);
		
		user u = ur.FindPo();
		
		user u1=ur.FindByUsername(username);
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		int type;
		
		if(u1.getRole()==Role.client)
			type=1;
		else 
			type=0;
		
		mr.save(new message(message.getContenu(), date , u1.getId(), u.getId(), u1.getRole(), type));
		
		return "redirect:/discution?username="+username;
		
	}

	
}
