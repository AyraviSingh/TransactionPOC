package com.idempotency.repository;

import com.idempotency.model.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the Transaction Repository.
 */
@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {
}
