package org.scrum.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.scrum.dao.BacklogRepository;
import org.scrum.dao.UserRepository;
import org.scrum.entities.backlog;
import org.scrum.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BacklogController {
	
	
	

	@Autowired
	private BacklogRepository br;
	
	@Autowired
	private UserRepository ur;
	
	public void projects(Model model, String username) {
		Set<backlog> backlogs=br.FindByUser(username);
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	
	@RequestMapping(value="/CreatePjtBlog", method=RequestMethod.GET)
	public String CreatePjtBlog(Model model, String username) {
		
		this.projects(model, username);
		return "CreatePjtBlog";
		
	}
	
	
	@RequestMapping(value="/CreateBacklog", method=RequestMethod.POST)
	public String CreateBacklog(Model model,@Valid backlog backlog,String username, BindingResult bindingResult) {
		
		this.projects(model, username);
		
		if(bindingResult.hasErrors())
			return "CreatePjtBlog";
		
		Set<user> u = ur.FindByUsernameBacklogs(username);
		
		br.save(new backlog(backlog.getProjectname(), backlog.getBacklogname(), backlog.getDatecreation(), backlog.getBacklogdescription(), backlog.getSprintduration(), u));
		
		this.projects(model, username);
		
		return "ProjectDetails";
		
	}
	
	@RequestMapping(value="/showProject", method=RequestMethod.GET)
	public String showProject(Model model,String projectname, String username) {
		
		this.projects(model, username);
		
		backlog b=br.FindByProjectname(projectname);
		
		model.addAttribute("backlog",b);
		
		System.out.println(b.getBacklogname());
		
		return "ProjectDetails";
		
	}
}
