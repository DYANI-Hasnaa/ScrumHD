package org.scrum.web;

import java.util.ArrayList;
import java.util.List;

import org.scrum.dao.SprintRepository;
import org.scrum.entities.sprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chart")
public class ChartController {
	
	@Autowired
	private SprintRepository sr;
	
	@RequestMapping(method = RequestMethod.GET)
	public String chart(ModelMap model) {
		return "hh";
	}
	@RequestMapping(value = "/getProducts", method = RequestMethod.GET)
	public @ResponseBody List<sprint> getProducts() {
		List<sprint> productList = new ArrayList<sprint>();
		List<sprint> products = sr.FindSprint();
		for (sprint s : products) {
			productList.add(s);
		}
	    	  
		return productList;
	}
}