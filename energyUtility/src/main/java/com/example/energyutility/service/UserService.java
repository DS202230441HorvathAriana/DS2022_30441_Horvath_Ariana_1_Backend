package com.example.energyutility.service;

import com.example.energyutility.dto.UserDTO;
import com.example.energyutility.dto.builder.UserBuilder;
import com.example.energyutility.model.MeteringDevice;
import com.example.energyutility.model.Role;
import com.example.energyutility.model.User;
import com.example.energyutility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(UserDTO userDTO) {
        User existentUser = userRepository.findByUsername(userDTO.getUsername());
        if (existentUser == null) {
            User user = UserBuilder.toUser(userDTO);
            user.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return user;
        } else
            return null;
    }

    public List<UserDTO> findAllClients() {
        List<UserDTO> userDTOS = userRepository.findAllByRole(Role.CLIENT)
                .stream()
                .map(UserBuilder::toUserDTO)
                .toList();
        return userDTOS;
    }

    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null)
            return UserBuilder.toUserDTO(user);
        else
            return null;
    }

    public User updateUser(String username, UserDTO newUserDTO) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (user.getUsername().compareTo(newUserDTO.getUsername()) != 0) {
                if (userRepository.findByUsername(newUserDTO.getUsername()) == null)
                    user.setUsername(newUserDTO.getUsername());
                else
                    return null;
            }
            if (!BCrypt.checkpw(newUserDTO.getPassword(), user.getPassword()))
                user.setPassword(BCrypt.hashpw(newUserDTO.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }

    public boolean deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.deleteByUsername(username);
            return true;
        } else
            return false;
    }

    public String logInUser(UserDTO userDTO) {
        User existentUser = userRepository.findByUsername(userDTO.getUsername());
        if (existentUser == null) {
            return "User with username " + userDTO.getUsername() + " not existent!";
        } else {
            if (!BCrypt.checkpw(userDTO.getPassword(), existentUser.getPassword())) {
                return "Incorrect password!";
            } else {
                return existentUser.getRole().toString();
            }
        }
    }
}
