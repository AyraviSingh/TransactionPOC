package com.idempotency.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * This is the Transaction Entity Class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "TransactionDetails")
public class TransactionEntity {
    @Id
    private String transactionId;
    @NotNull(message = "Sender AccountId cannot be null")
    private String senderAccountId;
    @NotNull(message = "Receiver AccountId cannot be null")
    private String receiverAccountId;
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount should be more than 0")
    private Double amount;
    @CreatedDate
    private Date transactionDate;
}
