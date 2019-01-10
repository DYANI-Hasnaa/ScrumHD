package org.scrum.web;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.scrum.dao.BacklogRepository;
import org.scrum.dao.ItemRepository;
import org.scrum.dao.UserRepository;
import org.scrum.entities.Item;
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
    private ItemRepository it;

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
		
		br.save(new backlog(backlog.getProjectname(), backlog.getDatecreation(), backlog.getBacklogdescription(), backlog.getSprintduration(), u));
		
		this.projects(model, username);
		
		return "ProjectDetails";
		
	}
	
	@RequestMapping(value="/showProject", method=RequestMethod.GET)
	public String showProject(Model model,String projectname, String username) {
		
		this.projects(model, username);
		
		backlog b=br.FindByProjectname(projectname);
		
		model.addAttribute("backlog",b);
		
		
		return "ProjectDetails";
		
	}
	
	@RequestMapping(value="/backlog", method=RequestMethod.GET)
	public String backlog(Model model,Model modell,Model modelll,String username, String projectname) {
		
		this.projects(model, username);
		user user = ur.FindByUsername(username);
		backlog backlog=br.FindByProjectname(projectname);
		
		List<Item> itemsTodo=(List<Item>) it.findBacklogTodo(backlog);
	    modelll.addAttribute("listItemsTodo", itemsTodo);
		List<Item> itemsIn=(List<Item>) it.findBacklogIn(backlog);
	    modelll.addAttribute("listItemsIn", itemsIn);
		List<Item> itemsDone=(List<Item>) it.findBacklogDone(backlog);
	    modelll.addAttribute("listItemsDone", itemsDone);
		
	    
		return "backlog";
		
	}
}
