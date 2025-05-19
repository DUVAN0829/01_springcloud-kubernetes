package co.duvan.sprinngcloud.msvc.users.controllers;

import co.duvan.sprinngcloud.msvc.users.models.entity.User;
import co.duvan.sprinngcloud.msvc.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {

        if (services.findByEmail(user.getEmail()).isPresent()) {

            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("message: ", "A user with email Already exists."));

        }

        if (result.hasErrors()) {

            return validationBody(result);

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(user));

    }

    //*Update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            validationBody(result);
        }

        Optional<User> optionalUser = services.findById(id);

        if (optionalUser.isPresent()) {

            User userDb = optionalUser.get();

            if (!user.getEmail().equals(userDb.getEmail()) && services.findByEmail(user.getEmail()).isPresent()) { //* Valida que no se intente poner el mismo correo electronico o uno que ya exista.

                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("message: ", "A user with email Already exists."));

            }

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

    //* Method: Validation Json Body
    public ResponseEntity<Map<String, String>> validationBody(BindingResult result) {

        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), " El campo " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);

    }

}








