package com.api.apiosito.services;

import com.api.apiosito.models.TransactionsModel;
import com.api.apiosito.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionsModel save(TransactionsModel transactionModel) {
        return transactionRepository.save(transactionModel);
    }

    public List<TransactionsModel> findAll() {
        return transactionRepository.findAll();
    }

    public Optional<TransactionsModel> findById(UUID id) {
        return transactionRepository.findById(id);
    }

    @Transactional
    public void delete(TransactionsModel transactionsModel) {
        transactionRepository.delete(transactionsModel);
    }
}
