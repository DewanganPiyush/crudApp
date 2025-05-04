package CRUDApplication.controller;

import CRUDApplication.model.User;
import CRUDApplication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> userList = new ArrayList<>();
            userRepo.findAll().forEach(userList::add);

            if (userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userData = userRepo.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {

            User userObj = userRepo.save(user);
            return new ResponseEntity<>(userObj, HttpStatus.OK);


    }
    @PatchMapping("/updateBook/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User newUserData) {

            Optional<User> oldUserData = userRepo.findById(id);

            if (oldUserData.isPresent()) {
                User updatedUserData = oldUserData.get();
                updatedUserData.setName(newUserData.getName());
                updatedUserData.setEmail(newUserData.getEmail());

                User userObj = userRepo.save(updatedUserData);
                return new ResponseEntity<>(userObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id) {
       userRepo.deleteById(id);
       return new ResponseEntity<>(HttpStatus.OK);

    }
}
