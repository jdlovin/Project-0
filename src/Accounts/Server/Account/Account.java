package Accounts.Server.Account;

import Accounts.Server.Bank;

public class Account {
    private int account_number;
    private int balance;
    private int opening_balance;
    private int id;
    private String pendingAccount;

    public Account() {

    }

    public Account(int account_number, int balance, int opening_balance, int id, String pendingAccount) {
        this.account_number = account_number;
        this.balance = balance;
        this.opening_balance = opening_balance;
        this.id = id;
        this.pendingAccount = pendingAccount;
    }

    public Account(int balance) {
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getOpening_balance() {
        return opening_balance;
    }

    public void setOpening_balance(int opening_balance) {
        this.opening_balance = opening_balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account { Account Number: " + account_number + ", Balance: " + balance + ", Pending: " + pendingAccount + " }";
    }


    public synchronized void withdraw(int amount) {
        System.out.println("Withdrawal processing, please wait . . . ");
        if (this.balance < amount) {
            System.out.println("Unable to process, insufficient funds");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.balance -= amount;
            System.out.println("Withdrawal complete");
        }
    }

    public synchronized void deposit(int amount) {
        System.out.println("Deposit processing, please wait . . . ");
        balance += amount;
        System.out.println("Deposit completed");
        notify();
    }

    public String getPendingAccount() {
        return pendingAccount;
    }

    public void setPendingAccount(String pendingAccount) {
        this.pendingAccount = pendingAccount;
    }
}

class BalanceThread extends Thread {
    Account account;

    public BalanceThread(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.deposit(account.getBalance());
    }
}


