package com.api.apiosito.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TransactionDto {

    @NotBlank
    @Size(max = 25)
    private String transactionType;
    @NotBlank
    @Size(max = 15)
    private String description;
    @NotBlank
    @Size(max = 50)
    private String value;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
