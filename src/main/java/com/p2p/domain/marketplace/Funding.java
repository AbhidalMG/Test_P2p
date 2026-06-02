package com.p2p.domain.marketplace;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Funding {

    private final UUID id;
    private final UUID loanListingId;
    private final UUID lenderId;

    private final BigDecimal amount;

    private FundingStatus status;

    private final LocalDateTime fundedAt;
    private LocalDateTime refundedAt;

    public Funding(UUID loanListingId,
                   UUID lenderId,
                   BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.loanListingId = loanListingId;
        this.lenderId = lenderId;
        this.amount = amount;
        this.status = FundingStatus.LOCKED;
        this.fundedAt = LocalDateTime.now();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public FundingStatus getStatus() {
        return status;
    }

    public void refund() {
        status = FundingStatus.REFUNDED;
        refundedAt = LocalDateTime.now();
    }
}