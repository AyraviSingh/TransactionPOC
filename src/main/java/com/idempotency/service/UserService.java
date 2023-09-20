package com.idempotency.service;

import com.idempotency.model.UserEntity;

import java.util.List;

/**
 * This is the User Service.
 */
public interface UserService {
    UserEntity saveUser(UserEntity user);

    List<UserEntity> getAllUsers();

    UserEntity getUserById(String userId);

    UserEntity updateUser(UserEntity user, String userId);

    void deleteUserById(String userId);
}
