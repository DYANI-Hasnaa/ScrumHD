package org.scrum.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.scrum.dao.BacklogRepository;
import org.scrum.entities.backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TeamController {
	
	
	@Autowired
	private BacklogRepository br;
	

	public void projects(Model model) {
		List<backlog> backlogs=br.findAll();
	    model.addAttribute("listBacklogs", backlogs);
	}
	
	
	@RequestMapping(value="/AddTeam1", method=RequestMethod.GET)
	public String AddTeam1(Model model) {
		
		this.projects(model);
		
		return "AddTeam";
		
	}
	
	private void sendmail(String email, String projectname)throws AddressException, MessagingException, IOException{
		
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("hasnaadyani@gmail.com","");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("hasnaadyani@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject("Member of Scrum HD");
		   msg.setContent("U are memeber of "+projectname, "text/html");
		   msg.setSentDate(new Date());

		   Transport.send(msg);    
		}
	
	@RequestMapping(value = "/sendemail", method=RequestMethod.POST)
	public String sendEmail(String email, String projectname)throws AddressException, MessagingException, IOException{
	   sendmail(email, projectname);
	   return "index";   
	}
	
}
