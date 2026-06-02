package com.p2p;

import com.p2p.domain.disbursement.Disbursement;
import com.p2p.domain.disbursement.DisbursementStatus;
import com.p2p.domain.transaction.TransactionContext;
import com.p2p.service.DisbursementService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class DisbursementServiceTest {

    @Test
    void shouldUpdateStatusToSuccessAfterExecution() {
        // Arrange
        DisbursementService service = new DisbursementService();
        Disbursement disbursement = new Disbursement(
            new BigDecimal("1000000"), BigDecimal.ZERO, BigDecimal.ZERO, "BCA", "12345", "Dal"
        );
        
        // Konteks yang memenuhi syarat
        TransactionContext context = new TransactionContext(BigDecimal.ZERO, "holding", 100);

        // Act
        service.executeDisbursement(disbursement, context, "W-01", "L-01");

        // Assert
        assertEquals(DisbursementStatus.SUCCESS, disbursement.getStatus());
    }

    @Test
    void shouldFailServiceWhenAccountIsInvalid() {
        // Arrange
        DisbursementService service = new DisbursementService();
        // Rekening tidak valid (accountNumber null)
        Disbursement disbursement = new Disbursement(
            new BigDecimal("1000000"), BigDecimal.ZERO, BigDecimal.ZERO, "BCA", null, "Dal"
        );
        TransactionContext context = new TransactionContext(BigDecimal.ZERO, "holding", 100);

        // Act
        service.executeDisbursement(disbursement, context, "W-01", "L-01");

        // Assert
        assertEquals(DisbursementStatus.FAILED, disbursement.getStatus());
    }
}