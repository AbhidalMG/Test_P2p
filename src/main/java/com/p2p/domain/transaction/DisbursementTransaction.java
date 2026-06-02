package com.p2p.domain.transaction;

import java.math.BigDecimal;

public class DisbursementTransaction extends Transaction {
    
    private BigDecimal adminFee;
    private BigDecimal netAmount;
    private final String bankName;
    private final String accountNumber;

    public DisbursementTransaction(
            String walletId, BigDecimal grossAmount, String loanId, 
            BigDecimal adminFee, String bankName, String accountNumber) {
        
        // Memanggil konstruktor Transaction (Parent)
        super(walletId, TransactionType.DISBURSEMENT, grossAmount, loanId);
        
        this.adminFee = adminFee;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.description = "Pencairan Pinjaman ke Rekening " + bankName;
        
        calculateNetAmount();
    }

    private void calculateNetAmount() {
        this.netAmount = this.amount.subtract(adminFee);
    }

    @Override
    protected void validatePreconditions(TransactionContext context) {
        if (context.getFundingPercentage() < 100) {
            throw new IllegalStateException("Dana belum terkumpul 100%");
        }
        if (!"holding".equalsIgnoreCase(context.getEscrowStatus())) {
            throw new IllegalStateException("Status Escrow bukan holding");
        }
        if (bankName == null || accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Data rekening bank tidak valid");
        }
    }

    @Override
    protected void executeBusinessLogic(TransactionContext context) {
    }
}

