package com.thatsmyspot.userservice.controllers;

import com.thatsmyspot.userservice.dto.CreateUserDto;
import com.thatsmyspot.userservice.dto.UpdateUserDto;
import com.thatsmyspot.userservice.entities.User;
import com.thatsmyspot.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<User> saveUser(@RequestBody CreateUserDto user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UpdateUserDto user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }
}
