package com.saad.learning.controller;

import com.saad.learning.model.User;
import com.saad.learning.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/api/addUser")
    User newUser(@RequestBody User newUser) {
        return userRepo.save(newUser);
    }

    @GetMapping("/api/showUsers")
    List<User> getAllUser() {
        return userRepo.findAll();
    }

    @GetMapping("/api/showUser/{id}")
    User getOneUser(@PathVariable Long id) {
        return userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Cannot find user"));
    }

    @PutMapping("/api/showUser/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepo.save(user);
                }).orElseThrow(()-> new RuntimeException("Cannot be updated!"));
    }

    @DeleteMapping("/api/showUser/{id}")
    String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "User deleted with id: " + id;
    }

}
