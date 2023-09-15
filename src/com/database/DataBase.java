package com.database;

import java.sql.SQLException;
import com.config.MyConnection;
import com.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends MyConnection {
    public static List<Product> readDatabase() {
        List<Product> productList = new ArrayList<>();

        MyConnection.connection();
        try {
            String query = "SELECT * FROM tb_produk";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nama = resultSet.getString("NAMA");
                int harga = resultSet.getInt("HARGA");
                int stok = resultSet.getInt("STOK");

                Product product = new Product();
                product.setId(id);
                product.setNama(nama);
                product.setHarga(harga);
                product.setStok(stok);

                productList.add(product);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public static boolean addDataBase(String product, int harga, int stok) {
        connection();
        query = "INSERT INTO tb_produk (NAMA, HARGA, STOK) VALUES (?, ?, ?)";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, product);
            preparedStatement.setInt(2, harga);
            preparedStatement.setInt(3, stok);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static boolean updateDatabase(int id, String nama, int harga, Integer stok) {
        connection();
        try {
            String query = "UPDATE tb_produk SET NAMA=?, HARGA=?, STOK=? WHERE ID=?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setInt(2, harga);
            preparedStatement.setInt(3, stok);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteDatabase(int id) {
        connection();
        query = "DELETE FROM tb_produk WHERE ID = ?";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowDelete = preparedStatement.executeUpdate();
            if (rowDelete > 0) {
                return true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
