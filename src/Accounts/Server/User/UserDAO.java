package Accounts.Server.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
    List<User> getUsers() throws SQLException;
    User userById(int id) throws SQLException;
}
