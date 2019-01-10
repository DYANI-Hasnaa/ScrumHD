package org.scrum.web;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.scrum.dao.BacklogRepository;
import org.scrum.dao.ItemRepository;
import org.scrum.dao.UserRepository;
import org.scrum.entities.Item;
import org.scrum.entities.backlog;
import org.scrum.entities.sprint;
import org.scrum.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private UserRepository ur;
	
	public void projects(Model model, String username) {
		Set<backlog> backlogs=br.FindByUser(username);
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	
	@RequestMapping(value="/CreateItem", method=RequestMethod.GET)
	public String CreateItem(Model model,Model modell, Model mod, String projectname, String username) {
		
		this.projects(model, username);
		
		backlog b=br.FindByProjectname(projectname);
		
		model.addAttribute("backlog",b);
		
		List<Item> items=ir.FindItemByBacklog(b);
		
	    model.addAttribute("listItems", items);
			
		return "CreateItem";
		
	}
	
	
    @RequestMapping(value="/CreateNewItem", params = "btn2", method=RequestMethod.POST)
	public String CreateNewItem(Model model , Model mod, Model modell,@Valid Item item,backlog backlog, String username, BindingResult bindingResult) {
		
		this.projects(model, username);
		
		List<Item> items=ir.FindItemByBacklog(backlog);
		
		mod.addAttribute("listItems", items);
		
		if(bindingResult.hasErrors())
			return "CreateItem";
		
		ir.save(new Item(item.getName(), item.getImportance(), item.getDays(), item.getNote(), item.getRequestedOn(), item.getStatus(), backlog));
		
	    mod.addAttribute("listItems", items);
		
		model.addAttribute("backlog",backlog);
		
		return "CreateSprint";
		
	}
	
	@RequestMapping(value="/CreateNewItem",params = "btn1", method=RequestMethod.POST)
	public String CreateNewItem1(Model model, Model modell, Model mod,@Valid Item item,backlog backlog, String username, BindingResult bindingResult) {
		
		this.projects(model, username);
		
		List<Item> items=ir.FindItemByBacklog(backlog);
		
	    mod.addAttribute("listItems", items);
		
		if(bindingResult.hasErrors())
			return "CreateItem";
		
		ir.save(new Item(item.getName(), item.getImportance(), item.getDays(), item.getNote(), item.getRequestedOn(), item.getStatus(), backlog));
		
		List<Item> itemss=ir.FindItemByBacklog(backlog);
	    mod.addAttribute("listItems", itemss);
		
		return "CreateItem";
		
	}
	
	@RequestMapping(value="/Allitems")
	public String Allitems(Model model, Model modell,Model mod ,Model moda, String projectname, String username) {
		
		this.projects(model, username);
		backlog b=br.FindByProjectname(projectname);
		
		
		mod.addAttribute("backlog",b);
		List<Item> items=ir.FindItemByBacklog(b);
	    modell.addAttribute("listItems", items);
	    List<user> usr = ur.FindTeam(projectname);
	    moda.addAttribute("listTeam", usr);
	    return "Allitems";
	}

	@RequestMapping(value="/deleteItem", method=RequestMethod.GET)
	public String deleteItem(Model model,Model mod, Long idItem, String projectname, String username) {
		
		this.projects(model, username);
		ir.deleteById(idItem);
		return "redirect:/Allitems?projectname="+projectname+"&username="+username;
		
	}
	
	@RequestMapping(value="/editItem", method=RequestMethod.GET)
	public String editItem(Model model,Model modell, Long idItem, String projectname, String username) {
		
		this.projects(model, username);
		Optional<Item> i = ir.findById(idItem);
		modell.addAttribute("item",i.get());
		backlog b=br.FindByProjectname(projectname);
		modell.addAttribute("backlog",b);
		return "editItem";
		
	}
	
	@RequestMapping(value="/editItemItem", params = "btns2", method=RequestMethod.POST)
	public String editItemItem(Model model, Item Item ,backlog backlog, sprint sprint, BindingResult bindingResult, Long idItem, String username) {
		
		this.projects(model,username);
		
		System.out.println(idItem);
		
		if(bindingResult.hasErrors())
			return "CreateSprint";
		
		Optional<Item> i=ir.findById(idItem);
		
		i.get().setDays(Item.getDays());
		i.get().setImportance(Item.getImportance());
		i.get().setName(Item.getName());
		i.get().setNote(Item.getNote());
		i.get().setRequestedOn(Item.getRequestedOn());
		i.get().setStatus(Item.getStatus());
		i.get().setBacklog(backlog);
		ir.save(i.get());
		
		Optional<backlog> b = br.findById(backlog.getIdBacklog());
		
		return "redirect:/Allitems?projectname="+b.get().getProjectname()+"&username="+username;
	}
	
	@RequestMapping(value="/AffectItem", method=RequestMethod.GET)
	public String AffectItem(Model model,Model mod, Long idItem, Long id,String projectname, String username) {
		
		this.projects(model, username);
		Optional<user> user = ur.findById(id);
		Optional<Item> item = ir.findById(idItem);
		item.get().setUser(user.get());
		String affect = "Affected";
		item.get().setAffect(affect);
		ir.save(item.get());
		return "redirect:/Allitems?projectname="+projectname+"&username="+username;
		
	}
}
