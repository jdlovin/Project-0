package Accounts.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {

    private static Connection connection = null;
    static final String CONNECTION_USERNAME = "jdlovin";
    static final String CONNECTION_PASSWORD = "Genesis1947$";
    static final String CONNECTION_HOST = "jdbc:mysql://localhost:3306/bank";

    private ConnectionFactory(){
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
//            ResourceBundle bundle = ResourceBundle.getBundle("Accounts/dbConfig.properties");
//            String url = bundle.getString("url");
//            String username = bundle.getString("username");
//            String password = bundle.getString("password");
            connection = DriverManager.getConnection(CONNECTION_HOST, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        }
        return connection;
    }
}
