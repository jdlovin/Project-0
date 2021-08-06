package Accounts.Server.User;

import Accounts.Server.ConnectionFactory;

import java.sql.*;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    private static final Statement statement = null;
    Connection connection = null;

    public UserDAOImpl(){
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void addUser(User user) throws SQLException {
        String sql = "insert into users (firstName, lastName, email, userName, passWord) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getUserName());
        preparedStatement.setString(5, user.getPassWord());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("User added");
        else
            System.out.println("Oh no! Something went wrong");
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User userById(int id) {
        return null;
    }
}
