package co.duvan.sprinngcloud.msvc.users.repositories;

import co.duvan.sprinngcloud.msvc.users.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
