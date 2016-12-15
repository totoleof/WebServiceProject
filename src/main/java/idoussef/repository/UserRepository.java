package idoussef.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import idoussef.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

	public User findByEmail(String email) ;
	public User findByEmailAndPassword(String email,String password);
}
