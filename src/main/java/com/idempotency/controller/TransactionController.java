package com.idempotency.controller;

import com.idempotency.model.TransactionEntity;
import com.idempotency.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *This is the Controller class for Transaction handling.
 *It is the entry-point for all transactions related operations.
 */
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**
     * This is the controller method to create a transaction.
     * @param transaction The transaction information to be created.
     * @param uniqueId The uniqueId sent in header .
     * @return A ResponseEntity containing the created TransactionEntity if
     *         successful with HTTP status 201 (Created).
     *         If the transaction creation fails,
     *         an error response will be returned.
     */
    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionEntity> insertTransaction(
            @Validated @RequestBody TransactionEntity transaction,
            @RequestHeader(name = "X-Unique-Id", required = true) String uniqueId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Unique-Id", uniqueId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(transactionService.saveTransaction(transaction));
    }

    /**
     * This is the controller method to fetch all existing transactions from db.
     * @return Returns all existing records from db,
     * else exception response is returned.
     */
    @GetMapping("/allTransactions")
    public List<TransactionEntity> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    /**
     * This is the controller method used to fetch
     * any particular transaction using transactionId.
     * @param transactionId It is the id with which the
     *                     transaction details are fetched.
     * @return Returns the fetched transaction
     * if available, else exception response is returned.
     */
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable("transactionId") String transactionId){
        return new ResponseEntity<TransactionEntity>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
    }

    /**
     * This is the controller method to generate the uniqueId.
     * @return Returns a UUID.
     */
    @PostMapping("/uniqueId")
    public String getUniqueId(){
        return transactionService.generateUniqueId();
    }

}
