package com.idempotency.controller;

import com.idempotency.model.UserEntity;
import com.idempotency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the Controller class for User handling.
 * It is the entry-point for all users related operations.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * This is the controller method used to create a user.
     * @param user The user information to be created.
     * @return A ResponseEntity containing the created UserEntity if successful,
     *      with HTTP status 201 (Created). If the user creation fails, an
     *      error response will be returned.
     */
    @PostMapping("/createUser")
    public ResponseEntity<UserEntity> insertUser(@Validated @RequestBody UserEntity user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    /**
     * This is the controller method which returns all available user details from db.
     * @return Returns all available user details from db,
     * else exception response is returned.
     */
    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    /**
     * This is the controller method used to return a particular user.
     * @param userId UserId with which user details are fetched from db.
     * @return Returns the fetched user details if available,
     * else exception response is returned.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") String userId){
        return new ResponseEntity<UserEntity>(userService.getUserById(userId), HttpStatus.OK);
    }

    /**
     * This is the controller method used to update a given user.
     * @param user User details to be updated.
     * @param userId user which needs to be updated.
     * @return Returns updated user entity if found,
     * else exception response is returned.
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user,@PathVariable("userId") String userId){
        return new ResponseEntity<UserEntity>(userService.updateUser(user, userId),HttpStatus.OK);
    }

    /**
     * Controller method used to delete a given user.
     * @param userId User which has to be deleted.
     * @return Returns a User deleted message if found,
     * else exception message is returned.
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User deleted successfully!");
    }

}
