package com.idempotency.service;

import com.idempotency.model.TransactionEntity;

import java.util.List;

/**
 * This is the Transaction Service.
 */
public interface TransactionService {
    TransactionEntity saveTransaction(TransactionEntity transaction);

    List<TransactionEntity> getAllTransactions();

    TransactionEntity getTransactionById(String transactionId);

    String generateUniqueId();
}
