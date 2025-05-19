package co.duvan.sprinngcloud.msvc.users.services;

import co.duvan.sprinngcloud.msvc.users.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> listAll();

    Optional<User> findById(Long id);

    User save(User user);

    void delete(Long id);

    Optional<User> findByEmail(String email);

}
