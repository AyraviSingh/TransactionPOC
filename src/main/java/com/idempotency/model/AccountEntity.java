package com.idempotency.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * This is the Account Entity class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "AccountDetails")
public class AccountEntity {
    @Id
    private String accountId;
    @NotNull(message = "UserId cannot be null")
    private String userId;
    private String userName;
    @NotNull(message = "Balance cannot be null")
    @Positive(message = "Balance should be more than 0")
    private Double balance;
    @NotNull(message = "Account Type cannot be null")
    private String accountType;
    @CreatedDate
    private Date createdTS;
    @LastModifiedDate
    private Date updatedTS;
}
