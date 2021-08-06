package Accounts.Server.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUser(User user) throws SQLException;
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getUsers();
    User userById(int id);
}
