package com.p2p.domain.repository;

import com.p2p.domain.transaction.Transaction;
import java.util.Optional;

public interface TransactionRepository {
    void save(Transaction transaction);
    Optional<Transaction> findById(String id);
}