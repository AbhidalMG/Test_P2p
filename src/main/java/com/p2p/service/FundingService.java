package com.p2p.service;

import com.p2p.domain.marketplace.EscrowAccount;
import com.p2p.domain.marketplace.Funding;
import com.p2p.domain.marketplace.LoanListing;

import java.math.BigDecimal;
import java.util.UUID;

public class FundingService {

    public Funding fund(LoanListing listing,
                        EscrowAccount escrow,
                        UUID lenderId,
                        BigDecimal amount) {

        if (amount.compareTo(listing.getRemainingAmount()) > 0) {
            throw new IllegalArgumentException("Funding exceeds remaining target");
        }

        Funding funding = new Funding(
                listing.getId(),
                lenderId,
                amount
        );

        listing.addFunding(amount);
        escrow.collect(amount);

        return funding;
    }
}