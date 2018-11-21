package org.scrum.web;

import org.springframework.stereotype.Controller;

import java.util.List;

import javax.validation.Valid;

import org.scrum.dao.BacklogRepository;
import org.scrum.dao.ItemRepository;
import org.scrum.entities.Item;
import org.scrum.entities.backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ItemController {
	
	

	@Autowired
	private BacklogRepository br;
	
	@Autowired
	private ItemRepository ir;
	
	
	public void projects(Model model) {
		List<backlog> backlogs=br.findAll();
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	
	@RequestMapping(value="/CreateItem", method=RequestMethod.GET)
	public String CreateItem(Model model, Model mod, Model modell,String projectname) {
		
		this.projects(model);
		
		backlog b=br.FindByProjectname(projectname);
		
		model.addAttribute("backlog",b);
		
		List<Item> items=ir.FindItemByBacklog(b);
		
	    model.addAttribute("listItems", items);
		
		System.out.println(b.getBacklogname());
			
		return "CreateItem";
		
	}
	
	
    @RequestMapping(value="/CreateNewItem", params = "btn2", method=RequestMethod.POST)
	public String CreateNewItem(Model model, Model mod, Model modell,@Valid Item item,backlog backlog, BindingResult bindingResult) {
		
		this.projects(model);
		
		this.items(modell);
		
		if(bindingResult.hasErrors())
			return "CreateItem";
		
		ir.save(new Item(item.getName(), item.getImportance(), item.getDays(), item.getNote(), item.getRequestedOn(), item.getStatus(), backlog));
		
		List<Item> items=ir.FindItemByBacklog(backlog);
		
	    mod.addAttribute("listItems", items);
		
		model.addAttribute("backlog",backlog);
		
		return "CreateSprint";
		
	}
	
	@RequestMapping(value="/CreateNewItem",params = "btn1", method=RequestMethod.POST)
	public String CreateNewItem1(Model model,Model mod, Model modell,@Valid Item item,backlog backlog, BindingResult bindingResult) {
		
		this.projects(model);
		
		this.items(modell);
		
		if(bindingResult.hasErrors())
			return "CreateItem";
		
		ir.save(new Item(item.getName(), item.getImportance(), item.getDays(), item.getNote(), item.getRequestedOn(), item.getStatus(), backlog));
		
		List<Item> items=ir.FindItemByBacklog(backlog);
		
	    mod.addAttribute("listItems", items);
		
		return "CreateItem";
		
	}
	
	public void items(Model model) {
		List<Item> items=ir.findAll();
	    model.addAttribute("listItems", items);
	}
	
	


}
