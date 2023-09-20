package com.idempotency.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * This is the User Entity Class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "UserDetails")
public class UserEntity {
    @Id
    private String userId;
    @NotNull(message = "User Name cannot be empty")
    private String userName;
    @Email(message = "Please Provide a valid email")
    private String email;
    private String phoneNumber;

    @CreatedDate
    private Date createdTS;
    @LastModifiedDate
    private Date updatedTS;
}
