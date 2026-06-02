package com.p2p.domain.marketplace;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

public class LoanListing {

    private final UUID id;
    private final UUID loanId;

    private final BigDecimal listedAmount;
    private BigDecimal fundedAmount;
    private BigDecimal fundingPercentage;

    private LoanListingStatus status;

    private final LocalDateTime expiredAt;
    private final LocalDateTime listedAt;
    private LocalDateTime fundedAt;

    public LoanListing(UUID loanId,
                       BigDecimal listedAmount,
                       LocalDateTime expiredAt) {
        this.id = UUID.randomUUID();
        this.loanId = loanId;
        this.listedAmount = listedAmount;
        this.expiredAt = expiredAt;

        this.fundedAmount = BigDecimal.ZERO;
        this.fundingPercentage = BigDecimal.ZERO;
        this.status = LoanListingStatus.OPEN;
        this.listedAt = LocalDateTime.now();
    }

    public void addFunding(BigDecimal amount) {
        fundedAmount = fundedAmount.add(amount);

        fundingPercentage = fundedAmount
                .multiply(BigDecimal.valueOf(100))
                .divide(listedAmount, 2, RoundingMode.HALF_UP);

        if (fundedAmount.compareTo(listedAmount) == 0) {
            status = LoanListingStatus.FULLY_FUNDED;
            fundedAt = LocalDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getListedAmount() {
        return listedAmount;
    }

    public BigDecimal getFundedAmount() {
        return fundedAmount;
    }

    public BigDecimal getFundingPercentage() {
        return fundingPercentage;
    }

    public LoanListingStatus getStatus() {
        return status;
    }

    public void setStatus(LoanListingStatus status) {
        this.status = status;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public BigDecimal getRemainingAmount() {
        return listedAmount.subtract(fundedAmount);
    }
}