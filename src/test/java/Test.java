import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("jdbc driver is ready.");

            String url = "jdbc:mysql://localhost:3306/myshop";
            String user = "root";
            String password = "12345678";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);


            //連續加入資料
            Statement statement = connection.createStatement();
            for (int i = 0; i < 10; i++) {
                String sqlStr = String.format("insert into customers(name,password,phone) values ('%s','%s','%s')",
                        "陳小" + i, "12345678", "093456756" + i);
                statement.addBatch(sqlStr);
            }

            System.out.println(Arrays.toString(statement.executeBatch()));


            String sqlStr = "insert into customers(name,password,phone) values ('Tony','12312312','0988888888')";
            int result = statement.executeUpdate(sqlStr);
            System.out.println(result + "筆資料新增完成");


            sqlStr = "update items set info='無備註' where info is null";
            result = statement.executeUpdate(sqlStr);
            System.out.println(result + "筆資料更新成功!");


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("close.");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
