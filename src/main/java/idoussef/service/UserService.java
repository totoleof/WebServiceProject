package idoussef.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import idoussef.model.User;
import idoussef.repository.UserRepository;

@Service
public class UserService {
	 @Autowired
	    private UserRepository repository; 
	     
	    
	    public void createnewuer(User account) throws Exception {
	         
	        if (emailExist(account.getEmail())) {  
	            throw new Exception(
	              "There is an account with that email adress: " +  account.getEmail());
	        }
	        
	        Map<String, Object> response = new LinkedHashMap<String, Object>();
		    response.put("message", "Your account has been created succesfully");
		    response.put("user", repository.save(account));
		    
	      
	    }
	    private boolean emailExist(String email) {
	        User user = repository.findByEmail(email);
	        if (user != null) {
	            return true;
	        }
	        return false;
	    }
}
