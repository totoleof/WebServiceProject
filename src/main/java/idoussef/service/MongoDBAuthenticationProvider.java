//package idoussef.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import com.mongodb.client.MongoCollection;
//
//@Service
//public class MongoDBAuthenticationProvider {
//	@Autowired
//    private MongoCollection users;
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//    }
//
//    @Override
//    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        UserDetails loadedUser;
//
//        try {
//            Client client = users.findOne("{#: #}", Client.USERNAME, username).as(Client.class);
//            loadedUser = new User(client.getUsername(), client.getPassword(), client.getRoles());
//        } catch (Exception repositoryProblem) {
//            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
//        }
//
//        if (loadedUser == null) {
//            throw new InternalAuthenticationServiceException(
//                    "UserDetailsService returned null, which is an interface contract violation");
//        }
//        return loadedUser;
//    }
//
//}
