package Accounts.Server.Account;

import Accounts.Server.ConnectionFactory;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsDAOImpl implements AccountsDAO {

    private static final Logger logger = LogManager.getLogger(AccountsDAOImpl.class);

    private static final Statement statement = null;
    Connection connection = null;

    public AccountsDAOImpl() {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addAccount(Account account) throws SQLException {
        String sql = "insert into accounts (balance, id, pendingAccount) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getBalance());
        preparedStatement.setInt(2, account.getId());
        preparedStatement.setString(3, account.getPendingAccount());
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Account added");
        else
            System.out.println("Oh no! Something happened");
    }

    @Override
    public Account checkAccount(int accountNumber) throws SQLException {
        Account account = new Account();
        String sql = "select * from accounts where account_number = " + accountNumber;
        Statement statement = connection.createStatement();
        ResultSet count = statement.executeQuery(sql);

        if (count.next()) {
            int account_number = count.getInt(1);
            int balance = count.getInt(2);
            int opening_balance = count.getInt(3);
            int id = count.getInt(4);
            String pendingAccount = count.getString(5);
            String pendingTransferIn = count.getString(6);
            int pendingTransferAmount = count.getInt(7);
            account = new Account(account_number, balance, opening_balance, id, pendingAccount, pendingTransferIn, pendingTransferAmount);
        } else {
            System.out.println("Something happened");
        }
        return account;
    }

    @Override
    public void depositAccount(Account account) throws SQLException {
        String sql = "update accounts set balance = balance + ? where account_number = ?";
        String sql2 = "commit";
        BalanceThread balanceThread = new BalanceThread(account);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getBalance());
        preparedStatement.setInt(2, account.getAccount_number());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account updated");
        } else {
            System.out.println("Unsuccessful update :(");
        }
    }

    @Override
    public void withdrawAccount(Account account) throws SQLException {
        String sql = "update accounts set balance = balance - ? where account_number = ?";
        String sql2 = "commit";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getBalance());
        preparedStatement.setInt(2, account.getAccount_number());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account updated");
        } else {
            System.out.println("Unsuccessful update :(");
        }
    }

    @Override
    public void deleteAccount(int account_number) throws SQLException {
        String sql = "delete from accounts where account_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account_number);
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Account Rejected");
        else
            System.out.println("Something went wrong");
    }

    @Override
    public void approveAccount(Account account) throws SQLException {
        String sql = "update accounts set pendingAccount = ? where account_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account.getPendingAccount());
        preparedStatement.setInt(2, account.getAccount_number());
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Account Approved!");
        else
            System.out.println("Something didn't work");
    }

    @Override
    public void pendingTransfer(Account account) throws SQLException {
        String sql = "update accounts set pendingTransferIn = ?, pendingTransferAmount = pendingTransferAmount + ? where account_number = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, account.getPendingTransferIn());
    preparedStatement.setInt(2, account.getPendingTransferAmount());
    preparedStatement.setInt(3, account.getAccount_number());
    int count = preparedStatement.executeUpdate();
    if (count > 0)
        System.out.println("Transfer sent");
    else
        System.out.println("Please check account number");
    }

    @Override
    public void checkIncomingTransfer(Account account) throws SQLException {
        String sql = "update accounts set balance = balance + ?, pendingTransferIn = ?, pendingTransferAmount = pendingTransferAmount - ?  where account_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getBalance());
        preparedStatement.setString(2, account.getPendingTransferIn());
        preparedStatement.setInt(3, account.getPendingTransferAmount());
        preparedStatement.setInt(4, account.getAccount_number());
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Transfer successful");
        else
            System.out.println("Something happened");
    }

    @Override
    public List<Account> pendingAccounts() throws SQLException {
        List<Account> accountsPending = new ArrayList<>();
        String sql = "select * from accounts where pendingAccount = 'y'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int account_number = resultSet.getInt(1);
            int balance = resultSet.getInt(2);
            int id = resultSet.getInt(3);
            int opening_balance = resultSet.getInt(4);
            String pendingAccount = resultSet.getString(5);
            String pendingTransferIn = resultSet.getString(6);
            int pendingTransferAmount = resultSet.getInt(7);
            Account account = new Account(account_number, balance, id, opening_balance, pendingAccount, pendingTransferIn, pendingTransferAmount);
            accountsPending.add(account);
        }


        return accountsPending;
    }


    @Override
    public List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "call get_accounts";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int account_number = resultSet.getInt(1);
            int balance = resultSet.getInt(2);
            int opening_balance = resultSet.getInt(3);
            int id = resultSet.getInt(4);
            String pendingAccount = resultSet.getNString(5);
            String pendingTransferIn = resultSet.getString(6);
            int pendingTransferAmount = resultSet.getInt(7);
            Account account = new Account(account_number, balance, opening_balance, id, pendingAccount, pendingTransferIn, pendingTransferAmount);
            accounts.add(account);
        }
        return accounts;
    }


    @Override
    public Account accountByID(int accountId) throws SQLException {
        Account account = new Account();
        String sql = "select * from accounts where id = " + accountId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            int account_number = resultSet.getInt(1);
            int balance = resultSet.getInt(2);
            int opening_balance = resultSet.getInt(3);
            int id = resultSet.getInt(4);
            String pendingAccount = resultSet.getNString(5);
            String pendingTransferIn = resultSet.getString(6);
            int pendingTransferAmount = resultSet.getInt(7);
            account = new Account(account_number, balance, opening_balance, id, pendingAccount, pendingTransferIn, pendingTransferAmount);
        } else {
            System.out.println("No account found");
        }
        return account;
    }
}
