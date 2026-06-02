package com.p2p.domain.marketplace;

public class Lender {
    private final String name;
    private double walletBalance;

    public Lender(String name, double walletBalance) {
        this.name = name;
        this.walletBalance = walletBalance;
    }

    public String getName() {
        return name;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void deduct(double amount) {
        walletBalance -= amount;
    }

    public void refund(double amount) {
        walletBalance += amount;
    }
}
