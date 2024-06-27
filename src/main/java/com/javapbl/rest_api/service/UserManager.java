package com.javapbl.rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserManager {

    @Autowired
    private DatabaseManager databaseManager;

    public boolean createUser(String username, String password) {
        String sql = "INSERT INTO users(username, hashedPassword, salt) VALUES(?, ?, ?)";
        String salt = PasswordManager.generateSalt();
        String hashedPassword = PasswordManager.hashPassword(password, salt);

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, salt);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        String sql = "SELECT hashedPassword, salt FROM users WHERE username = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("hashedPassword");
                String salt = rs.getString("salt");
                return PasswordManager.verifyPassword(password, hashedPassword, salt);
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        if (loginUser(username, oldPassword)) {
            String newSalt = PasswordManager.generateSalt();
            String newHashedPassword = PasswordManager.hashPassword(newPassword, newSalt);

            String sql = "UPDATE users SET hashedPassword = ?, salt = ? WHERE username = ?";
            try (Connection conn = databaseManager.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newHashedPassword);
                pstmt.setString(2, newSalt);
                pstmt.setString(3, username);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        else {
            return false;
        }
    }
}