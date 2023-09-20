package com.idempotency.service;

import com.idempotency.model.AccountEntity;

import java.util.List;

/**
 * This is the Account Service class.
 */
public interface AccountService {
    AccountEntity saveAccount(AccountEntity account);

    List<AccountEntity> getAllAccounts();

    AccountEntity getAccountById(String accountId);

    AccountEntity updateAccount(AccountEntity account, String accountId);

    void deleteAccountById(String accountId);
}
