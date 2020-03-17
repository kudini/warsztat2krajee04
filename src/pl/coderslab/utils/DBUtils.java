package pl.coderslab.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static String DB_NAME = "warsztat2krajee04";
    private static String DB_URL="jdbc:mariadb://localhost:3306/"+DB_NAME+"?encoding=utf8";
    private static String DB_USER="root";
    private static String DB_PASS="coderslab";

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        return con;
    }
}
