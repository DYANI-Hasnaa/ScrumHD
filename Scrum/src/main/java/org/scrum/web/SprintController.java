package org.scrum.web;

import java.util.List;

import javax.validation.Valid;

import java.util.Optional;

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
	

	
	public void items(Model model) {
		List<Item> items=it.findAll();
	    model.addAttribute("listItemsBacklog", items);
	}
	
	
	public void sprints(Model model) {
		
		List<sprint> sprints=sr.findAll();
	    model.addAttribute("listSprints", sprints);    
	    
	}
	
	

	
	
	@RequestMapping(value="/AllSprints", method=RequestMethod.GET)
	public String AllSprints(Model model, Model mod,String projectname) {
		
		
		this.projects(model);
		
		backlog b=br.FindByProjectname(projectname);
		
		
		mod.addAttribute("backlog",b);
		
		
		List<sprint> sprints=sr.FindSprintByBacklog(b);
		
	    model.addAttribute("listSprints", sprints);   
		
		
		return "AllSprints";
	    
	    
	    
	}
	
	
	
	
	
	
	

	@RequestMapping(value="/SprintItemDo", method=RequestMethod.GET)
	public String SprintItemDo(String name , String namesprint) {
		
		sprint s=sr.FindBySprintName(namesprint);
		Item i=it.FindByItemName(name);
		
		String status = "To do";
		i.setSprint(s);
		i.setStatus(status);
		it.save(i);
		
		return "redirect:/sprintBoard?namesprint="+namesprint;
		
	}
	
	@RequestMapping(value="/SprintItemIn", method=RequestMethod.GET)
	public String SprintItemIn(String name , String namesprint) {
		
		sprint s=sr.FindBySprintName(namesprint);
		Item i=it.FindByItemName(name);
		
		String status = "In progress";
		i.setSprint(s);
		i.setStatus(status);
		it.save(i);
		
		return "redirect:/sprintBoard?namesprint="+namesprint;
		
	}
	
	@RequestMapping(value="/SprintItemDone", method=RequestMethod.GET)
	public String SprintItemDone(String name , String namesprint) {
		
		sprint s=sr.FindBySprintName(namesprint);
		Item i=it.FindByItemName(name);
		
		String status = "Done";
		i.setSprint(s);
		i.setStatus(status);
		it.save(i);
		
		return "redirect:/sprintBoard?namesprint="+namesprint;
		
	}
	
	@RequestMapping(value="/SprintItemBacklog", method=RequestMethod.GET)
	public String SprintItemBacklog(String name , String namesprint) {
		
		sprint s=sr.FindBySprintName(namesprint);
		Item i=it.FindByItemName(name);
		
		String status = "To do";
		i.setSprint(null);
		i.setStatus(status);
		it.save(i);
		
		return "redirect:/sprintBoard?namesprint="+namesprint;
		
	}
	
	@RequestMapping(value="/editSprint", method=RequestMethod.GET)
	public String editSprint(Model model,Model modell, Long idSprint) {
		
		this.projects(model);
		Optional<sprint> s = sr.findById(idSprint);
		modell.addAttribute("sprint",s.get());
		return "editSprint";
		
	}
	
	@RequestMapping(value="/deleteSprint", method=RequestMethod.GET)
	public String deleteSprint(Model model,Model mod, Long idSprint, String projectname) {
		
		this.projects(model);
		sr.deleteById(idSprint);
		
		backlog b=br.FindByProjectname(projectname);
		
		mod.addAttribute("backlog", b);
		
		return "redirect:/AllSprints?projectname="+projectname;
		
	}
	
	
	
	
	
	
	
	
	
	/*@RequestMapping(value="/CreateSprint", method=RequestMethod.GET)
	public String CreateSprint(Model model) {
		
		this.projects(model);
		
		return "CreateSprint";
		
	}*/
	
	@RequestMapping(value="/CreateSprint", method=RequestMethod.GET)
	public String CreateSprint(Model model,Model mod, String projectname) {
		
		this.projects(model);
		

		backlog b=br.FindByProjectname(projectname);
		
		mod.addAttribute("backlog", b);
		
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
	public String CreateNewSprint1(Model model,Model modell, Model mod, Model mo, sprint sprint,backlog backlog, BindingResult bindingResult) {
		
		this.projects(model);
		
		if(bindingResult.hasErrors())
			return "CreateSprint";
		
		sr.save(new sprint(sprint.getNamesprint(), sprint.getDescriptionsprint(), sprint.getRequestedOnsprint(), sprint.getStatussprint(), backlog));

		
		
		List<sprint> sprints=sr.FindSprintByBacklog(backlog);
		
	    modell.addAttribute("listSprints", sprints); 
	    
	    mod.addAttribute("backlog", backlog); 
	    
	    System.out.println("\n\n\n\n"+backlog.getProjectname());
		
		
		return "AllSprints";
	}
	
	
	
	
	
	

	@RequestMapping(value="/sprintBoard", method=RequestMethod.GET)
	public String sprintBoard(Model model, Model mod, Model mo,Model modell,Model modelll,String namesprint, String projectname) {
		
		this.projects(model);
		
		sprint s=sr.FindBySprintName(namesprint);
		
		model.addAttribute("sprint",s);
		
		List<Item> itemsTodo=(List<Item>) it.findAllSprintTodo(s);
	    modelll.addAttribute("listItemsTodo", itemsTodo);
		List<Item> itemsIn=(List<Item>) it.findAllSprintIn(s);
	    modelll.addAttribute("listItemsIn", itemsIn);
		List<Item> itemsDone=(List<Item>) it.findAllSprintDone(s);
	    modelll.addAttribute("listItemsDone", itemsDone);
		
		backlog b=br.FindByProjectname(projectname);
		
		mod.addAttribute("backlog", b);
		
		List<Item> items=it.FindItemBacklog(b);
		mo.addAttribute("listItemsBacklog", items);
		
		return "SprintBoard";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}






























