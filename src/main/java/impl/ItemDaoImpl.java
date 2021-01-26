package impl;

import dao.ItemDao;
import entity.Item;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public List<Item> findAll() {

        ArrayList<Item> items = new ArrayList<Item>();
        Connection conn = DBUtil.getConnection();

        if (conn != null) {
            try {
                Statement statement = conn.createStatement();
                String sqlStr = "select * from items;";
                ResultSet resultSet = statement.executeQuery(sqlStr);

                while (resultSet.next()) {
                    //依造欄位類型取得資料
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    float price = resultSet.getFloat("price");
                    int qty = resultSet.getInt("qty");
                    String info = resultSet.getString("info");
                    Date date = resultSet.getDate("create_date");

                    Item item = new Item(id, name, price, qty, date, info);
                    items.add(item);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        DBUtil.close(conn);
        return items;
    }

    @Override
    public boolean insert(Item item) {
        Connection connection = DBUtil.getConnection();
        if (connection != null) {

            String sqlStr = "insert into items (name,price,qty,create_date,info) values(?,?,?,?,?);";

            try {
                PreparedStatement prepStatement = connection.prepareStatement(sqlStr);
                prepStatement.setString(1, item.getName());
                prepStatement.setFloat(2, item.getPrice());
                prepStatement.setInt(3, item.getQty());
                prepStatement.setDate(4, item.getCreateDate());
                prepStatement.setString(5, item.getInfo());

                prepStatement.execute();
                System.out.println("資料新增完成:\n" + item);
                return true;

            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean update(Item item) {

        Connection connection = DBUtil.getConnection();
        if (connection != null) {
            if (item.getQty() > 0) {
                item.setQty(item.getQty() - 1);
            }

            String sqlStr = "update items set qty=?,info=? where id=?;";
            try {
                PreparedStatement prepStatement = connection.prepareStatement(sqlStr);
                prepStatement.setInt(1, item.getQty());
                if (item.getQty() <= 0) {
                    item.setInfo("需補貨");
                }

                prepStatement.setString(2, item.getInfo());
                prepStatement.setInt(3, item.getId());

                prepStatement.execute();

                System.out.println("資料更新完成:\n" + item);
                return true;

            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
        return false;
    }


}
