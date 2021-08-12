package Accounts;

import Accounts.Server.Account.Account;
import Accounts.Server.Account.AccountDAOFactory;
import Accounts.Server.Account.AccountsDAO;
import Accounts.Server.Employee.Employee;
import Accounts.Server.Employee.EmployeeDAO;
import Accounts.Server.Employee.EmployeeDOAFactory;
import Accounts.Server.User.User;
import Accounts.Server.User.UserDAO;
import Accounts.Server.User.UserDAOFactory;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader( new FileReader( "transactionLog.txt"));


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        FileWriter logger = new FileWriter("transactionLog.txt", true);
        logger.write(dateFormat.format(date) + "\n" + "App Started \n");


        AccountsDAO accountsDAO = AccountDAOFactory.getAccountDao();
        EmployeeDAO dao = EmployeeDOAFactory.getEmployeeDao();
        UserDAO userDAO = UserDAOFactory.getUserDao();
        Employee employee = new Employee();
        User user = new User();
        Account account = new Account();

        int menuSelection = -1;
        while (menuSelection != 9) { //change this to a true statement

            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Welcome to Prestige Worldwide!");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("What would you like to do today?");
            System.out.println("1. Register");
            System.out.println("2. Employees");
            System.out.println("3. Users");
            System.out.println("9. Exit");
            Scanner menuScan = new Scanner(System.in);
            System.out.println();
            System.out.println("Please choose an option");
            if (menuScan.hasNextInt()) ;
            menuSelection = menuScan.nextInt();

            switch (menuSelection) {
                case 1:
                    // Register

                    System.out.println("Register");
                    System.out.println();
                    System.out.println("What would you like to do?");
                    System.out.println("1. Create user account");
                    System.out.println("2. Create another bank account");
                    menuSelection = menuScan.nextInt();
                    switch (menuSelection) {
                        case 1:
                            //Add User

                            System.out.print("First name: ");
                            String firstName = menuScan.next();
                            user.setFirstName(firstName);
                            System.out.print("Last name: ");
                            String lastName = menuScan.next();
                            user.setLastName(lastName);
                            System.out.print("Email: ");
                            String email = menuScan.next();
                            user.setEmail(email);
                            System.out.print("Username: ");
                            String userName = menuScan.next();
                            user.setUserName(userName);
                            System.out.print("Password: ");
                            String passWord = menuScan.next();
                            user.setPassWord(passWord);
                            userDAO.addUser(user);
                            break;
                        case 2:
                            //Add a bank account - just add minimum balance exception
                            System.out.print("Create a bank account");
                            System.out.println();
                            System.out.print("What is your user id?");
                            int userId = menuScan.nextInt();
                            user.setId(userId);
                            System.out.print("How much do you want to deposit into the account?");
                            int newAccountBalance = menuScan.nextInt();
                            if (newAccountBalance >= 100) {
                                //new account goes here
                                account.setBalance(newAccountBalance);
                                String pendingStatus = "Y";
                                account.setPendingAccount(pendingStatus);
                                account.setId(userId);
                                user.setChecking_account_number(userId);
                                accountsDAO.addAccount(account);
                                userDAO.addCheckingAccount(user);
                                System.out.println("Pending status");
                            } else {
                                System.out.println("Sorry, the minimum deposit is $100");
                                System.out.println();
                                System.out.println("Have a nice day!");
                                System.out.println();
                                System.out.println();
                            }
                            break;
// This is all set from here, up
                    }
                    break;
                case 2:
                    System.out.println("Please Login to your employee account");
                    System.out.println();
                    System.out.print("Employee ID: ");
                    int loginId = menuScan.nextInt();
                    System.out.println();
                    System.out.println();
                    System.out.print("Password :");
                    String loginPassword = menuScan.next();
                    employee.setId(loginId);
                    Employee employee1 = dao.employeeLogin(loginId);
                    System.out.println(employee1);


                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println("Employee Menu");
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println("1. Add Employee");
                    System.out.println("2. Update Employee");
                    System.out.println("3. List of employees");
                    System.out.println("4. List of user accounts");
                    System.out.println("5. Delete a user");
                    System.out.println("6. List of users");
                    System.out.println("7. View pending accounts");
                    System.out.println("8 View past transactions");
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
                            //Get a list of employees
                            System.out.println("List of employees");
                            List<Employee> employees = dao.getEmployees();
                            for (Employee employeeList : employees) {
                                System.out.println(employeeList);
                            }
                            break;
                        case 4:
                            //Gets a list of user accounts

                            System.out.println("List of user accounts");
                            List<Account> accounts = accountsDAO.getAccounts();
                            for (Account accountList : accounts) {
                                System.out.println(accountList);
                            }
                            break;
                        case 5:
                            //delete user
                            System.out.println("Had a bad line of credit");
                            System.out.println("Delete User");
                            int deleteUserId = menuScan.nextInt();
                            user.setId(deleteUserId);
                            userDAO.deleteUser(user.getId());
                            break;
                        case 6:
                            //List of users

                            System.out.println("List of users");
                            List<User> users = userDAO.getUsers();
                            for (User userList : users) {
                                System.out.println(userList);
                            }
                            break;
                        case 7:
                            //List of accounts that are pending
                            System.out.println("List of pending accounts");
                            System.out.println();
                            List<Account> pendingAccounts = accountsDAO.pendingAccounts();
                            for (Account pendingList : pendingAccounts) {
                                System.out.println(pendingList);
                            }

                            System.out.println("Do you want to approve any of these accounts?");
                            String pendingDecision = menuScan.next();
                            if (pendingDecision.equals("yes")) {
                                System.out.println("Which account would like to approve");
                                System.out.print("What account number is it?");
                                int pendingNumber = menuScan.nextInt();
                                account.setAccount_number(pendingNumber);
                                String pendingStatus = "N";
                                account.setPendingAccount(pendingStatus);
                                accountsDAO.approveAccount(account);
                                System.out.println("Account approved!");

                            } else {
                                System.out.println("Account rejection");
                                System.out.println();
                                System.out.println("Which account are you rejecting?");
                                System.out.print("Enter account number");
                                int pendingNumber = menuScan.nextInt();
                                accountsDAO.deleteAccount(pendingNumber);
                            }
                            break;
                        case 8:
                            //View log
                            System.out.println("Viewing transaction log");
                            String contentLine = reader.readLine();
                            while (contentLine != null) {
                                System.out.println(contentLine);
                                contentLine = reader.readLine();
                            }
                            break;
                    }
                    break;
                case 3:
                    //Users

                    System.out.println("Please Login to your account");
                    System.out.println();
                    System.out.print("User ID: ");
                    int userLoginId = menuScan.nextInt();
                    System.out.println();
                    System.out.println();
                    System.out.print("Password :");
                    String userLoginPassword = menuScan.next();
                    user.setId(userLoginId);
                    User user1 = userDAO.loginAccount(userLoginId);
                    System.out.println(user1);

                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println("User Menu");
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println("1. Update User");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Deposit");
                    System.out.println("4. Transfer");
                    System.out.println("5. Check Balance");
                    System.out.println("6. View transfers");

                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.print("Select an option:");
                    menuSelection = menuScan.nextInt();

                    switch (menuSelection) {
                        case 1:
                            //Update User

                            System.out.print("First name: ");
                            String newFirstName = menuScan.next();
                            user.setFirstName(newFirstName);
                            System.out.print("Last name: ");
                            String newLastName = menuScan.next();
                            user.setLastName(newLastName);
                            System.out.print("Email: ");
                            String newEmail = menuScan.next();
                            user.setEmail(newEmail);
                            System.out.print("Username: ");
                            String newUserName = menuScan.next();
                            user.setUserName(newUserName);
                            System.out.print("Password: ");
                            String newPassWord = menuScan.next();
                            user.setPassWord(newPassWord);

                            System.out.print("ID: ");
                            int id = menuScan.nextInt();
                            user.setId(id);
                            userDAO.updateUser(user);
                            break;
                        case 2:
                            //Withdraw

                            //Is adding to memory and subtracting from the DB

                            System.out.println("Withdraw");
                            System.out.print("How much would you like to withdraw?");
                            int withdrawCheck = menuScan.nextInt();
                            System.out.print("Which account number is this?");
                            int checkWithAccount = menuScan.nextInt();
                            account.setAccount_number(checkWithAccount);
//                            account.withdraw(account.getBalance() - withdrawCheck);
                            account.setBalance(withdrawCheck);
                            if (withdrawCheck > 0) {
                                accountsDAO.withdrawAccount(account);
                                System.out.println("You with withdrew $" + withdrawCheck);
                            } else {
                                System.out.println("Can't input a negative amount");
                            }

                            //Logging Withdrawals
                            logger.write(dateFormat.format(date) + "\n" + "Withdrawal \n");
                            logger.write("Amount withdrew: $" + withdrawCheck + ", Account Number: " + checkWithAccount + "\n");
                            logger.flush();
                            break;

                            
                        case 3:
                            //Deposit

                            System.out.println("Deposit");
                            System.out.print("How much would you like to deposit?");
                            int depositCheck = menuScan.nextInt();
                            System.out.print("Which account number is this?");
                            int checkDepId = menuScan.nextInt();
                            account.setAccount_number(checkDepId);
//                            account.deposit(depositCheck);
                            if (depositCheck > 0) {
                                account.setBalance(depositCheck);
                                accountsDAO.depositAccount(account);
                                System.out.println("You deposited $" + depositCheck);
                                System.out.println();
                            } else {
                                System.out.println("Can't input a negative amount");
                            }

                            //Deposit logger
                            logger.write(dateFormat.format(date) + "\n" + "Deposit \n");
                            logger.write("Amount withdrew: $" + depositCheck + ", Account Number: " + checkDepId + "\n");
                            logger.flush();

                            break;
                        case 4:
                            //Transfer
                            System.out.println("Withdraw");
                            System.out.print("How much would you like to transfer?");
                            int transferCheck = menuScan.nextInt();
                            if (transferCheck > 0) {
                                System.out.print("Which account number are you transferring from?");
                                int transferWithAccount = menuScan.nextInt();
                                account.setAccount_number(transferWithAccount);
//                            account.withdraw(account.getBalance() - transferCheck);
                                account.setBalance(transferCheck);
                                accountsDAO.withdrawAccount(account);
                                System.out.println("You with withdrew $" + transferCheck);

                                //Transfer to account
                                String pending = "pending";
                                account.setPendingTransferIn(pending);



                                //Start deposit

                                System.out.print("Which account number is this transferring to?");
                                int transferToId = menuScan.nextInt();
                                account.setId(transferToId);
                                account.setAccount_number(transferToId);
                                account.setPendingTransferAmount(transferCheck);
//                            account.deposit(transferCheck);
                                account.setBalance(transferCheck);
                                accountsDAO.depositAccount(account);
                                accountsDAO.pendingTransfer(account);
                                System.out.println("You deposited $" + transferCheck);
                                System.out.println();

                                //Transfer Log
                                logger.write(dateFormat.format(date) + "\n" + "Transfer \n");
                                logger.write("Amount transferred: $" + transferCheck + "from Account Number: " + transferWithAccount + ", to Account Number: " + transferToId + "\n");
                                logger.flush();



                            } else {
                                System.out.println("Can't input a negative amount");
                            }
                            System.out.println("Transfer");
                            break;
                        case 5:
                            System.out.println("Check balance");
                            System.out.println();
                            System.out.println("Enter account number");
                            int accountNumber = menuScan.nextInt();
                            Account accountBalance = accountsDAO.checkAccount(accountNumber);
                            System.out.println(accountBalance);
                            break;
                        case 6:
                            //View pending
                            System.out.println("Handle incoming transfer");
                            System.out.println();
                            System.out.println("Enter account number");
                            int accountTransferNumber = menuScan.nextInt();
                            Account accountTransfer = accountsDAO.checkAccount(accountTransferNumber);
                            System.out.println(accountTransfer);
                            System.out.println("Would you like to handle any incoming transfers?");
                            System.out.println();
                            System.out.println("Press 1 for yes");
                            System.out.println("Press 2 for no");
                            int transferSelection = menuScan.nextInt();
                            if(transferSelection == 1) {
                                System.out.println("Please confirm the amount you are transferring in");
                                System.out.print("Amount:");
                                int transferIn = menuScan.nextInt();
                                account.setPendingTransferAmount(transferIn);
                                account.setAccount_number(accountTransferNumber);
                                account.setBalance(transferIn);
                                String setStatus = "None";
                                account.setPendingTransferIn(setStatus);
                                accountsDAO.checkIncomingTransfer(account);
                                System.out.println();
                                System.out.println("Transfer complete");

                                System.out.println("You transferred in: " + transferIn);

                                //Incoming transfer logger
                                logger.write(dateFormat.format(date) + "\n" + "Deposit \n");
                                logger.write("Amount accepted $" + transferIn + ", Account Number: " + accountTransferNumber + "\n");
                                logger.flush();
                            } else {
                                System.out.println("Have a nice day");
                            }
                    }
                    break;

                case 9:
                    //Quit
                    logger.close();
                    break;
                default:
                    System.out.println("Please choose a number");
            }
        }
    }
}
//
//// Checking
//
//                    System.out.println("Checking");
//                            System.out.println();
//                            System.out.println("What would you like to do?");
//                            System.out.println("1. Check Balance");
//                            System.out.println("2. Withdraw");
//                            System.out.println("3. Deposit");
//                            System.out.println("4. Transfer");
//                            menuSelection = menuScan.nextInt();
