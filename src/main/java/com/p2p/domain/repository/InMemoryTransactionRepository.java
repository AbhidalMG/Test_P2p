package com.p2p.domain.repository;

import com.p2p.domain.repository.TransactionRepository;
import com.p2p.domain.transaction.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final Map<String, Transaction> database = new HashMap<>();

    @Override
    public void save(Transaction transaction) {
        database.put(transaction.getId(), transaction);
        System.out.println("💾 Transaksi disimpan di memori: " + transaction.getId());
    }

    @Override
    public Optional<Transaction> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }
}