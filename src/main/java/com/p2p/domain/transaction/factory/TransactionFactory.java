package com.p2p.domain.transaction.factory;

import com.p2p.domain.transaction.DisbursementTransaction;
import com.p2p.domain.transaction.RepaymentTransaction;
import com.p2p.domain.transaction.Transaction;
import java.math.BigDecimal;

public class TransactionFactory {

    public static Transaction createDisbursement(String walletId, BigDecimal grossAmount, String loanId, 
                                                 BigDecimal adminFee, String bankName, String accNumber) {
        return new DisbursementTransaction(walletId, grossAmount, loanId, adminFee, bankName, accNumber);
    }

    public static Transaction createRepayment(String walletId, BigDecimal amount, String loanId, 
                                              int installmentNumber, BigDecimal lateFee) {
        return new RepaymentTransaction(walletId, amount, loanId, installmentNumber, lateFee);
    }
}