package Accounts.Server.User;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String passWord;
    private int checking_account_number;

    public User() {

    }

    public User(int id, String firstName, String lastName, String email, String userName, String passWord, int checking_account_number) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
        this.checking_account_number = checking_account_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User: { id: " + id + ", Name: " + firstName + " " + lastName + ", Email: " + email + " }";
    }

    public int getChecking_account_number() {
        return checking_account_number;
    }

    public void setChecking_account_number(int checking_account_number) {
        this.checking_account_number = checking_account_number;
    }
}
