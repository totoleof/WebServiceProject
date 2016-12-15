package idoussef.service;




import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import idoussef.model.Comment;
import idoussef.repository.CommentRepository;
@EnableMongoRepositories(basePackages = "idoussef.repository")
@Service
public class CommentService {
	
	
	@Autowired
	private CommentRepository crep ; 
	
	
	public void addcomment(Comment comment , String groupid , String firstname)
	{
		   
	       Map<String, Object> response = new LinkedHashMap<String,Object>();
		   comment.setGroupid(groupid);
	       comment.setUserfname(firstname);
	       response.put("comment", crep.save(comment));
	      
		
	}

}
