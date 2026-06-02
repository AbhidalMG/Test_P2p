package com.p2p.domain.marketplace;

import java.math.BigDecimal;
import java.util.UUID;

public class EscrowAccount {

    private final UUID id;
    private final UUID loanListingId;

    private BigDecimal totalCollected;
    private EscrowStatus status;

    public EscrowAccount(UUID loanListingId) {
        this.id = UUID.randomUUID();
        this.loanListingId = loanListingId;
        this.totalCollected = BigDecimal.ZERO;
        this.status = EscrowStatus.HOLDING;
    }

    public void collect(BigDecimal amount) {
        totalCollected = totalCollected.add(amount);
    }

    public void refundAll() {
        totalCollected = BigDecimal.ZERO;
        status = EscrowStatus.REFUNDED;
    }

    public BigDecimal getTotalCollected() {
        return totalCollected;
    }

    public EscrowStatus getStatus() {
        return status;
    }
}