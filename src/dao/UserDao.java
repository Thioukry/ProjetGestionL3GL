package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class UserDao {


        public static User login(String username, String password, String s) throws SQLException {

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                return user;
            }
            return null;
        }

        // Ajouter un utilisateur (ADMIN seulement)
        public static void addUser(User user) throws SQLException {
            String sql = "INSERT INTO users(username,password,role) VALUES(?,?,?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());

            ps.executeUpdate();
        }

        // Lister tous les utilisateurs
        public static List<User> getAllUsers() throws SQLException {
            List<User> users = new ArrayList<>();
            Statement st = DBConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setRole(rs.getString("role"));
                users.add(u);
            }
            return users;
        }

        // Supprimer un utilisateur
        public static void deleteUser(int id) throws SQLException {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


