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
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTransaction(@PathVariable(value = "id")UUID id){
        Optional<TransactionsModel> transactionsModelOptional = transactionService.findById(id);
        if (!transactionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário informado não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionsModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable(value = "id")UUID id) {
        Optional<TransactionsModel> transactionsModelOptional = transactionService.findById(id);
        if (!transactionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário informado não existe");
        }
        transactionService.delete(transactionsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Transação deletada com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> upDateTransaction(@PathVariable(value = "id") UUID id,
                                                    @RequestBody TransactionDto transactionDto) {
        Optional<TransactionsModel> transactionsModelOptional = transactionService.findById(id);
        if (!transactionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário informado não existe");
        }
        var transactionsModel = transactionsModelOptional.get();
        transactionsModel.setValue(transactionDto.getValue());
        transactionsModel.setDescription(transactionDto.getDescription());
        transactionsModel.setTransactionType(transactionDto.getTransactionType());
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.save(transactionsModel));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> upDatePatchTransaction(@PathVariable(value = "id") UUID id,
                                                    @RequestBody TransactionDto transactionDto) {
        Optional<TransactionsModel> transactionsModelOptional = transactionService.findById(id);
        if (!transactionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário informado não existe");
        }
        var transactionsModel = transactionsModelOptional.get();
        transactionsModel.setValue(transactionDto.getValue());
        transactionsModel.setDescription(transactionDto.getDescription());
        transactionsModel.setTransactionType(transactionDto.getTransactionType());
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.save(transactionsModel));
    }

}
