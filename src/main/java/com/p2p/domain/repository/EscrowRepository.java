package com.p2p.domain.repository;

public interface EscrowRepository {
    String getStatusByLoanId(String loanId);
    void updateStatus(String loanId, String status);
}