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
    @GetMapping
    public List<User> list() {
        return services.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<User> optionalUser = services.byId(id);

        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalUser.orElseThrow());
        }

        return ResponseEntity.notFound().build();

    }
    

}
