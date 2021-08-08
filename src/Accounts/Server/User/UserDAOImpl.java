package Accounts.Server.User;

import Accounts.Server.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
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
    public void updateUser(User user) throws SQLException {
        String sql = "update users set firstName = ?, lastName = ?, email = ?, passWord = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassWord());
        preparedStatement.setInt(5, user.getId());
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("User updated");
        else
            System.out.println("Oh no! Something went wrong");
    }

    @Override
    public void deleteUser(int id) throws SQLException {
    String sql = "delete from users where id =?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, id);
    int count = preparedStatement.executeUpdate();
    if (count > 0)
        System.out.println("User Deleted");
    else
        System.out.println("Oh no! Something went wrong");
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String email = resultSet.getString(4);
            String userName = resultSet.getString(5);
            String passWord = resultSet.getString(6);
            User user = new User(id, firstName, lastName, email, userName, passWord);
            users.add(user);
        }
        return users;
    }

    @Override
    public User userById(int id) {
        return null;
    }
}
