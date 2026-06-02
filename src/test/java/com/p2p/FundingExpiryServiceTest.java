package com.p2p;

import org.junit.jupiter.api.Test;
import com.p2p.domain.marketplace.*;
import com.p2p.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FundingExpiryServiceTest {

    @Test
    void listingShouldBeCancelledWhenExpired() {
        LoanListing listing = new LoanListing(
                UUID.randomUUID(),
                BigDecimal.valueOf(1000),
                LocalDateTime.now().minusDays(1)
        );

        EscrowAccount escrow = new EscrowAccount(listing.getId());

        FundingService fundingService = new FundingService();

        Funding funding = fundingService.fund(
                listing,
                escrow,
                UUID.randomUUID(),
                BigDecimal.valueOf(400)
        );

        FundingExpiryService expiryService = new FundingExpiryService();

        expiryService.processExpiry(
                listing,
                escrow,
                List.of(funding),
                LocalDateTime.now()
        );

        assertEquals(LoanListingStatus.CANCELLED, listing.getStatus());
        assertEquals(BigDecimal.ZERO, escrow.getTotalCollected());
        assertEquals(FundingStatus.REFUNDED, funding.getStatus());
    }
}