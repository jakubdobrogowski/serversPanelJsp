package pl.sda.java9.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConector {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/adminPanelDB?useSSL=false&serverTimezone=UTC";
    private static final String LOGIN = "user";
    private static final String PASSWORD = "user";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager
                    .getConnection(URL,
                            LOGIN, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
