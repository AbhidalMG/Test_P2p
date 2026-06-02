package com.p2p.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Transaction {
    protected final String id;
    protected final String walletId;
    protected final TransactionType type;
    protected final BigDecimal amount; 
    protected final String refId;
    protected TransactionStatus status;
    protected String description;
    protected final LocalDateTime createdAt;

    public Transaction(String walletId, TransactionType type, BigDecimal amount, String refId) {
        this.id = UUID.randomUUID().toString();
        this.walletId = walletId;
        this.type = type;
        this.amount = amount;
        this.refId = refId;
        this.status = TransactionStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Kontrak Wajib: Semua jenis transaksi harus mendefinisikan cara mereka diproses.
     */

    public final void process(TransactionContext context) {
        try {
            validatePreconditions(context);
            
            executeBusinessLogic(context);

            markAsSuccess();
        } catch (Exception e) {
            markAsFailed(e.getMessage());
        }
    }

    private void markAsSuccess() {
        this.status = TransactionStatus.SUCCESS;
    }

    private void markAsFailed(String reason) {
        this.status = TransactionStatus.FAILED;
        this.description = reason;
    }

    protected abstract void validatePreconditions(TransactionContext context);
    protected abstract void executeBusinessLogic(TransactionContext context);

    // --- Getters ---
    public String getId() { return id; }
    public BigDecimal getAmount() { return amount; }
    public TransactionStatus getStatus() { return status; }

    
}

