package idoussef.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import idoussef.model.Comment;
import idoussef.model.Group;
import idoussef.model.User;
import idoussef.repository.CommentRepository;
import idoussef.repository.GroupRepository;
import idoussef.service.CommentService;

@Controller
public class CommentController {
	@Autowired
     CommentService service ; 
	
	@Autowired
	CommentRepository crep ;
	
	@Autowired 
	GroupRepository grep ; 
	
	@RequestMapping(value="/groupcomments", method = RequestMethod.GET)
	 public String commentpage(ModelMap model , HttpSession session ,@RequestParam String id )
	 {
		 Group group = grep.findByid(id);
		
		    List<Comment> comments = crep.findAll();
		    List<Comment> groupcomm = new ArrayList<Comment>();
		    System.out.print("HAHIA SIZE : " + comments.size() );
		    for (int i = 0 ; i < comments.size() ; i++ )
		    {
		    	if ((comments.get(i).getGroupid()).equals(id))
		    	{
		    		groupcomm.add(comments.get(i));
		    	}
		    	
		    }
		    model.addAttribute("group",group);
		    model.addAttribute("groupcomm",groupcomm);
		
		return "groupcomments" ; 
	 }

	@RequestMapping(value="/newcomment", method = RequestMethod.POST)
	  public String  createComment(Comment comment , @RequestParam String groupid , ModelMap model , HttpSession session) throws Exception {	
	    User login = (User) session.getAttribute("login");  
	    Group group = grep.findByid(groupid);
	    service.addcomment(comment, groupid, login.getFname());
	    
	    List<Comment> comments = crep.findAll();
	    List<Comment> groupcomm = new ArrayList<Comment>();
	    
	    for (int i = 0 ; i < comments.size() ; i++ )
	    {
	    	if ((comments.get(i).getGroupid()).equals(groupid))
	    	{
	    		groupcomm.add(comments.get(i));
	    	}
	    }
	    
	    model.addAttribute("groupcomm",groupcomm);
	    model.addAttribute("group",group);
      
	  return "groupcomments";
	  }
}
