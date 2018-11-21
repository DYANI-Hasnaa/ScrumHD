package org.scrum.web;

import java.util.List;

import javax.validation.Valid;

import org.scrum.dao.BacklogRepository;
import org.scrum.dao.ItemRepository;
import org.scrum.dao.SprintRepository;
import org.scrum.entities.sprint;
import org.scrum.entities.Item;
import org.scrum.entities.backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SprintController {
	
	

	@Autowired
	private BacklogRepository br;
	
	@Autowired
	private SprintRepository sr;
	
	@Autowired
	private ItemRepository it;
	
	
	
	public void projects(Model model) {
		List<backlog> backlogs=br.findAll();
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	
	@RequestMapping(value="/CreateSprint", method=RequestMethod.GET)
	public String CreateSprint(Model model) {
		
		this.projects(model);
		
		return "CreateSprint";
		
	}
	
	
	@RequestMapping(value="/CreateNewSprint", params = "btns1", method=RequestMethod.POST)
	public String CreateNewSprint(Model model, sprint sprint,BindingResult bindingResult) {
		
		this.projects(model);
		
		if(bindingResult.hasErrors())
			return "CreateSprint";
		
		sr.save(sprint);

		return "CreateSprint";
	}
	

	
	@RequestMapping(value="/CreateNewSprint", params = "btns2", method=RequestMethod.POST)
	public String CreateNewSprint1(Model model,Model modell, Model mod, sprint sprint,backlog backlog, BindingResult bindingResult) {
		
		this.projects(model);
		
		if(bindingResult.hasErrors())
			return "CreateSprint";
		
		sr.save(new sprint(sprint.getNamesprint(), sprint.getDescriptionsprint(), sprint.getRequestedOnsprint(), sprint.getStatussprint(), backlog));

		//this.items(modell);
		
		//return "SprintBoard";
		
		List<sprint> sprints=sr.FindSprintByBacklog(backlog);
		
	    modell.addAttribute("listSprints", sprints); 
	    
	    mod.addAttribute("backlog", backlog); 
		
		
		return "AllSprints";
	}
	
	public void items(Model model) {
		List<Item> items=it.findAll();
	    model.addAttribute("listItems", items);
	}
	
	
	public void sprints(Model model) {
		
		List<sprint> sprints=sr.findAll();
	    model.addAttribute("listSprints", sprints);    
	    
	}
	
	

	@RequestMapping(value="/sprintBoard", method=RequestMethod.GET)
	public String sprintBoard(Model model,Model mod, Model modell,String namesprint, String projectname) {
		
		this.projects(model);
		
		sprint s=sr.FindBySprintName(namesprint);
		
		model.addAttribute("sprint",s);
		
		System.out.println(s.getNamesprint());
		
		backlog b=br.FindByProjectname(projectname);
		
		mod.addAttribute("backlog", b);
		
		List<Item> items=it.FindItemByBacklog(b);
	    modell.addAttribute("listItems", items);
		
		return "SprintBoard";
		
	}
	
	
	
	@RequestMapping(value="/allSprints", method=RequestMethod.GET)
	public String allSprints(Model model, Model mod,String projectname) {
		
		
		this.projects(model);
		
		backlog b=br.FindByProjectname(projectname);
		
		
		mod.addAttribute("b",b);
		
		
		List<sprint> sprints=sr.FindSprintByBacklog(b);
		
	    model.addAttribute("listSprints", sprints);   
		
		
		return "AllSprints";
	    
	    
	    
	}
	
	
	
	
	

	@RequestMapping(value="/SprintTodo", method=RequestMethod.GET)
	public String SprintTodo(Model model, Model modelll,Model modell,String namesprint, String name) {
		
		this.projects(model);
		
		sprint s=sr.FindBySprintName(namesprint);
		
		Item i=it.FindByItemName(name);
		
		s.setStatussprint("Is Doing");
	
		i.setStatus("In Progress");
		
		it.save(i);
		sr.save(s);
		
		modelll.addAttribute("sprint", s);
		
		this.items(modell);
		
		return "SprintBoard";
		
	}
	
	
	
	
}
