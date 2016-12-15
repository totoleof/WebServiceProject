package idoussef.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import idoussef.model.Group;
import idoussef.model.User;

import idoussef.model.UserBelong;
import idoussef.repository.UserBelongRepository;
import java.util.ArrayList;

@Service
public class UserBelongService {
	
	@Autowired
	UserBelongRepository ubelong ;
	
	public void createuserbelonging ( Group group , User user)throws Exception 
	{
		   UserBelong ownerbelonging = new UserBelong();
		    ownerbelonging.setGroupid(group.getId());
		    ownerbelonging.setUserid(user.getId());
		    List<UserBelong> alllinks = ubelong.findAll(); 
		
		    for (int i = 0 ; i<alllinks.size(); i++)
		    {
		    	if (user.getId().equals(alllinks.get(i).getUserid())  && group.getId().equals(alllinks.get(i).getGroupid())  )
		    	{
		    		 throw new Exception(
		   	              "You already joined this group " );
		   	    }
		    }

		    Map<String, Object> response = new LinkedHashMap<String, Object>();
		    response.put("message", "Link created successfully");
		    response.put("usergroup", ubelong.save(ownerbelonging));
	}

}
