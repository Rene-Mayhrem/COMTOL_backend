package code.api_compas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import code.api_compas.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
}
