package com.idempotency.repository;

import com.idempotency.model.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the Account Repository.
 */
@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, String> {
}
