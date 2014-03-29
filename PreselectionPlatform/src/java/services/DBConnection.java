package services;

import ManagedBeans.MastersBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    public static String driver = "com.mysql.jdbc.Driver";
    public static String user = "root";
    public static String password = "";
    public static String url = "jdbc:mysql://localhost:3306/master";
    private static Connection connection;

    public static Connection getConnection() {

        if (connection == null) {
            System.out.println("Connecting to db");
            connect();
        }
        return connection;
    }

    private static void connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user,
                    password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MastersBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
