package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection _dbConnectionSingleton = null;
    private static Connection _conn = null;
    private boolean _flag = true;

    /**
     * A private Constructor prevents any other class from instantiating.
     */
    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            _flag = false;
        }
        if (_flag) {
            openConnection();
        }
    }

    public Connection openConnection() {
        if (_conn == null) {
            try {
                _conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/master", "root", "");
            } catch (SQLException e) {
                _flag = false;
            }
        }
        return _conn;
    }

    /**
     * Static 'instance' method
     */
    public static DBConnection getInstance() {
        if (_dbConnectionSingleton == null) {
            _dbConnectionSingleton = new DBConnection();
        }
        return _dbConnectionSingleton;
    }

    public boolean getConnectionStatus() {
        return _flag;

    }
}
