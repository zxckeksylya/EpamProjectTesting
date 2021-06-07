package repository;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionManager {
    INSTANCE;

    private Connection connection;
    private final static String DATABASE_SERVICE = "localhost";
    private final static String DATABASE = "system_of_testing";
    private final static String USER ="root";
    private final static String PASSWORD="root";

    ConnectionManager() {
        try {
            java.sql.DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://"+DATABASE_SERVICE+":3306/"+DATABASE+"?serverTimezone=Europe/Minsk";

            connection = DriverManager.getConnection(url,USER,PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() { return connection; }
}
