package com.p2p.domain.transaction;

import java.math.BigDecimal;

public class RepaymentTransaction extends Transaction {

    private BigDecimal lateFee;
    private final int installmentNumber; // Cicilan ke-berapa

    public RepaymentTransaction(
            String walletId, BigDecimal amount, String loanId, 
            int installmentNumber, BigDecimal lateFee) {
        
        // Memanggil konstruktor kelas induk
        super(walletId, TransactionType.REPAYMENT, amount, loanId);
        this.installmentNumber = installmentNumber;
        this.lateFee = lateFee;
        this.description = "Pembayaran Cicilan ke-" + installmentNumber;
    }


    @Override
    protected void validatePreconditions(TransactionContext context) {
        BigDecimal totalRequired = this.amount.add(lateFee);
        
        // Cek apakah saldo dompet cukup untuk membayar pokok cicilan + denda
        if (context.getCurrentWalletBalance().compareTo(totalRequired) < 0) {
            throw new IllegalStateException("Saldo dompet tidak mencukupi untuk pembayaran cicilan.");
        }
    }


    @Override
    protected void executeBusinessLogic(TransactionContext context) {
      
    }
}

