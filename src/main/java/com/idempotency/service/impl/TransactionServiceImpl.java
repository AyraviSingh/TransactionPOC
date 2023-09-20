package com.idempotency.service.impl;

import com.idempotency.exception.ResourceNotFoundException;
import com.idempotency.model.AccountEntity;
import com.idempotency.model.TransactionEntity;
import com.idempotency.repository.AccountRepository;
import com.idempotency.repository.TransactionRepository;
import com.idempotency.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public TransactionEntity saveTransaction(TransactionEntity transaction) {
        Optional<AccountEntity> sender = accountRepository.findById(transaction.getSenderAccountId());
        if (sender.isEmpty()) {
            log.info("No account exists for the provided sender accountId.");
            throw new ResourceNotFoundException("No account exists for given sender accountId.");
        }
        Optional<AccountEntity> receiver = accountRepository.findById(transaction.getReceiverAccountId());
        if (receiver.isEmpty()) {
            log.info("No account exists for the provided receiver accountId.");
            throw new ResourceNotFoundException("No account exists for given receiver accountId.");
        }
        double amount = transaction.getAmount();
        sender.get().setBalance(sender.get().getBalance() - amount);
        receiver.get().setBalance(receiver.get().getBalance() + amount);
        accountRepository.save(sender.get());
        accountRepository.save(receiver.get());
        transaction.setTransactionDate(new Date());
        return transactionRepository.save(transaction);
    }


    @Override
    public List<TransactionEntity> getAllTransactions(){
        List<TransactionEntity> fetchedTransactions = transactionRepository.findAll();
        if(fetchedTransactions.isEmpty()){
            log.info("No transactions yet to show");
            throw new ResourceNotFoundException("No transactions available in db.");
        }
        return fetchedTransactions;
    }

    @Override
    public TransactionEntity getTransactionById(String transactionId) {
        Optional<TransactionEntity> fetchedTransaction = transactionRepository.findById(transactionId);
        if(fetchedTransaction.isEmpty()){
            log.info("No transaction exists for transactionId: {}", transactionId);
            throw new ResourceNotFoundException("No transaction available with given transactionId");
        }
        return fetchedTransaction.get();
    }

    @Override
    public String generateUniqueId(){
        return UUID.randomUUID().toString();
    }

}
