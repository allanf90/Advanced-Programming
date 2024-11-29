package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class DepositTransaction extends BaseTransaction {
    public DepositTransaction(double amount, @NotNull Calendar date) {
        super(amount, date);
    }

    private boolean checkDepositAmount(double amt) {
        return amt >= 0;
    }

    @Override
    public void printTransactionDetails() {
        System.out.println("Deposit Transaction: " + this.toString());
    }

    @Override
    public void apply(BankAccount ba) {
        if (checkDepositAmount(getAmount())) {
            ba.deposit(getAmount());
            System.out.println("Deposit of " + getAmount() + " completed.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}
