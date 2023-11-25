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

import code.api_compas.model.User;
import code.api_compas.repository.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    @PostMapping
    public User creatUser(@RequestBody User user) {
        return this.repository.save(user);
    }

    //(condicion)?valor1:valor2;
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") String id,
            @RequestBody User user) {
        Optional<User> currentData = this.repository.findById(id);
        if (currentData.isPresent()) {
            User newUser = currentData.get();
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());    
            newUser.setPassword(user.getPassword());
            newUser.setCreated_at(user.getCreated_at());
            newUser.setImage_profile(user.getImage_profile());
            newUser.setDescription(user.getDescription());
            newUser.setRole(user.getRole());        
            return new ResponseEntity<>(this.repository.save(newUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
    Optional<User> currentData = this.repository.findById(id);
    if (currentData.isPresent()) {
      this.repository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
