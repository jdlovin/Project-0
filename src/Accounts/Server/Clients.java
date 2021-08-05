package Accounts.Server;

import java.sql.*;

public class Clients {
    private static final String CONNECTION_USERNAME = "jdlovin";
    private static final String CONNECTION_PASSWORD =  "Genesis1947$";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/bank";


    public Clients() {
    }
    public void GetClient() throws SQLException {
        //Step: 1 loading driver
//        Class.forName("jdbc:mysql://localhost:3306/squad");

        //Step 2: Create connection object
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "jdlovin", "Genesis1947$");

        //Step 3: Creating statement object
        Statement statement = conn.createStatement();

        //Step 4: Execute Query
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt(1) + ", First Name: " + resultSet.getString(2) + ", Last Name: " + resultSet.getString(3) + ", Email: " +
                    resultSet.getString(4));
        }
        //Step 5: Closing the connection
        conn.close();
    }

}
