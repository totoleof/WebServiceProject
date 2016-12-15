package idoussef.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import idoussef.model.Group;

@Repository
public interface GroupRepository extends MongoRepository<Group,String>{
	
	List<Group> findByownerid(String id);
	Group findByid( String id );

}
