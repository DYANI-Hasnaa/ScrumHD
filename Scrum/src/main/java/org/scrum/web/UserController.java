package org.scrum.web;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.validation.Valid;
import org.scrum.dao.BacklogRepository;
import org.scrum.dao.MessageRepository;
import org.scrum.dao.UserRepository;
import org.scrum.entities.Role;
import org.scrum.entities.backlog;
import org.scrum.entities.message;
import org.scrum.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="/scrum")
	public String homePage() {
		return "homePage";
	}

	public void projects(Model model, String username) {
		Set<backlog> backlogs=br.FindByUser(username);
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	@RequestMapping(value="/index")
	public String index(Model model,Model modell, Principal principal) {
		String username = principal.getName();
		this.projects(model,username);
		user u=ur.FindByUsername(username);
		
		if(u.getRole()==Role.client)
		{
			Set<backlog> backlogs=br.FindByUser(username);
			modell.addAttribute("Backlogs", backlogs);
			return "indexClient";
		}
		else if(u.getRole()==Role.administrateur) {
			return "indexAdmin";
			
		}else return "index";
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
	
	
	@RequestMapping(value="/discutionPO")
	public String discutionPO(Model model, Model mo, String projectname,Principal principal) {
		String username = principal.getName();
		this.projects(model, username);
		backlog b = br.FindByProjectname(projectname);
		Set<message> messages= mr.FindMessageByPO(b.getIdBacklog());
	    model.addAttribute("listMessages", messages);
	    backlog b1 = br.FindByProjectname(projectname);
		mo.addAttribute("pjt", b1);
		return "discutionPO";
		
	}
	
	@RequestMapping(value="/discutionC")
	public String discutionC(Model model,Model mo, Model mod, String username, String projectname) {
		
		user u=ur.FindByUsername(username);
		Set<message> messages= mr.FindMessageById(u.getId());
	    model.addAttribute("listMessages", messages);
	    backlog b = br.FindByProjectname(projectname);
		mo.addAttribute("pjt", b);
		return "discution";
		
	}
	
	@RequestMapping(value="/SendMessage", method=RequestMethod.POST)
	public String SendMessage(Model model,String username, @Valid message message, BindingResult bindingResult, String projectname) {
		
		
		user u1=ur.FindByUsername(username);
		Date date = new Date();
		int type;
		if(u1.getRole()==Role.client) {
			type=1;
				user u = ur.FindPo(projectname);
				user u2 = ur.FindC(projectname);
				backlog backlog = br.FindByProjectname(projectname);
				mr.save(new message(message.getContenu(), date , u2.getId(), backlog.getIdBacklog(), u.getId(), u1.getRole(), type));
			return "redirect:/discutionC?username="+username+"&projectname="+projectname;
		}
		else {
			type=0;
			user u = ur.FindPo(projectname);
			user u2 = ur.FindC(projectname);
			backlog backlog = br.FindByProjectname(projectname);
			mr.save(new message(message.getContenu(), date , u2.getId(),backlog.getIdBacklog(), u.getId(), u1.getRole(), type));
		
		return "redirect:/discutionPO?projectname="+projectname;
		
		}
	}

	
}
