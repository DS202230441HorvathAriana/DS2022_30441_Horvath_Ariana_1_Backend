package com.example.energyutility.controller;

import com.example.energyutility.dto.MessageDTO;
import com.example.energyutility.dto.UserDTO;
import com.example.energyutility.model.MeteringDevice;
import com.example.energyutility.model.User;
import com.example.energyutility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    ResponseEntity<MessageDTO> registerUser(@RequestBody UserDTO newUser) {
        User addedUser = userService.save(newUser);
        if (addedUser != null)
            return new ResponseEntity<>(new MessageDTO(true,"User " + addedUser.getUsername() + " successfully added."),
                    HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new MessageDTO(false,"User with username " + newUser.getUsername() + " already existent!"),
                    HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/clients")
    ResponseEntity<List<UserDTO>> findAllClients() {
        List<UserDTO> clients = userService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{username}")
    ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
        UserDTO user = userService.findByUsername(username);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{username}")
    ResponseEntity<MessageDTO> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(username, userDTO);
        if (updatedUser != null)
            return new ResponseEntity<>(new MessageDTO(true, "User successfully updated."), HttpStatus.OK);
        else
            return new ResponseEntity<>(new MessageDTO(false, "User with username " + username +
                    " not existent or new username taken"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/users/{username}")
    ResponseEntity<MessageDTO> deleteUser(@PathVariable String username) {
        if (userService.deleteUser(username)) {
            return new ResponseEntity<>(new MessageDTO(true, "User successfully deleted!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new MessageDTO(false,"User with username " + username + " not existent."),
                    HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    ResponseEntity<MessageDTO> logInAdmin(@RequestBody UserDTO userDTO) {
        String message = userService.logInUser(userDTO);
        if (message.compareTo("CLIENT") == 0 || message.compareTo("ADMIN") == 0)
            return new ResponseEntity<>(new MessageDTO(true, message), HttpStatus.OK);
        else
            return new ResponseEntity<>(new MessageDTO(false, message), HttpStatus.NOT_FOUND);
    }
}
