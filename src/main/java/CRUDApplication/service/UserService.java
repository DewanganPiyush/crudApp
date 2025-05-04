package CRUDApplication.service;

import CRUDApplication.model.User;
import CRUDApplication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }


    public User addUser(User user) {
        return userRepo.save(user);
    }


    public Optional<User> updateUser(Long id, User newUserData) {
        return userRepo.findById(id)
                .map(existing -> {
                    existing.setName(newUserData.getName());
                    existing.setEmail(newUserData.getEmail());
                    return userRepo.save(existing);
                });
    }


    public void deleteUserById(Long id) {
        userRepo.deleteById(id);

    }

}
