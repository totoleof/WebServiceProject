package idoussef.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import idoussef.model.Group;
import idoussef.model.User;
import idoussef.model.UserBelong;
import idoussef.repository.GroupRepository;
import idoussef.repository.UserRepository;
import idoussef.service.GroupService;
import idoussef.service.UserBelongService;


@Controller
public class GroupController {

	@Autowired
	private GroupService service ;
	
	@Autowired
	private UserBelongService belongservice ;
	
	@Autowired
	private UserRepository urep ;
	
	@Autowired(required = true)
	private GroupRepository grep ;
	
	@RequestMapping(value="/creergroupe", method = RequestMethod.GET)
	  public String  creer(){
	
	     return "creategroup";
	  }
	
	@RequestMapping(value="/newgroup", method = RequestMethod.POST)
	  public String  createGroup(Group group, ModelMap model , HttpSession session) throws Exception {
	  User login= (User) session.getAttribute("login");
	  model.addAttribute("login", login);
	   service.createnewgroup(group,login);
		belongservice.createuserbelonging(group,login);
		 List<Group> usergroups = grep.findByownerid(login.getId());
		 model.addAttribute("usergroups", usergroups);
		    
		  
		 System.out.print(login.getEmail());
	     return "welcomeuser";
	  }
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	  public String getUsersGroups( Model model, HttpSession session){
		 
	User login= (User) session.getAttribute("login");

	List<Group> usergroups = grep.findByownerid(login.getId());
	model.addAttribute("usergroups", usergroups);
	model.addAttribute("login", login);
	    return "welcomeuser";
	  }
	

	  @RequestMapping(value="/allthegroups", method = RequestMethod.GET)
	  public String getAllGroups(Model model){
	   List<Group> allthegroups = grep.findAll();
	    model.addAttribute("allthegroups", allthegroups);
	    return "allthegroups";
	  }
	

	 @RequestMapping(method = RequestMethod.DELETE, value="/deletegroup")
	  public String deleteGroup(@RequestParam String id , HttpSession session, Model model){
		  
		    grep.delete(id);
		    User login= (User) session.getAttribute("login");

			List<Group> usergroups = grep.findByownerid(login.getId());
			model.addAttribute("usergroups", usergroups);
			model.addAttribute("login", login);
			
		    Map<String, String> response = new HashMap<String, String>();
		    response.put("message", "Group deleted successfully");
		    
		    return "welcomeuser";
	  }
	 
	 
	 @RequestMapping(method = RequestMethod.GET, value="/editgroup")
	 public String edit(@RequestParam String id , ModelMap model, HttpSession session)
	 {
		 session.setAttribute("groupid", id );
		 return "editgroup" ;
	 } 
	
	 
	  
	 
	  @RequestMapping(method = RequestMethod.PUT, value="/updategroup")
	  public String editGroup(@RequestParam String description, @RequestParam String name , ModelMap model ,HttpSession session){
		  
		  String id = (String) session.getAttribute("groupid");
		  User login = (User) session.getAttribute("login");		  
		  Group group = new Group();
		  group.setDescription(description);
		  group.setName(name);
		  group.setOwnerid(login.getId());
		  group.setId(id);
		  Map<String, Object> response = new LinkedHashMap<String, Object>();
		  response.put("message", "Group Updated successfully");
		  response.put("grouo", grep.save(group));
		  


			List<Group> usergroups = grep.findByownerid(login.getId());
			model.addAttribute("usergroups", usergroups);
			model.addAttribute("login", login);
		  
		  
		  return "welcomeuser" ;
	  }
	 
	
	
}
