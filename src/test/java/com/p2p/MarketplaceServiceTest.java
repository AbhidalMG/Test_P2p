package com.p2p;

import com.p2p.domain.marketplace.Loan;
import com.p2p.domain.marketplace.LoanListing;
import com.p2p.domain.marketplace.LoanStatus;
import org.junit.jupiter.api.Test;
import com.p2p.service.MarketplaceService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MarketplaceServiceTest {

    @Test
    void onlyApprovedLoansShouldAppearInMarketplace() {
        Loan approved = new Loan(
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(12),
                6,
                "B",
                LoanStatus.APPROVED
        );

        Loan rejected = new Loan(
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(10),
                12,
                "A",
                LoanStatus.REJECTED
        );

        MarketplaceService service = new MarketplaceService();

        List<LoanListing> listings =
                service.getMarketplaceListings(List.of(approved, rejected));

        assertEquals(1, listings.size());
    }
}