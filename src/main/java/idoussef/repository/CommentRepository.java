package idoussef.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import idoussef.model.Comment;
import idoussef.model.Group;

@Repository
public interface CommentRepository  extends MongoRepository<Comment,String>{
	
	

}
