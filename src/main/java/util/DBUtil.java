package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DBUtil {

    //在屬性區宣告靜態區塊，引入jdbc driver
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String url = "jdbc:mysql://localhost:3306/myshop?useSSL=false";
    public static String user = "root";
    public static String password = "12345678";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("connection fail.");
        return null;
    }


    //關閉資料庫
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("connection close.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


}