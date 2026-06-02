package com.p2p;
import com.p2p.domain.transaction.DisbursementTransaction;
import com.p2p.domain.transaction.TransactionContext;
import com.p2p.domain.transaction.TransactionStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

class DisbursementTransactionTest {

    @Test
    void shouldProcessSuccessfullyWhenPreconditionsMet() {
        // Arrange
        DisbursementTransaction tx = new DisbursementTransaction(
                "W-01",
                new BigDecimal("1000000"),
                "L-01",
                BigDecimal.ZERO,
                "BCA",
                "12345"
        );
        
        TransactionContext context = new TransactionContext(BigDecimal.ZERO, "holding", 100);

        // Act
        tx.process(context);

        // Assert
        assertEquals(TransactionStatus.SUCCESS, tx.getStatus());
    }

    @Test
    void shouldFailWhenFundingIsIncomplete() {
        // Arrange
        DisbursementTransaction tx = new DisbursementTransaction(
                "W-01",
                new BigDecimal("1000000"),
                "L-01",
                BigDecimal.ZERO,
                "BCA",
                "12345"
        );
        
        // Syarat Gagal: Funding baru 80%
        TransactionContext context = new TransactionContext(BigDecimal.ZERO, "holding", 80);
        tx.process(context);
        assertEquals(TransactionStatus.FAILED, tx.getStatus());
    }

    @Test
    void shouldFailWhenEscrowStatusIsNotHolding() {
        // Arrange
        DisbursementTransaction tx = new DisbursementTransaction(
                "W-01",
                new BigDecimal("1000000"),
                "L-01",
                BigDecimal.ZERO,
                "BCA",
                "12345"
        );
        
        // Syarat Gagal: Status escrow bukan "holding"
        TransactionContext context = new TransactionContext(BigDecimal.ZERO, "released", 100);
        tx.process(context);
        assertEquals(TransactionStatus.FAILED, tx.getStatus());
    }
}