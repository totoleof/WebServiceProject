package idoussef.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import idoussef.model.Group;
import idoussef.model.User;
import idoussef.repository.GroupRepository;
import idoussef.repository.UserRepository;
import idoussef.service.UserService;

@Controller
public class UserController {
	
	 @Autowired
	    private UserRepository repository ;
	 @Autowired
	    private GroupRepository grep ;
	
	@Autowired
private UserService service ;
	
	@RequestMapping("/")
	public String homepage()
	{
     return "homepage";
     }

	
	@RequestMapping(value="/newuser", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_USER')")
	  public String  createUser(User user, ModelMap model , HttpSession session) throws Exception {	
	  service.createnewuer(user);
      session.setAttribute("login", user);
      
       User login = user ;
       
       model.addAttribute("login",login);
  	List<Group> usergroups = grep.findByownerid(user.getId());
	model.addAttribute("usergroups", usergroups);
	  return "welcomeuser";
	  }
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	  public String  login(@RequestParam String email, @RequestParam String password,HttpSession session, Model model) throws Exception {
		User user = repository.findByEmailAndPassword(email, password);
		if(user == null){
			return "homepage";
		}
		else{
			 model.addAttribute("login", repository.findByEmail(email));
			session.setAttribute("login", user);
		 	List<Group> usergroups = grep.findByownerid(user.getId());
			model.addAttribute("usergroups", usergroups);
			
			return "welcomeuser";
		}
	  }


	 @RequestMapping(method = RequestMethod.DELETE, value="/deleteaccount")
	  public String deleteUser(HttpSession session){
		  User user= (User) session.getAttribute("login");
		  System.out.print(user.getId());
	    repository.delete(user.getId());
	    System.out.print("supprimito");
	    return "homepage";
	  }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/editprofile")
	 public String edit( ModelMap model, HttpSession session)
	 {
		
		 return "editprofile" ;
	 } 

	  @RequestMapping(method = RequestMethod.PUT, value="/updateprofile")
	  public String editGroup(@RequestParam String fname,ModelMap model,@RequestParam String lname,@RequestParam String email,@RequestParam String description ,@RequestParam String password ,HttpSession session){
		  
		  User login = (User) session.getAttribute("login");
		  User user = new User();
		  user.setEmail(email);
		  user.setDescription(description);
		  user.setFname(fname);
		  user.setLname(lname);
		  user.setPassword(password);
		  user.setId(login.getId());
		  Map<String, Object> response = new LinkedHashMap<String, Object>();
		  response.put("message", "Group Updated successfully");
		  response.put("grouo", repository.save(user));
		  
		  
		    List<Group> usergroups = grep.findByownerid(user.getId());
			model.addAttribute("usergroups", usergroups);
			model.addAttribute("login", user);
			
			  return "welcomeuser" ;
		  
			  
			  
		  }
	 	  
}