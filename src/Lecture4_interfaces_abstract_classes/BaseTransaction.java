package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public abstract class BaseTransaction implements TransactionInterface {
    private final double amount;
    private final Calendar date;
    private final String transactionID;

    public BaseTransaction(double amount, @NotNull Calendar date) {
        this.amount = amount;
        this.date = (Calendar) date.clone();
        int uniq = (int) (Math.random() * 10000);
        this.transactionID = date.toString() + uniq;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public Calendar getDate() {
        return (Calendar) date.clone();
    }

    @Override
    public String getTransactionID() {
        return transactionID;
    }

    public abstract void printTransactionDetails();

    public abstract void apply(BankAccount ba) throws InsufficientFundsException;
}
