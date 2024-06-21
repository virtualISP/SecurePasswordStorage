package org.example;
import java.util.Scanner;

public class Main {
    private static final UserManager userManager = new UserManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create User");
            System.out.println("2. Login User");
            System.out.println("3. Change Password");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (option) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (userManager.createUser(username, password)) {
                        System.out.println("User created successfully.");
                    } else {
                        System.out.println("Username already exists.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (userManager.loginUser(loginUsername, loginPassword)) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter username: ");
                    String changeUsername = scanner.nextLine();
                    System.out.print("Enter old password: ");
                    String oldPassword = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    if (userManager.changePassword(changeUsername, oldPassword, newPassword)) {
                        System.out.println("Password changed successfully.");
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                }
                case 4 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
