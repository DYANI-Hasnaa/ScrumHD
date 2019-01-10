package org.scrum.web;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TeamController {
    
	@Autowired
	private UserRepository ur;
    
    @Autowired
    private BacklogRepository br;
    
    @Autowired
    private ItemRepository it;

    public void projects(Model model, String username) {
		Set<backlog> backlogs=br.FindByUser(username);
	    model.addAttribute("listBacklogs", backlogs);
	}
    
    
    @RequestMapping(value="/AddTeam1", method=RequestMethod.GET)
    public String AddTeam1(Model model, Model model2, String username) {
        
        this.projects(model, username);
        model2.addAttribute("user",new user());
        return "AddTeam";
        
    }
    
    private void sendmail(String email, String projectname, String role)throws AddressException, MessagingException, IOException{
        
           Properties props = new Properties();
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.starttls.enable", "true");
           props.put("mail.smtp.host", "smtp.gmail.com");
           props.put("mail.smtp.port", "587");
          
           Session session = Session.getInstance(props, new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication("test@gmail.com","****");
              }
           });
           Message msg = new MimeMessage(session);
           msg.setFrom(new InternetAddress("test@gmail.com",false));

           msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
           msg.setSubject("Member of Scrum HD");
           msg.setContent("U are memeber of "+projectname+" As "+role+" u can access by clicking the link bellow :"
           		+ "\n http://localhost:8086/inviteTeam?email="+email ,"text/html");
           msg.setSentDate(new Date());

           Transport.send(msg);
           
        }
    
    @RequestMapping(value="/inviteTeam", method=RequestMethod.GET)
    public String inviteTeam(Model model, String email) {
		
		user u=ur.FindByEmail(email);
		model.addAttribute("user",u);
        return "inviteTeam";
        
    }
    
    @RequestMapping(value = "/sendemail", params = "btns2", method=RequestMethod.POST)
    public String sendEmail(Model model, String email, String projectname, String role, @Valid user user)throws AddressException, MessagingException, IOException{
    	String username = user.getUsername();
    	this.projects(model, username);
    	sendmail(email, projectname, role);
    	
    	Set<backlog> b=br.FindByProjectnameBacklogs(projectname);
    	for (backlog a : b) {
			a.getUser().add(user);
			br.save(a);
		}
       return "index";
    }
    
    @RequestMapping(value = "/sendemail", params = "btns1", method=RequestMethod.POST)
    public String sendEmail1(Model model, String email, String projectname, String role, @Valid user user , Principal principal)throws AddressException, MessagingException, IOException{
    	String username = user.getUsername();
    	this.projects(model, username);
    	sendmail(email, projectname, role);
    	
    	Set<backlog> b=br.FindByProjectnameBacklogs(projectname);
    	for (backlog a : b) {
    		a.getUser().add(user);
			br.save(a);
		}
    	
    	String username1 = principal.getName();
    	
       return "redirect:/AddTeam1?username="+username1;
    }
    
    @RequestMapping(value="/MyTasks", method=RequestMethod.GET)
	public String MyTasks(Model model,Model modell,Model modelll,String username) {
		
		this.projects(model, username);
		user user = ur.FindByUsername(username);
		
		List<Item> itemsTodo=(List<Item>) it.findAllitemTodo(user);
	    modelll.addAttribute("listItemsTodo", itemsTodo);
		List<Item> itemsIn=(List<Item>) it.findAllitemIn(user);
	    modelll.addAttribute("listItemsIn", itemsIn);
		List<Item> itemsDone=(List<Item>) it.findAllitemDone(user);
	    modelll.addAttribute("listItemsDone", itemsDone);
		
	    
		return "MyTasks";
		
	}
    
    @RequestMapping(value="/ItemDo", method=RequestMethod.GET)
	public String ItemDo(Model model, String name , String username,RedirectAttributes redirAttrs) {
		
		Item i=it.FindByItemName(name);
		String status = "To do";
		i.setStatus(status);
		it.save(i);
		return "redirect:/MyTasks?username="+username;
		
	}
	
	
	@RequestMapping(value="/ItemIn", method=RequestMethod.GET)
	public String ItemIn(String name , String username) {
		
		Item i=it.FindByItemName(name);
		String status = "In progress";
		i.setStatus(status);
		it.save(i);
		
		return "redirect:/MyTasks?username="+username;
		
	}
	
	@RequestMapping(value="/ItemDone", method=RequestMethod.GET)
	public String ItemDone(String name , String username) {
		
		Item i=it.FindByItemName(name);
		String status = "Done";
		i.setStatus(status);
		it.save(i);
		
		return "redirect:/MyTasks?username="+username;
		
	}
}
