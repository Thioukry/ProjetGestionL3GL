package service;

import dao.DBConnection;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthService {

        public static User login(String username, String hashedPassword) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            try (PreparedStatement ps =
                         DBConnection.getConnection().prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setString(2, hashedPassword);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setRole(rs.getString("role"));
                    return u;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

