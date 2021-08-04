package Accounts;

import java.util.Scanner;

class Bank {
    int balance = 0;

    synchronized void withdraw(int amount) {
        System.out.println("Withdrawal processing, please wait . . . ");
        if(balance < amount) {
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

    synchronized void deposit(int amount) {
        System.out.println("Deposit processing, please wait . . . ");
        balance += amount;
        System.out.println("Deposit completed");
        notify();
    }
}

class WithdrawThread extends Thread {
    Bank bank;

    public WithdrawThread(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.withdraw(0);
    }
}

class BalanceThread extends Thread {
    Bank bank;

    public BalanceThread(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.deposit(0);
    }
}

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        int menuSelection = -1;
        while (menuSelection != 9) { //change this to a true statement
//        System.out.println("  |||\\              ");
//        System.out.println("  |//////               ");
//        System.out.println("  |//   //             ");
//        System.out.println("  |/////                ");
//        System.out.println("  ||                ");
//        System.out.println("  ||                   ");
//        System.out.println("  ||                   ");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Welcome to Prestige Worldwide!");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("What would you like to do today?");
            System.out.println("1. Checking");
            System.out.println("2. Saving");
            System.out.println("9. Exit");
            Scanner menuScan = new Scanner(System.in);
            System.out.println();
            System.out.println("Please choose an option");
            if (menuScan.hasNextInt()) ;
            menuSelection = menuScan.nextInt();

            switch (menuSelection) {
                case 1:
                    // Checking

                    System.out.println("Checking");
                    Scanner checkingScanner = new Scanner(System.in);
                    System.out.println();
                    System.out.println("What would you like to do?");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Deposit");
                    System.out.println("4. Transfer");
                    menuSelection = checkingScanner.nextInt();
                    switch(menuSelection) {
                        case 1:
                            //Check Balance

                            System.out.println("Check balance");
                            System.out.println("Your balance is: " + bank.balance);
                            break;
                        case 2:
                            //Withdraw

                            System.out.println("Withdraw");
                            System.out.println("How much would you like to withdraw?");
                            Scanner checkWithScanner = new Scanner(System.in);
                            int withdrawCheck = checkWithScanner.nextInt();
                            bank.withdraw(withdrawCheck);
                            System.out.println("You with withdrew $" + withdrawCheck);
                            System.out.println();

                            //If there are no funds, it will not be able to restart
                            break;
                        case 3:
                            //Deposit

                            System.out.println("Deposit");
                            System.out.println("How much would you like to deposit?");
                            Scanner checkDepositScanner = new Scanner(System.in);
                            int depositCheck = checkDepositScanner.nextInt();
                            bank.deposit(depositCheck);
                            System.out.println("You deposited $" + depositCheck);
                            System.out.println();
                            break;
                        case 4:
                            //Transfer

                            System.out.println("Transfer");
                            break;
                    }
                    break;
                case 2:
                    //Savings

                    System.out.println("Saving");
                    break;
                case 3:
                    //Quit

                    break;
            }
        }
    }
}
