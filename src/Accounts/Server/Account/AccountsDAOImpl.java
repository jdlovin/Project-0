package Accounts.Server.Account;

import Accounts.Server.ConnectionFactory;

import java.sql.*;
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

    @Override
    public void depositAccount(Account account) throws SQLException {
        String sql = "update accounts set balance = balance + ? where id = ?";
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
    public List<Account> getAccounts() {


        return null;
    }

    @Override
    public Account accountByID(int id) throws SQLException {
        Account account = new Account();
        String sql = "select * from accounts where id = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();


        return null;
    }
}
