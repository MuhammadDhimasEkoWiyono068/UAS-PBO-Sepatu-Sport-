/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sepatusport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class dbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sepatusport";
    //private static final String USERNAME = "username_mysql";
    //private static final String PASSWORD = "password_mysql";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            //connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection = DriverManager.getConnection(URL);
            System.out.println("Database Connected");
        } catch (SQLException e) {
            System.out.println("Database Connection Failed");
            e.printStackTrace();
        }
        return connection;
    }
}
