package com.p2p;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.p2p.domain.disbursement.Disbursement;
import com.p2p.domain.disbursement.DisbursementStatus;

public class DisbursementTest {

    @Test
    void shouldCalculateNetAmountCorrectly() {

        Disbursement disbursement = new Disbursement(
                new BigDecimal("1000000"),
                new BigDecimal("50000"),
                new BigDecimal("25000"),
                "BCA",
                "1234567890",
                "Alia Ardani"
        );

        assertEquals(
                new BigDecimal("925000"),
                disbursement.getNetAmount()
        );
    }

    @Test
    void shouldSetInitialStatusAsPending() {

        Disbursement disbursement = new Disbursement(
                new BigDecimal("1000000"),
                new BigDecimal("50000"),
                new BigDecimal("25000"),
                "BCA",
                "1234567890",
                "Alia Ardani"
        );

        assertEquals(
                DisbursementStatus.PENDING,
                disbursement.getStatus()
        );
    }

        @Test
        void shouldValidateBankAccountSuccessfully() {
        
                Disbursement disbursement = new Disbursement(
                new BigDecimal("1000000"),
                new BigDecimal("50000"),
                new BigDecimal("25000"),
                "BCA",
                "1234567890",
                "Alia Ardani"
                );

                assertEquals(true, disbursement.isBankAccountValid());
        }

        @Test
        void shouldChangeStatusToSuccess_whenDisbursementProcessed() {

        Disbursement disbursement = new Disbursement(
                new BigDecimal("1000000"),
                new BigDecimal("50000"),
                new BigDecimal("25000"),
                "BCA",
                "1234567890",
                "Alia Ardani"
        );

        disbursement.processDisbursement();

        assertEquals(
                DisbursementStatus.SUCCESS,
                disbursement.getStatus()
        );
    }

        @Test
        void shouldFail_whenBankAccountInvalid() {

                Disbursement disbursement = new Disbursement(
                        new BigDecimal("1000000"),
                        new BigDecimal("50000"),
                        new BigDecimal("25000"),
                        "",
                "",
                ""
                );

                disbursement.processDisbursement();

                assertEquals(
                        DisbursementStatus.FAILED,
                        disbursement.getStatus()
                );
        }

        @Test
        void shouldRejectNegativeGrossAmount() {

                assertThrows(IllegalArgumentException.class, () -> {

                        new Disbursement(
                                new BigDecimal("-1000000"),
                                new BigDecimal("50000"),
                                new BigDecimal("25000"),
                                "BCA",
                                "1234567890",
                                "Alia Ardani"
                        );
                });
        }

}