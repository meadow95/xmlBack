package api.gateway.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import api.gateway.model.DAOUser;


@Repository
public interface UserRepository extends MongoRepository<DAOUser, String> {
	DAOUser findByUsername(String username);

}