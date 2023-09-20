package com.idempotency.repository;

import com.idempotency.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the User Repository.
 */
@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
}
