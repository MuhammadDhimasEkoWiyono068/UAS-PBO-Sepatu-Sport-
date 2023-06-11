/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sepatusport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class akun {
    final private String username;
    final private String password;
    
    //Konstruktor
    public akun(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    //Menyimpan user ke database akun
    public void saveToDatabase() {
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO akun (username, password) VALUES (?, ?)");
            statement.setString(1, this.username);
            statement.setString(2, this.password);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Memeriksa kecocokan username dan password
    public static boolean isValidUser(String username, String password) {
        boolean isValid = false;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM akun WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isValid = true;
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
    
    //Memeriksa apakah user sudah terdaftar
    public static boolean isUserExists(String username) {
        boolean exists = false;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM akun WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
