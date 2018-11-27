package org.scrum.web;

import java.util.List;
import java.util.Optional;

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
	public String CreateSprint(Model model, String projectname) {
		
		this.projects(model);
		backlog b=br.FindByProjectname(projectname);
		model.addAttribute("backlog",b);
		return "CreateSprint";
		
	}
	
	
	@RequestMapping(value="/CreateNewSprint", params = "btns1", method=RequestMethod.POST)
	public String CreateNewSprint(Model model, Model mod, sprint sprint,backlog backlog, BindingResult bindingResult) {
		
		this.projects(model);
		
		if(bindingResult.hasErrors())
			return "CreateSprint";
		
		sr.save(new sprint(sprint.getNamesprint(), sprint.getDescriptionsprint(), sprint.getRequestedOnsprint(), sprint.getStatussprint(), backlog));
	    
	    mod.addAttribute("backlog", backlog);
		
		return "CreateSprint";
	}
	

	
	@RequestMapping(value="/CreateNewSprint", params = "btns2", method=RequestMethod.POST)
	public String CreateNewSprint1(Model model,Model modell,Model mod, sprint sprint ,backlog backlog, BindingResult bindingResult) {
		
		this.projects(model);
		
		if(bindingResult.hasErrors())
			return "CreateSprint";
		
		sr.save(new sprint(sprint.getNamesprint(), sprint.getDescriptionsprint(), sprint.getRequestedOnsprint(), sprint.getStatussprint(), backlog));
		
		Optional<backlog> b = br.findById(backlog.getIdBacklog());
		
		return "redirect:/AllSprints?projectname="+b.get().getProjectname();
	}
	
	

	@RequestMapping(value="/sprintBoard", method=RequestMethod.GET)
	public String sprintBoard(Model model,Model modell,Model modelll,String namesprint, String projectname) {
		
		this.projects(model);
		
		sprint s=sr.FindBySprintName(namesprint);
		
		model.addAttribute("sprint",s);
		
		backlog b=br.FindByProjectname(projectname);
		
		List<Item> itemsTodo=(List<Item>) it.findAllSprintTodo(s,b);
	    modelll.addAttribute("listItemsTodo", itemsTodo);
		List<Item> itemsIn=(List<Item>) it.findAllSprintIn(s,b);
	    modelll.addAttribute("listItemsIn", itemsIn);
		List<Item> itemsDone=(List<Item>) it.findAllSprintDone(s,b);
	    modelll.addAttribute("listItemsDone", itemsDone);
		
	    List<Item> itemsBacklog=it.FindItemBacklog(b);
	    model.addAttribute("listItemsBacklog", itemsBacklog);
	    model.addAttribute("backlog", b);
	    
	    String r = "hhhh";
		model.addAttribute("error",r);
	    
		return "SprintBoard";
		
	}
	
	
	@RequestMapping(value="/SprintItemDo", method=RequestMethod.GET)
	public String SprintItemDo(String name , String namesprint, String projectname) {
		
		sprint s=sr.FindBySprintName(namesprint);
		Item i=it.FindByItemName(name);
		backlog b=br.FindByProjectname(projectname);
		List<Item> items=(List<Item>) it.FindSum(s);
	   
		int somme=0;
		
		for(Item ii: items)
		{		
			somme+=ii.getDays();	
		}	
		
		String status = "To do";
		
		System.out.println(somme);
		
		if (somme + i.getDays() > b.getSprintduration()) {
	
			return "redirect:/sprintBoard?namesprint="+namesprint+"&projectname="+projectname;	
			
		}else {
			
			i.setSprint(s);
			i.setStatus(status);
			it.save(i);
			return "redirect:/sprintBoard?namesprint="+namesprint+"&projectname="+projectname;
		}
		
	}
	
	@RequestMapping(value="/SprintItemIn", method=RequestMethod.GET)
	public String SprintItemIn(String name , String namesprint, String projectname) {
		
		sprint s=sr.FindBySprintName(namesprint);
		Item i=it.FindByItemName(name);
		
		String status = "In progress";
		i.setSprint(s);
		i.setStatus(status);
		it.save(i);
		
		return "redirect:/sprintBoard?namesprint="+namesprint+"&projectname="+projectname;
		
	}
	
	@RequestMapping(value="/SprintItemDone", method=RequestMethod.GET)
	public String SprintItemDone(String name , String namesprint, String projectname) {
		
		sprint s=sr.FindBySprintName(namesprint);
		Item i=it.FindByItemName(name);
		
		String status = "Done";
		i.setSprint(s);
		i.setStatus(status);
		it.save(i);
		
		return "redirect:/sprintBoard?namesprint="+namesprint+"&projectname="+projectname;
		
	}
	
	@RequestMapping(value="/SprintItemBacklog", method=RequestMethod.GET)
	public String SprintItemBacklog(String name , String namesprint, String projectname) {
		
		sprint s=sr.FindBySprintName(namesprint);
		Item i=it.FindByItemName(name);
		
		String status = null;
		i.setSprint(null);
		i.setStatus(status);
		it.save(i);
		
		return "redirect:/sprintBoard?namesprint="+namesprint+"&projectname="+projectname;
		
	}
	
	@RequestMapping(value="/editSprint", method=RequestMethod.GET)
	public String editSprint(Model model,Model modell, Long idSprint, String projectname) {
		
		this.projects(model);
		Optional<sprint> s = sr.findById(idSprint);
		modell.addAttribute("sprint",s.get());
		backlog b=br.FindByProjectname(projectname);
		modell.addAttribute("backlog",b);
		return "editSprint";
		
	}
	
	@RequestMapping(value="/editSprintSprint", params = "btns2", method=RequestMethod.POST)
	public String editSprintSprint(Model model, sprint sprint ,backlog backlog, BindingResult bindingResult, Long idSprint) {
		
		this.projects(model);
		
		System.out.println(idSprint);
		
		if(bindingResult.hasErrors())
			return "CreateSprint";
		
		Optional<sprint> s=sr.findById(idSprint);
		
		s.get().setDescriptionsprint(sprint.getDescriptionsprint());
		s.get().setNamesprint(sprint.getNamesprint());
		s.get().setRequestedOnsprint(sprint.getRequestedOnsprint());
		s.get().setStatussprint(sprint.getStatussprint());
		s.get().setBacklog(backlog);
		sr.save(s.get());
		
		Optional<backlog> b = br.findById(backlog.getIdBacklog());
		
		return "redirect:/AllSprints?projectname="+b.get().getProjectname();
	}
	
	@RequestMapping(value="/deleteSprint", method=RequestMethod.GET)
	public String deleteSprint(Model model,Model mod, Long idSprint, String projectname) {
		
		this.projects(model);
		sr.deleteById(idSprint);
		return "redirect:/AllSprints?projectname="+projectname;
		
	}
	
	@RequestMapping(value="/AllSprints")
	public String AllSprints(Model model, Model modell,Model mod, String projectname) {
		
		this.projects(model);
		backlog b=br.FindByProjectname(projectname);
		
		
		mod.addAttribute("backlog",b);
		List<sprint> sprints=sr.FindSprintByBacklog(b);
	    modell.addAttribute("listSprints", sprints);
	    return "AllSprints";
	}
	
}
