package com.api.apiosito.controllers;

import com.api.apiosito.dtos.TransactionDto;
import com.api.apiosito.models.TransactionsModel;
import com.api.apiosito.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/transactions")
public class TransactionsController {

    final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTransaction(@RequestBody @Valid TransactionDto transactionDto) {
       var transactionModel = new TransactionsModel();
        BeanUtils.copyProperties(transactionDto, transactionModel);
        transactionModel.setDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transactionModel));
    }

    @GetMapping
    public ResponseEntity<List<TransactionsModel>> getAllTransactions(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTransaction(@PathVariable(value = "id")UUID id){
        Optional<TransactionsModel> transactionsModelOptional = transactionService.findById(id);
        if (!transactionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe o usuario");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionsModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable(value = "id")UUID id) {
        Optional<TransactionsModel> transactionsModelOptional = transactionService.findById(id);
        if (!transactionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe o usuario");
        }
        transactionService.delete(transactionsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Transação deletada com sucesso");
    }



}
