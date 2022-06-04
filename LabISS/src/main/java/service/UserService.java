package service;

import model.User;
import persistence.UserRepository;

public class UserService {
    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User logIn(String email, String password) {
        return repository.findByEmailPassword(email, password);
    }
}
