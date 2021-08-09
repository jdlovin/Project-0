package Accounts.Server.Account;

import java.sql.SQLException;

public interface AccountsDAO {
    void addAccount(Account account) throws SQLException;
    void depositAccount(Account account) throws SQLException;
    void withdrawAccount(Account account) throws SQLException;
    void deleteAccount(Account account);
    void getAccounts(Account account) throws SQLException;

    void getAccounts() throws SQLException;

    void accountByID(Account account) throws SQLException;
}
