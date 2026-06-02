package com.p2p.service;

import com.p2p.domain.marketplace.*;

import java.time.LocalDateTime;
import java.util.List;

public class FundingExpiryService {

    public void processExpiry(LoanListing listing,
                              EscrowAccount escrow,
                              List<Funding> fundings,
                              LocalDateTime now) {

        if (listing.getStatus() == LoanListingStatus.FULLY_FUNDED) {
            return;
        }

        if (now.isAfter(listing.getExpiredAt())) {
            listing.setStatus(LoanListingStatus.CANCELLED);

            for (Funding funding : fundings) {
                funding.refund();
            }

            escrow.refundAll();
        }
    }
}