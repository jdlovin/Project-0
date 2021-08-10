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

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {
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
            System.out.println("2. Saving");
            System.out.println("3. Employees");
            System.out.println("4. Users");
            System.out.println("9. Exit");
            Scanner menuScan = new Scanner(System.in);
            System.out.println();
            System.out.println("Please choose an option");
            if (menuScan.hasNextInt());
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
                            int userId =menuScan.nextInt();
                            user.setId(userId);
                            System.out.print("How much do you want to deposit into the account?");
                            int newAccountBalance = menuScan.nextInt();
                            //new account goes here
                            account.setBalance(newAccountBalance);
                            String pendingStatus = "Y";
                            account.setPendingAccount(pendingStatus);
                            account.setId(userId);
                            user.setChecking_account_number(userId);
                            accountsDAO.addAccount(account);
                            userDAO.addCheckingAccount(user);
                            System.out.println("Pending status");




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
                    System.out.println("3. List of employees");
                    System.out.println("4. Find employee by ID #");
                    System.out.println("5. List of user accounts");
                    System.out.println("6. Delete a user");
                    System.out.println("7. List of users");
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
                            // Get an employee by their ID

                            System.out.println("Employee search");
                            System.out.println();
                            System.out.print("Enter employee ID: ");
                            int empId = menuScan.nextInt();
                            Employee employeeById = dao.employeeById(empId);
                            System.out.println(employeeById);
                            break;
                        case 5:
                            //Gets a list of user accounts

                            System.out.println("List of user accounts");
                            List<Account> accounts = accountsDAO.getAccounts();
                            for (Account accountList : accounts) {
                                System.out.println(accountList);
                            }
                            break;
                        case 6:
                            //delete user
                            System.out.println("Had a bad line of credit");
                            System.out.println("Delete User");
                            int deleteUserId = menuScan.nextInt();
                            user.setId(deleteUserId);
                            userDAO.deleteUser(user.getId());
                            break;
                        case 7:
                            //List of users

                            System.out.println("List of users");
                            List<User> users = userDAO.getUsers();
                            for (User userList : users) {
                                System.out.println(userList);
                            }
                            break;
                        case 8:
                            //List of accounts that are pending
                            
                    }
                    break;
                case 4:
                    //Users

                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println("User Menu");
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println("1. Add User");
                    System.out.println("2. Update User");
                    System.out.println("3. Login");
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.print("Select an option:");
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
                        case 3:
                            //Login
                    }
                    break;

                case 9:
                    //Quit

                    break;
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

// //Withdraw
//
//                            //Is adding to memory and subtracting from the DB
//
//                            System.out.println("Withdraw");
//                            System.out.print("How much would you like to withdraw?");
//                            int withdrawCheck = menuScan.nextInt();
//                            System.out.print("Which account number is this?");
//                            int checkWithID = menuScan.nextInt();
//                            account.setId(checkWithID);
//                            account.withdraw(account.getBalance() - withdrawCheck);
//                            account.setBalance(withdrawCheck);
//                            accountsDAO.withdrawAccount(account);
//                            System.out.println("You with withdrew $" + withdrawCheck);
//

//    //Deposit
//
//                            System.out.println("Deposit");
//                            System.out.print("How much would you like to deposit?");
//                            int depositCheck = menuScan.nextInt();
//                            System.out.print("Which account number is this?");
//                            int checkDepId = menuScan.nextInt();
//                            account.setId(checkDepId);
//                            account.deposit(depositCheck);
//                            accountsDAO.depositAccount(account);
//                            System.out.println("You deposited $" + depositCheck);
//                            System.out.println();

// //Transfer
//                            System.out.println("Withdraw");
//                            System.out.print("How much would you like to transfer?");
//                            int transferCheck = menuScan.nextInt();
//                            System.out.print("Which account number are you transferring from?");
//                            int transferWithID = menuScan.nextInt();
//                            account.setId(transferWithID);
////                            account.withdraw(account.getBalance() - transferCheck);
//                            account.setBalance(transferCheck);
//                            accountsDAO.withdrawAccount(account);
//                            System.out.println("You with withdrew $" + transferCheck);
//                            //Start deposit
//
//                            System.out.print("Which account number is this transferring to?");
//                            int transferToId = menuScan.nextInt();
//                            account.setId(transferToId);
////                            account.deposit(transferCheck);
//                            account.setBalance(transferCheck);
//                            accountsDAO.depositAccount(account);
//                            System.out.println("You deposited $" + transferCheck);
//                            System.out.println();
//
//                            System.out.println("Transfer");