package Accounts.Server.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountsDAO {
    void addAccount(Account account) throws SQLException;
    void depositAccount(Account account) throws SQLException;
    void withdrawAccount(Account account) throws SQLException;
    void deleteAccount(Account account);
    List<Account> getAccounts();
    Account accountByID(int id) throws SQLException;
}
