package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {

    public static void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products(libelle,prix,quantite,user_id) VALUES(?,?,?,?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ps.setString(1, product.getLibelle());
        ps.setDouble(2, product.getPrix());
        ps.setInt(3, product.getQuantite());
        ps.setInt(4, product.getUserId());

        ps.executeUpdate();
    }


        public static List<Product> getProductsByUser(int userId) throws SQLException {
            List<Product> products = new ArrayList<>();
            String sql = "SELECT * FROM products WHERE user_id=?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setLibelle(rs.getString("libelle"));
                p.setPrix(rs.getDouble("prix"));
                p.setQuantite(rs.getInt("quantite"));
                products.add(p);
            }
            return products;
        }
    }

