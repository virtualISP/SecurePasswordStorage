package com.javapbl.rest_api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javapbl.rest_api.model.ApiResponse;
import com.javapbl.rest_api.service.UserManager;

@RestController
@RequestMapping("/user")

public class DatabaseAPIService {

    @Autowired
    private UserManager userManager;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserRequest userRequest) {
        boolean success = userManager.createUser(userRequest.getUsername(), userRequest.getPassword());
        if (success) {
            return ResponseEntity.ok(new ApiResponse(true, "User created successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "User creation failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody UserRequest userRequest) {
        boolean success = userManager.loginUser(userRequest.getUsername(), userRequest.getPassword());
        if (success) {
            return ResponseEntity.ok(new ApiResponse(true, "Login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "Login failed"));
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        boolean success = userManager.changePassword(
                changePasswordRequest.getUsername(),
                changePasswordRequest.getOldPassword(),
                changePasswordRequest.getNewPassword()
        );
        if (success) {
            return ResponseEntity.ok(new ApiResponse(true, "Password changed successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Password change failed"));
        }
    }

    public static class UserRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class ChangePasswordRequest {
        private String username;
        private String oldPassword;
        private String newPassword;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
}
