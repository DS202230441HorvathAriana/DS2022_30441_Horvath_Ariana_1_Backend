package com.example.energyutility.repository;

import com.example.energyutility.model.Role;
import com.example.energyutility.model.User;

import java.util.List;

public interface UserRepository extends AbstractRepository<User>{

    User findByUsername(String username);
    List<User> findAllByRole(Role role);

    void deleteByUsername(String username);

}
