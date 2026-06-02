package com.p2p.domain.disbursement;

import java.math.BigDecimal;

public class Disbursement {

    private BigDecimal grossAmount;
    private BigDecimal adminFee;
    private BigDecimal insurancePremium;
    private BigDecimal netAmount;

    private String bankName;
    private String accountNumber;
    private String accountName;

    private DisbursementStatus status;

    public Disbursement(BigDecimal grossAmount,
                        BigDecimal adminFee,
                        BigDecimal insurancePremium,
                        String bankName,
                        String accountNumber,
                        String accountName) {

        if (grossAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Gross amount must be greater than zero");
        }

        this.grossAmount = grossAmount;
        this.adminFee = adminFee;
        this.insurancePremium = insurancePremium;

        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountName = accountName;

        calculateNetAmount();

        this.status = DisbursementStatus.PENDING;
    }

    public void calculateNetAmount() {
        this.netAmount = grossAmount
                .subtract(adminFee)
                .subtract(insurancePremium);
    }

    public boolean isBankAccountValid() {

        return bankName != null && !bankName.isBlank()
                && accountNumber != null && !accountNumber.isBlank()
                && accountName != null && !accountName.isBlank();
    }

    public void processDisbursement() {

        if (!isBankAccountValid()) {
            this.status = DisbursementStatus.FAILED;
            return;
        }

        this.status = DisbursementStatus.PROCESSING;

        this.status = DisbursementStatus.SUCCESS;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public DisbursementStatus getStatus() {
        return status;
    }
    
}