package com.p2p.domain.marketplace;

import java.math.BigDecimal;
import java.util.UUID;

public class Loan {

    private final UUID id;
    private final BigDecimal amount;
    private final BigDecimal interestRate;
    private final int tenorMonths;
    private final String riskGrade;
    private final LoanStatus status;

    public Loan(BigDecimal amount,
                BigDecimal interestRate,
                int tenorMonths,
                String riskGrade,
                LoanStatus status) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.interestRate = interestRate;
        this.tenorMonths = tenorMonths;
        this.riskGrade = riskGrade;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public int getTenorMonths() {
        return tenorMonths;
    }

    public String getRiskGrade() {
        return riskGrade;
    }

    public LoanStatus getStatus() {
        return status;
    }
}