/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sepatusport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class sepatu {
    private String id;
    private String merek;
    private String tipe;
    private String model;
    private String warna;
    private int ukuran;
    private int harga;
    
    //Konstruktor
    public sepatu(String id, String merek, String tipe, String model, String warna, int ukuran, int harga) {
        this.id = id;
        this.merek = merek;
        this.tipe = tipe;
        this.model = model;
        this.warna = warna;
        this.ukuran = ukuran;
        this.harga = harga;
    }

    //Getter dan Setter
    public String getId() {
        return id;
    }

    public String getMerek() {
        return merek;
    }

    public String getTipe() {
        return tipe;
    }

    public String getModel() {
        return model;
    }

    public String getWarna() {
        return warna;
    }

    public int getUkuran() {
        return ukuran;
    }

    public int getHarga() {
        return harga;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public void setUkuran(int ukuran) {
        this.ukuran = ukuran;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    //Menambahkan sepatu ke database    
    public void saveToDatabase() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Mengambil koneksi dari dbConnection
            connection = dbConnection.getConnection();

            // Membuat statement SQL dengan parameter
            String query = "INSERT INTO sepatu (id, merek, tipe, model, warna, ukuran, harga) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, this.id);
            statement.setString(2, this.merek);
            statement.setString(3, this.tipe);
            statement.setString(4, this.model);
            statement.setString(5, this.warna);
            statement.setInt(6, this.ukuran);
            statement.setInt(7, this.harga);

            // Menjalankan pernyataan SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup statement dan koneksi
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Update sepatu ke database 
    public void updateInDatabase() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Mengambil koneksi dari dbConnection
            connection = dbConnection.getConnection();

            // Membuat statement SQL dengan parameter
            String query = "UPDATE sepatu SET merek = ?, tipe = ?, model = ?, warna = ?, ukuran = ?, harga = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, this.merek);
            statement.setString(2, this.tipe);
            statement.setString(3, this.model);
            statement.setString(4, this.warna);
            statement.setInt(5, this.ukuran);
            statement.setInt(6, this.harga);
            statement.setString(7, this.id);

            // Menjalankan pernyataan SQL
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("ID not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup statement dan koneksi
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Hapus sepatu dari database 
    public void deleteFromDatabase(String id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Mengambil koneksi dari dbConnection
            connection = dbConnection.getConnection();

            // Membuat statement SQL dengan parameter
            String query = "DELETE FROM sepatu WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, this.id);

            // Menjalankan pernyataan SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup statement dan koneksi
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Memeriksa apakah sepatu sudah terdaftar
    public static boolean isSepatuExists(String id) {
        boolean exists = false;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sepatu WHERE id = ?");
            statement.setString(1, id);
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
