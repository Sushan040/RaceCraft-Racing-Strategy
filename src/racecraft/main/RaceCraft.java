/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

// src/racecraft/main/Main.java
package racecraft.main;

import racecraft.utils.MemoryManager;
import racecraft.view.LoginForm;

public class RaceCraft {
    public static void main(String[] args) {
        MemoryManager.initializeData();
        new LoginForm().setVisible(true);
    }
}

