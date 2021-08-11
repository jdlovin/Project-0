package Accounts.Server.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountsDAO {
    Account checkAccount(int account_number) throws SQLException;
    void addAccount(Account account) throws SQLException;
    void depositAccount(Account account) throws SQLException;
    void withdrawAccount(Account account) throws SQLException;
    void deleteAccount(int account_number) throws SQLException;
    void approveAccount(Account account) throws SQLException;
    List<Account> pendingAccounts() throws SQLException;
    List<Account> getAccounts() throws SQLException;

    Account accountByID (int id) throws SQLException;
}
