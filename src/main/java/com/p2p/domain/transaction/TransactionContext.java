package com.p2p.domain.transaction;

import java.math.BigDecimal;

public class TransactionContext {
    private final BigDecimal currentWalletBalance;
    private final String escrowStatus;
    private final int fundingPercentage;
    
    public TransactionContext(BigDecimal balance, String escrowStatus, int funding) {
        this.currentWalletBalance = balance;
        this.escrowStatus = escrowStatus;
        this.fundingPercentage = funding;
    }
    
    // Getters
    public BigDecimal getCurrentWalletBalance() { return currentWalletBalance; }
    public String getEscrowStatus() { return escrowStatus; }
    public int getFundingPercentage() { return fundingPercentage; }
}