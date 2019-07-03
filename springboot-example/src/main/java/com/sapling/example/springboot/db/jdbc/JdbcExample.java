package com.sapling.example.springboot.db.jdbc;

import java.sql.*;

/**
 * @author wei.zhou
 * @date 2019/4/8
 * @since 1.0
 */
public class JdbcExample {

    static String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    static String username = "root";
    static String password = "123456";
    static String driverClassName = "com.mysql.cj.jdbc.Driver";
    static String sql = "select * from user";


    public static void main(String[] args) {

        try {
            Class.forName(driverClassName);
            try (
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement pstm = connection.prepareStatement(sql);
                    ResultSet rs = pstm.executeQuery();) {
                ResultSetMetaData metaData = pstm.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (rs.next()) {
                    for (int index = 1; index <= columnCount; index++) {

                        System.out.println(metaData.getColumnName(index) + " -> " + rs.getString(index));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
