/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package racecraft.controller;

import racecraft.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AuthController {

    public User login(String username, String password, String role) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/user.txt"))) { // ensure correct path & name
            String line;

            while ((line = br.readLine()) != null) {
                String[] u = line.split(",");

                if (u.length < 3) continue; // skip invalid lines

                // Trim spaces to avoid whitespace issues
                String fileUsername = u[0].trim();
                String filePassword = u[1].trim();
                String fileRole = u[2].trim();

                if (fileUsername.equals(username.trim()) &&
                    filePassword.equals(password.trim()) &&
                    fileRole.equals(role.trim())) {

                    // Assuming your User constructor: User(String username, String role)
                    return new User(fileUsername, fileRole);
                }
            }

        } catch (IOException e) {
            System.out.println("User file not found: " + e.getMessage());
        }

        return null; // login failed
    }
}

