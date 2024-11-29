package Lecture4_interfaces_abstract_classes;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar date = Calendar.getInstance();

        // Create a BankAccount object
        BankAccount account = new BankAccount(500);

        // Create DepositTransaction
        DepositTransaction deposit = new DepositTransaction(200, date);

        // Create WithdrawalTransaction
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(700, date);

        // Test DepositTransaction
        System.out.println("Before Deposit:");
        account.printAccountDetails();
        deposit.apply(account);
        System.out.println("After Deposit:");
        account.printAccountDetails();

        // Test WithdrawalTransaction
        System.out.println("\nBefore Withdrawal:");
        account.printAccountDetails();

        try {
            withdrawal.apply(account);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("After Withdrawal:");
        account.printAccountDetails();

        // Test Reversal
        System.out.println("\nReversing Withdrawal:");
        withdrawal.reverse(account);
        account.printAccountDetails();
    }
}
