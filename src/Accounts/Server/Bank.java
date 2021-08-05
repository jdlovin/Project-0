package Accounts.Server;

public class Bank {
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private int balance;
//    int balance = 0;

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
