package code.api_compas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import code.api_compas.model.Event;

public interface EventRepository extends MongoRepository<Event, String> {
    
}
