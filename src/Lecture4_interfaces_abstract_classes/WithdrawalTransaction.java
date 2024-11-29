package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class WithdrawalTransaction extends BaseTransaction {
    private boolean reversed = false;
    private double shortfall = 0;

    public WithdrawalTransaction(double amount, @NotNull Calendar date) {
        super(amount, date);
    }

    private boolean checkWithdrawalAmount(double amt) {
        return amt >= 0;
    }

    @Override
    public void printTransactionDetails() {
        System.out.println("Withdrawal Transaction: " + this.toString());
    }

    public boolean reverse(BankAccount ba) {
        if (!reversed) {
            ba.deposit(getAmount() - shortfall);
            reversed = true;
            System.out.println("Transaction reversed successfully.");
            return true;
        } else {
            System.out.println("Transaction has already been reversed.");
            return false;
        }
    }

    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        if (!checkWithdrawalAmount(getAmount())) {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }

        double currentBalance = ba.getBalance();
        if (currentBalance >= getAmount()) {
            ba.withdraw(getAmount());
            System.out.println("Withdrawal of " + getAmount() + " completed.");
        } else if (currentBalance > 0) {
            shortfall = getAmount() - currentBalance;
            ba.withdraw(currentBalance);
            System.out.println("Partial withdrawal of " + currentBalance + ". Shortfall: " + shortfall);
        } else {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
    }

    public void apply(BankAccount ba, boolean safeMode) {
        try {
            apply(ba);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Withdrawal attempt completed.");
        }
    }
}
