package com.p2p.domain.repository;

import java.math.BigDecimal;

public interface WalletRepository {
    BigDecimal getBalance(String walletId);
    void updateBalance(String walletId, BigDecimal amount);
}