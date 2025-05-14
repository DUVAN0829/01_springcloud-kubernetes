package co.duvan.sprinngcloud.msvc.users.controllers;

import co.duvan.sprinngcloud.msvc.users.models.entity.User;
import co.duvan.sprinngcloud.msvc.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    //Vars
    @Autowired
    private UserService services;

    //Methods
    //*list
    @GetMapping
    public List<User> list() {
        return services.listAll();
    }

    //*FindById
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<User> optionalUser = services.findById(id);

        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalUser.orElseThrow());
        }

        return ResponseEntity.notFound().build();

    }

    //*Create
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(user));
    }

    //*Update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {

        Optional<User> optionalUser = services.findById(id);

        if (optionalUser.isPresent()) {

            User userDb = optionalUser.get();
            userDb.setName(user.getName());
            userDb.setEmail(user.getEmail());
            userDb.setPassword(user.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(services.save(userDb));

        }

        return ResponseEntity.notFound().build();

    }

    //*Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<User> userOptional = services.findById(id);

        if (userOptional.isPresent()) {

            services.delete(id);
            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.notFound().build();

    }

}








