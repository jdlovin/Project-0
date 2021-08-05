package Accounts.Server;

public class Bank {
    private int balance;

    //Get current balance
    public int getBalance() {
        return balance;
    }

    //Set current balance
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) {
        System.out.println("Withdrawal processing, please wait . . . ");
        if (balance < amount) {
            System.out.println("Unable to process, insufficient funds");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println("Withdrawal complete");
        }
    }

    public synchronized void deposit(int amount) {
        System.out.println("Deposit processing, please wait . . . ");
        balance += amount;
        System.out.println("Deposit completed");
        notify();
    }
}
