package Accounts.Server.Account;

public class AccountDAOFactory {

    private static AccountsDAO dao;

    private AccountDAOFactory(){

    }

    public static AccountsDAO getAccountDao(){
        if(dao == null)
            dao = new AccountsDAOImpl();
        return dao;
    }
}
