package com.p2p.domain.repository;

import java.math.BigDecimal;

public interface LoanRepository {
    BigDecimal getAmountById(String loanId);
    int getFundingPercentage(String loanId);
    void updateStatus(String loanId, String status);
    void updateInstallmentStatus(String loanId, int installmentNo, String status);
}