package com.p2p.service;

import com.p2p.domain.transaction.Transaction;
import com.p2p.domain.transaction.TransactionContext;
import com.p2p.domain.transaction.TransactionStatus;
import com.p2p.domain.transaction.factory.TransactionFactory;
import com.p2p.domain.repository.TransactionRepository;
import com.p2p.domain.repository.WalletRepository;
import com.p2p.domain.repository.EscrowRepository;
import com.p2p.domain.repository.LoanRepository;

import java.math.BigDecimal;

public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final WalletRepository walletRepo;
    private final EscrowRepository escrowRepo;
    private final LoanRepository loanRepo;

    public TransactionService(TransactionRepository transactionRepo, 
                              WalletRepository walletRepo, 
                              EscrowRepository escrowRepo, 
                              LoanRepository loanRepo) {
        this.transactionRepo = transactionRepo;
        this.walletRepo = walletRepo;
        this.escrowRepo = escrowRepo;
        this.loanRepo = loanRepo;
    }

    /**
     * Layanan untuk memproses Pencairan Dana
     */
    public void executeDisbursement(String walletId, String loanId, BigDecimal grossAmount, 
                                    BigDecimal adminFee, String bankName, String accNumber) {
        
        BigDecimal currentBalance = walletRepo.getBalance(walletId);
        String escrowStatus = escrowRepo.getStatusByLoanId(loanId);
        int fundingPercentage = loanRepo.getFundingPercentage(loanId);

        TransactionContext context = new TransactionContext(currentBalance, escrowStatus, fundingPercentage);
        
        Transaction tx = TransactionFactory.createDisbursement(
            walletId, grossAmount, loanId, adminFee, bankName, accNumber
        );

        tx.process(context);
        transactionRepo.save(tx);

   
        if (tx.getStatus() == TransactionStatus.SUCCESS) {
            System.out.println("Pencairan Sukses! Mengirim perintah ke gerbang pembayaran...");
        } else {
            System.err.println("Pencairan Gagal direkam sistem.");
        }
    }

    /**
     * Layanan untuk memproses Pembayaran Cicilan
     */
    public void executeRepayment(String walletId, String loanId, BigDecimal amount, 
                                 int installmentNumber, BigDecimal lateFee) {
        

        BigDecimal currentBalance = walletRepo.getBalance(walletId);
        
    
        TransactionContext context = new TransactionContext(currentBalance, "released", 100);

        Transaction tx = TransactionFactory.createRepayment(
            walletId, amount, loanId, installmentNumber, lateFee
        );

        tx.process(context);

        transactionRepo.save(tx);

        if (tx.getStatus() == TransactionStatus.SUCCESS) {
            BigDecimal totalDeduction = amount.add(lateFee);
            BigDecimal newBalance = currentBalance.subtract(totalDeduction);
            walletRepo.updateBalance(walletId, newBalance);
            System.out.println("Cicilan berhasil dibayar!");
        }
    }
}