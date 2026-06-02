package com.p2p;

import org.junit.jupiter.api.Test;
import com.p2p.domain.marketplace.*;
import com.p2p.service.FundingService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FundingServiceTest {

    @Test
    void lenderCanFundPartially() {
        LoanListing listing = new LoanListing(
                UUID.randomUUID(),
                BigDecimal.valueOf(1000),
                LocalDateTime.now().plusDays(2)
        );

        EscrowAccount escrow = new EscrowAccount(listing.getId());

        FundingService service = new FundingService();

        service.fund(
                listing,
                escrow,
                UUID.randomUUID(),
                BigDecimal.valueOf(400)
        );

        assertEquals(BigDecimal.valueOf(400), listing.getFundedAmount());
        assertEquals(BigDecimal.valueOf(400), escrow.getTotalCollected());
    }

    @Test
    void loanShouldBecomeFullyFunded() {
        LoanListing listing = new LoanListing(
                UUID.randomUUID(),
                BigDecimal.valueOf(1000),
                LocalDateTime.now().plusDays(2)
        );

        EscrowAccount escrow = new EscrowAccount(listing.getId());

        FundingService service = new FundingService();

        service.fund(
                listing,
                escrow,
                UUID.randomUUID(),
                BigDecimal.valueOf(1000)
        );

        assertEquals(LoanListingStatus.FULLY_FUNDED, listing.getStatus());
    }

    @Test
    void fundingShouldFailIfExceedsRemainingAmount() {
        LoanListing listing = new LoanListing(
                UUID.randomUUID(),
                BigDecimal.valueOf(1000),
                LocalDateTime.now().plusDays(2)
        );

        EscrowAccount escrow = new EscrowAccount(listing.getId());

        FundingService service = new FundingService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.fund(
                    listing,
                    escrow,
                    UUID.randomUUID(),
                    BigDecimal.valueOf(1200)
            );
        });


    }
    @Test
    void fundingShouldBeLockedInitially() {
        LoanListing listing = new LoanListing(
                UUID.randomUUID(),
                BigDecimal.valueOf(1000),
                LocalDateTime.now().plusDays(2)
        );

        EscrowAccount escrow = new EscrowAccount(listing.getId());

        FundingService service = new FundingService();

        Funding funding = service.fund(
                listing,
                escrow,
                UUID.randomUUID(),
                BigDecimal.valueOf(200)
        );

        assertEquals(FundingStatus.LOCKED, funding.getStatus());
    }


}