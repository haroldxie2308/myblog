package com.haroldxie.myblog.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCHelper {
    static Connection con = null;
//    Statement stmt = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/HaroldsBlog";  //?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    static final String USER = "root";
    static final String PASS = "localmysql";

    static {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting...");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("HaroldsBlog DB Connected.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static void release(ResultSet set, Statement stmt, Connection connection) throws SQLException {
        if (set != null) {
            set.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}

