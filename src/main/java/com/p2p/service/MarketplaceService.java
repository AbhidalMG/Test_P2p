package com.p2p.service;

import com.p2p.domain.marketplace.Loan;
import com.p2p.domain.marketplace.LoanListing;
import com.p2p.domain.marketplace.LoanStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MarketplaceService {

    public List<LoanListing> getMarketplaceListings(List<Loan> loans) {
        return loans.stream()
                .filter(loan -> loan.getStatus() == LoanStatus.APPROVED)
                .map(loan -> new LoanListing(
                        loan.getId(),
                        loan.getAmount(),
                        LocalDateTime.now().plusDays(7)
                ))
                .collect(Collectors.toList());
    }
}