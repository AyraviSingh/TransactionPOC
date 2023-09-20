package com.idempotency.controller;

import com.idempotency.model.AccountEntity;
import com.idempotency.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the Controller class for Accounts handling.
 * It is the entry-point for all account related operations.
 */
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * This is the entry-point controller method for creating an account.
     * @param account The account information to be created.
     * @return A ResponseEntity containing the created AccountEntity if successful,
     *         with HTTP status 201 (Created). If the account creation fails, an
     *         error response will be returned.
     */
    @PostMapping("/createAccount")
    public ResponseEntity<AccountEntity> createAccount(@Validated @RequestBody AccountEntity account){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.saveAccount(account));
    }

    /**
     * This is the controller method which returns all accounts available in db.
     * @return Returns all available accounts data in the db,
     * else exception response will be returned.
     */
    @GetMapping("/allAccounts")
    public List<AccountEntity> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    /**
     * This is the controller method to fetch any particular account details.
     * @param accountId It is the accountId whose details are to be fetched.
     * @return Returns the account details if available in db with http response ok,
     * else exception response is returned.
     */
    @GetMapping("/account/{accountId}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable("accountId") String accountId){
        return new ResponseEntity<AccountEntity>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    /**
     * This is the controller method which updates an existing account.
     * @param account It is the account details which needs to be updated.
     * @param accountId It is the accountId for the account which needs to be updated.
     * @return Returns the updated account details if it exists,
     * else exception response is returned.
     */
    @PutMapping("/update/{accountId}")
    public ResponseEntity<AccountEntity> updateAccount(@RequestBody AccountEntity account,@PathVariable("accountId") String accountId){
        return new ResponseEntity<AccountEntity>(accountService.updateAccount(account, accountId),HttpStatus.OK);
    }

    /**
     * This is the controller method which deletes the given account.
     * @param accountId It is the accountId of the account to be deleted.
     * @return Returns http status ok if found and deleted,
     * else exception response is returned.
     */
    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<String> deleteAccountById(@PathVariable("accountId") String accountId) {
        accountService.deleteAccountById(accountId);
        return ResponseEntity.ok("Account deleted successfully!");
    }
}
