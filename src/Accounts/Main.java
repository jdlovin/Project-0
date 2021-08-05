package Accounts;

import Accounts.Server.Bank;
import Accounts.Server.Employee;
import Accounts.Server.EmployeeDAO;
import Accounts.Server.EmployeeDOAFactory;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

class WithdrawThread extends Thread {
    Bank bank;

    public WithdrawThread(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.withdraw(bank.getBalance());
    }
}

class BalanceThread extends Thread {
    Bank bank;

    public BalanceThread(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.deposit(bank.getBalance());
    }
}

public class Main {
    public static void main(String[] args) throws SQLException {
        EmployeeDAO dao = EmployeeDOAFactory.getEmployeeDao();
        Employee employee = new Employee();
        Bank bank = new Bank();

        int menuSelection = -1;
        while (menuSelection != 9) { //change this to a true statement

            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Welcome to Prestige Worldwide!");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("What would you like to do today?");
            System.out.println("1. Checking");
            System.out.println("2. Saving");
            System.out.println("3. Employees");
            System.out.println("9. Exit");
            Scanner menuScan = new Scanner(System.in);
            System.out.println();
            System.out.println("Please choose an option");
            if (menuScan.hasNextInt());
            menuSelection = menuScan.nextInt();

            switch (menuSelection) {
                case 1:
                    // Checking

                    System.out.println("Checking");
                    System.out.println();
                    System.out.println("What would you like to do?");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Deposit");
                    System.out.println("4. Transfer");
                    menuSelection = menuScan.nextInt();
                    switch (menuSelection) {
                        case 1:
                            //Check Balance

                            System.out.println("Check balance");
                            System.out.println("Your balance is: " + bank.getBalance());
                            break;
                        case 2:
                            //Withdraw

                            System.out.println("Withdraw");
                            System.out.println("How much would you like to withdraw?");
                            int withdrawCheck = menuScan.nextInt();
                            bank.withdraw(withdrawCheck);
                            System.out.println("You with withdrew $" + withdrawCheck);
                            bank.setBalance(bank.getBalance() - withdrawCheck);
                            System.out.println("Your new balance is: " + bank.getBalance());

                            //If there are no funds, it will not be able to restart

                            break;
                        case 3:
                            //Deposit

                            System.out.println("Deposit");
                            System.out.println("How much would you like to deposit?");
                            int depositCheck = menuScan.nextInt();
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
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println("Employee Menu");
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println("1. Add Employee");
                    System.out.println("2. Update Employee");
                    System.out.println("3. Delete Employee");
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.print("Select an option:");
                    menuSelection = menuScan.nextInt();
                    //Employee creation
                    switch (menuSelection) {
                        case 1:

                               //Add Employee
                               System.out.print("First name: ");
                               String firstName = menuScan.next();
                               employee.setFirstName(firstName);
                               System.out.print("Last name: ");
                               String lastName = menuScan.next();
                               employee.setLastName(lastName);
                               System.out.print("Email: ");
                               String email = menuScan.next();
                               employee.setEmail(email);
                               System.out.print("Username: ");
                               String userName = menuScan.next();
                               employee.setUserName(userName);
                               System.out.print("Password: ");
                               String passWord = menuScan.next();
                               employee.setPassWord(passWord);
                               dao.addEmployee(employee);

                            break;
                        case 2:
                            //Update Employee
                            System.out.println("Update employee");
                            System.out.print("First name: ");
                            String newFirstName = menuScan.next();
                            employee.setFirstName(newFirstName);
                            System.out.print("Last name: ");
                            String newLastName = menuScan.next();
                            employee.setLastName(newLastName);
                            System.out.print("Email: ");
                            String newEmail = menuScan.next();
                            employee.setEmail(newEmail);
                            System.out.print("Password: ");
                            String newPassWord = menuScan.next();
                            employee.setPassWord(newPassWord);
                            System.out.print("ID: ");
                            int id = menuScan.nextInt();
                            employee.setId(id);
                            dao.updateEmployee(employee);
                            break;
                        case 3:
                            //Delete Employee
                            System.out.println("Delete employee");
                            int deleteId = menuScan.nextInt();
                            employee.setId(deleteId);
                            dao.deleteEmployee(employee.getId());
                            break;
                        case 4:
                            //Get a list of employees

                            System.out.println("List of employees");
                            List<Employee>
                    }
                    break;
                case 9:
                    //Quit

                    break;
            }
        }
    }
}
