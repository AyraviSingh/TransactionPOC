package com.idempotency.service.impl;

import com.idempotency.exception.ResourceNotFoundException;
import com.idempotency.model.AccountEntity;
import com.idempotency.model.UserEntity;
import com.idempotency.repository.AccountRepository;
import com.idempotency.repository.UserRepository;
import com.idempotency.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Account service impl which has all methods to
 * perform all operations on account.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public AccountEntity saveAccount(AccountEntity account) {
        Optional<UserEntity> user = userRepository.findById(account.getUserId());
        if (user.isEmpty()) {
            log.info("No user exists with given user id");
            throw new ResourceNotFoundException("No user exists with given user details.");
        }
        account.setUserName(user.get().getUserName());
        log.info("Creating account for user");
        account.setCreatedTS(new Date());
        return accountRepository.save(account);
    }


    @Override
    public List<AccountEntity> getAllAccounts(){
        List<AccountEntity> fetchedAccounts = accountRepository.findAll();
        if(fetchedAccounts.isEmpty()){
            log.info("No data for accounts exists");
            throw new ResourceNotFoundException("No accounts available in db.");
        }
        return fetchedAccounts;
    }

    @Override
    public AccountEntity getAccountById(String accountId) {
        Optional<AccountEntity> fetchedAccount = accountRepository.findById(accountId);
        if(fetchedAccount.isEmpty()){
            log.info("No account exits for accountId: {}", accountId);
            throw new ResourceNotFoundException("No account exists for given accountId");
        }
        return fetchedAccount.get();
    }

    @Override
    public AccountEntity updateAccount(AccountEntity account, String accountId) {
        AccountEntity existingAccount = getAccountById(accountId);
        if(existingAccount == null){
            log.info("No account exists for accountId: {}" ,accountId);
            throw new ResourceNotFoundException("No account exists for given accountId");
        }
        existingAccount.setAccountType(account.getAccountType() != null ? account.getAccountType() : existingAccount
                .getAccountType());
        existingAccount.setUserName(account.getUserName() != null ? account.getUserName() : existingAccount
                .getUserName());
        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccountById(String accountId) {
        AccountEntity fetchedAccount = getAccountById(accountId);
        if(fetchedAccount == null){
            log.info("No accounts exists for accountId: {}", accountId);
            throw new ResourceNotFoundException("No account available for given accountId");
        }
        accountRepository.delete(fetchedAccount);
    }
}
