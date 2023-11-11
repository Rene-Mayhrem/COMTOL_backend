package code.api_compas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import code.api_compas.model.Tag;

public interface TagRepository extends MongoRepository<Tag, String> {
    
}
