package idoussef.service;

import java.util.LinkedHashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import idoussef.model.Group;
import idoussef.model.User;
import idoussef.repository.GroupRepository;

@EnableMongoRepositories(basePackages = "idoussef.repository")

@Service
public class GroupService {

	@Autowired
	  private GroupRepository repository ;
	
	 public void createnewgroup(Group group, User user) throws Exception {   
	        Map<String, Object> response = new LinkedHashMap<String, Object>();
	        group.setOwnerid(user.getId());
		    response.put("group", repository.save(group));
		    System.out.print(group.getDescription());
	    }
}
