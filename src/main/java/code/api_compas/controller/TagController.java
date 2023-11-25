package code.api_compas.controller;

import code.api_compas.model.Tag;
import code.api_compas.repository.TagRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/tags")
public class TagController {

  @Autowired
  private TagRepository repository;

  @GetMapping
  public List<Tag> getAllTags() {
    return this.repository.findAll();
  }

  @PostMapping
  public Tag createTag(@RequestBody Tag tag) {
    return this.repository.save(tag);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Tag> updateTag(
    @PathVariable("id") String id,
    @RequestBody Tag tag
  ) {
    Optional<Tag> currentData = this.repository.findById(id);
    if (currentData.isPresent()) {
      Tag newTag = currentData.get();
      newTag.setName(tag.getName());
      return new ResponseEntity<>(this.repository.save(newTag), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Tag> deleteTag(@PathVariable("id") String id) {
    Optional<Tag> currentData = this.repository.findById(id);
    if (currentData.isPresent()) {
      this.repository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
