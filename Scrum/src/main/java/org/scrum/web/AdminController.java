package org.scrum.web;

import java.util.List;

import org.scrum.dao.BacklogRepository;
import org.scrum.dao.ItemRepository;
import org.scrum.dao.SprintRepository;
import org.scrum.dao.UserRepository;
import org.scrum.entities.Item;
import org.scrum.entities.backlog;
import org.scrum.entities.sprint;
import org.scrum.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	@Autowired
	private BacklogRepository br;
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private SprintRepository sr;
	
	@Autowired
	private ItemRepository ir;
	
	public void projects(Model model) {
		List<backlog> backlogs=br.findAll();
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	@RequestMapping(value="/indexAdmin", method=RequestMethod.GET)
	public String indexAdmin(Model model) {
		
		this.projects(model);
		return "indexAdmin";
	}
	
	@RequestMapping(value="/AdminTeam", method=RequestMethod.GET)
	public String AdminTeam(Model model, Model model1, Model model2, Model model3, Model model4) {
		
		this.projects(model);
		List<user> productOwner=ur.FindPO();
	    model1.addAttribute("listPO",productOwner);
		
	    List<user> scrumMaster=ur.FindSM();
	    model1.addAttribute("listSM",scrumMaster);
	    
	    List<user> clients=ur.Findclients();
	    model1.addAttribute("listCL",clients);
	    
	    List<user> members=ur.FindMembers();
	    model1.addAttribute("listM",members);
	    
		return "AdminTeam";
	}
	
	@RequestMapping(value="/AdminTeamProjet", method=RequestMethod.GET)
	public String AdminTeamProjet(Model model, Model model1, Model model2, Model model3, Model model4, String projectname) {
		
		this.projects(model);
		List<user> productOwner=ur.FindP(projectname);
	    model1.addAttribute("listPO",productOwner);
		
	    List<user> scrumMaster=ur.FindS(projectname);
	    model1.addAttribute("listSM",scrumMaster);
	    
	    List<user> clients=ur.Findclient(projectname);
	    model1.addAttribute("listCL",clients);
	    
	    List<user> members=ur.FindTeam(projectname);
	    model1.addAttribute("listM",members);
	    
		return "AdminTeamProjet";
	}
	
	@RequestMapping(value="/AdminSprints", method=RequestMethod.GET)
	public String AdminSprints(Model model,Model mod,Model modell, String projectname) {
			
		this.projects(model);
		backlog b=br.FindByProjectname(projectname);
		mod.addAttribute("backlog",b);
		List<sprint> sprints=sr.FindSprintByBacklog(b);
		modell.addAttribute("listSprints", sprints);
		
		return "AdminSprints";
	}
	
	@RequestMapping(value="/AdminItems", method=RequestMethod.GET)
	public String AdminItems(Model model,Model mod,Model modell, String projectname) {
			
		this.projects(model);
		backlog b=br.FindByProjectname(projectname);
		mod.addAttribute("backlog",b);
		List<Item> items=ir.FindItemByBacklog(b);
	    modell.addAttribute("listItems", items);
		return "AdminItems";
	}
}
