package org.scrum.web;

import java.util.List;

import javax.validation.Valid;

import org.scrum.dao.BacklogRepository;
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
	
	
	public void projects(Model model) {
		List<backlog> backlogs=br.findAll();
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	
	@RequestMapping(value="/CreatePjtBlog", method=RequestMethod.GET)
	public String CreatePjtBlog(Model model) {
		
		this.projects(model);
		return "CreatePjtBlog";
		
	}
	
	
	@RequestMapping(value="/CreateBacklog", method=RequestMethod.POST)
	public String CreateBacklog(Model model,@Valid backlog backlog, BindingResult bindingResult) {
		
		this.projects(model);
		
		if(bindingResult.hasErrors())
			return "CreatePjtBlog";
		
		br.save(backlog);
		
		this.projects(model);
		
		return "ProjectDetails";
		
	}
	
	@RequestMapping(value="/showProject", method=RequestMethod.GET)
	public String showProject(Model model,String projectname) {
		
		this.projects(model);
		
		backlog b=br.FindByProjectname(projectname);
		
		model.addAttribute("backlog",b);
		
		System.out.println(b.getBacklogname());
		
		return "ProjectDetails";
		
	}
	
	
	
	
	

	
	

}
