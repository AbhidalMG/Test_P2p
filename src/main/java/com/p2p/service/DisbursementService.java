package com.p2p.service;

import com.p2p.domain.disbursement.Disbursement;
import com.p2p.domain.transaction.TransactionContext;

public class DisbursementService {

    public void executeDisbursement(Disbursement disbursement,
                                    TransactionContext context,
                                    String walletId,
                                    String loanId) {
        if (disbursement == null) {
            throw new IllegalArgumentException("Disbursement cannot be null");
        }
        if (context == null) {
            throw new IllegalArgumentException("TransactionContext cannot be null");
        }
        disbursement.processDisbursement();
    }
}