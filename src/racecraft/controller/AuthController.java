/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.controller;

import racecraft.model.User;
import racecraft.utils.MemoryManager;
import racecraft.view.AdminDashboard;
import racecraft.view.UserDashboard;

import javax.swing.*;
import racecraft.utils.DataValidator;

public class AuthController {

    public static void login(String username, String password, String selectedRole, JFrame loginFrame) {
        for (User user : MemoryManager.getUsers()) {
            if (user.getUsername().equals(username) 
                && user.getPassword().equals(password)
                && user.getRole().equalsIgnoreCase(selectedRole)) {
                
                loginFrame.dispose();
                if (user.getRole().equals("ADMIN")) {
                    new AdminDashboard().setVisible(true);
                } else {
                    new UserDashboard().setVisible(true);
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, 
            "Invalid username, password, or role selected!", 
            "Login Failed", 
            JOptionPane.ERROR_MESSAGE);
    }        

    public static void register(String username, String password, String role) {
        if (DataValidator.isEmpty(username) || DataValidator.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "Fields cannot be empty");
            return;
        }
        // Check for duplicate username
        for (User u : MemoryManager.getUsers()) {
            if (u.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(null, "Username exists");
                return;
            }
        }
        MemoryManager.getUsers().add(new User(username, password, role));
        JOptionPane.showMessageDialog(null, "Registration successful");
    }
}