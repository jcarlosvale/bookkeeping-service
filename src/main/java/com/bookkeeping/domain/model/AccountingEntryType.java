package com.bookkeeping.domain.model;

import com.bookkeeping.domain.enums.TransactionType;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "accounting_entry_type")
public class AccountingEntryType {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "transaction_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "description", nullable = false)
    private String description;

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
