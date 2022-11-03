package com.example.energyutility.dto.builder;

import com.example.energyutility.dto.UserDTO;
import com.example.energyutility.model.Role;
import com.example.energyutility.model.User;
import com.example.energyutility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserBuilder {

    public UserBuilder() {
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword(), user.getRole().toString());
    }

    public static User toUser(UserDTO userDTO) {
        if (userDTO.getRole().compareTo("CLIENT") == 0)
            return new User(userDTO.getUsername(), userDTO.getPassword(), Role.CLIENT);
        else return new User(userDTO.getUsername(), userDTO.getPassword(), Role.ADMIN);
    }
}
