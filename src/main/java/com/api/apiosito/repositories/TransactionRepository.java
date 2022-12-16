package com.api.apiosito.repositories;

import com.api.apiosito.models.TransactionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionsModel, UUID> {

}
