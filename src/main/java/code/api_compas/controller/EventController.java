package code.api_compas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code.api_compas.model.Event;
import code.api_compas.model.Tag;
import code.api_compas.repository.EventRepository;

@RestController
@CrossOrigin("http:localhost:3000")
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventRepository repository;

    @GetMapping
    public List<Event> getAllEvents() {
        return this.repository.findAll();
    }

    @PostMapping
    public Event createEevent(@RequestBody Event event) {
        return this.repository.save(event);
    }

    @PutMapping("/{id}")
  public ResponseEntity<Event> updateEvent(
    @PathVariable("id") String id,
    @RequestBody Event event
  ) {
    Optional<Event> currentData = this.repository.findById(id);
    if (currentData.isPresent()) {
      Event newEvent = currentData.get();
      newEvent.setTitle(event.getTitle());
      newEvent.setDescription(event.getDescription());
      newEvent.setCreated_at(event.getCreated_at());

      return new ResponseEntity<>(this.repository.save(newEvent), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Event> deleteEvent(@PathVariable("id") String id) {
    Optional<Event> currentData = this.repository.findById(id);
    if (currentData.isPresent()) {
      this.repository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}