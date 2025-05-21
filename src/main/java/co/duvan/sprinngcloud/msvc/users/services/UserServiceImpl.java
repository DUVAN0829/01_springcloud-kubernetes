package co.duvan.sprinngcloud.msvc.users.services;

import co.duvan.sprinngcloud.msvc.users.clients.CursoClienteFeign;
import co.duvan.sprinngcloud.msvc.users.models.entity.User;
import co.duvan.sprinngcloud.msvc.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    //* vars
    @Autowired
    private UserRepository repository;

    @Autowired
    private CursoClienteFeign clientRest;

    //* Methods
    @Override
    @Transactional(readOnly = true)
    public List<User> listAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        clientRest.deleteCursoUser(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return repository.findByEmail(email);

    }

    @Override
    public List<User> listById(Iterable<Long> ids) {
        return (List<User>) repository.findAllById(ids);
    }
}











