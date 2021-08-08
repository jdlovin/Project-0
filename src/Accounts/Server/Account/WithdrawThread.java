package Accounts.Server.Account;

public class WithdrawThread extends Thread {
    Account account;

    public WithdrawThread(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.withdraw(account.getBalance());
    }
}
