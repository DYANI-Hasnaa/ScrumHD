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
	public String CreateNewSprint(Model model, sprint sprint, BindingResult bindingResult) {
		
		this.projects(model);
		
		if(bindingResult.hasErrors())
			return "CreateSprint";
		
		sr.save(sprint);

		return "CreateSprint";
	}
	

	
	@RequestMapping(value="/CreateNewSprint", params = "btns2", method=RequestMethod.POST)
	public String CreateNewSprint1(Model model, sprint sprint, BindingResult bindingResult) {
		
		this.projects(model);
		
		if(bindingResult.hasErrors())
			return "CreateSprint";
		
		sr.save(sprint);

		return "SprintBoard";
	}
}
