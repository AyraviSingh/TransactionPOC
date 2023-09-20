package com.idempotency.service.impl;

import com.idempotency.exception.ResourceNotFoundException;
import com.idempotency.model.UserEntity;
import com.idempotency.repository.UserRepository;
import com.idempotency.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity saveUser(UserEntity user){
        user.setCreatedTS(new Date());
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> fetchedUsers = userRepository.findAll();

        if (fetchedUsers.isEmpty()) {
            log.info("No data for users exists");
            throw new ResourceNotFoundException("No users available");
        }

        return fetchedUsers;
    }


    @Override
    public UserEntity getUserById(String userId) {
        Optional<UserEntity> fetchedUser = userRepository.findById(userId);
        if(fetchedUser.isEmpty()) {
            log.info("No such user exists for userid: {}", userId);
            throw new ResourceNotFoundException("No user exits for given userId.");
        }
        return fetchedUser.get();
    }

    @Override
    public UserEntity updateUser(UserEntity user, String userId) {
        UserEntity existingUser = getUserById(userId);
        if(existingUser == null){
            log.info("No user available in db with userId: {}",userId);
            throw new ResourceNotFoundException("No user found to update.");
        }
        existingUser.setUserName(user.getUserName() != null ? user.getUserName() : existingUser
                .getUserName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser
                .getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber() != null ? user.getPhoneNumber() : existingUser
                .getPhoneNumber());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUserById(String userId) {
        UserEntity fetchedUser = getUserById(userId);
        if(fetchedUser == null){
            log.info("No user exists with userid: {}",userId);
            throw new ResourceNotFoundException("No user found in db.");
        }
        userRepository.delete(fetchedUser);
    }
}
