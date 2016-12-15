package idoussef.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import idoussef.model.UserBelong;

@Repository
public interface UserBelongRepository  extends MongoRepository<UserBelong,String> {
	
	
	List<UserBelong> findBygroupid(String id);
  
	List<UserBelong> findByuserid(String Id);
}
