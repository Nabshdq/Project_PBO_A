package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyConfig {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_produk";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static Connection connect;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void connection() {
        try {
            connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
    }

    public static void createDatabase(Scanner input) {
        connection();

        System.out.print("Nama  : ");
        String namaBaru = input.nextLine();
        System.out.print("Harga : ");
        int hargaBaru = input.nextInt();
        System.out.print("Jumlah : ");
        int jumlahBaru = input.nextInt();

        try {
            statement = connect.createStatement();
            statement.executeUpdate("INSERT INTO `tb_produk` (`ID`, `NAMA`, `HARGA`, `STOK`) VALUES (NULL, '" + namaBaru
                    + "', '" + hargaBaru + "', '" + jumlahBaru + "')");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readDatabase() {
        connection();
        try {
            String query = "SELECT * FROM `tb_produk`";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("ID") + ". " +
                                resultSet.getString("NAMA") +
                                " - Rp." + resultSet.getString("HARGA") +
                                " - Stock:" + resultSet.getString("STOK"));
            }
            resultSet.close(); // Menutup resultSet setelah selesai membacanya
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDatabase(Scanner input) {
        connection();
        try {
            System.out.println("Pilih data yang ingin diubah");
            System.out.println("1. Nama Barang\n2. Harga Barang\n3. Jumlah Stok Barang");

            System.out.print("Pilih: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Membaca karakter newline setelah angka

            System.out.println("=================================================");
            readDatabase();
            System.out.println("=================================================");
            System.out.print("Pilih data yang ingin diedit: ");
            int pilihan2 = input.nextInt();
            input.nextLine(); // Membaca karakter newline setelah angka

            String sql = "";
            statement = connect.createStatement();

            switch (pilihan) {
                case 1:
                    System.out.print("Nama Baru: ");
                    String namaBaru = input.nextLine();

                    sql = "UPDATE tb_produk SET `NAMA` = '" + namaBaru + "' WHERE ID = " + pilihan2;
                    statement.executeUpdate(sql);
                    break;
                case 2:
                    System.out.print("Harga Baru: ");
                    int hargaBaru = input.nextInt();

                    sql = "UPDATE tb_produk SET `HARGA` = '" + hargaBaru + "' WHERE ID = " + pilihan2;
                    statement.executeUpdate(sql);
                    break;
                case 3:
                    System.out.print("Stock Baru: ");
                    int stokBaru = input.nextInt();

                    sql = "UPDATE tb_produk SET `STOK` = '" + stokBaru + "' WHERE ID = " + pilihan2;
                    statement.executeUpdate(sql);
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia");
            }
            System.out.println("Berhasil diupdate");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDatabase(Scanner input) {
        connection();
        try {
            readDatabase();
            System.out.print("Pilih data yang ingin dihapus: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Membaca karakter newline setelah angka

            statement = connect.createStatement();
            String sql = "DELETE FROM tb_produk WHERE ID = " + pilihan;
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}