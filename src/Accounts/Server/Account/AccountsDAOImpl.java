package Accounts.Server.Account;

import Accounts.Server.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsDAOImpl implements AccountsDAO {


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
        String sql = "insert into accounts (account_number, balance, opening_balance, id) values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getAccount_number());
        preparedStatement.setInt(2, account.getBalance());
        preparedStatement.setInt(3, account.getOpening_balance());
        preparedStatement.setInt(4, account.getId());
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Account added");
        else
            System.out.println("Oh no! Something happened");
    }

//    @Override
//    public void checkAccount(Account account) throws SQLException {
////        String sql = "select balance from accounts where id = ?";
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
////        preparedStatement.setInt(1, account.getId());
////        ResultSet count = preparedStatement.executeQuery();
////        return account;
//    }

    @Override
    public void depositAccount(Account account) throws SQLException {
        String sql = "update accounts set balance = balance + ? where id = ?";
        String sql2 = "commit";
        BalanceThread balanceThread = new BalanceThread(account);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getBalance());
        preparedStatement.setInt(2, account.getId());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account updated");
        } else {
            System.out.println("Unsuccessful update :(");
        }
    }

    @Override
    public void withdrawAccount(Account account) throws SQLException {
        String sql = "update accounts set balance = balance - ? where id = ?";
        String sql2 = "commit";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getBalance());
        preparedStatement.setInt(2, account.getId());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account updated");
        } else {
            System.out.println("Unsuccessful update :(");
        }
    }

    @Override
    public void deleteAccount(Account account) {

    }


    @Override
    public List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from accounts";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int account_number = resultSet.getInt(1);
            int balance = resultSet.getInt(2);
            int opening_balance = resultSet.getInt(3);
            int id = resultSet.getInt(4);
            Account account = new Account(account_number, balance, opening_balance, id);
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
            account = new Account(account_number, balance, opening_balance, id);
        } else {
            System.out.println("No account found");
        }
        return account;
    }
}
