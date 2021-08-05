//package Accounts.Server;
//
//import java.sql.*;
//
//public interface Clients {
//    static final String CONNECTION_USERNAME = "jdlovin";
//    static final String CONNECTION_PASSWORD = "Genesis1947$";
//    static final String CONNECTION_HOST = "jdbc:mysql://localhost:3306/bank";
//    static final String CONNECTION_DATABASE = "bank";
//    static final String CONNECTION_URL = CONNECTION_HOST + CONNECTION_DATABASE;
//
//
//    public default void GetClient() throws SQLException {
//
//
//
//        //Step 2: Create connection object
//        Connection conn = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
//
//
//        //Step 3: Creating statement object
//        Statement statement = conn.createStatement();
//
//        //Step 4: Execute Query
//
//        }
//        //Step 5: Closing the connection
//        conn.close();
//    }
//}
