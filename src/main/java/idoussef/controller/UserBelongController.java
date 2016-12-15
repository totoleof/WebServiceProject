package idoussef.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import idoussef.model.User;
import idoussef.model.Group;
import idoussef.model.UserBelong;
import idoussef.repository.GroupRepository;
import idoussef.repository.UserBelongRepository;
import idoussef.repository.UserRepository;
import idoussef.service.UserBelongService;

@Controller
public class UserBelongController {
	
	@Autowired
	UserBelongRepository userbelongrep ; 
	
	@Autowired
	UserRepository urep ;
	
	@Autowired(required = true)
	GroupRepository grouprep ;
	
	@Autowired
	UserBelongService belongservice ; 
		
	@RequestMapping(value="/groupmembers", method = RequestMethod.GET)
	public String viewmembers(@RequestParam String id, Model model){
				
		List<User> allmembers = new ArrayList<User>();
		List<UserBelong> userbelong = userbelongrep.findBygroupid(id);
		List<User> users= urep.findAll();
		Iterator boucle=userbelong.iterator();
		
		while(boucle.hasNext()){
			UserBelong ub = (UserBelong) boucle.next();
			Iterator boucle2=users.iterator();
			while(boucle2.hasNext()){
				User user = (User) boucle2.next();
				if(user.getId().equals(ub.getUserid()))
				{allmembers.add(user);}
			}		
		}
		model.addAttribute("allmembers", allmembers);	
		model.addAttribute("totalofmembers", allmembers.size());	
		return "groupmembers";
	}
		
	@RequestMapping(value="/belongtogroups", method = RequestMethod.GET)
	  public String getJoinedGroups(Model model, HttpSession session){
		  User user= (User) session.getAttribute("login");
		   String id= user.getId();
		   
		   
		   
		   List<UserBelong> belongtogroups = userbelongrep.findByuserid(id);	
	      
		   List<Group> usergroups = grouprep.findAll();
		   List<Group> allgroups = new ArrayList<Group>();
	
		   for (int i=0 ; i< usergroups.size();i++ )
		   {
			   for ( int j =0 ;j < belongtogroups.size();j++)
			   {
		
				   if(belongtogroups.get(j).getGroupid().equals(usergroups.get(i).getId()))
				   {
					  allgroups.add(usergroups.get(i));
	  
				   }   
			   }
		   }
		   
	
			
	      model.addAttribute("allgroups", allgroups);

	    return "groupsbelong";
	  }
	

	@RequestMapping(value="/joingroup", method = RequestMethod.GET)
	  public String joinGroup(Model model, @RequestParam String id, HttpSession session) throws Exception{
			Group group = grouprep.findByid(id);
			User user= (User) session.getAttribute("login");
			 belongservice.createuserbelonging(group,user);
			 
			 // now let's add an attribute listing the group he joined so that we can see it in the view 
			 
			   List<UserBelong> belongtogroups = userbelongrep.findByuserid(user.getId());	
		      
			   List<Group> usergroups = grouprep.findAll();
			   List<Group> allgroups = new ArrayList<Group>();
		
			   for (int i=0 ; i< usergroups.size();i++ )
			   {
				   for ( int j =0 ;j < belongtogroups.size();j++)
				   {
					   System.out.print("usergroupsfih:"+ usergroups.get(i).getId());
					   System.out.print("usergroupsfih:"+ belongtogroups.get(j).getGroupid());
					   if(belongtogroups.get(j).getGroupid().equals(usergroups.get(i).getId()))
					   {
						  allgroups.add(usergroups.get(i));
		  
					   }   
				   }
			   }		   
				
		      model.addAttribute("allgroups", allgroups);			 	 
			return ("groupsbelong") ;		
	}		
	
	@RequestMapping(value="/leavegroup", method = RequestMethod.DELETE)
     public String leavegroup(Model model, @RequestParam String id, HttpSession session)
     {
		Group group = grouprep.findByid(id);
		User user= (User) session.getAttribute("login");
		UserBelong belong = new UserBelong();
		List<UserBelong> belongs = userbelongrep.findAll(); 
		
		for ( int i = 0 ;  i < belongs.size(); i++)
		{
			if ((belongs.get(i).getUserid()).equals(user.getId()) && (belongs.get(i).getGroupid()).equals(id))
{
	belong = belongs.get(i);
	break;
}	
		}		
		userbelongrep.delete(belong.getId());
		
		
		
		// let's find all the groups the users belongs to to return the view 
		   List<UserBelong> belongtogroups = userbelongrep.findByuserid(user.getId());	
	      
		   List<Group> usergroups = grouprep.findAll();
		   List<Group> allgroups = new ArrayList<Group>();
	
		   for (int i=0 ; i< usergroups.size();i++ )
		   {
			   for ( int j =0 ;j < belongtogroups.size();j++)
			   {
				   System.out.print("usergroupsfih:"+ usergroups.get(i).getId());
				   System.out.print("usergroupsfih:"+ belongtogroups.get(j).getGroupid());
				   if(belongtogroups.get(j).getGroupid().equals(usergroups.get(i).getId()))
				   {
					  allgroups.add(usergroups.get(i));
	  
				   }   
			   }
		   }		   
			
	      model.addAttribute("allgroups", allgroups);			 	 
		return ("groupsbelong") ;		
		
     }
}


           
